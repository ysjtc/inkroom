package com.mx.mapper;

import com.mx.pojo.Category;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CategoryMapper {


    List<Integer> queryCid();

    List<Category> queryAllCategory();

    List<Category> queryItemsItemCateName();

    int addCategory(@Param("cateName") String cateName);
}