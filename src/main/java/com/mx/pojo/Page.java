package com.mx.pojo;/*
@author 郭子雄
@description 用于分页的数据接收类
*/

public class Page {
    private Integer offset;
    private Integer pageSize;
    private String sort;
    private String sortOrder;
    private QueryData queryData;

    public QueryData getQueryData() {
        return queryData;
    }

    public void setQueryData(QueryData queryData) {
        this.queryData = queryData;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSzie) {
        this.pageSize = pageSzie;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(String sortOrder) {
        this.sortOrder = sortOrder;
    }

    @Override
    public String toString() {
        return "Page{" +
                "offset=" + offset +
                ", pageSize=" + pageSize +
                ", sort='" + sort + '\'' +
                ", sortOrder='" + sortOrder + '\'' +
                ", queryData=" + queryData +
                '}';
    }
}

