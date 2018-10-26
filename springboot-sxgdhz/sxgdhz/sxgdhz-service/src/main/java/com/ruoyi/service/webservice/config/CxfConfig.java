package com.ruoyi.service.webservice.config;

import com.ruoyi.service.webservice.service.TestService;
import com.sun.xml.internal.ws.api.BindingID;
import com.sun.xml.internal.ws.transport.http.server.EndpointImpl;
import org.apache.cxf.Bus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.xml.ws.Endpoint;

@Configuration
public class CxfConfig {

    @Autowired
    private Bus bus;

    @Autowired
    private TestService testService;

    @Bean
    public Endpoint endpoint(){
        EndpointImpl endpoint = new EndpointImpl((BindingID) bus, testService);
        endpoint.publish("/TestService");
        return endpoint;
    }
}
