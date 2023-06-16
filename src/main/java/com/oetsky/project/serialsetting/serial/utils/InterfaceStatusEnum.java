package com.oetsky.project.serialsetting.serial.utils;

/**
 * 设备状态
 * @author zhangw
 */
public enum InterfaceStatusEnum {

    CLOSE(0, "关闭"),
    OPEN(1, "打开");

    InterfaceStatusEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public Integer code;
    public String desc;

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
