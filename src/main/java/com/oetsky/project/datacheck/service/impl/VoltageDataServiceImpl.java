package com.oetsky.project.datacheck.service.impl;

import com.oetsky.common.utils.DateUtils;
import com.oetsky.common.utils.StringUtils;
import com.oetsky.common.utils.text.Convert;
import com.oetsky.project.datacheck.domain.DaVoltageData;
import com.oetsky.project.datacheck.mapper.VoltageDataMapper;
import com.oetsky.project.datacheck.service.IVoltageDataService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * 电压采集数据Service业务层处理
 * 
 * @author xiangzc
 * @date 2023-06-13
 */
@Service
public class VoltageDataServiceImpl implements IVoltageDataService
{
    @Autowired
    private VoltageDataMapper voltageDataMapper;

    /**
     * 查询电压采集数据
     * 
     * @param id 电压采集数据主键
     * @return 电压采集数据
     */
    @Override
    public DaVoltageData selectVoltageDataById(Long id)
    {
        return voltageDataMapper.selectVoltageDataById(id);
    }

    /**
     * 查询电压采集数据列表
     * 
     * @param daVoltageData 电压采集数据
     * @return 电压采集数据
     */
    @Override
    public List<DaVoltageData> selectVoltageDataList(DaVoltageData daVoltageData)
    {
        return voltageDataMapper.selectVoltageDataList(daVoltageData);
    }

    /**
     * 新增电压采集数据
     * 
     * @param daVoltageData 电压采集数据
     * @return 结果
     */
    @Override
    public int insertVoltageData(DaVoltageData daVoltageData)
    {
    if( daVoltageData.getCreateTime() == null){
        daVoltageData.setCreateTime(DateUtils.getNowDate());
}
        return voltageDataMapper.insertVoltageData(daVoltageData);
    }

    /**
     * 修改电压采集数据
     * 
     * @param daVoltageData 电压采集数据
     * @return 结果
     */
    @Override
    public int updateVoltageData(DaVoltageData daVoltageData)
    {
        return voltageDataMapper.updateVoltageData(daVoltageData);
    }

    /**
     * 批量删除电压采集数据
     * 
     * @param ids 需要删除的电压采集数据主键
     * @return 结果
     */
    @Override
    public int deleteVoltageDataByIds(String ids)
    {
        return voltageDataMapper.deleteVoltageDataByIds(Convert.toStrArray(ids));
    }

    /**
     * 批量新增voltageData信息
     *
     * @param daVoltageDataList 电压采集数据列表
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int batchVoltageDataList(List<DaVoltageData> daVoltageDataList)
    {
        int result = 0;
        if (StringUtils.isNotNull(daVoltageDataList))
        {
            List<DaVoltageData> list = new ArrayList<DaVoltageData>();
            for (DaVoltageData daVoltageData : daVoltageDataList)
            {
                        if( daVoltageData.getCreateTime() == null){
                        daVoltageData.setCreateTime(DateUtils.getNowDate());
                    }
                list.add(daVoltageData);
                if (list.size() >= 500)
                {
                        voltageDataMapper.batchVoltageDataList(list);
                    list.clear();
                    result = 1;
                }
            }
            if (list.size() > 0)
            {
                    voltageDataMapper.batchVoltageDataList(list);
                list.clear();
                result = 1;
            }
        }
        return result;
    }

    /**
     * 删除电压采集数据信息
     * 
     * @param id 电压采集数据主键
     * @return 结果
     */
    @Override
    public int deleteVoltageDataById(Long id)
    {
        return voltageDataMapper.deleteVoltageDataById(id);
    }
}
