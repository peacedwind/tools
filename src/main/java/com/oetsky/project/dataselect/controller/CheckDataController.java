package com.oetsky.project.dataselect.controller;

import com.oetsky.framework.web.controller.BaseController;
import com.oetsky.framework.web.page.TableDataInfo;
import com.oetsky.project.dataselect.domain.DaVoltageData;
import com.oetsky.project.dataselect.domain.ErrVoltageError;
import com.oetsky.project.dataselect.domain.InspectionEventInfo;
import com.oetsky.project.dataselect.service.IDaVoltageDataService;
import com.oetsky.project.dataselect.service.IErrVoltageErrorService;
import com.oetsky.project.dataselect.service.InspectionEventInfoService;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @description:
 * @author: cyx
 * @date: 2023-06-14
 **/
@Controller("/dataSelect")
public class CheckDataController extends BaseController {

    @Resource
    private IDaVoltageDataService daVoltageDataService;

    @Resource
    private IErrVoltageErrorService voltageErrorService;

    @Resource
    private InspectionEventInfoService inspectionEventInfoService;

    /**
     * 电压采集数据list查询
     * @param daVoltageData
     * @return
     */
    @PostMapping("/voltageCollect")
    @ResponseBody
    public TableDataInfo voltageDatalist(DaVoltageData daVoltageData){
        startPage();
        List<DaVoltageData> res = daVoltageDataService.selectDaVoltageDataList(
            daVoltageData);
        return getDataTable(res);
    }
    /**
     * 电压计算数据list查询
     * @param errVoltageError
     * @return
     */
    @PostMapping("/voltageCalculate")
    @ResponseBody
    public TableDataInfo voltageCalculatelist(ErrVoltageError errVoltageError){
        startPage();
        List<ErrVoltageError> res = voltageErrorService.selectErrVoltageErrorList(
            errVoltageError);
        return getDataTable(res);
    }

    /**
     * 事件list查询
     * @param inspectionEventInfo
     * @return
     */
    @PostMapping("/event")
    @ResponseBody
    public TableDataInfo eventlist(InspectionEventInfo inspectionEventInfo){
        startPage();
        List<InspectionEventInfo> res = inspectionEventInfoService.list(
            inspectionEventInfo);
        return getDataTable(res);
    }
}
