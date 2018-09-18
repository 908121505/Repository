<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="modal-dialog">
	<div class="modal-content">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal"
				aria-hidden="true">X</button>
			<h3 id="myModalLabel">新增敏感词信息</h3>
		</div>
		<div class="modal-body">
			<form class="form-horizontal" method="post" id="sysUserForm" name="bannerForm"
				action="sensitivity/saveInsert.htm"
				role="form">
				<div class="form-group">
					<label class="col-sm-2 control-label">敏感词<font color="red">&nbsp;*</font></label>
					<div class="col-sm-10">
						<input type="text" class="form-control required" name="sensitivityContent"/>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">敏感词类型<font color="red">&nbsp;*</font></label>
					<div class="col-sm-10">
						<select class="form-control" id="add_type" name = "type">
							<option value="1">社区敏感词</option>
							<option value="2">昵称敏感词</option>
						</select>
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
<script type="text/javascript"
	src="resources/js/My97DatePicker/WdatePicker.js" charset="utf-8"></script>
<script type="text/javascript">
function check_fun(){  
	return true;
}
</script>