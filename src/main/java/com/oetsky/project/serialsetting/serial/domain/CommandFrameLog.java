package com.oetsky.project.serialsetting.serial.domain;


/**
 * @author: huwm
 * @date: 2019/12/19
 */
public class CommandFrameLog {

    /**
     * 日期
     */
    private String date ;

    /**
     * 通道
     */
    private String channelNum ;

    private String phase;

    public Integer getChannel() {
        return channel;
    }

    public void setChannel(Integer channel) {
        this.channel = channel;
    }

    private Integer channel ;

    public String getPhase() {
        return phase;
    }

    public void setPhase(String phase) {
        this.phase = phase;
    }
    /**
     * 获取 日期
     *
     * @return date 日期
     */
    public String getDate() {
        return this.date;
    }

    /**
     * 设置 日期
     *
     * @param date 日期
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * 获取 通道
     *
     * @return channelNum 通道
     */
    public String getChannelNum() {
        return this.channelNum;
    }

    /**
     * 设置 通道
     *
     * @param channelNum 通道
     */
    public void setChannelNum(String channelNum) {
        this.channelNum = channelNum;
    }
}
