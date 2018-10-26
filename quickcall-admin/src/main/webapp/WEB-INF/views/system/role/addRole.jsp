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
			<h3 id="myModalLabel">${empty entity?'新增':'修改' }角色信息</h3>
		</div>
		<div class="modal-body">
			<form class="form-horizontal" method="post" id="sysUserForm" action="system/role/save${empty entity?'Insert':'Update' }.htm" role="form">
				<div class="form-group">
					<label class="col-sm-2 control-label">角色编号</label>
					<div class="col-sm-10">
						<input type="hidden" value="${entity.id }" name="id"/>
						<input type="text" class="form-control" name="codes" value="${entity.codes }">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">角色名称</label>
					<div class="col-sm-10">
						<input type="text" class="form-control" name="names" value="${entity.names }">
					</div>
				</div>
			</form>
		</div>
		<div class="modal-footer">
			<button class="btn btn-default" data-dismiss="modal"
				aria-hidden="true">取消</button>
			<button class="btn btn-primary" data-dismiss="modal">保存</button>
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