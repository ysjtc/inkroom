package com.mx.pojo;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
/**
 *
 * 物流表实体类
 *
 */
public class Logistics implements Serializable {

    //物流表的id
    private Integer lId;

    //订单表的id
    @NotNull(message = "订单id不能为null")
    private Integer oId;

    //物流单号
    @NotEmpty(message = "物流单号不能为空")
    private String waybillNum;

    //物流公司
    @NotEmpty(message = "物流公司不能为空")
    private String company;

    private static final long serialVersionUID = 1L;

    public Integer getlId() {
        return lId;
    }

    public void setlId(Integer lId) {
        this.lId = lId;
    }

    public Integer getoId() {
        return oId;
    }

    public void setoId(Integer oId) {
        this.oId = oId;
    }

    public String getWaybillNum() {
        return waybillNum;
    }

    public void setWaybillNum(String waybillNum) {
        this.waybillNum = waybillNum == null ? null : waybillNum.trim();
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company == null ? null : company.trim();
    }

    @Override
    public String toString() {
        return "Logistics{" +
                "lId=" + lId +
                ", oId=" + oId +
                ", waybillNum='" + waybillNum + '\'' +
                ", company='" + company + '\'' +
                '}';
    }
}