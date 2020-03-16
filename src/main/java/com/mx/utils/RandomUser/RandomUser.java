package com.mx.utils.RandomUser;/*
@author 郭子雄
@description 生成用户名的工具类
*/


public class RandomUser {

    public static String RandomName(int a){
        Integer number=a+1;
        String name=number.toString().trim();
        return name;
    }
}
