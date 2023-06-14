package com.oetsky.common.frame.service;

import cn.hutool.core.convert.Convert;
import com.oetsky.common.frame.domain.AgreementType;
import com.oetsky.common.frame.frameBean.ProtocolFrameContentData;
import com.oetsky.common.frame.frameBean.ProtocolFrameData;
import com.oetsky.common.frame.utils.LogMsgUtil;
import com.oetsky.common.frame.utils.ProtocolUtils;
import java.util.Locale;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 报文公共解析工具类
 */
public class AnalysisFrame {

    public static final Logger LOGGER = LoggerFactory.getLogger(ProtocolUtils.class);

    /**
     * 完成公共部分解析数据 并返回未解析数据部分
     *
     * @param frameStr 报文内容
     * @return 数据部分报文内容
     */
    public static ProtocolFrameData analysisPublicFrame(String frameStr) {
        ProtocolFrameData pfd = new ProtocolFrameData();
        StringBuffer sb = new StringBuffer(frameStr.toUpperCase(Locale.ENGLISH));
        // 帧头
        pfd.setHeader68(ProtocolUtils.subFrameStr(sb, 1));
        // 长度
        pfd.setControlLeng(ProtocolUtils.subFrameStr(sb, 2));
        String controlleng = ProtocolUtils.formatHighLow(pfd.getControlLeng());
        pfd.setControlLengInt(Convert.toInt(ProtocolUtils.hex16ToStr(controlleng)));
        //控制域
        pfd.setControlRegion(ProtocolUtils.subFrameStr(sb, 1));
        // 内容部分
        ProtocolFrameContentData frameData = new ProtocolFrameContentData();
        // 帧ID
        String frameId = ProtocolUtils.subFrameStr(sb, 2);
        frameData.setFrameId(frameId);

        // 类型标识
        String frameType = ProtocolUtils.subFrameStr(sb, 1);
        AgreementType agreementType = AgreementType.getProtocolTypeByData(frameType);
        if (agreementType == null) {
            LOGGER.error("报文帧ID为[{}]类型标识错误,报文[{}]!!!", LogMsgUtil.getSecMsg(frameId),
                LogMsgUtil.getSecMsg(frameStr));
            return null;
        }
        frameData.setFrameDataType(frameType);
        if (sb.length() > 4) {
            // 报文内容数据（长度从0开始，少两位则只需要减1）
            String frameDataStr = ProtocolUtils.subFrameStr(sb, (pfd.getControlLengInt() - 4));
//        if (ProtocolUtils.FRAME_GET_UP.equals(pfd.getControlRegion())
//                || ProtocolUtils.FRAME_SET_UP.equals(pfd.getControlRegion())) {
//            // 报文内容
//        }
            frameData.setFrameData(frameDataStr);
        }
        pfd.setFrameData(frameData);
        // 帧校验FCS
        pfd.setFinishChecks(ProtocolUtils.subFrameStr(sb, 1));
        // 帧尾
        pfd.setFooter16(ProtocolUtils.subFrameStr(sb, 1));
        return pfd;
    }
}
