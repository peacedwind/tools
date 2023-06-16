package com.oetsky.project.serialsetting.domain;

/**
 * @author: huwm
 * @date: 2019/5/16
 */
public class SerialPortBean {

    private String serialCode;

    private String serialLabel;


    /**
     * 获取
     *
     * @return serialCode
     */
    public String getSerialCode() {
        return this.serialCode;
    }

    /**
     * 设置
     *
     * @param serialCode
     */
    public void setSerialCode(String serialCode) {
        this.serialCode = serialCode;
    }

    /**
     * 获取
     *
     * @return serialLabel
     */
    public String getSerialLabel() {
        return this.serialLabel;
    }

    /**
     * 设置
     *
     * @param serialLabel
     */
    public void setSerialLabel(String serialLabel) {
        this.serialLabel = serialLabel;
    }
}
