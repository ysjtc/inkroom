package com.mx.utils.ConvertJson;
import com.mx.pojo.*;

import java.util.List;

public class ConvertJson {

    public static String Convert(int count,List<Item> list) {
        //定义一个StringBuilder
        StringBuilder jsonStrAll = new StringBuilder("{");
        String str=null;
        jsonStrAll.append("\""+"total"+"\""+":"+count+","+"\""+
                "rows"+"\""+":[");
            for (int i=0;i<list.size();i++){
            //把你要拼接的字段放进去
            jsonStrAll.append(list.get(i).toJson() +",");
             }
        //把最后的，（逗号）截取掉
        str = jsonStrAll.substring(0, jsonStrAll.length()-1)+"]}";
//        System.out.println(str);
        return str;
    }

    //获取item表中除itemid外的所有字段
    public static String UpdateConvertItem(List<Item> list) {
        //定义一个StringBuilder
        StringBuilder jsonStrAll = new StringBuilder("{");
        String str=null;
            for (int i=0;i<list.size();i++){
            //把你要拼接的字段放进去
            jsonStrAll.append(list.get(i).UpdateToJson() +",");
        }
        //把最后的，（逗号）截取掉
        str = jsonStrAll.substring(0, jsonStrAll.length()-1);
        System.out.println("UpdateConvertItem----"+str);
        return str;
    }

    //将上面的item数据与获取的itempic拼接成json字符串
    public static String UpdateConvertPath(String item,List<Item_Pic> list) {
        //定义一个StringBuilder
        StringBuilder jsonStrAll = new StringBuilder();
        String str=null;
        jsonStrAll.append(item+"\""+ "item_pic"+"\""+":[");
            for (int i=0;i<list.size();i++){
            //把你要拼接的字段放进去
            jsonStrAll.append("\""+list.get(i).getPicPath() +"\""+",");
        }
        //把最后的，（逗号）截取掉
        str = jsonStrAll.substring(0, jsonStrAll.length()-1)+"]}";
        System.out.println("item+path:"+str);
        return str;
    }


  //将获取的itempic拼接成json字符串
    public static String ConvertPicPath(List<Item_Pic> list) {
        //定义一个StringBuilder
        StringBuilder jsonStrAll = new StringBuilder("{");
        String str=null;
//        jsonStrAll.append();
            for (int i=0;i<list.size();i++){
            //把你要拼接的字段放进去
            jsonStrAll.append("\""+ list.get(i).getIpId()+"\""+":"+"\""+list.get(i).getPicPath() +"\""+",");
        }
        //把最后的，（逗号）截取掉
        str = jsonStrAll.substring(0, jsonStrAll.length()-1)+"}";
//        System.out.println("itemPath:"+str);
        return str;
    }


    //分页
    public static int getIndex(int pageNum,int pageSize){
        return (pageNum>0)? (pageNum-1)*pageSize:0;
    }


    //获取item表中除itemid外的所有字段
    public static String FrontConvertItem(List<Item> list) {
        //定义一个StringBuilder
        StringBuilder jsonStrAll = new StringBuilder("{");
        String str=null;
        for (int i=0;i<list.size();i++){
            //把你要拼接的字段放进去
            jsonStrAll.append(list.get(i).FrontToJson() +",");
        }
        //把最后的，（逗号）截取掉
        str = jsonStrAll.substring(0, jsonStrAll.length()-1);
        System.out.println("ConvertItem----"+str);
        return str;
    }


    //前台展示
    public static String ConvertOrder(int count,List<Order> list) {
        //定义一个StringBuilder
        StringBuilder jsonStrAll = new StringBuilder("{");
        String str=null;
        jsonStrAll.append("\""+"total"+"\""+":"+count+","+"\""+
                "rows"+"\""+":[");
        for (int i=0;i<list.size();i++){
            //把你要拼接的字段放进去"\""+"ItemName"+"\":"+"\""+itemName.getName()+"("+order_detail.getCount()+") \","+
            jsonStrAll.append(list.get(i).TOJSON()+ ",");
        }
        //把最后的，（逗号）截取掉
        str = jsonStrAll.substring(0, jsonStrAll.length()-1)+"]}";

//        System.out.println(str);
        return str;
    }

    public static String ConvertAddress(List<Address> addressList) {
        //定义一个StringBuilder
        StringBuilder jsonStrAll = new StringBuilder("{");
        String str=null;
        for (int i=0;i<addressList.size();i++){
            //把你要拼接的字段放进去
            jsonStrAll.append("\""+addressList.get(i).getAddId()+"\":[\""+addressList.get(i).getName()+"\",\""+addressList.get(i).getAddr().substring(0,3)+"..."+addressList.get(i).getAddr().substring(addressList.get(i).getAddr().length()-6,addressList.get(i).getAddr().length())+"\"],");
        }

        str = jsonStrAll+"\"result\":";
//        System.out.println(str);
        return str;
    }





    //后台管理
    public static String ConvertOrderBackManage(int count,List<Order> list,String what) {
        //定义一个StringBuilder
        StringBuilder jsonStrAll = new StringBuilder("{");
        String str=null;
        jsonStrAll.append("\""+"total"+"\""+":"+count+","+"\""+
                "rows"+"\""+":[");
        if (what.equals("queryOrder")){
            for (int i=0;i<list.size();i++){
                //把你要拼接的字段放进去
                jsonStrAll.append(list.get(i).TOJSONBackManage() +",");
            }
        }else if (what.equals("queryOrderReturn")){
            for (int i=0;i<list.size();i++){
                //把你要拼接的字段放进去
                jsonStrAll.append(list.get(i).TOJSONBackManageReturn() +",");
            }
        }else {
            return "\"result\";false";
        }

        //把最后的，（逗号）截取掉
        str = jsonStrAll.substring(0, jsonStrAll.length()-1)+"]}";
//        System.out.println(str);
        return str;
    }

    public static String ConvertCart(int count, List<Cart> cartlist) {
        //定义一个StringBuilder
        StringBuilder jsonStrAll = new StringBuilder("{");
        String str=null;
        jsonStrAll.append("\"result\":true,"+"\"rows"+"\""+":[");
        for (int i=0;i<cartlist.size();i++){
            //把你要拼接的字段放进去
            jsonStrAll.append(cartlist.get(i).TOJSONFrontManage() +",");
        }
        //把最后的，（逗号）截取掉
        str = jsonStrAll.substring(0, jsonStrAll.length()-1)+"]}";
//        System.out.println(str);
        return str;
    }
}
