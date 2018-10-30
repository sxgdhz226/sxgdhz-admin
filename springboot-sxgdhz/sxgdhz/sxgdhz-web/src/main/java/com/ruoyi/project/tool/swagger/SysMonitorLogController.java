package com.ruoyi.project.tool.swagger;

import com.ruoyi.api.entity.SysMonitorLog;
import com.ruoyi.api.service.ISysMonitorLogService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.Future;

@Api("sysMonitorLog")
@RestController
@RequestMapping("/sysMonitorLog")
public class SysMonitorLogController extends BaseController{

    @Autowired
    private ISysMonitorLogService iSysMonitorLogService;

    @ApiOperation("listByPage")
    @RequestMapping(value = "/listByPage", method = RequestMethod.GET)
    public Object listByPage(String content) throws Exception {
       return iSysMonitorLogService.searchLogByPage(1,10 , content);
    }

    @ApiOperation("add")
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public AjaxResult add(SysMonitorLog sysMonitorLog) throws Exception {
        try {
            iSysMonitorLogService.saveSysMonitorLog(sysMonitorLog);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return toAjax(1);
    }

}
