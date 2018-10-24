package com.ruoyi.project.web.controller;

import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.page.PageDomain;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.framework.web.page.TableSupport;
import com.ruoyi.project.monitor.logininfor.domain.Logininfor;
import com.ruoyi.project.web.entity.MonitorLog;
import com.ruoyi.project.web.service.MonitoLogService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/solr")
public class SolrIndexController extends BaseController{

    private String prefix = "solr/solr";

    @Autowired
    private MonitoLogService monitoLogService;

    @GetMapping()
    public String solr()
    {
        return prefix;
    }

    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(MonitorLog monitorLog)
    {
        PageDomain pageDomain = TableSupport.buildPageRequest();

        List<MonitorLog> list = monitoLogService.selectLogininforList(monitorLog,pageDomain);
        return getDataTable(list);
    }
}
