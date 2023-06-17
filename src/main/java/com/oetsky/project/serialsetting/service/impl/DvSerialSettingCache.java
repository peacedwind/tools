package com.oetsky.project.serialsetting.service.impl;

import cn.hutool.core.util.NumberUtil;
import com.google.common.collect.Lists;
import com.oetsky.common.frame.utils.IntegerUtil;
import com.oetsky.common.utils.StringUtils;
import com.oetsky.common.utils.spring.SpringUtils;
import com.oetsky.project.serialsetting.domain.DvSerialSetting;
import com.oetsky.project.serialsetting.domain.SerialPortBean;
import com.oetsky.project.serialsetting.service.IDvSerialSettingService;
import com.oetsky.project.serialsetting.serial.utils.SerialUtils;
import org.springframework.stereotype.Service;
import purejavacomm.CommPortIdentifier;

import java.util.Enumeration;
import java.util.List;

/**
 * 485通信配置 对象cache
 *
 * @author xiangzc
 * @date 2022-3-30 18:34:37
 */
@Service("serialSettingCache")
public class DvSerialSettingCache {

    private static volatile List<DvSerialSetting> arrayList = null;

    public static List<DvSerialSetting> getAllSerialSetting() {
        if (arrayList == null) {
            synchronized (DvSerialSettingCache.class) {
                if (arrayList == null) {
                    arrayList = SpringUtils.getBean(IDvSerialSettingService.class)
                        .selectDvSerialSettingList(new DvSerialSetting());
                }
            }
        }
        return arrayList;
    }

    /**
     * 根据查询数据
     *
     * @param serialNumber
     * @return
     */
    public static synchronized DvSerialSetting getDvSerialSettingBySerialNumber(
        String serialNumber) {
        List<DvSerialSetting> dvSerialSettingList = arrayList;
        if (dvSerialSettingList == null || dvSerialSettingList.size() == 0) {
            dvSerialSettingList = getAllSerialSetting();
        }
        for (DvSerialSetting dss : dvSerialSettingList) {
            if (dss.getSerialNumber().equals(serialNumber)) {
                return dss;
            }
        }
        return null;
    }

    /**
     * 根据查询数据
     *
     * @param deviceType
     * @return
     */
    public static synchronized DvSerialSetting getDvSerialSettingByDeviceType(String deviceType) {
        List<DvSerialSetting> dvSerialSettingList = arrayList;
        if (dvSerialSettingList == null || dvSerialSettingList.size() == 0) {
            dvSerialSettingList = getAllSerialSetting();
        }
        for (DvSerialSetting dss : dvSerialSettingList) {
            if (dss.getDeviceType().equals(deviceType)) {
                return dss;
            }
        }
        return null;
    }

    /**
     * 测试串口号，允许重复
     */
    public static final String DEFAULT_SERIAL_NUMBER = "/dev/COMXXX";

    public static List<SerialPortBean> findSerialPort() {
        // 获得当前所有可用串口
        Enumeration<CommPortIdentifier> portList = CommPortIdentifier.getPortIdentifiers();
        List<SerialPortBean> serialPortBeans = Lists.newArrayList();
        SerialPortBean emptyBean = new SerialPortBean();
        emptyBean.setSerialCode("");
        emptyBean.setSerialLabel("--选择串口--");
        serialPortBeans.add(emptyBean);
        SerialPortBean emptyBean2 = new SerialPortBean();
        emptyBean2.setSerialCode(DEFAULT_SERIAL_NUMBER);
        emptyBean2.setSerialLabel(DEFAULT_SERIAL_NUMBER);
        serialPortBeans.add(emptyBean2);
        // 将可用串口名添加到List并返回该List
        while (portList.hasMoreElements()) {
            String portName = portList.nextElement().getName();
            SerialPortBean serialPortBean = new SerialPortBean();
            serialPortBean.setSerialCode(portName);
            String label = getSerialLabel(portName);
            serialPortBean.setSerialLabel(label);
            serialPortBeans.add(serialPortBean);
        }
        //固定串口号 ttyRS1~ttyRS4
        aliasCommData(serialPortBeans);
        return serialPortBeans;
    }

    /**
     * 固定串口号 ttyRS1~ttyRS4
     *
     * @param serialPortBeans
     */
    private static void aliasCommData(List<SerialPortBean> serialPortBeans) {
        String aliasCommonNamePrefix = "/dev/ttyRS";
        for (int i = 1; i <= IntegerUtil.INTEGER_4; i++) {
            SerialPortBean emptyBean = new SerialPortBean();
            emptyBean.setSerialCode(aliasCommonNamePrefix + i);
            String realSerialName = SerialUtils.realSerialName(emptyBean.getSerialCode());
            if (!realSerialName.equals(emptyBean.getSerialCode())) {
                // 真实串口号拼在字符串后面
                emptyBean.setSerialLabel(aliasCommonNamePrefix + i + "【" + realSerialName + "】");
            } else {
                emptyBean.setSerialLabel(aliasCommonNamePrefix + i);
            }
            serialPortBeans.add(emptyBean);
        }
    }


    public static String getSerialLabel(String portName) {
        if (StringUtils.isEmpty(portName)) {
            return StringUtils.EMPTY;
        }
        if (portName.toLowerCase().contains("ttySA".toLowerCase())) {
            String numStr = portName.substring(portName.length() - 1, portName.length());
            if (NumberUtil.isNumber(numStr)) {
                Integer num = Integer.valueOf(numStr);
                return String.format("A%d|B%d", num + 1, num + 1);
            }
        }
        return portName;
    }


    /**
     * 置空 重新赋值。
     */
    public static synchronized void setCacheNull() {
        arrayList = null;
    }
}
