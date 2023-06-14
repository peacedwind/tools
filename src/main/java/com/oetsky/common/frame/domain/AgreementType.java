package com.oetsky.common.frame.domain;

import com.oetsky.common.utils.StringUtils;

/**
 * 协议类型
 */
public enum AgreementType {
    /**
     * 读取电压/电流采集板校正系数
     */
    READ_GATHER_RECTIFY_NUM("CA", "NETPORT", "读取电压/电流采集板校正系数"),
    SET_GATHER_RECTIFY_NUM("CB", "LIGHTPORT", "设置电压/电流采集板校正系数"),
    READ_GATHER_CONFIG("CC", "SERIAL", "读取终端板卡参数"),
    READ_STATUS("CD", "SERIAL", "查询终端运行状态"),
    GATHER_UP_RESULT("0D", "SERIAL", "采集板模拟量测量值计算结果上报"),
    SELECT_TERMINAL_MERGING_STATUS("CE", "SERIAL", "数字板卡光模块运行状态"),
    // SET_ELECTRICITY_TYPE("CE", "SERIAL", "设置电能表抄表数据项"),
    // GET_ELECTRICITY_TYPE("CF", "SERIAL", "查询电能表抄表数据项"),
    ELECTRICITY_UP_RESULT("D0", "SERIAL", "电能表抄表数据上报"),
    TERMINAL_UP_RESULT("2A", "SERIAL", "终端事件信息上报"),
    READ_GATHER_DOT_CONFIG("D1", "SERIAL", "读取电压/电流采集板模拟量点表配置"),
    SET_GATHER_DOT_CONFIG("D2", "SERIAL", "设置电压/电流采集板模拟量点表配置"),
    ONLINE_UPDATE("FF", "SERIAL", "软件程序在线升级"),
    SET_MERGING_SV_CONFIG("D4", "SERIAL", "设置数字化变电站报文配置参数"),
    GET_MERGING_SV_CONFIG("D5", "SERIAL", "读取数字化变电站报文配置参数"),
    GATHER_SELF_CALIBRATION("D6", "SERIAL", "采集板模拟量通道自校准"),
    HEART_BEAT("D7", "SERIAL", "连接心跳"),
    SET_UNIT_STATUS_CONFIG("D9", "SERIAL", "设置采集单元运行参数"),
    READ_UNIT_STATUS_CONFIG("DA", "SERIAL", "读取采集单元运行参数"),
    SET_GOOSE_UNIT_CONFIG("DB", "SERIAL", "设置合并单元SV/GOOSE报文配置参数"),
    READ_GOOSE_UNIT_CONFIG("DC", "SERIAL", "读取合并单元SV/GOOSE报文配置参数"),
    SWITCH_UP_RESULT("DD", "SERIAL", "开入量上报"),
    SET_UP_SWITCH("DE", "SERIAL", "设置开出量"),
    SERIAL_SETTING_DLT698("SS", "SERIAL", "DL/T 698 能源控制器"),
    TEST_NETTY("test", "SERIAL", "测试通讯连接");

    private String code;

    private String name;

    private String desc;

    AgreementType(String code, String name, String desc) {
        this.code = code;
        this.name = name;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public static String getProtocolTypeByDesc(String code) {
        if (StringUtils.isNotEmpty(code)) {
            AgreementType[] values = AgreementType.values();
            for (AgreementType pt : values) {
                if (pt.code.equals(code)) {
                    return pt.desc;
                }
            }
        }
        return "";
    }

    public static AgreementType getProtocolTypeByData(String code) {
        if (StringUtils.isNotEmpty(code)) {
            AgreementType[] values = AgreementType.values();
            for (AgreementType pt : values) {
                if (pt.code.equals(code)) {
                    return pt;
                }
            }
        }
        return null;
    }
}