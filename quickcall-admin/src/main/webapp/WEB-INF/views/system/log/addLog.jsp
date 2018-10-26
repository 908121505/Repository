<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<div class="modal-dialog">
	<div class="modal-content">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal"
				aria-hidden="true">X</button>
			<h3 id="myModalLabel">查看日志详细信息</h3>
		</div>
		<div class="modal-body">
			<form class="form-horizontal" method="post" action="system/user/save${empty entity?'Insert':'Update' }.htm" role="form">
				<div class="form-group">
					<label class="col-sm-2 control-label">操作人</label>
					<div class="col-sm-10">
						<input type="text" class="form-control" value="${entity.account }">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">操作类型</label>
					<div class="col-sm-10">
						<input type="text" class="form-control" name="oper_type" value="${entity.oper_type }">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">请求地址</label>
					<div class="col-sm-10">
						<input type="text" class="form-control" name="oper_url" value="${entity.oper_url }">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">请求参数</label>
					<div class="col-sm-10">
						<textarea class="form-control" name="oper_param" rows="5">${entity.oper_param }</textarea>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">IP地址</label>
					<div class="col-sm-10">
						<input type="text" class="form-control" name="ip_addr" value="${entity.ip_addr }">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">请求时间</label>
					<div class="col-sm-10">
						<input type="text" class="form-control" name="create_time" value="${entity.create_time }">
					</div>
				</div>
			</form>
		</div>
	</div>
</div>
</body>
<script type="text/javascript">
function check_fun(){
	return true;
}
</script>
</html>
