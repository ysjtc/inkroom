package com.mx.utils.ConvertJson;

import net.sf.json.JSONObject;

import java.util.Map;

public class JsonToJsonObject {

    public static  Object ToJsonObject(String json,String param) {
        Map<String, Object> map = JSONObject.fromObject(json);
        Object obj=null;
        for (String data : map.keySet()) {
//            System.out.println("key : "+data.equals("sort")+" value : "+map.get(data));
            if (data.equals(param)) {
                obj=map.get(data);
            }
        }
                return obj;
    }
}
