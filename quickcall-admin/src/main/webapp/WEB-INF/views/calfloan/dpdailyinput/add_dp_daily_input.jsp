<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="modal-dialog">
	<div class="modal-content">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal"
				aria-hidden="true">X</button>
			<h3 id="myModalLabel">${empty entity?'新增':'修改' }大屏人工录入</h3>
		</div>
		<div class="modal-body">
			<form class="form-horizontal" method="post" id="sysUserForm" name="dpDailyInputForm"
				action="dpDailyInput/save${empty entity?'Insert':'Update' }.htm"
				role="form">
				<div class="form-group">
					<label class="col-sm-2 control-label">统计日期<font color="red">&nbsp;*</font></label>
					   <div class="col-sm-10">
							<input type="text" id="stats_time" name="statsTime" class="form-control"
							onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd',minDate:'1950-01-01',maxDate:'2100-12-31'})"
							value="${entity.statsTime}" name="statsTime" />
					</div>
				</div>
				
				<div class="form-group">
					<label class="col-sm-2 control-label">商家数<font color="red">&nbsp;*</font></label>
					<div class="col-sm-10">
						<input type="number" step="0.01" class="form-control" id="newproductNumYdayPer" name="newproductNumYdayPer"
							value="${entity.newproductNumYdayPer}">
						<input type="hidden" value="${entity.id}" name="Id" />
							<%--<font color="red">(新增之后可以修改昨日人均注册商家数 ,保留四位有效数字)</font>--%>
					</div>
				</div>

				<div class="form-group">
					<label class="col-sm-2 control-label">人均收入<font color="red">&nbsp;*</font></label>
					<div class="col-sm-10">
						<input type="number" step="0.01" class="form-control" id="incomeYdayPer" name="incomeYdayPer"
							   value="${entity.incomeYdayPer}">
						<%--<font color="red">(新增之后可以修改昨日人均收入 ,保留四位有效数字)</font>--%>
					</div>
				</div>

				<div class="form-group">
					<label class="col-sm-2 control-label">次日留存率<font color="red">&nbsp;*</font></label>
					<div class="col-sm-10">
						<input type="number" step="0.01" class="form-control" id="userStayNdayRate" name="userStayNdayRate"
							   value="${entity.userStayNdayRate}">
						<%--<font color="red">(注：新增之后可以修改昨日次日留存率 ,保留四位有效数字)</font>--%>
					</div>
				</div>

				<div class="form-group">
					<label class="col-sm-2 control-label">闪贷转化率<font color="red">&nbsp;*</font></label>
					<div class="col-sm-10">
						<input type="number" step="0.01" class="form-control" id="shandaiToGuanjiaRate" name="shandaiToGuanjiaRate"
							   value="${entity.shandaiToGuanjiaRate}">
						<font color="red">(注：每天只能新增一条记录(时间默认为今天),新增之后可以修改 ,保留四位有效数字)</font>
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
	var productNum = $("#newproductNumYdayPer").val();
	var incomeYdayPer = $("#incomeYdayPer").val();
	var userStayNdayRate = $("#userStayNdayRate").val();
	var shandaiToGuanjiaRate = $("#shandaiToGuanjiaRate").val();
	var statsTime = $("#stats_time").val();
	if(productNum == null || productNum.trim() == ''){
		$("#tip").html("请输入昨日人均注册商家数");
		b = false;
	}
	if(statsTime == null || statsTime.trim() == ''){
		$("#tip").html("请选统计日期");
		b = false;
	}
	
	var temp = /^\d{1,9}(\.\d{1,4})?$/;
	if(!temp.test(productNum)){
		$("#tip").html("昨日人均注册商家数格式不对");
		b = false;
	}

    if(!temp.test(incomeYdayPer)){
        $("#tip").html("昨日人均收入格式不对");
        b = false;
    }

    if(!temp.test(userStayNdayRate) || userStayNdayRate>1){
        $("#tip").html("昨日次日留存率格式不对");
        b = false;
    }

    if(!temp.test(shandaiToGuanjiaRate) || shandaiToGuanjiaRate>1){
        $("#tip").html("昨日闪贷转化率格式不对");
        b = false;
    }
	return b;
}
</script>