$(document).ready(function() {

    //渲染显示订单的表格
    function doTable(url){
        // alert(url);
        $('#userOrderInfoTable').bootstrapTable('destroy');   //动态加载表格之前，先销毁表格
        $("#userOrderInfoTable").bootstrapTable({ // 对应table标签的id
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
            sortName: 'o_id', // 要排序的字段
            sortOrder: 'asc', // 排序规则
            queryParams:function(params){
                //alert(JSON.stringify(queryData));
                var param = {
                    pageSize: params.limit, // 每页要显示的数据条数
                    offset: params.offset, // 每页显示数据的开始行号(0开始)
                    sort: params.sort, // 要排序的字段
                    sortOrder: params.order, // 排序规则
                };
                return param;
            },
            columns:
                [
                {
					field: 'oId',
					title: 'ID', 
					align: 'center', 
					valign: 'middle', 
					width: '80',
					visible:false
                    }, {
                    field: 'oName',
                    title: '订单名称',
                    align: 'center',
                    valign: 'middle',
                    width: '100',
                }, {
                    field: 'Number',
                    title: '订单编号',
                    align: 'center',
                    valign: 'middle',
                    width: '150',
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
					width:'80',
                },{
                    field: 'totalPrice',
                    title: '订单总价',
                    align: 'center',
                    valign: 'middle',
                },{
                    field: 'Status',
                    title: '状态',
                    align: 'center',
                    valign: 'middle',
					width:'80',
                    formatter:function(value, row, index){
                        return value=="0"? "未支付":value=="1"? "未处理":value=="2"? "待收货":value=="3"? "收货完成":value=="4"? "退货中":value=="5"? "退货完成":value=="6"? "审核中":value=="7"? "申请通过":"申请失败";
                    }
                }, {
                    title: "操作",
                    align: 'center',
                    valign: 'middle',
                    width: '70',
                    formatter:function(value, row, index){
						var statusId=row.Status;
						if(statusId==0){
							return "<button class='btn btn-default btn-xs delOrder' oId='"+row.oId+"'><span class='glyphicon glyphicon-exclamation-sign'></span>订单取消</button><br/><button style='margin-top:7px' class='btn btn-default btn-xs payOrder' body='"+row.Note+"' total_amount='"+row.totalPrice+"' subject='"+row.oName+"' Number='"+row.Number+"'><span class='glyphicon glyphicon-ok'></span>订单付款</button>";
						}else if(statusId==2){
							return "<button class='btn btn-default btn-xs showLog' oId='"+row.oId+"'><span class='glyphicon glyphicon-eye-open'></span>查看物流</button><br/><button style='margin-top:7px' class='btn btn-default btn-xs confirmOrder' oId='"+row.oId+"'><span class='glyphicon glyphicon-ok'></span>确认收货</button><br/><button style='margin-top:7px' class='btn btn-default btn-xs rejectLog' oId='"+row.oId+"'><span class='glyphicon glyphicon-ban-circle'></span>拒收订单</button>";
						}else if(statusId==1||statusId==3||statusId==8){
							return "<button class='btn btn-default btn-xs applayLog' oId='"+row.oId+"'><span class='glyphicon glyphicon-edit'></span>退换申请</button>";
						}else if(statusId==7){
							return "<button class='btn btn-default btn-xs returnLog' oId='"+row.oId+"'>填写物流</button>";
						}else{
							return "<button class='btn btn-default btn-xs' oId='"+row.oId+"'><span class='glyphicon glyphicon-exclamation-sign'></span>无操作</button>";
						}

                        
                    }
                }
                ],

        })
    }
    //未定位查询前先设定表格显示所有
    doTable("FrontManageOrder/seeAllOrder");
	
	
	//取消订单时
	$("#userOrderInfoTable").on("click",".delOrder",function(){
		var oId=$(this).attr("oId");
		var data={};
		data['oId']=oId;
		var confirmPost=confirm("确定取消此订单？");
		if(confirmPost==true){
			//ajax发送订单取消的请求
			$.ajax({
					url : 'FrontManageOrder/cancelOrder',
					data:data,
					type : 'POST',
					success : function(data) {
						data=JSON.parse(data);
						if(data['result']){
							//取消成功后
							alert("订单取消成功！");
							doTable("FrontManageOrder/seeAllOrder");
						}else{
							if(data['isLogin']==false){
								window.location.href="FrontForward/loginMain";
							}
							alert("取消失败，请重试！");
						}
					},
					error : function(data){
						alert("请检查网络！");
					}
				});
		}
	});
	
	
	//结算订单时
	$("#userOrderInfoTable").on("click",".payOrder",function(){
		var data={};
		data['out_trade_no']=$(this).attr("Number");
		data['subject']=$(this).attr("subject");
		data['total_amount']=$(this).attr("total_amount");
		data['body']=$(this).attr("body");
		var confirmPost=confirm("确定付款此订单？");
		if(confirmPost==true){
			//ajax发送订单取消的请求
			$.ajax({
					url : 'Pay/AliPay',
					data:data,
					type : 'POST',
					success : function(data) {
						// data=JSON.parse(data);
						if(data['result']){
							//请求支付api成功
							$(".main-content").append(data['form']);
						}else{
							alert("意外错误！请重试！");
						}
					},
					error : function(data){
						alert("请检查网络！");
					}
			});
		}
		
	});
	
	
	//点击显示物流时showLog,弹出模态框
	$("#userOrderInfoTable").on("click",".showLog",function(){
		var oId=$(this).attr("oId");
		var data={};
		data['oId']=oId;
		console.log("发送的oid："+data);
		//ajax发送获取物流信息的请求
		$.ajax({
			url : 'BackManageOrder/checkLogistics',
			data:data,
			type : 'POST',
			success : function(data) {
				//data=JSON.parse(data);
				if(data['result']){
					//获取成功后,将数据渲染到模态框并弹出模态框
					$("#frontLogModal input[name='company']").val(data['company']);
					$("#frontLogModal input[name='waybillNum']").val(data['waybillNum']);
					$("#frontLogModal input").attr("disabled","disabled");
					$("#frontLogModal").modal('toggle');
				}else{
					if(data['isLogin']==false){
						window.location.href="SuperAdmin/login";
						return;
					}
					alert("失败，请重试！");
				}
			},
			error : function(data){
				alert("请检查网络！");
			}
		});
		
	});
	
	//点击了确认收货时confirmOrder或点击了拒收订单时rejectLog
	$("#userOrderInfoTable").on("click",".confirmOrder,.rejectLog",function(){
		var data={};
		data['oId']=$(this).attr("oId");
		var postConfirm;
		if($(this).hasClass("rejectLog")){
			postConfirm=confirm("确定已拒收？");
			data['what']="refuse";
		}else{
			postConfirm=confirm("确认收货？");
			data['what']="accept";
		}
		
		if(postConfirm){
			//直接发送一个确认收货的ajax请求
			$.ajax({
				url : 'FrontManageOrder/updateOrderStatus',
				data:data,
				type : 'POST',
				success : function(data) {
					data=JSON.parse(data);
					if(data['result']){
						//后台受理成功，刷新订单
						doTable("FrontManageOrder/seeAllOrder");
					}else{
						if(data['isLogin']==false){
							window.location.href="SuperAdmin/login";
						}
						alert("失败，请重试！");
					}
				},
				error : function(data){
					alert("请检查网络！");
				}
			});
		}
	});
	
	//一个暂存oId的变量
	var applyOid;
	//点击了申请退换applayLog，弹出模态框
	$("#userOrderInfoTable").on("click",".applayLog",function(){
		applyOid=$(this).attr("oId");
		//弹出模态框
		$("#frontApplyModal").modal('toggle');
		//点击了退换提交,完事后关闭模态框
	});
	$("#postApply").click(function(){
		//准备数据
		var data={};
		data['oId']=applyOid;
		data['what']="apply";
		data['reason']=$("#frontApplyModal textarea[name='reason']").val();
		var postFlag=true;
		$.each(data,function(k,v){
			if(v==""){
				postFlag==false;
			}
		});
		if(!postFlag){
			alert("请检查是否填写了信息！");
		}else{
			//ajax发送申请
			$.ajax({
				url : 'FrontManageOrder/updateOrderStatus',
				data:data,
				type : 'POST',
				success : function(data) {
					data=JSON.parse(data);
					if(data['result']){
						//后台受理成功，刷新订单
						$("#frontApplyModal").modal('hide');
						doTable("FrontManageOrder/seeAllOrder");
					}else{
						if(data['isLogin']==false){
							window.location.href="SuperAdmin/login";
						}
						alert("请勿重复提交！");
					}
				},
				error : function(data){
					alert("请检查网络！");
				}
			});
		}
	});
	
	//申请通过后填写物流returnLog
	$("#userOrderInfoTable").on("click",".returnLog",function(){
		applyOid=$(this).attr("oId");
		$("#frontLogModalLabel").html("退换货物流信息");
		// console.log(applyOid);
		//弹出模态框
		$("#frontLogModal").modal('toggle');
		//将模态框变为可编辑状态，并且显示提交按钮
		$("#frontLogModal input").removeAttr("disabled");
		$(".modal-footer").css("display","");
	});
	$("#postReLog").click(function(){
		//获取数据
		var data={};
		data['oId']=applyOid;
		data['company']=$("#frontLogModal input[name='company']").val();
		data['waybillNum']=$("#frontLogModal input[name='waybillNum']").val();
		console.log(data);
		var postFlag=true;
		$.each(data,function (index,value) {
			if(value==""){
				postFlag=false;
			}
		});
		if(!postFlag){
			alert("请填写完成的信息");
		}else{
			//ajax发送数据
			$.ajax({
				url : 'FrontManageOrder/applyReturn',
				data:data,
				type : 'POST',
				success : function(data) {
					data=JSON.parse(data);
					if(data['result']){
						//后台受理成功，刷新订单
						$("#frontLogModal").modal('hide');
						doTable("FrontManageOrder/seeAllOrder");
					}else{
						if(data['isLogin']==false){
							window.location.href="SuperAdmin/login";
						}
						alert("请勿重复提交！");
					}
				},
				error : function(data){
					alert("请检查网络！");
				}
			});
		}

	});
	
});