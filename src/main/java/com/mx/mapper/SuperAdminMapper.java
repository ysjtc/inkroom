package com.mx.mapper;

import com.mx.pojo.SuperAdmin;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SuperAdminMapper {

    /**
     * 添加superadmin
     * @param superAdmin
     * @return
     */
    int addSuperAdmin(SuperAdmin superAdmin);

    /**
     * 通过id查询超级管理员
     */
    SuperAdmin queryAdminById(@Param("super_id") String super_id);

    /**
     * 通过姓名查询超级管理员
     */
    List<SuperAdmin> queryAdminByName(SuperAdmin superAdmin);

    /**
     *查询管理员人数count
     */
    int queryAdminCount ();
}