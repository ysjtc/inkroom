package com.mx.mapper;

import com.mx.pojo.Comment_Pic;

public interface Comment_PicMapper {
    int insert(Comment_Pic record);

    int insertSelective(Comment_Pic record);
}