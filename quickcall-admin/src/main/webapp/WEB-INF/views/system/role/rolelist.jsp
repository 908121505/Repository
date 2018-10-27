<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<shiro:hasPermission name="role:select">
<div class="content1">
	<div class="header">
		<h1 class="page-title">角色列表</h1>
		<ul class="breadcrumb">
			<li>系统管理</li>
			<li class="active">角色列表</li>
		</ul>
	</div>
	<div class="main-content">
		<div class="row">
			<div class="col-md-4">
				<div class="form-group">
					<div class="input-group">
						<div class="input-group-addon">角色编号</div>
						<input class="form-control" type="text" id="codes">
					</div>
				</div>
			</div>
			<div class="col-md-2">				
				<button type="button" class="btn btn-primary btn-small btn-block" id="query"><i class="glyphicon glyphicon-search"></i> 查询</button>
			</div>
			<div class="col-md-2">
				 <shiro:hasPermission name="role:add">
					<button type="button" class="btn btn-info btn-small btn-block"onclick="addAndUpdateRow(0)"><i class="glyphicon glyphicon-plus"></i> 增加</button>
				</shiro:hasPermission>
			</div>
		</div>
		<table id="example" class="table"></table>
	</div>
	<script>
		$(document).ready(function() {
			 $('#example').initTable({
				sAjaxSource:"system/role/initTable.htm",
				aoColumns: [
		            { "data": "codes","sTitle":"角色编号" },
		            { "data": "names","sTitle":"角色名称" }
		            <shiro:hasPermission name="role:menu">
		            ,{ "data": "codes","sTitle":"分配角色菜单",'sClass':"text-center" }
		            </shiro:hasPermission>
		            <shiro:hasPermission name="role:update or role:delete">
		            ,{ "data": "id","sTitle":"操作",'sClass':"text-center"}
		            </shiro:hasPermission>
		         ],
				 fnServerParams: function (aoData) {  //查询条件
	             	aoData.push({ "name": "codes", "value": $("#codes").val() } );
	             },
	             aoColumnDefs : [
					<shiro:hasPermission name="role:menu">
					{"aTargets" :2,"mRender" : function(data, type, full){
						var menu="";
						<shiro:hasPermission name="role:menu">
							menu = "<a href='#' onclick='addTreeRow("+data+")' data-toggle='modal' class='padding-right-small label label-primary'><i class='glyphicon glyphicon-edit'></i> 分配菜单</a>";
						</shiro:hasPermission>
						return menu;
					}},
					</shiro:hasPermission>
					<shiro:hasPermission name="role:update or role:delete">
	             	{"aTargets" :3,"mRender" : function(data, type, full){
	             		var edit = "",del = "";
	             		<shiro:hasPermission name="role:update">
	             			edit = "<a href='#' onclick='addAndUpdateRow("+data+")' data-toggle='modal' class='padding-right-small label label-success'><i class='glyphicon glyphicon-edit'></i> 编辑</a>";
	             		</shiro:hasPermission>
	             		<shiro:hasPermission name="role:delete">
	             			del ="<a href='#' onclick='deleteRow("+data+")' data-toggle='modal' class='label label label-danger'><i class='glyphicon glyphicon-trash'></i> 删除 </a>";
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
				$('#myModal').deleteRow('system/role/delete.htm?id='+id);
			}
			//增加菜单页面
			function addTreeRow(id){
				$('#insertAndUpdate').addAndUpdateRow("system/role/addTreeHome.htm?codes="+id);
			}
			//增加或者修改受影响的行数
			function addAndUpdateRow(id){
				$('#insertAndUpdate').addAndUpdateRow("system/role/addAndUpdateHome.htm?id="+id);
			}
		</script>
	<div>
		<jsp:include page="/WEB-INF/views/common/delete_dialog.jsp" />
		<jsp:include page="/WEB-INF/views/common/addupdate_dialog.jsp" />
	  <div class="modal fade" id="insertAndUpdate" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	   </div>
	</div>
</div>
</shiro:hasPermission>