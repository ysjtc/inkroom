package com.mx.mapper;

import com.mx.pojo.Logistics;
import org.apache.ibatis.annotations.Param;

public interface LogisticsMapper {

    //向物流表中添加数据
    int addLogistics(Logistics logistics);

    //获取物流表中要退货的id
    Logistics applogIdByOid(@Param("oId") Integer oId);

    //查询发货（退货）物流
    Logistics checkLogistics(@Param("oId") Integer oId);
}