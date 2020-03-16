<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>个人中心</title>
    <%--引入basePath--%>
    <%@ include file="./../../common/basePath.jsp"%>
    <%--引入前台展示页部分的公共头部--%>
    <%@ include file="./../../common/frontShow/headBase.jsp"%>
    <%---引入bt-validator--%>
    <%@ include file="./../../common/bt_validator.jsp"%>
    <%--引入bootstrap-table--%>
    <%@ include file="./../../common/bt_table.jsp" %>
    <!-- 进入个人中心主页面相关资源 -->
    <link rel="stylesheet" type="text/css" href="static/css/personalMain.css">
    <script src="static/js/userMain.js"></script>
    <%--添加收货人信息的js--%>
    <script src="static/js/addAddr.js"></script>
</head>

<body>
<!-- 外层包装开始 -->
<div class="wrapper">
    <%--引入两个导航栏--%>
    <%@ include file="./../../common/frontShow/topNav.jsp"%>
    <%@ include file="./../../common/frontShow/leftNav.jsp"%>

        <!--右侧的显示内容开始-->
        <div class="col-lg-11 col-md-12 col-sm-12 col-xs-12 right-content">
            <div class="row pannel-main">
                <!-- 顶部个人基本信息 -- start -->
                <div class="col-xs-12 personal-info">
                    <!-- 左侧信息区域 -->
                    <div class="left-info">
                        <!-- 个人头像 -->
                        <a class="thumbnail personal-pic">
                            <img src="${userPic.userPath}" />
                        </a>

                        <!-- 用户ID和昵称 -->
                        <div class="personal-baseInfo">
                            <h4>${userData.uName}
                                <span class="personal-vip"><img src="static/images/vip/vip1.png"></span>
                                <span class="personal-id">(ID:${userData.name})</span>
                            </h4>
                            <span class="greeting">晚上好</span>
                            <a class="update" href="#" data-toggle="modal" data-target="#updateInfo">修改个人信息&nbsp;&gt;&nbsp;&gt;&nbsp;</a>
                        </div>
                    </div>


                    <!-- 右侧信息区域 -->
                    <div class="right-info">
                        <!-- 用户的其他基本信息 -->
                        <div class="personal-otherInfo">
                            <span>安全性: <span class="safe">普通</span></span>
                            <span>邮箱: ${userData.email}</span>
                            <span>手机号: ${userData.tel}</span>
                        </div>
                    </div>
            </div>
            <!-- 顶部个人基本信息 -- end -->

            
            <div class="col-xs-12 personal-info">
                <!-- 导航栏 -->
                <ul class="nav nav-tabs nav-justified">
                    <li><a href="FrontForward/personalMain">购物车</a></li>
                    <li><a href="FrontForward/userOrder">订单</a></li>
                    <%--<li><a href="#">物流</a></li>--%>
                    <li><a href="FrontForward/applyOrder">我的退换货</a></li>
                    <li class="active"><a href="FrontForward/addAddr">收货人信息</a></li>
                </ul>

                <!-- 导航栏下的内容 -->
                <div class="main-content">
                    <%--引入购物车的主内容--%>
                    <%@ include file="./../../common/frontShow/addAddrMain.jsp"%>
                </div>

            </div>

        </div>
    </div>
    <!--右侧的显示内容结束-->


</div>
<!-- 外层包装结束 -->
<%--引入模态框--%>
<%@ include file="./../../common/frontShow/userMainModal.jsp"%>

<!---一个有关修改收货人的模态框-->
<div class="modal fade" id="applyEditModal" tabindex="-1" role="dialog" aria-labelledby="applyEditModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="applyEditModalLabel">修改收货人信息</h4>
            </div>
            <div class="modal-body">
				<div class="row">
					<div class="col-xs-6">
						<div class="form-group">
							<label>收货人</label>
							<input name="name" type="text" class="form-control" placeholder="收货人姓名">
						</div>
					</div>

					<div class="col-xs-6">
						<div class="form-group">
							<label>收货电话</label>
							<input name="tel" type="text" class="form-control" placeholder="收货人联系电话">
						</div>
					</div>
				</div>

				<div class="row">
					<div class="col-xs-6">
						<div class="form-group">
							<label>邮编</label>
							<input name="postcode" type="text" class="form-control" placeholder="收货地邮编">
						</div>
					</div>

					<div class="col-xs-6">
						<div class="form-group">
							<label>省份</label>
							<select class="form-control" name="province">
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
					</div>
				</div>

				<div class="row">
					<div class="col-xs-12">
						<div class="form-group">
							<label>详细收货地址</label>
							<textarea name="addr" class="form-control" rows="3"></textarea>
						</div>
					</div>
				</div>
			</div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button id="postApplyEdit" type="button" class="btn btn-primary">提交</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>



</body>

</html>