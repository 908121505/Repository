<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<shiro:hasPermission name="sms:select">
	<div class="content1">
		<div class="header">
			<h1 class="page-title">短信记录</h1>
			<ul class="breadcrumb">
				<li>短信管理</li>
				<li class="active">短信记录</li>
			</ul>
		</div>
		
			<div class="row">
				<!-- <div class="col-md-2">
					<button type="button" class="btn btn-primary btn-small btn-block"
						id="query">
						<i class="glyphicon glyphicon-search"></i> 查询
					</button>
				</div> -->
				<div class="col-md-2">
					<shiro:hasPermission name="sms:add">
						<button type="button" class="btn btn-info btn-small btn-block"
							onclick="addAndUpdateRow(0)">
							<i class="glyphicon glyphicon-plus"></i> 短信推送
						</button>
					</shiro:hasPermission>
				</div>
				<div class="col-md-2">
					<shiro:hasPermission name="sms:add">
						<button type="button" class="btn btn-primary btn-small btn-block"
							onclick="addAndUpdateRow(1)">
							<i class="glyphicon glyphicon-plus"></i> 个推推送
						</button>
					</shiro:hasPermission>
				</div>
			</div>
			
			<div class="main-content">
				<div class="row">
				<div class="col-md-2">
					<div class="form-group">
						<div class="input-group">
							<div class="input-group-addon">类型</div>
							<select class="form-control" id="smsCode">
							  <option value="">请选择类型</option>
							  <option value="1">短信信息</option>
							  <option value="2">极光信息</option>
							</select>
						</div>
					</div>
				</div>
				<div class="col-md-2">
					<div class="form-group">
						<div class="input-group">
							<div class="input-group-addon">状态</div>
							<select class="form-control" id="state">
							  <option value="">请选择</option>
							  <option value="1">正常</option>
							  <option value="0">异常</option>
							</select>
						</div>
					</div>
				</div>
				<div class="col-md-1">					
					<button type="button" class="btn btn-primary btn-small btn-block" id="query"><i class="glyphicon glyphicon-search"></i>查询</button>
				</div>
			</div>
			<table id="example" class="table"></table>
		</div>
		<script>
			//表格的初始化
			$(document).ready(function() {
				var table = $('#example').initTable({
					sAjaxSource:"sms/initTable.htm",
					aoColumns: [
						{ 
						    "data": "smsName",
						    "sTitle":"标题",
						    'sClass':"text-center"
						  },
			            { 
			              "data": "smsContent",
			              "sTitle":"内容",
			              'sClass':"text-center"
			            },
			            { 
				              "data": "createTime",
				              "sTitle":"推送时间",
				              'sClass':"text-center"
				            },
				            { 
					              "data": "createMan",
					              "sTitle":"创建人",
					              'sClass':"text-center"
					            },
					            { 
						              "data": "pushSuccessNumber",
						              "sTitle":"发送量",
						              'sClass':"text-center"
						            },
					            
						            /*{
							              "data": "arrivalNumber",
							              "sTitle":"到达量",
							              'sClass':"text-center"
							            },
							            { 
								              "data": "checkNumber",
								              "sTitle":"点击量",
								              'sClass':"text-center"
								            },
							            { 
								              "data": "clickrate",
								              "sTitle":"点击率",
								              'sClass':"text-cente",
						 			           "mRender": function(data, type, full) { 
						 			                	 return (Math.round(data * 10000)/100).toFixed(2) + '%';
						 			                } 
								            },*/
					            
 			            { 
 			                "data": "state",
 			                "sTitle":"状态",
 			                'sClass':"text-center",
 			                "mRender": function(data, type, full) { 
 			                	if(data == 1){
									return "<font color='red'>正常</font>";
 								}else{
 									return "<font color='blue'>异常</font>";
								}
 			                } 
			            }
// 			            <shiro:hasPermission name="sms:update or sms:delete">
// 			            ,{"data": "smsTemplateId","sTitle":"操作",'sClass':"text-center"}
// 			            </shiro:hasPermission>
			         ],
					 fnServerParams: function (aoData) {  //查询条件
						 aoData.push({ "name": "smsCode", "value": $("#smsCode").val() } ); 
						 aoData.push({ "name": "state", "value": $("#state").val() } );
		             },
		             aoColumnDefs : [
						<shiro:hasPermission name="sms:update or sms:delete">
		             	{"aTargets" :6,"mRender" : function(data, type, row){
		             		var edit="",del="";
		             		<shiro:hasPermission name="sms:update">
		             			edit = "<a href='#' onclick='addAndUpdateRow(\""+data+"\")' data-toggle='modal' class='padding-right-small label label-success'><i class='glyphicon glyphicon-edit'></i> 编辑</a>";
		             		</shiro:hasPermission>
		             		<shiro:hasPermission name="sms:delete">
		             			del ="<a href='#' onclick='deleteRow(\""+data+"\")' data-toggle='modal' class='label label label-danger'><i class='glyphicon glyphicon-trash'></i> 删除 </a>";
		             		</shiro:hasPermission>
// 		                    return edit+"&nbsp;"+del;
							return del;
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
				$('#myModal').deleteRow('sms/del.htm?id='+id);
			}
			//增加或者修改受影响的行数
			function addAndUpdateRow(id){
				$('#insertAndUpdate').addAndUpdateRow("sms/addAndUpdateHome.htm?id="+id);
			}
// 			function addOrUpdateRow(id){
// 				$('#insertAndUpdate').addAndUpdateRow("jpush/addAndUpdateHome.htm?id="+id);
// 			}
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