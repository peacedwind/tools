package com.oetsky.common.frame.utils;

import cn.hutool.core.date.DateUtil;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * @author xiangzc
 */
public class Cp56Time2a {

    public static Date toDate(byte[] bytes) {
        int milliseconds1 = bytes[0] < 0 ? 256 + bytes[0] : bytes[0];
        int milliseconds2 = bytes[1] < 0 ? 256 + bytes[1] : bytes[1];
        int milliseconds = milliseconds1 + milliseconds2 * 256;
        //取分-秒
        BigDecimal b = new BigDecimal(milliseconds + "")
            .divide(new BigDecimal("1000"), 3, BigDecimal.ROUND_DOWN);
        String textBD = b.toPlainString();
        int radixLoc = textBD.indexOf('.');
        int s = Integer.valueOf(textBD.substring(0, radixLoc));
        int sm = Integer.valueOf(textBD.substring(radixLoc + 1));
        // 位于 0011 1111
        int minutes = bytes[2] & 0x3f;
        // 位于 0001 1111
        int hours = bytes[3] & 0x1f;
        // 位于 0000 1111
        int days = bytes[4] & 0x1f;
        // 位于 0001 1111
        int months = bytes[5] & 0x1f;
        // 位于 0111 1111
        int years = bytes[6] & 0x7f;
        final Calendar aTime = Calendar.getInstance();
        aTime.set(Calendar.MILLISECOND, milliseconds);
        aTime.set(Calendar.MILLISECOND, bytes[0]);
        aTime.set(Calendar.SECOND, bytes[1]);
        aTime.set(Calendar.MINUTE, minutes);
        aTime.set(Calendar.HOUR_OF_DAY, hours);
        aTime.set(Calendar.DAY_OF_MONTH, days);
        aTime.set(Calendar.MONTH, months);
        aTime.set(Calendar.YEAR, years + 2000);
        return aTime.getTime();
    }

    public static byte[] toBytes(Date aDate) {
        byte[] result = new byte[7];
        final Calendar aTime = Calendar.getInstance();
        aTime.setTime(aDate);
        result[0] = (byte) aTime.get(Calendar.MILLISECOND);
        result[1] = (byte) aTime.get(Calendar.SECOND);
        result[2] = (byte) aTime.get(Calendar.MINUTE);
        result[3] = (byte) aTime.get(Calendar.HOUR_OF_DAY);
        result[4] = (byte) aTime.get(Calendar.DAY_OF_MONTH);
        result[5] = (byte) aTime.get(Calendar.MONTH);
        result[6] = (byte) (aTime.get(Calendar.YEAR) % 100);
        return result;
    }

    /**
     * 将16进制转换为Cp56Time时间(错误)
     */
    public static Date hexToCp56Time1(String hexStr) {
        byte[] bytes1 = hexToByteArray(hexStr);
        return toDate(bytes1);
    }


    /**
     * 将16进制的UTC时间转换为Cp56Time时间 将转换的时间当作UTC 时间，将时间转为CST时间
     */
    public static Date hexUTCToCp56Time(String hexStr) {
        int[] bytes1 = hexStringToIntArray(hexStr);
        String timeDateScale = TimeDateScale(bytes1);
        // UTC 转 CST时间
        return utcToLocal(timeDateScale);
    }

    /**
     * utc时间转成local时间
     *
     * @param utcTime
     * @return
     */
    public static Date utcToLocal(String utcTime) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
        Date utcDate = null;
        try {
            utcDate = sdf.parse(utcTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        sdf.setTimeZone(TimeZone.getDefault());
        Date locatlDate = null;
        String localTime = null;
        if (utcDate != null) {
            localTime = sdf.format(utcDate.getTime());
        }
        try {
            locatlDate = sdf.parse(localTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return locatlDate;
    }


    /**
     * 将16进制的时间转换为Cp56Time时间
     */
    public static Date hexToCp56Time(String hexStr) {
        int[] bytes1 = hexStringToIntArray(hexStr);
        return TimeScale(bytes1);
    }

    /**
     * 时标CP56Time2a解析
     *
     * @param b 时标CP56Time2a（长度为7 的int数组）
     * @return 解析结果
     */
    public static Date TimeScale(int b[]) {
        String str = "";
        int year = b[6] & 0x7F;
        int month = b[5] & 0x0F;
        int day = b[4] & 0x1F;
        int week = (b[4] & 0xE0) / 32;
        int hour = b[3] & 0x1F;
        int minute = b[2] & 0x3F;
        int second = (b[1] << 8) + b[0];
        String data = (year + 2000) + "-"
            + String.format("%02d", month) + "-"
            + String.format("%02d", day) + " " + hour + ":" + minute + ":"
            + second / 1000 + "." + second % 1000;
        return DateUtil.parse(data, "yyyy-MM-dd HH:mm:ss.SSS");
    }

    /**
     * 时标CP56Time2a解析
     *
     * @param b 时标CP56Time2a（长度为7 的int数组）
     * @return 解析结果
     */
    public static String TimeDateScale(int b[]) {
        int year = b[6] & 0x7F;
        int month = b[5] & 0x0F;
        int day = b[4] & 0x1F;
        int hour = b[3] & 0x1F;
        int minute = b[2] & 0x3F;
        int second = (b[1] << 8) + b[0];
        String data = (2000 + year) + "-"
            + String.format("%02d", month) + "-"
            + String.format("%02d", day) + " " + hour + ":" + minute + ":"
            + second / 1000 + "." + second % 1000;
        return data;
    }

    /**
     * 16进制表示的字符串转换为字节数组
     *
     * @param s 16进制表示的字符串
     * @return byte[] 字节数组
     */
    public static int[] hexStringToIntArray(String s) {
        int len = s.length();
        int[] b = new int[len / 2];
        for (int i = 0; i < len; i += 2) {
            // 两位一组，表示一个字节,把这样表示的16进制字符串，还原成一个字节
            b[i / 2] = (Character.digit(s.charAt(i), 16) << 4) + Character
                .digit(s.charAt(i + 1), 16);
        }
        return b;
    }

    /**
     * 将时间转换为  16进制的Cp56Time(错误)
     */
    public static String dataToHexCp56Time1(Date date) {
        final byte[] bytes = toBytes(date);
        return bytesToHexString(bytes);
    }

    /**
     * 将时间转换为  16进制的Cp56Time
     */
    public static String dataToHexCp56Time(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        StringBuilder builder = new StringBuilder();
        String milliSecond = String.format("%04X",
            (calendar.get(Calendar.SECOND) * 1000) + calendar.get(Calendar.MILLISECOND));
        builder.append(milliSecond, 2, 4);
        builder.append(milliSecond, 0, 2);
        builder.append(String.format("%02X", calendar.get(Calendar.MINUTE) & 0x3F));
        builder.append(String.format("%02X", calendar.get(Calendar.HOUR_OF_DAY) & 0x1F));
        int week = calendar.get(Calendar.DAY_OF_WEEK);
        if (week == Calendar.SUNDAY) {
            week = 7;
        } else {
            week--;
        }
        builder.append(
            String.format("%02X", (week << 5) + (calendar.get(Calendar.DAY_OF_MONTH) & 0x1F)));
        builder.append(String.format("%02X", calendar.get(Calendar.MONTH) + 1));
        builder.append(String.format("%02X", calendar.get(Calendar.YEAR) - 2000));
        return builder.toString();
    }

    /**
     * 16进制转字节数组
     *
     * @param inHex
     * @return
     */
    public static byte[] hexToByteArray(String inHex) {
        int hexlen = inHex.length();
        byte[] result;
        if (hexlen % 2 == 1) {
            //奇数
            hexlen++;
            result = new byte[(hexlen / 2)];
            inHex = "0" + inHex;
        } else {
            //偶数
            result = new byte[(hexlen / 2)];
        }
        int j = 0;
        for (int i = 0; i < hexlen; i += 2) {
            result[j] = hexToByte(inHex.substring(i, i + 2));
            j++;
        }
        return result;
    }

    public static byte hexToByte(String inHex) {
        return (byte) Integer.parseInt(inHex, 16);
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
}
