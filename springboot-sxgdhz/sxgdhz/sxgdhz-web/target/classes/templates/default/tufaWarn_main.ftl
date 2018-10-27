<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<script type="text/javascript">
function nofind(imgurl){

var img=event.srcElement;

img.src=imgurl;

img.onerror=null; //控制不要一直跳动

}
</script>
<#if (result?size > 0)>
<#list result as obj>
	<div class="titles">
	       <img width="50px" height="50px" src="<c:url value="/tufawarn/share/${obj.warnimg}"/>" onerror="nofind('http://www.zhmb.gov.cn:8000/jeecms/tufawarn/share/${obj.warnimg}')">
	    <#--<p class="fontyellow h1">${obj.title}</p>-->
	    <#--<p>发布时间：${obj.warnTime}</p>-->
	    <#--<p>状态：<span class="fontred">生效中</span></p>                                 -->
	</div>
<br/><br/><br/>
	<!--p class="p1 fontred">含义：</p-->
	<p class="p2">${obj.hy}</p>
	<!--p class="p1 fontred">防御指引：</p-->
	<!--p class="p2"></p-->

</#list>
<#else>
	<div class="titles">
	    <p class="fontyellow h1">珠海市未发布突发事件预警信号</p>                              
	</div>
</#if>
                            