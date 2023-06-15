package com.oetsky.project.enums;

/**
 * @description: 事件状态枚举
 * @author: cyx
 * @date: 2022-10-18
 **/
public enum EventStatusEnum {

    NOT_RECOVERY(0,"未恢复"),

    READY_RECOVERY(1,"已恢复");

    /**
     * 编码
     */
    private Integer code;
    /**
     * 描述
     */
    private String desc;

    EventStatusEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public Integer getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
