package com.ruoyi.project.web.service.impl;

import com.ruoyi.common.utils.http.HttpUtils;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.framework.web.page.PageDomain;
import com.ruoyi.project.utils.DateUtils;
import com.ruoyi.project.web.config.mapper.MonitorMapper;
import com.ruoyi.project.web.entity.MonitorLog;
import com.ruoyi.project.web.service.MonitoLogService;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.CloudSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service("monitorServiceImpl")
public class MonitorServiceImpl implements MonitoLogService {

    private static final Logger log = LoggerFactory.getLogger(MonitorServiceImpl.class);

    @Autowired
    private MonitorMapper monitorMapper;

    @Autowired
    private CloudSolrClient cloudSolrClient;

    @Override
    public AjaxResult importMonitorLog() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        try {
            final Date now = sdf.parse("2018-01-01");
            List<MonitorLog> monitorLogList = monitorMapper.getAllMonitorLog()
                    .stream()
                    .filter(monitorLog -> monitorLog.getDateTime().compareTo(now) > 0)
                    .filter(monitorLog -> monitorLog.getTaskId() == "7")
                    .collect(Collectors.toList());
            log.info("查询到结果集>>>>>>>>>>>>>>>>>>>>>>>>>："+monitorLogList.size());
            final int i = 262227;

            monitorLogList.forEach(monitorLog -> {
                SolrInputDocument solrInputDocument = new SolrInputDocument();
                //向文档中添加域
                solrInputDocument.addField("id",i);
                solrInputDocument.addField("dateTime",monitorLog.getDateTime());
                solrInputDocument.addField("task_id",monitorLog.getTaskId());
                solrInputDocument.addField("task_name",monitorLog.getTaskName());
                solrInputDocument.addField("result",monitorLog.getResult());
                solrInputDocument.addField("result_desc",monitorLog.getResultDesc());
                solrInputDocument.addField("target",monitorLog.getTarget());
                System.out.println(cloudSolrClient+">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
                try {
                    cloudSolrClient.add(solrInputDocument);
                    cloudSolrClient.commit();
                    //i++;
                } catch (SolrServerException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            });
        } catch (ParseException e) {
            e.printStackTrace();
        }


        try {
            cloudSolrClient.commit();
        } catch (SolrServerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<MonitorLog> selectLogininforList(MonitorLog monitorLog,PageDomain pageDomain) {
        List<MonitorLog> monitorLogList = new ArrayList<>();

        SolrQuery solrQuery = new SolrQuery();
//        solrQuery.setQuery("date_time:"+monitorLog.getDateTime());
//        solrQuery.setQuery("task_name:"+monitorLog.getTaskName());
        solrQuery.setQuery("*:*");

        solrQuery.setStart(pageDomain.getPageNum());
        solrQuery.setRows(pageDomain.getPageSize());

        //设置高亮显示
        solrQuery.setHighlight(true);
        solrQuery.addHighlightField("task_name");
        solrQuery.setHighlightSimplePre("<font color='red'>");//标记，高亮关键字前缀
        solrQuery.setHighlightSimplePost("</font>");//

        solrQuery.setHighlightSnippets(2);//结果分片数，默认为1
        solrQuery.setHighlightFragsize(1000);//每个分片的最大长度，默认为100

        QueryResponse queryResponse = null;
        try {
            queryResponse = cloudSolrClient.query(solrQuery);
            SolrDocumentList solrDocumentList = queryResponse.getResults();
            solrDocumentList.forEach(e -> {
                MonitorLog log = new MonitorLog();
               // log.setDateTime(DateUtils.localDateTime2Date(DateUtils.parseStrDateToDate(e.get("dateTime").toString(),"yyyy-MM-dd")));
                System.out.println(e.get("result").toString());
                log.setResult(Integer.parseInt((String)e.get("result")));
                log.setTaskName(String.valueOf(e.get("task_name")));
                log.setResultDesc(String.valueOf(e.get("result_desc")));
                log.setId(Long.parseLong(e.get("id").toString()));
                log.setTarget(String.valueOf(e.get("target")));
                monitorLogList.add(log);
            });
        } catch (SolrServerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return monitorLogList;
    }
}
