package com.mx.pojo;

import java.io.Serializable;
/**
 *
 * 评论图片路径实体类
 *
 */
public class Comment_Pic implements Serializable {
    private Integer commentId;

    private String picPath;

    private static final long serialVersionUID = 1L;

    public Integer getCommentId() {
        return commentId;
    }

    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }

    public String getPicPath() {
        return picPath;
    }

    public void setPicPath(String picPath) {
        this.picPath = picPath == null ? null : picPath.trim();
    }
}