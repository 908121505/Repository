<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<shiro:hasPermission name="banner:select">
	<div class="content1">
		<div class="header">
			<h1 class="page-title">banner管理</h1>
			<ul class="breadcrumb">
				<li>APP配置</li>
				<li class="active">banner管理</li>
			</ul>
		</div>
		<div class="main-content">
			<div class="row">
				<div class="col-md-2">
					<div class="form-group">
						<div class="input-group">
							<div class="input-group-addon">标题</div>
							<input class="form-control" type="text" id="title">
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
					<div class="form-group">
						<div class="input-group">
							<div class="input-group-addon">banner类型</div>
							<select class="form-control" id="bannerType_query" name="bannerType">
								<option value="1" ${entity.bannerType=='1'?'selected':''}>首页banner</option>
								<option value="2"  ${entity.bannerType=='2'?'selected':''}>活动banner</option>
								<option value="5"  ${entity.bannerType=='5'?'selected':''}>社区banner</option>
								<option value="9"  ${entity.bannerType=='9'?'selected':''}>工具banner</option>
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
					<shiro:hasPermission name="banner:add">
						<button type="button" class="btn btn-info btn-small btn-block"
							onclick="addAndUpdateRow(0)">
							<i class="glyphicon glyphicon-plus"></i> 增加
						</button>
					</shiro:hasPermission>
				</div>
			</div>
			<div class="row">
				<div class="col-md-2">
					<div class="form-group">
						<div class="input-group">
							<div class="input-group-addon">有效时间</div>
							<input class="form-control" type="date" id="sTime">
							<div class="input-group-addon">到</div>
							<input class="form-control" type="date" id="eTime">
						</div>
					</div>
				</div>
			</div>
			<table id="example" class="table"></table>
		</div>
		<script>
			//表格的初始化
			$(document).ready(function() {
				var table = $('#example').initTable({
					sAjaxSource:"banner/initTable.htm",
					aoColumns: [
			            { 
			              "data": "title",
			              "sTitle":"标题",
			              'sClass':"text-center"
			            },
			            { 
			               "data": "image",
			               "sTitle":"图片",
			               'sClass':"text-center",
			               "mRender": function(data, type, full) { 
				            	return "<img src='" + data + "' height='50px;'/>"; 
			              } 
			            },
			            { 
			                "data": "bannerType",
			                "sTitle":"类型",
			                'sClass':"text-center",
			                "mRender": function(data, type, full) { 
			                	if(data == "1"){
									return "首页banner";
                                }else{
									return "其他";;
								}
			                } 
			            },
                        {
                            "data": "startTime",
                            "sTitle":"开始时间",
                            'sClass':"text-center",
                            "mRender": function(data, type, full) {
                                if(data == null){
                                    return "";
                                }else{
                                    return data.substring(0,19);
                                }
                            }
                        },
                        {
                            "data": "endTime",
                            "sTitle":"结束时间",
                            'sClass':"text-center",
                            "mRender": function(data, type, full) {
                                if(data == null){
                                    return "";
                                }else{
                                    return data.substring(0,19);
                                }
                            }
                        },
                        {
                            "data": "deviceType",
                            "sTitle":"设备类型",
                            'sClass':"text-center",
                            "mRender": function(data, type, full) {
                                if(data == 1){
                                    return "<font color='red'>IOS</font>";
                                } else if(data == 2) {
                                    return "<font color='red'>Android</font>";
                                }else{
                                    return "<font color='blue'>所有设备</font>";
                                }
                            }
                        },
                        {
                            "data": "appVersionRule",
                            "sTitle":"版本号匹配规则",
                            'sClass':"text-center",
                            "mRender": function(data, type, full) {
                                if(data == 1){
                                    return "<font >大于</font>";
                                } else if (data == 2) {
                                    return "<font >小于</font>";
                                }  else if (data == 3) {
                                    return "<font >等于</font>";
                                }  else if (data == 4) {
                                    return "<font >大于等于</font>";
                                }  else if (data == 5) {
                                    return "<font >小于等于</font>";
                                } else {
                                    return "<font >所有版本</font>";
                                }
                            }
                        },
                        {
                            "data": "appVersion",
                            "sTitle":"版本号",
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
			            }
			            <shiro:hasPermission name="banner:update or banner:delete">
			            ,{"data": "bannerId","sTitle":"操作",'sClass':"text-center"}
			            </shiro:hasPermission>
			         ],
					 fnServerParams: function (aoData) {  //查询条件
		             	aoData.push({ "name": "title", "value": $("#title").val() } );
		             	aoData.push({ "name": "state", "value": $("#state").val() } );
						aoData.push({ "name": "bannerType", "value": $("#bannerType_query").val() } );
						aoData.push({ "name": "startTime", "value": $("#sTime").val() } );
						aoData.push({ "name": "endTime", "value": $("#eTime").val() } );
		             },
		             aoColumnDefs : [
						<shiro:hasPermission name="banner:update or banner:delete">
		             	{"aTargets" :10,"mRender" : function(data, type, row){
		             		var edit="",del="";
		             		<shiro:hasPermission name="banner:update">
		             			edit = "<a href='#' onclick='addAndUpdateRow(\""+data+"\")' data-toggle='modal' class='padding-right-small label label-success'><i class='glyphicon glyphicon-edit'></i> 编辑</a>";
		             		</shiro:hasPermission>
		             		<shiro:hasPermission name="banner:delete">
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
				$('#myModal').deleteRow('banner/del.htm?id='+id);
			}
			//增加或者修改受影响的行数
			function addAndUpdateRow(id){
				$('#insertAndUpdate').addAndUpdateRow("banner/addAndUpdateHome.htm?id="+id);
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