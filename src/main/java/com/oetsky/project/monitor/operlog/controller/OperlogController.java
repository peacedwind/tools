package com.oetsky.project.monitor.operlog.controller;

import com.oetsky.common.utils.poi.ExcelUtil;
import com.oetsky.framework.aspectj.lang.annotation.Log;
import com.oetsky.framework.aspectj.lang.enums.BusinessType;
import com.oetsky.framework.web.controller.BaseController;
import com.oetsky.framework.web.domain.AjaxResult;
import com.oetsky.framework.web.page.TableDataInfo;
import com.oetsky.project.monitor.operlog.domain.OperLog;
import com.oetsky.project.monitor.operlog.service.IOperLogService;
import java.util.List;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 操作日志记录
 *
 * @author ruoyi
 */
@Controller
@RequestMapping("/monitor/operlog")
public class OperlogController extends BaseController {

    private String prefix = "monitor/operlog";

    @Autowired
    private IOperLogService operLogService;

    @GetMapping()
    public String operlog() {
        return prefix + "/operlog";
    }

    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(OperLog operLog) {
        startPage();
        List<OperLog> list = operLogService.selectOperLogList(operLog);
        return getDataTable(list);
    }

    @Log(title = "操作日志", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(OperLog operLog) {
        List<OperLog> list = operLogService.selectOperLogList(operLog);
        ExcelUtil<OperLog> util = new ExcelUtil<OperLog>(OperLog.class);
        return util.exportExcel(list, "操作日志");
    }

    @Log(title = "操作日志", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(operLogService.deleteOperLogByIds(ids));
    }

    @GetMapping("/detail/{operId}")
    public String detail(@PathVariable("operId") Long operId, ModelMap mmap) {
        mmap.put("operLog", operLogService.selectOperLogById(operId));
        return prefix + "/detail";
    }

    @Log(title = "操作日志", businessType = BusinessType.CLEAN)
    @PostMapping("/clean")
    @ResponseBody
    public AjaxResult clean() {
        operLogService.cleanOperLog();
        return success();
    }
}
