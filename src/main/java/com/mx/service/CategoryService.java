package com.mx.service;

import com.mx.pojo.Category;

import java.util.List;

/**
 * 商品类型管理
 */

public interface CategoryService {


    //查询商品类别id
    List<Integer> querycid();


    //查询商品类别id，商品类型
    List<Category> queryAllCategory();


    //查询商品类别
    String queryItemsItemCateName();


    boolean addCategory(String cateName);
}
