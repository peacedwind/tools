package com.oetsky.project.serialsetting.serial.domain;


import java.util.Date;

/**
 * 时间标签拆份
 */
public class RequestTimeTagData {
    /**
     * 是否包含时间
     */
    public String isTimeTag;
    /**
     * 时间范围
     */
    public String selectTimeTag;
    /**
     * 时间范围
     */
    public Date selectTimeTagDate;
    /**
     * 等待时间标签
     */
    public String awaitTimeTag;


    /**
     * 获取 是否包含时间
     *
     * @return isTimeTag 是否包含时间
     */
    public String getIsTimeTag() {
        return this.isTimeTag;
    }

    /**
     * 设置 是否包含时间
     *
     * @param isTimeTag 是否包含时间
     */
    public void setIsTimeTag(String isTimeTag) {
        this.isTimeTag = isTimeTag;
    }

    /**
     * 获取 时间范围
     *
     * @return selectTimeTag 时间范围
     */
    public String getSelectTimeTag() {
        return this.selectTimeTag;
    }

    /**
     * 设置 时间范围
     *
     * @param selectTimeTag 时间范围
     */
    public void setSelectTimeTag(String selectTimeTag) {
        this.selectTimeTag = selectTimeTag;
    }

    /**
     * 获取 时间范围
     *
     * @return selectTimeTagDate 时间范围
     */
    public Date getSelectTimeTagDate() {
        return this.selectTimeTagDate;
    }

    /**
     * 设置 时间范围
     *
     * @param selectTimeTagDate 时间范围
     */
    public void setSelectTimeTagDate(Date selectTimeTagDate) {
        this.selectTimeTagDate = selectTimeTagDate;
    }

    /**
     * 获取 等待时间标签
     *
     * @return awaitTimeTag 等待时间标签
     */
    public String getAwaitTimeTag() {
        return this.awaitTimeTag;
    }

    /**
     * 设置 等待时间标签
     *
     * @param awaitTimeTag 等待时间标签
     */
    public void setAwaitTimeTag(String awaitTimeTag) {
        this.awaitTimeTag = awaitTimeTag;
    }
}
