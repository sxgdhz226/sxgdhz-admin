package com.ruoyi.project.web.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "user")
public class PostgreUser {
    @Id
    private Integer id;
    private String name;
    private Integer age;
}
