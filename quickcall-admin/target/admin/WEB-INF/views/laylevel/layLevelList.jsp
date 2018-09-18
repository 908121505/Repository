<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
	<div class="content1">
		<div class="header">
			<h1 class="page-title">弹窗配置</h1>
			<ul class="breadcrumb">
				<li>弹窗配置列表</li>
				<li class="active">弹窗配置列表</li>
			</ul>
		</div>
		<div class="main-content">
			<div class="row">
				<div class="col-md-2">
					<div class="form-group">
						<div class="input-group">
							<div class="input-group-addon">标题</div>
							<input class="form-control" type="text" name="layTopic"
								id="layTopic">
						</div>
					</div>
				</div>
				<div class="col-md-2">
					<div class="form-group">
						<div class="input-group">
							<div class="input-group-addon">状态</div>
							<select class="form-control" id="state">
								<option value="">- - -请选择- - -</option>
								<option value="1">开启</option>
								<option value="2">关闭</option>
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
					sAjaxSource:"layLevel/initTable.htm",
					aoColumns: [
			            {
			               "data": "layImg",
			               "sTitle":"图片",
			               'sClass':"text-center",
			               "mRender": function(data, type, full) { 
				            	return "<img src='" + data + "' height='50px;'/>"; 
			              } 
			            },
                        {
                            "data": "layUrl",
                            "sTitle":"链接地址",
                            'sClass':"text-center"
                        },
                        {
                            "data": "layTopic",
                            "sTitle":"标题",
                            'sClass':"text-center"
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
                            "data": "version",
                            "sTitle":"使用版本号",
                            'sClass':"text-center"
                        },
                        {
                            "data": "versionRule",
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
                            "data": "startTime",
                            "sTitle":"开始时间",
                            'sClass':"text-center",
                            "mRender": function(data, type, full) {
                                if(data != null && data != '') {
                                    return data.substring(0,19);
                                }
                            }
                        },
                        {
                            "data": "endTime",
                            "sTitle":"结束时间",
                            'sClass':"text-center",
                            "mRender": function(data, type, full) {
                                if(data != null && data != '') {
                                    return data.substring(0,19);
                                }
                            }
                        },
                        {
                            "data": "startType",
                            "sTitle":"触发时间",
                            'sClass':"text-center",
                            "mRender": function(data, type, full) {
                                if(data == 1){
                                    return "<font >每日首次打开</font>";
                                } else if (data == 2) {
                                    return "<font >每次打开</font>";
                                } else {
                                    return "<font >首次打开</font>";
                                }
                            }
                        },
                        {
                            "data": "sort",
                            "sTitle":"排序",
                            'sClass':"text-center"
                        },
                        {
                            "data": "layLocation",
                            "sTitle":"弹出位置",
                            'sClass':"text-center",
                            "mRender": function(data, type, full) {
                                if(data == 1){
                                    return "<font >首页</font>";
                                } else if (data == 2) {
                                    return "<font >贷款大全</font>";
                                } else if (data == 3) {
                                    return "<font >活动</font>";
                                } else if (data == 4) {
                                    return "<font >社区</font>";
                                } else {
                                    return "<font >工具</font>";
                                }
                            }
                        },
                        {
                            "data": "oparationMan",
                            "sTitle":"操作人",
                            'sClass':"text-center"
                        },
                        {
                            "data": "modifyTime",
                            "sTitle":"操作时间",
                            'sClass':"text-center",
                            "mRender": function(data, type, full) {
                                if(data != null && data != '') {
									return data.substring(0,19);
                                }
                            }
                        }
			            ,{"data": "layLevelId","sTitle":"操作",'sClass':"text-center"}
			         ],
					 fnServerParams: function (aoData) {  //查询条件
		             	aoData.push({ "name": "layTopic", "value": $("#layTopic").val() } );
		             	aoData.push({ "name": "state", "value": $("#state").val() } );
                     },
		             aoColumnDefs : [
		             	{"aTargets" :14,"mRender" : function(data, type, row){
		             	   
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
				$('#myModal').deleteRow('layLevel/del.htm?id='+id);
			}
			//增加或者修改受影响的行数
			function addAndUpdateRow(id){
				$('#insertAndUpdate').addAndUpdateRow("layLevel/addAndUpdateHome.htm?id="+id);
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