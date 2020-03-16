package com.mx.service.Impl;

import com.mx.mapper.Article_CategoryMapper;
import com.mx.pojo.Article;
import com.mx.pojo.Article_Category;
import com.mx.service.Article_CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
    public class Article_CategoryServiceImpl implements Article_CategoryService {

    @Autowired
    private Article_CategoryMapper article_categoryMapper;

    @Override
    public List<Article> queryAll() {
        return article_categoryMapper.queryAll();
    }

    @Override
    public List<Article_Category> allCtgr() {return article_categoryMapper.allCtgr();
    }

    @Override
    public List<Article> queryByAcId(Integer acId) {
        return article_categoryMapper.queryByAcId(acId);
    }

}
