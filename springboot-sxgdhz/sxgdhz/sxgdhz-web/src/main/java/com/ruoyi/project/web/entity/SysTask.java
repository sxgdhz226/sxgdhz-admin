package com.ruoyi.project.web.entity;

import java.util.Date;
import java.util.Map;

public class SysTask {
	/**
	 * 
	 */
	public static String REF = "CmsTask";
	public static String PROP_USER = "user";
	public static String PROP_JOB_CLASS = "jobClass";
	public static String PROP_SITE = "site";
	public static String PROP_TYPE = "type";
	public static String PROP_INTERVAL_MINUTE = "intervalMinute";
	public static String PROP_TASK_CODE = "taskCode";
	public static String PROP_EXECYCLE = "execycle";
	public static String PROP_CRON_EXPRESSION = "cronExpression";
	public static String PROP_INTERVAL_HOUR = "intervalHour";
	public static String PROP_INTERVAL_UNIT = "intervalUnit";
	public static String PROP_DAY_OF_WEEK = "dayOfWeek";
	public static String PROP_NAME = "name";
	public static String PROP_DAY_OF_MONTH = "dayOfMonth";
	public static String PROP_HOUR = "hour";
	public static String PROP_ENABLE = "enable";
	public static String PROP_CREATE_TIME = "createTime";
	public static String PROP_MINUTE = "minute";
	public static String PROP_ID = "id";
	public static String PROP_REMARK = "remark";
	
	private String sendsplit;//发送时间间隔
	private String sendLastTime;//最后发送时间
	private String sendCount;//发送次数，大于规定次数，
	private String sendMaxCount;//发送次数，大于规定次数，
	private String sendMaxSplit;//最后发送时间，超过该间隔，sendCount 归0；
	private String sendcode;//发送短信num
	
	public String getSendcode() {
		return sendcode;
	}
	public void setSendcode(String sendcode) {
		this.sendcode = sendcode;
	}
	public String getSendMaxCount() {
		return sendMaxCount;
	}
	public void setSendMaxCount(String sendMaxCount) {
		this.sendMaxCount = sendMaxCount;
	}
	public String getSendsplit() {
		return sendsplit;
	}
	public void setSendsplit(String sendsplit) {
		this.sendsplit = sendsplit;
	}
	public String getSendLastTime() {
		return sendLastTime;
	}
	public void setSendLastTime(String sendLastTime) {
		this.sendLastTime = sendLastTime;
	}
	public String getSendCount() {
		return sendCount;
	}
	public void setSendCount(String sendCount) {
		this.sendCount = sendCount;
	}
	public String getSendMaxSplit() {
		return sendMaxSplit;
	}
	public void setSendMaxSplit(String sendMaxSplit) {
		this.sendMaxSplit = sendMaxSplit;
	}
	// fields
	private String taskCode; //任务名称，没有id代替，唯一
	public String getTaskCode() {
		return taskCode;
	}
	public void setTaskCode(String taskCode) {
		this.taskCode = taskCode;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getJobClass() {
		return jobClass;
	}
	public void setJobClass(String jobClass) {
		this.jobClass = jobClass;
	}
	public Integer getExecycle() {
		return execycle;
	}
	public void setExecycle(Integer execycle) {
		this.execycle = execycle;
	}
	public Integer getDayOfMonth() {
		return dayOfMonth;
	}
	public void setDayOfMonth(Integer dayOfMonth) {
		this.dayOfMonth = dayOfMonth;
	}
	public Integer getDayOfWeek() {
		return dayOfWeek;
	}
	public void setDayOfWeek(Integer dayOfWeek) {
		this.dayOfWeek = dayOfWeek;
	}
	public Integer getHour() {
		return hour;
	}
	public void setHour(Integer hour) {
		this.hour = hour;
	}
	public Integer getMinute() {
		return minute;
	}
	public void setMinute(Integer minute) {
		this.minute = minute;
	}
	public Integer getIntervalHour() {
		return intervalHour;
	}
	public void setIntervalHour(Integer intervalHour) {
		this.intervalHour = intervalHour;
	}
	public Integer getIntervalMinute() {
		return intervalMinute;
	}
	public void setIntervalMinute(Integer intervalMinute) {
		this.intervalMinute = intervalMinute;
	}
	public Integer getIntervalUnit() {
		return intervalUnit;
	}
	public void setIntervalUnit(Integer intervalUnit) {
		this.intervalUnit = intervalUnit;
	}
	public String getCronExpression() {
		return cronExpression;
	}
	public void setCronExpression(String cronExpression) {
		this.cronExpression = cronExpression;
	}
	public boolean getEnable() {
		if(en_able==0)
			return true;
		else{
			return false;
		}
	}
	public void setEnable(boolean enable) {
		this.enable = enable;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	private Integer type;//类型
	private String name;
	private String jobClass;//调用类
	private Integer execycle;//执行方法
	private Integer dayOfMonth;
	private Integer dayOfWeek;
	private Integer hour;
	private Integer minute;
	private Integer intervalHour;
	private Integer intervalMinute;
	private Integer intervalUnit;
	private String cronExpression;
	private boolean enable =true;
	private int en_able ;
	public int getEn_able() {
		return en_able;
	}
	public void setEn_able(int en_able) {
		this.en_able = en_able;
	}
	private String remark;
	private String description;
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	private Date createTime;

}
