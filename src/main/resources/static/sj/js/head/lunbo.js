$(function(){
//banner
var swiper = new Swiper('.swiper-containero', {
    pagination: '.swiper-pagination',
    paginationClickable: true,
    nextButton: '.swiper-button-next',
    prevButton: '.swiper-button-prev',
    loop : true,
    speed:1500,
    autoplay:2000
});
//阳光头条
jQuery(".picScroll-top").slide({titCell:".hd ul",mainCell:".bd ul",autoPage:true,effect:"top",autoPlay:true,vis:2,effect:"topLoop"});

var swiper = new Swiper('.swiper-containert', {
    slidesPerView: 3.1,
    paginationClickable: true,
    spaceBetween:20,
    freeMode: true
});

var swiper = new Swiper('.swiper-containers', {
    slidesPerView:2.1,
    paginationClickable: true,
    spaceBetween:7,
    freeMode: true
});
var swiper = new Swiper('.Headline .swiper-container', {
	direction: 'vertical',
	autoplay:2000,
	slidesPerView: 2,
	pagination: {
	el: '.swiper-pagination',
		clickable: true,
	},
});

})
