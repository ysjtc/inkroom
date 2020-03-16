package com.mx.utils.Validators;

import java.util.ArrayList;
import java.util.List;

public class FrontItemsUtils {


    public static List isNull(String queryData, String priceRange, Integer cateId, Integer labelId) {
        List list=new ArrayList();
        if (queryData!=null&&!queryData.equals("")){
            list.add("queryData");
        }
        if (priceRange!=null&&!priceRange.equals("")){
            list.add("priceRange");
        }
        if (cateId!=null) {
            list.add("cateId");
        }
        if (labelId!=null){
            list.add("labelId");
        }
        list.add("全为空");
        return list;
    }
}
