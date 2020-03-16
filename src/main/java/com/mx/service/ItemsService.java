package com.mx.service;

import com.mx.pojo.Item;

/**
 * 商品管理
 */

public interface ItemsService {

    /**
     *后台部分
     */
    //添加商品
    boolean addItems(Item item);

    //查询全部商品
    String queryAll(Integer pageSize, Integer offset, String sort, String sortOrder);

    //查询商品总记录数
    int queryItemsCount();

    //通过name查询商品
    String  queryItemsByName(Integer pageSize, Integer offset, String sort, String sortOrder,String name);

    //通过ISBN查询商品
    String queryItemsByISBN(Integer pageSize, Integer offset, String sort, String sortOrder,String isbn);

    //通过价格区间查询商品
    String queryItemsByPrice(Integer pageSize, Integer offset, String sort, String sortOrder,Integer lowPrice, Integer highPrice);

    //通过商品类别查询商品
    String queryItemsByItemCate(Integer pageSize, Integer offset, String sort, String sortOrder,Integer itemCate);

    //修改商品信息
    boolean updateItemsInfo(Item item);

    //删除商品
    boolean deleteItem(Integer id);

    //通过itemid查询商品
    String queryItemById(Integer itemid);


    /**
     * 前台部分
     */


    //查询所有商品（前台展示）
    String FrontQueryAll(Integer pageSize, Integer offset, String sort, String sortOrder);

    //通过价格区间查询商品（前台展示）
    String FrontQueryItemsByPrice(Integer pageSize, Integer offset, String sort, String sortOrder,Integer lowPrice, Integer highPrice);

    //通过价格区间和cateId和Labelid查询商品（前台展示）
    String FrontQueryItemsByPriceAndCateAndLabelID(Integer pageSize, Integer offset, String sort, String sortOrder, Integer lowPrice, Integer highPrice, Integer cateId, Integer labelId);

    //通过Labelid查询商品（前台展示）
    String FrontQueryItemsByLabelId(Integer pageSize, Integer offset, String sort, String sortOrder, Integer labelId);

    //通过Cateid查询商品（前台展示）
    String FrontQueryItemsByCateId(Integer pageSize, Integer offset, String sort, String sortOrder, Integer cateId);

    //通过Cateid和labelId查询商品（前台展示）
    String FrontQueryItemsByCateAndLabelID(Integer pageSize, Integer offset, String sort, String sortOrder, Integer cateId, Integer labelId);

    //通过name查询商品（前台展示）
    String FrontQueryItemsByName(Integer pageSize, Integer offset, String sort, String sortOrder, String queryData);

    //通过name和labelId查询商品（前台展示）
    String FrontQueryItemsByNameAndLabelId(Integer pageSize, Integer offset, String sort, String sortOrder, String queryData, Integer labelId);

    //通过name和CateId查询商品（前台展示）
    String FrontQueryItemsByNameAndCateId(Integer pageSize, Integer offset, String sort, String sortOrder, String queryData, Integer cateId);

    //通过name和CateIdAndLabelId查询商品（前台展示）
    String FrontQueryItemsByNameAndCateIdAndLabelId(Integer pageSize, Integer offset, String sort, String sortOrder, String queryData, Integer cateId, Integer labelId);

    //通过name和price查询商品（前台展示）
    String FrontQueryItemsByNameAndPrice(Integer pageSize, Integer offset, String sort, String sortOrder, String queryData, Integer lowPrice, Integer highPrice);

    //通过name和price和labelId查询商品（前台展示）
    String FrontQueryItemsByNameAndPriceAndLabelId(Integer pageSize, Integer offset, String sort, String sortOrder, String queryData, Integer lowPrice, Integer highPrice, Integer labelId);

    //通过name和price和cateId查询商品（前台展示）
    String FrontQueryItemsByExceptLabelId(Integer pageSize, Integer offset, String sort, String sortOrder, String queryData, Integer lowPrice, Integer highPrice, Integer cateId);

    //通过name和price和cateId和labelId查询商品（前台展示）
    String FrontQueryItemsByAll(Integer pageSize, Integer offset, String sort, String sortOrder, String queryData, Integer lowPrice, Integer highPrice, Integer cateId, Integer labelId);

    //通过price和labelId查询商品（前台展示）
    String FrontQueryItemsByPriceAndLabelId(Integer pageSize, Integer offset, String sort, String sortOrder, Integer lowPrice, Integer highPrice, Integer labelId);

    //通过price和cateId查询商品（前台展示）
    String FrontQueryItemsByPriceAndCateId(Integer pageSize, Integer offset, String sort, String sortOrder, Integer lowPrice, Integer highPrice, Integer cateId);

    //查询某商品种类的个数
    int QueryItemsCounts(Integer cateId);

    //通过itemid查询商品
    String FrontQueryItemById(Integer itemid);

    //通过itemid查询商品单价
    Float queryItemsPriceByItemId(Integer item_id);
}
