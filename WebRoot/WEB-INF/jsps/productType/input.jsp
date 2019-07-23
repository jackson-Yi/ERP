<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../taglibs.jsp" %>
<script type="text/javascript">
	function submitForm() {
		var supplierId = $("#supplierId").val();
		var name = $("#ptName").val();
		
		//成功标志
		var result = "";
		var isOk = validForm(supplierId, name);
		//$("#empForm").submit();
		if(isOk == "yes"){
			$("form:first").ajaxSubmit({
				//关闭异步，改为同步
				async:false,
				dateType:"text",
				success:function(responseText){
					//如果后台添加emp正确返回success
					result = responseText;
				}
			});
		}else{
			Dialog.alert("当前的供应商下的类别已经存在");
		}
		
		return result;
	}
	
	function validForm(supplierId, name) {
		var result = "yes";
		$.ajax({
			url:"${path}/ajax_productType_validUname",
			type:"post",
			data:{
				"productType.supplierId":supplierId,
				"productType.name":name
			},
			async:false,
			dateType:"text",
			success:function(responseText){
				result = responseText;
			}
		});
		return result;
	}
</script>
<div class="content-right">
	<div class="content-r-pic_w">
		<div style="margin:8px auto auto 12px;margin-top:6px">
			<span class="page_title">商品类别管理</span>
		</div>
	</div>
	<div class="content-text">
		<div class="square-order">
			<form action="${path }/ajax_productType_add" method="post">
  			<div style="border:1px solid #cecece;">
				<table width="100%"  border="0" cellpadding="0" cellspacing="0">
				  <tr bgcolor="#FFFFFF">
				    <td>&nbsp;</td>
				  </tr>
				</table>
				<table width="100%"  border="0" cellpadding="0" cellspacing="0">
				    <tr  bgcolor="#FFFFFF">
				      <td width="18%" height="30" align="center">供应商</td>
				      <td width="82%" colspan="3">
				      			<s:select id="supplierId" list="#suppliers" name="productType.supplier.supplierId" cssClass="kuan" headerKey="" headerValue="----请-选-择----" listKey="supplierId" listValue="name"></s:select>
				      </td>
				    </tr>
				    <tr  bgcolor="#FFFFFF">
				      <td colspan="4">&nbsp;</td>
				    </tr>
				    <tr  bgcolor="#FFFFFF">
				      <td width="18%" height="30" align="center">商品类别名称</td>
				      <td width="82%" colspan="3">
				      	<s:textfield id="ptName" name="productType.name" type="text" size="25"/>
				      </td>
				    </tr>
				    <tr  bgcolor="#FFFFFF">
				      <td colspan="4">&nbsp;</td>
				    </tr>
				</table>
				
			</div>
			
			</form>
		</div><!--"square-order"end-->
	</div><!--"content-text"end-->
	<div class="content-bbg"><img src="${path}/images/content_bbg.jpg" /></div>
</div>
