package com.ruoyi.common.intercepter;

import com.netflix.ribbon.RequestTemplate;
import com.ruoyi.common.contants.CommonConstants;
import com.ruoyi.common.context.FilterContextHandler;
import feign.RequestInterceptor;

public class FeignIntercepter implements RequestInterceptor {

    @Override
    public void apply(feign.RequestTemplate requestTemplate) {
        requestTemplate.header(CommonConstants.CONTEXT_TOKEN, FilterContextHandler.getToken());
    }
}