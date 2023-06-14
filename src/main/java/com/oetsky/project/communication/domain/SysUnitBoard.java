package com.oetsky.project.communication.domain;

import com.oetsky.framework.aspectj.lang.annotation.Excel;
import com.oetsky.framework.web.domain.BaseEntity;
import java.util.List;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 采集板卡信息对象 sys_unit_board
 *
 * @author xiangzc
 * @date 2023-02-08
 */
public class SysUnitBoard extends BaseEntity {

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
     * 采集板卡类型
     */
    @Excel(name = "采集板卡类型")
    private String unitBoardType;

    /**
     * 采集板卡号
     */
    @Excel(name = "采集板卡号")
    private Integer unitBoardNum;

    /**
     * 通道号
     */
    @Excel(name = "通道号")
    private Integer channelNum;



    /**
     * 硬件版本号
     */
    @Excel(name = "硬件版本号")
    private String hardwareVersion;

    /**
     * 软件版本号
     */
    @Excel(name = "软件版本号")
    private String softwareVersion;

    /**
     * 软件程序校验值
     */
    @Excel(name = "软件程序校验值")
    private String softwareCheckValue;

    /**
     * FPGA版本号
     */
    @Excel(name = "FPGA版本号")
    private String fpgaVersion;

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
     * 获取 采集板卡类型
     */
    public String getUnitBoardType() {
        return unitBoardType;
    }

    /**
     * 设置 采集板卡类型
     */
    public void setUnitBoardType(String unitBoardType) {
        this.unitBoardType = unitBoardType;
    }

    /**
     * 获取 采集板卡号
     */
    public Integer getUnitBoardNum() {
        return unitBoardNum;
    }

    /**
     * 设置 采集板卡号
     */
    public void setUnitBoardNum(Integer unitBoardNum) {
        this.unitBoardNum = unitBoardNum;
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
            .append("unitBoardType", getUnitBoardType())
            .append("unitBoardNum", getUnitBoardNum())
            .append("channelNum", getChannelNum())
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
     * 通道地址信息集合
     */
    @Excel(name = "通道地址信息集合")
    private List<SysChannelSort> channelSortList;

    /**
     * 获取 通道地址信息集合
     *
     * @return channelSortList 通道地址信息集合
     */
    public List<SysChannelSort> getChannelSortList() {
        return this.channelSortList;
    }

    /**
     * 设置 通道地址信息集合
     *
     * @param channelSortList 通道地址信息集合
     */
    public void setChannelSortList(List<SysChannelSort> channelSortList) {
        this.channelSortList = channelSortList;
    }

    /**
     * 获取 硬件版本号
     *
     * @return hardwareVersion 硬件版本号
     */
    public String getHardwareVersion() {
        return this.hardwareVersion;
    }

    /**
     * 设置 硬件版本号
     *
     * @param hardwareVersion 硬件版本号
     */
    public void setHardwareVersion(String hardwareVersion) {
        this.hardwareVersion = hardwareVersion;
    }

    /**
     * 获取 软件版本号
     *
     * @return softwareVersion 软件版本号
     */
    public String getSoftwareVersion() {
        return this.softwareVersion;
    }

    /**
     * 设置 软件版本号
     *
     * @param softwareVersion 软件版本号
     */
    public void setSoftwareVersion(String softwareVersion) {
        this.softwareVersion = softwareVersion;
    }

    /**
     * 获取 软件程序校验值
     *
     * @return softwareCheckValue 软件程序校验值
     */
    public String getSoftwareCheckValue() {
        return this.softwareCheckValue;
    }

    /**
     * 设置 软件程序校验值
     *
     * @param softwareCheckValue 软件程序校验值
     */
    public void setSoftwareCheckValue(String softwareCheckValue) {
        this.softwareCheckValue = softwareCheckValue;
    }

    /**
     * 获取 FPGA版本号
     *
     * @return fpgaVersion FPGA版本号
     */
    public String getFpgaVersion() {
        return this.fpgaVersion;
    }

    /**
     * 设置 FPGA版本号
     *
     * @param fpgaVersion FPGA版本号
     */
    public void setFpgaVersion(String fpgaVersion) {
        this.fpgaVersion = fpgaVersion;
    }

    /**
     * 采集单元名称
     */
    private String unitName;
    /**
     * 采集单元状态
     */
    private Integer unitStatus;

    /**
     * 获取 采集单元名称
     *
     * @return unitName 采集单元名称
     */
    public String getUnitName() {
        return this.unitName;
    }

    /**
     * 设置 采集单元名称
     *
     * @param unitName 采集单元名称
     */
    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    /**
     * 获取 采集单元状态
     *
     * @return unitStatus 采集单元状态
     */
    public Integer getUnitStatus() {
        return this.unitStatus;
    }

    /**
     * 设置 采集单元状态
     *
     * @param unitStatus 采集单元状态
     */
    public void setUnitStatus(Integer unitStatus) {
        this.unitStatus = unitStatus;
    }
}
