<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<shiro:hasPermission name="menu:select">
	<div class="content1">
		<div class="header">
			<h1 class="page-title">菜单列表</h1>
			<ul class="breadcrumb">
				<li>系统管理</li>
				<li class="active">菜单列表</li>
			</ul>
		</div>
		<div class="main-content">
			<div class="col-md-2">
				<ul id="treeDemo" class="ztree"></ul>
			</div>
			<div class="col-md-10">
				<div class="row">
					<div class="col-md-3">
						<div class="form-group">
							<div class="input-group">
								<div class="input-group-addon">菜单名称</div>
								<input class="form-control" type="text" id="names">
							</div>
						</div>
					</div>
					<div class="col-md-3">
						<div class="form-group">
							<div class="input-group">
								<div class="input-group-addon">父级编号</div>
								<input class="form-control" type="text" id="parentId">
							</div>
						</div>
					</div>
					<div class="col-md-2">					
						<button type="button" class="btn btn-primary btn-small btn-block" id="query"><i class="glyphicon glyphicon-search"></i> 查询</button>
					</div>
					<div class="col-md-2">
					     <shiro:hasPermission name="menu:add">
						   <button type="button" class="btn btn-info btn-small btn-block"onclick="addAndUpdateRow(0)"><i class="glyphicon glyphicon-plus"></i> 增加</button>
						 </shiro:hasPermission>
					</div>
				</div>
			<table id="example" class="table"></table>
			</div>
		</div>

		<!---dialog选项-->
	<div>
		<jsp:include page="/WEB-INF/views/common/delete_dialog.jsp" />
		<jsp:include page="/WEB-INF/views/common/addupdate_dialog.jsp" />
	  <div class="modal fade" id="insertAndUpdate" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	   </div>
	</div>
</div>
	<script>
	//表格的初始化
	$(document).ready(function() {
		$('#example').initTable({
			sAjaxSource:"system/menu/initTable.htm",
			aoColumns: [
	            { "data": "id","sTitle":"菜单编号"},
	            { "data": "names","sTitle":"菜单名称" },
	            { "data": "author","sTitle":"权限编码"},
	            { "data": "url","sTitle":"访问地址" },
	            { "data": "img_icon","sTitle":"菜单样式"},
	            { "data": "menu_type","sTitle":"菜单类别"},
	            { "data": "orders","sTitle":"显示顺序"},
	            { "data": "parent_id","sTitle":"父级菜单"}
	            <shiro:hasPermission name="menu:delete or menu:update">
	            ,{ "sTitle":"操作",'sClass':"text-center"}
	            </shiro:hasPermission>
	         ],
			 fnServerParams: function (aoData) {  //查询条件
             	aoData.push({ "name": "names", "value": $("#names").val() } );
             	aoData.push({ "name": "parentId", "value": $("#parentId").val() } );
             },
             aoColumnDefs : [
				<shiro:hasPermission name="menu:delete or menu:update">
             	{"aTargets" :8,"mRender" : function(data, type, row){
             		var edit = "",del = "";
             		<shiro:hasPermission name="menu:update">
             			edit = "<a href='#' onclick='addAndUpdateRow("+row.id+")' data-toggle='modal' class='padding-right-small label label-success'><i class='glyphicon glyphicon-edit'></i> 编辑</a>";
             		</shiro:hasPermission>
             		<shiro:hasPermission name="menu:delete">
             			del ="<a href='#' onclick='deleteRow("+row.id+")' data-toggle='modal' class='label label label-danger'><i class='glyphicon glyphicon-trash'></i> 删除 </a>";
             		</shiro:hasPermission>
                    return edit+"&nbsp;"+del;
                }
             }
             </shiro:hasPermission>
            ]
		});
		
		 $('#query').click(function(){
			$('#example').dataTable().fnDraw();
		});
	
	});
	//删除受影响的行数
	function deleteRow(id){
		$('#myModal').deleteRow('system/menu/delete.htm?id='+id);
	}
	//增加或者修改受影响的行数
	function addAndUpdateRow(id){
		$('#insertAndUpdate').addAndUpdateRow("system/menu/addAndUpdateHome.htm?id="+id);
	}
	</script>
	<script type="text/javascript">
		var setting = {
				async: {
					enable: true,
					url:"tree/initTree.htm"
				},
				data: {
					simpleData: {
						enable: true
					}
				},
				callback: {
					onClick: noteClick,
					onAsyncSuccess: zTreeOnAsyncSuccess 
				}
			};
			
			function noteClick(event, treeId, treeNode){
				$('#parentId').val(treeNode.id);
				$('#example').dataTable().fnDraw();
			}
			function zTreeOnAsyncSuccess(event, treeId, treeNode){
				 var selectedNode = treeObj.getSelectedNodes();  
                var nodes = treeObj.getNodes();  
                treeObj.expandNode(nodes[0], true);
			}
			
			var treeObj;
			$(document).ready(function() {
				treeObj = $.fn.zTree.init($("#treeDemo"), setting);
			});
		</script>
</shiro:hasPermission>