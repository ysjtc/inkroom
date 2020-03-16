package com.mx.service.Impl;

import com.mx.mapper.ArticleMapper;
import com.mx.mapper.Article_CommentMapper;
import com.mx.pojo.Article;
import com.mx.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
     ArticleMapper articleMapper;

    @Autowired
    Article_CommentMapper article_commentMapper;

    @Override

    public List<Article> queryByTitle(String title) {
        return articleMapper.queryByTitle(title);
    }

    @Override
    public Article queryArticle(Integer aId) {
        Article list=articleMapper.queryArticle(aId);
        /* StringBuilder json=new StringBuilder("{");
        try{
           list = articleMapper.queryArticle(aId);
            System.out.println(list);
          json.append("result:true");//记得改
            for (int i=0;i<list.size();i++) {
                json.append(list.get(i).TOjson());
            }
            System.out.println("json:::::"+json);//检验这一行丶输出，到网页
        }catch (Exception e){
            e.printStackTrace();
        }*/
        return list;
       //return json+"";
    }

    @Override
    public List<Article> queryArticleByuId(Integer uId) {
        return articleMapper.queryArticleByuId(uId);
    }

    @Override
    public int add(Article article) {
        return articleMapper.add(article);
    }

    @Override
    public List<Article> queryByConditions(String title, Integer uId, String content, Integer acId, String uName, Integer pageOff, Integer pageSize,String pageView,String praiseCount,String sort) {
        return articleMapper.queryByConditions(title,uId,content,acId,uName,pageOff,pageSize,pageView,praiseCount,sort);
    }

    @Override
    public boolean delete(Integer aId) {
        int row = articleMapper.delete(aId);
        return row == 1 ? true : false;
    }
    @Override
    public List<Article> myPraiseArticle(Integer aId){
        List<Article> l=articleMapper.myPraiseArticle(aId);
        return l;
    }

    @Override
    public List<Article> queryByctgr(Integer acId){
        List<Article> l=articleMapper.queryByctgr(acId);
        return l;
    }

    @Override
    public int addArticle(String title,String content,Integer uId,Integer acId) {
        int i=articleMapper.addArticle(title,content,uId,acId);
        return i;
    }

    @Override
    public Integer articlePraise(Integer aId) {
        return articleMapper.articlePraise(aId);

    }

    @Override
    public boolean ifPraised(Integer aId, Integer uId) {
        if(articleMapper.ifPraised(aId,uId)==null){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public void PVRaise(Integer aId) {
        articleMapper.PVRaise(aId);
    }

    @Override
    public int addAP(Integer aId, Integer uId) {
        return articleMapper.addAP(aId,uId);

    }

    @Override
    public int myPraiseCount(Integer uId) {
        return articleMapper.myPraiseCount(uId);
    }

    @Override
    public List<Map<String, Object>> myArticles(Integer uId) {
        return articleMapper.myArticles(uId);
    }

    @Override
    public Map<String, Object> statistics(Integer uId) {
        return articleMapper.statistics(uId);
    }
}
