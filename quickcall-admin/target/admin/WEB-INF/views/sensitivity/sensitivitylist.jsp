<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<shiro:hasPermission name="sensitivity:select">
	<div class="content1">
		<div class="header">
			<h1 class="page-title">敏感词列表</h1>
			<ul class="breadcrumb">
				<li>贷款管理</li>
				<li class="active">敏感词列表</li>
			</ul>
		</div>
		<div class="main-content">
			<div class="row">
				<div class="col-md-2">
					<div class="form-group">
						<div class="input-group">
							<div class="input-group-addon">敏感词</div>
							<input class="form-control"  type="text" id="sensitivityContent">
						</div>
					</div>
				</div>

				<div class="col-md-2">
					<div class="form-group">
						<div class="input-group">
							<div class="input-group-addon">敏感词类型</div>
							<select class="form-control" id="type">
								<option value="">全部</option>
								<option value="1">社区敏感词</option>
								<option value="2">昵称敏感词</option>
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
				<div class="col-md-2">
					<shiro:hasPermission name="sensitivity:add">
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
				initTable();
			});
			
			function initTable(){
				var table = $('#example').initTable({
					sAjaxSource:"sensitivity/initTable.htm",
					aoColumns: [
			            { 
			              "data": "sensitivityContent",
			              "sTitle":"敏感词",
			              'sClass':"text-center",
			                "mRender": function(data, type, full) { 
			                	return '<input type="input" value="'+data+'" id="'+full.id+'">'
			            	},

			              
			            },
                        {
                            "data": "type",
                            "sTitle":"敏感词类型",
                            'sClass':"text-center",
                            "mRender": function(data, type, full) {
                                if(data == 1) {
									return "社区敏感词";
                                } else if (data == 2) {
                                    return "昵称敏感词";
                                } else {
                                    return "其他";
								}
                            },


                        }
			            <shiro:hasPermission name="sensitivity:update or sensitivity:delete">
			            ,{"data": "id","sTitle":"操作",'sClass':"text-center"}
			            </shiro:hasPermission>
			         ],
					 fnServerParams: function (aoData) {  //查询条件
		             	aoData.push({ "name": "sensitivityContent", "value": $("#sensitivityContent").val() } );
					    aoData.push({ "name": "type", "value": $("#type").val() } );
		             },
		             aoColumnDefs : [
						<shiro:hasPermission name="sensitivity:update or sensitivity:delete">
		             	{"aTargets" :2,"mRender" : function(data, type, row){
		             		var edit="",del="";
		             		<shiro:hasPermission name="sensitivity:update">
		             			edit = "<a href='#' onclick='saveSensitiEdit(\""+data+"\")' data-toggle='modal' class='padding-right-small label label-success'><i class='glyphicon glyphicon-edit'></i>保存</a>";
		             		</shiro:hasPermission>
		             		<shiro:hasPermission name="sensitivity:delete">
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
			
				
			}
			
			//删除受影响的行数
			function deleteRow(id){
				console.log(id);
				$('#myModal').deleteRow('sensitivity/del.htm?id='+id);
			}
			
		  function saveSensitiEdit(id){
			  $('#insertAndUpdate').addAndUpdateRow("sensitivity/saveUpdateSis.htm?id="+id+"&sensitivityContent="+encodeURI(encodeURI($("#"+id).val())));
			  $(".modal-backdrop").hide();
			  $.globalMessenger().post({
					message: '操作成功！',
					type: 'success',
					id: "Only-one-message",
					hideAfter: 2,
					hideOnNavigate: true
				});
			  
		  }
		  
		//增加或者修改受影响的行数
			function addAndUpdateRow(id){
				$('#insertAndUpdate').addAndUpdateRow("sensitivity/addAndUpdateHome.htm?id="+id);
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