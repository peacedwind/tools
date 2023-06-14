package com.oetsky.common.frame.enums;

public enum StationType {
    DIGITIZATION(1, "数字化站点"),
    TRADITION(2,"传统站点");

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

    StationType(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
