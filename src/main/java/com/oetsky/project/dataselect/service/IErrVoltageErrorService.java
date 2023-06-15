package com.oetsky.project.dataselect.service;

import com.oetsky.project.dataselect.domain.ErrVoltageError;
import java.util.List;

/**
 * 电压互感器误差数据Service接口
 *
 * @author huwm
 * @date 2022-03-09
 */
public interface IErrVoltageErrorService {

    /**
     * 查询电压互感器误差数据列表
     *
     * @param errVoltageError 电压互感器误差数据
     * @return 电压互感器误差数据集合
     */
    List<ErrVoltageError> selectErrVoltageErrorList(ErrVoltageError errVoltageError);


}
