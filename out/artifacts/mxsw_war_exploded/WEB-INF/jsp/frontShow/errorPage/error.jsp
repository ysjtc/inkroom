<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <%--引入basePath--%>
    <%@ include file="./../../common/basePath.jsp"%>
    <%--引入404页面的资源文件--%>
    <link rel="stylesheet" href="static/css/error.css">
    <title>404 资源未找到</title>
</head>

<body>
    <div class="wrap">
        <div class="describe">
            <h1>Page not found.</h1>
            <p> We could not find the page<br> you were looking for.</p>
        </div>
        <a href="javascript:history.go(-1);" class="back-home">PREVIOUS PAGE</a>
    </div>
</body>

</html>