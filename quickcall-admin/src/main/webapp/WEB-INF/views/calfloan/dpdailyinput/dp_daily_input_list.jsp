<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<shiro:hasPermission name="banner:select">
	<div class="content1">
		<div class="header">
			<h1 class="page-title">大屏人工录入</h1>
			<ul class="breadcrumb">
				<li>贷款管理</li>
				<li class="active">大屏人工录入</li>
			</ul>
		</div>
		<div class="main-content">
			<div class="row">
				<div class="col-md-2">
					<div class="form-group">
						<div class="input-group">
							<div class="input-group-addon">统计录入日期</div>
							<input type="text" id="statsTime" class="form-control"
							onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd',minDate:'1950-01-01',maxDate:'2100-12-31'})"
							value="" name="statsTime" />
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
					<shiro:hasPermission name="banner:add">
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
					sAjaxSource:"dpDailyInput/initTable.htm",
					aoColumns: [
			            { 
			              "data": "createTime",
			              "sTitle":"创建日期",
			              'sClass':"text-center",
			              "mRender": function(data, type, full) { 
				              return data.substring(0,10); 
			              } 
			            },
			            { 
				              "data": "statsTime",
				              "sTitle":"数据录入日期",
				              'sClass':"text-center",
				              "mRender": function(data, type, full) { 
					              return data!=null?data.substring(0,10):""; 
				              } 
				            },
			            { 
			               "data": "newproductNumYdayPer",
			               "sTitle":"昨日人均注册商家数",
			               'sClass':"text-center"
			            }
                        ,
                        {
                            "data": "incomeYdayPer",
                            "sTitle":"昨日人均收入",
                            'sClass':"text-center"
                        }
                        ,
                        {
                            "data": "userStayNdayRate",
                            "sTitle":"次日留存率",
                            'sClass':"text-center"
                        }
                        ,
                        {
                            "data": "shandaiToGuanjiaRate",
                            "sTitle":"昨日闪贷转化率",
                            'sClass':"text-center"
                        }
			            <shiro:hasPermission name="dpDailyInput:update or dpDailyInput:delete">
			            ,{"data": "id","sTitle":"操作",'sClass':"text-center"}
			            </shiro:hasPermission>
			         ],
					 fnServerParams: function (aoData) {  //查询条件
		             	aoData.push({ "name": "statsTime", "value": $("#statsTime").val() } );
		             },
		             aoColumnDefs : [
						<shiro:hasPermission name="dpDailyInput:update or dpDailyInput:delete">
		             	{"aTargets" :6,"mRender" : function(data, type, row){
		             		var edit="",del="";
		             		<shiro:hasPermission name="dpDailyInput:update">
		             			edit = "<a href='#' onclick='addAndUpdateRow(\""+data+"\")' data-toggle='modal' class='padding-right-small label label-success'><i class='glyphicon glyphicon-edit'></i> 编辑</a>";
		             		</shiro:hasPermission>
		             		<shiro:hasPermission name="dpDailyInput:delete">
		             			del ="<a href='#' onclick='deleteRow(\""+data+"\")' data-toggle='modal' class='label label label-danger'><i class='glyphicon glyphicon-trash'></i> 删除 </a>";
		             		</shiro:hasPermission>
		                    return edit+"&nbsp;"+del;
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
				$('#myModal').deleteRow('dpDailyInput/del.htm?id='+id);
			}
			//增加或者修改受影响的行数
			function addAndUpdateRow(id){
			    console.log("所传的ID值为：" + id);
				$('#insertAndUpdate').addAndUpdateRow("dpDailyInput/addAndUpdateHome.htm?id="+id);
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