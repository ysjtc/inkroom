<%--
  Created by IntelliJ IDEA.
  User: Eros
  Date: 2019/7/15
  Time: 18:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>墨香书屋后台登录</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <%--引入basePath--%>
    <%@ include file="./../../common/basePath.jsp"%>
    <link rel="stylesheet" href="static/css/backManage/login.css">
</head>
<body>
<div class="warp">
    <div class="title">
        <h1>墨香书屋后台管理</h1>
        <span>The ink house Content Management System.</span>
    </div>
    <div class="head-img">
        <img src="static/images/backManage/head_img.jpeg" alt="">
    </div>
    <div class="info">
        <form action="SuperAdmin/login" method="POST">
            <input type="text" placeholder="用户名" name="name">
            <input type="password" placeholder="密码" name="password">
            <button type="submit">登&nbsp;&nbsp;&nbsp;&nbsp;录</button>
            <a href="">一些问题？</a>
        </form>
    </div>
</div>
</body>
</html>
