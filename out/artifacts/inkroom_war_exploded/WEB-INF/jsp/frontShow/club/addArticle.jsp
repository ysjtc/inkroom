<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 10904
  Date: 2019/8/8
  Time: 17:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/Article/addArticle"  method="post">
    <table>
        <thead>
            <tr>
                <td>写文章</td>
            </tr>
        </thead>

        <tbody>
            <tr>
                <td>文章标题</td>
                <td>
                    <input type="text" name="title">
                </td>
            </tr>

            <tr>
                <td>文章内容</td>
                <td>
                    <textarea name="content">

                    </textarea>
                </td>
            </tr>

            <tr>
                <td>文章类别</td>
                <td>
                    <label for="select"></label>
                    <select name="acId" id="select">
                        <c:forEach items="${allCtgr}" var="a">
                            <option value="${a.acId}">${a.ACname}</option>
                        </c:forEach>
                    </select>
                </td>
            </tr>
            <input type="hidden" value="<%=request.getSession().getAttribute("uId")%>" name="uId"/>
            <tr>
                <input type="submit" value="提交">
                <input type="button" value="返回">
            </tr>
        </tbody>
    </table>

</form>
</body>
</html>
