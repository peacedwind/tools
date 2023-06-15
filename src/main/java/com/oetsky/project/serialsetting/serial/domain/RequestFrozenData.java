package com.oetsky.project.serialsetting.serial.domain;


import java.util.List;

/**
 * 冻结数据对象
 */
public class RequestFrozenData {
    /**
     * 冻结数据时间
     */
    public String frozenTime;

    /**
     * 电压数据集合
     */
    public List<String> voltageList;
    public String voltage;
    /**
     * 电流数据集合
     */
    public List<String> electricityList;
    public String electricity;
    /**
     * 有功功率数据集合
     */
    public List<String> activePowerList;
    public String activePower;
    /**
     * 无功功率数据集合
     */
//    private List<String>  reactivePowerList;
    public String reactivePower;
    /**
     * 功率因数数据集合
     */
    public List<String> powerFactorList;
    public String powerFactor;
    /**
     * 正向有功电能数据集合
     */
    public String positiveActive;
    /**
     * 反向有功电能数据集合
     */
    public String reverseActive;
    /**
     * 组合无功1电能数据
     */
    public String reactivePower1;
    /**
     * 组合无功2电能数据
     */
    public String reactivePower2;
    /**
     * 电压相角数据
     */
    public String voltagePhaseAngle;


    /**
     * 获取 冻结数据时间
     *
     * @return frozenTime 冻结数据时间
     */
    public String getFrozenTime() {
        return this.frozenTime;
    }

    /**
     * 设置 冻结数据时间
     *
     * @param frozenTime 冻结数据时间
     */
    public void setFrozenTime(String frozenTime) {
        this.frozenTime = frozenTime;
    }

    /**
     * 获取 电压数据集合
     *
     * @return voltageList 电压数据集合
     */
    public List<String> getVoltageList() {
        return this.voltageList;
    }

    /**
     * 设置 电压数据集合
     *
     * @param voltageList 电压数据集合
     */
    public void setVoltageList(List<String> voltageList) {
        this.voltageList = voltageList;
    }

    /**
     * 获取
     *
     * @return voltage
     */
    public String getVoltage() {
        return this.voltage;
    }

    /**
     * 设置
     *
     * @param voltage
     */
    public void setVoltage(String voltage) {
        this.voltage = voltage;
    }

    /**
     * 获取 电流数据集合
     *
     * @return electricityList 电流数据集合
     */
    public List<String> getElectricityList() {
        return this.electricityList;
    }

    /**
     * 设置 电流数据集合
     *
     * @param electricityList 电流数据集合
     */
    public void setElectricityList(List<String> electricityList) {
        this.electricityList = electricityList;
    }

    /**
     * 获取
     *
     * @return electricity
     */
    public String getElectricity() {
        return this.electricity;
    }

    /**
     * 设置
     *
     * @param electricity
     */
    public void setElectricity(String electricity) {
        this.electricity = electricity;
    }

    /**
     * 获取 有功功率数据集合
     *
     * @return activePowerList 有功功率数据集合
     */
    public List<String> getActivePowerList() {
        return this.activePowerList;
    }

    /**
     * 设置 有功功率数据集合
     *
     * @param activePowerList 有功功率数据集合
     */
    public void setActivePowerList(List<String> activePowerList) {
        this.activePowerList = activePowerList;
    }

    /**
     * 获取
     *
     * @return activePower
     */
    public String getActivePower() {
        return this.activePower;
    }

    /**
     * 设置
     *
     * @param activePower
     */
    public void setActivePower(String activePower) {
        this.activePower = activePower;
    }

    /**
     * 获取 无功功率数据集合
     *
     * @return reactivePower 无功功率数据集合
     */
    public String getReactivePower() {
        return this.reactivePower;
    }

    /**
     * 设置 无功功率数据集合
     *
     * @param reactivePower 无功功率数据集合
     */
    public void setReactivePower(String reactivePower) {
        this.reactivePower = reactivePower;
    }

    /**
     * 获取 功率因数数据集合
     *
     * @return powerFactorList 功率因数数据集合
     */
    public List<String> getPowerFactorList() {
        return this.powerFactorList;
    }

    /**
     * 设置 功率因数数据集合
     *
     * @param powerFactorList 功率因数数据集合
     */
    public void setPowerFactorList(List<String> powerFactorList) {
        this.powerFactorList = powerFactorList;
    }

    /**
     * 获取
     *
     * @return powerFactor
     */
    public String getPowerFactor() {
        return this.powerFactor;
    }

    /**
     * 设置
     *
     * @param powerFactor
     */
    public void setPowerFactor(String powerFactor) {
        this.powerFactor = powerFactor;
    }

    /**
     * 获取 正向有功电能数据集合
     *
     * @return positiveActive 正向有功电能数据集合
     */
    public String getPositiveActive() {
        return this.positiveActive;
    }

    /**
     * 设置 正向有功电能数据集合
     *
     * @param positiveActive 正向有功电能数据集合
     */
    public void setPositiveActive(String positiveActive) {
        this.positiveActive = positiveActive;
    }

    /**
     * 获取 反向有功电能数据集合
     *
     * @return reverseActive 反向有功电能数据集合
     */
    public String getReverseActive() {
        return this.reverseActive;
    }

    /**
     * 设置 反向有功电能数据集合
     *
     * @param reverseActive 反向有功电能数据集合
     */
    public void setReverseActive(String reverseActive) {
        this.reverseActive = reverseActive;
    }

    /**
     * 获取 组合无功1电能数据
     *
     * @return reactivePower1 组合无功1电能数据
     */
    public String getReactivePower1() {
        return this.reactivePower1;
    }

    /**
     * 设置 组合无功1电能数据
     *
     * @param reactivePower1 组合无功1电能数据
     */
    public void setReactivePower1(String reactivePower1) {
        this.reactivePower1 = reactivePower1;
    }

    /**
     * 获取 组合无功2电能数据
     *
     * @return reactivePower2 组合无功2电能数据
     */
    public String getReactivePower2() {
        return this.reactivePower2;
    }

    /**
     * 设置 组合无功2电能数据
     *
     * @param reactivePower2 组合无功2电能数据
     */
    public void setReactivePower2(String reactivePower2) {
        this.reactivePower2 = reactivePower2;
    }

    /**
     * 获取 电压相角数据
     *
     * @return voltagePhaseAngle 电压相角数据
     */
    public String getVoltagePhaseAngle() {
        return this.voltagePhaseAngle;
    }

    /**
     * 设置 电压相角数据
     *
     * @param voltagePhaseAngle 电压相角数据
     */
    public void setVoltagePhaseAngle(String voltagePhaseAngle) {
        this.voltagePhaseAngle = voltagePhaseAngle;
    }
}
