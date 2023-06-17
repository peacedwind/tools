package com.oetsky.project.serialsetting.serial;

/**
 * @author zhangw
 */
public interface SerialMessageHandle {
    /**
     * 解析接口
     * @param message
     * @param serialHelper
     */
    void handle(Object message, SerialHelper serialHelper);
}
