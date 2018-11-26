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
			<h3 id="myModalLabel">新增订单</h3>
		</div>
		<div class="modal-body" style="max-height: 700px; overflow-y: auto;"  id = "editBody">
			<form class="form-horizontal" method="post" id="skillForm" name="skillForm" role="form"
				action="order/saveInsert.htm">

				<%--<div class="form-group">
					<label class="col-sm-3 control-label">订单ID<font color="red">&nbsp;*</font></label>
					<div class="col-sm-6">
						<input type="text" id="orderId" class="form-control"  name="orderId">
					</div>
				</div>--%>
				<div class="form-group">
					<label class="col-sm-3 control-label">订单开始时间<font color="red">&nbsp;*</font></label>
					<div class="col-sm-6">
						<input class="form-control" name="startTime" type="text" id="startTime" onfocus="WdatePicker({skin:'default',dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'1950-01-01'})">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label">订单结束时间<font color="red">&nbsp;*</font></label>
					<div class="col-sm-6">
						<input class="form-control" name="endTime" type="text" id="endTime" onfocus="WdatePicker({skin:'default',dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'1950-01-01'})">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label">接单用户ID<font color="red">&nbsp;*</font></label>
					<div class="col-sm-6">
						<input type="text" id="receivedOrderId" class="form-control"  name="receivedOrderId">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label">下单用户ID<font color="red">&nbsp;*</font></label>
					<div class="col-sm-6">
						<input type="text" id="placeOrderId" class="form-control"  name="placeOrderId">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label">服务类型<font color="red">&nbsp;*</font></label>
					<div class="col-sm-6">
						<select class="form-control" name="serviceType" id="serviceType">
							<option value="-1">--请选择--</option>
							<c:forEach items="${services}" var ="skillItem" varStatus="status">
								<option value="${skillItem.id}">${skillItem.skillItemName}</option>
							</c:forEach>
						</select>
						<%--<input type="text" id="serviceType" class="form-control"  name="serviceType">--%>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label">折扣类型<font color="red">&nbsp;*</font></label>
					<div class="col-sm-6">
						<select class="form-control" name="discountType" id="discountType">
							<option value="-1">--请选择--</option>
							<option value="10">无折扣</option>
							<option value="1">10%</option>
							<option value="2">20%</option>
							<option value="3">30%</option>
							<option value="4">40%</option>
							<option value="5">50%</option>
							<option value="6">60%</option>
							<option value="7">70%</option>
							<option value="8">80%</option>
							<option value="9">90%</option>
						</select>
						<%--<input type="text" id="discountType" class="form-control"  name="discountType">--%>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label">数量<font color="red">&nbsp;*</font></label>
					<div class="col-sm-6">
						<input type="text" id="orderNum" class="form-control"  name="orderNum">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label">订单单价<font color="red">&nbsp;*</font></label>
					<div class="col-sm-6">
						<input type="text" id="orderAmount" class="form-control"  name="orderAmount">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label">订单状态<font color="red">&nbsp;*</font></label>
					<div class="col-sm-6">
						<select class="form-control" name="orderStatus" id="orderStatus">
							<option value="-1">--请选择--</option>
							<c:forEach items="${smallStatus}" var ="smallOrderStatusVO" varStatus="status">
								<option value="${smallOrderStatusVO.value}">${smallOrderStatusVO.desc}</option>
							</c:forEach>
						</select>
						<%--<input type="text" id="orderStatus" class="form-control"  name="orderStatus">--%>
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