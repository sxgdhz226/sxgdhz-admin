package com.ruoyi.project.activiti.vo;

import org.activiti.engine.impl.event.logger.DatabaseEventFlusher;

import java.util.Date;

/**
 *   任务实体解决 activiti task不能直接转成json的问题
 */
public class TaskVo {

    private String id;

    private String name;

    private Date createTime;

    private String assignee;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getAssignee() {
        return assignee;
    }

    public void setAssignee(String assignee) {
        this.assignee = assignee;
    }
}
