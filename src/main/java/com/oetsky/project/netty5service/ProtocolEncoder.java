package com.oetsky.project.netty5service;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * 编码器(16进制字符串)
 *
 * @author GLR
 * @date
 */
public class ProtocolEncoder extends MessageToByteEncoder<String> {

    @Override
    protected void encode(ChannelHandlerContext ctx, String msg, ByteBuf out) throws Exception {
        //对传输内容加密后传输
        byte[] contentBytes = hexStringToByte(msg);
        // 写入消息主体
        out.writeBytes(contentBytes);
    }

    /**
     * 把16进制字符串转换成字节数组
     * @param hexStr
     * @return byte[]
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
}

