var activeOffset = $('nav ul .active').position().left;
var activeItemWidth = $('nav ul .active').width();

$('document').ready(function(){
	$('.dot').css('left', activeOffset + activeItemWidth / 2);
	var bgColor = $('.active a').css("background-color");
	$('.dot').css('background-color', bgColor);
});

$('nav').mouseout(function(){
	$('.dot').css('left', activeOffset + activeItemWidth / 2);
	var bgColor = $('.active a').css("background-color");
	$('.dot').css('background-color', bgColor);
});

$('nav ul li').hover(function(){
	var navOffset = $(this).position().left;
	var navItemWidth = $(this).width();
	
	$('.dot').css('left', navOffset + navItemWidth / 2);
	
	var bgColor = $('a', this).css("background-color");
	
	$('.dot').css('background-color', bgColor);
});