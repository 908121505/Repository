<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<div class="modal-dialog">
	<div class="modal-content">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal"
				aria-hidden="true">X</button>
			<h3 id="myModalLabel">入驻商户详情</h3>
		</div>
		<div class="modal-body">
			<form class="form-horizontal" method="post" id="productLabelForm" name="productLabelForm"
				action="bussProductDetail/saveUpdate.htm"
				role="form">
		<input type="hidden" readonly="readonly"   class="form-control" id="id" name="id" value="${entity.id}">		
				<div class="form-group">
					<label class="col-sm-2 control-label">商户名称</label>
					<div class="col-sm-10">
					  
						<input type="text" readonly="readonly"  class="form-control" id="label_name" name="productName1" value="${entity.productName}">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">公司名称</label>
					<div class="col-sm-10">
						<input type="text" readonly="readonly" class="form-control" id="weight" name="companyName1" value="${entity.companyName}">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">公司性质</label>
					<div class="col-sm-10">
						<input type="text" readonly="readonly" class="form-control" id="weight" name="companyNature1" value="${entity.companyNature}">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">合作模式</label>
					<div class="col-sm-10">
						<input type="text" readonly="readonly" class="form-control" id="weight" name="cooperModel1" value="${entity.cooperModel}">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">结算周期</label>
					<div class="col-sm-10">
						<input type="text" readonly="readonly" class="form-control" id="weight" name="settleDate1" value="${entity.settleDate}">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">单价/元</label>
					<div class="col-sm-10">
						<input type="text" readonly="readonly" class="form-control" id="weight" name="cpa1" value="${entity.cpa}">
					</div>
				</div>
				
                   <%-- <div class="form-group">
					<label class="col-sm-2 control-label">上线时间</label>
					<div class="col-sm-10">
						<input class="form-control" readonly="readonly" type="text" id="onlineTime1" value="${entity.onlineTime }">
					</div>
				</div>
                    <div class="form-group">
					<label class="col-sm-2 control-label">下线时间</label>
					<div class="col-sm-10">
						<input class="form-control" readonly="readonly" type="text" id="offlineTime1" value="${entity.offlineTime }">
					</div>
				</div> --%>
                    <div class="form-group">
					<label class="col-sm-2 control-label">上线时长</label>
					<div class="col-sm-10">
						<input type="text" class="form-control"  readonly="readonly" id="weight" name="onlineDate1" value="${entity.onlineDate}">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">上次打款时间</label>
					<div class="col-sm-10">
						<input class="form-control" readonly="readonly" type="text" id="daylyDate1" value="${entity.daylyDate }">
					</div>
				</div>
                   <div class="form-group">
					<label class="col-sm-2 control-label">打款时长</label>
					<div class="col-sm-10">
						<input type="text"  readonly="readonly" class="form-control" id="daylyTime" name="daylyTime1" value="${entity.daylyTime==-1?'当天':entity.daylyTime}">
					</div>
					</div>
					
					 <div class="form-group">
					<label class="col-sm-2 control-label">累计收入</label>
					<div class="col-sm-10">
						<input type="text"  readonly="readonly" class="form-control" id="amountOfIncome" name="amountOfIncome1" value="${entity.amountOfIncome}">
					</div>
					</div>
					 <div class="form-group">
					<label class="col-sm-2 control-label">已结算收入</label>
					<div class="col-sm-10">
						<input type="text"  readonly="readonly" class="form-control" id="settledIncome" name="settledIncome1" value="${entity.settledIncome}">
					</div>
                </div>
                 <div class="form-group">
					<label class="col-sm-2 control-label">欠款金额</label>
					<div class="col-sm-10">
						<input type="text" readonly="readonly"  class="form-control" id="amountInArear" name="amountInArear1" value="${entity.amountInArear}">
					</div>
                </div>
                
                 <div class="form-group">
					<label class="col-sm-2 control-label">回款率</label>
					<div class="col-sm-10">
						<input type="text"  readonly="readonly" class="form-control" id="reimbursementRate" name="reimbursementRate1" value="${entity.reimbursementRate}%">
					</div>
                </div>
                
                 <div class="form-group">
					<label class="col-sm-2 control-label">备注</label>
					<div class="col-sm-10">
						<input type="text"   class="form-control" id="remark" name="remark" value="${entity.remark}">
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
    	return true;
    }
    
	  
	
	
</script>