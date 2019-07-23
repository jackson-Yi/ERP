<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../taglibs.jsp" %>
<script type="text/javascript">
function inStockProduct(productId, orderDetailId){
	
	var diag = new Dialog();
	diag.Width = 850;
	diag.Height = 200;
	diag.ShowButtonRow=true;
	diag.Title = "修改采购单";
	diag.URL = "${path}/store_inStock";
	diag.OKEvent = function(){
		var win = diag.innerFrame.contentWindow;
		var result = win.inStockTure(productId, orderDetailId);
		if(result == "success"){
			var orderId = $("#orderId").val();
			diag.close();
			window.location.href = "${path}/tranOrder_inDetail?query.orderId="+orderId;
		}
		
	};
	diag.show();
}
</script>
<div class="content-right">
	<div class="content-r-pic_w">
		<div style="margin:8px auto auto 12px;margin-top:6px">
			<span class="page_title">进货订单明细</span>
		</div>
	</div>
	<div class="content-text">
			<input id="orderId" type="hidden" value="<s:property value="order.orderId"/>">
			<div class="square-o-top">
				<table width="100%" border="0" cellpadding="0" cellspacing="0"
					style="font-size:14px; font-weight:bold; font-family:"黑体";">
					<tr>
						<td>订 单 号:</td>
						<td class="order_show_msg" colspan="2"><s:property value="order.orderNum"/></td>
						<td>商品总量:</td>
						<td class="order_show_msg"><s:property value="order.totalNum"/></td>
						
					</tr>
					
				</table>
			</div>
			<!--"square-o-top"end-->
			<div class="square-order">
				<center style="text-decoration:underline;font-size:16px; font-weight:bold; font-family:"黑体";">&nbsp;&nbsp;&nbsp;&nbsp;订&nbsp;&nbsp;单&nbsp;&nbsp;明&nbsp;&nbsp;细&nbsp;&nbsp;&nbsp;&nbsp;</center>
				<br/>
				<table width="100%" border="1" cellpadding="0" cellspacing="0">
					<tr align="center"
						style="background:url(${path}/images/table_bg.gif) repeat-x;">
						<td width="20%" height="30">商品类别</td>
						<td width="20%">商品名称</td>
						<td width="20%">已入库数量</td>
						<td width="20%">剩余数量</td>
						<td width="20%">操作</td>
					</tr>
					<s:iterator value="order.details" var="detail">
						<tr align="center" bgcolor="#FFFFFF">
							<td height="30"><s:property value="#detail.product.productType.name"/></td>
							<td><s:property value="#detail.product.name"/></td>
							<td><s:property value="#detail.detailNum - #detail.surplus"/></td>
							<td align="right"><s:property value="#detail.surplus"/> </td>
							<td >
								<s:if test="#detail.surplus != 0">
									<input type="button" onclick="inStockProduct(<s:property value="#detail.product.productId"/>,<s:property value="#detail.orderDetailId"/>)" value="入库"></td>
								</s:if>
						</tr>
					</s:iterator>
					
					
					
				</table>
				<br/>
				
			</div>
	</div>
	<div class="content-bbg"></div>
</div>
