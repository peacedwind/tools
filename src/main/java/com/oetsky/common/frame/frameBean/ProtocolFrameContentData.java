package com.oetsky.common.frame.frameBean;

/**
 * 数据域
 */
public class ProtocolFrameContentData {
    /**
     * 报文帧ID
     */
    private String frameId;
    /**
     * 类型标识
     */
    private String frameDataType;
    /**
     * 数据内容
     */
    private String frameData;
    /**
     * 数据内容对象
     */
    private Object obj;

    public String getFrameId() {
        return frameId;
    }

    public void setFrameId(String frameId) {
        this.frameId = frameId;
    }

    public String getFrameDataType() {
        return frameDataType;
    }

    public void setFrameDataType(String frameDataType) {
        this.frameDataType = frameDataType;
    }

    public String getFrameData() {
        return frameData;
    }

    public void setFrameData(String frameData) {
        this.frameData = frameData;
    }

    public Object getObj() {
        return obj;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }
}
