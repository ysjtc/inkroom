package com.mx.service;

import com.mx.pojo.Address;
import com.mx.pojo.Page;

import java.util.Map;

public interface AddressService {
    /*获取所有收货人信息*/
    public Map getAlladdress(int uId, Page page);

    /*添加收货人信息*/
    public void Addaddress(Address address);

    /*删除收货人信息*/
    public void  Clearaddress(int addId);

    /*修改收货人信息*/
    public void  updateaddress(Address address);


    //获取收件人地址
    String getAddress(Integer uid);

}
