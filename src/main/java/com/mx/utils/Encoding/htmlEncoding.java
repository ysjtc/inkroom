package com.mx.utils.Encoding;/*
@author 郭子雄
@description 解决html实体编码问题
*/

import com.mx.pojo.Address;
import com.mx.pojo.UserData;
import org.apache.commons.lang.StringEscapeUtils;

public class htmlEncoding {
    public static UserData getUserData(UserData userData){
        userData.setTel(StringEscapeUtils.escapeHtml(userData.getTel()));
        userData.setuName(StringEscapeUtils.escapeHtml( userData.getuName()));
       return userData;
    }
    public static Address getAddress(Address address){
        address.setTel(StringEscapeUtils.escapeHtml(address.getTel()));
        address.setName(StringEscapeUtils.escapeHtml(address.getName()));
        address.setProvince(StringEscapeUtils.escapeHtml(address.getProvince()));
        address.setAddr(StringEscapeUtils.escapeHtml(address.getAddr()));
        address.setPostcode(StringEscapeUtils.escapeHtml(address.getPostcode()));
        return address;
    }
}
