package com.mx.service.Impl;

import com.mx.mapper.CategoryMapper;
import com.mx.mapper.ItemMapper;
import com.mx.pojo.Category;
import com.mx.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    private ItemMapper itemMapper;


    @Override
    public List<Integer> querycid() {
        return categoryMapper.queryCid();
    }

    @Override
    public List<Category> queryAllCategory() {
        List<Category> categoryList=categoryMapper.queryAllCategory();
//        //new一个只有cid的集合
//        List<Category> categories=new ArrayList<>();
//        //new一个Category对象
//        Category cg=new Category();
//        //遍历
//        for (Category category:categoryList) {
//            cg.setcId(category.getcId());
//            cg.setCateName(category.getCateName());
//            categories.add(cg);
//        }
        return categoryList;
    }

    @Override
    public String queryItemsItemCateName() {
        StringBuilder jsonStrAll = new StringBuilder("{");
        List<Category> categoryList=null;
        try{
            categoryList=categoryMapper.queryItemsItemCateName();
            jsonStrAll.append("\"result\":true,");
        }catch (Exception e){
            jsonStrAll.append("\"result\":false,");
        }

        String str=null;
        for (int i=0;i<categoryList.size();i++){
            //把你要拼接的字段放进去
            jsonStrAll.append("\""+categoryList.get(i).getcId()+"\":["+"\""+categoryList.get(i).getCateName()+"\","+itemMapper.QueryItemsCounts(categoryList.get(i).getcId())+"],");
        }
        //把最后的，（逗号）截取掉
        str = jsonStrAll.substring(0, jsonStrAll.length()-1)+"}";
        System.out.println("cate:"+str);
        return str;
    }

    @Override
    public boolean addCategory(String cateName) {
        int row=categoryMapper.addCategory(cateName);
        if (row==0){
            return false;
        }else return true;
    }


}
