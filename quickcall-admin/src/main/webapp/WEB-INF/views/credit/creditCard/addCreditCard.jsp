<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="modal-dialog">
	<div class="modal-content">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal"
				aria-hidden="true">X</button>
			<h3 id="myModalLabel">${empty entity?'新增':'修改' }信用卡信息</h3>
		</div>
		<div class="modal-body">
			<form class="form-horizontal" method="post" id="sysUserForm"
				name="creditCardForm"
				action="creditCard/save${empty entity?'Insert':'Update' }.htm"
				role="form">
				<div class="form-group">
					<label class="col-sm-2 control-label">信用卡名称<font
						color="red">&nbsp;*</font></label>
					<div class="col-sm-10">
						<input type="hidden" value="${entity.creditCardId }"
							name="creditCardId" /> <input type="text" class="form-control"
							id="creditCard_name" name="name" value="${entity.name }">
						<input type="hidden" class="form-control" name="image"
							id="creditCardFile_input" value="${entity.image }"> <input
							type="hidden" class="form-control" name="imageBack"
							id="creditCardFileBack_input" value="${entity.imageBack }">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">跳转url<font
						color="red">&nbsp;*</font></label>
					<div class="col-sm-10">
						<input type="text" class="form-control" name="url"
							id="creditCard_url" value="${entity.url }">
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
					<label class="col-sm-2 control-label">是否推荐<font color="red">&nbsp;*</font></label>
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
					<label class="col-sm-2 control-label">序号<font color="red">&nbsp;*</font></label>
					<div class="col-sm-10">
						<input type="number" step="1" class="form-control"
							name="serialNum" id="creditCard_serialNum"
							value="${entity.serialNum }">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">备注</label>
					<div class="col-sm-10">
						<input type="text" class="form-control" name="remark"
							id="creditCard_remark" value="${entity.remark }">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">图片上传<font color="red">&nbsp;*</font></label>
					<div class="col-sm-10">
						<div class="input-group">
							<input type="file" class="form-control" id="creditCardFile"
								name="creditCardFile"> <span class="input-group-btn">
								<button class="btn btn-success" type="button"
									id="uploadCreditCard">上传图片</button>
							</span>
						</div>
					</div>
				</div>
				<div class="form-group">
					<label for="inputEmail3" class="col-sm-2 control-label">信用卡图片</label>
					<div class="col-sm-10">
						<div class="col-sm-6">
							<img src="${entity.image }" alt="暂无图片，点击上传！" class="img-rounded"
								style="max-width: 100%; max-height: 300px;"
								id="creditCardFile_img">
						</div>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">图片上传(背景图片)<font
						color="red">&nbsp;*</font></label>
					<div class="col-sm-10">
						<div class="input-group">
							<input type="file" class="form-control" id="creditCardFileBack"
								name="creditCardFileBack"> <span class="input-group-btn">
								<button class="btn btn-success" type="button"
									id="uploadCreditCardBack">上传图片</button>
							</span>
						</div>
					</div>
				</div>
				<div class="form-group">
					<label for="inputEmail3" class="col-sm-2 control-label">信用卡图片(背景图片)</label>
					<div class="col-sm-10">
						<div class="col-sm-6">
							<img src="${entity.imageBack }" alt="暂无图片，点击上传！"
								class="img-rounded" style="max-width: 100%; max-height: 300px;"
								id="creditCardFileBack_img">
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
		var serialNum = $("#creditCard_serialNum").val();
		var isRecommend = $('input[name="isRecommend"]:checked').val();
		var state = $('input[name="state"]:checked').val();
		var url = $("#creditCard_url").val();
		var name = $("#creditCard_name").val();
		if ('${entity}' == '') {
			var file = document.creditCardForm.creditCardFile.value;
			var fileBack = document.creditCardForm.creditCardFileBack.value;
			var filepath = $("#creditCardFile_input").val();
			var filepathBack = $("#creditCardFileBack_input").val();
			if (file == "" || fileBack == "" || fileBack == ""
					|| filepath == null || filepath == ''
					|| filepathBack == null || filepathBack == '') {
				$("#tip").html("请选择图片");
				b = false;
			}

		}
		if (serialNum == null || serialNum.trim() == '') {
			$("#tip").html("请输入序号");
			b = false;
		}
		if (isRecommend == undefined) {
			$("#tip").html("请选择是否推荐");
			b = false;
		}
		if (state == undefined) {
			$("#tip").html("请选择状态");
			b = false;
		}
		if (url == null || url.trim() == '') {
			$("#tip").html("请输入跳转url");
			b = false;
		}
		if (name == null || name.trim() == '') {
			$("#tip").html("请输入名称");
			b = false;
		}
		return b;
	}

	$(function() {
		$('#uploadCreditCard').click(function() {
			var file = document.creditCardForm.creditCardFile.value;
			if (file == "") {
				$("#tip").html("请选择图片");
				return false;
			}
			$.ajaxFileUpload({
				type : "post",
				dataType : "json",
				fileElementId : 'creditCardFile',
				url : "upload/creditCard.htm",
				data : {},
				error : function(data) {
					alert("错误！");
				},
				success : function(data) {
					if (data.result = 'success') {
						console.info(data);
						alert("上传图片成功！");
						$('#creditCardFile_input').val(data.imgUrl);
						$('#creditCardFile_img').attr("src", data.imgUrl);
						$("#tip").html("");
					} else {
						alert(data.msg);
					}
				}
			});
		});
	});

	$(function() {
		$('#uploadCreditCardBack').click(function() {
			var fileBack = document.creditCardForm.creditCardFileBack.value;
			if (fileBack == "") {
				$("#tip").html("请选择图片");
				return false;
			}
			$.ajaxFileUpload({
				type : "post",
				dataType : "json",
				fileElementId : 'creditCardFileBack',
				url : "upload/creditBackCard.htm",
				data : {},
				error : function(data) {
					alert("错误！");
				},
				success : function(data) {
					if (data.result = 'success') {
						console.info(data);
						alert("上传图片成功！");
						$('#creditCardFileBack_input').val(data.imgUrl);
						$('#creditCardFileBack_img').attr("src", data.imgUrl);
						$("#tip").html("");
					} else {
						alert(data.msg);
					}
				}
			});
		});
	});
</script>