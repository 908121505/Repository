<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<shiro:hasPermission name="message:select">
	<div class="content1">
		<div class="header">
			<h1 class="input-group datepage-title">消息列表</h1>
			<ul class="breadcrumb">
				<li>消息管理</li>
				<li class="active">消息列表</li>
			</ul>
		</div>
			<div class="col-md-2">
					<shiro:hasPermission name="message:add">
						<button type="button" class="btn btn-info btn-small btn-block"
							onclick="addAndUpdateRow(0)">
							<i class="glyphicon glyphicon-plus"></i> 增加
						</button>
					</shiro:hasPermission>
				</div>
		<div class="main-content">
			<div class="row">
				<div class="col-md-2">
					<div class="form-group">
						<div class="input-group">
							<div class="input-group-addon">手机号码</div>
							<input class="form-control" type="text" name="cellPhone"
								id="cellPhone">
						</div>
					</div>
				</div>
				<div class="col-md-2">
					<div class="form-group">
						<div class="input-group">
							<div class="input-group-addon">发送开始时间</div>
							<input class="form-control" type="date" id="startTime">
						</div>
					</div>
				</div>
				<div class="col-md-2">
					<div class="form-group">
						<div class="input-group">
							<div class="input-group-addon">发送结束时间</div>
							<input class="form-control" type="date" id="endTime">
						</div>
					</div>
				</div>
				<div class="col-md-2">
					<div class="form-group">
						<div class="input-group">
							<div class="input-group-addon">类型</div>
							<select class="form-control" id="type"  name="type">
								<option value="0">请选择类型</option>
								<!-- <option value="1">个人信息</option>-->
								<option value="2">通知信息</option> 
								<option value="3">活动消息</option>
								<option value="4">系统通知</option>
								<!-- <option value="5">邀请好友</option>
								<option value="6">新人有礼</option>
								<option value="7">信用卡首页</option> -->
							</select>
						</div>
					</div>
				</div>
				<div class="col-md-2">
					<div class="form-group">
						<div class="input-group">
							<div class="input-group-addon">读否</div>
							<select class="form-control" id="isRead"  name="isRead">
								<option value="">请选择</option>
								<option value="1">已读</option>
								<option value="0">未读</option>
							</select>
						</div>
					</div>
				</div>
				<div class="col-md-2">
					<button type="button" class="btn btn-primary btn-small btn-block"
						id="query">
						<i class="glyphicon glyphicon-search"></i> 查询
					</button>
				</div>
				<div class="col-md-1">
					<div class="form-group">
						<a class="btn btn-primary btn-small btn-block" id="message_export"
							onclick="exportMessage();">导出消息信息</a>
						<!-- 						<button type="button" class="btn btn-primary btn-small btn-block" onclick="exportMessage();"><i class="glyphicon glyphicon-search"></i>导出Excel</button> -->
					</div>
				</div>
			</div>
			<table id="example" class="table"></table>
		</div>
		<script>
			//表格的初始化
			$(document).ready(function() {
				var table = $('#example').initTable({
					sAjaxSource:"message/initTable.htm",
					aoColumns: [
							{ 
							    "data": "cellPhone",
							    "sTitle":"手机号码",
							    'sClass':"text-center"
							  },
							{ 
							  "data": "title",
							  "sTitle":"标题",
							  'sClass':"text-center"
							},
							{ 
							    "data": "content",
							    "sTitle":"内容",
							    'sClass':"text-center"
							},
							{ 
							    "data": "linkUrlH",
							    "sTitle":"跳H5链接地址",
							    'sClass':"text-center"
							},
							{ 
							    "data": "type",
							    "sTitle":"类型",
							    'sClass':"text-center",
							    "mRender": function(data, type, full) { 
							    	if (data == 1) {
										return "个人信息";
									} else if (data == 2) {
										return "通知信息";
									} else if (data == 3) {
										return "活动消息";
									} else if (data == 4) {
										return "系统通知";
									}else if (data == 5) {
										return "邀请好友";
									} else if (data == 6) {
										return "新人有礼";
									} else if (data == 7) {
										return "信用卡首页";
									}
									return "未知";
							    } 
							},
							{ 
							    "data": "isRead",
							    "sTitle":"读否",
							    'sClass':"text-center",
							    "mRender": function(data, isRead, full) { 
							    	if (data == 1) {
										return "<font>已读</font>";
									} else if (data == 0) {
										return "<font>未读</font>";
									} else {
										return "<font>未知</font>";
									}
							    } 
							},
							{ 
							    "data": "state",
							    "sTitle":"状态",
							    'sClass':"text-center",
							    "mRender": function(data, state, full) { 
							    	if (data == 1) {
										return "<font>有效</font>";
									} else if (data == 2) {
										return "<font>用戶刪除</font>";
									} else {
										return "<font>未知</font>";
									}
							    } 
							},
							  { 
				                "data": "createTime",
				                "sTitle":"创建时间",
				                'sClass':"text-center"
				            }
			            <shiro:hasPermission name="message:update or message:delete">
			            ,{"data": "messageId","sTitle":"操作",'sClass':"text-center"}
			            </shiro:hasPermission>
			         ],
					 fnServerParams: function (aoData) {  //查询条件
						 aoData.push({ "name": "state", "value": $("#state").val() } );
						 aoData.push({ "name": "isRead", "value": $("#isRead ").val() } );
						 aoData.push({ "name": "type", "value": $("#type ").val() } );
						 aoData.push({ "name": "cellPhone", "value": $("#cellPhone ").val() } );
						 aoData.push({ "name": "startTime", "value": $("#startTime").val() } );
						 aoData.push({ "name": "endTime", "value": $("#endTime").val() } );
		             },
		             aoColumnDefs : [
						<shiro:hasPermission name="message:update or message:delete">
		             	{"aTargets" :8,"mRender" : function(data, type, row){
		             		var edit="",del="";
		             		<shiro:hasPermission name="message:update">
		             			edit = "<a href='#' onclick='addAndUpdateRow(\""+data+"\")' data-toggle='modal' class='padding-right-small label label-success'><i class='glyphicon glyphicon-edit'></i> 编辑</a>";
		             		</shiro:hasPermission>
		             		<shiro:hasPermission name="message:delete">
		             			del ="<a href='#' onclick='deleteRow(\""+data+"\")' data-toggle='modal' class='label label label-danger'><i class='glyphicon glyphicon-trash'></i> 删除 </a>";
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
				console.log(id);
				$('#myModal').deleteRow('message/del.htm?id='+id);
			}
			//增加或者修改受影响的行数
			function addAndUpdateRow(id){
				$('#insertAndUpdate').addAndUpdateRow("message/addAndUpdateHome.htm?id="+id);
			}
			function exportMessage(){
				var href="export/message.htm"
						+"?createMan="+$("#createMan").val()
						+"&startTime="+$("#startTime").val()
						+"&endTime="+$("#endTime").val();
						+"&cellPhone="+$("#cellPhone").val();
						+"&linkUrlH="+$("#linkUrlH").val();
						+"&type="+$("#type").val();
						+"&isRead="+$("#isRead").val();
						+"&state="+$("#state").val();
				            
				
				$('#message_export').attr("href",href);
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