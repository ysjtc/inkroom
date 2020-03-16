package com.mx.pojo;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
/**
 *
 * 地址表实体类
 *
 */
public class Address implements Serializable {

    private Integer addId;//数据库自增id

    private Integer uId;//用户数据库自增的id

    @NotBlank(message = "电话号码不能为空")
    private String tel;//电话号码

    @NotBlank(message = "邮编不能为空")
    private String postcode;//邮编

    @NotBlank(message = "收货人姓名不能为空")
    private String name;//收货人姓名

    @NotBlank(message = "详细地址不能为空")
    private String addr;//详细地址

    @NotBlank(message = "省份不能为空")
    private String province;//省份


    public Address() {
    }

    private static final long serialVersionUID = 1L;

    public Integer getAddId() {
        return addId;
    }

    public void setAddId(Integer addId) {
        this.addId = addId;
    }

    public Integer getuId() {
        return uId;
    }

    public void setuId(Integer uId) {
        this.uId = uId;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel == null ? null : tel.trim();
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode == null ? null : postcode.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr == null ? null : addr.trim();
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province == null ? null : province.trim();
    }



    @Override
    public String toString() {
        return "Address{" +
                "addId=" + addId +
                ", uId=" + uId +
                ", tel='" + tel + '\'' +
                ", postcode='" + postcode + '\'' +
                ", name='" + name + '\'' +
                ", addr='" + addr + '\'' +
                ", province='" + province + '\'' +
                '}';
    }
}