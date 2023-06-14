package com.oetsky.common.frame.enums;

import com.oetsky.common.utils.StringUtils;

public enum ProtocolDataTagType {

    BOOLEAN_TYPE("1", 1, "布尔", "1:true,0:false"),
    TINY_TYPE("43", 1, "小整形", "-128…127"),
    UTINY_TYPE("32", 1, "无符号小整形", "0…255"),
    SHORT_TYPE("33", 2, "短整形", "-32768…32767"),
    USHORT_TYPE("45", 2, "无符号短整形", "0…65535"),
    INT_TYPE("2", 4, "整形", "-2147483648…2147483648"),
    UINT_TYPE("35", 4, "无符号整形", "0…4294967295"),
    LONG_TYPE("36", 8, "长整形", "-2^64…2^64"),
    ULONG_TYPE("37", 8, "无符号长整形", "0…2^128-1"),
    FLOAT_TYPE("38", 4, "单精度浮点", "-2^128…2^128"),
    DOUBLE_TYPE("39", 8, "双精度浮点", "-2^1024…2^1024"),
    STRING_TYPE("4", 4, "字符串类型", "一个或者多个ASCII 组成,最长64 个字节，以‘\\0’结尾");

    /**
     * 类型标记
     */
    public String code;
    /**
     * 类型长度
     */
    public Integer dataLength;
    /**
     * 类型描述
     */
    public String desc;
    /**
     * 类型备注
     */
    public String remark;



    ProtocolDataTagType(String code, int dataLength, String desc, String remark) {
        this.code = code;
        this.dataLength = dataLength;
        this.desc = desc;
        this.remark = remark;
    }

    public static ProtocolDataTagType getProtocolDataTagType(String code) {
        if (StringUtils.isNotEmpty(code)) {
            ProtocolDataTagType[] values = ProtocolDataTagType.values();
            for (ProtocolDataTagType pt : values) {
                if (pt.code.equals(code)) {
                    return pt;
                }
            }
        }
        return null;
    }

    /**
     * 获取 类型标记
     *
     * @return code 类型标记
     */
    public String getCode() {
        return this.code;
    }

    /**
     * 设置 类型标记
     *
     * @param code 类型标记
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * 获取 类型长度
     *
     * @return dataLength 类型长度
     */
    public Integer getDataLength() {
        return this.dataLength;
    }

    /**
     * 设置 类型长度
     *
     * @param dataLength 类型长度
     */
    public void setDataLength(Integer dataLength) {
        this.dataLength = dataLength;
    }

    /**
     * 获取 类型描述
     *
     * @return desc 类型描述
     */
    public String getDesc() {
        return this.desc;
    }

    /**
     * 设置 类型描述
     *
     * @param desc 类型描述
     */
    public void setDesc(String desc) {
        this.desc = desc;
    }

    /**
     * 获取 类型备注
     *
     * @return remark 类型备注
     */
    public String getRemark() {
        return this.remark;
    }

    /**
     * 设置 类型备注
     *
     * @param remark 类型备注
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }
}
