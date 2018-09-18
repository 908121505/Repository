<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div class="content1">
	<div class="header">
		<h1 class="page-title">导出后台数据</h1>
		<ul class="breadcrumb">
			<li>前端推送</li>
			<li class="active">导出后台数据</li>
		</ul>
	</div>
	<div class="main-content">
		<div class="col-md-12">
			<blockquote>
			  <p class="text-warning">注意：导出数据时，<code>开始时间</code>和<code>结束时间</code>必填,并且开始时间不能大于结束时间！</p>
			</blockquote>
		</div>
		
		<div class="col-md-4">
			<div class="panel panel-primary">
			    <div class="panel-heading">
			      <h3 class="panel-title">导出用户信息</h3>
			    </div>
			    <div class="panel-body" id="pushcolumndata">
			    	<div class="form-group">
						<div class="input-group">
							<div class="input-group-addon">项目名称</div>
							<select class="form-control" id="user_app_name">
								<option value="standard">--OTT标准版5.3--</option>
							</select>
						</div>
					</div>
			    	<div class="form-group">
						<div class="input-group">
							<div class="input-group-addon">开始时间</div>
							<input class="form-control" type="date" id="user_begin_time">
						</div>
					</div>
					<div class="form-group">
						<div class="input-group">
							<div class="input-group-addon">结束时间</div>
							<input class="form-control" type="date" id="user_end_time">
						</div>
					</div>
					<a class="btn btn-primary btn-lg btn-block" id="user_export" onclick="exportUser()">确认导出用户信息</a>
			    </div>
			</div>
		</div>
		
		<div class="col-md-4">
			<div class="panel panel-success">
			    <div class="panel-heading">
			      <h3 class="panel-title">导出播放记录</h3>
			    </div>
			    <div class="panel-body" id="pushcolumndata">
			    	<div class="form-group">
						<div class="input-group">
							<div class="input-group-addon">项目名称</div>
							<select class="form-control" id="play_app_name">
								<option value="standard">--OTT标准版5.3--</option>
							</select>
						</div>
					</div>
			    	<div class="form-group">
						<div class="input-group">
							<div class="input-group-addon">开始时间</div>
							<input class="form-control" type="date" id="play_begin_time">
						</div>
					</div>
					<div class="form-group">
						<div class="input-group">
							<div class="input-group-addon">结束时间</div>
							<input class="form-control" type="date" id="play_end_time">
						</div>
					</div>
					<a type="button" class="btn btn-success btn-lg btn-block" id="paly_export" onclick="exportPlay()">确认导出播放记录</a>
			    </div>
			</div>
		</div>
		
		<div class="col-md-4">
			<div class="panel panel-info">
			    <div class="panel-heading">
			      <h3 class="panel-title">导出订单信息</h3>
			    </div>
			    <div class="panel-body" id="pushcolumndata">
			    	<div class="form-group">
						<div class="input-group">
							<div class="input-group-addon">项目名称</div>
							<select class="form-control" id="order_app_name">
								<option value="standard">--OTT标准版5.3--</option>
							</select>
						</div>
					</div>
			    	<div class="form-group">
						<div class="input-group">
							<div class="input-group-addon">开始时间</div>
							<input class="form-control" type="date" id="order_begin_time">
						</div>
					</div>
					<div class="form-group">
						<div class="input-group">
							<div class="input-group-addon">结束时间</div>
							<input class="form-control" type="date" id="order_end_time">
						</div>
					</div>
					<a type="button" class="btn btn-info btn-lg btn-block" id="order_export" onclick="exportOrder()">确认导出订单信息</a>
			    </div>
			</div>
		</div>
	</div>
	
	<script type="text/javascript">
		function exportUser(){
			var href="export/user.htm"
					+"?begin_time="+$("#user_begin_time").val()
					+"&end_time="+$("#user_end_time").val()
					+"&app_name="+$("#user_app_name").val();
			$('#user_export').attr("href",href);
		}
		function exportPlay(){
			var href="export/play.htm"
					+"?begin_time="+$("#play_begin_time").val()
					+"&end_time="+$("#play_end_time").val()
					+"&app_name="+$("#play_app_name").val();
			$('#paly_export').attr("href",href);
		}
		function exportOrder(){
			var href="export/order.htm"
					+"?begin_time="+$("#order_begin_time").val()
					+"&end_time="+$("#order_end_time").val()
					+"&app_name="+$("#order_app_name").val();
			$('#order_export').attr("href",href);
		}
	</script>
</div>