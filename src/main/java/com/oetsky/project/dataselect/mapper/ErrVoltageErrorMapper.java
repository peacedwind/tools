package com.oetsky.project.dataselect.mapper;

import cn.hutool.core.date.DateTime;
import com.oetsky.project.dataselect.domain.ErrVoltageError;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;

/**
 * 电压互感器误差数据Mapper接口
 *
 * @author huwm
 * @date 2022-03-09
 */
public interface ErrVoltageErrorMapper {

    /**
     * 查询电压互感器误差数据列表
     *
     * @param errVoltageError 电压互感器误差数据
     * @return 电压互感器误差数据集合
     */
    List<ErrVoltageError> selectErrVoltageErrorList(ErrVoltageError errVoltageError);

    /**
     * 查询响应终端的计算数据
     *
     * @param measureTime
     * @param channelNum
     * @return
     */
    List<ErrVoltageError> selectVoltageCalculateByRecentlyList(@Param("measureTime") Date measureTime, @Param("channelNum") Integer channelNum);

    /**
     *
     * @param map
     * @return
     */
    ErrVoltageError selectVoltageCalculateByRecently(Map<String, Object> map);
    /**
     * 批量新增电压互感器误差数据
     *
     * @param errVoltageErrorList 电压互感器误差数据列表
     * @return 结果
     */
    int batchErrVoltageErrorList(List<ErrVoltageError> errVoltageErrorList);

    Integer countDataByDate(@Param("start") Date start,@Param("end") Date end);
}
