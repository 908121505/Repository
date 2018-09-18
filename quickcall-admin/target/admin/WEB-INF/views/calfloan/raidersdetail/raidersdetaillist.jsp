<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<shiro:hasPermission name="raidersdetail:select">
	<div class="content1">
		<div class="header">
			<h1 class="page-title">攻略详情列表</h1>
			<ul class="breadcrumb">
				<li>攻略管理</li>
				<li class="active">攻略详情列表</li>
			</ul>
		</div>
		<div class="main-content">
			<div class="row">
				<div class="col-md-2">
					<div class="form-group">
						<div class="input-group">
							<div class="input-group-addon">标题</div>
							<input class="form-control" type="text" id="raidersTitle">
						</div>
					</div>
				</div>
				<div class="col-md-2">
					<div class="form-group">
						<div class="input-group">
							<div class="input-group-addon">类型</div>
							<select class="form-control" id="raidersCateId" name="raidersCateId">
								<option value="">--请选择--</option>
								<c:forEach var="info" items="${infos }">
									<option value="${info.raidersCateId }">${info.raidersTitle }</option>
								</c:forEach>
							</select>
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
					<div class="form-group">
						<div class="input-group">
							<div class="input-group-addon">是否推荐</div>
							<select class="form-control" id="isPush">
								<option value="">--请选择--</option>
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
					<shiro:hasPermission name="raidersdetail:add">
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
					sAjaxSource:"raidersdetail/initTable.htm",
					aoColumns: [
			            { 
			              "data": "raidersTitle",
			              "sTitle":"标题",
			              'sClass':"text-center"
			            },
			            { 
				              "data": "titleType",
				              "sTitle":"类型",
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
			                "data": "description",
			                "sTitle":"简介",
			                'sClass':"text-center"
			            },
			            { 
			                "data": "readNum",
			                "sTitle":"阅读数",
			                'sClass':"text-center"
			            },
			            { 
			                "data": "isPush",
			                "sTitle":"推荐",
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
			            	"data": "sort",
			            	"sTitle":"序号",
			            	'sClass':"text-center"
			            },
			            { 
			                "data": "remark",
			                "sTitle":"备注",
			                'sClass':"text-center"
			            }
			            <shiro:hasPermission name="raidersdetail:update or raidersdetail:delete">
			            ,{"data": "raidersDetailId","sTitle":"操作",'sClass':"text-center"}
			            </shiro:hasPermission>
			         ],
					 fnServerParams: function (aoData) {  //查询条件
		             	aoData.push({ "name": "raidersTitle", "value": $("#raidersTitle").val() } );
		             	aoData.push({ "name": "isPush", "value": $("#isPush").val() } );
		             	aoData.push({ "name": "state", "value": $("#state").val() } );
		             	aoData.push({ "name": "raidersCateId", "value": $("#raidersCateId").val() } );
		             },
		             aoColumnDefs : [
						<shiro:hasPermission name="raidersdetail:update or raidersdetail:delete">
		             	{"aTargets" :9,"mRender" : function(data, type, row){
		             		var edit="",del="";
		             		<shiro:hasPermission name="raidersdetail:update">
		             			edit = "<a href='#' onclick='addAndUpdateRow(\""+data+"\")' data-toggle='modal' class='padding-right-small label label-success'><i class='glyphicon glyphicon-edit'></i> 编辑</a>";
		             		</shiro:hasPermission>
		             		<shiro:hasPermission name="raidersdetail:delete">
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
				$('#myModal').deleteRow('raidersdetail/del.htm?id='+id);
			}
			//增加或者修改受影响的行数
			function addAndUpdateRow(id){
				$('#insertAndUpdate').addAndUpdateRow("raidersdetail/addAndUpdateHome.htm?id="+id);
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