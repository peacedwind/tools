package com.oetsky.project.serialsetting.serial.domain;


public class ResuestErrorEventData {
    /**
     * 事件记录序号 2022 0200
     */
    public String eventId;
    /**
     * 事件发生时间 201e 0200
     */
    public String startTime;
    /**
     * 事件结束时间 2020 0200
     */
    public String endTime;

    /**
     * 事件源 2024 0200
     */
    public String eventSource;
    /**
     * 异常事件上报状态 3300 0200
     */
    public String eventReportType;
    /**
     * 事件接口类型 3300 0206
     */
    public String eventControlType;
    /**
     * 事件异常类型 3300 0207
     */
    public String eventErrorType;
    /**
     * 事件抽样异常数据 2068 0200
     */
    public String eventMonitoringData;
    /**
     * 事件误差异常数据 2069 0200
     */
    public String eventErrorData;


    /**
     * 获取 事件记录序号 2022 0200
     *
     * @return eventId 事件记录序号 2022 0200
     */
    public String getEventId() {
        return this.eventId;
    }

    /**
     * 设置 事件记录序号 2022 0200
     *
     * @param eventId 事件记录序号 2022 0200
     */
    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    /**
     * 获取 事件发生时间 201e 0200
     *
     * @return startTime 事件发生时间 201e 0200
     */
    public String getStartTime() {
        return this.startTime;
    }

    /**
     * 设置 事件发生时间 201e 0200
     *
     * @param startTime 事件发生时间 201e 0200
     */
    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    /**
     * 获取 事件结束时间 2020 0200
     *
     * @return endTime 事件结束时间 2020 0200
     */
    public String getEndTime() {
        return this.endTime;
    }

    /**
     * 设置 事件结束时间 2020 0200
     *
     * @param endTime 事件结束时间 2020 0200
     */
    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    /**
     * 获取 事件源 2024 0200
     *
     * @return eventSource 事件源 2024 0200
     */
    public String getEventSource() {
        return this.eventSource;
    }

    /**
     * 设置 事件源 2024 0200
     *
     * @param eventSource 事件源 2024 0200
     */
    public void setEventSource(String eventSource) {
        this.eventSource = eventSource;
    }

    /**
     * 获取 异常事件上报状态 3300 0200
     *
     * @return eventReportType 异常事件上报状态 3300 0200
     */
    public String getEventReportType() {
        return this.eventReportType;
    }

    /**
     * 设置 异常事件上报状态 3300 0200
     *
     * @param eventReportType 异常事件上报状态 3300 0200
     */
    public void setEventReportType(String eventReportType) {
        this.eventReportType = eventReportType;
    }

    /**
     * 获取 事件接口类型 3300 0206
     *
     * @return eventControlType 事件接口类型 3300 0206
     */
    public String getEventControlType() {
        return this.eventControlType;
    }

    /**
     * 设置 事件接口类型 3300 0206
     *
     * @param eventControlType 事件接口类型 3300 0206
     */
    public void setEventControlType(String eventControlType) {
        this.eventControlType = eventControlType;
    }

    /**
     * 获取 事件异常类型 3300 0207
     *
     * @return eventErrorType 事件异常类型 3300 0207
     */
    public String getEventErrorType() {
        return this.eventErrorType;
    }

    /**
     * 设置 事件异常类型 3300 0207
     *
     * @param eventErrorType 事件异常类型 3300 0207
     */
    public void setEventErrorType(String eventErrorType) {
        this.eventErrorType = eventErrorType;
    }

    /**
     * 获取 事件抽样异常数据 2068 0200
     *
     * @return eventMonitoringData 事件抽样异常数据 2068 0200
     */
    public String getEventMonitoringData() {
        return this.eventMonitoringData;
    }

    /**
     * 设置 事件抽样异常数据 2068 0200
     *
     * @param eventMonitoringData 事件抽样异常数据 2068 0200
     */
    public void setEventMonitoringData(String eventMonitoringData) {
        this.eventMonitoringData = eventMonitoringData;
    }

    /**
     * 获取 事件误差异常数据 2069 0200
     *
     * @return eventErrorData 事件误差异常数据 2069 0200
     */
    public String getEventErrorData() {
        return this.eventErrorData;
    }

    /**
     * 设置 事件误差异常数据 2069 0200
     *
     * @param eventErrorData 事件误差异常数据 2069 0200
     */
    public void setEventErrorData(String eventErrorData) {
        this.eventErrorData = eventErrorData;
    }
}
