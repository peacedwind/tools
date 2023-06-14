package com.oetsky.project.enums;

import com.oetsky.common.utils.StringUtils;

public enum LedControlType {
    HDD1_LED(0, "HDD1_LED"),
    HDD2_LED(1, "HDD2_LED"),
    ERR_LED(2, "ERR_LED"),
    SYNC_LED(3, "SYNC_LED"),
    SATA0_LED(4, "SATA0_LED"),
    SATA1_LED(5, "SATA1_LED"),
    RSV0_LED(6, "RSV0_LED"),
    RSV1_LED(7, "RSV1_LED"),
    RSV2_LED(8, "RSV2_LED"),
    RSV3_LED(9, "RSV3_LED"),
    MAX_LED(10, "MAX_LED"),
    VOLTAGE_CLOSE_CHECK_LED(1101, "失压检测"),
    NETTY_CHECK_LED(1102, "通讯检测"),
    USB_CHECK_LED(1103, "U盘检测"),
    EQUIPMENT_DIAGNOSE_LED(1104, "装置自诊断");

    LedControlType(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static String getLedControlDesc(int code){
        if (StringUtils.isNotNull(code)) {
            LedControlType[] values = LedControlType.values();
            for (LedControlType lc : values) {
                if (lc.getCode().equals(code)) {
                    return lc.getDesc();
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
