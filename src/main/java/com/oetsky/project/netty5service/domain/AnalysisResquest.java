package com.oetsky.project.netty5service.domain;

public class AnalysisResquest {

    /**
     * 采集单元ID
     */
    private Long unitId;
    /**
     * 通讯协议
     */
    private String protocolType;
    /**
     * 板卡号
     */
    private Integer boardNum;
    /**
     * 协议方向
     */
    private String protocolDirection;
    /**
     * 协议内容
     */
    private String protocolContent;

    private Integer calibrationType;

    /**
     * 获取 采集单元ID
     *
     * @return unitId 采集单元ID
     */
    public Long getUnitId() {
        return this.unitId;
    }

    /**
     * 设置 采集单元ID
     *
     * @param unitId 采集单元ID
     */
    public void setUnitId(Long unitId) {
        this.unitId = unitId;
    }

    /**
     * 获取 通讯协议
     *
     * @return protocolType 通讯协议
     */
    public String getProtocolType() {
        return this.protocolType;
    }

    /**
     * 设置 通讯协议
     *
     * @param protocolType 通讯协议
     */
    public void setProtocolType(String protocolType) {
        this.protocolType = protocolType;
    }

    /**
     * 获取 板卡号
     *
     * @return boardNum 板卡号
     */
    public Integer getBoardNum() {
        return this.boardNum;
    }

    /**
     * 设置 板卡号
     *
     * @param boardNum 板卡号
     */
    public void setBoardNum(Integer boardNum) {
        this.boardNum = boardNum;
    }

    /**
     * 获取 协议方向
     *
     * @return protocolDirection 协议方向
     */
    public String getProtocolDirection() {
        return this.protocolDirection;
    }

    /**
     * 设置 协议方向
     *
     * @param protocolDirection 协议方向
     */
    public void setProtocolDirection(String protocolDirection) {
        this.protocolDirection = protocolDirection;
    }

    /**
     * 获取 协议内容
     *
     * @return protocolContent 协议内容
     */
    public String getProtocolContent() {
        return this.protocolContent;
    }

    /**
     * 设置 协议内容
     *
     * @param protocolContent 协议内容
     */
    public void setProtocolContent(String protocolContent) {
        this.protocolContent = protocolContent;
    }

    /**
     * 获取
     *
     * @return calibrationType
     */
    public Integer getCalibrationType() {
        return this.calibrationType;
    }

    /**
     * 设置
     *
     * @param calibrationType
     */
    public void setCalibrationType(Integer calibrationType) {
        this.calibrationType = calibrationType;
    }
}
