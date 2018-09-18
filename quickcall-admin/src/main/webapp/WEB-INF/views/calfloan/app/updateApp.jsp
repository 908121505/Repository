<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="modal-dialog">
	<div class="modal-content">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal"
				aria-hidden="true">X</button>
			<h3 id="myModalLabel">修改启动广告信息</h3>
		</div>
		<div class="modal-body">
			<form class="form-horizontal" method="post" id="sysUserForm" name="appForm"
				action="appinfo/saveInsert.htm"
				role="form">
				<div class="form-group">
					<label class="col-sm-2 control-label">名称<font color="red">&nbsp;*</font></label>
					<div class="col-sm-10">
						<input type="text" class="form-control" id="app_title" name="title"
							value="${entity.title }">
					    <input type="hidden"
							class="form-control" name="image" id="appinfoFile_input"
							value="${entity.image }">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">状态<font color="red">&nbsp;*</font></label>
					<div class="col-sm-10">
						<label class="checkbox-inline"> <input type="radio"
							name="state" value="1" ${entity.state=='1'?'checked':'' }>
							开启
						</label> <label class="checkbox-inline"> <input type="radio"
							name="state" value="0" ${entity.state=='0'?'checked':'' }>
							关闭
						</label>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">链接<font color="red">&nbsp;*</font></label>
					<div class="col-sm-10">
						<input type="text" class="form-control" name="url" id="app_url"
							value="${entity.url }">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">图片上传</label>
					<div class="col-sm-10">
						<div class="input-group">
							<input type="file" class="form-control" id="appinfoFile"
								name="appinfoFile"> <span class="input-group-btn">
								<button class="btn btn-success" type="button" id="uploadappinfo">上传图片</button>
							</span>
						</div>
					</div>
				</div>
				<div class="form-group">
					<label for="inputEmail3" class="col-sm-2 control-label">图片</label>
					<div class="col-sm-10">
						<div class="col-sm-6">
							<img src="${entity.image }" alt="暂无图片，点击上传！" class="img-rounded"
								style="max-width: 100%; max-height: 300px;" id="appinfoFile_img">
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
		var url = $("#app_url").val();
		var title = $("#app_title").val();
		var state = $('input[name="state"]:checked').val();
		if(url == null || url.trim() == ''){
			$("#tip").html("请输入链接");
			b = false;
		}
		if(state == undefined){
			$("#tip").html("请选择状态");
			b = false;
		}
		if(title == null || title.trim() == ''){
			$("#tip").html("请输入标题");
			b = false;
		}
		return b;
	}
	$(function() {
		$('#uploadappinfo').click(function() {
			var file = document.appForm.appinfoFile.value;
			if(file == ""){
				$("#tip").html("请选择图片");
				return false;
			}
			$.ajaxFileUpload({
				type : "post",
				dataType : "json",
				fileElementId : 'appinfoFile',
				url : "upload/appinfo.htm",
				data : {},
				error : function(data) {
					alert("错误！");
				},
				success : function(data) {
					if (data.result = 'success') {
						console.info(data);
						alert("上传图片成功！");
						$('#appinfoFile_input').val(data.imgUrl);
						$('#appinfoFile_img').attr("src", data.imgUrl);
						$("#tip").html("");
					} else {
						alert(data.msg);
					}
				}
			});
		});
	});
</script>