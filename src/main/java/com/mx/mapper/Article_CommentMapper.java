package com.mx.mapper;

import com.mx.pojo.Article_Comment;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface Article_CommentMapper {

    int add(@Param("article_comment") Article_Comment article_comment);
    List<Article_Comment> queryCommentByuId(@Param("uId") Integer uId);
    List<Article_Comment> queryCommentByaId(@Param("aId")Integer aId);
    void delete(@Param("aId") Integer aId);
    List<Article_Comment> queryCByA_id(@Param("aId") Integer aId);
    List<Map<String,Object>> cmtAndUser(@Param("aId") Integer aId);
    int addAC(Article_Comment article_comment);
    List<Map<String,Object>> reply(Integer aId,Integer acoId);
    int addReply(Map<String,Object> map);
    int delReply(Integer acr_id);
    int delAC(Integer acoId);
    List<Map<String,Object>> myComment(Integer uId);
    List<Map<String,Object>> myReply(Integer uId);
}