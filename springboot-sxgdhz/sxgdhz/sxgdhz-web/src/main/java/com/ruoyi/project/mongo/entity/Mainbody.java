package com.ruoyi.project.mongo.entity;

import com.ruoyi.framework.web.domain.BaseEntity;
import lombok.Data;
import org.springframework.data.annotation.Id;

/**
 * 公司实体
 * @author doonly
 */
@Data
public class Mainbody {

	@Id
	private String id;
	private String code;
	private String name;
	private String description;
	

}
