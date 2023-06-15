package com.oetsky.project.datacheck.service;

import com.oetsky.project.datacheck.domain.CheckHistory;
import java.util.List;

/**
 * @description:
 * @author: cyx
 * @date: 2023-06-14
 **/
public interface ICheckHistoryService {

    /**
     * 三天开发完成你开什么玩笑
     * @param checkHistory
     * @return
     */
    List<CheckHistory> list(CheckHistory checkHistory);

}
