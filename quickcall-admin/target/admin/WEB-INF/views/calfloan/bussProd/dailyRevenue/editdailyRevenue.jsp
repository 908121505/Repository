<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<link type="text/css"  href="resources/bootstrap/css/bootstrap-datetimepicker.min.css"   rel="stylesheet" />
<script type="text/javascript" language="javascript"  src="resources/bootstrap/js/bootstrap-datetimepicker.min.js"  charset="utf-8"></script>
<div class="modal-dialog">
	<div class="modal-content">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal"
				aria-hidden="true">X</button>
			<h3 id="myModalLabel">编辑对账信息</h3>
		</div>
		<div class="modal-body">
			<form class="form-horizontal" method="post" id="productLabelForm" name="productLabelForm"
				action="dailyRevenue/saveUpdate.htm"
				role="form">
		<input type="hidden" readonly="readonly"   class="form-control" id="id" name="id" value="${entity.id}">		
				
				<div class="form-group">
					<label class="col-sm-4 control-label">公司名称<font color="red">&nbsp;*</font></label>
					<div class="col-sm-8">
						<input type="text" readonly="readonly" id= "companyName" class="form-control" value="${entity.companyName}">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-4 control-label">商户名称<font color="red">&nbsp;*</font></label>
					<div class="col-sm-8">
						<input type="text" readonly="readonly" id= "productName" class="form-control" id="weight" name="companyName" value="${entity.productName}">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-4 control-label">对账日期<font color="red">&nbsp;*</font></label>
					<div class="col-sm-8">
						<input type="text"  id= "tallyTime"  class="form-control" name="tallyTime" value="${entity.tallyTime}">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-4 control-label">CPA<font color="red">&nbsp;*</font></label>
					<div class="col-sm-8">
						<input type="text"  class="form-control" id="cpa" name="cpa" onchange="checkAmount('cpa')" value="${entity.cpa}">
					</div>
				</div>
			
				<div class="form-group">
					<label class="col-sm-4 control-label">注册数</label>
					<div class="col-sm-8">
						<input type="text"  class="form-control" id="registCount" name="registCount" onchange="checkAmount('registCount')" value="${entity.registCount}">
					</div>
				</div>
			
				<div class="form-group">
					<label class="col-sm-4 control-label">点击UV</label>
					<div class="col-sm-8">
						<input type="text"  class="form-control" id="uv" name="uv" onchange="checkAmount('uv')" value="${entity.uv}">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-4 control-label">转化率</label>
					<div class="col-sm-8">
						<input type="text" readonly="readonly" class="form-control" id="percent" name="percent" value="${entity.percent}">(单位百分号%)
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-4 control-label">对账金额</label>
					<div class="col-sm-8">
						<input type="text" readonly="readonly" class="form-control" id="tallyAmount" name="tallyAmount" value="${entity.tallyAmount}">
					</div>
				</div>
				
				<div class="form-group">
					<label class="col-sm-4 control-label">期望注册量</label>
					<div class="col-sm-8">
						<input type="text"  class="form-control" id="expectCount" name="expectCount" value="${entity.expectCount}">
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

Date.prototype.Format = function (fmt) { //author: meizz   
    var o = {  
        "M+": this.getMonth() + 1, //月份   
        "d+": this.getDate(), //日   
        "H+": this.getHours(), //小时   
        "m+": this.getMinutes(), //分   
        "s+": this.getSeconds(), //秒   
        "q+": Math.floor((this.getMonth() + 3) / 3),
        "S": this.getMilliseconds()
    };  
    if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));  
    for (var k in o)  
    if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));  
    return fmt;  
} 
	//整数
	function isNotNumExt (numStr){
		if(numStr == 0){
			return false ;
		}
		var r = /^\+?[1-9][0-9]*$/;
		return !r.test(numStr);
	} 
	//包含小数点
	function isNotNum (numStr){
		var r = /^(([1-9]\d*)(\.\d{1,2})?|0\.([1-9]|\d[1-9])|0)$/;
		return !r.test(numStr);
	}
	function checkAmount(name){
	   $("#tip").html("");
	   var  cpa = $("#cpa").val();
	   var  registCount = $("#registCount").val();
	   var  uv = $("#uv").val();
	   if(name ==  'cpa'){
		   if(isNotNum(cpa)){
			   $("#cpa").val("");
			   $("#tip").html("CPA只能是整数或者小数");
		   }else{
			   //计算对账金额
			   if(registCount == null ||registCount.trim() == ''){
				   $("#tallyAmount").val("");
			   }else{
				   var tallyAmount = registCount*cpa;
				   $("#tallyAmount").val(tallyAmount);
			   }
		   }
	   }else if(name ==  'registCount'){
		  
		   if(isNotNumExt(registCount)){
			   $("#registCount").val("");
			   $("#tip").html("注册数只能是整数");
		   }else{
			   
			   
			   //计算转化率
			   if(uv == null || uv.trim() == ''){
				   $("#percent").val("");
			   }else{
				   if(uv == 0){
				  	 	$("#percent").val("");
				   }else{
					   var percent = (registCount/uv)*100;
					   $("#percent").val(new Number(percent).toFixed(2));
				   }
			   }
			   
			   //计算对账金额
			   if(cpa == null || cpa.trim() == ''){
				   $("#tallyAmount").val("");
			   }else{
				   if(cpa == 0){
				  	 	$("#tallyAmount").val("");
				   }else{
					   var tallyAmount = registCount*cpa;
					   $("#tallyAmount").val(tallyAmount);
				   }
			   }
		   }
	   }else if(name ==  'uv'){
		  
		   if(isNotNumExt(uv)){
			   $("#uv").val("");
			   $("#tip").html("点击UV只能是整数");
		   }else{
			   //计算转化率
			   if(registCount == null || registCount.trim() == ''){
				   $("#percent").val("");
			   }else{
				   if(uv == 0){
					   $("#percent").val("");
				   }else{
					   var percent = (registCount/uv)*100;
					   $("#percent").val(new Number(percent).toFixed(2));
				   }
			   }
		   }
	   }
	}
    function check_fun(){
    	var  companyName = $("#companyName").val();
    	var  productName = $("#productName").val();
    	var  tallyDate = $("#tallyTime").val();
    	var  cpa = $("#cpa").val();
    	var  uv = $("#uv").val();
    	var  expectCount = $("#expectCount").val();
    	
    	var ret =  true ;
    	
    	if(companyName == null || companyName.trim() == ''){
    		$("#tip").html("请选择公司名称");
    		ret = false;
    	}else   if(productName == null || productName.trim() == ''){
    		$("#tip").html("请选择供商户名称");
    		ret = false;
    	}else  if(tallyDate == null || tallyDate.trim() == ''){
    		$("#tip").html("请选择对账日期");
    		ret = false;
    	}else  if(cpa == null || cpa.trim() == ''){
    		$("#tip").html("请选择供cpa");
    		ret = false;
    	}
    	
    	if(expectCount == null || expectCount.trim() == ''){
    	}else{
    		if(isNotNumExt(expectCount)){
    			$("#tip").html("期望注册量只能是整数");
        		ret = false;
    		}
    	}
    	
    	//对账日期只能是昨天或者昨天之前日期
    	var currDate =  new Date();
		var currTimeStr = currDate.Format("yyyy-MM-dd HH:mm:ss");
		var currTime = getDate(currTimeStr);
    	var tallyDateTime = getDate(tallyDate);
    	if(tallyDateTime >= currTime){
    		$("#tip").html("对账日期不能早于今天");
    		ret = false;
    	}
    	return ret;
    }
    
	  //字符串转日期格式，strDate要转为日期格式的字符串
    function getDate(strDate) {
        var st = strDate;
        var a = st.split(" ");
        var b = a[0].split("-");
        var date = new Date(b[0], (b[1] - 1), b[2], 0, 0, 0)
        return date;
    }
    
    $(function() {
    	$('#tallyTime').datetimepicker({
   			format : 'yyyy-mm-dd'
   		});
    }) 
</script>