package com.oetsky.project.dataselect.domain;

import cn.hutool.core.date.DateUtil;
import com.oetsky.framework.aspectj.lang.annotation.Excel;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;

/**
 * 装置自诊断事件表(InspectionEventInfo)实体类
 *
 * @author makejava
 * @since 2022-10-17 15:01:29
 */
public class InspectionEventInfo implements Serializable {

    private static final long serialVersionUID = -87530599169818495L;
    /**
     * 主键id
     */
    @Excel(name = "序号", sort = 0)
    private Integer id;
    /**
     * 事件序号
     */
    private Integer num;
    /**
     * 类型(异常-2, 告警-1, 提示-0), 字典
     */
    @Excel(name = "类型", sort = 1, dictType = "inspect_event_type")
    private Integer eventType;
    /**
     * 事件编码(1-255)
     */
    @Excel(name = "事件编号", sort = 2)
    private Integer eventCode;
    /**
     * 事件名称
     */
    @Excel(name = "事件名", sort = 3)
    private String eventName;
    /**
     * 事件来源
     */
    @Excel(name = "事件来源", sort = 4)
    private String eventSource;

    /**
     * 保留字段2
     */
    @Excel(name = "备注",sort = 5)
    private String reserved2;
    /**
     * 开始时间
     */
    @Excel(name = "开始时间", dateFormat = "yyyy-MM-dd HH:mm:ss", sort = 6)
    private Date startTime;
    /**
     * 恢复时间
     */
    @Excel(name = "恢复时间", dateFormat = "yyyy-MM-dd HH:mm:ss", sort = 7)
    private Date recoveryTime;
    /**
     * 恢复原因
     */
    @Excel(name = "恢复原因", sort = 8)
    private String recoveryReason;
    /**
     * 事件来源采集单元号
     */
    private Long sourceUnitId;
    /**
     * 事件来源采集单元类型
     */
    private String sourceUnitType;
    /**
     * 事件来源板卡号
     */
    private Integer sourceBoardNum;
    /**
     * 事件来源通道号
     */
    private Integer sourceChannelNum;
    /**
     * 事件来源通道名称
     */
    private String sourceChannelName;
    /**
     * 事件来源模块编码
     */
    private String sourceCode;
    /**
     * 事件状态(未恢复-0, 已恢复-1)
     */
    private Integer eventStatus;
    /**
     * 记录创建时间
     */
    private Date createTime;

    /**
     * 异常事件id 当且仅当该事件是恢复的时候才会有值
     */
    private Integer exceptionId;
    /**
     * 该事件所在的任务编号
     */
    private String reserved1;

    /**
     * 查询的开始时间
     */
    private Date searchStartTime;

    /**
     * 查询的结束时间
     */
    private Date searchEndTime;

    /**
     * 查询事件类型集合
     */
    private Integer[] searchEventTypes;

    /**
     * 开始时间截至
     */
    private Date startTimeEnd;

    /**
     * 恢复时间截至
     */
    private Date recoveryTimeEnd;

    public Date getStartTimeEnd() {
        return startTimeEnd;
    }

    public void setStartTimeEnd(Date startTimeEnd) {
        this.startTimeEnd = startTimeEnd;
    }

    public Date getRecoveryTimeEnd() {
        return recoveryTimeEnd;
    }

    public void setRecoveryTimeEnd(Date recoveryTimeEnd) {
        this.recoveryTimeEnd = recoveryTimeEnd;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Integer getEventType() {
        return eventType;
    }

    public void setEventType(Integer eventType) {
        this.eventType = eventType;
    }

    public Integer getEventCode() {
        return eventCode;
    }

    public void setEventCode(Integer eventCode) {
        this.eventCode = eventCode;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getEventSource() {
        return eventSource;
    }

    public void setEventSource(String eventSource) {
        this.eventSource = eventSource;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getRecoveryTime() {
        return recoveryTime;
    }

    public void setRecoveryTime(Date recoveryTime) {
        this.recoveryTime = recoveryTime;
    }

    public String getRecoveryReason() {
        return recoveryReason;
    }

    public void setRecoveryReason(String recoveryReason) {
        this.recoveryReason = recoveryReason;
    }

    public String getSourceCode() {
        return sourceCode;
    }

    public void setSourceCode(String sourceCode) {
        this.sourceCode = sourceCode;
    }

    public Integer getEventStatus() {
        return eventStatus;
    }

    public void setEventStatus(Integer eventStatus) {
        this.eventStatus = eventStatus;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getReserved1() {
        return reserved1;
    }

    public void setReserved1(String reserved1) {
        this.reserved1 = reserved1;
    }

    public String getReserved2() {
        return reserved2;
    }

    public void setReserved2(String reserved2) {
        this.reserved2 = reserved2;
    }

    public Date getSearchStartTime() {
        return searchStartTime;
    }

    public void setSearchStartTime(Date searchStartTime) {
        this.searchStartTime = searchStartTime;
    }

    public Date getSearchEndTime() {
        return searchEndTime;
    }

    public void setSearchEndTime(Date searchEndTime) {
        this.searchEndTime = searchEndTime;
    }

    public Integer[] getSearchEventTypes() {
        return searchEventTypes;
    }

    public void setSearchEventTypes(Integer[] searchEventTypes) {
        this.searchEventTypes = searchEventTypes;
    }

    /**
     * 获取 事件来源通道号
     *
     * @return sourceChannelNum 事件来源通道号
     */
    public Integer getSourceChannelNum() {
        return this.sourceChannelNum;
    }

    /**
     * 设置 事件来源通道号
     *
     * @param sourceChannelNum 事件来源通道号
     */
    public void setSourceChannelNum(Integer sourceChannelNum) {
        this.sourceChannelNum = sourceChannelNum;
    }

    /**
     * 获取 事件来源通道名称
     *
     * @return sourceChannelName 事件来源通道名称
     */
    public String getSourceChannelName() {
        return this.sourceChannelName;
    }

    /**
     * 设置 事件来源通道名称
     *
     * @param sourceChannelName 事件来源通道名称
     */
    public void setSourceChannelName(String sourceChannelName) {
        this.sourceChannelName = sourceChannelName;
    }

    /**
     * 获取 事件来源板卡号
     *
     * @return sourceUnitId 事件来源板卡号
     */
    public Long getSourceUnitId() {
        return this.sourceUnitId;
    }

    /**
     * 设置 事件来源板卡号
     *
     * @param sourceUnitId 事件来源板卡号
     */
    public void setSourceUnitId(Long sourceUnitId) {
        this.sourceUnitId = sourceUnitId;
    }

    /**
     * 获取 事件来源板卡号
     *
     * @return sourceBoardNum 事件来源板卡号
     */
    public Integer getSourceBoardNum() {
        return this.sourceBoardNum;
    }

    /**
     * 设置 事件来源板卡号
     *
     * @param sourceBoardNum 事件来源板卡号
     */
    public void setSourceBoardNum(Integer sourceBoardNum) {
        this.sourceBoardNum = sourceBoardNum;
    }

    /**
     * 获取 事件来源采集单元类型
     *
     * @return sourceUnitType 事件来源采集单元类型
     */
    public String getSourceUnitType() {
        return this.sourceUnitType;
    }

    /**
     * 设置 事件来源采集单元类型
     *
     * @param sourceUnitType 事件来源采集单元类型
     */
    public void setSourceUnitType(String sourceUnitType) {
        this.sourceUnitType = sourceUnitType;
    }

    public Integer getExceptionId() {
        return exceptionId;
    }

    public void setExceptionId(Integer exceptionId) {
        this.exceptionId = exceptionId;
    }

    @Override
    public String toString() {
        return "InspectionEventInfo{" +
            "id=" + id +
            ", num=" + num +
            ", eventType=" + eventType +
            ", eventCode=" + eventCode +
            ", eventName='" + eventName + '\'' +
            ", eventSource='" + eventSource + '\'' +
            ", startTime=" + startTime +
            ", recoveryTime=" + recoveryTime +
            ", recoveryReason='" + recoveryReason + '\'' +
            ", sourceUnitId=" + sourceUnitId +
            ", sourceUnitType='" + sourceUnitType + '\'' +
            ", sourceBoardNum=" + sourceBoardNum +
            ", sourceChannelNum=" + sourceChannelNum +
            ", sourceChannelName='" + sourceChannelName + '\'' +
            ", sourceCode='" + sourceCode + '\'' +
            ", eventStatus=" + eventStatus +
            ", createTime=" + createTime +
            ", exceptionId=" + exceptionId +
            ", reserved1='" + reserved1 + '\'' +
            ", reserved2='" + reserved2 + '\'' +
            ", searchStartTime=" + searchStartTime +
            ", searchEndTime=" + searchEndTime +
            ", searchEventTypes=" + Arrays.toString(searchEventTypes) +
            '}';
    }

    /**
     * 是否是恢复记录
     *
     * @return true if this is a recoveryEvent
     */
    public boolean isRecoveryRecord() {
        return this.recoveryTime != null;
    }


    public void defaultSecondStartTime() {
        if (this.startTime != null) {
            this.startTime = DateUtil.beginOfSecond(this.startTime);
        }
    }

    public void defaultRecoveryTime() {
        if (this.recoveryTime != null) {
            this.recoveryTime = DateUtil.beginOfSecond(this.recoveryTime);
        }
    }


    /**
     * 校验查询时间
     */
    public void validSearchTime() {
        if (this.searchStartTime == null && this.searchEndTime != null) {
            throw new RuntimeException("时间窗口的开始时间和结束时间必须同时有值或同时为空");
        }
        if (this.searchEndTime == null && this.searchStartTime != null) {
            throw new RuntimeException("时间窗口的开始时间和结束时间必须同时有值或同时为空");
        }
    }

    /**
     * 获取事件的产生时间
     *
     * @return 时间
     */
    public Date getEventDate() {
        if (isRecoveryRecord()) {
            return this.recoveryTime;
        } else {
            return this.startTime;
        }
    }


    /**
     * 设置查询是不查询出未来的数据
     */
    public void notFutureSearch(){
        Date now = new Date();
        this.setStartTimeEnd(now);
        this.setRecoveryTimeEnd(now);
    }

    /**
     * 备份数据时间
     */
    private Date backCreateTime;

    /**
     * 获取 备份数据时间
     *
     * @return backCreateTime 备份数据时间
     */
    public Date getBackCreateTime() {
        return this.backCreateTime;
    }

    /**
     * 设置 备份数据时间
     *
     * @param backCreateTime 备份数据时间
     */
    public void setBackCreateTime(Date backCreateTime) {
        this.backCreateTime = backCreateTime;
    }

    /**
     * channelNum is null
     * @return
     */
    public boolean isChannelNumIsNull(){
        return sourceChannelNum == null;
    }
}

