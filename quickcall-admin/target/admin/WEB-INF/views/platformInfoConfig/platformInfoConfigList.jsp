<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<shiro:hasPermission name="platformInfoConfigController:select">
	<div class="content1">
		<div class="header">
			<h1 class="page-title">合作平台配置列表</h1>
			<ul class="breadcrumb">
				<li>贷款管理</li>
				<li class="active">合作平台配置列表</li>
			</ul>
		</div>
		<div class="main-content">
			<div class="row">
				<div class="col-md-2">
					<div class="form-group">
						<div class="input-group">
							<div class="input-group-addon">合作平台名称</div>
							<input class="form-control" type="text" id="platformName" name="platformName"
							/>
							
								
						</div>
					</div>
				</div>
				<div class="col-md-2">
					<div class="form-group">
						<div class="input-group">
							<div class="input-group-addon">合作平台ID</div>
							<input class="form-control" type="text" id = "platformId" name="platformId"
								/>
								
								
						</div>
					</div>
				</div>
				<div class="col-md-2">
					<div class="form-group">
						<div class="input-group">
							<div class="input-group-addon">状态</div>
							<select class="form-control" id="state">
								<option value="">--请选择--</option>
								<option value="1">开启</option>
								<option value="0">关闭</option>
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
				<div class="col-md-2">
					<shiro:hasPermission name="platformInfoConfigController:add">
						<button type="button" class="btn btn-info btn-small btn-block"
							onclick="addAndUpdateRow(0)">
							<i class="glyphicon glyphicon-plus"></i> 增加
						</button>
					</shiro:hasPermission>
				</div>
				
			</div>
			<table id="example" class="table"></table>
		</div>
		<script>
			//表格的初始化
			$(document).ready(function() {
				var table = $('#example').initTable({
					sAjaxSource:"platformInfoConfigController/initTable.htm",
					aoColumns: [
			            { 
			              "data": "platformName",
			              "sTitle":"合作平台名称",
			              'sClass':"text-center"
			            },
			            { 
			               "data": "platformId",
			               "sTitle":"合作平台ID",
			               'sClass':"text-center"
			            },
			            { 
			                "data": "platformCategory",
			                "sTitle":"平台类别",
			                'sClass':"text-center"
			            },
			            
			            { 
			                "data": "createMan",
			                "sTitle":"编辑人",
			                'sClass':"text-center" 
			            },
			            { 
			                "data": "createTime",
			                "sTitle":"增加时间",
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
			          
			          
			            <shiro:hasPermission name="platformInfoConfigController:update or platformInfoConfigController:delete">
			            ,{"data": "id","sTitle":"操作",'sClass':"text-center"}
			            </shiro:hasPermission>
			         ],
					 fnServerParams: function (aoData) {  //查询条件
		             	aoData.push({ "name": "platformName", "value": $("#platformName").val() } );
		             	aoData.push({ "name": "platformId", "value": $("#platformId").val() } );
		             	aoData.push({ "name": "state", "value": $("#state").val() } );
		             },
		             aoColumnDefs : [
						<shiro:hasPermission name="platformInfoConfigController:update or platformInfoConfigController:delete">
		             	{"aTargets" :6,"mRender" : function(data, type, row){
		             		var edit="",del="";
		             		<shiro:hasPermission name="platformInfoConfigController:update">
		             			edit = "<a href='#' onclick='addAndUpdateRow(\""+data+"\")' data-toggle='modal' class='padding-right-small label label-success'><i class='glyphicon glyphicon-edit'></i> 编辑</a>";
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
				$('#myModal').deleteRow('platformInfoConfigController/del.htm?id='+id);
			}
			//增加或者修改受影响的行数
			function addAndUpdateRow(id){
				$('#insertAndUpdate').addAndUpdateRow("platformInfoConfigController/addAndUpdateHome.htm?id="+id);
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