package com.oetsky.project.serialsetting.service.impl;

import com.oetsky.common.utils.DateUtils;
import com.oetsky.common.utils.StringUtils;
import com.oetsky.common.utils.text.Convert;
import com.oetsky.project.serialsetting.domain.DvSerialSetting;
import com.oetsky.project.serialsetting.mapper.DvSerialSettingMapper;
import com.oetsky.project.serialsetting.service.IDvSerialSettingService;
import com.oetsky.project.system.dict.utils.DictUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * RS-485Service业务层处理
 *
 * @author huwm
 * @date 2022-03-04
 */
@Service
public class DvSerialSettingServiceImpl implements IDvSerialSettingService {
    @Autowired
    private DvSerialSettingMapper dvSerialSettingMapper;

    /**
     * 查询RS-485
     *
     * @param id RS-485主键
     * @return RS-485
     */
    @Override
    public DvSerialSetting selectDvSerialSettingById(Long id) {
        return dvSerialSettingMapper.selectDvSerialSettingById(id);
    }

    /**
     * 查询RS-485列表
     *
     * @param deviceType 设备类型
     * @return RS-485集合
     */
    @Override
    public Integer selectCountByDeviceType(String deviceType) {
        return dvSerialSettingMapper.selectCountByDeviceType(deviceType);
    }

    /**
     * 查询RS-485列表
     *
     * @param dvSerialSetting RS-485
     * @return RS-485
     */
    @Override
    public List<DvSerialSetting> selectDvSerialSettingList(DvSerialSetting dvSerialSetting) {
        return dvSerialSettingMapper.selectDvSerialSettingList(dvSerialSetting);
    }

    /**
     * 新增RS-485
     *
     * @param dvSerialSetting RS-485
     * @return 结果
     */
    @Override
    public int insertDvSerialSetting(DvSerialSetting dvSerialSetting) {
        if (dvSerialSetting.getCreateTime() == null) {
            dvSerialSetting.setCreateTime(DateUtils.getNowDate());
            dvSerialSetting.setUpdateTime(DateUtils.getNowDate());
        }
        int row = dvSerialSettingMapper.insertDvSerialSetting(dvSerialSetting);
        DvSerialSettingCache.setCacheNull();
        return row;
    }

    /**
     * 数据校验
     * @param dvSerialSetting
     */
    @Override
    public void validDvSerialSetting(DvSerialSetting dvSerialSetting) {
        if (dvSerialSetting == null) {
            throw new RuntimeException("数据不能为空");
        }
        //设备名称
        String deviceName = dvSerialSetting.getDeviceName();
        if (StringUtils.isBlank(deviceName)) {
            throw new RuntimeException("设别名称不能为空");
        }
        //设备类型
        String deviceType = dvSerialSetting.getDeviceType();
        if (!DictUtils.containsTypeAndLabel("sys_485_device_type", deviceType)) {
            throw new RuntimeException("不符合规范的设备类型的值");
        }
        //通讯协议
        String agreementType = dvSerialSetting.getAgreementType();
        if (!DictUtils.containsTypeAndLabel("sys_protocol_type", agreementType)) {
            throw new RuntimeException("不符合规范的通讯协议的值");
        }
        //串口
        String serialNumber = dvSerialSetting.getSerialNumber();
        if (StringUtils.isBlank(serialNumber)) {
            throw new RuntimeException("串口号不能为空");
        }

       /* List<SerialPortBean> serialPorts = DvSerialSettingCache.findSerialPort();
        if (CollectionUtils.isNotEmpty(serialPorts)){
            boolean flag = serialPorts.stream().map(SerialPortBean::getSerialCode)
                    .anyMatch(serialNumber::equals);
            if (!flag){
                throw new RuntimeException("不符合规范的串口号的值");
            }
        }*/
        //波特率
        Integer baudRate = dvSerialSetting.getBaudRate();
        if (!DictUtils.containsTypeAndLabel("485_baud_rate",String.valueOf(baudRate))){
            throw new RuntimeException("不符合规范的波特率的值");
        }
        //数据位
        Integer dataBit = dvSerialSetting.getDataBit();
        if (!DictUtils.containsTypeAndLabel("485_data_bit",String.valueOf(dataBit))){
            throw new RuntimeException("不符合规范的数据位的值");
        }
        //校验位
        Integer parityBit = dvSerialSetting.getParityBit();
        if (!DictUtils.containsTypeAndLabel("485_check_bit",String.valueOf(parityBit))){
            throw new RuntimeException("不符合规范的校验位的值");
        }
        //停止位
        Integer stopBit = dvSerialSetting.getStopBit();
        if (!DictUtils.containsTypeAndLabel("485_stop_bit",String.valueOf(stopBit))){
            throw new RuntimeException("不符合规范的停止位的值");
        }

    }

    /**
     * 修改RS-485
     *
     * @param dvSerialSetting RS-485
     * @return 结果
     */
    @Override
    public int updateDvSerialSetting(DvSerialSetting dvSerialSetting) {
        dvSerialSetting.setUpdateTime(DateUtils.getNowDate());
        int row = dvSerialSettingMapper.updateDvSerialSetting(dvSerialSetting);
        DvSerialSettingCache.setCacheNull();
        return row;
    }

    /**
     * 批量删除RS-485
     *
     * @param ids 需要删除的RS-485主键
     * @return 结果
     */
    @Override
    public int deleteDvSerialSettingByIds(String ids) {
        int row = dvSerialSettingMapper.deleteDvSerialSettingByIds(Convert.toStrArray(ids));
        DvSerialSettingCache.setCacheNull();
        return row;
    }

    /**
     * 批量删除
     *
     * @return 结果
     */
    @Override
    public int truncateDvSerialSetting(){
        int row = dvSerialSettingMapper.truncateDvSerialSetting();
        DvSerialSettingCache.setCacheNull();
        return row;
    }

    /**
     * 批量新增dvSerialSetting信息
     *
     * @param dvSerialSettingList RS-485列表
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int batchDvSerialSettingList(List<DvSerialSetting> dvSerialSettingList) {
        int result = 0;
        if (StringUtils.isNotNull(dvSerialSettingList)) {
            List<DvSerialSetting> list = new ArrayList<DvSerialSetting>();
            for (DvSerialSetting dvSerialSetting : dvSerialSettingList) {
                if (dvSerialSetting.getCreateTime() == null) {
                    dvSerialSetting.setCreateTime(DateUtils.getNowDate());
                }
                list.add(dvSerialSetting);
                if (list.size() >= 500) {
                    dvSerialSettingMapper.batchDvSerialSettingList(list);
                    list.clear();
                    result = 1;
                }
            }
            if (list.size() > 0) {
                dvSerialSettingMapper.batchDvSerialSettingList(list);
                list.clear();
                result = 1;
            }
        }
        DvSerialSettingCache.setCacheNull();
        return result;
    }

    /**
     * 删除RS-485信息
     *
     * @param id RS-485主键
     * @return 结果
     */
    @Override
    public int deleteDvSerialSettingById(Long id) {
        int row = dvSerialSettingMapper.deleteDvSerialSettingById(id);
        DvSerialSettingCache.setCacheNull();
        return row;
    }

    /**
     * 修改设备状态
     *
     * @param id
     * @param interfaceStatus 设备状态
     * @return 结果
     */
    @Override
    public int updateDvSerialSettingByStatus(Long id, Integer interfaceStatus) {
        int row = dvSerialSettingMapper.updateDvSerialSettingByStatus(id, interfaceStatus);
        DvSerialSettingCache.setCacheNull();
        return row;
    }
}
