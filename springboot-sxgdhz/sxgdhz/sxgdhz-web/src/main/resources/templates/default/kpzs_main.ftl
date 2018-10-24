<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<h1 class="con_h1b">
<span style="float:left;font-size:16px;color:#fff;font-weight:bold">气象科普</span>
<a href="<c:url value="/web/kpfw"/>" class="amore">更多</a></h1>
                    <div class="polular_con">
                        <div class="con_dis">
                            <p class="conleft_p">
                                <span>气象百科</span>
                            </p>
                            <div class="conleft_img">
                                <a href="#"><img class="imgs" src="<c:url value='/userfiles/images/bk_default.jpg'/>"></a>
                                <ul class="list_ul">
	                                <#list bklist as obj>
	                                	<#if obj_index <8 >
                                    		<li><img src="<c:url value="/static/common/images/conlist5.png"/>"><a title="${obj.title}" href="<c:url value="/web/content?id=${obj.id}"/>">${obj.title}</a></li>
	                                	</#if>
	                                </#list>
                                </ul>                                
                            </div>
                        </div>
                        <div class="con_dis">
                            <p class="conleft_p">
                                <span>气象灾害</span>
                            </p>
                            <div class="conleft_img">
                                <a href="#"><img class="imgs" src="<c:url value='/userfiles/images/zh_default.jpg'/>"></a>
                                <ul class="list_ul">
                                	<#list zhlist as obj>
	                                	<#if obj_index <8 >
	                                    	<li><img src="<c:url value="/static/common/images/conlist5.png"/>"><a title="${obj.title}" href="<c:url value="/web/content?id=${obj.id}"/>">${obj.title}</a></li>
	                                	</#if>
	                                </#list>
                                </ul>                                               
                            </div>
                        </div>
                    </div>