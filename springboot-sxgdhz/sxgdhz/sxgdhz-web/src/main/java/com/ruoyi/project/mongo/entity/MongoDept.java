package com.ruoyi.project.mongo.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class MongoDept {

    @Id
    private Integer id;

    private String name;
}
