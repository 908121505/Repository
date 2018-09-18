<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="modal-dialog">
	<div class="modal-content">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal"
				aria-hidden="true">X</button>
			<h3 id="myModalLabel">${empty entity?'新增':'修改' }营收信息</h3>
		</div>
		<div class="modal-body">
			<form class="form-horizontal" method="post" id="sysUserForm" name="yingshouForm"
				action="yingshou/save${empty entity?'Insert':'Update' }.htm"
				role="form">
				<div class="form-group">
					<label class="col-sm-2 control-label">营收日期<font color="red">&nbsp;*</font></label>
					   <div class="col-sm-10">
							<input type="text" id="ys_time" name="ysTime" class="form-control"
							onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd',minDate:'1950-01-01',maxDate:'2100-12-31'})"
							value="${entity.ysTime }" name="createTime" />
					</div>
				</div>
				
				<div class="form-group">
					<label class="col-sm-2 control-label">营收金额<font color="red">&nbsp;*</font></label>
					<div class="col-sm-10">
						<input type="number" step="0.01" class="form-control" id="totalMoney" name="totalMoney"
							value="${entity.totalMoney }">
						<input type="hidden" value="${entity.yingshouId }" name="yingshouId" />
						<input type="hidden" value="${entity.createTime }" name="createTime" />
						<input type="hidden" value="${entity.state }" name="state" /> <br>
							<font color="red">(注：每天只能新增一条记录(时间默认为今天),新增之后可以修改营收金额 ,保留两位有效数字)</font>
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
function check_fun(){
	$("#tip").html("");
	var b = true;
	var totalMoney = $("#totalMoney").val();
	var ysTime = $("#ys_time").val();
	if(totalMoney == null || totalMoney.trim() == ''){
		$("#tip").html("请输入营收金额");
		b = false;
	}
	if(ysTime == null || ysTime.trim() == ''){
		$("#tip").html("请选择营收日期");
		b = false;
	}
	
	var temp = /^\d{1,9}(\.\d{1,2})?$/;
	if(!temp.test(totalMoney)){
		$("#tip").html("营收金额格式不对");
		b = false;
	}
	return b;
}
</script>