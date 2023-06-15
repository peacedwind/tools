package com.oetsky.project.dataselect.mapper;


import cn.hutool.core.date.DateTime;
import com.oetsky.project.dataselect.domain.DaVoltageData;
import java.util.Date;
import java.util.List;
import org.apache.ibatis.annotations.Param;

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

    void batchDaVoltageDataList(List<DaVoltageData> daVoltageData);

    Integer countDataByDate(@Param("start") Date start, @Param("end")Date end);
}
