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
				action="bigvPhone/save${empty entity?'Insert':'Update' }.htm"
				role="form">
				<div class="form-group">
					<label class="col-sm-2 control-label">手机号<font color="red">&nbsp;*</font></label>
					<div class="col-sm-10">
						<input type="hidden" value="${entity.id }" name="id" />
						<input type="text" class="form-control required" id="form_phone" name="phone"
							   value="${entity.phone }" maxlength="11">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">备注</label>
					<div class="col-sm-10">
						<input type="text" class="form-control" name="remark" value="${entity.remark }">
					</div>
				</div>
				<span id="tip" style="color: red;font-size: 14px;margin-left:20px; "></span>
			</form>
		</div>
		
		<div class="modal-footer">
			<button class="btn btn-default" data-dismiss="modal" aria-hidden="true">取消</button>
			<button class="btn btn-primary" data-dismiss="modal">保存</button>
		</div>

	</div>
</div>
<script type="text/javascript">
	function check_fun(){
		$("#tip").html("");

		var form_phone = $("#form_phone").val();
		if(form_phone == null || form_phone.trim() == ''){
			$("#tip").html("请输入用户手机号");
			return false;
		}

        var chekFlag = false;
        // 数据校验
        $.ajax({
            url: "bigvPhone/checkData.htm",
            async: false,
            dataType: "JSON",
            type: 'post',
            data: {
                phone: form_phone,
				id : "${entity.id}"
            },
            success: function (data) {
                if (data.code == "1") {
                    $("#tip").html(data.msg);
                } else {
                    if(window.confirm("确定是否保存手机号【" + form_phone + "】?")){
                        chekFlag = true;
                    }
                }
            },
            error: function (data) {
                alert("请求出错，请稍后再试！");
            }
        });

        return chekFlag;
	}
	$(function() {
	});
</script>