package com.ruoyi.framework.config.crawler.model;

import com.ruoyi.framework.model.BaseModel;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "shikigame")
// lomlok
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class ShikigamiModel extends BaseModel{

    /**
     * 名字
     */
    private String name;

    /**
     * 图片
     */
    private String image;

    /**
     * 声优
     */
    private String seiyou;


    /**
     * 性别
     */
    private String sex;

    /**
     * 星级
     */
    private String star;

    /**
     * 获取方式
     */
    private String getWay;


    /**
     * N/R/SR/SSR
     */
    private String level;


    /**
     * 描述
     */
    private String des;


}
