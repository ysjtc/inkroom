package com.mx.service.Impl;

import com.mx.mapper.ItemMapper;
import com.mx.mapper.Item_PicMapper;
import com.mx.pojo.Item;
import com.mx.pojo.Item_Pic;
import com.mx.service.ItemsService;
import com.mx.utils.ConvertJson.ConvertJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ItemsServiceImpl implements ItemsService {

    @Autowired
    private  ItemMapper itemMapper;

    @Autowired
    private Item_PicMapper item_picMapper;

    @Override
    public boolean addItems(Item item) {
        int row=itemMapper.addItems(item);
        if (row==0){
            return false;
        }else return true;
    }

    @Override
    public String queryItemsByName(Integer pageSize, Integer offset, String sort, String sortOrder,String name) {
        List<Item> list=itemMapper.queryItemsByName(pageSize,offset,sort,sortOrder,name);
        //将查询结果转换成json数组
        int count=itemMapper.queryItemsByNameCount(name);
        if (count==0){
            return null;
        }
        String str=ConvertJson.Convert(count,list);
        return str;
    }

    @Override
    public String queryItemsByISBN(Integer pageSize, Integer offset, String sort, String sortOrder,String isbn) {
        List<Item> list=itemMapper.queryItemsByISBN(pageSize,offset,sort,sortOrder,isbn);
        int count=itemMapper.queryItemsByISBNCount(isbn);
        if (count==0){
            return null;
        }
        String str=ConvertJson.Convert(count,list);
        return str;
    }

    @Override
    public String queryAll(Integer pageSize, Integer offset, String sort, String sortOrder) {
        List<Item> itemList=itemMapper.queryAll(pageSize,offset,sort,sortOrder);
//        System.out.println("lust："+itemList);
//        System.out.println(itemList.size());
        int count=itemMapper.queryItemsCount();
        if (count==0){
            return null;
        }
        String str=ConvertJson.Convert(count,itemList);
        return str;
    }

    //查询商品总记录数
    @Override
    public int queryItemsCount() {
        int count=itemMapper.queryItemsCount();
        if (count==0){
            return 0;
        }
        return count;
    }

    @Override
    public boolean updateItemsInfo(Item item) {
        boolean row=itemMapper.updateItemsInfo(item);
        if (!row){
            return false;
        }else return true;
    }

    @Override
    public boolean deleteItem(Integer id) {
        boolean row= itemMapper.deleteItem(id);
        if (!row){
            return false;
        }else return true;
    }

    @Override
    public String queryItemById(Integer  itemId) {
//       System.out.println("传入的itemid:"+itemId);
        List<Item> list=itemMapper.queryItemsById(itemId);
        String item=ConvertJson.UpdateConvertItem(list);
        List<Item_Pic> picPath=item_picMapper.queryByItemID(itemId);
        String itemPath=ConvertJson.UpdateConvertPath(item,picPath);
        return itemPath;
    }

    @Override
    public String queryItemsByPrice(Integer pageSize, Integer offset, String sort, String sortOrder,Integer lowPrice, Integer highPrice) {
        List<Item> list=itemMapper.queryItemsByPrice(pageSize,offset,sort,sortOrder,lowPrice,highPrice);
        int count=itemMapper.queryItemsByPriceCount(lowPrice,highPrice);
        if (count==0){
         return null;
        }
        String str=ConvertJson.Convert(count,list);
        return str;
    }

    @Override
    public String queryItemsByItemCate(Integer pageSize, Integer offset, String sort, String sortOrder,Integer itemCate) {
        List<Item> list=itemMapper.queryItemsByItemCate(pageSize,offset,sort,sortOrder,itemCate);
        int count=itemMapper.queryItemsByItemCateCount(itemCate);
        if (count==0){
            return null;
        }
        //将查询结果转换成json数组
        String str=ConvertJson.Convert(count,list);
        return str;
    }


    /**
     *前台展示
     */

    @Override
    public String FrontQueryAll(Integer pageSize, Integer offset, String sort, String sortOrder) {
        String allData="";
        StringBuilder jsonStrAll = new StringBuilder("{");
        try{
            String str=queryAll(pageSize,offset,sort,sortOrder);
             allData=str.substring(1,str.lastIndexOf("}"))+"}";
            jsonStrAll.append("\"result\":true,");
        }catch (Exception e){
            jsonStrAll.append("\"result\":false}");
        }
        jsonStrAll.append(allData);
        System.out.println("---"+jsonStrAll);
        return jsonStrAll+"";
    }

    @Override
    public String FrontQueryItemsByPrice(Integer pageSize, Integer offset, String sort, String sortOrder, Integer lowPrice, Integer highPrice) {
        String ItemsByPriceData="";
        StringBuilder jsonStrAll = new StringBuilder("{");
        try{
            String str=queryItemsByPrice(pageSize,offset,sort,sortOrder,lowPrice,highPrice);
            System.out.println("查询出来的价格商品："+str);
             ItemsByPriceData=str.substring(1,str.lastIndexOf("}"))+"}";
            jsonStrAll.append("\"result\":true,");
            jsonStrAll.append(ItemsByPriceData);
        }catch (Exception e){
            jsonStrAll.append("\"result\":false}");
        }
        System.out.println("---"+jsonStrAll);
        return jsonStrAll+"";
    }

    @Override
    public String FrontQueryItemsByPriceAndCateAndLabelID(Integer pageSize, Integer offset, String sort, String sortOrder, Integer lowPrice, Integer highPrice, Integer cateId, Integer labelId) {
        String ItemsByPriceData="";
        StringBuilder jsonStrAll = new StringBuilder("{");
        try{
            List<Item> list=itemMapper.queryItemsByPriceAndCateAndLabelID(pageSize,offset,sort,sortOrder,lowPrice,highPrice,cateId,labelId);
            int count=itemMapper.queryItemsByPriceAndCateAndLabelIDCount(lowPrice,highPrice,cateId,labelId);
            if (count==0){
                jsonStrAll.append("\"result\":false}");
                return jsonStrAll+"";
            }
            //将查询结果转换成json数组
            String str=ConvertJson.Convert(count,list);
            ItemsByPriceData=str.substring(1,str.lastIndexOf("}"))+"}";
            jsonStrAll.append("\"result\":true,");
        }catch (Exception e){
            jsonStrAll.append("\"result\":false}");
        }
        jsonStrAll.append(ItemsByPriceData);
        System.out.println("---"+jsonStrAll);
        return jsonStrAll+"";
    }

    @Override
    public String FrontQueryItemsByLabelId(Integer pageSize, Integer offset, String sort, String sortOrder, Integer labelId) {
        String ItemsByPriceData="";
        StringBuilder jsonStrAll = new StringBuilder("{");
        try{
            List<Item> list=itemMapper.queryItemsByLabelId(pageSize,offset,sort,sortOrder,labelId);
            int count=itemMapper.queryItemsByLabelIdCount(labelId);
            if (count==0){
                jsonStrAll.append("\"result\":false}");
                return jsonStrAll+"";
            }
            //将查询结果转换成json数组
            String str=ConvertJson.Convert(count,list);
            ItemsByPriceData=str.substring(1,str.lastIndexOf("}"))+"}";
            jsonStrAll.append("\"result\":true,");
        }catch (Exception e){
            jsonStrAll.append("\"result\":false}");
        }
        jsonStrAll.append(ItemsByPriceData);
        System.out.println("---"+jsonStrAll);
        return jsonStrAll+"";
    }

    @Override
    public String FrontQueryItemsByCateId(Integer pageSize, Integer offset, String sort, String sortOrder, Integer cateId) {
        String CateData="";
        StringBuilder jsonStrAll = new StringBuilder("{");
        try{
            String str=queryItemsByItemCate(pageSize,offset,sort,sortOrder,cateId);
            CateData =str.substring(1,str.lastIndexOf("}"))+"}";
            jsonStrAll.append("\"result\":true,");
        }catch (Exception e){
            jsonStrAll.append("\"result\":false,");
        }
        jsonStrAll.append(CateData);
        System.out.println("---"+jsonStrAll);
        return jsonStrAll+"";
    }

    @Override
    public String FrontQueryItemsByCateAndLabelID(Integer pageSize, Integer offset, String sort, String sortOrder, Integer cateId, Integer labelId) {
        String ItemsByPriceData="";
        StringBuilder jsonStrAll = new StringBuilder("{");
        try{
            List<Item> list=itemMapper.queryItemsByCateAndLabelID(pageSize,offset,sort,sortOrder,cateId,labelId);
            int count=itemMapper.queryItemsByCateAndLabelIDCount(cateId,labelId);
            if (count==0){
                jsonStrAll.append("\"result\":false}");
                return jsonStrAll+"";
            }
            //将查询结果转换成json数组
            String str=ConvertJson.Convert(count,list);
            ItemsByPriceData=str.substring(1,str.lastIndexOf("}"))+"}";
            jsonStrAll.append("\"result\":true,");

        }catch (Exception e){
            jsonStrAll.append("\"result\":false}");
        }
        jsonStrAll.append(ItemsByPriceData);
        System.out.println("---"+jsonStrAll);
        return jsonStrAll+"";
    }

    @Override
    public String FrontQueryItemsByName(Integer pageSize, Integer offset, String sort, String sortOrder, String queryData) {
        String querydata="";
        StringBuilder jsonStrAll = new StringBuilder("{");
        try{
            String str=queryItemsByName(pageSize,offset,sort,sortOrder,queryData);
            querydata =str.substring(1,str.lastIndexOf("}"))+"}";
            jsonStrAll.append("\"result\":true,");
        }catch (Exception e){
            jsonStrAll.append("\"result\":false}");
        }
        jsonStrAll.append(querydata);
        System.out.println("---"+jsonStrAll);
        return jsonStrAll+"";
    }

    @Override
    public String FrontQueryItemsByNameAndLabelId(Integer pageSize, Integer offset, String sort, String sortOrder, String queryData, Integer labelId) {
        String ItemsByPriceData="";
        StringBuilder jsonStrAll = new StringBuilder("{");
        try{
            List<Item> list=itemMapper.queryItemsByNameAndLabelId(pageSize,offset,sort,sortOrder,queryData,labelId);
            int count=itemMapper.queryItemsByNameAndLabelIdCount(queryData,labelId);
            if (count==0){
                jsonStrAll.append("\"result\":false}");
                return jsonStrAll+"";
            }
            //将查询结果转换成json数组
            String str=ConvertJson.Convert(count,list);
            ItemsByPriceData=str.substring(1,str.lastIndexOf("}"))+"}";
            jsonStrAll.append("\"result\":true,");

        }catch (Exception e){
            jsonStrAll.append("\"result\":false}");
        }
        jsonStrAll.append(ItemsByPriceData);
        System.out.println("---"+jsonStrAll);
        return jsonStrAll+"";
    }

    @Override
    public String FrontQueryItemsByNameAndCateId(Integer pageSize, Integer offset, String sort, String sortOrder, String queryData, Integer cateId) {
        String ItemsByPriceData="";
        StringBuilder jsonStrAll = new StringBuilder("{");
        try{
            List<Item> list=itemMapper.queryItemsByNameAndCateId(pageSize,offset,sort,sortOrder,queryData,cateId);
            int count=itemMapper.queryItemsByNameAndCateIdCount(queryData,cateId);
            if (count==0){
                jsonStrAll.append("\"result\":false}");
                return jsonStrAll+"";
            }
            //将查询结果转换成json数组
            String str=ConvertJson.Convert(count,list);
            ItemsByPriceData=str.substring(1,str.lastIndexOf("}"))+"}";
            jsonStrAll.append("\"result\":true,");

        }catch (Exception e){
            jsonStrAll.append("\"result\":false}");
        }
        jsonStrAll.append(ItemsByPriceData);
        System.out.println("---"+jsonStrAll);
        return jsonStrAll+"";
    }

    @Override
    public String FrontQueryItemsByNameAndCateIdAndLabelId(Integer pageSize, Integer offset, String sort, String sortOrder, String queryData, Integer cateId, Integer labelId) {
        String ItemsByPriceData="";
        StringBuilder jsonStrAll = new StringBuilder("{");
        try{
            List<Item> list=itemMapper.queryItemsByNameAndCateIdAndLabelId(pageSize,offset,sort,sortOrder,queryData,cateId,labelId);
            int count=itemMapper.queryItemsByNameAndCateIdAndLabelIdCount(queryData,cateId,labelId);
            if (count==0){
                jsonStrAll.append("\"result\":false}");
                return jsonStrAll+"";
            }
            //将查询结果转换成json数组
            String str=ConvertJson.Convert(count,list);
            ItemsByPriceData=str.substring(1,str.lastIndexOf("}"))+"}";
            jsonStrAll.append("\"result\":true,");

        }catch (Exception e){
            jsonStrAll.append("\"result\":false}");
        }
        jsonStrAll.append(ItemsByPriceData);
        System.out.println("---"+jsonStrAll);
        return jsonStrAll+"";
    }

    @Override
    public String FrontQueryItemsByNameAndPrice(Integer pageSize, Integer offset, String sort, String sortOrder, String queryData, Integer lowPrice, Integer highPrice) {
        String ItemsByPriceData="";
        StringBuilder jsonStrAll = new StringBuilder("{");
        try{
            List<Item> list=itemMapper.queryItemsByNameAndPrice(pageSize,offset,sort,sortOrder,queryData,lowPrice,highPrice);
            int count=itemMapper.queryItemsByNameAndPriceCount(queryData,lowPrice,highPrice);
            if (count==0){
                jsonStrAll.append("\"result\":false}");
                return jsonStrAll+"";
            }
            //将查询结果转换成json数组
            String str=ConvertJson.Convert(count,list);
            ItemsByPriceData=str.substring(1,str.lastIndexOf("}"))+"}";
            jsonStrAll.append("\"result\":true,");

        }catch (Exception e){
            jsonStrAll.append("\"result\":false}");
        }
        jsonStrAll.append(ItemsByPriceData);
        System.out.println("---"+jsonStrAll);
        return jsonStrAll+"";
    }

    @Override
    public String FrontQueryItemsByNameAndPriceAndLabelId(Integer pageSize, Integer offset, String sort, String sortOrder, String queryData, Integer lowPrice, Integer highPrice, Integer labelId) {
        String ItemsByPriceData="";
        StringBuilder jsonStrAll = new StringBuilder("{");
        try{
            List<Item> list=itemMapper.queryItemsByNameAndPriceAndLabelId(pageSize,offset,sort,sortOrder,queryData,lowPrice,highPrice,labelId);
            int count=itemMapper.queryItemsByNameAndPriceAndLabelIdCount(queryData,lowPrice,highPrice,labelId);
            if (count==0){
                jsonStrAll.append("\"result\":false}");
                return jsonStrAll+"";
            }
            //将查询结果转换成json数组
            String str=ConvertJson.Convert(count,list);
            ItemsByPriceData=str.substring(1,str.lastIndexOf("}"))+"}";
            jsonStrAll.append("\"result\":true,");

        }catch (Exception e){
            jsonStrAll.append("\"result\":false}");
        }
        jsonStrAll.append(ItemsByPriceData);
        System.out.println("---"+jsonStrAll);
        return jsonStrAll+"";
    }

    @Override
    public String FrontQueryItemsByExceptLabelId(Integer pageSize, Integer offset, String sort, String sortOrder, String queryData, Integer lowPrice, Integer highPrice, Integer cateId) {
        String ItemsByPriceData="";
        StringBuilder jsonStrAll = new StringBuilder("{");
        try{
            List<Item> list=itemMapper.queryItemsByExceptLabelId(pageSize,offset,sort,sortOrder,queryData,lowPrice,highPrice,cateId);
            int count=itemMapper.queryItemsByExceptLabelIdCount(queryData,lowPrice,highPrice,cateId);
            if (count==0){
                jsonStrAll.append("\"result\":false}");
                return jsonStrAll+"";
            }
            //将查询结果转换成json数组
            String str=ConvertJson.Convert(count,list);
            ItemsByPriceData=str.substring(1,str.lastIndexOf("}"))+"}";
            jsonStrAll.append("\"result\":true,");

        }catch (Exception e){
            jsonStrAll.append("\"result\":false}");
        }
        jsonStrAll.append(ItemsByPriceData);
        System.out.println("---"+jsonStrAll);
        return jsonStrAll+"";
    }

    @Override
    public String FrontQueryItemsByAll(Integer pageSize, Integer offset, String sort, String sortOrder, String queryData, Integer lowPrice, Integer highPrice, Integer cateId, Integer labelId) {
        String ItemsByPriceData="";
        StringBuilder jsonStrAll = new StringBuilder("{");
        try{
            List<Item> list=itemMapper.queryItemsByAll(pageSize,offset,sort,sortOrder,queryData,lowPrice,highPrice,cateId,labelId);
            int count=itemMapper.queryItemsByAllCount(queryData,lowPrice,highPrice,cateId,labelId);
            if (count==0){
                jsonStrAll.append("\"result\":false}");
                return jsonStrAll+"";
            }
            //将查询结果转换成json数组
            String str=ConvertJson.Convert(count,list);
            ItemsByPriceData=str.substring(1,str.lastIndexOf("}"))+"}";
            jsonStrAll.append("\"result\":true,");

        }catch (Exception e){
            jsonStrAll.append("\"result\":false}");
        }
        jsonStrAll.append(ItemsByPriceData);
        System.out.println("---"+jsonStrAll);
        return jsonStrAll+"";
    }

    @Override
    public String FrontQueryItemsByPriceAndLabelId(Integer pageSize, Integer offset, String sort, String sortOrder, Integer lowPrice, Integer highPrice, Integer labelId) {
        String ItemsByPriceData="";
        StringBuilder jsonStrAll = new StringBuilder("{");
        try{
            List<Item> list=itemMapper.queryItemsByPriceAndLabelId(pageSize,offset,sort,sortOrder,lowPrice,highPrice,labelId);
            int count=itemMapper.queryItemsByPriceAndLabelIdCount(lowPrice,highPrice,labelId);
            if (count==0){
                jsonStrAll.append("\"result\":false}");
                return jsonStrAll+"";
            }
            //将查询结果转换成json数组
            String str=ConvertJson.Convert(count,list);
            ItemsByPriceData=str.substring(1,str.lastIndexOf("}"))+"}";
            jsonStrAll.append("\"result\":true,");
        }catch (Exception e){
            jsonStrAll.append("\"result\":false}");
        }
        jsonStrAll.append(ItemsByPriceData);
        System.out.println("---"+jsonStrAll);
        return jsonStrAll+"";
    }

    @Override
    public String FrontQueryItemsByPriceAndCateId(Integer pageSize, Integer offset, String sort, String sortOrder, Integer lowPrice, Integer highPrice, Integer cateId) {
        String ItemsByPriceData="";
        StringBuilder jsonStrAll = new StringBuilder("{");
        try{
            List<Item> list=itemMapper.queryItemsByPriceAndCateId(pageSize,offset,sort,sortOrder,lowPrice,highPrice,cateId);
            int count=itemMapper.queryItemsByPriceAndCateIdCount(lowPrice,highPrice,cateId);
            if (count==0){
                jsonStrAll.append("\"result\":false}");
                return jsonStrAll+"";
            }
            //将查询结果转换成json数组
            String str=ConvertJson.Convert(count,list);
            ItemsByPriceData=str.substring(1,str.lastIndexOf("}"))+"}";
            jsonStrAll.append("\"result\":true,");

        }catch (Exception e){
            jsonStrAll.append("\"result\":false}");
        }
        jsonStrAll.append(ItemsByPriceData);
        System.out.println("---"+jsonStrAll);
        return jsonStrAll+"";
    }

    @Override
    public int QueryItemsCounts(Integer cateId) {
        return itemMapper.QueryItemsCounts(cateId);
    }

    @Override
    public String FrontQueryItemById(Integer itemId) {
        List<Item> list=itemMapper.FrontQueryItemById(itemId);
        String item=ConvertJson.FrontConvertItem(list);
        System.out.println("传入的itemid是："+itemId);
        List<Item_Pic> picPath=item_picMapper.queryByItemID(itemId);
        String itemPath=ConvertJson.UpdateConvertPath(item,picPath);
        return itemPath;
    }

    @Override
    public Float queryItemsPriceByItemId(Integer item_id) {
        try{
            return  itemMapper.queryItemsPriceByItemId(item_id);
        }catch (Exception e){
            return null;
        }
    }

}
