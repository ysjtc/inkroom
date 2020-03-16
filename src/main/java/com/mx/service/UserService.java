package com.mx.service;

import com.mx.pojo.Page;
import com.mx.pojo.User;
import com.mx.pojo.UserData;

import java.util.Map;

public interface UserService {
    /*用户登陆*/
    public User login(User user);

    /*添加用户*/
    public boolean addUser(User user);

    /*删除用户*/
    public boolean deleteUser(String name);

    /*修改用户*/
    public boolean updateUser(User user);

    /*查找特定用户*/
    public UserData queryUserByname(String name);

    /*得到用户id*/
    public int getUserIdByname(String name);

    /*查找所有用户*/
    public Map queryAllUser(Page page);

    /*修改密码*/
    public boolean updateUserPasswd(User user);

    /*得到所有用户数量*/
    public int getAlluserNum();

    //获取用户名最大值
    public int queryMaxName();

}
