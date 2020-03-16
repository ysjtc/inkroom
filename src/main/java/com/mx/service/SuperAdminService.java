package com.mx.service;

import com.mx.pojo.SuperAdmin;

/**
 *
 * 超级管理员
 */
public interface SuperAdminService {

    //查询记录数
    int querySuperAdminCount();

    //添加superAdmin
    void addSuperAdmin(SuperAdmin superAdmin);

    //超级管理员登陆
    SuperAdmin login(SuperAdmin superAdmin);

    
    //通过id查询
    SuperAdmin queryAdminById(String id);
}
