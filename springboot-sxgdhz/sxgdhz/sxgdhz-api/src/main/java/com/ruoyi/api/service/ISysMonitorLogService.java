package com.ruoyi.api.service;

import com.ruoyi.api.entity.SysMonitorLog;
import org.apache.lucene.util.automaton.LimitedFiniteStringsIterator;

import java.util.List;

public interface ISysMonitorLogService {

    public void saveSysMonitorLog(SysMonitorLog sysMonitorLog);

    public List<SysMonitorLog> searchLogByPage(Integer pageNumber, Integer pageSize,String searchContent);


}
