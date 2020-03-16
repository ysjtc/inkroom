package com.mx.controller.frontManage;

import com.mx.service.CategoryService;
import com.mx.service.ItemsService;
import com.mx.service.SuperAdminService;
import com.mx.utils.Validators.FrontItemsUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/ItemsFrontManage")
public class ItemsFrontController {

    //自动注入items
    @Autowired
    private ItemsService itemsService;

    //自动注入superadmin
    @Autowired
    private SuperAdminService superAdminService;

    //自动注入Category
    @Autowired
    private CategoryService categoryService;



    //查询所有商品
    @ResponseBody
    @RequestMapping("/query/AllItems")
    public String queryAll(String queryData,Integer pageSize,Integer offset,String sort,String sortOrder,String priceRange,Integer cateId,Integer labelId,HttpSession session){
        System.out.println("queryData:"+queryData+"\tpageSize:"+pageSize+"\toffset:"+offset+"\tsort:"+sort+"\tsortOrder:"+sortOrder+"\tpriceRange:"+priceRange+"\tcateId:"+cateId+"\tlabelId:"+labelId);
        String itemList=null;
        Integer lowPrice=null;
        Integer highPrice=null;
        List FrontList=FrontItemsUtils.isNull(queryData,priceRange,cateId,labelId);
        System.out.println(FrontList);
        try {
            if (!FrontList.get(0).equals("queryData")) {
                //第一个不是priceRange
                if (!FrontList.get(0).equals("priceRange")) {
                    //第一个不是cateId
                    if (!FrontList.get(0).equals("cateId")) {
                        //第一个不是labelID
                        if (!FrontList.get(0).equals("labelId")) {
                            //否则全为空，直接查询所有items
                            itemList = itemsService.FrontQueryAll(pageSize, offset, sort, sortOrder);
                            return itemList;
                        } else {
                            //除labelID不为空，其他均为空，通过labelid查询商品
                            itemList = itemsService.FrontQueryItemsByLabelId(pageSize, offset, sort, sortOrder, labelId);
                            return itemList;
                        }
                    } else {
                        if (!FrontList.get(1).equals("labelId")) {
                            //第一个元素是cateid，没有第二个元素
                            itemList = itemsService.FrontQueryItemsByCateId(pageSize, offset, sort, sortOrder, cateId);
                            return itemList;
                        } else {
                            //第一个元素是cateid,第二个是labelId
                            itemList = itemsService.FrontQueryItemsByCateAndLabelID(pageSize, offset, sort, sortOrder, cateId, labelId);
                            return itemList;
                        }
                    }
                } else {
                    //第一个是priceRange
                    if (!FrontList.get(1).equals("cateId")) {
                        if (!FrontList.get(1).equals("labelId")) {
                            lowPrice = Integer.parseInt(priceRange.substring(0, priceRange.lastIndexOf(";")));
                            highPrice = Integer.parseInt(priceRange.substring(priceRange.lastIndexOf(";") + 1, priceRange.length()));
                            //只有priceRange，所以通过price查询商品
                            itemList = itemsService.FrontQueryItemsByPrice(pageSize, offset, sort, sortOrder, lowPrice, highPrice);
                            return itemList;
                        } else {
                            lowPrice = Integer.parseInt(priceRange.substring(0, priceRange.lastIndexOf(";")));
                            highPrice = Integer.parseInt(priceRange.substring(priceRange.lastIndexOf(";") + 1, priceRange.length()));
                            //通过priceRange和labelId查询商品
                            itemList = itemsService.FrontQueryItemsByPriceAndLabelId(pageSize, offset, sort, sortOrder, lowPrice, highPrice, labelId);
                            return itemList;
                        }
                    } else {
                        //第二个是cateID
                        if (!FrontList.get(2).equals("labelId")) {
                            lowPrice = Integer.parseInt(priceRange.substring(0, priceRange.lastIndexOf(";")));
                            highPrice = Integer.parseInt(priceRange.substring(priceRange.lastIndexOf(";") + 1, priceRange.length()));
                            //第一个元素是priceRange,第二个是cateid,通过priceRange和cateId查询商品
                            itemList = itemsService.FrontQueryItemsByPriceAndCateId(pageSize, offset, sort, sortOrder, lowPrice, highPrice, cateId);
                            return itemList;
                        } else {
                            lowPrice = Integer.parseInt(priceRange.substring(0, priceRange.lastIndexOf(";")));
                            highPrice = Integer.parseInt(priceRange.substring(priceRange.lastIndexOf(";") + 1, priceRange.length()));
                            //第一个元素是priceRange,第二个是cateid,第三个是labelId，
                            itemList = itemsService.FrontQueryItemsByPriceAndCateAndLabelID(pageSize, offset, sort, sortOrder, lowPrice, highPrice, cateId, labelId);
                            return itemList;
                        }
                    }
                }
            } else {
                //第一个是querydata
                if (!FrontList.get(1).equals("priceRange")) {
                    //第二个不是priceRange
                    if (!FrontList.get(1).equals("cateId")) {
                        //第二个不是cateId
                        if (!FrontList.get(1).equals("labelId")) {
                            //说明只存在queryData，所以通过name查询商品
                            itemList = itemsService.FrontQueryItemsByName(pageSize, offset, sort, sortOrder, queryData);
                            return itemList;
                        } else {
                            //第二个是labelId，所以通过name和Labelid查询商品
                            itemList = itemsService.FrontQueryItemsByNameAndLabelId(pageSize, offset, sort, sortOrder, queryData, labelId);
                            return itemList;
                        }
                    } else {
                        //第二个是cateId
                        if (!FrontList.get(2).equals("labelId")) {
                            //第三个不是labelId，所以通过name和cateId查询商品
                            itemList = itemsService.FrontQueryItemsByNameAndCateId(pageSize, offset, sort, sortOrder, queryData, cateId);
                            return itemList;
                        } else {
                            //第三个是labelId，所以通过Name和cateId和LabelId查询商品
                            itemList = itemsService.FrontQueryItemsByNameAndCateIdAndLabelId(pageSize, offset, sort, sortOrder, queryData, cateId, labelId);
                            return itemList;
                        }
                    }
                } else {
                    //第二个是priceRange
                    if (!FrontList.get(2).equals("cateId")) {
                        //第三个不是cateId
                        if (!FrontList.get(2).equals("labelId")) {
                            lowPrice = Integer.parseInt(priceRange.substring(0, priceRange.lastIndexOf(";")));
                            highPrice = Integer.parseInt(priceRange.substring(priceRange.lastIndexOf(";") + 1, priceRange.length()));
                            //第三个不是labelId,通过querydata和priceRange查询商品
                            itemList = itemsService.FrontQueryItemsByNameAndPrice(pageSize, offset, sort, sortOrder, queryData, lowPrice, highPrice);
                            return itemList;
                        } else {
                            lowPrice = Integer.parseInt(priceRange.substring(0, priceRange.lastIndexOf(";")));
                            highPrice = Integer.parseInt(priceRange.substring(priceRange.lastIndexOf(";") + 1, priceRange.length()));
                            //第三个是labelId
                            itemList = itemsService.FrontQueryItemsByNameAndPriceAndLabelId(pageSize, offset, sort, sortOrder, queryData, lowPrice, highPrice, labelId);
                            return itemList;
                        }
                    } else {
                        //第三个是cateID
                        if (!FrontList.get(3).equals("labelId")) {
                            //第四个不是labelid，通过前三个查询商品
                            lowPrice = Integer.parseInt(priceRange.substring(0, priceRange.lastIndexOf(";")));
                            highPrice = Integer.parseInt(priceRange.substring(priceRange.lastIndexOf(";") + 1, priceRange.length()));
                            itemList = itemsService.FrontQueryItemsByExceptLabelId(pageSize, offset, sort, sortOrder, queryData, lowPrice, highPrice, cateId);
                            return itemList;
                        } else {
                            lowPrice = Integer.parseInt(priceRange.substring(0, priceRange.lastIndexOf(";")));
                            highPrice = Integer.parseInt(priceRange.substring(priceRange.lastIndexOf(";") + 1, priceRange.length()));
                            //第四个是labelID
                            itemList = itemsService.FrontQueryItemsByAll(pageSize, offset, sort, sortOrder, queryData, lowPrice, highPrice, cateId, labelId);
                            return itemList;
                        }
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            return "redirect:/frontShow/errorPage/error";
        }
    }

    //查询商品类别
    @ResponseBody
    @RequestMapping("/query/ItemsItemCate")
    public String queryItemsItemCate(){
        String str=categoryService.queryItemsItemCateName();
        System.out.println(str);
        return str;
      }
 //查询商品类别
    @ResponseBody
    @RequestMapping("/query/ItemByID")
    public String queryItemByID(Integer item_id,HttpSession session){
//        System.out.println("---------"+item_id);
        return itemsService.FrontQueryItemById(item_id);
      }


}
