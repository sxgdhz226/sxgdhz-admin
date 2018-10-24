<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script type="text/javascript">
function nofind(imgurl){

var img=event.srcElement;

img.src=imgurl;

img.onerror=null; //控制不要一直跳动

}

</script>
<h1 class="con_h1c">
		<ul class="conh1c_ul">
			<li id="fourrs2"  class="hover" onclick="setTab('fourrs',2,2)">工作动态</li>
			<li id="fourrs1" onclick="setTab('fourrs',1,2)">气象文化</li>
			
		</ul>
		<a href="<c:url value="/web/menu?method=kpfw_qxwh"/>" class="amore">更多</a>
</h1>
   <div id="con_fourrs_1" class="culture_con" style="display:none">
        <#list result as obj>
        	<#if obj_index < 4>
	        	<div  style="padding:11px 19px">
		            <p style="width:150px;height:20px;overflow: hidden;text-overflow: ellipsis;white-space: nowrap;"><a title="${obj.title }" href="<c:url value="/web/content?id=${obj.id }"/>">${obj.title }</a></p>
	        	</div>  
        	</#if>
        </#list>     
    </div>
     <div id="con_fourrs_2" class="culture_con" style="display:block">
        <#list resultt as obj>
        	<#if obj_index < 4>
	        	<div  style="padding:11px 19px">
		            <p style="width:150px;height:20px;overflow: hidden;text-overflow: ellipsis;white-space: nowrap;"><a title="${obj.title }" href="<c:url value="/web/content?id=${obj.id }"/>">${obj.title }</a></p>
	        	</div>  
        	</#if>
        </#list>     
    </div>