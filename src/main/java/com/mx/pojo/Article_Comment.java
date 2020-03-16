package com.mx.pojo;

import java.io.Serializable;

/**
 *
 * 文章评论表实体类
 *
 */
public class Article_Comment implements Serializable {
    private Integer acoId;

    private String ACcontent;

    private Integer uId;

    private Integer aId;

    private String createTime;

    private static final long serialVersionUID = 1L;
    private Article article;
    private User user;

    @Override
    public String toString() {
        return "Article_Comment{" +
                "acoId=" + acoId +
                ", ACcontent='" + ACcontent + '\'' +
                ", uId=" + uId +
                ", aId=" + aId +
                ", createTime='" + createTime + '\'' +
                ", articles=" + article +
                ", user=" + user +
                '}';
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public Integer getAcoId() {
        return acoId;
    }

    public void setAcoId(Integer acoId) {
        this.acoId = acoId;
    }

    public String getACcontent() {
        return ACcontent;
    }

    public void setACcontent(String ACcontent) {
        this.ACcontent = ACcontent;
    }

    public Integer getuId() {
        return uId;
    }

    public void setuId(Integer uId) {
        this.uId = uId;
    }

    public Integer getaId() {
        return aId;
    }

    public void setaId(Integer aId) {
        this.aId = aId;
    }


}