package com.oetsky.project.dataselect.service.impl;


import com.oetsky.project.dataselect.domain.InspectionEventInfo;
import com.oetsky.project.dataselect.mapper.InspectionEventInfoMapper;
import com.oetsky.project.dataselect.service.InspectionEventInfoService;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * 装置自诊断事件表(InspectionEventInfo)表服务实现类
 *
 * @author makejava
 * @since 2022-10-17 15:01:32
 */
@Service
public class InspectionEventInfoServiceImpl implements InspectionEventInfoService {


    private Logger LOGGER = LoggerFactory.getLogger(InspectionEventInfoServiceImpl.class);


    @Resource
    private InspectionEventInfoMapper inspectionEventInfoMapper;
        /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public InspectionEventInfo queryById(Integer id) {
        return this.inspectionEventInfoMapper.queryById(id);
    }

    /**
     * 分页查询
     *
     * @param inspectionEventInfo 筛选条件
     * @return 查询结果
     */
    @Override
    public List<InspectionEventInfo> list(InspectionEventInfo inspectionEventInfo) {
        return this.inspectionEventInfoMapper.list(inspectionEventInfo);
    }

}
