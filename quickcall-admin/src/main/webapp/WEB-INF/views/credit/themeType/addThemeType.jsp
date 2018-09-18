<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="modal-dialog">
	<div class="modal-content">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal"
				aria-hidden="true">X</button>
			<h3 id="myModalLabel">${empty entity?'新增':'修改' }信用卡主题信息</h3>
		</div>
		<div class="modal-body">
			<form class="form-horizontal" method="post" id="sysUserForm"
				name="themeTypeForm"
				action="themeType/save${empty entity?'Insert':'Update' }.htm"
				role="form">
				<div class="form-group">
					<label class="col-sm-2 control-label">标题<font color="red">&nbsp;*</font></label>
					<div class="col-sm-10">
						<input type="hidden" value="${entity.themeBankId }"
							name="themeBankId" /> <input type="text" class="form-control"
							id="themeType_title" name="title" value="${entity.title }">
						<input type="hidden" class="form-control" name="image"
							id="themeTypeFile_input" value="${entity.image }">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">跳转url<font
						color="red">&nbsp;*</font></label>
					<div class="col-sm-10">
						<input type="text" class="form-control" name="url"
							id="themeType_url" value="${entity.url }">
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
						<input type="number" step="1" class="form-control"
							name="serialNum" id="themeType_serialNum"
							value="${entity.serialNum }">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">主题banner<font
						color="red">&nbsp;*</font></label>
					<div class="col-sm-10">
						<input type="text" class="form-control" name="banner"
							id="themeType_banner" value="${entity.banner }">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">推荐<font color="red">&nbsp;*</font></label>
					<div class="col-sm-10">
						<label class="checkbox-inline"> <input type="radio"
							name="isRecommend" value="1"
							${entity.isRecommend=='1'?'checked':'' }> 推荐
						</label> <label class="checkbox-inline"> <input type="radio"
							name="isRecommend" value="0"
							${entity.isRecommend=='0'?'checked':'' }> 不推荐
						</label>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">标注</label>
					<div class="col-sm-10">
						<input type="text" class="form-control" name="remark"
							id="themeType_remark" value="${entity.remark }">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">图片上传<font color="red">&nbsp;*</font></label>
					<div class="col-sm-10">
						<div class="input-group">
							<input type="file" class="form-control" id="themeTypeFile"
								name="themeTypeFile"> <span class="input-group-btn">
								<button class="btn btn-success" type="button"
									id="uploadThemeType">上传图片</button>
							</span>
						</div>
					</div>
				</div>
				<div class="form-group">
					<label for="inputEmail3" class="col-sm-2 control-label">图片</label>
					<div class="col-sm-10">
						<div class="col-sm-6">
							<img src="${entity.image }" alt="暂无图片，点击上传！" class="img-rounded"
								style="max-width: 100%; max-height: 300px;"
								id="themeTypeFile_img">
						</div>
					</div>
				</div>
				<span id="tip"
					style="color: red; font-size: 14px; margin-left: 20px;"></span>
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
	function check_fun() {
		$("#tip").html("");
		var b = true;
		var isRecommend = $('input[name="isRecommend"]:checked').val();
		var banner = $("#themeType_banner").val();
		var serialNum = $("#themeType_serialNum").val();
		var state = $('input[name="state"]:checked').val();
		var url = $("#themeType_url").val();
		var title = $("#themeType_title").val();
		if ('${entity}' == '') {
			console.info(111);
			var file = document.themeTypeForm.themeTypeFile.value;
			var filepath = $("#themeTypeFile_input").val();
			if (file == "" || filepath == null || filepath == '') {
				$("#tip").html("请选择图片");
				b = false;
			}
		}
		if (isRecommend == undefined) {
			$("#tip").html("请选择推荐");
			b = false;
		}
		if (banner == null || banner.trim() == '') {
			$("#tip").html("请输入banner");
			b = false;
		}
		if (serialNum == null || serialNum.trim() == '') {
			$("#tip").html("请输入序号");
			b = false;
		}
		if (state == undefined) {
			$("#tip").html("请选择状态");
			b = false;
		}
		if (url == null || url.trim() == '') {
			$("#tip").html("请输入链接");
			b = false;
		}
		if (title == null || title.trim() == '') {
			$("#tip").html("请输入名称");
			b = false;
		}
		return b;
	}

	$(function() {
		$('#uploadThemeType').click(function() {
			var file = document.themeTypeForm.themeTypeFile.value;
			if (file == "") {
				$("#tip").html("请选择图片");
				return false;
			}
			$.ajaxFileUpload({
				type : "post",
				dataType : "json",
				fileElementId : 'themeTypeFile',
				url : "upload/themeType.htm",
				data : {},
				error : function(data) {
					alert("错误！");
				},
				success : function(data) {
					if (data.result = 'success') {
						console.info(data);
						alert("上传图片成功！");
						$('#themeTypeFile_input').val(data.imgUrl);
						$('#themeTypeFile_img').attr("src", data.imgUrl);
						$("#tip").html("");
					} else {
						alert(data.msg);
					}
				}
			});
		});
	});
</script>