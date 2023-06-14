package com.oetsky.common.frame.enums;

public enum ColumnFormat {
    FUNDAMENTAL_FREQUENCY("000000.0000", 5 * 2, 4, "基波频率"),
    FUNDAMENTAL_AMPLITUDE("00000.00000", 5 * 2, 5, "基波有效值"),
    HARMONIC_AMPLITUDE_3("000000.0000", 5 * 2, 4, "3次谐波有效值"),
    HARMONIC_AMPLITUDE_5("000000.0000", 5 * 2, 4, "5次谐波有效值"),
    FUNDAMENTAL_PHASE("000000.00", 4 * 2, 2, "基波相位"),
    HARMONIC_PHASE_3("000000.00", 4 * 2, 2, "3次谐波相位"),
    HARMONIC_PHASE_5("000000.00", 4 * 2, 2, "5次谐波相位"),
    ZERO_SEQUENCE_VOLTAGE_IMBALANCE("000000.0000", 5 * 2, 4, "零序电压不平衡度"),
    NEGATIVE_SEQUENCE_VOLTAGE_IMBALANCE("000000.0000", 5 * 2, 4, "负序电压不平衡度");
    ColumnFormat(String decimalFormat, int columnLength, int scale, String desc){
        this.decimalFormat = decimalFormat;
        this.columnLength = columnLength;
        this.scale = scale;
        this.desc = desc;
    }

    /**
     * 数据格式
     */
    private String decimalFormat;
    /**
     * 字段数据的长度
     */
    private int columnLength;
    /**
     * 字段数据的长度
     */
    private int scale;

    private String desc;

    /**
     * 获取 数据格式
     *
     * @return decimalFormat 数据格式
     */
    public String getDecimalFormat() {
        return this.decimalFormat;
    }

    /**
     * 设置 数据格式
     *
     * @param decimalFormat 数据格式
     */
    public void setDecimalFormat(String decimalFormat) {
        this.decimalFormat = decimalFormat;
    }

    /**
     * 获取 字段数据的长度
     *
     * @return columnLength 字段数据的长度
     */
    public int getColumnLength() {
        return this.columnLength;
    }

    /**
     * 设置 字段数据的长度
     *
     * @param columnLength 字段数据的长度
     */
    public void setColumnLength(int columnLength) {
        this.columnLength = columnLength;
    }

    /**
     * 获取 字段数据的长度
     *
     * @return scale 字段数据的长度
     */
    public int getScale() {
        return this.scale;
    }

    /**
     * 设置 字段数据的长度
     *
     * @param scale 字段数据的长度
     */
    public void setScale(int scale) {
        this.scale = scale;
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
