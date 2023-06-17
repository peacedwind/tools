package com.oetsky.project.serialsetting.domain;

import com.oetsky.framework.aspectj.lang.annotation.Excel;
import com.oetsky.framework.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * RS-485对象 dv_serial_setting
 *
 * @author huwm
 * @date 2022-03-04
 */
public class DvSerialSetting extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Long id;

    /**
     * 设备名称
     */
    @Excel(name = "设备名称")
    private String deviceName;

    /**
     * 设备类型
     */
    @Excel(name = "设备类型")
    private String deviceType;

    /**
     * 通讯协议
     */
    @Excel(name = "通讯协议")
    private String agreementType;

    /**
     * 串口号
     */
    @Excel(name = "串口号")
    private String serialNumber;

    /**
     * 波特率
     */
    @Excel(name = "波特率")
    private Integer baudRate;

    /**
     * 数据位
     */
    @Excel(name = "数据位")
    private Integer dataBit;

    /**
     * 校验位
     */
    @Excel(name = "校验位")
    private Integer parityBit;

    /**
     * 停止位
     */
    @Excel(name = "停止位")
    private Integer stopBit;

    /**
     * 通讯状态
     */
    @Excel(name = "通讯状态")
    private Integer interfaceStatus;

    /**
     * 设置 主键
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取 主键
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置 设备名称
     */
    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    /**
     * 获取 设备名称
     */
    public String getDeviceName() {
        return deviceName;
    }

    /**
     * 设置 设备类型
     */
    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    /**
     * 获取 设备类型
     */
    public String getDeviceType() {
        return deviceType;
    }

    /**
     * 设置 通讯协议
     */
    public void setAgreementType(String agreementType) {
        this.agreementType = agreementType;
    }

    /**
     * 获取 通讯协议
     */
    public String getAgreementType() {
        return agreementType;
    }

    /**
     * 设置 串口号
     */
    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    /**
     * 获取 串口号
     */
    public String getSerialNumber() {
        return serialNumber;
    }

    /**
     * 设置 波特率
     */
    public void setBaudRate(Integer baudRate) {
        this.baudRate = baudRate;
    }

    /**
     * 获取 波特率
     */
    public Integer getBaudRate() {
        return baudRate;
    }

    /**
     * 设置 数据位
     */
    public void setDataBit(Integer dataBit) {
        this.dataBit = dataBit;
    }

    /**
     * 获取 数据位
     */
    public Integer getDataBit() {
        return dataBit;
    }

    /**
     * 设置 校验位
     */
    public void setParityBit(Integer parityBit) {
        this.parityBit = parityBit;
    }

    /**
     * 获取 校验位
     */
    public Integer getParityBit() {
        return parityBit;
    }

    /**
     * 设置 停止位
     */
    public void setStopBit(Integer stopBit) {
        this.stopBit = stopBit;
    }

    /**
     * 获取 停止位
     */
    public Integer getStopBit() {
        return stopBit;
    }

    /**
     * 设置 通讯状态
     */
    public void setInterfaceStatus(Integer interfaceStatus) {
        this.interfaceStatus = interfaceStatus;
    }

    /**
     * 获取 通讯状态
     */
    public Integer getInterfaceStatus() {
        return interfaceStatus;
    }
    /**
     * 设置 备注
     */

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("deviceName", getDeviceName())
                .append("deviceType", getDeviceType())
                .append("agreementType", getAgreementType())
                .append("serialNumber", getSerialNumber())
                .append("baudRate", getBaudRate())
                .append("dataBit", getDataBit())
                .append("parityBit", getParityBit())
                .append("stopBit", getStopBit())
                .append("interfaceStatus", getInterfaceStatus())
                .append("createTime", getCreateTime())
                .append("updateTime", getUpdateTime())
                .append("remark", getRemark())
                .toString();
    }
}
