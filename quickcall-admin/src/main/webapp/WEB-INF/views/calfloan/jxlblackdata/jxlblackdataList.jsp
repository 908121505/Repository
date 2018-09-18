<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<shiro:hasPermission name="jxlblackdata:select">
	<div class="content1">
		<div class="header">
			<h1 class="page-title">黑名单数据列表</h1>
			<ul class="breadcrumb">
				<li>黑名单数据管理</li>
				<li class="active">黑名单数据列表</li>
			</ul>
		</div>
		<div class="main-content">
			<div class="row">
				<div class="col-md-2">
					<div class="form-group">
						<div class="input-group">
							<div class="input-group-addon">审核报告编号</div>
							<input class="form-control" type="text" name="auditReportId"
								id="auditReportId">
						</div>
					</div>
				</div>
				<div class="col-md-2">
					<button type="button" class="btn btn-primary btn-small btn-block"
						id="query">
						<i class="glyphicon glyphicon-search"></i> 查询
					</button>
				</div>
			</div>
			<table id="example" class="table"></table>
		</div>
		<script>
			//表格的初始化

  
			$(document).ready(function() {
				var table = $('#example').initTable({
					sAjaxSource:"jxlblackdata/initTable.htm",
					aoColumns: [
							
						{ 
						    "data": "customerId",
						    "sTitle":"APP应用客户编号",
						    'sClass':"text-center" 
						  },
			           
			            
			            { 
			                "data": "blackScore",
			                "sTitle":"黑中介分数",
			                'sClass':"text-center"
			            },
			            { 
			                "data": "linkmanBlackNum",
			                "sTitle":"直接联系人黑名单人数",
			                'sClass':"text-center"
			               
			            },
			            { 
			                "data": "jianBlackNum",
			                "sTitle":"间接联系人黑名单人数",
			                'sClass':"text-center"
			            },
			            { 
			                "data": "directLinkmanNum",
			                "sTitle":"直接联系人数",
			                'sClass':"text-center"
			            },
			            { 
			                "data": "directBlackNum",
			                "sTitle":"引起黑名单的直接联系人数量",
			                'sClass':"text-center"
			            },
			            { 
			                "data": "directScaleNum",
			                "sTitle":"直接联系人中引起间接黑名单占比",
			                'sClass':"text-center"
			              
			            },
			            { 
			                "data": "state",
			                "sTitle":"状态",
			                'sClass':"text-center"
			               
			            },
			            { 
			                "data": "auditReportId",
			                "sTitle":"审核报告编号",
			                'sClass':"text-center"
			            }, 
			            { 
			                "data": "platformCustomerId",
			                "sTitle":"平台用户编号",
			                'sClass':"text-center"
			            }
			           
			            
			            <shiro:hasPermission name="jxlblackdata:update or jxlblackdata:delete">
			            ,{"data": "blackDataId","sTitle":"操作",'sClass':"text-center"}
			            </shiro:hasPermission>
			         ],
					 fnServerParams: function (aoData) {  //查询条件
					 	aoData.push({ "name": "auditReportId", "value": $("#auditReportId").val() } ); 
		             },
		             aoColumnDefs : [
						<shiro:hasPermission name="jxlblackdata:update or jxlblackdata:delete">
		             	{"aTargets" : 10,"mRender" : function(data, type, row){
		             	   
		             		  var  edit="";
		             		<shiro:hasPermission name="jxlblackdata:update">
		             	   edit = "<a href='#' onclick='addAndUpdateRow(\""+data+"\")' data-toggle='modal' class='padding-right-small label label-success'><i class='glyphicon glyphicon-edit'></i> 查看详情</a>";
		             		/*  detail = "<a href='#' onclick='detailRow(\"" + data + "\")' data-toggle='modal' class='padding-right-small label label-success'><i class='glyphicon glyphicon-edit'></i> 查看</a>"; */
		         		     </shiro:hasPermission>
		             		
		             		
		         		    return  edit;
		                }
		             }
		            	</shiro:hasPermission>
		             ]
				});
				
				 $('#query').click(function(){
					$('#example').dataTable().fnDraw();
				});
			
			});
			
            //增加或者修改受影响的行数
            function addAndUpdateRow(id){
                $('#insertAndUpdate').addAndUpdateRow("jxlblackdata/addAndUpdateHome.htm?id="+id);
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