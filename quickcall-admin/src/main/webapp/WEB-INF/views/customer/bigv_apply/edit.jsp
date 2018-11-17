<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="modal-dialog" style="width: 50%;">
	<div class="modal-content">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal" aria-hidden="true">X</button>
			<h3 id="myModalLabel">修改声优申请</h3>
		</div>
		<div class="modal-body">
			<form class="form-horizontal" method="post" id="fadeCustomerForm" name="fadeCustomerForm"
				action="customerApplyBigv/saveUpdate.htm"
				role="form">
				<div class="form-group">
					<label class="col-sm-2 control-label">用户ID</label>
					<div class="col-sm-10">
						<input type="hidden" value="${entity.applyId }" name="applyId" />
						<label class="control-label">${entity.customerId }</label>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">用户昵称</label>
					<div class="col-sm-10">
						<label class="control-label">${entity.nickName }</label>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">手机号</label>
					<div class="col-sm-10">
						<label class="control-label">${entity.phone }</label>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">联系状态</label>
					<div class="col-sm-10">
						<c:choose>
							<c:when test="${entity.handleStatus == 0}">
								<input type="hidden" value="${entity.handleStatus }" name="oldHandleStatus" />
								<label class="radio-inline">
									<input type="radio" name="handleStatus" id="unconnect" value="0" checked />未联系
								</label>
								<label class="radio-inline">
									<input type="radio" name="handleStatus" id="connected" value="1" />已联系
								</label>
							</c:when>
							<c:otherwise>
								<label class="radio-inline">
									<input type="radio" name="handleStatus" id="unconnect" value="0" disabled />未联系
								</label>
								<label class="radio-inline">
									<input type="radio" name="handleStatus" id="connected" value="1" checked disabled />已联系
								</label>
							</c:otherwise>
						</c:choose>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">备注</label>
					<div class="col-sm-10">
						<input type="text" class="form-control" name="remark" value="${entity.remark }">
					</div>
				</div>
				<span id="tip" style="color: red;font-size: 14px;margin-left:20px; "></span>
			</form>
		</div>
		
		<div class="modal-footer">
			<button class="btn btn-default" data-dismiss="modal" aria-hidden="true">取消</button>
			<button class="btn btn-primary" data-dismiss="modal">保存</button>
		</div>

	</div>
</div>
<script type="text/javascript">
    function check_fun() {
        return true;
    }
</script>