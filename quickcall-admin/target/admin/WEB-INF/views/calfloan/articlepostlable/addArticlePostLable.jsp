<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<div class="modal-dialog">
	<div class="modal-content">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal"
				aria-hidden="true">X</button>
			<h3 id="myModalLabel">${empty entity?'新增':'修改' }产品标签信息</h3>
		</div>
		<div class="modal-body">
			<form class="form-horizontal" method="post" id="productLabelForm" name="productLabelForm"
				action="articlePostLable/save${empty entity?'Insert':'Update' }.htm"
				role="form">
				<div class="form-group">
					<label class="col-sm-2 control-label">标签名称<font color="red">&nbsp;*</font></label>
					<div class="col-sm-10">
					   <c:if test="${empty entity==false}">
							<input maxlength="8"  type="hidden" value="${entity.id }" id="lableId" name="id" />
						</c:if>
						<input maxlength="8" type="text" class="form-control" id="lable_name" name="lableName" value="${entity.lableName}">
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
	function lableNameCheck(name) {
        var b = false;
        $.ajax({
            type: "post",
            url: "articlePostLable/checkLableName",
			dataType:"json",
            async:false,
			data:{
                lableName:name
			},
			error:function () {
				alert("错误！");
            },
			success:function (data) {
                if (data > 0) {
                    b = true;
                }
            }
            
        });
        return b;
    }
	function check_fun(){
		$("#tip").html("");
		var b = true;
		var lableName = $("#lable_name").val();
		if(lableName == null || lableName.trim() == ''){
			$("#tip").html("请输入标签名称");
			b = false;
		}
		var myreg = /^[a-zA-Z\u4e00-\u9fa5]+$/;
        if (!myreg.test(lableName)) {
            $("#tip").html("标签只能输入中文和英文");
            b = false;
        }
        var result = lableNameCheck(lableName);
        if (result) {
            $("#tip").html("标签名称重复");
            b = false;
        }
		return b;
	}

</script>