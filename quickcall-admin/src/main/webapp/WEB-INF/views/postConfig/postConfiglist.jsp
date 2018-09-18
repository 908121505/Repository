<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<shiro:hasPermission name="postConfig:select">
	<div class="content1">
		<div class="header">
			<h1 class="page-title">虚拟数据配置列表</h1>
			<ul class="breadcrumb">
				<li>贷款管理</li>
				<li class="active">虚拟数据配置列表</li>
			</ul>
		</div>
		<div class="main-content">
			<div class="row">
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
				
				
				<div class="col-md-2">
					<button type="button" class="btn btn-primary btn-small btn-block"
						id="query">
						<i class="glyphicon glyphicon-search"></i> 查询
					</button>
				</div>
				<div class="col-md-2">
					<shiro:hasPermission name="postConfig:add">
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
					sAjaxSource:"postConfig/initTable.htm",
					aoColumns: [
			            { 
			              "data": "name",
			              "sTitle":"数据名称",
			              'sClass':"text-center"
			            },
			            { 
			               "data": "twoHours",
			               "sTitle":"2h增长数据",
			               'sClass':"text-center"
			            },
			            { 
				               "data": "fourHours",
				               "sTitle":"4h增长数据",
				               'sClass':"text-center"
				         },
				         { 
				               "data": "sixHours",
				               "sTitle":"6h增长数据",
				               'sClass':"text-center"
				        },
				        { 
				               "data": "eightHours",
				               "sTitle":"8h增长数据",
				               'sClass':"text-center"
				        },
				         { 
					               "data": "tenHours",
					               "sTitle":"10h增长数据",
					               'sClass':"text-center"
					     },
					     { 
					               "data": "twelveHours",
					               "sTitle":"12h增长数据",
					               'sClass':"text-center"
					     },
			            
			            { 
			                "data": "createTime",
			                "sTitle":"创建时间",
			                'sClass':"text-center"
			            },
			            { 
			                "data": "createMan",
			                "sTitle":"创建人",
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
			            <shiro:hasPermission name="postConfig:update or postConfig:delete">
			            ,{"data": "id","sTitle":"操作",'sClass':"text-center"}
			            </shiro:hasPermission>
			         ],
					 fnServerParams: function (aoData) {  //查询条件
		             	aoData.push({ "name": "startTime", "value": $("#startTime").val() } );
		             	aoData.push({ "name": "endTime", "value": $("#endTime").val() } );
		             	aoData.push({ "name": "state", "value": $("#state").val() } );
		             },
		             aoColumnDefs : [
						<shiro:hasPermission name="postConfig:update or postConfig:delete">
		             	{"aTargets" :10,"mRender" : function(data, type, row){
		             		var edit="",del="";
		             		<shiro:hasPermission name="postConfig:update">
		             			edit = "<a href='#' onclick='addAndUpdateRow(\""+data+"\")' data-toggle='modal' class='padding-right-small label label-success'><i class='glyphicon glyphicon-edit'></i> 编辑</a>";
		             		</shiro:hasPermission>
		             		<shiro:hasPermission name="postConfig:delete">
		             			del ="<a href='#' onclick='deleteRow(\""+data+"\")' data-toggle='modal' class='label label label-danger'><i class='glyphicon glyphicon-trash'></i> 删除 </a>";
		             		</shiro:hasPermission>
		                    return edit;
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
				$('#myModal').deleteRow('postConfig/del.htm?id='+id);
			}
			//增加或者修改受影响的行数
			function addAndUpdateRow(id){
				$('#insertAndUpdate').addAndUpdateRow("postConfig/addAndUpdateHome.htm?id="+id);
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