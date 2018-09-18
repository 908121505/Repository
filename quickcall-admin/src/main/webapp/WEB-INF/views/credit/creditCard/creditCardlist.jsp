<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<shiro:hasPermission name="creditCard:select">
	<div class="content1">
		<div class="header">
			<h1 class="page-title">信用卡列表</h1>
			<ul class="breadcrumb">
				<li>银行管理</li>
				<li class="active">信用卡列表</li>
			</ul>
		</div>
		<div class="main-content">
			<div class="row">
				<div class="col-md-2">
					<div class="form-group">
						<div class="input-group">
							<div class="input-group-addon">信用卡名称</div>
							<input class="form-control" type="text" id="name">
						</div>
					</div>
				</div>
				<div class="col-md-2">
					<div class="form-group">
						<div class="input-group">
							<div class="input-group-addon">状态</div>
							<select class="form-control" id="state">
							  <option value="1">有效</option>
							  <option value="0">无效</option>
							</select>
						</div>
					</div>
				</div>
					<div class="col-md-2">
					<div class="form-group">
						<div class="input-group">
							<div class="input-group-addon">是否推荐</div>
							<select class="form-control" id="isRecommend">
							  <option value="1">是</option>
							  <option value="0">否</option>
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
					<shiro:hasPermission name="creditCard:add">
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
					sAjaxSource:"creditCard/initTable.htm",
					aoColumns: [
						{ 
						    "data": "name",
						    "sTitle":"信用卡名称",
						    'sClass':"text-center"
						  },
			            
			            { 
			                "data": "state",
			                "sTitle":"状态",
			                'sClass':"text-center",
			                "mRender": function(data, type, full) { 
			                	if(data == 1){
									return "<font color='red'>有效</font>";
								}else{
									return "<font color='blue'>无效</font>";
								}
			                } 
			            },
			            { 
			                "data": "isRecommend",
			                "sTitle":"是否推荐",
			                'sClass':"text-center",
			                "mRender": function(data, type, full) { 
			                	if(data == 1){
									return "<font color='red'>是</font>";
								}else{
									return "<font color='blue'>否</font>";
								}
			                } 
			            },
			            { 
				               "data": "image",
				               "sTitle":"信用卡图片",
				               'sClass':"text-center",
				               "mRender": function(data, type, full) { 
				                    return "<img src='" + data + "' height='50px;'/>"; 
				                } 
				            },
			            { 
			              "data": "url",
			              "sTitle":"跳转URL",
			              'sClass':"text-center"
			            },
			            { 
				               "data": "imageBack",
				               "sTitle":"信用卡图片(背景图片)",
				               'sClass':"text-center",
				               "mRender": function(data, type, full) { 
				                    return "<img src='" + data + "' height='50px;'/>"; 
				                } 
				            },
				            
			            { 
			              "data": "serialNum",
			              "sTitle":"排序",
			              'sClass':"text-center"
			            },
			            { 
				              "data": "remark",
				              "sTitle":"备注",
				              'sClass':"text-center"
				            }
			            <shiro:hasPermission name="creditCard:update or creditCard:delete">
			            ,{"data": "creditCardId","sTitle":"操作",'sClass':"text-center"}
			            </shiro:hasPermission>
			         ],
					 fnServerParams: function (aoData) {  //查询条件
		             	aoData.push({ "name": "name", "value": $("#name").val() } );
		             	aoData.push({ "name": "state", "value": $("#state").val() } );
		             	aoData.push({ "name": "isRecommend", "value": $("#isRecommend").val() } );
		             },
		             aoColumnDefs : [
						<shiro:hasPermission name="creditCard:update or creditCard:delete">
		             	{"aTargets" :8,"mRender" : function(data, type, row){
		             		var edit="",del="";
		             		<shiro:hasPermission name="creditCard:update">
		             			edit = "<a href='#' onclick='addAndUpdateRow(\""+data+"\")' data-toggle='modal' class='padding-right-small label label-success'><i class='glyphicon glyphicon-edit'></i> 编辑</a>";
		             		</shiro:hasPermission>
		             		<shiro:hasPermission name="creditCard:delete">
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
				$('#myModal').deleteRow('creditCard/del.htm?id='+id);
			}
			//增加或者修改受影响的行数
			function addAndUpdateRow(id){
				console.info(id);
				$('#insertAndUpdate').addAndUpdateRow("creditCard/addAndUpdateHome.htm?id="+id);
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