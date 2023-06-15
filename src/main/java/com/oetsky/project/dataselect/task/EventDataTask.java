package com.oetsky.project.dataselect.task;

import com.oetsky.project.dataselect.service.InspectionEventInfoService;
import com.oetsky.project.util.ConfigurerSchedulingSpring;
import javax.annotation.Resource;
import org.springframework.context.annotation.Configuration;

/**
 * @description: 生成数据task
 * @author: cyx
 * @date: 2023-06-15
 **/
@Configuration
public class EventDataTask extends ConfigurerSchedulingSpring {

    @Resource
    private InspectionEventInfoService inspectionEventInfoService;

    @Override
    public String getCron() {
        return "0 0 1 * * ?";
    }

    @Override
    public void taskService() {
        inspectionEventInfoService.createData();
    }
}
