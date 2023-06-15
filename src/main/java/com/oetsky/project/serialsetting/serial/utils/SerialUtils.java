package com.oetsky.project.serialsetting.serial.utils;

import cn.hutool.system.SystemUtil;
import com.oetsky.common.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * @author zhangw
 */
public final class SerialUtils {

    private SerialUtils() {

    }
    public static String serialName = "serial";
    public static final Logger LOGGER = LoggerFactory.getLogger(SerialUtils.class);

    /**
     * bytes[] 转 hex string
     *
     * @param bytes 字节数组
     * @return 16进制字符串
     */
    public static String binaryToHexString(byte[] bytes) {
        String hexStr = "0123456789ABCDEF";
        String result = "";
        String hex = "";
        for (byte b : bytes) {
            hex = String.valueOf(hexStr.charAt((b & 0xF0) >> 4));
            hex += String.valueOf(hexStr.charAt(b & 0x0F));
            result += hex + " ";
        }
        return result.replace(" ", "");
    }

    /**
     * 把16进制字符串转换成字节数组
     *
     * @param hexStr 6进制字符串
     * @return 字节数组
     */
    public static byte[] hexStringToByte(String hexStr) {
        int len = (hexStr.length() / 2);
        byte[] result = new byte[len];
        char[] achar = hexStr.toCharArray();
        for (int i = 0; i < len; i++) {
            int pos = i * 2;
            result[i] = (byte) (toByte(achar[pos]) << 4 | toByte(achar[pos + 1]));
        }
        return result;
    }

    public static int toByte(char c) {
        byte b = (byte) "0123456789ABCDEF".indexOf(c);
        return b;
    }


    /**
     * 通过别名查找真实串口号
     *
     * @param serialName
     * @return
     */
    public static String realSerialName(String serialName) {
        String realSerialName = serialName;
        InputStream is = null;
        InputStreamReader inputStreamReader = null;
        BufferedReader reader = null;
        try {
            if (realSerialName.startsWith("/dev/ttyRS") && SystemUtil.getOsInfo().isLinux()) {
                // 读取真实串口名
                // 程序执行，核心代码
                String command = "readlink -f " + realSerialName;
                Process process = Runtime.getRuntime().exec(command);
                is = process.getInputStream();
                inputStreamReader = new InputStreamReader(is);
                reader = new BufferedReader(inputStreamReader);
                String line = reader.readLine();
                if (StringUtils.isNotEmpty(line) && !realSerialName.equals(line)) {
                    LOGGER.info(
                            "---------------------" + realSerialName + "对应的真实串口名：" + line);
                    realSerialName = line;
                }
                // 等待执行结果,0表示执行成功
                process.waitFor();
            }
        } catch (Exception e) {
            LOGGER.error("realSerialName find error!", e);
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
                if (inputStreamReader != null) {
                    inputStreamReader.close();
                }
                if (is != null) {
                    is.close();
                }
            } catch (IOException e) {
                LOGGER.error("close io fail ", e);
            }
        }
        return realSerialName;
    }
}
