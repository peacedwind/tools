package com.oetsky.project.monitor.druid;

import com.oetsky.framework.web.controller.BaseController;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * druid 监控
 *
 * @author ruoyi
 */
@Controller
@RequestMapping("/monitor/data")
public class DruidController extends BaseController {

    private String prefix = "/druid";

    @GetMapping()
    public String index() {
        return redirect(prefix + "/index.html");
    }
}
