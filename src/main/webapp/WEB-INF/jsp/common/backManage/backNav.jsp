<%--
  Created by IntelliJ IDEA.
  User: ysjba
  Date: 2020/4/15
  Time: 16:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!-- 头部导航开始 -->
<div class="header">
    <div class="nav-left">
        <div class="logo">
            <a href=".">
                <img src="static/images/backManage/logo.png" alt="">
            </a>

        </div>
        <div class="nav-title">
            <h1>墨香书屋后台管理</h1>
            <span>The ink house Content Management System.</span>
        </div>
    </div>
    <div class="nav-right">
        <span class="admin">${SUPERADMIN_SESSION.name}，欢迎您!</span>
        <a class="logout" href="SuperAdmin/logout">注销<span class="glyphicon glyphicon-log-out" aria-hidden="true"></span></a>
    </div>
</div>
<!-- 头部导航结束 -->

<!-- 侧边栏开始 -->
<div class="sidebar">
    <!-- 商品管理模块 -->
    <div class="section">
        <div class="section-title">
            <span class="good-icon icon"></span>
            <h4>商品管理</h4>
        </div>
        <ul>
            <li><a href="Forward/showItems">查看商品信息</a></li>
            <li><a href="Forward/addItems">添加商品</a></li>
        </ul>
    </div>
    <!-- 用户管理模块 -->
    <div class="section">
        <div class="section-title">
            <span class="user-icon icon"></span>
            <h4>用户管理</h4>
        </div>
        <ul>
            <li><a href="Forward/showUser">查看用户</a></li>
        </ul>
    </div>
    <!-- 免费书管理模块 -->
    <div class="section">
        <div class="section-title">
            <span class="free-icon icon"></span>
            <h4>免费书</h4>
        </div>
        <ul>
            <li>查看商品信息</li>
            <li>添加商品</li>
            <li>删除商品</li>
            <li>管理商品评论</li>
        </ul>
    </div>
    <!-- 社区管理 -->
    <div class="section">
        <div class="section-title">
            <span class="club-icon icon"></span>
            <h4>论坛管理</h4>
        </div>
        <ul>
            <li><a href="Forward/showArticles">帖子管理</a></li>
            <li>添加商品</li>
            <li>删除商品</li>
        </ul>
    </div>
    <!-- 订单管理 -->
    <div class="section">
        <div class="section-title">
            <span class="order-icon icon"></span>
            <h4>订单管理</h4>
        </div>
        <ul>
            <li><a href="Forward/showOrder">查看所有订单</a></li>
            <li><a href="Forward/confirmReturnOrder">查看所有退单</a></li>
        </ul>
    </div>
    <!-- 管理员 -->
    <div class="section">
        <div class="section-title">
            <span class="admin-icon icon"></span>
            <h4>管理员</h4>
        </div>
        <ul>
            <li>查看商品信息</li>
            <li>添加商品</li>
            <li>删除商品</li>
        </ul>
    </div>
</div>
<!-- 侧边栏结束 -->
