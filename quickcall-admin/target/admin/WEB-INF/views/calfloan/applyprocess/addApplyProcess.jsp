<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="modal-dialog">
	<div class="modal-content">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal"
				aria-hidden="true">X</button>
			<h3 id="myModalLabel">${empty entity?'新增':'修改' }审核流程信息</h3>
		</div>
		<div class="modal-body">
			<form class="form-horizontal" method="post" id="sysUserForm" name="applyForm"
				action="applyprocess/save${empty entity?'Insert':'Update' }.htm"
				role="form">
				<div class="form-group">
					<label class="col-sm-2 control-label">名称<font color="red">&nbsp;*</font></label>
					<div class="col-sm-10">
						<input type="hidden" value="${entity.applyProcessId }" name="applyProcessId" /> 
						<input type="text" class="form-control" id="apply_processName" name="processName"
							value="${entity.processName }">
					    <input type="hidden"
							class="form-control" name="processImage" id="applyprocessFile_input"
							value="${entity.processImage }">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">app<font color="red">&nbsp;*</font></label>
					<div class="col-sm-10">
						<select class="form-control" id="apply_useId" name="useId">
<!-- 								<option value="0">--请选择--</option> -->
								<c:forEach var="info" items="${infos }">
									<option value="${info.applyProcess }">${info.appName }</option>
								</c:forEach>
							</select>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">序号<font color="red">&nbsp;*</font></label>
					<div class="col-sm-10">
						<input type="number" step="1" class="form-control" id="apply_sort" name="sort"
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
							<input type="file" class="form-control" id="applyprocessFile"
								name="applyprocessFile"> <span class="input-group-btn">
								<button class="btn btn-success" type="button" id="uploadapplyprocess">上传图片</button>
							</span>
						</div>
					</div>
				</div>
				<div class="form-group">
					<label for="inputEmail3" class="col-sm-2 control-label">图片</label>
					<div class="col-sm-10">
						<div class="col-sm-6">
							<img src="${entity.processImage }" alt="暂无图片，点击上传！" class="img-rounded"
								style="max-width: 100%; max-height: 300px;" id="applyprocessFile_img">
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
	
	$("#apply_useId").val('${entity.useId}');

	
	function check_fun(){
		$("#tip").html("");
		var b = true;
		var sort = $("#apply_sort").val();
		var useId = $("#apply_useId").val();
		var processName = $("#apply_processName").val();
		if('${entity}' == ''){
			var file = document.applyForm.applyprocessFile.value;
			var filepath = $("#applyprocessFile_input").val();
			if(file == "" || filepath == null || filepath.trim() == ''){
				$("#tip").html("请上传图片");
				b = false;
			}
		}
		if(sort == null || sort.trim() == ''){
			$("#tip").html("请输入序号");
			b = false;
		}
		if(useId == null || useId.trim() == ''){
			$("#tip").html("请选择app");
			b = false;
		}
		if(processName == null || processName.trim() == ''){
			$("#tip").html("请输入名称");
			b = false;
		}
		return b;
	}
	
	$(function() {
		$('#uploadapplyprocess').click(function() {
			var file = document.applyForm.applyprocessFile.value;
			if(file == ""){
				$("#tip").html("请选择图片");
				return false;
			}
			$.ajaxFileUpload({
				type : "post",
				dataType : "json",
				fileElementId : 'applyprocessFile',
				url : "upload/applyprocess.htm",
				data : {
					"id" : "${entity.applyProcessId }"
				},
				error : function(data) {
					alert("错误！");
				},
				success : function(data) {
					if (data.result = 'success') {
						console.info(data);
						alert("上传图片成功！");
						$('#applyprocessFile_input').val(data.imgUrl);
						$('#applyprocessFile_img').attr("src", data.imgUrl);
						$("#tip").html("");
					} else {
						alert(data.msg);
					}
				}
			});
		});
	});
</script>