package com.ruoyi.project.activiti.service.impl;


import com.ruoyi.common.support.Convert;
import com.ruoyi.common.utils.security.ShiroUtils;
import com.ruoyi.project.activiti.config.mapper.LeaveMapper;
import com.ruoyi.project.activiti.entity.LeaveBill;
import com.ruoyi.project.activiti.service.ILeaveBillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value = "leaveBillServiceImpl")
public class LeaveBillServiceImpl implements ILeaveBillService {

	@Autowired
	private LeaveMapper leaveBillDao;


	/**查询自己的请假单的信息*/
	@Override
	public List<LeaveBill> findLeaveBillList() {
		List<LeaveBill> list = leaveBillDao.findLeaveBillList();
		return list;
	}
	
	/**保存请假单*/
	@Override
	public int saveLeaveBill(LeaveBill leaveBill) {
		//获取请假单ID
		Long id = leaveBill.getId();
		/**新增保存*/
		if(id==null){
			//1：从Session中获取当前用户对象，将LeaveBill对象中user与Session中获取的用户对象进行关联
			leaveBill.setUser(ShiroUtils.getUser());//建立管理关系
			//2：保存请假单表，添加一条数据
			return leaveBillDao.saveLeaveBill(leaveBill);
		}
		/**更新保存*/
		else{
			//1：执行update的操作，完成更新
			return leaveBillDao.updateLeaveBill(leaveBill);
		}
		
	}
	
	/**使用请假单ID，查询请假单的对象*/
	@Override
	public LeaveBill findLeaveBillById(Long id) {
		LeaveBill bill = leaveBillDao.findLeaveBillById(id);
		return bill;
	}
	
	/**使用请假单ID，删除请假单*/
	@Override
	public int deleteLeaveBillById(String ids) {
		return leaveBillDao.deleteLeaveBillById(Convert.toStrArray(ids));
	}

}
