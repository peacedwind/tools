package com.oetsky.project.datacheck.mapper;

import com.oetsky.project.datacheck.domain.CheckHistory;
import java.util.List;

/**
 * @description:
 * @author: cyx
 * @date: 2023-06-14
 **/
public interface CheckHistoryMapper {

    /**
     * list
     * @param checkHistory
     * @return
     */
    List<CheckHistory> list(CheckHistory checkHistory);


    /**
     * 查询单个
     * @param id
     * @return
     */
    CheckHistory selectById(Integer id);

    void save(CheckHistory checkHistory);


    void updateById(CheckHistory checkHistory);

}
