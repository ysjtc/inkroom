package com.mx.pojo;/*
@author 郭子雄
@description 存放所有与用户相关信息的类
*/

public class UserData {
    private String name;//账号

    private String password;//密码

    private String email;//邮箱

    private String uName;//昵称

    private String userPath;//用户头像路径

    private Integer score;//积分

    private String sex;//性别

    private String tel;//密码


    public String getSex() { return sex; }

    public void setSex(String sex) { this.sex = sex; }

    public String getTel() { return tel; }

    public void setTel(String tel) { this.tel = tel; }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getuName() {
        return uName;
    }

    public void setuName(String uName) {
        this.uName = uName;
    }

    public String getUserPath() {
        return userPath;
    }

    public void setUserPath(String userPath) {
        this.userPath = userPath;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "UserData{" +
                "name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", uName='" + uName + '\'' +
                ", userPath='" + userPath + '\'' +
                ", score=" + score +
                ", sex='" + sex + '\'' +
                ", tel='" + tel + '\'' +
                '}';
    }
}
