package com.oetsky.project.communication.domain;

public class SysUnitDict {

    /**
     * 主键
     */
    private Long id;


    /**
     * 采集单元名称
     */
    private String unitName;

    /**
     * 获取 主键
     *
     * @return id 主键
     */
    public Long getId() {
        return this.id;
    }

    /**
     * 设置 主键
     *
     * @param id 主键
     */
    public void setId(Long id) {
        this.id = id;
    }

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
}
