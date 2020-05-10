<%--
  Created by IntelliJ IDEA.
  User: ysjba
  Date: 2020/4/28
  Time: 12:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<html>

<head>
    <meta charset="UTF-8">
    <title>帖子管理</title>
    <%--引入basePath--%>
    <%@ include file="./../../common/basePath.jsp"%>
    <%--引入后台管理部分的公共头部--%>
    <%@ include file="./../../common/backManage/headBase.jsp"%>
    <%--引入bt-table--%>
    <%@ include file="./../../common/bt_table.jsp"%>

</head>

<body>

<%--引入导航栏--%>
<%@ include file="./../../common/backManage/backNav.jsp"%>

<!-- 右侧主要内容开始 -->
<div class="right-content">
    <div class="right-content-wrap">
        <div align="center">当前在线人数：${num}<br></div>
        <table id="info" border="1" align="center">
            <tr>
                <td>session_id</td>
                <td>账号</td>
                <td>昵称</td>
                <td>最近登录时间</td>
                <td>最近登录ip</td>
                <td>操作</td>
            </tr>
            <c:forEach items="${list}" var="in">
                <tr>
                    <td>${in.sessionId}</td>
                    <td>${in.userId}</td>
                    <td>${in.userName}</td>
                    <td>${in.date}</td>
                    <td>${in.ip}</td>
                    <td><input type="button" value="登出" id="${in.sessionId}"></td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>
</body>

<script language="JavaScript">
    $("#info").on("click","input",function () {
        //alert($(this).attr("id"));
        if(!confirm("确认注销此会话（"+$(this).attr("id")+")?")){
            return false;
        }
        var obj=$(this);
        $.ajax({
            url: 'User/offLine',
            data: {"sessionId":$(this).attr("id")},
            type: 'GET',
            success:function (data) {
                data=JSON.parse(data);
                if (data['result']){
                    alert("已将此人踢下线");
                    //obj.closest("tr").remove();
                    window.location.href="http://localhost:8080/inkroom_war_exploded/User/onLine";
                }else {
                    alert("请勿重复操作");
                }
            },
            error: function () {
                alert("未知错误");
            }

        })
    })
</script>
</html>
