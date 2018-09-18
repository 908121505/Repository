<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<div class="modal-dialog">
	<div class="modal-content">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal"
				aria-hidden="true">X</button>
			<h3 id="myModalLabel">${empty entity?'新增':'修改' }奖励信息</h3>
		</div>
		<div class="modal-body">
			<form class="form-horizontal" method="post" id="activityawardForm" name="activityawardForm"
				action="activityaward/save${empty entity?'Insert':'Update' }.htm"
				role="form">
				<div class="form-group">
					<label class="col-sm-4 control-label">奖品类型<font color="red">&nbsp;*</font></label>
					<div class="col-sm-8">
						<select class="form-control" id="activityPrizeId" name="activityPrizeId" onchange="setpname()">
							 <option value="fe24d09a679046a6978699cbd37cc98e" }>话费</option>
							  <option value="36168a8682f0420a8cc907c74397e376" >流量</option>
							   <option value="a9b5595fd82b46298b7bb3bd0ae15c0f" }>京东卡</option>
						</select>
						<input type="hidden" value="" id="activityPrizeName" name="activityPrizeName" /> 
					</div>
				</div>
				
				<div class="form-group">
					<label for="inputEmail3" class="col-sm-4 control-label">奖品金额<font color="red">&nbsp;*</font></label>
					<div class="col-sm-8">
						<div>
								<input type="number" id="activityPrizePrice"  class="form-control" name="activityPrizePrice">
						</div>
					</div>
				</div>
				
				<div class="form-group">
					<label class="col-sm-4 control-label">手机号<font color="red">&nbsp;*</font></label>
					<div class="col-sm-8">
						<input type=text id="phone_num"  class="form-control" name="phoneNum" onkeyup="value=value.replace(/[^(\d)]/g,'')">
					</div>
				</div>
				
				<div class="form-group">
					<label class="col-sm-4 control-label">所属活动<font color="red">&nbsp;*</font></label>
					<div class="col-sm-8">
						<select class="form-control" id="activity_id" name="activityId">
							  <option value="2a75ff4fcb034c0c9df1cac818ca44d2" }>问卷调查</option>
						</select>
					</div>
				</div>
				
				
				
				<div class="form-group">
					<label class="col-sm-4 control-label">收货地址</label>
					<div class="col-sm-8">
							<textarea  id="activityAwardAddress" name="activityAwardAddress"  cols="45" rows="10" style="overflow:auto"></textarea>
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
		var b = true;
		
		
		var phoneNum = $("#phone_num").val();
		var activityPrizeId = $("#activityPrizeId").val();
		var activityPrizePrice = $("#activityPrizePrice").val();
		var activity_id = $("#activity_id").val();
		if(phoneNum == null || phoneNum.trim() == ''){
			$("#tip").html("请输入中奖手机号");
			b = false;
		}
		if(activityPrizeId == null || activityPrizeId.trim() == ''){
			$("#tip").html("请选择奖品类型");
			b = false;
		}
		if(activityPrizePrice == null || activityPrizePrice.trim() == ''){
			$("#tip").html("请输入奖品金额");
			b = false;
		}
		if(activityId == null || activityId.trim() == ''){
			$("#tip").html("请选择所属活动 ");
			b = false;
		}
		return b;
	}
	
	function setpname(){
		var pname = $("#activityPrizeId").find("option:selected").text();
		$("#activityPrizeName").val(pname);
	}
	$("#activityPrizeName").val($("#activityPrizeId").find("option:selected").text());
	
</script>