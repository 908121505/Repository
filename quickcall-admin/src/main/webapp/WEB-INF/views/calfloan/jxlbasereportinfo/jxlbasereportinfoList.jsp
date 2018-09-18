<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<shiro:hasPermission name="jxlbasereportinfo:select">
	<div class="content1">
		<div class="header">
			<h1 class="page-title">用户聚信立报告前置信息表列表</h1>
			<ul class="breadcrumb">
				<li>用户聚信立报告前置信息表管理</li>
				<li class="active">用户聚信立报告前置信息表列表</li>
			</ul>
		</div>
		<div class="main-content">
			<div class="row">
				<div class="col-md-2">
					<div class="form-group">
						<div class="input-group">
							<div class="input-group-addon">获取报告状态</div>
							<select class="form-control" id="jxlState">
								<option value="">--请选择--</option>
								<option value="0">未获取</option>
								<option value="1">已获取</option>
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
			</div>
			<table id="example" class="table"></table>
		</div>
		<script>
			//表格的初始化


  
			$(document).ready(function() {
				var table = $('#example').initTable({
					sAjaxSource:"jxlbasereportinfo/initTable.htm",
					aoColumns: [
							
						{ 
						    "data": "jxlName",
						    "sTitle":"用户姓名",
						    'sClass':"text-center" 
						  },
						  { 
							    "data": "jxlIdNumber",
							    "sTitle":"身份证号",
							    'sClass':"text-center" 
							  },
							  { 
								    "data": "jxlMobile",
								    "sTitle":"手机号码",
								    'sClass':"text-center" 
								  },
								  { 
									    "data": "jxlMobileIdCard",
									    "sTitle":"身份证",
									    'sClass':"text-center" 
									  },
								  { 
									    "data": "jxlMobileIdName",
									    "sTitle":"客户姓名",
									    'sClass':"text-center" 
									  },
								  { 
									    "data": "jxlMobileReportTime",
									    "sTitle":"报告时间",
									    'sClass':"text-center",
									    "mRender": function(data, type, full) { 
							                  return	Format(new Date(data),"yyyy-MM-dd HH:mm:ss")
							                } 
									  },
								  { 
									    "data": "jxlMobileApplicationNumber",
									    "sTitle":"借款申请单编号",
									    'sClass':"text-center" 
									  },
								  { 
									    "data": "jxlMobileLoanDate",
									    "sTitle":"申请借款日期",
									    'sClass':"text-center",
									    "mRender": function(data, type, full) { 
							                  return	Format(new Date(data),"yyyy-MM-dd HH:mm:ss")
							                } 
									  },
								  { 
									    "data": "jxlMobileIpRemark",
									    "sTitle":"ip备注（公网+内网）",
									    'sClass':"text-center" 
									  },
									  { 
							                "data": "jxlMobileIpState",
							                "sTitle":"是否命中",
							                'sClass':"text-center"
							            },
							  { 
								    "data": "jxlAccountId",
								    "sTitle":"用户ID",
								    'sClass':"text-center" 
								  },
							  { 
									  "data": "jxlToken",
						                "sTitle":"获取报告token",
						                'sClass':"text-center"
								  },
							  { 
								    "data": "jxlPassword",
								    "sTitle":"手机服务密码",
								    'sClass':"text-center" 
								  },
							  { 
								    "data": "jxlWebsite",
								    "sTitle":"获取报告标识",
								    'sClass':"text-center" 
								  },
			            { 
			                "data": "jxlState",
			                "sTitle":"获取报告状态",
			                'sClass':"text-center",
			                "mRender": function(data, jxlState, full) { 
			                	if(data == 0){
									return "<font color='red'>未获取</font>";
								} else if(data ==1){
									return "<font color='blue'>已获取</font>";
								}
			                } 
			            },
			            { 
						    "data": "jxlCreateTime",
						    "sTitle":"创建时间",
						    'sClass':"text-center",
						    "mRender": function(data, type, full) { 
				                  return	Format(new Date(data),"yyyy-MM-dd HH:mm:ss")
				                } 
						  }
					  
			            <shiro:hasPermission name="jxlbasereportinfo:update or jxlbasereportinfo:delete">
			            ,{"data": "jxlBaseReportInfoId","sTitle":"操作",'sClass':"text-center"}
			            </shiro:hasPermission>
			         ],
					 fnServerParams: function (aoData) {  //查询条件
		              aoData.push({ "name": "jxlState", "value": $("#jxlState").val() } );
		             },
		             aoColumnDefs : [
						<shiro:hasPermission name="jxlbasereportinfo:update or jxlbasereportinfo:delete">
		             	{"aTargets" :16,"mRender" : function(data, type, row){
		             	   
		             		  var edit="";
		             		<shiro:hasPermission name="jxlbasereportinfo:update">
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
                $('#insertAndUpdate').addAndUpdateRow("jxlbasereportinfo/addAndUpdateHome.htm?id="+id);
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