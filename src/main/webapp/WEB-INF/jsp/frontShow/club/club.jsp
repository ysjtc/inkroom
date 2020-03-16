<%--
  Created by IntelliJ IDEA.
  User: ysjba
  Date: 2020/2/18
  Time: 21:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>书友社</title>
    <%--引入basePath--%>
    <%@ include file="./../../common/basePath.jsp"%>
    <%--引入前台展示页部分的公共头部--%>
    <%@ include file="./../../common/frontShow/headBase.jsp"%>
    <%---引入bt-validator
    <%@ include file="./../../common/bt_validator.jsp"%>
    <%--引入club页面资源--%>
    <link rel="stylesheet" type="text/css" href="static/css/articleMain.css">


</head>

<body>
<!-- 外层包装开始 -->
<div class="wrapper">
    <%--引入两个导航栏--%>
    <%@ include file="./../../common/frontShow/topNav.jsp"%>
    <%@ include file="./../../common/frontShow/leftNav.jsp"%>
        <div class="whole">
            <div class="top">
                <h3 align="center">条件筛选</h3>
                <div class="top-left">
                    <form action="${pageContext.request.contextPath}/Article/query/conditions" method="post">
                        标题:<input type="text" name="title" placeholder="${cache.title}"/><br>
                        作者:<input type="text" name="uName" placeholder="${cache.uName}"/><br>
                        内容:<input type="text" name="content" placeholder="${cache.content}"/><br>
                        <input type="submit" value="查询" />(可多条件查询)
                    </form>
                </div>
                <div class="top-right">
                    <form id="sort1" action="${pageContext.request.contextPath}/Article/query/conditions" method="post">
                        按点击量 <select  name="pageView">
                        <option class="sort1" value="true">降序</option>
                        <option class="sort1" value="false">升序</option>
                    </select>
                    </form>
                    <form id="sort2" action="${pageContext.request.contextPath}/Article/query/conditions" method="post">
                        按点赞数 <select  name="praiseCount">
                        <option class="sort2" value="true">降序</option>
                        <option class="sort2" value="false">升序</option>
                    </select>
                    </form>
                    <form id="sort3" action="${pageContext.request.contextPath}/Article/query/conditions" method="post">
                        按时间   <select  name="publishTime">
                        <option class="sort3" value="true">降序</option>
                        <option class="sort3" value="false">升序</option>
                    </select>
                    </form>
                </div>
                <a href="${pageContext.request.contextPath}/FrontForward/addArticle">我要发表文章</a>
            </div>

            <div class="allArticle">
                 共${total}条数据 <c:if test="${total != 0}">当前为${pageOff+1}——<c:choose><c:when test="${pageOff+size>=total}">${total}</c:when><c:otherwise>${pageOff+size}</c:otherwise> </c:choose>条<br></c:if>
                <c:forEach items="${article}" var="a">
                    <a href="${pageContext.request.contextPath}/Article/query/aId?id=${a.aId}">${a.article_category.ACname}&nbsp;${a.title}&nbsp;发布者:${a.user.uName}&nbsp;&nbsp;发布时间:${a.publishTime}   访问量:${a.pageView}   点赞数:<c:choose><c:when test="${a.praiseCount ==null}">0</c:when><c:otherwise>${a.praiseCount}</c:otherwise></c:choose></a> <br/>
                </c:forEach>
            </div>
            <div class="right">
                <c:forEach items="${allCtgr}" var="a">
                    <a href="${pageContext.request.contextPath}/Article/query/conditions?acId=${a.acId}">${a.ACname}</a> <br/>
                </c:forEach>
            </div>
            <div class="bottom" align="center">
                <form action="Article/query/conditions" method="post">
                    <a href="${pageContext.request.contextPath}/Article/query/conditions?pageNo=1">首页</a>
                    <a href="${pageContext.request.contextPath}/Article/query/conditions?pageNo=${requestScope.pageNo-1}">上一页</a>
                    <input type="submit" value="跳转至"/>
                    <input size="4" type="text" value="${requestScope.pageNo }" name="pageNo"/>
                    <a href="${pageContext.request.contextPath}/Article/query/conditions?pageNo=${requestScope.pageNo+1}">下一页</a>
                    <a href="${pageContext.request.contextPath}/Article/query/conditions?pageNo=${requestScope.last}">尾页</a>
                </form>
                <form id="pageSize" action="Article/query/conditions" method="post">
                    每页显示<select name="pageSize">
                                <option class="pageSize" value="5">5</option>
                                <option class="pageSize" value="10">10</option>
                                <option class="pageSize" value="15">15</option>
                                <option class="pageSize" value="20">20</option>
                            </select>条记录
                </form>
            </div>
        </div>

    <%--引入论坛文章界面的主内容--%>
</div>
<c:if test="${article==null}">
    这里空空如也~
</c:if>


<%--文章一览--%>


</body>
<script language="JavaScript">
    $(document).ready(function(){
        $(".allArticle a").click(function(){
            var exp=/[0-9]*$/;
            var aId=this.getAttribute("href").match(exp);
            $.post("<%=request.getContextPath()%>/Article/smg?aId="+aId)
        });

        $(".sort1").click(function () {
            $("#sort1").submit();
        })
        $(".sort2").click(function () {
            $("#sort2").submit();
        })
        $(".sort3").click(function () {
            $("#sort3").submit();
        })

        $(".pageSize").click(function () {
            $("#pageSize").submit();
        })
        $("select option[value='${pageSize}']").prop("selected", true); //youdu



    });

</script>
</html>
