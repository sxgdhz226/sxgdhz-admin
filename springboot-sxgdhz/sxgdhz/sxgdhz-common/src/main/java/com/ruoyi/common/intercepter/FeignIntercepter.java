package com.ruoyi.common.intercepter;

import com.ruoyi.common.contants.CommonConstants;
import com.ruoyi.common.context.FilterContextHandler;
import feign.RequestInterceptor;
import feign.RequestTemplate;

public class FeignIntercepter implements RequestInterceptor {
    @Override
    public void apply(RequestTemplate requestTemplate) {
        requestTemplate.header(CommonConstants.CONTEXT_TOKEN, FilterContextHandler.getToken());
    }
}