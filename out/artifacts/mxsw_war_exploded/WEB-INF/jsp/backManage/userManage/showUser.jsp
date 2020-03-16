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
    <link rel="stylesheet" href="static/css/backManage/showUser.css">

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
                    <div class="panel panel-default user-filter">
                        <div class="panel-heading">
                            <h4 class="panel-title">
                                <span data-toggle="collapse" data-parent="#accordion"
                                      href="#userFlag" class="glyphicon glyphicon-filter">
                                条件筛选
                                </span>
                            </h4>
                        </div>
                        <div id="userFlag" class="panel-collapse collapse in">
                            <div class="panel-body">
                                <!-- 内联样式 -->
                                <div class="form-inline" role="form" id="findUser">
                                    <div class="form-group">
                                        <label>用户昵称：</label>
                                        <input name="uName" type="text" class="form-control"/>
                                    </div>
                                    &emsp;&emsp;
                                    <div class="form-group">
                                        <label>用户ID：</label>
                                        <input name="name" type="text" class="form-control">
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
        <div class="panel panel-default user-info-table">
            <div class="panel-heading">
                <h3 class="panel-title">
                    <span class="glyphicon glyphicon-eye-open"></span>
                    站内用户信息
                </h3>

            </div>
            <div class="panel-body">
                
                <!--一个隐藏的消息提示，与用户表格结果有关-->
                <div id="userQueryIssu" hidden="hidden"  class="alert alert-success">
                    <button type="button" class="close" data-dismiss="alert"
                            aria-hidden="true">
                        &times;
                    </button>
                    <span id="userQueryIssu-main"></span>
                </div>
                <!-- 显示用户信息的表格 -->
                <table id="UserInfoTable"></table>


            </div>
        </div>
            
            
        </div>
    </div>
    <!-- 右侧主要内容结束 -->



    <!-- 提交筛选 -->
    <script type="text/javascript">
        // user信息表格，以及拉黑操作
        function doTable(url,queryData){
            // alert(url);
            $('#UserInfoTable').bootstrapTable('destroy');   //动态加载表格之前，先销毁表格
            $("#UserInfoTable").bootstrapTable({ // 对应table标签的id
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
                sortName: 'name', // 要排序的字段
                uniqueId:'name',
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
                        // alert(queryData);
                        param['queryData']=queryData;

                        //alert(JSON.stringify(param));
                    }
                    // alert(JSON.stringify(param));
                    return param;
                },
                columns:
                    [
                        {
                            field: 'uName',
                            title: '用户名称',
                            align: 'center',
                            valign: 'middle',
                            width: '100',
                        }, {
                        field: 'name',
                        title: '用户ID',
                        align: 'center',
                        valign: 'middle',
                        width: '100',
                    }, {
                        field: 'email',
                        title: '用户邮箱',
                        align: 'center',
                        valign: 'middle',
                        width: '100',
                    }, {
                        field: 'score',
                        title: '积分',
                        align: 'center',
                        valign: 'middle',
                        width: '100',
                    }, {
                        field: 'userPath',
                        title: '头像',
                        align: 'center',
                        valign: 'middle',
                        width: '100',
                        formatter:function(value, row, index){
                            //获取图片
                            return "<span style=\"margin:0 auto;width:80px\" class=\"thumbnail\"><img src=\""+value+"\"></span>";
                        }
                    }, {
                        title: "操作",
                        align: 'center',
                        valign: 'middle',
                        width: '70',
                        formatter:function(value, row, index){

                            return "<button onclick='blackRoom(this)' class='btn btn-default btn-xs' uName='"+row.name+"'><span class='glyphicon glyphicon-exclamation-sign'></span>关小黑屋</button>";
                        }
                    }
                    ],
                onLoadError: function(res){  //加载失败时执行
                    console.info("加载数据失败"+res);
                    $("#userQueryIssu-main").html("查询为空！");
                    $("#userQueryIssu").removeAttr("hidden");

                }

                
            })
        }
        //未定位查询前先设定表格显示所有
        doTable("User/getAlluser");

        // 筛选提交ajax
        $("#findUser button").click(function(){
            var uName=$.trim($("#findUser input[name='uName']").val());
            var name=$.trim($("#findUser input[name='name']").val());
            if(uName!=""||name!=""){
                var data={};
                data['uName']=uName;
                data['name']=name;
                // 查询接口地址
                var url="User/getAlluser";
                doTable(url,data);
                
            }else{
                $("#userQueryIssu-main").html("您未填写搜索参数！");
                $("#userQueryIssu").removeAttr("hidden");
            }
        });
        

        //小黑屋
        function blackRoom(obj){
            var uName={};
            uName['name']=$(obj).attr("uName");
            //alert(uName.name);
            // ajax提交删除请求
            if(confirm("确定要拉黑此用户？")){

                //ajax提交删除请求
                $.ajax({
                    url:"<%=request.getContextPath()%>/User/delateUser",
                    data:uName.name,
                    type:'post',
                    success:function(data){
                        // 要求返回json字符串，键名为result
                        if(data){
                            $("#userQueryIssu-main").html("删除成功！");
                            $("#userQueryIssu").removeAttr("hidden");
                            $('#userQueryIssu').bootstrapTable('removeByUniqueId', uName);
                        }
                    },
                    error(xhr,status,error){
                        $("#userQueryIssu-main").html("网络错误！请重试！");
                        $("#userQueryIssu").removeAttr("hidden");
                    }
                });
                return true;
            }else{
                return false;
            }
        }

    </script>



</body>

</html>
