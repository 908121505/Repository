<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="modal-dialog">
	<div class="modal-content">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal"
				aria-hidden="true">X</button>
			<h3 id="myModalLabel">${empty entity?'新增':'修改' }银行信息</h3>
		</div>
		<div class="modal-body">
			<form class="form-horizontal" method="post" id="sysUserForm"  name="bankForm"
				action="bank/save${empty entity?'Insert':'Update' }.htm" role="form">
				<div class="form-group">
					<label class="col-sm-2 control-label">银行名称<font color="red">&nbsp;*</font></label>
					<div class="col-sm-10">
						<input type="hidden" value="${entity.bankId }" name="bankId" />
						 <input	type="text" class="form-control" id="bank_bankName" name="bankName" value="${entity.bankName }"> 
							<input
							type="hidden" class="form-control" name="bankAvatar"
							id="bankFile_input" value="${entity.bankAvatar }">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">银行链接<font color="red">&nbsp;*</font></label>
					<div class="col-sm-10">
						<input type="text" class="form-control" name="bankLink"
							id="bank_bankLink" value="${entity.bankLink }">
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
					<label class="col-sm-2 control-label">是否推荐<font color="red">&nbsp;*</font></label>
					<div class="col-sm-10">
						<label class="checkbox-inline"> <input type="radio"
							name="isRecommend" value="1" ${entity.isRecommend=='1'?'checked':'' }>
							推荐
						</label> <label class="checkbox-inline"> <input type="radio"
							name="isRecommend" value="0" ${entity.isRecommend=='0'?'checked':'' }>
							不推荐
						</label>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">序号<font color="red">&nbsp;*</font></label>
					<div class="col-sm-10">
						<input type="number" step="1" class="form-control" name="sort" id="bank_sort"
							value="${entity.sort }">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">备注</label>
					<div class="col-sm-10">
						<input type="text" class="form-control" name="remark"
							id="bank_remark" value="${entity.remark }">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">图片上传<c:if test="${entity eq null }"><font color="red">&nbsp;*</font></c:if></label>
					<div class="col-sm-10">
						<div class="input-group">
							<input type="file" class="form-control" id="bankFile"
								name="bankFile"> <span class="input-group-btn">
								<button class="btn btn-success" type="button" id="uploadbank">上传图片</button>
							</span>
						</div>
					</div>
				</div>
				<div class="form-group">
					<label for="inputEmail3" class="col-sm-2 control-label">银行头像</label>
					<div class="col-sm-10">
						<div class="col-sm-6">
							<img src="${entity.bankAvatar }" alt="暂无图片，点击上传！"
								class="img-rounded" style="max-width: 100%; max-height: 300px;"
								id="bankFile_img">
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
		var state = $('input[name="state"]:checked').val();
		var isRecommend = $('input[name="isRecommend"]:checked').val();
		var sort = $("#bank_sort").val();
		var bankLink = $("#bank_bankLink").val();
		var bankName = $("#bank_bankName").val();
		if('${entity}' == ''){
			var file = document.bankForm.bankFile.value;
			var filepath = $("#bankFile_input").val();
			 if(file == "" || filepath == null || filepath == ''){
				$("#tip").html("请上传图片");
				b = false;
			} 
		}
		if (sort == null || sort.trim() == '') {
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
		if (bankLink == null || bankLink.trim() == '') {
			$("#tip").html("请输入银行链接");
			b = false;
		}
		if (bankName == null || bankName.trim() == '') {
			$("#tip").html("请输入银行名称");
			b = false;
		}
		return b;
	}

	$(function(){
		$('#uploadbank').click(function() {
			 var file = document.bankForm.bankFile.value;
			if(file == ""){
				$("#tip").html("请选择图片");
				return false;
			} 
			$.ajaxFileUpload({
				type : "post",
				dataType : "json",
				fileElementId : 'bankFile',
				url : "upload/bank.htm",
				data : {
				},
				error : function(data) {
					alert("错误！");
				},
				success : function(data) {
					if (data.result = 'success') {
						console.info(data);
						alert("上传图片成功！");
						$('#bankFile_input').val(data.imgUrl);
						$('#bankFile_img').attr("src", data.imgUrl);
						$("#tip").html("");
					} else {
						alert(data.msg);
					}
				}
			});
		});
	});
</script>