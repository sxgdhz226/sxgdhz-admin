<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib-new.jsp"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
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
<style type="text/css">
.zonelable{
		font-size:18px;
		font-weight:bold;
	}	
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
$(document).ready(function(){
/*气温 */
    $("#qwqx1").attr("checked",'checked');//默认香洲
	$("#xz").click(function(){
	  $("#qwqx1").attr("checked",'checked');
	});
	
	$("#dm").click(function(){
	  $("#qwqx2").attr("checked",'checked');
	});
	
	$("#jw").click(function(){
	  $("#qwqx3").attr("checked",'checked');
	});
	
	$("#hq").click(function(){
	  $("#qwqx4").attr("checked",'checked');
	});
/*降雨量 */
    $("#jylqx1").attr("checked",'checked');//默认香洲
	$("#xz1").click(function(){
	  $("#jylqx1").attr("checked",'checked');
	});
	
	$("#dm1").click(function(){
	  $("#jylqx2").attr("checked",'checked');
	});
	
	$("#jw1").click(function(){
	  $("#jylqx3").attr("checked",'checked');
	});
	
	$("#hq1").click(function(){
	  $("#jylqx4").attr("checked",'checked');
	});
});

function setTab_sk(name,cursel,n){
 for(i=1;i<=n;i++){
  var menu=document.getElementById(name+i);
  var con=document.getElementById("con_"+name+"_"+i);
  if(name=='yu'){
  	var cons=document.getElementById("cons_"+name+"_"+i);
  	cons.style.display=i==cursel?"block":"none";
  }

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
		 <#--<#if tcwCon !=''>-->
      	<#--<div class="con_alert">-->
      		<#--<img src="<c:url value="/static/common/images/conlist1.png"/>"/>${tcwCon }-->
      	<#--</div>-->
      	 <#--</#if>-->
      	 <#--<#if tqfxcon !=''>-->
      	 	<#--<div class="con_alertbak">-->
	      	<#--${tqfxcon }-->
	      	<#--</div>-->
      	 <#--</#if>-->

<!-- 			首页第一行 start -->
<%@include file="main_1.jsp"%>
<!-- 			end -->

<!-- 			首页第二行 start -->
<%@include file="main_2.jsp"%>
<!-- 			end -->
			
<!-- 			首页第三行 start -->
<%@include file="main_3.jsp"%>
<!-- 			end -->
		
	
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
						height="270px" id="yt" src="http://www.zhmb.gov.cn:8000/jeecms/static/yt/20.gif" />
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
					<a href="<c:url value="/web/qxjc/ldtx"/>"><img width="370px"
						height="270px" id="ldt" 
						 <#if ldt=='0'>
						 src="http://www.zhmb.gov.cn:8000/jeecms/static/ldt/20_m.gif"
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
				<iframe width="100%" height="550" class="share_self"  frameborder="0" scrolling="no" src="http://widget.weibo.com/weiboshow/index.php?language=&width=0&height=550&fansRow=2&ptype=1&speed=0&skin=1&isTitle=1&noborder=1&isWeibo=1&isFans=1&uid=2196955610&verifier=a0641c19&dpc=1 

"></iframe>

			</div>
			<div id="con_four_2" class="wechat_con" style="text-align: center;">
				<img width="370px" height="300px"
					src="<c:url value="/static/images/zhwx.jpg"/>">
			</div>
			<div id="con_four_3" class="wechat_con" style="text-align: center;">
				<img width="370px" height="300px"
					src="<c:url value="/static/images/zhapp.jpg"/>">
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
			公众版手机app<br />“珠海风云”<img
				src="<c:url value='/static/common/images/popur2.jpg'/>" />请扫描下载
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

	<script src="<c:url value="/static/js/dist/echarts.min.js"/>"></script> 
    <script type="text/javascript">
	    /* 风力风向曲线自动执行方法 */
	    $(function(){
	    	wdwfDate('59488','香洲');
	    })	
    
	    function wdwfDate(param,area){
			var params={"stationid":param};
			$.ajax({
				type:"POST",
				dateType:"json",
				url:"/jeecms/web/getWdWfDate",
				data:params,
				success:function(data){
					var obj = eval('(' + data + ')'); 
					/* console.log(data) */
					getWdWfChart(obj.wdList,obj.wfList,area);
				},
				error:function (XMLHttpRequest, textStatus, errorThrown) {      
		            alert("请求失败！");
		        }
			});
		}
   	 	function getWdWfChart(wdDate,wfData,area){
        // 基于准备好的dom，初始化echarts实例
        var myChart = echarts.init(document.getElementById('fxfsdiv')); 

        var x_TimeData = ["00","01","02","03","04","05","06","07","08","09","10","11","12","13","14","15","16","17","18","19","20","21","22","23"];
        var min = 100;
        var max =0;
        for(var i=0 ;i < wfData.length ; i++){
        	if(wfData[i] > max){
        		max = wfData[i];
        	}
        	if(wfData[i] < min){
        		min = wfData[i];
        	}
        }
        min = Math.round(min);//四写五入取整数
        max = Math.round(max);//四写五入取整数
        min -= 2;
        max += 2;
        if(min < 0){
        	min = 0;
        }
        var DateObject =[]; 
        
        for(var i=0 ; i < wfData.length ; i++){
        	DateObject.push(getSymbolObject(wfData[i],wdDate[i]));
        }
        // 指定图表的配置项和数据--风力风向Option
        var WDWF_Option = {
            title: {
                   text: area+'站24小时风向风速统计',
                      subtext: '珠海市气象局',
                      x:'center',
                       textStyle:{
                             fontSize: 15,
                             fontWeight: 'bolder',
                             color: '#333'
                      } 
	        },
            tooltip: {
		        trigger: 'axis',
		        axisPointer: {
		            type: 'cross'
		        }
		    },
		    grid: {
		    	x:55,
		        y:43,
		        x2:11,
		        y2:30,
		        borderWidth:1
		    },
            xAxis: {
            	 name:'时',
                 data: x_TimeData,
                 type : 'category',
                 boundaryGap : true ,
                 axisLabel: {  
                	formatter: '{value}时', 
		             interval:0,  
             	   	 rotate:50 
                	} 
                 /* axisLabel: {
		                formatter: '{value}时'
		            } */
            },
            yAxis: [
		        {
		           	type: 'value', 
		           	name:'风速（单位：m/s）',
		            min: min,
		            max: max,
		            position: 'left',
		            axisLabel: {
		                formatter: '{value} m/s'
		            } 
		        }      		     		
            ],
            series: [{
                name: '风力',
                type: 'line',
                data: DateObject,
                symbol: 'triangle',
                symbolSize: 60,
				itemStyle: {
		            normal: {
		                borderWidth: 3,
		                borderColor: 'orange',
		                color: 'orange'
		            }
	        	},
            }]
        };

        // 使用刚指定的配置项和数据显示图表。
        myChart.setOption(WDWF_Option);    	
    }
    
        //data数据方法,设置风力，风向图标
        function getSymbolObject(windv,windf){
			var setSymbol={
                    value:windv,
                    symbol: 'image://'+getTypeImager(windv),  // 数据级个性化拐点图形
                    symbolSize : 55,
                    symbolRotate: windf,
                    showAllSymbol: true,
                    itemStyle : { normal: {label : {
                        show: true,
                        textStyle : {
                            fontSize : '20',
                            fontFamily : '微软雅黑',
                            fontWeight : 'blue'
                        }
                    }}}
	        }	
	        return setSymbol;
        }
        
        //获取风力图片
		function getTypeImager(windv){
			var windvfloat = parseFloat(windv);
			var result = "/jeecms/static/wind/";
			/*return result;*/
			if(isNaN(windvfloat) || windvfloat >= 0 & windvfloat <= 0.3)
				result = result + "0.png";
			if(windvfloat > 0.3 & windvfloat <= 1.6)
				result = result + "1.png";
			if(windvfloat > 1.6 & windvfloat <= 3.4)
				result = result + "2.png";
			if(windvfloat > 3.4 & windvfloat <= 5.5)
				result = result + "3.png";
			if(windvfloat > 5.5 & windvfloat <= 8.0)
				result = result + "4.png";
			if(windvfloat > 8.0 & windvfloat <= 10.8)
				result = result + "5.png";
			if(windvfloat > 10.8 & windvfloat <= 13.9)
				result = result + "6.png";
			if(windvfloat > 13.9 & windvfloat <= 17.2)
				result = result + "7.png";
			if(windvfloat > 17.2 & windvfloat <= 20.8)
				result = result + "8.png";
			if(windvfloat > 20.8 & windvfloat <= 24.5)
				result = result + "9.png";
			if(windvfloat > 24.5 & windvfloat <= 28.5)
				result = result + "10.png";
			if(windvfloat > 28.5 & windvfloat <= 32.6)
				result = result + "11.png";
			if(windvfloat > 32.6)
				result = result + "12.png";
			return result;
		}
        
    </script>


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
//***************气温折线图**********************     
/*气温曲线香洲开始  */
var myChart = ec.init(document.getElementById('con_qwqx_1'));

var minMax = ${xz_y_date};
var min = 100;
var max = 0;
	for(var i=0 ;i < minMax.length ; i++){
		var overValue = parseFloat(minMax[i])//变成浮点型数字
		if(overValue > max){
			max = overValue;
		}
		if(overValue < min){
			min = overValue;
		}
	}
	min = Math.round(min);//四写五入取整数
    max = Math.round(max);//四写五入取整数
	min -= 2;
    max += 2;
var option = {
    title : {
        text: '香洲站24小时气温统计',
        subtext: '珠海市气象局',
        x:'center',
         textStyle:{
               fontSize: 15,
               fontWeight: 'bolder',
               color: '#333'
        }          
    },
    grid:{
        x:42,
        y:43,
        x2:11,
        y2:33,
        borderWidth:1
       },
    tooltip : {
        trigger: 'axis',
        show : false
    },
    legend: {
        show:false,
        data:['气温']
    },
    toolbox: {
        show : false
    },
    calculable : false,
    xAxis : [
        {
            type : 'category',
            data : ['00', '01', '02', '03', '04', '05', '06', '07', '08', '09', '10', '11', '12', '13', '14', '15','16','17','18','19','20','21','22','23'],
	        axisLabel : {
	           formatter: '{value}时',
	            show:true,
	            interval: 'auto',    // {number}
	            rotate:45,
	            margin: 8
	        }
        }
    ],
    yAxis : [
        {
            type : 'value',
            name : '  温度 ( 单位：℃ )',
            nameTextStyle:{
                          color:'black'
                        },
            max:max,
            min:min,
            axisLabel: {
		                formatter:'{value}℃',
		            } 
        }
    ],
    series : [
        {
            name:'气温',
            type:'line',
            data:${xz_y_date},
            itemStyle:{
	           	normal: {
	           		/* borderWidth: 3,
	                borderColor: 'yellow', */
	                color: 'orange',
	            	label: {
	                        show: true,
	                         textStyle : {
	                            fontSize : '11',
	                            fontFamily : '微软雅黑',
	                            fontWeight : 'bold'
	                        }
	                    }
		            }
	            }
        }
    ]
};                        
myChart.setOption(option); 
/*气温曲线香洲结束  */
 
/*气温曲线斗门开始  */
var myChart = ec.init(document.getElementById('con_qwqx_2'));     
	var minMax = ${dm_y_date};
	var min = 100;
	var max = 0;
		for(var i=0 ;i < minMax.length ; i++){
			var overValue = parseFloat(minMax[i])//变成浮点型数字
			if(overValue > max){
				max = overValue;
			}
			if(overValue < min){
				min = overValue;
			}
		}
		min = Math.round(min);//四写五入取整数
	    max = Math.round(max);//四写五入取整数
		min -= 2;
	    max += 2;
var option = {
    title : {
        text: '斗门站24小时气温统计',
        subtext: '珠海市气象局',
        x:'center',
         textStyle:{
               fontSize: 15,
               fontWeight: 'bolder',
               color: '#333'
        }          
    },
     grid:{
        x:42,
        y:43,
        x2:11,
        y2:33,
        borderWidth:1
       },
    tooltip : {
        trigger: 'axis',
        show : false
    },
    legend: {
        show:false,
        data:['气温']
    },
    toolbox: {
        show : false
    },
    calculable : false,
    xAxis : [
        {
            type : 'category',
            data : ['00', '01', '02', '03', '04', '05', '06', '07', '08', '09', '10', '11', '12', '13', '14', '15','16','17','18','19','20','21','22','23'],
	        axisLabel : {
	           formatter: '{value}时',
	            show:true,
	            interval: 'auto',    // {number}
	            rotate:45,
	            margin: 8
	        }
        }
    ],
    yAxis : [
        {
            type : 'value',
            name : '  温度 ( 单位：℃ )',
            nameTextStyle:{
                          color:'black'
                        },
            max:max,
            min:min,
            axisLabel: {
		                formatter:'{value}℃',
		            } 
        }
    ],
    series : [
        {
            name:'气温',
            type:'line',
            data:${dm_y_date},
            itemStyle:{
	           	normal: {
	           		color: 'orange',
	            	label: {
	                        show: true,
	                         textStyle : {
	                            fontSize : '11',
	                            fontFamily : '微软雅黑',
	                            fontWeight : 'bold'
	                        }
	                    }
		            }
	            }
        }
    ]
};                        
myChart.setOption(option); 
/*气温曲线斗门结束  */ 

/*气温曲线金湾开始  */
var myChart = ec.init(document.getElementById('con_qwqx_3'));
	var minMax = ${jw_y_date};
	var min = 100;
	var max = 0;
		for(var i=0 ;i < minMax.length ; i++){
			var overValue = parseFloat(minMax[i])//变成浮点型数字
			if(overValue > max){
				max = overValue;
			}
			if(overValue < min){
				min = overValue;
			}
		}
		min = Math.round(min);//四写五入取整数
	    max = Math.round(max);//四写五入取整数
		min -= 2;
	    max += 2;
var option = {
    title : {
        text: '金湾站24小时气温统计',
        subtext: '珠海市气象局',
        x:'center',
         textStyle:{
               fontSize: 15,
               fontWeight: 'bolder',
               color: '#333'
        }          
    },
    grid:{
        x:42,
        y:43,
        x2:11,
        y2:33,
        borderWidth:1
       },
    tooltip : {
        trigger: 'axis',
        show : false
    },
    legend: {
        show:false,
        data:['气温']
    },
    toolbox: {
        show : false
    },
    calculable : false,
   xAxis : [
        {
            type : 'category',
            data : ['00', '01', '02', '03', '04', '05', '06', '07', '08', '09', '10', '11', '12', '13', '14', '15','16','17','18','19','20','21','22','23'],
	        axisLabel : {
	           formatter: '{value}时',
	            show:true,
	            interval: 'auto',    // {number}
	            rotate:45,
	            margin: 8
	        }
        }
    ],
    yAxis : [
        {
            type : 'value',
            name : '  温度 ( 单位：℃ )',
            nameTextStyle:{
                          color:'black'
                        },
            max:max,
            min:min,
            axisLabel: {
		                formatter:'{value}℃',
		            } 
        }
    ],
    series : [
        {
            name:'气温',
            type:'line',
            data:${jw_y_date},
            itemStyle:{
	           	normal: {
	           		color: 'orange',
	            	label: {
	                        show: true,
	                         textStyle : {
	                            fontSize : '11',
	                            fontFamily : '微软雅黑',
	                            fontWeight : 'bold'
	                        }
	                    }
		            }
	            }
        }
    ]
};                        
myChart.setOption(option); 
/*气温曲线金湾结束  */
 
/*气温曲线横琴开始  */
var myChart = ec.init(document.getElementById('con_qwqx_4'));               
	var minMax = ${hq_y_date};
	var min = 100;
	var max = 0;
		for(var i=0 ;i < minMax.length ; i++){
			var overValue = parseFloat(minMax[i])//变成浮点型数字
			if(overValue > max){
				max = overValue;
			}
			if(overValue < min){
				min = overValue;
			}
		}
		min = Math.round(min);//四写五入取整数
	    max = Math.round(max);//四写五入取整数
		min -= 2;
	    max += 2;
var option = {
    title : {
        text: '横琴站24小时气温统计',
        subtext: '珠海市气象局',
        x:'center',
         textStyle:{
               fontSize: 15,
               fontWeight: 'bolder',
               color: '#333'
        }          
    },
     grid:{
        x:42,
        y:43,
        x2:11,
        y2:33,
        borderWidth:1
       },
    tooltip : {
        trigger: 'axis',
        show : false
    },
    legend: {
        show:false,
        data:['气温']
    },
    toolbox: {
        show : false
    },
    calculable : false,
   xAxis : [
        {
            type : 'category',
            data : ['00', '01', '02', '03', '04', '05', '06', '07', '08', '09', '10', '11', '12', '13', '14', '15','16','17','18','19','20','21','22','23'],
	        axisLabel : {
	           formatter: '{value}时',
	            show:true,
	            interval: 'auto',    // {number}
	            rotate:45,
	            margin: 8
	        }
        }
    ],
    yAxis : [
        {
            type : 'value',
            name : '  温度 ( 单位：℃ )',
            nameTextStyle:{
                          color:'black'
                        },
            max:max,
            min:min,
            axisLabel: {
		                formatter:'{value}℃',
		            } 
        }
    ],
    series : [
        {
            name:'气温',
            type:'line',
            data:${hq_y_date},
            itemStyle:{
	           	normal: {
	           		color: 'orange',
	            	label: {
	                        show: true,
	                         textStyle : {
	                            fontSize : '11',
	                            fontFamily : '微软雅黑',
	                            fontWeight : 'bold'
	                        }
	                    }
		            }
	            }
        }
    ]
};                        
myChart.setOption(option); 
/*气温曲线横琴结束  */ 
}
);
  

	


//***************降雨量折线图**********************
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
         'echarts/chart/bar' // 使用柱状图就加载bar模块，按需加载
     ],
function (ec) {
 /*香洲降雨量曲线开始  */
var myChart = ec.init(document.getElementById('con_jylqx_1')); 
var option = {
    title : {
        text: '香洲站24小时雨量统计',
        subtext: '珠海市气象局',
        x:'center',
        textStyle:{
               fontSize: 15,
               fontWeight: 'bolder',
               color: '#333'
        }        
    },
     grid:{
        x:46,
        y:43,
        x2:11,
        y2:33,
        borderWidth:1
       },
    tooltip : {
        trigger: 'axis',
        show : false
    },
    legend: {
        show:false,
        data:['降水量']
    },
    toolbox: {
        show : false
    },
     calculable : false,
    xAxis : [
        {
            type : 'category',
            data : ['00', '01', '02', '03', '04', '05', '06', '07', '08', '09', '10', '11', '12', '13', '14', '15','16','17','18','19','20','21','22','23'],
	        axisLabel : {
	            formatter: '{value}时',
	            show:true,
	            interval: 'auto',    // {number}
	            rotate:45,
	            margin: 8
	        }
        }
    ],
    yAxis : [
        {
            name : '  雨量 ( 单位：mm )',
            type : 'value',
            nameTextStyle:{
                          color:'black'
                          },
            axisLabel: {
		                formatter:'{value}mm',
		               },
            max:'${xz1_maxVal}'
        }
    ],
    series : [
        {
            name:'降水量',
            type:'bar',
            data:${xz1_y_date},
            itemStyle:{
	           	normal: {
	           		color: 'orange',
	            	label: {
	                        show: true,
	                         textStyle : {
	                            fontSize : '11',
	                            fontFamily : '微软雅黑',
	                            fontWeight : 'bold'
	                        }
// 	                         formatter: '{c} mm'
	                    }
		            }
	            }
        }
    ]
};
myChart.setOption(option); 
/*香洲降雨量曲线结束  */

/*斗门降雨量曲线开始  */
var myChart = ec.init(document.getElementById('con_jylqx_2')); 
var option = {
    title : {
        text: '斗门站24小时雨量统计',
        subtext: '珠海市气象局',
        x:'center',
        textStyle:{
               fontSize: 15,
               fontWeight: 'bolder',
               color: '#333'
        }        
    },
     grid:{
        x:46,
        y:43,
        x2:11,
        y2:33,
        borderWidth:1
       },
    tooltip : {
        trigger: 'axis',
        show : false
    },
    legend: {
        show:false,
        data:['降水量']
    },
    toolbox: {
        show : false
    },
    calculable : false,
    xAxis : [
        {
            type : 'category',
            data : ['00', '01', '02', '03', '04', '05', '06', '07', '08', '09', '10', '11', '12', '13', '14', '15','16','17','18','19','20','21','22','23'],
	        axisLabel : {
	            formatter: '{value}时',
	            show:true,
	            interval: 'auto',    // {number}
	            rotate:45,
	            margin: 8
	        }
        }
    ],
    yAxis : [
        {
            name : '  雨量 ( 单位：mm )',
            type : 'value',
            nameTextStyle:{
                          color:'black'
                          },
            axisLabel: {
		                formatter:'{value}mm',
		               },
            max:'${dm1_maxVal}'
        }
    ],
    series : [
        {
            name:'降水量',
            type:'bar',
            data:${dm1_y_date},
            itemStyle:{
	           	normal: {
	           		color: 'orange',
	            	label: {
	                        show: true,
	                         textStyle : {
	                            fontSize : '11',
	                            fontFamily : '微软雅黑',
	                            fontWeight : 'bold'
	                        }
// 	                         formatter: '{c} mm'
	                    }
		            }
	            }
        }
    ]
};
myChart.setOption(option); 
/*斗门降雨量曲线结束  */

/*金湾降雨量曲线开始  */
var myChart = ec.init(document.getElementById('con_jylqx_3')); 
var option = {
    title : {
        text: '金湾站24小时雨量统计',
        subtext: '珠海市气象局',
        x:'center',
        textStyle:{
               fontSize: 15,
               fontWeight: 'bolder',
               color: '#333'
        }        
    },
     grid:{
        x:46,
        y:43,
        x2:11,
        y2:33,
        borderWidth:1
       },
    tooltip : {
        trigger: 'axis',
        show : false
    },
    legend: {
        show:false,
        data:['降水量']
    },
    toolbox: {
        show : false
    },
    calculable : false,
    xAxis : [
        {
            type : 'category',
            data : ['00', '01', '02', '03', '04', '05', '06', '07', '08', '09', '10', '11', '12', '13', '14', '15','16','17','18','19','20','21','22','23'],
	        axisLabel : {
	            formatter: '{value}时',
	            show:true,
	            interval: 'auto',    // {number}
	            rotate:45,
	            margin: 8
	        }
        }
    ],
    yAxis : [
        {
            name : '  雨量 ( 单位：mm )',
            type : 'value',
            nameTextStyle:{
                          color:'black'
                          },
            axisLabel: {
		                formatter:'{value}mm',
		               },
            max:'${jw1_maxVal}'
        }
    ],
    series : [
        {
            name:'降水量',
            type:'bar',
            data:${jw1_y_date},
            itemStyle:{
	           	normal: {
	           		color: 'orange',
	            	label: {
	                        show: true,
	                         textStyle : {
	                            fontSize : '11',
	                            fontFamily : '微软雅黑',
	                            fontWeight : 'bold'
	                        }
// 	                         formatter: '{c} mm'
	                    }
		            }
	            }
        }
    ]
};
myChart.setOption(option); 
/*金湾降雨量曲线结束  */


/*横琴降雨量曲线开始  */
var myChart = ec.init(document.getElementById('con_jylqx_4')); 
var option = {
    title : {
        text: '横琴站24小时雨量统计',
        subtext: '珠海市气象局',
        x:'center',
        textStyle:{
               fontSize: 15,
               fontWeight: 'bolder',
               color: '#333'
        }        
    },
     grid:{
        x:46,
        y:43,
        x2:11,
        y2:33,
        borderWidth:1
       },
    tooltip : {
        trigger: 'axis',
        show : false
    },
    legend: {
        show:false,
        data:['降水量']
    },
    toolbox: {
        show : false
    },
    calculable : false,
    xAxis : [
        {
            type : 'category',
            data : ['00', '01', '02', '03', '04', '05', '06', '07', '08', '09', '10', '11', '12', '13', '14', '15','16','17','18','19','20','21','22','23'],
	        axisLabel : {
	            formatter: '{value}时',
	            show:true,
	            interval: 'auto',    // {number}
	            rotate:45,
	            margin: 8
	        }
        }
    ],
    yAxis : [
        {
            name : '  雨量 ( 单位：mm )',
            type : 'value',
            nameTextStyle:{
                          color:'black'
                          },
            axisLabel: {
		                formatter:'{value}mm',
		               },
            max:'${hq1_maxVal}'
        }
    ],
    series : [
        {
            name:'降水量',
            type:'bar',
            data:${hq1_y_date},
            itemStyle:{
	           	normal: {
	           		color: 'orange',
	            	label: {
	                        show: true,
	                         textStyle : {
	                            fontSize : '11',
	                            fontFamily : '微软雅黑',
	                            fontWeight : 'bold'
	                        }
// 	                         formatter: '{c} mm'
	                    }
		            }
	            }
        }
    ]
};
myChart.setOption(option); 
/*横琴降雨量曲线结束  */
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
<script src="<c:url value="/static/common/js/jquery.tools.min.js"/>"></script>

</body>
</html>