package com.oetsky.project.datacheck.domain;

import com.oetsky.framework.aspectj.lang.annotation.Excel;
import java.util.Date;

/**
 * @description: 采集数据异常信息
 * @author: cyx
 * @date: 2023-06-16
 **/
public class VoltageErrorInfo implements Comparable<VoltageErrorInfo>{

    /**
     * 采集时间
     */
    @Excel(name = "采集时间")
    private Date collectionTime;

    /**
     * 通道
     */
    @Excel(name = "采集通道")
    private Integer channelNum;

    /**
     * 异常信息
     */
    @Excel(name = "异常信息")
    private String errInfo;

    public Date getCollectionTime() {
        return collectionTime;
    }

    public void setCollectionTime(Date collectionTime) {
        this.collectionTime = collectionTime;
    }

    public Integer getChannelNum() {
        return channelNum;
    }

    public void setChannelNum(Integer channelNum) {
        this.channelNum = channelNum;
    }

    public String getErrInfo() {
        return errInfo;
    }

    public void setErrInfo(String errInfo) {
        this.errInfo = errInfo;
    }

    @Override
    public int compareTo(VoltageErrorInfo o) {
        if (this.collectionTime != null && o.collectionTime != null){
            return collectionTime.compareTo(o.collectionTime);
        }
        if (this.channelNum != null && o.channelNum != null){
            return channelNum.compareTo(o.channelNum);
        }
        if (this.collectionTime == null){
            return 1;
        }
        if (this.channelNum == null){
            return 1;
        }
        return 0;

    }
}
