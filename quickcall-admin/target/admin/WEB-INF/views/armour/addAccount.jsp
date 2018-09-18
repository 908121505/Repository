<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="modal-dialog">
	<div class="modal-content">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal"
				aria-hidden="true">X</button>
			<h3 id="myModalLabel">${empty entity?'新增':'修改' }账户信息</h3>
		</div>
		<div class="modal-body">
			<form class="form-horizontal" method="post" id="sysUserForm" name="bannerForm"
				action="armourAccount/save${empty entity?'Insert':'Update' }.htm"
				role="form">
				<div class="form-group">
					<label class="col-sm-2 control-label">电话号码<font color="red">&nbsp;*</font></label>
					<div class="col-sm-10">
						<input type="hidden" value="${entity.accountId }" name="accountId" /> 
						<input type="text"  class="form-control required" ${empty entity?'新增':'disabled="disabled"' }  id="phoneNums" name="phoneNum"
							value="${entity.phoneNum }">
					    <input type="text"
							class="form-control" name="userAvatar" id="bannerFile_input"
							value="${entity.userAvatar }">
					</div>
				</div>
				
				<div class="form-group">
					<label class="col-sm-2 control-label">状态<font color="red">&nbsp;*</font></label>
					<div class="col-sm-10">
						<label class="checkbox-inline"> <input type="radio"
							name="state" value="1" ${entity.state=='1'?'checked':'' }>
							开启
						</label> <label class="checkbox-inline"> <input type="radio"
							name="state" value="2" ${entity.state=='2'?'checked':'' }>
							关闭
						</label>
					</div>
				</div>
				
				<div class="form-group">
					<label class="col-sm-2 control-label">图片上传<c:if test="${entity eq null }"><font color="red">&nbsp;*</font></c:if>
					</label>
					
					<div class="col-sm-10">
						<div class="input-group">
							<input type="file" class="form-control" id="appversionFile"
								name="appversionFile"> <span class="input-group-btn">
								<button class="btn btn-success" type="button" id="uploadBanner">上传图片</button>
							</span>
						</div>
					</div>
			  </div>
			  <div class="form-group">
					<label for="inputEmail3" class="col-sm-2 control-label">图片</label>
					<div class="col-sm-10">
						<div class="col-sm-6">
							<img src="${entity.userAvatar }" alt="暂无图片，点击上传！" class="img-rounded"
								style="max-width: 100%; max-height: 300px;" id="bannerFile_img">
						</div>
					</div>
				</div>
				
				<div class="form-group">
					<label class="col-sm-2 control-label">昵称<font color="red">&nbsp;*</font></label>
					<div class="col-sm-10">
						
						<input type="text" class="form-control required" id="nicknames" name="nickname"
							value="${entity.nickname }">
					    
					</div>
				</div>
				
				<div class="form-group">
					<label  class="col-sm-2 control-label">备注</label>
					<div class="col-sm-10">
								<textarea  id="remark" name="remark"  cols="45" rows="5" style="overflow:auto">${entity.remark}</textarea>
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
	var state = $('input[name="state"]:checked').val();
	var phoneNum = $("#phoneNums").val();
	var nickname =$("#nicknames").val();
	var file = $('#appversionFile').val();
	
	if(file == ""){
		$("#tip").html("请选择图片");
		return false;
	}
	
	if(state == undefined){
		$("#tip").html("请选择状态");
		return false;
	}
	if(phoneNum.length<1){
		$("#tip").html("请填写手机号码");
		return false;
	}
	if(nickname.length<1){
		$("#tip").html("请填写昵称");
		return false;
	}
	
	
	return true;
}
	$(function() {
		$('#uploadBanner').click(function() {
			var file = document.bannerForm.appversionFile.value;
			if(file == ""){
				$("#tip").html("请选择图片");
				return false;
			}
			$.ajaxFileUpload({
				type : "post",
				dataType : "json",
				fileElementId : 'appversionFile',
				url : "upload/appversion.htm",
				data : {
					"id" : "${entity.accountId }"
				},
				error : function(data) {
					alert("错误！");
				},
				success : function(data) {
					if (data.result = 'success') {
						console.info(data);
						alert("上传图片成功！");
						$('#bannerFile_input').val(data.imgUrl);
						$('#bannerFile_img').attr("src", data.imgUrl);
						$("#tip").html("");
					} else {
						alert(data.msg);
					}
				}
			});
		});
	});
</script>