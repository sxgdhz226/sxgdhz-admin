package com.ruoyi.project.utils;

import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.security.ShiroUtils;
import com.ruoyi.common.utils.spring.SpringUtils;
import com.ruoyi.project.system.dept.domain.Dept;
import com.ruoyi.project.system.user.domain.User;
import com.ruoyi.project.system.user.mapper.UserMapper;
import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;
import org.apache.shiro.session.mgt.SessionContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 员工经理任务分配
 *
 */
@SuppressWarnings("serial")
public class ManagerTaskHandler implements TaskListener {

	@Override
	public void notify(DelegateTask delegateTask) {

		UserMapper userMapper = SpringUtils.getBean("userMapper");
		List<User> userList = userMapper.selectUser(ShiroUtils.getLoginName());

		System.out.println(userList.get(0).getLoginName()+"=======================================");

        delegateTask.setAssignee(userList.get(0).getLoginName());
	}

}
