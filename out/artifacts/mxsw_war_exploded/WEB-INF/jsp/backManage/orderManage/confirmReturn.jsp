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
                                退货申请查找
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
                    退货申请信息
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



                <!-- 显示退货申请请求信息的表格 -->
                <table id="returnTable"></table>


            </div>
        </div>


    </div>
</div>
<!-- 右侧主要内容结束 -->



<!-- 提交筛选 -->
<script type="text/javascript">
    //退货申请表格
    function doTable(url,queryData){
        // alert(url);
        $('#returnTable').bootstrapTable('destroy');   //动态加载表格之前，先销毁表格
        $("#returnTable").bootstrapTable({ // 对应table标签的id
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
            sortName: 'apply_id', // 要排序的字段
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
                // alert(JSON.stringify(param));
                console.log(param);
                return param;
            },
            columns:
                [
                {
					field: 'applyId', // 返回json数据中的name
					title: '申请表的id', // 表格表头显示文字
					align: 'center', // 左右居中
					valign: 'middle', // 上下居中
					visible: false,
                    }, {
                    field: 'name',
                    title: '用户ID',
                    align: 'center',
                    valign: 'middle',
                    width: '100',
                }, {
                    field: 'number',
                    title: '订单编号',
                    align: 'center',
                    valign: 'middle',
                    width: '100',
                }, {
                    field: 'create_time',
                    title: '创建时间',
                    align: 'center',
                    valign: 'middle',
                    width: '100',
                }, {
                    field: 'item',
                    title: '商品信息',
                    align: 'center',
                    valign: 'middle',
                    width: '150',
                }, {
                    field: 'reason',
                    title: '申请理由',
                    align: 'center',
                    valign: 'middle',
                    width: '200',
                }, {
                    field: 'status',
                    title: '状态',
                    align: 'center',
                    valign: 'middle',
                    width: '100',
                    formatter:function(value, row, index){
                        console.log(value);
                        return value=="0"? "未处理":value=="1"? "拒绝":"通过";
                    }
                }, {
                    title: "操作",
                    align: 'center',
                    valign: 'middle',
                    width: '70',
                    formatter:function(value, row, index){
                        //btn btn-default btn-xs refuseApply
                        if(row.status==0){
                            return "<button class='btn btn-default btn-xs refuseApply' status='1' applyId='"+row.applyId+"'>拒绝"+"</button><br/><button style='margin-top:8px' class='btn btn-default btn-xs okApply' status='2' applyId='"+row.applyId+"'>通过"+"</button>";
                        }
                        return "<button class='btn btn-default btn-xs '>无</button>";

                        
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
    //查看所有退货申请的接口
    doTable("BackManageOrder/CheckReturn");

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
            var url="BackManageOrder/seeAllOrderReturnByuId";
            // console.log(data);
            doTable(url,data);
        }else if(number!=""){
            data['trade_number']=number;
            //根据订单编号查询订单的接口
            var url="BackManageOrder/seeOrderReturn";
            // console.log(data);
            doTable(url,data);
        }
        if(jQuery.isEmptyObject(data)){
            alert("未填写搜索内容！");
        }
    });

    //拒绝申请,通过申请
	$("#returnTable").on("click",".refuseApply,.okApply",function(){
		var data={};
		data['status']=$(this).attr("status");
		data['applyId']=$(this).attr("applyId");
		var confirmPost=confirm("确认此操作？");
		if(confirmPost==true){
            //ajax发送数据
            $.ajax({
                url : 'BackManageOrder/updateApplyStatus',
                data:data,
                type : 'POST',
                success : function(data) {
                    data=JSON.parse(data);
                    if(data['result']){
                        //后台受理成功，刷新订单
                        alert("操作成功！");
                        doTable("BackManageOrder/CheckReturn");
                    }else{
                        if(data['isLogin']==false){
                            window.location.href="SuperAdmin/login";
                            return;
                        }
                        console.log(data);
                        alert("失败！");
                    }
                },
                error : function(data){
                    alert("请检查网络！");
                }
            });
        }

	});
  
	
</script>

</body>

</html>
