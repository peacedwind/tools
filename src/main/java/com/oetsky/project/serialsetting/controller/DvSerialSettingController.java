package com.oetsky.project.serialsetting.controller;

import com.oetsky.common.utils.StringUtils;
import com.oetsky.common.utils.poi.ExcelUtil;
import com.oetsky.common.utils.text.Convert;
import com.oetsky.framework.aspectj.lang.annotation.Log;
import com.oetsky.framework.aspectj.lang.enums.BusinessType;
import com.oetsky.framework.web.controller.BaseController;
import com.oetsky.framework.web.domain.AjaxResult;
import com.oetsky.framework.web.page.TableDataInfo;
import com.oetsky.project.serialsetting.domain.DvSerialSetting;
import com.oetsky.project.serialsetting.service.IDvSerialSettingService;
import com.oetsky.project.serialsetting.service.impl.DvSerialSettingCache;
import com.oetsky.project.serialsetting.serial.utils.SerialUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * RS-485Controller
 *
 * @author huwm
 * @date 2022-03-04
 */
@Controller
@RequestMapping("/serialsetting/serialsetting")
public class DvSerialSettingController extends BaseController {

    private String prefix = "serialsetting/serialsetting";

    @Autowired
    private IDvSerialSettingService dvSerialSettingService;

    @RequiresPermissions("serialsetting:serialsetting:view")
    @GetMapping()
    public String serialsetting() {
        return prefix + "/serialsetting";
    }

    /**
     * 查询RS-485列表
     */
    @RequiresPermissions("serialsetting:serialsetting:list")
    @PostMapping("/selectCountByDeviceType")
    @ResponseBody
    public AjaxResult selectCountByDeviceType(String deviceType) {
        Integer count = dvSerialSettingService.selectCountByDeviceType(deviceType);
        AjaxResult ajax = AjaxResult.success();
        ajax.put("count", count);
        return ajax;
    }

    /**
     * 查询RS-485列表
     */
    @RequiresPermissions("serialsetting:serialsetting:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(DvSerialSetting dvSerialSetting) {
        startPage();
        List<DvSerialSetting> list = dvSerialSettingService.selectDvSerialSettingList(
                dvSerialSetting);
        if (CollectionUtils.isNotEmpty(list)) {
            for (DvSerialSetting serialSetting : list) {
                String serialNumber = serialSetting.getSerialNumber();
                String serialLabel = DvSerialSettingCache.getSerialLabel(serialNumber);
                if (StringUtils.isNotBlank(serialLabel)) {
                    serialSetting.setSerialNumber(serialLabel);
                }
            }
        }
        return getDataTable(list);
    }

    /**
     * 导出RS-485列表
     */
    @RequiresPermissions("serialsetting:serialsetting:export")
    @Log(title = "RS-485", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(DvSerialSetting dvSerialSetting) {
        List<DvSerialSetting> list = dvSerialSettingService.selectDvSerialSettingList(
                dvSerialSetting);
        if (CollectionUtils.isNotEmpty(list)) {
            for (DvSerialSetting serialSetting : list) {
                String serialNumber = serialSetting.getSerialNumber();
                String serialLabel = DvSerialSettingCache.getSerialLabel(serialNumber);
                if (StringUtils.isNotBlank(serialLabel)) {
                    serialSetting.setSerialNumber(serialLabel);
                }
            }
        }
        ExcelUtil<DvSerialSetting> util = new ExcelUtil<DvSerialSetting>(DvSerialSetting.class);
        return util.exportExcel(list, "RS-485数据");
    }

    /**
     * 新增RS-485
     */
    @GetMapping("/add")
    public String add(ModelMap modelMap) {
        /*List<SerialPortBean> serialPorts = DvSerialSettingCache.findSerialPort();
        if (CollectionUtils.isNotEmpty(serialPorts)){
            Map<String, String> code2LabelMap = serialPorts.stream().collect(Collectors.toMap(SerialPortBean::getSerialCode, SerialPortBean::getSerialLabel));
            modelMap.put("code2LabelMap",code2LabelMap);
        }*/
        return prefix + "/add";
    }

    /**
     * 新增保存RS-485
     */
    @RequiresPermissions("serialsetting:serialsetting:add")
    @Log(title = "RS-485", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(DvSerialSetting dvSerialSetting) {
        DvSerialSetting dvSerialSettingForValid = getValidTypeObj(dvSerialSetting);
        Integer integer = derviceTypeUnique(dvSerialSettingForValid);
        dvSerialSettingService.validDvSerialSetting(dvSerialSetting);
        if (integer > 0) {
            return AjaxResult.error("添加数据重复，请确认【设备类型】、【串口号】是否重复!!!");
        }
        return toAjax(dvSerialSettingService.insertDvSerialSetting(dvSerialSetting));
    }

    private DvSerialSetting getValidTypeObj(DvSerialSetting dvSerialSetting) {
        if (dvSerialSetting != null) {
            DvSerialSetting res = new DvSerialSetting();
            res.setId(dvSerialSetting.getId());
            res.setDeviceType(dvSerialSetting.getDeviceType());
            return res;
        }
        return null;
    }

    /**
     * 修改RS-485
     */
    @RequiresPermissions("serialsetting:serialsetting:edit")
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap) {
        DvSerialSetting dvSerialSetting = dvSerialSettingService.selectDvSerialSettingById(id);
        mmap.put("dvSerialSetting", dvSerialSetting);
        return prefix + "/edit";
    }

    /**
     * 修改保存RS-485
     */
    @RequiresPermissions("serialsetting:serialsetting:edit")
    @Log(title = "RS-485", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(DvSerialSetting dvSerialSetting) {
        DvSerialSetting dvSerialSettingForValid = getValidTypeObj(dvSerialSetting);
        Integer integer = derviceTypeUnique(dvSerialSettingForValid);
        dvSerialSettingService.validDvSerialSetting(dvSerialSetting);
        if (integer > 0) {
            return AjaxResult.error("添加数据重复，请确认【设备类型】、【串口号】是否重复!!!");
        }
        return toAjax(dvSerialSettingService.updateDvSerialSetting(dvSerialSetting));
    }

    /**
     * 删除RS-485
     */
    @RequiresPermissions("serialsetting:serialsetting:remove")
    @Log(title = "RS-485", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(dvSerialSettingService.deleteDvSerialSettingByIds(ids));
    }

    /*

     */
/**
 * 设备通讯实时数据
 *//*

    @RequiresPermissions("serialsetting:serialsetting:list")
    @Log(title = "设备通讯实时数据", businessType = BusinessType.OTHER)
    @GetMapping("/interfaceRealTime/{id}")
    public String interfaceRealTime(@PathVariable("id") Long id, ModelMap mmap) {
        DvSerialSetting dvSerialSetting = dvSerialSettingService.selectDvSerialSettingById(id);
        if (dvSerialSetting == null) {
            throw new BeanExpressionException("请先确认是否存在设备!!!");
        } else if (InterfaceStatusEnum.CLOSE.getCode()
                .equals(dvSerialSetting.getInterfaceStatus())) {
            throw new BeanExpressionException(
                    "请先将【" + dvSerialSetting.getDeviceName() + "】设备开启!!!");
        }
        // 注册监听
//        serialSettingRealTime(dvSerialSetting);
        mmap.put("serialType", dvSerialSetting.getDeviceType());
        mmap.put("serialTypeStr",
                DictUtils.getDictLabel("sys_485_device_type", dvSerialSetting.getDeviceType()));
        mmap.put("serialId", id);
        mmap.put("serialNumber", dvSerialSetting.getSerialNumber());
        return prefix + "/interfaceRealTimeMsg";
    }


    public synchronized static SerialManage serialSettingRealTime(DvSerialSetting serialSetting) {
        SerialRealTimeMsg serialRealTimeMsgTemp = SerialDomain.getSerialRealTimeMsgTemp(
                serialSetting.getSerialNumber());
        if (serialRealTimeMsgTemp == null) {
            // 注册观察者监听
            serialRealTimeMsgTemp = new SerialRealTimeMsg(serialSetting.getSerialNumber());
            SerialDomain.setSerialRealTimeMsgTemp(serialSetting.getSerialNumber(),
                    serialRealTimeMsgTemp);
        }
        // 添加监听
        SerialManage serialManage = new SerialManage(serialSetting, serialRealTimeMsgTemp);
        for (String keys : SerialDomain.SERIAL_DATE_TIME.keySet()) {
            if (keys.equals(serialSetting.getSerialNumber())) {
                continue;
            }
            // 设置只允许一个实时查看报文，将其它查看时间设置为过期
            SerialDomain.setNettyDateTime(keys,
                    DateUtil.offsetMinute(new Date(), -1));
        }
        // 设置监听时间
        SerialDomain.setNettyDateTime(serialSetting.getSerialNumber(),
                DateUtil.offsetMinute(new Date(), SerialDomain.SERIAL_OFFSET_NEX_TIME));
        Map<Date, SerialObserver> map = new HashMap<>();
        map.put(new Date(), serialRealTimeMsgTemp);
        // 添加临时监听到集合中，用于定时删除
        SerialDomain.SERIAL_LISTENER_LIST.put(serialSetting.getSerialNumber(), map);
        return serialManage;
    }

    */
/**
 * 实时报文监控心跳连接
 *//*

    @RequiresPermissions("serialsetting:serialsetting:list")
    @PostMapping("/connectHeart")
    @ResponseBody
    public AjaxResult connectHeart(Long id) {
        DvSerialSetting serialSetting = dvSerialSettingService.selectDvSerialSettingById(id);
        if (serialSetting != null) {
            SerialDomain.setNettyDateTime(serialSetting.getSerialNumber(),
                    DateUtil.offsetMinute(new Date(), SerialDomain.SERIAL_OFFSET_NEX_TIME));
        } else {
            throw new BeanExpressionException("心跳设备接口错误!!!");
        }
        logger.info("获取实时报文心跳成功.....");
        return AjaxResult.success("获取实时报文心跳成功......");
    }

    */
/**
 * 删除实时报文监控
 *//*

    @RequiresPermissions("serialsetting:serialsetting:list")
    @Log(title = "删除实时报文监控", businessType = BusinessType.OTHER)
    @PostMapping("/closeWebSocket")
    @ResponseBody
    public AjaxResult closeWebSocket(Long id) {
        DvSerialSetting serialSetting = dvSerialSettingService.selectDvSerialSettingById(id);
        AjaxResult ajaxResult = new AjaxResult();
        if (serialSetting != null) {
            Subject subject = NettyDomain.nettyMsgListener.get(serialSetting.getSerialNumber());
            if (subject != null) {
                // 删除监听
                subject.removeObserver(
                        SerialDomain.getSerialRealTimeMsgTemp(serialSetting.getSerialNumber()));
            }
            logger.info("【485串口】设备删除设备监听成功.....");
        } else {
            throw new BeanExpressionException("请选择正确的设备接口!!!");
        }
        return ajaxResult.put("msg", "删除实时报文监控!!!");
    }

    */
/**
 * 设备通讯测试
 *//*

    @RequiresPermissions("serialsetting:serialsetting:list")
    @Log(title = "设备通讯测试", businessType = BusinessType.OTHER)
    @GetMapping("/interfaceTestSend")
    @ResponseBody
    public AjaxResult interfaceTestSend(Long id) {
        DvSerialSetting serialSetting = dvSerialSettingService.selectDvSerialSettingById(id);
        if (serialSetting == null) {
            return AjaxResult.error("请先确认是否存在设备接口!!!");
        } else if (InterfaceStatusEnum.CLOSE.getCode().equals(serialSetting.getInterfaceStatus())) {
            return AjaxResult.error("请先将【" + serialSetting.getDeviceName() + "】设备接口开启!!!");
        }
        // 注册监听
        SerialManage serialManage = serialSettingRealTime(serialSetting);
        for (int i = 0; i < 3; i++) {
            serialManage.sendToPortTest();
            if (i <= 1) {
                LockSupport.parkNanos(1000 * 1000 * 1000);
            }
        }
        logger.info("【485串口】设备通讯测试.....");
        return AjaxResult.success("设备接口正常，请检查硬件设备通讯灯是否闪烁!!!");
    }
*/

    /**
     * 校验 设备类型是否存在重复
     */
    @PostMapping("/checkDerviceTypeUnique")
    @ResponseBody
    public String checkDerviceTypeUnique(DvSerialSetting serialSetting) {
        return Convert.toStr(derviceTypeUnique(serialSetting));
    }

    /**
     * 验证数据是否存在
     *
     * @param serialSetting
     * @return
     */
    public Integer derviceTypeUnique(DvSerialSetting serialSetting) {
        if (serialSetting == null) {
            return 0;
        }
        List<DvSerialSetting> dvSerialSettingList = dvSerialSettingService.selectDvSerialSettingList(
                new DvSerialSetting());
        if (StringUtils.isEmpty(dvSerialSettingList)) {
            // 不存在重复
            return 0;
        }
        // 校验设备类型
        if (serialSetting.getDeviceType() != null) {
            for (DvSerialSetting dvSerialSetting : dvSerialSettingList) {
                if (dvSerialSetting.getDeviceType().equals(serialSetting.getDeviceType())) {
                    if (dvSerialSetting.getId().equals(serialSetting.getId())) {
                        return 0;
                    } else {
                        return 1;
                    }
                }
            }
        }

        // 校验串口号
        if (serialSetting.getSerialNumber() != null) {
            String serialName = serialSetting.getSerialNumber();
            if (serialName.startsWith("ttyUSB")) {
                serialName = "/dev/" + serialName;
            }
            if (serialName.startsWith("/dev/ttyRS")) {
                // ttyRS 需要找到真实串口号
                serialName = SerialUtils.realSerialName(serialName);
            }
            for (DvSerialSetting dvSerialSetting : dvSerialSettingList) {
                String dbSerialName = dvSerialSetting.getSerialNumber();
                if (dbSerialName.startsWith("ttyUSB")) {
                    dbSerialName = "/dev/" + dbSerialName;
                }
                if (dbSerialName.startsWith("/dev/ttyRS")) {
                    // ttyRS 需要找到真实串口号
                    dbSerialName = SerialUtils.realSerialName(dbSerialName);
                }
                if (dbSerialName.equals(serialName)) {
                    if (dvSerialSetting.getId().equals(serialSetting.getId())) {
                        return 0;
                    } else {
                        return 1;
                    }
                }
            }
        }

        return 0;
    }


}
