<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>查看用户</title>
    <%--引入basePath--%>
    <%@ include file="./../../common/basePath.jsp"%>
    <%--引入后台管理部分的公共头部--%>
    <%@ include file="./../../common/backManage/headBase.jsp"%>
    <%--引入bt-table--%>
    <%@ include file="./../../common/bt_table.jsp"%>
    <%--引入showUser页的资源--%>
    <link rel="stylesheet" href="static/css/backManage/showOrder.css">

</head>

<body>

<%--引入导航栏--%>
<%@ include file="./../../common/backManage/backNav.jsp"%>

<!-- 右侧主要内容开始 -->
<div class="right-content">
    <div class="right-content-wrap">



        <!-- 根据用户id查找用户 -->
        <!--一个折叠，帅选条件-->
        <div class="row">
            <div  class="row-wrap" style="padding-left:15px;padding-right:15px;">
                <div class="panel-group" id="accordion">
                    <div class="panel panel-default order-filter">
                        <div class="panel-heading">
                            <h4 class="panel-title">
                                    <span data-toggle="collapse" data-parent="#accordion"
                                          href="#userFlag" class="glyphicon glyphicon-filter">
                                    订单查找
                                    </span>
                            </h4>
                        </div>
                        <div id="userFlag" class="panel-collapse collapse in">
                            <div class="panel-body">
                                <!-- 内联样式 -->
                                <div class="form-inline" role="form" id="findOrder">
                                    <div class="form-group">
                                        <label>用户ID：</label>
                                        <input name="name" type="text" class="form-control">
                                    </div>
                                    &emsp;&emsp;
                                    <div class="form-group">
                                        <label>订单编号：</label>
                                        <input name="number" type="text" class="form-control">
                                    </div>
                                    &emsp;
                                    <button type="submit" class="btn btn-default">提交</button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>


        <!--一个表格，默认显示表的前10条记录-->
        <div class="panel panel-default order-info-table">
            <div class="panel-heading">
                <h3 class="panel-title">
                    <span class="glyphicon glyphicon-eye-open"></span>
                    订单信息
                </h3>

            </div>
            <div class="panel-body">

                <!--一个隐藏的消息提示，与用户表格结果有关-->
                <div id="orderQueryIssu" hidden="hidden"  class="alert alert-success">
                    <button type="button" class="close" data-dismiss="alert"
                            aria-hidden="true">
                        &times;
                    </button>
                    <span id="orderQueryIssu-main"></span>
                </div>
                <!-- 几个分类按钮（将订单分类） -->
                <div class="order-btns">
                    <button status="1" type="button" class="btn btn-default orderBtns">未处理订单</button>
                    <button status="2" type="button" class="btn btn-default orderBtns">待收货订单</button>
                    <button status="4" type="button" class="btn btn-default orderBtns">退货中订单</button>
                    <button status="3,5" type="button" class="btn btn-default orderBtns">已完结订单</button>
                </div>



                <!-- 显示用户信息的表格 -->
                <table id="orderInfoTable"></table>


            </div>
        </div>


    </div>
</div>
<!-- 右侧主要内容结束 -->

<!-- 一个关于发货以及查看物流的模态框 -->
<div class="modal fade" id="logModal" tabindex="-1" role="dialog" aria-labelledby="logModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="logModalLabel">发货信息</h4>
            </div>
            <div class="modal-body">

                <div class="form-group">
                    <label class="control-label">物流公司</label>
                    <input name="company" type="text" class="form-control" placeholder="请输入物流公司">
                </div>
                <div class="form-group">
                    <label class="control-label">物流单号</label>
                    <input name="waybillNum" type="text" class="form-control" placeholder="请输入物流单号">
                </div>

            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button id="postLog" type="button" class="btn btn-primary">提交</button>
            </div>
        </div>
    </div>
</div>




<!-- 提交筛选 -->
<script type="text/javascript">
    // order信息表格，以及订单操作
    function doTable(url,queryData){
        // alert(url);
        $('#orderInfoTable').bootstrapTable('destroy');   //动态加载表格之前，先销毁表格
        $("#orderInfoTable").bootstrapTable({ // 对应table标签的id
            url:url, // 获取表格数据的url
            method: "post",
            contentType:'application/json',
            cache: false, // 设置为 false 禁用 AJAX 数据缓存， 默认为true
            striped: true,  //表格显示条纹，默认为false
            pagination: true, // 在表格底部显示分页组件，默认false
            pageList: [10, 20], // 设置每页可显示的数据条数
            pageSize: 10, // 页面数据条数
            pageNumber: 1, // 首页页码
            sidePagination: 'server', // 设置为服务器端分页
            sortable: true,          //列排序
            sortName: 'o_status', // 要排序的字段
            sortOrder: 'asc', // 排序规则
            queryParams:function(params){
                //alert(JSON.stringify(queryData));
                var param = {
                    pageSize: params.limit, // 每页要显示的数据条数
                    offset: params.offset, // 每页显示数据的开始行号(0开始)
                    sort: params.sort, // 要排序的字段
                    sortOrder: params.order, // 排序规则
                };
                if(!jQuery.isEmptyObject(queryData)){
                    $.each(queryData,function(key,value){
                        param[key]=value;
                    });
                }
                // console.log(param);
                return param;
            },
            columns:
                [
                {
                    field: 'Number', // 返回json数据中的name
                    title: '订单编号', // 表格表头显示文字
                    align: 'center', // 左右居中
                    valign: 'middle', // 上下居中
                    width: '150',
                    }, {
                    field: 'Name',
                    title: '用户ID',
                    align: 'center',
                    valign: 'middle',
                    width: '80',
                }, {
                    field: 'ItemName',
                    title: '商品',
                    align: 'center',
                    valign: 'middle',
                    width: '150',
                }, {
                    field: 'Address',
                    title: '地址',
                    align: 'center',
                    valign: 'middle',
                    width: '200',
                },{
                    field: 'Note',
                    title: '备注',
                    align: 'center',
                    valign: 'middle',
                    width: '200',
                },{
                    field: 'createTime',
                    title: '创建时间',
                    align: 'center',
                    valign: 'middle',
                    width: '80',
                },{
                    field: 'totalPrice',
                    title: '订单总价',
                    align: 'center',
                    valign: 'middle',
                }, {
                    visible: false,
                    field: 'oId',
                    title: '订单id',
                    align: 'center',
                    valign: 'middle',
                    width: '100',
                }, {
                    field: 'Status',
                    title: '状态',
                    align: 'center',
                    valign: 'middle',
                    width: '100',
                    formatter:function(value, row, index){
                        return value=="0"? "未支付":value=="1"? "未处理":value=="2"? "待收货":value=="3"? "收货完成":value=="4"? "退货中":value=="5"? "退货完成":value=="6"? "审核中":value=="7"? "申请通过":"申请失败";
                    }
                }, {
                    title: "操作",
                    align: 'center',
                    valign: 'middle',
                    width: '70',
                    formatter:function(value, row, index){
                        var judgeId=row.Status;
                        var txt="无操作";
                        if (judgeId==1) {
                            txt="发货";
                        }else if(judgeId==2){
                            txt="查看发货物流";
                        }else if(judgeId==4){
							txt="查看退货物流";
						}
                        return "<button class='btn btn-default btn-xs doOrder' judgeId='"+judgeId+"' oId='"+row.oId+"'><span class='glyphicon glyphicon-exclamation-sign'></span>"+txt+"</button>";
                       
                    }
                }
                ],
            onLoadError: function(res){  //加载失败时执行
                console.info("加载数据失败"+res);
                $("#orderQueryIssu-main").html("查询为空！");
                $("#orderQueryIssu").removeAttr("hidden");

            }


        })
    }
    //未定位查询前先设定表格显示所有
    doTable("BackManageOrder/seeAllOrders");


    //订单查询只允许单条件查询
    $("input[name='name']").unbind('blur').bind('blur', function(){
        if($(this).val()!=""){
            //将另一个input设为disable
            $("input[name='number']").val("");
            $("input[name='number']").attr("disabled","disabled");
        }else{
            $("input[name='number']").removeAttr("disabled");
        }
    });
    $("input[name='number']").unbind('blur').bind('blur', function(){
        if($(this).val()!=""){
            //将另一个input设为disable
            $("input[name='name']").val("");
            $("input[name='name']").attr("disabled","disabled");
        }else{
            $("input[name='name']").removeAttr("disabled");
        }
    });
    

    // 定向搜索订单
    $("#findOrder button").click(function(){
        var name=$.trim($("#findOrder input[name='name']").val());
        var number=$.trim($("#findOrder input[name='number']").val());
        var data={};
        if(name!=""){
            data['name']=name;
            //根据登录id查询订单的接口
            var url="BackManageOrder/seeAllOrderByuId";
            // console.log(data);
            doTable(url,data);
        }else if(number!=""){
            data['trade_number']=number;
            //根据订单编号查询订单的接口
            var url="BackManageOrder/seeOrder";
            // console.log(data);
            doTable(url,data);
        }
        if(jQuery.isEmptyObject(data)){
            $("#orderQueryIssu-main").html("查询为空！");
            $("#orderQueryIssu").removeAttr("hidden");
            alert("未填写搜索内容！");
        }
    });



	//一个缓存oId的变量
    var oIdCahche="";
    //一个缓存那四个按钮发送的data的变量
    var dataCache;


    //点击了订单的操作时
    $("#orderInfoTable").on("click",".doOrder",function(){
        oIdCahche=$(this).attr("oId");
        var judgeId=$(this).attr("judgeId");
        if(judgeId==2){
            //查看发货物流
            $("#logModalLabel").html("查看发货物流");
            //ajax发送请求发货物流的信息
            $.ajax({
                url : 'BackManageOrder/checkLogistics',
                type : 'POST',
                data : {"oId":oIdCahche},
                success : function(data) {
                    // console.log(data);
                    // data=JSON.parse(data);
                    if(data['result']){
                        //成功后
                        $("#logModal input[name='company']").val(data['company']);
                        $("#logModal input[name='waybillNum']").val(data['waybillNum']);
                        $("#logModal input").attr("disabled","disabled");
                    }else{
                        if(data['isLogin']==false){
                            window.location.href="SuperAdmin/login";
                            return;
                        }
                        alert("意外错误！请重试！");
                    }
                },
                error : function(data){
                    alert("请检查网络！");
                }
            });
        }else if(judgeId==4){
            //查看退货物流
            $("#logModalLabel").html("查看退货物流");
            //ajax发送请求退货物流的信息
            $.ajax({
                url : 'BackManageOrder/checkLogistics',
                type : 'POST',
                data: {"oId":oIdCahche},
                success : function(data) {
                    // console.log(data);
                    // data=JSON.parse(data);
                    if(data['result']){
                        //成功后,这些物流信息你就返回：物流单号和物流公司信息就行了
                        $("#logModal input[name='company']").val(data['company']);
                        $("#logModal input[name='waybillNum']").val(data['waybillNum']);
                        $("#logModal input").attr("disabled","disabled");
                    }else{
                        if(data['isLogin']==false){
                            window.location.href="SuperAdmin/login";
                            return;
                        }
                        alert("意外错误！请重试！");
                    }
                },
                error : function(data){
                    alert("请检查网络！");
                }
            });
        }
        if(judgeId==1||judgeId==2||judgeId==4){
            $("#logModal input").removeAttr("disabled");
            $('#logModal').modal('toggle');
        }
    });


	//点击了那四个按钮时
    $(".order-btns").on("click",".orderBtns",function(){
        var status=$(this).attr("status");
        var url="BackManageOrder/seeAllOrdersStatus";
        var data={};
        data['status']=status.toString();
		dataCache=data;
        doTable(url,data);
    });

	//点击了模态框确认时
    $("#postLog").click(function(){
        //发货
        //获取模态框的input的值
        var data={};
        data['oId']=oIdCahche;
        data['company']=$("#logModal input[name='company']").val();
        data['waybillNum']=$("#logModal input[name='waybillNum']").val();
        console.log(data);
        var postFlag=true;
        $.each(data,function(key,value){
            if(value==""){
                postFlag=false;
            }
        });
        if(postFlag){
             //ajax发送发货请求
            $.ajax({
                url : 'BackManageOrder/updateOrderStatus',
                data:data,
                type : 'POST',
                success : function(data) {
                    data=JSON.parse(data);
                    if(data['result']){
                        //判断刷新哪个区间的表格
                        if(jQuery.isEmptyObject(dataCache)){
                            //刷新无选择条件时的表格
                            $('#logModal').modal('hide');
                            doTable("BackManageOrder/seeAllOrders");
                        }else{
                            $('#logModal').modal('hide');
                            doTable("BackManageOrder/seeAllOrdersStatus",dataCache);
                        }
                        
                    }else{
                        if(data['isLogin']==false){
                            window.location.href="SuperAdmin/login";
                        }
                        alert("意外错误！请重试！");
                    }
                },
                error : function(data){
                    alert("请检查网络！");
                }
            });
        }else{
            alert("请检查是否信息完整！");
        }
       
    });

</script>
</body>

</html>
