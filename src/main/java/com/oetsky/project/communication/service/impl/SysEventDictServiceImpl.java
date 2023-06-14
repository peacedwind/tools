package com.oetsky.project.communication.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.convert.Convert;
import com.oetsky.common.frame.domain.AgreementType;
import com.oetsky.common.frame.utils.ProtocolUtils;
import com.oetsky.common.utils.DateUtils;
import com.oetsky.common.utils.StringUtils;
import com.oetsky.project.communication.domain.SysEventDict;
import com.oetsky.project.communication.domain.SysUnit;
import com.oetsky.project.communication.mapper.SysEventDictMapper;
import com.oetsky.project.communication.service.ISysEventDictService;
import com.oetsky.project.netty5service.NettyDomain;
import io.netty.channel.ChannelHandlerContext;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 事件数据字典Service业务层处理
 *
 * @author xiangzc
 * @date 2023-02-08
 */
@Service
public class SysEventDictServiceImpl implements ISysEventDictService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SysEventDictServiceImpl.class);
    @Autowired
    private SysEventDictMapper sysEventDictMapper;

    /**
     * 查询事件数据字典
     *
     * @param id 事件数据字典主键
     * @return 事件数据字典
     */
    @Override
    public SysEventDict selectSysEventDictById(Long id) {
        return sysEventDictMapper.selectSysEventDictById(id);
    }

    /**
     * 查询事件数据字典列表
     *
     * @param sysEventDict 事件数据字典
     * @return 事件数据字典
     */
    @Override
    public List<SysEventDict> selectSysEventDictList(SysEventDict sysEventDict) {
        return sysEventDictMapper.selectSysEventDictList(sysEventDict);
    }

    /**
     * 新增事件数据字典
     *
     * @param sysEventDict 事件数据字典
     * @return 结果
     */
    @Override
    public int insertSysEventDict(SysEventDict sysEventDict) {
        if (sysEventDict.getCreateTime() == null) {
            sysEventDict.setCreateTime(DateUtils.getNowDate());
        }
        return sysEventDictMapper.insertSysEventDict(sysEventDict);
    }

    /**
     * 修改事件数据字典
     *
     * @param sysEventDict 事件数据字典
     * @return 结果
     */
    @Override
    public int updateSysEventDict(SysEventDict sysEventDict) {
        sysEventDict.setUpdateTime(DateUtils.getNowDate());
        return sysEventDictMapper.updateSysEventDict(sysEventDict);
    }

    /**
     * 批量删除事件数据字典
     *
     * @param ids 需要删除的事件数据字典主键
     * @return 结果
     */
    @Override
    public int deleteSysEventDictByIds(String ids) {
        return sysEventDictMapper.deleteSysEventDictByIds(Convert.toStrArray(ids));
    }

    /**
     * 批量新增sysEventDict信息
     *
     * @param sysEventDictList 事件数据字典列表
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int batchSysEventDictList(List<SysEventDict> sysEventDictList) {
        int result = 0;
        if (StringUtils.isNotNull(sysEventDictList)) {
            List<SysEventDict> list = new ArrayList<SysEventDict>();
            for (SysEventDict sysEventDict : sysEventDictList) {
                if (sysEventDict.getCreateTime() == null) {
                    sysEventDict.setCreateTime(DateUtils.getNowDate());
                }
                list.add(sysEventDict);
                if (list.size() >= 500) {
                    sysEventDictMapper.batchSysEventDictList(list);
                    list.clear();
                    result = 1;
                }
            }
            if (list.size() > 0) {
                sysEventDictMapper.batchSysEventDictList(list);
                list.clear();
                result = 1;
            }
        }
        return result;
    }

    /**
     * 删除事件数据字典信息
     *
     * @param id 事件数据字典主键
     * @return 结果
     */
    @Override
    public int deleteSysEventDictById(Long id) {
        return sysEventDictMapper.deleteSysEventDictById(id);
    }


    /**
     * 发送事件消息
     * @param sysUnit 事件采集单元
     * @param contextKeys 建立通道消息key
     * @param protectKeys 建立采集单元服务key
     * @param frameStr 消息报文
     */
    @Override
    public void sendEventMsg(SysUnit sysUnit, String contextKeys, String protectKeys,
        String frameStr) {
        if (StringUtils.isNotEmpty(frameStr)) {
            if (contextKeys
                .equals(NettyDomain.getNettyServiceContext(sysUnit.getUnitServiceProt()))) {
                if (NettyDomain.getChannelPool(sysUnit.getUnitServiceProt()) != null) {
                    Iterator<Entry<String, ChannelHandlerContext>> it = NettyDomain
                        .getChannelPool(sysUnit.getUnitServiceProt()).entrySet().iterator();
                    if (CollUtil.isNotEmpty(it)) {
                        try {
                            while (it.hasNext()) {
                                if (protectKeys.equals(NettyDomain.getNettyServicePortect(
                                    sysUnit.getUnitServiceProt()))
                                    &&
                                    contextKeys.equals(NettyDomain.getNettyServiceContext(
                                        sysUnit.getUnitServiceProt()))) {
                                    if (CollUtil.isNotEmpty(it)) {
                                        Entry<String, ChannelHandlerContext> next = it.next();
                                        ChannelHandlerContext ctx = next.getValue();
                                        ctx.writeAndFlush(ProtocolUtils.sendFrameData(frameStr));
                                        LOGGER.info("【Netty服务端】【{}】发送数据【{}】报文为【{}】",
                                            sysUnit.getUnitServiceProt(),
                                            AgreementType.TERMINAL_UP_RESULT.getDesc()
                                            , frameStr);
                                    }
                                }
                            }
                        } catch (Exception e) {
                            LOGGER.error("【Netty服务端】端口【{}】发送数据【{}】失败,等待下次发送...",
                                sysUnit.getUnitServiceProt(),
                                AgreementType.TERMINAL_UP_RESULT.getDesc());
                        }
                    }
                }
            }
        }
    }
}
