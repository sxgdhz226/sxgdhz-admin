<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
		<div class="con_position">
		       <span><img src="<c:url value="/static/common/images/positions.png"/>">当前位置：首页 &gt; ${menuName} &gt; ${subMenuName}</span>
		</div>           	
		<ul class="list_ul">
	    	<#list subMenuList as obj>
	    		<#if obj_index <15 >
	    			<li>
		    			<img src="<c:url value="/static/common/images/conlist6.png"/>">
			    		<a title="${obj.title}" href="<c:url value="/f/${obj.fileName!'#'}"/>"  style="color: ${obj.color};">
				    		${obj.title}
				    	</a>
				    	<span class="spana">${obj.createDate}</span>
				    </li>
			    <#else >
				    <li>
			    			<img src="<c:url value="/static/common/images/conlist6.png"/>">
				    		
					    	<span class="spana"><a style="width:50px;" href="<c:url value='/web/search?flag=${article.category.id}'/>"><b>更多</b></a></span></li>
				 </#if>
	    	</#list>
	    </ul>

