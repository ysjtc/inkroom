package com.mx.pojo;

import java.io.Serializable;
/**
 *
 *订单表实体类
 *
 */
public class Order implements Serializable {

    private static final long serialVersionUID = 1L;

    //订单表的id
    private Integer oId;

    //订单名称
    private String oName;

    //商品名称
    private Item itemName;

    //商品详细
    private  Order_Detail order_detail;

    //当前登陆的用户id
    private User user;

    //当前登陆的用户的地址id
    private Address address;

    //订单的创建时间
    private String createTime;

    //订单中的商品数量
    private String number;

    //当前订单的状态
    private String oStatus;

    //备注
    private String note;

    //退换货申请表
    private Apply_return apply_return;

    public Order() {
    }

    public Integer getoId() {
        return oId;
    }

    public void setoId(Integer oId) {
        this.oId = oId;
    }

    public String getoName() {
        return oName;
    }

    public void setoName(String oName) {
        this.oName = oName;
    }

    public User getuId() {
        return user;
    }

    public void setuId(User user) {
        this.user = user;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getoStatus() {
        return oStatus;
    }

    public void setoStatus(String oStatus) {
        this.oStatus = oStatus;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime == null ? null : createTime.trim();
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number == null ? null : number.trim();
    }


    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note == null ? null : note.trim();
    }

    public Item getItemName() {
        return itemName;
    }

    public void setItemName(Item itemName) {
        this.itemName = itemName;
    }

    public Order_Detail getOrder_detail() {
        return order_detail;
    }

    public void setOrder_detail(Order_Detail order_detail) {

        this.order_detail = order_detail;
    }

    public Apply_return getApply_return() {
        return apply_return;
    }

    public void setApply_return(Apply_return apply_return) {
        this.apply_return = apply_return;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    //    前台展示
    public String TOJSON(){//ipad：2;iphone:1;
        return  "{"+"\""+"oId"+"\":"+"\""+ oId +"\","+
                "\""+"oName"+"\":"+"\""+oName+"\","+
                "\""+"Number"+"\":"+"\""+number+"\","+
                "\""+"ItemName"+"\":"+"\""+itemName.getName()+"\","+
                "\""+"Address"+"\":"+"\""+address.getAddr()+"\","+
                "\""+"Note"+"\":"+"\""+note+"\","+
                "\""+"createTime"+"\":"+"\""+createTime+"\","+
                "\""+"totalPrice"+"\":"+"\""+order_detail.getTotalPrice()+"\","+
                "\""+"Status"+"\":"+"\""+oStatus+"\""+
                "}";
    }
//        后台管理
    public String TOJSONBackManage(){//ipad：2;iphone:1;
        return  "{"+"\""+"oId"+"\":"+"\""+ oId +"\","+
                "\""+"Number"+"\":"+"\""+number+"\","+
                "\""+"Name"+"\":"+"\""+user.getName()+"\","+
                "\""+"ItemName"+"\":"+"\""+itemName.getName()+" \","+
                "\""+"Address"+"\":"+"\""+address.getAddr()+"\","+
                "\""+"Note"+"\":"+"\""+note+"\","+
                "\""+"createTime"+"\":"+"\""+createTime+"\","+
                "\""+"totalPrice"+"\":"+"\""+order_detail.getTotalPrice()+"\","+
                "\""+"Status"+"\":"+"\""+oStatus+"\""+
                "}";
    }
//    退单
    public String TOJSONBackManageReturn(){//ipad：2;iphone:1;
        return  "{"+
                "\""+"applyId"+"\":"+"\""+apply_return.getApplyId()+"\","+
                "\""+"number"+"\":"+"\""+number+"\","+
                "\""+"name"+"\":"+"\""+user.getName()+"\","+
                "\""+"item"+"\":"+"\""+itemName.getName()+"\","+
                "\""+"create_time"+"\":"+"\""+createTime+"\","+
                "\""+"reason"+"\":"+"\""+apply_return.getReason().trim()+"\","+
                "\""+"status"+"\":"+"\""+apply_return.getApplyStatus()+"\""+
                "}";
    }

    @Override
    public String toString() {
        return "Order{" +
                "oId=" + oId +
                ", oName='" + oName + '\'' +
                ", itemName=" + itemName +
                ", order_detail=" + order_detail +
                ", user=" + user +
                ", address=" + address +
                ", createTime='" + createTime + '\'' +
                ", number='" + number + '\'' +
                ", oStatus='" + oStatus + '\'' +
                ", note='" + note + '\'' +
                ", apply_return=" + apply_return +
                '}';
    }
}