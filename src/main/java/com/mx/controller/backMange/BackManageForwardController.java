package com.mx.controller.backMange;

import com.mx.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/Forward")
public class BackManageForwardController {
    //此控制器与后台视图相关
    //此控制器用作转发后台部分的视图，到时候拦截器都要拦截Forward下的路径，做登录判断
    //这些都要登录判断的！！


    //自动注入category
    @Autowired
    private CategoryService categoryService;



    //查看商品信息
    @RequestMapping("/showItems")
    public String ShowItems(HttpSession session){
        //查询商品类型,跟随登陆启动
        session.setAttribute("Category",categoryService.queryAllCategory());
        return "backManage/storeManage/showItem";
    }
    //添加商品
    @RequestMapping("/addItems")
    public String addItems(HttpSession session){
        //查询商品类型,跟随登陆启动
        session.setAttribute("Category",categoryService.queryAllCategory());
        return "backManage/storeManage/addItem";
    }
    //进入后台查看所有用户页面
    @RequestMapping("/showUser")
    public String showUser(){
        return "backManage/userManage/showUser";
    }

    //订单管理页面
    @RequestMapping("/showOrder")
    public String showOrder(){
        return "backManage/orderManage/showOrder";
    }

    //退单页面
    @RequestMapping("/confirmReturnOrder")
    public String confirmReturnOrder(){
        return "backManage/orderManage/confirmReturn";
    }


}
