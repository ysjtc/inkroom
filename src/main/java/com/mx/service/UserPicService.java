package com.mx.service;

import com.mx.pojo.User_Pic;

public interface UserPicService {
    /*用户图片添加*/
    public void addUserPic(User_Pic userPic);
    /*用户图片删除*/
    public void deleteUserPic(User_Pic userPic);
    /*用户图片更新*/
    public void updateUserPic(User_Pic userPic);
    /*查找图片*/
    public User_Pic queryById(int id);
}
