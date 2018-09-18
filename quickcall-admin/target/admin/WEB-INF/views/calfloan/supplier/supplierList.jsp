<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<shiro:hasPermission name="supplier:select">
	<div class="content1">
		<div class="header">
			<h1 class="page-title">供应商列表</h1>
			<ul class="breadcrumb">
				<li>供应商管理</li>
				<li class="active">供应商列表</li>
			</ul>
		</div>
		<div class="main-content">
			<div class="row">
				<div class="col-md-2">
					<div class="form-group">
						<div class="input-group">
							<div class="input-group-addon">供应商名称</div>
							<input class="form-control" type="text" id="supplierName">
						</div>
					</div>
				</div>
				
				<div class="col-md-2">
					<div class="form-group">
						<div class="input-group">
							<div class="input-group-addon">供应商代码</div>
							<input class="form-control" type="text" id="supplierCode">
						</div>
					</div>
				</div>
				
				<div class="col-md-2">
					<div class="form-group">
						<div class="input-group">
							<div class="input-group-addon">供应商别名</div>
							<input class="form-control" type="text" id="supplierAlias">
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
					<shiro:hasPermission name="supplier:add">
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
					sAjaxSource:"supplier/initTable.htm",
					aoColumns: [
			            { 
			              "data": "supplierName",
			              "sTitle":"供应商名称",
			              'sClass':"text-center"
			            },
			            { 
			               "data": "supplierIcon",
			               "sTitle":"图标",
			               'sClass':"text-center",
			               "mRender": function(data, type, full) { 
				            	
				            	return "<img src='" + data + "' height='50px;'/>"; 
			              } 
			            },
			            { 
			                "data": "supplierLink",
			                "sTitle":"供应商链接",
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
			            },
			            { 
			                "data": "supplierCode",
			                "sTitle":"供应商代码",
			                'sClass':"text-center"
			            },
			            { 
			                "data": "supplierAlias",
			                "sTitle":"供应商别名",
			                'sClass':"text-center"
			            },
			            { 
			                "data": "remark",
			                "sTitle":"备注",
			                'sClass':"text-center"
			            }
			            <shiro:hasPermission name="supplier:update or supplier:delete">
			            ,{"data": "supplierId","sTitle":"操作",'sClass':"text-center"}
			            </shiro:hasPermission>
			         ],
					 fnServerParams: function (aoData) {  //查询条件
		             	aoData.push({ "name": "supplierName", "value": $("#supplierName").val() } );
		             	aoData.push({ "name": "supplierCode", "value": $("#supplierCode").val() } );
		            	aoData.push({ "name": "supplierAlias", "value": $("#supplierAlias").val() } );
		             	aoData.push({ "name": "state", "value": $("#state").val() } );
		             
					 
					 },
		             aoColumnDefs : [
						<shiro:hasPermission name="supplier:update or supplier:delete">
		             	{"aTargets" :7,"mRender" : function(data, type, row){
		             		var edit="",del="";
		             		<shiro:hasPermission name="supplier:update">
		             			edit = "<a href='#' onclick='addAndUpdateRow(\""+data+"\")' data-toggle='modal' class='padding-right-small label label-success'><i class='glyphicon glyphicon-edit'></i> 编辑</a>";
		             		</shiro:hasPermission>
		             		<shiro:hasPermission name="supplier:delete">
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
				$('#myModal').deleteRow('supplier/del.htm?id='+id);
			}
			//增加或者修改受影响的行数
			function addAndUpdateRow(id){
				$('#insertAndUpdate').addAndUpdateRow("supplier/addAndUpdateHome.htm?id="+id);
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