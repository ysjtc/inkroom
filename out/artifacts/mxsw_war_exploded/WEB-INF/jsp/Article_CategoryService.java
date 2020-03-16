package com.mx.service;

import com.mx.pojo.Article;

import java.util.List;

public interface Article_CategoryService {
    List<Article> queryAll();

    List<Article> queryByAcId(Integer acId);
}
