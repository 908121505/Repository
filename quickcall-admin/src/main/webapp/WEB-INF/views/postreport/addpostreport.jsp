<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="modal-dialog">
	<div class="modal-content">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal"
				aria-hidden="true">X</button>
			<h3 id="myModalLabel">${empty entity?'新增':'修改' }帖子投诉信息</h3>
		</div>
		<div class="modal-body">
			<form class="form-horizontal" method="post" id="sysUserForm" name="bannerForm"
				action="postreport/save${empty entity?'Insert':'Update' }.htm"
				role="form">
					<div class="form-group">
					<label class="col-sm-2 control-label">投诉原因<font color="red">&nbsp;*</font></label>
					<div class="col-sm-10">
						<textarea name = "complaintReason" id="complaint_reason" class="form-control" rows="6" style="overflow:auto">${entity.complaintReason}</textarea> 
						<input type="hidden" value="${entity.postReportId }" name="postReportId" /> 
					</div>
				</div>
			
				<div class="form-group">
					<label class="col-sm-2 control-label">状态<font color="red">&nbsp;*</font></label>
					<div class="col-sm-10">
						<label class="checkbox-inline"> <input type="radio" 
							name="state" value="1" ${entity.state=='1'?'checked':'' }>
							未处理
						</label> <label class="checkbox-inline"> <input type="radio"
							name="state" value="2" ${entity.state=='2'?'checked':'' }>
							已处理
						</label>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">帖子投诉次数<font color="red">&nbsp;*</font></label>
					<div class="col-sm-10">
					<input type="text" class="form-control required" id="complaint_num" name="complaintNum"
							value="${entity.complaintNum }">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">处理结果</label>
					<div class="col-sm-10">
					<textarea name = "handelResult" id="handel_result" class="form-control" rows="6" style="overflow:auto">${entity.handelResult}</textarea>
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
	$("#tip").html("");
	var b = true;
	
	var handelResult = $("#handel_result").val();
	var state = $('input[name="state"]:checked').val();
	var complaintReason = $("#complaint_reason").val();
	var complaintNum = $("#complaint_num").val();
	
	
	
	if(state == undefined){
		$("#tip").html("请选择正确的状态");
		b = false;
	}
	
	if(handelResult == null || handelResult.trim() == ''){
		$("#tip").html("请输入正确的处理结果");
		b = false;
	}
	
	if(complaintReason == null || complaintReason.trim() == ''){
		$("#tip").html("请输入正确的投诉原因");
		b = false;
	}

	if(complaintNum==null || complaintNum.trim() == ''){
		$("#tip").html("请输入正确的帖子投诉次数");
		b = false;
	}
	
	//格式校验
	
	/*if(title&&title.length>150){
		$("#tip").html("标题不能超过150个字");
		b = false;
	}**/
	
	
	return b;
}
</script>