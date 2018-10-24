<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<h1 align="left" style="padding-top:5px;padding-bottom:5px;font-size: 15px;">
珠海市气象台${publishTime}发布空气质量预报</h1>
${con}

<table>
<tr>
	<td width="7%">AQI指数</td>
	<td width="10%">等级</td>
	<td width="20%">注意事项</td>
	<td width="7%">AQI指数</td>
	<td width="10%">等级</td>
	<td width="20%">注意事项</td>
</tr>
<tr>
	<td>0-50</td>
	<td>1级 优</td>
	<td>参加户外活动呼吸清新空气</td>
	<td>50-100 </td>
	<td>2级 良</td>
	<td>可以正常进行室外活动</td>
</tr>

<tr>
	<td>101-150 </td>
	<td>3级 轻度 	</td>
	<td>敏感人群减少体力消耗大的户外活动</td>
	<td>151-200</td>
	<td>4级 中度</td>
	<td>对敏感人群影响较大</td>
</tr>

<tr>
	<td>201-300</td>
	<td>5级 重度</td>
	<td>所有人应适当减少室外活动</td>
	<td>>300</td>
	<td>6级 严重</td>
	<td>尽量不要留在室外</td>
</tr>

</table>


