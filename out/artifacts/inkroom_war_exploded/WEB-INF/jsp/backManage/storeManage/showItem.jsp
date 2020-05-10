<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Eros
  Date: 2019/7/15
  Time: 18:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>商品展示</title>
    <%--引入basePath--%>
    <%@ include file="./../../common/basePath.jsp"%>
    <%--引入后台管理部分的公共头部--%>
    <%@ include file="./../../common/backManage/headBase.jsp"%>
    <%--引入bt-table--%>
    <%@ include file="./../../common/bt_table.jsp"%>
    <%--引入bt-datepicker--%>
    <%@ include file="./../../common/bt_datetimepicker.jsp"%>
    <%---引入bt-validator--%>
    <%@ include file="./../../common/bt_validator.jsp"%>
    <!--引入商品展示页面的css-->
    <link rel="stylesheet" href="static/css/backManage/showItem.css">
</head>
<body>

    <%--引入导航栏--%>
    <%@ include file="./../../common/backManage/backNav.jsp"%>


    <!-- 主要内容开始 -->
    <div class="right-content">
        <div class="right-content-wrap">



            <!--一个折叠，帅选条件-->
            <div class="row">
                <div  class="row-wrap" style="padding-left:15px;padding-right:15px;">
                    <div class="panel-group" id="accordion">
                        <div class="panel panel-default item-filter">
                            <div class="panel-heading">
                                <h4 class="panel-title">
                                    <span data-toggle="collapse" data-parent="#accordion"
                                          href="#collapseOne" class="glyphicon glyphicon-filter">
                                    条件筛选
                                    </span>
                                </h4>
                            </div>
                            <div id="collapseOne" class="panel-collapse collapse in">
                                <div class="panel-body">
                                    <!--一个隐藏的消息提示，与商品查询有关-->
                                    <div id="queryIssu" hidden="hidden"  class="alert alert-success">
                                        <button type="button" class="close" data-dismiss="alert"
                                                aria-hidden="true">
                                            &times;
                                        </button>
                                        <span id="queryIssu-main"></span>
                                    </div>
                                    <!--暂时这样，到时候用ajax提交-->
                                    <div id="qeuryInfo">
                                        <div class="row">
                                            <div class="form-group">
                                                <label class="col-xs-2 control-label">
                                                    根据价格范围查找：
                                                </label>
                                                <div class="col-xs-2">
                                                    <div class="input-group">
                                                        <input type="text" class="form-control" name="lowPrice">
                                                        <span class="input-group-addon">￥</span>
                                                    </div>
                                                </div>
                                                <div class="col-xs-1" style="line-height:1;text-align: center;font-size:30px;color:#CCCCCC">
                                                    <span class="glyphicon glyphicon-arrow-right"></span>
                                                </div>
                                                <div class="col-xs-2">
                                                    <div class="input-group">
                                                        <input type="text" class="form-control" name="highPrice">
                                                        <span class="input-group-addon">￥</span>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <br/>
                                        <div class="row">
                                            <div class="form-group">
                                                <label class="col-xs-2 control-label">
                                                    根据ISBN查找：
                                                </label>
                                                <div class="col-xs-2">
                                                    <div class="input-group">
                                                        <input type="text" class="form-control" name="isbn">
                                                    </div>
                                                </div>
                                            </div>
                                        </div><br/>
                                        <div class="row">
                                            <div class="form-group">
                                                <label class="col-xs-2 control-label">
                                                    根据名称查找：
                                                </label>
                                                <div class="col-xs-2">
                                                    <div class="input-group">
                                                        <input type="text" class="form-control" name="bookname">
                                                    </div>
                                                </div>
                                            </div>
                                        </div><br/>
                                        <div class="row">
                                            <div class="form-group">
                                                <label class="col-xs-2 control-label">
                                                    根据类别查找：
                                                </label>
                                                <div class="col-xs-2">
                                                    <div class="from-group">
                                                        <select class="form-control" name="itemCate">
                                                            <option value=""></option>
                                                            <c:forEach items="${Category}" var="obj">
                                                                <option value="${obj.cId}">${obj.cateName}</option>
                                                            </c:forEach>
                                                            <%--<option value="22">计算机</option>--%>
                                                            <%--<option value="1">土木</option>--%>
                                                            <%--<option value="2">安全攻防</option>--%>
                                                            <%--<option value="3">美术</option>--%>
                                                            <%--<option value="4">商贸</option>--%>
                                                        </select>
                                                    </div>
                                                </div>
                                            </div>
                                        </div><br/>
                                        <div class="row">
                                            <div class="col-xs-12">
                                                <button id="subQuery" class="btn btn-default">提交查询</button>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <!--一个现实商品信息的表格，默认显示商品表的前10条记录-->
            <div class="panel panel-default item-info-table">
                <div class="panel-heading">
                    <h3 class="panel-title">
                        <span class="glyphicon glyphicon-eye-open"></span>
                        商品信息
                    </h3>

                </div>
                <div class="panel-body">
					 <!-- 模态框（Modal） -->
                    <div class="modal fade" id="editModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                        <div class="modal-dialog">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                                    <h4 class="modal-title" id="myModalLabel">
                                        修改商品&emsp;
                                    </h4>
                                </div>
                                <div class="modal-body form-horizontal" style="width:600px;">
                                    <div class="form-group">   
                                        <label class="col-sm-3 control-label">  
                                            商品名称：
                                        </label>
                                        <div class="col-sm-3">
                                            <input name="name" type="text" class="form-control">
                                        </div>
                                        
                                        <label class="col-sm-2 control-label">  
                                            商品价格：
                                        </label>
                                        <div class="col-sm-3">
                                            <input name="price" type="text" class="form-control">
                                        </div>
                                    </div>
                                    <div class="form-group">   
                                        <label class="col-sm-3 control-label">  
                                            商品库存：
                                        </label>
                                        <div class="col-sm-3">
                                            <input name="count" type="text" class="form-control">
                                        </div>
                                        
                                        <label class="col-sm-2 control-label">  
                                            商品作者：
                                        </label>
                                        <div class="col-sm-3">
                                            <input name="author" type="text" class="form-control">
                                        </div>
                                    </div>
                                    <div class="form-group">   
                                        <label class="col-sm-3 control-label">  
                                            新旧程度：
                                        </label>
                                        <div class="col-sm-3">
                                            <input name="oldLevel" type="text" class="form-control">
                                        </div>
                                        
                                        <label class="col-sm-2 control-label">  
                                            新书比价：
                                        </label>
                                        <div class="col-sm-3">
                                            <input name="compare" type="text" class="form-control">
                                        </div>
                                    </div>
                                    <div class="form-group">   
                                        <label class="col-sm-3 control-label">  
                                            商品类别：
                                        </label>
                                        <div class="col-sm-3">
                                            <select name="itemCateOne" class="form-control" id="itemCate">
                                                <option value=""></option>
                                                <c:forEach items="${Category}" var="obj">
                                                    <option value="${obj.cId}">${obj.cateName}</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                        
                                        <label class="col-sm-2 control-label">  
                                            商品标签：
                                        </label>
                                        <div class="col-sm-3">
                                            <select name="lable" class="form-control">
                                                <option value=""></option>
                                                <option value="0">成色好</option>
                                                <option value="1">价格低</option>
                                                <option value="2">稀有</option>
                                                <option value="3">性价比高</option>
                                                <option value="4">全新书籍</option>
                                                <option value="5">有收藏价值</option>
                                                <option value="6">破烂书</option>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="form-group">   
                                        <label class="col-sm-3 control-label">  
                                            发货地点：
                                        </label>
                                        <div class="col-sm-3">
                                            <select name="place" class="form-control">
                                                <option></option>
                                                <option value="北京">北京市</option>
                                                <option value="浙江省">浙江省</option>
                                                <option value="天津市">天津市</option>
                                                <option value="安徽省">安徽省</option>
                                                <option value="上海市">上海市</option>
                                                <option value="福建省">福建省</option>
                                                <option value="重庆市">重庆市</option>
                                                <option value="江西省">江西省</option>
                                                <option value="山东省">山东省</option>
                                                <option value="河南省">河南省</option>
                                                <option value="湖北省">湖北省</option>
                                                <option value="湖南省">湖南省</option>
                                                <option value="广东省">广东省</option>
                                                <option value="海南省">海南省</option>
                                                <option value="山西省">山西省</option>
                                                <option value="青海省">青海省</option>
                                                <option value="江苏省">江苏省</option>
                                                <option value="辽宁省">辽宁省</option>
                                                <option value="吉林省">吉林省</option>
                                                <option value="台湾省">台湾省</option>
                                                <option value="河北省">河北省</option>
                                                <option value="贵州省">贵州省</option>
                                                <option value="四川省">四川省</option>
                                                <option value="云南省">云南省</option>
                                                <option value="陕西省">陕西省</option>
                                                <option value="甘肃省">甘肃省</option>
                                                <option value="黑龙江省">黑龙江省</option>
                                                <option value="香港特别行政区">香港特别行政区</option>
                                                <option value="澳门特别行政区">澳门特别行政区</option>
                                                <option value="广西壮族自治区">广西壮族自治区</option>
                                                <option value="宁夏回族自治区">宁夏回族自治区</option>
                                                <option value="新疆维吾尔自治区">新疆维吾尔自治区</option>
                                                <option value="内蒙古自治区">内蒙古自治区</option>
                                                <option value="西藏自治区">西藏自治区</option>
                                            </select>
                                        </div>
                                        
                                        <label class="col-sm-2 control-label">  
                                            出版时间：
                                        </label>
                                        <div class="col-sm-3">
                                            <input id="publishTime" name="publishTime" type="text" class="form-control">
                                        </div>
                                    </div>
                                    <div class="form-group">   
                                        <label class="col-sm-3 control-label">  
                                            ISBN：
                                        </label>
                                        <div class="col-sm-3">
                                            <input name="ISBN" type="text" class="form-control">
                                        </div>
                                        
                                        <label class="col-sm-2 control-label">  
                                            出版社：
                                        </label>
                                        <div class="col-sm-3">
                                            <input name="publish" type="text" class="form-control">
                                        </div>
                                    </div>
                                    <!-- 商品介绍 -->
                                    <div class="form-group">
                                        <label class="col-sm-3 control-label">
                                            商品介绍：
                                        </label>
                                        <div class="col-sm-8">
                                            <textarea name="describe" type="text" class="form-control" placeholder="请输入书的标签"  rows="7"></textarea>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <span class="col-sm-1"></span>
                                        <label class="col-sm-2">
                                            <div style="float:right;">缩略图：</div>
                                        </label>
                                        <div class="plug-div">
                                            <span class="glyphicon glyphicon-plus-sign"></span>
                                            <span>添加</span>
                                        </div>
                                        
                                    </div>
                                    <!-- 缩略图开始 -->
                                    <div class="form-group">
                                        <div class="sm-item-pic">

                                            <!-- 一个隐藏的用于接收图片数据input -->
                                            <input style="display:none;" id="newPic" type="file" multiple="multiple" accept="image/png,image/jpeg,image/jpg"/>




                                        </div>
                                    </div>
                                    <!-- 缩略图结束 -->





                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                                    <button id="confirmUpdate" type="button" class="btn btn-primary">提交更改</button>
                                </div>
                            </div><!-- /.modal-content -->
                        </div><!-- /.modal -->
                    </div>
				
                    <!--一个隐藏的消息提示，与商品删除有关-->
                    <div id="delResult" hidden="hidden"  class="alert alert-success">
                        <button type="button" class="close" data-dismiss="alert"
                                aria-hidden="true">
                            &times;
                        </button>
                        <span id="delResult-main"></span>
                    </div>
                    <table id="ItemInfoTable" style="table-layout:fixed; word-break: break-all; word-wrap: break-word;"></table>

                </div>
            </div>





        </div>
    </div>
    <!-- 主要内容结束 -->




    <!--渲染显示商品表格-->
    <!--渲染显示商品表格-->
    <script type="text/javascript">

        //
        //
        //要求后台的json数据格式：
        //{"total":1,"rows":[{"item_id":1,"name":"1","cateName":"2","price":"3","count":"4","author":"5","ISBN":"6","old_level":"7","compare":"8","publish_time":"9","publish":"10","place":"11","label":"12","item_pic":"13"}]}
        //total为行数，rows为元素为json数据的数组，然后将两者组成一个json。后台需要注意要改响应头的content-type
        //
        //
        //一个暂存queryData的全局变量
        var queryDataCache;
        function doTable(url,queryData){
            //alert(url);
            $('#ItemInfoTable').bootstrapTable('destroy');   //动态加载表格之前，先销毁表格
            $("#ItemInfoTable").bootstrapTable({ // 对应table标签的id
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
                sortName: 'item_id', // 要排序的字段
                uniqueId:'item_id',
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
                        queryDataCache=queryData;

                        //alert(JSON.stringify(param));
                    }
                    if(queryDataCache){
                        param['queryData']=queryDataCache;
                    }
                    return param;
                },
                columns:
                    [
                        {
                            field: 'item_id', // 返回json数据中的name
                            title: '编号', // 表格表头显示文字
                            align: 'center', // 左右居中
                            valign: 'middle', // 上下居中
                            width: '100',
                        }, {
                        field: 'name',
                        title: '名称',
                        align: 'center',
                        valign: 'middle',
                        width: '100',
                    }, {
                        field: 'cateName',
                        title: '所属类别',
                        align: 'center',
                        valign: 'middle',
                        width: '100',
                    }, {
                        field: 'price',
                        title: '价格',
                        align: 'center',
                        valign: 'middle',
                        width: '100',
                    }, {
                        field: 'count',
                        title: '数量',
                        align: 'center',
                        valign: 'middle',
                        width: '100',
                    }, {
                        field: 'author',
                        title: '作者',
                        align: 'center',
                        valign: 'middle',
                        width: '100',
                    }, {
                        field: 'ISBN',
                        title: 'ISBN',
                        align: 'center',
                        valign: 'middle',
                        width: '130',
                    }, {
                        field: 'old_level',
                        title: '新旧程度',
                        align: 'center',
                        valign: 'middle',
                        width: '100',
                    }, {
                        field: 'compare',
                        title: '新书比价',
                        align: 'center',
                        valign: 'middle',
                        width: '100',
                    }, {
                        field: 'publish_time',
                        title: '出版日期',
                        align: 'center',
                        valign: 'middle',
                        width: '100',
                    }, {
                        field: 'publish',
                        title: '出版社',
                        align: 'center',
                        valign: 'middle',
                        width: '100',
                    }, {
                        field: 'place',
                        title: '发货地点',
                        align: 'center',
                        valign: 'middle',
                        width: '100',
                    }, {
                        field: 'label',
                        title: '标签',
                        align: 'center',
                        valign: 'middle',
                        width: '100',
                    }, {
                        field: 'item_pic',
                        title: '缩略图',
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

                            return "<a class=\'delItemLink\' itemId=\'"+row.item_id+"\' onclick=\'delItemConfirm(this)\'><span class=\'glyphicon glyphicon-remove\'></span></a>"+"&nbsp;&nbsp;<a itemId=\'"+row.item_id+"\' data-toggle=\'modal\' data-target=\'#editModal\' onclick=\'editItemLink(this)\'><span class=\'glyphicon glyphicon-edit\'></span></a>";
                        }
                    }
                    ],
                onLoadError: function(res){  //加载失败时执行
                    console.info("加载数据失败");
                    $("#queryIssu").html("网络错误！请稍后再试，或者再次提交！");
                    $("#queryIssu").removeAttr("hidden");


                },
                onLoadSuccess:function(){
                    $("#queryIssu").html("成功！请查看下方表格信息！");
                    $("#queryIssu").removeAttr("hidden");

                }

                /*,
                onClickCell: function(field, value, row, $element) {
                    if(field!='item_pic'){
                        $element.attr('contenteditable', true);
                        //此版本的jq有bug，会调触发两次blur，如下重新绑定可debug
                        $element.unbind('blur').bind('blur', function(){
                            //被修改商品的id
                            item_id=row.item_id;
                            //修改后的信息（value）
                            tdValue = $element.html();
                            //被修改的键
                            field=field;
                            //alert(field+"::"+tdValue+"::"+item_id);
                            saveData(item_id, field, tdValue);
                        })
                    }
                }*/
            })
        }
        //未定位查询前先设定表格显示所有
        doTable("ItemsBackManage/query/AllItems");


        /*function saveData(item_id, field, tdValue){
            //此处写ajax提交修改
            alert(item_id);
        }*/

        //验证提交的信息
        function ValidatorQeuryInfo(){
            $('#qeuryInfo').bootstrapValidator({
                message: '你的输入有误',
                fields: {
                    lowPrice: {
                        validators: {
                            regexp: {
                                /* 只需加此键值对，包含正则表达式，和提示 */
                                regexp: /^\d+(\.\d+)?$|^$|^(\d+|\-){7,}$/,
                                message: '只能是数字(包括小数)'
                            }
                        }
                    },
                    highPrice:{
                        validators: {
                            regexp: {
                                /* 只需加此键值对，包含正则表达式，和提示 */
                                regexp: /^\d+(\.\d+)?$|^$|^(\d+|\-){7,}$/,
                                message: '只能是数字(包括小数)'
                            }
                        }
                    },
                    isbn:{
                        validators: {
                            stringLength: {
                                min:10,
                                max:13,
                                message: 'ISBN不合规范'
                            },
                            numeric:{
                                message:'ISBN只能为数字'
                            }
                        }
                    }
                }

            })
        }
        ValidatorQeuryInfo();

        //新上线的功能
        //设置查询条件单一（暂未开发综合查询）
        //此处的blur函数，由于此版本的jq有bug，所以需要重新绑定
        //
        //lowPrice或者highPrice失去焦点时
        $("input[name$='Price']").unbind('blur').bind('blur', function(){

            //失去焦点时，内容不为空
            if($.trim($("input[name='lowPrice']").val())!=""||$.trim($("input[name='highPrice']").val())!=""){
                //其他的input和select都设置为不可选，并内容为空（除了hightPrice）
                $("input[name='isbn']").val("");
                $("input[name='isbn']").attr("disabled",true);
                $("input[name='bookname']").val("");
                $("input[name='bookname']").attr("disabled",true);
                $("select[name='itemCate']").find("option[value='']").attr("selected",true);
                $("select[name='itemCate']").attr("disabled",true);
                //alert($("select[name='itemCate']  option:selected").val());

            }else{
                $("input[name='isbn']").attr("disabled",false);
                $("input[name='bookname']").attr("disabled",false);
                $("select[name='itemCate']").attr("disabled",false);
            }
        });
        //ISBN失去焦点时
        $("input[name='isbn']").unbind('blur').bind('blur', function(){
            if($.trim($("input[name='isbn']").val())!=""){
                $("input[name$='Price']").val("");
                $("input[name$='Price']").attr("disabled",true);
                $("input[name='bookname']").val("");
                $("input[name='bookname']").attr("disabled",true);
                $("select[name='itemCate']").find("option[value='']").attr("selected",true);
                $("select[name='itemCate']").attr("disabled",true);
            }else{
                $("input[name$='Price']").attr("disabled",false);
                $("input[name='bookname']").attr("disabled",false);
                $("select[name='itemCate']").attr("disabled",false);
            }
        });
        //名称失去焦点时
        $("input[name='bookname']").unbind('blur').bind('blur', function(){
            if($.trim($("input[name='bookname']").val())!=""){
                $("input[name$='Price']").val("");
                $("input[name$='Price']").attr("disabled",true);
                $("input[name='isbn']").val("");
                $("input[name='isbn']").attr("disabled",true);
                $("select[name='itemCate']").find("option[value='']").attr("selected",true);
                $("select[name='itemCate']").attr("disabled",true);
            }else{
                $("input[name$='Price']").attr("disabled",false);
                $("input[name='isbn']").attr("disabled",false);
                $("select[name='itemCate']").attr("disabled",false);
            }
        });
        //cate失去焦点时
        $("select[name='itemCate']").unbind('blur').bind('blur', function(){
            if($.trim($("select[name='itemCate']  option:selected").val())!=""){
                $("input[name$='Price']").val("");
                $("input[name$='Price']").attr("disabled",true);
                $("input[name='isbn']").val("");
                $("input[name='isbn']").attr("disabled",true);
                $("input[name='bookname']").val("");
                $("input[name='bookname']").attr("disabled",true);
            }else{
                $("input[name$='Price']").attr("disabled",false);
                $("input[name='isbn']").attr("disabled",false);
                $("input[name='bookname']").attr("disabled",false);
            }
        });


        //ajax提交查询信息
        $("#subQuery").click(function(){
            $("#qeuryInfo").data('bootstrapValidator').destroy();
            $('#qeuryInfo').data('bootstrapValidator', null);
            ValidatorQeuryInfo();

            //获取查询信息，接下来判断是否为空从而选择性提交数据
            var lowPrice=$("input[name='lowPrice']").val();
            var highPrice=$("input[name='highPrice']").val();
            var isbn=$("input[name='isbn']").val();
            var bookname=$("input[name='bookname']").val();
            var itemCate=$("select[name='itemCate']  option:selected").val();

            //一个判断是否能够提交的flag
            var flag=true;

            //提交的url
            var url="";
            //要发送的数据
            data={};

            if($.trim(lowPrice)!=""&&$.trim(highPrice)!=""){
                //根据价格查询的url
                url="ItemsBackManage/query/ItemsByPrice";
                data["lowPrice"]=lowPrice;
                data["highPrice"]=highPrice;
            }else if($.trim(isbn)!=""){
                //根据ISBN查询的url
                url="ItemsBackManage/query/ToQueryItemsByIsbn";
                data["isbn"]=isbn;
            }else if($.trim(bookname)!=""){
                //根据书名查询的url
                url="ItemsBackManage/query/ItemsByName";
                data["bookname"]=bookname;
            }else if($.trim(itemCate)!=""){
                //根据商品类别来查询的url
                url="ItemsBackManage/query/ItemsByItemCate";
                data["itemCate"]=itemCate;
            }else{
                $("#queryIssu").html("错误！请检查是否输入了查询信息，或者信息是否完整！");
                $("#queryIssu").removeAttr("hidden");
                flag=false;
            }
            //alert(url+"::"+JSON.stringify(data));
            //如果取到了查询信息，则提交
            if (flag) {
                // $.ajax({
                //     url:url,
                //     data:data,
                //     contentType:'json',
                //     success:function(result){
                //         $("#queryIssu").html("成功！请查看下方表格！");
                //         $("#queryIssu").removeAttr("hidden");
                //     },
                //     error(xhr,status,error){
                //         $("#queryIssu").html("网络错误！请稍后再试，或者再次提交！");
                //         $("#queryIssu").removeAttr("hidden");
                //     }
                // });
                //
                //
                // 调用表格
                //提交验证
                $("#qeuryInfo").bootstrapValidator('validate');
                if($("#qeuryInfo").data('bootstrapValidator').isValid()) {
                    doTable(url,data);
                }

            }


        });

        //删除前确认
        function delItemConfirm(obj){
            var itemid=$(obj).attr("itemid");
            var itemIdData="itemId="+itemid;
            if(confirm("确定要删除此商品？")){

                //ajax提交删除请求
                $.ajax({
                    url:'ItemsBackManage/deleteItem',
                    data:itemIdData,
                    type:'post',
                    success:function(data){
                        if(JSON.parse(data).result){
                            $("#delResult-main").html("删除成功！");
                            $("#delResult").removeAttr("hidden");
                            $('#ItemInfoTable').bootstrapTable('removeByUniqueId', itemid);
                        }
                    },
                    error(xhr,status,error){
                        $("#delResult-main").html("网络错误！请重试！");
                        $("#delResult").removeAttr("hidden");
                    }
                });
                return true;
            }else{
                return false;
            }
        };

		//一个牛逼的函数
        function editItemLink(obj){
            //一个暂存新增图片数据的数组
            var cachePic=new Array();
            //一个用于暂存待删除图片的id的数组
            var cacheDelPic=new Array();
            //一个用于最终提交图片的表单
            var newPicForm= new FormData();

            //设置提交表单的参数itemId（覆盖性设置，无需担心多次修改）
            newPicForm.set("itemId",$(obj).attr("itemid"));

            //先销毁图片缩略框框
            $(".sm-item-pic .thumbnail").remove();
            // console.log($(".sm-item-pic .thumbnail"));
            //然后在添加本来就存在的图片
            var tmp={};
            tmp['itemID']= $(obj).attr("itemid");
            $.ajax({
                url : 'ItemsBackManage/query/getPicPath',//访问后台的上传方法路径
                data : tmp,       //保存的formdata集合
                type : 'POST',
                success : function(data) {
                    var originallPic=JSON.parse(data);
                    $.each(originallPic,function(key,value) {
                        // console.log(key);
                        // console.log(value);
                        //动态创建的标签
                        var imgTag="<div class=\"thumbnail\" style=\"width:100px;\"><img style=\"height:90px;width:90px\" class=\"img-thumbnail\" src=\""+value+"\"><div class=\"caption\"><a picId=\""+key+"\" class=\"a-no-line pic-del\"><span class=\"glyphicon glyphicon-trash sm-selspan\"></span></a> &emsp;<a picId=\""+key+"\" class=\"a-no-line pic-edit\"><span class=\"glyphicon glyphicon-edit sm-selspan\"></span></a></div></div>"
                        //动态添加
                        $(".sm-item-pic").append(imgTag);
                    });

                },
                error : function(data) {
                    // console.log(data);
                    alert("意外错误！");
                }
            });

            //新增商品图片
            $(".plug-div").click(function(){
                //首先获取已存在多少张图
                var count=$(".sm-item-pic img").length;
                if(count<4){
                    //主动触发newPic的选择事件
                    $("#newPic").click();
                    $("#newPic").unbind().change(function(){
                        var files=$("#newPic").prop("files");
                        if(files.length+count>4){
                            alert("超过最大允许图片数！");
                            return;
                        }else{
                            var flag=true;
                            //验证是否存在不合格式的文件
                            for(i=0;i<files.length;i++){
                                //获取文件名截取后的数组
                                var tmpName=files[i].name.split('.');
                                var suffix=tmpName[tmpName.length-1].toUpperCase();
                                if(!(suffix == "PNG" || suffix == "JPEG" || suffix == "JPG")){
                                    alert("存在不合格式的文件!");
                                    flag=false;
                                    break;
                                }
                            }
                            //显示缩略图，并将数据暂存到cachePic
                            if(flag){
                                for(i=0;i<files.length;i++){
                                    var reader=new FileReader();
                                    reader.index=files[i].lastModified;
                                    //读取数据
                                    reader.readAsDataURL(files[i]);
                                    reader.onload= function () {
                                        // console.log(this);
                                        //动态创建的标签
                                        var imgTag="<div class=\"thumbnail\" style=\"width:100px;\"><img style=\"height:90px;width:90px\" class=\"img-thumbnail\" src=\""+this.result+"\"><div class=\"caption\"><a sinName=\""+this.index+"\"  class=\"a-no-line pic-del\"><span class=\"glyphicon glyphicon-trash sm-selspan\"></span></a> &emsp;<a sinName=\""+this.index+"\"  class=\"a-no-line pic-edit\"><span class=\"glyphicon glyphicon-edit sm-selspan\"></span></a></div></div>"
                                        //动态添加
                                        $(".sm-item-pic").append(imgTag);
                                    }
                                    //将图片数据放在暂存区
                                    //一个巨大的坑
                                    cachePic.push(files[i]);
                                }
                            }

                        }
                    });
                }else{
                    alert("最多只能有4张商品图！");
                    return;
                }
            });

            //商品图片删除处理,动态绑定
            $(".sm-item-pic").on("click",".thumbnail .pic-del",function(){
                var aTag=$(this);
                var picId=aTag.attr("picId");
                //如果删除的是旧的图片
                if(picId){
                    cacheDelPic.push(picId);
                    //删除dom节点
                    var rootStart=aTag.parent().parent();
                    rootStart.remove();
                    // console.log(rootStart);
                }else{
                    //删除的是新增的图片，此时还未提交，从缓冲区中删除数据即可
                    //获取唯一标识
                    var sinName=aTag.attr("sinName");
                    for(i=0;i<cachePic.length;i++){
                        var cacheSinName=cachePic[i].lastModified;
                        if(cacheSinName==sinName){
                            //从缓冲区中移除数据
                            var res=cachePic.splice(i,1);
                            if(res){
                                //删除dom节点
                                var rootStart=aTag.parent().parent();
                                rootStart.remove();
                                break;
                                // console.log(rootStart);

                            }
                        }
                    }
                }
            });

            //商品图片修改处理
            $(".sm-item-pic").on("click",".thumbnail .pic-edit",function(){
                var rootOne=$(this);
                //主动触发input
                $("#newPic").click();
                $("#newPic").unbind().change(function(){
                    var files=$("#newPic").prop("files");
                    if(files.length>1){
                        alert("修改图片只能选择一张！");
                    }else{
                        var aTag=rootOne;
                        var picId=aTag.attr("picId");
                        // console.log(aTag);
                        //如果修改的是旧的图片
                        if(picId){
                            //修改原图：后台先删除，然后再新增
                            cacheDelPic.push(picId);
                        }
                        var flag=true;
                        //验证是否存在不合格式的文件
                        //获取文件名截取后的数组
                        var tmpName=files[0].name.split('.');
                        var suffix=tmpName[tmpName.length-1].toUpperCase();;
                        if(!(suffix == "PNG" || suffix == "JPEG" || suffix == "JPG")){
                            alert("存在不合格式的文件!");
                            flag=false;
                        }
                        //显示缩略图，并将数据暂存到cachePic
                        if(flag){

                            var reader=new FileReader();
                            reader.index=files[0].lastModified;
                            //读取数据
                            reader.readAsDataURL(files[0]);
                            reader.onload= function () {
                                //先删除dom
                                var rootStart=aTag.parent().parent();
                                rootStart.remove();

                                //动态创建的标签
                                var imgTag="<div class=\"thumbnail\" style=\"width:100px;\"><img style=\"height:90px;width:90px\" class=\"img-thumbnail\" src=\""+this.result+"\"><div class=\"caption\"><a sinName=\""+this.index+"\"  class=\"a-no-line pic-del\"><span class=\"glyphicon glyphicon-trash sm-selspan\"></span></a> &emsp;<a sinName=\""+this.index+"\"  class=\"a-no-line pic-edit\"><span class=\"glyphicon glyphicon-edit sm-selspan\"></span></a></div></div>"
                                //动态添加
                                $(".sm-item-pic").append(imgTag);
                            }
                            if(picId){
                                //修改的是原图，那么只需push修改后的数据
                                cachePic.push(files[0]);
                            }else{
                                //删除原来的缓存，替换为现在的
                                var sinName=aTag.attr("sinName");
                                for(k=0;k<cachePic.length;k++){
                                    if(cachePic[k].lastModified==sinName){
                                        cachePic.splice(k,1,files[0]);
                                        break;
                                    }
                                }
                            }
                        }
                    }
                });
            });

            //准备做数据提交
            $("#confirmUpdate").click(function(){
                //获取修改的基本数据
                var name=$("input[name='name']").val();
                var price=$("input[name='price']").val();
                var count=$("input[name='count']").val();
                var author=$("input[name='author']").val();
                var ISBN=$("input[name='ISBN']").val();
                var oldLevel=$("input[name='oldLevel']").val();
                var compare=$("input[name='compare']").val();
                var publishTime=$("input[name='publishTime']").val();
                var publish=$("input[name='publish']").val();
                var describe=$("textarea[name='describe']").val();
                var cateId=$("select[name='itemCateOne']  option:selected").val();
                var place=$("select[name='place']").val();
                var lableId=$("select[name='lable']  option:selected").val();
                //设置表单的数据

                //首先填充图片数据
                if(cachePic.length>0){
                    // console.log(cachePic);
                    for(i=0;i<cachePic.length;i++){
                        newPicForm.append("picFiles",cachePic[i]);
                    }
                    // console.log(newPicForm.get("picFiles"));
                }

                // alert(cachePic[i]);
                //填充普通的商品参数
                newPicForm.set("name",name);
                newPicForm.set("price",price);
                newPicForm.set("count",count);
                newPicForm.set("author",author);
                newPicForm.set("isbn",ISBN);
                newPicForm.set("oldLevel",oldLevel);
                newPicForm.set("compare",compare);
                newPicForm.set("publish",publish);
                newPicForm.set("publishTime",publishTime);
                newPicForm.set("describe",describe);
                newPicForm.set("cId",cateId);
                newPicForm.set("place",place);
                newPicForm.set("label",lableId);

                //填充删除图片的id数据
                newPicForm.set("delPicId",cacheDelPic);

                // 一个判断是否提交的flag:未修改就不提交请求
                var postFlag=false;
                for(var pair of newPicForm.entries()) {
                    if(pair[0]!='itemId'){
                        if($.trim(pair[1])!=""){
                            postFlag=true;
                            break;
                        }
                    }
                }
                if(postFlag){
                    //ajax发送数据
                    $.ajax({
                        url : 'ItemsBackManage/updateItem',//访问后台的上传方法路径
                        data : newPicForm,       //保存的formdata集合
                        type : 'POST',
                        cache : false,
                        processData : false,
                        contentType : false,
                        success : function(data) {
                            if(JSON.parse(data).result){
                                alert("上传成功");
                                location.reload();
                            }
                        },
                        error : function(data) {
                            // console.log(newPicForm.get("picFiles"));
                            newPicForm.delete("picFiles");
                            alert("意外错误！");
                        }
                    });
                }else{
                    // console.log(newPicForm.get("picFiles"));
                    newPicForm.delete("picFiles");
                    alert("您未作出任何修改！");
                }


            });
        }
        //出版时间
        $('#publishTime').datetimepicker({
            format: 'yyyy-mm-dd',
            minView: 'month',//设置时间选择为年月日 去掉时分秒选择
            todayBtn: true, //如果此值为true 或 "linked"，则在日期时间选择器组件的底部显示一个 "Today" 按钮用以选择当前日期。如果是true的话，"Today" 按钮仅仅将视图转到当天的日期，如果是"linked"，当天日期将会被选中。
            language: 'zh-CN',
            autoclose: true, //当选择一个日期之后是否立即关闭此日期时间选择器。
            keyboardNavigation: true, //是否允许通过方向键改变日期。
            forceParse: true, //当选择器关闭的时候，是否强制解析输入框中的值。
            todayHighlight: 1  //如果为true, 高亮当前日期
        });
    </script>

</body>
</html>