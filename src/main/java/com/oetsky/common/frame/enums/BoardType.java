package com.oetsky.common.frame.enums;

import com.oetsky.common.utils.StringUtils;

/**
 * 采集类型
 */
public enum BoardType {
    VOLTAGE("01", "电压采集板卡"),
    ELECTRICITY("02", "电流采集板卡"),
    ELECTRICITY_1A("06", "电流采集板卡"),
    SFP_DIGITIZED("17", "SFP数字化采集板卡"),
    LIGHT_SERIAL_PORT("18", "光串口数字化采集板卡"),
    ELECTRICITY_BOARD("33", "电能表采集板卡"),
    SYNC_TIME("49", "时间同步采集板卡"),
    ON_SWITCH("65", "开入板"),
    OUT_SWITCH("81", "开出板");


    public String code;
    public String desc;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    BoardType(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static String getBoardTypeByCode(String code) {
        if (StringUtils.isNotEmpty(code)) {
            BoardType[] values = BoardType.values();
            for (BoardType pt : values) {
                if (pt.getCode().equals(code)) {
                    return pt.getDesc();
                }
            }
        }
        return "";
    }
}
