package com.oetsky.project.serialsetting.serial.utils;

import cn.hutool.core.convert.Convert;
import com.oetsky.common.utils.StringUtils;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Locale;

/**
 * 通讯协议工具类
 */
public class DltAgreement {

    /**
     * 帧头
     */
    public static final String FRAME_HEADER_68 = "68";

    /**
     * 帧尾
     */
    public static final String FRAME_FOOTER_16 = "16";
    /**
     * 设置类下行报文控制域
     */
    public static final String FRAME_DOWN_SET = "03";
    /**
     * 读取类下行报文控制域
     */
    public static final String FRAME_DOWN_GET = "0A";
    /**
     * 终端事件信息上报 上位机 确认
     */
    public static final String FRAME_UPPER_COMPUTER_UP = "00";
    /**
     * 终端事件信息上报 上位机 否认
     */
    public static final String FRAME_UPPER_COMPUTER_UP_NOT = "01";
    /**
     * 上行报文控制域 设置类 确认
     */
    public static final String FRAME_SET_UP = "80";
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

    public static final String[] FRAME_PHASE = {"0", "A", "B", "C", "A", "B", "C", "A", "B", "C", "A", "B", "C", "A", "B", "C"};

    /**
     * 截取帧长度
     *
     * @param frameStr      数据帧
     * @param contentLength 数据位
     * @return
     */
    public static String subFrameStr(StringBuilder frameStr, Integer contentLength) {
        String str = "";
        if (contentLength > 0) {
            Integer length = contentLength * 2;
            str = frameStr.substring(0, length);
            frameStr = frameStr.delete(0, length);
        }
        return str;
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
            for (int i = data.length(); i >= 2; i -= 2) {
                str.append(data.substring(i - 2, i));
            }
        }
        return str.toString();
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
     * 补充16进制字节数 高位补 FF
     *
     * @param hexStr     16进制
     * @param numberByte 字节数
     * @return
     */
    public static String supplementaryMaxHex(String hexStr, Integer numberByte) {
        if (!StringUtils.isEmpty(hexStr) && numberByte > 0) {
            String str = hexStr;
            while (true) {
                if ((str.length() / 2) < numberByte) {
                    str = "FF" + str;
                    continue;
                }
                return str;
            }
        }
        return hexStr;
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
        if(aLong < Integer.MAX_VALUE){
            a = Integer.toHexString(Convert.toInt(farmat));
        }else{
            a = Long.toHexString(aLong);
        }
        if (a.length() % 2 == 1) {
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
        if (a.length() % 2 == 1) {
            a = "0" + a;
        }
        return a;
    }

    /**
     * 浮点数 转字符串转 IEEE 754十六进制字符串
     *
     * @param d
     * @author: 若非
     * @date: 2021/9/10 16:57
     */
    public static String singleToHex(double d) {
        long l = Double.doubleToRawLongBits(d);
        String hex = Long.toHexString(l);
        return hex;
    }

    /**
     * IEEE 754十六进制字符串 转浮点数
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
            return BigDecimal.valueOf(f).setScale(scale, BigDecimal.ROUND_HALF_UP);
        } catch (Exception e) {
            return new BigDecimal(0);
        }
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
            return formatHighLow(Convert.toHex(bytes));
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
        bytes[0] = (byte) (intBits & 0xff);
        bytes[1] = (byte) ((intBits & 0xff00) >> 8);
        bytes[2] = (byte) ((intBits & 0xff0000) >> 16);
        bytes[3] = (byte) ((intBits & 0xff000000) >> 24);
        return bytes;
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
     * 16进制转换为10进制
     *
     * @param farmat
     * @return
     */
    public static String hex16ToStr(String farmat) {
        if (StringUtils.isNotEmpty(farmat)) {
            BigInteger bigint = new BigInteger(farmat, 16);
            String a = Convert.toStr(bigint.intValue());
            if (a.length() % 2 == 1) {
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
        while (n != 0) {
            str = n % 2 + str;
            n = n / 2;
        }
        if (str.length() % 2 == 1) {
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
        int in = Integer.parseInt(str, 2);
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
        int temp = length % 4;
        // 每四位2进制数字对应一位16进制数字
        // 补足4位
        if (temp != 0) {
            for (int i = 0; i < 4 - temp; i++) {
                binary = "0" + binary;
            }
        }
        // 重新计算长度
        length = binary.length();
        StringBuilder sb = new StringBuilder();
        // 每4个二进制数为一组进行计算
        for (int i = 0; i < length / 4; i++) {
            int num = 0;
            // 将4个二进制数转成整数
            for (int j = i * 4; j < i * 4 + 4; j++) {
                // 左移
                num <<= 1;
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
    public static String getCS(String hexData) {
        if (hexData == null || hexData.equals("")) {
            return "00";
        }
        hexData = hexData.replaceAll(" ", "");
        int total = 0;
        int len = hexData.length();
        if (len % 2 != 0) {
            return "00";
        }
        int num = 0;
        while (num < len) {
            String s = hexData.substring(num, num + 2);
            total += Integer.parseInt(s, 16);
            num = num + 2;
        }
        String hexStr = hexInt(total);
        return hexStr.substring(hexStr.length() - 2).toUpperCase(Locale.ENGLISH);
    }

    private static String hexInt(int total) {
        int a = total / 256;
        int b = total % 256;
        if (a > 255) {
            return hexInt(a) + format(b);
        }
        return format(a) + format(b);
    }

    private static String format(int hex) {
        String hexa = Integer.toHexString(hex);
        int len = hexa.length();
        if (len < 2) {
            hexa = "0" + hexa;
        }
        return hexa;
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
        for (int i = 0; i < hex.length() - 1; i += 2) {
            //grab the hex in pairs
            String output = hex.substring(i, (i + 2));
            //convert hex to decimal
            int decimal = Integer.parseInt(output, 16);
            //convert the decimal to character
            sb.append((char) decimal);
            temp.append(decimal);
        }
        return sb.toString();
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
     * 将字符串转成ASCII（带间隔符）
     */
    public static String stringToAsciiSymbol(String value) {
        StringBuffer sbu = new StringBuffer("");
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
     * 将字符串转成ASCII
     */
    public static String stringToAscii(String value) {
        StringBuffer sbu = new StringBuffer("");
        char[] chars = value.toCharArray();
        String sub = "";
        for (int i = 0; i < chars.length; i++) {
            sbu.append((int) chars[i]);
        }
        return sbu.toString();
    }

}
