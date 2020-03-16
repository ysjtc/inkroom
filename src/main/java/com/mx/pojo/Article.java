package com.mx.pojo;

import java.io.Serializable;
import java.util.List;
/**
 *
 * 文章表实体类
 *
 */
public class Article implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer aId;

    private Article_Category article_category;    //类型

    private String title;

    private Integer pageView;

    private Integer uId;

    private String publishTime;   //发布时间

    private Integer praiseCount;   //点赞数


    private String content;     //内容

    private List<Article_Comment> article_comments;  //评论

    private User user;

    public Integer getaId() {
        return aId;
    }

    public void setaId(Integer aId) {
        this.aId = aId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getPageView() {
        return pageView;
    }

    public void setPageView(Integer pageView) {
        this.pageView = pageView;
    }

    public Integer getuId() {
        return uId;
    }

    public void setuId(Integer uId) {
        this.uId = uId;
    }

    public String getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(String publishTime) {
        this.publishTime = publishTime;
    }

    public Integer getPraiseCount() {
        return praiseCount;
    }

    public void setPraiseCount(Integer praiseCount) {
        this.praiseCount = praiseCount;
    }

    public Article_Category getArticle_category() {
        return article_category;
    }

    public void setArticle_category(Article_Category article_category) {
        this.article_category = article_category;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<Article_Comment> getArticle_comments() {
        return article_comments;
    }

    public void setArticle_comments(List<Article_Comment> article_comments) {
        this.article_comments = article_comments;
    }

    public User getUser() {
        return user;
    }

    public void setuUser(User user) {
        this.user = user;
    }

    //手动拼json{"key":"value","key2":"value2"}
    public String TOjson(){
        return "{" +"\""+"title"+"\""+"\""+title+"\","+
                "}";
    }


    @Override
    public String toString() {
        return "Article{" +
                "aId=" + aId +
                ", title='" + title + '\'' +
                ", pageView=" + pageView +
                ", uId=" + uId +
                ", publishTime='" + publishTime + '\'' +
                ", praiseCount=" + praiseCount +
                ", acId=" + article_category +
                ", content='" + content + '\'' +
                ", article_comments=" + article_comments +
                ", user=" + user +
                '}';
    }
}