<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="modal-dialog">
	<div class="modal-content">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal"
				aria-hidden="true">X</button>
			<h3 id="myModalLabel">广发信用卡详情</h3>
		</div>
		<div class="modal-body">
			<form class="form-horizontal" method="post" id="sysUserForm" name="bannerForm"
				action=""
				role="form">
				<div class="form-group">
					<label class="col-sm-2 control-label">客户id</label>
					<div class="col-sm-10">
						<input type="hidden" value="${entity.gfCreditCardId }" name="gfCreditCardId" /> 
						<input type="text" class="form-control required" disabled="disabled"  id="accountId" name="accountId"
							value="${entity.accountId }">
					    <input type="hidden"
							class="form-control" name="image" id="bannerFile_input"
							value="">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">产品名称</label>
					<div class="col-sm-10">
						<input type="text" class="form-control" disabled="disabled" id="productId" name="productId"
							value="${entity.productId }"><br>
							
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">状态</label>
					<div class="col-sm-10">
						<label class="checkbox-inline"> <input type="radio" disabled="disabled"
							name="state" value="1" ${entity.state=='1'?'checked':'' }>
							开启
						</label> <label class="checkbox-inline"> <input type="radio" disabled="disabled"
							name="state" value="0" ${entity.state=='0'?'checked':'' }>
							关闭
						</label>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">客户名称</label>
					<div class="col-sm-10">
						<input type="text" step="1" class="form-control" id="name" name="name" disabled="disabled"
							value="${entity.name }">
					</div>
				</div>
				
				<div class="form-group">
					<label class="col-sm-2 control-label">身份证号</label>
					<div class="col-sm-10">
						<input type="text" step="1" class="form-control" id="idCardNo" name="idCardNo" disabled="disabled"
							value="${entity.idCardNo }">
					</div>
				</div>
				
				
				<div class="form-group">
					<label class="col-sm-2 control-label">邮箱</label>
					<div class="col-sm-10">
						<input type="text" step="1" class="form-control" id="email" name="email" disabled="disabled"
							value="${entity.email }">
					</div>
				</div>
				
				<div class="form-group">
					<label class="col-sm-2 control-label">手机号</label>
					<div class="col-sm-10">
						<input type="text" step="1" class="form-control" id="mobile" name="mobile" disabled="disabled"
							value="${entity.mobile }">
					</div>
				</div>
				
				<div class="form-group">
					<label class="col-sm-2 control-label">公司名称</label>
					<div class="col-sm-10">
						<input type="text" step="1" class="form-control" id="companyName" name="companyName" disabled="disabled"
							value="${entity.companyName }">
					</div>
				</div>
				
				<div class="form-group">
					<label class="col-sm-2 control-label">工作地省份编号</label>
					<div class="col-sm-10">
						<input type="text" step="1" class="form-control" id="workProvId" name="workProvId" disabled="disabled"
							value="${entity.workProvId }">
					</div>
				</div>
				
				<div class="form-group">
					<label class="col-sm-2 control-label">工作地省份名称</label>
					<div class="col-sm-10">
						<input type="text" step="1" class="form-control" id="workProvName" name="workProvName"  disabled="disabled"
							value="${entity.workProvName }">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">工作地城市编号</label>
					<div class="col-sm-10">
						<input type="text" step="1" class="form-control" id="workCityId" name="workCityId"  disabled="disabled"
							value="${entity.workCityId }">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">工作地城市名称</label>
					<div class="col-sm-10">
						<input type="text" step="1" class="form-control" id="workCityName" name="workCityName"  disabled="disabled"
							value="${entity.workCityName }">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">工作地城市区域编号</label>
					<div class="col-sm-10">
						<input type="text" step="1" class="form-control" id="workCityAreaId" name="workCityAreaId"  disabled="disabled"
							value="${entity.workCityAreaId }">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">工作地城市区域名称</label>
					<div class="col-sm-10">
						<input type="text" step="1" class="form-control" id="workCityAreaName" name="workCityAreaName"  disabled="disabled"
							value="${entity.workCityAreaName }">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">工作地详细地址</label>
					<div class="col-sm-10">
						<input type="text" step="1" class="form-control" id="workAddress" name="workAddress"  disabled="disabled"
							value="${entity.workAddress }">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">居住地省份编号</label>
					<div class="col-sm-10">
						<input type="text" step="1" class="form-control" id="liveProvId" name="liveProvId"  disabled="disabled"
							value="${entity.liveProvId }">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">居住地省份名称</label>
					<div class="col-sm-10">
						<input type="text" step="1" class="form-control" id="liveProvName" name="liveProvName"  disabled="disabled"
							value="${entity.liveProvName }">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">居住地城市编号</label>
					<div class="col-sm-10">
						<input type="text" step="1" class="form-control" id="liveCityId" name="liveCityId"  disabled="disabled"
							value="${entity.liveCityId }">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">居住地城市名称</label>
					<div class="col-sm-10">
						<input type="text" step="1" class="form-control" id="liveCityName" name="liveCityName"  disabled="disabled"
							value="${entity.liveCityId }">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">居住地城市区域编号</label>
					<div class="col-sm-10">
						<input type="text" step="1" class="form-control" id="liveCityAreaId" name="liveCityAreaId"  disabled="disabled"
							value="${entity.liveCityAreaId }">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">居住地城市区域名称</label>
					<div class="col-sm-10">
						<input type="text" step="1" class="form-control" id="liveCityAreaName" name="liveCityAreaName"  disabled="disabled"
							value="${entity.liveCityAreaName }">
					</div>
				</div>
				
				<div class="form-group">
					<label class="col-sm-2 control-label">居住地详细地址</label>
					<div class="col-sm-10">
						<input type="text" step="1" class="form-control" id="liveAddress" name="liveAddress"  disabled="disabled"
							value="${entity.liveAddress }">
					</div>
				</div>
				
			
				<div class="form-group">
					<label class="col-sm-2 control-label">备注</label>
					<div class="col-sm-10">
						<input type="text" class="form-control" name="remark"  disabled="disabled"
							value="${entity.remark }">
					</div>
				</div>
			
				<span id="tip" style="color: red;font-size: 14px;margin-left:20px; "></span>
			</form>
		</div>
		
		<div class="modal-footer">
			<button class="btn btn-default" data-dismiss="modal"
				aria-hidden="true">关闭</button>
			
		</div>
		
		
	</div>
</div>
<script type="text/javascript"
	src="resources/js/My97DatePicker/WdatePicker.js" charset="utf-8"></script>
<script type="text/javascript">
function check_fun(){
	$("#tip").html("");
	var b = true;
	return b;
}

</script>