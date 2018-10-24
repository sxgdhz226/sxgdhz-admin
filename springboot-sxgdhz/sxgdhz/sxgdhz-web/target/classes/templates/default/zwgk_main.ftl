<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<h1 class="con_h1c">
                        <ul class="conh1c_ul">
                         <li id="three3" class="hover" onClick="setTab('three',3,3)">工作动态</li>
			             <li id="three2"  onClick="setTab('three',2,3)">行政法规</li>
                         <li id="three1" onClick="setTab('three',1,3)">法律</li>
                        <!-- <li id="three3" onClick="setTab('three',3,3)">地方性法规</li>-->
                        </ul><a href="<c:url value="/web/zwgk/zcfg"/>" class="amore">更多</a>
                    </h1>
                    <div id="con_three_1" class="attitude_con">
                        <ul class="list_ul">
                        <#list tdlist as obj>
                        	<#if obj_index <8 >
                        		<li><img src="<c:url value="/static/common/images/conlist5.png"/>"><a title="${obj.title}" href="<c:url value="/web/content?id=${obj.id}"/>">${obj.title}</a></li>
                        	</#if>
                        </#list>
                        </ul>
                    </div>
                    <div id="con_three_2" class="attitude_con" >
                        <ul class="list_ul">
                            <#list xwlist as obj>
	                            <#if obj_index <8 >
	                        		<li><img src="<c:url value="/static/common/images/conlist5.png"/>"><a title="${obj.title}" href="<c:url value="/web/content?id=${obj.id}"/>">${obj.title}</a></li>
	                        	</#if>
                        	</#list>
                        </ul>
                    </div>
                    <div id="con_three_3" class="attitude_con" style="display:block">
                        <ul class="list_ul">
                        <#list gzlist as obj>
                        	<#if obj_index <8 >
                        		<li><img src="<c:url value="/static/common/images/conlist5.png"/>"><a title="${obj.title}" href="<c:url value="/web/content?id=${obj.id}"/>">${obj.title}</a></li>
                        	</#if>
                        </#list>
                        </ul>
                    </div>
                <!--    <div id="con_three_3" class="attitude_con" >
                        <ul class="list_ul">
                            <#list fglist as obj>
	                            <#if obj_index <8 >
		                        	<li><img src="<c:url value="/static/common/images/conlist5.png"/>"><a title="${obj.title}" href="<c:url value="/web/content?id=${obj.id}"/>">${obj.title}</a></li>
	                        	</#if>
                        </#list>
                        </ul>
                    </div> -->