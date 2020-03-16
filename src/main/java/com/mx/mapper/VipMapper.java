package com.mx.mapper;

import com.mx.pojo.Vip;

import java.util.List;

public interface VipMapper {
    int insert(Vip record);

    int insertSelective(Vip record);

    Vip queryScore(int id);

    List<Vip> queryAllvip();

}