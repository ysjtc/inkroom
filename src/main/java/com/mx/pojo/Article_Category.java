package com.mx.pojo;

import java.io.Serializable;
/**
 *
 * 文章类型表实体类
 *
 */
public class Article_Category implements Serializable {
    private Integer acId;

    private String ACname;

    private static final long serialVersionUID = 1L;

    public Integer getAcId() {
        return acId;
    }

    public void setAcId(Integer acId) {
        this.acId = acId;
    }

    public String getACname() {
        return ACname;
    }

    public void setACname(String ACname) {
        this.ACname = ACname;
    }

    @Override
    public String toString() {
        return "Article_Category{" +
                "acId=" + acId +
                ", ACname='" + ACname + '\'' +
                '}';
    }
}