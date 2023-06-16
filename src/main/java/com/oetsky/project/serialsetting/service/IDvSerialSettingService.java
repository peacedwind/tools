package com.oetsky.project.serialsetting.service;

import com.oetsky.project.serialsetting.domain.DvSerialSetting;

import java.util.List;

/**
 * RS-485Service接口
 *
 * @author huwm
 * @date 2022-03-04
 */
public interface IDvSerialSettingService {
    /**
     * 查询RS-485
     *
     * @param id RS-485主键
     * @return RS-485
     */
    DvSerialSetting selectDvSerialSettingById(Long id);

    /**
     * 查询RS-485列表
     *
     * @param deviceType 设备类型
     * @return RS-485集合
     */
    Integer selectCountByDeviceType(String deviceType);

    /**
     * 查询RS-485列表
     *
     * @param dvSerialSetting RS-485
     * @return RS-485集合
     */
    List<DvSerialSetting> selectDvSerialSettingList(DvSerialSetting dvSerialSetting);

    /**
     * 新增RS-485
     *
     * @param dvSerialSetting RS-485
     * @return 结果
     */
    int insertDvSerialSetting(DvSerialSetting dvSerialSetting);

    void validDvSerialSetting(DvSerialSetting dvSerialSetting);

    /**
     * 修改RS-485
     *
     * @param dvSerialSetting RS-485
     * @return 结果
     */
    int updateDvSerialSetting(DvSerialSetting dvSerialSetting);

    /**
     * 批量删除RS-485
     *
     * @param ids 需要删除的RS-485主键集合
     * @return 结果
     */
    int deleteDvSerialSettingByIds(String ids);

    /**
     * 批量删除
     *
     * @return 结果
     */
    int truncateDvSerialSetting();

    /**
     * 删除RS-485信息
     *
     * @param id RS-485主键
     * @return 结果
     */
    int deleteDvSerialSettingById(Long id);

    /**
     * 批量新增RS-485
     *
     * @param dvSerialSettingList RS-485列表
     * @return 结果
     */
    int batchDvSerialSettingList(List<DvSerialSetting> dvSerialSettingList);

    /**
     * 修改设备状态
     *
     * @param id
     * @param interfaceStatus 设备状态
     * @return 结果
     */
    int updateDvSerialSettingByStatus(Long id, Integer interfaceStatus);
}
