<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<shiro:hasPermission name="tdriskcheck:select">
	<div class="content1">
		<div class="header">
			<h1 class="page-title">风险检查项表列表</h1>
			<ul class="breadcrumb">
				<li>风险检查项表管理</li>
				<li class="active">风险检查项表列表</li>
			</ul>
		</div>
		<div class="main-content">
			<div class="row">
				<div class="col-md-2">
					<div class="form-group">
						<div class="input-group">
							<div class="input-group-addon">风险等级</div>
							<select class="form-control" id="riskLevel">
								<option value="">--请选择--</option>
								<option value="high">高</option>
								<option value="medium">中</option>
								<option value="low">低</option>
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
					sAjaxSource:"tdriskcheck/initTable.htm",
					aoColumns: [
						{ 
						    "data": "reportId",
						    "sTitle":"报告编号",
						    'sClass':"text-center" 
						  },
						  { 
							    "data": "phoneNum",
							    "sTitle":"手机号",
							    'sClass':"text-center" 
							  },
							  { 
								    "data": "parentNameUserId",
								    "sTitle":"报告的申请编号",
								    'sClass':"text-center"
								  },
							  { 
								    "data": "parentNameTitle",
								    "sTitle":"名字",
								    'sClass':"text-center" 
								  },
							  { 
								    "data": "parentNameIdCard",
								    "sTitle":"身份证",
								    'sClass':"text-center" 
								  },
								  { 
						                "data": "group",
						                "sTitle":"检查项分组",
						                'sClass':"text-center",
						                "mRender": function(data, group, full) { 
						                	if(data == "客户行为检测"){
												return "<font color='red'>客户行为检测</font>";
											} else if(data == "多平台借贷申请检测"){
												return "<font color='blue'>多平台借贷申请检测</font>";
											}else if(data == "风险信息扫描"){
												return "<font color='blue'>风险信息扫描</font>";
											}else if(data == "个人基本信息核查"){
												return "<font color='blue'>个人基本信息核查</font>";
											}
						                } 
						            },
							  { 
									  "data": "parentNameTime",
						                "sTitle":"报告时间",
						                'sClass':"text-center",
						                "mRender": function(data, type, full) { 
							                  return	Format(new Date(data),"yyyy-MM-dd HH:mm:ss")
							                } 
								  },
							 
								  { 
									    "data": "parentNameFraudType",
									    "sTitle":"风险类型",
									    'sClass':"text-center",
						                "mRender": function(data, parentNameFraudType, full) { 
							                  if(full.parentNameFraudType!=null && full.parentNameFraudType.length >100)	{
							                	  return data.substr(0,100)+"..."
							                  }else{
							                	  return data;
							                  }
							                }  
									  },
								  { 
									    "data": "parentNameTypeDisplayname",
									    "sTitle":"匹配类型",
									    'sClass':"text-center" 
									  },
									  { 
							                "data": "parentNameType",
							                "sTitle":"规则的类型",
							                'sClass':"text-center"
							            },
								  { 
									    "data": "parentNameDescription",
									    "sTitle":"描述",
									    'sClass':"text-center",
						                "mRender": function(data, parentNameDescription, full) { 
							                  if(full.parentNameDescription!=null && full.parentNameDescription.length >100)	{
							                	  return data.substr(0,100)+"..."
							                  }else{
							                	  return data;
							                  }
							                }   
									  },
			            { 
			              "data": "itemId",
			              "sTitle":"检查项编号",
			              'sClass':"text-center"
			            },
			            { 
			               "data": "itemName",
			               "sTitle":"检查项名称",
			               'sClass':"text-center" 
			            },
			            { 
			                "data": "riskLevel",
			                "sTitle":"风险等级",
			                'sClass':"text-center",
			                "mRender": function(data, riskLevel, full) { 
			                	if(data == "high"){
									return "<font color='red'>高</font>";
								} else if(data == "medium"){
									return "<font color='blue'>中</font>";
								}else if(data == "low"){
									return "<font color='blue'>低</font>";
								}
			                } 
			            }, 
			            { 
						    "data": "parentNameSource",
						    "sTitle":"分数",
						    'sClass':"text-center" 
						  },
					 
						  { 
							    "data": "parentNameDiscreditTime",
							    "sTitle":"信贷逾期次数",
							    'sClass':"text-center" 
							  }
			            <shiro:hasPermission name="tdriskcheck:update or tdriskcheck:delete">
			            ,{"data": "seqRiskId","sTitle":"操作",'sClass':"text-center"}
			            </shiro:hasPermission>
			         ],
					 fnServerParams: function (aoData) {  //查询条件
		             	aoData.push({ "name": "riskLevel", "value": $("#riskLevel").val() } );
		             	aoData.push({ "name": "reportId", "value": $("#reportId").val() } );
		             },
		             aoColumnDefs : [
						<shiro:hasPermission name="tdriskcheck:update or tdriskcheck:delete">
		             	{"aTargets" :16,"mRender" : function(data, type, row){
		             	   
		             		  var edit="";
		             		<shiro:hasPermission name="tdriskcheck:update">
		             		  edit = "<a href='#' onclick='addAndUpdateRow(\""+data+"\")' data-toggle='modal' class='padding-right-small label label-success'><i class='glyphicon glyphicon-edit'></i> 查看详情</a>";
		             	/*    detail = "<a href='#' onclick='detailRow(\"" + data + "\")' data-toggle='modal' class='padding-right-small label label-success'><i class='glyphicon glyphicon-edit'></i> 查看</a>"; */
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
                $('#insertAndUpdate').addAndUpdateRow("tdriskcheck/addAndUpdateHome.htm?id="+id);
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