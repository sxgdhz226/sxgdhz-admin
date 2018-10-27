package com.ruoyi;

import com.ruoyi.service.webservice.interceptor.AuthInterceptor;
import com.ruoyi.service.webservice.interceptor.LoginInterceptor;
import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;
import org.junit.Test;

import static org.apache.commons.lang.SystemUtils.USER_NAME;

public class WebServiceTest {

    @Test
    public void testSend1(){

        // 创建动态客户端
        JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();
        Client client = dcf.createClient("http://localhost:8081/webservice/testService?wsdl");

        // 需要密码的情况需要加上用户名和密码
         client.getOutInterceptors().add(new LoginInterceptor("root","admin"));
        Object[] objects = new Object[0];
        try {

            // invoke("方法名",参数1,参数2,参数3....);
            objects = client.invoke("sendMessage", "wyj");
            System.out.println("返回数据:" + objects[0]);
        } catch (java.lang.Exception e) {
            e.printStackTrace();
        }
    }
}
