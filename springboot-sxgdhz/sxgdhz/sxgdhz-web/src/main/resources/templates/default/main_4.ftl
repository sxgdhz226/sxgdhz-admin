<%@ page pageEncoding="UTF-8" %>
<div class="con_module" style="margin-top:18px">
		<div class="con_left cllta">
			<div class="con_typhoon">
				<h1 class="con_h1a">
					<span style="float:left;color:#fff;font-size:16px;">实况监测展示</span> <span
						class="spansa">${skZoneMap.publishTime.publishTime }发布<a
						href="<c:url value="/web/qxjc/jyfb"/>"  target="_blank" >更多</a> </span>
				</h1>
				<div class="weahters_con" style="background-color: #F8F8F8">
					<div class="map_a">
						<p>
							<a id="sk1" href="javascript:setTab_sk('sk',1,3);" class="btnact"
								onclick="stopTimeout()" ondblclick="startTimeout()"><img
								src="<c:url value="/static/common/images/btn_1.png"/>">气温</a>
						</p>
						<p>
							<a id="sk2" href="javascript:setTab_sk('sk',2,3);"
								onclick="stopTimeout()" ondblclick="startTimeout()"><img
								src="<c:url value="/static/common/images/btn_2.png"/>">降雨量</a>
						</p>
						<p>
							<a id="sk3" href="javascript:setTab_sk('sk',3,3);"
								onclick="stopTimeout()" ondblclick="startTimeout()"><img
								src="<c:url value="/static/common/images/btn_3.png"/>">风力风向</a>
						</p>
					</div>
					<div class="weaact_con" id="con_sk_1">
						<img style="padding-left: 50px;"  src="/jeecms/resource/OCX/Temp/Plane/zhuhai${lastMin }_m.png">
						<img src="<c:url value="/static/common/images/imageColorTemp.png"/>" style="width:780px;"/>
					</div>
					<!--                         降雨量 -->
					<div class="weaact_con" id="con_sk_2" style="display: none;">
					<img  style="padding-left: 50px;"  src="/jeecms/resource/OCX/Rain/Plane/zhuhai${lastMin }_m.png">
					<img src="<c:url value="/static/common/images/imageColorRain.png"/>" style="width:780px;"/>
					</div>
					<!--                         湿度 -->
					<div class="weaact_con" id="con_sk_3" style="display: none;">
					<img style="padding-left: 50px;"  src="/jeecms/resource/OCX/Wind/Plane/zhuhai${lastMin }_m.png">
					<img src="<c:url value="/static/common/images/imageColorWind.png"/>" style="width:780px;"/>
					</div>
				</div>
			</div>

		</div>
		<div class="con_right clrta">
			<div class="con_typhoon">
				<h1 class="con_h1b">
					<a href="<c:url value="/web/qxjc/jrph"/>" style="color: #fff;float:left">监测数据排行</a>
					<ul class="conh1b_ul two_conul">
						<li id="two1" class="hover" onClick="setTab('two',1,4)"
							>最高温度</li>
						<li id="two2" onClick="setTab('two',2,4)"
							>最低温度</li>
						<li id="two3" onClick="setTab('two',3,4)"
							>雨量</li>
						<li id="two4" onClick="setTab('two',4,4)"
							>风力</li>
					</ul>
				</h1>
				<div id="con_two_1" class="con_two" style="overflow:hidden;">
					<table width="100%" border="0" cellspacing="0" cellpadding="0"
						class="tabel_1ist talistb">
						<tr>
							<th width="15%">排名</th>
							<th width="25%">站点名称</th>
							<th width="30%">站点数据</th>
							<th width="30%">监测时间</th>
						</tr>
						<#list zoneMaxTempSort as obj>
							<#if obj_index!=0>
								<#if obj_index%2==0>
									<tr>
										<td><span class="talistb_span tb1">${obj_index }</span>
										</td>
										<td>${obj.name }</td>
										<td>${obj.MaxTemp }</td>
										<td>${obj.time }</td>
									</tr>
								</#if>
								<#if obj_index%2!=0>
									<tr class="odd">
										<td><span class="talistb_span tb1">${obj_index }</span>
										</td>
										<td>${obj.name }</td>
										<td>${obj.MaxTemp }</td>
										<td>${obj.time }</td>
									</tr>
								</#if>
							</#if>
						</#list>
					</table>
				</div>
				<div id="con_two_2" class="con_two"
					style="overflow:hidden;display:none">
					<table width="100%" border="0" cellspacing="0" cellpadding="0"
						class="tabel_1ist talistb">
						<tr>
							<th width="15%">排名</th>
							<th width="25%">站点名称</th>
							<th width="30%">站点数据</th>
							<th width="30%">监测时间</th>
						</tr>
						<#list zoneMinTempSort as obj>
							<#if obj_index!=0>
								<#if obj_index%2==0>
									<tr>
										<td><span class="talistb_span tb1">${obj_index }</span>
										</td>
										<td>${obj.name }</td>
										<td>${obj.MinTemp }</td>
										<td>${obj.time }</td>
									</tr>
								</#if>
								<#if obj_index%2!=0>
									<tr class="odd">
										<td><span class="talistb_span tb1">${obj_index }</span>
										</td>
										<td>${obj.name }</td>
										<td>${obj.MinTemp }</td>
										<td>${obj.time }</td>
									</tr>
								</#if>
							</#if>
						</#list>
					</table>
				</div>
				<div id="con_two_3" class="con_two"
					style="overflow:hidden;display:none">
					<table width="100%" border="0" cellspacing="0" cellpadding="0"
						class="tabel_1ist talistb">
						<tr>
							<th width="15%">排名</th>
							<th width="25%">站点名称</th>
							<th width="30%">站点数据</th>
							<th width="30%">监测时间</th>
						</tr>
						<#list zoneRainSort as obj>
							<#if obj_index!=0>
								<#if obj_index%2==0>
									<tr>
										<td><span class="talistb_span tb1">${obj_index }</span>
										</td>
										<td>${obj.name }</td>
										<td>${obj.rain }</td>
										<td>${obj.time }</td>
									</tr>
								</#if>
								<#if obj_index%2!=0 >
									<tr class="odd">
										<td><span class="talistb_span tb1">${obj_index }</span>
										</td>
										<td>${obj.name }</td>
										<td>${obj.rain }</td>
										<td>${obj.time }</td>
									</tr>
								</#if>
							</#if>
						</#list>
					</table>
				</div>
				<div id="con_two_4" class="con_two"
					style="overflow:hidden;display:none">
					<table width="100%" border="0" cellspacing="0" cellpadding="0"
						class="tabel_1ist talistb">
						<tr>
							<th width="15%">排名</th>
							<th width="25%">站点名称</th>
							<th width="30%">站点数据</th>
							<th width="30%">监测时间</th>
						</tr>
						<#list zoneWindSort as obj>
							<#if obj_index!=0>
								<#if obj_index%2==0>
									<tr>
										<td><span class="talistb_span tb1">${obj_index }</span>
										</td>
										<td>${obj.name }</td>
										<td>${obj.windSpeed }</td>
										<td>${obj.time }</td>
									</tr>
								</#if>
								<#if obj_index%2!=0>
									<tr class="odd">
										<td><span class="talistb_span tb1">${obj_index }</span>
										</td>
										<td>${obj.name }</td>
										<td>${obj.windSpeed }</td>
										<td>${obj.time }</td>
									</tr>
								</#if>
							</#if>
						</#list>
					</table>
				</div>
			</div>
		</div>
		<div class="clearfloat"></div>
	</div>