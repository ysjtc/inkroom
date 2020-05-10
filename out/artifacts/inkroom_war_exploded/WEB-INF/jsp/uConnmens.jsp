<%--
  Created by IntelliJ IDEA.
  User: 10904
  Date: 2019/8/11
  Time: 17:16
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
            <th colspan="4">
                <h2>评论列表 <a href="/">返回文章列表</a> </h2>
            </th>
        </tr>
        <tr>
            <th>评论内容</th>
            <th>评论时间</th>
            <th>操作</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${comments}" var="obj">
            <tr>
                <td>${obj.content}</td>
                <td><fmt:formatDate value="${creatTime}" pattern="yyyy-mm-dd HH:MM:ss"></fmt:formatDate></td>
                <td>
                    <a href="">修改</a>
                    <a href="">删除</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>
