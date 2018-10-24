package com.ruoyi.project.servlet.bean;

import java.io.Serializable;

public class UserBean implements Serializable {

    private static final long serialVersionUID = -5190025603436708199L;

    private int id;
    private String name;
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}