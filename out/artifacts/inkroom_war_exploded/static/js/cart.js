$(function() {
    
    $(".product").on("click",".product-add",function(){
        var a = $(this).prev().val();
        var b = parseInt(a) + 1;
        if (b == 99) {
            return
        }
        $(this).prev().val(b);
        TotalPrice()

        //ajax发送增指令
        var data={};
        data['count']=b;
        data['cartId']=$(this).closest(".product-box").find("input.product-num").attr("cartId");
        $.ajax({
            url : 'CartFrontManage/UpdateItemCount',
            data:data,
            type : 'POST',
            success : function(data) {
                data=JSON.parse(data);
                if(data['result']){
                    //成功后啥也不做
                }else{
                    if(data['isLogin']==false){
                        window.location.href="FrontForward/loginMain";
                        return;
                    }
                    alert("下单失败！请重试！");
                }
            }
        });

    });

    $(".product").on("click",".product-jian",function(){
        var a = $(this).next().val();
        var b = parseInt(a) - 1;
        if (b == 0) {
            return
        }
        $(this).next().val(b);
        TotalPrice()
        
        //ajax发送增指令
        var data={};
        data['count']=b;
        data['cartId']=$(this).closest(".product-box").find("input.product-num").attr("cartId");
        $.ajax({
            url : 'CartFrontManage/UpdateItemCount',
            data:data,
            type : 'POST',
            success : function(data) {
                data=JSON.parse(data);
                if(data['result']){
                    //成功后啥也不做；
                }else{
                    if(data['isLogin']==false){
                        window.location.href="FrontForward/loginMain";
                        return;
                    }
                    alert("下单失败！请重试！");
                }
            }
        });

    });
    
    $(".product").on("click",".product-del",function(){
        if (!confirm("您确定要删除当前商品？")) {
            return false;
        }
        //ajax发送删除购物车
        var obj=$(this);
        var cartId=[];
        cartId.push($(this).closest(".product-box").find("input.product-num").attr("cartId"));
        //data['cartId']=cartIdArray;
        alert(cartId[0]);
        $.ajax({
            url : 'CartFrontManage/deleteCartItem?cartId='+cartId,
            type : 'POST',
            success : function(data) {
                data=JSON.parse(data);
                if(data['result']){
                    //alert("succeed");
                    obj.closest(".product-box").remove();
                }else{
                    if(data['isLogin']==false){
                        window.location.href="FrontForward/loginMain";
                        return;
                    }
                    //alert("下单失败！请重试！");
                }
            }
        });
        koncat();
        TotalPrice()
    });

    //选择了当前购物车项
    $(".product").on("click",".product-ckb",function(){
        $(this).children("em").toggleClass("product-xz");
        TotalPrice();
        productxz()
    });

    //全选购物车项
    $(".product-al").click(function() {
        var a = $(".product-em");
        var b = $(".product-all em");
        b.toggleClass("product-all-on");
        if ($(this).find(".product-all em").is(".product-all-on")) {
            a.addClass("product-xz")
        } else {
            a.removeClass("product-xz")
        }
        TotalPrice();
        shuliang()
    });
    
    TotalPrice();
    shuliang();
    koncat()
	
    //点击结账时
	$(".product-sett").click(function(){
        //请求联系人信息
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
                            var addrTag="<option class='addedAddrTag' value='"+key+"'>"+value[0]+" "+value[1]+" "+value[2]+"</option>";
                        }
                        $("#orderAddr").append(addrTag);
                    });
                    $('#cartOrderModal').modal('toggle');
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
	});
    //确认下单时
    $("#cartOrderPost").click(function(){
        //准备订单数据
        //获取数据
        var tag=$(".product-box.addedCart");
        // console.log($(tag.get(0)));
        var myDate = new Date();
        var itemArray=[];
        var data={};
        for(i=0;i<tag.length;i++){
            if($(tag.get(i)).find("em.product-xz").length>0){
                //获取item数据
                var itemInfo={};
                itemInfo['item_id']=$(tag.get(i)).find("input.product-num").attr("item_id");
                itemInfo['count']=$(tag.get(i)).find("input.product-num").val();
                itemArray.push(itemInfo);
            }
        }
        data['item']=JSON.stringify(itemArray)//itemArray;
        data['oName']=myDate.getTime();
        data['note']=$("#orderNote").val();
        data['address_id']=$("#orderAddr").val();
        //订单信息必须完整
        var flag=true;
        $.each(data,function(key,value){
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
                data:data,
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
	
    //ajax请求购物车信息
    $.ajax({
        url : 'CartFrontManage/SeeCart',
        type : 'GET',
        async: false,
        // data : data,
        success : function(data) {
            // data={"result":true,"rows":[{"CartId":"3","ItemName":"九阴真经","ItemPrice":"100.0","ItemCount":"5","ItemPic":"static/upload/images/2019-08-18/0fbf9575e49c81eb21ac90f7fce770d01566141441393.jpg"},{"CartId":"4","ItemName":"测试3","ItemPrice":"100.0","ItemCount":"2","ItemPic":"static/upload/images/2019-08-18/20180330211927_1 - 副本1566142234443.jpg"}]};
            data=JSON.parse(data);
            
            if(data['result']){
                //成功后
                $(".addedCart").remove();   //必须放一个购物项才能用
                $.each(data['rows'],function(i,v){//还少一个item_id
                    var addCartTag="<div class='product-box addedCart'><div class='product-ckb'><em class='product-em'></em></div><div class='product-sx'><a style='text-decoration:none'><img src='"+v['ItemPic']+"' class='product-img' /><span class='product-name'>"+v['ItemName']+"</span></a><span class='product-price'>单价：¥&thinsp;<span class='price'>"+v['ItemPrice']+"</span></span><div class='product-amount'><div class='product_gw'><em class='product-jian'>-</em><input item_id='"+v['ItemId']+"' cartId='"+v['CartId']+"' item_id='23' value='"+v['ItemCount']+"' type='text' class='product-num' /><em class='product-add'>+</em></div></div><div class='product-del'><img src='static/images/deleteico.png' /></div></div></div>";
                    $(".product").append(addCartTag);

                });
            }else{
                if(data['isLogin']==false){
                    window.location.href="FrontForward/loginMain";
                    return;
                }
                //购物车为空
                // alert("购物车为空");
                $(".product-box.addedCart").remove();
                koncat();
                TotalPrice()
            }
        },
        error : function(data){
            alert("请检查网络！");
        }
    });
	
	
});
function productxz() {
    var a = $(".product-em");
    var b = $(".product-xz");
    if (b.length == a.length) {
        $(".product-all em").addClass("product-all-on")
    } else {
        $(".product-all em").removeClass("product-all-on")
    }
    shuliang();
    TotalPrice()
}
function TotalPrice() {
    var a = 0;
    $(".product-em").each(function() {
        if ($(this).is(".product-xz")) {
            var c = parseInt($(this).parents(".product-ckb").siblings().find(".price").text());
            var d = parseInt($(this).parents(".product-ckb").siblings().find(".product-num").val());
            var b = c * d;
            a += b
        }
        $(".all-price").text(a.toFixed(2))
    })
}
function shuliang() {
    $(".product-all-sl").text("");
    var a = $(".product-xz").length;
    $(".product-all-sl").text(a);
    if (a > 0) {
        $(".product-all-qx").text("已选");
        $(".all-sl").css("display", "inline-block");
        $(".product-sett").removeClass("product-sett-a")
    } else {
        $(".product-all-qx").text("全选");
        $(".all-sl").css("display", "none");
        $(".product-sett").addClass("product-sett-a")
    }
}
function koncat() {
    var a = $(".product-box").length;
    if (a <= 0) {
        $(".all-price").text("0.00");
        $(".product-all-qx").text("全选");
        $(".all-sl").css("display", "none");
        $(".product-sett").addClass("product-sett-a");
        $(".product-all em").removeClass("product-all-on");
        $(".kon-cat").css("display", "block");
        $(".cart-body").css("display","none");
    } else {
        $(".kon-cat").css("display", "none");
    }
};