package com.oetsky.project.communication.service;

import com.oetsky.project.communication.domain.SysUnit;
import java.util.List;
import com.oetsky.project.communication.domain.SysCalibrationBoard;

/**
 * 板卡校正系数信息Service接口
 * 
 * @author xiangzc
 * @date 2023-02-08
 */
public interface ISysCalibrationBoardService 
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
     * 批量删除板卡校正系数信息
     * 
     * @param ids 需要删除的板卡校正系数信息主键集合
     * @return 结果
     */
    int deleteSysCalibrationBoardByIds(String ids);

    /**
     * 删除板卡校正系数信息信息
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
    int deleteCalibrationByUnitBoardNum( Long unitId, Integer boardNum);

    /**
     * 批量新增板卡校正系数信息
     *
     * @param sysCalibrationBoardList 板卡校正系数信息列表
     * @return 结果
     */
    int batchSysCalibrationBoardList(List<SysCalibrationBoard> sysCalibrationBoardList);

    /**
     * @param sysUnitAndBoard 采集单元
     * @param frameStr        报文
     * @param localhostPort   本地端口
     * @param hostString      远程ip
     * @param port            远程 端口
     * @return
     */
    String checkSetResponseFrame(SysUnit sysUnitAndBoard, String frameStr, int localhostPort,
        String hostString, int port);

    /**
     * @param sysUnitAndBoard 采集单元
     * @param frameStr        报文
     * @param localhostPort   本地端口
     * @param hostString      远程ip
     * @param port            远程 端口
     * @return 读取采集板校正系数
     */
     String checkReadResponseFrame(SysUnit sysUnitAndBoard, String frameStr, int localhostPort,
        String hostString, int port);
}
