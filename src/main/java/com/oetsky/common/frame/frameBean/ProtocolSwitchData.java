package com.oetsky.common.frame.frameBean;

/**
 * 功能描述: 开关盒子数据项
 *
 * @param:
 * @return:
 * @auther:
 * @date:
 */
public class ProtocolSwitchData {
    /**
     * 目的MAC地址
     */
    public String macAddr;

    /**
     * 源MAC地址
     */
    public String sourceMacAddr;
    /**
     * 协议类型
     */
    public String protocolType;
    /**
     * 版本IPv4
     */
    public String IPv4;
    /**
     * 服务类型
     */
    public String serviceType;

    /**
     * 总长度
     */
    public Integer controlLengInt;

    /**
     * 标识字段
     */
    public String markField;

    /**
     * 标志 片偏移
     */
    public String offset;

    /**
     * 生存时间
     */
    public String saveTime;

    /**
     * 协议
     */
    public String protocol;

    /**
     * 首部校验和
     */
    public String headCheck;
    /**
     * 源IP地址
     */
    public String sourceIp;
    /**
     * 目的IP地址
     */
    public String descIp;
    /**
     * 源端口
     */
    public String sourcePort;
    /**
     * 目的端口
     */
    public String descPort;


    /**
     * UDP长度
     */
    public Integer udpLengInt;

    /**
     * UDP校验和
     */
    public String udpCheck;


    /**
     * 报文ID
     */
    public String messageId;

    /**
     * 开关量采样时标使能
     */
    public String switchTime;

    /**
     * 时标年月日信息
     */
    public String clockDay;
    /**
     * 时标时分秒信息
     */
    public String clockSecond;

    public String getProtocolType() {
        return protocolType;
    }

    @Override
    public String toString() {
        return "ProtocolSwitchData{" +
                "macAddr='" + macAddr + '\'' +
                ", sourceMacAddr='" + sourceMacAddr + '\'' +
                ", protocolType='" + protocolType + '\'' +
                ", IPv4='" + IPv4 + '\'' +
                ", serviceType='" + serviceType + '\'' +
                ", controlLengInt=" + controlLengInt +
                ", markField='" + markField + '\'' +
                ", offset='" + offset + '\'' +
                ", saveTime='" + saveTime + '\'' +
                ", protocol='" + protocol + '\'' +
                ", headCheck='" + headCheck + '\'' +
                ", sourceIp='" + sourceIp + '\'' +
                ", descIp='" + descIp + '\'' +
                ", sourcePort='" + sourcePort + '\'' +
                ", descPort='" + descPort + '\'' +
                ", udpLengInt=" + udpLengInt +
                ", udpCheck='" + udpCheck + '\'' +
                ", messageId='" + messageId + '\'' +
                ", switchTime='" + switchTime + '\'' +
                ", clockDay='" + clockDay + '\'' +
                ", clockSecond='" + clockSecond + '\'' +
                ", clockNanosecond='" + clockNanosecond + '\'' +
                ", obj=" + obj +
                '}';
    }

    public void setProtocolType(String protocolType) {
        this.protocolType = protocolType;
    }

    public String getIPv4() {
        return IPv4;
    }

    public void setIPv4(String IPv4) {
        this.IPv4 = IPv4;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public String getMarkField() {
        return markField;
    }

    public void setMarkField(String markField) {
        this.markField = markField;
    }

    public String getOffset() {
        return offset;
    }

    public void setOffset(String offset) {
        this.offset = offset;
    }

    public String getSaveTime() {
        return saveTime;
    }

    public void setSaveTime(String saveTime) {
        this.saveTime = saveTime;
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public String getHeadCheck() {
        return headCheck;
    }

    public void setHeadCheck(String headCheck) {
        this.headCheck = headCheck;
    }

    public String getSourceIp() {
        return sourceIp;
    }

    public void setSourceIp(String sourceIp) {
        this.sourceIp = sourceIp;
    }

    public String getDescIp() {
        return descIp;
    }

    public void setDescIp(String descIp) {
        this.descIp = descIp;
    }

    public String getSourcePort() {
        return sourcePort;
    }

    public void setSourcePort(String sourcePort) {
        this.sourcePort = sourcePort;
    }

    public String getDescPort() {
        return descPort;
    }

    public void setDescPort(String descPort) {
        this.descPort = descPort;
    }

    public Integer getUdpLengInt() {
        return udpLengInt;
    }

    public void setUdpLengInt(Integer udpLengInt) {
        this.udpLengInt = udpLengInt;
    }

    public String getUdpCheck() {
        return udpCheck;
    }

    public void setUdpCheck(String udpCheck) {
        this.udpCheck = udpCheck;
    }

    /**
     * 时标纳秒信息
     */
    public String clockNanosecond;

    /**
     * 数据内容对象
     */
    private Object obj;


    public String getMacAddr() {
        return macAddr;
    }

    public void setMacAddr(String macAddr) {
        this.macAddr = macAddr;
    }

    public String getSourceMacAddr() {
        return sourceMacAddr;
    }

    public void setSourceMacAddr(String sourceMacAddr) {
        this.sourceMacAddr = sourceMacAddr;
    }

    public Integer getControlLengInt() {
        return controlLengInt;
    }

    public void setControlLengInt(Integer controlLengInt) {
        this.controlLengInt = controlLengInt;
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public String getSwitchTime() {
        return switchTime;
    }

    public void setSwitchTime(String switchTime) {
        this.switchTime = switchTime;
    }

    public String getClockDay() {
        return clockDay;
    }

    public void setClockDay(String clockDay) {
        this.clockDay = clockDay;
    }

    public String getClockSecond() {
        return clockSecond;
    }

    public void setClockSecond(String clockSecond) {
        this.clockSecond = clockSecond;
    }

    public String getClockNanosecond() {
        return clockNanosecond;
    }

    public void setClockNanosecond(String clockNanosecond) {
        this.clockNanosecond = clockNanosecond;
    }

    public Object getObj() {
        return obj;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }

}
