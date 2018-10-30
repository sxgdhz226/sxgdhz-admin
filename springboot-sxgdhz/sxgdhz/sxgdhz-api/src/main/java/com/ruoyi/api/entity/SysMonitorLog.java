package com.ruoyi.api.entity;

import lombok.Data;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**  indexName代表数据库名称
 *   type 代表表名称
 *
 */

@Document(indexName = "elasticsearch",type = "sysMonitorLog",shards = 1, replicas = 0)
@Data
public class SysMonitorLog implements Serializable{

	private static final long serialVersionUID = 8697547431971635124L;

	@Id
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

}
