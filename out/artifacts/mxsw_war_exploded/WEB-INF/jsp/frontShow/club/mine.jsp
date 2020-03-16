<%--
  Created by IntelliJ IDEA.
  User: ysjba
  Date: 2020/3/4
  Time: 15:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>论坛个人详情页</title>
    <%--引入basePath--%>
    <%@ include file="./../../common/basePath.jsp"%>
    <%--引入前台展示页部分的公共头部--%>
    <%@ include file="./../../common/frontShow/headBase.jsp"%>
    <%---引入bt-validator
    <%@ include file="./../../common/bt_validator.jsp"%>
    <%--引入club页面资源--%>
    <link rel="stylesheet" type="text/css" href="static/css/mine.css">


</head>

<body>
<div class="wrapper">
    <%--引入两个导航栏--%>
    <%@ include file="./../../common/frontShow/topNav.jsp"%>
    <%@ include file="./../../common/frontShow/leftNav.jsp"%>
    <div class="col-lg-11 col-md-12 col-sm-12 col-xs-12 right-content">
        <div class="row">
            <div class="container-fluid">

                <div class="row main-wrapper">
                    <div class="whole">
                        <div class="top">
                        </div>

                        <div class="information">
                            <nav class="navbar navbar-default" role="navigation">
                                <div class="container-fluid">
                                    <div class="navbar-header">
                                        <button type="button" class="navbar-toggle" data-toggle="collapse"
                                                data-target="#example-navbar-collapse">
                                            <span class="sr-only">切换导航</span>
                                            <span class="icon-bar"></span>
                                            <span class="icon-bar"></span>
                                            <span class="icon-bar"></span>
                                        </button>
                                        <a class="navbar-brand" href="FrontForward/mine">我的</a>
                                    </div>
                                    <div class="collapse navbar-collapse" id="example-navbar-collapse">
                                        <div>
                                            <ul id="target" class="nav navbar-nav">
                                                <li><a href="" onclick="return false">我发表的文章</a></li>
                                                <li><a href="" onclick="return false">我点赞的文章</a></li>
                                                <li><a href="" onclick="return false">我发表的评论</a></li>
                                                <li><a href="" onclick="return false">我收到的回复</a></li>
                                                <%--<li class="dropdown">
                                                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                                                        我收到的回复 <b class="caret"></b>
                                                    </a>
                                                    <ul class="dropdown-menu">
                                                        <li><a href="#">jmeter</a></li>
                                                        <li><a href="#">EJB</a></li>
                                                        <li><a href="#">Jasper Report</a></li>
                                                        <li class="divider"></li>
                                                        <li><a href="#">分离的链接</a></li>
                                                        <li class="divider"></li>
                                                        <li><a href="#">另一个分离的链接</a></li>
                                                    --%>
                                            </ul>
                                        </div>
                                    </div>
                                </div>
                            </nav>
                        </div>
                        <div class="append">
                            一共发表文章${statistics.a_c}篇<br>累计获得点赞${my_p_c}个<br>喜欢了${statistics.ilike}篇文章<br>发表评论${statistics.c_c}条<br>你发表的文章共收到${statistics.ced_c}条评论<br>你发表的评论(回复)共收到${statistics.red_c}条回复
                        </div>

                    </div>
                </div>

            </div>

        </div>
    </div>

</div>

<!-- 外层包装开始 -->
<%--
<div class="wrapper">
    &lt;%&ndash;引入两个导航栏&ndash;%&gt;
    <%@ include file="./../../common/frontShow/topNav.jsp"%>
    <%@ include file="./../../common/frontShow/leftNav.jsp"%>

&lt;%&ndash;
我发布的文章：<a href="<%=request.getContextPath()%>/Article/query/myInfo">${my_a_c}篇</a><br/>

<a href="<%=request.getContextPath()%>/Article/query/uId" name="<%=request.getSession().getAttribute("uId")%>">我发布的文章</a>
累计获得点赞：${my_p_c}次<br/>
<a href="<%=request.getContextPath()%>/Article/query/myPraise?id=<%=request.getSession().getAttribute("uId")%>">我点赞的文章</a>
&ndash;%&gt;
--%>

</body>
<script language="JavaScript">

    $(document).ready(function () {
        function timestampToTime(timestamp) {
            var date = new Date(timestamp);//时间戳为10位需*1000，时间戳为13位的话不需乘1000
            Y = date.getFullYear() + '-';
            M = (date.getMonth() + 1 < 10 ? '0' + (date.getMonth() + 1) : date.getMonth() + 1) + '-';
            D = date.getDate() + ' ';
            h = date.getHours() + ':';
            m = date.getMinutes() + ':';
            s = date.getSeconds();
            return Y+M+D+h+m+s;//时分秒可以根据自己的需求加上
        }
        //alert($(".navigation li:nth-child(2)")
        $("ul#target").on("click","li",function(){
            //alert( $("li[class='active']").text());
            $("li[class='active']").removeAttr("class");
            $(this).attr("class","active");
            //alert($(this).text());
        });
        $("ul#target li:nth-child(1)").on("click",function () {
            $.ajax({
                url:"Article/query/myArticles",
                success:function (data) {
                    //var info=JSON.parse(data);
                    //alert(data.length);
                    $(".append").remove();
                    $(".bot").remove();
                    for (var i=0;i<=data.length;i++){
                        data[i]['publish_time']=timestampToTime(data[i]['publish_time']);
                        $(".information").after(function () {
                            //alert("success");
                            return "<div class=\"append\">" +
                                "<div class=\"a_title\">" +
                                data[i]['title']+
                                "</div>" +
                                "<div class=\"a_content\">" +
                                data[i]['content'] +
                                "</div>" +
                                "<div class=\"a_time\">" +
                                data[i]['publish_time'] +
                                "</div>" +
                                "<div class=\"a_inter\">" +
                                "\t<input type=\"button\" onclick='del_article("+data[i]['a_id']+",this)' value=\"删除\" /><img src=\"\"/>"+data[i]['praise_count']+"<img src=\"\"/>"+data[i]['page_view']+"<img src=\"\"/>"+data[i]['summary']+"" +
                                "</div>" +
                                "</div>";
                        })
                    }
                    $(".append:last").after(function () {
                        return "<div class=\"bot\">\n"+"没有更多了...\n"+"</div>";
                    })
                },
                error:function () {
                    alert("请求失败,请稍后再试")
                }
            })
        })

        $("ul#target li:nth-child(2)").on("click",function () {
            $.ajax({
                url:"${pageContext.request.contextPath}/Article/query/myPraise",
                success:function (data) {
                    //var info=JSON.parse(data);
                    //alert(data.length);
                    $(".append").remove();
                    for (var i=0;i<=data.length;i++){
                        $(".information").after(function () {
                            //alert("success");
                            return "<div class=\"append\"><div class=\"title\"> <a href=\"Article/query/aId?id="+data[i]["aId"]+"\">"+data[i]["title"]+"</a></div> <div class='conente'>"+data[i]["content"]+"</div> <div class='icon'> <a href=''><img src=\""+data[i]["user"]["password"]+"\" /></a> </div> <div class='name'>"+data[i]['user']['uName']+" </div> <div class='inter'> <img src='' width='27' height='37' /> <a onclick=\"increase("+data[i]['aId']+")\" class='praiseCount'>"+data[i]['praiseCount']+"</a> <img src=''/>"+data[i]['pageView']+" <img src='' />"+data[i]['publishTime']+" </div> </div>";
                        })
                    }
                    $(".append:last").after(function () {
                        return "<div class=\"bot\">\n"+"没有更多了...\n"+"</div>";
                    })
                },
                error:function () {
                    alert("请求失败,请稍后再试")
                }
            })
        })
        $("ul#target li:nth-child(3)").on("click",function () {
            $.ajax({
                url:"${pageContext.request.contextPath}/Comment/query/myComment",
                success:function (data) {
                    //var info=JSON.parse(data);
                    //alert(data.length);
                    $(".append").remove();
                    for (var i=0;i<=data.length;i++){
                        data[i]["create_time"]=timestampToTime(data[i]["create_time"]);
                        $(".information").after(function () {
                            //alert("success");
                            return "<div class=\"append\">" +
                                "<div class=\"title\">" +
                                "<a href=\"Article/query/aId?id="+data[i]["a_id"]+"\">"+data[i]["title"]+"</a>\n" +
                                "</div>" +
                                "<div class=\"conente\">"
                                +data[i]["ac_content"]+
                                "</div>" +
                                "<div class=\"inter\">" +
                                "<input value=\"删除\" onclick=\"del_aco("+data[i]['aco_id']+",this)\" type=\"button\" />"
                                +data[i]['create_time']+"</div></div>";
                        })
                    }
                    $(".append:last").after(function () {
                        alert("fuck");
                        return "<div class=\"bot\">\n"+"没有更多了...\n"+"</div>";
                    })
                },
                error:function () {
                    alert("请求失败,请稍后再试")
                }
            })
        })
        $("ul#target li:nth-child(4)").on("click",function () {
            $.ajax({
                url:"${pageContext.request.contextPath}/Comment/query/myReply",
                success:function (data) {
                    $(".append").remove();
                    for (var i=0;i<=data.length;i++){
                        data[i]["reply_time"]=timestampToTime(data[i]["reply_time"]);
                        $(".information").after(function () {
                            //alert("success");
                            return "<div class=\"append\">\n" +
                                "<img class=\"r_icon\" src=\""+data[i]['user_path']+"\"/>\n" +
                                "<div class=\"replyer\">\n" +
                                "来自"+data[i]['u_name']+"的回复:\n" +
                                "</div>\n" +
                                "<div class=\"r_content\">\n"
                                +data[i]['acr_content']+
                                "</div><span class=\"data\">"+data[i]['reply_time']+"</span>\n" +
                                "<a href=\""+data[i]['a_id']+"\"><img class=\"detail\" src=\"\"/></a>\n" +
                                "</div>\n" +
                                "</body>";
                        })
                    }
                    $(".append:last").after(function () {
                        alert("fuck");
                        return "<div class=\"bot\">\n"+"没有更多了...\n"+"</div>";
                    })
                },
                error:function () {
                    alert("请求失败,请稍后再试")
                }
            })
        })

    })

    function increase(a) {
        //alert(a);
        var aId=parseInt(a);
        $.ajax({
            url:"<%=request.getContextPath()%>/Article/addAP?aId="+aId,
            success:function () {
                var c=parseInt(this.html());
                $(".praiseCount").html(c+1);
            },
            error:function () {
                alert("已经点过了哦！")
            }
        })
    }
    function del_aco(a,t) {
        if(!window.confirm("确认删除此条？")){
            return false;
        }
        $.ajax({
            url:"Comment/delAC",
            data:{aco_id: a},
            success:function (data) {
                if(data){
                t.parentNode.parentNode.parentNode.removeChild( t.parentNode.parentNode)//有毒
                }else {
                    alert("请勿重复操作！");
                }
            },
            error:function () {
                alert("操作失败，请稍后再试");
            }
        })

    }
    function del_article(a,t) {
        if(!window.confirm("确认删除此条？")){
            return false;
        }
        $.ajax({
            url:"Article/delete",
            data:{aId: a},
            success:function (data) {
                if(data){
                    t.parentNode.parentNode.parentNode.removeChild( t.parentNode.parentNode)//有毒
                }else {
                    alert("请勿重复操作！");
                }
            },
            error:function () {
                alert("操作失败，请稍后再试");
            }
        })

    }
    /*$(".whole").on("click",'.delete',function () {
        alert(this.parent());
    })*/
</script>
</html>
