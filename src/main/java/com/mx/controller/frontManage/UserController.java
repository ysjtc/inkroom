package com.mx.controller.frontManage;

import com.mx.pojo.Page;
import com.mx.pojo.User;
import com.mx.pojo.UserData;
import com.mx.pojo.User_Pic;
import com.mx.service.UserPicService;
import com.mx.service.UserService;
import com.mx.service.VipService;
import com.mx.utils.Anno.PreventRepeat;
import com.mx.utils.Encoding.htmlEncoding;
import com.mx.utils.Encoding.userCheck;
import com.mx.utils.RandomUser.RandomUser;
import com.mx.utils.UpLoad.UserUpload;
import com.mx.utils.Validators.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;


@Controller
@RequestMapping(value="/User")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserPicService userPicService;

    @Autowired
    private VipService vipService;


    /*用户管理下的个人信息模块*/
    /*用户登录的handle*/
    @PreventRepeat
    @RequestMapping(value="/login")
    public String login(
              User user,
            Model model,
            HttpSession session){
            /*判断用户名是否合法*/
            if(user.getName()==null||
                    user.getName().equals("")||
                    user.getName().length()>12||
                    user.getName().length()<6||
                    user.getPassword()==null||
                    user.getPassword().equals("")||
                    user.getPassword().length()>18||
                    user.getPassword().length()<6){
                return "frontShow/errorPage/error";
            }
            /*若用户账号与密码都正确，则从数据库拿到用户记录recorduser*/
            User recorduser = userService.login(user);
            /*当用户账号与密码都正确时进入登录成功页面*/
            if (recorduser != null) {
               User_Pic userPic=userPicService.queryById(recorduser.getuId());
                UserData userData=userService.queryUserByname(recorduser.getName());
                userData=htmlEncoding.getUserData(userData);
                session.setAttribute("userData", userData);
                session.setAttribute("userPic", userPic);
                session.setAttribute("USER_ID", recorduser.getName());
                session.setAttribute("uId",recorduser.getuId());
                //判断beforePath是否有请求的URL，有的话取出来跳转
                String beforePath=(String) session.getAttribute("beforePath");
                if(beforePath!=null) {
                    return "redirect:"+beforePath;

                }else {
                    return "frontShow/personal/personalMain";
                }
            } else {
                model.addAttribute("status","密码或账号输入错误");
                return "frontShow/personal/login";
            }
    }

    /*用户注册的handle*/
    @RequestMapping(value="/register",method = RequestMethod.POST)
    @PreventRepeat
    public String register(
             @Valid User user,
            BindingResult result,
            Model model,
            HttpSession session
            ){
        if (UserValidator.checkError(result)) {
            model.addAttribute("info","请勿非法测试！");
            return "frontShow/personal/login";
        }else{
            try {
                System.out.println("success________________++++++++++++++++++++++++");
                /*随机生成用户名*/
                //int num=userService.getAlluserNum();
                int num=userService.queryMaxName();
                user.setName(RandomUser.RandomName(num));
                //System.out.println(num);
                /*入库前的验证*/
                if(!userCheck.CheckregisterUser(user)) {
                    /*开始创建用户*/
                    userService.addUser(user);
                }else{
                    model.addAttribute("info","注册数据格式错误！");
                    return "frontShow/personal/login";
                }

                /*添加用户头像信息*/
                User_Pic userPic=new User_Pic();
                userPic.setuId(userService.getUserIdByname(user.getName()));
                userPic.setUserPath("static/images/personal_img.png");
                userPicService.addUserPic(userPic);

                UserData userData=userService.queryUserByname(user.getName());
                if(userData.getTel()==null||userData.getTel().equals("")) {
                    userData.setTel("none");
                }
                /*写入session*/
                userData=htmlEncoding.getUserData(userData);
                session.setAttribute("userData", userData);
                session.setAttribute("USER_ID",user.getName());
                session.setAttribute("userPic",userPic);
            }catch(Exception e){
                e.printStackTrace();
                return "frontShow/personal/login";
            }
            return "frontShow/personal/personalMain";
        }
    }

    /*用户登出的handle*/
    @RequestMapping("/loginout")
    @PreventRepeat
    public String loginout(HttpSession session){
        session.removeAttribute("USER_ID");
        session.invalidate();
        return "frontShow/navigation/nav";
    }

    /*删除用户的handle*/
    @ResponseBody
    @RequestMapping("/deleteUser")
    @PreventRepeat
    public String deleteuser(String name){
        System.out.println("fuck+++++++++++++");
        if(userService.deleteUser(name)){
            return "{\"result\":true}";
        }else {
            return "{\"result\":false}";
        }
    }

    /*修改用户信息的handle*/
    @RequestMapping("/updateUser")
    @ResponseBody
    public Object updateuser(
            User user,
        HttpServletRequest request,
        HttpSession session,
        @RequestParam(value = "userPic",required = false) MultipartFile file
        )throws Exception{
            Map map=new HashMap();
            user.setName((String)session.getAttribute("USER_ID"));
        //System.out.println(user);
        /*入库前的验证*/
            if(!userCheck.CheckupdateUser(user,file)){
                //System.out.println("============");
                map.put("result", false);
                return map;
            }
            /*用户信息更新*/
                if (userService.updateUser(user)) {
                    if(file!=null){
                        User_Pic userPic = UserUpload.imgUpload(file, request, user, userService, userPicService);
                        userPicService.updateUserPic(userPic);
                        session.setAttribute("userPic",userPic);
                    }
                    UserData userData=userService.queryUserByname(user.getName());
                    session.setAttribute("userData",userData);
                    map.put("result", true);
                    return map;
                } else {
                    map.put("result", false);
                    return map;
                }
    }

    /*查找所有用户的handle*/
    @ResponseBody
    @RequestMapping("/getAlluser")
    public Object getAlluser(
            @RequestBody Page page
    ){
        Map users=userService.queryAllUser(page);
        return users;
    }

    /*查找特定用户的handle*/
    @ResponseBody
    @RequestMapping("/queryUser")
    @PreventRepeat
    public Object queryUser(String name){
        UserData user=userService.queryUserByname(name);
        return user;
    }

}

