<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!--左侧导航栏开始-->
<div class="col-lg-1 visible-lg left-nav">
    <!-- logo -->
    <div class="row">
        <div class="nav-logo">
            <a href="${pageContext.request.contextPath}/FrontForward/nav"></a>
        </div>
    </div>
    <!-- 上部导航模块 -->
    <div class="row">
        <div class="nav-item">
            <a href="FrontForward/store">
                <span class="glyphicon glyphicon-book"></span>
                <span class="nav-section">商城</span>
            </a>
            <div class="subnav">
                <ul>
                    <li><a href="#">最新书籍</a></li>
                    <li><a href="#">热门书籍</a></li>
                    <li><a href="#">限时折扣</a></li>
                    <li><a href="#">好评书籍</a></li>
                </ul>
            </div>
        </div>
        <div class="nav-item">
            <a href="./../auction/main.html">
                <span class="glyphicon glyphicon-fire"></span>
                <span class="nav-section">拍卖区</span>
            </a>
            <div class="subnav">
                <ul>
                    <li><a href="#">最新上架</a></li>
                    <li><a href="#">拍卖热区</a></li>
                    <li><a href="#">限时拍卖</a></li>
                </ul>
            </div>
        </div>
        <div class="nav-item">
            <a href="${pageContext.request.contextPath}/Article/query/conditions">
                <span class="glyphicon glyphicon-globe"></span>
                <span id="club" class="nav-section">书友社</span>
            </a>
            <div class="subnav">
                <ul>
                    <li><a href="Article/query/myInfo">我的</a></li>
                    <li><a href="#">热门专题</a></li>
                    <li><a href="#">活动专题</a></li>
                </ul>
            </div>
        </div>
        <div class="nav-item">
            <a href="./../free/main.html">
                <span class="glyphicon glyphicon-heart"></span>
                <span class="nav-section">免费送</span>
            </a>
            <div class="subnav">
                <ul>
                    <li><a href="#">限时免费</a></li>
                    <li><a href="#">提供书籍</a></li>
                </ul>
            </div>
        </div>
    </div>
    <!-- 个人中心导航模块 -->
    <div class="row">
        <div class="nav-personal">
            <a class="nav-personal-img" href="<c:choose><c:when test="${empty USER_ID}">FrontForward/loginMain</c:when><c:when test="${not empty USER_ID}">FrontForward/personalMain</c:when></c:choose>">
                <img src="static/images/account.png" alt="">
            </a>
            <div class="subnav">
                <ul>
                    <li><a href="FrontForward/userOrder">我的订单</a></li>
                    <li><a href="/FrontForward/userOrder">我的收藏</a></li>
                    <li><a href="User/loginout">注销登录</a></li>
                </ul>
            </div>
        </div>
    </div>
</div>
<!--左侧导航栏结束-->