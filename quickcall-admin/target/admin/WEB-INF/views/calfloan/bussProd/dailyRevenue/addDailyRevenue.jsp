<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<link type="text/css"  href="resources/bootstrap/css/bootstrap-datetimepicker.min.css"   rel="stylesheet" />
<script type="text/javascript" language="javascript"  src="resources/bootstrap/js/bootstrap-datetimepicker.min.js"  charset="utf-8"></script>
	<script type="text/javascript" language="javascript"
	src="resources/bootstrap/js/bootstrap-datetimepicker.min.js"
	charset="utf-8"></script>
	
	
<div class="modal-dialog">
	<div class="modal-content">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal"
				aria-hidden="true">X</button>
			<h3 id="myModalLabel">新增对账信息</h3>
		</div>
		
		<div class="modal-body">
			<form class="form-horizontal" method="post" id="productLabelForm" name="productLabelForm"
				action="dailyRevenue/save.htm"
				role="form">
				<div class="form-group">
					<label class="col-sm-4 control-label">公司名称<font color="red">&nbsp;*</font></label>
					<div class="col-sm-8">
						<input type="text"  class="form-control"  readonly="readonly"   id="companyName" name="companyName" value="">
					</div>
				</div>
				<div class="form-group"   id = "productNameSelect">
					<label class="col-sm-4 control-label">商户名称<font color="red">&nbsp;*</font></label>
					<div class="col-sm-8">
						<!-- 页面展示商户名称，实际传送的是商户ID -->
					    <input type="hidden" id="productName" class="form-control"  name="prodId" value="">
                        <select  class="form-control"  style="width: 100%;float: right;display: inline-block;" onclick="setInputValue()" id = "productSelect"></select>
                        <input   class="form-control"  style="width: 96%;float: left;display: inline-block;position: relative;bottom: 34px;"  id="input" name="input"  onkeyup="getProductValue(this.value)" value = "--全部--">
                        <input   class="form-control"  id="inputProductId" name="inputProductId" type="hidden" value = "">
					</div>
				</div>
				
				
				<div class="form-group">
					<label class="col-sm-4 control-label">对账日期<font color="red">&nbsp;*</font></label>
					<div class="col-sm-8">
						<input type="text"   class="form-control" id="tallyDate" name="tallyDateStr" value="">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-4 control-label">CPA<font color="red">&nbsp;*</font></label>
					<div class="col-sm-8">
						<input type="text"  class="form-control" id="cpa" name="cpa" onchange="checkAmount('cpa')" value="">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-4 control-label">注册数</font></label>
					<div class="col-sm-8">
						<input type="text"  class="form-control" id="registCount" name="registCount" onchange="checkAmount('registCount')" value="">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-4 control-label">点击UV</label>
					<div class="col-sm-8">
						<input type="text"  class="form-control" id="uv" name="uv" onchange="checkAmount('uv')" value="">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-4 control-label">转化率</label>
					<div class="col-sm-8">
						<input type="text" readonly="readonly" class="form-control" id="percent" name="percent" value="">(单位百分号%)
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-4 control-label">对账金额</label>
					<div class="col-sm-8">
						<input type="text"   readonly="readonly" class="form-control" id="tallyAmount" name="tallyAmount" value="">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-4 control-label">期望注册量</label>
					<div class="col-sm-8">
						<input type="text"  class="form-control" id="expectCount" name="expectCount" value="">
					</div>
					<shiro:hasPermission name="dailyRevenue:upload">
						<input type="hidden" value="1" id="isAdmin" name = "isAdmin"/>
					</shiro:hasPermission>
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
    	var productSelect = $("#productSelect").val();
		$("#productName").val(productSelect);
    	var  companyName = $("#companyName").val();
    	var  productName = $("#productName").val();
    	var  tallyDate = $("#tallyDate").val();
    	var  cpa = $("#cpa").val();
    	var  uv = $("#uv").val();
    	
    	var  expectCount = $("#expectCount").val();
    	
    	var ret =  true ;
    	
    	if(companyName == null || companyName.trim() == ''){
    		$("#tip").html("请选择公司名称");
    		ret = false;
    	}else  if(productName == null || productName.trim() == ''){
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
    	/* if(ret){
	    	alert("========验证通过，可以提交========");
    	}
    	return false ; */
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
    
    function  changeAmount(){
    	var tallyAmount=$("#cpa").val()*$("#registCount").val();
    	$("#tallyAmount").val(tallyAmount);
    	if($("#uv").val()<0){
    		alter("uv输入有误");
    	}else{
	    	var  percent=($("#registCount").val()/$("#uv").val()).toFixed(2)*100;
	    	$("#percent").val(percent);
    	}
    }
    
	function getProductValue(data){
		$("#productSelect").empty();
        var str = "<option value=\" \">--全部--</option>";
        <c:forEach var="info" items="${prodNameList }">
            var proIdNew  = "${info.productId }";
            var proNameNew = "${info.productName }"
            // 将包含输入文字的下拉框填充进下拉列表
            if (proNameNew.indexOf(data) != -1){
                str = str + "<option value=\""+proIdNew+"\">"+proNameNew+"</option>";
            }
            // 如果输入文字后没有出发下拉框的选择事件，则会使用到此处的商户ID
            if (data == proNameNew) {
                document.getElementById("inputProductId").value = proIdNew;
            }
            // 输入空的时候查询所有
            if (data == ""){
                $("#inputProductId").val("");
            }
        </c:forEach>
        $("#productSelect").html(str);
        $("#productSelect").attr("onclick","setInputValue()");
    }
	
	function setInputValue(){
    	$("#productSelect").attr("onclick","showValue()");
	}
	function showValue(){
	    var index = document.getElementById("productSelect").selectedIndex;
	    var text = document.getElementById("productSelect").options[index].text;
	    $("#input").val(text);
        $("#inputProductId").val("");
        // 刷新下拉列表
        if (text == '--全部--') {
        	$("#companyName").val("");
        	$("#productSelect").empty();
            var selectStrNew = "<option value=\" \">--全部--</option>";
            <c:forEach var="info" items="${prodNameList }">
	            var proIdNew  = "${info.productId }";
	            var proNameNew = "${info.productName }";
	            selectStrNew = selectStrNew + "<option value=\""+proIdNew+"\">"+proNameNew+"</option>";
            </c:forEach>
            $("#productSelect").html(selectStrNew);
        }else{
		    //////////////////////////////////////////////////////////////////////////////////////
		    <c:forEach var="info" items="${prodNameList }">
		        var productIdSelect = $("#productSelect").val();
		        var  productId =  "${info.productId }"; 
		        if(productId == productIdSelect){
		           var  companyName = "${info.companyName }";
		           $("#companyName").val(companyName);
		           return;
		        }
		    </c:forEach>
		    //////////////////////////////////////////////////////////////////////////////////////
        }
    }
    
    
     $(function() {
    	var currDate =  new Date();
    	currDate =  new Date(currDate.getTime() - 86400000);
 		var currTimeStr = currDate.Format("yyyy-MM-dd");
 		//对账日期默认为昨天
 		$('#tallyDate').val(currTimeStr);
    	$('#tallyDate').datetimepicker({
   			format : 'yyyy-mm-dd'
   		});
    	 ////////////////////模糊查询///////////////////////////
 	    $("#productSelect").empty();
            var selectStr = "<option value=\" \">--全部--</option>";
            <c:forEach var="info" items="${prodNameList }">
             var proId  = "${info.productId }";
             var proName = "${info.productName }"
             selectStr = selectStr + "<option value=\""+proId+"\">"+proName+"</option>";
            </c:forEach>
            $("#productSelect").html(selectStr);
 		////////////////////模糊查询///////////////////////////
    }) 
	
</script>