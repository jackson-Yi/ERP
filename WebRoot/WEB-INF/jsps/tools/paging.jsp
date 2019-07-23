<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<script type="text/javascript">
$(function() {
	

	//获得总页数和当前页码
	var totalPage = parseInt($("#totalPage").val());
	var pageNo = parseInt($("#cpageNo").val());
	if(pageNo == 1 && totalPage == 1){
		$("#fir").hide();
		$("#pre").hide();
		$("#next").hide();
		$("#last").hide();
	}else if(pageNo == 1 && totalPage > pageNo){
		$("#fir").hide();
		$("#pre").hide();
		$("#next").show();
		$("#last").show();
	}else if(pageNo > 1 && totalPage > pageNo){
		$("#fir").show();
		$("#pre").show();
		$("#next").show();
		$("#last").show();
	}else if(pageNo > 1 && totalPage == pageNo){
		$("#fir").show();
		$("#pre").show();
		$("#next").hide();
		$("#last").hide();
	}
	/* 上一页 */
	$("#next").click(function(){
		$("#pageNo").val(pageNo + 1);
		$("form:first").submit();
	})
	//下一页
	$("#pre").click(function(){
		$("#pageNo").val(pageNo - 1);
		$("form:first").submit();
	})
	//首页
	$("#fir").click(function(){
		$("#pageNo").val(1);
		$("form:first").submit();
	})
	//末页
	$("#last").click(function(){
		$("#pageNo").val(totalPage);
		$("form:first").submit();
	})
	
	//跳转到指定页面
	$("#jump").click(function(){
		var selectPageNo = $("#selectPage").val();
		//正则表达式，校验输入的页数只能是1位数字
		var reg = /^\d{0,}$/;
		if(!reg.test(selectPageNo)){
			alert("请输入1到"+totalPage+"数字");
		}else{
			var selectPageNoNum = parseInt(selectPageNo);
			if(selectPageNoNum < 1 || selectPageNoNum > totalPage){
				alert("请输入1到"+totalPage+"数字");
			}else{
				$("#pageNo").val(selectPageNo);
				$("form:first").submit();
			}
			
		}
		
	})
	
	
	
	
});
</script>
<br/>
<table width="100%" border="0" cellpadding="0" cellspacing="0">
					
	<tr>
		<td width="51%">&nbsp;
			<!-- 
				用两个隐藏去来计算是否展示哪些按钮
			 -->
			<input type="hidden" id="totalPage" value="<s:property value="#page.totalPage"/>">
			<input type="hidden" id="cpageNo" value="<s:property value="#page.pageNo"/>">
			<input type="hidden" id="pageNo" name="query.pageNo" value="<s:property value="#page.pageNo"/>">
		</td>
		<td width="13%">共 <s:property value="#page.totalCount"/>条记录
		<td width="6%">
			<input type="button" id="fir" value="首页">
		</td>
		<td width="6%">
			<input type="button" id="pre" value="上一页">
		</td>
		<td width="6%">
			<input type="button" id="next" value="下一页">
		</td>
		<td width="6%">
			
			<input type="button" id="last" value="尾页">
		</td>
		<td>
			
			<input type="text" id="selectPage" value="<s:property value="#page.pageNo"/>" size="1">
		</td>
		<td >
			
			<input type="button" id="jump" value="跳转">
		</td>
		<td width="12%">当前第<span style="color:red;"> <s:property value="#page.pageNo"/></span>/ <s:property value="#page.totalPage"/>页</td>
	</tr>
</table>
