package com.oetsky.common.frame.utils;

/**
 * @description:
 * @author: cyx
 * @date: 2022-08-29
 **/
public class LogMsgUtil {
    /**
     * 处理日志
     * @param msg
     * @return
     */
    public static String getSecMsg(Object msg) {
        if (null != msg) {
            return String.valueOf(msg).replace('\n', '-').replace('\r', '_');
        }

        return null;
    }
}
