package com.ruoyi.framework.config.hessian;

import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.project.hessian.service.HessianTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.caucho.HessianServiceExporter;

@Configuration
public class HessianConfig {

    @Autowired
    private HessianTestService hessianTestService;

    @Log(title = "hessian上传文件")
    @Bean(name = "/HessianTestService")
    public HessianServiceExporter accountService(){

        HessianServiceExporter exporter = new HessianServiceExporter();
        exporter.setService(hessianTestService);  //将service作为服务暴露
        exporter.setServiceInterface(HessianTestService.class);
        return exporter;
    }
}
