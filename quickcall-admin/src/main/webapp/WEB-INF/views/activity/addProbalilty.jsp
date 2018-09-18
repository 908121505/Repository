<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<div class="modal-dialog">
	<div class="modal-content">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal"
				aria-hidden="true">X</button>
			<h3 id="myModalLabel">${empty entity?'新增':'修改' }抽奖概率配置</h3>
		</div>
		<div class="modal-body">
			<form class="form-horizontal" method="post" id="prizeTaskForm" name="prizeTaskForm"
				action="activityaward/save${empty entity?'Insert':'Update'}"
				role="form">
				<c:if test="${empty entity==false}">
					<div class="form-group">
						<label class="col-sm-4 control-label">奖品编号<font color="red">&nbsp;*</font></label>
						<div class="col-sm-8">
							<input type="text" readonly="readonly" class="form-control" id="giftInfoId" name="giftInfoId" value="${entity.giftInfoId}">
						</div>
					</div>
				</c:if>
				<div class="form-group">
					<label class="col-sm-4 control-label">奖品名称</label>
					<div class="col-sm-8">
						<input type="text"class="form-control" id="giftName" name="giftName" value="${entity.giftName}">
					</div>
				</div>
				
				<div class="form-group">
					<label class="col-sm-4 control-label">概率</label>
					<div class="col-sm-8">
						<input type="text"class="form-control" id="percent" name="percent" value="${entity.percent}">
					</div>
				</div>
				
				<div class="form-group">
					<label class="col-sm-4 control-label">库存</label>
					<div class="col-sm-8">
						<input type="text"class="form-control" id="giveNum" name="giveNum" value="${entity.giveNum}">
					</div>
				</div>
				
				
				<div class="form-group">
					<label class="col-sm-4 control-label">状态 <font color="red">&nbsp;*</font></label>
					<div class="col-sm-8">
						<select class="form-control" id="state_id" name="state">
							  <option value="1" ${entity.state=='1'?'selected':''}>有效</option>
							  <option value="2" ${entity.state=='2'?'selected':''}>无效</option>
						</select>
							
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
		var giftName = $("#giftName").val();
		var percent = $("#percent").val();
		var state = $("#state_id").val();
		var giveNum = $("#giveNum").val();
		
		if(giftName == null || giftName.trim() == ''){
			$("#tip").html("请填写奖品");
			b = false;
		}else if(percent == null || percent.trim() == ''){
			$("#tip").html("请填写概率");
			b = false;
		}else if(giveNum == null || giveNum.trim() == ''){
			$("#tip").html("请填写库存");
			b = false;
		}else if(state == null || state.trim() == ''){
			$("#tip").html("请选择状态");
			b = false;
		}
		return b;
	}
</script>