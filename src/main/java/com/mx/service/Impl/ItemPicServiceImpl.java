package com.mx.service.Impl;

import com.mx.mapper.Item_PicMapper;
import com.mx.pojo.Item_Pic;
import com.mx.service.ItemPicService;
import com.mx.utils.ConvertJson.ConvertJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemPicServiceImpl implements ItemPicService {


    //自动注入ItempicMapper
    @Autowired
    Item_PicMapper item_picMapper;


    //添加上传的图片路径
    @Override
    public boolean addItemsPic(Item_Pic item_pic) {
        int row=item_picMapper.insertPicPath(item_pic);
        if (row==0){
            return false;
        }else return true;
    }

    @Override
    public String queryitemPicPaths(Integer itemId) {
        System.out.println("传入的itemid:"+itemId);

        List<Item_Pic> picPath=item_picMapper.queryitemPicPaths(itemId);
        String str= ConvertJson.ConvertPicPath(picPath);
//        System.out.println(str);
        return str;
    }

    @Override
    public boolean deletePic(Integer delPicId) {
        return item_picMapper.deletePic(delPicId);
    }
}
