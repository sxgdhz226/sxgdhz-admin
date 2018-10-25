package com.ruoyi;

import com.ruoyi.project.servlet.MyServlet;
import org.activiti.spring.boot.EndpointAutoConfiguration;
import org.activiti.spring.boot.SecurityAutoConfiguration;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.boot.autoconfigure.solr.SolrAutoConfiguration;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Primary;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * 启动程序
 * MongoAutoConfiguration注解可以禁用springboot自带的配置。否则默认链接test数据库
 * 
 * @author ruoyi
 */
@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class,
        SecurityAutoConfiguration.class,SolrAutoConfiguration.class,
        MongoAutoConfiguration.class,MongoDataAutoConfiguration.class})
//@ComponentScan({"org.activiti.rest.diagram"})
@MapperScan("com.ruoyi.project.*.*.mapper")
@EnableScheduling
@EnableCaching   //开启redis  cache缓存
@ServletComponentScan  //启动器启动时，扫描本目录以及子目录带有的webservlet注解的
@EnableAsync
public class RuoYiApplication
{
    public static void main(String[] args)
    {
        // System.setProperty("spring.devtools.restart.enabled", "false");
        SpringApplication.run(RuoYiApplication.class, args);
        System.out.println("启动成功");
    }

    /**
     * 解决activiti和websocket冲突问题
     * @return
     */
    @Primary
    @Bean
    public TaskExecutor primaryTaskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        return executor;
    }

    /**
     * 注册servlet
     */
    @Bean
    public ServletRegistrationBean getServletRegistrationBean(){
        ServletRegistrationBean bean = new ServletRegistrationBean(new MyServlet());     //放入自己的Servlet对象实例
        bean.addUrlMappings("/myServlet");  //访问路径值
        return bean;
    }
}