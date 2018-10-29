package com.ruoyi.api.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;

import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Data
@TableName(value = "typhoonlist")
public class Typhoon implements Serializable{

    @TableId(value = "tcid",type = IdType.AUTO)
    private int tcid;

    private int tsid;

    private String tscname;

    private String tsename;

    private String intlid;
    private String landon;
    private String orgin;
    private String meanings;
    private String remark;
    private Date createtime;
    private String flag;

}
