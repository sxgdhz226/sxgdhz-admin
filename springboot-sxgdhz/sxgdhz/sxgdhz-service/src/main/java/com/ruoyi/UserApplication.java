package com.ruoyi;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.autoconfigure.endpoint.EndpointAutoConfiguration;
import org.springframework.boot.actuate.autoconfigure.health.HealthIndicatorAutoConfiguration;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;

/**
 *  springboot启动方式
 *  @EnableDubboConfiguration
 *
 */
@SpringBootApplication
@EnableAutoConfiguration
@AutoConfigureBefore(EndpointAutoConfiguration.class)
@AutoConfigureAfter(HealthIndicatorAutoConfiguration.class)
@ConditionalOnClass(value = {HealthIndicator.class, EndpointAutoConfiguration.class})
@MapperScan("com.ruoyi.*.mapper")
public class UserApplication
{
    public static void main( String[] args )
    {
        SpringApplication.run(UserApplication.class);
    }
}
