<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<link type="text/css"  href="resources/bootstrap/css/bootstrap-datetimepicker.min.css"  rel="stylesheet" />
<script type="text/javascript" language="javascript" src="resources/bootstrap/js/bootstrap-datetimepicker.min.js"  charset="utf-8"></script>


<div class="modal-dialog">
	<div class="modal-content">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal"
				aria-hidden="true">X</button>
			<h3 id="myModalLabel">编辑接单信息</h3>
		</div>
		<div class="modal-body" style="max-height: 700px; overflow-y: auto;"  id = "editBody">
			<form class="form-horizontal" method="post" id="skillForm" name="skillForm" role="form"
				action="receiveOrder/saveUpdate.htm">
				<div class="form-group">
					<label class="col-sm-3 control-label">用户ID<font color="red">&nbsp;*</font></label>
					<div class="col-sm-6">
						<input type="text" id="customerId" class="form-control"  name="customerId" readonly="readonly" value="${entity.customerId}">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label">用户昵称<font color="red">&nbsp;*</font></label>
					<div class="col-sm-6">
						<input type="text" id="customerNickName" class="form-control"  name="customerNickName" readonly="readonly" value="${entity.customerNickName}">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label">服务类型<font color="red">&nbsp;*</font></label>
					<div class="col-sm-6">
						<input type="text" id="serviceType" class="form-control"  name="serviceType"  readonly="readonly" value="${entity.serviceType}">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label">接单状态<font color="red">&nbsp;*</font></label>
					<div class="col-sm-6">
						<input type="text" id="receiveStatus" class="form-control"  name="receiveStatus" value="${entity.receiveStatus}">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label">价格等级<font color="red">&nbsp;*</font></label>
					<div class="col-sm-6">
						<input type="text" id="skillRange" class="form-control"  name="skillRange" readonly="readonly" value="${entity.skillRange}">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label">单价<font color="red">&nbsp;*</font></label>
					<div class="col-sm-6">
						<input type="text" id="serviceUnit" class="form-control"  name="serviceUnit" readonly="readonly" value="${entity.serviceUnit}">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label">折扣类型<font color="red">&nbsp;*</font></label>
					<div class="col-sm-6">
						<input type="text" id="discountType" class="form-control"  name="discountType" readonly="readonly" value="${entity.discountType}">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label">结束时间<font color="red">&nbsp;*</font></label>
					<div class="col-sm-6">
						<input type="text" id="endTime" class="form-control"  name="endTime" readonly="readonly" value="${entity.endTime}">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label">创建时间<font color="red">&nbsp;*</font></label>
					<div class="col-sm-6">
						<input type="text" id="createTime" class="form-control"  name="createTime" readonly="readonly" value="${entity.createTime}">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label">修改时间<font color="red">&nbsp;*</font></label>
					<div class="col-sm-6">
						<input type="text" id="modifyTime" class="form-control"  name="modifyTime" readonly="readonly" value="${entity.modifyTime}">
					</div>
				</div>
			</form>
		</div>
		<div class="modal-footer">
			<span id="tip" style="color: red; font-size: 16px; margin-left: 20px;float:left;"></span>
			<button class="btn btn-default" data-dismiss="modal" aria-hidden="true" >取消</button>
			<button class="btn btn-primary" data-dismiss="modal" >保存</button>
		</div>
	</div>
</div>

<script type="text/javascript">
    $(function (){
    });
    function check_fun(){
        $("#tip").html("");
        return true;
    }
</script>