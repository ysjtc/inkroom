package com.mx.pojo;

import java.io.Serializable;
/**
 *
 * 退换货实体类
 *
 */
public class Logistics_Return implements Serializable {

    //退换货表id
    private Integer lrId;

    //退货订单id
    private Integer oId;

    //物流表id
    private Integer lId;

    //退货单号
    private String waybillNum;

    //退货原因
    private String company;

    private static final long serialVersionUID = 1L;

    public Integer getLrId() {
        return lrId;
    }

    public void setLrId(Integer lrId) {
        this.lrId = lrId;
    }

    public Integer getoId() {
        return oId;
    }

    public void setoId(Integer oId) {
        this.oId = oId;
    }

    public Integer getlId() {
        return lId;
    }

    public void setlId(Integer lId) {
        this.lId = lId;
    }

    public String getWaybillNum() {
        return waybillNum;
    }

    public void setWaybillNum(String waybillNum) {
        this.waybillNum = waybillNum;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    @Override
    public String toString() {
        return "Logistics_Return{" +
                "lrId=" + lrId +
                ", oId=" + oId +
                ", lId=" + lId +
                ", waybillNum='" + waybillNum + '\'' +
                ", company='" + company + '\'' +
                '}';
    }
}