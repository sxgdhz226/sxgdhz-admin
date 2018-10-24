<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib-new.jsp"%>

<#if (result?size > 0)>
	<#list result as obj>
		<div class="titles">
		       <img width="50px" height="50px" src="<c:url value="/tufawarn/share/${obj.warnimg}"/>">
		    <#--<p class="fontyellow h1">${obj.title}</p>-->
		    <#--<p>发布时间：${obj.warnTime}</p>-->
		    <#--<p>状态：<span class="fontred">生效中</span></p>                                 -->
		</div>
	<br/>
		<p class="p2">${obj.hy}</p>
	</#list>
<#else>
<div class="titles">
    <p class="fontyellow h1">珠海市未发布天气预警信号</p>                              
</div>
</#if>

