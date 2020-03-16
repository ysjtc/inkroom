package com.mx.service.Impl;/*
@author 郭子雄
@description 收货人业务类
*/

import com.mx.mapper.AddressMapper;
import com.mx.pojo.Address;
import com.mx.pojo.Page;
import com.mx.service.AddressService;
import com.mx.utils.ConvertJson.ConvertJson;
import com.mx.utils.Encoding.htmlEncoding;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    private AddressMapper addressMapper;

    /*获得所有地址*/
    @Override
    public Map getAlladdress(int uId, Page page) {
        Map map =new HashMap();
        List<Address> list=addressMapper.selectAddress(page.getOffset(),
                page.getPageSize(),page.getSort(),page.getSortOrder(),uId);
        int total=addressMapper.getAlladdress(uId);
        List<Address> newList=new ArrayList<Address>();
        for(int i=0;i<list.size();i++){
            newList.add(htmlEncoding.getAddress(list.get(i)));
        }
        map.put("total",total);
        map.put("rows",newList);
        return map;
    }

    /*添加地址*/
    @Override
    public void Addaddress(Address address) {
        addressMapper.insertSelective(address);
    }

    /*删除地址*/
    @Override
    public void Clearaddress(int addId) {
        addressMapper.deleteAddress(addId);
    }

    /*更新地址*/
    @Override
    public void updateaddress(Address address) {
        addressMapper.updateAddress(address);
    }

    @Override
    public String getAddress(Integer uid) {
        String str=null;
        try {
            int count=addressMapper.queryAddressCount(uid);
            if (count==0){
                return "{\"result\":false}";
            }
            List<Address> addressList=addressMapper.getAddress(uid);
            System.out.println(addressList);
            str= ConvertJson.ConvertAddress(addressList);
            System.out.println(str);
            str=str+"true";
        }catch (Exception e){
            e.printStackTrace();
            str=str+"false";
        }
        str=str+"}";
        return str;
    }
}
