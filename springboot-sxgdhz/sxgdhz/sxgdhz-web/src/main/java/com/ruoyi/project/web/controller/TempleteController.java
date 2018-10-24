package com.ruoyi.project.web.controller;

import com.google.common.collect.Maps;
import com.ruoyi.project.utils.FileUtil;
import com.ruoyi.project.utils.ThymeleafUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.thymeleaf.TemplateEngine;

import java.util.Map;

@Controller
@RequestMapping("/templete")
public class TempleteController {
//
//    @Autowired
//    private TemplateEngine templateEngine;

//    public Object mainStatic(){
//        Map<String, Object> dataMap = Maps.newHashMap();
//        dataMap.put("title", "test");
//        dataMap.put("content", "test");
//        Context ctx = ThymeleafUtil.getContext(dataMap);
//        FileUtil.writeStringToFile(templateEngine.process("test", ctx), "result.html");
//
//        return "";
//    }

}
