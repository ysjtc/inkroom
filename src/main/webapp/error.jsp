<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getServerName() + ":"
            + request.getServerPort() + path ;
    String basePath2 = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01//EN"
"http://www.w3.org/TR/html4/strict.dtd">

<html xmlns="http://www.w3.org/1999/xhtml" lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title></title>
    <script type="text/javascript" src="../../static/js/jquery-3.3.1.js"></script>
    <style>
        textarea {
            height: 300px;
            width: 100%;
            resize: none;
            outline: none;
        }

        input[type=button] {
            float: right;
            margin: 5px;
            width: 50px;
            height: 35px;
            border: none;
            color: white;
            font-weight: bold;
            outline: none;
        }

        .clear {
            background: red;
        }

        .send {
            background: green;
        }

        .clear:active {
            background: yellow;
        }

        .send:active {
            background: yellow;
        }

        .msg {
            width: 100%;
            height: 25px;
            outline: none;
        }

        #content {
            border: 1px solid gray;
            width: 100%;
            height: 400px;
            overflow-y: scroll;
        }

        .from {
            background-color: green;
            width: 80%;
            border-radius: 10px;
            height: 30px;
            line-height: 30px;
            margin: 5px;
            float: left;
            color: white;
            padding: 5px;
            font-size: 22px;
        }

        .to {
            background-color: gray;
            width: 80%;
            border-radius: 10px;
            height: 30px;
            line-height: 30px;
            margin: 5px;
            float: right;
            color: white;
            padding: 5px;
            font-size: 22px;
        }

        .name {
            color: gray;
            font-size: 12px;
        }

        .tmsg_text {
            color: white;
            background-color: rgb(47, 47, 47);
            font-size: 18px;
            border-radius: 5px;
            padding: 2px;
        }

        .fmsg_text {
            color: white;
            background-color: rgb(66, 138, 140);
            font-size: 18px;
            border-radius: 5px;
            padding: 2px;
        }

        .sfmsg_text {
            color: white;
            background-color: rgb(148, 16, 16);
            font-size: 18px;
            border-radius: 5px;
            padding: 2px;
        }

        .tmsg {
            clear: both;
            float: right;
            width: 80%;
            text-align: right;
        }

        .fmsg {
            clear: both;
            float: left;
            width: 80%;
        }
    </style>

</head>
<body>
欢迎：${sessionScope.name }
<div id="content"></div>
<input type="text" placeholder="请输入要发送的信息" id="msg" class="msg" onkeydown="send(event)">
<input type="button" value="发送" class="send" onclick="sendMsg()" >
<input type="button" value="清空" class="clear" onclick="clearAll()">

</body>
</html>