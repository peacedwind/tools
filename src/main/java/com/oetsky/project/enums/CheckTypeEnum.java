package com.oetsky.project.enums;

/**
 * @description:
 * @author: cyx
 * @date: 2023-06-16
 **/
public enum CheckTypeEnum {
    VOLTAGE_DATA_CHECK(1,"电压采集数据校验"),

    VOLTAGE_ERROR(2,"电压计算数据校验"),

    INSPECTION_EVENT(3,"事件数据校验");

    private Integer checkType;

    private String desc;

    CheckTypeEnum(Integer checkType, String desc) {
        this.checkType = checkType;
        this.desc = desc;
    }

    public Integer getCheckType() {
        return checkType;
    }

    public String getDesc() {
        return desc;
    }
}
