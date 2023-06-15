package com.oetsky.project.serialsetting.serial;

import com.oetsky.project.serialsetting.serial.utils.SerialUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import purejavacomm.*;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @author zhangw
 */
public class SerialHelper extends Thread implements SerialPortEventListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(SerialHelper.class);

    // 保存
    private static final ConcurrentHashMap<String, SerialHelper> SERIALS = new ConcurrentHashMap<>(
            8);

    /**
     * 串口标识，例如：COM1
     */
    private String appName;
    /**
     * 波特率
     */
    private int baudRate;
    /**
     * 数据位
     */
    private int dataBit;
    /**
     * 停止位
     */
    private int stopBit;
    /**
     * 校验位
     */
    private int parityBit;

    /**
     * 串口实例
     */
    private SerialPort serialPort;

    /**
     * 读模式下是否异步处理
     */
    private boolean asyncRead;

    /**
     * 数据处理线程终止标识
     */
    private volatile boolean message_thread_status = true;

    /**
     * 串口输入流
     */
    private InputStream inputStream;
    /**
     * 串口输出流
     */
    private OutputStream outputStream;

    /**
     * 数据处理接口
     */
    private SerialMessageHandle messageHandle;

    /**
     * 二进制转换接口
     */
    private BinaryConverter binaryConverter;

    /**
     * 读模式下，异步数据队列
     */
    private LinkedBlockingQueue messageQueue;

    /**
     * 串口连接超时限制
     */
    private int timeout = 5000;

    public SerialHelper(String appName, int baudRate, int dataBit, int stopBit, int parityBit,
                        int timeout, BinaryConverter binaryConverter, SerialMessageHandle messageHandle,
                        boolean asyncRead) {
        init(appName, baudRate, dataBit, stopBit, parityBit, timeout, binaryConverter,
                messageHandle, asyncRead);
    }

    private void init(String appName, int baudRate, int dataBit, int stopBit, int parityBit,
                      int timeout, BinaryConverter binaryConverter, SerialMessageHandle messageHandle,
                      Boolean async) {
        this.setName("Thread-" + appName);
        this.appName = appName;
        this.baudRate = baudRate;
        this.dataBit = dataBit;
        this.stopBit = stopBit;
        this.parityBit = parityBit;
        this.timeout = timeout;
        this.messageHandle = messageHandle;
        this.binaryConverter = binaryConverter;
        this.asyncRead = async;
    }

    public void openSerial(SerialModeEnum mode)
            throws NoSuchPortException, PortInUseException, NotSerialPortException {
        //获取真实串口名
        String realSerialName = SerialUtils.realSerialName(this.appName);
        CommPortIdentifier portIdentifier = CommPortIdentifier.getPortIdentifier(realSerialName);
        CommPort commPort = portIdentifier.open(realSerialName, this.timeout);
        // 判断是不是串口
        if (commPort instanceof SerialPort) {
            try {
                serialPort = (SerialPort) commPort;
                // 设置波特率等参数
                serialPort.setSerialPortParams(baudRate, dataBit, stopBit, parityBit);
                SERIALS.put(this.appName, this);
                if (mode == SerialModeEnum.READ) {
                    inputStream = commPort.getInputStream();
                    serialPort.addEventListener(this);
                    serialPort.notifyOnDataAvailable(true);
                    this.beginThread();
                } else if (mode == SerialModeEnum.WRITE) {
                    outputStream = commPort.getOutputStream();
                } else {
                    outputStream = commPort.getOutputStream();
                    inputStream = commPort.getInputStream();
                    serialPort.addEventListener(this);
                    serialPort.notifyOnDataAvailable(true);
                    this.beginThread();
                }
            } catch (Exception e) {
                this.message_thread_status = false;
                this.closeSerial();
                throw new RuntimeException();
            }
        } else {
            this.message_thread_status = false;
            commPort.close();
            throw new NotSerialPortException(appName);
        }
    }

    private void beginThread() {
        if (asyncRead) {
            messageQueue = new LinkedBlockingQueue<>();
            this.start();
        }
    }

    public void closeSerial() {
        if (SERIALS.containsKey(appName)) {
            SERIALS.remove(this.appName);
            this.message_thread_status = false;
            if (this.serialPort != null) {
                this.serialPort.close();
            }
            if (this.inputStream != null) {
                try {
                    this.inputStream.close();
                } catch (IOException e) {
                    LOGGER.error("串口输入流关闭异常", e);
                }
            }
            if (this.outputStream != null) {
                try {
                    this.outputStream.close();
                } catch (IOException e) {
                    LOGGER.error("串口输出流关闭异常", e);
                }
            }
        } else {
            LOGGER.warn("当前串口【{}】未打开", this.appName);
        }
    }

    public static void close(String appName) {
        SerialHelper helper = SERIALS.get(appName);
        if (helper != null) {
            helper.closeSerial();
        } else {
            LOGGER.warn("当前串口【{}】无实例", appName);
        }
    }

    public void writeData(byte[] data, String desc) {
        try {
            this.outputStream.write(data);
            this.outputStream.flush();
        } catch (IOException e) {
            LOGGER.error("串口【{}】发送【{}】失败,", this.appName, desc, e);
        }
    }

    public static void write(String appName, byte[] data, String desc) throws IOException {
        SerialHelper helper = SERIALS.get(appName);
        if (helper != null) {
            helper.writeData(data, desc);
        }
    }

    @Override
    public void serialEvent(SerialPortEvent serialPortEvent) {
        // 当有可用数据时读取数据
        // 数据状态不可读取
        if (serialPortEvent.getEventType() == SerialPortEvent.DATA_AVAILABLE) {
            try {
                Object msg = binaryConverter.convert(inputStream);
                if (asyncRead) {
                    messageQueue.add(msg);
                } else {
                    messageHandle.handle(msg, this);
                }
            } catch (Exception e) {
                LOGGER.error("监听串口数据发生异常", e);
            }
        }
    }

    @Override
    public void run() {
        while (message_thread_status) {
            Object data;
            try {
                data = messageQueue.poll(10, TimeUnit.MILLISECONDS);
                if (data != null) {
                    messageHandle.handle(data, this);
                }
            } catch (InterruptedException e) {
                LOGGER.error("串口数据处理异常", e);
            }
        }
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

}
