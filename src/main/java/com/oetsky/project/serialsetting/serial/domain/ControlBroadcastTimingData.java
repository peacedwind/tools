package com.oetsky.project.serialsetting.serial.domain;


/**
 * 解析报文命令帧
 */

public class ControlBroadcastTimingData {
    /**
     * 帧头
     */
    public String header68 = "68";

    /**
     * 长度
     */
    public String controlLeng;

    /**
     * 控制域
     */
    public String controlRegion;

    /**
     * 地址类型
     */
    public String regionType;

    /**
     * 服务器地址SA
     */
    public String serverAddress;

    /**
     * 客户机地址CA
     */
    public String customerAddress;

    /**
     * 帧头校验HCS
     */
    public String headerChecks;
    /**
     * 帧头检验HCS数据
     */
    public String headerCheckDataStr;

    /**
     * 请求类型
     */
    public String requestNormal;

    /**
     * PIID
     */
    public String piid;

    /**
     * 数据OI类型及属性
     */
    public String omd;


    /**
     * 数据域
     */
    public String controlData;

    /**
     * 时间标签
     */
    public String timeTag;

    /**
     * 帧校验FCS
     */
    public String finishChecks;
    /**
     * 帧校验FCS数据
     */
    public String finishCheckDataStr;

    /**
     * 帧尾
     */
    public String footer16 = "16";


    /**
     * 获取 帧头
     *
     * @return header68 帧头
     */
    public String getHeader68() {
        return this.header68;
    }

    /**
     * 设置 帧头
     *
     * @param header68 帧头
     */
    public void setHeader68(String header68) {
        this.header68 = header68;
    }

    /**
     * 获取 长度
     *
     * @return controlLeng 长度
     */
    public String getControlLeng() {
        return this.controlLeng;
    }

    /**
     * 设置 长度
     *
     * @param controlLeng 长度
     */
    public void setControlLeng(String controlLeng) {
        this.controlLeng = controlLeng;
    }

    /**
     * 获取 控制域
     *
     * @return controlRegion 控制域
     */
    public String getControlRegion() {
        return this.controlRegion;
    }

    /**
     * 设置 控制域
     *
     * @param controlRegion 控制域
     */
    public void setControlRegion(String controlRegion) {
        this.controlRegion = controlRegion;
    }

    /**
     * 获取 地址类型
     *
     * @return regionType 地址类型
     */
    public String getRegionType() {
        return this.regionType;
    }

    /**
     * 设置 地址类型
     *
     * @param regionType 地址类型
     */
    public void setRegionType(String regionType) {
        this.regionType = regionType;
    }

    /**
     * 获取 服务器地址SA
     *
     * @return serverAddress 服务器地址SA
     */
    public String getServerAddress() {
        return this.serverAddress;
    }

    /**
     * 设置 服务器地址SA
     *
     * @param serverAddress 服务器地址SA
     */
    public void setServerAddress(String serverAddress) {
        this.serverAddress = serverAddress;
    }

    /**
     * 获取 客户机地址CA
     *
     * @return customerAddress 客户机地址CA
     */
    public String getCustomerAddress() {
        return this.customerAddress;
    }

    /**
     * 设置 客户机地址CA
     *
     * @param customerAddress 客户机地址CA
     */
    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    /**
     * 获取 帧头校验HCS
     *
     * @return headerChecks 帧头校验HCS
     */
    public String getHeaderChecks() {
        return this.headerChecks;
    }

    /**
     * 设置 帧头校验HCS
     *
     * @param headerChecks 帧头校验HCS
     */
    public void setHeaderChecks(String headerChecks) {
        this.headerChecks = headerChecks;
    }

    /**
     * 获取 帧头检验HCS数据
     *
     * @return headerCheckDataStr 帧头检验HCS数据
     */
    public String getHeaderCheckDataStr() {
        return this.headerCheckDataStr;
    }

    /**
     * 设置 帧头检验HCS数据
     *
     * @param headerCheckDataStr 帧头检验HCS数据
     */
    public void setHeaderCheckDataStr(String headerCheckDataStr) {
        this.headerCheckDataStr = headerCheckDataStr;
    }

    /**
     * 获取 请求类型
     *
     * @return requestNormal 请求类型
     */
    public String getRequestNormal() {
        return this.requestNormal;
    }

    /**
     * 设置 请求类型
     *
     * @param requestNormal 请求类型
     */
    public void setRequestNormal(String requestNormal) {
        this.requestNormal = requestNormal;
    }

    /**
     * 获取 PIID
     *
     * @return piid PIID
     */
    public String getPiid() {
        return this.piid;
    }

    /**
     * 设置 PIID
     *
     * @param piid PIID
     */
    public void setPiid(String piid) {
        this.piid = piid;
    }

    /**
     * 获取 数据OI类型及属性
     *
     * @return omd 数据OI类型及属性
     */
    public String getOmd() {
        return this.omd;
    }

    /**
     * 设置 数据OI类型及属性
     *
     * @param omd 数据OI类型及属性
     */
    public void setOmd(String omd) {
        this.omd = omd;
    }

    /**
     * 获取 数据域
     *
     * @return controlData 数据域
     */
    public String getControlData() {
        return this.controlData;
    }

    /**
     * 设置 数据域
     *
     * @param controlData 数据域
     */
    public void setControlData(String controlData) {
        this.controlData = controlData;
    }

    /**
     * 获取 时间标签
     *
     * @return timeTag 时间标签
     */
    public String getTimeTag() {
        return this.timeTag;
    }

    /**
     * 设置 时间标签
     *
     * @param timeTag 时间标签
     */
    public void setTimeTag(String timeTag) {
        this.timeTag = timeTag;
    }

    /**
     * 获取 帧校验FCS
     *
     * @return finishChecks 帧校验FCS
     */
    public String getFinishChecks() {
        return this.finishChecks;
    }

    /**
     * 设置 帧校验FCS
     *
     * @param finishChecks 帧校验FCS
     */
    public void setFinishChecks(String finishChecks) {
        this.finishChecks = finishChecks;
    }

    /**
     * 获取 帧校验FCS数据
     *
     * @return finishCheckDataStr 帧校验FCS数据
     */
    public String getFinishCheckDataStr() {
        return this.finishCheckDataStr;
    }

    /**
     * 设置 帧校验FCS数据
     *
     * @param finishCheckDataStr 帧校验FCS数据
     */
    public void setFinishCheckDataStr(String finishCheckDataStr) {
        this.finishCheckDataStr = finishCheckDataStr;
    }

    /**
     * 获取 帧尾
     *
     * @return footer16 帧尾
     */
    public String getFooter16() {
        return this.footer16;
    }

    /**
     * 设置 帧尾
     *
     * @param footer16 帧尾
     */
    public void setFooter16(String footer16) {
        this.footer16 = footer16;
    }
}
