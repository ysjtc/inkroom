package com.mx.service;

import com.mx.pojo.Vip;

import java.util.List;

public interface VipService {
    public void AddVip(Vip vip);/*添加用户Vip*/
    public Vip queryScore(int id);/*查询单个用户的积分*/
    public List<Vip> getAllscore();/*查询所有用户的积分*/
}
