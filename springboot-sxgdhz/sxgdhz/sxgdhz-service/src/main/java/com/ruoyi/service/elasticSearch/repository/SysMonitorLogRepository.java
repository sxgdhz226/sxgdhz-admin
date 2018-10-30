package com.ruoyi.service.elasticSearch.repository;

import com.ruoyi.api.entity.SysMonitorLog;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Component;

@Component
public interface SysMonitorLogRepository extends ElasticsearchRepository<SysMonitorLog,Integer> {
}
