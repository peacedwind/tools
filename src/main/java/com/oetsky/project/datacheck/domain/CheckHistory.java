package com.oetsky.project.datacheck.domain;

import com.oetsky.project.enums.CheckResultEnum;
import com.oetsky.project.enums.CheckStatusEnum;
import java.util.Date;

/**
 * @description: 校验历史
 * @author: cyx
 * @date: 2023-06-14
 **/
public class CheckHistory {

    private Integer id;

    private String importFileName;

    private String importFileUrl;

    private Date createTime;

    private Integer checkStatus;

    private Integer checkResult;

    private String resultFileUrl;

    private String resultFileName;

    private Date createTimeStart;

    private Date createTimeEnd;

    public Date getCreateTimeStart() {
        return createTimeStart;
    }

    public void setCreateTimeStart(Date createTimeStart) {
        this.createTimeStart = createTimeStart;
    }

    public Date getCreateTimeEnd() {
        return createTimeEnd;
    }

    public void setCreateTimeEnd(Date createTimeEnd) {
        this.createTimeEnd = createTimeEnd;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getImportFileName() {
        return importFileName;
    }

    public void setImportFileName(String importFileName) {
        this.importFileName = importFileName;
    }


    public String getImportFileUrl() {
        return importFileUrl;
    }

    public void setImportFileUrl(String importFileUrl) {
        this.importFileUrl = importFileUrl;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getCheckStatus() {
        return checkStatus;
    }

    public void setCheckStatus(Integer checkStatus) {
        this.checkStatus = checkStatus;
    }

    public Integer getCheckResult() {
        return checkResult;
    }

    public void setCheckResult(Integer checkResult) {
        this.checkResult = checkResult;
    }

    public String getResultFileUrl() {
        return resultFileUrl;
    }

    public void setResultFileUrl(String resultFileUrl) {
        this.resultFileUrl = resultFileUrl;
    }

    public String getResultFileName() {
        return resultFileName;
    }

    public void setResultFileName(String resultFileName) {
        this.resultFileName = resultFileName;
    }

    public void defaultValue(){
        setCreateTime(new Date());
        setCheckStatus(CheckStatusEnum.ING.getCode());
        setCheckResult(CheckResultEnum.OK.getCode());
    }
}
