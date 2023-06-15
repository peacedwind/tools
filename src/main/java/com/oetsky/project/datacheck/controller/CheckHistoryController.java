package com.oetsky.project.datacheck.controller;

import com.oetsky.framework.web.controller.BaseController;
import com.oetsky.framework.web.page.TableDataInfo;
import com.oetsky.project.datacheck.domain.CheckHistory;
import com.oetsky.project.datacheck.service.ICheckHistoryService;
import com.oetsky.project.dataselect.domain.DaVoltageData;
import com.oetsky.project.dataselect.domain.ErrVoltageError;
import com.oetsky.project.dataselect.domain.InspectionEventInfo;
import com.oetsky.project.dataselect.service.IDaVoltageDataService;
import com.oetsky.project.dataselect.service.IErrVoltageErrorService;
import com.oetsky.project.dataselect.service.InspectionEventInfoService;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
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

    @Resource
    private IDaVoltageDataService daVoltageDataService;

    @Resource
    private IErrVoltageErrorService errVoltageErrorService;

    @Resource
    private InspectionEventInfoService inspectionEventInfoService;
    @GetMapping("/test")
    public void test(){
        List<DaVoltageData> dateByDate = daVoltageDataService.createDataByDate(new Date());
        daVoltageDataService.batchDaVoltageDataList(dateByDate);
        List<ErrVoltageError> errVoltageError = errVoltageErrorService.createErrVoltageError(
            new Date());
        errVoltageErrorService.batchErrVoltageErrorList(errVoltageError);
        List<InspectionEventInfo> eventList = inspectionEventInfoService.createEventList(
            new Date());
        inspectionEventInfoService.batSave(eventList);
        System.out.println(dateByDate);
    }
}
