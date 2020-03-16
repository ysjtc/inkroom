package com.mx.pojo;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 *
 * User表实体类
 */
public class User implements Serializable {

    private Integer uId;//自增id字段

    private String name;//账号

    @NotBlank(message="密码不能为空")
    @Length(min=6,max =18,message = "密码长度必须在6到18位之间")
    private String password;//密码

    @Email
    private String email;//邮箱

    @NotBlank(message="用户昵称不能为空")
    private String uName;//昵称

    private String sex;//性别

    private String tel;//电话号码

    public User() {
    }

    private static final long serialVersionUID = 1L;

    public Integer getuId() {
        return uId;
    }

    public void setuId(Integer uId) {
        this.uId = uId;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) { this.email = email == null ? null : email.trim(); }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getuName() {
        return uName;
    }

    public void setuName(String uName) {
        this.uName = uName;
    }

    @Override
    public String toString() {
        return "User{" +
                "uId=" + uId +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", uName='" + uName + '\'' +
                ", tel='" + tel + '\'' +
                ", sex='" + sex + '\'' +
                '}';
    }
}