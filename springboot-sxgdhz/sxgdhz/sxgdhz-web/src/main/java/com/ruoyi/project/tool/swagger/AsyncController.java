package com.ruoyi.project.tool.swagger;

import com.ruoyi.framework.config.async.task.AsyncTask;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import java.util.concurrent.Future;

/**
 * 异步任务调用，同时调用三个任务，总共花的时间比一个一个执行少
 */
@Api("async")
@RestController
@RequestMapping("/async")
public class AsyncController extends BaseController{

    @Autowired
    private  AsyncTask task;

    @ApiOperation("task")
    @RequestMapping(value = "/task", method = RequestMethod.GET)
    public AjaxResult task() throws Exception {
        long start = System.currentTimeMillis();

        Future<String> task1 = task.doTaskOne();
        Future<String> task2 = task.doTaskTwo();
        Future<String> task3 = task.doTaskThree();

        while (true) {
            if (task1.isDone() && task2.isDone() && task3.isDone()) {
                // 三个任务都调用完成，退出循环等待
                break;
            }
            Thread.sleep(1000);
        }

        long end = System.currentTimeMillis();

        System.out.println("任务全部完成，总耗时：" + (end - start) + "毫秒");
        return toAjax(1);
    }

}
