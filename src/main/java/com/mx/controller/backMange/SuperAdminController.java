package com.mx.controller.backMange;


import com.mx.pojo.SuperAdmin;
import com.mx.service.SuperAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/SuperAdmin")
public class SuperAdminController {

    //自动注入superadmin
    @Autowired
    private SuperAdminService superAdminService;


//    //自动注入Item
//    @Autowired
//    private ItemsService itemsService;


    //初始化superadmin
    @RequestMapping("/addSuperadmin")
    public String addSuperAdmin(SuperAdmin superAdmin,Model model){
        //获取superadmin表中的总数据记录数，大于1条时拒绝插入
        int row=superAdminService.querySuperAdminCount();
        if(row>0){
            model.addAttribute("ERROR","INSERT_ERROR");
            return "redirect:/WEB-INF/jsp/error.jsp";
        }else{
            superAdminService.addSuperAdmin(superAdmin);
            return "backManage/login/login";
        }
    }

    //superadmin登陆
    @RequestMapping("/login")
    public String login(SuperAdmin superAdmin, Model model, HttpSession session){

            if (superAdmin != null) {
                 superAdmin = superAdminService.login(superAdmin);
                 if (superAdmin==null){
                     return "redirect:/SuperAdmin/ToLogin";
                 }else {
                     //登录成功  跳向成功页面
                     session.setAttribute("SUPERADMIN_SESSION", superAdmin);
                     session.setAttribute("SUPERADMIN_ID", superAdmin.getSuperId());
                     //存储管理员开发者信息
                     model.addAttribute("SuperAdmin", "超级管理员");
                     return "redirect:/SuperAdmin/ToLogin";
                 }
            } else {
                //存储错误信息
                model.addAttribute("loginErr", "请先登录");
                //登录失败  跳向登陆页面
                return "redirect:/SuperAdmin/ToLogin";
            }

    }

    @RequestMapping("/ToLogin")
    public String llo(HttpSession session,Model model){
//        System.out.println("SUPERADMIN="+session.getAttribute("SUPERADMIN_SESSION"));
//        System.out.println("SUPERADMIN_ID="+session.getAttribute("SUPERADMIN_ID"));
        //先判断是否有管理员登陆
        Object o=session.getAttribute("SUPERADMIN_ID");
        if (o==null||o.equals("")){
            System.out.println("当前没有管理员登陆");
            //存储错误信息
            model.addAttribute("loginErr", "请先登录");
            return "backManage/login/login";
        }else
        return "backManage/navigation/nav";
    }

    //superadmin退出
    @RequestMapping("/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("SUPERADMIN_ID");
        //session.removeAttribute("SUPERADMIN_SESSION");
        return "redirect:/SuperAdmin/ToLogin";
    }


}
