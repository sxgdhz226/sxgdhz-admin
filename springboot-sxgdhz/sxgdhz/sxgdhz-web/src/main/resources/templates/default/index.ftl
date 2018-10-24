<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib-new.jsp"%>
<!DOCTYPE>
<html>
<head>
<meta charset="utf-8">
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="title" content="珠海市气象局（台）" />
<meta name="keywords" content="珠海,珠海市气象局,珠海天气,珠海温度,珠海香洲" />
<meta name="description" content="珠海,珠海市气象局,珠海天气,珠海温度,珠海香洲" />
<title>珠海气象局(台)</title>

<link href="<c:url value="/static/common/css/General.css"/>"
	type="text/css" rel="stylesheet" />
<link href="<c:url value="/static/common/css/index.css"/>"
	type="text/css" rel="stylesheet" />
<link ID="skin" href="<c:url value="/static/common/css/blues.css"/>"
	type="text/css" rel="stylesheet" />
    <link rel="stylesheet" href="static/common/css/jquery-ui.css"/>
<style type="text/css">
.con_search {
	height: 31px;
	padding-top: 10px;
	border-bottom: 1px solid #9cc9e8;
	padding: 8px 15px 0 20px;
}
.testt {
	overflow:hidden;
	 width:356px;
	 height:172px;
	 /* padding-left:45px; */
	 padding-left:45px;
	 background:url(../image/4.jpg);
	 background-size:100%;
}
.search_lt {
	float: left
}

.search_lt span {
	color: #24a5d5
}

.search_lt span img {
	vertical-align: middle;
}

.search_rt {
	float: right;
	color: #24a5d5
}

.search_rt input {
	height: 21px;
	border: 1px solid #b9bdc0;
	border-right: none;
	outline: none;
	margin-left: 15px
}

.search_rt a {
	height: 23px;
	border: 1px solid #b9bdc0;
	border-left: none;
	outline: none;
	border-top: 1px solid #b9bdc0;
	border-bottom: 2px solid #b9bdc0;
}

.search_rt a img {
	vertical-align: top;
	padding-top: 10px;
}

#demo1 table tr td {
	padding-left: 5px;
}

.tideTable strong {
	padding: 8px 18px 0 28px !important
}

.sunriseimg {
	position: relative;
}

.sunriseimg .spanl {
	position: absolute;
	left: 10px
}

.sunriseimg .spanr {
	position: absolute;
	right: 10px
}
.qingtian{
	background-color: #3D89CD;
}
.yintian{
	background-color: #22435D;
}
.yutian{
	background-color: #9DBD8F;
}
#typhoonInfo{
	font-size: 18px; 
	font-family: 宋体;
}
#typhoonInfo li{
	font-weight: bold;
	width: 20px;
}
.con_alertbak{
	    padding-left: 10px;
    overflow: hidden;
    word-wrap: break-word;
    line-height: 25px;
    padding-top: 8px;
    font-size:18px;
    height: auto;
    border: 1px solid #e0dede;
    background:  no-repeat #f5f4f4;
    margin-top: 15px;
    position: relative;
      border-color: red;
}
#con_yu_1 a{
    position: absolute;
    right: 10px;
    bottom: 10px;
    padding: 5px 10px;
    background: rgba(11,11,11,0.5);
    color: #FFF;
    -webkit-border-radius: 15px;
    -moz-border-radius: 15px;
    border-radius: 15px;
}

.zscd{
    margin-top: 5px;
    width: 100%;

}
.zscd h3{
    height: 35px;
    line-height: 35px;
    font-size: 16px;
    color: #0D7ED2;
    text-indent: 15px;
    border-bottom: 2px solid #0D7ED2;
    position: relative;
    width:396px;
}
.zscd h3 span{
	float: left;
    height: 33px;
    line-height: 35px;
    font-size: 16px;
    color: #fff;
    text-indent: 15px;
    position: relative;
    display: inline-block;
    width:93px;
    background: url(../static/common/images/blue/nav_hover.png);
}
.zscd h3 a{
    margin-right: 18px;
	float: right;
	display: block;
   	cursor: pointer;
}
/*.zscd h3:before{*/
    /*content: '';*/
    /*position: absolute;*/
    /*top: 15px;*/
    /*left: 7;*/
    /*width: 6px;*/
    /*height: 6px;*/
    /*background: #0D7ED2;*/
/*}*/
#zscd{
    width: 100%;
    border: 1px solid #e9e9e7;
}
#zscd li{
    width: 100%;
    height: 30px;
    line-height: 30px;
    background: ;
    border-bottom: 1px dashed #e9e9e7;
    cursor: pointer;
    position: relative;
}
#zscd li:before{
    content: '';
    position: absolute;
    left: 8px;
    top: 12px;
    width: 5px;
    height: 5px;
    -webkit-border-radius: 50%;
    -moz-border-radius: 50%;
    border-radius: 50%;
    background: #0e7eee;
}
#zscd li:hover{
    background: #EEE;
}
#zscd li:last-of-type{
    border-bottom:none ;
}
#zscd li a{
    display: inline-block;
    width: 70%;
    text-overflow: ellipsis;
    white-space: nowrap;
    overflow: hidden;
    text-indent: 20px;
}
#zscd li span{
    float: right;
    font-size: 12px;
    color:#7b7b7b;
    margin-right: 7px;
}

.warp {
    visibility: hidden;
    position: fixed;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    background: rgba(11, 11, 11, 0.7);
    z-index:  19891019;
}
</style>
<script src="<c:url value="/static/common/js/jquery-1.11.1.min.js"/>"
	type="text/javascript"></script>
<script src="<c:url value="/static/common/js/indexjs.js"/>"
	type="text/javascript"></script>
<!--                处理在IE10和11下网站变灰问题
<script type="text/javascript" src="js/grayscale.js"></script>
<script type="text/javascript"> 
	window.onload=function(){ //网页变灰
		 grayscale(document.body);
 	}
</script>-->
<script type="text/javascript"
	src="<c:url value="/static/common/js/skin_change.js"/>"></script>
<script type="text/javascript">
function toone1() {
	window.location.href = '<c:url value="/web/zwgk/zcfg"/>';
}
function setTab_sk(name,cursel,n){
 for(i=1;i<=n;i++){
  var menu=document.getElementById(name+i);
  var con=document.getElementById("con_"+name+"_"+i);
  // if(name=='yu'){
  // 	var cons=document.getElementById("cons_"+name+"_"+i);
  // 	cons.style.display=i==cursel?"block":"none";
  // }

  menu.className=i==cursel?"btnact":"";
  con.style.display=i==cursel?"block":"none";
 }
}
function addFavorite2() {
		var url = window.location;
		var title = document.title;
		var ua = navigator.userAgent.toLowerCase();
		if (ua.indexOf("360se") > -1) {
			alert("由于360浏览器功能限制，请按 Ctrl+D 手动收藏！");
		} else if (ua.indexOf("msie 8") > -1) {
			window.external.AddToFavoritesBar(url, title); //IE8
		} else if (document.all) {
			try {
				window.external.addFavorite(url, title);
			} catch (e) {
				alert('您的浏览器不支持,请按 Ctrl+D 手动收藏!');
			}
		} else if (window.sidebar) {
			window.sidebar.addPanel(title, url, "");
		} else {
			alert('您的浏览器不支持,请按 Ctrl+D 手动收藏!');
		}
	}
$(document).ready(function(){
  	$("#menu_1").addClass("hover");
  	//判断是否加载台风
	var typh = '${typhoonState}';
  	var warnstate= '${warnState}';//先判断是否有预警，
  	if(warnstate=='yes'){
  		var warnshow='${aoWanShow!""}';
  		if(warnshow=='ao'){
  			setTab_sk('yu',2,3);
  		}else if(warnshow=='weather'){
  			setTab_sk('yu',1,3);
  		}else {
  			setTab_sk('yu',3,3);
  		}
  	}
  	
});
var tt;
var ttt;
var num=0;
function hideTyphoon(){
num++;
	if(num>=10){
		$("#tempTyph").hide();
		clearTimeout(ttt);
		return falses;
	}else{
		ttt = setTimeout("hideTyphoon()",500);
	}
}
function typhoonclick(){
	window.location.href="<c:url value="/web/tflj"/>";
}
function loadtyph(){
	if ($("#mygis").attachEvent){
			$("#mygis").attachEvent("onload", function(){
				if (tt !=""){
					clearTimeout(tt);
					hideTyphoon();
					return falses;
				}
			});
		}else {
			$("#mygis").load(function() {
				if (tt !=""){
					clearTimeout(tt);
					hideTyphoon();
					return falses;
				}
			}); 
		} 
	tt = setTimeout("loadtyph()",500);
}

function newvideoHit(){
	var web = '<c:url value='/web/newvideoHit'/>';
	var param="&keywords=";
	$.ajax({
	   type: "POST",
	   url: web,
	   data:param,
	   	success: function(msg){
	   }
	});

}



</script>

</head>
<body>
	<a name="top"></a>
	
	<div class="con_con">
		<%@include file="/WEB-INF/views/web/head_new.jsp"%>
		<div class="contenters">
			<div class="con_listbom" style="display: none;" id="zht_2">
        	
		</div>
      	 <#if tqfxcon !=''>
      	 	<div class="con_alertbak">
				${tqfxcon }
            </div>
		 </#if>

            <%@include file="main_3.jsp"%>
            <%@include file="main_2.jsp"%>
			<%@include file="main_1.jsp"%>
	</div>
<!-- 			首页第四行 start -->
<%@include file="main_4.jsp"%>
<!-- 			end -->

<!-- 			首页第四行 start -->
<%@include file="main_5.jsp"%>
<!-- 			end -->		
	<div class="con_left cllta">
		<div class="con_left clltb">
			<div class="con_typhoon">
				<h1 class="con_h1b">
					<span
						style="float:left;color:#fff;font-size:16px;font-weight:bold;">卫星云图</span>
					<a href="<c:url value="/web/qxjc/wxyt"/>" class="amore">更多</a>
				</h1>
				<div class="maps_con" style="padding:8px 0px 5px 9px">
					<a href="<c:url value="/web/qxjc/wxyt"/>"><img width="370px"
						height="270px" id="yt" src="http://www.zhmb.gov.cn/jeecms/static/yt/20.gif" />
						<img style="position: absolute;top:105px;left:155px;" src="<c:url value="/static/common/images/zhuhai.png"/>"/>
						
					</a>
				</div>
			</div>
		</div>
		<div class="con_right clrtb">
			<div class="con_typhoon">
				<h1 class="con_h1b">
					<span
						style="float:left;color:#fff;font-size:16px;font-weight:bold;">雷达图</span>
					<a href="<c:url value="/web/qxjc/ldtx"/>" class="amore">更多</a>
				</h1>
				<div class="maps_con" style="padding:8px 0px 5px 9px">
					<a href="<c:url value="/web/qxjc/ldtx"/>"><img width="370px" height="270px" id="ldt"
						 <#if ldt=='0'>
						 src="http://www.zhmb.gov.cn/jeecms/static/ldt/20_m.gif"
						  </#if>
						 <#if ldt=='1'>
						 	src="<c:url value="/static/ldt/noLd.gif"/>" 
						 </#if>
						  /><img style="position: absolute;top:97px;left:142px;" src="<c:url value="/static/common/images/zhuhai.png"/>"/>
						  
					</a>
				</div>
			</div>
		</div>
		<div class="con_typhoon">
			<!--  科普知识 -->
			<%@include file="/WEB-INF/views/web/kpzs.jsp"%>
		</div>
	</div>
	<div class="con_right clrta">
		<div class="con_typhoon">
			<!--  政务 -->
			<%@include file="/WEB-INF/views/web/zwgk_main.jsp"%>
		</div>
		<div class="con_typhoon">
			<h1 class="con_h1c">
				<ul class="conh1c_ul">
					<li id="four1" class="hover" onClick="setTab('four',1,3)">微博互动</li>
					<li id="four2" onClick="setTab('four',2,3)">微信</li>
					<li id="four3" onClick="setTab('four',3,3)">珠海天气APP</li>
				</ul>
			</h1>
            <div id="con_four_1" class="wechat_con" style="display:block">
                <iframe width="100%" height="550" class="share_self"  frameborder="0" scrolling="no" src="http://widget.weibo.com/weiboshow/index.php?language=&width=0&height=550&fansRow=2&ptype=1&speed=0&skin=1&isTitle=1&noborder=1&isWeibo=1&isFans=1&uid=2196955610&verifier=a0641c19&dpc=1"></iframe>
            </div>
			<div id="con_four_2" class="wechat_con" style="text-align: center;">
				<img width="370px" height="300px"
					src="<c:url value="/static/images/zhwx.jpg"/>">
			</div>
			<div id="con_four_3" class="wechat_con" style="text-align: center;">
                <ul>
                    <li style="display: inline-block">
                        <p style="margin-top: 60px;margin-bottom: 0px">苹果 </p>
                        <p style="margin-top: 0px;margin-bottom: 0px">
                            <img src="<c:url value='/static/common/images/ios.png'/>" width="150px" height="150px"/>
                        </p>
                    </li>
                    <li style="display: inline-block">
                        <p style="margin-top: 60px;margin-left:20px;margin-bottom: 0px">安卓</p>
                        <p style="margin-top: 0px;margin-bottom: 0px">
                            <img src="<c:url value='/static/common/images/and.png'/>" width="150px" height="150px"/>
                        </p>
                    </li>
                </ul>

			</div>
		</div>
	</div>
	<div class="clearfloat"></div>
	</div>
	<div class="clearfloat"></div>
	<!--         脚部引用 -->
	<%@include file="/WEB-INF/views/web/foot.jsp"%>
	<!--        结束 -->
	</div>

	<div id="back-to-top">
		<a class="contop" href="#top" rel="external nofollow"> <img
			src="<c:url value='/static/common/images/top_con.png'/>" /> </a> <a
			class="img_close" href="#close"><img
			src="<c:url value='/static/common/images/popur_close.png'/>" /> </a>
		<div class="clearfloat"></div>
		<p>
			珠海气象局官方微信<br />“珠海天气”<img
				src="<c:url value='/static/common/images/popur1.jpg'/>" />微信扫扫
		</p>
		<p>
			公众版手机app<br />“珠海风云”
			<ul>
				<li style="display: inline-block">
					<p style="margin-top: 0px;margin-bottom: 0px">苹果 </p>
					<p style="margin-top: 0px;margin-bottom: 0px">
						<img src="<c:url value='/static/common/images/ios.png'/>" width="50px" height="50px"/>
					</p>
				</li>
				<li style="display: inline-block">
                    <p style="margin-top: 0px;margin-bottom: 0px">安卓</p>
                    <p style="margin-top: 0px;margin-bottom: 0px">
                        <img src="<c:url value='/static/common/images/and.png'/>" width="50px" height="50px"/>
                    </p>
				</li>
			</ul>
			<p style="margin-top: 0px">请扫描下载</p>
		</p>
		<p>
			信用珠海<img
				src="<c:url value='/static/common/images/xyzhuhai.png'/>" />请扫描下载
		</p>
		<p>
			<a href="http://weibo.com/zhsqxj" target="_Blank">新浪微博<br /> <img
				src="<c:url value='/static/common/images/popur3.png'/>" /> </a>
               <br>ID:@珠海天气
		</p>
		<p id="zt_4">
			
		</p>
	</div>

	<div id="gg1" style="display: none">
		<div class="gg_close">关闭</div>
		<div class="gg_con" id="zt_1"></div>
	</div>

    <!--弹出框-->
    <div class="warp" >

    </div>

    <!--弹出框-->
    <div id="dialogDiv" >

    </div>

	<script src="<c:url value="/static/js/dist/echarts.js"/>"></script>

	<script type="text/javascript">
// 路径配置
require.config({
    paths: {
        echarts: '<c:url value="/static/js/dist"/>'
    }
});

// 使用
require(
    [
        'echarts',
        'echarts/chart/line' // 使用柱状图就加载bar模块，按需加载
    ],
function (ec) {
     // 基于准备好的dom，初始化echarts图表
     var myChart = ec.init(document.getElementById('main')); 
var option = {
    tooltip : {
     	show : false,
        trigger: 'axis'
    },
    legend: {
    	show: false,
        data:['最高气温','最低气温']
    },
     grid:{
    	x:'7%',
    	y:'1%',
    	borderWidth:0,
    	y2:0
    },
    toolbox: {
        show : false
    },
    calculable : false,
    xAxis : [
        {
            type : 'category',
            boundaryGap : false,
            data : ${x_date},
            splitLine:{show: false}
        }
    ],
    yAxis : [{
        	max:${y_max},
        	min:${y_min},
            type : 'value',
            show:false,
            splitLine:{show: false}
        }
    ],
    series : [
        {
            name:'最高气温',
            type:'line',
            data:${json_main_maxT},
            itemStyle:{
	           	normal: {
	            	label: {
	                        show: true,
	                         textStyle : {
	                            fontSize : '18',
	                            fontFamily : '微软雅黑',
	                            fontWeight : 'bold'
	                        },
	                         formatter: '{c} °C'
	                    }
		            }
	            }
        },
        {
            name:'最低气温',
            type:'line',
            data:${json_main_minT},
            itemStyle:{
	           	normal: {
	           	  color: 'blue',
	            	label: {
	                        show: true,
	                         textStyle : {
	                            fontSize : '18',
	                            fontFamily : '微软雅黑',
	                            fontWeight : 'bold'
	                        },
	                        position:'bottom',
	                        formatter: '{c} °C'
	                    }
		            }
	            }
        }
    ]
};

      // 为echarts对象加载数据 
      myChart.setOption(option); 
	}
);
    </script>
	<!--首页飘动图片START-->
	<script type="text/javascript">
function addEvent(obj,evtType,func,cap){
    cap=cap||false;
if(obj.addEventListener){
     obj.addEventListener(evtType,func,cap);
  return true;
}else if(obj.attachEvent){
        if(cap){
         obj.setCapture();
         return true;
     }else{
      return obj.attachEvent("on" + evtType,func);
  }
}else{
  return false;
    }
}
function getPageScroll(){
    var xScroll,yScroll;
if (self.pageXOffset) {
  xScroll = self.pageXOffset;
} else if (document.documentElement && document.documentElement.scrollLeft){
  xScroll = document.documentElement.scrollLeft;
} else if (document.body) {
  xScroll = document.body.scrollLeft;
}
if (self.pageYOffset) {
  yScroll = self.pageYOffset;
} else if (document.documentElement && document.documentElement.scrollTop){
  yScroll = document.documentElement.scrollTop;
} else if (document.body) {
  yScroll = document.body.scrollTop;
}
arrayPageScroll = new Array(xScroll,yScroll);
return arrayPageScroll;
}
function GetPageSize(){
    var xScroll, yScroll;
    if (window.innerHeight && window.scrollMaxY) { 
        xScroll = document.body.scrollWidth;
        yScroll = window.innerHeight + window.scrollMaxY;
    } else if (document.body.scrollHeight > document.body.offsetHeight){
        xScroll = document.body.scrollWidth;
        yScroll = document.body.scrollHeight;
    } else {
        xScroll = document.body.offsetWidth;
        yScroll = document.body.offsetHeight;
    }
    var windowWidth, windowHeight;
    if (self.innerHeight) {
        windowWidth = self.innerWidth;
        windowHeight = self.innerHeight;
    } else if (document.documentElement && document.documentElement.clientHeight) {
        windowWidth = document.documentElement.clientWidth;
        windowHeight = document.documentElement.clientHeight;
    } else if (document.body) {
        windowWidth = document.body.clientWidth;
        windowHeight = document.body.clientHeight;
    } 
    if(yScroll < windowHeight){
        pageHeight = windowHeight;
    } else { 
        pageHeight = yScroll;
    }
    if(xScroll < windowWidth){ 
        pageWidth = windowWidth;
    } else {
        pageWidth = xScroll;
    }
    arrayPageSize = new Array(pageWidth,pageHeight,windowWidth,windowHeight) 
    return arrayPageSize;
}
var AdMoveConfig=new Object();
AdMoveConfig.IsInitialized=false;
AdMoveConfig.ScrollX=0;
AdMoveConfig.ScrollY=0;
AdMoveConfig.MoveWidth=0;
AdMoveConfig.MoveHeight=0;
AdMoveConfig.Resize=function(){
    var winsize=GetPageSize();
    AdMoveConfig.MoveWidth=winsize[2];
    AdMoveConfig.MoveHeight=winsize[3];
    AdMoveConfig.Scroll();
};
AdMoveConfig.Scroll=function(){
    var winscroll=getPageScroll();
    AdMoveConfig.ScrollX=winscroll[0];
    AdMoveConfig.ScrollY=winscroll[1];
};
addEvent(window,"resize",AdMoveConfig.Resize,false);
addEvent(window,"scroll",AdMoveConfig.Scroll,false);
function AdMove(id){
    if(!AdMoveConfig.IsInitialized){
        AdMoveConfig.Resize();
        AdMoveConfig.IsInitialized=true;
    }
    var obj=document.getElementById(id);
    obj.style.position="absolute";
    var W=AdMoveConfig.MoveWidth-obj.offsetWidth;
    var H=AdMoveConfig.MoveHeight-obj.offsetHeight;
    var x = W*Math.random(),y = H*Math.random();
    var rad=(Math.random()+1)*Math.PI/6;
    var kx=Math.sin(rad),ky=Math.cos(rad);
    var dirx = (Math.random()<0.5?1:-1), diry = (Math.random()<0.5?1:-1);
    var step = 1;
    var interval;
    this.SetLocation=function(vx,vy){x=vx;y=vy;}
    this.SetDirection=function(vx,vy){dirx=vx;diry=vy;}
    obj.CustomMethod=function(){
        obj.style.left = (x + AdMoveConfig.ScrollX) + "px";
        obj.style.top = (y + AdMoveConfig.ScrollY) + "px";
        rad=(Math.random()+1)*Math.PI/6;
        W=AdMoveConfig.MoveWidth-obj.offsetWidth-50;
        H=AdMoveConfig.MoveHeight-obj.offsetHeight;
        x = x + step*kx*dirx;
        if (x < 0){dirx = 1;x = 0;kx=Math.sin(rad);ky=Math.cos(rad);} 
        if (x > W){dirx = -1;x = W;kx=Math.sin(rad);ky=Math.cos(rad);}
        y = y + step*ky*diry;
        if (y < 0){diry = 1;y = 0;kx=Math.sin(rad);ky=Math.cos(rad);} 
        if (y > H){diry = -1;y = H;kx=Math.sin(rad);ky=Math.cos(rad);}
    }
    this.Run=function(){
        var delay = 10;
        interval=setInterval(obj.CustomMethod,delay);
        obj.onmouseover=function(){clearInterval(interval);};
        obj.onmouseout=function(){interval=setInterval(obj.CustomMethod, delay);};
    };
}

$(function(){ 


// 	$("#back-to-top .contop").click(function(){ 
// 			$('body').animate({scrollTop:0},1000); 
// 			return false; 
// 		});  
		$(".img_close").click(function(){ 
			$("#back-to-top").hide();
		});
		
	$(".gg_close").click(function(){ 
		$("#gg1").hide();
	});
	zht();
	//是否启用实况监测展示轮播
	changSk();
	
	//天气实景自动切换
	$.cdcIfocus($("#ifocus_tabs li"),$("#ifocus_img li"), {start_item:0});
	
}); 
//漂浮专题
function zht(){
 	var zt_1 = '${zhuanti_1!''}';
	if(zt_1==''){
		$("#gg1").hide();
	}else{
		var ad1=new AdMove("gg1");
		ad1.Run();
		$("#zt_1").html(zt_1);
		$("#gg1").show();
	}
 	var zt_2 = '${zhuanti_2!''}';
	if(zt_2==''){
		$("#zht_2").hide();
	}else{
		$("#zht_2").html(zt_2);
		$("#zht_2").show();
	}
 	var zt_3 = '${zhuanti_3!''}';
	if(zt_3!=''){
		$("#zt_3").html(zt_3);
	}
	
	var zt_4 = '${zhuanti_4!''}';
	if(zt_4!=''){
		$("#zt_4").html(zt_4);
	}
}
//实况监测自动切换
var k=1;
var timmerId='';
function changSk(){
	if(k==4){
		k=1;
	}
	setTab_sk('sk',k,3);
	k++;
	timmerId = setTimeout("changSk()",15000);
}
function stopTimeout(){
	clearTimeout(timmerId );
}
function startTimeout(){
	changSk();
}
</script>
	<script type="text/javascript">
    var speed=30;
    var demo = $("#demo");
    var demo1 = $("#demo1");
    var demo2 = $("#demo2");
    demo2.html(demo1.html());
    function Marquee(){
        if(demo.scrollLeft()>=demo1.width())
            demo.scrollLeft(0);
        else{
            demo.scrollLeft(demo.scrollLeft()+1);
        }
    }
    var MyMar=setInterval(Marquee,speed);
    demo.mouseover(function() {
        clearInterval(MyMar);
    } )
    demo.mouseout(function() {
        MyMar=setInterval(Marquee,speed);
    } )
</script>
	<!--首页飘动图片END -->
    <script src="<c:url value="/static/js/jquery-ui.js"/>"></script>
	<script src="<c:url value="/static/common/js/jquery.tools.min.js"/>"></script>
    <script>


    $('#yu3').bind('click',function () {

        if( $('#con_yu_3 ul').height() >= 230){
            init();
        }
    })
    $('#yu2').bind('click',function () {

        if( $('#con_yu_2 ul').height() >= 230){
            init();
        }
    })

    // window.onload = init;

    function init() {
        new Marquee({
            name: "con_yu_2",
            speed: 50,
            offset: 1
        });
    }

    function init() {
        new Marquee({
            name: "con_yu_3",
            speed: 50,
            offset: 1
        });
    }


    function Marquee(opt) {
        var dom = document.getElementById(opt.name);
        var speed = opt.speed || 50;
        var offset = opt.offset || 1;
        var interId;
        var ul1 = dom.getElementsByTagName("ul")[0];
        var ul2;
        init();

        animate();

        function animate() {
            interId = setInterval(function() {
                dom.scrollTop += offset;
                if (dom.scrollTop >= ul1.scrollHeight) {
                    dom.scrollTop = 0;
                }
            }, speed)
        }
        dom.onmouseover = function() {
            clearInterval(interId);
        }
        dom.onmouseout = function() {
            animate();
        }

        function init() {

            ul2 = document.createElement("ul");
            ul2.innerHTML = ul1.innerHTML;
            dom.appendChild(ul2);


        }
    }

    </script>

    <script>
        $(function(){
            // $.ajax({
            //     url : 'http://app.gd.gov.cn/xxts/pushinfo_json.php',
            //     dataType : "jsonp",
            //     jsonp : "pushInfoJsonpCallBack",
            //     jsonpCallback:"pushInfoJsonpCallBack",
            //     success : function(data) {
            //         $.each(data,function(i,json){
            //             debugger
            //             $("#testUl").append("<li><a href='"+json.link+"'>"+json.title+"</a><span>"+json.pubDate+"</span></li>")
            //         })
            //     },
            //     error:function(){
            //         alert('fail');
            //     }
            // });
            $.ajax({
                url : 'pushInfo',
                dataType : "json",
                success : function(data) {
                    $.each(data,function(i,json){
                        if (i <= 5){
                            $("#zscd").append("<li><a onclick='redirectTo(\""+json.link+"\")'>"+json.title+"</a><span>"+json.pubDate+"</span></li>")
                        }
                    })
                },
                error:function(){
                    alert('fail');
                }
            });
        });

        // function pushInfoJsonpCallBack(data) {
        //
        //     $.each(data,function(i,json){
        //         alert(json.link);
        //         $("#testUl").append("<li><a href='"+json.link+"'>"+json.title+"</a><span>"+json.pubDate+"</span></li>")
        //     })
        // }

        // $("#dialogDiv").dialog({
        //     resizable: false,
        //     modal: false,
        //     title: "删除提醒",
        //     height:150,
        //     width:300,
        //     buttons: [
        //         {
        //             html: "确认",
        //             click: function(){
        //                 var param = {
        //                     optionBtn: "delete",
        //                     url: "users/delete",
        //                     dialogOb: $(this),
        //                     data:{keyid:function(){
        //                             var keyId = "";
        //                             for(obj in rows) keyId +=","+rows[obj].keyId;
        //                             return keyId.substring(1);
        //                         }(),
        //                         channels:function(){
        //                             var channels = "";
        //                             for(obj in rows) channels +=","+rows[obj].channelId;
        //                             return channels.substring(1);
        //                         }()
        //                     }
        //                 };
        //                 paramiterUsers(param);
        //                 //$(this).dialog("close");
        //             }
        //         },
        //         {
        //             html: "取消",
        //             click: function(){
        //                 $(this).dialog("close");
        //             }
        //         }
        //     ]
        // });

        $("#dialog").bind("click", function() {
            if (confirm('您访问的链接即将离开"珠海市气象局"网站是否继续？')){
                window.open("http://www.gd.gov.cn/govpub/xxts/");
			}
        });

        var redirectTo = function (link){
            window.open(link);
        }
    </script>


</body>
</html>