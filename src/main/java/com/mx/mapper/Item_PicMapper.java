package com.mx.mapper;

import com.mx.pojo.Item_Pic;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface Item_PicMapper {

    //添加上传的图片路径
    int insertPicPath(Item_Pic item_pic);

    void queryByID(@Param("itemId") String item_id);

    //通过itemid查询商品图片
    List<Item_Pic> queryByItemID(@Param("itemId") Integer item_id);

    //查询商品对应的所有的图片路径及id
    List<Item_Pic> queryitemPicPaths(@Param("itemId") Integer itemId);

    boolean deletePic(@Param("delPicId") Integer delPicId);
}