package com.oetsky.project.serialsetting.serial.utils;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.oetsky.common.frame.utils.DateTools;
import com.oetsky.common.frame.utils.IntegerUtil;
import com.oetsky.common.frame.utils.ProtocolUtils;
import com.oetsky.common.utils.StringUtils;
import com.oetsky.project.serialsetting.serial.domain.*;
import com.oetsky.project.serialsetting.serial.domain.CommandFrameLog;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.*;

/**
 * @author: xiangzc
 * @date: 2021-3-12
 */
public class DLT698Utils {

    private static final Logger LOGGER = LoggerFactory.getLogger(DLT698Utils.class);
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

    private DLT698Utils() {
    }

    /**
     * 解析命令帧 丰富日志
     */
    public static CommandFrameLog parseCommandFrame(Control698Data control698Data) {
        //截取命令帧里的地址域
        String addrFieldFrame = control698Data.getServerAddress();
        CommandFrameLog commandFrameLog = new CommandFrameLog();
        commandFrameLog.setChannelNum(addrFieldFrame);
        //TODO:根据表计规则-指定线路编号和相位
        AddressTransformEnum addressTransformEnum =  AddressTransformEnum.getByCode(addrFieldFrame.substring(10));
        if(addressTransformEnum == null){
            return null;
        }
        commandFrameLog.setPhase(addressTransformEnum.getP());
        commandFrameLog.setChannel(addressTransformEnum.getC());
        try {
            //截取命令帧里的测量时间
            ControlApudDatas controlApudDatas = control698Data.getControlApudDatas();
            RequestTimeTagData timeTagData = controlApudDatas.getTimeTagData();
            if (StringUtils.isNotNull(timeTagData)) {
                //格式化日期
                commandFrameLog.setDate(DateTools.getFormat1().format(timeTagData.getSelectTimeTagDate()));
            } else {
                commandFrameLog.setDate(DateUtil.format(DateUtil.date(), "yyyy-MM-dd HH:mm:ss"));
            }
        } catch (Exception e) {
            LOGGER.error("命令帧数据无时间");
            commandFrameLog.setDate("1990-03-30 15:00:00");
        }
        return commandFrameLog;
    }

    /**
     * 获取某一通道的异常应答帧
     *
     * @param addrFieldFrame 地址域 ， 低位在前
     * @return
     */
    public static String getErrorFrame(String addrFieldFrame) {
        String frame = ControlConstants.DATA_EMPTY_FRAME.replace(ControlConstants.ADDR_FIELD_FLAG, addrFieldFrame);
        String cs = getCs(frame.substring(0, frame.indexOf(ControlConstants.CS_FLAG)));
        return frame.replace(ControlConstants.CS_FLAG, cs);
    }

    /**
     * 将数据库的数据 转为 协议帧数据
     * <p>
     * 压缩字节
     * 例如
     * 值:-20.99
     * 数据格式：XX.XX
     * 最高位2转为二进制是：‭0010‬
     * 值为负数，所以‭0010‬最高位转为1，就是 1010
     * 1010转16进制是A
     * 那么这个值就应该 A099 ，然后低位在前
     *
     * @param val
     * @param columnFormat 数据类型枚举值
     * @return
     */
    public static String formatValue2(BigDecimal val, ControlConstants.ColumnFormat columnFormat) {
        if (val == null) {
            String str = new DecimalFormat(columnFormat.getDecimalFormat()).format(0).replace(".", "");
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
        int highBit = Integer.parseInt(fmtVal.substring(0, 1));
        // 最大应为7 否则就溢出 ，7 -> 1111 -> F
        if (highBit >= IntegerUtil.INTEGER_8) {
            LOGGER.error("[{}][{}]最高位符号位溢出", columnFormat.getDesc(), val.toString());
            LOGGER.error("[{}]已将溢出数据强制转为0", columnFormat.getDesc());
            String str = new DecimalFormat(columnFormat.getDecimalFormat()).format(0).replace(".", "");
            return str;
        }
        //最高位转为2进制
        String binaryHighBit = new DecimalFormat("0000").format(Integer.parseInt(Integer.toBinaryString(highBit)));
        //二进制最高位 0为正，1为负
        String formatHighBit = (isNegative ? ControlConstants.NEGATIVE_1 : ControlConstants.POSITIVE_0) + binaryHighBit.substring(1);
        //二进制转16进制
        String hexHighBit = Integer.toHexString(Integer.parseInt(formatHighBit, 2));
        //最高位替换为正负标识符 ，去掉 "."
        String newVal = (hexHighBit + fmtVal.substring(1)).replace(".", "");
        //长度溢出
        if (newVal.length() > columnFormat.getColumnLength()) {
            LOGGER.error("[{}][{}]长度溢出|当前字节长度为:{}|最大字节长度为:{}", columnFormat.getDesc(), val.toString(), newVal.length(), columnFormat.getColumnLength());
            newVal = new DecimalFormat(columnFormat.getDecimalFormat()).format(0).replace(".", "");
            LOGGER.error("[{}]已将溢出数据强制转为0", columnFormat.getDesc());
        }
        // 低位在前 +33
//        return plusHighLow(newVal);
        return newVal;
    }

    /**
     * 将数据库的数据 转为 协议帧数据
     * <p>
     * 例如
     * 值:-20.99
     * 数据格式：XX.XX
     * 20.99转16进制是00d2
     * 负数则在前转低位为8
     * 那么这个值就应该 80d2
     *
     * @param val
     * @param columnFormat 数据类型枚举值
     * @return
     */
    public static String formatValue1(BigDecimal val, ControlConstants.ColumnFormat columnFormat) {
        if (val == null) {
            String str = new DecimalFormat(columnFormat.getDecimalFormat()).format(0).replace(".", "");
            return str;
        }
        //负数标识
        boolean isNegative = val.doubleValue() < 0;
        //绝对值
        BigDecimal absVal = val.abs();
        // 转化，例如：8000.99 转为 008000.99
        String fmtVal = new DecimalFormat(columnFormat.getDecimalFormat()).format(absVal);
        //最高位替换为正负标识符 ，去掉 "."
        String newVal = strToHex16(fmtVal.replace(".", ""));
        int columnLength = columnFormat.getColumnLength();
        String value = newVal;
        if (newVal.length() < columnLength) {
            for (int i = newVal.length(); i < columnLength; i++) {
                if (i == columnLength - 1 && isNegative) {
                    value = "8" + value;
                } else {
                    value = "0" + value;
                }

            }
        }
        return value;
    }

    /**
     * 将数据库的数据 转为 协议帧数据
     * <p>
     * 例如
     * 值:-20.99
     * 数据格式：XX.XX
     * 20.99转16进制是00d2
     * 负数则为最大值 补码 + 1
     * 那么这个值就应该 f7cd
     *
     * @param val          数据值（可为负数）
     * @param columnFormat 数据类型枚举值
     * @return
     */
    public static String formatValue(BigDecimal val, ControlConstants.ColumnFormat columnFormat) {
        if (val == null) {
            String str = new DecimalFormat(columnFormat.getDecimalFormat()).format(0).replace(".", "");
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
        Integer formatLeng = columnFormat.getColumnLength() / 2;
        String value = "";
        BigDecimal matvalZone = new BigDecimal(fmtVal);
        if (isNegative && BigDecimal.ZERO.compareTo(matvalZone) != 0) {
            if (formatLeng >= IntegerUtil.INTEGER_8) {
                // 表示为Long类型数据
                String replace = fmtVal.replace(".", "");
                Long aLong = Convert.toLong(replace) * -1L;
                value = Long.toHexString(aLong);
            } else {
                Long valueStr = Convert.toLong(fmtVal.replace(".", ""));
                if (valueStr < Integer.MAX_VALUE) {
                    // 如果为负数 则获取当前数据最大值
                    String newValue = "";
                    for (int i = 0; i < formatLeng; i++) {
                        newValue = newValue + "FF";
                    }
                    // 将16进制最大值转换为十进制 +1（这个就是补码）
                    Integer maxValue = Convert.toInt(DltAgreement.hex16ToStr(newValue)) + 1;
                    // 新数据值为   16进制最大值 - 格式化最大值
                    Integer newMaxValue = maxValue - Convert.toInt(fmtVal.replace(".", ""));
                    // 最终值为 新值转16进制 ,并在高位补FF
                    value = DltAgreement.supplementaryMaxHex(DltAgreement.intToHex16(newMaxValue), formatLeng);
                    if (value.length() > columnFormat.getColumnLength()) {
                        // 如果数据位长度 大于 最大数据位，则取16进制最大值
                        value = newValue;
                    }
                } else {
                    // 如果为负数 则获取当前数据最大值
                    String newValue = "";
                    for (int i = 0; i < formatLeng; i++) {
                        newValue = newValue + "FF";
                    }
                    // 将16进制最大值转换为十进制 +1（这个就是补码）
                    Long maxValue = Convert.toLong(DltAgreement.hex16ToStr(newValue)) + 1;
                    // 新数据值为   16进制最大值 - 格式化最大值
                    Long newMaxValue = maxValue - Convert.toLong(fmtVal.replace(".", ""));
                    // 最终值为 新值转16进制 ,并在高位补FF
                    value = DltAgreement.supplementaryMaxHex(DltAgreement.strToHex16(Convert.toStr(newMaxValue)), formatLeng);
                    if (value.length() > columnFormat.getColumnLength()) {
                        // 如果数据位长度 大于 最大数据位，则取16进制最大值
                        value = newValue;
                    }
                }
            }
        } else {
            //最高位替换为正负标识符 ，去掉 "."    并补齐数据位数
            value = DltAgreement.supplementaryHex(DLT698Utils.strToHex16(fmtVal.replace(".", "")), formatLeng);
        }
        return value.toUpperCase(Locale.ENGLISH);
    }

    /**
     * 获取数据域长度
     * 此方法具有局限性
     * 参数data必须包含 data length 标识 ，将数据长度补充为2个字节 00 00
     *
     * @param frame
     * @return
     */
    public static String getHexDataLength(String frame) {
//        int length = (frame.substring(frame.indexOf(ControlConstants.DATA_LENGTH_FLAG)).replace(ControlConstants.DATA_LENGTH_FLAG,"")).length()/2;
        int length = (frame.length() / 2) - 2;
        if (length > IntegerUtil.INTEGER_65535) {
            length = IntegerUtil.INTEGER_65535;
        }
        String hexLength = Integer.toHexString(length);
        hexLength = ProtocolUtils.formatHighLow(ProtocolUtils.supplementaryHex(hexLength, 2));
        return hexLength.toUpperCase(Locale.ENGLISH);
    }

    /**
     * 字符串转日志
     *
     * @param dateStr
     * @return
     */
    public static Date parseDate(String dateStr) {
        try {
            return DateTools.getFormat3().parse(dateStr);
        } catch (ParseException e) {
            LOGGER.error("解析日期出现错误", e);
            return null;
        }
    }

    /**
     * 日期格式化
     * 低位在前
     *
     * @param date
     * @return
     */
    public static String formatDate(Date date) {
        return plusHighLow(DateTools.getFormat3().format(date));
    }

    /**
     * 日期转换格式16进制
     */
    public static String formatDateToHex16(Date date) {
        String s = "";
        if (StringUtils.isNotNull(date)) {
            String[] formatArray = splitFormatDate(DateTools.getFormat4().format(date));
            for (String farmat : formatArray) {
                s = s + strToHex16(farmat);
            }
        }
        return s;
    }

    /**
     * 日期转换格式16进制（按年月日时分秒转换）
     */
    public static String formatDateToHhMmSsMmHex16(Date date) {
        StringBuilder sb = new StringBuilder();
        if (StringUtils.isNotNull(date)) {
            String[] formatArray = splitFormatDateMm(DateTools.getFormat7().format(date));
            sb.append(ProtocolUtils.supplementaryHex(DLT698Utils.strToHex16(formatArray[0]), 2));
            sb.append(ProtocolUtils.supplementaryHex(DLT698Utils.strToHex16(formatArray[1]), 1));
            sb.append(ProtocolUtils.supplementaryHex(DLT698Utils.strToHex16(formatArray[2]), 1));
            int dayOfWeek = DateUtil.dayOfWeek(date) - 1;
            if (dayOfWeek == 0) {
                dayOfWeek = 7;
            }
            sb.append(ProtocolUtils.supplementaryHex(ProtocolUtils.intToHex16(dayOfWeek), 1));
            sb.append(ProtocolUtils.supplementaryHex(DLT698Utils.strToHex16(formatArray[3]), 1));
            sb.append(ProtocolUtils.supplementaryHex(DLT698Utils.strToHex16(formatArray[4]), 1));
            sb.append(ProtocolUtils.supplementaryHex(DLT698Utils.strToHex16(formatArray[5]), 1));
            sb.append(ProtocolUtils.supplementaryHex(DLT698Utils.strToHex16(formatArray[6]), 2));
        }
        return sb.toString();
    }

    /**
     *
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
     * 10进制转换为16进制
     *
     * @param farmat
     * @return
     */
    public static String strToHex16(String farmat) {
        String a = Long.toHexString(Convert.toLong(farmat));
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
        BigInteger bigint = new BigInteger(farmat, 16);
        String a = Convert.toStr(bigint.intValue());
        if (a.length() % IntegerUtil.INTEGER_2 == 1) {
            a = "0" + a;
        }
        return a;
    }

    /**
     * 高低位转换
     *
     * @return
     */
    public static String plusHighLow(String data) {
        return formatHighLow(data);
    }

    /**
     * 高低位转换
     *
     * @return
     */
    public static String minusHighLow(String data) {
        return formatHighLow(data);
    }

    /**
     * 转换数据高低位顺序为：低位在前 高位在后
     *
     * @param data data为偶数
     * @return
     */
    public static String formatHighLow(String data) {
        StringBuilder str = new StringBuilder();
        for (int i = data.length(); i >= IntegerUtil.INTEGER_2; i -= IntegerUtil.INTEGER_2) {
            str.append(data, i - 2, i);
        }
        return str.toString();
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
        int total = 0;
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
        return hexStr.substring(hexStr.length() - IntegerUtil.INTEGER_2).toUpperCase(Locale.ENGLISH);
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
     * 验证帧数据并转换为帧对象
     *
     * @param str
     * @return
     */
    public static Control698Data checkControlStr(String str, String serialNumber) {
        if (!StringUtils.isEmpty(str)) {
            Control698Data cd = new Control698Data();
            String headerLength = str.substring(0, ControlConstants.HEADER_LENGTH);
            // 如果请求帧头为FE唤醒  则替换后再进行拆解
            if (StringUtils.containsIgnoreCase(ControlConstants.AWAKEN_COMMAND_FE, headerLength)) {
                str = str.replaceAll("(?i)FEFEFEFE", "");
            }
            // 帧头
            cd.setHeader68(str.substring(0, ControlConstants.HEADER_LENGTH));
            // 长度
            cd.setControlLeng(str.substring(ControlConstants.HEADER_LENGTH, ControlConstants.CONTROL_LENGTH));
            // 验证长度是否符合
            Integer countLength = Convert.toInt(DLT698Utils.hex16ToStr(cd.getControlLeng().substring(0, 2)));
            if (countLength != (str.length() - IntegerUtil.INTEGER_4) / IntegerUtil.INTEGER_2) {
                LOGGER.error("[DLT-698][命令帧异常][长度异常无法解析]:{}", str);
                return null;
            }
            // 控制域
            cd.setControlRegion(str.substring(ControlConstants.CONTROL_LENGTH, ControlConstants.REGION_LENGTH));
            // 地址类型
            cd.setRegionType(str.substring(ControlConstants.REGION_LENGTH, ControlConstants.REGION_TYPE_LENGTH));
            // 服务器地址SA
            cd.setServerAddress(str.substring(ControlConstants.REGION_TYPE_LENGTH, ControlConstants.SERVICE_ADDRESS_LENGTH));
            // 服务器地址CA
            cd.setCustomerAddress(str.substring(ControlConstants.SERVICE_ADDRESS_LENGTH, ControlConstants.CUSTOMER_ADDRESS_LENGTH));
            // 帧头校验HCS
            cd.setHeaderChecks(str.substring(ControlConstants.CUSTOMER_ADDRESS_LENGTH, ControlConstants.HEADER_CHECKS_LENGTH));
            // 帧头检验HCS数据
            cd.setHeaderCheckDataStr(str.substring(ControlConstants.HEADER_LENGTH, ControlConstants.CUSTOMER_ADDRESS_LENGTH));
            //验证帧头检验HSC数据
            String fillInHsC = DLT698Utils.cheakFramesStr(cd.getHeaderCheckDataStr());
            if (!cd.getHeaderChecks().equalsIgnoreCase(fillInHsC)) {
                LOGGER.error("[DLT-698][命令帧异常][帧头检验HSC数据异常]:{}", str);
                return null;
            }
            // 数据域
            cd.setControlData(str.substring(ControlConstants.HEADER_CHECKS_LENGTH, str.length() + ControlConstants.FINISH_CHECKS_LENGTH));
            // 帧数据中数据范围
            ControlApudDatas cad = new ControlApudDatas();
            // 请求类型
            cad.setRequestNormal(cd.getControlData().substring(0, 4));
            // 请求类型及特征拆分
            RequestNormalData rnd = new RequestNormalData();
            rnd.setType(cad.getRequestNormal().substring(0, 2));
            rnd.setNumber(cad.getRequestNormal().substring(2));
            cad.setRequestNormalData(rnd);
            // PIID
            cad.setPiid(cd.getControlData().substring(4, 6));
            // 实时数据
            if (str.contains(DLT698Constants.FrozenDataFlag.CVT_MONITORING_DATA.getCode() + DLT698Constants.FrozenDataFlag.CVT_ERROR_DATA.getCode())
                    || str.contains(DLT698Constants.FrozenDataFlag.CVT_ERROR_DATA.getCode() + DLT698Constants.FrozenDataFlag.CVT_MONITORING_DATA.getCode())) {
                Integer strLength = Convert.toInt(cd.getControlData().substring(6, 8));
                List<RequestOAD> oadList = new ArrayList<>();
                // OAD
                cad.setOad(ControlConstants.DataFlag.CVT_REAL_DATA.getCode());
                RequestOAD ro = new RequestOAD();
                ro.setDataOi(ControlConstants.DataFlag.CVT_REAL_DATA.getCode());
                ro.setAttribute("0200");
                cad.setRequestOAD(ro);
                int strSize = 8;
                for (int i = 0; i < strLength; i++) {
                    RequestOAD oad = new RequestOAD();
                    oad.setDataOi(cd.getControlData().substring(strSize, strSize + 4));
                    oad.setAttribute(cd.getControlData().substring(strSize + 4, strSize + 8));
                    strSize = strSize + 8;
                    oadList.add(oad);
                }
                cad.setRequestOADList(oadList);
                cad.setTimeTag(cd.getControlData().substring(strSize));

            } else if (cd.getControlData().substring(IntegerUtil.INTEGER_8, IntegerUtil.INTEGER_16).equals(DLT698Constants.FrozenDataFlag.CVT_DETECTS_HARDWARE_INFORMATION.getCode())) {
                // OAD //硬件信息
                cad.setOad(cd.getControlData().substring(8, 16));
                RequestOAD ro = new RequestOAD();
                ro.setDataOi(cad.getOad().substring(0, 4));
                ro.setAttribute(cad.getOad().substring(4));
                cad.setRequestOAD(ro);
                cad.setTimeTag(cd.getControlData().substring(16));
            } else {
                // OAD
                cad.setOad(cd.getControlData().substring(6, 14));
                RequestOAD ro = new RequestOAD();
                ro.setDataOi(cad.getOad().substring(0, 4));
                ro.setAttribute(cad.getOad().substring(4));
                cad.setRequestOAD(ro);
                if (ControlConstants.DataFlag.CVT_EVENT_DATA.getCode().equalsIgnoreCase(ro.getDataOi()) ||
                        ControlConstants.DataFlag.CVT_ACQUISITION_OF_ABNORMAL_EVENTS_RECORDS.getCode().equalsIgnoreCase(ro.getDataOi()) ||
                        ControlConstants.DataFlag.CVT_ACQUISITION_OF_ABNORMAL_EVENTS.getCode().equalsIgnoreCase(ro.getDataOi())) {
                    // RSD
                    RequestEventDataRsdType redrt = new RequestEventDataRsdType();
                    redrt.setRsdTypeStr(cd.getControlData().substring(14, 18));
                    redrt.setType(cd.getControlData().substring(14, 16));
                    redrt.setValue(cd.getControlData().substring(16, 18));
                    cad.setRequestEventDataRsdType(redrt);
                    // RCSD
                    Integer startLength = 18;
                    if ("00".equals(cd.getControlData().substring(IntegerUtil.INTEGER_18, IntegerUtil.INTEGER_20))) {
                        startLength = rcsdTypeIsNotData(cad, IntegerUtil.INTEGER_18);
                    } else {
                        startLength = rcsdTypeData(cad, cd.getControlData(), IntegerUtil.INTEGER_18);
                    }
                    // 添加默认数据对象
                    RequestRCSDFrozenDataType rrcsdf = cad.getRequestRCSDFrozenDataType();
                    List<String> stringList = rrcsdf.getRcsdList();
                    List<Map<String, String>> rcsdMapList = rrcsdf.getRcsdMapList();
                    rrcsdf.setRcsdList(stringList);
                    rrcsdf.setRcsdMapList(rcsdMapList);
                    cad.setRequestRCSDFrozenDataType(rrcsdf);

                    cad.setTimeTag(cd.getControlData().substring(startLength));
                } else // 广播校时 拆分数据域
                    if (ControlConstants.DataFlag.CORRECTING_TIME.getCode().equalsIgnoreCase(cad.getOad())) {
                        CorrectingTimeData ctd = new CorrectingTimeData();
                        ctd.setDataStr(cd.getControlData().substring(14, 30));
                        ctd.setTimeDateStr(ctd.getDataStr().substring(2));
                        Date parse = DLT698Utils.hex16ToFormatDate(ctd.getTimeDateStr());
                        ctd.setTimeDateTime(parse);
                        cad.setCorrectingTimeData(ctd);
                        cad.setTimeTag(cd.getControlData().substring(30));
                    } else // 日冻结数据  月冻结数据  年冻结数据
                        if (ControlConstants.DataFlag.CVT_FROZEN_DATA.getCode().equalsIgnoreCase(ro.getDataOi())
                                || ControlConstants.DataFlag.CVT_DAYS_FROZEN_DATA.getCode().equalsIgnoreCase(ro.getDataOi())
                                || ControlConstants.DataFlag.CVT_MONTHS_FROZEN_DATA.getCode().equalsIgnoreCase(ro.getDataOi())
                                || ControlConstants.DataFlag.CVT_YEARS_FROZEN_DATA.getCode().equalsIgnoreCase(ro.getDataOi())) {
                            if ("01".equals(cd.getControlData().substring(IntegerUtil.INTEGER_14, IntegerUtil.INTEGER_16))) {
                                // RSD
                                RequestFrozenDaysDataRsdType redrt = new RequestFrozenDaysDataRsdType();
                                redrt.setRsdTypeStr(cd.getControlData().substring(IntegerUtil.INTEGER_14, IntegerUtil.INTEGER_36));
                                redrt.setType(cd.getControlData().substring(IntegerUtil.INTEGER_14, IntegerUtil.INTEGER_16));
                                redrt.setRsdOad(cd.getControlData().substring(IntegerUtil.INTEGER_16, IntegerUtil.INTEGER_24));
                                redrt.setDateTimeStr(cd.getControlData().substring(IntegerUtil.INTEGER_24, IntegerUtil.INTEGER_40));
                                redrt.setDateTimeVal(cd.getControlData().substring(IntegerUtil.INTEGER_26, IntegerUtil.INTEGER_40));
                                redrt.setDateTime(DLT698Utils.hex16ToFormatDate(redrt.getDateTimeVal()));
                                redrt.setStartTime(redrt.getDateTime());
                                redrt.setStartTimeStr(redrt.getDateTimeStr());
                                cad.setRequestFrozenDaysDataRsdType(redrt);
                                // RCSD
                                Integer startLength = rcsdTypeData(cad, cd.getControlData(), IntegerUtil.INTEGER_40);
                                cad.setTimeTag(cd.getControlData().substring(startLength));
                            } else if ("02".equals(cd.getControlData().substring(IntegerUtil.INTEGER_14, IntegerUtil.INTEGER_16))) {
                                // RSD
                                RequestFrozenDaysDataRsdType rfdt = new RequestFrozenDaysDataRsdType();
                                rfdt.setType(cd.getControlData().substring(IntegerUtil.INTEGER_14, IntegerUtil.INTEGER_16));
                                rfdt.setRsdOad(cd.getControlData().substring(IntegerUtil.INTEGER_16, IntegerUtil.INTEGER_24));
                                rfdt.setStartTimeStr(cd.getControlData().substring(IntegerUtil.INTEGER_24, IntegerUtil.INTEGER_40));
                                rfdt.setStartTime(DLT698Utils.hex16ToFormatDate(rfdt.getStartTimeStr().substring(IntegerUtil.INTEGER_2)));
                                rfdt.setEndTimeStr(cd.getControlData().substring(IntegerUtil.INTEGER_40, IntegerUtil.INTEGER_56));
                                rfdt.setEndTime(DLT698Utils.hex16ToFormatDate(rfdt.getEndTimeStr().substring(IntegerUtil.INTEGER_2)));
                                rfdt.setUnitTimeStr(cd.getControlData().substring(IntegerUtil.INTEGER_56, IntegerUtil.INTEGER_64));
                                rfdt.setUnitTime(Convert.toInt(DLT698Utils.hex16ToStr(rfdt.getUnitTimeStr().substring(IntegerUtil.INTEGER_4, IntegerUtil.INTEGER_8))));
                                rfdt.setRsdTypeStr(cd.getControlData().substring(IntegerUtil.INTEGER_14, IntegerUtil.INTEGER_64));
                                rfdt.setDateTimeVal(rfdt.getStartTimeStr().substring(IntegerUtil.INTEGER_2));
                                rfdt.setDateTime(rfdt.getStartTime());
                                rfdt.setDateTimeStr(rfdt.getStartTimeStr());
                                cad.setRequestFrozenDaysDataRsdType(rfdt);
                                // RCSD
                                Integer startLength = rcsdTypeData(cad, cd.getControlData(), IntegerUtil.INTEGER_64);
                                cad.setTimeTag(cd.getControlData().substring(startLength));
                            } else {
                                // RSD
                                RequestFrozenDaysDataRsdType redrt = new RequestFrozenDaysDataRsdType();
                                redrt.setRsdTypeStr(cd.getControlData().substring(IntegerUtil.INTEGER_14, IntegerUtil.INTEGER_18));
                                redrt.setType(cd.getControlData().substring(IntegerUtil.INTEGER_14, IntegerUtil.INTEGER_16));

                                cad.setRequestFrozenDaysDataRsdType(redrt);
                                // RCSD
                                Integer startLength = rcsdTypeData(cad, cd.getControlData(), IntegerUtil.INTEGER_18);
                                cad.setTimeTag(cd.getControlData().substring(startLength));
                            }
                        } else {
                            cad.setTimeTag(cd.getControlData().substring(IntegerUtil.INTEGER_14));
                        }
            }
            if (!"00".equals(cad.getTimeTag())) {
                RequestTimeTagData timeTagData = new RequestTimeTagData();
                timeTagData.setIsTimeTag(cad.getTimeTag().substring(IntegerUtil.INTEGER_0, IntegerUtil.INTEGER_2));
                timeTagData.setSelectTimeTag(cad.getTimeTag().substring(IntegerUtil.INTEGER_2, IntegerUtil.INTEGER_16));
                timeTagData.setSelectTimeTagDate(DLT698Utils.hex16ToFormatDate(timeTagData.getSelectTimeTag()));
                timeTagData.setAwaitTimeTag(cad.getTimeTag().substring(IntegerUtil.INTEGER_16));
                cad.setTimeTagData(timeTagData);
            }
            cd.setControlApudDatas(cad);
            // 帧校验FCS
            cd.setFinishChecks(str.substring(str.length() + ControlConstants.FINISH_CHECKS_LENGTH, str.length() + ControlConstants.FOOTER_LENGTH));
            // 帧校验FCS数据
            cd.setFinishCheckDataStr(str.substring(ControlConstants.HEADER_LENGTH, str.length() + ControlConstants.FINISH_CHECKS_LENGTH));
            String fillInFsC = DLT698Utils.cheakFramesStr(cd.getFinishCheckDataStr());
            if (!cd.getFinishChecks().equalsIgnoreCase(fillInFsC)) {
                LOGGER.error("[DLT-698][命令帧异常][帧尾校验FCS数据异常]:{}", str);
                return null;
            }
            // 帧尾
            cd.setFooter16(str.substring(str.length() + ControlConstants.FOOTER_LENGTH));
            return cd;
        }
        return null;
    }

    /**
     * 事件数据RCSD部份拆分
     *
     * @param cad
     * @param data
     * @param dataLength
     * @return
     */
    public static Integer rcsdTypeData(ControlApudDatas cad, String data, Integer dataLength) {
        // RCSD
        Integer startLength = dataLength + 2;
        RequestRCSDFrozenDataType rrcsdf = new RequestRCSDFrozenDataType();
        rrcsdf.setRcsdLength(data.substring(dataLength, startLength));
        Integer rcsdLeng = Convert.toInt(DLT698Utils.hex16ToStr(rrcsdf.getRcsdLength()));
        Integer endLength = startLength;
        List<String> stringList = new ArrayList<>();
        List<Map<String, String>> rcsdMapList = new ArrayList<>();
        for (int i = 0; i < rcsdLeng; i++) {
            endLength = endLength + 10;
            String rcsdContent = data.substring(startLength, endLength);
            String rcsdType = rcsdContent.substring(0, 2);
            String content = rcsdContent.substring(2);
            stringList.add(content);
            startLength = endLength;
            Map<String, String> mm = new HashMap<>(IntegerUtil.INTEGER_2);
            mm.put(rcsdType, content);
            rcsdMapList.add(mm);
        }
        rrcsdf.setRcsdList(stringList);
        rrcsdf.setRcsdMapList(rcsdMapList);
        rrcsdf.setRcsdTypeStr(data.substring(dataLength, endLength));
        cad.setRequestRCSDFrozenDataType(rrcsdf);
        return startLength;
    }

    /**
     * 事件数据RCSD部份拆分  无数据部分添加默认数据
     *
     * @param cad
     * @param dataLength
     * @return
     */
    public static Integer rcsdTypeIsNotData(ControlApudDatas cad, Integer dataLength) {
        // RCSD
        RequestRCSDFrozenDataType rrcsdf = new RequestRCSDFrozenDataType();
        List<String> stringList = new ArrayList<>();
        stringList.add("20220200");
        stringList.add("201E0200");
        stringList.add("20200200");
        stringList.add("20240200");
        stringList.add("33000200");
        stringList.add("33430206");
        stringList.add("33430207");
        stringList.add("20680200");
        stringList.add("20690200");
        String startLength = DLT698Utils.strToHex16(stringList.size() + "");
        rrcsdf.setRcsdLength(startLength);
        List<Map<String, String>> rcsdMapList = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        sb.append(startLength);
        String type = "00";
        for (int i = 0; i < stringList.size(); i++) {
            Map<String, String> mm = new HashMap<>(IntegerUtil.INTEGER_2);
            mm.put(type, stringList.get(i));
            sb.append(type);
            sb.append(stringList.get(i));
            rcsdMapList.add(mm);
        }
        rrcsdf.setRcsdList(stringList);
        rrcsdf.setRcsdMapList(rcsdMapList);
        rrcsdf.setRcsdTypeStr(sb.toString());
        cad.setRequestRCSDFrozenDataType(rrcsdf);
        return dataLength + 2;
    }

    /**
     * 将数据对象转换为帧命令
     *
     * @param obj 数据对象
     * @return
     */
    public static String clientDataToControl(Object obj) {
        return "";
    }

    /**
     * 将帧命令转换为数据对象
     *
     * @param str
     * @return
     */
    public static Object controlToClientData(String str) {
        // 先转换为数据对象
        Control698Data control698Data = checkControlStr(str, "COMM1");
        return new Object();
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
            d[i] = (byte) (charToByte(hexChars[pos]) << 4 | charToByte(hexChars[pos + 1]));
        }
        return d;
    }

    /**
     * Convert char to byte
     *
     * @param c char
     * @return byte
     */
    public static byte charToByte(char c) {
        return (byte) "0123456789ABCDEF".indexOf(c);
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
        if (temp != 0) {
            for (int i = 0; i < IntegerUtil.INTEGER_4 - temp; i++) {
                binary = "0" + binary;
            }
        }
        // 重新计算长度
        length = binary.length();
        StringBuilder sb = new StringBuilder();
        // 每4个二进制数为一组进行计算
        for (int i = 0; i < length / IntegerUtil.INTEGER_4; i++) {
            int num = 0;
            // 将4个二进制数转成整数
            for (int j = i * IntegerUtil.INTEGER_4; j < i * IntegerUtil.INTEGER_4 + IntegerUtil.INTEGER_4; j++) {
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
     * 将字符串转成ASCII
     */
    public static String stringToAscii(String value) {
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
        String asciiValue = stringToAscii(value);
        String[] split = asciiValue.split(",");
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < split.length; i++) {
            sb.append(strToHex16(split[i]));
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
     * 添加  接收  命令帧对象
     *
     * @param control698Data 帧命令转换对象 (当接收正常时才有，否则为NULL)
     * @param commandFrame   命令帧
     * @param sendType       发送类型(1接收,2发送)
     * @param isNormal       是否正常(1正常,2异常)
     * @param remark         备注
     */
    public static void sendCommandFrameInfo(Control698Data control698Data, String commandFrame, Integer sendType, Integer isNormal, String remark) {
        DateTime date = DateUtil.date();
        CommandFrameInfo cfi = new CommandFrameInfo();
        // 设备类型(1威胜电能表,2能源控制器)
        cfi.setCommunicationType(Convert.toInt(EventConstants.SysCommunicationType.SERIAL_DLT_698.getCode()));
        // 发送类型(1接收,2发送)
        cfi.setSendType(sendType);
        // 如果为正常时
        if (isNormal == 1) {
            // 表计地址
            String meterAddr = control698Data.getServerAddress();
            cfi.setMeterAddr(meterAddr);
            /*AddrVoltagePhase transmissionLine = AddrVoltagePhaseCache.getByChannelMeterAdd(DLT698Utils.formatHighLow(meterAddr));
            if (StringUtils.isNotNull(transmissionLine)) {
                // 通道号
                cfi.setChannelNum(transmissionLine.getChannelNum());
                // 相序
                cfi.setPhaseSeq(transmissionLine.getPhaseSeq());
            }*/
            // 命令帧时间
            try {
                cfi.setSendTime(control698Data.getControlApudDatas().timeTagData.getSelectTimeTagDate());
            } catch (Exception e) {

            }
            // 命令帧数据OAD
            cfi.setFrameOad(control698Data.getControlApudDatas().getOad());
            cfi.setFrameOadName(ControlConstants.DataFlag.getDescName(cfi.getFrameOad()));
        } else if (IntegerUtil.INTEGER_3.equals(isNormal)) {
            cfi.setMeterAddr(remark);
            cfi.setFrameOad(ControlConstants.DataFlag.CORRECTING_TIME.getCode());
            cfi.setFrameOadName(ControlConstants.DataFlag.CORRECTING_TIME.getDesc());
            cfi.setFrameData(commandFrame);
            isNormal = 1;
            // 当 isNormal == 3 时 表示为广播校时，当为广播校时时，将数据状态修改为正常，将备注修改为空
            remark = null;
        }
        // 命令帧
        cfi.setFrameData(commandFrame);
        // 创建时间
        cfi.setCreateTime(date);
        // 是否正常(1正常,2异常)
        cfi.setIsNormal(isNormal);
        // 备注
        cfi.setRemark(remark);
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
        if (minutes != IntegerUtil.INTEGER_0 && minutes != IntegerUtil.INTEGER_15 && minutes != IntegerUtil.INTEGER_30 && minutes != IntegerUtil.INTEGER_45) {
            int yushu = minutes % IntegerUtil.INTEGER_15;
            // 取四舍五入   大于10分钟时按最近15分钟的时间取数据
            if (yushu > IntegerUtil.INTEGER_7) {
                minutes = (IntegerUtil.INTEGER_15 - yushu);
            } else {
                // 小于10分钟时按前一个最近15分钟的时间时
                minutes = yushu * -IntegerUtil.INTEGER_1;
            }
        } else {
            minutes = 0;
        }
        return DateUtil.offsetMinute(dateTime, minutes);
    }
}
