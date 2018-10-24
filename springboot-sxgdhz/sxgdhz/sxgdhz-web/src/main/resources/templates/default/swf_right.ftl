<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
        <div class="con_position">
		       <span><img src="<c:url value="/static/common/images/positions.png"/>">当前位置：首页 &gt; ${menuName} &gt; ${subMenuName}</span>
		</div> 
       <div class="con_video" > 
                <p class="p1">${article.title} </p>
                <p class="p2"><span>发布时间：${article.createDate}</span><span>发布单位：珠海气象局</span></p>
                <div class="videos" onclick="newvideoHit()">
				 <video autoplay="autoplay" controls="controls" height="600" width="700">
	                <source src="<c:url value="/userfiles/swf/${swf}.mp4"/>" type="video/mp4">
	            </video>
                </div>
            </div>