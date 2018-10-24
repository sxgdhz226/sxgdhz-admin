<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<div class="con_position">
    <span><img src="<c:url value="/static/common/images/positions.png"/>">当前位置：首页 &gt; ${menuName} &gt; ${subMenuName}</span>
</div> 
 <div class="governmentopen">
	<ul>
        <#list rightTopList as obj>
        	<#if obj_index+1 == 1>
        		<li id="ten${obj_index+1}" onclick="setTab('ten',${obj_index+1},${count})"  class="hover">${obj.name}</li>
	        </#if>
	        <#if obj_index+1 != 1>
	        	<li id="ten${obj_index+1}" onclick="setTab('ten',${obj_index+1},${count})">${obj.name}</li>
	        </#if>
        </#list>
        
    </ul>
</div>
<#list rightList as obj>
	<#if obj_index+1 == 1>
		<div id="con_ten_${obj_index+1}" class="governmentopen_ul" style="display:block"> 
			
				<ul class="list_ul">
						<#list obj as artic>
							<#if artic_index <15>
								<li><img src="<c:url value="/static/common/images/conlist6.png"/>">
						        <a title="${artic.title}" href="<c:url value="/f/${artic.fileName!'#'}"/>"  style="color: ${artic.color};">${artic.title}</a>
						        <span class="spana">${artic.createDate}</span></li>
					        <#else>
					        	<li>
					        	<a></a>
						        <span class="spana"><b><a href="<c:url value='/web/search?flag=${artic.category.id}'/>" style="width:50px;">更多>></a></b></span></li>
						        <#break>
					        </#if>
						</#list>
				    </ul>
			
		</div>
    </#if>
    <#if obj_index+1 != 1>
		<div id="con_ten_${obj_index+1}" class="governmentopen_ul">
			
				<ul class="list_ul">
			        <#list obj as artic>
			        	<#if artic_index <15 >
							<li><img src="<c:url value="/static/common/images/conlist6.png"/>">
					        <a title="${artic.title}" href="<c:url value="/f/${artic.fileName!'#'}"/>"  style="color: ${artic.color};">${artic.title}</a>
					        <span class="spana">${artic.createDate}</span></li>
					    <#else>
				        	<li>
				        	<a></a>
					        <span class="spana"><a href="<c:url value='/web/search?flag=${artic.category.id}'/>" style="width:50px;"><b>更多>></b></a></span></li>
					        <#break>
				        </#if>
					</#list>
			    </ul>
			
		</div>
    </#if>
</#list>
</div>