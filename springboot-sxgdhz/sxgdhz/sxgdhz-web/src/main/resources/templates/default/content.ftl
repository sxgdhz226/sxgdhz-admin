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
<link ID="skin" href="<c:url value="/static/common/css/blues.css"/>" type="text/css" rel="stylesheet"/>
<link href="<c:url value="/static/cd/sq/css/second.css"/>" type="text/css" rel="stylesheet" />
<script src="<c:url value="/static/common/js/jquery-1.11.1.min.js"/>" type="text/javascript"></script>
<script type="text/javascript" src="<c:url value="/static/common/js/skin_change.js"/>"></script>
</head>
<script type="text/javascript">
function nofind(imgurl){

var img=event.srcElement;

img.src=imgurl;

img.onerror=null; //控制不要一直跳动

}

</script>
<body>
	<div class="con_con">
<%@include file="../head_new.jsp" %>
        	<div class="list_middle">
        	<div class="infor_tt" style="colro:${article.color}">${article.title}</div>
            <div class="infor_oth">
               <span class="infor_date">加入时间：${article.createDate}</span>
               <!-- <span class="infor_pers">发布人：${article.createBy.name} </span>-->
               <!--<span class="infor_count">点击：${article.hits}</span> -->
            </div>
            <div class="infor_ct">
            
            	${article.articleData.content}
            </div>
        </div>
<!--         脚部引用 -->
<%@include file="../foot.jsp" %>
<!--        结束 -->
    </div>
    
    <script type="text/javascript" src="<c:url value="/static/common/js/jquery-latest.js"/>"></script>
<script type="text/javascript" src="<c:url value="/static/common/js/slides.min.jquery.js"/>"></script>
<script type="text/javascript">
	$(function(){
		$('#slides').slides({
			effect: 'slide'
		});
	})
</script>	
</body>
</html>
