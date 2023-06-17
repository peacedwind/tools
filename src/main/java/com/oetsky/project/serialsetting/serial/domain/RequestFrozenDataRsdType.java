package com.oetsky.project.serialsetting.serial.domain;


import java.util.Date;

/**
 * RSD_type
 * 冻结数据帧 RSD_type数据
 * FROZEN_DATA
 */
public class RequestFrozenDataRsdType {
    /**
     * RSD TYPE  数据位    26位
     */
    public String rsdTypeStr;

    /**
     * RSD TYPE  位数    1位 *2
     */
    public String type;
    /**
     * RSD TYPE  OAD    4位 * 2
     */
    public String rsdOad;
    /**
     * 开始时间  位数    8位（类型1+数据7）* 2
     */
    public String startTimeStr;
    public Date startTime;
    /**
     * 结束时间  位数    8位（类型1+数据7）* 2
     */
    public String endTimeStr;
    public Date endTime;
    /**
     * 间隔  位数    4位（类型1+位数1+数据2位）* 2
     */
    public String unitTimeStr;
    public Integer unitTime;


    /**
     * 获取 RSD TYPE  数据位    26位
     *
     * @return rsdTypeStr RSD TYPE  数据位    26位
     */
    public String getRsdTypeStr() {
        return this.rsdTypeStr;
    }

    /**
     * 设置 RSD TYPE  数据位    26位
     *
     * @param rsdTypeStr RSD TYPE  数据位    26位
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
     * 获取 RSD TYPE  OAD    4位  2
     *
     * @return rsdOad RSD TYPE  OAD    4位  2
     */
    public String getRsdOad() {
        return this.rsdOad;
    }

    /**
     * 设置 RSD TYPE  OAD    4位  2
     *
     * @param rsdOad RSD TYPE  OAD    4位  2
     */
    public void setRsdOad(String rsdOad) {
        this.rsdOad = rsdOad;
    }

    /**
     * 获取 开始时间  位数    8位（类型1+数据7） 2
     *
     * @return startTimeStr 开始时间  位数    8位（类型1+数据7） 2
     */
    public String getStartTimeStr() {
        return this.startTimeStr;
    }

    /**
     * 设置 开始时间  位数    8位（类型1+数据7） 2
     *
     * @param startTimeStr 开始时间  位数    8位（类型1+数据7） 2
     */
    public void setStartTimeStr(String startTimeStr) {
        this.startTimeStr = startTimeStr;
    }

    /**
     * 获取
     *
     * @return startTime
     */
    public Date getStartTime() {
        return this.startTime;
    }

    /**
     * 设置
     *
     * @param startTime
     */
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    /**
     * 获取 结束时间  位数    8位（类型1+数据7） 2
     *
     * @return endTimeStr 结束时间  位数    8位（类型1+数据7） 2
     */
    public String getEndTimeStr() {
        return this.endTimeStr;
    }

    /**
     * 设置 结束时间  位数    8位（类型1+数据7） 2
     *
     * @param endTimeStr 结束时间  位数    8位（类型1+数据7） 2
     */
    public void setEndTimeStr(String endTimeStr) {
        this.endTimeStr = endTimeStr;
    }

    /**
     * 获取
     *
     * @return endTime
     */
    public Date getEndTime() {
        return this.endTime;
    }

    /**
     * 设置
     *
     * @param endTime
     */
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    /**
     * 获取 间隔  位数    4位（类型1+位数1+数据2位） 2
     *
     * @return unitTimeStr 间隔  位数    4位（类型1+位数1+数据2位） 2
     */
    public String getUnitTimeStr() {
        return this.unitTimeStr;
    }

    /**
     * 设置 间隔  位数    4位（类型1+位数1+数据2位） 2
     *
     * @param unitTimeStr 间隔  位数    4位（类型1+位数1+数据2位） 2
     */
    public void setUnitTimeStr(String unitTimeStr) {
        this.unitTimeStr = unitTimeStr;
    }

    /**
     * 获取
     *
     * @return unitTime
     */
    public Integer getUnitTime() {
        return this.unitTime;
    }

    /**
     * 设置
     *
     * @param unitTime
     */
    public void setUnitTime(Integer unitTime) {
        this.unitTime = unitTime;
    }
}
