package com.oetsky.project.system.config.controller;

import com.oetsky.common.constant.UserConstants;
import com.oetsky.common.utils.poi.ExcelUtil;
import com.oetsky.framework.aspectj.lang.annotation.Log;
import com.oetsky.framework.aspectj.lang.enums.BusinessType;
import com.oetsky.framework.web.controller.BaseController;
import com.oetsky.framework.web.domain.AjaxResult;
import com.oetsky.framework.web.page.TableDataInfo;
import com.oetsky.project.system.config.domain.Config;
import com.oetsky.project.system.config.service.IConfigService;
import java.util.List;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 参数配置 信息操作处理
 *
 * @author ruoyi
 */
@Controller
@RequestMapping("/system/config")
public class ConfigController extends BaseController {

    private String prefix = "system/config";

    @Autowired
    private IConfigService configService;

    @GetMapping()
    public String config() {
        return prefix + "/config";
    }

    /**
     * 查询参数配置列表
     */
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(Config config) {
        startPage();
        List<Config> list = configService.selectConfigList(config);
        return getDataTable(list);
    }

    @Log(title = "参数管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Config config) {
        List<Config> list = configService.selectConfigList(config);
        ExcelUtil<Config> util = new ExcelUtil<Config>(Config.class);
        return util.exportExcel(list, "参数数据");
    }

    /**
     * 新增参数配置
     */
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    /**
     * 新增保存参数配置
     */
    @Log(title = "参数管理", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(@Validated Config config) {
        if (UserConstants.CONFIG_KEY_NOT_UNIQUE
            .equals(configService.checkConfigKeyUnique(config))) {
            return error("新增参数'" + config.getConfigName() + "'失败，参数键名已存在");
        }
        return toAjax(configService.insertConfig(config));
    }

    /**
     * 修改参数配置
     */
    @GetMapping("/edit/{configId}")
    public String edit(@PathVariable("configId") Long configId, ModelMap mmap) {
        mmap.put("config", configService.selectConfigById(configId));
        return prefix + "/edit";
    }

    /**
     * 修改保存参数配置
     */
    @Log(title = "参数管理", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(@Validated Config config) {
        if (UserConstants.CONFIG_KEY_NOT_UNIQUE
            .equals(configService.checkConfigKeyUnique(config))) {
            return error("修改参数'" + config.getConfigName() + "'失败，参数键名已存在");
        }
        return toAjax(configService.updateConfig(config));
    }

    /**
     * 删除参数配置
     */
    @Log(title = "参数管理", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        configService.deleteConfigByIds(ids);
        return success();
    }

    /**
     * 刷新参数缓存
     */
    @Log(title = "参数管理", businessType = BusinessType.CLEAN)
    @GetMapping("/refreshCache")
    @ResponseBody
    public AjaxResult refreshCache() {
        configService.resetConfigCache();
        return success();
    }

    /**
     * 校验参数键名
     */
    @PostMapping("/checkConfigKeyUnique")
    @ResponseBody
    public String checkConfigKeyUnique(Config config) {
        return configService.checkConfigKeyUnique(config);
    }
}
