<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
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
            <th>评论人</th>
            <th>评论时间</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${comments}" var="obj">
            <tr>
                <td>${obj.content}</td>
                <td>${obj.uName}</td>
                <td><fmt:formatDate value="${creatTime}" pattern="yyyy-mm-dd HH:MM:ss"></fmt:formatDate></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>
