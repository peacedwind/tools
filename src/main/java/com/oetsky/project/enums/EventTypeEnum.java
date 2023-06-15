package com.oetsky.project.enums;

/**
 * @description: 事件类型枚举
 * @author: cyx
 * @date: 2022-10-18
 **/
public enum EventTypeEnum {
    EXCEPTION(2, "异常",10),
    WARNING(1, "告警",5),
    TIP(0, "提示",0);

    /**
     * 编码
     */
    private Integer code;
    /**
     * 描述
     */
    private String desc;

    /**
     * 优先级 值越大越严重
     */
    private Integer priority;

    EventTypeEnum(Integer code, String desc,Integer priority) {
        this.code = code;
        this.desc = desc;
        this.priority = priority;
    }

    public Integer getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    /**
     * 依据code获取
     * @param code
     * @return
     */
    public static EventTypeEnum getByCode(Integer code){
        if (code != null){
            for (EventTypeEnum value : values()) {
                if (value.getCode().equals(code)){
                    return value;
                }
            }
        }
        return null;
    }

    public Integer getPriority() {
        return priority;
    }


}
