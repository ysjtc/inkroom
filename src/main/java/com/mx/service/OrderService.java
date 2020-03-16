package com.mx.service;

import com.mx.pojo.Logistics;
import com.mx.pojo.Logistics_Return;
import com.mx.pojo.Order;
import com.mx.pojo.Order_Detail;

import java.util.Map;

public interface OrderService {

    //生成订单
    boolean createOrder(Order order);
    boolean createOrderDet(Order_Detail order_detail);

    //查询单个订单
    String  SeeOrder(String trade_number);

    //查看某个用户的全部订单(前台)
    String SeeAllOrder(Integer pageSize, Integer offset, String sort, String sortOrder,Integer uid);

    //取消订单
    boolean deleteOrder(Integer o_id);

    //更新订单状态
    boolean updateFrontOrderStatus(Integer oId, Integer status);

    //退换货申请
    boolean applyOrder(Integer oId, Integer status,String reason);

    /*
    后台
     */


    //查看某个用户的全部订单(后台)
    String SeeAllOrderBackManage(Integer pageSize, Integer offset, String sort, String sortOrder,Integer uid);

    //查询所有订单(后台)
    String SeeAllOrders(Integer pageSize, Integer offset, String sort, String sortOrder);

    //查询所有订单中的状态
    String QueryAllOrderStatus(Integer pageSize, Integer offset, String sort, String sortOrder,Integer[] status);

    //更新订单状态
    boolean updateOrderStatus(Logistics logistics, Integer status);

    //退换货申请
    boolean updateApplyStatus(Integer applyId, Integer status);

    //退换货物流信息
    boolean appReturnOrder(Logistics_Return logistics_return);

    //获取物流表中要退货的id
    int applogIdByOid(Integer oId);

    //查询所有退单(后台)
    String CheckReturn(int pageSize, int offset, String sort, String sortOrder);

    //查看发货（退货）物流
    Map checkLogistics(Integer oId);

    //查看某个用户的全部退单(后台)
    String SeeAllOrderReurnBackManage(int parseInt, int parseInt1, String sort, String sortOrder, int uid);

    //查询单个退单
    String SeeOrderReturn(String trade_number);
}
