<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- 天气实景模板 -->
<ul id="ifocus_img">
	<#list res as obj>
			<li><a href="<c:url value="/web/qxjc/tqsj"/>" target="_blank">
				<img width="328px" height="221px" src="http://www.zhmb.gov.cn/jeecms/static/common/images/${obj.description}_m.jpg" />
				<p>${obj.content }</p> </a>
			</li>
		
	</#list>
</ul>
<ul id="ifocus_tabs" style="text-align: center;">
	<#list res as obj>
			<li></li>
	</#list>
</ul>