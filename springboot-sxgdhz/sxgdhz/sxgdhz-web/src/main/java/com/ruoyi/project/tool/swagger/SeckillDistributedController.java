package com.ruoyi.project.tool.swagger;

import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.project.web.service.ISeckillDistributedService;
import com.ruoyi.project.web.service.ISeckillService;
import com.ruoyi.project.web.service.impl.MonitorServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * 分布式秒杀
 * tags  指定文档标题
 *
 */
@Api(tags ="分布式秒杀")
@RestController
@RequestMapping("/seckillDistributed")
public class SeckillDistributedController extends BaseController{

    private static final Logger log = LoggerFactory.getLogger(SeckillDistributedController.class);

    @Autowired
    private ISeckillDistributedService seckillDistributedService;

    @Autowired
    private ISeckillService seckillService;

    //获取系统可用的处理器数量
    private static int corePoolSize = Runtime.getRuntime().availableProcessors();

    private static ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(corePoolSize, corePoolSize+1, 10l, TimeUnit.SECONDS,
            new LinkedBlockingQueue<Runnable>(10000));

    @ApiOperation(value="秒杀一(Rediss分布式锁)",nickname="路漫漫其修远兮")
    @PostMapping("/startRedisLock")
    public AjaxResult startRedisLock(long seckillId){

        IntStream.range(1, 1000).forEach(e -> {
            final long userId = e;
            threadPoolExecutor.submit(() -> {
                AjaxResult ajaxResult = seckillDistributedService.startSeckilRedisLock(seckillId, userId);
                log.info("用户：{}{}",userId,ajaxResult.get("msg"));
            });
        });

        try {
            Thread.sleep(15000);

            Long  seckillCount = seckillService.getSeckillCount(seckillId);
            //日志级联{}表示参数占位符
            log.info("一共秒杀出{}件商品",seckillCount);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return toAjax(1);
    }
}
