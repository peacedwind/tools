package com.oetsky.project.serialsetting.serial.handle;

import cn.hutool.core.collection.CollectionUtil;
import com.oetsky.common.frame.utils.LogMsgUtil;
import com.oetsky.common.utils.StringUtils;
import com.oetsky.common.utils.spring.SpringUtils;
import com.oetsky.project.serialsetting.serial.SerialHelper;
import com.oetsky.project.serialsetting.serial.SerialMessageHandle;
import com.oetsky.project.serialsetting.serial.domain.Control698Data;
import com.oetsky.project.serialsetting.serial.domain.ControlApudDatas;
import com.oetsky.project.serialsetting.serial.domain.RequestOAD;
import com.oetsky.project.serialsetting.serial.service.IControlService;
import com.oetsky.project.serialsetting.serial.utils.ControlConstants;
import com.oetsky.project.serialsetting.serial.utils.DLT698Utils;
import com.oetsky.project.serialsetting.serial.utils.SerialUtils;
import com.oetsky.project.serialsetting.serial.domain.CommandFrameLog;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Locale;

/**
 * @author zhangw
 */
public class SanHuiMessageHandle implements SerialMessageHandle {

    private static final Logger LOGGER = LoggerFactory.getLogger(SanHuiMessageHandle.class);
    private IControlService controlService = SpringUtils.getBean(IControlService.class);

    @Override
    public void handle(Object message, SerialHelper helper) {
        List<String> messageParts = (List<String>) message;
        if (CollectionUtil.isNotEmpty(messageParts)) {
            messageParts.forEach(singleMessage -> {
                handleCommandFrame(singleMessage, helper);
            });
        }
    }

    /**
     *  解析
     * @param commandFrame
     * @param helper
     */
    public void handleCommandFrame(String commandFrame, SerialHelper helper) {
        if (StringUtils.isEmpty(commandFrame)) {
            return;
        }
        Control698Data control698Data = DLT698Utils.checkControlStr(commandFrame,
                SerialUtils.serialName + helper.getAppName());
        if (control698Data == null) {
            LOGGER.error("[DLT-698][命令帧异常][帧命令无法解析]:{}", commandFrame);
            return;
        }
        ControlApudDatas controlApudDatas = control698Data.getControlApudDatas();
        RequestOAD requestOAD = controlApudDatas.getRequestOAD();
        // 互感器采集异常事件
        if (requestOAD.getDataOi().equalsIgnoreCase(
            ControlConstants.DataFlag.CVT_ACQUISITION_OF_ABNORMAL_EVENTS.getCode())) {
            LOGGER.info("[DLT-698][命令帧][{}]：{}",
                ControlConstants.DataFlag.CVT_ACQUISITION_OF_ABNORMAL_EVENTS.getDesc(),
                commandFrame);
            this.sendCvtGatheringEvent(control698Data, helper);
        }
        // 冻结数据
        else if (requestOAD.getDataOi()
            .equalsIgnoreCase(ControlConstants.DataFlag.CVT_FROZEN_DATA.getCode())) {
            LOGGER.info("[DLT-698][命令帧][{}]：{}",
                ControlConstants.DataFlag.CVT_FROZEN_DATA.getDesc(), commandFrame);
            this.sendCvtFrozenData(control698Data, helper);
        } else {
            // 串口设置页面采集终端指示灯 - 红灯 表示收到采集终端命令帧，但是帧是异常的
            LOGGER.info("[DLT-698][命令帧异常][OAD不匹配]：{}", commandFrame);
        }
    }

    public void sendCvtGatheringEvent(Control698Data control698Data,
        SerialHelper helper) {
        // 截取命令帧里的地址域
        String addrFieldFrame = control698Data.getServerAddress();
        // 解析终端命令帧
        CommandFrameLog commandFrameLog = DLT698Utils.parseCommandFrame(
            control698Data);
        // 通过通道号 获取 应答帧
        String sendFrame = controlService.eventDataToAnswerFrame(commandFrameLog, control698Data,
                ControlConstants.DataFlag.CVT_ACQUISITION_OF_ABNORMAL_EVENTS.getCode());
       /* if (ConfigCache.eventArrayStatus()) {
            sendFrame = controlService.eventDataToAnswerFrame(commandFrameLog, control698Data,
                ControlConstants.DataFlag.CVT_ACQUISITION_OF_ABNORMAL_EVENTS.getCode());
        } else {
            sendFrame = controlService.eventDataToAnswerFrameArray(commandFrameLog, control698Data,
                ControlConstants.DataFlag.CVT_ACQUISITION_OF_ABNORMAL_EVENTS.getCode());
        }*/
        helper.writeData(SerialUtils.hexStringToByte(sendFrame.toUpperCase(Locale.ENGLISH)),
            "互感器采集异常事件");
        LOGGER.info("[DLT-698][应答帧][互感器采集异常事件][" + LogMsgUtil.getSecMsg(
            commandFrameLog.getDate()) + "][通道" + LogMsgUtil.getSecMsg(
            commandFrameLog.getChannelNum()) + "]:" + LogMsgUtil.getSecMsg(sendFrame));
    }

    public void sendCvtFrozenData( Control698Data control698Data,
        SerialHelper helper) {
        // 解析终端命令帧
        CommandFrameLog commandFrameLog = DLT698Utils.parseCommandFrame(
            control698Data);
        String sendFrame = controlService.frozenDataToAnswerFrame(commandFrameLog, control698Data);
        helper.writeData(SerialUtils.hexStringToByte(sendFrame.toUpperCase(Locale.ENGLISH)),
            "冻结数据");
        LOGGER.info("[DLT-698][应答帧][冻结数据][" + LogMsgUtil.getSecMsg(commandFrameLog.getDate())
            + "][通道" + LogMsgUtil.getSecMsg(commandFrameLog.getChannelNum()) + "]:"
            + LogMsgUtil.getSecMsg(sendFrame));
    }

}
