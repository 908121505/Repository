<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="modal-dialog">
	<div class="modal-content">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal"
				aria-hidden="true">X</button>
			<h3 id="myModalLabel">${empty entity?'新增':'修改' }攻略类型信息</h3>
		</div>
		<div class="modal-body">
			<form class="form-horizontal" method="post" id="sysUserForm"  name="raidersCateForm"
				action="raiderscate/save${empty entity?'Insert':'Update' }.htm"
				role="form">
				<div class="form-group">
					<label class="col-sm-2 control-label">标题<font color="red">&nbsp;*</font></label>
					<div class="col-sm-10">
						<input type="hidden" value="${entity.raidersCateId }"
							name="raidersCateId" /> <input type="text"
							class="form-control required" id="raidersCate_raidersTitle"
							name="raidersTitle" value="${entity.raidersTitle }"> <input
							type="hidden" class="form-control" name="imgUrl"
							id="raiderscateFile_input" value="${entity.imgUrl }">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">简介<font color="red">&nbsp;*</font></label>
					<div class="col-sm-10">
<%-- 						<textarea id="editor" name="introduction">${entity.introduction }</textarea> --%>
												<input type="text" class="form-control"
													name="introduction"  id="raidersCate_introduction"  value="${entity.introduction }">
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
					<label class="col-sm-2 control-label">序号<font color="red">&nbsp;*</font></label>
					<div class="col-sm-10">
						<input type="number" step="1"  class="form-control" name="sort" id="raidersCate_sort"
							value="${entity.sort }">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">备注</label>
					<div class="col-sm-10">
						<input type="text" class="form-control" name="remark" id="raidersCate_remark"
							value="${entity.remark }">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">图片上传<font color="red">&nbsp;*</font></label>
					<div class="col-sm-10">
						<div class="input-group">
							<input type="file" class="form-control" id="raiderscateFile"
								name="raiderscateFile"> <span class="input-group-btn">
								<button class="btn btn-success" type="button"
									id="uploadraiderscate">上传图片</button>
							</span>
						</div>
					</div>
				</div>
				<div class="form-group">
					<label for="inputEmail3" class="col-sm-2 control-label">图片</label>
					<div class="col-sm-10">
						<div class="col-sm-6">
							<img src="${entity.imgUrl }" alt="暂无图片，点击上传！" class="img-rounded"
								style="max-width: 100%; max-height: 300px;"
								id="raiderscateFile_img">
						</div>
					</div>
				</div>
				<span id="tip" style="color: red; font-size: 14px; margin-left: 20px;"></span>
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
<script src="resources/ueditor/ueditor.config.js" type="text/javascript"></script>
<script src="resources/ueditor/ueditor.all.min.js" type="text/javascript"></script>

<script type="text/javascript">
// 	var ue = UE.getEditor("editor");
	function check_fun() {
		$("#tip").html("");
		var b = true;
		var state = $('input[name="state"]:checked').val();
		var sort = $("#raidersCate_sort").val();
		var introduction = $("#raidersCate_introduction").val();
		var raidersTitle = $("#raidersCate_raidersTitle").val();
		if ('${entity}' == '') {
			console.info(111);
			var file = document.raidersCateForm.raiderscateFile.value;
			var filepath = $("#raiderscateFile_input").val();
			if(file == ""){
				$("#tip").html("请选择图片");
				return false;
			}
		}
		if (sort == null || sort.trim() == '') {
			$("#tip").html("请输入序号");
			b = false;
		}
		if (state == undefined) {
			$("#tip").html("请选择状态");
			b = false;
		}
		if (introduction == null || introduction.trim() == '') {
			$("#tip").html("请输入银行链接");
			b = false;
		}
		if (raidersTitle == null || raidersTitle.trim() == '') {
			$("#tip").html("请输入标题");
			b = false;
		}
		return b;
	}
	$(function() {
		$('#uploadraiderscate').click(function() {
			var file = document.raidersCateForm.raiderscateFile.value;
			if(file == ""){
				$("#tip").html("请选择图片");
				return false;
			}
			$.ajaxFileUpload({
				type : "post",
				dataType : "json",
				fileElementId : 'raiderscateFile',
				url : "upload/raiderscate.htm",
				data : {
					"id" : "${entity.raidersCateId }"
				},
				error : function(data) {
					alert("错误！");
				},
				success : function(data) {
					if (data.result = 'success') {
						console.info(data);
						alert("上传图片成功！");
						$('#raiderscateFile_input').val(data.imgUrl);
						$('#raiderscateFile_img').attr("src", data.imgUrl);
					} else {
						alert(data.msg);
					}
				}
			});
		});
	});
</script>