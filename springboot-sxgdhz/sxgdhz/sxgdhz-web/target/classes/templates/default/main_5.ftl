<%@ page pageEncoding="UTF-8" %>
﻿<div class="con_module" style="margin-top:-20px;">
		<div class="con_left cllta">
			<div class="con_typhoon">
				<h1 class="con_h1a pendha">
					<span style="float:left;color:#fff;font-size:16px;">未来七天天气</span> <span
						class="spansa">${fivDayMap_publishTime } 发布 <a id="onetwo1"
						onclick="setTab('onetwo',1,2)" class="hover" href="javascript:viod(0)"><img
							src="<c:url value="/static/common/images/typhoons.png"/>" />趋势</a> <a
						id="onetwo2" onclick="setTab('onetwo',2,2)"
						href="javascript:viod(0)"><img
							src="<c:url value="/static/common/images/typhoons.png"/>" />列表</a> </span>
				</h1>
				<div id="pending_con" class="pending_con">
					<div id="con_onetwo_1" class="pending_ul" style="display:block">
						<div class="pendingnav" style="font-weight: bold;">${txqs }</div>
						<table width="100%" cellpadding="0" cellspacing="0" border="0"
							class="pendtab">
							<tr>
								<td style="padding-top: 0px;" width="12.5%"><span
									class="tdspan1 fontsyellow">${fivDayMap[1].week }</span>
								</td>
								<td style="padding-top: 0px;" width="12.5%"><span
									class="tdspan1 fontsyellow">${fivDayMap[2].week }</span>
								</td>
								<td style="padding-top: 0px;" width="12.5%"><span
									class="tdspan1 fontsyellow">${fivDayMap[3].week }</span>
								</td>
								<td style="padding-top: 0px;" width="12.5%"><span
									class="tdspan1 fontsyellow">${fivDayMap[4].week }</span>
								</td>
								<td style="padding-top: 0px;" width="12.5%"><span
									class="tdspan1 fontsyellow">${fivDayMap[5].week }</span>
								</td>
								<td style="padding-top: 0px;" width="12.5%"><span
									class="tdspan1 fontsyellow">${fivDayMap[6].week }</span>
								</td>
								<td style="padding-top: 0px;" width="12.5%"><span
									class="tdspan1 fontsyellow">${fivDayMap[7].week }</span>
								</td>
							</tr>
							<tr>
								<td style="padding-top: 0px;"><span
									class="tdspan1 fontsblue">${fivDayMap[1].date }</span>
								</td>
								<td style="padding-top: 0px;"><span
									class="tdspan1 fontsblue">${fivDayMap[2].date }</span>
								</td>
								<td style="padding-top: 0px;"><span
									class="tdspan1 fontsblue">${fivDayMap[3].date }</span>
								</td>
								<td style="padding-top: 0px;"><span
									class="tdspan1 fontsblue">${fivDayMap[4].date }</span>
								</td>
								<td style="padding-top: 0px;"><span
									class="tdspan1 fontsblue">${fivDayMap[5].date }</span>
								</td>
								<td style="padding-top: 0px;"><span
									class="tdspan1 fontsblue">${fivDayMap[6].date }</span>
								</td>
								<td style="padding-top: 0px;"><span
									class="tdspan1 fontsblue">${fivDayMap[7].date }</span>
								</td>
							</tr>
							<tr>
								<td style="padding-top: 0px;"><img title="${fivDayMap[1].weatherDesc}" style="width: 80px;height: 80px;"
									src="<c:url value="/static/common/images/logo1/${fivDayMap[1].picId }.png"/>" />
								</td>
								<td style="padding-top: 0px;"><img title="${fivDayMap[2].weatherDesc}" style="width: 80px;height: 80px;"
									src="<c:url value="/static/common/images/logo1/${fivDayMap[2].picId }.png"/>" />
								</td>
								<td style="padding-top: 0px;"><img title="${fivDayMap[3].weatherDesc}" style="width: 80px;height: 80px;"
									src="<c:url value="/static/common/images/logo1/${fivDayMap[3].picId }.png"/>" />
								</td>
								<td style="padding-top: 0px;"><img title="${fivDayMap[4].weatherDesc}" style="width: 80px;height: 80px;"
									src="<c:url value="/static/common/images/logo1/${fivDayMap[4].picId }.png"/>" />
								</td>
								<td style="padding-top: 0px;"><img title="${fivDayMap[5].weatherDesc}" style="width: 80px;height: 80px;"
									src="<c:url value="/static/common/images/logo1/${fivDayMap[5].picId }.png"/>" />
								</td>
								<td style="padding-top: 0px;"><img title="${fivDayMap[6].weatherDesc}" style="width: 80px;height: 80px;"
									src="<c:url value="/static/common/images/logo1/${fivDayMap[6].picId }.png"/>" />
								</td>
								<td style="padding-top: 0px;"><img title="${fivDayMap[7].weatherDesc}" style="width: 80px;height: 80px;"
									src="<c:url value="/static/common/images/logo1/${fivDayMap[7].picId }.png"/>" />
								</td>
							</tr>
						</table>

						<div id="main" style="height:180px;width: 800px;"></div>
					</div>
					<div id="con_onetwo_2" class="pendcon"
						style="overflow:hidden;display:none">
						<div class="pendingnav" style="font-weight: bold;">${txqs }</div>
						<table width="100%" cellpadding="0" cellspacing="0" border="0"
							class="pendtab">
							<tr>
								<td style="padding-top: 0px;" rowspan="2" width="12.5%">日期</td>
								<td style="padding-top: 0px;" width="12.5%"><span
									class="tdspan1 fontsyellow">${fivDayMap[1].week }</span>
								</td>
								<td style="padding-top: 0px;" width="12.5%"><span
									class="tdspan1 fontsyellow">${fivDayMap[2].week }</span>
								</td>
								<td style="padding-top: 0px;" width="12.5%"><span
									class="tdspan1 fontsyellow">${fivDayMap[3].week }</span>
								</td>
								<td style="padding-top: 0px;" width="12.5%"><span
									class="tdspan1 fontsyellow">${fivDayMap[4].week }</span>
								</td>
								<td style="padding-top: 0px;" width="12.5%"><span
									class="tdspan1 fontsyellow">${fivDayMap[5].week }</span>
								</td>
								<td style="padding-top: 0px;" width="12.5%"><span
									class="tdspan1 fontsyellow">${fivDayMap[6].week }</span>
								</td>
								<td style="padding-top: 0px;" width="12.5%"><span
									class="tdspan1 fontsyellow">${fivDayMap[7].week }</span>
								</td>
							</tr>
							<tr>
								<td style="padding-top: 0px;"><span
									class="tdspan1 fontsblue">${fivDayMap[1].date }</span>
								</td>
								<td style="padding-top: 0px;"><span
									class="tdspan1 fontsblue">${fivDayMap[2].date }</span>
								</td>
								<td style="padding-top: 0px;"><span
									class="tdspan1 fontsblue">${fivDayMap[3].date }</span>
								</td>
								<td style="padding-top: 0px;"><span
									class="tdspan1 fontsblue">${fivDayMap[4].date }</span>
								</td>
								<td style="padding-top: 0px;"><span
									class="tdspan1 fontsblue">${fivDayMap[5].date }</span>
								</td>
								<td style="padding-top: 0px;"><span
									class="tdspan1 fontsblue">${fivDayMap[6].date }</span>
								</td>
								<td style="padding-top: 0px;"><span
									class="tdspan1 fontsblue">${fivDayMap[7].date }</span>
								</td>
							</tr>
							<tr>
								<td style="padding-top: 0px;" rowspan="2">天气状况</td>
								<td style="padding-top: 0px;"><img title="${fivDayMap[1].weatherDesc}" style="width: 80px;height: 80px;"
									src="<c:url value="/static/common/images/logo1/${fivDayMap[1].picId }.png"/>" />
								</td>
								<td style="padding-top: 0px;"><img title="${fivDayMap[2].weatherDesc}" style="width: 80px;height: 80px;"
									src="<c:url value="/static/common/images/logo1/${fivDayMap[2].picId }.png"/>" />
								</td>
								<td style="padding-top: 0px;"><img title="${fivDayMap[3].weatherDesc}" style="width: 80px;height: 80px;"
									src="<c:url value="/static/common/images/logo1/${fivDayMap[3].picId }.png"/>" />
								</td>
								<td style="padding-top: 0px;"><img title="${fivDayMap[4].weatherDesc}" style="width: 80px;height: 80px;"
									src="<c:url value="/static/common/images/logo1/${fivDayMap[4].picId }.png"/>" />
								</td>
								<td style="padding-top: 0px;"><img title="${fivDayMap[5].weatherDesc}" style="width: 80px;height: 80px;"
									src="<c:url value="/static/common/images/logo1/${fivDayMap[5].picId }.png"/>" />
								</td>
								<td style="padding-top: 0px;"><img title="${fivDayMap[6].weatherDesc}" style="width: 80px;height: 80px;"
									src="<c:url value="/static/common/images/logo1/${fivDayMap[6].picId }.png"/>" />
								</td>
								<td style="padding-top: 0px;"><img title="${fivDayMap[7].weatherDesc}" style="width: 80px;height: 80px;"
									src="<c:url value="/static/common/images/logo1/${fivDayMap[7].picId }.png"/>" />
								</td>
							</tr>
							<tr>
								<td style="vertical-align: top">${fivDayMap[1].weatherDesc}
									</td>
								<td style="vertical-align: top">${fivDayMap[2].weatherDesc}
									</td>
								<td style="vertical-align: top">${fivDayMap[3].weatherDesc}
									</td>
								<td style="vertical-align: top">${fivDayMap[4].weatherDesc}
									</td>
								<td style="vertical-align: top">${fivDayMap[5].weatherDesc}
									</td>
								<td style="vertical-align: top">${fivDayMap[6].weatherDesc}
									</td>
								<td style="vertical-align: top">${fivDayMap[7].weatherDesc}
									</td>
							</tr>
							<tr>
								<td>温度</td>
								<td>${fivDayMap[1].temperature }</td>
								<td>${fivDayMap[2].temperature }</td>
								<td>${fivDayMap[3].temperature }</td>
								<td>${fivDayMap[4].temperature }</td>
								<td>${fivDayMap[5].temperature }</td>
								<td>${fivDayMap[6].temperature }</td>
								<td>${fivDayMap[7].temperature }</td>
							</tr>
							<tr>
								<td>湿度</td>
								<td>${fivDayMap[1].humidity }</td>
								<td>${fivDayMap[2].humidity }</td>
								<td>${fivDayMap[3].humidity }</td>
								<td>${fivDayMap[4].humidity }</td>
								<td>${fivDayMap[5].humidity }</td>
								<td>${fivDayMap[6].humidity }</td>
								<td>${fivDayMap[7].humidity }</td>
							</tr>
							<tr>
								<td>风向</td>
								<td>${fivDayMap[1].windDirect }</td>
								<td>${fivDayMap[2].windDirect }</td>
								<td>${fivDayMap[3].windDirect }</td>
								<td>${fivDayMap[4].windDirect }</td>
								<td>${fivDayMap[5].windDirect }</td>
								<td>${fivDayMap[6].windDirect }</td>
								<td>${fivDayMap[7].windDirect }</td>
							</tr>
							<tr>
								<td>陆地风力</td>
								<td>${fivDayMap[1].windSpeed }</td>
								<td>${fivDayMap[2].windSpeed }</td>
								<td>${fivDayMap[3].windSpeed }</td>
								<td>${fivDayMap[4].windSpeed }</td>
								<td>${fivDayMap[5].windSpeed }</td>
								<td>${fivDayMap[6].windSpeed }</td>
								<td>${fivDayMap[7].windSpeed }</td>
							</tr>
							<tr>
								<td>海面风力</td>
								<td>${fivDayMap[1].onceWindSpeed }</td>
								<td>${fivDayMap[2].onceWindSpeed }</td>
								<td>${fivDayMap[3].onceWindSpeed }</td>
								<td>${fivDayMap[4].onceWindSpeed }</td>
								<td>${fivDayMap[5].onceWindSpeed }</td>
								<td>${fivDayMap[6].onceWindSpeed }</td>
								<td>${fivDayMap[7].onceWindSpeed }</td>
							</tr>
						</table>
					</div>
				</div>
			</div>
		</div>
		<div class="con_right clrta">
			<div class="con_typhoon" id="conty1">
				<h1 class="con_h1b">
					<a href="<c:url value="/web/ybfw/rcrl"/>"
						style="color: #fff;float:left">日出日落</a> <span class="spanrt">${nowTime}
						发布</span>
				</h1>
				<div id="sunrise_con" class="sunrise_con" style="height: 170px;">
					<div id="con_nine_1" class="sunriseimg" STYLE="display: block;">
						<span class="spanl">日出<br /> <strong class="fontsyellow">${sunRise_1}
								</strong> </span>
						<object id="FlashID"
							classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000" width="256"
							height="144">
							<param name="movie"
								value="<c:url value="/static/common/flash/riluochu.swf"/>" />
							<param name="quality" value="high" />
							<param name="wmode" value="transparent" />
							<embed src="<c:url value="/static/common/flash/riluochu.swf"/>"
								wmode="transparent" quality="high"
								pluginspage="http://www.macromedia.com/go/getflashplayer"
								type="application/x-shockwave-flash" width="256" height="144"></embed>
						</object>
						<span class="spanr">日落<br /> <strong class="fontsyellow">${sunSet_1}
								</strong> </span>
					</div>
					<div id="con_nine_2" class="sunriseimg">
						<span class="spanl">日出<br /> <strong class="fontsyellow">${sunRise_2}
								</strong> </span>
						<object id="FlashID"
							classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000" width="256"
							height="144">
							<param name="movie"
								value="<c:url value="/static/common/flash/riluochu.swf"/>" />
							<param name="quality" value="high" />
							<param name="wmode" value="transparent" />
							<embed src="<c:url value="/static/common/flash/riluochu.swf"/>"
								wmode="transparent" quality="high"
								pluginspage="http://www.macromedia.com/go/getflashplayer"
								type="application/x-shockwave-flash" width="256" height="144"></embed>
						</object>
						<span class="spanr">日落<br /> <strong class="fontsyellow">${sunSet_2}
								</strong> </span>
					</div>
					<div id="con_nine_3" class="sunriseimg">
						<span class="spanl">日出<br /> <strong class="fontsyellow">${sunRise_3}
								</strong> </span>
						<object id="FlashID"
							classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000" width="256"
							height="144">
							<param name="movie"
								value="<c:url value="/static/common/flash/riluochu.swf"/>" />
							<param name="quality" value="high" />
							<param name="wmode" value="transparent" />
							<embed src="<c:url value="/static/common/flash/riluochu.swf"/>"
								wmode="transparent" quality="high"
								pluginspage="http://www.macromedia.com/go/getflashplayer"
								type="application/x-shockwave-flash" width="256" height="144"></embed>
						</object>
						<span class="spanr">日落<br /> <strong class="fontsyellow">${sunSet_3}
								</strong> </span>
					</div>
					<p style="margin-top: -10px;">
						<a id="nine1" onClick="" href="javascript:setTab('nine',1,3)">${day1}
							</a> <a id="nine2" onClick="" href="javascript:setTab('nine',2,3)">${day2}
							</a> <a id="nine3" onClick="" href="javascript:setTab('nine',3,3)">${day3}
							</a>
					</p>
				</div>
			</div>
            <div class="zscd">
                <h3><span>政声传递</span><a id="dialog">更多</a></h3>
                <ul id="zscd"></ul>

            </div>
			<!--div class="con_typhoon" id="conty2">
				<%@include file="/WEB-INF/views/web/chaoxi.jsp"%>
			</div-->
		</div>
		<div class="clearfloat"></div>

	</div>