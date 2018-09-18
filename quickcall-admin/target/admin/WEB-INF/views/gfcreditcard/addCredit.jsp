<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="modal-dialog">
	<div class="modal-content">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal"
				aria-hidden="true">X</button>
			<h3 id="myModalLabel">${empty entity?'新增':'修改' }广发信用卡申请信息</h3>
		</div>
		<div class="modal-body">
			<form class="form-horizontal" method="post" id="sysUserForm" name="bannerForm"
				action="gfCreditCard/save${empty entity?'Insert':'Update' }.htm"
				role="form">
				<div class="form-group">
					<label class="col-sm-2 control-label">客户id</label>
					<div class="col-sm-10">
						<input type="hidden" value="${entity.gfCreditCardId }" name="gfCreditCardId" /> 
						<input type="text" class="form-control required" id="accountId" name="accountId"
							value="${entity.accountId }">
					    <input type="hidden"
							class="form-control" name="image" id="bannerFile_input"
							value="">
					</div>
				</div>
				
				<div class="form-group">
					<label class="col-sm-2 control-label">${empty entity?'产品id':'产品名称' }</label>
					<div class="col-sm-10">
						<input type="text" class="form-control" ${empty entity?'':'disabled="disabled"' } id="productId" name="productId"
							value="${entity.productId }"><br>
							
					</div>
				</div>
				
				<div class="form-group">
					<label class="col-sm-2 control-label">状态</label> 
					<div class="col-sm-10">
						<label class="checkbox-inline"> <input type="radio"
							name="state" value="1" ${(entity.state=='1') or (empty entity) ?'checked':'' }>
							开启
						</label> <label class="checkbox-inline"> <input type="radio"
							name="state" value="2" ${entity.state=='2'?'checked':'' }>
							关闭
						</label>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">客户名称</label>
					<div class="col-sm-10">
						<input type="text" step="1" class="form-control" id="name" name="name"
							value="${entity.name }">
					</div>
				</div>
				
				<div class="form-group">
					<label class="col-sm-2 control-label">身份证号</label>
					<div class="col-sm-10">
						<input type="text" step="1" class="form-control" id="idCardNo" name="idCardNo"
							value="${entity.idCardNo }">
					</div>
				</div>
				
				
				<div class="form-group">
					<label class="col-sm-2 control-label">邮箱</label>
					<div class="col-sm-10">
						<input type="text" step="1" class="form-control" id="email" name="email"
							value="${entity.email }">
					</div>
				</div>
				
				<div class="form-group">
					<label class="col-sm-2 control-label">手机号</label>
					<div class="col-sm-10">
						<input type="text" step="1" class="form-control" id="mobile" name="mobile"
							value="${entity.mobile }">
					</div>
				</div>
				
				<div class="form-group">
					<label class="col-sm-2 control-label">公司名称</label>
					<div class="col-sm-10">
						<input type="text" step="1" class="form-control" id="companyName" name="companyName"
							value="${entity.companyName }">
					</div>
				</div>
				
				<div class="form-group">
					<label class="col-sm-2 control-label">工作地省份编号</label>
					<div class="col-sm-10">
						<input type="text" step="1" class="form-control" id="workProvId" name="workProvId"
							value="${entity.workProvId }">
					</div>
				</div>
				
				<div class="form-group">
					<label class="col-sm-2 control-label">工作地省份名称</label>
					<div class="col-sm-10">
						<input type="text" step="1" class="form-control" id="workProvName" name="workProvName"
							value="${entity.workProvName }">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">工作地城市编号</label>
					<div class="col-sm-10">
						<input type="text" step="1" class="form-control" id="workCityId" name="workCityId"
							value="${entity.workCityId }">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">工作地城市名称</label>
					<div class="col-sm-10">
						<input type="text" step="1" class="form-control" id="workCityName" name="workCityName"
							value="${entity.workCityName }">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">工作地城市区域编号</label>
					<div class="col-sm-10">
						<input type="text" step="1" class="form-control" id="workCityAreaId" name="workCityAreaId"
							value="${entity.workCityAreaId }">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">工作地城市区域名称</label>
					<div class="col-sm-10">
						<input type="text" step="1" class="form-control" id="workCityAreaName" name="workCityAreaName"
							value="${entity.workCityAreaName }">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">工作地详细地址</label>
					<div class="col-sm-10">
						<input type="text" step="1" class="form-control" id="workAddress" name="workAddress"
							value="${entity.workAddress }">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">居住地省份编号</label>
					<div class="col-sm-10">
						<input type="text" step="1" class="form-control" id="liveProvId" name="liveProvId"
							value="${entity.liveProvId }">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">居住地省份名称</label>
					<div class="col-sm-10">
						<input type="text" step="1" class="form-control" id="liveProvName" name="liveProvName"
							value="${entity.liveProvName }">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">居住地城市编号</label>
					<div class="col-sm-10">
						<input type="text" step="1" class="form-control" id="liveCityId" name="liveCityId"
							value="${entity.liveCityId }">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">居住地城市名称</label>
					<div class="col-sm-10">
						<input type="text" step="1" class="form-control" id="liveCityName" name="liveCityName"
							value="${entity.liveCityId }">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">居住地城市区域编号</label>
					<div class="col-sm-10">
						<input type="text" step="1" class="form-control" id="liveCityAreaId" name="liveCityAreaId"
							value="${entity.liveCityAreaId }">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">居住地城市区域名称</label>
					<div class="col-sm-10">
						<input type="text" step="1" class="form-control" id="liveCityAreaName" name="liveCityAreaName"
							value="${entity.liveCityAreaName }">
					</div>
				</div>
				
				<div class="form-group">
					<label class="col-sm-2 control-label">居住地详细地址</label>
					<div class="col-sm-10">
						<input type="text" step="1" class="form-control" id="liveAddress" name="liveAddress"
							value="${entity.liveAddress }">
					</div>
				</div>
				
			
				<div class="form-group">
					<label class="col-sm-2 control-label">备注</label>
					<div class="col-sm-10">
						<input type="text" class="form-control" name="remark"
							value="${entity.remark }">
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
	return b;
}

</script>