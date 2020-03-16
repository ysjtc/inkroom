package com.mx.mapper;

import com.mx.pojo.Order;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrderMapper {

    //生成订单
    int createOrder(Order order);

    //查询单个订单
    List<Order>  SeeOrder(@Param("number") String number);

    //判断是否有此订单
    int  SeeOrderCount(@Param("number") String number);

    //查询某个人的全部订单
    List<Order> SeeAllOrder(@Param("pageSize")int pageSize,@Param("offset") int offset,@Param("sort") String sort,@Param("sortOrder") String sortOrder,@Param("uid") Integer uid);

//    //由于itemName又多个，group_concat函数不生效
//    List<Order> queryAllOid(@Param("uid") Integer uid);
//    List<Order> queryItemNames(@Param("oId") Integer oId);

    //查询某个人的全部订单的数量
    int AllOrderCount(@Param("uid") Integer uid);

    //取消订单
    boolean deleteOrder(@Param("o_id") Integer o_id);



//    后台管理



    //查询全部订单
    List<Order> SeeAllOrders(@Param("pageSize")int pageSize,@Param("offset") int offset,@Param("sort") String sort,@Param("sortOrder") String sortOrder);

    //查询全部订单的数量
    int AllOrdersCount();

    //查询所有用户的全部订单中的状态
    List<Order>  QueryAllOrderStatus(@Param("pageSize")int pageSize,@Param("offset") int offset,@Param("sort") String sort,@Param("sortOrder") String sortOrder,@Param("oStatus") Integer oStatus);

    //查询某个人的全部订单
    List<Order> SeeAllOrderBackManage(@Param("pageSize")int pageSize,@Param("offset") int offset,@Param("sort") String sort,@Param("sortOrder") String sortOrder,@Param("uid") Integer uid);


    //查询所有用户的全部订单中的状态的数量
    int AllOrderStatusCount(@Param("oStatus") Integer oStatus);

    //更新订单的状态
    boolean updateOrderStatus(@Param("oId") Integer oId,@Param("oStatus") Integer oStatus);

    //查询订单详情表的商品数量
    int queryItemsCountByOrder(@Param("oId") Integer oId);

    //查询所有退单
    List<Order> CheckReturn(@Param("pageSize")int pageSize,@Param("offset") int offset,@Param("sort") String sort,@Param("sortOrder") String sortOrder);

    //查询退单个数
    int CheckReturnCount();

    //查询某个用户的全部退单
    List<Order> SeeAllOrderReturnBackManage(@Param("pageSize")int pageSize,@Param("offset") int offset,@Param("sort") String sort,@Param("sortOrder") String sortOrder,@Param("uid") int uid);

    //查询某个用户的全部退单的个数
    int AllOrderReturnCount(@Param("oId") int oId);

    //查询单个退单
    List<Order>  SeeOrderReturn(@Param("number") String number);

    //查询单个退单是否存在
    int SeeOrderReturnCount(String trade_number);
}