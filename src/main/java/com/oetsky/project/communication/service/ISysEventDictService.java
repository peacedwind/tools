package com.oetsky.project.communication.service;

import com.oetsky.project.communication.domain.SysEventDict;
import com.oetsky.project.communication.domain.SysUnit;
import java.util.List;

/**
 * 事件数据字典Service接口
 *
 * @author xiangzc
 * @date 2023-02-08
 */
public interface ISysEventDictService {

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
     * 批量删除事件数据字典
     *
     * @param ids 需要删除的事件数据字典主键集合
     * @return 结果
     */
    int deleteSysEventDictByIds(String ids);

    /**
     * 删除事件数据字典信息
     *
     * @param id 事件数据字典主键
     * @return 结果
     */
    int deleteSysEventDictById(Long id);

    /**
     * 批量新增事件数据字典
     *
     * @param sysEventDictList 事件数据字典列表
     * @return 结果
     */
    int batchSysEventDictList(List<SysEventDict> sysEventDictList);

    /**
     * 发送事件消息
     *
     * @param sysUnit     事件采集单元
     * @param contextKeys 建立通道消息key
     * @param protectKeys 建立采集单元服务key
     * @param frameStr    消息报文
     */
    void sendEventMsg(SysUnit sysUnit, String contextKeys, String protectKeys, String frameStr);
}
