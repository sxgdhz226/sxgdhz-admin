package com.ruoyi.project.web.service.impl;

import com.ruoyi.common.dynamicQuery.DynamicQuery;
import com.ruoyi.framework.aspectj.lang.annotation.ServiceLimit;
import com.ruoyi.framework.aspectj.lang.annotation.Servicelock;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.project.web.entity.Seckill;
import com.ruoyi.project.web.entity.SuccessKilled;
import com.ruoyi.project.web.repository.SeckillRepository;
import com.ruoyi.project.web.service.ISeckillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 使用springboot2.0 时findOne报错 换成
 *
 */

@Service("seckillService")
public class SeckillServiceImpl implements ISeckillService {
    /**
     * 思考：为什么不用synchronized
     * service 默认是单例的，并发下lock只有一个实例
     */
	private Lock lock = new ReentrantLock(true);//互斥锁 参数默认false，不公平锁  
	
	@Autowired
	private DynamicQuery dynamicQuery;
	@Autowired
	private SeckillRepository seckillRepository;
	
	@Override
	public List<Seckill> getSeckillList() {
		return seckillRepository.findAll();
	}

	@Override
	public Seckill getById(long seckillId) {
		return seckillRepository.findById(seckillId).get();
	}

	@Override
	public Long getSeckillCount(long seckillId) {
		String nativeSql = "SELECT count(*) FROM success_killed WHERE seckill_id=?";
		Object object =  dynamicQuery.nativeQueryObject(nativeSql, new Object[]{seckillId});
		return ((Number) object).longValue();
	}
	@Override
	@Transactional
	public void deleteSeckill(long seckillId) {
		String nativeSql = "DELETE FROM  success_killed WHERE seckill_id=?";
		dynamicQuery.nativeExecuteUpdate(nativeSql, new Object[]{seckillId});
		nativeSql = "UPDATE seckill SET number =100 WHERE seckill_id=?";
		dynamicQuery.nativeExecuteUpdate(nativeSql, new Object[]{seckillId});
	}
	@Override
	@ServiceLimit
	@Transactional
	public AjaxResult startSeckil(long seckillId,long userId) {
		//校验库存
		String nativeSql = "SELECT number FROM seckill WHERE seckill_id=?";
		Object object =  dynamicQuery.nativeQueryObject(nativeSql, new Object[]{seckillId});
		Long number =  ((Number) object).longValue();
		if(number>0){
			//扣库存
			nativeSql = "UPDATE seckill  SET number=number-1 WHERE seckill_id=?";
			dynamicQuery.nativeExecuteUpdate(nativeSql, new Object[]{seckillId});
			//创建订单
			SuccessKilled killed = new SuccessKilled();
			killed.setSeckillId(seckillId);
			killed.setUserId(userId);
			killed.setState((short)0);
			killed.setCreateTime(new Timestamp(new Date().getTime()));
			dynamicQuery.save(killed);
			//支付
			return AjaxResult.error("秒杀成功");
		}else{
			return AjaxResult.error("秒杀结束");
		}
	}
	@Override
	@Transactional
	public AjaxResult  startSeckilLock(long seckillId, long userId) {
		 try {
			lock.lock();
			//这里、不清楚为啥、总是会被超卖101、难道锁不起作用、lock是同一个对象
			//来自热心网友 zoain 的细心测试思考、然后自己总结了一下
			//事物未提交之前，锁已经释放(事物提交是在整个方法执行完)，导致另一个事物读取到了这个事物未提交的数据，也就是传说中的脏读。建议锁上移
			//给自己留个坑思考：为什么分布式锁(zk和redis)没有问题？(事实是有问题的，由于redis释放锁需要远程通信，不那么明显而已)
			String nativeSql = "SELECT number FROM seckill WHERE seckill_id=?";
			Object object =  dynamicQuery.nativeQueryObject(nativeSql, new Object[]{seckillId});
			Long number =  ((Number) object).longValue();
			if(number>0){
				nativeSql = "UPDATE seckill  SET number=number-1 WHERE seckill_id=?";
				dynamicQuery.nativeExecuteUpdate(nativeSql, new Object[]{seckillId});
				SuccessKilled killed = new SuccessKilled();
				killed.setSeckillId(seckillId);
				killed.setUserId(userId);
				killed.setState(Short.parseShort(number+""));
				killed.setCreateTime(new Timestamp(new Date().getTime()));
				dynamicQuery.save(killed);
			}else{
				return AjaxResult.error("秒杀结束");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			lock.unlock();
		}
		return AjaxResult.success("秒杀成功");
	}

	/**
	 * 通过注解的方法给方法加索
	 * @param seckillId
	 * @param userId
	 * @return
	 */
	@Override
	@Servicelock
	@Transactional
	public AjaxResult startSeckilAopLock(long seckillId, long userId) {
		//来自码云码友<马丁的早晨>的建议 使用AOP + 锁实现
		String nativeSql = "SELECT number FROM seckill WHERE seckill_id=?";
		Object object =  dynamicQuery.nativeQueryObject(nativeSql, new Object[]{seckillId});
		Long number =  ((Number) object).longValue();
		if(number>0){
			nativeSql = "UPDATE seckill  SET number=number-1 WHERE seckill_id=?";
			dynamicQuery.nativeExecuteUpdate(nativeSql, new Object[]{seckillId});
			SuccessKilled killed = new SuccessKilled();
			killed.setSeckillId(seckillId);
			killed.setUserId(userId);
			killed.setState(Short.parseShort(number+""));
			killed.setCreateTime(new Timestamp(new Date().getTime()));
			dynamicQuery.save(killed);
		}else{
			return AjaxResult.error("秒杀结束");
		}
		return AjaxResult.success("秒杀成功");
	}
	//注意这里 限流注解 可能会出现少买 自行调整
	@Override
	@ServiceLimit
	@Transactional
	public AjaxResult startSeckilDBPCC_ONE(long seckillId, long userId) {
		//单用户抢购一件商品或者多件都没有问题
		String nativeSql = "SELECT number FROM seckill WHERE seckill_id=? FOR UPDATE";
		Object object =  dynamicQuery.nativeQueryObject(nativeSql, new Object[]{seckillId});
		Long number =  ((Number) object).longValue();
		if(number>0){
			nativeSql = "UPDATE seckill  SET number=number-1 WHERE seckill_id=?";
			dynamicQuery.nativeExecuteUpdate(nativeSql, new Object[]{seckillId});
			SuccessKilled killed = new SuccessKilled();
			killed.setSeckillId(seckillId);
			killed.setUserId(userId);
			killed.setState((short)0);
			killed.setCreateTime(new Timestamp(new Date().getTime()));
			dynamicQuery.save(killed);
			return AjaxResult.success("秒杀成功");
		}else{
			return AjaxResult.error("秒杀结束");
		}
	}
    /**
     * SHOW STATUS LIKE 'innodb_row_lock%'; 
     * 如果发现锁争用比较严重，如InnoDB_row_lock_waits和InnoDB_row_lock_time_avg的值比较高
     */
	@Override
	@Transactional
	public AjaxResult startSeckilDBPCC_TWO(long seckillId, long userId) {
		//单用户抢购一件商品没有问题、但是抢购多件商品不建议这种写法
		String nativeSql = "UPDATE seckill  SET number=number-1 WHERE seckill_id=? AND number>0";//UPDATE锁表
		int count = dynamicQuery.nativeExecuteUpdate(nativeSql, new Object[]{seckillId});
		if(count>0){
			SuccessKilled killed = new SuccessKilled();
			killed.setSeckillId(seckillId);
			killed.setUserId(userId);
			killed.setState((short)0);
			killed.setCreateTime(new Timestamp(new Date().getTime()));
			dynamicQuery.save(killed);
			return AjaxResult.success("秒杀成功");
		}else{
			return AjaxResult.error("秒杀结束");
		}
	}
	@Override
	@Transactional
	public AjaxResult startSeckilDBOCC(long seckillId, long userId, long number) {
		Seckill kill = seckillRepository.findById(seckillId).get();
		//if(kill.getNumber()>0){
		if(kill.getNumber()>=number){//剩余的数量应该要大于等于秒杀的数量
			//乐观锁
			String nativeSql = "UPDATE seckill  SET number=number-?,version=version+1 WHERE seckill_id=? AND version = ?";
			int count = dynamicQuery.nativeExecuteUpdate(nativeSql, new Object[]{number,seckillId,kill.getVersion()});
			if(count>0){
				SuccessKilled killed = new SuccessKilled();
				killed.setSeckillId(seckillId);
				killed.setUserId(userId);
				killed.setState((short)0);
				killed.setCreateTime(new Timestamp(new Date().getTime()));
				dynamicQuery.save(killed);
				return AjaxResult.success("秒杀成功");
			}else{
				return AjaxResult.error("秒杀结束");
			}
		}else{
			return AjaxResult.error("秒杀结束");
		}
	}

	@Override
	public AjaxResult startSeckilTemplate(long seckillId, long userId, long number) {
		return null;
	}

}
