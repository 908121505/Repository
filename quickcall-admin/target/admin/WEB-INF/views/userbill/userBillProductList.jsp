<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
	<div class="content1">
		<div class="header">
			<h1 class="page-title">记账产品列表</h1>
			<ul class="breadcrumb">
				<li>记账产品列表</li>
				<li class="active">记账产品列表</li>
			</ul>
		</div>
		<div class="main-content">
			<div class="row">
				<div class="col-md-2">
					<div class="form-group">
						<div class="input-group">
							<div class="input-group-addon">产品名称</div>
							<input class="form-control" type="text" name="productName"
								id="productName">
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
						<button type="button" class="btn btn-info btn-small btn-block"
							onclick="addAndUpdateRow(0)">
							<i class="glyphicon glyphicon-plus"></i> 增加
						</button>
				</div>
			</div>
			<table id="example" class="table"></table>
		</div>
		<script>
			//表格的初始化
			$(document).ready(function() {
				var table = $('#example').initTable({
					sAjaxSource:"userBillProduct/initTable.htm",
					aoColumns: [
						{ 
						    "data": "productName",
						    "sTitle":"产品名称",
						    'sClass':"text-center"
						},
			            {
			               "data": "productImg",
			               "sTitle":"产品logo",
			               'sClass':"text-center",
			               "mRender": function(data, type, full) { 
				            	return "<img src='" + data + "' height='50px;'/>"; 
			              } 
			            },
						{
                            "data": "isRecommand",
                            "sTitle":"是否默认显示",
                            'sClass':"text-center",
                            "mRender": function(data, type, full) {
                                if(data == 1){
                                    return "<font color='red'>是</font>";
                                }else{
                                    return "<font color='blue'>否</font>";
                                }
                            }
                        }
			            ,{"data": "billProductId","sTitle":"操作",'sClass':"text-center"}
			         ],
					 fnServerParams: function (aoData) {  //查询条件
		             	aoData.push({ "name": "productName", "value": $("#productName").val() } );
		             },
		             aoColumnDefs : [
		             	{"aTargets" :3,"mRender" : function(data, type, row){
		             	   
		             		var edit="",del="",detail="";
		             			edit = "<a href='#' onclick='addAndUpdateRow(\""+data+"\")' data-toggle='modal' class='padding-right-small label label-success'><i class='glyphicon glyphicon-edit'></i> 编辑</a>";
		             			del ="<a href='#' onclick='deleteRow(\""+data+"\")' data-toggle='modal' class='label label label-danger'><i class='glyphicon glyphicon-trash'></i> 删除 </a>";
		                    return edit+"&nbsp;"+del;
		                }
		             }
		             ]
				});
				
				 $('#query').click(function(){
					$('#example').dataTable().fnDraw();
				});
			
			});
			//删除受影响的行数
			function deleteRow(id){
				console.log(id);
				$('#myModal').deleteRow('userBillProduct/del.htm?id='+id);
			}
			//增加或者修改受影响的行数
			function addAndUpdateRow(id){
				$('#insertAndUpdate').addAndUpdateRow("userBillProduct/addAndUpdateHome.htm?id="+id);
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