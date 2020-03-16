package com.mx.utils.Validators;

import com.mx.pojo.Item;

import java.lang.reflect.Field;
import java.util.Arrays;

public class IsEmpty {
    /**
     * 判断对象中属性值是否全为空
     *
     * @param object
     * @return
     */
    public static boolean checkIsNull(Object object) {
        boolean flag=true;
        Item item=(Item) object;
        try {
            Class cla=object.getClass();//得到类对象
            Field[] fields=cla.getDeclaredFields();//得到属性集合
            fields= Arrays.copyOfRange(fields,2,fields.length);//得到除了序列号id和类id之外的属性集合
//            System.out.println(" fields[0];"+ fields[0]);
            for (Field fd:fields) {
                fd.setAccessible(true);//设置属性可访问（包括私有的）
                Object value = fd.get(object);//得到属性的值
//                System.out.println("sda"+value);
                if (value != null && !value.equals("")) {
                    flag = false;
                    break;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return flag;
    }

}
