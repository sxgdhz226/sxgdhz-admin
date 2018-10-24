package com.ruoyi.project.tool.swagger;

import com.ruoyi.framework.config.kafka.MsgProducer;
import com.ruoyi.framework.config.rabbitmq.Sender;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Api("rabbitmq消息队列管理")
@RestController
@RequestMapping("/rabbitmq")
public class RabbitMqController extends BaseController{

    @Autowired
    private Sender sender;

    /**
     * 加入swagger注解方便测试
     * @return
     */
    @RequestMapping(value = "/send", method = RequestMethod.GET)
    @ApiOperation("send")
    public AjaxResult sendKafka() {

        sender.send();
        return toAjax(1);
    }
}
