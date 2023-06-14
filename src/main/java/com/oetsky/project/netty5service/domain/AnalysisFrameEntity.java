package com.oetsky.project.netty5service.domain;

import lombok.Data;

@Data
public class AnalysisFrameEntity {

    public Integer id;

    public Integer parentId;

    /**
     * 帧域
     */
    public String frameArea;

    /**
     * 帧域说明
     */
    public String frameRemark;
    /**
     * 帧数据
     */
    public String frameCount;
    /***
     * 数据值
     */
    public String frameValue;
    /**
     * 备注
     */
    public String remark;
}
