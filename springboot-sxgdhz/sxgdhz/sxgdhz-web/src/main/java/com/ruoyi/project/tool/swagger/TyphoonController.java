package com.ruoyi.project.tool.swagger;

import com.baomidou.mybatisplus.plugins.Page;
import com.ruoyi.api.service.ITyphoonService;
import com.ruoyi.framework.web.controller.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Api("typhoon")
@RestController
@RequestMapping("/typhoon")
public class TyphoonController extends BaseController{

    @Autowired
    private ITyphoonService typhoonService;

    @ApiOperation("list")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public Object list() throws Exception {
        Page page = new Page(1,10);
       return typhoonService.queryTyphoonForList();
    }

    @ApiOperation("listByPage")
    @RequestMapping(value = "/listByPage", method = RequestMethod.GET)
    public Object listByPage() throws Exception {
        Page page = new Page(1,10);
        return typhoonService.queryTyphoonForPageList(page);
    }

}
