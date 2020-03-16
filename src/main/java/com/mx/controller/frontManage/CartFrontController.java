package com.mx.controller.frontManage;

import com.mx.pojo.Cart;
import com.mx.pojo.User;
import com.mx.service.CartService;
import com.mx.service.CategoryService;
import com.mx.service.ItemsService;
import com.mx.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/CartFrontManage")
public class CartFrontController {

    //自动注入items
    @Autowired
    private ItemsService itemsService;

    //自动注入User
    @Autowired
    private UserService userService;

    //自动注入Category
    @Autowired
    private CategoryService categoryService;

    @Autowired
    private CartService cartService;



    //加入购物车
    @ResponseBody
    @RequestMapping("/addItemToCart")
    public String addItemToCart(Cart cart,HttpSession session){
        String truejson="{\"result\":true}";
        String falsejson="{\"result\":false}";
        //1.获取uid
        String name=(String)session.getAttribute("USER_ID");
        if (name==null||name.equals("")){
            return "{\"result\":false,\"isLogin\":false}";
        }else {
            if (cart.getCount()!=null&&cart.getItem().getItemId()!=null){
//            if (cart.getCount()!=null&&item_id!=null){
                try{
                    int uid=userService.getUserIdByname(name);
                    if (uid==0){
                        return falsejson;
                    }
                    //实例化一个user
                    User user=new User();
                    user.setuId(uid);
//                    //实例化一个item
//                    Item item=new Item();
//                    item.setItemId(item_id);

                    cart.setUser(user);
//                    cart.setItem(item);

//                    System.out.println("cart:"+cart);
                    //由于数据库中uid和itemid没有作为联合主键，所以这里进行判断是否存在同一个用户对同一个商品重复下单
                    boolean repeat=cartService.repeatToCart(uid,cart.getItem().getItemId(),session);
//                    System.out.println("repeat为："+repeat);
                    if (repeat){
                        boolean addItemToCart=cartService.addItemToCart(cart);
                        if (addItemToCart){
                            return truejson;
                        }else {
                            return falsejson;
                        }
                    }else {
                        //如果重复下单，即将商品数量加count
                        //1-获取商品id
                        int cartId=Integer.parseInt(String.valueOf(session.getAttribute("RepeatCartId")));
                        //2-获取商品数量(前台发送的+后台查询的)
                        int count=cart.getCount()+cartService.queryitemCount(uid,cart.getItem().getItemId());
//                        System.out.println("cart.getCount()"+cart.getCount()+"count:::::"+count);
                        //判断数量是否加入成功
                        boolean edit=cartService.editCount(cartId,count);
                        session.removeAttribute("RepeatCartId");
                        if (edit){
                            return truejson;
                        }else {
                            return falsejson;
                        }
                    }
                }catch (Exception e){
                    e.printStackTrace();
                    //重定向到404
                    return falsejson;
                }
            }else {
                return falsejson;
            }
        }
    }

    //查看购物车
    @ResponseBody
    @RequestMapping("/SeeCart")
    public String SeeCart(HttpSession session) {
//        System.out.println("---------"+item_id);
        String truejson = "{\"result\":true}";
        String falsejson = "{\"result\":false}";
        //1.获取uid
        String name = (String) session.getAttribute("USER_ID");
        if (name == null || name.equals("")) {
            return "{\"result\":false,\"isLogin\":false}";
        } else {
            try {
                int uid=userService.getUserIdByname(name);
                if (uid==0){
                    return falsejson;
                }
                String cart=cartService.queryCart(uid);
                if (cart==null){
                    return falsejson;
                }else {
                    return cart;
                }
            } catch (Exception e) {
                e.printStackTrace();
                //重定向到404
                return "{\"result\":false}";
            }
        }
    }

    //修改商品数量
    @ResponseBody
    @RequestMapping("/UpdateItemCount")
    public String UpdateItemCount(Integer cartId,Integer count,HttpSession session) {
//        System.out.println("---------"+item_id);
        String truejson = "{\"result\":true}";
        String falsejson = "{\"result\":false}";
        //1.获取uid
        String name = (String) session.getAttribute("USER_ID");
        if (name == null || name.equals("")) {
            return "{\"result\":false,\"isLogin\":false}";
        } else {
            try {
                int uid=userService.getUserIdByname(name);
                if (uid==0){
                    return falsejson;
                }
                boolean edit=cartService.editCount(cartId,count);
                if (edit){
                    return truejson;
                }else return falsejson;
            } catch (Exception e) {
                e.printStackTrace();
                //重定向到404
                return falsejson;
            }
        }
    }

    //批量删除
    @ResponseBody
    @RequestMapping("/deleteCartItem")
    public String deleteCartItem(Integer[] cartId,HttpSession session) {
        System.out.println(cartId);
        for (int i=0;i<cartId.length;i++){
            System.out.println(cartId[i]);
        }
        String truejson = "{\"result\":true}";
        String falsejson = "{\"result\":false}";
        System.out.println(cartId.length);
        //1.获取uid
        String name = (String) session.getAttribute("USER_ID");
        if (name == null || name.equals("")) {
            return "{\"result\":false,\"isLogin\":false}";
        } else {
            try {
                int uid=userService.getUserIdByname(name);
                if (uid==0){
                    return falsejson;
                }
                boolean delItem=false;
                for (int i=0;i<cartId.length;i++){
                    delItem=cartService.deleteCartItem(cartId[i]);
                }
                if (delItem){
                    return truejson;
                }else {
                    return falsejson;
                }
            } catch (Exception e) {
                e.printStackTrace();
                //重定向到404
                return falsejson;
            }
        }
    }
}
