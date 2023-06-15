package com.oetsky.project.dataselect.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import com.oetsky.framework.aspectj.lang.annotation.Excel;
import com.oetsky.framework.web.domain.BaseEntity;
import java.math.BigDecimal;
import java.util.Date;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


/**
 * 电压互感器误差数据对象 err_voltage_error
 *
 * @author huwm
 * @date 2022-03-09
 */
public class ErrVoltageError extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    private Date queryMaxTime;
    /** ID */
    private Long id;

    /** 通道 */
    @Excel(name = "通道")
    private Integer channelNum;

    private String channelName ;
    /** 计算时间 */

//    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "计算时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date calculateTime;

    /** A比差 */
    @Excel(name = "A比差")
    private BigDecimal aaRatioError;

    /** B比差 */
    @Excel(name = "B比差")
    private BigDecimal bbRatioError;

    /** C比差 */
    @Excel(name = "C比差")
    private BigDecimal ccRatioError;

    /** A比差方差 */
    @Excel(name = "A比差方差")
    private BigDecimal aaRatioVariance;

    /** B比差方差 */
    @Excel(name = "B比差方差")
    private BigDecimal bbRatioVariance;

    /** C比差方差 */
    @Excel(name = "C比差方差")
    private BigDecimal ccRatioVariance;

    /** A角差 */
    @Excel(name = "A角差")
    private BigDecimal aaAngleError;

    /** B角差 */
    @Excel(name = "B角差")
    private BigDecimal bbAngleError;

    /** C角差 */
    @Excel(name = "C角差")
    private BigDecimal ccAngleError;

    /** A角差方差 */
    @Excel(name = "A角差方差")
    private BigDecimal aaAngleVariance;

    /** B角差方差 */
    @Excel(name = "B角差方差")
    private BigDecimal bbAngleVariance;

    /** C角差方差 */
    @Excel(name = "C角差方差")
    private BigDecimal ccAngleVariance;

    /** 计算数据区域开始时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "计算数据区域开始时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date dataStartTime;

    /** 计算数据区域结束时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "计算数据区域结束时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date dataEndTime;

    /** 计算输出的UBnew */
    @Excel(name = "计算输出的UBnew")
    private String ubNew;

    /** 计算输入值UB */
    @Excel(name = "计算输入值UB")
    private String inputub;

    /** 电压等级 */
    @Excel(name = "线路电压等级")
    private Integer channelLevel;

    /**     * 计算状态     */
    @Excel(name = "计算状态")
    private String calculateStatus;

    /**     * 计算说明     */
    @Excel(name = "计算说明")
    private String calculateMsg;

    /**     * 比差参考值     */
    @Excel(name = "比差参考值")
    private String ratioVarargin;

    /**     * 角差参考值     */
    @Excel(name = "角差参考值")
    private String angleVarargin;

    /**     * A定性结果 0=正常 1=异常     */
    @Excel(name = "A定性结果")
    private String aaQualitativeResult;

    /**     * B定性结果 0=正常 1=异常     */
    @Excel(name = "B定性结果")
    private String bbQualitativeResult;

    /**     * C定性结果 0=正常 1=异常     */
    @Excel(name = "C定性结果")
    private String ccQualitativeResult;

    /**
     * 分组ID
     */
    private Long groupId;

    /**
     * 分组优先级
     */
    private Integer groupPriority;

    /**
     * 计算开始时间
     */
    private Date calculateTimeStart;

    /**
     * 计算结束时间
     */
    private Date calculateTimeEnd;

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public Date getCalculateTimeStart() {
        return calculateTimeStart;
    }

    public void setCalculateTimeStart(Date calculateTimeStart) {
        this.calculateTimeStart = calculateTimeStart;
    }

    public Date getCalculateTimeEnd() {
        return calculateTimeEnd;
    }

    public void setCalculateTimeEnd(Date calculateTimeEnd) {
        this.calculateTimeEnd = calculateTimeEnd;
    }

    public Integer getGroupPriority() {
        return groupPriority;
    }

    public void setGroupPriority(Integer groupPriority) {
        this.groupPriority = groupPriority;
    }

    public String getAaQualitativeResult() {
        return aaQualitativeResult;
    }

    public void setAaQualitativeResult(String aaQualitativeResult) {
        this.aaQualitativeResult = aaQualitativeResult;
    }

    public String getBbQualitativeResult() {
        return bbQualitativeResult;
    }

    public void setBbQualitativeResult(String bbQualitativeResult) {
        this.bbQualitativeResult = bbQualitativeResult;
    }

    public String getCcQualitativeResult() {
        return ccQualitativeResult;
    }

    public void setCcQualitativeResult(String ccQualitativeResult) {
        this.ccQualitativeResult = ccQualitativeResult;
    }

    public Date getQueryMaxTime() {
        return queryMaxTime;
    }

    public void setQueryMaxTime(Date queryMaxTime) {
        this.queryMaxTime = queryMaxTime;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    /** 设置 ID */
    public void setId(Long id)
    {
        this.id = id;
    }

    /** 获取 ID */
    public Long getId()
    {
        return id;
    }
    /** 设置 通道 */
    public void setChannelNum(Integer channelNum)
    {
        this.channelNum = channelNum;
    }

    /** 获取 通道 */
    public Integer getChannelNum()
    {
        return channelNum;
    }
    /** 设置 计算时间 */
    public void setCalculateTime(Date calculateTime)
    {
        this.calculateTime = calculateTime;
    }

    /** 获取 计算时间 */
    public Date getCalculateTime()
    {
        return calculateTime;
    }
    /** 设置 A比差 */
    public void setAaRatioError(BigDecimal aaRatioError)
    {
        this.aaRatioError = aaRatioError;
    }

    /** 获取 A比差 */
    public BigDecimal getAaRatioError()
    {
        return aaRatioError;
    }
    /** 设置 B比差 */
    public void setBbRatioError(BigDecimal bbRatioError)
    {
        this.bbRatioError = bbRatioError;
    }

    /** 获取 B比差 */
    public BigDecimal getBbRatioError()
    {
        return bbRatioError;
    }
    /** 设置 C比差 */
    public void setCcRatioError(BigDecimal ccRatioError)
    {
        this.ccRatioError = ccRatioError;
    }

    /** 获取 C比差 */
    public BigDecimal getCcRatioError()
    {
        return ccRatioError;
    }
    /** 设置 A比差方差 */
    public void setAaRatioVariance(BigDecimal aaRatioVariance)
    {
        this.aaRatioVariance = aaRatioVariance;
    }

    /** 获取 A比差方差 */
    public BigDecimal getAaRatioVariance()
    {
        return aaRatioVariance;
    }
    /** 设置 B比差方差 */
    public void setBbRatioVariance(BigDecimal bbRatioVariance)
    {
        this.bbRatioVariance = bbRatioVariance;
    }

    /** 获取 B比差方差 */
    public BigDecimal getBbRatioVariance()
    {
        return bbRatioVariance;
    }
    /** 设置 C比差方差 */
    public void setCcRatioVariance(BigDecimal ccRatioVariance)
    {
        this.ccRatioVariance = ccRatioVariance;
    }

    /** 获取 C比差方差 */
    public BigDecimal getCcRatioVariance()
    {
        return ccRatioVariance;
    }
    /** 设置 A角差 */
    public void setAaAngleError(BigDecimal aaAngleError)
    {
        this.aaAngleError = aaAngleError;
    }

    /** 获取 A角差 */
    public BigDecimal getAaAngleError()
    {
        return aaAngleError;
    }
    /** 设置 B角差 */
    public void setBbAngleError(BigDecimal bbAngleError)
    {
        this.bbAngleError = bbAngleError;
    }

    /** 获取 B角差 */
    public BigDecimal getBbAngleError()
    {
        return bbAngleError;
    }
    /** 设置 C角差 */
    public void setCcAngleError(BigDecimal ccAngleError)
    {
        this.ccAngleError = ccAngleError;
    }

    /** 获取 C角差 */
    public BigDecimal getCcAngleError()
    {
        return ccAngleError;
    }
    /** 设置 A角差方差 */
    public void setAaAngleVariance(BigDecimal aaAngleVariance)
    {
        this.aaAngleVariance = aaAngleVariance;
    }

    /** 获取 A角差方差 */
    public BigDecimal getAaAngleVariance()
    {
        return aaAngleVariance;
    }
    /** 设置 B角差方差 */
    public void setBbAngleVariance(BigDecimal bbAngleVariance)
    {
        this.bbAngleVariance = bbAngleVariance;
    }

    /** 获取 B角差方差 */
    public BigDecimal getBbAngleVariance()
    {
        return bbAngleVariance;
    }
    /** 设置 C角差方差 */
    public void setCcAngleVariance(BigDecimal ccAngleVariance)
    {
        this.ccAngleVariance = ccAngleVariance;
    }

    /** 获取 C角差方差 */
    public BigDecimal getCcAngleVariance()
    {
        return ccAngleVariance;
    }
    /** 设置 计算数据区域开始时间 */
    public void setDataStartTime(Date dataStartTime)
    {
        this.dataStartTime = dataStartTime;
    }

    /** 获取 计算数据区域开始时间 */
    public Date getDataStartTime()
    {
        return dataStartTime;
    }
    /** 设置 计算数据区域结束时间 */
    public void setDataEndTime(Date dataEndTime)
    {
        this.dataEndTime = dataEndTime;
    }

    /** 获取 计算数据区域结束时间 */
    public Date getDataEndTime()
    {
        return dataEndTime;
    }
    /** 设置 计算输出的UBnew */
    public void setUbNew(String ubNew)
    {
        this.ubNew = ubNew;
    }

    /** 获取 计算输出的UBnew */
    public String getUbNew()
    {
        return ubNew;
    }
    /** 设置 计算输入值UB */
    public void setInputub(String inputub)
    {
        this.inputub = inputub;
    }

    /** 获取 计算输入值UB */
    public String getInputub()
    {
        return inputub;
    }
    /** 设置 电压等级 */
    public void setChannelLevel(Integer channelLevel)
    {
        this.channelLevel = channelLevel;
    }

    /** 获取 电压等级 */
    public Integer getChannelLevel()
    {
        return channelLevel;
    }

    public String getCalculateStatus() {
        return calculateStatus;
    }

    public void setCalculateStatus(String calculateStatus) {
        this.calculateStatus = calculateStatus;
    }

    public String getCalculateMsg() {
        return calculateMsg;
    }

    public void setCalculateMsg(String calculateMsg) {
        this.calculateMsg = calculateMsg;
    }

    public String getRatioVarargin() {
        return ratioVarargin;
    }

    public void setRatioVarargin(String ratioVarargin) {
        this.ratioVarargin = ratioVarargin;
    }

    public String getAngleVarargin() {
        return angleVarargin;
    }

    public void setAngleVarargin(String angleVarargin) {
        this.angleVarargin = angleVarargin;
    }

    /** 设置 创建时间 */

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("channelNum", getChannelNum())
            .append("calculateTime", getCalculateTime())
            .append("aaRatioError", getAaRatioError())
            .append("bbRatioError", getBbRatioError())
            .append("ccRatioError", getCcRatioError())
            .append("aaRatioVariance", getAaRatioVariance())
            .append("bbRatioVariance", getBbRatioVariance())
            .append("ccRatioVariance", getCcRatioVariance())
            .append("aaAngleError", getAaAngleError())
            .append("bbAngleError", getBbAngleError())
            .append("ccAngleError", getCcAngleError())
            .append("aaAngleVariance", getAaAngleVariance())
            .append("bbAngleVariance", getBbAngleVariance())
            .append("ccAngleVariance", getCcAngleVariance())
            .append("dataStartTime", getDataStartTime())
            .append("dataEndTime", getDataEndTime())
            .append("ubNew", getUbNew())
            .append("inputub", getInputub())
            .append("channelLevel", getChannelLevel())
            .append("createTime", getCreateTime())
            .toString();
    }
}
