package com.oetsky.project.datacheck.service.impl;

import com.oetsky.project.datacheck.domain.CheckHistory;
import com.oetsky.project.datacheck.mapper.CheckHistoryMapper;
import com.oetsky.project.datacheck.service.ICheckHistoryService;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Component;

/**
 * @description:
 * @author: cyx
 * @date: 2023-06-14
 **/
@Component
public class CheckHistoryServiceImpl implements ICheckHistoryService {

    @Resource
    private CheckHistoryMapper checkHistoryMapper;

    @Override
    public List<CheckHistory> list(CheckHistory checkHistory) {
        return checkHistoryMapper.list(checkHistory);
    }
}
