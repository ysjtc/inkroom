package com.mx.service.Impl;

import com.mx.mapper.Article_CommentMapper;
import com.mx.pojo.Article_Comment;
import com.mx.service.Article_CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service

    public class Article_CommentServiceImpl implements Article_CommentService {

    @Autowired
    private Article_CommentMapper article_commentMapper;


    @Override
    public boolean add(Article_Comment article_comment){
        //article_comment.setCreattime(new Date());
        int row = article_commentMapper.add(article_comment);
        return row ==1 ? true : false;
    }

    @Override
    public List<Article_Comment> queryCByA_id(Integer aId) {
        return article_commentMapper.queryCByA_id(aId);
    }

    @Override
    public List<Map<String, Object>> cmtAndUser(Integer aId) {
        List<Map<String,Object>> l=article_commentMapper.cmtAndUser(aId);
        return l;
    }

    @Override
    public int addAC(Article_Comment article_comment) {
        return article_commentMapper.addAC(article_comment);
    }

    @Override
    public List<Map<String, Object>> reply(Integer aId, Integer acoId) {
        return article_commentMapper.reply(aId,acoId);
    }

    @Override
    public int addReply(Map<String, Object> map) {
        return article_commentMapper.addReply(map);
    }

    @Override
    public int delReply(Integer acr_id) {
        return article_commentMapper.delReply(acr_id);
    }

    @Override
    public int delAC(Integer aco_id) {
        return article_commentMapper.delAC(aco_id);
    }

    @Override
    public List<Map<String, Object>> myComment(Integer uId) {
        return article_commentMapper.myComment(uId);
    }

    @Override
    public List<Map<String, Object>> myReply(Integer uId) {
        return article_commentMapper.myReply(uId);
    }

    @Override
    public List<Article_Comment> queryCommentByuId(Integer uId) {
        return article_commentMapper.queryCommentByuId(uId);
    }

    @Override
    public List<Article_Comment> queryCommentByaId(Integer aId) {
        return article_commentMapper.queryCommentByaId(aId);
    }
}
