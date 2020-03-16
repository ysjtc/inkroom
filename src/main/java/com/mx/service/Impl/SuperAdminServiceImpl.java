package com.mx.service.Impl;

import com.mx.mapper.SuperAdminMapper;
import com.mx.pojo.SuperAdmin;
import com.mx.service.SuperAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SuperAdminServiceImpl implements SuperAdminService {
    @Autowired
    private SuperAdminMapper superAdminMapper;

    @Override
    public int querySuperAdminCount() {
        int sam=superAdminMapper.queryAdminCount();
        return sam;
    }

    @Override
    public void addSuperAdmin(SuperAdmin superAdmin) {

            superAdminMapper.addSuperAdmin(superAdmin);

    }

    @Override
    public SuperAdmin login(SuperAdmin superAdmin) {
//        System.out.println("superAdminName:" + superAdmin.getName());
//        System.out.println("superAdminPwd:" + superAdmin.getPassword());
        if (superAdmin.getName()!= null && superAdmin.getPassword() != null&&!"".equals(superAdmin.getPassword())&&!"".equals(superAdmin.getName())) {
           try{
               List<SuperAdmin> superAdminlist = superAdminMapper.queryAdminByName(superAdmin);
//            System.out.println("-------------superadminList:" + superAdminlist);
               //查询超级管理员不等于null  list集合大小为1
               if (superAdminlist != null && superAdminlist.size() == 1) {
//            System.out.println(superAdminlist.get(0).getPassword().trim().equals(superAdmin.getPassword().trim())?"相等":"不对");
                   if (superAdminlist.get(0).getPassword().equals(superAdmin.getPassword())) {
                       return superAdminlist.get(0);
                   }else {
                       System.out.println("用户名或密码错误");
                       return null;
                   }
               }else {
                   System.out.println("用户名不存在");
                   return null;
               }
           }catch (Exception e){
               e.printStackTrace();
               System.out.println("用户名或密码错误");
               return null;
           }
        } else {
            System.out.println("用户名或密码为空");
            return null;
        }

    }

    @Override
    public SuperAdmin queryAdminById(String id) {
        return superAdminMapper.queryAdminById(id);
    }

}
