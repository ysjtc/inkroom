package com.mx.mapper;

import com.mx.pojo.User_Pic;

public interface User_PicMapper {
    int insert(User_Pic record);

    int insertSelective(User_Pic record);

    User_Pic queryUserPic(int userPic);

    int deleteUserPic(int uId);

    int updateUserPic(User_Pic userPic);
}