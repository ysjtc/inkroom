package com.mx.controller.backMange;

import com.mx.pojo.Category;
import com.mx.pojo.Item;
import com.mx.pojo.Item_Pic;
import com.mx.service.CategoryService;
import com.mx.service.ItemPicService;
import com.mx.service.ItemsService;
import com.mx.service.SuperAdminService;
import com.mx.utils.ConvertJson.JsonToJsonObject;
import com.mx.utils.UpLoad.Upload;
import com.mx.utils.Validators.IsEmpty;
import com.mx.utils.Validators.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/ItemsBackManage")
public class ItemsBackManageController {

    //自动注入items
    @Autowired
    private ItemsService itemsService;

    //自动注入superadmin
    @Autowired
    private SuperAdminService superAdminService;

    //自动注入Category
    @Autowired
    private CategoryService categoryService;

    //自动注入Itempic
    @Autowired
    private ItemPicService itemPicService;


      @ResponseBody
      @RequestMapping("/query/itemById")
      public String itemByID(Integer itemID){
          String itemPath=itemsService.queryItemById(itemID);
          System.out.println(itemPath);
          return itemPath;
      }
    //添加商品
    @ResponseBody
    @RequestMapping("/ToAdd")
    public String add(@RequestParam(value = "itemPic")MultipartFile[] multipartFiles, @Valid Item item, BindingResult result, HttpSession session, HttpServletRequest request, Category itemCate, Model model){
        item.setCateId(itemCate);
        System.out.println(multipartFiles.length);
//        System.out.println(itemCate);
//        System.out.println(item+"-----------------");
        String truejson="{\"result\": true }";
        String falsejson="{\"result\":false}";
        //获取当前登陆的管理员
        Object superID=session.getAttribute("SUPERADMIN_ID");
        if (superID==null||superID.equals("")){
//            System.out.println("superID="+superID);
            return "redirect:/SuperAdmin/ToLogin";
        }else {
            //判断传入的数据是否为null
            if (Validator.checkErrors(result, session)) {
                //判断是否存在的商品类型
                List<Integer> l = categoryService.querycid();
                //存在商品类型
                if (l.contains(item.getCateId())) {
                    //判断添加数据是否成功
                    boolean additems = itemsService.addItems(item);
                    //上传图片
                    String path=request.getServletContext().getRealPath("/static/upload/images")+ File.separator;
                    Upload.upLoads(multipartFiles,session,path);
                    //获取图片的相对地址
                    String ItemPicPath="";
                    //将所有的‘\’替换成‘/’
                    String newItemPicPath="";
                    //商品路径添加
                    boolean addItemPic=false;
                    Item_Pic ip=new Item_Pic();
                    ip.setItemId(item.getItemId());
                    List<String> itemPicPath=(ArrayList)session.getAttribute("itemPicturePath");
                    //如果每个商品存在多张图片。那么将图片的相对地址存在一个list，然后便利list，将地址分别以相同的itemid存入数据库
                    for (int i=0;i<itemPicPath.size();i++){
                        //获取图片的相对地址
                        ItemPicPath= "static/upload/images"+File.separator+itemPicPath.get(i);
                        //将所有的‘\’替换成‘/’
                        newItemPicPath=ItemPicPath.replaceAll("\\\\","/");
                        ip.setPicPath(newItemPicPath);
                        //存入数据库
                        addItemPic=itemPicService.addItemsPic(ip);
                    }
//                    System.out.println(additems&&addItemPic);
                        //成功
                        if (additems&&addItemPic) {
                            model.addAttribute("addInfo", "商品添加成功");
                            return truejson;
                        //失败
                        }else{
                            model.addAttribute("addInfo", "商品添加失败");
                            return falsejson;
                        }
                    //不存在商品类型
                    }else {
                        model.addAttribute("addInfo", "商品类别不存在，添加失败");
                        return falsejson;
                    }
                //传入的参数有null
                } else {
                    model.addAttribute("addInfo", "商品添加失败");
                    return falsejson;
                }
            }

    }


    //查询所有商品
    @ResponseBody
    @RequestMapping("/query/AllItems")
    public String queryAll(@RequestBody String param,HttpSession session){
        System.out.println(param);
        try{
            String pageSize=String.valueOf(JsonToJsonObject.ToJsonObject(param,"pageSize"));
            String offset=String.valueOf(JsonToJsonObject.ToJsonObject(param,"offset"));
            String sort=String.valueOf(JsonToJsonObject.ToJsonObject(param,"sort"));
            String sortOrder=String.valueOf(JsonToJsonObject.ToJsonObject(param,"sortOrder"));
            String itemList = itemsService.queryAll(Integer.parseInt(pageSize),Integer.parseInt(offset),sort,sortOrder);
//        System.out.println(itemList.getName());
//        System.out.println(itemList);
            session.setAttribute("items",itemList);
            return itemList;
        }catch (Exception e){
            e.printStackTrace();
            return "{\"result\":false}";
        }

    }

    //通过商品类别查询
    @ResponseBody
    @RequestMapping("/query/ItemsByItemCate")
    public String queryItemsByItemCate(@RequestBody String param, Model model,HttpSession session){
//        System.out.println(param+"-------------");
        //将json字符串转成json对象后遍历键值对
        String truejson="{\"result\":true}";
        String falsejson="{\"result\":false}";
        String pageSize=String.valueOf(JsonToJsonObject.ToJsonObject(param,"pageSize"));
        String offset=String.valueOf(JsonToJsonObject.ToJsonObject(param,"offset"));
        String sort=String.valueOf(JsonToJsonObject.ToJsonObject(param,"sort"));
        String sortOrder=String.valueOf(JsonToJsonObject.ToJsonObject(param,"sortOrder"));
        String queryData=String.valueOf(JsonToJsonObject.ToJsonObject(param,"queryData"));
        String itemCate=String.valueOf(JsonToJsonObject.ToJsonObject(queryData,"itemCate"));
        System.out.println(Integer.parseInt(itemCate));
        if (itemCate!=null&&!itemCate.equals("")){
            String itemList = itemsService.queryItemsByItemCate(Integer.parseInt(pageSize),Integer.parseInt(offset),sort,sortOrder,Integer.parseInt(itemCate));
            session.setAttribute("itemList",itemList);
            return itemList;
        }else {
            model.addAttribute("queryErr","ItemCate=空");
            return  falsejson;
        }
      }

    //通过价格查询商品
    @ResponseBody
    @RequestMapping("/query/ItemsByPrice")
    public String QueryItemsBetweenPrice( @RequestBody String param, HttpSession session, Model model){
        System.out.println(param);
        String truejson="{\"result\":true}";
        String falsejson="{\"result\":false}";
        //将json字符串转成json对象后遍历键值对
        String pageSize=String.valueOf(JsonToJsonObject.ToJsonObject(param,"pageSize"));
        String offset=String.valueOf(JsonToJsonObject.ToJsonObject(param,"offset"));
        String sort=String.valueOf(JsonToJsonObject.ToJsonObject(param,"sort"));
        String sortOrder=String.valueOf(JsonToJsonObject.ToJsonObject(param,"sortOrder"));
        String queryData=String.valueOf(JsonToJsonObject.ToJsonObject(param,"queryData"));
        String lowPrice=String.valueOf(JsonToJsonObject.ToJsonObject(queryData,"lowPrice"));
        String highPrice=String.valueOf(JsonToJsonObject.ToJsonObject(queryData,"highPrice"));
        System.out.println("lowPrice:"+lowPrice+"highPrice:"+highPrice);
        if (lowPrice!=null&&!lowPrice.equals("")&&highPrice!=null&&!highPrice.equals("")){
            String itemList=itemsService.queryItemsByPrice(Integer.parseInt(pageSize),Integer.parseInt(offset),sort,sortOrder,Integer.parseInt(lowPrice),Integer.parseInt(highPrice));
            session.setAttribute("itemList",itemList);
            return itemList;
        }else {
            model.addAttribute("queryErr","price="+lowPrice+highPrice);
            return falsejson;
        }
    }

    //通过ISBN查询商品
    @RequestMapping("/query/ToQueryItemsByIsbn")
    public String toQuery(@RequestBody String param){
//        System.out.println(param);
        //将json字符串转成json对象后遍历键值对
        String pageSize=String.valueOf(JsonToJsonObject.ToJsonObject(param,"pageSize"));
        String offset=String.valueOf(JsonToJsonObject.ToJsonObject(param,"offset"));
        String sort=String.valueOf(JsonToJsonObject.ToJsonObject(param,"sort"));
        String sortOrder=String.valueOf(JsonToJsonObject.ToJsonObject(param,"sortOrder"));
        String queryData=String.valueOf(JsonToJsonObject.ToJsonObject(param,"queryData"));
        String isbn=String.valueOf(JsonToJsonObject.ToJsonObject(queryData,"isbn"));
        return "redirect:/Items/query/ItemsByISBN/"+pageSize+"/"+offset+"/"+sort+"/"+sortOrder+"/"+isbn;
    }

    @RequestMapping("/query/ItemsByISBN/{pageSize}/{offset}/{sort}/{sortOrder}/{isbn}")
    @ResponseBody
    public  String queryItemsByISBN(@PathVariable("pageSize")String pageSize,@PathVariable("offset")String offset,@PathVariable("sort")String sort,@PathVariable("sortOrder")String sortOrder,@PathVariable("isbn") String isbn,HttpSession session,Model model){
//        System.out.println("isbn"+isbn);
        String truejson="{\"result\":true}";
        String falsejson="{\"result\":false}";
        if (isbn!=null&&!isbn.equals("")){
            String itemList = itemsService.queryItemsByISBN(Integer.parseInt(pageSize),Integer.parseInt(offset),sort,sortOrder,isbn);
            System.out.println(itemList);
            session.setAttribute("itemList",itemList);
            return itemList;
        }else {
            model.addAttribute("queryErr","ISBN=空");
            return falsejson;
        }
    }

    //通过商品名称查询商品（模糊查询）
    @ResponseBody
    @RequestMapping("/query/ItemsByName")
    public String queryItemsByName(@RequestBody String param, Model model,HttpSession session){
//        System.out.println(param+"-------------");
        String truejson="{\"result\":true}";
        String falsejson="{\"result\":false}";
        //将json字符串转成json对象后遍历键值对
        String pageSize=String.valueOf(JsonToJsonObject.ToJsonObject(param,"pageSize"));
        String offset=String.valueOf(JsonToJsonObject.ToJsonObject(param,"offset"));
        String sort=String.valueOf(JsonToJsonObject.ToJsonObject(param,"sort"));
        String sortOrder=String.valueOf(JsonToJsonObject.ToJsonObject(param,"sortOrder"));
        String queryData=String.valueOf(JsonToJsonObject.ToJsonObject(param,"queryData"));
        String bookname=String.valueOf(JsonToJsonObject.ToJsonObject(queryData,"bookname"));
        if (bookname!=null&&!bookname.equals("")){
            String itemList = itemsService.queryItemsByName(Integer.parseInt(pageSize),Integer.parseInt(offset),sort,sortOrder,bookname);
//            System.out.println(itemList);
            session.setAttribute("itemList",itemList);
            return itemList;
        }else {
            model.addAttribute("queryErr","name=空");
            return falsejson;
        }
    }

    //通过商品名删除商品
    @ResponseBody
    @RequestMapping("/deleteItem")
    public String deleteItemById(Integer itemId,Model model,HttpSession session ){
//        System.out.println(itemId+"--------------");
        String truejson="{\"result\":true}";
        String falsejson="{\"result\":false}";
        Object obj=session.getAttribute("SUPERADMIN_ID");
        if (obj==null||obj.equals("")){
            System.out.println("当前没有管理员登陆");
            //存储错误信息
            model.addAttribute("loginErr", "请先登录");
            return "backManage/login/login";
        }else {
            if (itemId != null && !itemId.equals("")) {
                boolean is = itemsService.deleteItem(itemId);
                if (is) {
                    model.addAttribute("deleteInfo", "删除成功!");
                    return truejson;
                } else {
                    model.addAttribute("deleteInfo", "删除失败!");
                    return falsejson;
                }
            } else
                return falsejson;
        }
    }


//    @RequestMapping("/toUpdate")
//    public String toUpdate(HttpSession session,Integer id,Model model){
//        //先判断是否又管理员登陆，在进行更新
//        Object obj=session.getAttribute("SUPERADMIN_ID");
//        if (obj==null||obj.equals("")){
//            System.out.println("当前没有管理员登陆");
//            //存储错误信息
//            model.addAttribute("loginErr", "请先登录");
//            return "backManage/login/login";
//        }else
//             return "redirect:/Items/updateItem"+id;
//    }

    //修改商品信息
    @ResponseBody
    @RequestMapping("/updateItem")
    public String updateItemByName(@RequestParam(value = "picFiles")MultipartFile[] multipartFiles, HttpSession session, Integer[] delPicId, Item item, Model model, HttpServletRequest request ){
        System.out.println("item是不是空的:"+ IsEmpty.checkIsNull(item));
//        System.out.println("+++" + multipartFiles.length);
//        System.out.println("+++" + delPicId.length);
        String truejson="{\"result\": true }";
        String falsejson="{\"result\":false}";
        //先判断是否有管理员登陆，在进行更新
        Object obj=session.getAttribute("SUPERADMIN_ID");
        if (obj==null||obj.equals("")){
            System.out.println("当前没有管理员登陆");
            //存储错误信息
            model.addAttribute("loginErr", "请先登录");
            return "{\"result\":false,\"isLogin\":false}";
        }else {
            if (multipartFiles.length == 0) {
                if (delPicId.length==0){
                    if (IsEmpty.checkIsNull(item)){
                        return falsejson;
                    }else {
                        boolean updateData = itemsService.updateItemsInfo(item);
                        System.out.println(updateData);
                        if (updateData) {
                            return truejson;
                        } else
                            return falsejson;
                    }
                }else {
                    boolean deletepic=false;
                    if (IsEmpty.checkIsNull(item)) {
                        for(int i=0;i<delPicId.length;i++){
                            deletepic=itemPicService.deletePic(delPicId[i]);
                        }
                        if (deletepic) {
                            return truejson;
                        } else
                            return falsejson;
                    } else {
                        for(int i=0;i<delPicId.length;i++){
                            deletepic=itemPicService.deletePic(delPicId[i]);
                        }
                            boolean updateData = itemsService.updateItemsInfo(item);
                            if (updateData && deletepic) {
                                return truejson;
                            } else
                                return falsejson;
                        }
                    }
            } else {
//            System.out.println(delPicId+"------"+multipartFiles);
                boolean updateData=true;
                boolean deletepic=false;
                System.out.println(delPicId.length);
                if (!IsEmpty.checkIsNull(item)){
                   updateData =itemsService.updateItemsInfo(item);
                }
                if (delPicId.length==0){
                    deletepic=true;
                }
                for(int i=0;i<delPicId.length;i++){
                    deletepic=itemPicService.deletePic(delPicId[i]);
                }
                //上传图片
                String path = request.getServletContext().getRealPath("/static/upload/images") + File.separator;
                Upload.upLoads(multipartFiles, session, path);
                //获取图片的相对地址
                String ItemPicPath = null;
                //将所有的‘\’替换成‘/’
                String newItemPicPath = null;
                //商品路径添加
                boolean addItemPic = false;
                Item_Pic ipic = new Item_Pic();
                ipic.setItemId(item.getItemId());

                List<String> itemPicPath = (ArrayList) session.getAttribute("itemPicturePath");
                //如果每个商品存在多张图片。那么将图片的相对地址存在一个list，然后便利list，将地址分别以相同的itemid存入数据库
                for (int i = 0; i < itemPicPath.size(); i++) {
                    //获取图片的相对地址
                    ItemPicPath = "static/upload/images" + File.separator + itemPicPath.get(i);
                    //将所有的‘\’替换成‘/’
                    newItemPicPath = ItemPicPath.replaceAll("\\\\", "/");
                    ipic.setPicPath(newItemPicPath);
                    System.out.println("ip++++++" + ipic);
                    //存入数据库
                    addItemPic = itemPicService.addItemsPic(ipic);
                }
                System.out.println(updateData&&addItemPic&&deletepic);
                if (updateData&&addItemPic&&deletepic) {
                    model.addAttribute("deleteinfo", "更新成功!");
                    return truejson;
                } else {

                    model.addAttribute("deleteinfo", "更新失败!");
                    return falsejson;
                }
            }
        }
    }


    //通过itemid查询商品路径及id
    @ResponseBody
    @RequestMapping("/query/getPicPath")
    public String getpicpath(Integer itemID){
        String falsejson="{\"result\":false}";
          if (itemID!=null){
            return itemPicService.queryitemPicPaths(itemID);
          }else
              return falsejson;
    }


    @ResponseBody
    @RequestMapping(value = {"/addCateName"},method = RequestMethod.POST)
    public String addCateName(String cateName){
        String truejson="{\"result\":true}";
        String falsejson="{\"result\":false}";
          if (cateName!=null&&!cateName.equals("")){
              boolean addcate=categoryService.addCategory(cateName);
              if (addcate){
                  return truejson;
              }else return falsejson;
          }else
          return falsejson;
    }
}
