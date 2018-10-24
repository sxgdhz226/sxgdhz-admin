package com.ruoyi.framework.config.properties;

import lombok.Data;
import org.springframework.boot.autoconfigure.mongo.MongoProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * properties属性映射到实体类中 省去挨个属性注入
 * mongodb
 */
@Configuration
@ConfigurationProperties(prefix="spring.data")
@PropertySource(value = {"classpath:/properties/mongo.properties"})
@Data
public class MongoResource {

    private MongoProperties mongodb = new MongoProperties();
}
