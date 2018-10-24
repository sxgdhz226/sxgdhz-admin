package com.ruoyi.project.web.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/log4jMonitor")
public class Log4jMonitorController {

    private String prefix = "log4jMonitor";
    @GetMapping()
    public String log4j()
    {
        return prefix + "/console";
    }
}
