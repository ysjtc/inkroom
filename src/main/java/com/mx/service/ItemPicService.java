package com.mx.service;

import com.mx.pojo.Item_Pic;

public interface ItemPicService {

    //添加上传的图片路径
    boolean addItemsPic(Item_Pic item_pic);

    //通过itemid查询商品对应的路径及id
    String queryitemPicPaths(Integer itemId);

    boolean deletePic(Integer delPicId);
}
