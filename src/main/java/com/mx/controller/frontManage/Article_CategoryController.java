package com.mx.controller.frontManage;

import com.mx.pojo.Article;
import com.mx.pojo.Article_Category;
import com.mx.service.Article_CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller

@RequestMapping("/ArticleCategory")
public class Article_CategoryController {

    @Autowired
    private Article_CategoryService article_categoryService;

    @RequestMapping("/queryAll")
    public String queryAll(HttpSession session){
        //查询所有文章类别
       /* List<Article> articles = article_categoryService.queryAll();
        //存入模型
        session.setAttribute("articles",articles);
        return "main ";*/
       List<Article_Category> l=article_categoryService.allCtgr();
       session.setAttribute("allCtgr",l);
       System.out.println(l);
       return "frontShow/club/club";
    }

    @RequestMapping("/query/category")
    public String queryByAcId(Integer acId, HttpSession session){
        List<Article> articles = article_categoryService.queryByAcId(acId);
        session.setAttribute("articles",articles);
        return "main";
    }


}
