package com.oetsky.project.serialsetting.serial.service;

import com.oetsky.project.dataselect.domain.DaVoltageData;
import com.oetsky.project.serialsetting.serial.domain.Control698Data;
import com.oetsky.project.serialsetting.serial.domain.CommandFrameLog;

/**
 * @Author: zhangw
 * @Date: 2023/6/15
 */

public interface IControlService {

    /**
     * 组装互感器监测数据命令帧中数据帧
     *
     * @param voltageSample 抽样数据
     * @param phase         相序
     * @return
     */
    String checkMonitoringFramesData(DaVoltageData voltageSample, String phase);


    /**
     *  冻结数据 转为 应答帧  5002
     * @param commandFrameLog
     * @param control698Data
     * @return
     */
    String frozenDataToAnswerFrame(CommandFrameLog commandFrameLog, Control698Data control698Data);

    /**
     *  事件数据 转为 应答帧  301b
     * @param commandFrameLog
     * @param control698Data
     * @param eventCode
     * @return
     */
    String eventDataToAnswerFrame(CommandFrameLog commandFrameLog,Control698Data control698Data, String eventCode);

    /**
     *  事件数据 转为 应答帧  301b  /  304C   /   3343
     * @param control698Data
     * @param eventCode
     * @return
     */
    public String eventDataToAnswerFrameArray(CommandFrameLog commandFrameLog, Control698Data control698Data, String eventCode);

}
