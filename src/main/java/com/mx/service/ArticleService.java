package com.mx.service;

import com.mx.pojo.Article;

import java.util.List;
import java.util.Map;


public interface ArticleService {

    List<Article>queryByTitle(String title);

    Article queryArticle(Integer aId);    //按标题打开文章

    List<Article> queryArticleByuId(Integer uId);

    int add(Article article);

    List<Article> queryByConditions(String title, Integer uId, String content, Integer acId, String uName, Integer pageOff, Integer pageSize, String pageView,String praiseCount,String sort);

    boolean delete(Integer aId);

    List<Article> myPraiseArticle(Integer aId);

    List<Article> queryByctgr(Integer acId);

    int addArticle(String title,String content,Integer uId,Integer acId);

    Integer articlePraise(Integer aId);

    boolean ifPraised(Integer aId,Integer uId);

    void PVRaise(Integer aId);

    int addAP(Integer aId,Integer uId);

    int myPraiseCount(Integer uId);

    List<Map<String,Object>> myArticles(Integer uId);

    Map<String,Object> statistics(Integer uId);

    //articleManage
    List<Map<String,Object>> articleManage();
}
