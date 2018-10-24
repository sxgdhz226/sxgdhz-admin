package com.ruoyi.framework.config.properties;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource(value = {"classpath:/properties/activemq.properties",
        "classpath:/properties/mongo.properties","classpath:/properties/redis.properties",
        "classpath:/properties/kafka.properties","classpath:/properties/rabbitmq.properties"})  //多个属性文件以逗号隔开
//@ImportResource({"classpath:/application/spring-*.xml"})
public class PropertiesConfig {
}
