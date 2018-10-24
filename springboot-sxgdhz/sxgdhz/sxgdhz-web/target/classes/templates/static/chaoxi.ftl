<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<style>
.tideTable {
	padding: 0px 0px 0px 0px;
text-align: center;
}

.tideTable strong {
	color: #CB4B2D;
	font-size: 14px;
	font-weight: bold;
	padding: 0px 18px 0px 28px;
	height: 20px;
	line-height: 20px;
	display: inline-block;
	margin-right: 18px;
}

.tideTable span {
	font-weight: bold;
}

.tideTable table {
	border: 1px solid #FFFFFF;
	border-collapse: collapse;
	background: #D8EAF8;
	color: #4D4D4D;
	margin: 10px auto;
}

.tideTable table td{
	border: 1px solid #FFFFFF;
	empty-cells:show;
	vertical-align: middle;
	text-align: left;
	height: 30px;
}

.tideTable table td p{
	padding-left: 12px;
}

.tideTable h2{
	color: #4D4D4D;
	font-size: 12px;
	text-align: center;
	line-height: 28px;
	font-size: 14px;
}

</style>
<h1 class="con_h1b"><a href="<c:url value="/web/ybfw/cxyb"/>" style="color: #fff;float:left">天文潮预报</a>
<span class="spanrt">${dateTime}发布</span></h1>
<div class="tideTable"><strong>天文潮汐表 (珠海香洲港)</strong>
<table  border="0" cellspacing="0" cellpadding="0">                      
<tr>                        
<td width="100"><p><span>潮时 (Hrs)</span></p></td>                                                
<td width="100"><p>${dateMap.time1}</p></td>             
<td width="100"><p>${dateMap.time2}</p></td>                                                
<td width="100"><p>${dateMap.time3}</p></td>                                                
<td width="100"><p>${dateMap.time4}</p></td>                                              
</tr>                      
<tr>                        
<td><p><span>潮高 (cm)</span></p></td>                                                
<td width="100"><p>${dateMap.height1}</p></td>                                                
<td width="100"><p>${dateMap.height2}</p></td>                                                
<td width="100"><p>${dateMap.height3}</p></td>                                                
<td width="100"><p>${dateMap.height4}</p></td>                                              
</tr>                    
</table>
</div>

