package com.oetsky.common.frame.frameBean;

public class ProtocolFrameData {
    /**
     * 帧头
     */
    public String header68 = "68";
    /**
     * 长度
     */
    public String controlLeng;
    /**
     * 长度
     */
    public Integer controlLengInt;

    /**
     * 控制域
     */
    public String controlRegion;

    /**
     * 报文内容数据
     */
    public ProtocolFrameContentData frameData;
    /**
     * 帧校验FCS
     */
    public String finishChecks;

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
     * 获取 长度
     *
     * @return controlLengInt 长度
     */
    public Integer getControlLengInt() {
        return this.controlLengInt;
    }

    /**
     * 设置 长度
     *
     * @param controlLengInt 长度
     */
    public void setControlLengInt(Integer controlLengInt) {
        this.controlLengInt = controlLengInt;
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
     * 获取 报文内容数据
     *
     * @return frameData 报文内容数据
     */
    public ProtocolFrameContentData getFrameData() {
        return this.frameData;
    }

    /**
     * 设置 报文内容数据
     *
     * @param frameData 报文内容数据
     */
    public void setFrameData(ProtocolFrameContentData frameData) {
        this.frameData = frameData;
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
