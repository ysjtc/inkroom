package com.mx.pojo;

import java.io.Serializable;

public class Vip implements Serializable {
    private Integer vipId;

    private Integer score;

    private static final long serialVersionUID = 1L;

    public Integer getVipId() {
        return vipId;
    }

    public void setVipId(Integer vipId) {
        this.vipId = vipId;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }
}