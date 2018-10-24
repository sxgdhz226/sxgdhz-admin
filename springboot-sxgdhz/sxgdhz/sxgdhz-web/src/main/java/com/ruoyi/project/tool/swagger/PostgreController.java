package com.ruoyi.project.tool.swagger;

import com.ruoyi.framework.aspectj.lang.annotation.Ds;
import com.ruoyi.framework.aspectj.lang.enums.DataSourceType;
import com.ruoyi.framework.config.kafka.MsgProducer;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.project.web.entity.PostgreUser;
import com.ruoyi.project.web.repository.PostgreSqlRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "postgresql")
@RestController
@RequestMapping("/postgresql")
public class PostgreController extends BaseController{

    @Autowired
    private PostgreSqlRepository postgreSqlRepository;

    /**
     * 加入swagger注解方便测试
     * @return
     */
    @RequestMapping(value = "/queryAll", method = RequestMethod.GET)
    @ApiOperation("queryAll")
    @Ds(DataSourceType.THIRD)
    @ResponseBody
    public Object queryAll() {

        List<PostgreUser> list = postgreSqlRepository.findAll();
        return list;
    }
}
