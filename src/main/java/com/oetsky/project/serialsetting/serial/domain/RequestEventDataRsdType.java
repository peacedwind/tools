package com.oetsky.project.serialsetting.serial.domain;


/**
 * RSD_type
 * 事件数据帧 RSD_type数据
 * EventData
 * @author zhangw
 */
public class RequestEventDataRsdType {
    /**
     * RSD TYPE  数据位    2位 *2
     */
    public String rsdTypeStr;

    /**
     * RSD TYPE  位数    1位 *2
     */
    public String type;

    /**
     * RSD value  位数    1位 *2
     */
    public String value;


    /**
     * 获取 RSD TYPE  数据位    2位 2
     *
     * @return rsdTypeStr RSD TYPE  数据位    2位 2
     */
    public String getRsdTypeStr() {
        return this.rsdTypeStr;
    }

    /**
     * 设置 RSD TYPE  数据位    2位 2
     *
     * @param rsdTypeStr RSD TYPE  数据位    2位 2
     */
    public void setRsdTypeStr(String rsdTypeStr) {
        this.rsdTypeStr = rsdTypeStr;
    }

    /**
     * 获取 RSD TYPE  位数    1位 2
     *
     * @return type RSD TYPE  位数    1位 2
     */
    public String getType() {
        return this.type;
    }

    /**
     * 设置 RSD TYPE  位数    1位 2
     *
     * @param type RSD TYPE  位数    1位 2
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * 获取 RSD value  位数    1位 2
     *
     * @return value RSD value  位数    1位 2
     */
    public String getValue() {
        return this.value;
    }

    /**
     * 设置 RSD value  位数    1位 2
     *
     * @param value RSD value  位数    1位 2
     */
    public void setValue(String value) {
        this.value = value;
    }
}
