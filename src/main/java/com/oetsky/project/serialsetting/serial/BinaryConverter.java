package com.oetsky.project.serialsetting.serial;

import java.io.InputStream;

/**
 * @author zhangw
 */
public interface BinaryConverter {
    /**
     *  组帧接口
     * @param inputStream
     * @return
     */
    Object convert(InputStream inputStream);
}
