package com.mx.pojo;/*
@author 郭子雄
@description 接收数据类
*/

public class QueryData {
    private String name;
    private String uName;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getuName() {
        return uName;
    }

    public void setuName(String uName) {
        this.uName = uName;
    }

    @Override
    public String toString() {
        return "queryData{" +
                "name='" + name + '\'' +
                ", uName='" + uName + '\'' +
                '}';
    }
}
