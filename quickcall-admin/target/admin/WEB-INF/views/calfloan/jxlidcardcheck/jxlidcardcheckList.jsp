<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<shiro:hasPermission name="jxlidcardcheck:select">
	<div class="content1">
		<div class="header">
			<h1 class="page-title">身份信息检查列表</h1>
			<ul class="breadcrumb">
				<li>身份信息检查管理</li>
				<li class="active">身份信息检查列表</li>
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
					sAjaxSource:"jxlidcardcheck/initTable.htm",
					aoColumns: [
							
						{ 
						    "data": "auditReportId",
						    "sTitle":"审核报告编号",
						    'sClass':"text-center" 
						  },
			            { 
			                "data": "sex",
			                "sTitle":"性别",
			                'sClass':"text-center"
			            },
			            { 
			                "data": "age",
			                "sTitle":"年龄",
			                'sClass':"text-center"
			               
			            },
			            { 
			                "data": "provinceId",
			                "sTitle":"省份编号",
			                'sClass':"text-center"
			            },
			            { 
			                "data": "provinceName",
			                "sTitle":"省份名称",
			                'sClass':"text-center"
			            },
			            { 
			                "data": "cityId",
			                "sTitle":"城市编号",
			                'sClass':"text-center"
			            },
			            { 
			                "data": "cityName",
			                "sTitle":"城市名称",
			                'sClass':"text-center"
			              
			            },
			            { 
			                "data": "cityAreaId",
			                "sTitle":"城市区域编号",
			                'sClass':"text-center"
			            },
			            { 
			                "data": "cityAreaName",
			                "sTitle":"城市区域名称",
			                'sClass':"text-center"
			            },
			            { 
			                "data": "financialBlacklist",
			                "sTitle":"金融黑名单记录",
			                'sClass':"text-center"
			            },
			            { 
			                "data": "courtBlacklist",
			                "sTitle":"法院黑名单记录",
			                'sClass':"text-center"
			            },
			            { 
			                "data": "keyValue",
			                "sTitle":"检查值",
			                'sClass':"text-center"
			            },
			            { 
			                "data": "state",
			                "sTitle":"状态",
			                'sClass':"text-center"
			            }
			            
			            <shiro:hasPermission name="jxlidcardcheck:update or jxlidcardcheck:delete">
			            ,{"data": "idCardCheckId","sTitle":"操作",'sClass':"text-center"}
			            </shiro:hasPermission>
			         ],
					 fnServerParams: function (aoData) {  //查询条件
					  	aoData.push({ "name": "auditReportId", "value": $("#auditReportId_id").val() } ); 
		             },
		             aoColumnDefs : [
						<shiro:hasPermission name="jxlidcardcheck:update or jxlidcardcheck:delete">
		             	{"aTargets" : 13,"mRender" : function(data, type, row){
		             	   
		             		  var  edit="";
		             		<shiro:hasPermission name="jxlidcardcheck:update">
		             		 edit = "<a href='#' onclick='addAndUpdateRow(\""+data+"\")' data-toggle='modal' class='padding-right-small label label-success'><i class='glyphicon glyphicon-edit'></i> 查看详情</a>";
		             	/* 	detail = "<a href='#' onclick='detailRow(\"" + data + "\")' data-toggle='modal' class='padding-right-small label label-success'><i class='glyphicon glyphicon-edit'></i> 查看</a>"; */
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
                $('#insertAndUpdate').addAndUpdateRow("jxlidcardcheck/addAndUpdateHome.htm?id="+id);
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