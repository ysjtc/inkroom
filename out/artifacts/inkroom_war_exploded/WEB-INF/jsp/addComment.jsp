 <%--
  Created by IntelliJ IDEA.
  User: 10904
  Date: 2019/8/6
  Time: 3:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

    <form action="comment/add" method="post">
        <input type="hidden" name="article.aId" value="${aId}">
        <input type="hidden" name="user.uId" value="${uId}">
        <table border="1">
            <thead>
                <tr>
                    <th colspan="2">增加评论</th>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <td>评论内容</td>
                    <td>
                        <textarea name="content" rows="10" cols="40"></textarea>
                    </td>
                </tr>

                <tr>
                    <td colspan="2">\
                        <input type="submit" value="提交">
                        <a href="javascript:history.go(-1);">返回</a>
                    </td>
                </tr>
            </tbody>
        </table>
    </form>

</body>
</html>
