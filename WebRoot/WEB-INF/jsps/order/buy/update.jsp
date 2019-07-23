<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="../../taglibs.jsp" %>
<script type="text/javascript">
	
	//修改供应商
	$(function() {
		$("#supplierId").change(function(){
			//获得供应商ID
			var supplierId = $(this).val();
			
			loadPt(supplierId);
			$(this).attr("disabled", "disabled");
			$("#supplierIdHide").val(supplierId);
		});
		
		$(".goodsType").change(function(){
			$(this).next("input").val($(this).val());
			//获得供应商ID
			var productTypeId = $(this).val();
			//获得这一行的tr对象
			var trObj = $(this).parent().parent();
			loadPro(trObj, productTypeId);
		});
		
		$(".goods").change(function(){
			$(this).next("input").val($(this).val());
			
			var productId = $(this).val();
			var proThis = $(this);
			var isExist = false;
			var count = 0;
			$(this).parent().parent().parent().find(".goods").each(function(){
				//循序获得商品id
				var productId1 = $(this).val();
				if(productId1 == productId){
					count++;
				}
			});
			if(count == 2){
				Dialog.alert("商品已经存在");
				$(this).find("[value='"+productId+"']").remove();
			}else{
				load_Product_detail(proThis.parent().parent(), productId);
				
				
				
				
				
			}
			
		});
		
		
		$(".deleteBtn").click(function(){
			var trObj = $(this).parent().parent();
			var trId = trObj.attr("id");
			if(trId == "defaultTr"){
				Dialog.alert("默认行不可以删除");
			}else{
				trObj.remove();
				var totalPrice = 0;
				$("#order").find(".total").each(function(){
					var trTotal = parseInt($(this).attr("trTotal"));
					totalPrice = totalPrice + trTotal;
				});
				
				$(".all").html(totalPrice+"元");
			}
			
		});
		
		$(".num").keyup(function(){
			//获得修改后的数字
			var count = parseInt($(this).val());
			$(this).parent().parent().find(".total")
			var inPrice = parseInt($(this).parent().parent().find(".prices").val());
			//给行的小计设置自定义属性值
			$(this).parent().parent().find(".total").html(inPrice*count+"元");
			$(this).parent().parent().find(".total").attr("trTotal",inPrice*count);
			//计算总价
			var totalPrice = 0;
			$(this).parent().parent().parent().find(".total").each(function(){
				var trTotal = parseInt($(this).attr("trTotal"));
				totalPrice = totalPrice + trTotal;
			});
			
			$(".all").html(totalPrice+"元");
		});
		$(".prices").keyup(function(){
			//获得修改后的数字
			var inPrice = parseInt($(this).val());
			$(this).parent().parent().find(".total");
			var count = parseInt($(this).parent().parent().find(".num").val());
			//给行的小计设置自定义属性值
			$(this).parent().parent().find(".total").html(inPrice*count+"元");
			$(this).parent().parent().find(".total").attr("trTotal",inPrice*count);
			//计算总价
			var totalPrice = 0;
			$(this).parent().parent().parent().find(".total").each(function(){
				var trTotal = parseInt($(this).attr("trTotal"));
				totalPrice = totalPrice + trTotal;
			});
			
			$(".all").html(totalPrice+"元");
		});
		/**
		 * 新建一行
		 */
		$("#add").click(function(){
			//拷贝默认行
			var tr = $("#defaultTr").clone(true);
			//新建的tr行id属性被删除
			tr.removeAttr("id");
			//把新拷贝的行中的商品下拉清空
			tr.find(".goods").empty();
			//获得当前拷贝商品的类别Id
			var productTypeId = tr.find(".goodsType").val();
			//加载当前商品类别下的商品
			loadPro(tr,productTypeId);
			$("#finalTr").before(tr);
		});
	});
	
	function loadPt(supplierId){
		var trObj = $("#defaultTr");
		$.ajax({
			url:"${path}/ajax_supplier_getSupplier",
			type:"post",
			data:{
				"query.supplierId":supplierId,
			},
			async:false,
			dataType:"text",
			success:function(responseText){
				trObj.find(".goodsType").empty();
				var jsonArr = $.parseJSON(responseText);
				for(var i = 0; i < jsonArr.length; i++){
					$("#defaultTr").find(".goodsType").append("<option value='"+jsonArr[i].productTypeId+"'>"+jsonArr[i].name+"</option>")
				}
				
			}
		});
		//获得商品类别
		var productTypeId = $("#defaultTr").find(".goodsType").val();
		loadPro(trObj,productTypeId);
		
	}
	function loadPro(trObj,productTypeId){
		$.ajax({
			url:"${path}/ajax_productType_getProduct",
			type:"post",
			data:{
				"query.productTypeId":productTypeId,
			},
			async:false,
			dataType:"text",
			success:function(responseText){
				//alert(responseText);
				trObj.find(".goods").empty();
				var jsonArr = $.parseJSON(responseText);
				for(var i = 0; i < jsonArr.length; i++){
					var isExist = false;
					$("#order").find(".goods").each(function(){
						//循序获得商品id
						var productId = $(this).val();
						
						if(productId == jsonArr[i].productId+""){
							isExist = true;
						}
					});
					//alert(isExist);
					if(!isExist){
						trObj.find(".goods").append("<option value='"+jsonArr[i].productId+"'>"+jsonArr[i].name+"</option>");
					}
				}
			}
		});
		var productId = $(trObj).find(".goods").val();
		load_Product_detail(trObj, productId);
	}
	//商品设置select的jquery对象
	function load_Product_detail(trObj, productId){
		$.ajax({
			url:"${path}/ajax_product_getProduct",
			type:"post",
			data:{
				"query.productId":productId,
			},
			dataType:"text",
			success:function(responseText){
				var obj = $.parseJSON(responseText);
				//设置商品单价
				trObj.find(".prices").val(obj.inPrice);
				//获得数量
				var count = parseInt(trObj.find(".num").val());
				//设置小计
				trObj.find(".total").html(obj.inPrice*count+"元");
				//给行的小计设置自定义属性值
				trObj.find(".total").attr("trTotal",obj.inPrice*count);
				//计算总价
				var totalPrice = 0;
				trObj.parent().find(".total").each(function(){
					var trTotal = parseInt($(this).attr("trTotal"));
					totalPrice = totalPrice + trTotal;
				});
				$(".all").html(totalPrice+"元");
				
			}
		});
	}
	function updateOrder(){
		//成功标识
		var result = "";
		//$("#empForm").submit();
		$("form:first").ajaxSubmit({
				async:false,
				dataType:"text",
				success:function(responseText){
					//如果后台添加emp正确返回success
					result = responseText;
				}
		});
		return result;
	}
</script>
<div class="content-right">
	<div class="content-r-pic_w">
		<div style="margin:8px auto auto 12px;margin-top:6px">
			<span class="page_title">订单管理</span>
		</div>
	</div>
	<div class="content-text">
		<form action="${path }/ajax_orderModel_updateOrder" method="post">
		<input type="hidden" name="order.orderChecker.empId" value="<s:property value="order.orderChecker.empId"/>">
		<input type="hidden" name="order.checkTime" value="<s:property value="order.checkTime"/>">
		<input type="hidden" name="order.orderId" value="<s:property value="order.orderId"/>">
		<input type="hidden" name="order.orderNum" value="<s:property value="order.orderNum"/>">
		
			<div class="square-o-top">
				<table width="100%" border="0" cellpadding="0" cellspacing="0"
					style="font-size:14px; font-weight:bold; font-family:"黑体";">
					<tr>
						<td width="68px" height="30">供应商：</td>
						<td width="648px">
							<s:select id="supplierId"  list="#suppliers"   headerKey="" headerValue="-----请选择-----" name="order.supplier.supplierId" cssStyle="width:300px"  listKey="supplierId" listValue="name"></s:select>
							<!-- <input id="supplierIdHide" type="hidden" name="order.supplierId"> -->
						</td>
						<td height="30">
							<a id="add"><img src="${path}/images/can_b_02.gif" border="0" /> </a>
						</td>
					</tr>
				</table>
			</div>
			<!--"square-o-top"end-->
			<div class="square-order">
				<table id="order" width="100%" border="1" cellpadding="0" cellspacing="0">
					<tr align="center"
						style="background:url(${path}/images/table_bg.gif) repeat-x;">
						<td width="25%" height="30">商品类别</td>
						<td width="25%">商品名称</td>
						<td width="10%">采购数量</td>
						<td width="15%">单价</td>
						<td width="15%">合计</td>
						<td width="10%">操作</td>
					</tr>
					<s:iterator value="order.details" var="detail">
					<tr id="defaultTr" align="center" bgcolor="#FFFFFF">
						<td height="30">
							<!-- <select name="productTypeId" class="goodsType" style="width:200px">
							</select> -->
							<s:select list="order.supplier.pts" cssClass="goodsType" cssStyle="width:200px" listKey="productTypeId" listValue="name" name="#detail.product.productType.productTypeId"></s:select>
							<input type="hidden" name="productTypeId" value="<s:property value="#detail.product.productType.productTypeId"/>">
						</td>
						<td>
							<!-- <select name="productId" class="goods" style="width:200px">
							</select> -->
							<s:select list="#detail.product.productType.products" cssClass="goods" cssStyle="width:200px" listKey="productId" listValue="name" name="#detail.product.productId"></s:select>
							<input type="hidden" name="productId" value="<s:property value="#detail.product.productId"/>">
						</td>
						<td><input name="detailNum"  class="num order_num" style="width:67px;border:1px solid black;text-align:right;padding:2px" type="text" value="<s:property value="#detail.detailNum"/>"/></td>
						<td><input name="detailPrice" class="prices order_num" style="width:93px;border:1px solid black;text-align:right;padding:2px" type="text" value="<s:property value="#detail.detailPrice"/>"/> 元</td>
						<td class="total" align="right" trTotal="<s:property value="#detail.detailNum * #detail.detailPrice"/>"><s:property value="#detail.detailNum * #detail.detailPrice"/>&nbsp;元</td>
						<td>
							<a class="deleteBtn delete xiu" href="javascript:void(0);" value="4"><img src="${path}/images/icon_04.gif" /> 删除</a>
						</td>
					</tr>
					</s:iterator>
					<tr id="finalTr" align="center"
						style="background:url(${path}/images/table_bg.gif) repeat-x;">
						<td height="30" colspan="4" align="right">总&nbsp;计:&nbsp;</td>
						<td class="all" width="16%" align="right"><s:property value="order.totalPrice"/>&nbsp;元</td>
						<td>&nbsp;</td>
					</tr>
				</table>
			</div>
		</form>
	</div>
	<div class="content-bbg"></div>
</div>
