package com.mx.service.Impl;

import com.mx.mapper.*;
import com.mx.pojo.*;
import com.mx.service.OrderService;
import com.mx.utils.ConvertJson.ConvertJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private Order_DetailMapper order_detailMapper;

    @Autowired
    private LogisticsMapper logisticsMapper;

    @Autowired
    private Apply_returnMapper apply_returnMapper;

    @Autowired
    private Logistics_ReturnMapper logistics_returnMapper;

    //生成订单号
    @Override
    public boolean createOrder(Order order) {
        System.out.println(order);
        //将订单添加到订单表
        int row=orderMapper.createOrder(order);
        if (row==0){ return false; }
        else return true;
    }

    @Override
    public boolean createOrderDet(Order_Detail order_detail) {
//        System.out.println("------------"+order_detail);
        //将订单添加到订单详情表
        int row=order_detailMapper.createOrderDet(order_detail);
        if (row==0){ return false; }
        else return true;
    }


    @Override
    public String SeeAllOrder(Integer pageSize, Integer offset, String sort, String sortOrder,Integer uid) {
//        System.out.println(uid);
        try{
            String str="";
            List<Order> orderlist=orderMapper.SeeAllOrder(pageSize,offset,sort,sortOrder,uid);
//            System.out.println("++++++++"+orderlist);
            //将查询结果转换成json数组
            int count=orderMapper.AllOrderCount(uid);
            if (count==0){
                return "\"result\":false";
            }else {
                str = ConvertJson.ConvertOrder(count,orderlist);
                System.out.println("----" + str);
                return str;
            }
        }catch (Exception e){
            e.printStackTrace();
            return "\"result\":false";
        }
    }

    @Override
    public boolean deleteOrder(Integer o_id) {
        boolean row= orderMapper.deleteOrder(o_id);
        if (!row){
            return false;
        }else return true;
    }

    @Override
    public boolean updateFrontOrderStatus(Integer oId, Integer oStatus) {
        try{
            boolean Orderstatus=orderMapper.updateOrderStatus(oId,oStatus);
            if (Orderstatus){ return true; }
            else return false;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean applyOrder(Integer oId, Integer status, String reason) {
        try{
            //实例化一个apply—return对象和order对象
            Apply_return app=new Apply_return();
            Order order=new Order();
            order.setoId(oId);
            app.setOrder(order);
            app.setApplyStatus(0);
            app.setReason(reason);

            //判断用户是否是第一次提交
            //判断oid是不是在表中
            Apply_return repeat= apply_returnMapper.repeatApply(oId);
            System.out.println("rep:"+repeat);
            if (repeat==null){
                //不存在
                int apply=apply_returnMapper.insertApply(app);
                boolean Orderstatus=orderMapper.updateOrderStatus(oId,status);
                if (apply==0&&!Orderstatus){ return false; }
                else return true;
            }else {
                //存在,拒绝重复申请
                return false;
            }
        }catch (Exception e){
            e.printStackTrace();
            //异常(查不到数据)
            return false;
        }
    }


    /**
     * 后台管理
     */

    @Override
    public String SeeAllOrderBackManage(Integer pageSize, Integer offset, String sort, String sortOrder, Integer uid) {
        System.out.println(uid);
        try{
            List<Order> orderlist=orderMapper.SeeAllOrderBackManage(pageSize,offset,sort,sortOrder,uid);
//        System.out.println("++++++++"+orderlist);
            //将查询结果转换成json数组
            int count=orderMapper.AllOrderCount(uid);
            if (count==0){
                return "\"result\":false";
            }else {
                String str = ConvertJson.ConvertOrderBackManage(count, orderlist,"queryOrder");
                System.out.println("----" + str);
                return str;
            }
        }catch (Exception e){
            e.printStackTrace();
            return "\"result\":false";
        }

    }


    @Override
    public String SeeAllOrders(Integer pageSize, Integer offset, String sort, String sortOrder) {

        try{
            List<Order> orderlist=orderMapper.SeeAllOrders(pageSize,offset,sort,sortOrder);
    //        System.out.println("++++++++"+orderlist);
            //将查询结果转换成json数组
            int count=orderMapper.AllOrdersCount();
            if (count==0){
                return "\"result\":false";
            }else {
    //            int num=orderMapper.queryItemsCountByOrder();
                String str= ConvertJson.ConvertOrderBackManage(count,orderlist,"queryOrder");
    //            System.out.println("----"+str);
                return str;
            }
        }catch (Exception e){
            e.printStackTrace();
            return  "\"result\":false";
        }

    }

    @Override
    public String QueryAllOrderStatus(Integer pageSize, Integer offset, String sort, String sortOrder, Integer[] oStatus) {
        List<Order> orderlist=null;
        int count=0;
        //定义一个StringBuilder
        StringBuilder jsonStrAll = new StringBuilder("{");
        String str="";
        for (int i=0;i<oStatus.length;i++)
            //将查询结果转换成json数组
        count=count+orderMapper.AllOrderStatusCount(oStatus[i]);
        jsonStrAll.append("\""+"total"+"\""+":"+count+","+"\""+
                "rows"+"\""+":[");
        for (int i=0;i<oStatus.length;i++) {
            System.out.println("ostatus:::"+oStatus[i]);
            orderlist= orderMapper.QueryAllOrderStatus(pageSize, offset, sort, sortOrder, oStatus[i]);
            for (int j=0;j<orderlist.size();j++){
                //把你要拼接的字段放进去
//                int num=orderMapper.queryItemsCountByOrder(orderlist.get(j).getoId());
                jsonStrAll.append(orderlist.get(j).TOJSONBackManage() +",");
            }
        }
        //把最后的，（逗号）截取掉
        str = jsonStrAll.substring(0, jsonStrAll.length()-1)+"]}";
        System.out.println("QueryAllOrderStatus:"+str);
        return str;
    }

    @Override
    public boolean updateOrderStatus(Logistics logistics, Integer oStatus) {
        try{
            int log=logisticsMapper.addLogistics(logistics);
            boolean Orderstatus=orderMapper.updateOrderStatus(logistics.getoId(),oStatus);
            if (log!=0&& Orderstatus==true){ return true; }
            else return false;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updateApplyStatus(Integer applyId, Integer status) {
        try{
            boolean Appstatus=apply_returnMapper.updateApplyStatus(applyId,status);
            Apply_return oId=apply_returnMapper.queryoIdByapplyId(applyId);
            boolean Orderstatus=false;
            if (status==1){
                 Orderstatus=orderMapper.updateOrderStatus(oId.getOrder().getoId(),8);
            }else  if (status==2){
                Orderstatus=orderMapper.updateOrderStatus(oId.getOrder().getoId(),7);
            }else {
                return false;
            }

            if (Appstatus&&Orderstatus){ return true; }
            else return false;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean appReturnOrder(Logistics_Return logistics_return) {
        try{
            //判断用户是否是第一次提交
            //判断oid是不是在表中
            Logistics_Return repeat= logistics_returnMapper.repeatApply(logistics_return.getoId());
            System.out.println("rep:"+repeat);
            //说明是第一次提交
            if (repeat==null){
                int appReturn=logistics_returnMapper.createLog(logistics_return);
                boolean Orderstatus=orderMapper.updateOrderStatus(logistics_return.getoId(),4);
                if (appReturn!=0 && Orderstatus==true){
                    return true;
                }
                else return false;
            }else return false;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public int applogIdByOid(Integer oId) {
        try{
            Logistics appReturn=logisticsMapper.applogIdByOid(oId);
            if (appReturn==null){ return 0; }
            else return appReturn.getlId();
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public String CheckReturn(int pageSize, int offset, String sort, String sortOrder) {
        try{
            List<Order> CheckReturn=orderMapper.CheckReturn(pageSize,offset,sort,sortOrder);
            System.out.println("++++++++"+CheckReturn);
            //将查询结果转换成json数组
            int count=orderMapper.CheckReturnCount();
            if (count==0){
                return "\"result\":false";
            }else {
                String str = ConvertJson.ConvertOrderBackManage(count, CheckReturn,"queryOrderReturn");
                System.out.println("----" + str);
                return str;
            }
        }catch (Exception e){
            e.printStackTrace();
            return "\"result\":false";
        }
    }

    @Override
    public Map checkLogistics(Integer oId) {
        Map logmaps=new HashMap();
        try{
            Logistics logisticslist=logisticsMapper.checkLogistics(oId);
            logmaps.put("result",true);
            logmaps.put("company",logisticslist.getCompany());
            logmaps.put("waybillNum",logisticslist.getWaybillNum());
            return logmaps;
        }catch (Exception e){
            e.printStackTrace();
            logmaps.put("result",false);
            return logmaps;
        }
    }

    @Override
    public String SeeAllOrderReurnBackManage(int pageSize, int offset, String sort, String sortOrder, int uid) {
        try{
            List<Order> orderlist=orderMapper.SeeAllOrderReturnBackManage(pageSize,offset,sort,sortOrder,uid);
           //某人的全部订单
            List<Order> SBorderlist=orderMapper.SeeAllOrderBackManage(pageSize,offset,sort,sortOrder,uid);
            //所有的退单
            List<Order> AllorderReturnlist=orderMapper.CheckReturn(pageSize,offset,sort,sortOrder);
            int count=0;
            for (int i=0;i<SBorderlist.size();i++) {
                for (int J = 0; J < AllorderReturnlist.size(); J++) {
                   if ( SBorderlist.get(i).getoId().equals(AllorderReturnlist.get(J).getoId())){
                       count++;
                   }
                }
            }
            if (count==0){
                return "\"result\":false";
            }else {
                //将查询结果转换成json数组
                String str = ConvertJson.ConvertOrderBackManage(count, orderlist,"queryOrderReturn");
                return str;
            }
        }catch (Exception e){
            e.printStackTrace();
            return "\"result\":false";
        }
    }

    @Override
    public String SeeOrderReturn(String trade_number) {
        try{
            List<Order> orderlist=orderMapper.SeeOrderReturn(trade_number);
            int SeeOrderCount=orderMapper.SeeOrderReturnCount(trade_number);
            if (SeeOrderCount==0){
                return "\"result\":false";
            }else {
                //将查询结果转换成json数组
                String str = ConvertJson.ConvertOrderBackManage(1, orderlist,"queryOrderReturn");
                return str;
            }
        }catch (Exception e){
            e.printStackTrace();
            return "\"result\":false";
        }
    }

    @Override
    public String SeeOrder(String trade_number) {
        try{
            List<Order> orderlist=orderMapper.SeeOrder(trade_number);
            int SeeOrderCount=orderMapper.SeeOrderCount(trade_number);
            if (SeeOrderCount==0){
                return "\"result\":false";
            }else {
                //将查询结果转换成json数组
                String str = ConvertJson.ConvertOrderBackManage(1, orderlist,"queryOrder");
                return str;
            }
        }catch (Exception e){
            e.printStackTrace();
            return "\"result\":false";
        }

    }
}
