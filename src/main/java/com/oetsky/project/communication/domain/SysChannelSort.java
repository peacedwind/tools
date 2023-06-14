package com.oetsky.project.communication.domain;

import com.oetsky.framework.aspectj.lang.annotation.Excel;
import com.oetsky.framework.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 采集通道信息对象 sys_channel_sort
 *
 * @author xiangzc
 * @date 2023-02-08
 */
public class SysChannelSort extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Long id;

    /**
     * 采集单元ID
     */
    @Excel(name = "采集单元ID")
    private Long unitId;

    /**
     * 采集单元类型
     */
    @Excel(name = "采集单元类型")
    private String unitType;

    /**
     * 板卡类型
     */
    @Excel(name = "板卡类型")
    private String boardType;

    /**
     * 板卡号
     */
    @Excel(name = "板卡号")
    private Integer boardNum;

    /**
     * 通道号
     */
    @Excel(name = "通道号")
    private Integer channelNum;

    /**
     * 通道地址
     */
    @Excel(name = "通道地址")
    private String channelAddr;

    /**
     * 通道序号
     */
    @Excel(name = "通道序号")
    private Integer channelSort;

    /**
     * 站点类型
     */
    @Excel(name = "站点类型")
    private Integer stationType;

    /**
     * 数字化站点SVID
     */
    @Excel(name = "数字化站点SVID")
    private String svId;

    /**
     * 数字化站点APPID
     */
    @Excel(name = "数字化站点APPID")
    private String appId;

    /**
     * 频率校正系数
     */
    @Excel(name = "频率校正系数")
    private String factorValueUu;

    /**
     * 幅值校正系数
     */
    @Excel(name = "幅值校正系数")
    private String factorValuePp;
    /**
     * 数据对象通道类型
     */
    @Excel(name = "幅值校正系数")
    private String channelAddrType;

    /**
     * 备用1
     */
    @Excel(name = "备用1")
    private String remarkExt1;

    /**
     * 备用2
     */
    @Excel(name = "备用2")
    private String remarkExt2;

    /**
     * 备用3
     */
    @Excel(name = "备用3")
    private String remarkExt3;

    /**
     * 备用4
     */
    @Excel(name = "备用4")
    private String remarkExt4;

    /**
     * 备用5
     */
    @Excel(name = "备用5")
    private String remarkExt5;

    /**
     * 获取 主键
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置 主键
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取 采集单元ID
     */
    public Long getUnitId() {
        return unitId;
    }

    /**
     * 设置 采集单元ID
     */
    public void setUnitId(Long unitId) {
        this.unitId = unitId;
    }

    /**
     * 获取 采集单元类型
     */
    public String getUnitType() {
        return unitType;
    }

    /**
     * 设置 采集单元类型
     */
    public void setUnitType(String unitType) {
        this.unitType = unitType;
    }

    /**
     * 获取 板卡类型
     */
    public String getBoardType() {
        return boardType;
    }

    /**
     * 设置 板卡类型
     */
    public void setBoardType(String boardType) {
        this.boardType = boardType;
    }

    /**
     * 获取 板卡号
     */
    public Integer getBoardNum() {
        return boardNum;
    }

    /**
     * 设置 板卡号
     */
    public void setBoardNum(Integer boardNum) {
        this.boardNum = boardNum;
    }

    /**
     * 获取 通道号
     */
    public Integer getChannelNum() {
        return channelNum;
    }

    /**
     * 设置 通道号
     */
    public void setChannelNum(Integer channelNum) {
        this.channelNum = channelNum;
    }

    /**
     * 获取 通道地址
     */
    public String getChannelAddr() {
        return channelAddr;
    }

    /**
     * 设置 通道地址
     */
    public void setChannelAddr(String channelAddr) {
        this.channelAddr = channelAddr;
    }

    /**
     * 获取 通道序号
     */
    public Integer getChannelSort() {
        return channelSort;
    }

    /**
     * 设置 通道序号
     */
    public void setChannelSort(Integer channelSort) {
        this.channelSort = channelSort;
    }

    /**
     * 获取 站点类型
     */
    public Integer getStationType() {
        return stationType;
    }

    /**
     * 设置 站点类型
     */
    public void setStationType(Integer stationType) {
        this.stationType = stationType;
    }

    /**
     * 获取 数字化站点SVID
     */
    public String getSvId() {
        return svId;
    }

    /**
     * 设置 数字化站点SVID
     */
    public void setSvId(String svId) {
        this.svId = svId;
    }

    /**
     * 获取 数字化站点APPID
     */
    public String getAppId() {
        return appId;
    }

    /**
     * 设置 数字化站点APPID
     */
    public void setAppId(String appId) {
        this.appId = appId;
    }
/** 设置 创建人 */
/** 设置 创建时间 */
/** 设置 修改人 */
/** 设置 修改时间 */
/** 设置 备用 */

    /**
     * 获取 备用1
     */
    public String getRemarkExt1() {
        return remarkExt1;
    }

    /**
     * 设置 备用1
     */
    public void setRemarkExt1(String remarkExt1) {
        this.remarkExt1 = remarkExt1;
    }

    /**
     * 获取 备用2
     */
    public String getRemarkExt2() {
        return remarkExt2;
    }

    /**
     * 设置 备用2
     */
    public void setRemarkExt2(String remarkExt2) {
        this.remarkExt2 = remarkExt2;
    }

    /**
     * 获取 备用3
     */
    public String getRemarkExt3() {
        return remarkExt3;
    }

    /**
     * 设置 备用3
     */
    public void setRemarkExt3(String remarkExt3) {
        this.remarkExt3 = remarkExt3;
    }

    /**
     * 获取 备用4
     */
    public String getRemarkExt4() {
        return remarkExt4;
    }

    /**
     * 设置 备用4
     */
    public void setRemarkExt4(String remarkExt4) {
        this.remarkExt4 = remarkExt4;
    }

    /**
     * 获取 备用5
     */
    public String getRemarkExt5() {
        return remarkExt5;
    }

    /**
     * 设置 备用5
     */
    public void setRemarkExt5(String remarkExt5) {
        this.remarkExt5 = remarkExt5;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("unitId", getUnitId())
            .append("unitType", getUnitType())
            .append("boardType", getBoardType())
            .append("boardNum", getBoardNum())
            .append("channelNum", getChannelNum())
            .append("channelAddr", getChannelAddr())
            .append("channelSort", getChannelSort())
            .append("stationType", getStationType())
            .append("svId", getSvId())
            .append("appId", getAppId())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .append("remarkExt1", getRemarkExt1())
            .append("remarkExt2", getRemarkExt2())
            .append("remarkExt3", getRemarkExt3())
            .append("remarkExt4", getRemarkExt4())
            .append("remarkExt5", getRemarkExt5())
            .toString();
    }

    /**
     * 获取 频率校正系数
     *
     * @return factorValueUu 频率校正系数
     */
    public String getFactorValueUu() {
        return this.factorValueUu;
    }

    /**
     * 设置 频率校正系数
     *
     * @param factorValueUu 频率校正系数
     */
    public void setFactorValueUu(String factorValueUu) {
        this.factorValueUu = factorValueUu;
    }

    /**
     * 获取 幅值校正系数
     *
     * @return factorValuePp 幅值校正系数
     */
    public String getFactorValuePp() {
        return this.factorValuePp;
    }

    /**
     * 设置 幅值校正系数
     *
     * @param factorValuePp 幅值校正系数
     */
    public void setFactorValuePp(String factorValuePp) {
        this.factorValuePp = factorValuePp;
    }

    private String unitName;

    /**
     * 获取
     *
     * @return unitName
     */
    public String getUnitName() {
        return this.unitName;
    }

    /**
     * 设置
     *
     * @param unitName
     */
    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    /**
     * 获取 数据对象通道类型
     *
     * @return channelAddrType 数据对象通道类型
     */
    public String getChannelAddrType() {
        return this.channelAddrType;
    }

    /**
     * 设置 数据对象通道类型
     *
     * @param channelAddrType 数据对象通道类型
     */
    public void setChannelAddrType(String channelAddrType) {
        this.channelAddrType = channelAddrType;
    }
}
