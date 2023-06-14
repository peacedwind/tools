package com.oetsky.project.enums;


/**
 * @author: huwm
 * @date: 2019/5/15
 */
public enum CheckEnum {

    SUCCESS("111111", "校验成功"),

    EMPTY("000001", "数据为空"),
    DATA_LENGTH_ERROR("000002", "数据长度错误"),
    FRAME_HEADER_ERROR("000003", "帧头错误"),
    FRAME_FOOTER_ERROR("000004", "帧尾错误"),
    DATA_NAN("000005", "采集数据NAN"),

    // 串口
    SERIAL_SETTING_FAIL("100001", "设置串口参数失败"),
    SERIAL_NOT_SERIAL("100002", "端口指向设备不是串口类型"),
    SERIAL_NO_PORT("100003", "没有该端口对应的串口设备"),
    SERIAL_PORT_OCCUPIED("100004", "端口已被占用");

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

    private String code;

    private String desc;

    CheckEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
