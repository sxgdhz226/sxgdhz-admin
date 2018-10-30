package com.ruoyi;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.entity.ContentType;
import org.apache.http.nio.entity.NStringEntity;
import org.apache.http.util.EntityUtils;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Response;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.net.InetAddress;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;

public class ElasticSearchTest {

    private static final String HOST = "192.168.19.129";
    private static final int PORT = 9300;

    private static final String INDEX = "eshop";
    private static final String TYPE = "product";

    private static final ObjectMapper MAPPER = new ObjectMapper();

    private RestClient client = null;



    @Test
    public void test(){
        //初始化RestClient实例
          RestClient restClient = RestClient.builder(
//                new HttpHost("192.168.10.5", 9200, "http"),
//                new HttpHost("192.168.10.6", 9200, "http"),
                new HttpHost("192.168.19.129", 9200, "http")).build();


        // （1） 执行一个基本的方法，验证es集群是否搭建成功

        Response response = null;
        try {
            response = restClient.performRequest("GET", "/", Collections.singletonMap("pretty", "true"));
            System.out.println(EntityUtils.toString(response.getEntity()));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 创建索引
     * @throws Exception
     */
    @Test
    public void CreateIndex() throws Exception{
        String method = "PUT";
        String endpoint = "/elasticsearch/sysMonitorLog/_search";
        Response response = client.performRequest(method,endpoint);
        System.out.println(EntityUtils.toString(response.getEntity()));
    }

    /**
     * 查询所有数据
     * @throws Exception
     */
    @Test
    public void QueryAll() throws Exception {
        String method = "POST";
        // index+type
        String endpoint = "/elasticsearch/sysMonitorLog/_search";
        HttpEntity entity = new NStringEntity("{\n" +
                "  \"query\": {\n" +
                "    \"match_all\": {}\n" +
                "  }\n" +
                "}", ContentType.APPLICATION_JSON);

        Response response = client.performRequest(method,endpoint,Collections.<String, String>emptyMap(),entity);
        System.out.println(EntityUtils.toString(response.getEntity()));
    }

    /**
     * 根据ID获取
     * @throws Exception
     */
    @Test
    public void QueryByField() throws Exception {
        String method = "POST";
        String endpoint = "/elasticsearch/sysMonitorLog/_search";
        HttpEntity entity = new NStringEntity("{\n" +
                "  \"query\": {\n" +
                "    \"match\": {\n" +
                "      \"user\": \"kimchy\"\n" +
                "    }\n" +
                "  }\n" +
                "}", ContentType.APPLICATION_JSON);

        Response response = client.performRequest(method,endpoint,Collections.<String, String>emptyMap(),entity);
        System.out.println(EntityUtils.toString(response.getEntity()));
    }

    /**
     * 更新数据
     * @throws Exception
     */
    @Test
    public void UpdateByScript() throws Exception {
        String method = "POST";
        String endpoint = "/elasticsearch/sysMonitorLog/_search";
        HttpEntity entity = new NStringEntity("{\n" +
                "  \"doc\": {\n" +
                "    \"user\":\"大美女\"\n" +
                "	}\n" +
                "}", ContentType.APPLICATION_JSON);
        Response response = client.performRequest(method,endpoint,Collections.<String, String>emptyMap(),entity);
        System.out.println(EntityUtils.toString(response.getEntity()));
    }

    // 获取客户端
    @Before
    public void getClient() throws Exception{
        client = RestClient.builder(
//                new HttpHost("192.168.10.5", 9200, "http"),
//                new HttpHost("192.168.10.6", 9200, "http"),
                new HttpHost("192.168.19.129", 9200, "http")).build();

    }

    // 关闭客户端
    @After
    public void closeClient(){
        if (this.client != null){
            try {
                this.client.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}
