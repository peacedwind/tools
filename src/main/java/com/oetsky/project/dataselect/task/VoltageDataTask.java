package com.oetsky.project.dataselect.task;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.oetsky.project.dataselect.service.IDaVoltageDataService;
import com.oetsky.project.util.ConfigurerSchedulingSpring;
import java.util.Date;
import javax.annotation.Resource;
import org.springframework.context.annotation.Configuration;

/**
 * @description: 电压数据生成任务
 * @author: cyx
 * @date: 2023-06-15
 **/
@Configuration
public class VoltageDataTask extends ConfigurerSchedulingSpring {

    @Resource
    private IDaVoltageDataService daVoltageDataService;

    @Override
    public String getCron() {
        return "0 0 1 * * ?" ;
    }

    @Override
    public void taskService() {
        daVoltageDataService.createData();
    }
}
