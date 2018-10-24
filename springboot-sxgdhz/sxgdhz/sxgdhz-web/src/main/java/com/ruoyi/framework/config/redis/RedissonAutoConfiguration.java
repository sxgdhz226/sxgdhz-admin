package com.ruoyi.framework.config.redis;

import com.ruoyi.common.distributeLock.RedissLockUtil;
import org.apache.commons.lang3.StringUtils;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.config.SingleServerConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *  ConditionalOnProperty用来控制 配置文件是否生效
 *  如果返回值为false，则该configuration不生效；为true则生效。
 *  使用EnableConfigurationProperties来生效ConfigurationProperties注解
 *
 *
 */

@Configuration
@EnableConfigurationProperties(RedisProperties.class)
public class RedissonAutoConfiguration {

    @Autowired
    private RedisProperties redisProperties;

    /**
     * 单机模式自动装配
     * @return
     */
    @Bean
    @ConditionalOnProperty(name="redisson.address")
    RedissonClient redissonSingle() {
        Config config = new Config();
        SingleServerConfig serverConfig = config.useSingleServer()
                .setAddress(redisProperties.getAddress())
                .setTimeout(redisProperties.getTimeout())
                .setConnectionPoolSize(redisProperties.getConnectionPoolSize())
                .setConnectionMinimumIdleSize(redisProperties.getConnectionMinimumIdleSize());
        if (StringUtils.isNotBlank(redisProperties.getPassword())) {
            serverConfig.setPassword(redisProperties.getPassword());
        }

        return Redisson.create(config);
    }
        
    /**
     * 装配locker类，并将实例注入到RedissLockUtil中
     * @return
     */
    @Bean
    RedissLockUtil redissLockUtil(RedissonClient redissonClient) {
        RedissLockUtil redissLockUtil = new RedissLockUtil();
        redissLockUtil.setRedissonClient(redissonClient);
        return redissLockUtil;
    }

}
