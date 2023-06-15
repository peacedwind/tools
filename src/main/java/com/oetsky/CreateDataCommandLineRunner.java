package com.oetsky;

import com.oetsky.project.dataselect.service.IDaVoltageDataService;
import com.oetsky.project.dataselect.service.IErrVoltageErrorService;
import com.oetsky.project.dataselect.service.InspectionEventInfoService;
import java.util.Date;
import javax.annotation.Resource;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * @description: 程序启动添加
 * @author: cyx
 * @date: 2023-06-15
 **/
@Component
public class CreateDataCommandLineRunner implements CommandLineRunner {

    @Resource
    private IDaVoltageDataService daVoltageDataService;
    @Resource
    private IErrVoltageErrorService errVoltageErrorService;
    @Resource
    private InspectionEventInfoService inspectionEventInfoService;

    @Override
    public void run(String... args) throws Exception {
        createVoltageData();
        createErrVoltageError();
        createInspectEventInfo();
    }

    private void createInspectEventInfo() {
        inspectionEventInfoService.createData();
    }

    private void createErrVoltageError() {
        errVoltageErrorService.createData();
    }

    private void createVoltageData() {
        daVoltageDataService.createData();
    }
}
