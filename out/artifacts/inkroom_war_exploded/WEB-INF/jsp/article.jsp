<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 10904
  Date: 2019/8/6
  Time: 16:41
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
        <c:forEach items="${article}" var="obj">
            <tr>
                <td>${obj.title}</td>

                <td>${obj.uName}</td>  //用户昵称

                <td>${obj.publishTime}</td>

                <td>${obj.name}</td>
            </tr>
            <tr>
                <td>${obj.content}</td>

            </tr>

        </c:forEach>
    </table>
</div>

<div>
    <table>
        <td><a href="Comment/query/aId/${obj.aId}">查看评论</a></td>   //根据a_id查询
        <td><a href="Comment/addTo/${obj.aId}">评论</a></td>
        <td><a href="Comment/query/aId/${obj.aId}">查看评论</a></td>   //根据a_id查询
        <td><a href="Comment/addTo/${obj.aId}">评论</a></td>

        <td><a href="">${obj.praiseCount}</a></td>  //点赞及点赞数
        <td>${obj.pageView}</td>   //访问量
    </table>
</div>




</body>
</html>
