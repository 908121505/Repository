<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<shiro:hasPermission name="tdriskcourt:select">
	<div class="content1">
		<div class="header">
			<h1 class="page-title">法院详情信息表列表</h1>
			<ul class="breadcrumb">
				<li>法院详情信息管理</li>
				<li class="active">法院详情信息列表</li>
			</ul>
		</div>
		<div class="main-content">
			<div class="row">
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
					sAjaxSource:"tdriskcourt/initTable.htm",
					aoColumns: [
							
						{ 
						    "data": "reportId",
						    "sTitle":"报告编号",
						    'sClass':"text-center" 
						  },
			           
			            
			            { 
			                "data": "name",
			                "sTitle":"被执行人姓名",
			                'sClass':"text-center"
			            },
			            { 
			                "data": "filingTime",
			                "sTitle":"立案时间",
			                'sClass':"text-center"
			               
			            },
			            { 
			                "data": "fraudType",
			                "sTitle":"风险类型",
			                'sClass':"text-center",
			                "mRender": function(data, fraudType, full) { 
			                	if(data == "法院执行"){
									return "<font color='red'>法院执行</font>";
								}else if(data == "法院失信"){
									return "<font color='blue'>法院失信</font>";
								}
			                } 
			            },
			          
			      
			            { 
			                "data": "courtName",
			                "sTitle":"执行法院",
			                'sClass':"text-center"
			            }, 
			            { 
			                "data": "situation",
			                "sTitle":"被执行人的履行情况",
			                'sClass':"text-center"
			            },
			            { 
			                "data": "caseNumber",
			                "sTitle":"案号",
			                'sClass':"text-center"
			            },
			            { 
			                "data": "discreditDetail",
			                "sTitle":"失信被执行人行为具体情形",
			                'sClass':"text-center"
			            },
			            { 
			                "data": "createTime",
			                "sTitle":"创建时间",
			                'sClass':"text-center"
			              
			            },
			            { 
			                "data": "modifyTime",
			                "sTitle":"修改时间",
			                'sClass':"text-center"
			               
			            }
			            <shiro:hasPermission name="tdriskcourt:update or tdriskcourt:delete">
			            ,{"data": "seqRiskId","sTitle":"操作",'sClass':"text-center"}
			            </shiro:hasPermission>
			         ],
					 fnServerParams: function (aoData) {  //查询条件
							aoData.push({ "name": "reportId", "value": $("#reportId").val() } );
		             },
		             aoColumnDefs : [
						<shiro:hasPermission name="tdriskcourt:update or tdriskcourt:delete">
		             	{"aTargets" : 10,"mRender" : function(data, type, row){
		             	   
		             		  var  edit="";
		             		<shiro:hasPermission name="tdriskcourt:update">
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
                $('#insertAndUpdate').addAndUpdateRow("tdriskcourt/addAndUpdateHome.htm?id="+id);
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