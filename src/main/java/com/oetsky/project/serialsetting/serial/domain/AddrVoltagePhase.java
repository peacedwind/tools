package com.oetsky.project.serialsetting.serial.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 电压互感器表计地址对象 addr_voltage_phase
 * 
 * @author huwm
 * @date 2022-03-25
 */
public class AddrVoltagePhase
{
    private static final long serialVersionUID = 1L;

    /**  */
    private Long id;

    /**  */
    private Integer channelNum;

    /**  */
    private String phaseSeq;

    /**  */
    private String meterAddr;

    /**  */
    private String hardwareAddr;

/** 设置  */
    public void setId(Long id) 
    {
        this.id = id;
    }

/** 获取  */
    public Long getId() 
    {
        return id;
    }
/** 设置  */
    public void setChannelNum(Integer channelNum) 
    {
        this.channelNum = channelNum;
    }

/** 获取  */
    public Integer getChannelNum() 
    {
        return channelNum;
    }
/** 设置  */
    public void setPhaseSeq(String phaseSeq) 
    {
        this.phaseSeq = phaseSeq;
    }

/** 获取  */
    public String getPhaseSeq() 
    {
        return phaseSeq;
    }
/** 设置  */
    public void setMeterAddr(String meterAddr) 
    {
        this.meterAddr = meterAddr;
    }

/** 获取  */
    public String getMeterAddr() 
    {
        return meterAddr;
    }
/** 设置  */
    public void setHardwareAddr(String hardwareAddr) 
    {
        this.hardwareAddr = hardwareAddr;
    }

/** 获取  */
    public String getHardwareAddr() 
    {
        return hardwareAddr;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("channelNum", getChannelNum())
            .append("phaseSeq", getPhaseSeq())
            .append("meterAddr", getMeterAddr())
            .append("hardwareAddr", getHardwareAddr())
            .toString();
    }
}
