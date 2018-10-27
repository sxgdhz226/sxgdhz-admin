package com.ruoyi.service.webservice.config;

import com.ruoyi.service.webservice.interceptor.AuthInterceptor;
import com.ruoyi.service.webservice.service.TestService;
import org.apache.cxf.Bus;
import org.apache.cxf.bus.spring.SpringBus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.apache.cxf.transport.servlet.CXFServlet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import javax.xml.ws.Endpoint;

/**
 * EndpointImpl  注意引入包的错误jaxws
 */
@Configuration
public class CxfConfig {

    @Autowired
    private Bus bus;

    @Autowired
    private TestService testService;

    /**
     * 一路采坑 此处方法名不能是dispacherServlet，否则会覆盖springmvc
     * @return
     */
    @Bean
    public ServletRegistrationBean disServlet() {
        return new ServletRegistrationBean(new CXFServlet(), "/webservice/*");// 发布服务名称 localhost:8080/cxf

    }


    @Bean(name = Bus.DEFAULT_BUS_ID)
    public SpringBus springBus() {
        return new SpringBus();
    }

    //mdm同步人员部门
    @Bean
    public Endpoint syncEmpOrgImpl() {
        EndpointImpl endpoint = new EndpointImpl(springBus(), testService);// 绑定要发布的服务实现类
        endpoint.getInInterceptors().add(new AuthInterceptor());
        endpoint.publish("/testService"); // 接口访问地址
        return endpoint;
    }


}
