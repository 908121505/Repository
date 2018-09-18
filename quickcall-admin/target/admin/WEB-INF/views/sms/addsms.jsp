<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="modal-dialog">
	<div class="modal-content">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal"
				aria-hidden="true">X</button>
			<h3 id="myModalLabel">${flag == '1'?'个推':'短信' }推送</h3>
		</div>
		<div class="modal-body">
			<form class="form-horizontal" method="post" id="sysUserForm"
				action="sms/saveAdd.htm" role="form">
				<div class="form-group">
					<label class="col-sm-2 control-label">发送对象</label>
					<div class="col-sm-10">
						<label class="checkbox-inline"> <input type="radio"
							name="sendType" value="1" checked onclick="clickSendType(1)">
							指定用户
						</label> <label class="checkbox-inline"> <input type="radio"
							name="sendType" value="2" onclick="clickSendType(2)">
							所有用户
						</label>
						<%-- <c:if test="${flag == 1 }">
							<label class="checkbox-inline"> <input type="radio"
								name="sendType" value="3" onclick="clickSendType(3)">
								IOS用户
							</label>
							<label class="checkbox-inline"> <input type="radio"
								name="sendType" value="4" onclick="clickSendType(4)">
								Android用户
							</label>
						</c:if> --%>
					</div>
				</div>
				<c:if test="${flag == 1 }">
					<div class="form-group">
						<label class="col-sm-2 control-label">标题<font color="red">&nbsp;*</font></label>
						<div class="col-sm-10">
							<input type="text" class="form-control" name="title"
								id="sms_title" value="">
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">内容<font color="red">&nbsp;*</font></label>
						<div class="col-sm-10">
							<input type="text" class="form-control" name="content"
								id="sms_content" value="">
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">链接</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" name="url" id="sms_url"
								value="">
						</div>
					</div>
				</c:if>
				
				
				
				<c:if test="${flag == 0 }">
				<!--  <div class="form-group" id="cellPhoneDiv" style="display: block;">
					<label class="col-sm-2 control-label">用户手机号码<font
						color="red">&nbsp;*</font></label>
					<div class="col-sm-10">
						<textarea id="smsPhones" name="smsPhones" value="" class="form-control"
							rows="6"
							placeholder="输入多个手机号时用英文逗号隔开。例如:13300000000,13200000000。一次发送不能大于50000个号码"></textarea>
					</div>
				</div>  -->
				</c:if>
				
				<div class="form-group" id="cellPhoneDiv" style="display: block;">
					<label class="col-sm-2 control-label">批量手机号上传<font
						color="red">&nbsp;*</font></label>
					<div class="col-sm-10">
					     <input type="file" id = "filePhone" name="fe"/>
					</div>
				</div>
				
				
				<c:if test="${flag == 0 }">
					<div class="form-group">
						<label class="col-sm-2 control-label">内容<font color="red">&nbsp;*</font></label>
						<div class="col-sm-10">

							<textarea name="smsContent" id="smsContent" class="form-control"
								rows="6" placeholder="必须是已经跟短信平台报备好的内容。不能超过536个字。"></textarea>
						</div>
					</div>
				</c:if>
				
				
				<c:if test="${flag == 1 }">
							
							
				<div class="form-group">
									<label class="col-sm-2 control-label">定时类型</label>
									<div class="col-sm-10">
										<label class="checkbox-inline"> <input type="radio" checked="checked" onchange="hidePushTime(2)"
											name="isTime" value="2" 
				
			>
											开启 
										</label> <label class="checkbox-inline"> <input type="radio" onchange="hidePushTime(1)"
											name="isTime" value="1" 
				
			>
											关闭
										</label>
									</div>
								</div>
				
				
				
					<div class="form-group" id="banner_startTime">
					<label class="col-sm-2 control-label" >推送时间<font color="red">&nbsp;*</font></label>
					<div class="col-sm-10">
						<input type="text" class="form-control"
							onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'1950-01-01'})"
							value="${entity.startTime }" id="timePushTime" name="timePushTime" />
					</div>
				</div>
			
				
				
				
					<div class="form-group">
									<label class="col-sm-2 control-label">通知类型</label>
									<div class="col-sm-10">
										<label class="checkbox-inline"> <input type="radio" checked="checked" onchange="hideSelect(2)"
											name="pushType" value="2" 
				                              
			>
											是
										</label> <label class="checkbox-inline"> <input type="radio"  onchange="hideSelect(1)"
											name="pushType" value="1" 
				
			>
											否
										</label>
									</div>
								</div>	
								
								
								<div class="form-group" id="specificType">
									<label class="col-sm-2 control-label"></label>
									<div class="col-sm-10">
										<label class="checkbox-inline"> <input type="radio" id="defaultSel" checked="checked" onchange="specificType(2)"
											name="target" value="1" 
				                              
			>
											产品详情
										</label> <label class="checkbox-inline"> <input type="radio"  onchange="specificType(1)"
											name="target" value="2" 
				
			>
											文章详情
										</label>
										
										<label class="checkbox-inline"> <input type="radio"  onchange="specificType(3)"
											name="target" value="3" 
				
			>
											活动详情
										</label>
									</div>
								</div>	
				
				
				
				
				
			<div class="form-group" id="productName">
								<label class="col-sm-4 control-label">产品名称<font 
			
			color="red">&nbsp;*</font></label>
								<div class="col-sm-8">
									<select class="form-control1" name="productId" id="productEleName" 	name="productCategoryId"
									style="LEFT: 0px; TOP: 0px; WIDTH: 175px; CLIP: rect(0px auto auto 80px); POSITION: absolute" onchange="changeValue(1,this);"
									>
										<c:forEach var="info" 	items="${productList }">
										  <option value="${info.productId }-${info.productName}-${info.isRecommend}">${info.productName }</option>
										</c:forEach>
									</select>
									<INPUT id="district1" onchange="inputChange(1,this);"   name="district" style="LEFT: 0px; TOP: 0px; WIDTH: 150px; POSITION: absolute">
								</div>
							</div>
				
				
				
					<div class="form-group" id="articletitle" style="display:none;"> 
								<label class="col-sm-4 control-label">文章主题<font 
			
			color="red">&nbsp;*</font></label>
								<div class="col-sm-8">
									<select class="form-control1" name="productId" id="articleEleName" 
			
			name="productCategoryId"    style="LEFT: 0px; TOP: 0px; WIDTH: 175px; CLIP: rect(0px auto auto 80px); POSITION: absolute" onchange="changeValue(2,this);">
											<c:forEach var="info" 
			
			items="${articleList }">
												<option 
			
			value="${info.articleId }-${info.articleTitle }">${info.articleTitle }</option>
											</c:forEach>
									</select>
									<INPUT id="district2" onchange="inputChange(2,this);" name="district" style="LEFT: 0px; TOP: 0px; WIDTH: 150px; POSITION: absolute">
								</div>
							</div>
							
							
				<div class="form-group" id="activitTitle" style="display:none;"> 
								<label class="col-sm-4 control-label">活动页面<font 
			
			color="red">&nbsp;*</font></label>
								<div class="col-sm-8">
								
									
									<SELECT name="productId" id="activitEleTitle" 
			name="productCategoryId" style="LEFT: 0px; TOP: 0px; WIDTH: 175px; CLIP: rect(0px auto auto 80px); POSITION: absolute" onchange="changeValue(3,this);">
                <c:forEach var="info" 
			
			items="${activityList }">
												<option   
			
			value="${info.bannerId }-${info.title }">${info.title }</option>
											</c:forEach>
            </SELECT>
            <INPUT id="district3" onchange="inputChange(3,this);"  name="district" style="LEFT: 0px; TOP: 0px; WIDTH: 150px; POSITION: absolute">
									
									
									
								</div>
							</div>
				
							
							
				</c:if>
				
				
				<%-- <c:if test="${flag == 1 }">
							<textarea name="content" id="smsContent" class="form-control"
								rows="6" placeholder="极光内容。"></textarea>
						</c:if> --%>

				<input type="hidden" value="${flag }" name="flag" /> <span id="tip"
					style="color: red; font-size: 14px; margin-left: 20px;"></span>
			</form>
		</div>
		<div class="modal-footer">
			<button class="btn btn-default" data-dismiss="modal"
				aria-hidden="true">取消</button>
			<button class="btn btn-primary" data-dismiss="modal">发送</button>
		</div>
	</div>
</div>

<script type="text/javascript"
	src="resources/js/My97DatePicker/WdatePicker.js" charset="utf-8"></script>
<script type="text/javascript">


function changeValue(num,obj){
	$("#district"+num).val(obj.options[obj.selectedIndex].innerText);
}

function  inputChange(num,obj){
	var selectId="";
	if(num==1){
		selectId="productEleName";
	}else if(num==2){
		selectId="articleEleName";
	}else if(num==3){
		selectId="activitEleTitle";
	}
	$.ajax({
		type:"POST",
		url: "sms/queryProduct.htm",
		data:{"proName":obj.value,"num"	:num},
		scriptCharset: 'utf-8' , 
		success: function(data){
			$("#"+selectId).empty();
			 var arr = JSON.parse(data);
		    if(num==1){
			for(var i=0;i<arr.length;i++){
				$("#"+selectId).append("<option value='"+arr[i].productId+"-"+arr[i].productName+"-"+arr[i].isRecommend+"'>"+arr[i].productName+"</option>");
			}  
		    }else if(num==2){
		    	for(var i=0;i<arr.length;i++){
		    	$("#"+selectId).append("<option value='"+arr[i].articleId+"-"+arr[i].articleTitle+"'>"+arr[i].articleTitle+"</option>");
		     }
		    }	
		    else if(num==3){
		    	for(var i=0;i<arr.length;i++){
		    	$("#"+selectId).append("<option value='"+arr[i].bannerId+"-"+arr[i].title+"'>"+arr[i].title+"</option>");
		    }
		    }
      }});
	
	
}





function specificType(val){
	
	if(1==val){
		  $("#productName").hide();
    	  $("#productName").val("");
    	  $("#articletitle").show();
    	  $("#activitTitle").hide();
    	  
    	  $("activitEleTitle").attr("disabled",true); 
    	  $("#productEleName").attr("disabled",true); 
    	  $("#articleEleName").attr("disabled",false); 
    	 
    	 
  	  
  	 }else if(2==val){
  		 $("#articletitle").hide();
   	     $("#articletitle").val("");
   	     $("#activitTitle").hide();
   	     $("#productName").show();
   	  
	     $("activitEleTitle").attr("disabled",true); 
   	     $("#articleEleName").attr("disabled",true); 
   	     $("#productEleName").attr("disabled",false); 
   	    
  	 }else{
  		 
  		 $("#productName").hide();
   	     $("#articletitle").hide();
   	     $("#activitTitle").show();
   	  
   	     $("activitEleTitle").attr("disabled",false); 
   	     $("#articleEleName").attr("disabled",true); 
   	     $("#productEleName").attr("disabled",true); 
   	     
   	   
   	  
   	     
  		 
  	 }
	
}


     function hidePushTime(val){
    	
    	 if(1==val){
    	  $("#banner_startTime").hide();
    	  $("#banner_startTime").val("");
    	  
    	 }else{
    		 $("#banner_startTime").show();
    	 }
     }
     
     function hideSelect(val){
     	
    	 if(1==val){
    	  $("#productName").hide();
    	  $("#productName").val("");
    	   $("#articletitle").hide();
    	  $("#articletitle").val("");
    	  $("#specificType").hide();
    	  $("#activitTitle").hide();
    	  $("#activitTitle").val("");
    	 
    	 }else{ 
    	   $('#defaultSel').click();
    	   $("#productName").show();
   	       $("#specificType").show();
	     }
     }
     
     

	function check_fun() {
		$("#tip").html("");
		var b = true;
		var content = $("#smsContent").val();
		var cellPhone = $("#smsPhones").val();
		var smsContent = $("#sms_content").val();
		var url = $("#sms_url").val();
		var type = $('input[name="sendType"]:checked').val();
		var flag = '${flag}';
		var title = $("#sms_title").val();
		var state =  $('input[name="isTime"]:checked').val();
		var bannerStartTime =  $('#timePushTime').val();
		var filePhone =  $('#filePhone').val();
		var smsPhone =  $('#smsPhones').val();
		
		
		if (flag == 0) {
			if (content == null || content.trim() == '') {
				$("#tip").html("请输入内容");
				b = false;
			}
		}
		
	  /*if (filePhone == null || filePhone.trim() == '') {
			if (type == 1) {
				$("#tip").html("请上传手机号");
				b = false;
			}
		}
		
		if (smsPhone == null || smsPhone.trim() == '') {
			if (type == 0) {
				$("#tip").html("请填写手机号");
				b = false;
			}
		}*/
		
		
		
		if (flag == 1) {
			
			if (smsContent == null || smsContent.trim() == '') {
				$("#tip").html("请输入内容");
				b = false;
			}
			if (title == null || title.trim() == '') {
				$("#tip").html("请输入标题");
				b = false;
			}
			
			if(2==state){
				if (bannerStartTime == null || bannerStartTime.trim() == '') {
					$("#tip").html("请输入推送时间");
					b = false;
				}
			}
			
			
		}
		return b;
	}
	function clickSendType(value) {
		if (value == 1) {
			$("#cellPhoneDiv").css("display", 'block');
			$("#cellPhone").val("");
		} else if (value == 2 || value == 3 || value == 4) {
			$("#cellPhoneDiv").css("display", 'none');
		}
	}
</script>
