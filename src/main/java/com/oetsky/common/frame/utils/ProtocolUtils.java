package com.oetsky.common.frame.utils;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import com.oetsky.common.frame.enums.BoardType;
import com.oetsky.common.frame.enums.FrameColumnFormat;
import com.oetsky.common.frame.enums.StationType;
import com.oetsky.common.utils.StringUtils;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Random;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author xiangzc 通讯协议工具类
 */
public class ProtocolUtils {

    public static final Logger LOGGER = LoggerFactory.getLogger(ProtocolUtils.class);

    /**
     * 帧头
     */
    public static final String FRAME_HEADER_68 = "68";

    /**
     * 帧尾
     */
    public static final String FRAME_FOOTER_16 = "16";
    /**
     * 终端事件信息上报 上位机 确认
     */
    public static final String FRAME_UPPER_COMPUTER_UP = "00";
    /**
     * 终端事件信息上报 上位机 否认
     */
    public static final String FRAME_UPPER_COMPUTER_UP_NOT = "01";
    /**
     * 上行报文控制域 设置类 否认
     */
    public static final String FRAME_SET_UP_NOT = "81";
    /**
     * 上行报文控制域 无需回复类
     */
    public static final String FRAME_UP_ELECTRICITY_NOTIN = "83";
    /**
     * 上行报文控制域 无需回复类
     */
    public static final String FRAME_UP_GATHER_NOTIN = "84";
    /**
     * 上行报文控制域 读取类有数据
     */
    public static final String FRAME_GET_UP = "88";
    /**
     * 上行报文控制域 读取类无数据
     */
    public static final String FRAME_GET_UP_NOT = "89";

    /**
     * 长度字段预值
     */
    public static final String FRAME_LENGTH = "#CD#";

    public static final String[] FRAME_PHASE = {"0", "A", "B", "C", "A", "B", "C", "A", "B", "C",
        "A", "B", "C", "A", "B", "C"};
    /**
     * 数据域 帧 二进制 最高位 0为正 1为负
     */
    public static final String POSITIVE_0 = "0";
    /**
     * 数据域 帧 二进制 最高位 0为正 1为负
     */
    public static final String NEGATIVE_1 = "1";
    static int[] fcstab = new int[]{
        0x0000, 0x1189, 0x2312, 0x329b, 0x4624, 0x57ad, 0x6536, 0x74bf,
        0x8c48, 0x9dc1, 0xaf5a, 0xbed3, 0xca6c, 0xdbe5, 0xe97e, 0xf8f7,
        0x1081, 0x0108, 0x3393, 0x221a, 0x56a5, 0x472c, 0x75b7, 0x643e,
        0x9cc9, 0x8d40, 0xbfdb, 0xae52, 0xdaed, 0xcb64, 0xf9ff, 0xe876,
        0x2102, 0x308b, 0x0210, 0x1399, 0x6726, 0x76af, 0x4434, 0x55bd,
        0xad4a, 0xbcc3, 0x8e58, 0x9fd1, 0xeb6e, 0xfae7, 0xc87c, 0xd9f5,
        0x3183, 0x200a, 0x1291, 0x0318, 0x77a7, 0x662e, 0x54b5, 0x453c,
        0xbdcb, 0xac42, 0x9ed9, 0x8f50, 0xfbef, 0xea66, 0xd8fd, 0xc974,
        0x4204, 0x538d, 0x6116, 0x709f, 0x0420, 0x15a9, 0x2732, 0x36bb,
        0xce4c, 0xdfc5, 0xed5e, 0xfcd7, 0x8868, 0x99e1, 0xab7a, 0xbaf3,
        0x5285, 0x430c, 0x7197, 0x601e, 0x14a1, 0x0528, 0x37b3, 0x263a,
        0xdecd, 0xcf44, 0xfddf, 0xec56, 0x98e9, 0x8960, 0xbbfb, 0xaa72,
        0x6306, 0x728f, 0x4014, 0x519d, 0x2522, 0x34ab, 0x0630, 0x17b9,
        0xef4e, 0xfec7, 0xcc5c, 0xddd5, 0xa96a, 0xb8e3, 0x8a78, 0x9bf1,
        0x7387, 0x620e, 0x5095, 0x411c, 0x35a3, 0x242a, 0x16b1, 0x0738,
        0xffcf, 0xee46, 0xdcdd, 0xcd54, 0xb9eb, 0xa862, 0x9af9, 0x8b70,
        0x8408, 0x9581, 0xa71a, 0xb693, 0xc22c, 0xd3a5, 0xe13e, 0xf0b7,
        0x0840, 0x19c9, 0x2b52, 0x3adb, 0x4e64, 0x5fed, 0x6d76, 0x7cff,
        0x9489, 0x8500, 0xb79b, 0xa612, 0xd2ad, 0xc324, 0xf1bf, 0xe036,
        0x18c1, 0x0948, 0x3bd3, 0x2a5a, 0x5ee5, 0x4f6c, 0x7df7, 0x6c7e,
        0xa50a, 0xb483, 0x8618, 0x9791, 0xe32e, 0xf2a7, 0xc03c, 0xd1b5,
        0x2942, 0x38cb, 0x0a50, 0x1bd9, 0x6f66, 0x7eef, 0x4c74, 0x5dfd,
        0xb58b, 0xa402, 0x9699, 0x8710, 0xf3af, 0xe226, 0xd0bd, 0xc134,
        0x39c3, 0x284a, 0x1ad1, 0x0b58, 0x7fe7, 0x6e6e, 0x5cf5, 0x4d7c,
        0xc60c, 0xd785, 0xe51e, 0xf497, 0x8028, 0x91a1, 0xa33a, 0xb2b3,
        0x4a44, 0x5bcd, 0x6956, 0x78df, 0x0c60, 0x1de9, 0x2f72, 0x3efb,
        0xd68d, 0xc704, 0xf59f, 0xe416, 0x90a9, 0x8120, 0xb3bb, 0xa232,
        0x5ac5, 0x4b4c, 0x79d7, 0x685e, 0x1ce1, 0x0d68, 0x3ff3, 0x2e7a,
        0xe70e, 0xf687, 0xc41c, 0xd595, 0xa12a, 0xb0a3, 0x8238, 0x93b1,
        0x6b46, 0x7acf, 0x4854, 0x59dd, 0x2d62, 0x3ceb, 0x0e70, 0x1ff9,
        0xf78f, 0xe606, 0xd49d, 0xc514, 0xb1ab, 0xa022, 0x92b9, 0x8330,
        0x7bc7, 0x6a4e, 0x58d5, 0x495c, 0x3de3, 0x2c6a, 0x1ef1, 0x0f78
    };
    static int PPPINITFCS16 = 0xffff;

    /**
     * 截取帧长度
     *
     * @param frameStr      数据帧
     * @param contentLength 数据位
     * @return
     */
    public static String subFrameStr(StringBuffer frameStr, Integer contentLength) {
        String str = "";
        if (contentLength > 0) {
            Integer length = contentLength * 2;
            str = frameStr.substring(0, length);
            frameStr = frameStr.delete(0, length);
        }
        return str;
    }

    /**
     * 补充16进制字节数
     *
     * @param hexStr     16进制
     * @param numberByte 字节数
     * @return
     */
    public static String supplementaryHex(String hexStr, Integer numberByte) {
        if (!StringUtils.isEmpty(hexStr) && numberByte > 0) {
            String str = hexStr;
            while (true) {
                if ((str.length() / 2) < numberByte) {
                    str = "00" + str;
                    continue;
                }
                return str;
            }
        }
        return hexStr;
    }

    /**
     * 补充字符串长度（默认补0）
     *
     * @param strValue  字符串
     * @param strLength 字符串长度
     * @return
     */
    public static String supplementaryDefaultStr(String strValue, Integer strLength) {
        if (!StringUtils.isEmpty(strValue) && strLength > 0) {
            String str = strValue;
            while (true) {
                if (str.length() < strLength) {
                    str = "0" + str;
                    continue;
                }
                return str;
            }
        }
        return strValue;
    }

    /**
     * 10进制转换为16进制
     *
     * @param farmat
     * @return
     */
    public static String strToHex16(String farmat) {
        String a = "00";
        Long aLong = Convert.toLong(farmat);
        if (aLong < Integer.MAX_VALUE) {
            a = Integer.toHexString(Convert.toInt(farmat));
        } else {
            a = Long.toHexString(aLong);
        }
        if (a.length() % IntegerUtil.INTEGER_2 == 1) {
            a = "0" + a;
        }
        return a;
    }

    /**
     * 10进制转换为16进制
     *
     * @param farmat
     * @return
     */
    public static String intToHex16(Integer farmat) {
        String a = Integer.toHexString(farmat);
        if (a.length() % IntegerUtil.INTEGER_2 == IntegerUtil.INTEGER_1) {
            a = "0" + a;
        }
        return a;
    }

    /**
     * 16进制转换为10进制
     *
     * @param farmat
     * @return
     */
    public static String hex16ToStr(String farmat) {
        if (StringUtils.isNotEmpty(farmat)) {
            BigInteger bigint = new BigInteger(farmat, IntegerUtil.INTEGER_16);
            String a = Convert.toStr(bigint.intValue());
            if (a.length() % IntegerUtil.INTEGER_2 == IntegerUtil.INTEGER_1) {
                a = "0" + a;
            }
            return a;
        }
        return "";
    }

    /**
     * 十进制转二进制
     *
     * @param n
     */
    public static String binaryTo2Decimal(int n) {
        String str = "";
        while (n != IntegerUtil.INTEGER_0) {
            str = n % IntegerUtil.INTEGER_2 + str;
            n = n / IntegerUtil.INTEGER_2;
        }
        if (str.length() % IntegerUtil.INTEGER_2 == IntegerUtil.INTEGER_1) {
            str = "0" + str;
        }
        return str;
    }

    /**
     * 二进制转十进制
     *
     * @param str
     */
    public static int binaryTodecimal2(String str) {
        int in = Integer.parseInt(str, IntegerUtil.INTEGER_2);
        return in;
    }

    /**
     * 将二进制转换成16进制
     *
     * @param binary
     * @return
     */
    public static String parseByte2HexStr(String binary) {
        String[] hexStr = {"0", "1", "2", "3", "4", "5", "6", "7", "8",
            "9", "A", "B", "C", "D", "E", "F"};
        // 这里还可以做些判断，比如传进来的数字是否都是0和1
        int length = binary.length();
        int temp = length % IntegerUtil.INTEGER_4;
        // 每四位2进制数字对应一位16进制数字
        // 补足4位
        if (temp != IntegerUtil.INTEGER_0) {
            for (int i = 0; i < IntegerUtil.INTEGER_4 - temp; i++) {
                binary = "0" + binary;
            }
        }
        // 重新计算长度
        length = binary.length();
        StringBuilder sb = new StringBuilder();
        // 每4个二进制数为一组进行计算
        for (int i = IntegerUtil.INTEGER_0; i < length / IntegerUtil.INTEGER_4; i++) {
            int num = IntegerUtil.INTEGER_0;
            // 将4个二进制数转成整数
            for (int j = i * IntegerUtil.INTEGER_4;
                j < i * IntegerUtil.INTEGER_4 + IntegerUtil.INTEGER_4; j++) {
                // 左移
                num <<= IntegerUtil.INTEGER_1;
                // 或运算
                num |= (binary.charAt(j) - '0');
            }
            // 直接找到该整数对应的16进制，这里不用switch来做
            sb.append(hexStr[num]);
        }
        return sb.toString();
    }

    /**
     * 将16进制转换为二进制
     *
     * @param hexStr
     * @return
     */
    public static String parseHexStr2Byte(String hexStr) {
        if (StringUtils.isEmpty(hexStr) || hexStr.length() < 1) {
            return null;
        }
        String s = Integer.toBinaryString(Integer.valueOf(hexStr, 16));
        Integer count = 8 - s.length();
        if (count > 0) {
            for (int i = 0; i < count; i++) {
                s = "0" + s;
            }
        }
        return s;
//        byte[] result = new byte[hexStr.length() / 2];
//        for (int i = 0; i < hexStr.length() / 2; i++) {
//            int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 16);
//            int low = Integer.parseInt(hexStr.substring(i * 2 + 1, i * 2 + 2),
//                    16);
//            result[i] = (byte) (high * 16 + low);
//        }
//        return result;
    }

    /**
     * 获取CS校验码
     *
     * @param hexData
     * @return
     */
    public static String getCs(String hexData) {
        if (hexData == null || "".equals(hexData)) {
            return "00";
        }
        hexData = hexData.replaceAll(" ", "");
        int total = IntegerUtil.INTEGER_0;
        int len = hexData.length();
        if (len % IntegerUtil.INTEGER_2 != IntegerUtil.INTEGER_0) {
            return "00";
        }
        int num = 0;
        while (num < len) {
            String s = hexData.substring(num, num + IntegerUtil.INTEGER_2);
            total += Integer.parseInt(s, IntegerUtil.INTEGER_16);
            num = num + 2;
        }
        String hexStr = hexInt(total);
        return hexStr.substring(hexStr.length() - IntegerUtil.INTEGER_2)
            .toUpperCase(Locale.ENGLISH);
    }

    private static String hexInt(int total) {
        int a = total / IntegerUtil.INTEGER_256;
        int b = total % IntegerUtil.INTEGER_256;
        if (a > IntegerUtil.INTEGER_255) {
            return hexInt(a) + format(b);
        }
        return format(a) + format(b);
    }

    private static String format(int hex) {
        String hexa = Integer.toHexString(hex);
        int len = hexa.length();
        if (len < IntegerUtil.INTEGER_2) {
            hexa = "0" + hexa;
        }
        return hexa;
    }

    /**
     * 根据报文内容拼接完整报文
     *
     * @param frameStr
     * @return
     */
    public static String frameContentDataStr(String frameStr) {
        StringBuilder sb = new StringBuilder();
        // 超始帧
        sb.append(ProtocolUtils.FRAME_HEADER_68);
        // 长度
        String frameLength = ProtocolUtils.formatHighLow(
            ProtocolUtils.supplementaryHex(ProtocolUtils.intToHex16(frameStr.length() / 2), 2));
        sb.append(frameLength);
        // 数据域
        sb.append(frameStr);
        // 校验帧
        sb.append(ProtocolUtils.getCs(frameLength + frameStr));
        // 尾帧
        sb.append(ProtocolUtils.FRAME_FOOTER_16);
        return sb.toString().toUpperCase(Locale.ENGLISH);
    }

    /**
     * 调用公共方法生成请求响应
     * @param frameCode
     * @param frameType
     * @param content
     * @return
     */
    public static String publicRequest(String frameCode,String frameType, String content) {
        StringBuilder sb = new StringBuilder();
        // 控制域
        sb.append(frameCode);
        // 帧ID
        sb.append(ProtocolUtils.supplementaryHex(ProtocolUtils.intToHex16(IntegerUtil.INTEGER_1),IntegerUtil.INTEGER_2));
        // 类型标识
        sb.append(frameType);
        // 内容
        sb.append(content);
        return ProtocolUtils.frameContentDataStr(sb.toString());
    }


    /**
     * 随机生成UUID，截取UUID前四位数字用于报文帧ID
     *
     * @return
     */
    public static String frameIdDataStr() {
        String replaceAll = UUID.randomUUID().toString().replaceAll("-", "");
        String regEx = "[^0-9]";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(replaceAll);
        String trim = m.replaceAll("").trim();
        if (trim.length() < IntegerUtil.INTEGER_4) {
            frameIdDataStr();
        } else {
            return trim.substring(IntegerUtil.INTEGER_0, IntegerUtil.INTEGER_4);
        }
        return "0000";
    }

    /**
     * 16进制转浮点数
     *
     * @param hexStr
     * @param scale  小数位
     * @return
     * @throws Exception
     */
    public static BigDecimal getBigDecimalData(String hexStr, int scale) {
        try {
            BigInteger b = new BigInteger(hexStr, 16);
            Float f = Float.intBitsToFloat(b.intValue());
            if (Float.isInfinite(f)) {
                StringBuilder sb = new StringBuilder();
                sb.append("1");
                for (int i = 0; i < scale; i++) {
                    sb.append("0");
                }
                return BigDecimal.valueOf(b.intValue()).divide(new BigDecimal(sb.toString()))
                    .setScale(scale, BigDecimal.ROUND_HALF_UP);
            } else if (Float.isNaN(f)) {
                Long b1 = Long.valueOf(hexStr, 16);
                Double longBitsToDouble = Double.longBitsToDouble(b1.longValue());
                String longStr = longBitsToDouble.toString();
                BigDecimal longBits1 = new BigDecimal(longStr)
                    .setScale(scale, BigDecimal.ROUND_HALF_UP);
                return longBits1;
            } else {
                return BigDecimal.valueOf(f).setScale(scale, BigDecimal.ROUND_HALF_UP);
            }
        } catch (Exception e) {
            LOGGER.error("转换错误[{}]", hexStr, e);
            return new BigDecimal(0);
        }
    }

    public static void main(String[] args) {
        String hexStr = "7FC00000";
        BigDecimal bigDecimalData = getBigDecimalData(hexStr, 4);
        System.out.println(bigDecimalData);
    }

    /**
     * 将数据库的数据 转为 协议帧数据
     * <p>
     * 压缩字节 例如 值:-20.99 数据格式：XX.XX 最高位2转为二进制是：‭0010‬ 值为负数，所以‭0010‬最高位转为1，就是 1010 1010转16进制是A
     * 那么这个值就应该 A099 ，然后低位在前，+33
     *
     * @param val
     * @param columnFormat 数据类型枚举值
     * @return
     */
    public static String formatValue(BigDecimal val, FrameColumnFormat columnFormat) {
        if (val == null) {
            String str = new DecimalFormat(columnFormat.getDecimalFormat()).format(0)
                .replace(".", "");
//            return plusHighLow(str);
            return str;
        }
        //负数标识
        boolean isNegative = val.doubleValue() < 0;
        //绝对值
        BigDecimal absVal = val.abs();
        // 转化，例如：8000.99 转为 008000.99
        DecimalFormat df = new DecimalFormat(columnFormat.getDecimalFormat());
        df.setRoundingMode(RoundingMode.HALF_UP);
        String fmtVal = df.format(absVal);
        //最高位
        int highBit = Integer
            .parseInt(fmtVal.substring(IntegerUtil.INTEGER_0, IntegerUtil.INTEGER_1));
        // 最大应为7 否则就溢出 ，7 -> 1111 -> F
        if (highBit >= IntegerUtil.INTEGER_8) {
            LOGGER.error("[{}][{}]最高位符号位溢出", columnFormat.getDesc(), val.toString());
            LOGGER.error("[{}]已将溢出数据强制转为0", columnFormat.getDesc());
            String str = new DecimalFormat(columnFormat.getDecimalFormat())
                .format(IntegerUtil.INTEGER_0).replace(".", "");
            return str;
        }
        //最高位转为2进制
        String binaryHighBit = new DecimalFormat("0000")
            .format(Integer.parseInt(Integer.toBinaryString(highBit)));
        //二进制最高位 0为正，1为负
        String formatHighBit =
            (isNegative ? NEGATIVE_1 : POSITIVE_0) + binaryHighBit.substring(IntegerUtil.INTEGER_1);
        //二进制转16进制
        String hexHighBit = Integer
            .toHexString(Integer.parseInt(formatHighBit, IntegerUtil.INTEGER_2));
        //最高位替换为正负标识符 ，去掉 "."
        String newVal = (hexHighBit + fmtVal.substring(IntegerUtil.INTEGER_1)).replace(".", "");
        //长度溢出
        if (newVal.length() > columnFormat.getColumnLength()) {
            LOGGER
                .error("[{}][{}]长度溢出|当前字节长度为:{}|最大字节长度为:{}", columnFormat.getDesc(), val.toString(),
                    newVal.length(), columnFormat.getColumnLength());
            newVal = new DecimalFormat(columnFormat.getDecimalFormat())
                .format(IntegerUtil.INTEGER_0).replace(".", "");
            LOGGER.error("[{}]已将溢出数据强制转为0", columnFormat.getDesc());
        }
        // 低位在前 +33
//        return plusHighLow(newVal);
        return newVal;
    }

    /**
     * +33 且 高低位转换
     *
     * @return
     */
    public static String plusHighLow(String data) {
        return formatHighLow(plusHex33(data));
    }

    /**
     * -33 且 高低位转换
     *
     * @return
     */
    public static String minusHighLow(String data) {
        return formatHighLow(minusHex33(data));
    }

    /**
     * 转换数据高低位顺序为：低位在前 高位在后
     *
     * @param data data为偶数
     * @return
     */
    public static String formatHighLow(String data) {
        StringBuilder str = new StringBuilder();
        if (StringUtils.isNotEmpty(data)) {
            for (int i = data.length(); i >= IntegerUtil.INTEGER_2; i -= IntegerUtil.INTEGER_2) {
                str.append(data, i - IntegerUtil.INTEGER_2, i);
            }
        }
        return str.toString();
    }

    /**
     * hex +33
     *
     * @param hexData
     * @return
     */
    public static String plusHex33(String hexData) {
        StringBuilder str = new StringBuilder();
        for (int i = IntegerUtil.INTEGER_0; i <= hexData.length() - IntegerUtil.INTEGER_2;
            i += IntegerUtil.INTEGER_2) {
            String single = hexData.substring(i, i + IntegerUtil.INTEGER_2);
            String singleFormat = Integer.toHexString(
                (Integer.parseInt(single, IntegerUtil.INTEGER_16) + Integer
                    .parseInt("33", IntegerUtil.INTEGER_16))).toUpperCase(Locale.ENGLISH);
            if (singleFormat.length() == IntegerUtil.INTEGER_1) {
                singleFormat = "0" + singleFormat;
            }
            str.append(singleFormat);
        }
        return str.toString();
    }

    /**
     * hex -33
     *
     * @param hexData
     * @return
     */
    public static String minusHex33(String hexData) {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i <= hexData.length() - IntegerUtil.INTEGER_2; i += IntegerUtil.INTEGER_2) {
            String single = hexData.substring(i, i + IntegerUtil.INTEGER_2);
            String singleFormat = Integer.toHexString(
                (Integer.parseInt(single, IntegerUtil.INTEGER_16) - Integer
                    .parseInt("33", IntegerUtil.INTEGER_16))).toUpperCase(Locale.ENGLISH);
            if (singleFormat.length() == 1) {
                singleFormat = "0" + singleFormat;
            }
            str.append(singleFormat);
        }
        return str.toString();
    }

    /**
     * 浮点数 转 十六进制字符串 转
     *
     * @param value
     * @return
     * @throws Exception
     */
    public static String getFloatBigDecimalData(BigDecimal value) {
        try {
            byte[] bytes = floatToBytes(value.floatValue());
            return Convert.toHex(bytes);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return "00000000";
        }
    }

    /**
     * 浮点数转为二进制
     *
     * @param data
     * @return
     */
    public static byte[] floatToBytes(float data) {
        int intBits = Float.floatToIntBits(data);
        byte[] bytes = new byte[4];
        bytes[3] = (byte) (intBits & 0xff);
        bytes[2] = (byte) ((intBits & 0xff00) >> 8);
        bytes[1] = (byte) ((intBits & 0xff0000) >> 16);
        bytes[0] = (byte) ((intBits & 0xff000000) >> 24);
        return bytes;
    }

    /**
     * 将 BigDecimal 转换为IEEE754 格式
     *
     * @param value
     * @return
     */
    public static String bigDecimalToIeeE754Hex(BigDecimal value) {
        if (BigDecimal.ZERO.compareTo(value) == 0) {
            return "00000000";
        }
        String floatToIEEE754 = IEEE754FloatUtil.floatToIEEE754(value.floatValue());
        return ProtocolUtils.parseByte2HexStr(floatToIEEE754);
    }

    /**
     * 将 IEEE754 转换为 BigDecimal 格式
     *
     * @param iee754Hex
     * @return
     */
    public static BigDecimal ieee754HexToBigDecimal(String iee754Hex) {
        if (StringUtils.isEmpty(iee754Hex.replaceAll("0", ""))) {
            return BigDecimal.valueOf(0.0);
        }
        byte[] bytes2 = ProtocolUtils.hexStringToByte(iee754Hex);
        return BigDecimal.valueOf(IEEE754FloatUtil.hex2FloatIeee(bytes2));
    }

    /**
     * 将二进制字节流数组转换为字符串
     *
     * @param bArray 二进制字节流数组
     * @return 返回字符串
     */
    public static final String bytesToHexString(byte[] bArray) {
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

    /**
     * 把16进制字符串转换成字节数组
     *
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

    private static int toByte(char c) {
        byte b = (byte) "0123456789ABCDEF".indexOf(c);
        return b;
    }

    /**
     * bytes[] 转 hex string
     *
     * @param bytes
     * @return
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
     * 将一段错误解码的字符串重新解码
     */
    public static String convertEncodingFormat(String str, String formatFrom, String FormatTo) {
        String result = null;
        if (!(str == null || str.length() == 0)) {
            try {
                result = new String(str.getBytes(formatFrom), FormatTo);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    /**
     * 将字符串转成ASCII（带间隔符）
     */
    public static String stringToAsciiSymbol(String value) {
        StringBuffer sbu = new StringBuffer();
        char[] chars = value.toCharArray();
        String sub = "";
        for (int i = 0; i < chars.length; i++) {
            if (i != chars.length - 1) {
                sbu.append((int) chars[i]).append(",");
            } else {
                sbu.append((int) chars[i]);
            }
        }
        return sbu.toString();
    }

    /**
     * 将ASCII码转换为16进制数据
     *
     * @param value
     * @return
     */
    public static String stringToAsciiHex(String value) {
        String asciiValue = stringToAsciiSymbol(value);
        String[] split = asciiValue.split(",");
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < split.length; i++) {
            sb.append(strToHex16(split[i]));
        }
        return sb.toString();
    }

    /**
     * 将字符串转成ASCII
     */
    public static String stringToAscii(String value) {
        StringBuffer sbu = new StringBuffer();
        char[] chars = value.toCharArray();
        String sub = "";
        for (int i = 0; i < chars.length; i++) {
            sbu.append((int) chars[i]);
        }
        return sbu.toString();
    }

    /**
     * 将16进制码数据转换为ASCII数据
     *
     * @param hex
     * @return
     */
    public static String convertHexToString(String hex) {
        StringBuilder sb = new StringBuilder();
        StringBuilder temp = new StringBuilder();
        for (int i = 0; i < hex.length() - IntegerUtil.INTEGER_1; i += IntegerUtil.INTEGER_2) {
            //grab the hex in pairs
            String output = hex.substring(i, (i + IntegerUtil.INTEGER_2));
            //convert hex to decimal
            int decimal = Integer.parseInt(output, IntegerUtil.INTEGER_16);
            //convert the decimal to character
            sb.append((char) decimal);
            temp.append(decimal);
        }
        return sb.toString();
    }

    /**
     * 将ASCII转成字符串
     */
    public static String asciiToString(String value) {
        StringBuffer sbu = new StringBuffer();
        String[] chars = value.split(",");
        for (int i = 0; i < chars.length; i++) {
            sbu.append((char) Integer.parseInt(chars[i]));
        }
        return sbu.toString();
    }

    /**
     * 获取逻辑地址所属 数据类型
     *
     * @param logicalAddr
     * @return
     */
    public static String getLogicatAddrBoardType(String logicalAddr) {
        if (StringUtils.isNotEmpty(logicalAddr) && logicalAddr.length() == IntegerUtil.INTEGER_4) {
            String substring = logicalAddr.substring(IntegerUtil.INTEGER_0, IntegerUtil.INTEGER_2);
            Integer integer = Convert.toInt(hex16ToStr(substring));
            boolean b5 = integer < IntegerUtil.INTEGER_64;
            boolean b6 = integer > IntegerUtil.INTEGER_127;
            boolean b7 = integer < IntegerUtil.INTEGER_192;

            if (b5 || (b6 && b7)) {
                return BoardType.VOLTAGE.getCode();
            }
            boolean b = integer >= IntegerUtil.INTEGER_64;
            boolean b1 = integer <= IntegerUtil.INTEGER_127;
            boolean b2 = integer >= IntegerUtil.INTEGER_192;
            boolean b3 = integer <= IntegerUtil.INTEGER_255;
            if ((b && b1) || (b2 && b3)) {
                return BoardType.ELECTRICITY.getCode();
            }
        }
        return null;
    }

    /**
     * 获取逻辑地址所属  数据站点类型
     *
     * @param logicalAddr
     * @return
     */
    public static Integer getLogicatAddrStationType(String logicalAddr) {
        if (StringUtils.isNotEmpty(logicalAddr) && logicalAddr.length() == IntegerUtil.INTEGER_4) {
            String substring = logicalAddr.substring(IntegerUtil.INTEGER_0, IntegerUtil.INTEGER_2);
            Integer integer = Convert.toInt(hex16ToStr(substring));
            if (integer <= IntegerUtil.INTEGER_127) {
                return StationType.TRADITION.getCode();
            }
            if (integer > IntegerUtil.INTEGER_127 && integer <= IntegerUtil.INTEGER_255) {
                return StationType.DIGITIZATION.getCode();
            }
        }
        return null;
    }

    /**
     * 日期转换格式16进制（按年月日时分秒转换）
     */
    public static String formatDateToHHMMSSMmHex16(Date date) {
        StringBuilder sb = new StringBuilder();
        if (StringUtils.isNotNull(date)) {
            String[] formatArray = splitFormatDateMm(DateTools.getFormat8().format(date));
            sb.append(ProtocolUtils.supplementaryHex(ProtocolUtils.strToHex16(formatArray[0]), 2));
            sb.append(ProtocolUtils.supplementaryHex(ProtocolUtils.strToHex16(formatArray[1]), 1));
            sb.append(ProtocolUtils.supplementaryHex(ProtocolUtils.strToHex16(formatArray[2]), 1));
            int dayOfWeek = DateUtil.dayOfWeek(date) - 1;
            if (dayOfWeek == 0) {
                dayOfWeek = 7;
            }
            sb.append(ProtocolUtils.supplementaryHex(ProtocolUtils.intToHex16(dayOfWeek), 1));
            sb.append(ProtocolUtils.supplementaryHex(ProtocolUtils.strToHex16(formatArray[3]), 1));
            sb.append(ProtocolUtils.supplementaryHex(ProtocolUtils.strToHex16(formatArray[4]), 1));
            sb.append(ProtocolUtils.supplementaryHex(ProtocolUtils.strToHex16(formatArray[5]), 1));
            sb.append(ProtocolUtils.supplementaryHex(ProtocolUtils.strToHex16(formatArray[6]), 2));
        }
        return sb.toString();
    }

    /**
     * 16进制转换为日期
     */
    public static Date hex16ToFormatDate(String dateFarmat) {
        Date date = null;
        if (StringUtils.isNotEmpty(dateFarmat)) {
            try {
                String[] formatArray = splitFormatDate(dateFarmat);
                String s = "";
                for (String farmat : formatArray) {
                    s = s + hex16ToStr(farmat);
                }
                date = DateUtil.parse(s, "yyyyMMddHHmmss");
            } catch (Exception e) {
                date = null;
            }
        }
        return date;
    }

    public static String[] splitFormatDate(String formatData) {
        String[] array = new String[6];
        array[0] = formatData.substring(0, 4);
        array[1] = formatData.substring(4, 6);
        array[2] = formatData.substring(6, 8);
        array[3] = formatData.substring(8, 10);
        array[4] = formatData.substring(10, 12);
        array[5] = formatData.substring(12, 14);
        return array;
    }

    public static String[] splitFormatDateMm(String formatData) {
        String[] array = new String[7];
        array[0] = formatData.substring(0, 4);
        array[1] = formatData.substring(4, 6);
        array[2] = formatData.substring(6, 8);
        array[3] = formatData.substring(8, 10);
        array[4] = formatData.substring(10, 12);
        array[5] = formatData.substring(12, 14);
        array[6] = formatData.substring(14, 17);
        return array;
    }

    /**
     * 获取数据域长度 此方法具有局限性 参数data必须包含 data length 标识 ，将数据长度补充为2个字节 00 00
     *
     * @param frame
     * @return
     */
    public static String getHexDataLength(String frame) {
        int length = (frame.length() / IntegerUtil.INTEGER_2) - IntegerUtil.INTEGER_2;
        if (length > IntegerUtil.INTEGER_65535) {
            length = IntegerUtil.INTEGER_65535;
        }
        String hexLength = Integer.toHexString(length);
        hexLength = ProtocolUtils.formatHighLow(ProtocolUtils.supplementaryHex(hexLength, 2));
        return hexLength.toUpperCase(Locale.ENGLISH);
    }

    /**
     * 验证帧
     *
     * @param data
     * @return
     */
    public static String cheakFramesStr(String data) {
        byte[] b = new byte[0];
        try {
            b = hexStringToBytes(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tryfcs16(b, b.length);
    }

    /**
     * Convert hex string to byte[]
     *
     * @param hexString the hex string
     * @return byte[]
     */
    public static byte[] hexStringToBytes(String hexString) {
        if (hexString == null || "".equals(hexString)) {
            return null;
        }

        hexString = hexString.toUpperCase(Locale.ENGLISH);
        int length = hexString.length() / 2;
        char[] hexChars = hexString.toCharArray();
        byte[] d = new byte[length];
        for (int i = 0; i < length; i++) {
            int pos = i * 2;
            d[i] = (byte) (toByte(hexChars[pos]) << 4 | toByte(hexChars[pos + 1]));
        }
        return d;
    }

    public static int pppfcs16(int fcs, byte[] cp, int len) {
        for (int i = 0; i < len; i++) {
            fcs = (fcs >> 8) ^ fcstab[(fcs ^ cp[i]) & 0xff];
        }
        return fcs;
    }

    /**
     * 校验码
     *
     * @param cp
     * @param len
     */
    public static String tryfcs16(byte[] cp, int len) {
        int trialfcs;
        trialfcs = pppfcs16(PPPINITFCS16, cp, len);
        trialfcs ^= 0xffff;
        int u1 = (trialfcs & 0x00ff);
        int u2 = ((trialfcs >> 8) & 0x00ff);
        String su1 = Integer.toHexString(u1);
        String su2 = Integer.toHexString(u2);
        return fillIn(su1) + fillIn(su2);
    }

    public static String fillIn(String a) {
        if (a.length() == 1) {
            a = "0" + a;
        }
        return a;
    }

    /**
     * 获取最近一个时间点（00，15，30，45）
     *
     * @param newDate
     * @return
     */
    public static Date getLastPointDate(Date newDate) {
        if (newDate == null) {
            newDate = new Date();
        }
        Date dateTime = DateUtil.beginOfMinute(newDate);
        // 得到分钟
        int minutes = DateUtil.minute(dateTime);
        //时间不为00，15，30，45的情况
        if (minutes != IntegerUtil.INTEGER_0 && minutes != IntegerUtil.INTEGER_15
            && minutes != IntegerUtil.INTEGER_30 && minutes != IntegerUtil.INTEGER_45) {
            int yushu = minutes % IntegerUtil.INTEGER_15;
            // 取四舍五入   大于10分钟时按最近15分钟的时间取数据
            if (yushu > IntegerUtil.INTEGER_7) {
                minutes = (IntegerUtil.INTEGER_15 - yushu);
            } else {
                // 小于10分钟时按前一个最近15分钟的时间时
                minutes = yushu * -IntegerUtil.INTEGER_1;
            }
        } else {
            minutes = IntegerUtil.INTEGER_0;
        }
        return DateUtil.offsetMinute(dateTime, minutes);
    }

    public static Boolean getBeenDataToResult(Date collectTime) {
        try {
            long between = DateUtil.between(collectTime, DateUtil.date(), DateUnit.MINUTE);
            if ((IntegerUtil.INTEGER_24 * IntegerUtil.INTEGER_60) > between) {
                return Boolean.FALSE;
            } else {
                return Boolean.TRUE;
            }
        } catch (Exception e) {
            return Boolean.FALSE;
        }
    }

    public static Boolean getBeenErrorDataToResult(Date collectTime) {
        try {
            long between = DateUtil.between(collectTime, DateUtil.date(), DateUnit.MINUTE);
            if ((IntegerUtil.INTEGER_48 * IntegerUtil.INTEGER_60) > between) {
                return Boolean.FALSE;
            } else {
                return Boolean.TRUE;
            }
        } catch (Exception e) {
            return Boolean.FALSE;
        }
    }

    public static String getValueNullToEmpty(String value){
        if(StringUtils.isNull(value)){
            return "";
        }
        return value;
    }

    public static ByteBuf sendFrameData(String frameStr){
        byte[] hexStringToBytes = ProtocolUtils.hexStringToBytes(frameStr);
        // netty需要用ByteBuf传输
        ByteBuf buff = Unpooled.buffer();
        // 对接需要16进制
        buff.writeBytes(hexStringToBytes);
        return buff;
    }

    public static BigDecimal getRandomBigScal(Boolean falg){
        Random rand = new Random();
        int nextInt = rand.nextInt(99999);
        BigDecimal bigDecimal = new BigDecimal("0." + nextInt);
        int anInt = rand.nextInt(999);
        if(anInt % 2 == 0 && falg){
            return bigDecimal.negate();
        }
        return bigDecimal;
    }

    public static BigDecimal getRandomMaxBigScal(Boolean falg){
        Random rand = new Random();
        int nextInt = rand.nextInt(19999);
        int scalVal = rand.nextInt(999);
        BigDecimal bigDecimal = new BigDecimal(nextInt+"."+scalVal);
        int anInt = rand.nextInt(999);
        if(anInt % 2 == 0 && falg){
            return bigDecimal.negate();
        }
        return bigDecimal;
    }
}
