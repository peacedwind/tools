package com.oetsky.project.serialsetting.serial.domain;


import java.util.List;
import java.util.Map;

/**
 * RCSD_type
 * 冻结数据帧 or 数据事件帧 RCSD_type
 * FROZEN_DATA
 * @author zhangw
 */
public class RequestRCSDFrozenDataType {
    /**
     * RCSD TYPE  数据
     */
    public String rcsdTypeStr;

    /**
     * RCSD TYPE  位数    2位
     */
    public String rcsdLength;
    /**
     * 请求参数类型
     */
    public List<Map<String, String>> rcsdMapList;
    /**
     * 请求参数类型
     */
    public List<String> rcsdList;

    /**
     * 获取 RCSD TYPE  数据
     *
     * @return rcsdTypeStr RCSD TYPE  数据
     */
    public String getRcsdTypeStr() {
        return this.rcsdTypeStr;
    }

    /**
     * 设置 RCSD TYPE  数据
     *
     * @param rcsdTypeStr RCSD TYPE  数据
     */
    public void setRcsdTypeStr(String rcsdTypeStr) {
        this.rcsdTypeStr = rcsdTypeStr;
    }

    /**
     * 获取 RCSD TYPE  位数    2位
     *
     * @return rcsdLength RCSD TYPE  位数    2位
     */
    public String getRcsdLength() {
        return this.rcsdLength;
    }

    /**
     * 设置 RCSD TYPE  位数    2位
     *
     * @param rcsdLength RCSD TYPE  位数    2位
     */
    public void setRcsdLength(String rcsdLength) {
        this.rcsdLength = rcsdLength;
    }

    /**
     * 获取 请求参数类型
     *
     * @return rcsdMapList 请求参数类型
     */
    public List<Map<String, String>> getRcsdMapList() {
        return this.rcsdMapList;
    }

    /**
     * 设置 请求参数类型
     *
     * @param rcsdMapList 请求参数类型
     */
    public void setRcsdMapList(List<Map<String, String>> rcsdMapList) {
        this.rcsdMapList = rcsdMapList;
    }

    /**
     * 获取 请求参数类型
     *
     * @return rcsdList 请求参数类型
     */
    public List<String> getRcsdList() {
        return this.rcsdList;
    }

    /**
     * 设置 请求参数类型
     *
     * @param rcsdList 请求参数类型
     */
    public void setRcsdList(List<String> rcsdList) {
        this.rcsdList = rcsdList;
    }
}
