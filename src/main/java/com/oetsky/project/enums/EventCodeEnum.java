package com.oetsky.project.enums;

import com.oetsky.common.utils.StringUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description: 事件编码枚举
 * @author: cyx
 * @date: 2022-10-19
 **/
public enum EventCodeEnum {

    /**
     * 电压
     */
    VOLTAGE_DATA_LOST(51, "电压数据丢失", EventTypeEnum.EXCEPTION),
    VOLTAGE_DATA_REPEAT(52, "电压数据重复", EventTypeEnum.EXCEPTION),
    VOLTAGE_DATA_AMPLITUDE_EXCEPTION(53, "电压幅值异常", EventTypeEnum.EXCEPTION),
    VOLTAGE_DATA_FREQUENCY_EXCEPTION(6, "电压频率异常", EventTypeEnum.EXCEPTION),

    VOLTAGE_ROUTE_ERROR(54, "电压接线错误", EventTypeEnum.WARNING),

    VOLTAGE_ROUTE_POWER_OUT(8, "电压线路停电", EventTypeEnum.TIP),
    VOLTAGE_ROUTE_CATE_RUN(32, "电压线路分列运行", EventTypeEnum.TIP),
    VOLTAGE_ROUTE_HOMOLOGOUS(33, "电压线路同源", EventTypeEnum.TIP),

    /**
     * 边缘计算单元
     */
    EDGE_CALCULATION_UNIT_STOP(10, "站端软件停止运行", EventTypeEnum.EXCEPTION),
    EDGE_CALCULATION_UNIT_POWER_DOWN(9, "装置掉电", EventTypeEnum.EXCEPTION),
    EDGE_CALCULATION_UNIT_SHIYA(7, "边缘计算单元失压", EventTypeEnum.WARNING),
    EDGE_CALCULATION_UNIT_CPU_OVERLOAD(11, "CPU使用率超限", EventTypeEnum.EXCEPTION),
    VOLTAGE_CHANNEL_CONFIG_ERROR(12, "电压线路配置错误", EventTypeEnum.WARNING),
    VOLTAGE_TRANSFORMER_LOST(13, "电压互感器档案缺失", EventTypeEnum.WARNING),

    /**
     * 电流
     */
    CURRENT_DATA_LOST(61, "电流数据丢失", EventTypeEnum.WARNING),
    CURRENT_DATA_REPEAT(62, "电流数据重复", EventTypeEnum.WARNING),
    CURRENT_DATA_AMPLITUDE_EXCEPTION(63, "电流数据幅值异常", EventTypeEnum.WARNING),
    CURRENT_DATA_FREQUENCY_EXCEPTION(64, "电流频率异常", EventTypeEnum.WARNING),

    /**
     * 电能表
     */
    ELECTRIX_DATA_LOST(71, "电能表数据丢失", EventTypeEnum.WARNING),

    /**
     * 母联开关
     */
    BUSCOUPLE_SWITCH_DATA_LOST(81, "母联开关数据丢失", EventTypeEnum.WARNING),

    /**
     * 用采通讯
     */
    NORTH_COMMUNICATION_NOT_MESSAGE(91, "未收到用采终端消息", EventTypeEnum.WARNING),

    /**
     * U 盘
     */
    U_DISK_NOT_FOUND(14, "未检测到可用U盘", EventTypeEnum.WARNING),
    U_DISK_INSUFFICIENT_CAPACITY(15, "U盘容量不足", EventTypeEnum.WARNING),

    /**
     * 采集单元
     */
    UNIT_CLOCKS_NOT_SYNC(201, "装置时钟不同步", EventTypeEnum.EXCEPTION),
    UNIT_RESTART_RECORD(21, "终端重启记录", EventTypeEnum.EXCEPTION),
    UNIT_CONNECT_COLSE(22, "装置通讯中断", EventTypeEnum.EXCEPTION),

    UNIT_COMMUNICATION_EXCEPTION_RECORD(202, "通讯过程异常记录", EventTypeEnum.WARNING),
    UNIT_PART_RECORD(203, "终端内部各类插件、元件异常自检记录", EventTypeEnum.WARNING),
    UNIT_SOFTWARE_EXCEPTION(204, "装置内部软件进程异常记录", EventTypeEnum.WARNING),
    UNIT_SHIYA(20, "终端失压告警记录", EventTypeEnum.WARNING),
    UNIT_DISCONNECTION(205, "控制回路断线异常记录", EventTypeEnum.WARNING),
    UNIT_ANALOG_EXCEPTION(206, "模拟量采集回路异常记录", EventTypeEnum.WARNING),


    UNIT_PARAM_UPDATE(207, "终端参数修改记录", EventTypeEnum.TIP),
    UNIT_SOFTWARE_VERSION_UPDATE(208, "软件版本升级记录", EventTypeEnum.TIP),
    UNIT_OPERATE_RECORD(209, "装置操作记录", EventTypeEnum.TIP),

    UNIT_SOC_OPTICAL_MODULE_NOT_REIGN(210, "Soc板卡光模块不在位", EventTypeEnum.WARNING),
    UNIT_SOC_OPTICAL_MODULE_NOT_LIGHT(211, "Soc板卡光模块无光", EventTypeEnum.WARNING),
    UNIT_SOC_LOST_SIGNAL(212, "Soc板卡失去对时信号", EventTypeEnum.WARNING),
    UNIT_SV_MESSAGE_EXCEPTION(213, "SV报文异常", EventTypeEnum.WARNING),
    UNIT_GOOSE_MESSAGE_EXCEPTION(214, "GOOSE报文异常", EventTypeEnum.WARNING);


    /**
     * 事件编码
     */
    private Integer code;

    /**
     * 事件描述
     */
    private String desc;

    /**
     * 事件类型
     */
    private EventTypeEnum eventTypeEnum;

    EventCodeEnum(Integer code, String desc, EventTypeEnum eventTypeEnum) {
        this.code = code;
        this.desc = desc;
        this.eventTypeEnum = eventTypeEnum;
    }

    private static final Map<Integer,EventCodeEnum> code2EventMap = new HashMap<>();

    static {
        for (EventCodeEnum value : EventCodeEnum.values()) {
            code2EventMap.put(value.code,value);
        }
    }

    public static EventCodeEnum getEventEnumByCode(Integer code){
        return code2EventMap.get(code);
    }

    public Integer getCode() {
        return code;
    }


    public String getDesc() {
        return desc;
    }

    public EventTypeEnum getEventTypeEnum() {
        return eventTypeEnum;
    }

}
