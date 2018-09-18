<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<shiro:hasPermission name="managerActivity:select">
	<div class="content1">
		<div class="header">
			<h1 class="page-title">活动列表信息</h1>
			<ul class="breadcrumb">
				<li>活动管理</li>
				<li class="active">活动列表信息</li>
			</ul>
		</div>
		<div class="main-content">
			<div class="row">
				<div class="col-md-2">
					<div class="form-group">
						<div class="input-group">
							<div class="input-group-addon">标题</div>
							<input class="form-control" type="text" id="title">
						</div>
					</div>
				</div>
				<div class="col-md-2">
					<div class="form-group">
						<div class="input-group">
							<div class="input-group-addon">状态</div>
							<select class="form-control" id="state">
								<option value="">请选择</option>
								<option value="1">有效</option>
								<option value="2">无效</option>
							</select>
						</div>
					</div>
				</div>
				<div class="col-md-2">
					<div class="form-group">
						<div class="input-group">
							<div class="input-group-addon">是否热门活动</div>
							<select class="form-control" id="ifHot">
							<option value="">请选择</option>
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
					<shiro:hasPermission name="managerActivity:add">
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
					sAjaxSource:"managerActivity/initTable.htm",
					aoColumns: [
						{ 
						    "data": "title",
						    "sTitle":"标题",
						    'sClass':"text-center"
						  },
			            
						  { 
							    "data": "linkAddress",
							    "sTitle":"热链接",
							    'sClass':"text-center"
							  },
						  { 
							    "data": "auditMan",
							    "sTitle":"用户分类",
							    'sClass':"text-center"
							  },
							  { 
								    "data": "createTime",
								    "sTitle":"创建时间",
								    'sClass':"text-center"
								  },
							  { 
					                "data": "ifHot",
					                "sTitle":"热门活动",
					                'sClass':"text-center",
					                "mRender": function(data, type, full) { 
					                	if(data == 1){
											return "<font color='red'>是</font>";
										}else if(data == 0){
											return "<font color='red'>否</font>";
										}
					                } 
					            },
					          { 
						              "data": "content",
						              "sTitle":"活动内容",
						              'sClass':"text-center"
						      },
					            
			           
			            { 
				               "data": "picOne",
				               "sTitle":"图片",
				               'sClass':"text-center",
				               "mRender": function(data, type, full) { 
				                    return "<img src='" + data + "' height='50px;'/>"; 
				                } 
				            },
				           
				            { 
				                "data": "state",
				                "sTitle":"状态",
				                'sClass':"text-center",
				                "mRender": function(data, type, full) { 
					                	if(data == 1){
											return "<font color='blue'>有效</font>";
										}else{
											return "<font color='red'>无效</font>";
										}
				                } 
				            },
				            
			            { 
			              "data": "beginTime",
			              "sTitle":"开始时间",
			              'sClass':"text-center"
			            },
			            { 
				              "data": "endTime",
				              "sTitle":"结束时间",
				              'sClass':"text-center"
				            },
			            { 
				              "data": "useId",
				              "sTitle":"排序值",
				              'sClass':"text-center"
				            },
						{
							"data": "remark",
							"sTitle":"备注",
							'sClass':"text-center"
						},
				            
				            { 
					              "data": "checkNumber",
					              "sTitle":"活动点击量",
					              'sClass':"text-center",
						          "mRender": function(data, type, full) { 
						                	if(data==null||data==''){
						        	           return 0;
						                	}
						                	return data;
						                } 
					            },
					            { 
						              "data": "pushSuccessNumber",
						              "sTitle":"活动推送人数",
						              'sClass':"text-center",
							          "mRender": function(data, type, full) { 
							        	  if(data==null||data==''){
						        	           return 0;
						                	}
							        	  return data;
						                } 
						            },
					            { 
						              "data": "percentConversion",
						              "sTitle":"活动转化人数",
						              'sClass':"text-center",
							          "mRender": function(data, type, full) { 
							        	  if(data==null||data==''){
						        	           return 0;
						                	}
							        	  return data;
						                } 
						            },
						            
						            
				            
				            
				            
					          
			            <shiro:hasPermission name="managerActivity:update or managerActivity:delete">
			            {"data": "activityId","sTitle":"操作",'sClass':"text-center"}
			            </shiro:hasPermission>
			         ],
					 fnServerParams: function (aoData) {  //查询条件
		             	aoData.push({ "name": "title", "value": $("#title").val() } );
		             	aoData.push({ "name": "state", "value": $("#state").val() } );
		             	aoData.push({ "name": "ifHot", "value": $("#ifHot").val() } );
		             },
		             aoColumnDefs : [
						<shiro:hasPermission name="managerActivity:update or activityelete">
		             	{"aTargets" :15,"mRender" : function(data, type, row){
		             		var edit="",del="";
		             		<shiro:hasPermission name="managerActivity:update">
		             			edit = "<a href='#' onclick='addAndUpdateRow(\""+data+"\")' data-toggle='modal' class='padding-right-small label label-success'><i class='glyphicon glyphicon-edit'></i> 编辑</a>";
		             		</shiro:hasPermission>
		             		<shiro:hasPermission name="managerActivity:delete">
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
				$('#myModal').deleteRow('managerActivity/del.htm?id='+id);
			}
			//增加或者修改受影响的行数
			function addAndUpdateRow(id){
				console.info(id);
				$('#insertAndUpdate').addAndUpdateRow("managerActivity/addAndUpdateHome.htm?id="+id);
			}
			
			//审核
			function audit(id){
				console.info(id);
				$('#insertAndUpdate').addAndUpdateRow("managerActivity/addAndUpdateHome.htm?id="+id);
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