$(function(){
		var sk = getCookie("nowskin");
		changecss(sk);
		//左侧菜单栏目和右侧内容等高
		
		var height =  $(".con_right").height()-65;
		$(".navContent").css("height",height+"px");
		
});
/**
 * 一级菜单选中状态
 */
$(document).ready(function(){
	var checkmenu = $("#selectMenuVal").val();
	 $(".menu_ul").children().each(function(i){
		 i++;
		 var tem = $("#menu_hi_"+i).val();
		 if(checkmenu.indexOf(tem)!=-1){
			 $("#menu_"+i).addClass("hover");
		 }else{
			 $("#menu_"+i).removeClass("hover");
		 }
	});
});
function setTab(name,cursel,n){
	 for(i=1;i<=n;i++){
	  var menu=document.getElementById(name+i);
	  var con=document.getElementById("con_"+name+"_"+i);
	  menu.className=i==cursel?"hover":"";
	  con.style.display=i==cursel?"block":"none";
	 }
	}
function getCookie(c_name)
{
if (document.cookie.length>0)
  {
  c_start=document.cookie.indexOf(c_name + "=")
  if (c_start!=-1)
    { 
    c_start=c_start + c_name.length+1 
    c_end=document.cookie.indexOf(";",c_start)
    if (c_end==-1) c_end=document.cookie.length
    return unescape(document.cookie.substring(c_start,c_end))
    } 
  }
return ""
}
function setCookie(c_name,value,expiredays)
{
	var exdate=new Date()
	exdate.setDate(exdate.getDate()+expiredays)
	document.cookie=c_name+ "=" +escape(value)+
	((expiredays==null) ? "" : ";expires="+exdate.toGMTString())
}
function changecss(url){
	if(url!=""){
		skin.href=url;
		var expdate=new Date();
		expdate.setTime(expdate.getTime()+(24*60*60*1000*30));
		//expdate=null;
		//以下设置COOKIES时间为1年,自己随便设置该时间..
		//SetCookie("nowskin",url,expdate,"/",null,false);
		setCookie("nowskin",url,expdate);
	}
}