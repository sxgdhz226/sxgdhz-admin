package com.ruoyi.cloud.zuul.service;

import com.ruoyi.common.intercepter.FeignIntercepter;
import com.ruoyi.common.model.BaseModel;
import feign.Headers;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Headers("Content-Type:application/json")
@FeignClient(name = "api-admin", configuration = FeignIntercepter.class)
public interface MenuService {
    @GetMapping("/menu/userMenus")
    List<BaseModel> userMenus();
}
