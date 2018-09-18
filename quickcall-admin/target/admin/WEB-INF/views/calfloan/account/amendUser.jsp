<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="modal-dialog">
	<div class="modal-content">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal"
				aria-hidden="true">X</button>
			<h3 id="myModalLabel">修改用户权限</h3>
		</div>
		<div class="modal-body">
			<form class="form-horizontal" method="post" id="sysUserForm" action="account/saveUpdate.htm" role="form">			
				<div class="form-group">
					<label class="col-sm-2 control-label">用户权限</label>
					<div class="col-sm-10">
						<label class="checkbox-inline">
							<input type="hidden" value="${entity.accountId }" name="accountId"/>
						  <input type="radio" name="state" value="1" ${entity.state=='1'?'checked':'' }> 正常
						</label>
						<label class="checkbox-inline">
						  <input type="radio" name="state" value="6" ${entity.state=='6'?'checked':'' }> 限制社区发帖
						</label>
						<label class="checkbox-inline">
						  <input type="radio" name="state" value="7" ${entity.state=='7'?'checked':'' }> 限制社区评论
						</label>
						<label class="checkbox-inline">
						  <input type="radio" name="state" value="8" ${entity.state=='8'?'checked':'' }> 限制社区发帖及评论
						</label>
						<label class="checkbox-inline">
						  <input type="radio" name="state" value="9" ${entity.state=='9'?'checked':'' }> 限制产品详情评论
						</label>
						<label class="checkbox-inline">
						  <input type="radio" name="state" value="10" ${entity.state=='10'?'checked':'' }> 限制社区发帖及评论及产品评论
						</label>
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