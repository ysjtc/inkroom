package com.mx.service.Impl;/*
@author 郭子雄
@description 用户图片业务实现类
*/


import com.mx.mapper.User_PicMapper;
import com.mx.pojo.User_Pic;
import com.mx.service.UserPicService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserPicServiceImp implements UserPicService {
    @Autowired
    private User_PicMapper userPicmapper;

    @Override
    public void addUserPic(User_Pic userPic) {
        if(userPicmapper.queryUserPic(userPic.getuId())!=null){
            userPicmapper.deleteUserPic(userPic.getuId());
            userPicmapper.insert(userPic);
            System.out.println("id为"+userPic.getuId()+"   路径为"+userPic.getUserPath());
        }else {
            userPicmapper.insert(userPic);
            System.out.println("id为"+userPic.getuId()+"   路径为"+userPic.getUserPath());

        }
    }

    @Override
    public void deleteUserPic(User_Pic userPic) {
        userPicmapper.deleteUserPic(userPic.getuId());
    }

    @Override
    public void updateUserPic(User_Pic userPic) {
        userPicmapper.updateUserPic(userPic);
    }

    @Override
    public User_Pic queryById(int id) {
        return  userPicmapper.queryUserPic(id);
    }


}
