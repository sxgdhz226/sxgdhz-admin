<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<table border="0" cellspacing="0" cellpadding="0">
	<tr align="center">
		
		<#list res as obj>
			<td><a href="<c:url value="/web/qxst"/>"
			target="_blank"> <img
				src="http://www.zhmb.gov.cn:8000/jeecms/static/common/images/${obj.content }"
				width="170" height="115" > </a></td>
		</#list>
	</tr>
</table>