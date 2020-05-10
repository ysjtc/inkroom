<%--
  Created by IntelliJ IDEA.
  User: ysjba
  Date: 2020/4/26
  Time: 10:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>帖子管理</title>
    <%--引入basePath--%>
    <%@ include file="./../../common/basePath.jsp"%>
    <%--引入后台管理部分的公共头部--%>
    <%@ include file="./../../common/backManage/headBase.jsp"%>
    <%--引入bt-table--%>
    <%@ include file="./../../common/bt_table.jsp"%>
    <%--引入articleManage页的资源--%>
    <link rel="stylesheet" href="static/css/backManage/showArticles.css">

</head>

<body>

<%--引入导航栏--%>
<%@ include file="./../../common/backManage/backNav.jsp"%>

<!-- 右侧主要内容开始 -->
<div class="right-content">
    <div class="right-content-wrap">
        <div id="orderQueryIssu" hidden="hidden"  class="alert alert-success">
            <button type="button" class="close" data-dismiss="alert"
                    aria-hidden="true">
                &times;
            </button>
            <span id="orderQueryIssu-main"></span>
        </div>
        <table id="articleInfo" align="center"></table>
    </div>
    <div class="modal fade" id="detail" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                        &times;
                    </button>
                    <h7 class="modal-title" id="myModalLabel">
                        文章详情
                    </h7>
                </div>
                <div class="modal-body">
                    <span id="display"></span>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
<script language="JavaScript">

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
    window.onload=doTable;
    var t1Init=new Object();
    var t2Init=new Object();
    function doTable(){
        // alert(url);
        $('#articleInfo').bootstrapTable('destroy');   //动态加载表格之前，先销毁表格
        $("#articleInfo").bootstrapTable({ // 对应table标签的id
            url: 'ArticleBackManage/query/allArticles', // 获取表格数据的url
            method: "post",
            contentType:'application/json',
            cache: false, // 设置为 false 禁用 AJAX 数据缓存， 默认为true
            detailView: true, //开启父子表
            striped: true, //是否显示行间隔色
            pagination: true,//显示分页
            sidePagination: "client",
            pageNumber: 1,
            pageSize: 15,
            pageList: [10, 25],
            columns:
                [
                    {
                        field: 'u_name', // 返回json数据中的name
                        title: '发布者', // 表格表头显示文字
                        align: 'center', // 左右居中
                        valign: 'middle', // 上下居中
                        width: '80',
                    }, {
                    field: 'title',
                    title: '标题',
                    align: 'center',
                    valign: 'middle',
                    width: '120',
                }, {
                    field: 'content',
                    title: '内容',
                    align: 'left',
                    valign: 'middle',
                    width: '250',
                    formatter: function (value,row,index) {
                        return "<span class='view-text'>"+value+"</span><button  class='btn btn-default btn-xs doOrder' data-toggle='modal' value='"+value+"' data-target='#detail'>...</button>"
                    }
                }, {
                    field: 'publish_time',
                    title: '发布日期',
                    align: 'center',
                    valign: 'middle',
                    width: '140',
                    formatter:function (value, row, index) {
                        return timestampToTime(value);
                    }
                },{
                    field: 'page_view',
                    title: '浏览量',
                    align: 'center',
                    valign: 'middle',
                    width: '30',
                },{
                    field: 'praise_count',
                    title: '点赞数',
                    align: 'center',
                    valign: 'middle',
                    width: '30',
                },{
                    field: 'summary',
                    title: '评论（回复）',
                    align: 'center',
                    valign: 'middle',
                    width: '30',
                },{
                    title: "操作",
                    align: 'center',
                    valign: 'middle',
                    width: '70',
                    formatter:function(value, row, index) {
                        return "<input type='button' onclick='delete1("+row.a_id+",this)' class='btn btn-default btn-xs doOrder' value='删除'/>";
                    }
                }
                ],
            onLoadError: function(res){  //加载失败时执行
                console.info("加载数据失败"+res);
                $("#orderQueryIssu-main").html("查询为空！");
                $("#orderQueryIssu").removeAttr("hidden");

            },
            onExpandRow: function (index, row, $detail) {
                t1Init.initSubTable(index, row, $detail);
            }
        })
    }
    t1Init.initSubTable = function (index, row, $detail) {
        var parentid = row.a_id;
        var cur_table = $detail.html('<table class="child-table"></table>').find('table');
        //alert(row.a_id);
        $(cur_table).bootstrapTable({
            url: 'ArticleBackManage/query/Comments',
            method: 'get',
            detailView: true, //开启字表的字表
            queryParams: {a_id: parentid},
            ajaxOptions: {a_id: parentid},
            uniqueId: "aco_id",
            striped: true, //是否显示行间隔色
            pagination: true,//显示分页
            sidePagination: "client",
            pageNumber: 1,
            pageSize: 15,
            pageList: [10, 25],
            columns: [{
                field: 'u_name',
                title: '评论人'
            }, {
                field: 'ac_content',
                title: '评论内容'
            }, {
                field: 'create_time',
                title: '日期',
                formatter: function (value,row,index) {
                    return timestampToTime(value);
                }
            }, {
                field: 'r_count',
                title: '回复数'
            },{
                title: "操作",
                align: 'center',
                valign: 'middle',
                width: '30',
                formatter:function(value, row, index) {
                    return "<input type='button' onclick='delete2("+row.aco_id+",this)' class='btn btn-default btn-xs doOrder' value='删除'/>";
                }
            }],
            //无线循环取子表，直到子表里面没有记录
            onExpandRow: function (index, row, $Subdetail) {
                t2Init.initSubTable(index, row, $Subdetail);
            }
        });
    }
    t2Init.initSubTable = function (index, row, $detail) {
        var parentid = row.aco_id;
        //alert(parentid);
        var cur_table = $detail.html('<table class="child2-table"></table>').find('table');
        //alert(row.a_id);
        $(cur_table).bootstrapTable({
            url: 'ArticleBackManage/query/Reply',
            method: 'get',
            queryParams: {aco_id: parentid},
            ajaxOptions: {aco_id: parentid},
            uniqueId: "acr_id",
            striped: true, //是否显示行间隔色
            pagination: true,//显示分页
            sidePagination: "client",
            pageNumber: 1,
            pageSize: 15,
            pageList: [10, 25],
            columns: [{
                field: 'uName',
                title: '回复人'
            }, {
                field: 'acr_content',
                title: '回复内容'
            }, {
                field: 'reply_time',
                title: '日期',
                formatter: function (value, row, index) {
                    return timestampToTime(value);
                }
            }, {
                field: 'r_uName',
                title: '回复目标'
            },{
                title: "操作",
                align: 'center',
                valign: 'middle',
                width: '30',
                formatter:function(value, row, index) {
                    return "<input type='button' onclick='delete3("+row.acr_id+",this)' class='btn btn-default btn-xs doOrder' value='删除'/>";
                }
            }],
            //无线循环取子表，直到子表里面没有记录
            onExpandRow: function (index, row, $Subdetail) {
                //initSubTable(index, row, $Subdetail);
            }
        });
    }
    //删除文章
    function delete1(aId,t) {
        if(!confirm("确定删除吗？这将删除此文章及该文章下的所有评论与回复")){
            return false;
            //t.parentNode.parentNode.parentNode.removeChild(t.parentNode.parentNode);
        }
        $.ajax({
            url: 'ArticleBackManage/delete/Article',
            data:{a_id:aId},
            success: function (data) {
                if (data){
                    alert("删除成功！");
                    t.parentNode.parentNode.parentNode.removeChild(t.parentNode.parentNode);
                }else {
                    alert("该记录已删除，请勿重复操作")
                }
            },
            error:function () {
                alert("删除失败，请稍后再试");
            }
        })
    }
    //删除评论
    function delete2(acoId,t) {
        if(!confirm("确定删除吗？这将删除此评论及该评论下的所有回复")){
            return false;
        }
        $.ajax({
            url: 'ArticleBackManage/delete/Comment',
            data:{aco_id:acoId},
            success: function (data) {
                if (data){
                    alert("删除成功！");
                    t.parentNode.parentNode.parentNode.removeChild(t.parentNode.parentNode);
                }else {
                    alert("该记录已删除，请勿重复操作")
                }
            },
            error:function () {
                alert("删除失败，请稍后再试");
            }
        })
    }
    //删除回复
    function delete3(acrId,t) {
        if(!confirm("确定删除吗？这将删除此回复")){
            return false;
        }
        $.ajax({
            url: 'ArticleBackManage/delete/Reply',
            data:{acr_id:acrId},
            success: function (data) {
                if (data){
                    alert("删除成功！");
                    t.parentNode.parentNode.parentNode.removeChild(t.parentNode.parentNode);
                }else {
                    alert("该记录已删除，请勿重复操作")
                }
            },
            error:function () {
                alert("删除失败，请稍后再试");
            }
        })
    }

    $("#articleInfo").on("click","button",function () {
        $("#display").html($(this).val())
    })
</script>
</html>
