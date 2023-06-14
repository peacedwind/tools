package com.oetsky.common.frame.domain;

import java.math.BigDecimal;

public enum AnalysisDataMax {
    /**
     * 采集最大值
     */
    FUN_FREQUENCY(BigDecimal.valueOf(99999.99998), "xxxxx.xxxxx", "基波频率"),
    FUN_AMPLITUDE(BigDecimal.valueOf(99999.99998), "xxxxx.xxxxx", "基波幅值"),
    FUN3_AMPLITUDE(BigDecimal.valueOf(99999.9999998), "xxxxx.xxxxxxx", "3次谐波幅值"),
    FUN5_AMPLITUDE(BigDecimal.valueOf(99999.9999998), "xxxxx.xxxxxxx", "5次谐波幅值"),
    FUN_PHASE(BigDecimal.valueOf(99999999.98), "xxxxxxxx.xx", "基波相位"),
    HAR3_PHASE(BigDecimal.valueOf(99999999.98), "xxxxxxxx.xx", "3次谐波相位"),
    HAR5_PHASE(BigDecimal.valueOf(99999999.98), "xxxxxxxx.xx", "5次谐波相位"),
    ZERO_SEQUENCE(BigDecimal.valueOf(99999999.99998), "xxxxxxxx.xxxxx", "零序电压不平衡度"),
    NEGATIVE_SEQUENCE(BigDecimal.valueOf(99999999.99998), "xxxxxxxx.xxxxx", "负序电压不平衡度"),



    ERROR_MAX(BigDecimal.valueOf(999999.999998), "xxxxxx.xxxxxx", "比差"),
    ANGLE_ERROR(BigDecimal.valueOf(999999.999998), "xxxxxx.xxxxxx", "角差"),
    PER_MAX(BigDecimal.valueOf(999999.999998), "xxxxxx.xxxxxx", "误差百分比"),


    AVERAGE_MAX(BigDecimal.valueOf(999999.999998), "xxxxxx.xxxxxx", "送检比差"),
    VARIANCE_MAX(BigDecimal.valueOf(999999.999998), "xxxxxx.xxxxxx", "送检比差方差"),
    ANGLE_AVG(BigDecimal.valueOf(999999.999998), "xxxxxx.xxxxxx", "送检角差"),
    ANGLE_VRE(BigDecimal.valueOf(999999.999998), "xxxxxx.xxxxxx", "送检角差方差");

    /**
     * 最大值
     */
    private BigDecimal maxValue;

    /**
     * 格式化
     */
    private String formateName;

    /**
     * 描述
     */
    private String desc;

    AnalysisDataMax(BigDecimal maxValue, String formateName, String desc) {
        this.maxValue = maxValue;
        this.formateName = formateName;
        this.desc = desc;
    }


    /**
     * 获取 最大值
     *
     * @return maxValue 最大值
     */
    public BigDecimal getMaxValue() {
        return this.maxValue;
    }

    /**
     * 设置 最大值
     *
     * @param maxValue 最大值
     */
    public void setMaxValue(BigDecimal maxValue) {
        this.maxValue = maxValue;
    }

    /**
     * 获取 格式化
     *
     * @return formateName 格式化
     */
    public String getFormateName() {
        return this.formateName;
    }

    /**
     * 设置 格式化
     *
     * @param formateName 格式化
     */
    public void setFormateName(String formateName) {
        this.formateName = formateName;
    }

    /**
     * 获取 描述
     *
     * @return desc 描述
     */
    public String getDesc() {
        return this.desc;
    }

    /**
     * 设置 描述
     *
     * @param desc 描述
     */
    public void setDesc(String desc) {
        this.desc = desc;
    }
}
