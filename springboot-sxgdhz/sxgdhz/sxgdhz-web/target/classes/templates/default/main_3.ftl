<%@ page pageEncoding="UTF-8" %>
<#if warnState=='yes'>
	<div class="con_module" style="display: block;">
</#if>
<#if warnState=='no'>
	<div class="con_module" style="display: none;">
</#if>

<div class="con_left cllta">
	<div class="con_typhoon">
		<h1 class="con_h1a">
			<ul class="prediction_ul">
				<li id="yu1" class="btnact" onclick="javascript:setTab_sk('yu',1,3);">珠海天气预警</li>
				<li id="yu2" onclick="javascript:setTab_sk('yu',2,3);">澳门天气预警</li>
				<li id="yu3" onclick="javascript:setTab_sk('yu',3,3);">应急消息</li>
				<a target="_blank" href="http://14.215.217.67:8023"><li>地质灾害预警信息</li></a>
			</ul>
			<span class="spansa"> <a
				href="<c:url value="/web/yjfw/fqyj"/>">更多</a> </span>
		</h1>

		<div id="con_yu_1" class="aomen_con aomen1 aomena">
            <table style="padding-bottom: 100%;background-color:#e5f0f9;width:100%">
                <#if warnList1?? && (warnList1?size>0)>
                <#if (warnList1?size>= 3)>
                    <#list warnList1[0..2] as warn>
                        <tr>
                            <td>
                                <p class="p1" style="margin-top: 5px;border-right: 1px #888;margin-right: 5px;padding-right: 5px;">
                                    <img width="55" height="55"  src="<c:url value="/tufawarn/share/${warn.warnimg }"/>" style="display: inline-block" />
                                    <strong style="vertical-align: top;display: inline-block;width:310px;margin-left: 5px">
                                        ${warn.message?substring(warn.message?index_of('【'),warn.message?index_of('】')+1)}
                                    </strong>
                                </p>
                            </td>
                            <#if (warnList1?size >=4 && warn_index==0)>
                                    <td>
                                        <p class="p1" style="margin-top: 5px;border-right: 1px #888;margin-right: 5px;padding-right: 5px;">
                                            <img width="55" height="55"  src="<c:url value="/tufawarn/share/${warnList1[3].warnimg }"/>" style="display: inline-block" />
                                            <strong style="vertical-align: top;display: inline-block;width:310px;margin-left: 5px">
                                                ${warnList1[3].message?substring(warnList1[3].message?index_of('【'),warnList1[3].message?index_of('】')+1)}
                                            </strong>
                                        </p>
                                    </td>
                            <#elseif (warnList1?size >=5 && warn_index==1)>
                                    <td>
                                        <p class="p1" style="margin-top: 5px;border-right: 1px #888;margin-right: 5px;padding-right: 5px;">
                                            <img width="55" height="55"  src="<c:url value="/tufawarn/share/${warnList1[4].warnimg }"/>" style="display: inline-block" />
                                            <strong style="vertical-align: top;display: inline-block;width:310px;margin-left: 5px">
                                                ${warnList1[4].message?substring(warnList1[4].message?index_of('【'),warnList1[4].message?index_of('】')+1)}
                                            </strong>
                                        </p>
                                    </td>
                            <#elseif (warnList1?size >=6 && warn_index==2)>
                                    <td>
                                        <p class="p1" style="margin-top: 5px;border-right: 1px #888;margin-right: 5px;padding-right: 5px;">
                                            <img width="55" height="55"  src="<c:url value="/tufawarn/share/${warnList1[5].warnimg }"/>" style="display: inline-block" />
                                            <strong style="vertical-align: top;display: inline-block;width:310px;margin-left: 5px">
                                                ${warnList1[5].message?substring(warnList1[5].message?index_of('【'),warnList1[5].message?index_of('】')+1)}
                                            </strong>
                                        </p>
                                    </td>
                            <#else>
                                    <td></td>
                            </#if>
                        </tr>
                    </#list>
                <#else>
                    <#list warnList1 as warn>
                         <tr>
                            <td>
                                <p class="p1" style="margin-top: 5px;border-right: 1px #888;margin-right: 5px;padding-right: 5px;">
                                    <img width="55" height="55"  src="<c:url value="/tufawarn/share/${warn.warnimg }"/>" style="display: inline-block" />
                                    <strong style="vertical-align: top;display: inline-block;width:310px;margin-left: 5px">
                                        ${warn.message?substring(warn.message?index_of('【'),warn.message?index_of('】')+1)}
                                    </strong>
                                </p>
                            </td>
                         </tr>

                    </#list>
                </#if>
                <#else>
                <#--<#if (warnList1?size < 0) && warnList1 !??>-->
                    <tr>
                        <td>
                            <p class="p1">
                                <strong>当前无预警</strong>
                            </p>
                        </td>
                    </tr>
                </#if>
            </table>
            <a href="<c:url value="/web/yjfw/fqyj"/>">点此查看详情</a>
		</div>

		<div id="con_yu_2" class="aomen_con aomen2" style="display:none" >
            <a href="<c:url value="/web/yjfw/amyj"/>">
			<ul id="content2" style="padding-bottom: 20px">
				<#if aoWarn?? && (aoWarn?size>0)>
					<#list aoWarn as warn>
						<li><img width="50" height="50" src="${warn.icon }" />
                            <p>发布时间：${warn.time }</p>
							<p class="p1" style="margin-top: 40px">
                                <strong>${warn.desc }</strong>
							</p>
						</li>
					</#list>
				<#else>
					<li>
						<p class="p1">
							<strong>当前无预警</strong>
						</p>
					</li>
				</#if>
			</ul>
			</a>
		</div>
		<div id="con_yu_3" class="aomen_con aomen3 aomena" style="display:none"  >
            <a href="<c:url value="/web/yjfw/qxyj"/>">
			<ul id="content3" style="padding-bottom: 20px">
				<#if warnList2?? && (warnList2?size>0)>
					<#list warnList2 as warn>
						<li>
                            <img width="50" height="50"
							src="<c:url value="/tufawarn/share/${warn.warnimg }"/>" />
							<p class="p1" style="margin-top: 65px">
                                <strong>${warn.message }</strong>
							</p>
                        </li>
					</#list>
				<#else>
					<li>
						<p class="p1">
							<strong>当前无应急消息</strong>
						</p></li>
				</#if>
			</ul>
			</a>
		</div>
	</div>
</div>
<div class="con_right clrta">
			<div class="con_typhoon">
				<h1 class="con_h1c">
					<ul class="conh1c_ul">
						<li id="one1" class="hover" onclick="toone1()" style="color: #fff;float:left">预警信号相关法律法规</li>
					</ul>
				</h1>
				<div id="con_one_1" class="defense_con" style="display:block;">
                    <ul class="list_ul">
                        <li><img src="<c:url value="/static/common/images/conlist6.png"/>">
                            <a title="广东省气象管理规定" href="<c:url value="/f/con_82_304"/>"  style="color: ;">珠海市突发事件预警信息发布管理办法</a>
                            </li>
                        <li><img src="<c:url value="/static/common/images/conlist6.png"/>">
                            <a title="广东省气象管理规定" href="<c:url value="/f/con_82_83"/>"  style="color: ;">珠海市防御气象灾害规定</a>
                        </li>
                        <li><img src="<c:url value="/static/common/images/conlist6.png"/>">
                            <a title="广东省气象灾害防御条例" href="<c:url value="/f/con_81_82"/>"  style="color: ;">广东省气象灾害防御条例</a>
                        </li>
                        <li><img src="<c:url value="/static/common/images/conlist6.png"/>">
                            <a title="广东省突发气象灾害预警信号发布规定" href="<c:url value="/f/con_81_81"/>"  style="color: ;">广东省突发气象灾害预警信号发布规定</a>
                        </li>
                        <li><img src="<c:url value="/static/common/images/conlist6.png"/>">
                            <a title="广东省珠江三角洲大气污染防治办法" href="<c:url value="/f/con_81_431"/>"  style="color: ;">广东省气象灾害防御重点单位气象安全管理办法</a>
                        </li>
                        <li><img src="<c:url value="/static/common/images/conlist6.png"/>">
                            <a title="广东省气象管理规定" href="<c:url value="/f/con_80_74"/>"  style="color: ;">气象灾害防御条例</a>
                        </li>
                        <li><img src="<c:url value="/static/common/images/conlist6.png"/>">
                            <a title="广东省气象管理规定" href="<c:url value="/f/con_79_68"/>"  style="color: ;">中华人民共和国气象法</a>
                        </li>
                        <li><img src="<c:url value="/static/common/images/conlist6.png"/>">
                            <a title="广东省气象管理规定" href="<c:url value="/f/con_83_84"/>"  style="color: ;">气象灾害预警信号发布与传播办法</a>
                        </li>
                    </ul>
				</div>
			</div>
		</div>
		<div class="clearfloat"></div>
</div>