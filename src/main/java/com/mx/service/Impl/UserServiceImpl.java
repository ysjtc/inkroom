package com.mx.service.Impl;

import com.mx.mapper.UserMapper;
import com.mx.mapper.User_PicMapper;
import com.mx.mapper.VipMapper;
import com.mx.pojo.Page;
import com.mx.pojo.User;
import com.mx.pojo.UserData;
import com.mx.pojo.Vip;
import com.mx.service.UserService;
import com.mx.utils.MD5.MD5;
import org.apache.commons.lang.StringEscapeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper usermapper;

    @Autowired
    private VipMapper vipMapper;

    @Autowired
    private User_PicMapper userPicMapper;

    @Override
    /*登陆*/
    public User login(User u) {

        /*获取用户记录*/
        /*获取用户记录前，先将密码加密后在与数据库中的密码比对*/
        MD5 md5 = new MD5();
        String passwd = md5.MD5Encode(u.getPassword());
        u.setPassword(passwd);
        User user = usermapper.queryUserByname(u.getName());
        if(user==null)return null;
        if (user.getPassword().equals(u.getPassword())) {
            return user;
        } else {
            return null;
        }

    }
    /*注册*/
    @Override
    public boolean addUser(User user) {
        //System.out.println("addUser执行"+user+"=====================================");
        /*判断账号是否已存在，不存在添加成功，存在则添加失败*/
        if(usermapper.queryUserByname(user.getName())==null){
            /*密码加密处理*/
            MD5 md5 = new MD5();
            String passwd=md5.MD5Encode(user.getPassword());
            user.setPassword(passwd);
            /*添加数据*/
            usermapper.insertSelective(user);

            /*级联生成vip*/
            Vip vip=new Vip();
            vip.setVipId(usermapper.queryUserByname(user.getName()).getuId());
            vip.setScore(0);
            vipMapper.insert(vip);
            return true;
        }else{
            return false;
        }
    }

    /*删除用户*/
    @Override
    public boolean deleteUser(String name) {
        User user=usermapper.queryUserByname(name);
        /*查看用户是否存在，若不存在则删除失败，存在则成功*/
        if(user!=null){
            usermapper.deleteUserByname(name);
            return true;
        }else{
            return false;
        }
    }

    /*修改用户*/
    @Override
    public boolean updateUser(User user) {
        User u=usermapper.queryUserByname(user.getName());
        /*查看用户是否存在，若不存在则修改失败，存在则成功*/
        if(u!=null){
        usermapper.updateUserByname(user);
            return true;
        }else{
            return false;
        }
    }


    /*修改密码*/
    public boolean updateUserPasswd(User user){
        /*修改密码时需对密码进行加密处理*/
        if(user.getPassword()!=null){
            MD5 md5 = new MD5();
            String passwd=md5.MD5Encode(user.getPassword());
            user.setPassword(passwd);
        }
        return true;
    }

    @Override
    public int getAlluserNum() {
        return usermapper.getAlluserNum(null,null);
    }

    /*依据账号查找用户*/
    @Override
    public UserData queryUserByname(String name) {
        UserData userData=new UserData();
        User user=usermapper.queryUserByname(name);
        if(user==null)return null;
        userData.setName(user.getName());/*账号*/
        userData.setPassword(null);/*密码*/
        userData.setEmail( user.getEmail());/*邮箱*/
        userData.setuName( user.getuName());/*用户名*/
        userData.setSex(user.getSex());/*性别*/
        userData.setTel(user.getTel());/*电话*/
        /*用户积分*/
        userData.setScore(vipMapper.queryScore(user.getuId()).getScore());
        /*用户头像*/
        userData.setUserPath(userPicMapper.queryUserPic(user.getuId()).getUserPath());
        user.setPassword(null);
        return userData;
    }

    @Override
    public int getUserIdByname(String name) {
        User user=null;
        try{
            user=usermapper.queryUserByname(name);
            if (user==null){
                return 0;
            }
            return user.getuId();
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }

    /*查找所有用户*/
    @Override
    public Map queryAllUser(Page page) {
        List<User> userlist=new ArrayList<User>();
        int total;
        if(page.getQueryData()==null){
            userlist=usermapper.queryPageUsers(page.getOffset(),
                    page.getPageSize(),page.getSort(),page.getSortOrder(),
                    null,null);
            total=usermapper.getAlluserNum(null,null);
        }else {
            userlist = usermapper.queryPageUsers(page.getOffset(),
                    page.getPageSize(), page.getSort(), page.getSortOrder(),
                    page.getQueryData().getName(), page.getQueryData().getuName());
            total = usermapper.getAlluserNum(page.getQueryData().getName(), page.getQueryData().getuName());

        }
        List<UserData> userDataList=new ArrayList<UserData>();
        for(int i=0;i<userlist.size();i++){
            UserData userData=new UserData();
            userData.setName(StringEscapeUtils.escapeHtml(userlist.get(i).getName()));/*账号*/
            userData.setPassword(null);/*密码*/
            userData.setEmail(StringEscapeUtils.escapeHtml(userlist.get(i).getEmail()));/*邮箱*/
            userData.setuName(StringEscapeUtils.escapeHtml(userlist.get(i).getuName()));/*用户名*/
            userData.setSex(StringEscapeUtils.escapeHtml(userlist.get(i).getSex()));/*性别*/
            userData.setTel(StringEscapeUtils.escapeHtml(userlist.get(i).getTel()));/*电话*/
            /*用户积分*/
            userData.setScore(vipMapper.queryScore(userlist.get(i).getuId()).getScore());
            /*用户头像*/
            userData.setUserPath(StringEscapeUtils.escapeHtml(userPicMapper.queryUserPic(userlist.get(i).getuId()).getUserPath()));
            userDataList.add(userData);
        }
        Map map=new HashMap();
        map.put("total",total);
        map.put("rows",userDataList);
        System.out.println(map);
        return map;
    }
    public int queryMaxName(){
        return usermapper.queryMaxName();
    }
}
