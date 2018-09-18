<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<shiro:hasPermission name="waistcoatTrial:select">
	<div class="content1">
		<div class="header">
			<h1 class="page-title">市场审核屏蔽</h1>
			<ul class="breadcrumb">
				<li>市场审核屏蔽</li>
				<li class="active">市场审核屏蔽</li>
			</ul>
		</div>
		
			<div class="row">
			
			</div>
			<table id="example" class="table"></table>
		</div>
		<script>
			//表格的初始化
			$(document).ready(function() {
				var table = $('#example').initTable({
					sAjaxSource:"waistcoatTrial/initTable.htm",
					aoColumns: [
						{ 
						    "data": "name",
						    "sTitle":"app名称",
						    'sClass':"text-center"
						  },
			            { 
			              "data": "chanel",
			              "sTitle":"屏蔽渠道",
			              'sClass':"text-center"
			            },
			            { 
				              "data": "version",
				              "sTitle":"屏蔽版本号",
				              'sClass':"text-center"
				            },
				           
			            { 
 			                "data": "status",
 			                "sTitle":"状态",
 			                'sClass':"text-center",
 			                "mRender": function(data, type, full) { 
 			                	if(data == 0){
									return "<font color='red'>开启</font>";
 								}else{
 									return "<font color='blue'>关闭</font>";
								}
 			                } 
			            },
				            { 
					              "data": "remark",
					              "sTitle":"备注	",
					              'sClass':"text-center"
					            },
			           
				            { 
					              "data": "modifyMan",
					              "sTitle":"操作人",
					              'sClass':"text-center"
					         },
					         { 
					              "data": "modifyTime",
					              "sTitle":"操作时间",
					              'sClass':"text-center"
					         }
					           
					            
 			           
 			            
 			            ,{"data": "id","sTitle":"操作",'sClass':"text-center"}
 			            
			         ],
		             aoColumnDefs : [
		             	{"aTargets" :7,"mRender" : function(data, type, row){
		             		var edit="";
		             		edit = "<a href='#' onclick='addAndUpdateRow(\""+data+"\")' data-toggle='modal' class='padding-right-small label label-success'><i class='glyphicon glyphicon-edit'></i> 编辑</a>";
							return edit;
		                }
		             }
		             ]
				});
				
				 $('#query').click(function(){
					$('#example').dataTable().fnDraw();
				});
			
			});
			
			//增加或者修改受影响的行数
			function addAndUpdateRow(id){
				$('#insertAndUpdate').addAndUpdateRow("waistcoatTrial/addAndUpdateHome.htm?id="+id);
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