package com.mx.pojo;

import java.io.Serializable;
/**
 *
 * 雇员实体类
 *
 */
public class Employee implements Serializable {
    private Integer emId;

    private String name;

    private String password;

    private static final long serialVersionUID = 1L;

    public Integer getEmId() {
        return emId;
    }

    public void setEmId(Integer emId) {
        this.emId = emId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }
}