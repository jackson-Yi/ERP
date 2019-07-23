<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../taglibs.jsp" %>
<script type="text/javascript">
	$(function() {
		
	});
</script>
<div class="content-right">
	<div class="content-r-pic_w">
		<div style="margin:8px auto auto 12px;margin-top:6px">
			<span class="page_title">仓库货物明细</span>
		</div>
	</div>
	<div class="content-text">
		<form action="list.jsp" method="post">
			
			<!--"square-o-top"end-->
			<div class="square-order">
				<table width="100%" border="1" cellpadding="0" cellspacing="0">
					<tr align="center"
						style="background:url(${path}/images/table_bg.gif) repeat-x;">
						<td width="50%" height="30">商品名称</td>
						<td width="50%">商品数量</td>
						
					</tr>
					<s:iterator value="#details" var="detail">
					<tr align="center" bgcolor="#FFFFFF">
							<td height="30"><s:property value="#detail.product.name"/></td>
							<td><s:property value="#detail.num"/><s:property value="#detail.product.unit"/></td>
							
						</tr>
					</s:iterator>
						
						
				</table>
				
			</div>
		</form>
	</div>
	<div class="content-bbg"></div>
</div>
