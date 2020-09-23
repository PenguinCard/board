/**
 * 
 */
var prevOnload = window.onload=function(){
	var h=window.innerHeight;
	var w=window.innerWidth;
	var t=$("table").css("height").replace("px", "");
	$(".wrap").css("height", h);
	$(".wrap").css("width", w);
	$("table").css("margin-top", ((h-t)/2)+"px");
}
window.onresize=function(){
	var h=window.innerHeight;
	var w=window.innerWidth;
	var t=$("table").css("height").replace("px", "");
	$(".wrap").css("height", h);
	$(".wrap").css("width", w);
	$("table").css("margin-top", ((h-t)/2)+"px");
}