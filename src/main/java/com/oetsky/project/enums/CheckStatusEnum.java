package com.oetsky.project.enums;

/**
 * @description: 校验状态枚举
 * @author: cyx
 * @date: 2023-06-16
 **/
public enum CheckStatusEnum {
    OVER(0,"已完成"),
    ING(1,"进行中");

    private Integer code;

    private String desc;

    CheckStatusEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
