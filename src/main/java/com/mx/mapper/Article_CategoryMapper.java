package com.mx.mapper;

import com.mx.pojo.Article;
import com.mx.pojo.Article_Category;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface Article_CategoryMapper {
    List<Article> queryAll();
    List<Article_Category> allCtgr();
    List<Article> queryByAcId(@Param("acId")Integer acId);
}