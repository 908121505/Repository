<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="modal-dialog">
	<div class="modal-content">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal"
				aria-hidden="true">X</button>
			<h3 id="myModalLabel">${empty entity?'新增':'修改' }贷款类型信息</h3>
		</div>
		<div class="modal-body">
			<form class="form-horizontal" method="post" id="sysUserForm" name="loancateForm"
				action="loancate/save${empty entity?'Insert':'Update' }.htm"
				role="form">
				<div class="form-group">
					<label class="col-sm-2 control-label">名称<font color="red">&nbsp;*</font></label>
					<div class="col-sm-10">
						<input type="hidden" value="${entity.loanCateId }" name="loanCateId" /> 
						<input type="text" class="form-control" id="loancate_loanTitle" name="loanTitle"
							value="${entity.loanTitle }">
					    <input type="hidden"
							class="form-control" name="imageUrl" id="loancateFile_input"
							value="${entity.imageUrl }">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">简介<font color="red">&nbsp;*</font></label>
					<div class="col-sm-10">
						<input type="text" class="form-control" id="loancate_introduction" name="introduction"
							value="${entity.introduction }">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">状态<font color="red">&nbsp;*</font></label>
					<div class="col-sm-10">
						<label class="checkbox-inline"> <input type="radio"
							name="state" value="1" ${entity.state=='1'?'checked':'' }>
							有效
						</label> <label class="checkbox-inline"> <input type="radio"
							name="state" value="0" ${entity.state=='0'?'checked':'' }>
							无效
						</label>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">序号<font color="red">&nbsp;*</font></label>
					<div class="col-sm-10">
						<input type="number" step="1" class="form-control" id="loancate_sort" name="sort"
							value="${entity.sort }">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">备注</label>
					<div class="col-sm-10">
						<input type="text" class="form-control" name="remark"
							value="${entity.remark }">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">图片上传<c:if test="${entity eq null }"><font color="red">&nbsp;*</font></c:if></label>
					<div class="col-sm-10">
						<div class="input-group">
							<input type="file" class="form-control" id="loancateFile"
								name="loancateFile"> <span class="input-group-btn">
								<button class="btn btn-success" type="button" id="uploadloancate">上传图片</button>
							</span>
						</div>
					</div>
				</div>
				<div class="form-group">
					<label for="inputEmail3" class="col-sm-2 control-label">图片</label>
					<div class="col-sm-10">
						<div class="col-sm-6">
							<img src="${entity.imageUrl }" alt="暂无图片，点击上传！" class="img-rounded"
								style="max-width: 100%; max-height: 300px;" id="loancateFile_img">
						</div>
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
	var sort = $("#loancate_sort").val();
	var state = $('input[name="state"]:checked').val();
	var introduction = $("#loancate_introduction").val();
	var title = $("#loancate_loanTitle").val();
	if('${entity}' == ''){
		var file = document.loancateForm.loancateFile.value;
		var filepath = $("#loancateFile_input").val();
		if(file == "" || filepath == null || filepath == ''){
			$("#tip").html("请上传图片");
			b = false;
		}
		
	}
	if(sort == null || sort.trim() == ''){
		$("#tip").html("请输入序号");
		b = false;
	}
	if(state == undefined){
		$("#tip").html("请选择状态");
		b = false;
	}
	if(introduction == null || introduction.trim() == ''){
		$("#tip").html("请输入简介");
		b = false;
	}
	if(title == null || title.trim() == ''){
		$("#tip").html("请输入标题");
		b = false;
	}
	return b;
}
	$(function() {
		$('#uploadloancate').click(function() {
			var file = document.loancateForm.loancateFile.value;
			if(file == ""){
				$("#tip").html("请选择图片");
				return false;
			}
			$.ajaxFileUpload({
				type : "post",
				dataType : "json",
				fileElementId : 'loancateFile',
				url : "upload/loancate.htm",
				data : {
					"id" : "${entity.loanCateId }"
				},
				error : function(data) {
					alert("错误！");
				},
				success : function(data) {
					if (data.result = 'success') {
						console.info(data);
						alert("上传图片成功！");
						$('#loancateFile_input').val(data.imgUrl);
						$('#loancateFile_img').attr("src", data.imgUrl);
					} else {
						alert(data.msg);
					}
				}
			});
		});
	});
</script>