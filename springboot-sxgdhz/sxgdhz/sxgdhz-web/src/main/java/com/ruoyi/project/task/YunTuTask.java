package com.ruoyi.project.task;

import com.ruoyi.project.utils.GuangYt;
import com.ruoyi.project.utils.ZaiWaiYt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class YunTuTask {
	private static final Logger logger = LoggerFactory.getLogger(YunTuTask.class);
	/**
	 * 可见光卫星云图
	 */
//	@Scheduled(cron="0 0/1 * * * ?")
	public void getGYt(){
		logger.info("执行下载可见光卫星云图定时任务");
	    GuangYt g = new GuangYt();
	    g.a();
	}  
	/**
	 * 紫外线卫星云图
	 */
//	@Scheduled(cron="0 0/20 * * * ?")
	public void getZYt(){
		logger.info("执行下载紫外线卫星云图定时任务");
		ZaiWaiYt z = new ZaiWaiYt();
		z.a();
	}  
}
