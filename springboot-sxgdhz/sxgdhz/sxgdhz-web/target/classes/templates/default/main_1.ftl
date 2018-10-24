<%@ page pageEncoding="UTF-8" %>
<script type='text/javascript'>
var num=0;
function autoChange(){ 
if(num==0){
	document.getElementById('banshi').style.display='none';
	document.getElementById('quanze').style.display='block';
	document.getElementById('wangluo').style.display='none';
	num++;
}else if(num==1){
	document.getElementById('quanze').style.display='none';
	document.getElementById('banshi').style.display='none';
	document.getElementById('wangluo').style.display='block';
	num++;
}else{
	document.getElementById('quanze').style.display='none';
	document.getElementById('banshi').style.display='block';
	document.getElementById('wangluo').style.display='none';
	num=0;
}
};
setInterval('autoChange()',1500);
</script>
<div class="con_module" style="margin-top:-15px;">
	<div class="con_left cllta">
		<div class="con_prediction">
			<h1 class="con_h1a">
				<ul class="prediction_ul preul">
						<li id="seten1" onclick="setTab('seten',1,3)" class="hover">时段天气播报</li>
								<li id="seten2" onclick="setTab('seten',2,3)">气象生活指数</li>
								<li id="seten3" onclick="setTab('seten',3,3)">海岛天气</li>
					<a target="_blank" href="http://202.105.183.112:8350/Forecast"><li>空气质量</li></a>
					
				</ul>
				<a href="<c:url value="/web/ybfw/zhtq"/>"><span
					class="spansb">点击获取更多</span> </a>
			</h1>
			<div id="con_seten_1" class="prediction_con" style="display:block">
<!-- 							flash 加载过慢，增加背景色 -->
				<div class="con_left ${swfType }">
					<!--                             qingtian,yintian,yutian,lei --<!--  -->
					<object id="FlashID"
						classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000"
						width="490" height="247">
						<param name="movie"
							value="<c:url value="/static/common/flash/eaybacg-${swfType }.swf"/>" />
						<param name="quality" value="high" />
						<param name="wmode" value="transparent" />
						<embed
							src="<c:url value="/static/common/flash/eaybacg-${swfType }.swf"/>"
							wmode="transparent" quality="high"
							pluginspage="http://www.macromedia.com/go/getflashplayer"
							type="application/x-shockwave-flash" width="490" height="247"></embed>
					</object>
					<div class="ob_flash">
						<p>
							<span>珠海市</span>${weatherMap.desc }
						</p>
						<div>
							<img class="limg"
								src="<c:url value="/static/common/images/logo1/${weatherMap.picId }.png"/>" />
							<p>${weatherMap.weatherDesc }</p>
							<p>气&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;温：${weatherMap.temperature }</p>
							<p>风&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;向：${weatherMap.windDirect }</p>
							<p>风&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;速：${weatherMap.windSpeed }</p>
							<p>相对湿度：${weatherMap.humidity }</p>
							<p class="p1">珠海市气象台 ${weatherMap.publishTime }</p>
							<p class="p1">
								<a target="target" href="<c:url value="/web/kpfw"/>"><img
									src="<c:url value="/static/common/images/conlist7.png"/>" />降雨等级划分表</a>
								<a target="target"
									href="<c:url value="/web/menu?method=kpfw_fldj"/>"><img
									src="<c:url value="/static/common/images/conlist7.png"/>" />风力等级划分表</a>
							</p>
						</div>
					</div>
				</div>
				<div class="con_right">
					<h1>天气实景</h1>
					<div class="foucs" id="ifocus">
						<%@include file="/WEB-INF/views/web/indexpage/actualWeather.jsp"%>
					</div>
				</div>
			</div>
			
			<div id="con_seten_2" class="prediction_con pre3" style="background-color: #EDF5FB;">
					<p>
						<h3 class="hqyb_tt">珠海市气象台${lifeMap_time}发布</h3>
					</p>
				<ul>
					<#list lifeMap as life>
						<#if life_index != 0>
							<li><img width="50" height="50"
								src="<c:url value="/static/images/${life.picId }.png"/>" />
								<p>
									<span class="fontzblue">${life.lifeType }</span>
								</p>
								<p>指数等级：${life.lifeClass }</p>
								<p>
									<#if life.strong != ''>
	                            		强度：${life.strong }
	                            	</#if>
								</p></li>
						</#if>
					</#list>
				</ul>
			</div>
			<div id="con_seten_3" class="prediction_con">
				<h3 class="hqyb_tt">珠海市气象台${oceanMap.Time.publishTime}发布海岛天气预报</h3>
				<dl class="hqyb_ct">

					<dd>
						<table width="100%" align="center" border="0" cellspacing="0"
							cellpadding="0" class="tabel_1ist talistbb">
							<tr>
								<th>时间</th>
								<th>海域</th>
								<th>天气现象</th>
								<th>风向</th>
								<th>风力/阵风（级）</th>
								<th>能见度（千米）</th>
							</tr>
							<tr>
								<td>${oceanMap.frist.forecastdata }</td>
								<td>${oceanMap.frist.name}</td>
								<td>${oceanMap.frist.weatherDesc}</td>
								<td>${oceanMap.frist.windDirect}</td>
								<td>${oceanMap.frist.windSpeed}</td>
								<td>${oceanMap.frist.visibi}</td>
							</tr>
							<tr class="odd">
								<td>${oceanMap.second.forecastdata }</td>
								<td>${oceanMap.second.name}</td>
								<td>${oceanMap.second.weatherDesc}</td>
								<td>${oceanMap.second.windDirect}</td>
								<td>${oceanMap.second.windSpeed}</td>
								<td>${oceanMap.second.visibi}</td>
							</tr>
							<tr>
								<td>${oceanMap.thred.forecastdata }</td>
								<td>${oceanMap.thred.name}</td>
								<td>${oceanMap.thred.weatherDesc}</td>
								<td>${oceanMap.thred.windDirect}</td>
								<td>${oceanMap.thred.windSpeed}</td>
								<td>${oceanMap.thred.visibi}</td>
							</tr>
							<tr class="odd">
								<td>${oceanMap.forth.forecastdata }</td>
								<td>${oceanMap.forth.name}</td>
								<td>${oceanMap.forth.weatherDesc}</td>
								<td>${oceanMap.forth.windDirect}</td>
								<td>${oceanMap.forth.windSpeed}</td>
								<td>${oceanMap.forth.visibi}</td>
							</tr>
						</table>
					</dd>
				</dl>
			</div>
		</div>
	</div>
    <div class="con_right clrta">
        <div class="con_img" id="zt_3">
            <a id="quanze"  target="_blank" href="http://www.zhuhai.gov.cn/zhsqlqd2016/sqxj/"><img
                    src="<c:url value="/static/common/images/zh_quanze.jpg"/>">
            </a>
            <a id="banshi"  target="_blank" href="http://www.zhwsbs.gov.cn/deptWindow/index.do?divisionCode=440400&depCode=455924701"><img
                    src="<c:url value="/static/common/images/rightimg.jpg"/>">
            </a>
            <a id="wangluo"  target="_blank" href="http://www.miit.gov.cn/n1146295/n1146557/n1146614/c5345009/content.html"><img
                    src="<c:url value="/static/common/images/wangluo.jpg"/>">
            </a>
        </div>
        <div class="maps_con mapscon" style="display:block;padding:1px 4px 2px">
            <div class="con_typhoon">
                <h1 class="con_h1c">
                    <ul class="conh1c_ul">
                        <a href="<c:url value="/web/qxst"/>"><li style="margin:0;" id="threees1" class="hover" onclick="setTab('threees',1,2)">预报员讲天气</li></a>
                        <a href="<c:url value="/web/menu?method=kpfw_yztx"/>"><li style="margin:0" id="threees2" onclick="setTab('threees',2,2)">珠海一周天空图选</li></a>
                    </ul>
                    <a href="<c:url value="/web/qxst"/>" class="amore">更多</a>
                </h1>
                <div id="container" class="testt" onclick="newvideoHit()"></div>
                <script type="text/javascript" src="<c:url value="/videoList/swfobject.js"/>" charset="utf-8"></script>
                <script type="text/javascript">
                var s1 = new SWFObject("<c:url value='/videoList/player.swf'/>","ply","270","172","9","#FFFFFF");
                s1.addParam("allowfullscreen","true");
                s1.addParam("allowscriptaccess","always");
                s1.addParam("flashvars","file=<c:url value='/userfiles/swf/0-20180712.flv'/>&image=<c:url value='/videoList/video.jpg'/>");
                s1.write("container");
                </script>


            </div>
        </div>
    </div>
</div>