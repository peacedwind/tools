package com.oetsky.project.datacheck.mapper;


import com.oetsky.project.datacheck.domain.DaVoltageData;
import java.util.List;

/**
 * 电压采集数据Mapper接口
 * 
 * @author xiangzc
 * @date 2023-06-13
 */
public interface VoltageDataMapper 
{
    /**
     * 查询电压采集数据
     * 
     * @param id 电压采集数据主键
     * @return 电压采集数据
     */
    DaVoltageData selectVoltageDataById(Long id);

    /**
     * 查询电压采集数据列表
     * 
     * @param daVoltageData 电压采集数据
     * @return 电压采集数据集合
     */
    List<DaVoltageData> selectVoltageDataList(DaVoltageData daVoltageData);

    /**
     * 新增电压采集数据
     * 
     * @param daVoltageData 电压采集数据
     * @return 结果
     */
    int insertVoltageData(DaVoltageData daVoltageData);

    /**
     * 修改电压采集数据
     * 
     * @param daVoltageData 电压采集数据
     * @return 结果
     */
    int updateVoltageData(DaVoltageData daVoltageData);

    /**
     * 删除电压采集数据
     * 
     * @param id 电压采集数据主键
     * @return 结果
     */
    int deleteVoltageDataById(Long id);

    /**
     * 批量删除电压采集数据
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    int deleteVoltageDataByIds(String[] ids);

    /**
     * 批量新增电压采集数据
     *
     * @param daVoltageDataList 电压采集数据列表
     * @return 结果
     */
    int batchVoltageDataList(List<DaVoltageData> daVoltageDataList);
}
