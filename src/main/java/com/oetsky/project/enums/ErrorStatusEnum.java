package com.oetsky.project.enums;

import cn.hutool.core.util.RandomUtil;

public enum ErrorStatusEnum {
    SUCCESS("0", "正常评估"), // 正常评估
    POWER_CUT("1", "线路停电"),// 线路停电
    SAME("2", "信号同源且同电压等级下无并列运行线路"),// 线路同源
    SPLIT("3", "线路分列运行"), // 线路分列
    UNSTABLE_MODE("4", "线路运行模式不稳定"),// 当天累计相同的运行状态不足720个点
    PARALLEL_FEW("5", "同电压等级下并列运行线路过少"),
    NO_COLLECT_DATA("6", "装置停电或初装"),// 当天无采集数据
    DATA_NOT_780("7", "装置停电或初装"),// 当天采集数据不足780
    NEED_OPERATION("8", "需人工运维"),// 比差算法输出结果为0
    MODE_CONFIG_ERROR("9", "线路运行模式配置错误"),// 不支持线路初始配置运行模式
    QUALITATIVE_EVAL("14", "定性误差评估"),// 江苏 basicphasing算法
    CALCULATE_TASK_NO_RUN("15", "装置停电，误差评估未计算"), // 不需要我做，老向上传的时候直接使用此code
    ;

    /**
     * 计算状态码
     */
    private String code;

    /**
     * 界面显示，对外解释
     */
    private String desc;

    ErrorStatusEnum(String code, String desc) {
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

    public static int randomStatus(){
        return RandomUtil.randomInt(16);
    }
}