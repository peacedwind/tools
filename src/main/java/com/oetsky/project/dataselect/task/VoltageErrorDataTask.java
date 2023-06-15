package com.oetsky.project.dataselect.task;

import com.oetsky.project.dataselect.service.IDaVoltageDataService;
import com.oetsky.project.dataselect.service.IErrVoltageErrorService;
import com.oetsky.project.util.ConfigurerSchedulingSpring;
import javax.annotation.Resource;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;

/**
 * @description:
 * @author: cyx
 * @date: 2023-06-15
 **/
@Configuration
public class VoltageErrorDataTask extends ConfigurerSchedulingSpring {

    @Resource
    private IErrVoltageErrorService errVoltageErrorService;

    @Override
    public String getCron() {
        return "0 0 1 * * ?";
    }

    @Override
    public void taskService() {
        errVoltageErrorService.createData();
    }
}
