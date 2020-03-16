package com.mx.pojo;

import java.io.Serializable;
/**
 *
 * 商品类型表实体类
 *
 */
public class Category implements Serializable {


    private Integer cId;

    private String CateName;

    private static final long serialVersionUID = 1L;

    public Integer getcId() {
        return cId;
    }

    public void setcId(Integer cId) {
        this.cId = cId;
    }

    public String getCateName() {
        return CateName;
    }

    public void setCateName(String cateName) {
        CateName = cateName;
    }

    @Override
    public String toString() {
        return "Category{" +
                "cId=" + cId +
                ", CateName='" + CateName + '\'' +
                '}';
    }
}