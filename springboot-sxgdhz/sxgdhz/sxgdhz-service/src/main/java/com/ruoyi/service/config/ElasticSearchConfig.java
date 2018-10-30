//package com.ruoyi.service.config;
//
//import org.elasticsearch.client.transport.TransportClient;
//import org.elasticsearch.common.network.InetAddresses;
//import org.elasticsearch.common.settings.Settings;
//import org.elasticsearch.common.transport.InetSocketTransportAddress;
//import org.elasticsearch.transport.client.PreBuiltTransportClient;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import java.net.InetSocketAddress;
//
///**
// *  使用原生方式
// */
//@Configuration
//public class ElasticSearchConfig {
//
//    @Bean
//    public TransportClient transportClient() {
//        Settings settings = Settings.builder().put("cluster.name", "elasticsearch").build();
//        //我用6.3.2版本的时候这里一直报异常说找不到InetSocketTransportAddress类，这应该和jar有关，当我改成5.6.8就不报错了
//        TransportClient client = new PreBuiltTransportClient(settings);//6.3.2这里TransportAddress代替InetSocketTransportAddress
//        client.addTransportAddress(new InetSocketTransportAddress(
//                new InetSocketAddress(InetAddresses.forString("192.168.19.129"), 9300)));
//        return client;
//    }
//}
