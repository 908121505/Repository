<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<link type="text/css" href="resources/bootstrap/css/bootstrap-datetimepicker.min.css" rel="stylesheet" />
<script type="text/javascript" language="javascript" src="resources/bootstrap/js/bootstrap-datetimepicker.min.js" charset="utf-8"></script>

<head>

<style type="text/css">
    .row  div{
       padding-right:4px;
       padding-left:0px;
    }
     .form-horizontal .control-label {
   		 text-align: center;
	} 

</style>

</head>
<div class="modal-dialog"  style="width: 82%;">
	<div class="modal-content">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal"  id = "closePage" onclick= "closePage()"
				aria-hidden="true">X</button>
			<h3 id="myModalLabel">活动对账</h3>
		</div>
		<div class="modal-body" style="max-height:1000px;overflow-y:auto;">
			<form class="form-horizontal" method="post" id="productForm" name="tallyForm"  action="spread/tally.htm"  role="form">
				<c:if test="${not  empty  infoList }">
					<!-- 遍历返回参数-->
					<c:forEach var="info" items="${infoList }">
						<div  class = "row"  style="margin-bottom: 10px;">
						
						<div class="col-sm-1"   style="width:15%;">
								<div class="col-sm-12" >
									<c:if test="${not empty info.companyName}">
										<label class="col-sm-5 control-label">公司名称</label>
									</c:if>		
									<div class="col-sm-7">
										<c:if test="${not empty info.companyName}">
											<input type="text" id="productName" readonly="readonly" style="height:28px;border-radius:0px;" class="form-control" name="productName"
												value="${info.companyName}">
										</c:if>		
									</div>
								</div>
							</div>
							<div class="col-sm-1"   style="width:15%;">
								<div class="col-sm-12" >
									<c:if test="${not empty info.productName}">
										<label class="col-sm-5 control-label">商户名称</label>
									</c:if>		
									<div class="col-sm-7">
										<c:if test="${not empty info.productName}">
											<input type="text" id="productName" readonly="readonly" style="height:28px;border-radius:0px;" class="form-control" name="productName"
												value="${info.productName}">
										</c:if>		
										<input type="text" id="productId"  style="display: none;" class="form-control" name="productId"
											value="${info.productId}">
										<input type="text" id="companyId"  style="display: none;" class="form-control" name="companyId"
											value="${info.companyId}">
									</div>
								</div>
							</div>
							
							
							
							<!-- <div class="col-sm-10"> -->
								<div class="col-sm-1"  style="width:14%;">
									<label class="col-sm-5 control-label">对账日期</label>
									<div class="col-sm-7">
										<input type="text" id="tallyDate"  class="form-control" style="height:28px;border-radius:0px;" name="tallyDate"  readonly="readonly"
											value="${info.tallyDate}">
									</div>
								</div>
								
								<div class="col-sm-1"  style="width:10%;">
									<label class="col-sm-5 control-label">CPA</label>
									<div class="col-sm-7">
										<input type="text" id="cpa"  class="form-control"    style="height:28px;border-radius:0px;"  name="cpa"
											value="0"  onchange="sumAmount(this)">
									</div>
								</div>
								<div class="col-sm-1"  style="width:11%;">
									<label class="col-sm-6 control-label">注册数</label>
									<div class="col-sm-6">
										<input type="text" id="registerCount"  class="form-control"  style="height:28px;border-radius:0px;"  name="registerCount"
											value="0"    onchange="sumAmount(this)">
									</div>
								</div>
								
								
								<div class="col-sm-1"  style="width:11%;">
									<label class="col-sm-6 control-label">点击UV</label>
									<div class="col-sm-6">
										<input type="text" id="uv"  class="form-control"    style="height:28px;border-radius:0px;"  name="uv"
											value="0"  onchange="sumAmount(this)">
									</div>
								</div>
								<div class="col-sm-1"  style="width:11%;">
									<label class="col-sm-6 control-label">转化率</label>
									<div class="col-sm-6">
										<input type="text" id="percent"  name= "percent" class="form-control" readonly="readonly" style="height:28px;border-radius:0px;"  value="0" >
									</div>
								</div>
								
								
								<div class="col-sm-1"  style="width:12%;">
									<label class="col-sm-6 control-label">对账金额</label>
									<div class="col-sm-6">
											<span  class="form-control">0</span>
									</div>
								</div>
							<!-- </div> -->
						</div>
					</c:forEach>
				</c:if>
				<c:if test="${empty  infoList }">
					<div class="col-sm-12" >
						<label class="col-sm-6 control-label" style="font-size: 18px;font-weight: bold;color: red;">没有需要对账数据</label>
					</div>
				
				</c:if>
				
				
			</form>
			
			
		</div>
		<div class="modal-footer">
			<button class="btn btn-default" data-dismiss="modal"
				aria-hidden="true">取消</button>
			<button class="btn btn-primary" data-dismiss="modal" id = "saveBtn">保存</button>
		</div>
	</div>
</div>

<script type="text/javascript">

	
	function closePage() {
		$("#closePage").css({display : "none"});
	}

	
	function  sumAmount(obj){
		var r = /^\+?[1-9][0-9]*$/;
		var  tmp  = $(obj);
		var  $name =  tmp.attr("name");
		var  currVal = tmp.val() ;
		
		if("cpa" != $name){
			if(currVal == null || currVal.trim() == '' || isNotNumExt(currVal)){
				currVal = 0;
				tmp.val("0");
			}
		}else{
			if(currVal == null || currVal.trim() == '' || isNotNum(currVal)){
				currVal = 0;
				tmp.val("0");
			}
		}
		
		var $parent = tmp.parent().parent();
		if("cpa" == $name){
			var otherVal =  $parent.next().find("input").eq(0).val();
			if(otherVal == null || otherVal.trim() == '' || isNotNum(otherVal)){
				otherVal = 0;
				$parent.next().find("input").eq(0).val("0");
			}
			var  sumcount =  currVal * otherVal;
			$parent.siblings().find("span").text(sumcount);
		}else if("registerCount" == $name){
			var otherVal =  $parent.prev().find("input").eq(0).val();
			if(otherVal == null || otherVal.trim() == '' || isNotNum(otherVal)){
				otherVal = 0;
				 $parent.prev().find("input").eq(0).val("0");
			}
			var  sumcount =  currVal * otherVal;
			$parent.siblings().find("span").text(sumcount);
			var  uvTmp  =  $parent.next().find("input").eq(0).val();
			if(uvTmp == null || uvTmp.trim() == '' || isNotNum(uvTmp)){
				uvTmp = 0;
				$parent.next().find("input").eq(0).val("0");
			}
			if(uvTmp == 0){
				//var  sumcount =  (currVal / uvTmp) * 100;
			    var  sumCountStr =  "0.0%";
			    $parent.siblings().find("#percent").val(sumCountStr);
			}else{
				var  sumcount =  (currVal / uvTmp) * 100;
			    var  sumCountStr = new Number(sumcount).toFixed(2) + "%";
			    $parent.siblings().find("#percent").val(sumCountStr);
			}
			
			
			
		}else if("uv" == $name){
			var otherVal =  $parent.prev().find("input").eq(0).val();
			if(otherVal == null || otherVal.trim() == '' || isNotNum(otherVal)){
				otherVal = 0;
				 $parent.prev().find("input").eq(0).val("0");
			}
			if(currVal == 0){
				var sumCountStr = "0.0%";
				$parent.siblings().find("#percent").val(sumCountStr);
			}else{
				var  sumcount =  (otherVal / currVal) * 100;
			    var  sumCountStr = new Number(sumcount).toFixed(2) + "%";
			    $parent.siblings().find("#percent").val(sumCountStr);
			}
			
		}
	}
	
	//整数
 	function isNotNumExt (numStr){
		var r = /^\+?[1-9][0-9]*$/;
		return !r.test(numStr);
	} 
	//包含小数点
	function isNotNum (numStr){
		var r = /^(([1-9]\d*)(\.\d{1,2})?|0\.([1-9]|\d[1-9])|0)$/;
		return !r.test(numStr);
	}
	
	
	
	function check_fun() {
		var b = true;
		return b;
	}
	
	
	$(function() {
		var  showFlag =  "${showFlag}";
		if(showFlag == '0' || showFlag == 0){
			 $("#saveBtn").hide();
		}
	});
	

</script>