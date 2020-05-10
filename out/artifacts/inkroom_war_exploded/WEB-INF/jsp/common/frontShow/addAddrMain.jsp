<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="main-content-wrapper">

    <div class="panel panel-default">
        <div class="panel-heading">
            <h3 class="panel-title">
                添加收货人信息
            </h3>
        </div>

        <div id="addrInfo" class="panel-body">
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

                <div class="col-xs-1">
                    <div class="form-group">
                        <button id="addAddr" type="submit" class="btn btn-default">提交</button>
                    </div>
                </div>
            </div>


        </div>
    </div>

    <div class="panel panel-default">
        <div class="panel-heading">
            <h3 class="panel-title">
                我的收货人信息
            </h3>
        </div>
        <div class="panel-body">
            <table id="addrInfoTable"></table>
        </div>
    </div>

</div>