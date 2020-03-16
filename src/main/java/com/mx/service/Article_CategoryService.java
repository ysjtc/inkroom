package com.mx.service;

import com.mx.pojo.Article;
import com.mx.pojo.Article_Category;

import java.util.List;

public interface Article_CategoryService {
    List<Article> queryAll();
    List<Article_Category> allCtgr();
    List<Article> queryByAcId(Integer acId);
}
