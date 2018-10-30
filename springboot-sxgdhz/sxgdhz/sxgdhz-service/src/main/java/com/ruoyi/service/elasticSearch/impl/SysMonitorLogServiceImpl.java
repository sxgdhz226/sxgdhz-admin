package com.ruoyi.service.elasticSearch.impl;

import com.ruoyi.api.entity.SysMonitorLog;
import com.ruoyi.api.service.ISysMonitorLogService;
import com.ruoyi.service.elasticSearch.repository.SysMonitorLogRepository;
import org.apache.commons.lang.StringUtils;
import org.apache.lucene.util.QueryBuilder;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("sysMonitorLogServiceImpl")
public class SysMonitorLogServiceImpl implements ISysMonitorLogService {

    @Autowired
    private SysMonitorLogRepository sysMonitorLogRepository;

    @Override
    public void saveSysMonitorLog(SysMonitorLog sysMonitorLog) {
        sysMonitorLogRepository.save(sysMonitorLog);
    }

    @Override
    public List<SysMonitorLog> searchLogByPage(Integer pageNumber, Integer pageSize, String searchContent) {

        try {
            BoolQueryBuilder builder = QueryBuilders.boolQuery();

            /**
             *  must
             所有的语句都 必须（must） 匹配，与 AND 等价。
             must_not
             所有的语句都 不能（must not） 匹配，与 NOT 等价。
             should
             至少有一个语句要匹配，与 OR 等价。
             trem
             精确查找 与= 号等价。
             match
             模糊匹配 与like 等价。
             */
            //设置多字段组合模糊搜索
            System.out.println("查询内容："+searchContent);
            if(StringUtils.isNotBlank(searchContent)){
                builder.must(QueryBuilders.multiMatchQuery(searchContent,"taskName"));
            }
            //设置排序
            FieldSortBuilder sort = SortBuilders.fieldSort("id").order(SortOrder.DESC);
            //设置分页
            Pageable pageable = new PageRequest(pageNumber, pageSize);

            SearchQuery searchQuery = new NativeSearchQueryBuilder()
                    .withPageable(pageable)
                    .withQuery(builder)
                    .withSort(sort)
                    .build();

            Page<SysMonitorLog> page = sysMonitorLogRepository.search(searchQuery);
            return page.getContent();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
