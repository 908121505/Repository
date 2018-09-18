<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="modal-dialog">
	<div class="modal-content">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal"
				aria-hidden="true"></button>
			<h3 id="myModalLabel">请输入回复内容<font color="red">&nbsp;*</font></h3>
		</div>
		<div class="modal-body">
			<form class="form-horizontal" method="post" id="sysUserForm" name="bannerForm"
				action="articleReply/save${empty entity?'Insert':'Update' }.htm"
				role="form">
				
				<div class="form-group">
						<input type="hidden" value="${parentId}" name="parentId" /> 
					<label class="col-sm-2 control-label"></label>
					<div class="col-sm-10">
						<textarea cols="50" rows="15" name="content" id="contents">${entity.content }</textarea>
					
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
     var contents = $("#contents").val();
     if(contents.length<1){
     	$("#tip").html("请输入回复内容");
     	return false;
     }
	return true;
}
	
</script>