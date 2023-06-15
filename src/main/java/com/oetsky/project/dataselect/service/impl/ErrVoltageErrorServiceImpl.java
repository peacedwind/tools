package com.oetsky.project.dataselect.service.impl;

import com.oetsky.project.dataselect.domain.ErrVoltageError;
import com.oetsky.project.dataselect.mapper.ErrVoltageErrorMapper;
import com.oetsky.project.dataselect.service.IErrVoltageErrorService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 电压互感器误差数据Service业务层处理
 *
 * @author huwm
 * @date 2022-03-09
 */
@Service
public class ErrVoltageErrorServiceImpl implements IErrVoltageErrorService {


    @Autowired
    private ErrVoltageErrorMapper errVoltageErrorMapper;

    /**
     * 查询电压互感器误差数据列表
     *
     * @param errVoltageError 电压互感器误差数据
     * @return 电压互感器误差数据
     */
    @Override
    public List<ErrVoltageError> selectErrVoltageErrorList(ErrVoltageError errVoltageError) {
        List<ErrVoltageError> errVoltageErrors = errVoltageErrorMapper.selectErrVoltageErrorList(
            errVoltageError);
        return errVoltageErrors;
    }

}
