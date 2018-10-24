<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<title>珠海气象局（台）</title>
<script>
	function MM_jumpMenu(url){
		window.open(url); 
	}
</script>
</head>
<body>
<h1 class="con_h1b">友情链接</h1>
    <div class="link_con">
        <#list categoryList as obj>
        	<p>${obj.name}
            	<select  onchange="MM_jumpMenu(this.value)">
            	 	<#list linkList as link>
            	 		<#if link.category.id==obj.id>
            	 			<option value="${link.href}">${link.title}</option>
            	 		</#if>
                	</#list>
            	</select>
        	</p>
        </#list>
    </div>
</body></html>