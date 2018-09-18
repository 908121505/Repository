<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%
	String path =  request.getContextPath();
	String basePath =  "https://" + request.getServerName() +  path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath %>" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<!--  treeview -->

<link rel="stylesheet" href="resources/treeview/css/zTreeStyle.css" type="text/css">
<script type="text/javascript" src="resources/treeview/js/jquery.ztree.core-3.5.js"></script>
<script type="text/javascript" src="resources/treeview/js/jquery.ztree.excheck-3.5.js"></script>
<script type="text/javascript" src="resources/treeview/js/jquery.ztree.exedit-3.5.js"></script>
<!--  treeview end -->

<script type="text/javascript">
	//表格的初始化
	var setting = {
			async: {
				enable: true,
				url:"system/role/initTree.htm?role_code=${entity.codes}",
				autoParam:[],
				dataFilter: filter
			},
			data: {
				simpleData: {
					enable: true
				}
			},
			check:{
				enable: true
			}
	};

	function filter(treeId, parentNode, childNodes) {
		if (!childNodes) return null;
		var obj = childNodes.aaData;
		for (var i=0; i<obj.length; i++) {
			obj[i].id = obj[i].id;
			obj[i].name = obj[i].names;
			obj[i].pId = obj[i].parent_id;
		}
		return obj;
	}
	 $(document).ready(function() {
		//树形初始化
		$.fn.zTree.init($("#treeDemo"), setting);
	 });
	  var btn = $('.modal-dialog').find("button.btn-primary");
		$(btn).on("click",function(){
			 var treeObj=$.fn.zTree.getZTreeObj("treeDemo");
			  nodes = treeObj.getCheckedNodes(true);
			 var v="";
			  for(var i=0;i<nodes.length;i++){
				  v+=nodes[i].id+",";
			  } 
			  $.post("system/role/AddMenus.htm",{role_code:$('#role_code').val(),
				  menu_id:v},function(data){
				  if(data>=0){
					$.globalMessenger().post({
						message: '操作成功！',
						type: 'success',
						id: "Only-one-message",
						hideAfter: 2,
						hideOnNavigate: true
					})
				  }else{
						$.globalMessenger().post({
							message: '错误：'+data,
							type: 'error',
							id: "Only-one-message",
							hideAfter: 2,
							hideOnNavigate: true
						});
					}
			  });
		  });
</script>
</head>
<body>
<div class="modal-dialog">
	<div class="modal-content">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal"
				aria-hidden="true">X</button>
			<h3 id="myModalLabel">${empty entity?'新增':'修改' }角色信息</h3>
		</div>
		<div class="modal-body">
			<div class="form-horizontal">
				<div class="form-group">
					<label class="col-sm-2 control-label">角色名称:</label>
					<label class="h4">${entity.names }</label>
					<input type="hidden" value="${entity.codes }" name="codes" id="role_code"/>
					<div class="col-sm-10">
					</div>
				</div>
				<div class="row">
					<div class="col-md-2">
						<div class="zTreeDemoBackground">
							<ul id="treeDemo" class="ztree"></ul>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="modal-footer">
			<button class="btn btn-default" data-dismiss="modal"
				aria-hidden="true">取消</button>
			<button class="btn btn-primary" data-dismiss="modal" >保存</button>
		</div>
	</div>
</div>
</body>
<script type="text/javascript">
function check_fun(){
	return true;
}
</script>
</html>