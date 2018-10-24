package com.ruoyi.project.tool.swagger;

import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.project.mongo.entity.Mainbody;
import com.ruoyi.project.mongo.entity.MongoDept;
import com.ruoyi.project.mongo.entity.MongoUser;
import com.ruoyi.project.mongo.repository.MainbodyRepository;
import com.ruoyi.project.mongo.repository.MongoDeptRepository;
import com.ruoyi.project.mongo.repository.UserRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.InputStream;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

@Api("mongo接口测试")
@RestController
@RequestMapping("/mongo")
public class MongoController extends BaseController{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MainbodyRepository mainbodyRepository;

    @Autowired
    private MongoDeptRepository mongoDeptRepository;

    /**
     * 级联插入
     * @param user
     * @return
     */
    @ApiOperation("新增用户")
    @PostMapping("save")
    public AjaxResult save(MongoUser user)
    {
        try {
            Mainbody mainbody = mainbodyRepository.findByCode("001");
            user.setMainbody(mainbody);
            userRepository.save(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return success();
    }

    @ApiOperation("查询单个用户")
    @PostMapping("findUserById")
    @ResponseBody
    public Object findUserById(@RequestParam(value = "id") Long id)
    {
        return userRepository.findById(id.intValue());
    }

    @ApiOperation("删除单个用户")
    @PostMapping("remove")
    public AjaxResult remove(@RequestParam(value = "id") Long id)
    {
        try {
            userRepository.deleteById(id.intValue());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return toAjax(1);
    }

    @ApiOperation("查询所有用户")
    @PostMapping("findAll")
    @ResponseBody
    public Object findAll ()
    {
        try {
            List<MongoUser> list = userRepository.findAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userRepository.findAll();
    }

    /**
     * 循环插入mongo数据库测试插入性能
     * 使用java8 IntStream
     */
    @ApiOperation("批量插入用户")
    @PostMapping("saveBatch")
    public AjaxResult saveBatch()
    {
        try {
                long start = System.currentTimeMillis();
                System.out.println("开始执行："+start);
                IntStream.range(1,1000000).forEach(e -> {
                MongoUser user = new MongoUser();
                user.setAge(e);
                user.setId(e);
                user.setName(""+ new Random().nextInt()+e);
                Mainbody mainbody = mainbodyRepository.findByCode("001");
                user.setMainbody(mainbody);
                userRepository.save(user);
                long end = System.currentTimeMillis();
                System.out.println("结束执行："+end);
                System.out.println("总共花："+((end-start) % 3600000) / 60000+"分钟");
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
        return success();
    }

    /**
     * 循环插入mongo数据库测试插入性能
     * 使用java8 IntStream
     * 插入100万 本机测试3分多钟
     */
    @ApiOperation("批量插入部门")
    @PostMapping("saveDeptBatch")
    public AjaxResult saveDeptBatch()
    {
        try {
            long start = System.currentTimeMillis();
            IntStream.range(1,1000000).forEach(e -> {
                MongoDept mongoDept = new MongoDept();
                mongoDept.setId(e);
                mongoDept.setName(""+new Random().nextInt()+e);
                mongoDeptRepository.save(mongoDept);
            });
            long end = System.currentTimeMillis();
            System.out.println("总共花："+((end-start) % 3600000) / 60000+"分钟");

        } catch (Exception e) {
            e.printStackTrace();
        }
        return success();
    }
}
