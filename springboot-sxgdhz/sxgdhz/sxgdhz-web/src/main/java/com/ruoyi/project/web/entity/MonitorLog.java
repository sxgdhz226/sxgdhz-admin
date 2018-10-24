package com.ruoyi.project.web.entity;

import java.io.Serializable;
import java.util.Date;

public class MonitorLog implements Serializable{

    private static final long serialVersionUID = 7952759884407540570L;

    private long id;// id
    private Date dateTime;// 创建时间
    private String taskId;// 任务id，关联task表
    private String parentId;
    private String taskName;// 任务名称
    private String target;// 任务目标地址
    private Date nextTime;// 下次执行时间
    private int result;// 执行结果 0：成功，1：失败
    private String resultDesc;// 结果描述

    private String sendSms;//0:发送，1:不发送

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public Date getNextTime() {
        return nextTime;
    }

    public void setNextTime(Date nextTime) {
        this.nextTime = nextTime;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public String getResultDesc() {
        return resultDesc;
    }

    public void setResultDesc(String resultDesc) {
        this.resultDesc = resultDesc;
    }

    public String getSendSms() {
        return sendSms;
    }

    public void setSendSms(String sendSms) {
        this.sendSms = sendSms;
    }
}
