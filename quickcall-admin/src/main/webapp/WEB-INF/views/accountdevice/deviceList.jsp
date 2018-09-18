<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
	<div class="content1">
		<div class="header">
			<h1 class="page-title">设备号限制</h1>
			<ul class="breadcrumb">
				<li>用户账号管理</li>
				<li class="active">设备列表</li>
			</ul>
		</div>
		<div class="main-content">
			<div class="row">
				<div class="col-md-2">
					<div class="form-group">
						<div class="input-group">
							<div class="input-group-addon">手机号</div>
							<input class="form-control" type="text" name="phoneNum"
								id="phoneNum">
						</div>
					</div>
				</div>
				<div class="col-md-2">
					<div class="form-group">
						<div class="input-group">
							<div class="input-group-addon">昵称</div>
							<input class="form-control" type="text" name="nickName"
								   id="nickName">
						</div>
					</div>
				</div>
				<div class="col-md-2">
					<div class="form-group">
						<div class="input-group">
							<div class="input-group-addon">设备号</div>
							<input class="form-control" type="text" name="deviceId"
								   id="deviceId">
						</div>
					</div>
				</div>
				<div class="col-md-2">
					<div class="form-group">
						<div class="input-group">
							<div class="input-group-addon">状态</div>
							<select class="form-control" id="deviceState">
								<option value="">- - -请选择- - -</option>
								<option value="0">正常</option>
								<option value="1">限制</option>
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
					sAjaxSource:"accountDevice/initTableNew.htm",
					aoColumns: [
                        {
                            "data": "phoneNum",
                            "sTitle":"手机号",
                            'sClass':"text-center"
                        },
                        {
                            "data": "nickName",
                            "sTitle":"昵称",
                            'sClass':"text-center",
                            "mRender": function(data, type, full) {
                                if(data != null && data != '') {
                                    return data;
                                } else {
                                    return "无";
                                }
                            }
                        },
                        {
                            "data": "deviceId",
                            "sTitle":"设备号",
                            'sClass':"text-center",
                            "mRender": function(data, type, full) {
                                if(data != null && data != '') {
                                    return data;
                                } else {
                                    return "无";
                                }
                            }
                        },

						{
                            "data": "deviceState",
                            "sTitle":"状态",
                            'sClass':"text-center",
                            "mRender": function(data, type, full) {
                                if(data == 0){
                                    return "<font color='green'>正常</font>";
                                }else{
                                    return "<font color='red'>限制</font>";
                                }
                            }
                        },
                        {
                            "data": "modifyMan",
                            "sTitle":"操作人",
                            'sClass':"text-center",
                            "mRender": function(data, type, full) {
                                if(data != null && data != '') {
                                    return data;
                                } else {
                                    return "无";
                                }
                            }
                        },
                        {
                            "data": "modifyTime",
                            "sTitle":"操作时间",
                            'sClass':"text-center",
                            "mRender": function(data, type, full) {
                                if(data != null && data != '') {
									return formatDateTime(data);
                                } else {
                                    return "无";
                                }
                            }
                        }
			            ,{"data": "accountId","sTitle":"操作",'sClass':"text-center"}
			         ],
					 fnServerParams: function (aoData) {  //查询条件
		             	aoData.push({ "name": "phoneNum", "value": $("#phoneNum").val() } );
                        aoData.push({ "name": "nickName", "value": $("#nickName").val() } );
                        aoData.push({ "name": "deviceId", "value": $("#deviceId").val() } );
		             	aoData.push({ "name": "deviceState", "value": $("#deviceState").val() } );
                     },
		             aoColumnDefs : [
		             	{"aTargets" :6,"mRender" : function(data, type, row){
		             	   
		             		var edit="";
		             			edit = "<a href='#' onclick='addAndUpdateRow(\""+data+"\")' data-toggle='modal' class='padding-right-small label label-success'><i class='glyphicon glyphicon-edit'></i> 编辑</a>";
		                    return edit;
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
				$('#insertAndUpdate').addAndUpdateRow("accountDevice/addAndUpdateHome.htm?id="+id);
			}
            function formatDateTime(inputTime) {
                var date = new Date(inputTime);
                var y = date.getFullYear();
                var m = date.getMonth() + 1;
                m = m < 10 ? ('0' + m) : m;
                var d = date.getDate();
                d = d < 10 ? ('0' + d) : d;
                var h = date.getHours();
                h = h < 10 ? ('0' + h) : h;
                var minute = date.getMinutes();
                var second = date.getSeconds();
                minute = minute < 10 ? ('0' + minute) : minute;
                second = second < 10 ? ('0' + second) : second;
                return y + '-' + m + '-' + d+' '+h+':'+minute+':'+second;
            };
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