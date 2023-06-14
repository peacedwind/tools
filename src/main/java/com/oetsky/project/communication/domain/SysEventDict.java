package com.oetsky.project.communication.domain;

import com.oetsky.framework.aspectj.lang.annotation.Excel;
import com.oetsky.framework.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 事件数据字典对象 sys_event_dict
 *
 * @author xiangzc
 * @date 2023-02-08
 */
public class SysEventDict extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Long id;

    /**
     * 事件编码
     */
    @Excel(name = "事件编码")
    private String eventCode;

    /**
     * 事件名称
     */
    @Excel(name = "事件名称")
    private String eventName;

    /**
     * 事件类型
     */
    @Excel(name = "事件类型")
    private Integer eventType;

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
     * 获取 事件编码
     */
    public String getEventCode() {
        return eventCode;
    }

    /**
     * 设置 事件编码
     */
    public void setEventCode(String eventCode) {
        this.eventCode = eventCode;
    }

    /**
     * 获取 事件名称
     */
    public String getEventName() {
        return eventName;
    }

    /**
     * 设置 事件名称
     */
    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    /**
     * 获取 事件类型
     */
    public Integer getEventType() {
        return eventType;
    }

    /**
     * 设置 事件类型
     */
    public void setEventType(Integer eventType) {
        this.eventType = eventType;
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
            .append("eventCode", getEventCode())
            .append("eventName", getEventName())
            .append("eventType", getEventType())
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
}
