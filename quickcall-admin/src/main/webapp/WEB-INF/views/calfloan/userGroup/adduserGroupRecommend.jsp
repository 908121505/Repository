<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<div class="modal-dialog">
	<div class="modal-content">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal"
				aria-hidden="true">X</button>
			<h3 id="myModalLabel">${empty entity?'新增':'修改' }管家推荐配置</h3>
		</div>
		<div class="modal-body">
			<form class="form-horizontal" method="post" id="userGroupRecommendForm" name="userGroupRecommendForm"
				action="userGroupRecommend/save${empty entity?'Insert':'Update' }.htm"
				role="form">
				<div class="form-group">
					<div class="col-sm-10">
					   <c:if test="${empty entity==false}">
							<input type="hidden" value="${entity.userGroupRecommendId }" id="userGroupRecommendId" name="userGroupRecommendId" /> 
							<input type="hidden" value="${entity.createTime }" id="createTime" name="createTime" /> 
							<input type="hidden" value="${entity.modifyTime }" id="modifyTime" name="modifyTime" /> 
						</c:if>
						<input type="hidden" value="${entity.productName }" id="productName" name="productName" />
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">用户组<font color="red">&nbsp;*</font></label>
					<div class="col-sm-10">
						<select class="form-control" id="userGroupId" name="userGroupId">
								<c:forEach var="info" items="${userGroups}">
									<option value="${info.userGroupId}">${info.userGroupName }</option>
								</c:forEach>
						</select>
					</div>
				</div>
				
				<div class="form-group">
					<label class="col-sm-2 control-label">产品<font color="red">&nbsp;*</font></label>
					<div class="col-sm-10">
						<select class="form-control" id=productId name="productId" onchange="setProductName()">
								<option value="">-请选择-</option>
								<c:forEach var="product" items="${products}">
									<option value="${product.productId}">${product.productName}</option>
								</c:forEach>
						</select>
						
					</div>
				</div>
				
				<div class="form-group">
					<label class="col-sm-2 control-label">精选产品评论</label>
					<div class="col-sm-10">
						<select class="form-control" id=opinionFeedbackId name="opinionFeedbackId">
						</select>
						
					</div>
				</div>
			
				<div class="form-group">
					<label class="col-sm-2 control-label">备注</label>
					<div class="col-sm-10">
								<textarea  id="remark" name="remark"  cols="45" rows="5" style="overflow:auto">${entity.remark}</textarea>
					</div>
				</div>
				
				<div class="form-group">
					<label class="col-sm-2 control-label">标签状态 <font color="red">&nbsp;*</font></label>
					<div class="col-sm-10">
						<select class="form-control" id="state_id" name="state">
							  <option value="1">开启</option>
							  <option value="2">停用</option>
						</select>
					</div>
				</div>
				
				<div class="form-group">
					<label class="col-sm-2 control-label">开始时间<font color="red">&nbsp;*</font></label>
					<div class="col-sm-10">
						<input type="text" id="startTime" class="form-control"
							onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd',minDate:'1950-01-01',maxDate:'#F{$dp.$D(\'endTime\')}'})"
							value="${entity.startTime }" name="startTime" />
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">结束时间<font color="red">&nbsp;*</font></label>
					<div class="col-sm-10">
						<input type="text" id="endTime" class="form-control"
							onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'startTime\')}',maxDate:'2050-12-31'})"
							value="${entity.endTime }" name="endTime" />
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
	 $("#userGroupId").val('${entity.userGroupId}');
	 $("#productId").val('${entity.productId}');
	 $("#state_id").val('${entity.state}');
	 $("#opinionFeedbackId").val('${entity.opinionFeedbackId}');
	 
		getpl('${entity.productId}');
}
function setProductName(){
	$("#opinionFeedbackId").empty(); //清空原数据
	var productName = $("#productId").find("option:selected").text();
	var productId = $("#productId").val();
	$("#productName").val(productName);
	getpl(productId);
}

function getpl(productId){
	$.ajax({
		dataType: "json", 
		url: "userGroupRecommend/getpl.htm?productId="+productId,
		type:"POST",
		contentType: "application/json;charset=utf-8",
		async: false,
		success: function(data){
			//刷新列表
			  var datas = data.list;  
				 $.each(datas,function(index,value){
                     $("#opinionFeedbackId").append("<option value='"+value.opinionFeedbackId+"'>"+value.feedbackReason+"</option>");
                 })
		},
		error:function(data){
			alert("获取评论列表失败");
		}
		
		});
}
	function check_fun(){
		$("#tip").html("");
		var b = true;
		
		
		var userGroupRecommendName = $("#userGroupId").val();
		var productId = $("#productId").val();
		var state = $("#state_id").val();
		var startTime = $("#startTime").val();
		var endTime = $("#endTime").val();
		
		if(userGroupRecommendName == null || userGroupRecommendName.trim() == ''){
			$("#tip").html("请选择分组");
			b = false;
		}else if(productId == null || productId.trim() == ''){
			$("#tip").html("请选择产品");
			b = false;
		}else if(state == null || state.trim() == ''){
			$("#tip").html("请选择状态");
			b = false;
		}else if(endTime == null || endTime.trim() == ''){
			$("#tip").html("请输入结束时间");
			b = false;
		}else if(startTime == null || startTime.trim() == ''){
			$("#tip").html("请输入开始时间");
			b = false;
		}
		return b;
	}
	
</script>