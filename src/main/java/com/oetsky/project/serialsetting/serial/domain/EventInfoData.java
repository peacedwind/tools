package com.oetsky.project.serialsetting.serial.domain;

import java.util.Date;

public class EventInfoData {
    /**
     * 主键
     */
    private Long id;

    /**
     * 事件序号
     */
    private Long num;
    /**
     * 通道
     */
    private Integer channelNum;
    /**
     * 相序
     */
    private String phaseSeq;
    /**
     * 表计地址
     */
    private String meterAddr;
    /**
     * 发生时间
     */
    private Date startTime;
    /**
     * 结束时间
     */
    private Date endTime;
    /**
     * 事件发生源
     */
    private String sourceContent;
    /**
     * 事件上报状态
     */
    private String reportStatusOad;
    /**
     * 事件上报状态
     */
    private String reportStatusValue;
    /**
     * 接口类型 暂时无用字段（01-光口，02-网口，03-串口）
     */
    private String interfaceType;
    /**
     * 异常类型（01-采集设备无数据，02-采集设备数据异常，03-误差警告，04-误差异常，05-频率超限.......）
     */
    private String errorType;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 错误数据时间
     */
    private Date errorDateTime;

    /**
     * 获取 主键
     *
     * @return id 主键
     */
    public Long getId() {
        return this.id;
    }

    /**
     * 设置 主键
     *
     * @param id 主键
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取 事件序号
     *
     * @return num 事件序号
     */
    public Long getNum() {
        return this.num;
    }

    /**
     * 设置 事件序号
     *
     * @param num 事件序号
     */
    public void setNum(Long num) {
        this.num = num;
    }

    /**
     * 获取 通道
     *
     * @return channelNum 通道
     */
    public Integer getChannelNum() {
        return this.channelNum;
    }

    /**
     * 设置 通道
     *
     * @param channelNum 通道
     */
    public void setChannelNum(Integer channelNum) {
        this.channelNum = channelNum;
    }

    /**
     * 获取 相序
     *
     * @return phaseSeq 相序
     */
    public String getPhaseSeq() {
        return this.phaseSeq;
    }

    /**
     * 设置 相序
     *
     * @param phaseSeq 相序
     */
    public void setPhaseSeq(String phaseSeq) {
        this.phaseSeq = phaseSeq;
    }

    /**
     * 获取 表计地址
     *
     * @return meterAddr 表计地址
     */
    public String getMeterAddr() {
        return this.meterAddr;
    }

    /**
     * 设置 表计地址
     *
     * @param meterAddr 表计地址
     */
    public void setMeterAddr(String meterAddr) {
        this.meterAddr = meterAddr;
    }

    /**
     * 获取 发生时间
     *
     * @return startTime 发生时间
     */
    public Date getStartTime() {
        return this.startTime;
    }

    /**
     * 设置 发生时间
     *
     * @param startTime 发生时间
     */
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    /**
     * 获取 结束时间
     *
     * @return endTime 结束时间
     */
    public Date getEndTime() {
        return this.endTime;
    }

    /**
     * 设置 结束时间
     *
     * @param endTime 结束时间
     */
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    /**
     * 获取 事件发生源
     *
     * @return sourceContent 事件发生源
     */
    public String getSourceContent() {
        return this.sourceContent;
    }

    /**
     * 设置 事件发生源
     *
     * @param sourceContent 事件发生源
     */
    public void setSourceContent(String sourceContent) {
        this.sourceContent = sourceContent;
    }

    /**
     * 获取 事件上报状态
     *
     * @return reportStatusOad 事件上报状态
     */
    public String getReportStatusOad() {
        return this.reportStatusOad;
    }

    /**
     * 设置 事件上报状态
     *
     * @param reportStatusOad 事件上报状态
     */
    public void setReportStatusOad(String reportStatusOad) {
        this.reportStatusOad = reportStatusOad;
    }

    /**
     * 获取 事件上报状态
     *
     * @return reportStatusValue 事件上报状态
     */
    public String getReportStatusValue() {
        return this.reportStatusValue;
    }

    /**
     * 设置 事件上报状态
     *
     * @param reportStatusValue 事件上报状态
     */
    public void setReportStatusValue(String reportStatusValue) {
        this.reportStatusValue = reportStatusValue;
    }

    /**
     * 获取 接口类型 暂时无用字段（01-光口，02-网口，03-串口）
     *
     * @return interfaceType 接口类型 暂时无用字段（01-光口，02-网口，03-串口）
     */
    public String getInterfaceType() {
        return this.interfaceType;
    }

    /**
     * 设置 接口类型 暂时无用字段（01-光口，02-网口，03-串口）
     *
     * @param interfaceType 接口类型 暂时无用字段（01-光口，02-网口，03-串口）
     */
    public void setInterfaceType(String interfaceType) {
        this.interfaceType = interfaceType;
    }

    /**
     * 获取 异常类型（01-采集设备无数据，02-采集设备数据异常，03-误差警告，04-误差异常，05-频率超限.......）
     *
     * @return errorType 异常类型（01-采集设备无数据，02-采集设备数据异常，03-误差警告，04-误差异常，05-频率超限.......）
     */
    public String getErrorType() {
        return this.errorType;
    }

    /**
     * 设置 异常类型（01-采集设备无数据，02-采集设备数据异常，03-误差警告，04-误差异常，05-频率超限.......）
     *
     * @param errorType 异常类型（01-采集设备无数据，02-采集设备数据异常，03-误差警告，04-误差异常，05-频率超限.......）
     */
    public void setErrorType(String errorType) {
        this.errorType = errorType;
    }

    /**
     * 获取 创建时间
     *
     * @return createTime 创建时间
     */
    public Date getCreateTime() {
        return this.createTime;
    }

    /**
     * 设置 创建时间
     *
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取 错误数据时间
     *
     * @return errorDataTime 错误数据时间
     */
    public Date getErrorDateTime() {
        return this.errorDateTime;
    }

    /**
     * 设置 错误数据时间
     *
     * @param errorDateTime 错误数据时间
     */
    public void setErrorDateTime(Date errorDateTime) {
        this.errorDateTime = errorDateTime;
    }

    public Integer limitSize;

    public Integer getLimitSize() {
        return limitSize;
    }

    public void setLimitSize(Integer limitSize) {
        this.limitSize = limitSize;
    }
}
