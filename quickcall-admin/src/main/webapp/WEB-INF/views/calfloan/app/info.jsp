<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<shiro:hasPermission name="appinfo:select">
	<div class="content1">
		<div class="header">
			<h1 class="page-title">app启动信息</h1>
			<ul class="breadcrumb">
				<li>贷款管理</li>
				<li class="active">app启动信息</li>
			</ul>
		</div>
		<div class="main-content">
			<%-- <div class="row">
				<div class="col-md-3">
					<div class="form-group">
						<div class="input-group">
							<div class="input-group-addon">名称</div>
							<input class="form-control" type="text" id="loanTitle">
						</div>
					</div>
				</div>
				<div class="col-md-2">
					<button type="button" class="btn btn-primary btn-small btn-block"
						id="query">
						<i class="glyphicon glyphicon-search"></i> 查询
					</button>
				</div>
				<div class="col-md-2">
					<shiro:hasPermission name="appinfo:add">
						<button type="button" class="btn btn-info btn-small btn-block"
							onclick="addAndUpdateRow(0)">
							<i class="glyphicon glyphicon-plus"></i> 增加
						</button>
					</shiro:hasPermission>
				</div>
			</div> --%>
			<table id="example" class="table"></table>
		</div>
		<script>
			//表格的初始化
			$(document).ready(function() {
				var table = $('#example').initTable({
					sAjaxSource:"appinfo/initTable.htm",
					aoColumns: [
			            { 
			              "data": "title",
			              "sTitle":"名称",
			              'sClass':"text-center"
			            },
			            { 
			               "data": "image",
			               "sTitle":"图片",
			               'sClass':"text-center",
			               "mRender": function(data, type, full) { 
			                    return "<img src='" + data + "' height='50px;'/>"; 
			                } 
			            },
			            { 
			                "data": "url",
			                "sTitle":"链接",
			                'sClass':"text-center"
			            },
			            { 
			                "data": "state",
			                "sTitle":"状态",
			                'sClass':"text-center",
			                "mRender": function(data, type, full) { 
			                	if(data == 1){
									return "<font color='red'>开启</font>";
								}else{
									return "<font color='blue'>关闭</font>";
								}
			                } 
			            }
			            <shiro:hasPermission name="appinfo:update or appinfo:delete">
			            ,{"data": "appinfoId","sTitle":"操作",'sClass':"text-center"}
			            </shiro:hasPermission>
			         ],
					 fnServerParams: function (aoData) {  //查询条件
		             	aoData.push({ "name": "loanTitle", "value": $("#loanTitle").val() } );
		             },
		             aoColumnDefs : [
						<shiro:hasPermission name="appinfo:update or appinfo:delete">
		             	{"aTargets" :4,"mRender" : function(data, type, row){
		             		var edit="",del="";
		             		<shiro:hasPermission name="appinfo:update">
		             			edit = "<a href='#' onclick='addAndUpdateRow()' data-toggle='modal' class='padding-right-small label label-success'><i class='glyphicon glyphicon-edit'></i> 编辑</a>";
		             		</shiro:hasPermission>
		                    return edit;
		                }
		             }
		            	</shiro:hasPermission>
		             ]
				});
				
				 $('#query').click(function(){
					$('#example').dataTable().fnDraw();
				});
			
			});
			//增加或者修改受影响的行数
			function addAndUpdateRow(){
				$('#insertAndUpdate').addAndUpdateRow("appinfo/addAndUpdateHome.htm?id=1");
			}
			</script>
		<!---dialog选项-->
		<div>
			<jsp:include page="/WEB-INF/views/common/delete_dialog.jsp" />
			<jsp:include page="/WEB-INF/views/common/addupdate_dialog.jsp" />

			<div class="modal fade" id="insertAndUpdate" tabindex="-1"
				role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			</div>
		</div>
	</div>
</shiro:hasPermission>