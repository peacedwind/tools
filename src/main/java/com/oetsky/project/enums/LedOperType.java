package com.oetsky.project.enums;

import com.oetsky.common.utils.StringUtils;

public enum LedOperType {
    OPEN(1, "开启"),
    CLOSE(0, "关闭");

    LedOperType(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }


    public static String getLedOperDesc(int code){
        if (StringUtils.isNotNull(code)) {
            LedOperType[] values = LedOperType.values();
            for (LedOperType lot : values) {
                if (lot.getCode().equals(code)) {
                    return lot.getDesc();
                }
            }
        }
        return "";
    }

    private Integer code;
    private String desc;

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
