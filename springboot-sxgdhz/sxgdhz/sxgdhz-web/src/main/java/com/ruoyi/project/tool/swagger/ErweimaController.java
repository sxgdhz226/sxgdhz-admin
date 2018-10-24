package com.ruoyi.project.tool.swagger;

import com.ruoyi.framework.config.kafka.MsgProducer;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Api("二维码管理")
@RestController
@RequestMapping("/erweima")
public class ErweimaController extends BaseController{

    @RequestMapping(value = "/toerweima", method = RequestMethod.GET)
    @ApiOperation("erweima")
    public Object toerweima() {

        return "erweima";
    }
}
