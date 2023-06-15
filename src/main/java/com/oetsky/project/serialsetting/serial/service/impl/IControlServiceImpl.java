package com.oetsky.project.serialsetting.serial.service.impl;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.map.CaseInsensitiveMap;
import com.oetsky.common.frame.utils.IntegerUtil;
import com.oetsky.common.frame.utils.ProtocolUtils;
import com.oetsky.common.utils.StringUtils;
import com.oetsky.common.utils.reflect.ReflectUtils;
import com.oetsky.project.dataselect.domain.DaVoltageData;
import com.oetsky.project.dataselect.domain.ErrVoltageError;
import com.oetsky.project.dataselect.service.IDaVoltageDataService;
import com.oetsky.project.dataselect.service.IErrVoltageErrorService;
import com.oetsky.project.dataselect.service.InspectionEventInfoService;
import com.oetsky.project.serialsetting.serial.domain.*;
import com.oetsky.project.serialsetting.serial.service.IControlService;
import com.oetsky.project.serialsetting.serial.utils.ControlConstants;
import com.oetsky.project.serialsetting.serial.utils.DLT698Utils;
import com.oetsky.project.serialsetting.serial.utils.FrameNotData;
import com.oetsky.project.serialsetting.serial.domain.CommandFrameLog;
import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;

/**
 * @Author: zhangw
 * @Date: 2023/6/15
 */
@Service
public class IControlServiceImpl implements IControlService {
    private static final Logger LOGGER = LoggerFactory.getLogger(IControlServiceImpl.class);

    @Resource
    private IDaVoltageDataService daVoltageDataService;

    @Resource
    private IErrVoltageErrorService voltageErrorService;

    @Resource
    private InspectionEventInfoService inspectionEventInfoService;

    /**
     * 组装互感器监测数据命令帧中数据帧
     *
     * @param sampleData 抽样数据
     * @param phase      相序
     * @return
     */
    @Override
    public String checkMonitoringFramesData(DaVoltageData sampleData, String phase) {
        StringBuilder frames = new StringBuilder();
        // 发送数据长度
        frames.append(DLT698Constants.STRUCT + "04");

        // A相 低位在前
        if ("A".equals(phase)) {
            // 基波频率
            frames.append(DLT698Constants.DOUBLE_LONG_UNSIGEND + DLT698Utils.formatValue(sampleData.getAaFundamentalFrequency(), ControlConstants.ColumnFormat.FUNDAMENTAL_FREQUENCY));
            // 基波有效值
            frames.append(DLT698Constants.LONG64_UNSIGEND + DLT698Utils.formatValue(sampleData.getAaFundamentalAmplitude(), ControlConstants.ColumnFormat.FUNDAMENTAL_AMPLITUDE));
            // 基波相位
            frames.append(DLT698Constants.DOUBLE_LONG + DLT698Utils.formatValue(sampleData.getAaFundamentalPhase(), ControlConstants.ColumnFormat.FUNDAMENTAL_PHASE));
        }
        //B相 低位在前
        if ("B".equals(phase)) {
            // 基波频率
            frames.append(DLT698Constants.DOUBLE_LONG_UNSIGEND + DLT698Utils.formatValue(sampleData.getBbFundamentalFrequency(), ControlConstants.ColumnFormat.FUNDAMENTAL_FREQUENCY));
            // 基波有效值
            frames.append(DLT698Constants.LONG64_UNSIGEND + DLT698Utils.formatValue(sampleData.getBbFundamentalAmplitude(), ControlConstants.ColumnFormat.FUNDAMENTAL_AMPLITUDE));
            // 基波相位
            frames.append(DLT698Constants.DOUBLE_LONG + DLT698Utils.formatValue(sampleData.getBbFundamentalPhase(), ControlConstants.ColumnFormat.FUNDAMENTAL_PHASE));
        }
        //C相 低位在前
        if ("C".equals(phase)) {
            // 基波频率
            frames.append(DLT698Constants.DOUBLE_LONG_UNSIGEND + DLT698Utils.formatValue(sampleData.getCcFundamentalFrequency(), ControlConstants.ColumnFormat.FUNDAMENTAL_FREQUENCY));
            // 基波有效值
            frames.append(DLT698Constants.LONG64_UNSIGEND + DLT698Utils.formatValue(sampleData.getCcFundamentalAmplitude(), ControlConstants.ColumnFormat.FUNDAMENTAL_AMPLITUDE));
            // 基波相位
            frames.append(DLT698Constants.DOUBLE_LONG + DLT698Utils.formatValue(sampleData.getCcFundamentalPhase(), ControlConstants.ColumnFormat.FUNDAMENTAL_PHASE));
        }

        // 保留字段
        String reserved = DLT698Utils.parseHexStr2Byte("00");
        String reservedData = "00";
        if (StringUtils.isNotEmpty(reserved) && reserved.length() > IntegerUtil.INTEGER_2) {
            reservedData = reservedData + reserved.substring(IntegerUtil.INTEGER_0, IntegerUtil.INTEGER_2);
        } else {
            reservedData = reservedData + "00";
        }
        // 同步状态：0-成功   其他-失败
        String syncType = "0000";
        // 设备自身状态：0-正常 其他-异常
        String equipmentStatus = "0000";
        // 计量状态：0-正常、1- 警告、2- 异常
        String meteringStatus = "0000";

        // 判断数据是否为有效

        if (ProtocolUtils.getBeenDataToResult(sampleData.getCollectTime())
                || StringUtils.isNull(sampleData.getId())
                || sampleData.getId() < 1) {
            // 表示当前数据并不为实时数据,而为无效数据
            syncType = "0001";
            equipmentStatus = "0001";
        }
        //采集状态
        String dataType = reservedData + syncType + equipmentStatus + meteringStatus;
        frames.append(DLT698Constants.BIT_STRING + DLT698Constants.LONG_STR + DLT698Utils.parseByte2HexStr(dataType));
        return frames.toString().toUpperCase(Locale.ENGLISH);
    }

    /**
     * 组装 互感器误差数据 命令帧中数据帧
     *
     * @param calculateData 讲算数据
     * @param phase         相序
     * @return
     */
    public String checkErrorDataFramesData(ErrVoltageError calculateData, String phase, ControlConstants.ColumnFormat columnFormat) {
        StringBuilder frames = new StringBuilder();
        // 发送数据长度
        frames.append(DLT698Constants.STRUCT + "03");

        if ("A".equalsIgnoreCase(phase)) {
            //A相 比差均值
            frames.append(DLT698Constants.DOUBLE_LONG + DLT698Utils.formatValue(calculateData.getAaRatioError(), ControlConstants.ColumnFormat.RATIO_AVERAGE));
            //A相 角差
            frames.append(DLT698Constants.DOUBLE_LONG + DLT698Utils.formatValue(calculateData.getAaAngleError(), columnFormat));
        }
        if ("B".equalsIgnoreCase(phase)) {
            //B相 比差均值
            frames.append(DLT698Constants.DOUBLE_LONG + DLT698Utils.formatValue(calculateData.getBbRatioError(), ControlConstants.ColumnFormat.RATIO_AVERAGE));
            //B相 角差
            frames.append(DLT698Constants.DOUBLE_LONG + DLT698Utils.formatValue(calculateData.getBbAngleError(), columnFormat));
        }

        if ("C".equalsIgnoreCase(phase)) {
            //C相 比差均值
            frames.append(DLT698Constants.DOUBLE_LONG + DLT698Utils.formatValue(calculateData.getCcRatioError(), ControlConstants.ColumnFormat.RATIO_AVERAGE));
            //C相 角差
            frames.append(DLT698Constants.DOUBLE_LONG + DLT698Utils.formatValue(calculateData.getCcAngleError(), columnFormat));
        }
        //计算状态
        frames.append(DLT698Constants.BIT_STRING + DLT698Constants.LONG_STR + DLT698Utils.parseByte2HexStr(calculateData.getCalculateStatus()));

        return frames.toString().toUpperCase(Locale.ENGLISH);
    }

    /**
     * 组装硬件信息地址数据 命令帧
     *
     * @return
     */
    public String checkHardwareStatusFramesData() {
        StringBuilder frames = new StringBuilder();
        // 数据类型
        frames.append(DLT698Constants.VISIBLE_STRING);
        String hardwareAddr = "000000000001";
        String hexAsciiValue = DLT698Utils.stringToAsciiHex(DLT698Utils.formatHighLow(hardwareAddr));
        // 数据长度
        frames.append(DLT698Utils.strToHex16(Convert.toStr(hexAsciiValue.length() / 2)));
        // 硬件地址信息    先进行高低位转换    再进行ASCII码转换
        frames.append(hexAsciiValue);
        return frames.toString().toUpperCase(Locale.ENGLISH);
    }


    /**
     * 冻结数据 转为 应答帧  5002
     *
     * @param control698Data
     */
    @Override
    public String frozenDataToAnswerFrame(CommandFrameLog commandFrameLog, Control698Data control698Data) {
        StringBuilder frames = new StringBuilder();
        // 帧头
        frames.append(ControlConstants.FRAME_HEADER_68);
        // 数据域长度————————》先填写标识符，帧命令拼接完成全再进行验证替换
        frames.append(ControlConstants.DATA_LENGTH_FLAG);
        // 控制域
        frames.append(ControlConstants.CONTROL_CODE_C3);
        // 服务器地址   地址类型
        frames.append(ControlConstants.CONTROL_REGION_TYPE);
        // 服务器地址
        String addrFieldFrame = DLT698Utils.formatHighLow(commandFrameLog.getChannelNum());
        frames.append(addrFieldFrame);
        // 客户机地址
        frames.append(control698Data.getCustomerAddress());
        // 帧头校验————————》先填写标识符，帧命令拼接完成全再进行验证替换
        frames.append(ControlConstants.FRAME_HEADER_FLAG);
        // 请求类型
        frames.append(ControlConstants.FRAME_EVENT_DATA_RESPONSE_NORMAL);
        // PIID
        frames.append(control698Data.getControlApudDatas().getPiid());
        // 请求参数分类 OAD
        frames.append(control698Data.getControlApudDatas().getOad());
        // request 请求数据
        frames.append(control698Data.getControlApudDatas().getRequestRCSDFrozenDataType().getRcsdTypeStr());
        List<String> stringList = control698Data.getControlApudDatas().getRequestRCSDFrozenDataType().getRcsdList();
        // 发送 GetResultType
        frames.append(ControlConstants.FRAME_RESULT_TYPE);
        //相序
        String phase = commandFrameLog.getPhase().toUpperCase(Locale.ENGLISH);
        Map<String, Object> map = new HashMap<>(IntegerUtil.INTEGER_6);
        map.put("channelNum", commandFrameLog.getChannel());
        Date collectTime1 = new Date();
        Date errorTime = new Date();
        ControlApudDatas controlApudDatas = control698Data.getControlApudDatas();
        RequestFrozenDaysDataRsdType requestFrozenDaysDataRsdType = controlApudDatas.getRequestFrozenDaysDataRsdType();
        List<Date> dataTimeList = new ArrayList<>();
        if (!"09".equals(requestFrozenDaysDataRsdType.getType())) {
            collectTime1 = DLT698Utils.getLastPointDate(DateUtil.offsetHour(requestFrozenDaysDataRsdType.getDateTime(), -1));
            errorTime = DLT698Utils.getLastPointDate(DateUtil.offsetHour(requestFrozenDaysDataRsdType.getEndTime(), -1));
            dataTimeList.clear();
            Date startTime = DLT698Utils.getLastPointDate(DateUtil.offsetHour(requestFrozenDaysDataRsdType.getDateTime(), -1));
            Date endTime = DLT698Utils.getLastPointDate(DateUtil.offsetHour(requestFrozenDaysDataRsdType.getEndTime(), -1));
            for (int i = 0; i < 20; i++) {
                if (i == 0) {
                    dataTimeList.add(startTime);
                } else {
                    DateTime dateTime = DateUtil.offsetMinute(startTime, i * 15);
                    if (dateTime.getTime() < endTime.getTime()) {
                        dataTimeList.add(dateTime);
                    } else {
                        break;
                    }
                }

            }
        }
        if (controlApudDatas.getRequestOAD().getDataOi().equalsIgnoreCase(ControlConstants.DataFlag.CVT_FROZEN_DATA.getCode()) && "09".equals(requestFrozenDaysDataRsdType.getType())) {
            // 如果为分钟冻结，且为09数据项时，将查询时间修改为  一小时前的最近一条数据
            collectTime1 = DLT698Utils.getLastPointDate(DateUtil.offsetHour(new Date(), -1));
            errorTime = collectTime1;
            dataTimeList.clear();
            dataTimeList.add(collectTime1);
        }
        Map<Date, DaVoltageData> voltageSampleList = daVoltageDataService.selectSampleDataExitDataList(dataTimeList, collectTime1, commandFrameLog.getChannel());
        Map<Integer, ErrVoltageError> voltageCalculateList = voltageErrorService.selectVoltageCalculateByRecentlyList(voltageSampleList, collectTime1, commandFrameLog.getChannel());

        // 发送数据类型
        frames.append(ProtocolUtils.intToHex16(voltageSampleList.size()));

        for (Date collectTime : voltageSampleList.keySet()) {
            map.put("measureTime", collectTime);
            map.put("collectTime", collectTime);
            DaVoltageData voltageSample = voltageSampleList.get(collectTime);
            if (StringUtils.isNull(voltageSample) || StringUtils.isNull(voltageSample.getId())) {
                // 当查询无数据时，默认生成一个空的数据

            }

            //数据时间为格式化
            voltageSample.setCollectTime(DLT698Utils.getLastPointDate(voltageSample.getCollectTime()));
            Integer errTime = Convert.toInt(DateUtil.format(collectTime, "yyyyMMdd"));
            ErrVoltageError voltageCalculate = voltageCalculateList.get(errTime);
            if (StringUtils.isNull(voltageCalculate) || StringUtils.isNull(voltageCalculate.getId())) {
                // 当查询计算无数据时，默认生成一个空的数据

            }
            // 如果不为送检,则数据时间为格式化
            voltageCalculate.setCalculateTime(DLT698Utils.getLastPointDate(voltageCalculate.getCalculateTime()));
            voltageCalculate.setChannelNum(commandFrameLog.getChannel());

            ControlConstants.ColumnFormat columnFormat = ControlConstants.ColumnFormat.ANGLE_AVERAGE_OLD;
            String city = commandFrameLog.getChannelNum().substring(0, 2);
            if ("42".equals(city) || "65".equals(city) || "35".equals(city)) {
                // 当省份为 湖北 、新疆 、 福建 时，为兼容历史版本需要使用协议解析为-3（3位小数），其它省份默认使用通用版本协议
                columnFormat = ControlConstants.ColumnFormat.ANGLE_AVERAGE;
            }
            for (String str : stringList) {
                if (str.equalsIgnoreCase(DLT698Constants.FrozenDataFlag.CVT_FROZEN_TIME_DATA.getCode())) {
                    frames.append(DLT698Constants.DATE_TIME_S + DLT698Utils.formatDateToHex16(DLT698Utils.getLastPointDate(voltageSample.getCollectTime())));
                } else if (str.equalsIgnoreCase(DLT698Constants.FrozenDataFlag.CVT_DETECTS_HARDWARE_INFORMATION.getCode())) {
                    // 抄读硬件信息
                    String hardwareAddr = checkHardwareStatusFramesData();
                    frames.append(hardwareAddr);
                } else if (str.equalsIgnoreCase(DLT698Constants.FrozenDataFlag.CVT_ACQUISITION_OF_DEVICE_TYPE.getCode())) {
                    // 设备类型
                    frames.append(DLT698Constants.LONG_UNSIGNED);
                    frames.append("00");
                    // 数据类型
                    String deviceTypeStr = "04";
                    if (1 == 1) {
                        deviceTypeStr = "05";
                    }
                    frames.append(deviceTypeStr);
                } else if (str.equalsIgnoreCase(DLT698Constants.FrozenDataFlag.CVT_MONITORING_DATA.getCode())) {
                    String framesValue = null;
                    //组装回复命令帧
                    framesValue = this.checkMonitoringFramesData(voltageSample, phase);
                    frames.append(framesValue);
                } else if (str.equalsIgnoreCase(DLT698Constants.FrozenDataFlag.CVT_ERROR_DATA.getCode())) {
                    String framesValue = null;
                    //组装回复命令帧
                    framesValue = this.checkErrorDataFramesData(voltageCalculate, phase, columnFormat);
                    frames.append(framesValue);
                } else {
                    LOGGER.error("DLT698类型错误");
                }
            }
        }
        voltageSampleList.clear();
        voltageCalculateList.clear();

        // 跟随上报信息域
        frames.append(ControlConstants.FRAME_FOLLOW_REPORT);
        // 默认跟随时间
        // if (StringUtils.isNull(voltageSample.getSampleId()) || Convert.toLong(IntegerUtil.INTEGER_0).equals(voltageSample.getSampleId())) {
        //     frames.append("00");
        // } else if (StringUtils.isNull(voltageSample.getCollectTime())) {
        //     frames.append("00");
        // } else {
        frames.append("01");
        frames.append(DLT698Utils.formatDateToHex16(new Date()));
        frames.append(ControlConstants.FRAME_AWAIT_TIME);
        // }
        // 校验帧FCS————————》先填写标识符，帧命令拼接完成全再进行验证替换
        frames.append(ControlConstants.FRAME_FOOTER_FLAG);
        //帧尾
        frames.append(ControlConstants.FRAME_FOOTER_16);

        String framesStr = frames.toString();
        // 转换数据长度
        framesStr = framesStr.replace(ControlConstants.DATA_LENGTH_FLAG, DLT698Utils.getHexDataLength(framesStr));
        //帧头校验码HCS
        String hcs = framesStr.substring(ControlConstants.HEADER_LENGTH, framesStr.indexOf(ControlConstants.FRAME_HEADER_FLAG));
        framesStr = framesStr.replace(ControlConstants.FRAME_HEADER_FLAG, DLT698Utils.cheakFramesStr(hcs));
        // 帧属校验码FCS
        String fcs = framesStr.substring(ControlConstants.HEADER_LENGTH, framesStr.indexOf(ControlConstants.FRAME_FOOTER_FLAG));
        framesStr = framesStr.replace(ControlConstants.FRAME_FOOTER_FLAG, DLT698Utils.cheakFramesStr(fcs));
        return framesStr.toUpperCase(Locale.ENGLISH);
    }

    /**
     * 组装事件数据对象  数据帧
     *
     * @param control698Data 事件查询 num
     */
    public ResuestErrorEventData checkRequestEventData(Control698Data control698Data, CommandFrameLog commandFrameLog) {
        // 1、获取事件最近10条记录
        EventInfoData eventInfo = new EventInfoData();
        String adree = control698Data.getServerAddress();
        eventInfo.setChannelNum(commandFrameLog.getChannel());
        List<EventInfoData> eventInfoList = new ArrayList<>();
        Date addMinutes = DateUtils.addMinutes(DLT698Utils.getLastPointDate(new Date()), -15);
        // 2、判断事件查询的条数是否大于等于num
        // 查询事件记录数
        String value = control698Data.getControlApudDatas().getRequestEventDataRsdType().getValue();
        Integer selectNum = 1;
        try {
            // 避免出次数为16进制数据0a = 10 时，则直接转int会出再错误
            selectNum = Convert.toInt(DLT698Utils.hex16ToStr(value));
        } catch (Exception e) {
            selectNum = 1;
        }
        if ("A".equals(commandFrameLog.getPhase())) {
            // 2022-11-17 添加异常事件只有A通道时才进行查询，当不为A通道时不查询
            eventInfo.setCreateTime(addMinutes);
            List<EventInfoData> eventInfoData = inspectionEventInfoService.selectInspectionEventInfoListBy(eventInfo);
            // 2023-4-17 用新对象添加数据
            if (eventInfoData != null && eventInfoData.size() > 0) {
                eventInfoList.addAll(eventInfoData);
            }
        }
        if (eventInfoList == null || eventInfoList.size() == 0) {
            eventInfoList = FrameNotData.getNotEventInfoList();
        }
        // 2023-4-16当召测第一个事件数据时，则按降序排序，其它序号时，则按默认排序进行排序上报数据，避免出现优先级高的事件未被上传
        if (selectNum == 1) {
            eventInfoList.sort(Comparator.comparing(EventInfoData::getId).reversed());
        }
        eventInfo = null;
        if (eventInfoList == null || eventInfoList.size() == 0) {
            eventInfo = FrameNotData.getNotEventInfo();
        } else {
            if (eventInfoList.size() >= selectNum) {
                selectNum = selectNum;
            } else if (eventInfoList.size() < selectNum) {
                selectNum = eventInfoList.size();
            }
            // 3、获取事件num -1 的数据
            eventInfo = eventInfoList.get(selectNum - 1);
            // 4、验证缓存是否存在该数据
            eventInfo = FrameNotData.getNowEventList(eventInfoList);
            if (eventInfo != null) {
                eventInfo.setErrorDateTime(eventInfo.getStartTime());
                if (eventInfo.getEndTime() != null) {
                    eventInfo.setErrorDateTime(eventInfo.getEndTime());
                }
            }
            // 1、如果序号为1，则将发送的数据进行记录，
            if (eventInfo != null && selectNum == 1) {
                FrameNotData.setCatchEventInfoData(commandFrameLog.getChannel(), addMinutes, eventInfo);
            }
            // 2、在事件为空时且序号不为1时，|| 序号为1时，上报缓存中记录1的数据，并清空
            if ((eventInfo == null && selectNum != 1) || selectNum == 1) {
                eventInfo = FrameNotData.getCatchEventInfoData(commandFrameLog.getChannel(), addMinutes);
            }
            if (eventInfo == null) {
                eventInfo = FrameNotData.getNotEventInfo();
            }
        }
        // 4、如果事件查询条数小于 num，则获取事件查询条数的最接近的一条
        ResuestErrorEventData reed = new ResuestErrorEventData();
        // 事件记录序号 2022 0200  eventId;
        if (StringUtils.isNotNull(eventInfo.getId()) && eventInfo.getId() > 0) {
            reed.setEventId(DLT698Constants.DOUBLE_LONG_UNSIGEND + DLT698Utils.formatValue(new BigDecimal(eventInfo.getId()), ControlConstants.ColumnFormat.EVENT_DATA_POSITIVE_ACTIVE));
        } else {
            reed.setEventId(DLT698Constants.NULL_STR);
        }
        // 事件发生时间 201e 0200  startTime;
        if (StringUtils.isNotNull(eventInfo.getStartTime())) {
            reed.setStartTime(DLT698Constants.DATE_TIME_S + DLT698Utils.formatDateToHex16(eventInfo.getStartTime()));
        } else {
            // 正式环境
            reed.setStartTime(DLT698Constants.NULL_STR);
        }
        // 事件结束时间 2020 0200 endTime;
        if (StringUtils.isNotNull(eventInfo.getEndTime())) {
            reed.setEndTime(DLT698Constants.DATE_TIME_S + DLT698Utils.formatDateToHex16(eventInfo.getEndTime()));
        } else {

            // 正式环境
            reed.setEndTime(DLT698Constants.NULL_STR);
        }
        // 事件源 2024 0200 eventSource;
        reed.setEventSource(DLT698Constants.OCTET_STR + "0705" + commandFrameLog.getChannelNum());

        // 异常事件上报状态 3300 0200 eventReportType;
        reed.setEventReportType(DLT698Constants.NULL_STR);
        // 事件接口类型 3300 0206 eventControlType;
        if (StringUtils.isNotEmpty(eventInfo.getInterfaceType())) {
            reed.setEventControlType(DLT698Constants.ENUM_STR + DLT698Utils.strToHex16(eventInfo.getInterfaceType()));
        } else {
            reed.setEventControlType(DLT698Constants.NULL_STR);
        }

        // 事件异常类型 3300 0207 eventErrorType;
        if (StringUtils.isNotEmpty(eventInfo.getErrorType())) {
            reed.setEventErrorType(DLT698Constants.ENUM_STR + DLT698Utils.strToHex16(eventInfo.getErrorType()));
        } else {
            reed.setEventErrorType(DLT698Constants.NULL_STR);
        }
        // 判断异常事件是否为空
        if (StringUtils.isNotNull(eventInfo.getId()) && eventInfo.getId() > 0) {
            Map<String, Object> map = new HashMap<>(IntegerUtil.INTEGER_6);
            map.put("channelNum", commandFrameLog.getChannel());
            DaVoltageData voltageSample = null;
            ErrVoltageError voltageCalculate = null;
            if (StringUtils.isNotNull(eventInfo.getErrorDateTime())) {
                map.put("measureTime", eventInfo.getErrorDateTime());
                map.put("collectTime", eventInfo.getErrorDateTime());
                // 如有误差时间数据，则根据误差时间数据查询抽样数据
                if (StringUtils.isNull(voltageSample)) {
                    voltageSample = daVoltageDataService.selectSampleDataByRecently(map);
                    if (voltageSample == null || voltageSample.getId() == null || voltageSample.getId().equals(0)) {
                        voltageSample = FrameNotData.sampleDataNotData(eventInfo.getErrorDateTime());
                        voltageSample.setId(0L);
                    }
                }
                // 如有误差时间数据，则根据误差时间数据查询计算数据
                if (StringUtils.isNull(voltageCalculate)) {

                    voltageCalculate = voltageErrorService.selectVoltageCalculateByRecently(map);
                    if (voltageCalculate == null || voltageCalculate.getId() == null || voltageCalculate.getId().equals(0)) {
                        voltageCalculate = FrameNotData.calculateDataNotData(eventInfo.getErrorDateTime());
                        voltageCalculate.setId(0L);
                    }
                }
            }
            if (StringUtils.isNotNull(voltageSample) && StringUtils.isNotNull(voltageSample.getId())) {
                // 组装抽样数据
                String framesData = this.checkMonitoringFramesData(voltageSample, commandFrameLog.getPhase());
                reed.setEventMonitoringData(framesData);
            } else {
                // 如抽样数据查询为空
                reed.setEventMonitoringData(DLT698Constants.NULL_STR);
            }
            if (voltageCalculate != null && voltageCalculate.getId() != null) {
                ControlConstants.ColumnFormat columnFormat = ControlConstants.ColumnFormat.ANGLE_AVERAGE_OLD;
                String city = adree.substring(0, 2);
                if ("42".equals(city) || "65".equals(city) || "35".equals(city)) {
                    // 当省份为 湖北 、新疆 、 福建 时，为兼容历史版本需要使用协议解析为-3（3位小数），其它省份默认使用通用版本协议
                    columnFormat = ControlConstants.ColumnFormat.ANGLE_AVERAGE;
                }
                // 组装讲算数据
                String framesData = this.checkErrorDataFramesData(voltageCalculate, commandFrameLog.getPhase(), columnFormat);
                reed.setEventErrorData(framesData);
            } else {
                // 如讲算数据查询为空
                reed.setEventErrorData(DLT698Constants.NULL_STR);
            }
        } else {
            reed.setEventMonitoringData(DLT698Constants.NULL_STR);
            reed.setEventErrorData(DLT698Constants.NULL_STR);
        }
        return reed;
    }

    /**
     * 组装事件数据对象  数据帧
     *
     * @param addrVoltagePhase  事件查询 num
     * @param eventInfoDataList 事件集合
     */
    public List<ResuestErrorEventData> checkRequestEventDataArray(CommandFrameLog addrVoltagePhase, List<EventInfoData> eventInfoDataList) {
        List<ResuestErrorEventData> resuestErrorEventDataList = new ArrayList<>();
        if (eventInfoDataList == null || eventInfoDataList.size() == 0) {
            return resuestErrorEventDataList;
        }
        for (EventInfoData eventInfo1 : eventInfoDataList) {
            EventInfoData eventInfo = new EventInfoData();
            eventInfo = eventInfo1;
            ResuestErrorEventData reed = new ResuestErrorEventData();
            // 事件记录序号 2022 0200  eventId;
            if (StringUtils.isNotNull(eventInfo.getId()) && eventInfo.getId() > 0) {
                reed.setEventId(DLT698Constants.DOUBLE_LONG_UNSIGEND + DLT698Utils.formatValue(new BigDecimal(eventInfo.getId()), ControlConstants.ColumnFormat.EVENT_DATA_POSITIVE_ACTIVE));
            } else {
                reed.setEventId(DLT698Constants.NULL_STR);
            }
            // 事件发生时间 201e 0200  startTime;
            if (StringUtils.isNotNull(eventInfo.getStartTime())) {
                reed.setStartTime(DLT698Constants.DATE_TIME_S + DLT698Utils.formatDateToHex16(eventInfo.getStartTime()));
            } else {
                // 正式环境
                reed.setStartTime(DLT698Constants.NULL_STR);
            }
            // 事件结束时间 2020 0200 endTime;
            if (StringUtils.isNotNull(eventInfo.getEndTime())) {
                reed.setEndTime(DLT698Constants.DATE_TIME_S + DLT698Utils.formatDateToHex16(eventInfo.getEndTime()));
            } else {
                reed.setEndTime(DLT698Constants.NULL_STR);
            }
            // 事件源 2024 0200 eventSource;
            reed.setEventSource(DLT698Constants.OCTET_STR + "0705" + addrVoltagePhase.getChannelNum());

            // 异常事件上报状态 3300 0200 eventReportType;
            reed.setEventReportType(DLT698Constants.NULL_STR);
            // 事件接口类型 3300 0206 eventControlType;
            if (StringUtils.isNotEmpty(eventInfo.getInterfaceType())) {
                reed.setEventControlType(DLT698Constants.ENUM_STR + DLT698Utils.strToHex16(eventInfo.getInterfaceType()));
            } else {
                reed.setEventControlType(DLT698Constants.NULL_STR);
            }

            // 事件异常类型 3300 0207 eventErrorType;
            if (StringUtils.isNotEmpty(eventInfo.getErrorType())) {
                reed.setEventErrorType(DLT698Constants.ENUM_STR + DLT698Utils.strToHex16(eventInfo.getErrorType()));
            } else {
                reed.setEventErrorType(DLT698Constants.NULL_STR);
            }
            // 判断异常事件是否为空
            if (StringUtils.isNotNull(eventInfo.getId()) && eventInfo.getId() > 0) {
                Map<String, Object> map = new HashMap<>(IntegerUtil.INTEGER_6);
                map.put("channelNum", addrVoltagePhase.getChannelNum());
                DaVoltageData voltageSample = null;
                ErrVoltageError voltageCalculate = null;
                if (StringUtils.isNotNull(eventInfo.getErrorDateTime())) {
                    map.put("measureTime", eventInfo.getErrorDateTime());
                    map.put("collectTime", eventInfo.getErrorDateTime());
                    // 如有误差时间数据，则根据误差时间数据查询采集数据
                    if (StringUtils.isNull(voltageSample)) {
                        voltageSample = daVoltageDataService.selectSampleDataByRecently(map);
                    }
                    // 如有误差时间数据，则根据误差时间数据查询计算数据
                    if (StringUtils.isNull(voltageCalculate)) {
                        voltageCalculate = voltageErrorService.selectVoltageCalculateByRecently(map);
                    }
                }
                if (StringUtils.isNotNull(voltageSample) && StringUtils.isNotNull(voltageSample.getId())) {
                    // 组装抽样数据
                    String framesData = this.checkMonitoringFramesData(voltageSample, addrVoltagePhase.getPhase());
                    reed.setEventMonitoringData(framesData);
                } else {
                    // 如抽样数据查询为空
                    reed.setEventMonitoringData(DLT698Constants.NULL_STR);
                }
                if (voltageCalculate != null && voltageCalculate.getId() != null) {
                    ControlConstants.ColumnFormat columnFormat = ControlConstants.ColumnFormat.ANGLE_AVERAGE_OLD;
                    String city = addrVoltagePhase.getChannelNum().substring(0, 2);
                    if ("42".equals(city) || "65".equals(city) || "35".equals(city)) {
                        // 当省份为 湖北 、新疆 、 福建 时，为兼容历史版本需要使用协议解析为-3（3位小数），其它省份默认使用通用版本协议
                        columnFormat = ControlConstants.ColumnFormat.ANGLE_AVERAGE;
                    }
                    // 组装讲算数据
                    String framesData = this.checkErrorDataFramesData(voltageCalculate, addrVoltagePhase.getPhase(), columnFormat);
                    reed.setEventErrorData(framesData);
                } else {
                    // 如讲算数据查询为空
                    reed.setEventErrorData(DLT698Constants.NULL_STR);
                }
            } else {
                reed.setEventMonitoringData(DLT698Constants.NULL_STR);
                reed.setEventErrorData(DLT698Constants.NULL_STR);
            }
            resuestErrorEventDataList.add(reed);
        }
        return resuestErrorEventDataList;
    }

    /**
     * 事件数据 转为 应答帧  301b  /  304C   /   3343
     *
     * @param control698Data
     */
    @Override
    public String eventDataToAnswerFrame(CommandFrameLog commandFrameLog, Control698Data control698Data, String eventCode) {
        StringBuilder frames = new StringBuilder();
        // 帧头
        frames.append(ControlConstants.FRAME_HEADER_68);
        // 数据域长度————————》先填写标识符，帧命令拼接完成全再进行验证替换
        frames.append(ControlConstants.DATA_LENGTH_FLAG);
        // 控制域
        frames.append(ControlConstants.CONTROL_CODE_C3);
        // 服务器地址   地址类型
        frames.append(ControlConstants.CONTROL_REGION_TYPE);

        // 服务器地址
        String addrFieldFrame = commandFrameLog.getChannelNum();
        frames.append(addrFieldFrame);
        // 客户机地址
        frames.append(control698Data.getCustomerAddress());
        // 帧头校验————————》先填写标识符，帧命令拼接完成全再进行验证替换
        frames.append(ControlConstants.FRAME_HEADER_FLAG);
        // 请求类型
        frames.append(ControlConstants.FRAME_EVENT_DATA_RESPONSE_NORMAL);
        // PIID
        frames.append(control698Data.getControlApudDatas().getPiid());
        // 请求参数分类 OAD
        frames.append(eventCode + ControlConstants.FRAME_REQUEST_NORMAL_OAD);

        // request 请求数据
        frames.append(control698Data.getControlApudDatas().getRequestRCSDFrozenDataType().getRcsdTypeStr());
        List<String> stringList = control698Data.getControlApudDatas().getRequestRCSDFrozenDataType().getRcsdList();

        // resultRecordType
        frames.append(ControlConstants.FRAME_RESULT_TYPE);

        // 数据逻辑判断
        DLT698Constants.EventDataFlag[] values = DLT698Constants.EventDataFlag.values();
        //创建忽略key大小写的map
        Map<String, String> refMap = new CaseInsensitiveMap<>();
        for (int i = 0; i < values.length; i++) {
            refMap.put(values[i].getCode(), values[i].getFileName());
        }
        // 添加数据域帧
        String dataValue = this.checkRequestErrorEventFrames(stringList, refMap, eventCode, control698Data, commandFrameLog);
        // 判断序号是否为空，如果为空，则事件传空
        if (StringUtils.isEmpty(dataValue) || "00".equals(dataValue.substring(IntegerUtil.INTEGER_0, IntegerUtil.INTEGER_2))) {
            // 发送数据长度
            frames.append(DLT698Constants.NULL_STR);
        } else {
            // 发送数据长度
            frames.append(DLT698Constants.ARRAY_LEN);
            frames.append(dataValue);
        }

        // 跟随上报信息域
        frames.append(ControlConstants.FRAME_FOLLOW_REPORT);
        // TimeTag 时间
        frames.append("01");
        frames.append(DLT698Utils.formatDateToHex16(new Date()));
        frames.append(ControlConstants.FRAME_AWAIT_TIME);
        // 校验帧FCS————————》先填写标识符，帧命令拼接完成全再进行验证替换
        frames.append(ControlConstants.FRAME_FOOTER_FLAG);
        //帧尾
        frames.append(ControlConstants.FRAME_FOOTER_16);

        String framesStr = frames.toString();
        // 转换数据长度
        framesStr = framesStr.replace(ControlConstants.DATA_LENGTH_FLAG, DLT698Utils.getHexDataLength(framesStr));
        //帧头校验码HCS
        String hcs = framesStr.substring(ControlConstants.HEADER_LENGTH, framesStr.indexOf(ControlConstants.FRAME_HEADER_FLAG));
        framesStr = framesStr.replace(ControlConstants.FRAME_HEADER_FLAG, DLT698Utils.cheakFramesStr(hcs));
        // 帧属校验码FCS
        String fcs = framesStr.substring(ControlConstants.HEADER_LENGTH, framesStr.indexOf(ControlConstants.FRAME_FOOTER_FLAG));
        framesStr = framesStr.replace(ControlConstants.FRAME_FOOTER_FLAG, DLT698Utils.cheakFramesStr(fcs));
        return framesStr.toUpperCase(Locale.ENGLISH);
    }

    /**
     * 事件数据 转为 应答帧 304C 异常事件数组
     *
     * @param control698Data
     */
    @Override
    public String eventDataToAnswerFrameArray(CommandFrameLog commandFrameLog, Control698Data control698Data, String eventCode) {
        StringBuilder frames = new StringBuilder();
        // 帧头
        frames.append(ControlConstants.FRAME_HEADER_68);
        // 数据域长度————————》先填写标识符，帧命令拼接完成全再进行验证替换
        frames.append(ControlConstants.DATA_LENGTH_FLAG);
        // 控制域
        frames.append(ControlConstants.CONTROL_CODE_C3);
        // 服务器地址   地址类型
        frames.append(ControlConstants.CONTROL_REGION_TYPE);

        // 服务器地址
        String addrFieldFrame = DLT698Utils.formatHighLow(commandFrameLog.getChannelNum());
        frames.append(addrFieldFrame);
        // 客户机地址
        frames.append(control698Data.getCustomerAddress());
        // 帧头校验————————》先填写标识符，帧命令拼接完成全再进行验证替换
        frames.append(ControlConstants.FRAME_HEADER_FLAG);
        // 请求类型
        frames.append(ControlConstants.FRAME_EVENT_DATA_RESPONSE_NORMAL);
        // PIID
        frames.append(control698Data.getControlApudDatas().getPiid());
        // 请求参数分类 OAD
        frames.append(eventCode + ControlConstants.FRAME_REQUEST_NORMAL_OAD);

        // request 请求数据
        frames.append(control698Data.getControlApudDatas().getRequestRCSDFrozenDataType().getRcsdTypeStr());
        List<String> stringList = control698Data.getControlApudDatas().getRequestRCSDFrozenDataType().getRcsdList();

        // resultRecordType
        frames.append(ControlConstants.FRAME_RESULT_TYPE);

        // 数据逻辑判断
        DLT698Constants.EventDataFlag[] values = DLT698Constants.EventDataFlag.values();
        //创建忽略key大小写的map
        Map<String, String> refMap = new CaseInsensitiveMap<>();
        for (int i = 0; i < values.length; i++) {
            refMap.put(values[i].getCode(), values[i].getFileName());
        }

        // 添加数据域帧
        String dataValue = this.checkRequestErrorEventFramesArrays(stringList, refMap, commandFrameLog);
        // 判断序号是否为空，如果为空，则事件传空
        if (StringUtils.isEmpty(dataValue) || "00".equals(dataValue.substring(IntegerUtil.INTEGER_0, IntegerUtil.INTEGER_2))) {
            // 发送数据长度
            frames.append(DLT698Constants.NULL_STR);
        } else {
            frames.append(dataValue);
        }

        // 跟随上报信息域
        frames.append(ControlConstants.FRAME_FOLLOW_REPORT);
        // TimeTag 时间
        frames.append("01");
        frames.append(DLT698Utils.formatDateToHex16(new Date()));
        frames.append(ControlConstants.FRAME_AWAIT_TIME);
        // 校验帧FCS————————》先填写标识符，帧命令拼接完成全再进行验证替换
        frames.append(ControlConstants.FRAME_FOOTER_FLAG);
        //帧尾
        frames.append(ControlConstants.FRAME_FOOTER_16);

        String framesStr = frames.toString();
        // 转换数据长度
        framesStr = framesStr.replace(ControlConstants.DATA_LENGTH_FLAG, DLT698Utils.getHexDataLength(framesStr));
        //帧头校验码HCS
        String hcs = framesStr.substring(ControlConstants.HEADER_LENGTH, framesStr.indexOf(ControlConstants.FRAME_HEADER_FLAG));
        framesStr = framesStr.replace(ControlConstants.FRAME_HEADER_FLAG, DLT698Utils.cheakFramesStr(hcs));
        // 帧属校验码FCS
        String fcs = framesStr.substring(ControlConstants.HEADER_LENGTH, framesStr.indexOf(ControlConstants.FRAME_FOOTER_FLAG));
        framesStr = framesStr.replace(ControlConstants.FRAME_FOOTER_FLAG, DLT698Utils.cheakFramesStr(fcs));
        return framesStr.toUpperCase(Locale.ENGLISH);
    }


    /**
     * 组装异常事件命令帧中数据部份帧
     *
     * @param stringList 请求集合
     * @param refMap     反射数据
     * @param eventCode  类型
     * @return
     */
    public String checkRequestErrorEventFrames(List<String> stringList, Map<String, String> refMap, String eventCode, Control698Data control698Data, CommandFrameLog commandFrameLog) {
        StringBuilder frames = new StringBuilder();
        ResuestErrorEventData requestEventData = checkRequestEventData(control698Data, commandFrameLog);
        for (String str : stringList) {
            if (refMap.get(str) != null) {
                Object fieldValue = ReflectUtils.getFieldValue(requestEventData, refMap.get(str));
                if (StringUtils.isNotNull(fieldValue)) {
                    frames.append(fieldValue + "");
                } else {
                    frames.append("00");
                    System.out.println("Key 为 " + str + " ，字段名为" + refMap.get(str) + " ,反射值为空");
                }
            } else {
                frames.append("00");
                System.out.println("Key 为 " + str + " ，字段名为" + refMap.get(str) + " 无数据！");
            }
        }
        return frames.toString().toUpperCase(Locale.ENGLISH);
    }

    /**
     * 组装异常事件命令帧中数据部份帧
     *
     * @param stringList       请求集合
     * @param refMap           反射数据
     * @param commandFrameLog 类型
     * @return
     */
    public String checkRequestErrorEventFramesArrays(List<String> stringList, Map<String, String> refMap, CommandFrameLog commandFrameLog) {
        StringBuilder frames = new StringBuilder();
        // 1、获取事件最近10条记录
        EventInfoData eventInfo = new EventInfoData();
        eventInfo.setChannelNum(commandFrameLog.getChannel());
        List<EventInfoData> eventInfoList = new ArrayList<>();
        if ("A".equals(commandFrameLog.getPhase())) {
            // 2022-11-17 添加异常事件只有A通道时才进行查询，当不为A通道时不查询
            eventInfo.setCreateTime(DateUtils.addMinutes(DLT698Utils.getLastPointDate(new Date()), -15));
            eventInfoList = inspectionEventInfoService.selectInspectionEventInfoListBy(eventInfo);
        }
        List<ResuestErrorEventData> requestEventDataList = checkRequestEventDataArray(commandFrameLog, eventInfoList);
        Integer eventCount = 0;
        for (ResuestErrorEventData requestEventData : requestEventDataList) {
            eventCount++;
            for (String str : stringList) {
                if (refMap.get(str) != null) {
                    Object fieldValue = ReflectUtils.getFieldValue(requestEventData, refMap.get(str));
                    if (StringUtils.isNotNull(fieldValue)) {
                        frames.append(fieldValue + "");
                    } else {
                        frames.append("00");
                        System.out.println("Key 为 " + str + " ，字段名为" + refMap.get(str) + " ,反射值为空");
                    }
                } else {
                    frames.append("00");
                    System.out.println("Key 为 " + str + " ，字段名为" + refMap.get(str) + " 无数据！");
                }
            }
        }
        String tempFrame = ProtocolUtils.intToHex16(eventCount) + frames.toString();
        return tempFrame.toUpperCase(Locale.ENGLISH);
    }
}
