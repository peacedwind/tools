package com.oetsky.project.util;


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
