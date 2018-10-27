<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<div class="con_image">
                    <span>${menuName}</span>
                </div>
                <ul class="navContent">
<#list menuList as obj>
<li id="submenu_${obj_index }" name="${obj.keywords }">
		<a href="<c:url value="${obj.href}"/>" target="${obj.target}">
			<span>&nbsp;</span>
				<strong>${obj.name }</strong>
				<small>&gt;&gt;</small>
		</a>
</li>
</#list>
                </ul>
