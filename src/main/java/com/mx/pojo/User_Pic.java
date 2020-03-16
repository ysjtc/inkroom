package com.mx.pojo;

import java.io.Serializable;
/**
 *
 * User头像路径实体类
 *
 */
public class User_Pic implements Serializable {
    private Integer uId;

    private String userPath;

    private static final long serialVersionUID = 1L;

    public Integer getuId() {
        return uId;
    }

    public void setuId(Integer uId) {
        this.uId = uId;
    }

    public String getUserPath() {
        return userPath;
    }

    public void setUserPath(String userPath) {
        this.userPath = userPath == null ? null : userPath.trim();
    }
}