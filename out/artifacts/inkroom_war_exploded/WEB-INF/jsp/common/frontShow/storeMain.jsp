<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!--右侧的显示内容开始-->
<div class="col-lg-11 col-md-12 col-sm-12 col-xs-12 right-content">
    <div class="row">
        <div class="container-fluid">
            <!-- 商展顶部--start-- -->
            <div class="row title">
                <div class="title-img">
                    <img src="static/images/store_top.jpg" alt="">
                </div>
                <div class="search">
                    <input  type="text" class="form-control" placeholder="请输入查询的内容">
                    <button id="queryItem" type="button"></button>
                </div>
            </div>
            <!-- 商展顶部--end-- -->

            <!--商展页面的主体--start-->
            <div class="row main-wrapper">

                <!--左侧商品展示--start-->
                <div class="col-lg-9 left-show">
                    <!--一个显示总共find商品的div--->
                    <div class="row item-count">
                        我们总共查到了<span id="itemCount" style="color:#4D4D4D"></span>件商品
                    </div>

                    <div class="row addTagBefore">
                        <!-- 一个分页 -->
                        <ul class="pagination store-paging">
                            <li id="prevFlag"><a href="javascript:void(-1);" onclick="toPage(this);" page="prev"><span class="glyphicon glyphicon-chevron-left"></span></a></li>

                            <li id="nextFlag"><a href="javascript:void(-1);" onclick="toPage(this)" page="next"><span class="glyphicon glyphicon-chevron-right"></span></a></li>
                            <!-- 一个分页跳转 -->
                            <span id="pageShow">总共&nbsp;<span>12</span>&nbsp;页,当前为第</span>
                            <select id="pageSelect">
                            </select>
                        </ul>
                    </div>

                </div>
                <!--左侧商品展示---end-->




                <!--右侧过滤栏--start-->
                <div class="col-lg-3 right-filter">

                    <!--过滤器-->
                    <div class="right-filter-item filter-item">
                        <h4>
                            <span class="glyphicon glyphicon-filter"></span> 价格过滤器
                            <div class="item-line"></div>
                        </h4>
                        <!--提交过滤信息到后台-->

                        <!--后台接受的参数的形式为：10;50这样（封号隔开）-->
                        <div id="range_1" name="priceRange"></div>
                        <button id="priceRangeBtn" type="submit">过滤</button>
                    </div>


                    <!--分类-->
                    <div class="right-filter-item cate-item">
                        <h4>
                            <span class="glyphicon glyphicon-list"></span> 商品分类
                            <div class="item-line"></div>
                        </h4>
                        <!--分类项-->
                        <ul id="addCateTag">

                        </ul>
                    </div>

                    <!--标签-->
                    <div class="right-filter-item label-item">
                        <h4>
                            <span class="glyphicon glyphicon-tags"></span> 商品标签
                            <div class="item-line"></div>
                        </h4>
                        <!--具体的标签项-->
                        <ul>
                            <li>
                                <a class="lableTag" lableId='0' href="javascript:void(-1);">成色好</a>
                            </li>
                            <li>
                                <a class="lableTag" lableId='1' href="javascript:void(-1);">价格低</a>
                            </li>
                            <li>
                                <a class="lableTag" lableId='2' href="javascript:void(-1);">稀有</a>
                            </li>
                            <li>
                                <a class="lableTag" lableId='3' href="javascript:void(-1);">性价比高</a>
                            </li>
                            <li>
                                <a class="lableTag" lableId='4' href="javascript:void(-1);">全新书籍</a>
                            </li>
                            <li>
                                <a class="lableTag" lableId='5' href="javascript:void(-1);">有收藏价值</a>
                            </li>
                            <li>
                                <a class="lableTag" lableId='6' href="javascript:void(-1);">破烂书</a>
                            </li>
                        </ul>
                    </div>
                </div>
                <!--右侧过滤栏--end-->
            </div>
        </div>
        <!--商展页面的主体--end-->



    </div>
</div>
<!--右侧的显示内容结束-->