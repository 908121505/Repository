<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="modal-dialog" style="width: 50%;">
	<div class="modal-content">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal" aria-hidden="true">X</button>
			<h3 id="myModalLabel">${empty entity?'新增':'修改' }随机用户</h3>
		</div>
		<div class="modal-body">
			<form class="form-horizontal" method="post" id="fadeCustomerForm" name="fadeCustomerForm"
				action="fadeCustomer/save${empty entity?'Insert':'Update' }.htm"
				role="form">
				<div class="form-group">
					<label class="col-sm-2 control-label">用户昵称<font color="red">&nbsp;*</font></label>
					<div class="col-sm-10">
						<input type="hidden" value="${entity.id }" name="id" />
						<input type="text" class="form-control required" id="form_nickName" name="nickName" value="${entity.nickName }">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">状态<font color="red">&nbsp;*</font></label>
					<div class="col-sm-10">
						<label class="checkbox-inline">
							<input type="radio" name="status" value="1" ${empty entity or entity.status=='1'?'checked':'' }>
							启用
						</label>
						<label class="checkbox-inline">
							<input type="radio" name="status" value="2" ${entity.status=='0'?'checked':'' }>
							停用
						</label>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">备注</label>
					<div class="col-sm-10">
						<input type="text" class="form-control" name="remark" value="${entity.remark }">
					</div>
				</div>
				<span id="tip" style="color: red;font-size: 14px;margin-left:20px; "></span>
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
		$("#tip").html("");

		var form_nickName = $("#form_nickName").val();
		if(form_nickName == null || form_nickName.trim() == ''){
			$("#tip").html("请输入用户昵称");
			return false;
		}

		return true;
	}
	$(function() {
	});
</script>