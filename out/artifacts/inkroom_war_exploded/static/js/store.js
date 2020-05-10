$(document).ready(function() {



    //设置RangeSlider插件的一些参数---开始
    $("#range_1").ionRangeSlider({
        min: 0,
        max: 400,
        from: 15,
        to: 100,
        type: 'double', //设置几个滑块
        step: 1,
        prefix: "", //设置数值前缀
        postfix: "元", //设置数值后缀
        prettify: true,
        hasGrid: true
    });
    //设置RangeSlider插件的一些参数---结束
    

    

    //处理商展页的数据显示
    //X0首次访问时
    //首先请求分类信息
    $.ajax({
        url : 'ItemsFrontManage/query/ItemsItemCate',
        success : function(data) {
            var cateJsonData=JSON.parse(data);
            // cateJsonData={
            //     "result":true,
            //     "1":["安卓开发",23],
            //     "2":["区块链",22],
            //     "3":["人工智能",33]
            // };
            
            if(cateJsonData['result']){
                $("#addedCate").remove();
                $.each(cateJsonData,function(key,value){
                    if(key!="result"){
                        var cateTag="<li><a class='addedCate' cateId='"+key+"' href='javascript:void(-1);'>"+value[0]+"<span>("+value[1]+")</span></a></li>";
                        $("#addCateTag").append(cateTag);
                    }
                });
            }else{
                alert("商品类别请求失败！请重试！");
            }
        },
        error : function(data){
            alert("请求失败！请刷新再试！");
        }
    });


    //一个处理商品数据的函数，包括分页等功能
    function showItemInfo(queryData="",pageSize=5,offset=0,sort="item_id",sortOrder="asc",priceRange="",cateId="",labelId=""){
        var firstQuery={};
        firstQuery['pageSize']=pageSize;
        firstQuery['offset']=offset;
        firstQuery['sort']=sort;
        firstQuery['sortOrder']=sortOrder;
        firstQuery['queryData']=queryData;
        firstQuery['priceRange']=priceRange;
        firstQuery['cateId']=cateId;
        firstQuery['labelId']=labelId;
        $.ajax({
            url : 'ItemsFrontManage/query/AllItems',
            data : firstQuery,
            type : 'POST',
            success : function(data) {
                //itemsJsonData={"result":true,"total":26,"rows":[{"item_id":1,"name":"1","cateName":"2","price":"3","count":"4","author":"5","ISBN":"6","old_level":"7","compare":"8","publish_time":"9","publish":"10","place":"11","label":"12","item_pic":"13"},{"item_id":1,"name":"1","cateName":"2","price":"3","count":"4","author":"5","ISBN":"6","old_level":"7","compare":"8","publish_time":"9","publish":"10","place":"11","label":"12","item_pic":"13"},{"item_id":1,"name":"1","cateName":"2","price":"3","count":"4","author":"5","ISBN":"6","old_level":"7","compare":"8","publish_time":"9","publish":"10","place":"11","label":"12","item_pic":"13"},{"item_id":1,"name":"1","cateName":"2","price":"3","count":"4","author":"5","ISBN":"6","old_level":"7","compare":"8","publish_time":"9","publish":"10","place":"11","label":"12","item_pic":"13"},{"item_id":1,"name":"1","cateName":"2","price":"3","count":"4","author":"5","ISBN":"6","old_level":"7","compare":"8","publish_time":"9","publish":"10","place":"11","label":"12","item_pic":"13"}]};
                var itemsJsonData=JSON.parse(data);
                if(itemsJsonData['result']){
                    // console.log(data);
                    var total=itemsJsonData['total'];
                    //设置商展页的总数显示
                    $("#itemCount").html(total);
                    var v=itemsJsonData['rows'];
                    // console.log(v);
                    $(".addedTag").remove();
                    $.each(v,function(index,value){
                        //先取数据
                        var item_id=value['item_id'];
                        var item_pic=value['item_pic'];
                        var name=value['name'];
                        var price=value['price'];
                        var old_level=value['old_level'];
                        var compare=value['compare'];
                        var count=value['count'];
                        var place=value['place'];
                        var author=value['author'];
                        var publish=value['publish'];
                        var publish_time=value['publish_time'];
                        var ISBN=value['ISBN'];
                        var postage=15;
                        //每一项都是一个商品相关的json
                        //动态添加商展列表
                        var addTag="<div class=\"row addedTag\"><div class=\"item-box\"><div class=\"col-lg-5 col-md-5 col-sm-6 col-xs-12 item-box-img\"><div class=\"img-wrapper\"><img src=\""+item_pic+"\"/><div class=\"item-box-img-mask\"></div><a item_id='"+item_id+"' class='showMore' href=\"javascript:void(-1);\" data-toggle=\"modal\" data-target=\"#myModal\"><div class=\"item-box-img-mask-readMore\">查看详细</div></a></div></div><div class=\"col-lg-7 col-md-7 col-sm-6 col-xs-12 item-box-text\"><div class=\"text-des\"><h3>《"+name+"》</h3><table><tr><td>价格："+price+"元</td><td>邮费："+postage+"元</td></tr><tr><td>新旧程度："+old_level+"成新</td><td>新书比价："+compare+"</td></tr><tr><td>库存："+count+"</td><td>发货地点："+place+"</td></tr><tr><td>作者："+author+"</td><td>出版社："+publish+"</td></tr><tr><td>出版时间："+publish_time+"</td><td>ISBN:"+ISBN+"</td></tr></table><div class=\"text-des-buttom\"><a item_id='"+item_id+"' type=\"button\" class=\"btn btn-warning btn-xs showMore\" data-toggle=\"modal\" data-target=\"#myModal\">查看详细</a><a item_id='"+item_id+"' type=\"button\" class=\"btn btn-warning btn-xs addCart\">加入购物车</a><a item_id='"+item_id+"' type=\"button\" class=\"btn btn-warning btn-xs buyNow\">立即购买</a></div></div></div></div></div>";
                        
                        $(".addTagBefore").before(addTag);  
                    });
                    //接下来做分页的样式处理
                    var totalPage=parseInt((total+4)/5);
                    // offset=15;
                    var curentPage=parseInt(offset/5)+1;
                    // console.log(curentPage);
                    $("#pageShow>span").html(totalPage);
                    $(".addedSelTag").remove();
                    for(i=0;i<totalPage;i++){
                        //当前页的select
                        if((i+1)==curentPage){
                            var selectTag="<option selected='selected' class='addedSelTag' value=\""+(i+1)+"\">"+(i+1)+"页</option>";
                        }else{
                            var selectTag="<option class='addedSelTag' value=\""+(i+1)+"\">"+(i+1)+"页</option>";
                        }
                        
                        $("#pageSelect").prepend(selectTag);
                    }
                    //分页处理(分页条显示5项)
                    $(".addedLiTag").remove();
                    if((curentPage-2)>0&&(curentPage+2)<=totalPage){
                        //以curentPage为中心，两边各2各项展开分页
                        for(i=(curentPage-2);i<=curentPage+2;i++){
                            if (i==curentPage) {
                                    var pageTag="<li class='clicked addedLiTag'><a href=\"javascript:void(-1);\" class=\"toPage\" page=\""+(i)+"\">"+(i)+"</a></li>";
                                }else{
                                    var pageTag="<li class='addedLiTag'><a href=\"javascript:void(-1);\" class=\"toPage\" page=\""+(i)+"\">"+(i)+"</a></li>";
                                }
                                $("#nextFlag").before(pageTag);
                        }
                    }else{
                        if(totalPage<6){
                            for(i=0;i<totalPage;i++){
                                // alert("i----"+(i+1));
                                if ((i+1)==curentPage) {
                                    var pageTag="<li class='clicked addedLiTag'><a href=\"javascript:void(-1);\" class=\"toPage\" page=\""+(i+1)+"\">"+(i+1)+"</a></li>";
                                }else{
                                    var pageTag="<li class='addedLiTag'><a href=\"javascript:void(-1);\" class=\"toPage\" page=\""+(i+1)+"\">"+(i+1)+"</a></li>";
                                }
                                $("#nextFlag").before(pageTag);
                            }
                        }else{
                            if((curentPage-2)<1){
                                for(i=0;i<5;i++){
                                    // alert("i----"+(i+1));
                                    if ((i+1)==curentPage) {
                                        var pageTag="<li class='clicked addedLiTag'><a href=\"javascript:void(-1);\" class=\"toPage\" page=\""+(i+1)+"\">"+(i+1)+"</a></li>";
                                    }else{
                                        var pageTag="<li class='addedLiTag'><a href=\"javascript:void(-1);\" class=\"toPage\" page=\""+(i+1)+"\">"+(i+1)+"</a></li>";
                                    }
                                    $("#nextFlag").before(pageTag);
                                }
                            }else{
                                //从next开始倒着，达到5项停止
                                for(i=5,n=totalPage;i>0;i--,n--){
                                    if (n==curentPage) {
                                        var pageTag="<li class='clicked addedLiTag'><a href=\"javascript:void(-1);\" class=\"toPage\" page=\""+n+"\">"+n+"</a></li>";
                                    }else{
                                        var pageTag="<li class='addedLiTag'><a href=\"javascript:void(-1);\" class=\"toPage\" page=\""+n+"\">"+n+"</a></li>";
                                    }
                                    
                                    $("#prevFlag").after(pageTag);
                                }
                            }
                            
                        }
                    }




                    
                    // doclickedPage();  
                }else{
                    alert("该类商品暂时缺货,待补货中...");
                    searchCache="";
                    priceRangeCache="";
                    cateIdCache="";
                    labelIdCache="";
                    showItemInfo(searchCache);
                }
                
            },
            error : function(data) {
                alert("意外错误！请重试！");
            }
        });
    }
    showItemInfo();

    //一个暂存上一次搜索的关键词的变量
    var searchCache="";
    //一个暂存上一次的价格过滤范围的变量
    var priceRangeCache="";
    //一个暂存上一次分类id的变量
    var cateIdCache="";
    //一个暂存上一次标签id的变量
    var labelIdCache="";

    //X1查询时
    $("#queryItem").click(function(){
        // if(searchCache!=""){alert("有上一次的搜索记录")}
        var data=$(this).prev().val();
        searchCache=data;
        priceRangeCache="";
        cateIdCache="";
        labelIdCache="";
        showItemInfo(searchCache);
    });

    //X2点击分页项时
    $(".pagination.store-paging").on("click",".addedLiTag .toPage",function(){
        var page=$(this).attr("page");
        var offset=(page-1)*5;
        showItemInfo(searchCache,5,offset,"item_id","asc",priceRangeCache,cateIdCache,labelIdCache);
    });


    //X3价格过滤器提交时
    $("#priceRangeBtn").click(function(){
        var priceRange=$("#range_1").val();
        priceRangeCache=priceRange;
        showItemInfo(searchCache,5,0,"item_id","asc",priceRangeCache,cateIdCache,labelIdCache);
    });

    //当选择了分类时
    $("#addCateTag").on("click",".addedCate",function(){
        var cateId=$(this).attr("cateId");
        cateIdCache=cateId;
        showItemInfo(searchCache,5,0,"item_id","asc",priceRangeCache,cateIdCache,labelIdCache);
    });

    //选择了标签时
    $(".lableTag").click(function(){
        var labelId=$(this).attr("lableId");
        labelIdCache=labelId;
        showItemInfo(searchCache,5,0,"item_id","asc",priceRangeCache,cateIdCache,labelIdCache);
    });
    

    //点击了select时
    $("#pageSelect").change(function(){
        var selectPage=$(this).val();
        var offset=(selectPage-1)*5;
        showItemInfo(searchCache,5,offset,"item_id","asc",priceRangeCache,cateIdCache,labelIdCache);
    });

    //点击了查看更多时

    $(".left-show").on("click",".showMore",function(){
        var item_id={};
        item_id['item_id']=$(this).attr("item_id");
        //ajax发送请求商品信息
        $.ajax({
            url : 'ItemsFrontManage/query/ItemByID',
            method : 'POST',
            data : item_id,
            async: false,
            success : function(data) {
                var itemJsonData=JSON.parse(data);
                //itemJsonData={"desc":"不错的商品","item_id":1,"name":"1","cateName":"2","price":"3","count":"4","author":"5","ISBN":"6","old_level":"7","compare":"8","publish_time":"9","publish":"10","place":"11","label":"12","item_pic":["13","14","15"]};

                //需要的参数
                var item_pic=itemJsonData['item_pic'];
                var name=itemJsonData['name'];
                var price=itemJsonData['price'];
                var old_level=itemJsonData['old_level'];
                var compare=itemJsonData['compare'];
                var count=itemJsonData['count'];
                var place=itemJsonData['place'];
                var author=itemJsonData['author'];
                var publish=itemJsonData['publish'];
                var publish_time=itemJsonData['publish_time'];
                var ISBN=itemJsonData['ISBN'];
                var desc=itemJsonData['desc'];
                var postage=15;

                //动态添加数据
                $(".addedIndicators").remove();
                for(i=0;i<item_pic.length;i++){
                    var tag="<li class='addedIndicators' data-target=\"#carousel-example-generic\" data-slide-to=\""+i+"\"></li>";
                    if(i==0){
                        tag="<li class='active addedIndicators' data-target=\"#carousel-example-generic\" data-slide-to=\""+i+"\"></li>";
                    }
                    $(".carousel-indicators").append(tag);
                }
                $(".addedImg").remove();
                for(i=0;i<item_pic.length;i++){
                    var tag="<div class='item addedImg'><img src='"+item_pic[i]+"'></div>";
                    if(i==0){
                        tag="<div class='item active addedImg'><img src='"+item_pic[i]+"'></div>";
                    }
                    $(".carousel-inner").append(tag);
                }
                $("#modal_item_name").html(name);
                $("#modal_item_price").html(price);
                $("#modal_item_postage").html(postage);
                $("#modal_item_oldLevel").html(old_level);

                $("#modal_item_compare").html(compare);
                $("#modal_item_count").html(count);
                $("#modal_item_place").html(place);
                $("#modal_item_author").html(author);
                $("#modal_item_publish").html(publish);
                $("#modal_item_publishTime").html(publish_time);
                $("#modal_item_ISBN").html(ISBN);
                $("#modal_item_desc").html(desc);

            },
            error : function(data){
                alert("请求失败！请刷新再试！");
            }
        });
    });

	//点击了prev按钮
    $("#prevFlag").click(function(){
        var currentPage=$("li.clicked.addedLiTag a").attr("page");
        if(currentPage!=1){
            var offset=(currentPage-2)*5;
            showItemInfo(searchCache,5,offset,"item_id","asc",priceRangeCache,cateIdCache,labelIdCache);
        }
    });

    //点击了next按钮
    $("#nextFlag").click(function(){
        var currentPage=$("li.clicked.addedLiTag a").attr("page");
        var totalPage=$("#pageShow span").html();
        // console.log(totalPage);
        if(currentPage!=totalPage){
            var offset=(currentPage)*5;
            showItemInfo(searchCache,5,offset,"item_id","asc",priceRangeCache,cateIdCache,labelIdCache);
        }
    });








    //一个数量减少的函数
    $("#countCut").click(function(){
        $("#orderItemCount").val($("#orderItemCount").val()-1);
        var itemCount=$("#orderItemCount").val();
        if(itemCount<1){
            $("#orderItemCount").val(1);
        }
    });
    //一个数量增多的函数
    $("#countAdd").click(function(){
        $("#orderItemCount").val(parseInt($("#orderItemCount").val())+1);
    });



    //一个暂存item_id的变量
    var orderItemId="";
    //点击购买时
    $(".left-show").on("click",".buyNow",function(){
        //首先请求联系人数据
        $.ajax({
            url : 'FrontManageOrder/seeAddressee',     //得到规定格式的收货人信息的url
            type : 'POST',
            success : function(data) {
                // var data={"1":["曹淦","天津市...件学院"],"2":["p1n93r","13723423232"],"result":true};
                //动态添加联系人的select
                if(data['result']){
                    $(".addedAddrTag").remove();
                    $.each(data,function(key,value){
                        if (key!="result") {
                            var addrTag="<option class='addedAddrTag' value='"+key+"'>"+value[0]+"："+value[1]+"</option>";
                        }
                        $("#orderAddr").append(addrTag);
                    });
                    $('#orderModal').modal('toggle');
                }else{
                    if(!data['isLogin']){
                        alert("还没有登陆？");
                        window.location.href="FrontForward/loginMain";
                    }else{
                        alert("请添加收货人信息！");
                    }
                }
            },
            error : function(data){
                alert("网络错误！请检查网络！");
            }
        });
        orderItemId=$(this).attr("item_id");

    });


    //点击下单时
    $("#orderPost").click(function(){
        //准备订单数据
        var orderForm={};
        var myDate = new Date();
		var itemArray=[];
		var cacheJson={};
		cacheJson['item_id']=orderItemId;
		cacheJson['count']=$("#orderItemCount").val();
		itemArray.push(cacheJson);
		orderForm['item']=JSON.stringify(itemArray);
        orderForm['oName']=myDate.getTime();
        orderForm['note']=$("#orderNote").val();
        orderForm['address_id']=$("#orderAddr").val();

        //订单信息必须完整
        var flag=true;
        $.each(orderForm,function(key,value){
            if(value==null||value==""){
                flag=false;
            }
        });
        if(!flag){
            alert("信息不完整，请检查！");
        }else{
            //ajax提交请求
            $.ajax({
                url : 'FrontManageOrder/createOrder',
                data:orderForm,
                type : 'POST',
                success : function(data) {
                    data=JSON.parse(data);
                    if(data['result']){
                        //成功后重定向到个人中心的订单页面
                        window.location.href="FrontForward/userOrder";
                    }else{
                        if(data['isLogin']==false){
                            window.location.href="FrontForward/loginMain";
                        }
                        alert("下单失败！请重试！");
                    }
                }
            });
        }
    });


	//点击了假如购物车时addCart
	$(".left-show").on("click",".addCart",function(){
		var data={};
		data['item.itemId']=$(this).attr("item_id");
		data['count']=1;
		//ajax发送添加购物车请求
		$.ajax({
			url : 'CartFrontManage/addItemToCart',
			type : 'POST',
			data : data,
			success : function(data) {
				data=JSON.parse(data);
				if(data['result']){
					//成功后
					alert("添加成功！");
				}else{
					if(data['isLogin']==false){
					    alert("还没有登陆？");
						window.location.href="FrontForward/loginMain";
					}else{
                        alert("添加失败！请重试！");
                    }
				}
			},
			error : function(data){
				alert("请检查网络！");
			}
		});
	});



});