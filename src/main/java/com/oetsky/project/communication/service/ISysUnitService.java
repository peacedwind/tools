package com.oetsky.project.communication.service;

import com.oetsky.project.communication.domain.SysUnit;
import java.util.List;

/**
 * 采集单元Service接口
 *
 * @author xiangzc
 * @date 2023-02-08
 */
public interface ISysUnitService {

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
     * 批量删除采集单元
     *
     * @param ids 需要删除的采集单元主键集合
     * @return 结果
     */
    int deleteSysUnitByIds(String ids);

    /**
     * 删除采集单元信息
     *
     * @param id 采集单元主键
     * @return 结果
     */
    int deleteSysUnitById(Long id);

    /**
     * 根据端口更新采集单元状态
     *
     * @param unitServiceProt 端口号
     * @param unitStatus      采集单元状态
     * @return 结果
     */
    int updateSysUnitProt(Integer unitServiceProt, Integer unitStatus);

    /**
     * 批量新增采集单元
     *
     * @param sysUnitList 采集单元列表
     * @return 结果
     */
    int batchSysUnitList(List<SysUnit> sysUnitList);


    /**
     * 打开采集单元通讯服务
     *
     * @param sysUnit
     * @return
     */
    Boolean openNettyService(SysUnit sysUnit);

    /**
     * 关闭采集单元
     *
     * @param port 端口号
     * @return
     */
    Boolean closeNettyService(Integer port);

    /**
     * @param sysUnitAndBoard 采集单元
     * @param frameStr        报文
     * @param localhostPort   本地端口
     * @param hostString      远程ip
     * @param port            远程 端口
     * @return 设置采集单元运行参数配置
     */
    String chreckSetResponseFrame(SysUnit sysUnitAndBoard, String frameStr,
        int localhostPort, String hostString, int port);
}
