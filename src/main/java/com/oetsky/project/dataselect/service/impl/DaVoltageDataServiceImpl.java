package com.oetsky.project.dataselect.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.RandomUtil;
import com.oetsky.project.dataselect.domain.DaVoltageData;
import com.oetsky.project.dataselect.domain.InspectionEventInfo;
import com.oetsky.project.dataselect.mapper.DaVoltageDataMapper;
import com.oetsky.project.dataselect.service.IDaVoltageDataService;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
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
    @Resource
    private DaVoltageDataMapper daVoltageDataMapper;

    private static final BigDecimal B54 = new BigDecimal(54);

    private static final BigDecimal B00 = BigDecimal.ZERO;

    private static final BigDecimal B_000001 = new BigDecimal(-0.000001);

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


    @Override
    public boolean existsDate(Date date) {
        if (date == null){
            return false;
        }
        Date end = DateUtil.offsetSecond(DateUtil.offsetDay(date, +1), -1);
        DaVoltageData daVoltageData = new DaVoltageData();
        daVoltageData.setCollectTimeStart(date);
        daVoltageData.setCollectTimeEnd(end);
        List<DaVoltageData> res = this.selectDaVoltageDataList(daVoltageData);
        return CollectionUtil.isNotEmpty(res);
    }

    @Override
    public List<DaVoltageData> createDataByDate(Date date) {
        if (date == null){
            date = new Date();
        }
        List<DaVoltageData> res = new ArrayList<>();
        //获取当前的日期开始
        Date dayStart = DateUtil.beginOfDay(date);
        //1-12
        for (int i = 0; i < 12; i++) {
            // 每天 96个点
            for (int j = 0; j < 96; j++) {
                Date time = DateUtil.offsetMinute(dayStart, j * 15);
                DaVoltageData daVoltageData = new DaVoltageData();
                daVoltageData.setCreateTime(new Date());
                daVoltageData.setBoardNum(1);
                daVoltageData.setBoardChannel(1);
                //通道号
                daVoltageData.setChannelNum(i+1);
                daVoltageData.setCollectTime(time);
                daVoltageData.setTimeType(0);
                daVoltageData.setAaFundamentalFrequency(createVoltageRandom());
                daVoltageData.setAaFundamentalAmplitude(createVoltageRandom());
                daVoltageData.setA3HarmonicAmplitude(createVoltageRandom());
                daVoltageData.setA5HarmonicAmplitude(createVoltageRandom());
                daVoltageData.setAaFundamentalPhase(createVoltageRandom());
                daVoltageData.setA3HarmonicPhase(createVoltageRandom());
                daVoltageData.setA5HarmonicPhase(createVoltageRandom());
                daVoltageData.setBbFundamentalFrequency(createVoltageRandom());
                daVoltageData.setBbFundamentalAmplitude(createVoltageRandom());
                daVoltageData.setB3HarmonicAmplitude(createVoltageRandom());
                daVoltageData.setB5HarmonicAmplitude(createVoltageRandom());
                daVoltageData.setBbFundamentalPhase(createVoltageRandom());
                daVoltageData.setB3HarmonicPhase(createVoltageRandom());
                daVoltageData.setB5HarmonicPhase(createVoltageRandom());
                daVoltageData.setCcFundamentalFrequency(createVoltageRandom());
                daVoltageData.setCcFundamentalAmplitude(createVoltageRandom());
                daVoltageData.setC3HarmonicAmplitude(createVoltageRandom());
                daVoltageData.setC5HarmonicAmplitude(createVoltageRandom());
                daVoltageData.setCcFundamentalPhase(createVoltageRandom());
                daVoltageData.setC3HarmonicPhase(createVoltageRandom());
                daVoltageData.setC5HarmonicPhase(createVoltageRandom());
                daVoltageData.setZeroSequenceVoltageImbalance(createVoltageRandom());
                daVoltageData.setNegativeSequenceVoltageImbalance(createVoltageRandom());
                daVoltageData.setStatus(0);
                daVoltageData.setChannelLevel(1);
                daVoltageData.setColumn1("");
                daVoltageData.setColumn2("");
                daVoltageData.setColumn3("");
                daVoltageData.setIsSupplement(1);
                daVoltageData.setStartTime(time);
                daVoltageData.setEndTime(time);
                daVoltageData.setChannelName("test");
                daVoltageData.setAaBoardSortNum(2);
                daVoltageData.setBbBoardSortNum(2);
                daVoltageData.setCcBoardSortNum(2);
                daVoltageData.setAaClockStatus(0);
                daVoltageData.setBbClockStatus(0);
                daVoltageData.setCcClockStatus(0);
                daVoltageData.setAaLogicDataQuality("00000000");
                daVoltageData.setBbLogicDataQuality("00000000");
                daVoltageData.setCcLogicDataQuality("00000000");
                res.add(daVoltageData);
            }
        }
        return res;
    }

    /**
     * 获取电压数据的随机值
     * @return
     */
    private BigDecimal createVoltageRandom(){
        int index = RandomUtil.randomInt(4);
        BigDecimal res = null;
        switch (index){
            //正数
            case 0: res = RandomUtil.randomBigDecimal(B54); break;
            //负数
            case 1: res = RandomUtil.randomBigDecimal(B54).negate(); break;
            // -0.000001;
            case 2: res = B_000001; break;
            default: res = B00;
        }
        return res;
    }


    @Override
    public void batchDaVoltageDataList(List<DaVoltageData> dataList) {
        if (dataList != null){
            daVoltageDataMapper.batchDaVoltageDataList(dataList);
        }
    }

    @Override
    public void createData() {
        Date date = DateUtil.beginOfDay(new Date());
        //判断当天是否数据
        boolean todayFlag =  this.existsDataByDate(date);
        if (!todayFlag){
            //不存在
            List<DaVoltageData> dataList = this.createDataByDate(date);
            this.batchDaVoltageDataList(dataList);
        }
        //判断下一天
        boolean nextDayFlag =  this.existsDataByDate(DateUtil.offsetDay(date,1));
        if (!nextDayFlag){
            List<DaVoltageData> dataList = this.createDataByDate(DateUtil.offsetDay(date,1));
            this.batchDaVoltageDataList(dataList);
        }
    }

    private boolean existsDataByDate(Date date) {
        Integer count = this.daVoltageDataMapper.countDataByDate(date,DateUtil.offsetDay(date,1));
        return count != null && count > 0;
    }
}
