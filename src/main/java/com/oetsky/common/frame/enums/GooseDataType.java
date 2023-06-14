package com.oetsky.common.frame.enums;

import com.oetsky.common.utils.StringUtils;

public enum GooseDataType {
    /**
     * 读取电压/电流采集板校正系数
     */
    BOOLEAN_TYPE("83", 1, "单点"),
    CHAR_TYPE("84", 1, "双点/品质"),
    INT_TYPE("85", 4, "整形"),
    UNIT_TYPE("86", 4, "无符号整形"),
    FLOAT_TYPE("87", 4, "单精度浮点"),
    STRING_TYPE("8A", 0, "字符串类型"),
    UTC_TIME_TYPE("91", 8, "时间"),
    CHAR_ARRAY_TYPE("A2", 0, "结构体");


    private String code;

    private int dataLength;

    private String desc;

    GooseDataType(String code, int dataLength, String desc) {
        this.code = code;
        this.dataLength = dataLength;
        this.desc = desc;
    }

    public static String getGooseDataTypeByDesc(String code) {
        if (StringUtils.isNotEmpty(code)) {
            GooseDataType[] values = GooseDataType.values();
            for (GooseDataType pt : values) {
                if (pt.getCode().equals(code)) {
                    return pt.getCode();
                }
            }
        }
        return "";
    }

    public static Integer getGooseDataTypeByLength(String code) {
        if (StringUtils.isNotEmpty(code)) {
            GooseDataType[] values = GooseDataType.values();
            for (GooseDataType pt : values) {
                if (pt.code.equals(code)) {
                    return pt.getDataLength();
                }
            }
        }
        return null;
    }

    /**
     * 获取
     *
     * @return code
     */
    public String getCode() {
        return this.code;
    }

    /**
     * 设置
     *
     * @param code
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * 获取
     *
     * @return dataLength
     */
    public int getDataLength() {
        return this.dataLength;
    }

    /**
     * 设置
     *
     * @param dataLength
     */
    public void setDataLength(int dataLength) {
        this.dataLength = dataLength;
    }

    /**
     * 获取
     *
     * @return desc
     */
    public String getDesc() {
        return this.desc;
    }

    /**
     * 设置
     *
     * @param desc
     */
    public void setDesc(String desc) {
        this.desc = desc;
    }
}
