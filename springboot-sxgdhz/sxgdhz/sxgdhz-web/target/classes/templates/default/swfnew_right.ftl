
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
        <div class="con_position">
		       <span><img src="<c:url value="/static/common/images/positions.png"/>">当前位置：首页 &gt; ${menuName} &gt; ${subMenuName}</span>
		</div> 
       <div class="con_video" > 
                <p class="p1">${article.title} </p>
                <p class="p2"><span>发布时间：${article.createDate}</span><span>发布单位：珠海气象局</span></p>
               <div class="con_video" id="con_video_1"> 
	              <div class="con_video"> 
                    <p class="p2">
                    <span></span></p>
                    <div class="videos" id="FlvContainer">
                    	<embed type="application/x-shockwave-flash" 
                    	src="<c:url value='/static/flash/player.swf'/>" style="undefined" id="ply" 
                    	name="ply" bgcolor="#FFFFFF" quality="high" 
                    	allowfullscreen="true" allowscriptaccess="always" 
                    	flashvars="file=<c:url value='/userfiles/swf/${swf}.mp4&amp;autostart=true'/>" 
                    	height="483" width="680">
                    </div>
                    <script type="text/javascript" src="<c:url value="/static/js/swfobject.js"/>"></script>
                  </div> 
                </div>
         </div>
