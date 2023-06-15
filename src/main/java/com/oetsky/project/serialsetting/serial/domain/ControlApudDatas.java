package com.oetsky.project.serialsetting.serial.domain;

import java.util.List;

/**
 * 帧数据中数据范围
 * @author zhangw
 */
public class ControlApudDatas {
    /**
     * 请求类型
     */
    public String requestNormal;
    public RequestNormalData requestNormalData;

    public String piid;
    /**
     * 数据OI类型及属性
     */
    public String oad;
    public RequestOAD requestOAD;

    public List<RequestOAD> requestOADList;

    /**
     * RSD_type
     * 冻结数据帧 RSD_type数据
     * FROZEN_DATA
     */
    public RequestFrozenDataRsdType requestFrozenDataRsdType;
    /**
     * RSD_type
     * 冻结日、月、年数据帧 RSD_type数据
     * FROZEN_DATA
     */
    public RequestFrozenDaysDataRsdType requestFrozenDaysDataRsdType;
    /**
     * RSD_type
     * 事件数据帧 RSD_type数据
     * FROZEN_DATA
     */
    public RequestEventDataRsdType requestEventDataRsdType;
    /**
     * RCSD_type
     * 冻结数据帧 RCSD_type
     * FROZEN_DATA
     */
    public RequestRCSDFrozenDataType requestRCSDFrozenDataType;

    /**
     * 广播校时数据
     */
    public CorrectingTimeData correctingTimeData;

    /**
     * 时间标签
     */
    public String timeTag;
    public RequestTimeTagData timeTagData;


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
     * 获取
     *
     * @return requestNormalData
     */
    public RequestNormalData getRequestNormalData() {
        return this.requestNormalData;
    }

    /**
     * 设置
     *
     * @param requestNormalData
     */
    public void setRequestNormalData(RequestNormalData requestNormalData) {
        this.requestNormalData = requestNormalData;
    }

    /**
     * 获取
     *
     * @return piid
     */
    public String getPiid() {
        return this.piid;
    }

    /**
     * 设置
     *
     * @param piid
     */
    public void setPiid(String piid) {
        this.piid = piid;
    }

    /**
     * 获取 数据OI类型及属性
     *
     * @return oad 数据OI类型及属性
     */
    public String getOad() {
        return this.oad;
    }

    /**
     * 设置 数据OI类型及属性
     *
     * @param oad 数据OI类型及属性
     */
    public void setOad(String oad) {
        this.oad = oad;
    }

    /**
     * 获取
     *
     * @return requestOAD
     */
    public RequestOAD getRequestOAD() {
        return this.requestOAD;
    }

    /**
     * 设置
     *
     * @param requestOAD
     */
    public void setRequestOAD(RequestOAD requestOAD) {
        this.requestOAD = requestOAD;
    }

    /**
     * 获取
     *
     * @return requestOADList
     */
    public List<RequestOAD> getRequestOADList() {
        return this.requestOADList;
    }

    /**
     * 设置
     *
     * @param requestOADList
     */
    public void setRequestOADList(List<RequestOAD> requestOADList) {
        this.requestOADList = requestOADList;
    }

    /**
     * 获取 RSD_type      冻结数据帧 RSD_type数据      FROZEN_DATA
     *
     * @return requestFrozenDataRsdType RSD_type      冻结数据帧 RSD_type数据      FROZEN_DATA
     */
    public RequestFrozenDataRsdType getRequestFrozenDataRsdType() {
        return this.requestFrozenDataRsdType;
    }

    /**
     * 设置 RSD_type      冻结数据帧 RSD_type数据      FROZEN_DATA
     *
     * @param requestFrozenDataRsdType RSD_type      冻结数据帧 RSD_type数据      FROZEN_DATA
     */
    public void setRequestFrozenDataRsdType(RequestFrozenDataRsdType requestFrozenDataRsdType) {
        this.requestFrozenDataRsdType = requestFrozenDataRsdType;
    }

    /**
     * 获取 RSD_type      冻结日、月、年数据帧 RSD_type数据      FROZEN_DATA
     *
     * @return requestFrozenDaysDataRsdType RSD_type      冻结日、月、年数据帧 RSD_type数据      FROZEN_DATA
     */
    public RequestFrozenDaysDataRsdType getRequestFrozenDaysDataRsdType() {
        return this.requestFrozenDaysDataRsdType;
    }

    /**
     * 设置 RSD_type      冻结日、月、年数据帧 RSD_type数据      FROZEN_DATA
     *
     * @param requestFrozenDaysDataRsdType RSD_type      冻结日、月、年数据帧 RSD_type数据      FROZEN_DATA
     */
    public void setRequestFrozenDaysDataRsdType(RequestFrozenDaysDataRsdType requestFrozenDaysDataRsdType) {
        this.requestFrozenDaysDataRsdType = requestFrozenDaysDataRsdType;
    }

    /**
     * 获取 RSD_type      事件数据帧 RSD_type数据      FROZEN_DATA
     *
     * @return requestEventDataRsdType RSD_type      事件数据帧 RSD_type数据      FROZEN_DATA
     */
    public RequestEventDataRsdType getRequestEventDataRsdType() {
        return this.requestEventDataRsdType;
    }

    /**
     * 设置 RSD_type      事件数据帧 RSD_type数据      FROZEN_DATA
     *
     * @param requestEventDataRsdType RSD_type      事件数据帧 RSD_type数据      FROZEN_DATA
     */
    public void setRequestEventDataRsdType(RequestEventDataRsdType requestEventDataRsdType) {
        this.requestEventDataRsdType = requestEventDataRsdType;
    }

    /**
     * 获取 RCSD_type      冻结数据帧 RCSD_type      FROZEN_DATA
     *
     * @return requestRCSDFrozenDataType RCSD_type      冻结数据帧 RCSD_type      FROZEN_DATA
     */
    public RequestRCSDFrozenDataType getRequestRCSDFrozenDataType() {
        return this.requestRCSDFrozenDataType;
    }

    /**
     * 设置 RCSD_type      冻结数据帧 RCSD_type      FROZEN_DATA
     *
     * @param requestRCSDFrozenDataType RCSD_type      冻结数据帧 RCSD_type      FROZEN_DATA
     */
    public void setRequestRCSDFrozenDataType(RequestRCSDFrozenDataType requestRCSDFrozenDataType) {
        this.requestRCSDFrozenDataType = requestRCSDFrozenDataType;
    }

    /**
     * 获取 广播校时数据
     *
     * @return correctingTimeData 广播校时数据
     */
    public CorrectingTimeData getCorrectingTimeData() {
        return this.correctingTimeData;
    }

    /**
     * 设置 广播校时数据
     *
     * @param correctingTimeData 广播校时数据
     */
    public void setCorrectingTimeData(CorrectingTimeData correctingTimeData) {
        this.correctingTimeData = correctingTimeData;
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
     * 获取
     *
     * @return timeTagData
     */
    public RequestTimeTagData getTimeTagData() {
        return this.timeTagData;
    }

    /**
     * 设置
     *
     * @param timeTagData
     */
    public void setTimeTagData(RequestTimeTagData timeTagData) {
        this.timeTagData = timeTagData;
    }
}






