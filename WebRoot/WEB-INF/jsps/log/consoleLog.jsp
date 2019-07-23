<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../taglibs.jsp" %>
<script type="text/javascript">
</script>
	<div class="">
			<!--"square-o-top"end-->
			<div class="square-order">
				<table width="100%" border="1" cellpadding="0" cellspacing="0">
					<tr align="center"
						style="background:url(${path}/images/table_bg.gif) repeat-x;">
						<td width="13%" height="30">操作人</td>
						<td width="13%">时间</td>
						<td width="16%">备注</td>
					</tr>
					<s:iterator value="#logList" var="log">
					<tr align="center" bgcolor="#FFFFFF">
						<td><s:property value="#log.emp.name"/></td>
						<td><s:property value="#log.optTime"/></td>
						<td><s:property value="#log.note"/></td>
					</tr>
					</s:iterator>
				</table>
			</div>
	</div>
	
