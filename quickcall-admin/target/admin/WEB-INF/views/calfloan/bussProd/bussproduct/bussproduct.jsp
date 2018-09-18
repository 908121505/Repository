<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<shiro:hasPermission name="bussProduct:select">
	<div class="content1">
		<div class="header">
			<h1 class="page-title">我的商户</h1>
			<ul class="breadcrumb">
				<li>商务后台</li>
				<li class="active">我的商户</li>
			</ul>
		</div>
		<div class="main-content">
			<div class="row">
				<table>
					<tr>
						<td>

							<div class="col-md-4">
								<div class="form-group">
									<div class="input-group">
										<div class="input-group-addon">时间</div>
										<input class="form-control" type="date" id="startzTime">
										<div class="input-group-addon">到</div>
										<input class="form-control" type="date" id="endzTime">
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
										<i class="glyphicon glyphicon-plus"></i> 新增
									</button>
								</div>
							<shiro:hasPermission name = "bussProduct:download">
								<div class="col-md-2">

									<a id="message_export" onclick="exportMessage()"> 导出Excel</a>
								</div>
							</shiro:hasPermission>
						</td>
					</tr>
					<tr>
						<td>
							<div class="col-md-2">
								<div class="form-group">
									<div class="input-group">
										<div class="input-group-addon">公司名称</div>
										<input class="form-control" type="text" name="companyName"
											   id="companyName_query" placeholder="支持模糊搜索">
										<input type="hidden" id = "isAdmin" name="isAdmin" value="isAdmin"/>
										<shiro:hasPermission name = "bussProduct:delete">
											<script type="text/javascript">
                                                $("#isAdmin").val("");
											</script>
										</shiro:hasPermission>
									</div>
								</div>
							</div>
							<div class="col-md-2">
								<div class="form-group">
									<div class="input-group">
										<div class="input-group-addon">商户名称</div>
										<input placeholder="支持模糊搜索" class="form-control" type="text" name="productName"
											   id="productName_query">
									</div>
								</div>
							</div>
							<div class="col-md-2">
								<div class="form-group">
									<div class="input-group">
										<div class="input-group-addon">归属人</div>
										<input class="form-control" type="text" name="owner"
											   id="owner_query" placeholder="支持模糊搜索">
									</div>
								</div>
							</div>
						</td>
					</tr>
				</table>

			</div>
			<table id="example" class="table"></table>
		</div>
		<script>
			//表格的初始化
			$(document).ready(function() {
				var table = $('#example').initTable({
					sAjaxSource:"bussProduct/initTableNew.htm",
					aoColumns: [
			            {
			               "data": "companyName",
			               "sTitle":"公司名称",
			               'sClass':"text-center"
			            },
                        {
                            "data": "productName",
                            "sTitle":"商户名称",
                            'sClass':"text-center",
                            "mRender": function(data, type, full) {
                                if(data !== null && data !== ''){
                                    return data;
                                } else {
                                    return "--";
                                }
                            }
                        },
                        {
                            "data": "payAmount",
                            "sTitle":"总打款",
                            'sClass':"text-center",
                            "mRender": function(data, type, full) {
                                if(data !== null && data !== ''){
                                    return data;
                                } else {
                                    return "--";
                                }
                            }
                        },
                        {
                            "data": "arrearsAmount",
                            "sTitle":"总欠款",
                            'sClass':"text-center",
                            "mRender": function(data, type, full) {
                                if(data !== null && data < 0){
                                    return "<font color='red'>"+data+"</font>";
                                } else if (data !== null && data >= 0){
                                    return "<font color='green'>"+data+"</font>";
                                } else if (data == '未对账'){
                                    return "<font color='red'>"+data+"</font>";
                                } else {
                                    return "--";
                                }
                            }
                        },
                        {
                            "data": "settleDate",
                            "sTitle":"结算周期",
                            'sClass':"text-center",
                            "mRender": function(data, type, full) {
                                if(data !== null && data !== ''){
                                    return data;
                                } else {
                                    return "--";
                                }
                            }
                        },
                        {
                            "data": "companyNature",
                            "sTitle":"公司性质",
                            'sClass':"text-center",
                            "mRender": function(data, type, full) {
                                if(data !== null && data !== ''){
                                    return data;
                                } else {
                                    return "--";
                                }
                            }
                        },
                        {
                            "data": "cooperModel",
                            "sTitle":"合作模式",
                            'sClass':"text-center",
                            "mRender": function(data, type, full) {
                                if(data !== null && data !== ''){
                                    return data;
                                } else {
                                    return "--";
                                }
                            }
                        },
						{
                            "data": "comContactName",
                            "sTitle":"联系人",
                            'sClass':"text-center",
                            "mRender": function(data, type, full) {
                                if(data !== null && data !== ''){
                                    return data;
                                } else {
                                    return "--";
                                }
                            }
                        },
                        {
                            "data": "comContactMobile",
                            "sTitle":"联系电话",
                            'sClass':"text-center",
                            "mRender": function(data, type, full) {
                                if(data !== null && data !== ''){
                                    return data;
                                } else {
                                    return "--";
                                }
                            }
                        },
                        {
                            "data": "owner",
                            "sTitle":"归属人",
                            'sClass':"text-center",
                            "mRender": function(data, type, full) {
                                if(data !== null && data !== ''){
                                    return data;
                                } else {
									return "--";
                                }
                            }
                        },
                        {
                            "data": "createTime",
                            "sTitle":"创建时间",
                            'sClass':"text-center",
                            "mRender": function(data, type, full) {
                                if(data !== null && data !== '' && data != undefined){
                                    return formatDateTime(data);
                                } else {
                                    return "--";
                                }
                            }
                        },
                        {
                            "data": "modifyTime",
                            "sTitle":"修改时间",
                            'sClass':"text-center",
                            "mRender": function(data, type, full) {
                                if(data !== null && data !== '' && data != undefined){
                                    return formatDateTime(data);
                                } else {
                                    return "--";
                                }
                            }
                        },
						{
						    "data": "id",
							"sTitle":"操作",
							'sClass':"text-center"
						}
			         ],
					 fnServerParams: function (aoData) {  //查询条件
		             	aoData.push({ "name": "companyName", "value": $("#companyName_query").val().replace(new RegExp(" ","g"),"") } );
		             	aoData.push({ "name": "productName", "value": $("#productName_query").val().replace(new RegExp(" ","g"),"") } );
					 	aoData.push({ "name": "owner", "value": $("#owner_query").val().replace(new RegExp(" ","g"),"") } );
                         aoData.push({ "name": "startTime", "value": $("#startzTime").val() } );
                         aoData.push({ "name": "endTime", "value": $("#endzTime").val()} );
                         aoData.push({ "name": "isAdmin", "value": $("#isAdmin").val()} );
                     },
		             aoColumnDefs : [
		             	{"aTargets" :12,"mRender" : function(data, type, row){
		             	   
		             		var edit="",del="",detail="",settle="";
								edit = "<a href='#' onclick='productDetial(\""+data+"\")' data-toggle='modal' class='padding-right-small label label-success'><i class='glyphicon glyphicon-edit'></i> 详情</a>";

								<shiro:hasPermission name="bussProduct:add">
									detail = "<a href='#' onclick='pushCompany(\""+data+"\")' data-toggle='modal' class='padding-right-small label label-success'><i class='glyphicon glyphicon-edit'></i> 推广</a>";
                            	</shiro:hasPermission>
								<shiro:hasPermission name="bussProduct:update">
									settle = "<a href='#' onclick='settleAccounts(\""+data+"\")' data-toggle='modal' class='padding-right-small label label-success'><i class='glyphicon glyphicon-edit'></i> 结算</a>";
                            	</shiro:hasPermission>
		             				// del ="<a href='#' onclick='deleteRow(\""+data+"\")' data-toggle='modal' class='label label label-danger'><i class='glyphicon glyphicon-trash'></i> 删除 </a>";

		                    return edit+"&nbsp;"+detail+"&nbsp;"+settle+"&nbsp;"+del;
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
				$('#myModal').deleteRow('bussProduct/del.htm?id='+id);
			}
			//增加或者修改受影响的行数
			function addAndUpdateRow(id){
				$('#insertAndUpdate').addAndUpdateRow("bussProduct/addAndUpdateHome.htm?id="+id);
			}
			// 推广
			function pushCompany(id) {
                $('#content').load('spread/skiphome.htm?paramId='+id);
            }
			// 产品结算
			function settleAccounts(id) {
                $('#insertAndUpdate').addAndUpdateRow("bussProduct/settleAccountsHome?id="+id);
            }
            // 产品详情
			function productDetial(id) {
                $('#insertAndUpdate').addAndUpdateRow("bussProduct/productDetial?id="+id);
            }
            // 导出csv
            function exportMessage(){
                var companyName = $("#companyName_query").val();
                var productName = $("#productName_query").val();
                var owner = $("#owner_query").val();
                var startTime = $("#startzTime").val();
                var endTime = $("#endzTime").val();
                var isAdmin = $("#isAdmin").val();
                var href="bussProduct/exportCsv"
                    +"?companyName="+encodeURI(encodeURIComponent(companyName))
                    +"&productName="+encodeURI(encodeURIComponent(productName))
                    +"&startTime="+startTime
                    +"&endTime="+endTime
                    +"&isAdmin="+isAdmin
                    +"&owner="+encodeURI(encodeURIComponent(owner));
                $('#message_export').attr("href",href );
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
</shiro:hasPermission>