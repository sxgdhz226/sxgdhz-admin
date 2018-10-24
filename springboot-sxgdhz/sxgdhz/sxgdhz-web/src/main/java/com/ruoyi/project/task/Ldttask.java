/**  

* @Title: Ldttask.java

* @Package cn.net.ssd.task

* @Description: TODO(用一句话描述该文件做什么)

* @author A18ccms A18ccms_gmail_com  

* @date 2017-4-6 上午8:47:17

* @version V1.0  

*/
package com.ruoyi.project.task;

import com.ruoyi.project.utils.CopyLdtUtil;
import com.ruoyi.project.utils.Regionldt;
import com.ruoyi.project.utils.Shengldt;
import com.ruoyi.project.utils.Shengldt02;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 *类描述：
 *@author: e450
 *@date： 日期：2017-4-6 时间：上午8:47:17
 *@version 1.0
 */
@Component
public class Ldttask {
	private static final Logger logger = LoggerFactory.getLogger(Ldttask.class);
	/**
	 * 雷达图
	 */
	
//	@Scheduled(cron="0 0/1 * * * ?")
	public  void getsldt(){
		logger.info("执行下载省雷达图01定时任务");
		Shengldt.a();
	}
	/**
	 * 雷达图
	 */
//	@Scheduled(cron="0 0/6 * * * ?")
	public void getsldt02(){
		logger.info("执行下载省雷达图02定时任务");
		Shengldt02.a();
	}
	/**
	 * 雷达图
	 */
	@Scheduled(cron="0 0/6 * * * ?")
	public void Regionldt(){
		logger.info("执行下载区域雷达拼图定时任务");
		Regionldt.a();
	}
	/**
	 * 雷达图
	 */
//	@Scheduled(cron="0 0/6 * * * ?")
	public void ldtappic(){
		logger.info("执行下载组合雷达图任务");
		CopyLdtUtil.CopyLdt();
	}
	
	
}
