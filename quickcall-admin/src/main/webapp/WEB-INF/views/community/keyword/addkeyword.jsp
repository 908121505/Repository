<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="modal-dialog">
	<div class="modal-content">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal"
				aria-hidden="true">X</button>
			<h3 id="myModalLabel">${empty entity?'新增':'修改' }app信息</h3>
		</div>
		<div class="modal-body">
			<form class="form-horizontal" method="post" id="sysUserForm"
				name="keywordForm"
				action="keyword/save${empty entity?'Insert':'Update' }.htm"
				role="form">
				<div class="form-group">
					<label class="col-sm-2 control-label">APP类型<font
						color="red">&nbsp;*</font></label>
					<div class="col-sm-10">
						<input type="hidden" value="${entity.keywordId }"
							name="articleId" /> <label class="checkbox-inline"> <input
							type="radio" name="appType" id="article_apptype" value="1"
							${entity.appType=='1'?'checked':'' }> ios
						</label> <label class="checkbox-inline"> <input type="radio"
							name="appType" value="0" ${entity.appType=='0'?'checked':'' }>
							android
						</label> <label class="checkbox-inline"> <input type="radio"
							name="appType" value="2" ${entity.appType=='0'?'checked':'' }>
							wp
						</label>
						<input
							type="hidden" class="form-control" name="downloadUrl"
							id="keywordFile_input" value="${entity.downloadUrl }">
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
					<label class="col-sm-2 control-label">是否弹窗<font color="red">&nbsp;*</font></label>
					<div class="col-sm-10">
						<label class="checkbox-inline"> <input type="radio"
							name="popup" value="1" ${entity.popup=='1'?'checked':'' }>
							弹窗
						</label> <label class="checkbox-inline"> <input type="radio"
							name="popup" value="0" ${entity.popup=='0'?'checked':'' }>
							不弹窗
						</label>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">版本号<font color="red">&nbsp;*</font></label>
					<div class="col-sm-10">
						<input type="text" class="form-control" name="versionNumber"
							id="keyword_versionNumber" value="${entity.popup }">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">更新属性<font color="red">&nbsp;*</font></label>
					<div class="col-sm-10">
						<label class="checkbox-inline"> <input type="radio"
							name="changeProperties" id="articlecomment_changeProperties"
							value="1" ${entity.changeProperties=='1'?'checked':'' }>
							强制更新
						</label> <label class="checkbox-inline"> <input type="radio"
							name="changeProperties" value="0"
							${entity.changeProperties=='0'?'checked':'' }> 提示更新
						</label>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">更新描述</label>
					<div class="col-sm-10">
						<input type="text" class="form-control" name="changeDesc"
							id="keyword_changeDesc" value="${entity.changeDesc }">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">更新日志</label>
					<div class="col-sm-10">
						<input type="text" class="form-control" name="changeLog"
							id="keyword_changeLog" value="${entity.changeLog }">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">开始时间<font color="red">&nbsp;*</font></label>
					<div class="col-sm-10">
						<input type="text" id="article_startTime" class="form-control"
							onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'1950-01-01',maxDate:'#F{$dp.$D(\'appversion_endTime\')}'})"
							value="${entity.beginTime }" name="beginTime" />
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">结束时间<font color="red">&nbsp;*</font></label>
					<div class="col-sm-10">
						<input type="text" id="article_endTime" class="form-control"
							onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'appversion_beginTime\')}',maxDate:'2050-12-31'})"
							value="${entity.endTime }" name="endTime" />
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">备注</label>
					<div class="col-sm-10">
						<input type="text" class="form-control" name="remark"
							id="article_remark" value="${entity.remark }">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">文件上传<c:if test="${entity eq null }"><font color="red">&nbsp;*</font></c:if></label>
					<div class="col-sm-10">
						<div class="input-group">
							<input type="file" class="form-control" id="articleFile"
								name="articleFile"> <span class="input-group-btn">
								<button class="btn btn-success" type="button" id="uploadarticle">上传文件</button>
							</span>
						</div>
					</div>
				</div>
				<div class="form-group">
					<label for="inputEmail3" class="col-sm-2 control-label">文件内容</label>
					<div class="col-sm-10">
						<div class="col-sm-6">
							<img src="${entity.downloadUrl }" alt="暂无图片，点击上传！"
								class="img-rounded" style="max-width: 100%; max-height: 300px;"
								id="keywordFile_img">
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
		var endTime = $("#appversion_endTime").val();
		var beginTime = $("#appversion_beginTime").val();
		var changeProperties = $('input[name="changeProperties"]:checked')
				.val();
		var state = $('input[name="state"]:checked').val();
		var popup = $('input[name="popup"]:checked').val();
		var versionNumber = $("#appversion_versionNumber").val();
		var downloadUrl = $("#appversionFile_input").val();
		var apptype = $('input[name="appType"]:checked').val();
		if('${entity}' == ''){
			 var file = document.appversionForm.appversionFile.value;
			var filepath = $("#appversionFile_input").val();
			 if(file == ""){
				$("#tip").html("请上传文件");
				b = false;
			} 
		}
		if (changeProperties == undefined) {
			$("#tip").html("请选择更新属性");
			b = false;
		}
		if (versionNumber == null || versionNumber.trim() == '') {
			$("#tip").html("请输入版本号");
			b = false;
		}
		if (popup == null || popup.trim() == '') {
			$("#tip").html("请选择是否弹窗");
			b = false;
		}
		if (state == undefined) {
			$("#tip").html("请选择状态");
			b = false;
		}
		if (apptype == undefined) {
			$("#tip").html("请选择app类型");
			b = false;
		}
		return b;
	}
	
	$(function(){
		$('#uploadkeyword').click(function() {
			 var file = document.keywordform.keywordFile.value;
			if(file == ""){
				$("#tip").html("请选择文件");
				return false;
			} 
			$.ajaxFileUpload({
				type : "post",
				dataType : "json",
				fileElementId : 'keywordFile',
				url : "upload/keyword.htm",
				data : {
				},
				error : function(data) {
					alert("错误！");
				},
				success : function(data) {
					if (data.result = 'success') {
						console.info(data);
						alert("上传图片成功！");
						$('#keywordFile_input').val(data.imgUrl);
						$('#keywordFile_img').attr("src", data.imgUrl);
						$("#tip").html("");
					} else {
						alert(data.msg);
					}
				}
			});
		});
	});
	</script>
