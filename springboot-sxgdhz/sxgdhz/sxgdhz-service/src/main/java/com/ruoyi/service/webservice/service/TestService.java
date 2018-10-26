package com.ruoyi.service.webservice.service;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

/**
 * 暴露服务名称
 * 命名空间,一般是接口的包名倒序
 */
@WebService(name = "testServcie",targetNamespace = "http://impl.webservice.service.ruoyi.com")
public interface TestService {

    @WebMethod
    @WebResult(name = "String",targetNamespace = "")
    String sendMessage(@WebParam(name = "username") String username);
}
