<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>贷款管家</title>
</head>
<body>
<div class="modal-dialog">
	<div class="modal-content">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal"
				aria-hidden="true">X</button>
			<h3 id="myModalLabel">新增商户评论</h3>
		</div>
		<div class="modal-body">
			<form class="form-horizontal" method="post" id="sysUserForm"   name="sendMessageForm"
			action="productreviews/saveInsert.htm"
			 role="form">
					<div class="form-group">
						<label class="col-sm-2 control-label">评论商户<font color="red"></font></label> 
						<div class="col-sm-10">
							<select class="form-control" id="feedbackProductId" name="feedbackProductId" >
							<c:forEach var="info" items="${products }">
							<option value="${info.productId }" >${info.productName }</option>
							</c:forEach>
							</select>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">马甲号<font color="red"></font></label> 
						<div class="col-sm-10">
							<select class="form-control" id="feedbackPerson" name="feedbackPerson" >
							<c:forEach var="info" items="${banners }">
							<option value="${info.accountId }">${info.nickname }</option>
							</c:forEach>
							</select>
						</div>
					</div>
					<div class="form-group" id = "cellPhoneDiv" style="display:block;">
						<label class="col-sm-2 control-label">评论内容<font color="red">&nbsp;*</font></label>
						<div class="col-sm-10" >
						    <textarea   id ="feedbackReason" name="feedbackReason" class="form-control" rows="6"  style="overflow:auto"></textarea> 
						</div>
					</div>		
					<div class="form-group">
					<label class="col-sm-2 control-label">评论时间<font color="red">&nbsp;*</font></label>
					<div class="col-sm-10">
                            <input class="form-control" type="datetime-local" onchange="check_time('feedbackTime')" name="feedbackTime"  id="feedbackTime">
                        </div>
				</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">星级</label> 
						<div class="col-sm-10">
							<select class="form-control" id="grade" name="grade">
							<option value="5" selected="selected">五星</option>
							<option value="4">四星</option>
							<option value="3">三星</option>
							<option value="2">二星</option>
							<option value="1">一星</option>
							</select>
						</div>
					</div>	
					
					<div class="form-group">
						<label class="col-sm-2 control-label">标签<font color="red">&nbsp;*</font></label>
						<div class="col-sm-10">
						<c:forEach var="info" items="${productLabelsNice }" >
							<label class="checkbox-inline"> 
								<input type="checkbox" name="feedbackLabel" value="${info.labelName }" checked > ${info.labelName }
							</label> 
							</c:forEach>
							</br>
							<c:forEach var="info" items="${productLabelsBad }" >
							<label class="checkbox-inline"> 
								<input type="checkbox" name="feedbackLabel" value="${info.labelName }"  > ${info.labelName }
							</label> 
							</c:forEach>
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
</body>

<script type="text/javascript">
	function check_fun(){
		$("#tip").html("");
		var b = true;
		var content = $("#feedbackReason").val();
		var feedbackLabel = $("#feedbackLabel").val();
		
		
		var time=$("#feedbackTime").val();
		if($("input[name='feedbackLabel']:checked").length <1)
		{
			$("#tip").html("请选择标签");
			b = false;
	    }
		if(time==null||time.trim()==''){
			$("#tip").html("请选择评论时间");
			b = false;
		}
		if(content == null || content.trim() == ''){
			$("#tip").html("请输入内容");
			b = false;
		}
		return b;
	}
	function check_time(id){
		 var obj=$("#"+id);
		 var pdate = obj.val();
	     var d = new Date;
	     var today = new Date(d.getFullYear (), d.getMonth (), d.getDate ());
	     var reg = /\d+/g;
	     var temp = pdate.match(reg);
	     var foday = new Date (temp[0], parseInt (temp[1]) - 1, temp[2]);
	     var toHours=d.getHours();
	     var toMinutes=d.getMinutes();
	     var foHours=temp[3];
	     var foMinutes=temp[4];
	     if (foday > today)
	        {
	    	 $("#tip").html("评论时间不能大于今天");
	    	 obj.val("");
	        }
	     else if(foHours>toHours){
	    	 $("#tip").html("评论时间不能大于今天");
	    	 obj.val(""); 
	     }
	     else{
	        	 $("#tip").html("");
	        }
	     
		
		
		
		
	}
	
	
</script>
</html>