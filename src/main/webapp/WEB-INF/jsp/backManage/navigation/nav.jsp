<%--
  Created by IntelliJ IDEA.
  User: ysjba
  Date: 2020/3/15
  Time: 16:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>墨香书屋后台管理首页</title>
    <%--引入basePath--%>
    <%@ include file="./../../common/basePath.jsp"%>
    <%--引入后台管理部分的公共头部--%>
    <%@ include file="./../../common/backManage/headBase.jsp"%>
    <script src="static/js/plugins/Chart.bundle.min.js"></script>

</head>
<body>
    <%--引入导航栏--%>
    <%@ include file="./../../common/backManage/backNav.jsp"%>


    <%--此页面主体内容--%>
    <!-- 右侧主要内容开始 -->
    <div class="right-content">
    <div class="right-content-wrap">

        <!--提示时间-->
        <div class="alert alert-success which-one">
        <!--此处应当第一次从服务器获取身份和时间后，再由js动态刷新时间(假时间，减轻服务器压力)-->
        <a href="#" class="close" data-dismiss="alert">&times;</a>
        <strong>欢迎管理员/雇员！</strong>当前时间为：北京时间 <span id="date"></span>
        </div>


        <!--几个显示一些数据的方块-->
        <div class="row">
            <div class="row-wrap">
                <div class="show-info">
                    <!--一个字体图标-->
                    <div class="sel-glyphicon glyphicon-user"></div>
                    <!--统计数据-->
                    <div class="main-info">
                        <h4>总注册用户数</h4>
                        <h2>3423</h2>
                    </div>
                </div>


                <div class="show-info">
                    <!--一个字体图标-->
                    <div class="sel-glyphicon glyphicon-home"></div>
                    <!--统计数据-->
                    <div class="main-info">
                        <h4>总雇员数</h4>
                        <h2>23</h2>
                    </div>
                </div>

                <div class="show-info">
                    <!--一个字体图标-->
                    <div class="sel-glyphicon glyphicon-ok"></div>
                    <!--统计数据-->
                    <div class="main-info">
                        <h4>总成交订单数</h4>
                        <h2>235</h2>
                    </div>
                </div>

                <div class="show-info">
                    <!--一个字体图标-->
                    <div class="sel-glyphicon glyphicon-briefcase"></div>
                    <!--统计数据-->
                    <div class="main-info">
                        <h4>总商品数</h4>
                        <h2>540</h2>
                    </div>
                </div>
            </div>
		</div>


		<div class="row">
			<div class="row-wrap">
				<!--一个统计图表（本周的成交额）开始-->
				<div class="chart-container">
					<!--可以指定画布的大小，实际中通过外层的div指定width和height感觉好些，还具备响应式呢-->
					<canvas id="income-chart"></canvas>
				</div>
				<!--一个统计图表（本周的成交额）结束-->

				<!--一个统计（购买量趋势）开始-->
				<div class="chart-container">
					<!--可以指定画布的大小，实际中通过外层的div指定width和height感觉好些，还具备响应式呢-->
					<canvas id="buycount-chart"></canvas>
				</div>
				<!--一个统计（购买量趋势）结束-->
			</div>
		</div>

    </div>
    </div>
    <!-- 右侧主要内容结束 -->
	
	
	<!--直接将此js用jsp处理，可以省却写ajax请求-->
    <script type="text/javascript">
        $(document).ready(function() {
            var d=new Date();
            var t=d.toLocaleString();
            //alert(t);
            $("#date").html(t);
        })


        //本周内的成交额的chart相关调用
        var popCanvas = $("#income-chart");
        var popCanvas = document.getElementById("income-chart").getContext("2d");
        var barChart = new Chart(popCanvas, {
            type: 'bar',
            data: {
                labels: ["周一", "周二", "周三", "周四", "周五", "周六", "星期天"],
                datasets: [{
                    label: '成交额',
                    //此处处理后台传来的数据，觉得还是直接用jsp表达式渲染个list方便哈
                    //需要的数据：flaot型的list，或者数字字符串的list
                    data: [200, 400, 600, 800, 1000, 1500, 2000],
                    backgroundColor:'rgba(75, 192, 192, 0.6)'
                }]
            },
            options:{
                title: {
                    display: true,
                    text: '本周的成交金额'
                }
            }
        });


        //购买量趋势的chart的相关调用
        var popCanvas1 = $("#buycount-chart");
        var popCanvas1 = document.getElementById("buycount-chart").getContext("2d");
        var barChart1 = new Chart(popCanvas1, {
            type: 'line',
            data: {
                labels: ["一月", "二月", "三月", "四月", "五月", "六月", "七月","八月","九月","十月","十一月","十二月"],
                datasets: [
                {
                    label: '今年销售量',
                    //此处处理后台传来的数据，觉得还是直接用jsp表达式渲染个list方便哈
                    //需要的数据：float型的list，或者数字字符串的list
                    data: [200, 300, 240, 140.2, 300.5, 1500, 2000,3000,1000,500,2000,1200],
                    backgroundColor:'rgba(75, 192, 192, 0.6)'
                },
                {
                        label : "去年销售量",
                        //此处处理后台传来的数据，觉得还是直接用jsp表达式渲染个list方便哈
                        //需要的数据：float型的list，或者数字字符串的list
                        data : [200, 400, 600, 800, 1000, 1500, 1500,800,1200,3000,3000,2300]
                    }
                ]
            },
            options:{
                title: {
                    display: true,
                    text: '商品销量趋势'
                }
            }
        });



    </script>




</body>
</html>
