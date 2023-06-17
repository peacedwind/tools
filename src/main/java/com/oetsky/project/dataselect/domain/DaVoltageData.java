package com.oetsky.project.dataselect.domain;

import cn.hutool.core.date.DateUtil;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.oetsky.framework.aspectj.lang.annotation.Excel;
import com.oetsky.framework.web.domain.BaseEntity;
import com.oetsky.project.constants.DIffConstants;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 电压互感器采集数据对象 da_voltage_data
 *
 * @author hanxz
 * @date 2022-02-24
 */
public class DaVoltageData extends BaseEntity {

    private static final long serialVersionUID = 1L;
    /**
     * 接收数据ID
     */
    private Long id;

    /**
     * 板卡号
     */
    @Excel(name = "板卡号")
    private Integer boardNum;
    private Long unitId;

    /**
     * 板卡通道
     */
    @Excel(name = "板卡通道")
    private Integer boardChannel;

    /**
     * 线路通道
     */
    @Excel(name = "线路通道")
    private Integer channelNum;

    private String channelName;

    /**
     * 采集时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "采集时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date collectTime;
    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    @Override
    public Date getCreateTime() {
        return createTime;
    }

    @Override
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 时间类型（0终端本地时间，1站内B码，2PPS秒脉冲信号，3工控机本地时间）
     */
    @Excel(name = "时间类型", readConverterExp = "0=终端本地时间,1=站内B码,2=PPS秒脉冲信号,3=工控机本地时间,10=站内B码对时信号已同步,11=站内B码对时信号对时中,20=PPS秒脉冲信号已同步,21=PPS秒脉冲信号对时中")
    private Integer timeType;

    /**
     * a基波频率
     */
    @Excel(name = "a基波频率")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private BigDecimal aaFundamentalFrequency;

    /**
     * a基波幅值
     */
    @Excel(name = "a基波幅值")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private BigDecimal aaFundamentalAmplitude;

    /**
     * a3次谐波幅值
     */
    @Excel(name = "a3次谐波幅值")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private BigDecimal a3HarmonicAmplitude;

    /**
     * a5次谐波幅值
     */
    @Excel(name = "a5次谐波幅值")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private BigDecimal a5HarmonicAmplitude;

    /**
     * a基波相位
     */
    @Excel(name = "a基波相位")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private BigDecimal aaFundamentalPhase;

    /**
     * a3次谐波相位
     */
    @Excel(name = "a3次谐波相位")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private BigDecimal a3HarmonicPhase;

    /**
     * a5次谐波相位
     */
    @Excel(name = "a5次谐波相位")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private BigDecimal a5HarmonicPhase;

    /**
     * b基波频率
     */
    @Excel(name = "b基波频率")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private BigDecimal bbFundamentalFrequency;

    /**
     * b基波幅值
     */
    @Excel(name = "b基波幅值")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private BigDecimal bbFundamentalAmplitude;

    /**
     * b3次谐波幅值
     */
    @Excel(name = "b3次谐波幅值")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private BigDecimal b3HarmonicAmplitude;

    /**
     * b5次谐波幅值
     */
    @Excel(name = "b5次谐波幅值")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private BigDecimal b5HarmonicAmplitude;

    /**
     * b基波相位
     */
    @Excel(name = "b基波相位")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private BigDecimal bbFundamentalPhase;

    /**
     * b3次谐波相位
     */
    @Excel(name = "b3次谐波相位")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private BigDecimal b3HarmonicPhase;

    /**
     * b5次谐波相位
     */
    @Excel(name = "b5次谐波相位")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private BigDecimal b5HarmonicPhase;

    /**
     * c基波频率
     */
    @Excel(name = "c基波频率")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private BigDecimal ccFundamentalFrequency;

    /**
     * c基波幅值
     */
    @Excel(name = "c基波幅值")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private BigDecimal ccFundamentalAmplitude;

    /**
     * c3次谐波幅值
     */
    @Excel(name = "c3次谐波幅值")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private BigDecimal c3HarmonicAmplitude;

    /**
     * c5次谐波幅值
     */
    @Excel(name = "c5次谐波幅值")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private BigDecimal c5HarmonicAmplitude;

    /**
     * c基波相位
     */
    @Excel(name = "c基波相位")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private BigDecimal ccFundamentalPhase;

    /**
     * c3次谐波相位
     */
    @Excel(name = "c3次谐波相位")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private BigDecimal c3HarmonicPhase;

    /**
     * c5次谐波相位
     */
    @Excel(name = "c5次谐波相位")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private BigDecimal c5HarmonicPhase;

    /**
     * 零序电压不平衡度
     */
    @Excel(name = "零序电压不平衡度")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private BigDecimal zeroSequenceVoltageImbalance;

    /**
     * 负序电压不平衡度
     */
    @Excel(name = "负序电压不平衡度")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private BigDecimal negativeSequenceVoltageImbalance;

    /**
     * 正常数据1 ，异常数据0
     */
    @Excel(name = "数据状态", readConverterExp = "0=异常数据,1=正常数据")
    private Integer status;

    /**
     * 线路电压等级
     */
    @Excel(name = "线路电压等级", readConverterExp = "1=10kV,2=35kV,3=66kV,4=110kV,5=220kV,6=330kV,7=400kV,8=500kV")
    private Integer channelLevel;

    /** 板卡采样值A真实通道序号 */
    @Excel(name = "板卡采样值A真实通道序号")
    private Integer aaBoardSortNum;

    /** 板卡采样值B真实通道序号 */
    @Excel(name = "板卡采样值B真实通道序号")
    private Integer bbBoardSortNum;

    /**
     * 板卡采样值C真实通道序号
     */
    @Excel(name = "板卡采样值C真实通道序号")
    private Integer ccBoardSortNum;

    /**
     * 板卡采样值A通道时钟同步标志
     */
    @Excel(name = "板卡采样值A通道时钟同步标志")
    private Integer aaClockStatus;

    /**
     * 板卡采样值B通道时钟同步标志
     */
    @Excel(name = "板卡采样值B通道时钟同步标志")
    private Integer bbClockStatus;

    /**
     * 板卡采样值C通道时钟同步标志
     */
    @Excel(name = "板卡采样值C通道时钟同步标志")
    private Integer ccClockStatus;

    /**
     * 板卡采样值A通道品质位
     */
    @Excel(name = "板卡采样值A通道品质位")
    private String aaLogicDataQuality;

    /**
     * 板卡采样值B通道品质位
     */
    @Excel(name = "板卡采样值B通道品质位")
    private String bbLogicDataQuality;

    /**
     * 板卡采样值C通道品质位
     */
    @Excel(name = "板卡采样值C通道品质位")
    private String ccLogicDataQuality;

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    /**
     * 保留字段
     */
    private String flag;

    /**
     * 扩展字段1
     */
//    @Excel(name = "扩展字段1")
    private String column1;

    /**
     * 扩展字段2
     */
//    @Excel(name = "扩展字段2")
    private String column2;

    /**
     * 扩展字段3
     */
//    @Excel(name = "扩展字段3")
    private String column3;

    /**
     * 是否补全数据（正常数据1，补全数据0）
     */
    @Excel(name = "是否补全", readConverterExp = "0=补全数据,1=正常数据")
    private Integer isSupplement;

    /**
     * 测量时间 开始时间
     */
    private Date startTime;
    /**
     * 测量时间 结束时间
     */
    private Date endTime;

    /**
     * 温度
     */
    private BigDecimal temperature;
    /**
     * 湿度
     */
    private BigDecimal humidity;


    /**
     * 基波频率-状态
     */
    private String aaFundamentalFrequencyStatus;
    private String bbFundamentalFrequencyStatus;
    private String ccFundamentalFrequencyStatus;
    /**
     * 基波有效值-状态
     */
    private String aaFundamentalAmplitudeStatus;
    private String bbFundamentalAmplitudeStatus;
    private String ccFundamentalAmplitudeStatus;

    /**
     * 基波相位-状态
     */
    private String aaFundamentalPhaseStatus;
    private String bbFundamentalPhaseStatus;
    private String ccFundamentalPhaseStatus;
    /**
     * 采集时间开始
     */
    private Date collectTimeStart;

    /**
     * 采集时间结束
     */
    private Date collectTimeEnd;

    /**
     * 设置 接收数据ID
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取 接收数据ID
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置 板卡号
     */
    public void setBoardNum(Integer boardNum) {
        this.boardNum = boardNum;
    }

    /**
     * 获取 板卡号
     */
    public Integer getBoardNum() {
        return boardNum;
    }

    /**
     * 设置 板卡通道
     */
    public void setBoardChannel(Integer boardChannel) {
        this.boardChannel = boardChannel;
    }

    /**
     * 获取 板卡通道
     */
    public Integer getBoardChannel() {
        return boardChannel;
    }

    /**
     * 设置 线路通道
     */
    public void setChannelNum(Integer channelNum) {
        this.channelNum = channelNum;
    }

    /**
     * 获取 线路通道
     */
    public Integer getChannelNum() {
        return channelNum;
    }

    /**
     * 设置 采集时间
     */
    public void setCollectTime(Date collectTime) {
        this.collectTime = collectTime;
    }

    /**
     * 获取 采集时间
     */
    public Date getCollectTime() {
        return collectTime;
    }

    /**
     * 设置 时间类型（0终端本地时间，1站内B码，2PPS秒脉冲信号，3工控机本地时间）
     */
    public void setTimeType(Integer timeType) {
        this.timeType = timeType;
    }

    /**
     * 获取 时间类型（0终端本地时间，1站内B码，2PPS秒脉冲信号，3工控机本地时间）
     */
    public Integer getTimeType() {
        return timeType;
    }

    /**
     * 设置 a基波频率
     */
    public void setAaFundamentalFrequency(BigDecimal aaFundamentalFrequency) {
        this.aaFundamentalFrequency = aaFundamentalFrequency;
    }

    /**
     * 获取 a基波频率
     */
    public BigDecimal getAaFundamentalFrequency() {
        return aaFundamentalFrequency;
    }

    /**
     * 设置 a基波幅值
     */
    public void setAaFundamentalAmplitude(BigDecimal aaFundamentalAmplitude) {
        this.aaFundamentalAmplitude = aaFundamentalAmplitude;
    }

    /**
     * 获取 a基波幅值
     */
    public BigDecimal getAaFundamentalAmplitude() {
        return aaFundamentalAmplitude;
    }

    /**
     * 设置 a3次谐波幅值
     */
    public void setA3HarmonicAmplitude(BigDecimal a3HarmonicAmplitude) {
        this.a3HarmonicAmplitude = a3HarmonicAmplitude;
    }

    /**
     * 获取 a3次谐波幅值
     */
    public BigDecimal getA3HarmonicAmplitude() {
        return a3HarmonicAmplitude;
    }

    /**
     * 设置 a5次谐波幅值
     */
    public void setA5HarmonicAmplitude(BigDecimal a5HarmonicAmplitude) {
        this.a5HarmonicAmplitude = a5HarmonicAmplitude;
    }

    /**
     * 获取 a5次谐波幅值
     */
    public BigDecimal getA5HarmonicAmplitude() {
        return a5HarmonicAmplitude;
    }

    /**
     * 设置 a基波相位
     */
    public void setAaFundamentalPhase(BigDecimal aaFundamentalPhase) {
        this.aaFundamentalPhase = aaFundamentalPhase;
    }

    /**
     * 获取 a基波相位
     */
    public BigDecimal getAaFundamentalPhase() {
        return aaFundamentalPhase;
    }

    /**
     * 设置 a3次谐波相位
     */
    public void setA3HarmonicPhase(BigDecimal a3HarmonicPhase) {
        this.a3HarmonicPhase = a3HarmonicPhase;
    }

    /**
     * 获取 a3次谐波相位
     */
    public BigDecimal getA3HarmonicPhase() {
        return a3HarmonicPhase;
    }

    /**
     * 设置 a5次谐波相位
     */
    public void setA5HarmonicPhase(BigDecimal a5HarmonicPhase) {
        this.a5HarmonicPhase = a5HarmonicPhase;
    }

    /**
     * 获取 a5次谐波相位
     */
    public BigDecimal getA5HarmonicPhase() {
        return a5HarmonicPhase;
    }

    /**
     * 设置 b基波频率
     */
    public void setBbFundamentalFrequency(BigDecimal bbFundamentalFrequency) {
        this.bbFundamentalFrequency = bbFundamentalFrequency;
    }

    /**
     * 获取 b基波频率
     */
    public BigDecimal getBbFundamentalFrequency() {
        return bbFundamentalFrequency;
    }

    /**
     * 设置 b基波幅值
     */
    public void setBbFundamentalAmplitude(BigDecimal bbFundamentalAmplitude) {
        this.bbFundamentalAmplitude = bbFundamentalAmplitude;
    }

    /**
     * 获取 b基波幅值
     */
    public BigDecimal getBbFundamentalAmplitude() {
        return bbFundamentalAmplitude;
    }

    /**
     * 设置 b3次谐波幅值
     */
    public void setB3HarmonicAmplitude(BigDecimal b3HarmonicAmplitude) {
        this.b3HarmonicAmplitude = b3HarmonicAmplitude;
    }

    /**
     * 获取 b3次谐波幅值
     */
    public BigDecimal getB3HarmonicAmplitude() {
        return b3HarmonicAmplitude;
    }

    /**
     * 设置 b5次谐波幅值
     */
    public void setB5HarmonicAmplitude(BigDecimal b5HarmonicAmplitude) {
        this.b5HarmonicAmplitude = b5HarmonicAmplitude;
    }

    /**
     * 获取 b5次谐波幅值
     */
    public BigDecimal getB5HarmonicAmplitude() {
        return b5HarmonicAmplitude;
    }

    /**
     * 设置 b基波相位
     */
    public void setBbFundamentalPhase(BigDecimal bbFundamentalPhase) {
        this.bbFundamentalPhase = bbFundamentalPhase;
    }

    /**
     * 获取 b基波相位
     */
    public BigDecimal getBbFundamentalPhase() {
        return bbFundamentalPhase;
    }

    /**
     * 设置 b3次谐波相位
     */
    public void setB3HarmonicPhase(BigDecimal b3HarmonicPhase) {
        this.b3HarmonicPhase = b3HarmonicPhase;
    }

    /**
     * 获取 b3次谐波相位
     */
    public BigDecimal getB3HarmonicPhase() {
        return b3HarmonicPhase;
    }

    /**
     * 设置 b5次谐波相位
     */
    public void setB5HarmonicPhase(BigDecimal b5HarmonicPhase) {
        this.b5HarmonicPhase = b5HarmonicPhase;
    }

    /**
     * 获取 b5次谐波相位
     */
    public BigDecimal getB5HarmonicPhase() {
        return b5HarmonicPhase;
    }

    /**
     * 设置 c基波频率
     */
    public void setCcFundamentalFrequency(BigDecimal ccFundamentalFrequency) {
        this.ccFundamentalFrequency = ccFundamentalFrequency;
    }

    /**
     * 获取 c基波频率
     */
    public BigDecimal getCcFundamentalFrequency() {
        return ccFundamentalFrequency;
    }

    /**
     * 设置 c基波幅值
     */
    public void setCcFundamentalAmplitude(BigDecimal ccFundamentalAmplitude) {
        this.ccFundamentalAmplitude = ccFundamentalAmplitude;
    }

    /**
     * 获取 c基波幅值
     */
    public BigDecimal getCcFundamentalAmplitude() {
        return ccFundamentalAmplitude;
    }

    /**
     * 设置 c3次谐波幅值
     */
    public void setC3HarmonicAmplitude(BigDecimal c3HarmonicAmplitude) {
        this.c3HarmonicAmplitude = c3HarmonicAmplitude;
    }

    /**
     * 获取 c3次谐波幅值
     */
    public BigDecimal getC3HarmonicAmplitude() {
        return c3HarmonicAmplitude;
    }

    /**
     * 设置 c5次谐波幅值
     */
    public void setC5HarmonicAmplitude(BigDecimal c5HarmonicAmplitude) {
        this.c5HarmonicAmplitude = c5HarmonicAmplitude;
    }

    /**
     * 获取 c5次谐波幅值
     */
    public BigDecimal getC5HarmonicAmplitude() {
        return c5HarmonicAmplitude;
    }

    /**
     * 设置 c基波相位
     */
    public void setCcFundamentalPhase(BigDecimal ccFundamentalPhase) {
        this.ccFundamentalPhase = ccFundamentalPhase;
    }

    /**
     * 获取 c基波相位
     */
    public BigDecimal getCcFundamentalPhase() {
        return ccFundamentalPhase;
    }

    /**
     * 设置 c3次谐波相位
     */
    public void setC3HarmonicPhase(BigDecimal c3HarmonicPhase) {
        this.c3HarmonicPhase = c3HarmonicPhase;
    }

    /**
     * 获取 c3次谐波相位
     */
    public BigDecimal getC3HarmonicPhase() {
        return c3HarmonicPhase;
    }

    /**
     * 设置 c5次谐波相位
     */
    public void setC5HarmonicPhase(BigDecimal c5HarmonicPhase) {
        this.c5HarmonicPhase = c5HarmonicPhase;
    }

    /**
     * 获取 c5次谐波相位
     */
    public BigDecimal getC5HarmonicPhase() {
        return c5HarmonicPhase;
    }

    /**
     * 设置 零序电压不平衡度
     */
    public void setZeroSequenceVoltageImbalance(BigDecimal zeroSequenceVoltageImbalance) {
        this.zeroSequenceVoltageImbalance = zeroSequenceVoltageImbalance;
    }

    /**
     * 获取 零序电压不平衡度
     */
    public BigDecimal getZeroSequenceVoltageImbalance() {
        return zeroSequenceVoltageImbalance;
    }

    /**
     * 设置 负序电压不平衡度
     */
    public void setNegativeSequenceVoltageImbalance(BigDecimal negativeSequenceVoltageImbalance) {
        this.negativeSequenceVoltageImbalance = negativeSequenceVoltageImbalance;
    }

    /**
     * 获取 负序电压不平衡度
     */
    public BigDecimal getNegativeSequenceVoltageImbalance() {
        return negativeSequenceVoltageImbalance;
    }

    /**
     * 设置 正常数据1 ，异常数据0
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 获取 正常数据1 ，异常数据0
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置 线路电压等级
     */
    public void setChannelLevel(Integer channelLevel) {
        this.channelLevel = channelLevel;
    }

    /**
     * 获取 线路电压等级
     */
    public Integer getChannelLevel() {
        return channelLevel;
    }
/** 设置 创建时间 */
    /**
     * 设置 扩展字段1
     */
    public void setColumn1(String column1) {
        this.column1 = column1;
    }

    /**
     * 获取 扩展字段1
     */
    public String getColumn1() {
        return column1;
    }

    /**
     * 设置 扩展字段2
     */
    public void setColumn2(String column2) {
        this.column2 = column2;
    }

    /**
     * 获取 扩展字段2
     */
    public String getColumn2() {
        return column2;
    }

    /**
     * 设置 扩展字段3
     */
    public void setColumn3(String column3) {
        this.column3 = column3;
    }

    /**
     * 获取 扩展字段3
     */
    public String getColumn3() {
        return column3;
    }

    /**
     * 设置 是否补全数据（正常数据1，补全数据0）
     */
    public void setIsSupplement(Integer isSupplement) {
        this.isSupplement = isSupplement;
    }

    /**
     * 获取 是否补全数据（正常数据1，补全数据0）
     */
    public Integer getIsSupplement() {
        return isSupplement;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getAaFundamentalFrequencyStatus() {
        return aaFundamentalFrequencyStatus;
    }

    public void setAaFundamentalFrequencyStatus(String aaFundamentalFrequencyStatus) {
        this.aaFundamentalFrequencyStatus = aaFundamentalFrequencyStatus;
    }

    public String getBbFundamentalFrequencyStatus() {
        return bbFundamentalFrequencyStatus;
    }

    public void setBbFundamentalFrequencyStatus(String bbFundamentalFrequencyStatus) {
        this.bbFundamentalFrequencyStatus = bbFundamentalFrequencyStatus;
    }

    public String getCcFundamentalFrequencyStatus() {
        return ccFundamentalFrequencyStatus;
    }

    public void setCcFundamentalFrequencyStatus(String ccFundamentalFrequencyStatus) {
        this.ccFundamentalFrequencyStatus = ccFundamentalFrequencyStatus;
    }

    public String getAaFundamentalAmplitudeStatus() {
        return aaFundamentalAmplitudeStatus;
    }

    public void setAaFundamentalAmplitudeStatus(String aaFundamentalAmplitudeStatus) {
        this.aaFundamentalAmplitudeStatus = aaFundamentalAmplitudeStatus;
    }

    public String getBbFundamentalAmplitudeStatus() {
        return bbFundamentalAmplitudeStatus;
    }

    public void setBbFundamentalAmplitudeStatus(String bbFundamentalAmplitudeStatus) {
        this.bbFundamentalAmplitudeStatus = bbFundamentalAmplitudeStatus;
    }

    public String getCcFundamentalAmplitudeStatus() {
        return ccFundamentalAmplitudeStatus;
    }

    public void setCcFundamentalAmplitudeStatus(String ccFundamentalAmplitudeStatus) {
        this.ccFundamentalAmplitudeStatus = ccFundamentalAmplitudeStatus;
    }

    public String getAaFundamentalPhaseStatus() {
        return aaFundamentalPhaseStatus;
    }

    public void setAaFundamentalPhaseStatus(String aaFundamentalPhaseStatus) {
        this.aaFundamentalPhaseStatus = aaFundamentalPhaseStatus;
    }

    public String getBbFundamentalPhaseStatus() {
        return bbFundamentalPhaseStatus;
    }

    public void setBbFundamentalPhaseStatus(String bbFundamentalPhaseStatus) {
        this.bbFundamentalPhaseStatus = bbFundamentalPhaseStatus;
    }

    public String getCcFundamentalPhaseStatus() {
        return ccFundamentalPhaseStatus;
    }

    public void setCcFundamentalPhaseStatus(String ccFundamentalPhaseStatus) {
        this.ccFundamentalPhaseStatus = ccFundamentalPhaseStatus;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("boardNum", getBoardNum())
            .append("boardChannel", getBoardChannel())
            .append("channelNum", getChannelNum())
            .append("collectTime", getCollectTime())
            .append("timeType", getTimeType())
            .append("aaFundamentalFrequency", getAaFundamentalFrequency())
            .append("aaFundamentalAmplitude", getAaFundamentalAmplitude())
            .append("a3HarmonicAmplitude", getA3HarmonicAmplitude())
            .append("a5HarmonicAmplitude", getA5HarmonicAmplitude())
            .append("aaFundamentalPhase", getAaFundamentalPhase())
            .append("a3HarmonicPhase", getA3HarmonicPhase())
            .append("a5HarmonicPhase", getA5HarmonicPhase())
            .append("bbFundamentalFrequency", getBbFundamentalFrequency())
            .append("bbFundamentalAmplitude", getBbFundamentalAmplitude())
            .append("b3HarmonicAmplitude", getB3HarmonicAmplitude())
            .append("b5HarmonicAmplitude", getB5HarmonicAmplitude())
            .append("bbFundamentalPhase", getBbFundamentalPhase())
            .append("b3HarmonicPhase", getB3HarmonicPhase())
            .append("b5HarmonicPhase", getB5HarmonicPhase())
            .append("ccFundamentalFrequency", getCcFundamentalFrequency())
            .append("ccFundamentalAmplitude", getCcFundamentalAmplitude())
            .append("c3HarmonicAmplitude", getC3HarmonicAmplitude())
            .append("c5HarmonicAmplitude", getC5HarmonicAmplitude())
            .append("ccFundamentalPhase", getCcFundamentalPhase())
            .append("c3HarmonicPhase", getC3HarmonicPhase())
            .append("c5HarmonicPhase", getC5HarmonicPhase())
            .append("zeroSequenceVoltageImbalance", getZeroSequenceVoltageImbalance())
            .append("negativeSequenceVoltageImbalance", getNegativeSequenceVoltageImbalance())
            .append("status", getStatus())
            .append("channelLevel", getChannelLevel())
            .append("createTime", getCreateTime())
            .append("column1", getColumn1())
            .append("column2", getColumn2())
            .append("column3", getColumn3())
            .append("aaBoardSortNum", getAaBoardSortNum())
            .append("bbBoardSortNum", getBbBoardSortNum())
            .append("ccBoardSortNum", getCcBoardSortNum())
            .append("aaClockStatus", getAaClockStatus())
            .append("bbClockStatus", getBbClockStatus())
            .append("ccClockStatus", getCcClockStatus())
            .append("aaLogicDataQuality", getAaLogicDataQuality())
            .append("bbLogicDataQuality", getBbLogicDataQuality())
            .append("ccLogicDataQuality", getCcLogicDataQuality())
            .append("isSupplement", getIsSupplement())
            .toString();
    }

    /**
     * 获取 板卡采样值A真实通道序号
     *
     * @return aaBoardSortNum 板卡采样值A真实通道序号
     */
    public Integer getAaBoardSortNum() {
        return this.aaBoardSortNum;
    }

    /**
     * 设置 板卡采样值A真实通道序号
     *
     * @param aaBoardSortNum 板卡采样值A真实通道序号
     */
    public void setAaBoardSortNum(Integer aaBoardSortNum) {
        this.aaBoardSortNum = aaBoardSortNum;
    }

    /**
     * 获取 板卡采样值B真实通道序号
     *
     * @return bbBoardSortNum 板卡采样值B真实通道序号
     */
    public Integer getBbBoardSortNum() {
        return this.bbBoardSortNum;
    }

    /**
     * 设置 板卡采样值B真实通道序号
     *
     * @param bbBoardSortNum 板卡采样值B真实通道序号
     */
    public void setBbBoardSortNum(Integer bbBoardSortNum) {
        this.bbBoardSortNum = bbBoardSortNum;
    }

    /**
     * 获取 板卡采样值C真实通道序号
     *
     * @return ccBoardSortNum 板卡采样值C真实通道序号
     */
    public Integer getCcBoardSortNum() {
        return this.ccBoardSortNum;
    }

    /**
     * 设置 板卡采样值C真实通道序号
     *
     * @param ccBoardSortNum 板卡采样值C真实通道序号
     */
    public void setCcBoardSortNum(Integer ccBoardSortNum) {
        this.ccBoardSortNum = ccBoardSortNum;
    }

    /**
     * 获取 板卡采样值A通道时钟同步标志
     *
     * @return aaClockStatus 板卡采样值A通道时钟同步标志
     */
    public Integer getAaClockStatus() {
        return this.aaClockStatus;
    }

    /**
     * 设置 板卡采样值A通道时钟同步标志
     *
     * @param aaClockStatus 板卡采样值A通道时钟同步标志
     */
    public void setAaClockStatus(Integer aaClockStatus) {
        this.aaClockStatus = aaClockStatus;
    }

    /**
     * 获取 板卡采样值B通道时钟同步标志
     *
     * @return bbClockStatus 板卡采样值B通道时钟同步标志
     */
    public Integer getBbClockStatus() {
        return this.bbClockStatus;
    }

    /**
     * 设置 板卡采样值B通道时钟同步标志
     *
     * @param bbClockStatus 板卡采样值B通道时钟同步标志
     */
    public void setBbClockStatus(Integer bbClockStatus) {
        this.bbClockStatus = bbClockStatus;
    }

    /**
     * 获取 板卡采样值C通道时钟同步标志
     *
     * @return ccClockStatus 板卡采样值C通道时钟同步标志
     */
    public Integer getCcClockStatus() {
        return this.ccClockStatus;
    }

    /**
     * 设置 板卡采样值C通道时钟同步标志
     *
     * @param ccClockStatus 板卡采样值C通道时钟同步标志
     */
    public void setCcClockStatus(Integer ccClockStatus) {
        this.ccClockStatus = ccClockStatus;
    }

    /**
     * 获取 板卡采样值A通道品质位
     *
     * @return aaLogicDataQuality 板卡采样值A通道品质位
     */
    public String getAaLogicDataQuality() {
        return this.aaLogicDataQuality;
    }

    /**
     * 设置 板卡采样值A通道品质位
     *
     * @param aaLogicDataQuality 板卡采样值A通道品质位
     */
    public void setAaLogicDataQuality(String aaLogicDataQuality) {
        this.aaLogicDataQuality = aaLogicDataQuality;
    }

    /**
     * 获取 板卡采样值B通道品质位
     *
     * @return bbLogicDataQuality 板卡采样值B通道品质位
     */
    public String getBbLogicDataQuality() {
        return this.bbLogicDataQuality;
    }

    /**
     * 设置 板卡采样值B通道品质位
     *
     * @param bbLogicDataQuality 板卡采样值B通道品质位
     */
    public void setBbLogicDataQuality(String bbLogicDataQuality) {
        this.bbLogicDataQuality = bbLogicDataQuality;
    }

    /**
     * 获取 板卡采样值C通道品质位
     *
     * @return ccLogicDataQuality 板卡采样值C通道品质位
     */
    public String getCcLogicDataQuality() {
        return this.ccLogicDataQuality;
    }

    /**
     * 设置 板卡采样值C通道品质位
     *
     * @param ccLogicDataQuality 板卡采样值C通道品质位
     */
    public void setCcLogicDataQuality(String ccLogicDataQuality) {
        this.ccLogicDataQuality = ccLogicDataQuality;
    }

    public Date getCollectTimeStart() {
        return collectTimeStart;
    }

    public void setCollectTimeStart(Date collectTimeStart) {
        this.collectTimeStart = collectTimeStart;
    }

    public Date getCollectTimeEnd() {
        return collectTimeEnd;
    }

    public void setCollectTimeEnd(Date collectTimeEnd) {
        this.collectTimeEnd = collectTimeEnd;
    }

    /**
     * 获取
     *
     * @return unitId
     */
    public Long getUnitId() {
        return this.unitId;
    }

    /**
     * 设置
     *
     * @param unitId
     */
    public void setUnitId(Long unitId) {
        this.unitId = unitId;
    }

    public BigDecimal getTemperature() {
        return temperature;
    }

    public void setTemperature(BigDecimal temperature) {
        this.temperature = temperature;
    }

    public BigDecimal getHumidity() {
        return humidity;
    }

    public void setHumidity(BigDecimal humidity) {
        this.humidity = humidity;
    }

    public String getDiffInfo(DaVoltageData other){
        if (other == null){
            return String.format(DIffConstants.LOST);
        }
        List<String> res = new ArrayList<>();
        //基波频率
        if (!this.aaFundamentalFrequency.equals(other.aaFundamentalFrequency)) {
            res.add("基波频率A不一致");
        }
        if (!this.bbFundamentalFrequency.equals(other.bbFundamentalFrequency)) {
            res.add("基波频率B不一致");
        }
        if (!this.ccFundamentalFrequency.equals(other.ccFundamentalFrequency)) {
            res.add("基波频率C不一致");
        }
        //基波幅值
        if (!this.aaFundamentalAmplitude.equals(other.aaFundamentalAmplitude)) {
            res.add("基波幅值A不一致");
        }
        if (!this.bbFundamentalFrequency.equals(other.bbFundamentalFrequency)) {
            res.add("基波幅值B不一致");
        }
        if (!this.ccFundamentalFrequency.equals(other.ccFundamentalFrequency)) {
            res.add("基波幅值C不一致");
        }
        //基波相位
        if (!this.aaFundamentalPhase.equals(other.aaFundamentalPhase)){
            res.add("基波相位A不一致");
        }
        if (!this.bbFundamentalPhase.equals(other.bbFundamentalPhase)){
            res.add("基波相位B不一致");
        }
        if (!this.ccFundamentalPhase.equals(other.ccFundamentalPhase)){
            res.add("基波相位C不一致");
        }
        return String.join(",\n",res);
    }


    public String getKey(){
        return String.format("%s_%d",DateUtil.formatDateTime(this.collectTime),this.channelNum);
    }
}
