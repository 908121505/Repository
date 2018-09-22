<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<link type="text/css"  href="resources/bootstrap/css/bootstrap-datetimepicker.min.css" rel="stylesheet" />
<script type="text/javascript" language="javascript"  src="resources/bootstrap/js/bootstrap-datetimepicker.min.js"  charset="utf-8"></script>


<div class="modal-dialog">
	<div class="modal-content">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal"
				aria-hidden="true">X</button>
			<h3 id="myModalLabel">新增打款详情</h3>
		</div>
		<div class="modal-body" style="max-height: 700px; overflow-y: auto;"  id = "editBody">
			<form class="form-horizontal" method="post" action="companySettle/saveInsert.htm"  role="form">
					<div class="form-group">
						<label class="col-sm-4 control-label">公司名称<font  color="red">&nbsp;*</font></label>
						<div class="col-sm-8">
							<input type="text" class="form-control" id="companyName"  value=""  onchange="verifyCompanyName(this,1)"  name= "companyName">
							<input type="hidden" class="form-control" id="checkFlag"  value="0"  >
							<span id="checkCompanyName" style="color: red;font-size: 14px;margin-left:20px; "></span>
						</div>
					</div>
					
					<div class="form-group">
						<label class="col-sm-4 control-label">打款金额/元<font  color="red">&nbsp;*</font></label>
						<div class="col-sm-8">
							<input type="text" class="form-control" id="payAmount" value=""  name= "payAmount">
							<span id="checkPayAmount" style="color: red;font-size: 14px;margin-left:20px; "></span>
						</div>
					</div>
					
					<div class="form-group">
						<label class="col-sm-4 control-label">打款人<font  color="red">&nbsp;*</font></label> 
						<div class="col-sm-8">
							<input type="text" class="form-control" id="payerName" value=""  name = "payerName" onchange="verifyPayerName(this)">
							<span id="checkPayerName" style="color: red;font-size: 14px;margin-left:20px; "></span>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-4 control-label">打款时间<font  color="red">&nbsp;*</font></label>
						<div class="col-sm-8">
							<input type="text" class="form-control" id="payDate" value=""  name = "payDateStr">
						</div>
					</div>
			</form>
		</div>
		<div class="modal-footer">
		    <span id="tip" style="color: red; font-size: 16px; margin-left: 20px;float:left;"></span>
			<button class="btn btn-default" data-dismiss="modal" aria-hidden="true" >返回</button>
			<button class="btn btn-primary" data-dismiss="modal" >保存</button>
		</div>
	</div>
</div>
<script type="text/javascript" charset="utf-8">



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

	function check_fun() {
		$("#tip").html("");
		var  companyName = $("#companyName").val();
		var  payerName = $("#payerName").val();
		var payAmount = $("#payAmount").val();
		var payDateStr = $("#payDate").val();
		
		var r =  true;
		if(companyName == null  || companyName.trim() == ''){
			$("#tip").html("请选择公司名称");
			r =  false;
		}else if(payerName == null  || payerName.trim() == ''){
			$("#tip").html("请选择打款人");
			r =  false;
		}else if(payAmount == null  || payAmount.trim() == ''){
			$("#tip").html("请选择打款金额");
			r =  false;
		}else if(payDateStr == null  || payDateStr.trim() == ''){
			$("#tip").html("请选择打款日期");
			r =  false;
		}
		
		if(r){
			//校验输入的打款金额是否是整数
			if(isNotNumExt(payAmount)){
				r =  false;
				$("#tip").html("打款金额只能是整数");
			}
		}
		
		if(r){
			var currDate =  new Date();
			var currTimeStr = currDate.Format("yyyy-MM-dd HH:mm:ss");
			//当前时间
			var currTime = getDate(currTimeStr);
			var payDate = getDate(payDateStr);
			if(payDate > currTime){
				r =  false;
				$("#tip").html("打款时间不能早于当前时间");
			}
		}
		
		if(r){
			var  checkFlag = $("#checkFlag").val();
			if("1" ==  checkFlag){
				$("#tip").html("公司打款人不匹配");
				r = false;
			}
		}
		
		
		return r ;
	    
	}

    
	//字符串转日期格式，strDate要转为日期格式的字符串
    function getDate(strDate) {
        var st = strDate;
        var a = st.split(" ");
        var b = a[0].split("-");
        var c = a[1].split(":");
        var date = new Date(b[0], (b[1]-1), b[2], c[0], c[1], c[2]);
        return date;
    }
	//整数
 	function isNotNumExt (numStr){
		var r = /^\+?[1-9][0-9]*$/;
		return !r.test(numStr);
	} 


	function verifyPayerName(obj){
		$("#checkPayerName").text("");
		$("#checkCompanyName").text("");
		//公司名称
		var  companyName = $("#companyName").val();
		if(companyName == null ||  companyName.trim() == ''){
			$("#checkCompanyName").text("请输入公司名称");
			return ;
		}
		//打款人姓名
		var  payerName =  $(obj).val();
		if(payerName == null || payerName.trim() ==''){
			$("#checkPayerName").text("请输入打款人姓名");
			return ;
		}
		
		$.ajax({
	         type: "post",
	         dataType: "json",
	         url: "companySettle/verifyCompanyPayer",
	         async:false,
	         data: {
	             "payerName": payerName,
	             "companyName":companyName
	         },
	         error: function () {
	             alert("错误！");
	         },
	         success: function (data) {
	             var result =  data.result ;
	             if(result == 0){
	            	$("#checkPayerName").text("打款人"+payerName +"不属于公司："+companyName);
	            	$("#checkFlag").val("1");
	             }else{
	            	 $("#checkFlag").val("0");
	             }
	         }
	     });
	}
	
	
	
    function verifyCompanyName(obj,index){
    	$("#checkCompanyName").text("");
    	var item = $(obj).val();
    	if(item == null || item.trim() == ''){
    		return  ;
    	}
    	$.ajax({
             type: "post",
             dataType: "json",
             url: "bussProduct/verify",
             async:false,
             data: {
                 "item": item,
                 "index":index
             },
             error: function () {
                 alert("错误！");
             },
             success: function (data) {
                 var result =  data.result ;
                 if(result == 0){
                	$("#companyName").val("");
                	$("#checkCompanyName").text("公司："+item +"不存在");
                 }
             }
         });
    }
    
    
    
    
    
    $(function(){
		$('#payDate').datetimepicker({
			format : 'yyyy-mm-dd hh:ii:ss'
		});
    })
    
    

</script>
