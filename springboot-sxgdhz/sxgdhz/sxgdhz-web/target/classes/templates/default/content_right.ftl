<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
        <div class="con_position">
		       <span><img src="<c:url value="/static/common/images/positions.png"/>">当前位置：首页 &gt; ${menuName} &gt; ${subMenuName}</span>
		</div> 
                ${content}