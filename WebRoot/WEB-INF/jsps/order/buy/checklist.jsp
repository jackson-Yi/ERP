<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../taglibs.jsp" %>
<script type="text/javascript">
	$(function() {
		$("#query").click(function() {
			$("form:first").submit();
		});
		
		var orderState = $("#orderState").val();
		if(orderState == "1"){
			$("#li1").css("background","#8ECC3D");
		}
		if(orderState == "2"){
			$("#li2").css("background","#8ECC3D");
		}
		if(orderState == "3"){
			$("#li3").css("background","#8ECC3D");
		}
	});
	
	function viewDetail(orderId){
		var diag = new Dialog();
		diag.Width = 850;
		diag.Height = 400;
		diag.ShowButtonRow=true;
		diag.Title = "订单明细";
		diag.URL = "${path}/orderModel_orderDetail?query.orderId="+orderId;
		diag.OKEvent = function(){
			var win = diag.innerFrame.contentWindow;
			
			
		};
		diag.show();
		
		diag.addButton("next","操作日志",function(){
			var win = diag.innerFrame.contentWindow;
			
			var diag1 = new Dialog();
			diag1.Width = 850;
			diag1.Height = 300;
			diag1.ShowButtonRow=true;
			diag1.Title = "操作日志";
			diag1.URL = "${path}/consoleLog_consoleLog?query.entityId="+orderId+"&query.tableName=order_model&query.optType=审核订单";
			diag1.OKEvent = function(){
				var win = diag.innerFrame.contentWindow;
			};
			diag1.show();
		});
		
	}
	
	function auditOrderNote(orderId){
		var diag = new Dialog();
		diag.Width = 450;
		diag.Height = 200;
		diag.ShowButtonRow=true;
		
		diag.Title = "审核";
		diag.URL = "${path}/orderModel_auditText";
		diag.OKEvent = function(){
			var win = diag.innerFrame.contentWindow;
			//获得审批意见
			var note = win.getNote();
			var result = auditOrder(orderId, 2, note);
			if(result == "success"){
				diag.close();
				window.location.href = "${path }/orderModel_checklist?query.orderType=1&query.orderState=1";
			}
		};
		
		
		
		
		diag.show();
		diag.okButton.value="通过";
	
		diag.addButton("next","驳回",function(){
			var win = diag.innerFrame.contentWindow;
			//获得审批意见
			var note = win.getNote();
			var result = auditOrder(orderId, 3, note);
			if(result == "success"){
				diag.close();
				window.location.href = "${path }/orderModel_checklist?query.orderType=1&query.orderState=1";
			}
		});
	}
	/* ajax 表单提交 */
	function auditOrder(orderId, orderState, note){
		var result = "";
		$.ajax({
			url:"${path}/ajax_orderModel_auditOrder",
			type:"post",
			data:{
				"order.orderId":orderId,
				"order.orderState":orderState,
				"note":note
			},
			async:false,
			dataType:"text",
			success:function(responseText){
				result = responseText;
			}
		});
		return  result; 
	}
</script>
<style>
li{
	list-style-type: none;
	float: left;
	padding: 3px;
	border: 1px solid #8ECC3D;
	width: 80px;
	text-align: center;
	margin-left: 1px;
	font-size: 15px;
	
}
</style>
<div class="content-right">
	<div class="content-r-pic_w">
		<div style="margin:8px auto auto 12px;margin-top:6px">
			<span class="page_title">进货审核</span>
		</div>
	</div>
	<div class="content-text">
		<form action="${path }/orderModel_checklist" method="post"> 
		<input type="hidden" name="query.orderType" value="<s:property value="query.orderType"/>">
		<input id="orderState" type="hidden" name="query.orderState" value="<s:property value="query.orderState"/>">
			<div class="square-o-top">
				<table width="100%" border="0" cellpadding="0" cellspacing="0"
					style="font-size:14px; font-weight:bold; font-family:"黑体";">
					<tr>
						
						<td>下单人:</td>
						<td><s:textfield name="query.createrName" type="text" size="14" /></td>
						<td>总量:</td>
						<td><s:textfield name="query.minTotalNum" type="text" size="14" /></td>
						<td>到 </td>
						<td>&nbsp;&nbsp;<s:textfield name="query.maxTotalNum" type="text" size="14" /></td>
						<td height="30"></td>
						<td> 
							
						</td>
						<td ><a id="query"> 
							<img src="${path}/images/can_b_01.gif" border="0" /> </a>
						</td>
					</tr>
					<tr>
						<td>下单时间:</td>
						<td>
							<s:textfield name="query.minCreateTime" type="text" size="14" onfocus="c.showMoreDay=false;c.show(this);" readonly="true"/>
						</td>
						<td>到</td>
						<td>
							<s:textfield name="query.maxCreateTime" type="text" size="14" onfocus="c.showMoreDay=false;c.show(this);" readonly="true"/>
						</td>
						<td>总额:</td>
						<td><s:textfield name="query.minTotalPrice" type="text" size="14" /></td>
						<td>到</td>
						<td>&nbsp;&nbsp;<s:textfield name="query.maxTotalPrice" type="text" size="14" /></td>
						<td>
							<a id="addOrder" href="javascript:void(0);">
								<img src="${path}/images/can_b_02.gif" border="0" /> 
							</a>
						</td>
					</tr>
					
					
				</table>
			</div>
			<!--"square-o-top"end-->
			<div class="square-order">
				<ul>
					<li id="li1"><a href="${path }/orderModel_checklist?query.orderType=1&query.orderState=1" style="text-decoration: none;color: black">未审核</a></li>
					<li id="li2"><a href="${path }/orderModel_checklist?query.orderType=1&query.orderState=2" style="text-decoration: none;color: black">审核通过</a></li>
					<li id="li3"><a href="${path }/orderModel_checklist?query.orderType=1&query.orderState=3" style="text-decoration: none;color: black">审核不通过</a></li>
				</ul>
				<div style="border: 1px solid #8ECC3D;clear: left;"></div>
				<table width="100%" border="1" cellpadding="0" cellspacing="0">
					<tr align="center"
						style="background:url(${path}/images/table_bg.gif) repeat-x;">
						<td width="25%" height="30">订单号</td>
						<td width="9%">供应商</td>
						<td width="10%">制单人</td>
						<td width="10%">制单时间</td>
						<td width="10%">订单商品总量</td>
						<td width="12%">订单总金额</td>
						<td width="15%">详情</td>
						<!-- <td width="9%">订单状态</td> -->
					</tr>
					
					<s:iterator value="#page.list" var="order">
						<tr align="center" bgcolor="#FFFFFF">
							<td width="13%" height="30"><s:property value="#order.orderNum"/></td>
							<td><s:property value="#order.supplier.name"/></td>
							<td><s:property value="#order.orderCreater.name"/></td>
							<td><s:property value="#order.createTime"/></td>
							<td><s:property value="#order.totalNum"/></td>
							<td align="right"><s:property value="#order.totalPrice"/> 元</td>
							<td>
								<input type="button" onclick="viewDetail(<s:property value="#order.orderId"/>)"  value="详情">
								<s:if test="#order.orderState == 1">
									<input type="button" onclick="auditOrderNote(<s:property value="#order.orderId"/>)"  value="审核">
								</s:if>
							</td>
							<!-- <td>未审核</td> -->
						</tr>
					</s:iterator>
					
				</table>
				<%@ include file="../../tools/paging.jsp" %>
			</div>
		</form>
	</div>
	<div class="content-bbg"></div>
</div>
