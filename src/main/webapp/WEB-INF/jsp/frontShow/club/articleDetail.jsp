<%--
  Created by IntelliJ IDEA.
  User: ysjba
  Date: 2020/2/25
  Time: 19:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>登录</title>
    <%--引入basePath--%>
    <%@ include file="./../../common/basePath.jsp"%>
    <%--引入前台展示页部分的公共头部--%>
    <%@ include file="./../../common/frontShow/headBase.jsp"%>
    <%---引入bt-validator
    <%@ include file="./../../common/bt_validator.jsp"%>
    <!-- 引入login页面的资源 -->
    <link rel="stylesheet" href="static/css/personal.css" />
    <script src="static/js/login.js"></script>
    --%>
    <%--引入club页面资源--%>
    <link rel="stylesheet" type="text/css" href="static/css/articleDetail.css">


</head>

<body>
<!-- 外层包装开始 -->
<div class="wrapper">
    <%--引入两个导航栏--%>
    <%@ include file="./../../common/frontShow/topNav.jsp"%>
    <%@ include file="./../../common/frontShow/leftNav.jsp"%>
    <div class="col-lg-11 col-md-12 col-sm-12 col-xs-12 right-content">
            <div class="row">
                <div class="container-fluid">
                    <!-- 文章内容--start-- -->
                    <div class="row title">
                        <div class="title-img">
                                <h3 align="center">${articleDetail.title}</h3>
                                <pre>${articleDetail.content}</pre><br/>
                                <span>文章发布时间：${articleDetail.publishTime}</span>
                                <p>喜欢这篇文章？点个赞吧——> <input type="button" value="点赞" id="addAP"/> 点赞：<span id="praiseCount"> ${article_praise}</span></p>点击数${articleDetail.pageView}
                        </div>
                    </div>
                    <!-- 文章顶部--end-- -->

                    <!--文章评论主体--start-->
                    <div class="row main-wrapper">
                        <c:forEach items="${cmtAndUser}" var="cau">
                            <div class="single">
                                <div class="right">
                                    <div class="c_name">
                                            ${cau.u_name}
                                    </div>
                                    <img src="${cau.user_path}" width="96" height="113" class="icon"/>
                                </div>
                                <div class="comment">
                                    <span>${cau.ac_content}</span>
                                </div>
                                <div class="reply">
                                    <a class="replyR" data-toggle="modal" name="${cau.aco_id}" data-target="#myModal">回复 <c:if test="${cau.r_count!=null}">(${cau.r_count})</c:if></a>
                                </div>
                                <div class="c_time">
                                    发表于:&nbsp;&nbsp;&nbsp;${cau.create_time}
                                </div>
                            </div>
                        </c:forEach>
                        <c:if test="${cmtAndUser == null}">
                            此文章还没有评论，快快火钳刘明吧！
                        </c:if>
                    </div>
                </div>
                <!--文章内容主体--end-->
                <form class="aComment" role="form" action="${pageContext.request.contextPath}/Comment/addAC" method="post">
                    <div class="form-group">
                        <label>在这里发表你的评论(100字符以内)：</label>
                        <textarea name="ACcontent" class="form-control" rows="3">
                        </textarea>
                    </div>
                    <input id="publish" align="right" type="button" value="发表" />
                    <input type="hidden" name="aId" value="${articleDetail.aId}"/>
                    <input type="hidden" name="uId" value="${uId}"/>
                </form>
            </div>
        </div>

</div>

<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" id="cancer" class="close" data-dismiss="modal" aria-hidden="true">
                    &times;
                </button>
                <h4 class="modal-title" id="myModalLabel">
                </h4>
            </div>
            <div class="modal-body">
               <%-- <c:forEach items="${replyDetail}" var="r">
                    <div class="replyMain">
                        <div class="replyer">
                            <img src="${r.upath}"/>
                            <div class="r_content">
                                ${r.uName}:${r.acr_content}
                            </div>
                            <span>
                            <a href="">回复</a>
                        </span>
                            <div id="nkt">${r.reply_time}</div>
                        </div>
                    </div>
                </c:forEach>--%>
                <form role="form" action="${pageContext.request.contextPath}/Comment/addReply" method="post">
                    <div class="form-group">
                        <label id="tips">在这里回复(80字符以内)：</label>
                        <textarea  id="content" name="acr_content" class="form-control" rows="3">
                        </textarea>
                    </div>
                    <input id="submit" align="right" type="button" value="发表" />
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" id="close" class="btn btn-default" data-dismiss="modal">关闭
                </button>
                <button type="button" class="btn btn-primary">
                    提交更改
                </button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>
<script language="JavaScript">
    var content=null;
    var to_uid=null;
    var acoId=null;
    var uName=null;
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

        $("#publish").click(function () {
            var comment=$(".form-control").val();
            //alert(comment.toString().trim());
            if (comment==null||comment.trim()==""){
                alert("评论内容不能为空！")
            }else {
                $(".aComment").submit();
            }
        })
            var flag=true;
        $("#addAP").click(function () {
                $.ajax({
                        url:"<%=request.getContextPath()%>/Article/addAP?aId="+${aId},
                        success: function(data){
                            $("#praiseCount").html(parseInt($("#praiseCount").html())+1);
                        },
                        error: function (data) {
                            if (flag) {
                                alert("你已经赞过这篇文章了哦！");
                                flag=false;
                            }else{
                                alert("都特么让你不要点了还点，宁点宁玛呢！")
                            }
                        }
                    }
                )
        })
        $(".replyR").click(function (){
            //alert(parseInt(this.name));
            acoId=parseInt(this.name);
            $.ajax({
                url: "<%=request.getContextPath()%>/Comment/query/reply?acoId="+acoId,
                success:function (data) {
                    $(".replyMain").remove();
                    for(var i=0;i<data.length;i++){
                        data[i]["reply_time"]=timestampToTime(data[i]["reply_time"]);
                        $(".form-group").before(function(){
                            if (data[i]["to_uid"]==null){
                                return "<div class=\"replyMain\"> <div class=\"replyer\"> <img src=\""+data[i]['upath']+"\"/> <div class=\"r_content\">"+data[i]['uName']+":"+data[i]["acr_content"]+"</div> <span> <a onclick='dj(this)' id=\"getAttribute\" name=\""+data[i]["aco_id"]+"wocao"+data[i]["u_id"]+"name"+data[i]["uName"]+"name"+data[i]["uName"]+"\">回复</a> </span> <div id=\"nkt\">"+data[i]["reply_time"]+"</div> </div> </div>";
                            }else {
                                return "<div class=\"replyMain\"> <div class=\"replyer\"> <img src=\""+data[i]['upath']+"\"/> <div class=\"r_content\">"+data[i]['uName']+":"+data[i]["acr_content"]+"  //@"+data[i]["r_uName"]+"</div><span><a onclick='dj(this)' id=\"getAttribute\" name=\""+data[i]["aco_id"]+"wocao"+data[i]["u_id"]+"name"+data[i]["uName"]+"\">回复</a> </span> <div id=\"nkt\">"+data[i]["reply_time"]+"</div> </div> </div>";
                            }
                        });
                    }
                    //alert(data[0]["a_id"]);
                },
                error: function (data) {
                    alert("暂时无法回复，请刷新后再试！")
                }
            })
        })
        /*$("#close,#cancer").click(function () {
            //alert("wtf");
            //$(".replyMain").remove();
            window.location.reload();
        })*/
        //模态框 关闭前事件
        $("#myModal").on('hidden.bs.modal',function () {
            //$(".replyMain").remove();
            window.location.reload();
        })
        $("#submit").click(function () {
            content=$("#content").val().trim();
            //alert(content);
            if(content==""){
                alert("回复内容不能为空！")
                return false;
            }
            //alert(content+typeof(content));
            $.ajax({
                url: "<%=request.getContextPath()%>/Comment//addReply",
                data: {acoId:acoId,to_uid: to_uid,acr_content: content},
                success:function (data) {
                    //alert("success");
                    $("#content").val('');
                    $.ajax({
                        url: "<%=request.getContextPath()%>/Comment/query/reply?acoId="+acoId,
                        success:function (data) {
                            $(".replyMain").remove();
                            for(var i=0;i<data.length;i++){
                                data[i]["reply_time"]=timestampToTime(data[i]["reply_time"]);
                                $(".form-group").before(function(){
                                    if (data[i]["to_uid"]==null){
                                        return "<div class=\"replyMain\"> <div class=\"replyer\"> <img src=\""+data[i]['upath']+"\"/> <div class=\"r_content\">"+data[i]['uName']+":"+data[i]["acr_content"]+"</div> <span> <a onclick='dj(this)' id=\"getAttribute\" name=\""+data[i]["aco_id"]+"wocao"+data[i]["u_id"]+"name"+data[i]["uName"]+"name"+data[i]["uName"]+"\">回复</a> </span> <div id=\"nkt\">"+data[i]["reply_time"]+"</div> </div> </div>";
                                    }else {
                                        return "<div class=\"replyMain\"> <div class=\"replyer\"> <img src=\""+data[i]['upath']+"\"/> <div class=\"r_content\">"+data[i]['uName']+":"+data[i]["acr_content"]+"  //@"+data[i]["r_uName"]+"</div><span><a onclick='dj(this)' id=\"getAttribute\" name=\""+data[i]["aco_id"]+"wocao"+data[i]["u_id"]+"name"+data[i]["uName"]+"\">回复</a> </span> <div id=\"nkt\">"+data[i]["reply_time"]+"</div> </div> </div>";
                                    }
                                });
                            }
                            //alert(data[0]["a_id"]);
                        },
                        error: function (data) {
                            alert("出了点儿小问题！")
                        }
                    })
                    //alert(data[0]["a_id"]);
                },
                error: function (data) {
                    alert("回复失败！")
                }
            })
        })
        $("#content").blur(function () {
            if($("#content").val().trim()=="" || $("#content").val().trim()==null){
                $("#tips").html("在这里回复(80字符以内)");
                to_uid=null;
                uName=null;
            }
        })

        })
    function dj(a) {
        var exp=/^[0-9]*/;
        var tem=a.name.split("name")[0];
        to_uid=tem.split("wocao")[1];
        uName=a.name.split("name")[1];
        //alert(a.name);
        //alert(typeof (acoId));
        //aco_id=parseInt(a.name.match(exp));
        //to_uid=parseInt(a.name.match(exp2));
        //alert("请输入回复内容！");
        $("#content").focus();
    }
    $("#content").focus(function () {
        if (uName!=null){
            $("#tips").html("正在回复"+uName);
        }else{
            $("#tips").html("正在输入...");

        }
    });


</script>
</body>
</html>
