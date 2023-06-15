package com.oetsky.project.dataselect.mapper;


import com.oetsky.project.dataselect.domain.DaVoltageData;
import java.util.List;

/**
 * 电压互感器采集数据Mapper接口
 * 
 * @author hanxz
 * @date 2022-02-24
 */
public interface DaVoltageDataMapper {


    /**
     * 查询电压互感器采集数据列表
     * 
     * @param daVoltageData 电压互感器采集数据
     * @return 电压互感器采集数据集合
     */
    List<DaVoltageData> selectDaVoltageDataList(DaVoltageData daVoltageData);

    DaVoltageData selectDaVoltageDataById(Long id);
}
