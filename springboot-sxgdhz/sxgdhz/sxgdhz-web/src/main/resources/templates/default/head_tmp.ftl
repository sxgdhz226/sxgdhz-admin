<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<style>
.con_alert,.con_prediction,.con_typhoon,.con_img{overflow:hidden;margin-top:15px;position:relative}
.con_alert{padding-left:80px;padding-right:15px;overflow:hidden;word-wrap: break-word;line-height: 25px;padding-top: 8px;height:55px;}
.con_alert img{margin-right: 6px;vertical-align: middle;}
</style>
	<div class="con_con">
		<div class="con_notice">
        	<%@include file="head_title.jsp" %>
            <div>
            	<a href="#" onclick="changecss('<c:url value="/static/common/css/blues.css"/>')" class="skins blueskin"></a> 
                <a href="#" onclick="changecss('<c:url value="/static/common/css/oranges.css"/>')" class="skins orangeskin"></a>
                <a href="#" onclick="changecss('<c:url value="/static/common/css/greens.css"/>')" class="skins greenskin"></a> 
                <a href="#" onclick="changecss('<c:url value="/static/common/css/grays.css"/>')" class="skins grayskin"></a>
                <a href="<c:url value='/web'/>"><img src="<c:url value="/static/common/images/noticehome.png"/>"/>&nbsp;网站首页</a>
                <a href="javascript:addFavorite2();"  rel="sidebar" ><img src="<c:url value="/static/common/images/noticehouse.png"/>"/>&nbsp;加入收藏</a>              
            </div>            
        </div>
        <div class="con_header">
        	<div class="logos"></div>
        </div>
        <div class="con_nav">
            <ul class="menu_ul">
            <#list categoryList as obj>
	        	<#if obj.sort == 1>
	        		<li id="menu_${obj_index+1}" class="hover" >
	        		<input type="hidden" value="${obj.keywords}" id="menu_hi_${obj_index+1}">
	        			<a  href="<c:url value='${obj.href}'/>" >${obj.name}</a>
	        		</li>
	        	</#if>
				<#if obj.sort != 1>
	        		<li id="menu_${obj_index+1}" >
	        		<input type="hidden" value="${obj.keywords}" id="menu_hi_${obj_index+1}">
	        			<a  href="<c:url value='${obj.href}'/>" >${obj.name}</a>
	        		</li>
	        	</#if>
			</#list>
            </ul>
        </div>
        <div class="clearfloat"></div>
        <div class="con_announcement"><form action="<c:url value="/web/search"/>" id="sarchForm" method="post">
        	<#if quickList_size!=0>
			<span>&nbsp;快速通道：</span>
			<span style='color:#00FF00'>  
			<a target="_blank"  href="<c:url value='http://www.zhmb.gov.cn/jeecms/web/content?id=449'/>" >巡查公告</a> |
			<a target="_blank"  href="<c:url value='http://www.zhmb.gov.cn/jeecms/web/content?id=450'/>" >市委第一巡察组进驻市气象局开展巡察工作</a> |
				<#list quickList as obj>
					
					<a target="_blank"  href="<c:url value='${obj.href}'/>" >${obj.name}</a> 
					<#if quickList_size!=obj_index+1>
						|
					</#if>
				</#list>
				|<a target="_blank"  href="http://wza.zhuhai.gov.cn/wza/yixuan/yixuan_head/index.html?dh" >信息无障碍版</a> 
				
			</span> 
		</#if>
	        	<div class="search_rt" style="display:inline;">
	            	站内搜索<input type="text" name="search"/><a href="javascript:subSearch()"><img src="<c:url value="/static/common/images/search_btn.png"/>"/></a>
	            </div>
			</form>
        </div>     
<script>
	function subSearch(){
		$("#sarchForm").submit();
	}
</script>
