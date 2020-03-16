package com.mx.pojo;

import java.io.Serializable;
/**
 *
 * 购物车表实体类
 *
 */
public class Cart implements Serializable {

    private Integer cartId;

    //某用户
    private User user;

    //某商品
    private Item item;

    //数量
    private Integer count;

    private static final long serialVersionUID = 1L;


    public Integer getCart_id() {
        return cartId;
    }

    public void setCart_id(Integer cart_id) {
        this.cartId = cart_id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }


    @Override
    public String toString() {
        return "Cart{" +
                "cartId=" + cartId +
                ", user=" + user +
                ", item=" + item +
                ", count=" + count +
                '}';
    }

    public String TOJSONFrontManage() {//1.商品名+商品数量
        return  "{"+
                "\""+"CartId"+"\":"+"\""+cartId+"\","+
                "\""+"ItemId"+"\":"+"\""+item.getItemId()+"\","+
                "\""+"ItemName"+"\":"+"\""+item.getName()+"\","+
                "\""+"ItemPrice"+"\":"+"\""+item.getPrice()+"\","+
                "\""+"ItemCount"+"\":"+"\""+count+"\","+
                "\""+"ItemPic"+"\":"+"\""+item.getItem_pic().getPicPath()+"\""+
                "}";
    }
}