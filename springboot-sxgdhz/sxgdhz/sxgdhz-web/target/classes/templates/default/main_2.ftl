<%@ page pageEncoding="UTF-8" %>
﻿<#if typhoonState == 'show'>
		<div class="con_module" style="display: block;">
</#if>
<#if typhoonState != 'show'>
		<div class="con_module" style="display: none;">
</#if>

	<div class="con_left cllta">
		<div class="con_typhoon" style="margin-bottom: 20px;">
			<h1 class="con_h1a">
				<span
					style="float:left;color:#fff;font-size:16px;font-weight:bold;">台风路径</span>
				<a style="float:right" href="<c:url value="/web/tflj"/>"
					target="_blank"> <span class="spansa">点击查询</span> </a>
			</h1>
			<div class="typhoon_con">
				<#if "${typhoonpic}" == "0">
                    <iframe id="mygis" src="/typhoon/typhoon.html" style="height: 600px;" id="iframe" width="100%"
                            frameborder="0" height="100%"></iframe>
                <#else>
					<img id="tempTyph" onclick="typhoonclick();" src="<c:url value="/static/common/images/typhoon.jpg"/>">
                </#if>
			</div>
		</div>
	</div>
	<div class="con_right clrta">
		<div class="con_typhoon">
			<h1 class="con_h1b">
				<span
					style="float:left;color:#fff;font-size:16px;font-weight:bold;">台风情况</span>
			</h1>
			<div class="typhoon_con" style="overflow-y:auto; ">
						<div id="typhoonInfo">
						<#if lastTyphSkInfo??>
							<table>
								<tr><td><span>台风名称：</span></td><td>${lastTyphSkInfo.name }</td></tr>
								<tr><td><span>时  间：</span></td><td>${lastTyphSkInfo.time }</td></tr>
								<tr><td><span>等  级：</span></td><td>${lastTyphSkInfo.grade }</td></tr>
								<tr><td><span>当前位置：</span></td><td>东经${lastTyphSkInfo.longitude } 北纬${lastTyphSkInfo.latitude }</td></tr>
								<tr><td><span>中心气压：</span></td><td>${lastTyphSkInfo.pressure }百帕</td></tr>
								<tr><td><span>风  力：</span></td><td>${lastTyphSkInfo.windforce }级</td></tr>
								<tr><td><span>平均风速：</span></td><td>${lastTyphSkInfo.windspeed }米/秒</td></tr>
								<tr><td><span>阵风风速：</span></td><td>${lastTyphSkInfo.gust }米/秒</td></tr>
								<tr><td><span>七级半径：</span></td><td>${lastTyphSkInfo.rr07 }公里</td></tr>
								<tr><td><span>距离珠海：</span></td><td>${lastTyphSkInfo.distances }公里</td></tr>
							</table>
						</#if>
						</div>
				${tcwDesc }
			</div>
		</div>
	</div>
	<div class="clearfloat"></div>
</div>