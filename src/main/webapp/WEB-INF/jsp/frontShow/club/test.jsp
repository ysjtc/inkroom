<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>导航栏demo</title>
    <!-- 引入 Bootstrap -->
    <link rel="stylesheet" type="text/css" href="./../../../../static/css/bootstrap.min.css">
    <!-- jQuery (Bootstrap 的 JavaScript 插件需要引入 jQuery) -->
    <script src="./../../../../static/js/jquery-3.3.1.js"></script>
    <!-- 包括所有已编译的插件 -->
    <script src="./../../../../static/js/bootstrap.min.js"></script>
    <!-- HTML5 Shiv 和 Respond.js 用于让 IE8 支持 HTML5元素和媒体查询 -->
    <!-- 注意： 如果通过 file://  引入 Respond.js 文件，则该文件无法起效果 -->
    <!--[if lt IE 9]>
	    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
	    <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
	<![endif]-->
    <link rel="stylesheet" type="text/css" href="./../../../../static/css/base.css">
    <link rel="stylesheet" type="text/css" href="./../../../../static/css/navigation.css">
    <link rel="stylesheet" type="text/css" href="./../../../../static/css/clubIndex.css">
    <script src="./../../../../static/js/nav.js"></script>

</head>

<body>
    <!-- 外层包装开始 -->
    <div class="wrapper">
        <!--左侧导航栏开始-->
        <div class="col-lg-1 visible-lg left-nav">
            <!-- logo -->
            <div class="row">
                <div class="nav-logo">
                    <a href="./../navigation/nav.html"></a>
                </div>
            </div>
            <!-- 上部导航模块 -->
            <div class="row">
                <div class="nav-item">
                    <a href="./../store/store.html">
                        <span class="glyphicon glyphicon-book"></span>
                        <span class="nav-section">商城</span>
                    </a>
                    <div class="subnav">
                        <ul>
                            <li><a href="#">最新书籍</a></li>
                            <li><a href="#">热门书籍</a></li>
                            <li><a href="#">限时折扣</a></li>
                            <li><a href="#">好评书籍</a></li>
                        </ul>
                    </div>
                </div>
                <div class="nav-item">
                    <a href="./../auction/main.html">
                        <span class="glyphicon glyphicon-fire"></span>
                        <span class="nav-section">拍卖区</span>
                    </a>
                    <div class="subnav">
                        <ul>
                            <li><a href="#">最新上架</a></li>
                            <li><a href="#">拍卖热区</a></li>
                            <li><a href="#">限时拍卖</a></li>
                        </ul>
                    </div>
                </div>
                <div class="nav-item">
                    <a href="./../community/main.html">
                        <span class="glyphicon glyphicon-globe"></span>
                        <span class="nav-section">书友社</span>
                    </a>
                    <div class="subnav">
                        <ul>
                            <li><a href="#">最新专题</a></li>
                            <li><a href="#">热门专题</a></li>
                            <li><a href="#">活动专题</a></li>
                        </ul>
                    </div>
                </div>
                <div class="nav-item">
                    <a href="./../free/main.html">
                        <span class="glyphicon glyphicon-heart"></span>
                        <span class="nav-section">免费送</span>
                    </a>
                    <div class="subnav">
                        <ul>
                            <li><a href="#">限时免费</a></li>
                            <li><a href="#">提供书籍</a></li>
                        </ul>
                    </div>
                </div>
            </div>
            <!-- 个人中心导航模块 -->
            <div class="row">
                <div class="nav-personal">
                    <a class="nav-personal-img" href="../personal/login.html">
                        <img src="../../../../static/images/account.png" alt="">
                    </a>
                    <div class="subnav">
                        <ul>
                            <li><a href="#">我的订单</a></li>
                            <li><a href="#">我的收藏</a></li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
        <!--左侧导航栏结束-->

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
                    <a href="#"><img style="width:50px;" src="static/images/logo.png"></a>
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

        <!--右侧的显示内容开始-->
        <div class="col-lg-11 col-md-12 col-sm-12 col-xs-12 right-content">
            <div class="row">
                <!-- 中间主体内容 -- start -->
                <div class="main col-sm-8">

                    <!-- 轮播图 -- start -->
                    <div id="carousel-example-generic" class="club-panel carousel slide" data-ride="carousel">
                        <!-- 轮播图底部导航 -->
                        <ol class="carousel-indicators">
                            <li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
                            <li data-target="#carousel-example-generic" data-slide-to="1"></li>
                            <li data-target="#carousel-example-generic" data-slide-to="2"></li>
                        </ol>

                        <!-- 轮播内容 -->
                        <div class="carousel-inner" role="listbox">
                            <div class="item active">
                                <img src="./../../../../static/images/club_carousel_1.jpg" alt="...">
                            </div>
                            <div class="item">
                                <img src="./../../../../static/images/club_carousel_1.jpg" alt="...">
                            </div>
                            <div class="item">
                                <img src="./../../../../static/images/club_carousel_1.jpg" alt="...">
                            </div>
                        </div>

                        <!-- 轮播图两侧控件 -->
                        <a class="left carousel-control" href="#carousel-example-generic" role="button" data-slide="prev">
                            <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
                            <span class="sr-only">Previous</span>
                        </a>
                        <a class="right carousel-control" href="#carousel-example-generic" role="button" data-slide="next">
                            <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
                            <span class="sr-only">Next</span>
                        </a>
                    </div>
                    <!-- 轮播图 -- end -->

                    <!-- 文章部分 -- start -->
                    <div class="articles club-panel">
                        <!-- 文章列表 -- start -->
                        <ul class="article-list">
                            <!-- 不含图片的文章 -->
                            <li class="">
                                <div class="content">
                                    <a class="item-title" href="#">Flutter中Google官方建议Json序列化方案</a>
                                    <p class="item-abstract">在微博首页看到了这样一个采访： “未来社会，90%的人，由于过度放纵欲望，可能会像蛆一样地活着，他们没有能力改变自己，约束自己，而是沉醉在短暂的... 在微博首页看到了这样一个采访： “未来社会，90%的人，由于过度放纵欲望，可能会像蛆一样地活着，他们没有能力改变自己，约束自己，而是沉醉在短暂的...
                                    </p>
                                    <div class="item-meta">
                                        <!-- 作者信息 -->
                                        <div class="author">
                                            <img src="./../../../../static/images/personal_img.png" alt="">
                                            <span class="author-name">淦得漂亮</span>

                                        </div>
                                        <!-- 赞、评论数 -->
                                        <div class="feedback">
                                            <i class="glyphicon glyphicon-comment" aria-hidden="true"></i><span>23</span>
                                            <i class="glyphicon glyphicon-heart" aria-hidden="true"></i><span>100</span>
                                            <span class="publish-time">2019.08.20 21:34</span>
                                        </div>
                                    </div>
                                </div>
                            </li>

                            <!-- 含图片的文章 -->
                            <li class="have-img">
                                <!-- 文章内容显示 -->
                                <div class="content">
                                    <a class="item-title" href="#">Flutter中Google官方建议Json序列化方案</a>
                                    <p class="item-abstract">在微博首页看到了这样一个采访： “未来社会，90%的人，由于过度放纵欲望，可能会像蛆一样地活着，他们没有能力改变自己，约束自己，而是沉醉在短暂的... 在微博首页看到了这样一个采访： “未来社会，90%的人，由于过度放纵欲望，可能会像蛆一样地活着，他们没有能力改变自己，约束自己，而是沉醉在短暂的...
                                    </p>
                                    <div class="item-meta">
                                        <!-- 作者信息 -->
                                        <div class="author">
                                            <img src="./../../../../static/images/personal_img.png" alt="">
                                            <span class="author-name">淦得漂亮</span>

                                        </div>
                                        <!-- 赞、评论数 -->
                                        <div class="feedback">
                                            <i class="glyphicon glyphicon-comment" aria-hidden="true"></i><span>23</span>
                                            <i class="glyphicon glyphicon-heart" aria-hidden="true"></i><span>100</span>
                                            <span class="publish-time">2019.08.20 21:34</span>
                                        </div>
                                    </div>
                                </div>
                                <!-- 图片显示 -->
                                <a href="#" class="article-img">
                                    <img src="./../../../../static/images/article_img.png" alt="">
                                </a>
                            </li>
                        </ul>
                        <!-- 文章列表 -- end -->
                    </div>
                    <!-- 文章部分 -- end -->

                    <!-- 显示更多 -->
                    <a href="" class="more">更多内容</a>
                </div>
                <!-- 中间主体内容 -- end -->

                <!-- 侧边栏位 -- start -->
                <div class="aside col-sm-3">
                    <!-- 个人信息 -- start -->
                    <div class="club-panel userInfo">
                        <div class="top">
                            <div class="userInfo-img">
                                <img src="./../../../../static/images/personal_img.png" alt="">
                            </div>
                            <div class="userInfo-button">
                                <a href="#"><i class="glyphicon glyphicon-pencil" aria-hidden="true"></i>写作</a>
                                <a href="#"><i class="glyphicon glyphicon-home" aria-hidden="true"></i>主页</a>
                            </div>
                        </div>
                        <div class="bottom">
                            <ul>
                                <li>
                                    <a>
                                        <span>100</span>
                                        <p>粉丝</p>
                                    </a>
                                </li>
                                <li>
                                    <a>
                                        <span>100</span>
                                        <p>关注</p>
                                    </a>
                                </li>
                                <li>
                                    <a>
                                        <span>100</span>
                                        <p>文章</p>
                                    </a>
                                </li>
                            </ul>
                        </div>
                    </div>
                    <!-- 个人信息 -- end -->

                    <!-- 搜索框 -- start -->
                    <div class="search">
                        <input type="text">
                        <a class="glyphicon glyphicon-search" aria-hidden="true"></a>
                    </div>
                    <!-- 搜索框 -- end -->

                    <!-- 推荐作者 -- start -->
                    <div class="club-panel recommend">
                        <div class="recommend-top">
                            <h2>推荐作者</h2>
                            <a href="#">换一批<i class="glyphicon glyphicon-refresh" aria-hidden="true"></i></a>
                        </div>
                        <ul class="recommend-main">
                            <li>
                                <a class="avatar">
                                    <img src="./../../../../static/images/personal_img.png" alt="">
                                </a>
                                <a href="" class="follow">
                                    <i class="glyphicon glyphicon-plus" aria-hidden="true"></i> 关注
                                </a>
                                <a class="nickname">淦得漂亮</a>
                                <p>200关注 · 300喜欢</p>
                            </li>
                            <li>
                                <a class="avatar">
                                    <img src="./../../../../static/images/personal_img.png" alt="">
                                </a>
                                <a href="" class="follow">
                                    <i class="glyphicon glyphicon-plus" aria-hidden="true"></i> 关注
                                </a>
                                <a class="nickname">淦得漂亮</a>
                                <p>200关注 · 300喜欢</p>
                            </li>
                            <li>
                                <a class="avatar">
                                    <img src="./../../../../static/images/personal_img.png" alt="">
                                </a>
                                <a href="" class="follow">
                                    <i class="glyphicon glyphicon-plus" aria-hidden="true"></i> 关注
                                </a>
                                <a class="nickname">淦得漂亮</a>
                                <p>200关注 · 300喜欢</p>
                            </li>
                            <li>
                                <a class="avatar">
                                    <img src="./../../../../static/images/personal_img.png" alt="">
                                </a>
                                <a href="" class="followed">
                                    <i class="glyphicon glyphicon-ok" aria-hidden="true"></i> 已关注
                                </a>
                                <a class="nickname">淦得漂亮</a>
                                <p>200关注 · 300喜欢</p>
                            </li>
                        </ul>
                    </div>
                    <!-- 推荐作者 -- end -->
                </div>
                <!-- 侧边栏位 -- end -->
            </div>
        </div>
        <!--右侧的显示内容结束-->

    </div>
    <!-- 外层包装结束 -->
</body>

</html>