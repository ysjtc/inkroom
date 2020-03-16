package com.mx.mapper;

import com.mx.pojo.Item;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ItemMapper {

    //添加商品
    int addItems(Item item);

    //通过name查询商品
    List<Item> queryItemsByName(@Param("pageSize")int pageSize,@Param("offset") int offset,@Param("sort") String sort,@Param("sortOrder") String sortOrder,@Param("name") String name);

    //通过name查询商品记录数
    int queryItemsByNameCount(@Param("name") String name);

    //通过ISBN查询商品
    List<Item> queryItemsByISBN(@Param("pageSize")int pageSize,@Param("offset") int offset,@Param("sort") String sort,@Param("sortOrder") String sortOrder,@Param("isbn") String isbn);

    //通过ISBN查询商品记录数
    int queryItemsByISBNCount(@Param("isbn") String isbn);

    //查询全部商品
    List<Item>  queryAll(@Param("pageSize")int pageSize,@Param("offset") int offset,@Param("sort") String sort,@Param("sortOrder") String sortOrder);

    //查询商品总记录数
    int queryItemsCount();

    //删除单个商品
    boolean deleteItem(@Param("itemId") Integer itemId);

    //更新商品信息
    boolean updateItemsInfo(Item item);

    //通过价格区间查询商品信息
    List<Item> queryItemsByPrice(@Param("pageSize")int pageSize,@Param("offset") int offset,@Param("sort") String sort,@Param("sortOrder") String sortOrder,@Param("lowPrice") Integer lowPrice, @Param("highPrice") Integer highPrice);

    //通过价格区间查询商品信息个数
    int queryItemsByPriceCount(@Param("lowPrice") Integer lowPrice, @Param("highPrice") Integer highPrice);

    //通过商品类别查询商品
    List<Item> queryItemsByItemCate(@Param("pageSize")int pageSize,@Param("offset") int offset,@Param("sort") String sort,@Param("sortOrder") String sortOrder,@Param("itemCate")Integer itemCate);

    //通过商品类别查询商品个数
    int queryItemsByItemCateCount(@Param("itemCate")Integer itemCate);

    //通过itemId查询商品
    List<Item> queryItemsById(@Param("itemId") Integer itemId);

    //前台展示

    //通过price和cateId和labelid查询商品
    List<Item> queryItemsByPriceAndCateAndLabelID(@Param("pageSize")int pageSize,@Param("offset") int offset,@Param("sort") String sort,@Param("sortOrder") String sortOrder, @Param("lowPrice") Integer lowPrice,@Param("highPrice") Integer highPrice, @Param("cateId") Integer cateId,@Param("labelId") Integer labelId);

    //通过price和cateId和labelid查询商品个数
    int queryItemsByPriceAndCateAndLabelIDCount( @Param("lowPrice") Integer lowPrice,@Param("highPrice") Integer highPrice, @Param("cateId") Integer cateId,@Param("labelId") Integer labelId);

    //通过labelid查询商品
    List<Item> queryItemsByLabelId(@Param("pageSize")int pageSize,@Param("offset") int offset,@Param("sort") String sort,@Param("sortOrder") String sortOrder,@Param("labelId") Integer labelId);

    //通过labelid查询商品个数
    int queryItemsByLabelIdCount(@Param("labelId")Integer labelId);

    //通过cateId和labelid查询商品
    List<Item> queryItemsByCateAndLabelID(@Param("pageSize")int pageSize,@Param("offset") int offset,@Param("sort") String sort,@Param("sortOrder") String sortOrder, @Param("cateId") Integer cateId,@Param("labelId") Integer labelId);

    //通过cateId和labelid查询商品个数
    int queryItemsByCateAndLabelIDCount( @Param("cateId") Integer cateId,@Param("labelId") Integer labelId);

    //通过name和labelid查询商品
    List<Item> queryItemsByNameAndLabelId(@Param("pageSize")int pageSize,@Param("offset") int offset,@Param("sort") String sort,@Param("sortOrder") String sortOrder, @Param("queryData") String queryData, @Param("labelId") Integer labelId);

    //通过name和labelid查询商品个数
    int queryItemsByNameAndLabelIdCount( @Param("queryData") String queryData, @Param("labelId") Integer labelId);

    //通过name和Cateid查询商品
    List<Item> queryItemsByNameAndCateId(@Param("pageSize")int pageSize,@Param("offset") int offset,@Param("sort") String sort,@Param("sortOrder") String sortOrder, @Param("queryData") String queryData,@Param("cateId") Integer cateId);

    //通过name和Cateid查询商品个数
    int queryItemsByNameAndCateIdCount( @Param("queryData") String queryData,@Param("cateId") Integer cateId);

    //通过name和Cateid和labelId查询商品
    List<Item> queryItemsByNameAndCateIdAndLabelId(@Param("pageSize")int pageSize,@Param("offset") int offset,@Param("sort") String sort,@Param("sortOrder") String sortOrder,@Param("queryData") String queryData,@Param("cateId") Integer cateId,@Param("labelId") Integer labelId);

    //通过name和Cateid和labelId查询商品个数
    int queryItemsByNameAndCateIdAndLabelIdCount(@Param("queryData") String queryData,@Param("cateId") Integer cateId,@Param("labelId") Integer labelId);

    //通过name和价格区间查询商品
    List<Item> queryItemsByNameAndPrice(@Param("pageSize")int pageSize,@Param("offset") int offset,@Param("sort") String sort,@Param("sortOrder") String sortOrder,@Param("queryData") String queryData,@Param("lowPrice") Integer lowPrice, @Param("highPrice")Integer highPrice);

    //通过name和价格区间查询商品个数
    int queryItemsByNameAndPriceCount(@Param("queryData") String queryData,@Param("lowPrice") Integer lowPrice, @Param("highPrice")Integer highPrice);

    //通过name和价格区间和labelid查询商品
    List<Item> queryItemsByNameAndPriceAndLabelId(@Param("pageSize")int pageSize,@Param("offset") int offset,@Param("sort") String sort,@Param("sortOrder") String sortOrder,@Param("queryData") String queryData,@Param("lowPrice") Integer lowPrice, @Param("highPrice")Integer highPrice, @Param("labelId") Integer labelId);

    //通过name和价格区间和labelid查询商品个数
    int queryItemsByNameAndPriceAndLabelIdCount(@Param("queryData") String queryData,@Param("lowPrice") Integer lowPrice, @Param("highPrice")Integer highPrice, @Param("labelId") Integer labelId);

    //通过name和价格区间和Cateid查询商品
    List<Item> queryItemsByExceptLabelId(@Param("pageSize")int pageSize,@Param("offset") int offset,@Param("sort") String sort,@Param("sortOrder") String sortOrder,@Param("queryData") String queryData,@Param("lowPrice") Integer lowPrice, @Param("highPrice")Integer highPrice,@Param("cateId") Integer cateId);

    //通过name和价格区间和Cateid查询商品个数
    int queryItemsByExceptLabelIdCount(@Param("queryData") String queryData,@Param("lowPrice") Integer lowPrice, @Param("highPrice")Integer highPrice,@Param("cateId") Integer cateId);

    //通过所有查询商品
    List<Item> queryItemsByAll(@Param("pageSize")int pageSize,@Param("offset") int offset,@Param("sort") String sort,@Param("sortOrder") String sortOrder,@Param("queryData") String queryData,@Param("lowPrice") Integer lowPrice, @Param("highPrice")Integer highPrice,@Param("cateId") Integer cateId,@Param("labelId") Integer labelId);

    //通过所有查询商品个数
    int queryItemsByAllCount(@Param("queryData") String queryData,@Param("lowPrice") Integer lowPrice, @Param("highPrice")Integer highPrice,@Param("cateId") Integer cateId,@Param("labelId") Integer labelId);

    //通过价格区间和labelId查询商品个数
    List<Item> queryItemsByPriceAndLabelId(@Param("pageSize")int pageSize,@Param("offset") int offset,@Param("sort") String sort,@Param("sortOrder") String sortOrder,@Param("lowPrice") Integer lowPrice, @Param("highPrice")Integer highPrice,@Param("labelId") Integer labelId);

    //通过价格区间和labelId查询商品个数
    int queryItemsByPriceAndLabelIdCount(@Param("lowPrice") Integer lowPrice, @Param("highPrice")Integer highPrice,@Param("labelId") Integer labelId);

    //通过价格区间和CateId查询商品个数
    List<Item> queryItemsByPriceAndCateId(@Param("pageSize")int pageSize,@Param("offset") int offset,@Param("sort") String sort,@Param("sortOrder") String sortOrder,@Param("lowPrice") Integer lowPrice, @Param("highPrice")Integer highPrice,@Param("cateId") Integer cateId);

    //通过价格区间和CateId查询商品个数
    int queryItemsByPriceAndCateIdCount(@Param("lowPrice") Integer lowPrice, @Param("highPrice")Integer highPrice,@Param("cateId") Integer cateId);

    //查询某商品种类的个数
    int QueryItemsCounts(@Param("cateId") Integer cateId);

    //通过itemid查询商品
    List<Item> FrontQueryItemById(@Param("itemId") Integer itemId);

    Float queryItemsPriceByItemId(@Param("item_id") Integer item_id);
}