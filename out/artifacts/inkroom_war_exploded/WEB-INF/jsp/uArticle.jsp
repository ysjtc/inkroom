<%--
  Created by IntelliJ IDEA.
  User: 10904
  Date: 2019/8/11
  Time: 17:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
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
            <th>发布时间</th>
            <th>操作</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${article}" var="obj">
            <tr>
                <td>${obj.name}</td>       //种类
                <td><a href="Article/queryArticle/${obj.aId}">${obj.title}</a></td>
                <td>${obj.publishTime}</td>
                <td>
                    <a href="">修改</a>
                    <a href="Article/delete/${obj.aId}">删除</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>
