package com.ruoyi.project.web.service;

import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.framework.web.page.PageDomain;
import com.ruoyi.project.web.entity.MonitorLog;

import java.util.List;

public interface MonitoLogService {


    public AjaxResult importMonitorLog();

    public List<MonitorLog> selectLogininforList(MonitorLog monitorLog,PageDomain pageDomain);

}
