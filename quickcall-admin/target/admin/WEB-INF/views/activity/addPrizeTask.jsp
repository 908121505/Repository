<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<div class="modal-dialog">
	<div class="modal-content">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal"
				aria-hidden="true">X</button>
			<h3 id="myModalLabel">${empty entity?'新增':'修改' }活动任务信息</h3>
		</div>
		<div class="modal-body">
			<form class="form-horizontal" method="post" id="prizeTaskForm" name="prizeTaskForm"
				action="prizeTask/save${empty entity?'Insert':'Update' }.htm"
				role="form">
				<c:if test="${empty entity==false}">
					<div class="form-group">
						<label class="col-sm-4 control-label">任务ID<font color="red">&nbsp;*</font></label>
						<div class="col-sm-8">
							<input type="text" readonly="readonly" class="form-control" id="prizeTaskId" name="prizeTaskId" value="${entity.prizeTaskId}">
						</div>
					</div>
				</c:if>
				<div class="form-group">
					<label class="col-sm-4 control-label">产品<font color="red">&nbsp;*</font></label>
					<div class="col-sm-8">
						<select class="form-control" id="productId" name="productId">
								<c:forEach var="info" items="${products }">
									<option value="${info.productId }">${info.productName }</option>
								</c:forEach>
						</select>
						<input type="hidden" class="form-control" id="productName" name="productName">
					</div>
				</div>
				
				
				<div class="form-group">
					<label class="col-sm-4 control-label">状态 <font color="red">&nbsp;*</font></label>
					<div class="col-sm-8">
						<select class="form-control" id="state_id" name="state">
							  <option value="1">有效</option>
							  <option value="2">无效</option>
						</select>
							
					</div>
				</div>
				
				<div class="form-group">
					<label class="col-sm-4 control-label">奖品</label>
					<div class="col-sm-8">
						<select class="form-control" id="activityPrizeName" name="activityPrizeName">
							  <option value="红包">红包</option>
							  <option value="京东卡">京东卡</option>
							  <option value="流量">流量</option>
							  <option value="话费">话费</option>
						</select>
							
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-4 control-label">奖金</label>
					<div class="col-sm-8">
						<input type="text"class="form-control" id="activityPrizePrice" name="activityPrizePrice" value="${entity.activityPrizePrice}">
					</div>
				</div>
				
				<div class="form-group">
					<label class="col-sm-4 control-label">所属活动<font color="red">&nbsp;*</font></label>
					<div class="col-sm-8">
						<select class="form-control" id="activityId" name="activityId">
								<c:forEach var="info" items="${activitys }">
									<option value="${info.activityId }">${info.title }</option>
								</c:forEach>
						</select>
					</div>
				</div>
				
				<div class="form-group">
					<label class="col-sm-4 control-label">图片上传</label>
					<div class="col-sm-8">
						<div class="input-group">
							<input type="file" class="form-control" id="prizeTaskFile"
								name="prizeTaskFile"> <span class="input-group-btn">
								<button class="btn btn-success" type="button"
									id="uploadprizeTask">上传图片</button>
							</span>
						</div>
					</div>
				</div>
				<div class="form-group">
					<label for="inputEmail3" class="col-sm-4 control-label">任务背景图</label>
					<div class="col-sm-8">
						<div class="col-sm-6">
							<img src="${entity.prizeTaskImg }"
								class="img-rounded" style="max-width: 100px; max-height: 80px;"
								id="labelBackgroundImg"/>
							<input type="hidden" class="form-control" name="prizeTaskImg"
							id="prizeTaskImg" value="${entity.prizeTaskImg }">
						</div>
					</div>
				</div>
				
				<div class="form-group">
					<label for="inputEmail3" class="col-sm-4 control-label">活动介绍</label>
					<div class="col-sm-8">
						<div class="col-sm-6">
								<textarea  id="remark" name="remark"  cols="45" rows="5" style="overflow:auto">${entity.remark}</textarea>
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
if('${entity}'!=''){
	 $("#productId").val('${entity.productId}');
	$("#activityPrizeName").val('${entity.activityPrizeName}');
	$("#state_id").val(parseInt('${entity.state}'));
	$("#activityId").val('${entity.activity.activityId}');
	
}
	function check_fun(){
		$("#tip").html("");
		var b = true;
		
		var productId = $("#productId").val();
		var activityPrizeName = $("#activityPrizeName").val();
		var state = $("#state_id").val();
		var activityId = $("#activityId").val();
		
		if(productId == null || productId.trim() == ''){
			$("#tip").html("请选择产品");
			b = false;
		}else if(activityPrizeName == null || activityPrizeName.trim() == ''){
			$("#tip").html("请选择奖品");
			b = false;
		}else if(activityId == null || activityId.trim() == ''){
			$("#tip").html("请选择活动");
			b = false;
		}else if(state == null || state.trim() == ''){
			$("#tip").html("请选择状态");
			b = false;
		}
		
		//赋值给产品名称
		$("#productName").val($("#productId").find("option:selected").text());
		return b;
	}
	$(function() {
		$('#uploadprizeTask').click(function() {
			var file = document.prizeTaskForm.prizeTaskFile.value;
			if(file == ""){
				$("#tip").html("请选择图片");
				return false;
			}
			$.ajaxFileUpload({
				type : "post",
				dataType : "json",
				fileElementId : 'prizeTaskFile',
				url : "upload/prizeTask.htm",
				data : {
					"id" : "${entity.prizeTaskId }"
				},
				error : function(data) {
					alert("错误！");
				},
				success : function(data) {
					if (data.result = 'success') {
						console.info(data);
						alert("上传图片成功！");
						$('#prizeTaskImg').val(data.imgUrl);
						$('#labelBackgroundImg').attr("src", data.imgUrl);
						$("#tip").html("");
					} else {
						alert(data.msg);
					}
				}
			});
		});
	});
</script>