package com.oetsky.project.dataselect.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import com.oetsky.common.frame.utils.IntegerUtil;
import com.oetsky.common.utils.StringUtils;
import com.oetsky.project.dataselect.domain.DaVoltageData;
import cn.hutool.core.date.DateField;
import cn.hutool.core.util.RandomUtil;
import com.oetsky.common.utils.DateUtils;
import com.oetsky.project.dataselect.domain.ErrVoltageError;
import com.oetsky.project.dataselect.mapper.ErrVoltageErrorMapper;
import com.oetsky.project.dataselect.service.IErrVoltageErrorService;

import java.util.Date;
import java.util.HashMap;
import com.oetsky.project.enums.ErrorStatusEnum;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.oetsky.project.serialsetting.serial.utils.DLT698Utils;
import com.oetsky.project.serialsetting.serial.utils.FrameNotData;
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
    /**
     * 查询响应终端的计算数据
     *
     * @param voltageSampleList
     * @param measureTime
     * @param channelNum
     * @return
     */
    @Override
    public Map<Integer, ErrVoltageError> selectVoltageCalculateByRecentlyList(Map<Date, DaVoltageData> voltageSampleList, Date measureTime, Integer channelNum) {
        Map<String, Object> map = new HashMap<>();
        map.put("channelNum", channelNum);
        map.put("measureTime", measureTime);
        Map<Integer, ErrVoltageError> mapData = new HashMap<>();
        List<ErrVoltageError> voltageCalculateList = errVoltageErrorMapper.selectVoltageCalculateByRecentlyList(measureTime, channelNum);
        if (CollUtil.isEmpty(voltageCalculateList)) {
            ErrVoltageError voltageCalculate = FrameNotData.calculateDataNotData(measureTime);
            voltageCalculate = checkVoltageCalculateOldData(map, voltageCalculate);
            mapData.put(Convert.toInt(DateUtil.format(measureTime, "yyyyMMdd")), voltageCalculate);
            return mapData;
        }
        for (Date dateTime : voltageSampleList.keySet()) {
            Integer yyyyMMdd = Convert.toInt(DateUtil.format(dateTime, "yyyyMMdd"));
            if (StringUtils.isNull(mapData.get(yyyyMMdd))) {
                for (ErrVoltageError vc : voltageCalculateList) {
                    map.put("measureTime", dateTime);
                    Integer mMdd = Convert.toInt(DateUtil.format(DLT698Utils.getLastPointDate(vc.getCalculateTime()), "yyyyMMdd"));
                    if (yyyyMMdd.equals(mMdd)) {
                        vc = checkVoltageCalculateOldData(map, vc);
                        mapData.put(mMdd, vc);
                        continue;
                    } else if (StringUtils.isNull(mapData.get(yyyyMMdd))) {
                        vc = checkVoltageCalculateOldData(map, vc);
                        mapData.put(yyyyMMdd, vc);
                        continue;
                    }
                }
            }
        }
        return mapData;
    }

    @Override
    public ErrVoltageError selectVoltageCalculateByRecently(Map<String, Object> map) {
        ErrVoltageError errVoltageError =  errVoltageErrorMapper.selectVoltageCalculateByRecently(map);
        return checkVoltageCalculateOldData(map,errVoltageError);
    }

    public static ErrVoltageError checkVoltageCalculateOldData(Map<String, Object> map, ErrVoltageError voltageCalculate) {
        Integer channelNum = Convert.toInt(map.get("channelNum"));

        Date measureTime = null;
        try {
            measureTime = (Date) map.get("measureTime");
        } catch (Exception e) {
            measureTime = DLT698Utils.getLastPointDate(DateUtil.offsetHour(DateUtil.date(), -IntegerUtil.INTEGER_2));
        }
        if (StringUtils.isNull(voltageCalculate) || StringUtils.isNull(voltageCalculate.getId())) {
            // 表示前一天的数据也是空数据
            voltageCalculate = FrameNotData.calculateDataNotData(measureTime);
            voltageCalculate.setId(0L);
            voltageCalculate.setChannelNum(channelNum);
            voltageCalculate.setCalculateTime(measureTime);
            // 验证是否属于定性误差评估
            //FrameNotData.checkCalculateErrorResult(voltageCalculate);
            return voltageCalculate;
        } else {
            // 此处应为判断数据时间是否与发送时间范围差距24小时，但因查询时间已经为 -2 小时数据，故在此减少数据时间为22小时
            long between = DateUtil.between(voltageCalculate.getCalculateTime(), measureTime, DateUnit.DAY, Boolean.FALSE);
            // 2023-5-8 此处应为判断数据时间是否与发送时间范围差距1天
            Boolean flag = measureTime.getTime() >= voltageCalculate.getCalculateTime().getTime() ? true : false;
            // 2023-5-8 此处应为判断获取数据时间     一定大于    实际计算数据时间
            Integer maxHours = IntegerUtil.INTEGER_22;
            Integer calculateStatus = IntegerUtil.INTEGER_15;
            // if (between > maxHours) {
            if (!flag || between != IntegerUtil.INTEGER_0) {
                // 表示查询到的数据为历史数据，不为当前天或当天的记算数据
                voltageCalculate = FrameNotData.calculateDataNotData(measureTime);
                voltageCalculate.setId(0L);
                voltageCalculate.setChannelNum(channelNum);
                voltageCalculate.setCalculateTime(measureTime);
                // 验证是否属于定性误差评估
                //FrameNotData.checkCalculateErrorResult(voltageCalculate);
                return voltageCalculate;
            }
            if (StringUtils.isNull(voltageCalculate.getCalculateStatus())) {
                // 数据有效状态-扩展(5-8) dataErrorStatusType;
                voltageCalculate.setCalculateStatus(calculateStatus + "");
            } else if (Integer.valueOf(voltageCalculate.getCalculateStatus()) > calculateStatus || Integer.valueOf(voltageCalculate.getCalculateStatus()) < 0) {
                // 数据有效状态-扩展(5-8) dataErrorStatusType;
                voltageCalculate.setCalculateStatus(calculateStatus + "");
            }
            // 误差计算方法(0-1) errorCalculateStatus;
            voltageCalculate.setErrorCalculateStatus(IntegerUtil.INTEGER_0);
            // 数据有效状态(2) dataStatus;
            voltageCalculate.setDataStatus("0");
            // 数据有效状态(3-4) dataErrorStatus;
            voltageCalculate.setDataErrorStatus(IntegerUtil.INTEGER_0);
        }
        // 验证是否属于定性误差评估
       // FrameNotData.checkCalculateErrorResult(voltageCalculate);
        return voltageCalculate;
    }
}
