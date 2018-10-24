package com.ruoyi.project.tool.swagger;

import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.project.hessian.service.HessianTestService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

@Api(tags = "hessian上传文件")
@Controller
@RequestMapping("/hessian")
public class HessianController extends BaseController{

    private static Logger log = Logger.getLogger(HessianController.class);

    @Autowired
    private HessianTestService hessianTestService;

    @ApiOperation("upload")
    @GetMapping()
    @ResponseBody
    public AjaxResult upload()
    {
        /********************************************hessian***************************/
        try {
            if (log.isInfoEnabled()){
                log.info("开始hessian上传");
            }

            hessianTestService.upload("hessian.txt",new FileInputStream("d:/1234.pdf"));

            if (log.isInfoEnabled()){
                log.info("上传hessian成功");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return toAjax(1);
    }

}
