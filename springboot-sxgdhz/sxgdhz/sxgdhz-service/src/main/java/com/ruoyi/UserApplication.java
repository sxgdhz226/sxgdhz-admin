package com.ruoyi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 *  springboot启动方式
 *  @EnableDubboConfiguration
 *
 */
@SpringBootApplication
public class UserApplication
{
    public static void main( String[] args )
    {
        SpringApplication.run(UserApplication.class);
    }
}
