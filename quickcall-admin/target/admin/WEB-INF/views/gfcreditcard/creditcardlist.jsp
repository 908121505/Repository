<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<shiro:hasPermission name="gfCreditCard:select">
	<div class="content1">
		<div class="header">
			<h1 class="page-title">广发信用卡申请列表</h1>
			<ul class="breadcrumb">
				<li>广发信用卡管理</li>
				<li class="active">广发信用卡申请列表</li>
			</ul>
		</div>
		<div class="main-content">
			<div class="row">
				
				<div class="main-content">
			<div class="row">
				<div class="col-md-2">
					<div class="form-group">
						<div class="input-group">
							<div class="input-group-addon">客户名称</div>
							<input class="form-control" type="text" name="name" id="name">
						</div>
					</div>
				</div>
				<div class="col-md-2">
					<div class="form-group">
						<div class="input-group">
							<div class="input-group-addon">手机号</div>
							<input class="form-control" type="text" name="mobile" id="mobile">
						</div>
					</div>
				</div>
				
				<div class="col-md-2">
					<div class="form-group">
						<div class="input-group">
							<div class="input-group-addon">公司名称</div>
							<input class="form-control" type="text" name="companyName" id="companyName">
						</div>
					</div>
				</div>
				
				<div class="col-md-2">
					<div class="form-group">
						<div class="input-group">
							<div class="input-group-addon">状态</div>
							<select class="form-control" name="state" id="state">
								<option value="">--请选择--</option>
								<option value="1">开启</option>
								<option value="2">关闭</option>
							</select>
						</div>
					</div>
				</div>
				
				
				<div class="col-md-1">
					<button type="button" class="btn btn-primary btn-small btn-block"
						id="query">
						<i class="glyphicon glyphicon-search"></i> 查询
					</button>
				</div>
				<div class="col-md-1">
					<shiro:hasPermission name="gfCreditCard:add">
						<button type="button" class="btn btn-info btn-small btn-block"
							onclick="addAndUpdateRow(0)">
							<i class="glyphicon glyphicon-plus"></i> 增加
						</button>
					</shiro:hasPermission>
				</div>
				<div class="col-md-1">	
					<div class="form-group">
					<a class="btn btn-primary btn-small btn-block" id="message_export" onclick="exportMessage();">导出信用卡信息</a>
<!-- 						<button type="button" class="btn btn-primary btn-small btn-block" onclick="exportMessage();"><i class="glyphicon glyphicon-search"></i>导出Excel</button> -->
					</div>				
				</div>
			</div>
			<table id="example" class="table"></table>
		</div>
		<script>
		
		
		function exportMessage(){
			
			var href="export/gfCredCard.htm"
					+"?name="+$("#name").val()
					+"&mobile="+$("#mobile").val();
					+"&companyName="+$("#companyName").val();
					+"&state="+$("#state").val();
			            
			$('#message_export').attr("href",href);
		}
		
			//表格的初始化
			$(document).ready(function() {
				var table = $('#example').initTable({
					sAjaxSource:"gfCreditCard/initTable.htm",
					aoColumns: [
			           
					            
					            { 
						              "data": "name",
						              "sTitle":"客户名称",
						              'sClass':"text-center",
						            	     "mRender": function(data, type, full) { 
								                	 return data;
								                }
						            },
						            { 
						               "data": "email",
						               "sTitle":"邮箱",
						               'sClass':"text-center" ,
					            	     "mRender": function(data, type, full) { 
						                	 return data;
						                }
						            },
						            { 
						                "data": "mobile",
						                "sTitle":"手机号",
						                'sClass':"text-center",
					            	     "mRender": function(data, type, full) { 
						                	 return data;
						                }
						            },
						            { 
						                "data": "companyName",
						                "sTitle":"公司名称",
						                'sClass':"text-center",
					            	     "mRender": function(data, type, full) { 
						                	 return data;
						                }
						              
						            },
						            { 
						                "data": "liveAddress",
						                "sTitle":"居住地详细地址",
						                'sClass':"text-center",
					            	     "mRender": function(data, type, full) { 
						                	 return data;
						                }
						            },{ 
						                "data": "state",
						                "sTitle":"状态",
						                'sClass':"text-center",
						                "mRender": function(data, type, full) { 
						                	if(data == 1){
												return "<font color='red'>开启</font>";
											}else if(data == 2){
												return "<font color='blue'>关闭</font>";
											}
						                } 
						            }
			            
			            
			            <shiro:hasPermission name="gfCreditCard:update or gfCreditCard:delete">
			            ,{"data": "gfCreditCardId","sTitle":"操作",'sClass':"text-center"}
			            </shiro:hasPermission>
			         ],
					 fnServerParams: function (aoData) {  //查询条件
						    aoData.push({ "name": "name", "value": $("#name").val() } );
			             	aoData.push({ "name": "mobile", "value": $("#mobile").val() } );
			            	aoData.push({ "name": "companyName", "value": $("#companyName").val() } );
			            	aoData.push({ "name": "state", "value": $("#state").val() } );
		             },
		             aoColumnDefs : [
						<shiro:hasPermission name="gfCreditCard:update or gfCreditCard:delete">
		             	{"aTargets" :6,"mRender" : function(data, type, row){
		             		var edit="",del="",detail="";
		             		<shiro:hasPermission name="gfCreditCard:update">
		             			edit = "<a href='#' onclick='addAndUpdateRow(\""+data+"\")' data-toggle='modal' class='padding-right-small label label-success'><i class='glyphicon glyphicon-edit'></i> 编辑</a>";
		             		</shiro:hasPermission>
		             		<shiro:hasPermission name="gfCreditCard:delete">
		             			del ="<a href='#' onclick='deleteRow(\""+data+"\")' data-toggle='modal' class='label label label-danger'><i class='glyphicon glyphicon-trash'></i> 删除 </a>";
		             		</shiro:hasPermission>
		             		detail = "<a href='#' onclick='detailRow(\""+data+"\")' data-toggle='modal' class='padding-right-small label label-success'><i class='glyphicon glyphicon-edit'></i>详情</a>";
	             		    return edit+"&nbsp;"+del+"&nbsp;"+detail;
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
				$('#myModal').deleteRow('gfCreditCard/del.htm?id='+id);
			}
			//增加或者修改受影响的行数
			function addAndUpdateRow(id){
				$('#insertAndUpdate').addAndUpdateRow("gfCreditCard/addAndUpdateHome.htm?id="+id);
			}
			
			function detailRow(id){
				$('#insertAndUpdate').addAndUpdateRow("gfCreditCard/detail.htm?id="+id);
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