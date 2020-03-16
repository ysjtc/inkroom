package com.mx.pojo;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;
/**
 *
 * 超级管理员
 *
 */
public class SuperAdmin implements Serializable {
    private Integer superId;
    @NotEmpty
    @Pattern(regexp = "^([a-zA-Z]*[0-9_-]*$)", message = "只能包含字母、数字、下划线，且不能以数字或下划线开头")
    @Size(max = 10)
    private String name;
    @NotEmpty
    @Pattern(regexp = "/^([a-zA-Z]*[0-9_-]*$)"+"^(?=.*\\d)(?=.*[a-zA-Z])(?=.*[\\W_]).{8,32}$/",message = "只能是长度为8-32位包含数字、字母、特殊字符的密码,且不能以数字或下划线开头")
    @Size(min = 8,max = 32)
    private String password;

    private static final long serialVersionUID = 1L;

    public Integer getSuperId() {
        return superId;
    }

    public void setSuperId(Integer superId) {
        this.superId = superId;
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

    @Override
    public String toString() {
        return "SuperAdmin{" +
                "superId=" + superId +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}