package com.oetsky.project.communication.mapper;

import java.util.List;
import com.oetsky.project.communication.domain.SysUnit;
import org.apache.ibatis.annotations.Param;

/**
 * 采集单元Mapper接口
 * 
 * @author xiangzc
 * @date 2023-02-08
 */
public interface SysUnitMapper 
{
    /**
     * 查询采集单元
     * 
     * @param id 采集单元主键
     * @return 采集单元
     */
    SysUnit selectSysUnitById(Long id);

    /**
     * 查询采集单元列表
     * 
     * @param sysUnit 采集单元
     * @return 采集单元集合
     */
    List<SysUnit> selectSysUnitList(SysUnit sysUnit);

    /**
     * 新增采集单元
     * 
     * @param sysUnit 采集单元
     * @return 结果
     */
    int insertSysUnit(SysUnit sysUnit);

    /**
     * 修改采集单元
     * 
     * @param sysUnit 采集单元
     * @return 结果
     */
    int updateSysUnit(SysUnit sysUnit);

    /**
     * 删除采集单元
     * 
     * @param id 采集单元主键
     * @return 结果
     */
    int deleteSysUnitById(Long id);

    /**
     * 批量删除采集单元
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    int deleteSysUnitByIds(String[] ids);

    /**
     * 批量新增采集单元
     *
     * @param sysUnitList 采集单元列表
     * @return 结果
     */
    int batchSysUnitList(List<SysUnit> sysUnitList);

    /**
     * 根据端口更新采集单元状态
     *
     * @param unitServiceProt 端口号
     * @param unitStatus 采集单元状态
     * @return 结果
     */
    int updateSysUnitProt(@Param("unitServiceProt")Integer unitServiceProt, @Param("unitStatus")Integer unitStatus);
}
