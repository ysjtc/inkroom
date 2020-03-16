package com.mx.pojo;

import java.io.Serializable;
/**
 *
 * 商品图片路径实体类
 *
 */
public class Item_Pic implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer ipId;

    private Integer itemId;

    private String picPath;


    public Integer getIpId() {
        return ipId;
    }

    public void setIpId(Integer ipId) {
        this.ipId = ipId;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public String getPicPath() {
        return picPath;
    }

    public void setPicPath(String picPath) {
        this.picPath = picPath == null ? null : picPath.trim();
    }


    @Override
    public String toString() {
        return "Item_Pic{" +
                "ipId=" + ipId +
                ", itemId=" + itemId +
                ", picPath='" + picPath + '\'' +
                '}';
    }
}