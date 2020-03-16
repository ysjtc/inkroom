package com.mx.mapper;

import com.mx.pojo.Logistics_Return;
import org.apache.ibatis.annotations.Param;

public interface Logistics_ReturnMapper {

    ////退换货物流信息
    int createLog(Logistics_Return logistics_return);


    //判断表中是否存在相同的oid
    Logistics_Return repeatApply(@Param("oid") Integer oid);
}