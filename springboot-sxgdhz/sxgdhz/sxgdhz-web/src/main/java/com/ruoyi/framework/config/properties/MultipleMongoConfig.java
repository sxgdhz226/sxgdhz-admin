package com.ruoyi.framework.config.properties;

import com.mongodb.MongoClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.mongo.MongoProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;

/**
 * 主：MongoTemplate方法名必须是 mongoTemplate
 */
@Configuration
public class MultipleMongoConfig {

    @Autowired
    private MongoResource mongoProperties;

    @Bean
    public MongoTemplate mongoTemplate() throws Exception {

        MongoDbFactory mongoDbFactory = primaryFactory(this.mongoProperties.getMongodb());
        return new MongoTemplate(primaryFactory(this.mongoProperties.getMongodb()));
    }

    @Bean
    public MongoDbFactory primaryFactory(MongoProperties mongo) throws Exception {
        return new SimpleMongoDbFactory(new MongoClient(mongo.getHost(), mongo.getPort()),
                mongo.getDatabase());
    }

//    public @Bean MongoClient mongoClient() {
//        return new MongoClient("localhost");
//    }
//
//    public @Bean MongoTemplate mongoTemplate() {
//        return new MongoTemplate(mongoClient(), "vshop");
//    }

}
