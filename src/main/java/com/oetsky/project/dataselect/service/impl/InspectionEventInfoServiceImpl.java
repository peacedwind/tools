package com.oetsky.project.dataselect.service.impl;


import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.RandomUtil;
import com.oetsky.common.utils.bean.BeanUtils;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.convert.Convert;
import com.oetsky.project.dataselect.domain.InspectionEventInfo;
import com.oetsky.project.dataselect.mapper.InspectionEventInfoMapper;
import com.oetsky.project.dataselect.service.InspectionEventInfoService;
import com.oetsky.project.enums.EventCodeEnum;
import com.oetsky.project.enums.EventTypeEnum;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import java.util.*;

import java.util.Random;
import javax.annotation.Resource;

import com.oetsky.project.serialsetting.serial.domain.EventInfoData;
import com.oetsky.project.serialsetting.serial.utils.DLT698Utils;
import org.apache.commons.lang3.time.DateUtils;
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

    @Override
    public synchronized List<InspectionEventInfo> createEventList(Date date) {
        if (date == null){
            date = new Date();
        }
        List<InspectionEventInfo> res = new ArrayList<>();
        date = DateUtil.beginOfDay(date);
        //查询数据库最大id值
        Integer maxId = this.inspectionEventInfoMapper.selectMaxId();
        if (maxId == null){
            maxId = 0;
        }
        //随机生成 100 - 254条事件数据
        int eventSum = RandomUtil.randomInt(100, 255);
        System.out.println("产生了"+eventSum+"条事件");
        int recSum = 0;
        //分配给24个小时段
        int[] everyHourNum = getEveryHourNum(eventSum);
        List<Integer> typeList = initTypeList();
        for (int i = 0; i < everyHourNum.length; i++) {
            for (int j = 0; j < everyHourNum[i]; j++) {
                //时间获取
                int offsetMin = RandomUtil.randomInt((i+1)*60);
                int offsetSecond = RandomUtil.randomInt(60);
                Date start = DateUtil.offsetSecond(DateUtil.offsetMinute(date, offsetMin),
                    offsetSecond);
                //通道获取
                int channelNum = RandomUtil.randomInt(13);
                //获取类型
                int randomType = getRandomType(typeList);
                //获取事件
                InspectionEventInfo startEvent = getInspectEvent(start,channelNum,randomType);
                int startId = ++maxId;
                startEvent.setId(startId);
                startEvent.setNum(startId);
                res.add(startEvent);
                //是否恢复
                int recoveryFlag = RandomUtil.randomInt(2);
                Date end = null;
                if (recoveryFlag == 1 && i != 23){
                    InspectionEventInfo endEvent = new InspectionEventInfo();
                    //恢复
                    recSum++;
                    end = DateUtil.offsetHour(start,1);
                    BeanUtils.copyProperties(startEvent,endEvent);
                    int endId = ++maxId;
                    endEvent.setId(endId);
                    endEvent.setNum(endId);
                    endEvent.setExceptionId(startId);
                    endEvent.setRecoveryTime(end);
                    res.add(endEvent);
                }
            }
        }
        System.out.println("产生了恢复事件"+recSum);
        return res;
    }

    private InspectionEventInfo getInspectEvent(Date start, int channelNum, int randomType) {
        InspectionEventInfo inspectionEventInfo = new InspectionEventInfo();
        EventCodeEnum eventCodeEnum = EventCodeEnum.getEventEnumByCode(randomType);
        Integer eventCode = randomType;
        String eventName = String.format("随机事件_%d",randomType);
        Integer eventType = EventTypeEnum.TIP.getCode();
        if (eventCodeEnum != null){
            eventCode = eventCodeEnum.getCode();
            eventName = eventCodeEnum.getDesc();
            eventType = eventCodeEnum.getEventTypeEnum().getCode();
        }
        inspectionEventInfo.setEventType(eventType);
        inspectionEventInfo.setEventCode(eventCode);
        inspectionEventInfo.setEventName(eventName);
        inspectionEventInfo.setEventSource("电压采集单元"+channelNum);
        inspectionEventInfo.setStartTime(start);
        inspectionEventInfo.setRecoveryReason("正常恢复");
        inspectionEventInfo.setSourceCode(null);
        inspectionEventInfo.setCreateTime(start);
        inspectionEventInfo.setSourceChannelNum(channelNum);
        inspectionEventInfo.setSourceChannelName(String.valueOf(channelNum));
        inspectionEventInfo.setSourceUnitId(1L);
        inspectionEventInfo.setSourceBoardNum(1);
        inspectionEventInfo.setSourceUnitType("1");
        inspectionEventInfo.setExceptionId(null);
        return inspectionEventInfo;
    }

    private List<Integer> initTypeList(){
        List<Integer> typeList = new LinkedList<>();
        for (int i = 0; i < 254; i++) {
            typeList.add(i+1);
        }
        return typeList;
    }

    private int getRandomType(List<Integer> typeList){
        if (CollectionUtil.isEmpty(typeList)){
            return 0;
        }
        int index = RandomUtil.randomInt(typeList.size());
        Integer res = typeList.get(index);
        typeList.remove(index);
        return res;
    }

    private int[] getEveryHourNum(int num){
        int numHour = 24;
        // 初始化每个人得到0个苹果
        int[] applesPerPerson = new int[numHour];
        Arrays.fill(applesPerPerson, 0);

        Random random = new Random();

        // 随机分配苹果
        for (int i = 0; i < num; i++) {
            // 在0-9之间生成一个随机数来表示当前获得苹果的人的索引号码
            int personIndex = random.nextInt(numHour);
            applesPerPerson[personIndex]++;
        }

        return applesPerPerson;
    }


    @Override
    public void batSave(List<InspectionEventInfo> inspectionEventInfos) {
        inspectionEventInfoMapper.insertBatch(inspectionEventInfos);
    }

    @Override
    public synchronized void createData() {
        Date date = DateUtil.beginOfDay(new Date());
        //判断当天是否数据
        boolean todayFlag =  this.existsDataByDate(date);
        if (!todayFlag){
            //不存在
            List<InspectionEventInfo> eventList = this.createEventList(date);
            this.batSave(eventList);
        }
        //判断下一天
        boolean nextDayFlag =  this.existsDataByDate(DateUtil.offsetDay(date,1));
        if (!nextDayFlag){
            List<InspectionEventInfo> eventList = this.createEventList(DateUtil.offsetDay(date,1));
            this.batSave(eventList);
        }
    }

    private boolean existsDataByDate(Date date) {
        if (date == null){
            throw new RuntimeException("非法参数。。。");
        }
        Integer count = this.inspectionEventInfoMapper.countDataByDate(date,DateUtil.offsetDay(date,1));
        return count != null && count > 0;
    }
    public static Map<Long,Map<Integer,List<EventInfoData>>> eventChannelData= new HashMap<>();
    @Override
    public List<EventInfoData> selectInspectionEventInfoListBy(EventInfoData eventInfoData) {
        List<EventInfoData> list = new ArrayList<>();
        Date createTime = eventInfoData.getCreateTime();
        if(createTime == null){
            createTime = DateUtils.addMinutes(DLT698Utils.getLastPointDate(new Date()), -15);
            eventInfoData.setCreateTime(createTime);
        }
        Long timeLog = Convert.toLong(DateUtil.format(createTime,"yyyyMMddHHmmss"));
        Map<Integer, List<EventInfoData>> mmap = eventChannelData.get(timeLog);
        if(CollUtil.isNotEmpty(mmap)){
            list = mmap.get(eventInfoData.getChannelNum());
            if(CollUtil.isNotEmpty(list)){
                return list;
            }
        }
        List<EventInfoData> eventInfoDataList = inspectionEventInfoMapper
                .selectInspectionEventInfoListBy(eventInfoData);
        if(CollUtil.isNotEmpty(eventInfoDataList)){
            if(CollUtil.isNotEmpty(eventChannelData) && eventChannelData.size() > 1){
                Iterator<Map.Entry<Long,Map<Integer,List<EventInfoData>>>> it = eventChannelData.entrySet().iterator();
                if(it!=null){
                    while (it.hasNext()){
                        // 删除历史数据
                        Map.Entry<Long, Map<Integer, List<EventInfoData>>> next = it.next();
                        if(!next.getKey().equals(eventInfoData.getCreateTime())){
                            it.remove();
                            eventChannelData.remove(next.getKey());
                        }
                    }
                }
            }
            // 2023-4-16 取消默认事件序序，按需求排序
            // eventInfoDataList.sort(Comparator.comparing(EventInfoData :: getId ).reversed());
            Map<Integer, List<EventInfoData>> integerListMap = eventChannelData.get(timeLog);
            if(integerListMap == null){
                integerListMap = new HashMap<>();
            }
            integerListMap.put(eventInfoData.getChannelNum(), eventInfoDataList);
            eventChannelData.put(timeLog, integerListMap);
        } else {
            eventInfoDataList = new ArrayList<>();
        }
        return eventInfoDataList;
    }

}
