package com.oetsky.project.serialsetting.serial.utils;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.date.DateUtil;
import com.oetsky.common.frame.utils.ProtocolUtils;
import com.oetsky.common.utils.StringUtils;
import com.oetsky.project.dataselect.domain.DaVoltageData;
import com.oetsky.project.dataselect.domain.ErrVoltageError;
import com.oetsky.project.serialsetting.serial.domain.EventInfoData;
import org.apache.commons.lang3.time.DateUtils;

import java.math.BigDecimal;
import java.util.*;
import java.util.Map.Entry;

/**
 * @author xiangzc
 */
public class FrameNotData {


    /**
     * 生成空  电压采集数据
     *
     * @return
     */
    public static DaVoltageData sampleDataNotData(Date collectTime) {
        DaVoltageData sampleData = new DaVoltageData();
        sampleData.setChannelNum(0);
        BigDecimal zero = BigDecimal.valueOf(0);
        //a相
        // 采集基波频率
        sampleData.setAaFundamentalFrequency(zero);
        // 采集基波幅值
        sampleData.setAaFundamentalAmplitude(zero);
        // 采集3次谐波幅值
        sampleData.setA3HarmonicAmplitude(zero);
        // 采集5次谐波幅值
        sampleData.setA5HarmonicAmplitude(zero);
        // 采集基波相位
        sampleData.setAaFundamentalPhase(zero);
        // 采集3次谐波相位
        sampleData.setA3HarmonicPhase(zero);
        // 采集5次谐波相位
        sampleData.setA5HarmonicPhase(zero);
        //b相
        sampleData.setBbFundamentalFrequency(zero);
        sampleData.setBbFundamentalAmplitude(zero);
        sampleData.setB3HarmonicAmplitude(zero);
        sampleData.setB5HarmonicAmplitude(zero);
        sampleData.setBbFundamentalPhase(zero);
        sampleData.setB3HarmonicPhase(zero);
        sampleData.setB5HarmonicPhase(zero);

        //c相
        sampleData.setCcFundamentalFrequency(zero);
        sampleData.setCcFundamentalAmplitude(zero);
        sampleData.setC3HarmonicAmplitude(zero);
        sampleData.setC5HarmonicAmplitude(zero);
        sampleData.setCcFundamentalPhase(zero);
        sampleData.setC3HarmonicPhase(zero);
        sampleData.setC5HarmonicPhase(zero);
        // 同步标识
        sampleData.setFlag("00");
        // 数据时间
        sampleData.setCollectTime(collectTime);
        // 零序
        sampleData.setZeroSequenceVoltageImbalance(zero);
        // 负序
        sampleData.setNegativeSequenceVoltageImbalance(zero);
        return sampleData;
    }

    /**
     * 生成空  电压计算数据
     *
     * @return
     */
    public static ErrVoltageError calculateDataNotData(Date measureTime) {
        ErrVoltageError calculateData = new ErrVoltageError();
        calculateData.setChannelNum(0);
        BigDecimal zero = BigDecimal.valueOf(0);
        //A比差
        calculateData.setAaRatioError(zero);
        calculateData.setAaRatioVariance(zero);
        //A角差
        calculateData.setAaAngleError(zero);
        calculateData.setAaAngleVariance(zero);
        //B比差
        calculateData.setBbRatioError(zero);
        calculateData.setBbRatioVariance(zero);
        //B角差
        calculateData.setBbAngleError(zero);
        calculateData.setBbAngleVariance(zero);
        //C比差
        calculateData.setCcRatioError(zero);
        calculateData.setCcRatioVariance(zero);
        //C 角差
        calculateData.setCcAngleError(zero);
        calculateData.setCcAngleVariance(zero);
        //数据时间
        calculateData.setCalculateTime(measureTime);

        // 误差计算方法(0-1) errorCalculateStatus;
        calculateData.setErrorCalculateStatus(1);
        // 数据有效状态(2) dataStatus;
        calculateData.setDataStatus("1");
        // 数据有效状态(3-4) dataErrorStatus;
        calculateData.setDataErrorStatus(2);
        // 数据有效状态-扩展(5-8) dataErrorStatusType;
        calculateData.setCalculateStatus("15");
        return calculateData;
    }

    public static EventInfoData getNotEventInfo() {
        EventInfoData eventInfo = new EventInfoData();
        eventInfo.setId(0L);
        eventInfo.setNum(0L);
        eventInfo.setChannelNum(0);
        eventInfo.setStartTime(new Date());
        eventInfo.setEndTime(new Date());
        eventInfo.setInterfaceType("0");
        eventInfo.setErrorType("0");
        return eventInfo;
    }

    public static List<EventInfoData> getNotEventInfoList() {
        List<EventInfoData> list = new ArrayList<>();
        list.add(getNotEventInfo());
        return list;
    }


    /**
     * 2023-4-16 按未上报事件循环获取事件
     * @param eventInfoDataList
     * @return
     */
    public static EventInfoData getNowEventList(List<EventInfoData> eventInfoDataList){
        if(eventInfoDataList!=null && eventInfoDataList.size() > 0){
            for (EventInfoData getNowEvent : eventInfoDataList) {
                EventInfoData sendEventData = FrameNotData.getSendEventData(getNowEvent);
                if(sendEventData != null){
                    return sendEventData;
                }
            }
        }
         return null;
    }

    public static Map<Integer, Map<Date,List<EventInfoData>>> catchEventInfoData = new HashMap<>();
    public static void setCatchEventInfoData(Integer channelNum, Date createTime,EventInfoData eventInfoData){
        Map<Date, List<EventInfoData>> dateEventInfoDataMap = catchEventInfoData.get(channelNum);
        if(StringUtils.isEmpty(dateEventInfoDataMap)){
            dateEventInfoDataMap = new HashMap<>();
        }
        if(StringUtils.isNull(dateEventInfoDataMap.get(createTime))){
            Iterator<Entry<Integer,Map<Date,List<EventInfoData>>>> it = catchEventInfoData.entrySet().iterator();
            if(it!=null){
                while (it.hasNext()){
                    // 删除历史数据
                    Entry<Integer,Map<Date,List<EventInfoData>>> next = it.next();
                    if(!next.getKey().equals(createTime)){
                        it.remove();
                    }
                }
            }
            List<EventInfoData> list = new ArrayList<>(5);
            list.add(eventInfoData);
            list.add(eventInfoData);
            dateEventInfoDataMap.put(createTime,list);
            catchEventInfoData.put(channelNum,dateEventInfoDataMap);
        }
    }
    public static EventInfoData getCatchEventInfoData(Integer channelNum, Date createTime){
        Map<Date, List<EventInfoData>> stringEventInfoDataMap = catchEventInfoData.get(channelNum);
        if(StringUtils.isNotEmpty(stringEventInfoDataMap)){
            List<EventInfoData> eventInfoDataList = stringEventInfoDataMap.get(createTime);
            if(CollUtil.isNotEmpty(eventInfoDataList)){
                EventInfoData eventInfoData = eventInfoDataList.get(0);
                eventInfoDataList.remove(0);
                stringEventInfoDataMap.put(createTime,eventInfoDataList);
                catchEventInfoData.put(channelNum,stringEventInfoDataMap);
                return eventInfoData;
            }
        }
        return null;
    }


    /**
     * 验证事件是否重复上报
     */
    public static Map<Integer, Map<String,String>> eventMap = new HashMap<>();
    public static EventInfoData getSendEventData(EventInfoData eventInfoData){
        if(eventInfoData == null){
            eventInfoData = FrameNotData.getNotEventInfo();
        }
        Date createTime = eventInfoData.getCreateTime();
        if(createTime == null){
            createTime = DateUtils.addMinutes(new Date(), -15);
            eventInfoData.setCreateTime(createTime);
        }
        Integer nowTime = Convert.toInt(DateUtil.format(createTime, "yyyyMMdd"));
        if(eventMap!= null && eventMap.size() > 1){
            Iterator<Entry<Integer,Map<String,String>>> it = eventMap.entrySet().iterator();
            if(it!=null){
                while (it.hasNext()){
                    // 删除历史数据
                    Entry<Integer, Map<String, String>> next = it.next();
                    if(!next.getKey().equals(nowTime)){
                        it.remove();
                    }
                }
            }
        }
        Map<String, String> stringIntegerMap = eventMap.get(nowTime);
        if(stringIntegerMap == null){
            stringIntegerMap = new HashMap<>();
        }
        // 如果事件为开始 ，则结束时间为空，值为0，如果为结束，则结束不为空，值为1
        Integer eventStatus = eventInfoData.getEndTime() == null ? 0 : 1;
        String key = eventInfoData.getChannelNum() + "_" + eventInfoData.getErrorType() + "_" + eventStatus;
        String countId = stringIntegerMap.get(key);
        if(countId == null || !key.equals(countId)){
            stringIntegerMap.put(key, key);
            eventMap.put(nowTime, stringIntegerMap);
            return eventInfoData;
        }
        return null;
    }
}
