$(document).ready(function() {
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
                    //alert(JSON.stringify(param));
                }
                // alert(JSON.stringify(param));
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
                        return value=="0"? "未处理":value=="1"? "拒绝":"通过";
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
    doTable("FrontManageOrder/applyOrder");

});