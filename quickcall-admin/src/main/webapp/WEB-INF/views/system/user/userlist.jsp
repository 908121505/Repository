<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<shiro:hasPermission name="user:select">
<div class="content1">
	<div class="header">
		<h1 class="page-title">用户列表</h1>
		<ul class="breadcrumb">
			<li>系统管理</li>
			<li class="active">用户列表</li>
		</ul>
	</div>
		<div class="main-content">
			<div class="row">
				<div class="col-md-3">
					<div class="form-group">
						<div class="input-group">
							<div class="input-group-addon">登录账号</div>
							<input class="form-control" type="text" id="account">
						</div>
					</div>
				</div>
				<div class="col-md-2">					
					<button type="button" class="btn btn-primary btn-small btn-block" id="query"><i class="glyphicon glyphicon-search"></i> 查询</button>
				</div>
				<div class="col-md-2">
				     <shiro:hasPermission name="user:add">
					     <button type="button" class="btn btn-info btn-small btn-block" onclick="addAndUpdateRow(0)"><i class="glyphicon glyphicon-plus"></i> 增加</button>
					 </shiro:hasPermission>
				</div>
			</div>
			<table id="example" class="table"></table>
		</div>
	<script>
			//表格的初始化
			$(document).ready(function() {
				var table = $('#example').initTable({
					sAjaxSource:"system/user/initTable.htm",
					aoColumns: [
			            { "data": "account","sTitle":"登录账号" },
			            { "data": "nick_name","sTitle":"用户昵称" },
			            { "data": "user_name","sTitle":"用户姓名"},
			            { "data": "sex","sTitle":"性别"}
			            <shiro:hasPermission name="user:role">
			            ,{ "sTitle":"分配用户角色",'sClass':"text-center"}
			            </shiro:hasPermission>
			            <shiro:hasPermission name="user:update or user:delete">
			            ,{ "data": "id","sTitle":"操作",'sClass':"text-center"}
			            </shiro:hasPermission>
			         ],
					 fnServerParams: function (aoData) {  //查询条件
		             	aoData.push({ "name": "account", "value": $("#account").val() } );
		             },
		             aoColumnDefs : [
						<shiro:hasPermission name="user:role">
						{"aTargets" :4,"mRender" : function(data, type, row){
							var role="";
							<shiro:hasPermission name="user:role">
								role = "<a href='#' onclick='addUserRole("+row.id+")' data-toggle='modal' class='padding-right-small label label-primary'><i class='fa fa-users'></i> 分配角色</a>";
							</shiro:hasPermission>
							return role;
						}},
						</shiro:hasPermission>
						<shiro:hasPermission name="user:update or user:delete">
		             	{"aTargets" :5,"mRender" : function(data, type, full){
		             		var edit="",del="";
		             		<shiro:hasPermission name="user:update">
		             			edit = "<a href='#' onclick='addAndUpdateRow("+data+")' data-toggle='modal' class='padding-right-small label label-success'><i class='glyphicon glyphicon-edit'></i> 编辑</a>";
		             		</shiro:hasPermission>
		             		<shiro:hasPermission name="user:delete">
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
				$('#myModal').deleteRow('system/user/delete.htm?id='+id);
			}
			//增加或者修改受影响的行数
			function addAndUpdateRow(id){
				$('#insertAndUpdate').addAndUpdateRow("system/user/addAndUpdateHome.htm?id="+id);
			}
			function addUserRole(id){
				$('#insertAndUpdate').addAndUpdateRow('system/user/addUserRole.htm?id='+id);
			}
			</script>
		<!---dialog选项-->
	<div>
		<jsp:include page="/WEB-INF/views/common/delete_dialog.jsp" />
		<jsp:include page="/WEB-INF/views/common/addupdate_dialog.jsp" />
	  
	  <div class="modal fade" id="insertAndUpdate" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	   </div>
	</div>
</div>
</shiro:hasPermission>