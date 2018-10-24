package com.ruoyi.project.activiti.config.mapper;

import com.ruoyi.project.activiti.entity.LeaveBill;

import java.util.List;

public interface LeaveMapper {

    List<LeaveBill> findLeaveBillList();

    int saveLeaveBill(LeaveBill leaveBill);

    LeaveBill findLeaveBillById(Long id);

    int deleteLeaveBillById(String[] ids);

    int updateLeaveBill(LeaveBill leaveBill);
}
