package com.mx.service.Impl;/*
@author 郭子雄
@description VIP的业务实现类
*/

import com.mx.mapper.VipMapper;
import com.mx.pojo.Vip;
import com.mx.service.VipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VipServiceImpl implements VipService {
    @Autowired
    private VipMapper vipMapper;

    @Override
    public void AddVip(Vip vip) {
        vipMapper.insert(vip);
    }

    @Override
    public Vip queryScore(int id) {
        Vip vip=vipMapper.queryScore(id);
        return vip;
    }

    @Override
    public List<Vip> getAllscore() {
        List<Vip> listVip=vipMapper.queryAllvip();
        return listVip;
    }


}
