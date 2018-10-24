<%@ page pageEncoding="UTF-8" %>
<div class="con_module">
		<div class="con_left cllta">
			<div class="con_typhoon">
				<h1 class="con_h1a">
					<ul class="prediction_ul">
					   <li id="fq1" class="btnact" onclick="javascript:setTab('fq',1,2);">分区要素展示</li>
					   <li id="fq2"  onclick="javascript:setTab('fq',2,2);">要素空间分布展示</li>
					</ul>
					 <span
						class="spansa">${skZoneMap.publishTime.publishTime }发布<a
						href="<c:url value="/web/qxjc/jyfb"/>"  target="_blank" >更多</a> </span>
				</h1>
				<div class="weahters_con" id="con_fq_1">
					<div class="map_a" style="margin-top: 46px;">
						<p id="p1">
							<a id="ec1" href="javascript:setTab_sk('ec',1,3);" class="btnact"
								><img
								src="<c:url value="/static/common/images/btn_1.png"/>">气温</a>
						</p>
						<p id="p2">
							<a id="ec2" href="javascript:setTab_sk('ec',2,3);"
								><img
								src="<c:url value="/static/common/images/btn_2.png"/>">降雨量</a>
						</p>
						<p id="p3">
							<a id="ec3" href="javascript:setTab_sk('ec',3,3);"
								><img
								src="<c:url value="/static/common/images/btn_3.png"/>">风力风向</a>
						</p>

					</div>
					
					<div id="con_ec_1">
						
                       	<div class="distribution">
                    <div class="distribution_img">
	                    <div style="width: 100%;text-align: left;padding-top: 10px;" id="zone">
		                    	 <form id="myform"> 
			                    	<input  type="radio" value="xz" id="qwqx1" onclick="javascript:setTab('qwqx',1,4);" name="type" ><label class="zonelable" onclick="javascript:setTab('qwqx',1,4);" id="xz">香洲</label>
			                    	<input  type="radio" value="dm" id="qwqx2" onclick="javascript:setTab('qwqx',2,4);" name="type" ><label class="zonelable" onclick="javascript:setTab('qwqx',2,4);" id="dm">斗门</label>
			                    	<input  type="radio" value="jw" id="qwqx3" onclick="javascript:setTab('qwqx',3,4);" name="type" ><label class="zonelable" onclick="javascript:setTab('qwqx',3,4);" id="jw">金湾</label>
			                    	<input  type="radio" value="hq" id="qwqx4" onclick="javascript:setTab('qwqx',4,4);" name="type" ><label class="zonelable" onclick="javascript:setTab('qwqx',4,4);" id="hq">横琴</label>
		                    	</form> 
	                    	</div>
	                     <#assign display="${display}"></#assign>
	                     <#if display=='hidden'>
	                    <div style="height:308px;width: 673px;display:none">数据维护中...</div>
	                    <div id="con_qwqx_1"  style="display:none"></div>
	                    </#if>
	                     <#if display=='show'>
                    	<div id="con_qwqx_1"  style="height:308px;width: 673px;"></div>
                    	<div id="con_qwqx_2"  style="height:308px;width: 673px;display:none;"></div>
                    	<div id="con_qwqx_3"  style="height:308px;width: 673px;display:none;"></div>
                    	<div id="con_qwqx_4"  style="height:308px;width: 673px;display:none;"></div>
                    	</#if>
                    </div>
                    <div style="padding-left: 0%;font-size:15px;">
                     <font style="font-size: 17px;font-weight: bold;color: #C00;">说明：</font>
						
							<b>气温曲线展示香洲区、斗门区、金湾区、横琴新区等区域气象气象自动监测代表站点当天逐小时的气温变化情况，用户可根据自己的所在区域选择查看代表站点的气温变化情况。</b>
                     </div>
                </div> 
					</div>
					
					
					
					<!--                         降雨量 -->
					<div id="con_ec_2" style="display: none;" >
					<div class="distribution">
                    <div class="distribution_img">
                    	<div style="width: 100%;text-align: left;padding-top: 10px;">
	                    	<form id="myform1">
		                    	<input id="jylqx1" onclick="javascript:setTab('jylqx',1,4);" type="radio" value="xz1" name="station"><label class="zonelable" onclick="javascript:setTab('jylqx',1,4);"  id="xz1">香洲</label>
		                    	<input id="jylqx2" onclick="javascript:setTab('jylqx',2,4);" type="radio" value="dm1" name="station"><label class="zonelable" onclick="javascript:setTab('jylqx',2,4);"  id="dm1">斗门</label>
		                    	<input id="jylqx3" onclick="javascript:setTab('jylqx',3,4);" type="radio" value="jw1" name="station"><label class="zonelable" onclick="javascript:setTab('jylqx',3,4);"  id="jw1">金湾</label>
		                    	<input id="jylqx4" onclick="javascript:setTab('jylqx',4,4);" type="radio" value="hq1" name="station"><label class="zonelable" onclick="javascript:setTab('jylqx',4,4);"  id="hq1">横琴</label>
	                    	</form>
                    	</div>
                    	<#assign display1="${display1}">
                    	 <#if display1=='hidden'>
	                    <div style="height:308px;width: 673px;">数据维护中...</div>
	                    <div id="con_jylqx_1"  style="display:none"></div>
	                    </#if>
	                     <#if display1=='show'>
                    	<div id="con_jylqx_1"  style="height:308px;width: 673px;"></div>
                    	<div id="con_jylqx_2"  style="height:308px;width: 673px;display:none;"></div>
                    	<div id="con_jylqx_3"  style="height:308px;width: 673px;display:none;"></div>
                    	<div id="con_jylqx_4"  style="height:308px;width: 673px;display:none;"></div>
                    	</#if>
                    	<div style="padding-left: 0%;font-size:15px;">
                     <font style="font-size: 17px;font-weight: bold;color: #C00;">说明：</font>
							
							<b>雨量柱状图展示香洲区、斗门区、金湾区、横琴新区等区域气象气象自动监测代表站点当天逐小时降雨量变化情况，每条雨量柱表示一小时的降雨量，比如横轴坐标00与01之间的雨量柱表示00:00-00:59的降雨量，01与02之间的雨量柱表示01:00-01:59的降雨量，依此类推。用户可根据自己的所在区域选择查看代表站点的降雨量变化情况。</b>
							<br><br>
                     </div>
                    </div>
                    
                </div>  

					</div>
					
					
					
					<!-- 风力风向图 start -->
					<div id="con_ec_3" style="display: none;" >
					    <div class="distribution_img">
			                  <div class="distribution_img">
			                    <div style="width: 100%;text-align: left;padding-top: 10px;" id="zone">
					                    	<input onclick="wdwfDate('59488','香洲')" checked="checked" type="radio"   name="type" ><label class="zonelable"  onclick="javascript:setTab('jylqx',1,4);" id="xz">香洲</label>
					                    	<input onclick="wdwfDate('59487','斗门')" type="radio"   name="type" ><label class="zonelable" onclick="javascript:setTab('jylqx',2,4);" id="dm1">斗门</label>
					                    	<input onclick="wdwfDate('58265','金湾')" type="radio"   name="type" ><label class="zonelable" onclick="javascript:setTab('jylqx',3,4);" id="jw1">金湾</label>
					                    	<input onclick="wdwfDate('G3850','横琴')" type="radio"   name="type" ><label class="zonelable" onclick="javascript:setTab('jylqx',4,4);" id="hq1">横琴</label>
			                    </div>
			                    <#assign display2="${display2}">
			                     <#if display2=='hidden'>
			                    <div style="height:580px;width: 970px;display:none">数据维护中...</div>
			                    <div id="fxfsdiv"  style="display:none"></div>
			                    </#if>
			                     <#if display2=='show'>
				                   <div id="fxfsdiv" style="height:308px;width:673px;"></div>
				                    <div style="padding-left: 0%;font-size:18px;">
				                    <font style="font-size: 17px;font-weight: bold;color: #C00;">说明：</font>
											<b>风向风速图展示香洲区、斗门区、金湾区、横琴新区等区域气象自动监测代表站点当天逐小时
											的风向风速变化情况。<br>气象上把风吹来的方向叫做风向，通常用风向杆表示，有风羽（或风三角）的
											一端指向风的来向。用户可根据自己的所在区域选择查看代表站点的风向风速变化情况。</b>
				                    </div>
		                    	</#if> 
		                    </div>
					</div>
					<!--  风力风向图 end -->

				</div>
				<div class="weahters_con" style="background-color: #F8F8F8;display: none;" id="con_fq_2">
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
						<img style="padding-left: 50px;"  src="http://www.zhmb.gov.cn:8000/jeecms/resource/OCX/Temp/Plane/zhuhai${lastMin }_m.png">
						<img src="<c:url value="/static/common/images/imageColorTemp.png"/>" style="width:780px;"/>
					</div>
					<!--                         降雨量 -->
					<div class="weaact_con" id="con_sk_2" style="display: none;">
					<img  style="padding-left: 50px;"  src="http://www.zhmb.gov.cn:8000/jeecms/resource/OCX/Rain/Plane/zhuhai${lastMin }_m.png">
					<img src="<c:url value="/static/common/images/imageColorRain.png"/>" style="width:780px;"/>
					</div>
					<!--                         湿度 -->
					<div class="weaact_con" id="con_sk_3" style="display: none;">
					<img style="padding-left: 50px;"  src="http://www.zhmb.gov.cn:8000/jeecms/resource/OCX/Wind/Plane/zhuhai${lastMin }_m.png">
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