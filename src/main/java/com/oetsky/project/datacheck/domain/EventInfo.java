package com.oetsky.project.datacheck.domain;

import com.oetsky.framework.aspectj.lang.annotation.Excel;
import java.util.Date;

/**
 * @description: 事件异常信息
 * @author: cyx
 * @date: 2023-06-16
 **/
public class EventInfo implements Comparable<EventInfo>{

    /**
     * 序号
     */
    @Excel(name = "序号")
    private Integer id;

    /**
     * 异常信息
     */
    @Excel(name = "错误信息")
    private String errInfo;



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getErrInfo() {
        return errInfo;
    }

    public void setErrInfo(String errInfo) {
        this.errInfo = errInfo;
    }

    @Override
    public int compareTo(EventInfo o) {
        return this.id.compareTo(o.getId());
    }

}
