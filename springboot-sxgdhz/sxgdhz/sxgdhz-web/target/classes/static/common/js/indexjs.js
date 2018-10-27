//天气预报
//$(function(){
//	$(document).ready(function() {
//		jQuery.jqtab = function(tabtit,tabcon,defensecon) {			
//			$(tabcon).hide(); 	
//			$(defensecon).hide(); 		
//			$(tabtit+" li:first").addClass("preactive").show();  
//			$(tabcon+":first").show();
//			$(defensecon+":first").show();  
//			$(tabtit+" li").click(function() {   
//				$(tabtit+" li").removeClass("preactive");
//				$(this).addClass("preactive");   		           	
//				$(tabcon).hide();
//				$(defensecon).hide();
//				var activeTab = $(this).attr("tab");
//				$("."+activeTab).fadeIn();
//				return false;
//			});		  
//		};
//		$.jqtab(".aomenul",".aomen_con",".defensecon_box");						
//	});		
//});






//天气预报
$(function(){
	$(document).ready(function() {
		//
		/*jQuery.jqtab = function(tabtit,tabcon) {			
			$(tabcon).hide(); 			
			$(tabtit+"span a:first").addClass("fact").show();  
			$(tabcon+":first").show();  
			$(tabtit+" a").click(function() {   
				$(tabtit+" a").removeClass("fact");
				$(this).addClass("fact");   		           	
				$(tabcon).hide();
				var activeTab = $(this).attr("tab");
				$("."+activeTab).fadeIn();
				return false;
			});		  
		};
		$.jqtab(".pendha",".pendcon");*/
		
		var pend2=$(".pend2").height();	
		$(".pend1").css("height",pend2+"px");		
		
		
		
	});		
});
window.onload = function(){   
	// var a=document.getElementById("pending_con");
	// var b=document.getElementById("sunrise_con");
	// alert(a.clientHeight+","+b.clientHeight);
	// if(a.clientHeight<b.clientHeight){
	// 	a.style.height=b.clientHeight+"px";
	// }else{
	// 	b.style.height=a.clientHeight-162+"px";
	// }
}



var i=-1; //第i+1个tab开始
var j=-1;
var offset = 20000; //轮换时间
var timer = null;
$(document).ready(function(){
	autoroll();
	autorollss();
})
function autoroll(){
	n = $('.aomenul li').length-1;
	i++;
	if(i > n){
	i = 0;
	}
//	slide(i);
	timer = window.setTimeout(autoroll, offset);
}
function slide(i){
	$('.defensecon_box').eq(i).css('display','block').siblings('.defensecon_box').css('display','none');
}


var two_conul='';
function autorollss(){
	n = $('.two_conul li').length-1;
	j++;
	if(j > n){
	j = 0;
	}
	slides(j);
	two_conul = window.setTimeout(autorollss, offset);
}
/*function two_conulStop(){
	clearTimeout(two_conul );
}
function two_conulStart(){
	autorollss();
}*/
function slides(j){
	$('.two_conul li').eq(j).addClass('hover').siblings().removeClass('hover');
	$('.con_two').eq(j).css('display','block').siblings('.con_two').css('display','none');
}

