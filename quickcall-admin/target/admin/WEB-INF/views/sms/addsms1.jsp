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
			<h3 id="myModalLabel">发送短信</h3>
		</div>
		<div class="modal-body">
			<form class="form-horizontal" method="post" id="sendSmsForm" action="sms/saveInsert.htm" role="form">
					<div class="form-group">
						<label class="col-sm-2 control-label">发送对象</label>
						<div class="col-sm-10">
							<label class="checkbox-inline"> 
								<input type="radio" name="sendType" value="1" checked onclick="clickSendType(1)"> 指定用户发送
							</label> 
							<label class="checkbox-inline"> 
								<input type="radio" name="sendType" value="2" onclick="clickSendType(2)"> 所有用户
							</label>
						</div>
					</div>

					<div class="form-group" id = "cellPhoneDiv" style="display:block;">
						<label class="col-sm-2 control-label">用户手机号码<font color="red">&nbsp;*</font></label>
						<div class="col-sm-10" >
						    <textarea name = "cellPhone" class="form-control" rows="6" placeholder="输入多个手机号时用英文逗号隔开。例如:13300000000,13200000000。一次发送不能大于50000个号码"></textarea> 
						</div>
					</div>		
				<div class="form-group">
					<label class="col-sm-2 control-label">内容<font color="red">&nbsp;*</font></label>
					<div class="col-sm-10">
						<textarea name = "content" class="form-control" rows="6"  placeholder="必须是已经跟短信平台报备好的内容。不能超过536个字。"></textarea> 
					</div>
				</div>
			</form>
		</div>
		<div class="modal-footer">
			<button class="btn btn-default" data-dismiss="modal"
				aria-hidden="true">取消</button>
			<button class="btn btn-primary" data-dismiss="modal">发送</button>
		</div>
	</div>
</div>
</body>

<script type="text/javascript">
	
	function clickSendType(value) {
		if (value == 1) {
			$("#cellPhoneDiv").css("display",'block');
		} else if (value == 2) {
			$("#cellPhoneDiv").css("display",'none');
		}
	}

</script>
</html>