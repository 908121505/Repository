<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="modal-dialog">
	<div class="modal-content">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal"
				aria-hidden="true">X</button>
			<h3 id="myModalLabel">${empty entity?'新增':'修改' }自动评论</h3>
		</div>
		<div class="modal-body">
			<form class="form-horizontal" method="post" id="sysUserForm" name="bannerForm";
				action="autoComment/save${empty entity?'Insert':'Update' }.htm"
				role="form">
				<div class="form-group">
					<label class="col-sm-2 control-label">自动评论内容<font color="red">&nbsp;*</font></label>
					<div class="col-sm-10">
						<input type="hidden" value="${entity.id }" name="id" /> 
						<input type="text" maxlength="100" class="form-control required" id="con" name="content"
							value="${entity.content }">
					    
					</div>
				</div>
				
				<div class="form-group">
					<label class="col-sm-2 control-label">评论类型<font color="red">&nbsp;*</font></label>
					<div class="col-sm-10">
						<label class="checkbox-inline"> <input type="radio"
							name="commonType" value="1" ${entity.commonType=='1' or (empty entity)?'checked':'' }>
							帖子评论
						</label> <label class="checkbox-inline"> <input type="radio"
							name="commonType" value="2" ${entity.commonType=='2'?'checked':'' }>
							文章评论
						</label>
					</div>
				</div>
				
				<div class="form-group">
					<label class="col-sm-2 control-label">状态<font color="red">&nbsp;*</font></label>
					<div class="col-sm-10">
						<label class="checkbox-inline"> <input type="radio"
							name="state" value="1" ${entity.state=='1' or (empty entity)?'checked':'' }>
							开启
						</label> <label class="checkbox-inline"> <input type="radio"
							name="state" value="0" ${entity.state=='0'?'checked':'' }>
							关闭
						</label>
					</div>
				</div>
				
				<div class="form-group">
					<label class="col-sm-2 control-label">序号<font color="red">&nbsp;*</font></label>
					<div class="col-sm-10">
						<input type="number" step="1" class="form-control" id="sort" name="sort"
							value="${entity.sort }">
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
	
	if(tp("con")){
		$("#tip").text("评论内容不能为空");
		return false;
	}
	
	if(tp("sort")){
		$("#tip").text("序号不能为空");
		return false;
	}
	
	return true;
	
}

function tp(id){
    return $("#"+id).val().length<1;
}

</script>