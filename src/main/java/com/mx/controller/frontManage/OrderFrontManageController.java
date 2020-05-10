package com.mx.controller.frontManage;

import com.mx.pojo.*;
import com.mx.service.AddressService;
import com.mx.service.ItemsService;
import com.mx.service.OrderService;
import com.mx.service.UserService;
import com.mx.utils.ConvertJson.JsonToJsonObject;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@RequestMapping("/FrontManageOrder")
public class OrderFrontManageController {

    @Autowired
    private UserService userService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private AddressService addressService;

    @Autowired
    private  ItemsService itemsService;


    /**
     *      -------接受的参数
     *      1.商品id  2.商品数量
     *      5.收货地址的id  6.订单名称 7.备注
     *订单状态（0表示未处理,1未支付,2待收货中，4退货中，3收货完成，5退货完成，6审核中，7审核通过，8审核失败）
     *
     1--订单名称（当前登陆id+time）<‘name’是用户登陆用的id，还有一个‘uname’，他是用户的昵称，还有一个uid，是用于用户表自增的id>
     2--商品id
     3--商品数量
     4--获取session中的userid（name）
     5--备注
     */

    //1生成订单
    @ResponseBody
    @RequestMapping("/createOrder")
    public String createOrder(@RequestParam(value = "item",required = false) String items, String oName, String note, Integer address_id, HttpSession session){
//        System.out.println("itemid:"+item_id+"count:"+count+"address_id:"+address_id+"oName="+oName+"-----note="+note+"uid:"+session.getAttribute("USER_ID"));
        String truejson="{\"result\":true}";
        String falsejson="{\"result\":false}";
        JSONArray ItemArray=JSONArray.fromObject(items);
        //当前登陆的用户id
        String uname = (String) session.getAttribute("USER_ID");
        if (uname == null || uname.equals("")) {
            return "{\"result\":false,\"isLogin\":false}";
        } else {
            boolean createOrder = false;
            boolean createOrderDet = false;
            int oId=0;
            Float totalPrice=0f;
            //遍历item【】
            for (int i = 0; i <ItemArray.size(); i++) {
                //解析前台发送的item数组
                Integer item_id = Integer.parseInt(String.valueOf(JsonToJsonObject.ToJsonObject(ItemArray.get(i).toString(), "item_id")));
                Integer count = Integer.parseInt(String.valueOf(JsonToJsonObject.ToJsonObject(ItemArray.get(i).toString(), "count")));
                System.out.println("item:"+ItemArray.get(i).toString());
                System.out.println("itemID:::"+item_id+"count:::"+count);
                if (count != null && count > 0 && oName != null && !oName.equals("") && address_id != null && address_id > 0) {
                    int u_id = userService.getUserIdByname(uname);
                    String create_time="";
                    //订单编号(自动生成)
                    StringBuilder trade_number = new StringBuilder();
                    if (i==0){
                        //订单创建时间
                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                         create_time = dateFormat.format(new Date());
                        System.out.println("订单创建时间：" + create_time);
                        trade_number.append("mx" + new Date().getTime());
                        //            System.out.println("订单创建时间:::::::"+trade_number);  //mx1566050992765//mx1566051018661
                    }
                    //实例化一个Order对象和一个Order_detail对象
                    Order order = new Order();
                    Order_Detail order_detail = new Order_Detail();
                    //实例化一个User对象
                    User user = new User();
                    user.setuId(u_id);
                    //实例化一个Address对象
                    //当前登陆的用户的地址表id
                    Address address = new Address();
                    address.setAddId(address_id);

                    order.setuId(user);
                    order.setoName(oName);
                    order.setAddress(address);
                    order.setCreateTime(create_time);
                    order.setNumber(String.valueOf(trade_number));
                    order.setoStatus("0");
                    order.setNote(note);

                    try {
                        //数据插入订单表只插入一次
                        if (i==0){
                            createOrder = orderService.createOrder(order);
                             oId=order.getoId();
                        }
                        order_detail.setoId(oId);
                        System.out.println("订单表最后一次插入的oid是"+oId);
                        order_detail.setItemCount(count);
                        order_detail.setItemId(item_id);
                        Float price = itemsService.queryItemsPriceByItemId(item_id);
                        //                System.out.println("price:"+price);
                         totalPrice = totalPrice+count * price;
                        //                System.out.println("totalPrice:"+totalPrice);
                        order_detail.setTotalPrice(totalPrice);
                        //数据插入订单详情表插入i次
                        createOrderDet = orderService.createOrderDet(order_detail);
                        System.out.println("huaq=====================");
                    } catch (Exception e) {
                        e.printStackTrace();
                        return falsejson;
                    }
                }else {
                    System.out.println("非法的传入数据");
                    return falsejson;
                }
            }
            //判断创建订单是否成功
            if (createOrder && createOrderDet) {
                return truejson;
            } else {
                return falsejson;
            }
        }
    }


    //查看单个订单
    @ResponseBody
    @RequestMapping("/seeOrder")
    public String seeOrder(String trade_number, HttpSession session, HttpServletResponse response){
        System.out.println(trade_number);
        String uname=(String)session.getAttribute("USER_ID");
        if (uname==null||uname.equals("")){
            return "{\"result\":false,\"isLogin\":false}";
        }else {
            try{
                String orderList=orderService.SeeOrder(trade_number);
                return orderList;
            }catch (Exception e){
                e.printStackTrace();
                return "{\"result\":false}";
            }
        }
    }


    //查看某个用户的全部订单
    @ResponseBody
    @RequestMapping("/seeAllOrder")
    public String seeAllOrder(@RequestBody String param, HttpSession session){
        System.out.println(param);
        //当前登陆的用户id
        String uname=(String)session.getAttribute("USER_ID");
        if (uname==null||uname.equals("")){
            return "{\"result\":false,\"isLogin\":false}";
        }else {
            try{
                int u_id=userService.getUserIdByname(uname);
                String pageSize=String.valueOf(JsonToJsonObject.ToJsonObject(param,"pageSize"));
                String offset=String.valueOf(JsonToJsonObject.ToJsonObject(param,"offset"));
                String sort=String.valueOf(JsonToJsonObject.ToJsonObject(param,"sort"));
                String sortOrder=String.valueOf(JsonToJsonObject.ToJsonObject(param,"sortOrder"));
                System.out.println(pageSize+offset+sort+sortOrder);
                String OrderList = orderService.SeeAllOrder(Integer.parseInt(pageSize),Integer.parseInt(offset),sort,sortOrder,u_id);
                return OrderList;
            }catch (Exception e){
                e.printStackTrace();
                //重定向到404
                return "{\"result\":false}";
            }

        }

    }


    //删除订单（取消订单）
    @ResponseBody
    @RequestMapping("/cancelOrder")
    public String cancelOrder(Integer oId, Model model,HttpSession session){
        String truejson="{\"result\":true}";
        String falsejson="{\"result\":false}";
        //当前登陆的用户id
        String uname=(String)session.getAttribute("USER_ID");
        if (uname==null||uname.equals("")){
            return "{\"result\":false,\"isLogin\":false}";
        }else {
            try{
            if (oId != null) {
                boolean is = orderService.deleteOrder(oId);
                if (is) {
                    model.addAttribute("deleteInfo", "删除成功!");
                    return truejson;
                } else {
                    model.addAttribute("deleteInfo", "删除失败!");
                    return falsejson;
                }
            } else return falsejson;
            }catch (Exception e){
                e.printStackTrace();
                return "redirect:/FrontForward/error";
            }
        }
    }


    //更新订单状态-----订单状态（0表示未处理,1未支付,2待收货中，4退货中，3收货完成，5退货完成，6审核中，7审核通过，8审核失败）
    @ResponseBody
    @RequestMapping("/updateOrderStatus")
    public String updateOrderStatus(Integer oId, String what,String reason,HttpSession session){
        String truejson="{\"result\":true}";
        String falsejson="{\"result\":false}";
        //当前登陆的用户id
        String uname=(String)session.getAttribute("USER_ID");
        if (uname==null||uname.equals("")){
            return "{\"result\":false,\"isLogin\":false}";
        }else {
            if (oId!=null&&what!=null&&!what.equals("")){
                boolean update=false;
                if (what.equals("refuse")){
                     update=orderService.updateFrontOrderStatus(oId,4);
                }else if(what.equals("accept")){
                     update=orderService.updateFrontOrderStatus(oId,3);
                }
                else if(what.equals("apply")){
                    System.out.println("what.equals(apply)::::::"+oId);
                    update=orderService.applyOrder(oId,6,reason);
                }else {
                    return falsejson;
                }
                if (update){
                    return truejson;
                }else return falsejson;
            }else return falsejson;
        }
    }


    //用户退换货后的填写物流操作
    @ResponseBody
    @RequestMapping("/applyReturn")
    public String applyReturn(Logistics_Return logistics_return,HttpSession session){
        String truejson="{\"result\":true}";
        String falsejson="{\"result\":false}";
        //当前登陆的用户id
        String uname=(String)session.getAttribute("USER_ID");
        if (uname==null||uname.equals("")){
            return "{\"result\":false,\"isLogin\":false}";
        }else {
            try {
                //获取物流表中要退货的id
                int lid=orderService.applogIdByOid(logistics_return.getoId());
                logistics_return.setlId(lid);
                System.out.println(logistics_return);
                boolean appReturn=orderService.appReturnOrder(logistics_return);
                if (appReturn){
                    return truejson;
                }else return falsejson;
            }catch (Exception e){
                e.printStackTrace();
                return falsejson;
            }
        }
    }


    //查询收货人
    @ResponseBody
    @RequestMapping(value = {"/seeAddressee"},produces = "application/json;charset=utf-8")
    public String queryAddressee(HttpSession session){
        String truejson="{\"result\":true}";
        String falsejson="{\"result\":false}";
        //当前登陆的用户id
        String uname=(String)session.getAttribute("USER_ID");
        if (uname==null||uname.equals("")){
            return "{\"result\":false,\"isLogin\":false}";
        }else {
            try{
                int uid=userService.getUserIdByname(uname);
                String uaddr=addressService.getAddress(uid);
                return uaddr;
            }catch (Exception e){
                e.printStackTrace();
                return falsejson;
            }
        }
    }

    //查询用户的退换货
    @ResponseBody
    @RequestMapping("/applyOrder")
    public String applyOrder(@RequestBody String param, HttpSession session){
        System.out.println(param);
        //当前登陆的用户id
        String uname=(String)session.getAttribute("USER_ID");
        if (uname==null||uname.equals("")){
            return "{\"result\":false,\"isLogin\":false}";
        }else {
            try{
                int uid=userService.getUserIdByname(uname);
                String pageSize=String.valueOf(JsonToJsonObject.ToJsonObject(param,"pageSize"));
                String offset=String.valueOf(JsonToJsonObject.ToJsonObject(param,"offset"));
                String sort=String.valueOf(JsonToJsonObject.ToJsonObject(param,"sort"));
                String sortOrder=String.valueOf(JsonToJsonObject.ToJsonObject(param,"sortOrder"));
                System.out.println(pageSize+offset+sort+sortOrder);
                String applyOrder=orderService.SeeAllOrderReurnBackManage(Integer.parseInt(pageSize),Integer.parseInt(offset),sort,sortOrder,uid);
                return applyOrder;
            }catch (Exception e){
                e.printStackTrace();
                //重定向到404
                return "{\"result\":false}";
            }

        }

    }

}
