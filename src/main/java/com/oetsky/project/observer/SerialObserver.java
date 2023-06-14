package com.oetsky.project.observer;

import java.util.Map;
import java.util.concurrent.LinkedBlockingQueue;

public interface SerialObserver {
    LinkedBlockingQueue<Map<Long, Object>> linkedBlockingQueue = new LinkedBlockingQueue<>();

    //反应
    void response(Long longTime, Object msg);
}
