<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <%--引入basePath--%>
    <%@ include file="/WEB-INF/jsp/common/basePath.jsp"%>
</head>
<body>
<div>
    <table>
        <tr>
            <td><a href="ArticleCategory/queryAll">全站</a></td>
            <td><a href="ArticleCategory/query/category/${obj.acId=1}">随笔</a></td>
            <td><a href="ArticleCategory/query/category/${obj.acId=2}">科学</a></td>
            <td><a href="ArticleCategory/query/category/${obj.acId=3}">文学</a></td>
            <td>热榜</td>
        </tr>
    </table>
</div>


    <div>
        <form action="Article/query/title">
            <span>文章标题</span>  <input type="text" name="title" value="${title}">
            <c:forEach var="obj" items="${article}">
                ${obj}
            </c:forEach>
            <input type="submit" value="搜索">
        </form>
    </div>

    <div>
        <table>
            <tr>
                <td><a href="Article/query/uId/${obj.uId}">我的文章</a></td>
                <td><a href="Comment/query/uId/${obj.uId}">我的评论</a></td>
                <td><a href="">写文章</a></td>
            </tr>
        </table>
    </div>


    <div>
        <table>
            <thead>
                <tr>
                    <th colspan="6">
                        <h2>文章列表</h2>
                    </th>
                </tr>
                <tr>
                    <th>文章类别</th>
                    <th>文章内容</th>
                    <th>用户id</th>
                    <th>发布时间</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${article}" var="obj">
                    <tr>
                        <td>${obj.name}</td>       //种类
                        <td><a href="Article/queryArticle/${obj.aId}">${obj.title}</a></td>
                        <td>${obj.uName}</td>
                        <td>${obj.publishTime}</td>
                        <td>${obj}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</body>
</html>
