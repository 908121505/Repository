<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
	<div class="content1">
		<div class="header">
			<h1 class="page-title">底部icon配置</h1>
			<ul class="breadcrumb">
				<li>APP配置</li>
				<li class="active">底部icon配置列表</li>
			</ul>
		</div>
		<div class="main-content">

			<table id="example" class="table"></table>
		</div>
		<script>
			//表格的初始化
			$(document).ready(function() {
				var table = $('#example').initTable({
					sAjaxSource:"bottomIcon/initTable.htm",
					aoColumns: [
			            {
			               "data": "preIconName",
			               "sTitle":"之前的名字",
			               'sClass':"text-center",
			               "mRender": function(data, type, full) {
			                   	if (data == '1') {
                                    return "首页";
                                } else if (data == '2') {
                                    return "贷款大全";
                                } else if (data == '3') {
                                    return "活动";
                                } else if (data == '4') {
                                    return "社区";
                                } else if (data == '5') {
                                    return "工具";
                                }else {
                                    return "--";
								}
			              } 
			            },
                        {
                            "data": "iconPosition",
                            "sTitle":"icon位置",
                            'sClass':"text-center"
                        },
                        {
                            "data": "iconName",
                            "sTitle":"icon名称",
                            'sClass':"text-center"
                        },
                        {
                            "data": "preIconImg",
                            "sTitle":"点击前icon图片",
                            'sClass':"text-center",
                            "mRender": function(data, type, full) {
                                return "<img src='" + data + "' height='50px;'/>";
                            }
                        },

                        {
                            "data": "aftIconImg",
                            "sTitle":"点击后icon图片",
                            'sClass':"text-center",
                            "mRender": function(data, type, full) {
                                return "<img src='" + data + "' height='50px;'/>";
                            }
                        },
                        {
                            "data": "state",
                            "sTitle":"状态",
                            'sClass':"text-center",
                            "mRender": function(data, type, full) {
                                if (data == '1') {
                                    return "<font color='green'>有效</font>";
                                } else if (data == '2') {
                                    return "<font color='red'>无效</font>";
                                } else {
                                    return "--";
                                }
                            }
                        },
                        {
                            "data": "modifyMan",
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
			            ,{"data": "id","sTitle":"操作",'sClass':"text-center"}
			         ],
					 fnServerParams: function (aoData) {  //查询条件

                     },
		             aoColumnDefs : [
		             	{"aTargets" :8,"mRender" : function(data, type, row){
		             	   
		             		var edit="";
		             			edit = "<a href='#' onclick='addAndUpdateRow(\""+data+"\")' data-toggle='modal' class='padding-right-small label label-success'><i class='glyphicon glyphicon-edit'></i> 编辑</a>";
		                    return edit+"&nbsp;";
		                }
		             }
		             ]
				});
				
				 $('#query').click(function(){
					$('#example').dataTable().fnDraw();
				});
			
			});
			//增加或者修改受影响的行数
			function addAndUpdateRow(id){
				$('#insertAndUpdate').addAndUpdateRow("bottomIcon/addAndUpdateHome.htm?id="+id);
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