<%--
  Created by IntelliJ IDEA.
  User: 10904
  Date: 2019/8/6
  Time: 15:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <%--引入basePath--%>
    <%@ include file="./WEB-INF/jsp/common/basePath.jsp"%>
    <%--引入前台展示页部分的公共头部--%>
    <%@ include file="./WEB-INF/jsp/common/frontShow/headBase.jsp"%>
    <link rel="stylesheet" href="./static/css/plugins/daterangepicker.min.css">
    <script type="text/javascript" src="./static/js/plugins/daterangepicker.js"></script>

</head>
<body>

<div class="search-group">
    <div id="reportrange" class="pull-right" style="background: #fff; cursor: pointer; padding: 5px 10px; border: 1px solid #ccc; width: 100%">
        <i class="glyphicon glyphicon-calendar fa fa-calendar"></i>&nbsp;
        <span>请选择日期范围</span><b class="caret pull-right"></b>
    </div>
</div>

</body>
<script language="JavaScript">
    const daterange = () => {

        var start = moment().startOf('month');//当前月份1号
        var end = moment();//当前日期


        function cb(start, end) {
            $('#reportrange span').html(start.format('YYYY-MM-DD') + ' ~ ' + end.format('YYYY-MM-DD'));
        }

        $('#reportrange').daterangepicker({
            startDate: start,
            endDate: end,
            autoUpdateInput: false,
            locale: {
                applyLabel: '确认',
                cancelLabel: '清空',
                fromLabel: '从',
                toLabel: '到',
                weekLabel: 'W',
                customRangeLabel: '自定义',
                daysOfWeek: ["日", "一", "二", "三", "四", "五", "六"],
                monthNames: ["一月", "二月", "三月", "四月", "五月", "六月", "七月", "八月", "九月", "十月", "十一月", "十二月"],
            },

            ranges: {
                '今天': [moment(), moment()],
                '昨天': [moment().subtract(1, 'days'), moment().subtract(1, 'days')],
                '前7天': [moment().subtract(6, 'days'), moment()],
                '前30天': [moment().subtract(29, 'days'), moment()],
                '本月': [moment().startOf('month'), moment().endOf('month')],
                '上个月': [moment().subtract(1, 'month').startOf('month'), moment().subtract(1, 'month').endOf('month')]
            }
        }, cb);



        $('#reportrange').on('apply.daterangepicker', function (e, picker) {
            searchSubmit();//ajax请求
        });

        $('#reportrange').on('cancel.daterangepicker', function (e, picker) {
            $(this).html(`<i class="glyphicon glyphicon-calendar fa fa-calendar"></i>&nbsp;<span>请选择日期范围</span><b class="caret pull-right"></b>`);
            searchSubmit();//ajax请求
        });

        cb(start, end);
    };
</script>

</html>
