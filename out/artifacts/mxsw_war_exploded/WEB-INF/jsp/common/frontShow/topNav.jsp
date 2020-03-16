<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!--响应式顶部导航栏开始-->
<nav class="navbar navbar-default navbar-fixed-top hidden-lg" role="navigation">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#show-navbar">
                <span class="sr-only">切换导航</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <!--横板导航栏的logo-->
            <a href="#"><img  style="width:50px;" src="static/images/logo.png"></a>
        </div>
        <div class="collapse navbar-collapse" id="show-navbar">
            <ul class="nav navbar-nav">
                <li><a href="#">书展</a></li>
                <li><a href="#">拍卖</a></li>
                <li><a href="#">书友圈</a></li>
                <li><a href="#">免费书</a></li>
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                        个人中心<b class="caret"></b>
                    </a>
                    <ul class="dropdown-menu">
                        <li><a href="#">登陆</a></li>
                        <li><a href="#">登出</a></li>
                    </ul>
                </li>
            </ul>
        </div>
    </div>
</nav>
<!--响应式顶部导航栏结束-->