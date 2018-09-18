<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<div class="content1">
	<div class="header">
		<h1 class="page-title">程序日志</h1>
		<ul class="breadcrumb">
			<li>系统管理</li>
			<li class="active">程序日志</li>
		</ul>
	</div>
		<div class="main-content">
			<table id="example" class="table"></table>
		</div>
	<script>
			//表格的初始化
			$(document).ready(function() {
				var table = $('#example').initTable({
					sAjaxSource:"system/projectlog/initTable.htm",
					aoColumns: [
			            { "data": "file_name","sTitle":"日志名称" },
			            { "data": "file_size","sTitle":"日志大小" }
			            <shiro:hasPermission name="user:update or user:delete">
			            ,{"sTitle":"操作",'sClass':"text-center"}
			            </shiro:hasPermission>
			         ],
					 fnServerParams: function (aoData) {  //查询条件
		             	aoData.push({ "name": "account", "value": $("#account").val() } );
		             },
		             aoColumnDefs : [
						{"aTargets" :2,"mRender" : function(data, type, row){
							<shiro:hasPermission name="user:role">
								return "<a href='system/projectlog/download.htm?fileName="+row.file_name+"' data-toggle='modal' class='padding-right-small label label-primary'><i class='fa fa-users'></i> 下载程序日志</a>";
							</shiro:hasPermission>
							return null;
						}},
		             ]
				});
			});
		</script>
</div>
