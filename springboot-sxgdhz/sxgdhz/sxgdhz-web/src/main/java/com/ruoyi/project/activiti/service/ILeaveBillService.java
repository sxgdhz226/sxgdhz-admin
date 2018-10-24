package com.ruoyi.project.activiti.service;


import com.ruoyi.project.activiti.entity.LeaveBill;

import java.util.List;


public interface ILeaveBillService {

	List<LeaveBill> findLeaveBillList();

	int saveLeaveBill(LeaveBill leaveBill);

	LeaveBill findLeaveBillById(Long id);

	int deleteLeaveBillById(String ids);

}
