package com.oetsky.project.netty5service;

import cn.hutool.core.convert.Convert;
import com.oetsky.common.frame.utils.ProtocolUtils;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import java.util.List;
import java.util.Locale;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author xiangzc
 * @date 2022-1-12
 */
public class Netty698Decoder extends ByteToMessageDecoder {
    private static final Logger LOGGER = LoggerFactory.getLogger(Netty698Decoder.class);
    private static final String FRAME_HEADER_68 = "68";
    private StringBuffer builder = new StringBuffer();

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf buffer, List<Object> out) throws Exception {
        try {
            //创建字节数组,buffer.readableBytes可读字节长度
            byte[] b = new byte[buffer.readableBytes()];
            //复制内容到字节数组b
            buffer.readBytes(b);
            builder.append(Convert.toStr(bytesToHexString(b)).toUpperCase(Locale.ENGLISH));
            while (true) {
                // 获取帧头
                String frameHeader = builder.substring(0, 2);
                // 判断数据是否为有效数据，如不为有效数据则丢
                if (ProtocolUtils.FRAME_HEADER_68.equals(frameHeader)) {
                    // 取报文 2 - 6 位为报文长度，高低位转换   16进制转字符串  报文长度 * 2 后+10位(长度是从控制域到数据域结束帧，
                    // 不包含帧头、长度、CS、结束帧共5字节10位数据的)
                    Integer frameLength = Convert.toInt(
                        ProtocolUtils.hex16ToStr(ProtocolUtils.formatHighLow(builder.substring(2, 6)))) * 2 + 10;
                    try {
                        // 判断当前数据帧是否大于等于   报文长度  如果大于，则进行拆包
                        if (builder.length() > frameLength) {
                            String subFrameStr = ProtocolUtils.subFrameStr(builder, frameLength / 2);
                            out.add(subFrameStr);
                            Thread.sleep(0);
                        } else if (builder.length() == frameLength) {
                            // 如果等于则发送，并清空
                            out.add(builder.toString());
                            builder.setLength(0);
                            break;
                        } else {
                            break;
                        }
                    } catch (Exception e) {
                        if (builder.length() > 0) {
                            LOGGER.error("【Netty】数据拆包错误!!!剩余数据为[{}]", builder.toString());
                        }
                        builder.setLength(0);
                    }
                } else {
                    if (builder.length() > 0) {
                        LOGGER.error("【Netty】数据拆包错误,首字符不为【{}】!!!剩余数据为[{}]", ProtocolUtils.FRAME_HEADER_68, builder.toString());
                    }
                    builder.setLength(0);
                }
            }
        } catch (Exception e) {
            builder.setLength(0);
        }
    }

    public String bytesToHexString(byte[] bArray) {
        StringBuffer sb = new StringBuffer(bArray.length);
        String sTemp;
        for (int i = 0; i < bArray.length; i++) {
            sTemp = Integer.toHexString(0xFF & bArray[i]);
            if (sTemp.length() < 2) {
                sb.append(0);
            }
            sb.append(sTemp.toUpperCase());
        }
        return sb.toString();
    }
}