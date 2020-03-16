package com.mx.utils.ConvertJson;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mx.pojo.Item;

import java.io.IOException;

public class JsonUtils {


    /**
     * 将json字符串转换为实体类对象
     *
     * @param json
     * @return
     */
    public static Item JsonToObj( String json) {
        try {
            ObjectMapper mapper = new ObjectMapper();
             Item item = mapper.readValue(json, Item.class);
            return item;
        } catch (com.fasterxml.jackson.core.JsonParseException e) {
            e.printStackTrace();
        } catch (com.fasterxml.jackson.databind.JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
