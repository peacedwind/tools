package com.oetsky.common.frame.frameBean;

import com.oetsky.framework.aspectj.lang.annotation.Excel;

public class MergingUnitClasses {

    /**
     * 采集数据类型
     */
    private String unitType;

    /**
     * 电流一次值
     */
    @Excel(name = "电流一次值")
    private Integer electrorheologicalClasses;

    /**
     * 电流二次值
     */
    @Excel(name = "电流二次值")
    private Integer electrorheologicalLevel;

    /**
     * 电压一次值
     */
    @Excel(name = "电压一次值")
    private Integer voltageClasses;

    /**
     * 电压二次值
     */
    @Excel(name = "电压二次值")
    private Integer voltageChangeThan;

    /**
     * 获取 电流一次值
     *
     * @return electrorheologicalClasses 电流一次值
     */
    public Integer getElectrorheologicalClasses() {
        return this.electrorheologicalClasses;
    }

    /**
     * 设置 电流一次值
     *
     * @param electrorheologicalClasses 电流一次值
     */
    public void setElectrorheologicalClasses(Integer electrorheologicalClasses) {
        this.electrorheologicalClasses = electrorheologicalClasses;
    }

    /**
     * 获取 电流二次值
     *
     * @return electrorheologicalLevel 电流二次值
     */
    public Integer getElectrorheologicalLevel() {
        return this.electrorheologicalLevel;
    }

    /**
     * 设置 电流二次值
     *
     * @param electrorheologicalLevel 电流二次值
     */
    public void setElectrorheologicalLevel(Integer electrorheologicalLevel) {
        this.electrorheologicalLevel = electrorheologicalLevel;
    }

    /**
     * 获取 电压一次值
     *
     * @return voltageClasses 电压一次值
     */
    public Integer getVoltageClasses() {
        return this.voltageClasses;
    }

    /**
     * 设置 电压一次值
     *
     * @param voltageClasses 电压一次值
     */
    public void setVoltageClasses(Integer voltageClasses) {
        this.voltageClasses = voltageClasses;
    }

    /**
     * 获取 电压二次值
     *
     * @return voltageChangeThan 电压二次值
     */
    public Integer getVoltageChangeThan() {
        return this.voltageChangeThan;
    }

    /**
     * 设置 电压二次值
     *
     * @param voltageChangeThan 电压二次值
     */
    public void setVoltageChangeThan(Integer voltageChangeThan) {
        this.voltageChangeThan = voltageChangeThan;
    }

    /**
     * 获取 采集数据类型
     *
     * @return unitType 采集数据类型
     */
    public String getUnitType() {
        return this.unitType;
    }

    /**
     * 设置 采集数据类型
     *
     * @param unitType 采集数据类型
     */
    public void setUnitType(String unitType) {
        this.unitType = unitType;
    }
}
