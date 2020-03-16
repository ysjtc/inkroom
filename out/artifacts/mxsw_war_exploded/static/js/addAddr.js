$(document).ready(function() {
    //新增收货人信息验证
    function ValidatorAddrInfo(){
        $('#addrInfo').bootstrapValidator({
            message: '你的输入有误',
            feedbackIcons: {
                valid: 'glyphicon glyphicon-ok',
                invalid: 'glyphicon glyphicon-remove',
                validating: 'glyphicon glyphicon-refresh'
            },
            fields: {
                name: {
                    validators: {
                        notEmpty: {
                            message: '收货人名不能为空'
                        },
                        stringLength: {
                            min: 1,
                            max: 10,
                            message: '收货人名长度为1-10个字符'
                        }
                    }
                },
                tel:{
                    validators:{
                        notEmpty:{
                            message:'电话号码不能为空'
                        },
                        stringLength: {
                            min: 11,
                            max: 11,
                            message: '电话号码为11个数字'
                        },
                        digits:{
                            message:'电话号码只能为数字'
                        }
                    }
                },
                postcode:{
                    validators:{
                        notEmpty:{
                            message:'邮编不能为空'
                        },
                        stringLength: {
                            min: 6,
                            max: 6,
                            message: '邮编为6个数字'
                        },
                        digits:{
                            message:'邮编只能为数字'
                        }
                    }
                },
                addr: {
                    validators: {
                        notEmpty: {
                            message: '详细地址不能为空'
                        }
                    }
                },
                province: {
                    validators: {
                        notEmpty: {
                            message: '详细地址不能为空'
                        }
                    }
                }




            }
        });
    }
    ValidatorAddrInfo();
    $("#addAddr").click(function(){
        $("#addrInfo").bootstrapValidator('validate');
        if($("#addrInfo").data('bootstrapValidator').isValid()) {
            //接收参数
            var postData={};
            postData['name']=$("input[name='name']").val();
            postData['tel']=$("input[name='tel']").val();
            postData['postcode']=$("input[name='postcode']").val();
            postData['province']=$("select[name='province']  option:selected").val();
            postData['addr']=$("textarea[name='addr']").val();
            //ajax发送数据
            $.ajax({
                url : 'Address/addAddr',//访问后台的上传方法路径
                data : postData,
                type : 'POST',
                success : function(data) {
                    var data=JSON.parse(data);
                    if(data['result']){
                        //修改成功后刷新下方表格
                        doTable("Address/getAlladdress");
                        alert("添加成功");
                    }else{
                        alert("意外错误，请重试！");
                    }
                }
            });
        }else{
            alert("请检查是否信息完整");
        }
    });

    //渲染显示收货人信息的表格
    function doTable(url){
        // alert(url);
        $('#addrInfoTable').bootstrapTable('destroy');   //动态加载表格之前，先销毁表格
        $("#addrInfoTable").bootstrapTable({ // 对应table标签的id
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
            sortName: 'add_id', // 要排序的字段
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
                        field: 'name', // 返回json数据中的name
                        title: '收货人', // 表格表头显示文字
                        align: 'center', // 左右居中
                        valign: 'middle', // 上下居中
                        width: '100',
                    }, {
                    field: 'tel',
                    title: '电话号码',
                    align: 'center',
                    valign: 'middle',
                    width: '100',
                }, {
                    field: 'postcode',
                    title: '邮编',
                    align: 'center',
                    valign: 'middle',
                    width: '100',
                }, {
                    field: 'province',
                    title: '省份',
                    align: 'center',
                    valign: 'middle',
                    width: '100',
                }, {
                    field: 'addr',
                    title: '地址',
                    align: 'center',
                    valign: 'middle',
                    width: '200',
                },{
                    field: 'add_id',
                    title: '地址id',
                    align: 'center',
                    valign: 'middle',
                    visible:false,
                }, {
                    title: "操作",
                    align: 'center',
                    valign: 'middle',
                    width: '70',
                    formatter:function(value, row, index){

                        return "<button class='btn btn-default btn-xs delAddr' add_id='"+row.add_id+"'><span class='glyphicon glyphicon-exclamation-sign'></span>删除</button><br/><button style='margin-top:8px' class='btn btn-default btn-xs editAddr' add_id='"+row.add_id+"'><span class='glyphicon glyphicon-pencil'></span>修改</button>";
                    }
                }
                ],

        })
    }
    //未定位查询前先设定表格显示所有
    doTable("Address/getAlladdress");
	
	//点击删除该地址
	$(".product").on("click",".delAddr",function(){
		var data={};
		data['add_id']=$(this).attr("attr");
		$.ajax({
			url : 'aa/bb',
			data : postData,
			type : 'POST',
			success : function(data) {
				var data=JSON.parse(data);
				if(data['result']){
					//修改成功后刷新下方表格
					doTable("Address/getAlladdress");
					alert("添加成功");
				}else{
					alert("意外错误，请重试！");
				}
			}
		});
	});
	
	//点击修改时editAddr
	$(".product").on("click",".delAddr",function(){
		//弹出模态框
		$("#applyEditModal").modal('toggle');
	});
	
	//点击了模态框的提交按钮时postApplyEdit
	$("#postApplyEdit").click(function(){
		//接收参数
		var postData={};
		postData['name']=$("#applyEditModal input[name='name']").val();
		postData['tel']=$("#applyEditModal input[name='tel']").val();
		postData['postcode']=$("#applyEditModal input[name='postcode']").val();
		postData['province']=$("#applyEditModal select[name='province']  option:selected").val();
		postData['addr']=$("#applyEditModal textarea[name='addr']").val();
		//ajax发送数据
		$.ajax({
			url : 'aa/bb',//访问后台的上传方法路径
			data : postData,
			type : 'POST',
			success : function(data) {
				var data=JSON.parse(data);
				if(data['result']){
					//修改成功后刷新下方表格
					doTable("Address/getAlladdress");
					alert("修改成功！");
				}else{
					alert("意外错误，请重试！");
				}
			}
		});
	});
	
	
	
	
	
	
});