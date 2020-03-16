<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!-- 修改个人信息模态框开始 -->
<div class="modal fade" id="updateInfo" tabindex="-1" role="dialog" aria-labelledby="updatePersonalInfo">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="updatePersonalInfo">个人信息</h4>
            </div>
            <div class="modal-body">
                <!--一个隐藏的消息提示，与商品删除有关-->
                <div id="userEditResult" hidden="hidden"  class="alert alert-success">
                    <span id="userEditResult-main"></span>
                </div>

                <!-- 信息区域 -->
                <ul class="info-data">
                    <li>
                        <div class="avatar">
                            <img id="userPic" src="${userPic.userPath}" />
                            <%--一个隐藏的用于接收图片数据的input--%>
                            <input name="userPic" id="userPicInput" style="display:none;" type="file"/>
                            <a id="editUserPic">编辑</a>
                        </div>
                    </li>
                    <li>
                        <label>昵称：</label>
                        <input name="uname" type="text" class="form-control" placeholder="${userData.uName}">
                    </li>
                    <li>
                        <label>性别：</label>
                        <input type="radio" value="man" name="userSex" class="data-radio" checked>男
                        <input type="radio" value="woman" name="userSex" class="data-radio">女
                    </li>
                    <li>
                        <label>手机：</label>
                        <input name="phoneNum" type="number" class="form-control" placeholder="${userData.tel}">
                    </li>
                    <li>
                        <label>邮箱：</label>
                        <input name="email" type="email" class="form-control" placeholder="${userData.email}">
                    </li>
                </ul>

            </div>
            <div class="modal-footer">
                <button id="updateUser" type="button" class="btn btn-primary">保存修改</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">取消修改</button>
            </div>
        </div>
    </div>
</div>
<!-- 修改个人信息模态框结束 -->