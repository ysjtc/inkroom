<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 陈
  Date: 2019/7/7
  Time: 16:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
超级管理员${SUPERADMIN_SESSION.name}
<button><a href="<%=request.getContextPath()%>/SuperAdmin/logout">退出</a> </button>
<button><a href="<%=request.getContextPath()%>/WEB-INF/jsp/error.jsp?uid=1">联系</a> </button>
<br><c:forEach items="${items}" var="obj">
    <tr>
        <td>${obj}</td><br>

    </tr>

</c:forEach>

    <c:forEach items="${itemList}" var="obj">
        <tr>
            <td>${obj}</td>

        </tr>

    </c:forEach>
<c:forEach items="${errorMap}" var="obj">
        <tr>
            <td>${obj}</td>
            <br>

        </tr>

    </c:forEach>
</body>
</html>
