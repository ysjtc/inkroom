package com.mx.mapper;

import com.mx.pojo.Comment;

public interface CommentMapper {
    int insert(Comment record);

    int insertSelective(Comment record);
}