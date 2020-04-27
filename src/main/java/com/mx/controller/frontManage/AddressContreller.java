package com.mx.controller.frontManage;/*
@author 郭子雄
@description 
*/

import com.mx.pojo.Address;
import com.mx.pojo.Page;
import com.mx.service.AddressService;
import com.mx.service.UserService;
import com.mx.utils.Encoding.userCheck;
import com.mx.utils.Validators.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping(value="/Address")
public class AddressContreller {

    @Autowired
    private UserService userService;

    @Autowired
    private AddressService addressService;


    /*用户管理下的收货人模块*/
    @ResponseBody
    @RequestMapping (value="/addAddr")
    public String addAddress(
            @Valid Address address,
            BindingResult result,
            Model model,
            HttpSession session
    ){
        try{
            if (UserValidator.checkError(result)) {
                model.addAttribute("info","请勿非法测试！");
                return "{\"result\":false}";
            }else{
                int uId=userService.getUserIdByname((String)session.getAttribute("USER_ID"));
                address.setuId(uId);
                /*入库前的检验*/
                if(userCheck.CheckAddaddr(address)){
                    model.addAttribute("info","数据输入格式错误或未输入数据！");
                    return "{\"result\":false}";
                }else {
                    /*入库*/
                    addressService.Addaddress(address);
                }
                return "{\"result\":true}";
        }}catch (Exception e){
            e.printStackTrace();
            return "{\"result\":false}";
        }
    }

    @ResponseBody
    @RequestMapping (value="/getAlladdress")
    public Object getAlladdress(
            @RequestBody Page page,
            HttpSession session
    ){
        try {
            int uId = userService.getUserIdByname((String) session.getAttribute("USER_ID"));
            Map map = addressService.getAlladdress(uId, page);
            return map;
        }catch(Exception e){
            e.printStackTrace();
            Map map=new HashMap();
            map.put("result",false);
            return map;
        }
    }

    @ResponseBody
    @RequestMapping(value="/updateAddress")
    public Object updateAddress(
            @Valid  Address address,
            BindingResult result,
            HttpSession session
    ){
        try{
             Map map=new HashMap();
            if (UserValidator.checkError(result)) {
                map.put("result",false);
                return map;
            }else {
                int uId = userService.getUserIdByname((String) session.getAttribute("USER_ID"));
                address.setuId(uId);
                /*入库前的检验*/
                if (userCheck.CheckAddaddr(address)) {
                    map.put("result",false);
                    return map;
                } else {
                    /*入库*/
                    addressService.updateaddress(address);
                }
            }
            map.put("result",map);
            return map;
        }catch (Exception e){
            e.printStackTrace();
            Map map=new HashMap();
            map.put("result",false);
            return map;
        }

    }
    @ResponseBody
    @RequestMapping(value="clearAddress")
    public String ClaerAddress(Integer addId){
        try{
            /*判断addId是否合法*/
            if(addId>0){
                addressService.Clearaddress(addId);
                return "{\"result\":true}";
            }else{
                return "{\"result\":false}";
            }

        }catch (Exception e){
            e.printStackTrace();
            return "{\"result\":false}";
        }
    }

}
