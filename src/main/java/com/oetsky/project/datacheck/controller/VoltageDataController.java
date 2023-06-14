package com.oetsky.project.datacheck.controller;

import com.oetsky.common.utils.poi.ExcelUtil;
import com.oetsky.framework.web.controller.BaseController;
import com.oetsky.framework.web.domain.AjaxResult;
import com.oetsky.framework.web.page.TableDataInfo;
import com.oetsky.project.datacheck.domain.DaVoltageData;
import com.oetsky.project.datacheck.service.IVoltageDataService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * 电压采集数据Controller
 * 
 * @author xiangzc
 * @date 2023-06-13
 */
@Controller
@RequestMapping("/datacheck")
public class VoltageDataController extends BaseController
{
    private String prefix = "datacheck";

    @Autowired
    private IVoltageDataService voltageDataService;

    @GetMapping("/voltageData")
    public String voltageData()
    {
        return prefix + "/voltageData";
    }

    /**
     * 查询电压采集数据列表
     */
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(DaVoltageData daVoltageData)
    {
        startPage();
        List<DaVoltageData> list = voltageDataService.selectVoltageDataList(daVoltageData);
        return getDataTable(list);
    }

    /**
     * 导出电压采集数据列表
     */
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(DaVoltageData daVoltageData)
    {
        List<DaVoltageData> list = voltageDataService.selectVoltageDataList(daVoltageData);
        ExcelUtil<DaVoltageData> util = new ExcelUtil<DaVoltageData>(DaVoltageData.class);
        return util.exportExcel(list, "电压采集数据数据");
    }

    /**
     * 新增电压采集数据
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存电压采集数据
     */
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(DaVoltageData daVoltageData)
    {
        return toAjax(voltageDataService.insertVoltageData(daVoltageData));
    }

    /**
     * 修改电压采集数据
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        DaVoltageData daVoltageData = voltageDataService.selectVoltageDataById(id);
        mmap.put("voltageData", daVoltageData);
        return prefix + "/edit";
    }

    /**
     * 修改保存电压采集数据
     */
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(DaVoltageData daVoltageData)
    {
        return toAjax(voltageDataService.updateVoltageData(daVoltageData));
    }

    /**
     * 删除电压采集数据
     */
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(voltageDataService.deleteVoltageDataByIds(ids));
    }
}
