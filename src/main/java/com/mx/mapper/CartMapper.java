package com.mx.mapper;

import com.mx.pojo.Cart;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CartMapper {

    /**
     * 前台部分
     */
    //@Param("uid") Integer uid,@Param("item_id") Integer item_id,@Param("count") Integer count
    //@Param("pageSize")int pageSize,@Param("offset") int offset,@Param("sort") String sort,@Param("sortOrder") String sortOrder
    //加入购物车
    int addCart(Cart cart);

    //查看购物车
    List<Cart> SeeCart( @Param("uid") Integer uid);

    //查看某用户的购物车的总商品数
    int SeeCartCount(@Param("uid") Integer uid);

    //修改商品数量
    boolean editCount(@Param("cartId") Integer cartId,@Param("count") Integer count);

    //单个/批量删除购物车的商品
    int delCartItem(@Param("cartId")Integer cartId);

    //由于数据库中uid和itemid没有作为联合主键，所以这里进行判断是否存在同一个用户对同一个商品重复下单
    Cart repeatToCart(@Param("uid") Integer uid,@Param("itemId") Integer itemId);

    //查询某个商品的数量
    Cart queryitemCount(@Param("uid") Integer uid,@Param("itemId") Integer itemId);
}