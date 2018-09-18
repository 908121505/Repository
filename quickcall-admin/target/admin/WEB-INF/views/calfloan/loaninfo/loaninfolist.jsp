<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<shiro:hasPermission name="loaninfo:select">
	<div class="content1">
		<div class="header">
			<h1 class="page-title">贷款App列表</h1>
			<ul class="breadcrumb">
				<li>贷款管理</li>
				<li class="active">贷款App列表</li>
			</ul>
		</div>
		<div class="main-content">
			<div class="row">
				<div class="col-md-2">
					<div class="form-group">
						<div class="input-group">
							<div class="input-group-addon">名称</div>
							<input class="form-control" type="text" id="appName">
						</div>
					</div>
				</div>
				<div class="col-md-2">
					<div class="form-group">
						<div class="input-group">
							<div class="input-group-addon">类型</div>
							<select class="form-control" id="loanCateId" name="loanCateId">
								<option value="">--请选择--</option>
								<c:forEach var="info" items="${infos }">
									<option value="${info.loanCateId }">${info.loanTitle }</option>
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
							<select class="form-control" id=isRecommend>
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
					<shiro:hasPermission name="loaninfo:add">
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
					sAjaxSource:"loaninfo/initTable.htm",
					aoColumns: [
			            { 
			              "data": "appName",
			              "sTitle":"名称",
			              'sClass':"text-center"
			            },
			            { 
				              "data": "viceAppName",
				              "sTitle":"副名称",
				              'sClass':"text-center"
				            },
			            { 
			               "data": "imageUrl",
			               "sTitle":"图片",
			               'sClass':"text-center",
			               "mRender": function(data, type, full) { 
			                    return "<img src='" + data + "' height='50px;'/>"; 
			                } 
			            },
			            { 
				              "data": "loanCateId",
				              "sTitle":"类型",
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
			                "data": "linkUrlH",
			                "sTitle":"简介",
			                'sClass':"text-center"
			            },
			            { 
			                "data": "applicantsNum",
			                "sTitle":"申请人数",
			                'sClass':"text-center"
			            },
			            { 
			                "data": "sort",
			                "sTitle":"排序",
			                'sClass':"text-center"
			            },
			            { 
			                "data": "remark",
			                "sTitle":"备注",
			                'sClass':"text-center"
			            }
			            <shiro:hasPermission name="loaninfo:update or loaninfo:delete">
			            ,{"data": "loanInfoId","sTitle":"操作",'sClass':"text-center"}
			            </shiro:hasPermission>
			         ],
					 fnServerParams: function (aoData) {  //查询条件
		             	aoData.push({ "name": "appName", "value": $("#appName").val() } );
		             	aoData.push({ "name": "state", "value": $("#state").val() } );
		             	aoData.push({ "name": "isRecommend", "value": $("#isRecommend").val() } );
		             	aoData.push({ "name": "loanCateId", "value": $("#loanCateId").val() } );
		             },
		             aoColumnDefs : [
						<shiro:hasPermission name="loaninfo:update or loaninfo:delete">
		             	{"aTargets" :10,"mRender" : function(data, type, row){
		             		var edit="",del="";
		             		<shiro:hasPermission name="loaninfo:update">
		             			edit = "<a href='#' onclick='addAndUpdateRow(\""+data+"\")' data-toggle='modal' class='padding-right-small label label-success'><i class='glyphicon glyphicon-edit'></i> 编辑</a>";
		             		</shiro:hasPermission>
		             		<shiro:hasPermission name="loaninfo:delete">
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
				$('#myModal').deleteRow('loaninfo/del.htm?id='+id);
			}
			//增加或者修改受影响的行数
			function addAndUpdateRow(id){
				$('#insertAndUpdate').addAndUpdateRow("loaninfo/addAndUpdateHome.htm?id="+id);
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