<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>个人中心</title>
    <%--引入basePath--%>
    <%@ include file="./../../common/basePath.jsp"%>
    <%--引入前台展示页部分的公共头部--%>
    <%@ include file="./../../common/frontShow/headBase.jsp"%>
    <%---引入bt-validator--%>
    <%@ include file="./../../common/bt_validator.jsp"%>
    <%--引入bootstrap-table--%>
    <%@ include file="./../../common/bt_table.jsp" %>
    <!-- 进入个人中心主页面相关资源 -->
    <link rel="stylesheet" type="text/css" href="static/css/personalMain.css">
    <script src="static/js/userMain.js"></script>
    <%--用户订单的js--%>
    <script src="static/js/userApplyOrder.js"></script>
</head>

<body>
<!-- 外层包装开始 -->
<div class="wrapper">
    <%--引入两个导航栏--%>
    <%@ include file="./../../common/frontShow/topNav.jsp"%>
    <%@ include file="./../../common/frontShow/leftNav.jsp"%>

    <!--右侧的显示内容开始-->
    <div class="col-lg-11 col-md-12 col-sm-12 col-xs-12 right-content">
        <div class="row pannel-main">
            <!-- 顶部个人基本信息 -- start -->
            <div class="col-xs-12 personal-info">
                <!-- 左侧信息区域 -->
                <div class="left-info">
                    <!-- 个人头像 -->
                    <a class="thumbnail personal-pic">
                        <img src="${userPic.userPath}" />
                    </a>

                    <!-- 用户ID和昵称 -->
                    <div class="personal-baseInfo">
                        <h4>${userData.uName}
                            <span class="personal-vip"><img src="static/images/vip/vip1.png"></span>
                            <span class="personal-id">(ID:${userData.name})</span>
                        </h4>
                        <span class="greeting">晚上好</span>
                        <a class="update" href="#" data-toggle="modal" data-target="#updateInfo">修改个人信息&nbsp;&gt;&nbsp;&gt;&nbsp;</a>
                    </div>
                </div>


                <!-- 右侧信息区域 -->
                <div class="right-info">
                    <!-- 用户的其他基本信息 -->
                    <div class="personal-otherInfo">
                        <span>安全性: <span class="safe">普通</span></span>
                        <span>邮箱: ${userData.email}</span>
                        <span>手机号: ${userData.tel}</span>
                    </div>
                </div>
            </div>
            <!-- 顶部个人基本信息 -- end -->

            <div class="col-xs-12 personal-info">
                <!-- 导航栏 -->
                <ul class="nav nav-tabs nav-justified">
                    <li><a href="FrontForward/personalMain">购物车</a></li>
                    <li><a href="FrontForward/userOrder">订单</a></li>
                    <li class="active"><a href="FrontForward/applyOrder">我的退换货</a></li>
                    <li><a href="FrontForward/addAddr">收货人信息</a></li>
                </ul>

                <!-- 导航栏下的内容 -->
                <div class="main-content">
                    <%--引入购物车的主内容--%>
                    <%@ include file="./../../common/frontShow/userApplyOrderMain.jsp"%>
                </div>

            </div>

        </div>
    </div>
    <!--右侧的显示内容结束-->


</div>
<!-- 外层包装结束 -->
<%--引入模态框--%>
<%@ include file="./../../common/frontShow/userMainModal.jsp"%>

</body>

</html>