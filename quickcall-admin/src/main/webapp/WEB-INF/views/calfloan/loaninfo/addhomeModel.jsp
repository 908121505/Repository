<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<div class="modal-dialog">
	<div class="modal-content">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal"
				aria-hidden="true">X</button>
			<h3 id="myModalLabel">${empty entity?'新增':'修改' }首页配置信息</h3>
		</div>
		<div class="modal-body">
			<form class="form-horizontal" method="post" id="homeModelForm" name="homeModelForm"
				action="homeModel/save${empty entity?'Insert':'Update' }.htm"
				role="form">
				<c:if test="${empty entity==false}">
					<div class="form-group">
						<label class="col-sm-4 control-label">首页配置ID<font color="red">&nbsp;*</font></label>
						<div class="col-sm-8">
							<input type="text" readonly="readonly" class="form-control" id="homeModelId" name="homeModelId" value="${entity.homeModelId}">
						</div>
					</div>
				</c:if>
				<div class="form-group">
					<label class="col-sm-4 control-label">模块名称<font color="red">&nbsp;*</font></label>
					<div class="col-sm-8">
						<input type="text" class="form-control" id="model_name" name="modelName" value="${entity.modelName}">
					</div>
				</div>
				<div class="form-group">
					<label for="inputEmail3" class="col-sm-4 control-label">模块介绍</label>
					<div class="col-sm-8">
						<div class="col-sm-6">
								<textarea  id="modelContent" name="modelContent"  cols="45" rows="5" style="overflow:auto">${entity.modelContent}</textarea>
						</div>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-4 control-label">排序<font color="red">&nbsp;*</font></label>
					<div class="col-sm-8">
						<input type="number" class="form-control" id="sort" name="sort" value="${entity.sort}">
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
					<label for="inputEmail3" class="col-sm-4 control-label">备注说明<font color="red">&nbsp;* ios 配置填写ios，android 填写 android</font></label>
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
	$("#state_id").val(parseInt('${entity.state}'));
	
}
	function check_fun(){
		$("#tip").html("");
		var b = true;
		
		var modelName = $("#model_name").val();
		var sort = $("#sort").val();
		var state = $("#state_id").val();
		var remark = $("#remark").val();
		
		if(modelName == null || modelName.trim() == ''){
			$("#tip").html("请输入模块名称");
			b = false;
		}else if(sort == null || sort.trim() == ''){
			$("#tip").html("请输入序号");
			b = false;
		}else if(remark == null || remark.trim() == ''){
			$("#tip").html("请输入备注说明，ios 或 android");
			b = false;
		}else if(state == null || state.trim() == ''){
			$("#tip").html("请选择状态");
			b = false;
		}
		
		return b;
	}
	
</script>