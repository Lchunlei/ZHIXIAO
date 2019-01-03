//尺寸
(function (doc, win) {
	var docEl = doc.documentElement,
		resizeEvt = 'orientationchange' in window ? 'orientationchange' : 'resize',
		recalc = function () {
			var clientWidth = docEl.clientWidth;
			if (!clientWidth) return;
			docEl.style.fontSize = 100 * (clientWidth / 750) + 'px';
		};
	// Abort if browser does not support addEventListener
	if (!doc.addEventListener) return;
	win.addEventListener(resizeEvt, recalc, false);
	doc.addEventListener('DOMContentLoaded', recalc, false);
})(document, window);
//侧边栏
$(".more").click(function(){
	$(".mengb").stop().animate({"left":"0"},1000)
})
$(".close").click(function(){
	$(".mengb").stop().animate({"left":"-7.5rem"},1000)
})
$(".mengb").click(function(){
	$(this).stop().animate({"left":"-7.5rem"},1000)
})
$(".mengb_n").click(function(e){
	e.stopPropagation()
})
$(".mengb_n_t .yiji li").click(function(){
	$(this).addClass("active").siblings("li").removeClass("active")
	var Index = $(this).index();
	$(".mengb_n_t .erji .erjio").eq(Index).show().siblings().hide()
})

//导航定位
var nav=$(".header"); //得到导航对象
var win=$(window); //得到窗口对象
var sc=$(document);//得到document文档对象。
win.scroll(function(){
  if(sc.scrollTop()>=50){
    nav.addClass("fixedheader"); 
    $(".sousuo").show();
    $(".header_t").css("margin-left","1.28rem")
    $(".header_sousuo").hide()
    $(".bao_ss").removeClass("pbottom")
  }else{
   nav.removeClass("fixedheader");
   $(".sousuo").hide();
   $(".header_t").css("margin-left","2.04rem")
   $(".header_sousuo").show()
   $(".bao_ss").addClass("pbottom")
  }
})  
$(".sousuo").click(function(){
	$(".header_sousuo").fadeIn(300)
	$(".bao_ss").addClass("pbottom")
})
//详情页-点击显示全部
$(".ckall .clickb").click(function(){
	$(this).parent().hide()
	$(this).parent().parent(".wz_t").css("height","auto")
})
//列表页-回到顶部
$(".hdb").hide();
$(window).scroll(function(){
	if($(this).scrollTop() > 50){
		$(".hdb").fadeIn(300);
	}else{
		$(".hdb").fadeOut(300);
	}
})
$(".hdb").click(function(){
	 $('html ,body').animate({scrollTop: 0}, 300);
})
//在线商城-弹框
$(".zxsc").click(function(){
	$(".zxsctc").fadeIn(300).delay (3000).fadeOut(300);
})




















