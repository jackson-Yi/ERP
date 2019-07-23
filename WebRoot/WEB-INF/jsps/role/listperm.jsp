<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ include file="../taglibs.jsp" %>
<link rel="stylesheet" href="${path }/js/ztree/css/demo.css" type="text/css">
<link rel="stylesheet" href="${path }/js/ztree/css/zTreeStyle/zTreeStyle.css" type="text/css">
<script type="text/javascript">
    var tree = null;
	$(function() {
		var setting = {
				check: {
					enable: true
				},
				data: {
					simpleData: {
						enable: true
					}
				}
			};
		
		var zNodes = ${zNodes};
		tree = $.fn.zTree.init($("#treeDemo"), setting, zNodes);
	});
	function getNodes(roleId){
		var nodes = tree.getCheckedNodes(true);
		var permIds = "";
		for(var i = 0; i < nodes.length; i++){
			permIds = permIds + nodes[i].id+",";
		}
		var result = grantRolePerm(roleId, permIds);
		return result;
	}
	
	function grantRolePerm(roleId, permIds){
		var result = "";
		$.ajax({
			url:"${path}/ajax_role_grantPerm",
			type:"post",
			data:{
				"query.roleId":roleId,
				"permIds":permIds
			},
			async:false,
			dataType:"text",
			success:function(responseText){
				result = responseText;
			}
		});
		return result;
	}
</script>
<div>
	<div>
		<div class="zTreeDemoBackground left" style="height: 450px;">
			<ul id="treeDemo" class="ztree"></ul>
		</div>
	</div>
	
</div>
