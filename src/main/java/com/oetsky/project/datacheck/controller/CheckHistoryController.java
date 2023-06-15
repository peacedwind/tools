package com.oetsky.project.datacheck.controller;

import com.oetsky.framework.web.controller.BaseController;
import com.oetsky.framework.web.page.TableDataInfo;
import com.oetsky.project.datacheck.domain.CheckHistory;
import com.oetsky.project.datacheck.service.ICheckHistoryService;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @description:
 * @author: cyx
 * @date: 2023-06-14
 **/
@Controller
@RequestMapping("/dataCheck")
public class CheckHistoryController extends BaseController {
    @Resource
    private ICheckHistoryService checkHistoryService;

    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(CheckHistory checkHistory){
        startPage();
        List<CheckHistory> list = checkHistoryService.list(checkHistory);
        return getDataTable(list);
    }
}
