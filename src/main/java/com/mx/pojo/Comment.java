package com.mx.pojo;

import java.io.Serializable;
/**
 *
 * 评论表表实体类
 *
 */
public class Comment implements Serializable {
    private Integer commentId;

    private Integer itemId;

    private String content;

    private static final long serialVersionUID = 1L;

    public Integer getCommentId() {
        return commentId;
    }

    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
}