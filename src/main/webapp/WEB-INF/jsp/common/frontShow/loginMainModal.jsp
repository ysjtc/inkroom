<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!-- 用户注册界面弹出开始 -->
<div class="modal fade personal-join" id="join" tabindex="-1" role="dialog">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <!-- Join Us 字样标题 -->
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h3 class="modal-title text-center">Join Us</h3>
            </div>
            <!-- 注册表单开始 -->
            <form action="User/register" method="POST" id="registerValidate">
                <div class="modal-body">
                    <!-- 用户名 -->
                    <div class="form-group">
                        <label>用户昵称</label>
                        <div class="input-group">
                            <div class="input-group-addon">
                                <span class="glyphicon glyphicon-user" aria-hidden="true"></span>
                            </div>
                            <input name="uName" type="text" class="form-control" placeholder="Your Name">
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
                    <!-- 再次输入密码 -->
                    <div class="form-group">
                        <label>确认密码</label>
                        <div class="input-group">
                            <div class="input-group-addon">
                                <span class="glyphicon glyphicon-lock" aria-hidden="true"></span>
                            </div>
                            <input name="repassword" type="password" class="form-control" placeholder="Re-Password">
                        </div>
                    </div>
                    <!-- 邮箱 -->
                    <div class="form-group">
                        <label>电子邮箱</label>
                        <div class="input-group">
                            <div class="input-group-addon">
                                <span class="glyphicon glyphicon-envelope" aria-hidden="true"></span>
                            </div>
                            <input name="email" class="form-control" placeholder="Your Email">
                        </div>
                    </div>
                    <!-- 联系电话 -->
                    <!-- <div class="form-group">
                        <label for="joinPhone">Phone</label>
                        <div class="input-group">
                            <div class="input-group-addon">
                                <span class="glyphicon glyphicon-earphone" aria-hidden="true"></span>
                            </div>
                            <input type="number" class="form-control" id="joinPhone" placeholder="Your Phone">
                        </div>
                    </div> -->
                </div>
                <!-- 注册按钮 -->
                <div class="modal-footer">
                    <div class="form-group personal-btn">
                        <button type="submit" class="btn btn-info">
                            Join Us
                        </button>
                        <button type="reset" class="btn btn-info">
                            Reset Info
                        </button>
                    </div>
                </div>
            </form>
            <!-- 注册表单结束 -->
        </div>
    </div>
</div>
<!-- 用户注册界面弹出结束 -->