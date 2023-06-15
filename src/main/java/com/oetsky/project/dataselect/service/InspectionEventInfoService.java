package com.oetsky.project.dataselect.service;


import com.oetsky.project.dataselect.domain.InspectionEventInfo;
import java.util.List;

/**
 * 装置自诊断事件表(InspectionEventInfo)表服务接口
 *
 * @author makejava
 * @since 2022-10-17 15:01:31
 */
public interface InspectionEventInfoService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    InspectionEventInfo queryById(Integer id);

    /**
     * 分页查询
     *
     * @param inspectionEventInfo 筛选条件
     * @return 查询结果
     */
    List<InspectionEventInfo> list(InspectionEventInfo inspectionEventInfo);


}