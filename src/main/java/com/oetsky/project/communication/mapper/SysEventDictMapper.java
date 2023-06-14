package com.oetsky.project.communication.mapper;

import java.util.List;
import com.oetsky.project.communication.domain.SysEventDict;

/**
 * 事件数据字典Mapper接口
 * 
 * @author xiangzc
 * @date 2023-02-08
 */
public interface SysEventDictMapper 
{
    /**
     * 查询事件数据字典
     * 
     * @param id 事件数据字典主键
     * @return 事件数据字典
     */
    SysEventDict selectSysEventDictById(Long id);

    /**
     * 查询事件数据字典列表
     * 
     * @param sysEventDict 事件数据字典
     * @return 事件数据字典集合
     */
    List<SysEventDict> selectSysEventDictList(SysEventDict sysEventDict);

    /**
     * 新增事件数据字典
     * 
     * @param sysEventDict 事件数据字典
     * @return 结果
     */
    int insertSysEventDict(SysEventDict sysEventDict);

    /**
     * 修改事件数据字典
     * 
     * @param sysEventDict 事件数据字典
     * @return 结果
     */
    int updateSysEventDict(SysEventDict sysEventDict);

    /**
     * 删除事件数据字典
     * 
     * @param id 事件数据字典主键
     * @return 结果
     */
    int deleteSysEventDictById(Long id);

    /**
     * 批量删除事件数据字典
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    int deleteSysEventDictByIds(String[] ids);

    /**
     * 批量新增事件数据字典
     *
     * @param sysEventDictList 事件数据字典列表
     * @return 结果
     */
    int batchSysEventDictList(List<SysEventDict> sysEventDictList);
}
