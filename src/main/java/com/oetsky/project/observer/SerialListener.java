package com.oetsky.project.observer;

import com.oetsky.common.utils.text.Convert;
import java.util.concurrent.LinkedBlockingQueue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 观察者数据接收管理工具，
 * @author xiangzc
 * @date 2021-12-17
 * 新建类只需要继承该类，然后可以通过 getLinkedBlockingQueue 获取观察者收到的数据
 */
public class SerialListener implements SerialObserver {
    private static final Logger LOGGER = LoggerFactory.getLogger(SerialListener.class);

    public static LinkedBlockingQueue<String> serialFrameQueue = new LinkedBlockingQueue<>();

    public static LinkedBlockingQueue<String> getSerialFrameQueue() {
        return serialFrameQueue;
    }

    @Override
    public void response(Long longTime, Object msg) {
        try{
            serialFrameQueue.put(Convert.toStr(msg));
        }catch (Exception e){
            LOGGER.error("当前监听数据转换失败：【{}】【{}】",longTime,msg);
        }
    }
}
