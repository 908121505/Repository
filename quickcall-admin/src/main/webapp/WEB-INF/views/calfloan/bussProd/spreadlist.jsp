<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- <shiro:hasPermission name="product:select"> --%>
<div class="content1">
	<div class="header">
		<h1 class="page-title">商户活动管理</h1>
		<ul class="breadcrumb">
			<li>商务后台</li>
			<li class="active">商户活动管理</li>
		</ul>
	</div>
	<div class="main-content">
		<div class="row">
			<div class="col-md-2">
				<div class="form-group">
					<div class="input-group">
						<div class="input-group-addon">公司名称</div>
						<input class="form-control" type="text" id="companyNameQuery"
							placeholder="支持模糊查询">
					</div>
				</div>
			</div>


			<div class="col-md-2">
				<div class="form-group">
					<div class="input-group">
						<div class="input-group-addon">商户名称</div>
						<input class="form-control" type="text" id="prodName"
							placeholder="支持模糊查询"> <input type="hidden" id="isAdmin"
							name="isAdmin" value="0" /> <input type="hidden" id="paramIdStr"
							value="${paramId}" />
						<shiro:hasPermission name="spread:upload">
							<script type="text/javascript">
								$("#isAdmin").val("1");
							</script>
						</shiro:hasPermission>

					</div>
				</div>
			</div>

			<div class="col-md-2">
				<div class="form-group">
					<div class="input-group">
						<div class="input-group-addon">活动类型</div>
						<select class="form-control" id="spreadTypeQuery">
							<option value="">--全部--</option>
							<c:if test="${not empty  tglxList}">
								<c:forEach items="${tglxList }" var="item">
									<option value="${item.id }">${item.modelContent }</option>
								</c:forEach>
							</c:if>
						</select>
					</div>
				</div>
			</div>
			<!-- 商户状态 -->
			<div class="col-md-2">
				<div class="form-group">
					<div class="input-group">
						<div class="input-group-addon">商户状态</div>
						<select class="form-control" id="stateQuery">
							<option value="">--全部--</option>
							<option value="1">已上线</option>
							<option value="999">APP在线</option>
							<option value="2">已下线</option>
							<option value="30">待预约上线</option>
							<option value="3">已预约上线</option>
							<option value="4">待上线</option>
							<option value="5">待下线</option>
						</select>
					</div>
				</div>
			</div>
			<!-- 审核状态 -->
			<div class="col-md-2">
				<div class="form-group">
					<div class="input-group">
						<div class="input-group-addon">审核状态</div>
						<select class="form-control" id="auditStatus">
							<option value="">--全部--</option>
							<option value="1">待审核</option>
							<option value="2">审核通过</option>
							<option value="3">审核未通过</option>
						</select>
					</div>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-md-2">
				<div class="form-group">
					<div class="input-group">
						<div class="input-group-addon">是否推荐</div>
						<select class="form-control" id="recommendFlag">
							<option value="">--全部--</option>
							<option value="1">推荐</option>
							<option value="2">不推荐</option>
						</select>
					</div>
				</div>
			</div>

				<div class="col-md-2">
					<div class="form-group">
						<div class="input-group">
							<div class="input-group-addon">排序</div>
							<select class="form-control" id="sortFlag">
								<option value="modifyTimeDesc">修改时间倒序</option>
								<option value="modifyTimeAsc">修改时间正序</option>
								<option value="createTimeDesc">创建时间倒序</option>
								<option value="createTimeAsc">创建时间正序</option>
								<option value="sortAsc">权重正序（从大到小）</option>
								<option value="sortDesc">权重倒序（从小到大）</option>
							</select>
						</div>
					</div>
				</div>





				<div class="col-md-2">
					<div class="form-group">
						<div class="input-group">
							<div class="input-group-addon">上线日期</div>
							<input class="form-control" type="date" id="onlineTime">
						</div>
					</div>
				</div>
				<div class="col-md-2">
					<div class="form-group">
						<div class="input-group">
							<div class="input-group-addon">下线日期</div>
							<input class="form-control" type="date" id="orderOfflineTime">
						</div>
					</div>
				</div>
				<div class="col-md-2">
					<div class="form-group">
						<div class="input-group">
							<div class="input-group-addon">最近对账日期</div>
							<input class="form-control" type="date" id="tallyTime">
						</div>
					</div>
				</div>
		</div>
		<div class="row">
		<div class="col-md-2">
			<div class="form-group">
				<div class="input-group">
					<div class="input-group-addon">是否在贷款大全显示</div>
					<select class="form-control" id="showFlagQuery">
						<option value="">--全部--</option>
						<option value="2">是</option>
						<option value="1">否</option>
					</select>
				</div>
			</div>
			</div>
		
		
			<!-- 查询权限：查询+审批+详情 -->
			<div class="col-md-1">
				<button type="button" class="btn btn-primary btn-small btn-block"
					id="query">
					<i class="glyphicon glyphicon-search"></i> 查询
				</button>
			</div>
			<!-- 查询权限：新增+对账+详情 -->
			<shiro:hasPermission name="spread:select">
				<div class="col-md-1">
					<button type="button" class="btn btn-info btn-small btn-block"
						onclick="addAndUpdateRow('')">
						<i class="glyphicon glyphicon-plus"></i> 新增
					</button>
				</div>
				<div class="col-md-1" style="width: 8%;">
					<button type="button" class="btn btn-info btn-small btn-block"
						onclick="tallyinfo()" id="tallyinfo">
						<i class="glyphicon"></i> 对账
					</button>
				</div>
			</shiro:hasPermission>
			<!-- 下载权限：导出Excel -->
			<shiro:hasPermission name="spread:download">
				<div class="col-md-1">
					<div class="form-group">
						<a class="btn btn-primary btn-small btn-block" id="message_export"
							onclick="exportMessage();">导出数据</a>
					</div>
				</div>
			</shiro:hasPermission>
			<shiro:hasPermission name="spread:update">
			    <div class="col-md-3">
	                   <input type="file" class="form-control" id="importDataFile"  name="importDataFile" style="width: 60%;float: left;"> 
					   <button class="btn btn-success" type="button" id="uploadDataFile" style="width: 30%;float: left;">Excel导入排序</button>
	            </div> 
			</shiro:hasPermission>

		</div>




		<table id="example" class="table"></table>
	</div>
	<script type="text/JavaScript" charset="UTF-8">
		function Format(now, mask) {
			var d = now;
			var zeroize = function(value, length) {
				if (!length)
					length = 2;
				value = String(value);
				for (var i = 0, zeros = ''; i < (length - value.length); i++) {
					zeros += '0';
				}
				return zeros + value;
			};

			return mask
					.replace(
							/"[^"]*"|'[^']*'|\b(?:d{1,4}|m{1,4}|yy(?:yy)?|([hHMstT])\1?|[lLZ])\b/g,
							function($0) {
								switch ($0) {
								case 'd':
									return d.getDate();
								case 'dd':
									return zeroize(d.getDate());
								case 'ddd':
									return [ 'Sun', 'Mon', 'Tue', 'Wed', 'Thr',
											'Fri', 'Sat' ][d.getDay()];
								case 'dddd':
									return [ 'Sunday', 'Monday', 'Tuesday',
											'Wednesday', 'Thursday', 'Friday',
											'Saturday' ][d.getDay()];
								case 'M':
									return d.getMonth() + 1;
								case 'MM':
									return zeroize(d.getMonth() + 1);
								case 'MMM':
									return [ 'Jan', 'Feb', 'Mar', 'Apr', 'May',
											'Jun', 'Jul', 'Aug', 'Sep', 'Oct',
											'Nov', 'Dec' ][d.getMonth()];
								case 'MMMM':
									return [ 'January', 'February', 'March',
											'April', 'May', 'June', 'July',
											'August', 'September', 'October',
											'November', 'December' ][d
											.getMonth()];
								case 'yy':
									return String(d.getFullYear()).substr(2);
								case 'yyyy':
									return d.getFullYear();
								case 'h':
									return d.getHours() % 12 || 12;
								case 'hh':
									return zeroize(d.getHours() % 12 || 12);
								case 'H':
									return d.getHours();
								case 'HH':
									return zeroize(d.getHours());
								case 'm':
									return d.getMinutes();
								case 'mm':
									return zeroize(d.getMinutes());
								case 's':
									return d.getSeconds();
								case 'ss':
									return zeroize(d.getSeconds());
								case 'l':
									return zeroize(d.getMilliseconds(), 3);
								case 'L':
									var m = d.getMilliseconds();
									if (m > 99)
										m = Math.round(m / 10);
									return zeroize(m);
								case 'tt':
									return d.getHours() < 12 ? 'am' : 'pm';
								case 'TT':
									return d.getHours() < 12 ? 'AM' : 'PM';
								case 'Z':
									return d.toUTCString().match(/[A-Z]+$/);
									// Return quoted strings with the surrounding quotes removed
								default:
									return $0.substr(1, $0.length - 2);
								}
							});
		};

		function exportMessage() {
			var companyName = $("#companyNameQuery").val();
			
			var prodName = $("#prodName").val();
			
			var spreadType = $("#spreadTypeQuery").val();
			var state = $("#stateQuery").val();
			var auditStatus = $("#auditStatus").val();
			var onlineTime = $("#onlineTime").val();
			var orderOfflineTime = $("#orderOfflineTime").val();
			var tallyTime = $("#tallyTime").val();
			var paramId = $("#paramIdStr").val();
			var recommendFlag = $("#recommendFlag").val();
			var sortFlag = $("#sortFlag").val();
			
			var  showFlag =  $("#showFlagQuery").val();

			var href = "export/productSpread.htm" + "?prodName="
					+ encodeURI(encodeURIComponent(prodName)) + "&spreadType="
					+ spreadType + "&state=" + state + "&auditStatus="
					+ auditStatus + "&onlineTime=" + onlineTime
					+ "&orderOfflineTime=" + orderOfflineTime + "&tallyTime="
					+ tallyTime + "&paramId=" + paramId + "&recommendFlag="
					+ recommendFlag + "&sortFlag=" + sortFlag
					+ "&showFlag=" + showFlag+ "&companyName=" + encodeURI(encodeURIComponent(companyName));

			$('#message_export').attr("href", href);
		}
		/////////////////////////////////////////////////////////////////////////////////////
		//表格的初始化
		$(document).ready(function() {
				var table = $('#example').initTable({
									sAjaxSource : "spread/initTable.htm",
									aoColumns : [
											{
												"data" : "companyName",
												"sTitle" : "公司名称",
												'sClass' : "text-center",
												"mRender" : function(data, type,full) {
													if (data == null) {
														return "<font>--</font>";
													}else{
														if(data.length > 6){
															var head =  data.substring(0,6);
				                                            return "<font>"+head +"......</font>";
														}else{
															return "<font>"+data+"</font>";
														}
													}
													
												}
											},
											{
												"data" : "prodName",
												"sTitle" : "商户名称",
												'sClass' : "text-center"
											},
											{
												"data" : "spreadType",
												"sTitle" : "活动类型",
												'sClass' : "text-center",
											},
											{
												"data" : "state",
												"sTitle" : "商户状态",
												'sClass' : "text-center",
												"mRender" : function(
														data, type,
														full) {
													if (data == null) {
														return "<font color='red'>--</font>";
													}
													if (data == 1) {
														return "<font color='red'>已上线</font>";
													} else if (data == 2) {
														return "<font color='blue'>已下线</font>";
													} else if (data == 30) {
														return "<font color='blue'>待预约上线</font>";
													} else if (data == 3) {
														return "<font color='red'>已预约上线</font>";
													} else if (data == 4) {
														return "<font color='blue'>待上线</font>";
													} else if (data == 5) {
														return "<font color='red'>待下线</font>";
													}
												}
											},
											{
												"data" : "auditStatus",
												"sTitle" : "审核状态",
												'sClass' : "text-center",
												"mRender" : function(
														data, type,
														full) {
													if (data == null) {
														return "<font color='red'>--</font>";
													}
													if (data == 1) {
														return "<font color='red'>待审核</font>";
													} else if (data == 2) {
														return "<font color='green'>审核通过</font>";
													} else if (data == 3) {
														return "<font color='blue'>审核未通过</font>";
													}
												}
											},

											{
												"data" : "auditReson",
												"sTitle" : "审核不通过原因",
												'sClass' : "text-center"
											},
											{
												"data" : "offlineReason",
												"sTitle" : "下线理由",
												'sClass' : "text-center"
											},
											{
												"data" : "recommentFlag",
												"sTitle" : "是否推荐",
												'sClass' : "text-center",
												"mRender" : function(
														data, type,
														full) {
													if (data == null) {
														return "<font color='blue'>--</font>";
													}
													if (data == 1) {
														return "<font color='red'>推荐</font>";
													} else if (data == 2) {
														return "<font color='blue'>不推荐</font>";
													}else {
														return "<font color='red'>--</font>";
													}
												}
											},
											{
												"data" : "sort",
												"sTitle" : "权重值",
												'sClass' : "text-center"
											},
											{
												"data" : "orderTime",
												"sTitle" : "预约上线时间",
												'sClass' : "text-center",
												"mRender" : function(
														data, type,
														full) {
													if (data != null) {
														return Format(
																new Date(
																		data),
																"yyyy-MM-dd HH:mm:ss")
													} else {
														return '--';
													}
												}
											},
											{
												"data" : "onlineTime",
												"sTitle" : "上线时间",
												'sClass' : "text-center",
												"mRender" : function(
														data, type,
														full) {
													if (data != null) {
														return Format(
																new Date(
																		data),
																"yyyy-MM-dd HH:mm:ss")
													} else {
														return '--';
													}
												}
											},
											{
												"data" : "offlineTime",
												"sTitle" : "下线时间",
												'sClass' : "text-center",
												"mRender" : function(
														data, type,
														full) {
													if (data != null) {
														return Format(
																new Date(
																		data),
																"yyyy-MM-dd HH:mm:ss")
													} else {
														return '--';
													}
												}
											},
											{
												"data" : "tallyDate",
												"sTitle" : "最近对账日期",
												'sClass' : "text-center",
												"mRender" : function(
														data, type,
														full) {
													if (data != null) {
														return Format(
																new Date(
																		data),
																"yyyy-MM-dd")
													} else {
														return '--';
													}
												}
											},
								/*{
												"data" : "productUrl",
												"sTitle" : "链接",
												'sClass' : "text-center"
											}, */
											{
												"data" : "backFlag",
												"sTitle" : "是否有后台",
												'sClass' : "text-center",
												"mRender" : function(
														data, type,
														full) {
													if (data == null) {
														return "<font color='blue'>--</font>";
													}
													if (data == 0) {
														return "<font color='red'>无</font>";
													} else if (data == 1) {
														return "<font color='blue'>有</font>";
													}
												}
											},
											{
												"data" : "sumCpa",
												"sTitle" : "日均CPA",
												'sClass' : "text-center"
											},
											{
												"data" : "registerCount",
												"sTitle" : "总注册数",
												'sClass' : "text-center"
											},
											{
												"data" : "tallyAmount",
												"sTitle" : "总结算金额",
												'sClass' : "text-center"
											},
											{
												"data" : "showFlag",
												"sTitle" : "贷款大全显示",
												'sClass' : "text-center",
												"mRender" : function(data, type,full) {
													if (data == null) {
														return "<font color='blue'>--</font>";
													}
													if (data == 1) {
														return "<font color='red'>否</font>";
													} else if (data == 2) {
														return "<font color='blue'>是</font>";
													}
												}
											},
											{
												"data" : "owner",
												"sTitle" : "归属人",
												'sClass' : "text-center"
											},

											{
												"data" : "createTime",
												"sTitle" : "创建时间",
												'sClass' : "text-center",
												"mRender" : function(
														data, type,
														full) {
													if (data != null) {
														return Format(
																new Date(
																		data),
																"yyyy-MM-dd HH:mm:ss")
													} else {
														return '--';
													}
												}
											},
											{
												"data" : "modifyMan",
												"sTitle" : "修改人",
												'sClass' : "text-center"
											},
											{
												"data" : "modifyTime",
												"sTitle" : "修改时间",
												'sClass' : "text-center",
												"mRender" : function(
														data, type,
														full) {
													if (data != null) {
														return Format(
																new Date(
																		data),
																"yyyy-MM-dd HH:mm:ss")
													} else {
														return '--';
													}
												}
											},

											{
												"data" : "productId",
												"sTitle" : "操作",
												'sClass' : "text-center"
											}

									],
									fnServerParams : function(
											aoData) { //查询条件
										aoData.push({
													"name" : "companyName",
													"value" : $("#companyNameQuery").val().replace(new RegExp(" ","g"),"")
												});
										aoData.push({
													"name" : "prodName",
													"value" : $("#prodName").val().replace(new RegExp(" ","g"),"")
												});
										aoData.push({
													"name" : "spreadType",
													"value" : $("#spreadTypeQuery").val()
												});
										aoData.push({
											"name" : "state",
											"value" : $("#stateQuery").val()
										});
										aoData.push({
											"name" : "auditStatus",
											"value" : $("#auditStatus").val()
										});
										aoData.push({
											"name" : "onlineTime",
											"value" : $("#onlineTime").val()
										});
										aoData.push({
													"name" : "orderOfflineTime",
													"value" : $("#orderOfflineTime").val()
												});
										aoData.push({
											"name" : "tallyTime",
											"value" : $("#tallyTime").val()
										});
										aoData.push({
											"name" : "paramId",
											"value" : $("#paramIdStr").val()
										});
										aoData.push({
													"name" : "recommendFlag",
													"value" : $("#recommendFlag").val()
										});
										aoData.push({
													"name" : "sortFlag",
													"value" : $("#sortFlag").val()
										});
										aoData.push({
											"name" : "isAdmin",
											"value" : $("#isAdmin").val()
										});
										aoData.push({
											"name" : "showFlag",
											"value" : $("#showFlagQuery").val()
										});
									},
									aoColumnDefs : [ {
										"aTargets" : 22,
										"mRender" : function(data,
												type, row) {
											var edit = "", detail = "", approve = "", tally = "";
										/* 	var auditStatus = row.auditStatus;
											//只有待审核状态才能被审核
											if (auditStatus == 1) {
												// 添加权限：审批
												<shiro:hasPermission name="spread:add">
												approve = "<a href='#' onclick='approvetest(\""
														+ row.productId
														+ "\")' data-toggle='modal' class='padding-right-small label label-success'><i class='glyphicon glyphicon-edit'></i> 审批</a>";
												</shiro:hasPermission>
											} */
											// 查询权限：新增+对账+详情
											<shiro:hasPermission name = "spread:select">
											detail = "<a href='#' onclick='detailRow(\""
													+ row.productId
													+ "\")' data-toggle='modal' class='padding-right-small label label-success'><i class='glyphicon glyphicon-edit'></i>详情</a>";
											</shiro:hasPermission>
											<shiro:hasPermission name = "spread:delete">
											detail = "<a href='#' onclick='detailRow(\""
													+ row.productId
													+ "\")' data-toggle='modal' class='padding-right-small label label-success'><i class='glyphicon glyphicon-edit'></i>详情</a>";
											</shiro:hasPermission>
											//修改权限：对账记录
											<shiro:hasPermission name = "spread:select">
											tally = "<a href='#' onclick='jilu(\""
													+ row.productId
													+ "\")' data-toggle='modal' class='padding-right-small label label-success'><i class='glyphicon glyphicon-edit'></i>对账记录</a>";
											</shiro:hasPermission>
											return approve
													+ "&nbsp;"
													+ edit
													+ "&nbsp;"/* +del+"&nbsp;" */
													+ detail
													+ "&nbsp;"
													+ tally;
										}
									} ]

							});
							
							
							 //商户文件上传
							$('#uploadDataFile').click(function() {
								var file = $("#importDataFile").val();
								if(file == ""){
									alert("请选择文件");
									return false;
								}
								
								if(!/\.(XLSX)$/.test(file.toUpperCase())){
									$("#tip").html("文件格式错误，请选择Excel2007或者以上版本文件");
									return false;
								}
								
								
								
								$.ajaxFileUpload({
									type : "post",
									dataType : "json",
									fileElementId : 'importDataFile',
									url : "spread/excelSort.htm",
									data : {
										"id" : ""
									},
									error : function(data) {
										$("#importDataFile").val("");
										alert("错误！" + data.msg);
									},
									success : function(data) {
										$("#importDataFile").val("");
										alert(data.msg);
									}
								});
							});
							

							$('#query').click(function() {
								$('#example').dataTable().fnDraw();
							});

							var showFlag = "${showFlag}";
							if (showFlag == '1' || showFlag == 1) {
								<shiro:hasPermission name = "spread:select">
								$('#tallyinfo').trigger("click");
								</shiro:hasPermission>
							}

							var paramIdStr = $("#paramIdStr").val();
							if (paramIdStr.length > 0) {
								$('#query').trigger("click");
								$("#paramIdStr").val("");
							}
						});
		/////////////////////////////////////////////////////////////////////////////////////

		//增加或者修改受影响的行数
		function addAndUpdateRow(id) {
			var createFlag = '${createFlag}';
			if (createFlag == '0') {
				id = '${paramId}'
				$('#insertAndUpdate').addAndUpdateRow(
						"spread/company.htm?id=" + id);
			} else {
				$('#insertAndUpdate').addAndUpdateRow(
						"spread/addAndUpdateHome.htm?id=" + id);
			}
		};
		//增加或者修改受影响的行数
		function tallyinfo() {
			var id = "";
			<shiro:hasPermission name = "spread:select">//对账权限
			<shiro:hasPermission name = "spread:upload">//查询部分权限
			id = "1";
			</shiro:hasPermission>
			</shiro:hasPermission>
			$('#insertAndUpdate').addAndUpdateRow(
					"spread/tallyinfo.htm?id=" + id);
		};
		//审批
		function approvetest(id, auditStatus) {
			$('#insertAndUpdate').addAndUpdateRow(
					"spread/approveinfo.htm?id=" + id);
		};
		function detailRow(id) {
			$('#insertAndUpdate').addAndUpdateRow(
					"spread/addAndUpdateHome.htm?id=" + id);
		};
		function jilu(id) {
			$('#content').load('dailyRevenue/skipHome.htm?productId=' + id)
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
</SHIRO:HASPERMISSION>