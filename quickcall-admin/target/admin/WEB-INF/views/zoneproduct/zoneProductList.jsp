<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<shiro:hasPermission name="zoneProduct:select">
	<div class="content1">
		<div class="header">
			<h1 class="page-title">专区列表</h1>
			<ul class="breadcrumb">
				<li>专区管理</li>
				<li class="active">专区列表</li>
			</ul>
		</div>
		<div class="main-content">
			<div class="row">
				<div class="col-md-2">
					<div class="form-group">
						<div class="input-group">
							<div class="input-group-addon">专区名</div>
							<input class="form-control" type="text" name="zoneName"
								id="zoneNameFs">
								<input type="hidden" id="zoneName" value=""/>
								
						</div>
					</div>
				</div>
				<div class="col-md-2">
					<div class="form-group">
						<div class="input-group">
							<div class="input-group-addon">状态</div>
							<select class="form-control" id="state">
								<option value="">--请选择--</option>
								<option value="1">开启</option>
								<option value="0">关闭</option>
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
					<shiro:hasPermission name="zoneProduct:add">
						<button type="button" class="btn btn-info btn-small btn-block"
							onclick="addAndUpdateRow(0)">
							<i class="glyphicon glyphicon-plus"></i> 增加
						</button>
					</shiro:hasPermission>
				</div>
				<div class="col-md-2">
					<button type="button" class="btn btn-primary btn-small btn-block"
							id="initialize">
						<i class="glyphicon glyphicon-search"></i> 初始化(勿点)
					</button>
				</div>
			</div>
			<table id="example" class="table"></table>
		</div>
		<script>
			//表格的初始化
			$(document).ready(function() {
				var table = $('#example').initTable({
					sAjaxSource:"zoneProduct/initTable.htm",
					aoColumns: [
						{ 
						    "data": "zoneId",
						    "sTitle":"专区id",
						    'sClass':"text-center"
						},
						{ 
						    "data": "zoneName",
						    "sTitle":"专区名",
						    'sClass':"text-center" ,
						    "mRender": function(data, type, full) { 
						    	$("#zoneName").val(data);
				            	return data;
				              } 
						  },
			            { 
			               "data": "labelBackgroundImg",
			               "sTitle":"专区banner",
			               'sClass':"text-center",
			               "mRender": function(data, type, full) { 
				            	return "<img src='" + data + "' height='50px;'/>"; 
			              } 
			            },
			            { 
				              "data": "labelLcolor",
				              "sTitle":"底色",
				              'sClass':"text-center"
				            },
			            { 
			                "data": "remark",
			                "sTitle":"备注",
			                'sClass':"text-center"
			            },
			            { 
			                "data": "state",
			                "sTitle":"状态",
			                'sClass':"text-center",
			                "mRender": function(data, type, full) { 
			                	if(data == 1){
									return "<font color='red'>开启</font>";
								}else{
									return "<font color='blue'>关闭</font>";
								}
			                } 
			            },
			            { 
			                "data": "createTime",
			                "sTitle":"创建时间",
			                'sClass':"text-center"
			            },
			            { 
			                "data": "modifyTime",
			                "sTitle":"更新时间",
			                'sClass':"text-center"
			            },
			            { 
			                "data": "modifyMan",
			                "sTitle":"更新人",
			                'sClass':"text-center"
			            }
			            <shiro:hasPermission name="zoneProduct:update or zoneProduct:delete">
			            ,{"data": "zoneId","sTitle":"操作",'sClass':"text-center"}
			            </shiro:hasPermission>
			         ],
					 fnServerParams: function (aoData) {  //查询条件
		             	aoData.push({ "name": "zoneName", "value": $("#zoneNameFs").val() } );
		             	aoData.push({ "name": "state", "value": $("#state").val() } );
		             },
		             aoColumnDefs : [
						<shiro:hasPermission name="zoneProduct:update or zoneProduct:delete">
		             	{"aTargets" :9,"mRender" : function(data, type, row){
		             	   
		             		var edit="",del="",detail="";
		             		<shiro:hasPermission name="zoneProduct:update">
		             			edit = "<a href='#' onclick='addAndUpdateRow(\""+data+"\")' data-toggle='modal' class='padding-right-small label label-success'><i class='glyphicon glyphicon-edit'></i> 编辑</a>";
		             		</shiro:hasPermission>
		             		<shiro:hasPermission name="zoneProduct:delete">
		             			del ="<a href='#' onclick='deleteRow(\""+data+"\")' data-toggle='modal' class='label label label-danger'><i class='glyphicon glyphicon-trash'></i> 删除 </a>";
		             		</shiro:hasPermission>
		             		
		             		detail = "<a href='#' id="+data+" onclick='sunProductListRow(\""+data+"\",\""+$("#zoneName").val()+"\")' data-toggle='modal' class='padding-right-small label label-success'><i class='glyphicon glyphicon-edit'></i>查看专区下产品</a>";
		             		
		                    return edit+"&nbsp;"+del+"&nbsp;"+detail;
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
				$('#myModal').deleteRow('zoneProduct/del.htm?id='+id);
			}
			//增加或者修改受影响的行数
			function addAndUpdateRow(id){
				$('#insertAndUpdate').addAndUpdateRow("zoneProduct/addAndUpdateHome.htm?id="+id);
			}
			
			function sunProductListRow(id,zoneName){
			    $("#zoneName").val(zoneName);
				$('#insertAndUpdate').addAndUpdateRow("zoneProduct/sunZongDetail?zoneId="+id+"&zoneName="+$("#zoneName").val());
			}



            $('#initialize').click(function(){
				$.ajax({
					url:"zoneProduct/updateZoneproductData.htm",
					data:{},
					type:'get',
					success:function(data){
						if(data == 1){
							alert('初始化成功')
						}else{
							alert('初始化失败');
						}
					}
				})
            });
			
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