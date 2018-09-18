<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<shiro:hasPermission name="productuser:statistics:select">
	<div class="content1">
		<div class="header">
			<h1 class="page-title">产品用户标签数据统计</h1>
			<ul class="breadcrumb">
				<li>产品用户标签数据统计</li>
				<li class="active">产品用户标签数据统计</li>
				<spam>&nbsp;&nbsp;<font color="red">(*时间不填或只填一个默认查近15天数据)</font></spam>
			</ul>
		</div>
		<div class="main-content">
			<div class="row">
				<div class="col-md-4">
					<div class="form-group">
						<div class="input-group">
							<div class="input-group-addon">时间</div>
							<input class="form-control" type="date" id="sTime">
							<div class="input-group-addon">到</div>
							<input class="form-control" type="date" id="eTime">
						</div>
					</div>
				</div>
				<div class="col-md-1">
					&nbsp;
				</div>
				<div class="col-md-1">
					<button type="button" class="btn btn-primary btn-small btn-block"
						id="query">
						<i class="glyphicon glyphicon-search"></i> 查询
					</button>
				</div>
				<div class="col-md-1">
					<div class="form-group">
						<a class="btn btn-primary btn-small btn-block" id="data_export"
							onclick="exportData();">导出数据</a>
					</div>
				</div>
			</div>
			<table id="example" class="table"></table>
		</div>

		<script>
			//表格的初始化
			$(document)
					.ready(
							function() {
								var table = $('#example')
										.initTable(
												{
													sAjaxSource : "productuser/statistics/initTable.htm",
													aoColumns : [
															{
																"data" : "productName",
																"sTitle" : "合作平台名称",
																'sClass' : "text-center"
															},
															{
																"data" : "labelName",
																"sTitle" : "标签名称",
																'sClass' : "text-center"
															},
															
															{
																"data" : "clickCount",
																"sTitle" : "平台点击次数",
																'sClass' : "text-center",
																"mRender" : function(
																		data,
																		type,
																		full) {
																	return "<a href='' onclick='statisticsDetail(this)' data-date="
																			+ full.date
																			+ " data-apply='0' data-name="
																			+ full.productId
																			+ "  data-toggle='modal'><u>"
																			+ data
																			+ "</u></a>"
																}
															},
															{
																"data" : "date",
																"sTitle" : "立即事件点击时间",
																'sClass' : "text-center"
															},
															{
																"data" : "parentId",
																"sTitle" : "子标签名称",
																'sClass' : "text-center"
															},
															{
																"data" : "applyCount",
																"sTitle" : "立即事件点击次数",
																'sClass' : "text-center",
																"mRender" : function(
																		data,
																		type,
																		full) {
																	return "<a href='' onclick='statisticsDetail(this)'data-date="
																			+ full.date
																			+ "  data-apply='1' data-name="
																			+ full.productId
																			+ "  data-toggle='modal'><u>"
																			+ data
																			+ "</u></a>"
																}
															} ],
													fnServerParams : function(
															aoData) { //查询条件
														aoData.push({
															"name" : "sDate",
															"value" : $(
																	"#sTime")
																	.val()
														});
														aoData.push({
															"name" : "eDate",
															"value" : $(
																	"#eTime")
																	.val()
														});
													},
													
												});

								$('#query').click(function() {
									$('#example').dataTable().fnDraw();
								});

							});

			function exportData() {
				var href = "export/cooperation.htm" + "?sDate="
						+ $("#sTime").val() + "&eDate=" + $("#eTime").val();
				$('#data_export').attr("href", href);
			}

			function statisticsDetail(obj) {
				var name = $(obj).attr("data-name");
				var apply = $(obj).attr("data-apply");
				var date = $(obj).attr("data-date");
				$('#insertAndUpdate').addAndUpdateRow(
						"arrondi/statistics/detail.htm?type=2&name=" + name
								+ "&apply=" + apply + "&date=" + date);
			}
		</script>
		<!---dialog选项-->
		<div>
			<jsp:include page="/WEB-INF/views/common/delete_dialog.jsp" />
			<jsp:include page="/WEB-INF/views/common/addupdate_dialog.jsp" />

		</div>
	</div>
</shiro:hasPermission>