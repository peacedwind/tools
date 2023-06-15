package com.oetsky.project.dataselect.service.impl;
import com.google.common.collect.Maps;

import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.RandomUtil;
import com.oetsky.common.utils.DateUtils;
import com.oetsky.common.utils.StringUtils;
import com.oetsky.project.dataselect.domain.DaVoltageData;
import com.oetsky.project.dataselect.domain.ErrVoltageError;
import com.oetsky.project.dataselect.mapper.ErrVoltageErrorMapper;
import com.oetsky.project.dataselect.service.IErrVoltageErrorService;
import com.oetsky.project.enums.ErrorStatusEnum;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
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

    @Override
    public List<ErrVoltageError> createErrVoltageError(Date date) {
        if (date == null){
            date = new Date();
        }
        List<ErrVoltageError> errVoltageErrors = new ArrayList<>();
        Date calculateTime = DateUtil.beginOfDay(date).offset(DateField.HOUR, 23)
            .offset(DateField.MINUTE, 59);
        //12条线路
        for (int i = 0; i < 12; i++) {
            //状态字
            int calculate = ErrorStatusEnum.randomStatus();
            ErrVoltageError errVoltageError = getErrVoltage(i,calculateTime,calculate);
            errVoltageErrors.add(errVoltageError);
        }
        return errVoltageErrors;
    }

    @Override
    public int batchErrVoltageErrorList(List<ErrVoltageError> errVoltageErrorList) {
        int result = 0;
        if (StringUtils.isNotNull(errVoltageErrorList)) {
            List<ErrVoltageError> list = new ArrayList<ErrVoltageError>();
            for (ErrVoltageError errVoltageError : errVoltageErrorList) {
                if (errVoltageError.getCreateTime() == null) {
                    errVoltageError.setCreateTime(DateUtils.getNowDate());
                }
                list.add(errVoltageError);
                if (list.size() >= 500) {
                    errVoltageErrorMapper.batchErrVoltageErrorList(list);
                    list.clear();
                    result = 1;
                }
            }
            if (list.size() > 0) {
                errVoltageErrorMapper.batchErrVoltageErrorList(list);
                list.clear();
                result = 1;
            }
        }
        return result;
    }

    private ErrVoltageError getErrVoltage(int channelNum, Date calculateTime, int calculate) {
        ErrVoltageError errVoltageError = new ErrVoltageError();
        errVoltageError.setChannelNum(channelNum);
        errVoltageError.setCalculateTime(calculateTime);
        if (calculate == 0){
            errVoltageError.setAaRatioError(getRandomRatio());
            errVoltageError.setBbRatioError(getRandomRatio());
            errVoltageError.setCcRatioError(getRandomRatio());
            errVoltageError.setAaAngleError(getRandomAngle());
            errVoltageError.setBbAngleError(getRandomAngle());
            errVoltageError.setCcAngleError(getRandomAngle());
        }else {
            errVoltageError.setAaRatioError(BigDecimal.ZERO);
            errVoltageError.setBbRatioError(BigDecimal.ZERO);
            errVoltageError.setCcRatioError(BigDecimal.ZERO);
            errVoltageError.setAaAngleError(BigDecimal.ZERO);
            errVoltageError.setBbAngleError(BigDecimal.ZERO);
            errVoltageError.setCcAngleError(BigDecimal.ZERO);
        }
        errVoltageError.setAaAngleVariance(BigDecimal.ZERO);
        errVoltageError.setBbAngleVariance(BigDecimal.ZERO);
        errVoltageError.setCcAngleVariance(BigDecimal.ZERO);
        errVoltageError.setDataStartTime(calculateTime);
        errVoltageError.setDataEndTime(calculateTime);
        errVoltageError.setUbNew("");
        errVoltageError.setInputub("");
        errVoltageError.setChannelLevel(1);
        errVoltageError.setCalculateStatus(String.valueOf(calculate));
        errVoltageError.setCalculateMsg("");
        errVoltageError.setRatioVarargin("");
        errVoltageError.setAngleVarargin("");
        errVoltageError.setSearchValue("");
        errVoltageError.setCreateBy("");
        errVoltageError.setCreateTime(calculateTime);
        errVoltageError.setUpdateBy("");
        errVoltageError.setUpdateTime(calculateTime);
        return errVoltageError;
    }

    private static final BigDecimal B9_9 = new BigDecimal(9.9);

    private static final BigDecimal B99 = new BigDecimal(99);

    private static final BigDecimal B100 = new BigDecimal(100);
    private BigDecimal getRandomRatio(){
        BigDecimal res = RandomUtil.randomBigDecimal(B9_9, BigDecimal.TEN);
        if (RandomUtil.randomBoolean()) {
            res = res.negate();
        }
        return res;
    }

    private BigDecimal getRandomAngle(){
        BigDecimal res = RandomUtil.randomBigDecimal(B99, B100);
        if (RandomUtil.randomBoolean()) {
            res = res.negate();
        }
        return res;
    }


    @Override
    public void createData() {
        Date date = DateUtil.beginOfDay(new Date());
        //判断当天是否数据
        boolean todayFlag =  this.existsDataByDate(date);
        if (!todayFlag){
            //不存在
            List<ErrVoltageError> dataList = this.createErrVoltageError(date);
            this.batchErrVoltageErrorList(dataList);
        }
        //判断下一天
        boolean lastDayFlag =  this.existsDataByDate(DateUtil.offsetDay(date,-1));
        if (!lastDayFlag){
            List<ErrVoltageError> dataList = this.createErrVoltageError(DateUtil.offsetDay(date,-1));
            this.batchErrVoltageErrorList(dataList);
        }
    }

    private boolean existsDataByDate(Date date) {
        Integer count = this.errVoltageErrorMapper.countDataByDate(date,DateUtil.offsetDay(date,1));
        return count != null && count > 0;
    }
}
