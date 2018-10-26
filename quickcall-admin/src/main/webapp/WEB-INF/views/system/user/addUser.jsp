<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="modal-dialog">
	<div class="modal-content">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal"
				aria-hidden="true">X</button>
			<h3 id="myModalLabel">${empty entity?'新增':'修改' }用户信息</h3>
		</div>
		<div class="modal-body">
			<form class="form-horizontal" method="post" id="sysUserForm" action="system/user/save${empty entity?'Insert':'Update' }.htm" role="form">
				<div class="form-group">
					<label class="col-sm-2 control-label">登录账号</label>
					<div class="col-sm-10">
						<input type="hidden" value="${entity.id }" name="id"/>
						<input type="text" class="form-control" id="inputEmail3" name="account" value="${entity.account }">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">用户昵称</label>
					<div class="col-sm-10">
						<input type="text" class="form-control" name="nick_name" value="${entity.nick_name }">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">真实姓名</label>
					<div class="col-sm-10">
						<input type="text" class="form-control" name="user_name" value="${entity.user_name }">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">用户性别</label>
					<div class="col-sm-10">
						<label class="checkbox-inline">
						  <input type="radio" name="sex" value="1" ${entity.sex=='1'?'checked':'' }> 男
						</label>
						<label class="checkbox-inline">
						  <input type="radio" name="sex" value="0" ${entity.sex=='0'?'checked':'' }> 女
						</label>
					</div>
				</div>
				
				<div class="form-group">
					<label class="col-sm-2 control-label">登录密码</label>
					<div class="col-sm-10">
						<input type="password" class="form-control" name="password">
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
<script type="text/javascript">
function check_fun(){
	return true;
}
</script>