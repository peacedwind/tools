package com.oetsky.project.communication.mapper;

import java.util.List;
import com.oetsky.project.communication.domain.SysCalibrationBoard;
import org.apache.ibatis.annotations.Param;

/**
 * 板卡校正系数信息Mapper接口
 * 
 * @author xiangzc
 * @date 2023-02-08
 */
public interface SysCalibrationBoardMapper 
{
    /**
     * 查询板卡校正系数信息
     * 
     * @param id 板卡校正系数信息主键
     * @return 板卡校正系数信息
     */
    SysCalibrationBoard selectSysCalibrationBoardById(Long id);

    /**
     * 查询板卡校正系数信息列表
     * 
     * @param sysCalibrationBoard 板卡校正系数信息
     * @return 板卡校正系数信息集合
     */
    List<SysCalibrationBoard> selectSysCalibrationBoardList(SysCalibrationBoard sysCalibrationBoard);

    /**
     * 新增板卡校正系数信息
     * 
     * @param sysCalibrationBoard 板卡校正系数信息
     * @return 结果
     */
    int insertSysCalibrationBoard(SysCalibrationBoard sysCalibrationBoard);

    /**
     * 修改板卡校正系数信息
     * 
     * @param sysCalibrationBoard 板卡校正系数信息
     * @return 结果
     */
    int updateSysCalibrationBoard(SysCalibrationBoard sysCalibrationBoard);

    /**
     * 删除板卡校正系数信息
     * 
     * @param id 板卡校正系数信息主键
     * @return 结果
     */
    int deleteSysCalibrationBoardById(Long id);
    /**
     * 删除板卡校正系数信息
     *
     * @param unitId 采集单元ID
     * @param boardNum 采集板卡号
     * @return 结果
     */
    int deleteCalibrationByUnitBoardNum(@Param("unitId") Long unitId,@Param("boardNum") Integer boardNum);

    /**
     * 批量删除板卡校正系数信息
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    int deleteSysCalibrationBoardByIds(String[] ids);

    /**
     * 批量新增板卡校正系数信息
     *
     * @param sysCalibrationBoardList 板卡校正系数信息列表
     * @return 结果
     */
    int batchSysCalibrationBoardList(List<SysCalibrationBoard> sysCalibrationBoardList);
}
