package com.ruoyi.cloud.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * EnableEurekaServer  开启eurekaServer注解
 */
@EnableEurekaServer
@SpringBootApplication
public class ClouddoServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClouddoServerApplication.class, args);
	}
}
