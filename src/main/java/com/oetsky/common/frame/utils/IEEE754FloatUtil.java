package com.oetsky.common.frame.utils;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class IEEE754FloatUtil {


    /**
     * 获取float的IEEE754存储格式
     *
     * @param value
     * @return 返回IEEE754二进制数据
     */
    public static String floatToIEEE754(float value) {
        //符号位
        String sflag = value > 0 ? "0" : "1";

        //整数部分
        int fz = (int) Math.floor(value);
        //整数部分二进制
        String fzb = Integer.toBinaryString(fz);
        //小数部分，格式： 0.02
        String valueStr = String.valueOf(value);
        String fxStr = "0" + valueStr.substring(valueStr.indexOf("."));
        float fx = Float.parseFloat(fxStr);
        //小数部分二进制
        String fxb = toBin(fx);

        //指数位
        String e = Integer.toBinaryString(127 + fzb.length() - 1);
        //尾数位
        String m = fzb.substring(1) + fxb;

        String result = sflag + e + m;

        while (result.length() < 32) {
            result += "0";
        }
        if (result.length() > 32) {
            result = result.substring(0, 32);
        }
        return result;
    }

    private static String toBin(float f) {
        List<Integer> list = new ArrayList<Integer>();
        Set<Float> set = new HashSet<Float>();
        // 最多8位
        int MAX = 24;
        int bits = 0;
        while (true) {
            f = calc(f, set, list);
            bits++;
            if (f == -1 || bits >= MAX) {
                break;
            }
        }
        String result = "";
        for (Integer i : list) {
            result += i;
        }
        return result;
    }

    private static float calc(float f, Set<Float> set, List<Integer> list) {
        if (f == 0 || set.contains(f)) {
            return -1;
        }
        float t = f * 2;
        if (t >= 1) {
            list.add(1);
            return t - 1;
        } else {
            list.add(0);
            return t;
        }
    }

    /**
     * @Desc: IEEE754标准（四字节转浮点数）,公式转换
     * @Author: Aries.hu
     * @Date: 2021/2/3 10:14
     */
    public static float hex2FloatIeee(byte[] hex) {
        String hexStr = DataTransform.bytesToHex(hex);
        StringBuilder binaryStr = new StringBuilder();
        for (int i = 0; i < hexStr.length(); i += 2) {
            String a = hexStr.substring(i, i + 2);
            int c = Integer.parseInt(a, 16);
            String item = String.format("%08d", Integer.parseInt(Integer.toBinaryString(c)));
            binaryStr.append(item);
        }
        int n = Integer.parseInt(binaryStr.substring(1, 9), 2);
        String mStr = binaryStr.substring(9, binaryStr.length() - 1);
        double sum = 0;
        for (int i = 1; i <= mStr.length(); i++) {
            if (mStr.charAt(i - 1) == '1') {
                sum = sum + Math.pow(0.5, i);
            }
        }
        float v = (float) ((Math.pow(2, n - 127)) * (1 + sum));
        return v;
    }

    public static void main(String[] args) {
        BigDecimal bigDecimal1 = new BigDecimal(1.0);
        BigDecimal bigDecimal2 = BigDecimal.valueOf(0);
        System.out.println("1 =" + bigDecimal1);
        String bigDecimalToIEEE754Hex1 = ProtocolUtils.getFloatBigDecimalData(bigDecimal1);
        System.out.println("2 =" + bigDecimalToIEEE754Hex1);
//        BigDecimal bigDecimalToIEEE754Hex2 = ProtocolUtils.getBigDecimalData(bigDecimalToIEEE754Hex1,1);
        BigInteger b = new BigInteger(bigDecimalToIEEE754Hex1, 16);
        Float f = Float.intBitsToFloat(b.intValue());
        System.out.println("3 =" + BigDecimal.valueOf(f));

    }

    public static String decimal2BinaryStr(double d) {

        String result = decimal2BinaryStr_Inte(d);

        result += decimal2BinaryStr_Deci(d);

        return result;

    }

    /**
     * 十进制整数部分转二进制数
     *
     * @param d 十进制数
     * @return 十进制整数部分转换成二进制的字符串
     */

    public static String decimal2BinaryStr_Inte(double d) {


//      return Integer.toBinaryString((int)d);

        /*

         * 本来利用上面的Integer.toBinaryString(int)就可以得到整数部分的二进制结果，

         * 但为了展示十进制转二进制的算法，现选择以下程序来进行转换

         */

        String result = "";

        long inte = (long) d;

        int index = 0;

        while (true) {


            result += inte % 2;

            inte = inte / 2;

            index++;

            if (index % 4 == 0) {


                result += " ";

            }

            if (inte == 0) {


                while (index % 4 != 0) {


                    result += "0";

                    index++;

                }

                break;

            }

        }

        char[] c = result.toCharArray();

        char[] cc = new char[c.length];

        for (int i = c.length; i > 0; i--) {


            cc[cc.length - i] = c[i - 1];

        }

        return new String(cc);

    }

    /**
     * 十进制小数部分转二进制
     *
     * @param d 十进制数
     * @return 十进制小数部分转换成二进制小数的字符串
     */

    public static String decimal2BinaryStr_Deci(double d) {


        return decimal2BinaryStr_Deci(d, 0);

    }

    /**
     * 十进制小数部分转二进制
     *
     * @param d     十进制数
     * @param scale 小数部分精确的位数
     * @return 十进制小数部分转换成二进制小数的字符串
     */

    public static String decimal2BinaryStr_Deci(double d, int scale) {

        double deci = sub(d, (long) d);

        if (deci == 0) {

            return "";

        }

//为了防止程序因所转换的数据转换后的结果是一个无限循环的二进制小数，因此给其一个默认的精确度

        if (scale == 0) {

            scale = (String.valueOf(deci).length() - 2) * 4;

        }

        int index = 0;

        StringBuilder inteStr = new StringBuilder();

        double tempD = 0.d;

        while (true) {

            if (deci == 0 || index == scale) {

                while (index % 4 != 0) {

                    inteStr.append("0");

                    index++;

                }

                break;

            }

            if (index == 0) {

                inteStr.append(".");

            }

            tempD = deci * 2;

            inteStr.append((int) tempD);

            deci = sub(tempD, (int) tempD);

            index++;

            if (index % 4 == 0) {

                inteStr.append(" ");

            }

        }

        return inteStr.toString();

    }

    /**
     * 提供精确的减法运算。
     *
     * @param v1 被减数
     * @param v2 减数
     * @return 两个参数的差
     */

    public static double sub(double v1, double v2) {

        BigDecimal b1 = new BigDecimal(Double.toString(v1));

        BigDecimal b2 = new BigDecimal(Double.toString(v2));

        return b1.subtract(b2).doubleValue();

    }
}
