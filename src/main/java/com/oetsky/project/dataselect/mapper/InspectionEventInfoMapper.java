package com.oetsky.project.dataselect.mapper;

import com.oetsky.project.dataselect.domain.InspectionEventInfo;
import java.util.Date;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 * 装置自诊断事件表(InspectionEventInfo)表数据库访问层
 *
 * @author makejava
 * @since 2022-10-17 15:01:28
 */
public interface InspectionEventInfoMapper {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    InspectionEventInfo queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param inspectionEventInfo 查询条件
     * @return 对象列表
     */
    List<InspectionEventInfo> list(InspectionEventInfo inspectionEventInfo);

    Integer selectMaxId();

    void insertBatch(@Param("entities") List<InspectionEventInfo> entities);

    Integer countDataByDate(@Param("start")Date start,@Param("end")Date end);

    List<InspectionEventInfo> listByCreateTime(@Param("start") Date start,@Param("end") Date end);
}

