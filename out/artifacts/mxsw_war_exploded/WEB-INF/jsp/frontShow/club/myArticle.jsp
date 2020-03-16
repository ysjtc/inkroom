<%--
  Created by IntelliJ IDEA.
  User: ysjba
  Date: 2020/3/5
  Time: 13:08
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div>
    <c:forEach items="${article}">
        ${article.title}
    </c:forEach>
</div>
