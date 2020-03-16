<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!--右侧的显示内容开始-->
<div class="col-lg-11 col-md-12 col-sm-12 col-xs-12 right-content">
    <div class="row personal-wrap">
        <!-- 用户登陆界面开始 -->
        <div class="container personal-main">
            <!-- LOGIN 字样标题 -->
            <div class="row">
                <div class="col-md-6 col-md-offset-3  col-sm-6 col-sm-offset-3 personal-form-title">
                    <h1 class="text-center">LOGIN</h1>
                </div>
            </div>
            <!-- 登陆表单开始 -->
            <div class="row">
                <div class="col-md-6 col-md-offset-3 col-sm-6 col-sm-offset-3">
                    <form action="User/login" method="post" id="login-form">
                        <!-- 用户名 -->
                        <div class="form-group">
                            <label>用户ID</label>
                            <div class="input-group">
                                <div class="input-group-addon">
                                    <span class="glyphicon glyphicon-user" aria-hidden="true"></span>
                                </div>
                                <input name="name" type="text" class="form-control" placeholder="Your Name">
                            </div>
                        </div>
                        <!-- 密码 -->
                        <div class="form-group">
                            <label>用户密码</label>
                            <div class="input-group">
                                <div class="input-group-addon">
                                    <span class="glyphicon glyphicon-lock" aria-hidden="true"></span>
                                </div>
                                <input name="password" type="password" class="form-control" placeholder="Your Password">
                            </div>
                        </div>
                        <!-- 登陆注册按钮 -->
                        <div class="form-group personal-btn">
                            <button type="submit" class="btn btn-info">
                                Submit
                            </button>
                            <button type="button" class="btn btn-info" data-toggle="modal" data-target="#join">
                                Join Us
                            </button>
                        </div>
                    </form>
                </div>
            </div>
            <!-- 登陆表单结束 -->
        </div>
        <!-- 用户登陆界面结束 -->
    </div>
</div>
<!--右侧的显示内容结束-->
