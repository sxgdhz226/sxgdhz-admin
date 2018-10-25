package com.ruoyi.service.user.config.properties;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;

@Configuration
//@PropertySource(value = {"classpath:/properties/dubbo.properties","classpath:/properties/redis.properties"})
@PropertySource(value = {"classpath:/properties/redis.properties","classpath:/properties/dubbo.properties"})
@ImportResource({"classpath:/application/spring-*.xml"})
public class PropertiesConfig {
}