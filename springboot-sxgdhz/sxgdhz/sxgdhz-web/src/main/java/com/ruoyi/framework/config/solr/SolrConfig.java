package com.ruoyi.framework.config.solr;

import org.apache.solr.client.solrj.impl.CloudSolrClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SolrConfig {

    @Value("${data.solr.zk-host}")
    private String zkHost;

    @Bean
    public CloudSolrClient cloudSolrClient(){
        // 创建集群客户端
        CloudSolrClient cloudSolrClient = new CloudSolrClient.Builder().withZkHost(zkHost).build();
        // 设置默认的索引库
        cloudSolrClient.setDefaultCollection("zk_server");
        return cloudSolrClient;
    }
}
