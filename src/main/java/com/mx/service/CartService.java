package com.mx.service;

import com.mx.pojo.Cart;

import javax.servlet.http.HttpSession;

/**
 * 购物车
 */

public interface CartService {


    //加入购物车
    boolean addItemToCart(Cart cart);


    //查询购物车
    String queryCart(Integer uid);


    //修改商品数量
    boolean editCount(Integer cart_id, Integer count);


    //单个/批量删除购物车的商品
    boolean deleteCartItem(Integer cart_id);


    //查看同一个用户是否再次将同一个商品加入购物车
    boolean repeatToCart(int uid, Integer item_id, HttpSession session);

    //查询某个商品数量
    Integer queryitemCount(int uid,Integer itemId);
}
