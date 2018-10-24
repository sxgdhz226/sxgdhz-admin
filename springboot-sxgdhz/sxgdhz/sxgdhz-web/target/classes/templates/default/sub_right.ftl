<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>珠海气象局(台)</title>
<link href="<c:url value="/static/common/css/General.css"/>" type="text/css" rel="stylesheet"/>
<link href="<c:url value="/static/common/css/secondarys.css"/>" type="text/css" rel="stylesheet"/>
<link ID="skin" href="<c:url value="/static/common/css/oranges.css"/>" type="text/css" rel="stylesheet"/>
<script src="<c:url value="/static/common/js/jquery-1.11.1.min.js"/>" type="text/javascript"></script>
<script type="text/javascript" src="<c:url value="/static/common/js/skin_change.js"/>"></script>
<script type="text/javascript">
$(document).ready(function(){
	var hoverMenu = '${menu}';
  	$(".navContent").children().each(function(i){
	   		var obj=$("#submenu_"+i);
	   		if(obj.attr("name")==hoverMenu){
   			obj.addClass("hover");
   		}else{
   			obj.removeClass("hover");
   		}
	});
});
</script>
</head>
<body>
<div class="con_con">
    	<%@include file="../head_new.jsp" %>
        <div class="contenters">
        <%@include file="../tqjb.jsp" %>
        	<div class="con_left clltc">
        	<!--   父key_menu -->
               <%@include file="kpfw_menu.jsp" %>
            </div>
<div class="con_right clrtc alrtc_bcg">
	<div class="con_position">
        <span><img src="/jeecms/static/common/images/positions.png">当前位置：首页 &gt; {pName} &gt; {name}</span>
    </div>
	<!--  内容 -->
 </div>
 </div>
<!--         脚部引用 -->
<%@include file="../foot.jsp" %>
<!--        结束 -->
    </div> 
</body>
</html>