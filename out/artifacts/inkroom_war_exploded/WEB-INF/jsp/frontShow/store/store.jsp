<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>导航栏demo</title>
    <%--引入basePath--%>
    <%@ include file="./../../common/basePath.jsp"%>
    <%--引入前台展示页部分的公共头部--%>
    <%@ include file="./../../common/frontShow/headBase.jsp"%>
    <%--引入商展页的资源文件--%>
    <link rel="stylesheet" type="text/css" href="static/css/store.css">
    <script src="static/js/store.js"></script>
    <!--插件引入-->
    <!--一个滑块插件---开始0-->
    <link rel="stylesheet" type="text/css" href="static/css/plugins/normalize.min.css">
    <link rel="stylesheet" type="text/css" href="static/css/plugins/ion.rangeSlider.css">
    <link rel="stylesheet" type="text/css" href="static/css/plugins/ion.rangeSlider.skinFlat.css">
    <script type="text/javascript" src="static/js/plugins/ion.rangeSlider.js"></script>
    <!--一个滑块插件---结束0-->
</head>

<body>
    <!-- 外层包装开始 -->
    <div class="wrapper">

        <%--引入两个导航栏--%>
        <%@ include file="./../../common/frontShow/topNav.jsp"%>
        <%@ include file="./../../common/frontShow/leftNav.jsp"%>

        <!--右侧的显示内容开始-->
        <%@ include file="./../../common/frontShow/storeMain.jsp"%>
        <!--右侧的显示内容结束-->
    </div>
    <!-- 外层包装结束 -->
    
	<!-- 模态框开始 （商品详情的模态框）-->
    <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-body">
                    <div class="good-info-img">
                        <!-- 模态框轮播部分 -->
                        <div id="carousel-example-generic" class="carousel slide" data-ride="carousel">
                            <!-- Indicators -->
                            <ol class="carousel-indicators">
                                
                            </ol>

                            <!-- Wrapper for slides -->
                            <div class="carousel-inner" role="listbox">
                                
                            </div>

                            <!-- Controls -->
                            <a class="left carousel-control" href="#carousel-example-generic" role="button" data-slide="prev">
                                <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
                                <span class="sr-only">Previous</span>
                            </a>
                            <a class="right carousel-control" href="#carousel-example-generic" role="button" data-slide="next">
                                <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
                                <span class="sr-only">Next</span>
                            </a>
                        </div>
                    </div>
                    <div class="good-info-document">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                        <h1 id="modal_item_name"></h1>
                        <div class="base-info">
                            <!--采用表格的方式展示商品的信息-->
                            <table>
                                <tr>
                                    <td>价格：<span id="modal_item_price"></span>元</td>
                                    <td>邮费：<span id="modal_item_postage"></span>元</td>
                                </tr>
                                <tr>
                                    <td>新旧程度：<span id="modal_item_oldLevel"></span>成新</td>
                                    <td>新书比价：<span id="modal_item_compare"></span></td>
                                </tr>
                                <tr>
                                    <td>库存：<span id="modal_item_count"></span></td>
                                    <td>发货地点：<span id="modal_item_place"></span></td>
                                </tr>
                                <tr>
                                    <td>作者：<span id="modal_item_author"></span> </td>
                                    <td>出版社：<span id="modal_item_publish"></span></td>
                                </tr>
                                <tr>
                                    <td>出版时间：<span id="modal_item_publishTime"></span></td>
                                    <td>ISBN:<span id="modal_item_ISBN"></span></td>
                                </tr>
                            </table>


                        </div>
                        <div class="detail-info" style="border-bottom:0px;">
                            <h2>概述</h2>
                            <p id="modal_item_desc">
                                
                            </p>
                        </div>
<%--                        <div class="opera">--%>
<%--                            <button class="btn btn-primary buyNow">立即购买</button>--%>
<%--                            <button class="btn btn-success">加入购物车</button>--%>
<%--                        </div>--%>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- 模态框结束 -->

    <!--模态开始（一个用于选择订单的模态框）-->
    <div style="width:30%;margin-left:auto;margin-right:auto;" class="modal fade" id="orderModal" tabindex="-1" role="dialog" aria-labelledby="orderLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                        &times;
                    </button>
                    <h4 class="modal-title" id="orderLabel">
                        下单
                    </h4>
                </div>
                <div class="modal-body" style="padding:10px;">
                    <div style="margin-left: auto;margin-right: auto;">
                        <div class="form-group">
                            <label>购买数量</label>
                            <div class="input-group input-group-sm" style="width:65%;">
                                <span id="countCut" class="input-group-addon">-</span>
                                <input id="orderItemCount" type="number" class="form-control" value="5">
                                <span id="countAdd" class="input-group-addon">+</span>
                            </div>
                        </div>
                        <div class="form-group">
                            <label>备注</label>
                            <textarea id="orderNote" class="form-control" row="3"></textarea>
                        </div>
                        <div class="form-group">
                            <label>收货人</label>
                            <select id="orderAddr" class="form-control"></select>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">关闭
                    </button>
                    <button id="orderPost" type="button" class="btn btn-primary">
                        提交订单
                    </button>
                </div>
            </div><!-- /.modal-content -->
        </div><!-- /.modal -->
    </div>




</body>

</html>