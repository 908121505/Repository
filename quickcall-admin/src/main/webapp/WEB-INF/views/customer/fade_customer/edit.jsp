<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="modal-dialog" style="width: 50%;">
	<div class="modal-content">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal" aria-hidden="true">X</button>
			<h3 id="myModalLabel">${empty entity?'新增':'修改' }随机用户</h3>
		</div>
		<div class="modal-body">
			<form class="form-horizontal" method="post" id="fadeCustomerForm" name="fadeCustomerForm"
				action="fadeCustomer/save${empty entity?'Insert':'Update' }.htm"
				role="form">
				<div class="form-group">
					<label class="col-sm-2 control-label">用户昵称<font color="red">&nbsp;*</font></label>
					<div class="col-sm-10">
						<input type="hidden" value="${entity.id }" name="id" />
						<input type="text" class="form-control required" id="form_nickName" name="nickName" value="${entity.nickName }">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">状态<font color="red">&nbsp;*</font></label>
					<div class="col-sm-10">
						<label class="checkbox-inline">
							<input type="radio" name="status" value="1" ${empty entity or entity.status=='1'?'checked':'' }>
							启用
						</label>
						<label class="checkbox-inline">
							<input type="radio" name="status" value="2" ${entity.status=='0'?'checked':'' }>
							停用
						</label>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">备注</label>
					<div class="col-sm-10">
						<input type="text" class="form-control" name="remark" value="${entity.remark }">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">图片上传<c:if test="${entity eq null }"><font color="red">&nbsp;*</font></c:if>
					</label>
					<div class="col-sm-10">
						<div class="input-group">
							<input type="hidden" class="form-control" name="headPortraitUrl" id="headPortraitUrl_input" value="${entity.headPortraitUrl }">
							<input type="file" class="form-control" id="appversionFile" name="appversionFile">
							<span class="input-group-btn">
								<button class="btn btn-success" type="button" id="uploadBanner">上传图片</button>
							</span>
						</div>
					</div>
				</div>
				<div class="form-group">
					<label for="bannerFile_img" class="col-sm-2 control-label">图片</label>
					<div class="col-sm-10">
						<div class="col-sm-6">
							<img src="${entity.headPortraitUrl }" alt="暂无图片，点击上传！" class="img-rounded" style="max-width: 100%; max-height: 300px;" id="bannerFile_img">
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
<script type="text/javascript">
	function check_fun(){
		$("#tip").html("");

		var form_nickName = $("#form_nickName").val();
		if(form_nickName == null || form_nickName.trim() == ''){
			$("#tip").html("请输入用户昵称");
			return false;
		}

		return true;
	}
	$(function() {
        $('#uploadBanner').click(function() {
            var file = document.fadeCustomerForm.appversionFile.value;
            if(file == ""){
                $("#tip").html("请选择图片");
                return false;
            }
            $.ajaxFileUpload({
                type : "post",
                dataType : "json",
                fileElementId : 'appversionFile',
                url : "upload/fadeCustomerHeadImg.htm",
                data : {},
                error : function(data) {
                    alert("错误！");
                },
                success : function(data) {
                    if (data.result = 'success') {
                        console.info(data);
                        alert("上传图片成功！");
                        $('#headPortraitUrl_input').val(data.imgUrl);
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