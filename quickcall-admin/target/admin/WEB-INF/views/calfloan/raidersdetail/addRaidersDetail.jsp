<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="modal-dialog">
	<div class="modal-content">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal"
				aria-hidden="true">X</button>
			<h3 id="myModalLabel">${empty entity?'新增':'修改' }攻略信息</h3>
		</div>
		<div class="modal-body">
			<form class="form-horizontal" method="post" id="sysUserForm"
				name="raidersDetailForm"
				action="raidersdetail/save${empty entity?'Insert':'Update' }.htm"
				role="form">
				<div class="form-group">
					<label class="col-sm-2 control-label">标题<font color="red">&nbsp;*</font></label>
					<div class="col-sm-10">
						<input type="hidden" value="${entity.raidersDetailId }"
							name="raidersDetailId" /> <input type="text"
							class="form-control required" id="raidersDetail_raidersTitle"
							name="raidersTitle" value="${entity.raidersTitle }"> <input
							type="hidden" class="form-control" name="image"
							id="raidersdetailFile_input" value="${entity.image }">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">内容<font color="red">&nbsp;*</font></label>
					<div class="col-sm-10">
						<textarea id="editor" name="content" id="">${entity.content }</textarea>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">简介<font color="red">&nbsp;*</font></label>
					<div class="col-sm-10">
						<input type="text" class="form-control" name="description" id="raidersDetail_desc"
							 value="${entity.description }">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">类型<font color="red">&nbsp;*</font></label>
					<div class="col-sm-10">
						<select class="form-control" id="raidersDetail_raidersCateId"
							name="raidersCateId">
							<option value="">--请选择--</option>
							<c:forEach var="info" items="${infos }">
								<option value="${info.raidersCateId }">${info.raidersTitle }</option>
							</c:forEach>
						</select>
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
					<label class="col-sm-2 control-label">推荐<font color="red">&nbsp;*</font></label>
					<div class="col-sm-10">
						<label class="checkbox-inline"> <input type="radio"
							name="isPush" value="1" ${entity.isPush=='1'?'checked':'' }>
							是
						</label> <label class="checkbox-inline"> <input type="radio"
							name="isPush" value="0" ${entity.isPush=='0'?'checked':'' }>
							否
						</label>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">阅读数</label>
					<div class="col-sm-10">
						<input type="text" class="form-control" name="readNum"
							id="raidersDetail_readNum" value="${entity.readNum }">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">序号<font color="red">&nbsp;*</font></label>
					<div class="col-sm-10">
						<input type="text" class="form-control" name="sort"
							id="raidersDetail_sort" value="${entity.sort }">
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
					<label class="col-sm-2 control-label">图片上传<font color="red">&nbsp;*</font></label>
					<div class="col-sm-10">
						<div class="input-group">
							<input type="file" class="form-control" id="raidersdetailFile"
								name="raidersdetailFile"> <span class="input-group-btn">
								<button class="btn btn-success" type="button"
									id="uploadraidersdetail">上传图片</button>
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
								id="raidersdetailFile_img">
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
<script src="resources/ueditor/ueditor.all.min.js"
	type="text/javascript"></script>

<script type="text/javascript">
	function check_fun() {
		$("#tip").html("");
		var b = true;
		var state = $('input[name="state"]:checked').val();
		var isPush = $('input[name="isPush"]:checked').val();
		var sort = $("#raidersDetail_sort").val();
		var cateId = $("#raidersDetail_raidersCateId").val();
		var content = $("#editor").val();
		var raidersTitle = $("#raidersDetail_raidersTitle").val();
		var desc = $("#raidersDetail_desc").val();
		if ('${entity}' == '') {
			var file = document.raidersDetailForm.raidersdetailFile.value;
			var filepath = $("#raidersdetailFile_input").val();
			if(file == "" || filepath == null || filepath == ''){
				$("#tip").html("请上传图片");
				b = false;
			}
		}
		if (sort == null || sort.trim() == '') {
			$("#tip").html("请输入序号");
			b = false;
		}
		if (isPush == undefined) {
			$("#tip").html("请选择是否推荐");
			b = false;
		}
		if (state == undefined) {
			$("#tip").html("请选择状态");
			b = false;
		}
		if (cateId == null || cateId.trim() == '') {
			$("#tip").html("请选择类型");
			b = false;
		}
		if (desc == null || desc.trim() == '') {
			$("#tip").html("请输入内容");
			b = false;
		}
		if (content == null || content.trim() == '') {
			$("#tip").html("请输入内容");
			b = false;
		}
		if (raidersTitle == null || raidersTitle.trim() == '') {
			$("#tip").html("请输入标题");
			b = false;
		}
		return b;
	}

	var ue = UE.getEditor("editor");
	$("#raidersDetail_raidersCateId").val('${entity.raidersCateId }');
	$(function() {
		$('#uploadraidersdetail').click(function() {
			var file = document.raidersDetailForm.raidersdetailFile.value;
			var filepath = $("#raidersdetailFile_input").val();
			if (file == "") {
				$("#tip").html("请选择图片");
				return false;
			}
			$.ajaxFileUpload({
				type : "post",
				dataType : "json",
				fileElementId : 'raidersdetailFile',
				url : "upload/raidersdetail.htm",
				data : {},
				error : function(data) {
					alert("错误！");
				},
				success : function(data) {
					if (data.result = 'success') {
						console.info(data);
						alert("上传图片成功！");
						$('#raidersdetailFile_input').val(data.imgUrl);
						$('#raidersdetailFile_img').attr("src", data.imgUrl);
						$("#tip").html("");
					} else {
						alert(data.msg);
					}
				}
			});
		});
	});
</script>