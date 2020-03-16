package com.mx.pojo;

import java.io.Serializable;

/**
 *
 * 退换货申请表表实体类
 *
 */
public class Apply_return implements Serializable {

    //表id
    private Integer applyId;

    //订单表id
    private Order order;

    //理由
    private String reason;

    //申请状态
    private Integer applyStatus;

    public Integer getApplyId() {
        return applyId;
    }

    public void setApplyId(Integer applyId) {
        this.applyId = applyId;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Integer getApplyStatus() {
        return applyStatus;
    }

    public void setApplyStatus(Integer applyStatus) {
        this.applyStatus = applyStatus;
    }


    @Override
    public String toString() {
        return "apply_return{" +
                "applyId=" + applyId +
                ", order=" + order +
                ", reason='" + reason + '\'' +
                ", applyStatus=" + applyStatus +
                '}';
    }
}