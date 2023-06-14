package com.oetsky.project.communication.domain;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.oetsky.framework.aspectj.lang.annotation.Excel;
import com.oetsky.framework.web.domain.BaseEntity;
import java.util.List;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.boot.convert.DurationFormat;

/**
 * 采集单元对象 sys_unit
 *
 * @author xiangzc
 * @date 2023-02-08
 */
public class SysUnit extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Long id;

    /**
     * 采集单元类型
     */
    @Excel(name = "采集单元类型")
    private String unitType;

    /**
     * 服务端口号
     */
    @Excel(name = "服务端口号")
    private Integer unitServiceProt;

    /**
     * 采集单元名称
     */
    @Excel(name = "采集单元名称")
    private String unitName;

    /**
     * 采集单元状态
     */
    @Excel(name = "采集单元状态")
    private Integer unitStatus;


    /**
     * 电源类型
     */
    @Excel(name = "电源类型")
    private String powerType;
    /**
     * 装置对时类型
     */
    @Excel(name = "装置对时类型")
    private String timeHackType;

    /**
     * 对时补偿
     */
    @Excel(name = "对时补偿")
    private Integer timeMakeUpFor;

    /**
     * 主控板硬件版本号
     */
    @Excel(name = "主控板硬件版本号")
    private String hardwareVersion;

    /**
     * 主控板软件版本号
     */
    @Excel(name = "主控板软件版本号")
    private String softwareVersion;

    /**
     * 主控板软件程序校验值
     */
    @Excel(name = "主控板软件程序校验值")
    private String softwareCheckValue;

    /**
     * FPGA版本号
     */
    @Excel(name = "FPGA版本号")
    private String fpgaVersion;

    /**
     * 采集单元中板卡信息集合
     */
    @JsonFilter("unitBoardList")
    private List<SysUnitBoard> unitBoardList ;

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
     * 获取 服务端口号
     */
    public Integer getUnitServiceProt() {
        return unitServiceProt;
    }

    /**
     * 设置 服务端口号
     */
    public void setUnitServiceProt(Integer unitServiceProt) {
        this.unitServiceProt = unitServiceProt;
    }

    /**
     * 获取 采集单元名称
     */
    public String getUnitName() {
        return unitName;
    }

    /**
     * 设置 采集单元名称
     */
    public void setUnitName(String unitName) {
        this.unitName = unitName;
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
            .append("unitType", getUnitType())
            .append("unitServiceProt", getUnitServiceProt())
            .append("unitName", getUnitName())
            .append("unitStatus", getUnitStatus())
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

    /**
     * 获取
     *
     * @return unitBoardList
     */
    public List<SysUnitBoard> getUnitBoardList() {
        return this.unitBoardList;
    }

    /**
     * 设置
     *
     * @param unitBoardList
     */
    public void setUnitBoardList(List<SysUnitBoard> unitBoardList) {
        if(unitBoardList != null){
            this.unitBoardList = unitBoardList;
        }
    }

    /**
     * 获取 主控板硬件版本号
     *
     * @return hardwareVersion 主控板硬件版本号
     */
    public String getHardwareVersion() {
        return this.hardwareVersion;
    }

    /**
     * 设置 主控板硬件版本号
     *
     * @param hardwareVersion 主控板硬件版本号
     */
    public void setHardwareVersion(String hardwareVersion) {
        this.hardwareVersion = hardwareVersion;
    }

    /**
     * 获取 主控板软件版本号
     *
     * @return softwareVersion 主控板软件版本号
     */
    public String getSoftwareVersion() {
        return this.softwareVersion;
    }

    /**
     * 设置 主控板软件版本号
     *
     * @param softwareVersion 主控板软件版本号
     */
    public void setSoftwareVersion(String softwareVersion) {
        this.softwareVersion = softwareVersion;
    }

    /**
     * 获取 主控板软件程序校验值
     *
     * @return softwareCheckValue 主控板软件程序校验值
     */
    public String getSoftwareCheckValue() {
        return this.softwareCheckValue;
    }

    /**
     * 设置 主控板软件程序校验值
     *
     * @param softwareCheckValue 主控板软件程序校验值
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
     * 获取 电源类型
     *
     * @return powerType 电源类型
     */
    public String getPowerType() {
        return this.powerType;
    }

    /**
     * 设置 电源类型
     *
     * @param powerType 电源类型
     */
    public void setPowerType(String powerType) {
        this.powerType = powerType;
    }


    /**
     * 获取 装置对时类型
     *
     * @return timeHackType 装置对时类型
     */
    public String getTimeHackType() {
        return this.timeHackType;
    }

    /**
     * 设置 装置对时类型
     *
     * @param timeHackType 装置对时类型
     */
    public void setTimeHackType(String timeHackType) {
        this.timeHackType = timeHackType;
    }

    /**
     * 获取 对时补偿
     *
     * @return timeMakeUpFor 对时补偿
     */
    public Integer getTimeMakeUpFor() {
        return this.timeMakeUpFor;
    }

    /**
     * 设置 对时补偿
     *
     * @param timeMakeUpFor 对时补偿
     */
    public void setTimeMakeUpFor(Integer timeMakeUpFor) {
        this.timeMakeUpFor = timeMakeUpFor;
    }
}
