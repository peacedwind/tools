package com.oetsky.project.datacheck.service;

import com.oetsky.project.datacheck.domain.CheckHistory;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;

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

    void importCheck(Integer checkType, MultipartFile file) throws Exception;

    CheckHistory selectById(Integer id);
}
