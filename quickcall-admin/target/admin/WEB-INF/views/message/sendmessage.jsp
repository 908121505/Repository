<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<shiro:hasPermission name="message:add:select">
<div class="content1">
	<div class="header">
		<h1 class="page-title">消息发送</h1>
		<ul class="breadcrumb">
			<li>消息管理</li>
			<li class="active">消息发送</li>
		</ul>
	</div>
		<div class="main-content">
			<div class="row">
				<div class="col-md-1">					
					<button type="button" class="btn btn-primary btn-small btn-block" id="add"  onclick="addAndUpdateRow()" ><i class="glyphicon glyphicon-add"></i>+发送消息</button>
				</div>
				<div class="col-md-2">
					<div class="form-group">
						<div class="input-group">
							<div class="input-group-addon">发送人</div>
							<select class="form-control" id="createMan">
							  
							</select>
						</div>
					</div>
				</div>
				<div class="col-md-2">
					<div class="form-group">
						<div class="input-group">
							<div class="input-group-addon">发送开时间</div>
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
				<div class="col-md-1">	
					<div class="form-group">
						<button type="button" class="btn btn-primary btn-small btn-block" id="query"><i class="glyphicon glyphicon-search"></i>查询</button>
					</div>				
				</div>
				<div class="col-md-1">	
					<div class="form-group">
					<a class="btn btn-primary btn-small btn-block" id="message_export" onclick="exportMessage();">导出消息信息</a>
<!-- 						<button type="button" class="btn btn-primary btn-small btn-block" onclick="exportMessage();"><i class="glyphicon glyphicon-search"></i>导出Excel</button> -->
					</div>				
				</div>
			</div>
			<table id="example" class="table"></table>
		</div>
	<div>
	  <jsp:include page="/WEB-INF/views/common/addupdate_dialog.jsp" />
	  <div class="modal fade" id="insertAndUpdate" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	   </div>
	</div>
	<script>
	function exportMessage(){
		var href="export/message.htm"
				+"?createMan="+$("#createMan").val()
				+"&startTime="+$("#startTime").val()
				+"&endTime="+$("#endTime").val();
		$('#message_export').attr("href",href);
	}
			//表格的初始化
			$(document).ready(function() {
				findSendUserSelect("createMan");
				var table = $('#example').initTable({
					sAjaxSource:"message/send/initTable.htm",
					aoColumns: [
			            { "data": "createMan","sTitle":"发送记录"} ,
			            { "data": "createTime","sTitle":"发送时间"}
			         ],
					 fnServerParams: function (aoData) {  //查询条件
		             	aoData.push({ "name": "createMan", "value": $("#createMan").val() },
		             			{ "name": "startTime", "value": $("#startTime").val() },
		             			{ "name": "endTime", "value": $("#endTime").val() }
		             			);
		             },
		             aoColumnDefs : []
				});
				
				 $('#query').click(function(){
					$('#example').dataTable().fnDraw();
				});
			});
			
			//加载发送用户下拉列表值
			 function findSendUserSelect(CompanentId) {
			        $.ajax({
			            url: 'combobox/sendUser.htm',
			            type: 'post',
			            dataType: 'json',
			            success: function (data) {
			                var len = data.length;
					        //此处仅可以使原生态的select加载后台数据
			       		    $("#" + CompanentId + "").find('option').remove();
			                $("#" + CompanentId).append($('<option value=\'\'>' + "---请选择---" + '</option>'));
			                for (i = 0; i < len; i++) {
			                    $("#" + CompanentId).append($('<option value=' + data[i].codes + '>' + data[i].names + '</option>'));
			                }
			            }
			        })
			    }
				//增加或者修改受影响的行数
			 function addAndUpdateRow(){
				$('#insertAndUpdate').addAndUpdateRow("message/send/addAndUpdateHome.htm");
			 }
			</script>
</div>
</shiro:hasPermission>