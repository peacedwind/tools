package com.oetsky.common.frame.domain;

import com.oetsky.common.utils.StringUtils;

/**
 * 控制域 类型
 */
public enum ProtocolControlRegionType {
    AFFIRM_REQ("00", "确认收到"),
    NOT_AFFIRM_REQ("01", "否认收到"),
    FRAME_DOWN_SET("03", "设置类下行报文控制域"),
    FRAME_DOWN_GET("0A", "读取类下行报文控制域"),
    FRAME_UPPER_COMPUTER_UP("00", "终端事件信息上报 上位机 确认"),
    FRAME_UPPER_COMPUTER_UP_NOT("01", "终端事件信息上报 上位机 否认"),
    FRAME_SET_UP("80", "上行报文控制域 设置类 确认"),
    FRAME_SET_UP_NOT("81", "上行报文控制域 设置类 否认"),
    FRAME_UP_ELECTRICITY_NOTIN("83", "上行报文控制域 无需回复类"),
    FRAME_UP_GATHER_NOTIN("84", "上行报文控制域 无需回复类"),
    FRAME_GET_UP("88", "上行报文控制域 读取类有数据"),
    FRAME_GET_UP_NOT("89", "上行报文控制域 读取类无数据");
    public String code;
    public String desc;

    ProtocolControlRegionType(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

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

    public static ProtocolControlRegionType getProtocolControlRegionData(String code) {
        if (StringUtils.isNotEmpty(code)) {
            ProtocolControlRegionType[] values = ProtocolControlRegionType.values();
            for (ProtocolControlRegionType pt : values) {
                if (pt.code.equals(code)) {
                    return pt;
                }
            }
        }
        return null;
    }
}
