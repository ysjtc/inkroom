<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>墨香书屋</title>
    <%--引入basePath--%>
    <%@ include file="./../../common/basePath.jsp"%>
    <%--引入前台展示页部分的公共头部--%>
    <%@ include file="./../../common/frontShow/headBase.jsp"%>
    <%--引入首页海报轮播资源--%>
    <link rel="stylesheet" type="text/css" href="static/css/index.css">
</head>

<body>
    <!-- 外层包装开始 -->
    <div class="wrapper">
        <%--引入两个导航栏--%>
        <%@ include file="./../../common/frontShow/topNav.jsp"%>
        <%@ include file="./../../common/frontShow/leftNav.jsp"%>

        <%--引入海报轮播页的主内容--%>
        <%@ include file="./../../common/frontShow/indexMain.jsp"%>
    </div>
    <!-- 外层包装结束 -->
</body>

</html>