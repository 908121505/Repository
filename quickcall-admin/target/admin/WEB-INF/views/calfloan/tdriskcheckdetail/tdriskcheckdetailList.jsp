<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<shiro:hasPermission name="tdriskcheckdetail:select">
	<div class="content1">
		<div class="header">
			<h1 class="page-title">检查项详情表列表</h1>
			<ul class="breadcrumb">
				<li>检查项详情表管理</li>
				<li class="active">检查项详情表列表</li>
			</ul>
		</div>
		<div class="main-content">
			<div class="row">
				<div class="col-md-2">
					<div class="form-group">
						<div class="input-group">
							<div class="input-group-addon">规则的类型</div>
							<select class="form-control" id="type">
								<option value="">--请选择--</option>
								<option value="black_list">规则一(black_list)</option>
								<option value="fuzzy_list">规则二(fuzzy_list)</option>
								<option value="grey_list">规则三(grey_list)</option>
							</select>
						</div>
					</div>
				</div>
				<div class="col-md-2">
					<div class="form-group">
						<div class="input-group">
							<div class="input-group-addon">报告编号</div>
							<input class="form-control" type="text" name="reportId"
								id="reportId">
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
					sAjaxSource:"tdriskcheckdetail/initTable.htm",
					aoColumns: [
							
						{ 
						    "data": "reportId",
						    "sTitle":"报告编号",
						    'sClass':"text-center" 
						  },
			            { 
			              "data": "itemId",
			              "sTitle":"检查项编号",
			              'sClass':"text-center"
			            },
			            { 
			               "data": "discreditTimes",
			               "sTitle":"信贷逾期次数",
			               'sClass':"text-center" 
			            },
			            { 
			                "data": "hitTypeDisplayname",
			                "sTitle":"匹配类型",
			                'sClass':"text-center"
			            },
			           
			            { 
			                "data": "type",
			                "sTitle":"状态",
			                'sClass':"text-center",
			                "mRender": function(data, type, full) { 
			                	if(data == "black_list"){
									return "<font color='red'>规则一(black_list)</font>";
								}else if(data == "fuzzy_list"){
									return "<font color='blue'>规则二(fuzzy_list)</font>";
								}else if(data == "grey_list"){
									return "<font color='blue'>规则三(grey_list)</font>";
								}
			                } 
			            },
			          
			            { 
			                "data": "fraudType",
			                "sTitle":"风险类型",
			                'sClass':"text-center",
			                "mRender": function(data, fraudType, full) { 
				                  if(full.fraudType!=null && full.fraudType.length >100)	{
				                	  return data.substr(0,100)+"..."
				                  }else{
				                	  return data;
				                  }
				                } 
			            },
			           
			            { 
			                "data": "description",
			                "sTitle":"描述",
			                'sClass':"text-center",
			                "mRender": function(data, description, full) { 
				                  if(full.description!=null && full.description.length >100)	{
				                	  return data.substr(0,100)+"..."
				                  }else{
				                	  return data;
				                  }
				                } 
			            }, 
			            { 
			                "data": "createTime",
			                "sTitle":"创建时间",
			                'sClass':"text-center"
			               
			            }
			            <shiro:hasPermission name="tdriskcheck:update or tdriskcheck:delete">
			            ,{"data": "seqRiskId","sTitle":"操作",'sClass':"text-center"}
			            </shiro:hasPermission>
			         ],
					 fnServerParams: function (aoData) {  //查询条件
		             	aoData.push({ "name": "type", "value": $("#type").val() } );
		            	aoData.push({ "name": "reportId", "value": $("#reportId").val() } );
		             },
		             aoColumnDefs : [
						<shiro:hasPermission name="tdriskcheckdetail:update or tdriskcheckdetail:delete">
		             	{"aTargets" :8,"mRender" : function(data, type, row){
		             	   
		             		  var  detail="";
		             		<shiro:hasPermission name="tdriskcheckdetail:update">
		             		 detail = "<a href='#' onclick='detailRow(\"" + data + "\")' data-toggle='modal' class='padding-right-small label label-success'><i class='glyphicon glyphicon-edit'></i> 查看</a>";
		         		     </shiro:hasPermission>
		             		
		             		
		         		    return  detail;
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
				$('#insertAndUpdate').addAndUpdateRow("account/info/viewInfoById.htm?id="+id);
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