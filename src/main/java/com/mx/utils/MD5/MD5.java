package com.mx.utils.MD5;/*
@author 郭子雄
@description 用于加密密码的算法
*/

import java.security.MessageDigest;

public class MD5 {
    private static String[] hexDigits = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f" };

    private static String byteArrayToHexString(byte[] b) {
        StringBuffer resultSb = new StringBuffer();
        for (int i = 0; i < b.length; i++) {
            resultSb.append(byteToHexString(b[i]));
        }
        return resultSb.toString();
    }
/*用于截断字符串的函数
* 截断方式为将一个字符串取整等分成四份，前两份交换位置，后两份交换位置
* 例如：
* 字符串： 123456789
* 截断后的字符串： 341278956
* */
    private static String  Rearrage(String origin){
        String newstring="";
        int lengthOne=origin.length()/2;
        int lengthOne1=lengthOne/2;

        int lengthTwo=origin.length()-lengthOne;
        int lengthTwo1=lengthTwo/2;

        newstring=origin.substring(lengthOne1, lengthOne)+origin.substring(0, lengthOne1)
                +origin.substring(lengthOne+lengthTwo1, origin.length())
                +origin.substring(lengthOne,lengthOne+lengthTwo1);
        return newstring;
    }

    private static String byteToHexString(byte b) {
        int n = b;
        if (n < 0) n += 256;
        int d1 = n / 16;
        int d2 = n % 16;
        return hexDigits[d1] + hexDigits[d2];
    }

    public static String MD5Encode(String origin) {
        String resultString = null;
        try {
            /*先将字串截断，再将其转换成32位的数，即得到我们数据库要存放的密码*/
            resultString=Rearrage(origin);
            resultString = new String(origin);
            MessageDigest md = MessageDigest.getInstance("MD5");
            resultString = byteArrayToHexString(md.digest(resultString.getBytes()));
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return resultString;
    }
}

