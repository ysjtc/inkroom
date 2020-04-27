package com.mx.mapper;

import com.mx.pojo.Article;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ArticleMapper {

    List<Article>queryByTitle(@Param("title") String title);

    Article queryArticle(@Param("aId")Integer aId);

    List<Article> queryArticleByuId(@Param("uId")Integer uId);

    int add(Article article);

    List<Article> queryByConditions(@Param("title") String title, @Param("uId") Integer uId,@Param("content") String content,@Param("acId") Integer acId,@Param("uName") String uName,@Param("pageOff") Integer pageOff,@Param("pageSize") Integer pageSize,@Param("pageView") String pageView,@Param("praiseCount") String praiseCount,@Param("sort") String sort);

    int delete(@Param("aId") Integer aId);

    List<Article> myPraiseArticle(@Param(("aId")) Integer aId);

    List<Article> queryByctgr(@Param("acId") Integer acId);

    int addArticle(String title,String content,Integer uId,Integer acId);

    Integer articlePraise(@Param("aId") Integer aId);

    Integer ifPraised(@Param("aId") Integer aId,@Param("uId") Integer uId);

    void PVRaise(@Param("aId") Integer aId);

    int addAP(@Param("aId") Integer aId,@Param("uId") Integer uId);

    int myPraiseCount(@Param("uId") Integer uId);

    List<Map<String,Object>> myArticles(Integer uId);

    Map<String,Object> statistics(Integer uId);

    //articleManage
    List<Map<String,Object>> articleManage();

}