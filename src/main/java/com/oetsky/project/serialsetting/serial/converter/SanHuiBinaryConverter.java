package com.oetsky.project.serialsetting.serial.converter;

import cn.hutool.core.convert.Convert;
import com.oetsky.common.frame.utils.ProtocolUtils;
import com.oetsky.project.serialsetting.serial.BinaryConverter;
import com.oetsky.project.serialsetting.serial.utils.SerialUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * @author zhangw
 */
public class SanHuiBinaryConverter implements BinaryConverter {

    private static final Logger LOGGER = LoggerFactory.getLogger(SanHuiBinaryConverter.class);

    private volatile StringBuffer tempFrameData = new StringBuffer();

    @Override
    public Object convert(InputStream inputStream) {
        return this.serial698DecoderFrames(inputStream);
    }

    public List<String> serial698DecoderFrames(InputStream inputStream) {
        List<String> dataList = new ArrayList<>();
        int numBytes = 0;
        try {
            numBytes = inputStream.available();
        } catch (IOException e) {
            LOGGER.error("【DLT-698】串口inputStream读取错误");
            tempFrameData.setLength(0);
        }
        while (numBytes > 0) {
            try {
                byte[] readBuffer = new byte[numBytes];
                numBytes = inputStream.read(readBuffer);
                numBytes = -1;
                tempFrameData.append(
                    SerialUtils.binaryToHexString(readBuffer).toUpperCase(Locale.ENGLISH));
                while (true) {
                    for (int i = 0; i < 4; i++) {
                        if (tempFrameData.length() >= 2 && "FE".equals(
                            tempFrameData.substring(0, 2))) {
                            ProtocolUtils.subFrameStr(tempFrameData, 1);
                            continue;
                        }
                        break;
                    }
                    if (tempFrameData.length() < 2) {
                        break;
                    }
                    if (ProtocolUtils.FRAME_HEADER_68.equals(tempFrameData.substring(0, 2))) {
                        if (tempFrameData.length() > 6) {
                            int frameLength = Convert.toInt(ProtocolUtils.hex16ToStr(
                                ProtocolUtils.formatHighLow(tempFrameData.substring(2, 6)))) * 2
                                + 4;
                            if (tempFrameData.length() > frameLength) {
                                // 如果数据长度大于数据帧长度
                                String subFrameStr = ProtocolUtils.subFrameStr(tempFrameData,
                                    frameLength / 2);
                                // SerialDomain.getSerialMsgListener(ifsVo.getSerialNumber())
                                //     .notifyObserver(System.currentTimeMillis(), subFrameStr);
                                dataList.add(subFrameStr);
                                // todo updateReceiveDate(); 记录收取消息的时间
                                Thread.sleep(100);
                                // 继续循环下一次获取未取完数据
                                continue;
                            } else if (tempFrameData.length() == frameLength) {
                                // 如果数据长度与数据帧相同
                                // SerialDomain.getSerialMsgListener(ifsVo.getSerialNumber())
                                //     .notifyObserver(System.currentTimeMillis(),
                                //         tempFrameData.toString());
                                dataList.add(tempFrameData.toString());
                                tempFrameData.setLength(0);
                                // todo updateReceiveDate(); 记录收取消息的时间
                                Thread.sleep(100);
                                // 退出本次获取数据
                                break;
                            } else {
                                // 如果小于数据帧，退出循环等待下次进入
                                break;
                            }
                        } else {
                            // 如果小于数据帧，退出循环等待下次进入
                            break;
                        }
                    } else {
                        if (tempFrameData.length() > 0) {
                            LOGGER.error(
                                "【DLT-698】串口数据拆包错误,首字符不为【{}】!!!剩余数据为[{}]",
                                ProtocolUtils.FRAME_HEADER_68, tempFrameData.toString());
                        }
                        tempFrameData.setLength(0);
                    }
                }
            } catch (Exception e) {
                if (tempFrameData != null) {
                    if (tempFrameData.length() > 0) {
                        LOGGER.error("【DLT-698】串口数据拆包错误!!!剩余数据为[{}]", tempFrameData);
                    }
                    tempFrameData.setLength(0);
                }
                LOGGER.error("数据拆包错误", e);
            }
        }
        return dataList;
    }

}
