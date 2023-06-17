package com.oetsky.project.datacheck.controller;

import cn.hutool.core.io.IoUtil;
import com.oetsky.common.constant.Constants;
import com.oetsky.common.utils.StringUtils;
import com.oetsky.common.utils.file.FileUtils;
import com.oetsky.common.utils.poi.ExcelUtil;
import com.oetsky.framework.config.OetskyConfig;
import com.oetsky.framework.web.controller.BaseController;
import com.oetsky.framework.web.domain.AjaxResult;
import com.oetsky.framework.web.page.TableDataInfo;
import com.oetsky.project.datacheck.domain.CheckHistory;
import com.oetsky.project.datacheck.service.ICheckHistoryService;
import com.oetsky.project.dataselect.domain.DaVoltageData;
import com.oetsky.project.dataselect.domain.ErrVoltageError;
import com.oetsky.project.dataselect.domain.InspectionEventInfo;
import com.oetsky.project.dataselect.service.IDaVoltageDataService;
import com.oetsky.project.dataselect.service.IErrVoltageErrorService;
import com.oetsky.project.dataselect.service.InspectionEventInfoService;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

/**
 * @description:
 * @author: cyx
 * @date: 2023-06-14
 **/
@Controller
@RequestMapping("/dataCheck")
public class CheckHistoryController extends BaseController {

    private static final long MAX_IMPORT_SIZE = 10 * 1024 * 1024;
    @Resource
    private ICheckHistoryService checkHistoryService;

    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(CheckHistory checkHistory){
        startPage();
        List<CheckHistory> list = checkHistoryService.list(checkHistory);
        return getDataTable(list);
    }


    @RequestMapping("/download")
    public void fileDownLoad(HttpServletResponse response, @RequestParam("fileName") String fileName){
        ResourceLoader resourceLoader = new DefaultResourceLoader();
        InputStream is = null;
        OutputStream os = null;
        try {
            // 下载名称
            response.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
            FileUtils.setAttachmentResponseHeader(response, fileName);
            os = response.getOutputStream();
            org.springframework.core.io.Resource resource = resourceLoader.getResource("classpath:excel/" + fileName);
            is = resource.getInputStream();
            byte[] b = new byte[1024];
            int length;
            while ((length = is.read(b)) > 0) {
                os.write(b, 0, length);
            }
        } catch (Exception e) {
            System.out.println("下载失败。。。");
        } finally {
            if (os != null) {
                IoUtil.close(os);
            }
            if (is != null) {
                IoUtil.close(is);
            }
        }
    }


    @PostMapping("/importCheck")
    @ResponseBody
    public AjaxResult importCheck(@RequestParam("checkType") Integer checkType,
                                        @RequestParam("file") MultipartFile file) {
        if (null == file) {
            return AjaxResult.error("上传文件为空");
        }
        try {
            if (file.getSize() > MAX_IMPORT_SIZE) {
                return AjaxResult.error("数据过多");
            }
            checkHistoryService.importCheck(checkType,file);
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.error(StringUtils.isNotEmpty(e.getMessage()) ? e.getMessage() : "导入电压互感器档案失败");
        }
        return new AjaxResult();
    }


    @GetMapping("/downErrorInfo")
    public void downErrorInfo(Integer id,HttpServletResponse response) {
        try {
            if (id != null){
                CheckHistory checkHistory = checkHistoryService.selectById(id);
                if (checkHistory == null){
                    return;
                }
                String resultFileUrl = checkHistory.getResultFileUrl();
                if (StringUtils.isNotEmpty(resultFileUrl)){
                    FileUtils.setAttachmentResponseHeader(response, resultFileUrl);
                    FileUtils.writeBytes(resultFileUrl, response.getOutputStream());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
