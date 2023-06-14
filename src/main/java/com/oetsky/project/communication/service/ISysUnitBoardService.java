package com.oetsky.project.communication.service;

import java.util.List;
import com.oetsky.project.communication.domain.SysUnitBoard;

/**
 * 采集板卡信息Service接口
 * 
 * @author xiangzc
 * @date 2023-02-08
 */
public interface ISysUnitBoardService 
{
    /**
     * 查询采集板卡信息
     * 
     * @param id 采集板卡信息主键
     * @return 采集板卡信息
     */
    SysUnitBoard selectSysUnitBoardById(Long id);

    /**
     * 查询采集板卡信息列表
     * 
     * @param sysUnitBoard 采集板卡信息
     * @return 采集板卡信息集合
     */
    List<SysUnitBoard> selectSysUnitBoardList(SysUnitBoard sysUnitBoard);

    /**
     * 新增采集板卡信息
     * 
     * @param sysUnitBoard 采集板卡信息
     * @return 结果
     */
    int insertSysUnitBoard(SysUnitBoard sysUnitBoard);

    /**
     * 修改采集板卡信息
     * 
     * @param sysUnitBoard 采集板卡信息
     * @return 结果
     */
    int updateSysUnitBoard(SysUnitBoard sysUnitBoard);

    /**
     * 批量删除采集板卡信息
     * 
     * @param ids 需要删除的采集板卡信息主键集合
     * @return 结果
     */
    int deleteSysUnitBoardByIds(String ids);

    /**
     * 删除采集板卡信息信息
     * 
     * @param id 采集板卡信息主键
     * @return 结果
     */
    int deleteSysUnitBoardById(Long id);

    /**
     * 批量新增采集板卡信息
     *
     * @param sysUnitBoardList 采集板卡信息列表
     * @return 结果
     */
    int batchSysUnitBoardList(List<SysUnitBoard> sysUnitBoardList);
}
