package com.mx.pojo;

import java.io.Serializable;
/**
 *
 * 订单详情表表实体类
 *
 */
public class Order_Detail implements Serializable {

    private Integer odId;

    private Integer oId;

    private Integer itemId;

    private Integer itemCount;

    private Float totalPrice;

    private static final long serialVersionUID = 1L;

    public Order_Detail() {
    }

    public Integer getOdId() {
        return odId;
    }

    public void setOdId(Integer odId) {
        this.odId = odId;
    }

    public Integer getoId() {
        return oId;
    }

    public void setoId(Integer oId) {
        this.oId = oId;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public Integer getItemCount() {
        return itemCount;
    }

    public void setItemCount(Integer itemCount) {
        this.itemCount = itemCount;
    }

    public Float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Float totalPrice) {
        this.totalPrice = totalPrice;
    }


    @Override
    public String toString() {
        return "Order_Detail{" +
                "odId=" + odId +
                ", oId=" + oId +
                ", itemId=" + itemId +
                ", itemCount=" + itemCount +
                ", totalPrice=" + totalPrice +
                '}';
    }
}