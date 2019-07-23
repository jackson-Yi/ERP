<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../taglibs.jsp" %>
<script type="text/javascript">
	$(function() {
		$("#all").click(function() {
			$("[name=roles]:checkbox").attr("checked",$("#all").attr("checked")=="checked");
		});
		$("#reverse").click(function() {
			$("[name=roles]:checkbox").each(function () {
                $(this).attr("checked", !$(this).attr("checked"));
            });

		});
		//表单离开焦点自动进行校验
		$("#empForm").find("[regr]").keyup(function(){
			//获得每一个必填项的输入信息
			var val = $(this).val();
			//获得每一个必填项的正则表达式
			var regStr = $(this).attr("regr");
			//获得每一个必填项的提示信息
			var tip = $(this).attr("tip");
			//创建正则表达式的对象
			var reg = new RegExp(regStr);
			//获得每一个文本域的name
			var name = $(this).attr("name");
			if(!reg.test(val)){
				$(this).css("background", "#FFAC8C");
				//设置提示信息
				$("#tip").html(tip);
				//展示提示信息
				$("#tip").show();
				$(this).next("span").hide();
			}else{
				//校验输入密码和确认密码是否相同
				if(name == "repassword"){
					//获得密码的值
					var password = $("#password").val();
					if(password != val){
						$(this).css("background", "#FFAC8C");
						//设置提示信息
						$("#tip").html("确认密码不一致");
						//展示提示信息
						$("#tip").show();
						$(this).next("span").hide();
				}else{
					//正则表达式校验正确，将输入框变回白色
					$(this).css("background", "white");
					//展示提示信息
					$("#tip").hide();
					$(this).next("span").show();
				}
			}else{
				$(this).css("background","white");
				$("#tip").hide();
				$(this).next("span").show();
			}
		}
		});
		
		//校验代码(对非必填项校验代码)————（必填项find为[regr]，非必填项为[regr]）
		$("#empForm").find("[reg]").keyup(function(){
			//获得每一个必填项的输入信息
			var val = $(this).val();
			//获得每一个必填项的正则表达式
			var regStr = $(this).attr("reg");
			//获得每一个必填项的提示信息
			var tip = $(this).attr("tip");
			//创建正则表达式的对象
			var reg = new RegExp(regStr);
			if(val != null && $.trim(val) != "" && !reg.test($.trim(val))){
				$(this).css("background", "#FFAC8C");
				//设置提示信息
				$("#tip").html(tip);
				//展示提示信息
				$("#tip").show();
				//设置提交表示是false
				isSubmit = false;
				//跳出校验循环
				return false;
				
			}else{
				//正则表达式校验正确，将输入框变回白色
				$(this).css("background", "white");
				$("#tip").hide();
			}
		});
		
		
	});
	//提交表单的校验
	function submitForm() {
		//成功标志
		var result = "";
		var isOk = validForm();
		if(isOk){
			//因为使用的是弹窗表单，所以要使用ajax方式提交表单，不能使用普通方法
			//$("#empForm").submit();
			$("#empForm").ajaxSubmit({
				//关闭异步，改为同步
				async:false,
				dateType:"text",
				success:function(responseText){
					//如果后台添加emp正确返回success
					result = responseText;
				}
			});
		}
		return result;
	}
	
	//在validForm中写校验逻辑，在submitForm中提交校验
	function validForm() {
		//表单的提交标志
		var isSubmit = true;
		//校验代码
		$("#empForm").find("[regr]").each(function(){
			//获得每一个必填项的输入信息
			var val = $(this).val();
			//获得每一个必填项的正则表达式
			var regStr = $(this).attr("regr");
			//获得每一个必填项的提示信息
			var tip = $(this).attr("tip");
			//创建正则表达式的对象
			var reg = new RegExp(regStr);
			//获得每一个文本域的name
			var name = $(this).attr("name");
			if(!reg.test(val)){
				$(this).css("background", "#FFAC8C");
				//设置提示信息
				$("#tip").html(tip);
				//展示提示信息
				$("#tip").show();
				//设置提交表示是false
				isSubmit = false;
				//跳出校验循环
				return false;
				
			}else{
				if(name == "emp.username"){
					var result = validUname(val);
					if(result == "no"){
						//设置提示信息
						$("#tip").html("用户名已存在");
						//展示提示信息
						$("#tip").show();
						//设置提交表示是false
						isSubmit = false;
						//跳出校验循环
						return false;
					}
				}
				//校验输入密码和确认密码是否相同
				if(name == "repassword"){
					//获得密码的值
					var password = $("#password").val();
					if(password != val){
						$(this).css("background", "#FFAC8C");
						//设置提示信息
						$("#tip").html("确认密码不一致");
						//展示提示信息
						$("#tip").show();
						//设置提交表示是false
						isSubmit = false;
						//跳出校验循环
						return false;
					}
				}
				//正则表达式校验正确，将输入框变回白色
				$(this).css("background", "white");
			}
		});
		
		
		//校验代码(对非必填项校验代码)————（必填项find为[regr]，非必填项为[regr]）
		$("#empForm").find("[reg]").each(function(){
			//获得每一个必填项的输入信息
			var val = $(this).val();
			//获得每一个必填项的正则表达式
			var regStr = $(this).attr("reg");
			//获得每一个必填项的提示信息
			var tip = $(this).attr("tip");
			//创建正则表达式的对象
			var reg = new RegExp(regStr);
			if(val != null && $.trim(val) != "" && !reg.test($.trim(val))){
				$(this).css("background", "#FFAC8C");
				//设置提示信息
				$("#tip").html(tip);
				//展示提示信息
				$("#tip").show();
				//设置提交表示是false
				isSubmit = false;
				//跳出校验循环
				return false;
				
			}else{
				//正则表达式校验正确，将输入框变回白色
				$(this).css("background", "white");
			}
		});
		return isSubmit;
	}
	
	/**
	*	ajax 默认是异步的，一旦要使用ajax的结果作为返回值，就必须把ajax变成同步：async:"false",
	*/
	function validUname(username) {
		var result = "yes";
		$.ajax({
			url:"${path}/ajax_emp_validUname",
			type:"post",
			data:{
				"emp.username":username
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
			<span class="page_title">员工管理</span>
		</div>
	</div>
	<div class="content-text">
		<div class="square-order">
			<form id="empForm" action="${path }/ajax_emp_add" method="post"> 
  			<div style="border:1px solid #cecece;">
				<table width="100%"  border="0" cellpadding="0" cellspacing="0">
				  <tr bgcolor="#FFFFFF">
				    <td align="center">
						<div id="tip" style="text-align: center; border: 1px solid #FFC2DB; margin: 5px; padding: 5px; width: 700px; background-color: #FFE790;color: red; display: none;"></div>
					</td>
				  </tr>
				</table>
				<table width="100%"  border="0" cellpadding="0" cellspacing="0">
				    <tr  bgcolor="#FFFFFF">
				      <td width="18%" height="30" align="center"><font color="red">*</font>用&nbsp;户&nbsp;名</td>
				      <td width="32%">
				      	
				      	<s:textfield name="emp.username" type="text" size="25" regr="^\w{6,8}$" tip="请输入6到8位用户名"></s:textfield><span style="display: none" ><img width="20" src="${path }/images/ok.png"></span>
				      </td>
				      <td width="18%"align="center"><font color="red">*</font>真实姓名</td>
				      <td width="32%">
				      	<s:textfield name="emp.name" type="text" size="25" regr="^[\u4e00-\u9fa5]{2,10}$" tip="请输入2到10位的真实姓名"></s:textfield>
					  	<span style="display: none" ><img width="20" src="${path }/images/ok.png"></span>	
					  </td>
				    </tr>
				    <tr bgcolor="#FFFFFF">
					  <td colspan="4">&nbsp;</td>
					</tr>
				    <tr  bgcolor="#FFFFFF">
				      <td align="center"><font color="red">*</font>密&nbsp;&nbsp;&nbsp;&nbsp;码</td>
				      <td>
				      	<s:textfield id="password" name="emp.password" type="password" size="25" regr="^[\w!@#$%^&*()_]{6,8}$" tip="请输入6到8位密码"></s:textfield>
				      	<span style="display: none" ><img width="20" src="${path }/images/ok.png"></span>
				      </td>
				      <td  align="center"><font color="red">*</font>确认密码</td>
				      <td >
				      	<s:textfield name="repassword" type="password" size="25" regr="^[\w!@#$%^&*()_]{6,8}$" tip="请输入6到8位确认密码"></s:textfield>
				      	<span style="display: none" ><img width="20" src="${path }/images/ok.png"></span>
				      </td>
				    </tr>
				     <tr bgcolor="#FFFFFF">
					  <td colspan="4">&nbsp;</td>
					</tr>
				    <tr  bgcolor="#FFFFFF">
				      <td height="30" align="center">电子邮箱</td>
				      <td>
				      	<s:textfield name="emp.email" type="text" size="25" reg="^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$" tip="请输入邮箱格式的文本，如renliang@126.com"></s:textfield>
				      	<span style="display: none" ><img width="20" src="${path }/images/ok.png"></span>
				      <td align="center">电话号码</td>
				      <td>
				      	<s:textfield name="emp.tel" type="text" size="25" reg="^(13[0-9]|14[5|7]|15[0|1|2|3|5|6|7|8|9]|18[0|1|2|3|5|6|7|8|9])\d{8}$" tip="请输入正确的11位电话号"></s:textfield>
					  	<span style="display: none" ><img width="20" src="${path }/images/ok.png"></span>
					  </td>
				     </tr>
				      <tr bgcolor="#FFFFFF">
					  <td colspan="4">&nbsp;</td>
					</tr>
				    <tr  bgcolor="#FFFFFF">
				      <td height="30" align="center"><font color='red'>*</font>性&nbsp;&nbsp;&nbsp;&nbsp;别</td>
				      <td>
				      	<s:select list="#{'1':'男','2':'女' }" name="emp.gender" cssClass="kuan" headerKey=""></s:select>
					  </td>
				      <td align="center">地&nbsp;&nbsp;&nbsp;&nbsp;址</td>
				      <td>
				      	<s:textfield name="emp.address" type="text" size="25" reg="^[\u4e00-\u9fa5\w]{1,50}$" tip="请输入50字以内的中英文"></s:textfield>
					  	<span style="display: none" ><img width="20" src="${path }/images/ok.png"></span>
					  </td>
				    </tr>
				     <tr bgcolor="#FFFFFF">
					  <td colspan="4">&nbsp;</td>
					</tr>
				    <tr  bgcolor="#FFFFFF">
				      <td height="30" align="center">出生日期</td>
				      <td>
				      	 <s:textfield name="emp.birthday" type="text" size="25" onfocus="c.showMoreDay=false;c.show(this);" readonly="true"></s:textfield>
					  </td>
				      <td align="center"><font color='red'>*</font>所属部门</td>
				      <td>
				      	<s:select list="#dList" cssClass="kuan" name="emp.dep.depId" headerKey="" listKey="depId" listValue="name"></s:select>
					  </td>
				    </tr>
				    <tr  bgcolor="#FFFFFF">
				      <td colspan="4">&nbsp;</td>
				    </tr>
				</table>
			</div>
			<%-- <div class="order-botton">
				<div style="margin:1px auto auto 1px;">
					<table width="100%"  border="0" cellpadding="0" cellspacing="0">
					  <tr>
					    <td>
					    	<a href="javascript:document.forms[0].submit()"><img src="${path}/images/order_tuo.gif" border="0" /></a>
					    </td>
					    <td>&nbsp;</td>
					    <td><a href="#"><img src="${path}/images/order_tuo.gif" border="0" /></a></td>
					    <td>&nbsp;</td>
					    <td><a href="#"><img src="${path}/images/order_tuo.gif" border="0" /></a></td>
					  </tr>
					</table>
				</div>
			</div> --%>
			</form>
		</div><!--"square-order"end-->
	</div><!--"content-text"end-->
	<div class="content-bbg"><img src="${path}/images/content_bbg.jpg" /></div>
</div>
