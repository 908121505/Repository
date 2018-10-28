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
			<h3 id="myModalLabel">接单详情</h3>
		</div>
		<div class="modal-body" style="max-height: 700px; overflow-y: auto;"  id = "editBody">
			<form class="form-horizontal" method="post" id="editOrderForm" name="editOrderForm" role="form"
				action="order/saveUpdate.htm">

				<div class="form-group">
					<label class="col-sm-3 control-label">订单ID<font color="red">&nbsp;*</font></label>
					<div class="col-sm-6">
						<input type="text" id="orderId" class="form-control"  name="orderId" readonly="readonly" value="${entity.orderId}">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label">订单开始时间<font color="red">&nbsp;*</font></label>
					<div class="col-sm-6">
						<input type="text" id="startTime" class="form-control"  name="startTime" readonly="readonly" value="${entity.startTime}">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label">订单结束时间<font color="red">&nbsp;*</font></label>
					<div class="col-sm-6">
						<input type="text" id="endTime" class="form-control"  name="endTime"  readonly="readonly" value="${entity.endTime}">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label">接单用户ID<font color="red">&nbsp;*</font></label>
					<div class="col-sm-6">
						<input type="text" id="receivedOrderId" class="form-control"  name="receivedOrderId" readonly="readonly" value="${entity.receivedOrderId}">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label">下单用户ID<font color="red">&nbsp;*</font></label>
					<div class="col-sm-6">
						<input type="text" id="placeOrderId" class="form-control"  name="placeOrderId" readonly="readonly" value="${entity.placeOrderId}">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label">服务类型<font color="red">&nbsp;*</font></label>
					<div class="col-sm-6">
						<input type="text" id="serviceType" class="form-control"  name="serviceType" readonly="readonly" value="${entity.serviceType}">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label">折扣类型<font color="red">&nbsp;*</font></label>
					<div class="col-sm-6">
						<input type="text" id="discountType" class="form-control"  name="discountType" readonly="readonly" value="${entity.discountType}">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label">数量<font color="red">&nbsp;*</font></label>
					<div class="col-sm-6">
						<input type="text" id="orderNum" class="form-control"  name="orderNum" readonly="readonly" value="${entity.orderNum}">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label">订单金额<font color="red">&nbsp;*</font></label>
					<div class="col-sm-6">
						<input type="text" id="orderAmount" class="form-control"  name="orderAmount" readonly="readonly" value="${entity.orderAmount}">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label">订单状态<font color="red">&nbsp;*</font></label>
					<div class="col-sm-6">
						<select id="orderStatus" class="form-control"  name="orderStatus">
							<c:forEach items="${editSmallStatus}" var ="smallOrderStatusVO" varStatus="status">
								<option value="${smallOrderStatusVO.value}">${smallOrderStatusVO.desc}</option>
							</c:forEach>
						</select>
					</div>
				</div>
				<div class="form-group" id="remarkReasonDev">
					<label class="col-sm-3 control-label">取消原因<font color="red">&nbsp;*</font></label>
					<div class="col-sm-6">
						<input type="text" placeholder="如果状态选取消，请写上取消原因" id="remarkReason" class="form-control"  name="remarkReason" value="${entity.remarkReason}">
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