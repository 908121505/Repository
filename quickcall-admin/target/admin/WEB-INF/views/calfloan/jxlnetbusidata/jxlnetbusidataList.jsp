<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<shiro:hasPermission name="jxlnetbusidata:select">
	<div class="content1">
		<div class="header">
			<h1 class="page-title">电商数据列表</h1>
			<ul class="breadcrumb">
				<li>电商数据管理</li>
				<li class="active">电商数据列表</li>
			</ul>
		</div>
		<div class="main-content">
			<div class="row">
				<div class="col-md-2">
					<div class="form-group">
						<div class="input-group">
							<div class="input-group-addon">审核报告编号</div>
							<input class="form-control" type="text" name="auditReportId"
								id="auditReportId_id">
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
					sAjaxSource:"jxlnetbusidata/initTable.htm",
					aoColumns: [
							
						{ 
						    "data": "auditReportId",
						    "sTitle":"审核报告编号",
						    'sClass':"text-center" 
						  },
			            { 
			                "data": "customerId",
			                "sTitle":"APP应用客户编号",
			                'sClass':"text-center"
			            },
			            { 
			                "data": "platformCustomerId",
			                "sTitle":"平台用户编号",
			                'sClass':"text-center"
			               
			            },
			            { 
			                "data": "year",
			                "sTitle":"年份",
			                'sClass':"text-center"
			            },
			            { 
			                "data": "month",
			                "sTitle":"月份",
			                'sClass':"text-center"
			            },
			            { 
			                "data": "consumeNum",
			                "sTitle":"消费笔数",
			                'sClass':"text-center"
			            },
			            { 
			                "data": "category",
			                "sTitle":"品类",
			                'sClass':"text-center"
			            },
			            { 
			                "data": "state",
			                "sTitle":"状态",
			                'sClass':"text-center"
			            },
			            { 
			                "data": "consumeSum",
			                "sTitle":"消费金额",
			                'sClass':"text-center"
			              
			            }
			            
			            <shiro:hasPermission name="jxlnetbusidata:update or jxlnetbusidata:delete">
			            ,{"data": "netBusiDataId","sTitle":"操作",'sClass':"text-center"}
			            </shiro:hasPermission>
			         ],
					 fnServerParams: function (aoData) {  //查询条件
					  	aoData.push({ "name": "auditReportId", "value": $("#auditReportId_id").val() } ); 
		             },
		             aoColumnDefs : [
						<shiro:hasPermission name="jxlnetbusidata:update or jxlnetbusidata:delete">
		             	{"aTargets" : 9,"mRender" : function(data, type, row){
		             	   
		             		  var  edit="";
		             		<shiro:hasPermission name="jxlnetbusidata:update">
		             		 edit = "<a href='#' onclick='addAndUpdateRow(\""+data+"\")' data-toggle='modal' class='padding-right-small label label-success'><i class='glyphicon glyphicon-edit'></i> 查看详情</a>";
		             		/* detail = "<a href='#' onclick='detailRow(\"" + data + "\")' data-toggle='modal' class='padding-right-small label label-success'><i class='glyphicon glyphicon-edit'></i> 查看</a>"; */
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
                $('#insertAndUpdate').addAndUpdateRow("jxlnetbusidata/addAndUpdateHome.htm?id="+id);
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