package com.ruoyi.project.tool.swagger;

import com.ruoyi.framework.config.kafka.MsgProducer;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Api("消息队列管理")
@RestController
@RequestMapping("/kafka")
public class KafkaController extends BaseController{

    @Autowired
    private MsgProducer kafkaProducer;

    /**
     * 加入swagger注解方便测试
     * @param key
     * @param data
     * @return
     */
    @RequestMapping(value = "/send", method = RequestMethod.GET)
    @ApiOperation("Kafka")
    public AjaxResult sendKafka(@RequestParam(value = "key") String key, @RequestParam(value = "data") String data) {

        //发送一个topic
        kafkaProducer.send(key,data);
        return toAjax(1);
    }
}
