package com.oetsky.project.serialsetting.serial.domain;


/**
 * OAD拆分数据
 */
public class RequestOAD {
    /**
     * 数据OI
     */
    public String dataOi;
    /**
     * 数据OI属性
     */
    public String attribute;


    /**
     * 获取 数据OI
     *
     * @return dataOi 数据OI
     */
    public String getDataOi() {
        return this.dataOi;
    }

    /**
     * 设置 数据OI
     *
     * @param dataOi 数据OI
     */
    public void setDataOi(String dataOi) {
        this.dataOi = dataOi;
    }

    /**
     * 获取 数据OI属性
     *
     * @return attribute 数据OI属性
     */
    public String getAttribute() {
        return this.attribute;
    }

    /**
     * 设置 数据OI属性
     *
     * @param attribute 数据OI属性
     */
    public void setAttribute(String attribute) {
        this.attribute = attribute;
    }
}