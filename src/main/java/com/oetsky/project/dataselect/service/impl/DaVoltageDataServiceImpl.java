package com.oetsky.project.dataselect.service.impl;

import com.oetsky.project.dataselect.domain.DaVoltageData;
import com.oetsky.project.dataselect.mapper.DaVoltageDataMapper;
import com.oetsky.project.dataselect.service.IDaVoltageDataService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 电压互感器采集数据Service业务层处理
 *
 * @author hanxz
 * @date 2022-02-24
 */
@Service
public class DaVoltageDataServiceImpl implements IDaVoltageDataService {
    @Autowired
    private DaVoltageDataMapper daVoltageDataMapper;

    /**
     * 查询电压互感器采集数据
     *
     * @param id 电压互感器采集数据主键
     * @return 电压互感器采集数据
     */
    @Override
    public DaVoltageData selectDaVoltageDataById(Long id) {
        return daVoltageDataMapper.selectDaVoltageDataById(id);
    }

    /**
     * 查询电压互感器采集数据列表
     *
     * @param daVoltageData 电压互感器采集数据
     * @return 电压互感器采集数据
     */
    @Override
    public List<DaVoltageData> selectDaVoltageDataList(DaVoltageData daVoltageData) {
        return daVoltageDataMapper.selectDaVoltageDataList(daVoltageData);
    }

}
