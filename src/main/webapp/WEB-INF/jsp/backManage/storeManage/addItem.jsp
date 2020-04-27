<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: ysjba
  Date: 2020/3/21
  Time: 18:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>墨香书屋(商品添加)</title>
    <%--引入basePath--%>
    <%@ include file="./../../common/basePath.jsp"%>
    <%--引入后台管理部分的公共头部--%>
    <%@ include file="./../../common/backManage/headBase.jsp"%>
    <%--引入bt-fileinput--%>
    <%@ include file="./../../common/bt_fileInput.jsp"%>
    <%--引入bt-datepicker--%>
    <%@ include file="./../../common/bt_datetimepicker.jsp"%>
    <%---引入bt-validator--%>
    <%@ include file="./../../common/bt_validator.jsp"%>
    <!--引入商品添加页面的css-->
    <link rel="stylesheet" href="static/css/backManage/addItem.css">
</head>
<body>
    <%--引入导航栏--%>
    <%@ include file="./../../common/backManage/backNav.jsp"%>


    <!-- 主要内容开始 -->
    <div class="right-content">
        <div class="right-content-wrap">
			
			<!--添加商品类型-->
			<div class="panel panel-default addItem-main">
                <div class="panel-heading">
                    <h3 class="panel-title">
                    <span class="glyphicon glyphicon-plus"></span>
                    商品类型添加
                    </h3>
                </div>
                <div class="panel-body">
                    
                    <form action="ItemsBackManage/addCateName" method="POST" class="form-horizontal">
                        <div class="form-group">
                            <label style="float: left;margin-left:30px" class="control-label">分类名</label>
                            <div class="col-sm-4">
                                <input name="cateName" type="text" class="form-control" placeholder="请输入分类名">
                            </div>
                            <div class="col-sm-2">
                                <button type="submit" class="btn btn-default">提交</button>
                            </div>
                        </div> 
                    </form>
               
                </div>
            </div>

            <!--将上传图片部分放在面板里-->
            <div class="panel panel-default addItem-main">
                <div class="panel-heading">
                    <h3 class="panel-title">
                        <span class="glyphicon glyphicon-plus"></span>
                        商品添加
                    </h3>
                </div>
                <div class="panel-body" id="itemInfo">
                    <!--一个隐藏的消息提示，商品添加成功后显示-->
                    <div id="itemAddSuccess" hidden="hidden"  class="alert alert-success">
                        <button type="button" class="close" data-dismiss="alert"
                                aria-hidden="true">
                            &times;
                        </button>
                        <span id="itemAddSuccess-main"></span>
                    </div>


                    <!--商品的基本信息填入-->
                    <div class="col-xs-5">
                        <div class="form-group">
                            <label>商品名称</label>
                            <input name="name" type="text" class="form-control" placeholder="请输入商品名称">
                        </div>
                        <div class="form-group">
                            <label>商品价格</label>
                            <input name="price" type="text" class="form-control" placeholder="请输入商品价格">
                        </div>
                        <div class="form-group">
                            <label>商品类别</label>
                            <select name="itemCate" class="form-control" id="itemCate">
                                <c:forEach items="${Category}" var="obj">
                                    <option value="${obj.cId}">${obj.cateName}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="form-group">
                            <label>商品库存</label>
                            <input name="count" type="text" class="form-control" placeholder="请输入商品库存">
                        </div>
                        <div class="form-group">
                            <label>商品发货地点</label>
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
                        <div class="form-group">
                            <label>书籍作者</label>
                            <input name="author" type="text" class="form-control" placeholder="请输入书籍作者">
                        </div>
                        <div class="form-group">
                            <label>书籍ISBN</label>
                            <input name="ISBN" type="text" class="form-control" placeholder="请输入书籍ISBN">
                        </div>
                        <div class="form-group">
                            <label>书籍新旧程度</label>
                            <input name="oldLevel" type="text" class="form-control" placeholder="例如：8，代表8成新">
                        </div>
                        <div class="form-group">
                            <label>书籍新书比价</label>
                            <input name="compare" type="text" class="form-control" placeholder="请输入书籍新书比价">
                        </div>
                        <div class="form-group">
                            <label>书籍出版时间</label>
                            <input name="publishTime" id="publishTime" class="form-control" placeholder="请输入书籍出版时间">
                        </div>
                        <div class="form-group">
                            <label>书籍出版社</label>
                            <input name="publish" type="text" class="form-control" placeholder="请输入书籍出版社名称">
                        </div>
                        <div class="form-group">
                            <label>书籍标签</label>
                            <select name="lable" class="form-control">
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


                    <div class="col-xs-7">
                        <!--商品介绍填写-->
                        <div class="form-group">
                            <label>商品介绍</label>
                            <textarea name="describe" class="form-control" rows="7"></textarea>
                        </div>

                        <!--商品图片上传-->
                        <div class="form-group">
                            <label>选择商品图片：</label>
                            <span class="text-muted">图片大小不能超过1M=1024kb,尺寸范围：XXX</span>
                            <input name="itemPic" id="item_pic" type="file" class="file-loading" multiple="multiple" accept="image/png,image/jpeg,image/jpg"/>
                        </div>
                        <button id="itemSub" class="btn btn-default">提交添加</button>
                    </div>




                </div>
            </div>











        </div>
    </div>
    <!-- 主要内容结束 -->


    <!--商品图片的剪辑设置-->
    <!--商品图片的剪辑设置-->
    <script type="text/javascript">

        //日期插件
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


        //商品信息验证
        //设置商品信息验证规则，先定义一个函数，提交时验证
        function ValidatorItemInfo(){
            $('#itemInfo').bootstrapValidator({
                message: '你的输入有误',
                feedbackIcons: {
                    valid: 'glyphicon glyphicon-ok',
                    invalid: 'glyphicon glyphicon-remove',
                    validating: 'glyphicon glyphicon-refresh'
                },
                fields: {
                    name: {
                        message: '用户名验证失败',
                        validators: {
                            notEmpty: {
                                message: '用户名不能为空'
                            }
                        }
                    },
                    price:{
                        message:'商品价格验证失败',
                        validators:{
                            notEmpty:{
                                message:'商品价格不能为空'
                            },
                            regexp: {
                                /* 只需加此键值对，包含正则表达式，和提示 */
                                regexp: /^\d+(\.\d+)?$|^$|^(\d+|\-){7,}$/,
                                message: '只能是数字(包括小数)'
                            }
                        }
                    },
                    count:{
                        message:'商品库存验证失败',
                        validators:{
                            notEmpty:{
                                message:'商品库存不能为空'
                            },
                            numeric: {
                                message: '只能为整数'
                            }
                        }
                    },
                    author:{
                        message:'书籍作者验证失败',
                        validators:{
                            stringLength: {
                                max:10,
                                message: '作者名称长度最长为10'
                            },
                            notEmpty:{
                                message:'书籍作者不能为空'
                            }
                        }
                    },
                    ISBN:{
                        message:'ISBN验证失败',
                        validators:{
                            stringLength: {
                                min:10,
                                max:13,
                                message: 'ISBN不合规范'
                            },
                            numeric:{
                                message:'ISBN只能为数字'
                            },
                            notEmpty:{
                                message:'ISBN不能为空'
                            }
                        }
                    },
                    oldLevel:{
                        message:'新旧程度验证失败',
                        validators:{
                            notEmpty:{
                                message:'不能为空'
                            },
                            numeric:{
                                message:'只能为整数'
                            },
                            between:{
                                message:'只能为0-10',
                                min:0,
                                max:10
                            }
                        }
                    },
                    compare:{
                        message:'新书比价验证失败',
                        validators:{
                            notEmpty:{
                                message:'不能为空'
                            },
                            regexp: {
                                /* 只需加此键值对，包含正则表达式，和提示 */
                                regexp: /^0+(\.\d+)?$|^$|^(\d+|\-){7,}$/,
                                message: '只能为小于1的小数'
                            },
                            stringLength:{
                                max:4
                            }

                        }
                    },
                    publish:{
                        message:'出版社名称验证失败',
                        validators:{
                            notEmpty:{
                                message:'不能为空'
                            }
                        }
                    },
                    describe:{
                        message:'商品描述验证失败',
                        validators:{
                            notEmpty:{
                                message:'不能为空'
                            },
                            stringLength:{
                                max:255,
                                message:'超出最大字符255'
                            }
                        }
                    },
                    publishTime:{
                        message:'出版时间验证失败',
                        validators:{
                            callback: {/*自定义，可以在这里与其他输入项联动校验*/
                                message: '出版时间不能为空',
                                callback:function(value,validator,$field){
                                    var publishTime=$("input[name='publishTime']").val();
                                    if($.trim(publishTime)==''){
                                        return false;
                                    }
                                    return true;
                                }
                            }
                        }
                    }

                }
            });
        }
        ValidatorItemInfo();



        //一个暂存表单参数的全局变量
        var cacheExtData;

        //图片上传插件
        //没有显示提交按钮，需要手动提交
        function doFileInput(extData){
                cacheExtData=extData;
                //alert(cacheExtData);
                $("#item_pic").fileinput({
                language: 'zh', //设置语言
                uploadUrl:"ItemsBackManage/ToAdd", //上传的地址
                allowedFileExtensions: ['jpg', 'jpeg', 'png'],//接收的文件后缀
                uploadExtraData:function(){
                    return cacheExtData;
                },
                uploadAsync: false, //默认异步上传*****为了一次性提交图片设置为false
                showUpload:false, //是否显示上传按钮
                showRemove :true, //显示移除按钮
                showPreview :true, //是否显示预览
                showCaption:true,//是否显示标题*****为了美观，设置为true
                
                browseClass:"btn btn-primary", //按钮样式    
                dropZoneEnabled: true,//是否显示拖拽区域
                //minImageWidth: 50, //图片的最小宽度
                //minImageHeight: 50,//图片的最小高度
                //maxImageWidth: 1000,//图片的最大宽度
                //maxImageHeight: 1000,//图片的最大高度
                //maxFileSize:800,//单位为kb，如果为0表示不限制文件大小
                
                maxFileCount:4, //表示允许同时上传的最大文件个数
                allowedPreviewTypes: ['image'],
                layoutTemplates :{
                    actionUpload:'',//去除上传预览缩略图中的上传图片；
                    actionZoom:''    //关闭详情展示，没必要展示
                },
                enctype:'multipart/form-data',
                validateInitialCount:true,
                previewFileIcon: "<iclass='glyphicon glyphicon-king'></i>",
                msgFilesTooMany: "选择上传的文件数量({n}) 超过允许的最大数值{m}！",
            }).on("filebatchuploadsuccess", function(event, data) {
                    //alert(data.response.result);
                if(data.response.result){
                    //上传成功后的回调
                    //发送成功后提示发送成功信息
                    $("#itemAddSuccess-main").text("成功！你已添加商品成功！");
                    $("#itemAddSuccess").removeAttr("hidden");
                    $("#itemInfo input,textarea").val("");
                    $("#itemInfo").data('bootstrapValidator').destroy();
                    $('#itemInfo').data('bootstrapValidator', null);
                    ValidatorItemInfo();
                    $("#item_pic").fileinput('clear');
                    doFileInput();
                    //alert("dsads");
                }else{
                    $("#itemAddSuccess-main").text("失败！请重试！");
                    $("#itemAddSuccess").removeAttr("hidden");
                }
                
            }).on('filebatchuploaderror', function(event, data, msg) {
                //alert("提交失败");
                //发送失败后的提示
                $("#itemAddSuccess-main").text("失败！网络错误！");
                $("#itemAddSuccess").removeAttr("hidden");
            });

        }
        doFileInput();





        //点击提交商品添加按钮，触发提交操作
        $("#itemSub").click(function(){
            //验证销毁，此步骤必须要，不然将保留上次的错误验证提示
            $("#itemInfo").data('bootstrapValidator').destroy();
            $('#itemInfo').data('bootstrapValidator', null);
            ValidatorItemInfo();

            //提交验证
            $("#itemInfo").bootstrapValidator('validate');
            if($("#itemInfo").data('bootstrapValidator').isValid()) {
                //获取数据
                // var name=$("input[name='name']").val();
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
                var cateId=$("select[name='itemCate']  option:selected").val();
                var place=$("select[name='place']").val();
                var lableId=$("select[name='lable']  option:selected").val();


                //构造json对象
                /*  var data={};
                  data['name']=name;
                  data['cId']=cateId;
                  data['price']=price;
                  data['count']=count;
                  data['author']=author;
                  data['isbn']=ISBN;
                  data['oldLevel']=oldLevel;
                  data['compare']=compare;
                  data['describe']=describe;
                  data['publishTime']=publishTime;
                  data['publish']=publish;
                  data['place']=place;
                  data['label']=lableId;*/

                //测试json数据
                //alert(JSON.stringify(data));

                //获取商品图片个数，如果是0，则取消上传
                var picCount=$('#item_pic').fileinput('getFilesCount');
                if(picCount==0){
                    //发送失败后的提示
                    $("#itemAddSuccess-main").text("失败！未选择商品图片！");
                    $("#itemAddSuccess").removeAttr("hidden");
                }else{

                    var extraData={
                        // extraData:data
                        //  extraData:JSON.stringify(data)
                        name:name,
                        price:price,
                        count:count,
                        author:author,
                        isbn:ISBN,
                        oldLevel:oldLevel,
                        compare:compare,
                        publish:publish,
                        publishTime:publishTime,
                        describe:describe,
                        cId:cateId,
                        place:place,
                        label:lableId
                    };
                    //extraData=JSON.stringify(extraData);
                    doFileInput(extraData);
                    //此外将图片发送给后台
                    //alert($('#item_pic').fileinput('getFilesCount'));
                    $('#item_pic').fileinput('upload'); //触发插件开始上传。

                }
            }else{
                //提示商品信息未完善
                $("#itemAddSuccess-main").text("失败！请认真检查商品信息是否完善！");
                $("#itemAddSuccess").removeAttr("hidden");
            }

            //$('#item_pic').fileinput('upload'); //触发插件开始上传。
        });


    </script>



</body>

</html>
