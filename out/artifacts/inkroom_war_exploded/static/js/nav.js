$(document).ready(function(){
	
	//左侧导航栏隐藏时，右侧做出合理响应式
	// $(window).resize(function() {
	// 	var display =$(".left-nav").is(':visible');
	// 	//alert(display);
	// 	if(!display){
	// 		$(".right-content").css({"left":"0","top":"50px"});
	// 	}
	// 	else{
	// 		$(".right-content").css({"left":"60px","top":"0"});
	// 	}
	// });
	

	//启动提示框
	$("[data-toggle='tooltip']").tooltip();
	//使得轮播图为屏幕大小
	function listenSlider() {
		var display =$(".left-nav").is(':visible');
		var w=$(window).height();
		//alert(display);
		if(!display){
			$(".slide-img").css({"height":w-50});
			$(".right-content").css({"top":"50px"});
		}else{
			$(".slide-img").css({"height":w});
			$(".right-content").css({"top":"0px"});
		}
	}
	$(window).resize(function(){listenSlider();});
	listenSlider();
	

});