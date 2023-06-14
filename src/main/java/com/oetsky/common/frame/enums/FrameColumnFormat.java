package com.oetsky.common.frame.enums;
/**
 * 数据字段 数据格式
 */
public enum FrameColumnFormat {
    /**
     * 抽样数据
     */
    FUNDAMENTAL_FREQUENCY("000000.0000",5 * 2,"基波频率"),
    FUNDAMENTAL_AMPLITUDE("000000.0000",5 * 2,"基波有效值"),
    HARMONIC_AMPLITUDE_3("000000.0000",5 * 2,"3次谐波有效值"),
    HARMONIC_AMPLITUDE_5("000000.0000",5 * 2,"5次谐波有效值"),
    FUNDAMENTAL_PHASE("000000.00",4 * 2,"基波相位"),
    HARMONIC_PHASE_3("000000.00",4 * 2,"3次谐波相位"),
    HARMONIC_PHASE_5("000000.00",4 * 2,"5次谐波相位"),
    ZERO_SEQUENCE_VOLTAGE_IMBALANCE("000000.0000",5 * 2,"零序电压不平衡度"),
    NEGATIVE_SEQUENCE_VOLTAGE_IMBALANCE("000000.0000",5 * 2,"负序电压不平衡度"),
    /**
     * 温湿度
     */
    TEMPERATURE("000.0",2*2,"温度"),
    HUMIDITY("000.0",2*2,"湿度"),

    /**
     * 计算数据
     */
    RATIO_AVERAGE("000000.00000000",7*2,"比差均值"),
    RATIO_VARIANCE("000000.00000000",7*2,"比差方差"),
    ANGLE_AVERAGE("000000.00000000",7*2,"角差均值"),
    ANGLE_VARIANCE("000000.00000000",7*2,"角差方差"),

    /**
     * 异常日志
     */
    ERROR_START_TIME("yyMMddHHmmss",6*2,"异常开始时间"),
    ERROR_END_TIME("yyMMddHHmmss",6*2,"异常开始时间"),
    INTERFACE_TYPE("00",1 * 2,"接口类型"),
    ERROR_TYPE("00",1 * 2,"异常类型")
    ;

    FrameColumnFormat(String decimalFormat, Integer columnLength, String desc){
        this.decimalFormat = decimalFormat;
        this.columnLength = columnLength;
        this.desc = desc;
    }

    /**
     * 数据格式
     */
    private String decimalFormat ;

    /**
     * 字段数据的长度
     */
    private int columnLength ;

    /**
     * 备注
     */
    private String desc ;

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
     * 获取 备注
     *
     * @return desc 备注
     */
    public String getDesc() {
        return this.desc;
    }

    /**
     * 设置 备注
     *
     * @param desc 备注
     */
    public void setDesc(String desc) {
        this.desc = desc;
    }
}
