<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="zh-CN">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>登录</title>
    <%--引入basePath--%>
    <%@ include file="./../../common/basePath.jsp"%>
    <%--引入前台展示页部分的公共头部--%>
    <%@ include file="./../../common/frontShow/headBase.jsp"%>
    <%---引入bt-validator--%>
    <%@ include file="./../../common/bt_validator.jsp"%>
    <!-- 引入login页面的资源 -->
    <link rel="stylesheet" href="static/css/personal.css" />
    <script src="static/js/login.js"></script>


</head>

<body>
    <!-- 外层包装开始 -->
    <div class="wrapper">
        <%--引入两个导航栏--%>
        <%@ include file="./../../common/frontShow/topNav.jsp"%>
        <%@ include file="./../../common/frontShow/leftNav.jsp"%>

        <%--引入登录注册界面的主内容--%>
        <%@ include file="./../../common/frontShow/loginMain.jsp"%>
    </div>
    <!-- 外层包装结束 -->
    <%--引入注册界面的主内容--%>
    <%@ include file="./../../common/frontShow/loginMainModal.jsp"%>
</body>

</html>