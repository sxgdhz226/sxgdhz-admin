<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
        <div class="con_position">
		       <span><img src="<c:url value="/static/common/images/positions.png"/>">当前位置：首页 &gt; ${menuName} &gt; ${subMenuName}</span>
		</div> 
		<h1 style="text-align:center;font-size:20px;padding-top:10px;padding-bottom:10px;">${title}</h1>
                ${content}