package com.oetsky.project.dataselect.service;


import com.oetsky.project.dataselect.domain.DaVoltageData;
import java.util.Date;
import java.util.List;

/**
 * 电压互感器采集数据Service接口
 * 
 * @author hanxz
 * @date 2022-02-24
 */
public interface IDaVoltageDataService {
    /**
     * 查询电压互感器采集数据
     * 
     * @param id 电压互感器采集数据主键
     * @return 电压互感器采集数据
     */
    DaVoltageData selectDaVoltageDataById(Long id);

    /**
     * 查询电压互感器采集数据列表
     * 
     * @param daVoltageData 电压互感器采集数据
     * @return 电压互感器采集数据集合
     */
    List<DaVoltageData> selectDaVoltageDataList(DaVoltageData daVoltageData);


    /**
     * 判断当天是否有数据
     * @param date
     * @return
     */
    boolean existsDate(Date date);

    /**
     * 生成当天的数据
     *
     * @param date 日期
     * @return
     */
    List<DaVoltageData> createDataByDate(Date date);

    /**
     * 批量保存
     * @param dataList 数据
     */
    void batchDaVoltageDataList(List<DaVoltageData> dataList);

    /**
     * 生成数据
     */
    void createData();

}
