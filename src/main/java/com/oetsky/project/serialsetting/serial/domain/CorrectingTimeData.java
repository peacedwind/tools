package com.oetsky.project.serialsetting.serial.domain;

import java.util.Date;

/**
 * @author zhangw
 */
public class CorrectingTimeData {
    /**
     * 广播校时时间
     */
    public String dataStr;
    /**
     * 广播校时日期字符串值
     */
    public String timeDateStr;
    /**
     * 广播校时日期格式
     */
    public Date timeDateTime;
    /**
     * 时间值类型及值
     */
    public String dataBoolStr;
    /**
     * 时间值
     */
    public String dataBoolValue;


    /**
     * 获取 广播校时时间
     *
     * @return dataStr 广播校时时间
     */
    public String getDataStr() {
        return this.dataStr;
    }

    /**
     * 设置 广播校时时间
     *
     * @param dataStr 广播校时时间
     */
    public void setDataStr(String dataStr) {
        this.dataStr = dataStr;
    }

    /**
     * 获取 广播校时日期字符串值
     *
     * @return timeDateStr 广播校时日期字符串值
     */
    public String getTimeDateStr() {
        return this.timeDateStr;
    }

    /**
     * 设置 广播校时日期字符串值
     *
     * @param timeDateStr 广播校时日期字符串值
     */
    public void setTimeDateStr(String timeDateStr) {
        this.timeDateStr = timeDateStr;
    }

    /**
     * 获取 广播校时日期格式
     *
     * @return timeDateTime 广播校时日期格式
     */
    public Date getTimeDateTime() {
        return this.timeDateTime;
    }

    /**
     * 设置 广播校时日期格式
     *
     * @param timeDateTime 广播校时日期格式
     */
    public void setTimeDateTime(Date timeDateTime) {
        this.timeDateTime = timeDateTime;
    }

    /**
     * 获取 时间值类型及值
     *
     * @return dataBoolStr 时间值类型及值
     */
    public String getDataBoolStr() {
        return this.dataBoolStr;
    }

    /**
     * 设置 时间值类型及值
     *
     * @param dataBoolStr 时间值类型及值
     */
    public void setDataBoolStr(String dataBoolStr) {
        this.dataBoolStr = dataBoolStr;
    }

    /**
     * 获取 时间值
     *
     * @return dataBoolValue 时间值
     */
    public String getDataBoolValue() {
        return this.dataBoolValue;
    }

    /**
     * 设置 时间值
     *
     * @param dataBoolValue 时间值
     */
    public void setDataBoolValue(String dataBoolValue) {
        this.dataBoolValue = dataBoolValue;
    }
}
