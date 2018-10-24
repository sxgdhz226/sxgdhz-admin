package com.ruoyi.project.web.service;


import com.ruoyi.framework.web.domain.AjaxResult;

public interface ISeckillDistributedService {

	/**
	 * 秒杀 一  单个商品
	 * @param seckillId 秒杀商品ID
	 * @param userId 用户ID
	 * @return
	 */
	AjaxResult startSeckilRedisLock(long seckillId, long userId);
	/**
	 * 秒杀 一  单个商品
	 * @param seckillId 秒杀商品ID
	 * @param userId 用户ID
	 * @return
	 */
	AjaxResult startSeckilZksLock(long seckillId, long userId);

	/**
	 * 秒杀 二 多个商品
	 * @param seckillId 秒杀商品ID
	 * @param userId 用户ID
	 * @param number 秒杀商品数量
	 * @return
	 */
	AjaxResult startSeckilLock(long seckillId, long userId, long number);
	
}
