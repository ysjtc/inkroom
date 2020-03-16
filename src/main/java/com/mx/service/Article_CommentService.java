package com.mx.service;

import com.mx.pojo.Article_Comment;

import java.util.List;
import java.util.Map;


public interface Article_CommentService {

    List<Article_Comment> queryCommentByuId(Integer uId);

    List<Article_Comment> queryCommentByaId(Integer aId);

    boolean add(Article_Comment article_comment);

    List<Article_Comment> queryCByA_id(Integer aId);
    List<Map<String,Object>> cmtAndUser(Integer aId);
    int addAC(Article_Comment article_comment);
    List<Map<String ,Object>> reply(Integer aId,Integer acoId);
    int addReply(Map<String,Object> map);
    int delReply(Integer acr_id);
    int delAC(Integer aco_id);
    List<Map<String,Object>> myComment(Integer uId);
    List<Map<String,Object>> myReply(Integer uId);

}
