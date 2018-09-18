<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<shiro:hasPermission name="autoComment:select">
	<div class="content1">
		<div class="header">
			<h1 class="page-title">自动评论列表</h1>
			<ul class="breadcrumb">
				<li>贷款管理</li>
				<li class="active">自动评论列表</li>
			</ul>
		</div>
		<div class="main-content">
			<div class="row">
			  
			  <div class="col-md-2">
					<div class="form-group">
						<div class="input-group">
							<div class="input-group-addon">评论内容</div>
							<input class="form-control" type="text" id="contents">
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
							<div class="input-group-addon">评论状态</div>
							<select class="form-control" id="commStates">
								<option value="">--请选择--</option>
								<option value="1">帖子评论</option>
								<option value="2">文章评论</option>
							</select>
						</div>
					</div>
				</div>
				
				 <div class="col-md-4">
                    <div class="form-group">
                        <div class="input-group">
                            <div class="input-group-addon">创建时间</div>
                            <input class="form-control" type="date" id="startTime">
                            <div class="input-group-addon">到</div>
                            <input class="form-control" type="date" id="endTime">
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
					<shiro:hasPermission name="autoComment:add">
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
					sAjaxSource:"autoComment/initTable.htm",
					aoColumns: [
			            
						{ 
						    "data": "id",
						    "sTitle":"评论ID",
						    'sClass':"text-center"
						  },
			            { 
			              "data": "content",
			              "sTitle":"评论内容",
			              'sClass':"text-center"
			            },
			            { 
			              "data": "commonType",
			              "sTitle":"评论类型",
			              'sClass':"text-center",
				                "mRender": function(data, type, full) { 
				                	if(data == 1){
										return "<font color='red'>帖子评论</font>";
									}else{
										return "<font color='blue'>文章评论</font>";
									}
				                } 
				        },
			            
			            
			            { 
			               "data": "createTime",
			               "sTitle":"创建时间",
			               'sClass':"text-center"
			            },
			            { 
				               "data": "createTime",
				               "sTitle":"创建人",
				               'sClass':"text-center"
				        },
				        { 
				               "data": "modifyTime",
				               "sTitle":"更新时间",
				               'sClass':"text-center"
				        },
				        { 
					               "data": "modifyMan",
					               "sTitle":"更新人",
					               'sClass':"text-center"
					    },
					    { 
				               "data": "sort",
				               "sTitle":"排序",
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
			            <shiro:hasPermission name="autoComment:update or autoComment:delete">
			            ,{"data": "id","sTitle":"操作",'sClass':"text-center"}
			            </shiro:hasPermission>
			         ],
					 fnServerParams: function (aoData) {  //查询条件
		             	aoData.push({ "name": "state", "value": $("#state").val() } );
		             	aoData.push({ "name": "content", "value": $("#contents").val() } );
		             	aoData.push({ "name": "commonType", "value": $("#commStates").val() } );
		             	aoData.push({ "name": "state", "value": $("#state").val() } );
		             	aoData.push({ "name": "startTime", "value": $("#startTime").val() } );
		             	aoData.push({ "name": "endTime", "value": $("#endTime").val() } );
		             	
		             	
		             },
		             aoColumnDefs : [
						<shiro:hasPermission name="autoComment:update or autoComment:delete">
		             	{"aTargets" :9,"mRender" : function(data, type, row){
		             		var edit="",del="";
		             		<shiro:hasPermission name="autoComment:update">
		             			edit = "<a href='#' onclick='addAndUpdateRow(\""+data+"\")' data-toggle='modal' class='padding-right-small label label-success'><i class='glyphicon glyphicon-edit'></i> 编辑</a>";
		             		</shiro:hasPermission>
		             		<shiro:hasPermission name="autoComment:delete">
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
				$('#myModal').deleteRow('autoComment/del.htm?id='+id);
			}
			//增加或者修改受影响的行数
			function addAndUpdateRow(id){
				$('#insertAndUpdate').addAndUpdateRow("autoComment/addAndUpdateHome.htm?id="+id);
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