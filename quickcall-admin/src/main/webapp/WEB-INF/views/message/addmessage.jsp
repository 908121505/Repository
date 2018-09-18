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
			<h3 id="myModalLabel">${empty entity?'新增':'修改' }发送消息</h3>
		</div>
		<div class="modal-body">
			<form class="form-horizontal" method="post" id="sysUserForm"   name="sendMessageForm"
			action="message/save${empty entity?'Insert':'Update' }.htm"
			 role="form">
					<div class="form-group">
						<label class="col-sm-2 control-label">类型<font color="red">&nbsp;*</font></label> 
						<div class="col-sm-10">
							<select class="form-control" id="message_type" name="type">
							<%-- <option value="1" ${entity.type=='1'?'selected':'' }>个人信息</option> --%>
							<option value="3" ${entity.type=='3'?'selected':'' }>活动消息</option>
							<option value="4" ${entity.type=='4'?'selected':'' }>系统通知</option>
							<option value="2" ${entity.type=='2'?'selected':'' }>通知信息</option>
							<%-- <option value="5" ${entity.type=='5'?'selected':'' }>邀请好友</option>
							<option value="6" ${entity.type=='6'?'selected':'' }>新人有礼</option>
							<option value="7" ${entity.type=='7'?'selected':'' }>信用卡首页</option> --%>
							</select>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">标题<font color="red">&nbsp;*</font></label>
						<div class="col-sm-10">
							<input type="text" id="message_title" class="form-control" name="title"  value="${entity.title}">
							<input type="hidden" value="${entity.messageId }" name="messageId" /> 
							<input type="hidden" value="" name="idStr"  id = "idStr" /> 
							 <input type="hidden"
							class="form-control" name="messageUrl" id="bannerFile_input"
							value="${entity.messageUrl }">
						</div>
					</div>	
					<div class="form-group">
					<label class="col-sm-2 control-label">跳H5链接地址</label>
					<div class="col-sm-8">
						<input type="text" id="linkUrlH" class="form-control"
							name="linkUrlH" value="${entity.linkUrlH}">
					</div>
				</div>
				<div class="form-group"  id = "uploadImg2">
					<label class="col-sm-2 control-label">图片上传<c:if
							test="${entity eq null }">
							<font color="red">&nbsp;*</font>
						</c:if>
					</label>

					<div class="col-sm-10"  >
						<div class="input-group">
							<input type="file" class="form-control" id="messageFile"
								name="messageFile"> <span class="input-group-btn">
								<button class="btn btn-success" type="button" id="uploadBannerImg">上传图片</button>
							</span>
						</div>
					</div>
				</div>
				<div class="form-group"  id = "uploadImg1">
					<label for="inputEmail5" class="col-sm-2 control-label">背景图片</label>
					<div class="col-sm-10">
						<div class="col-sm-6">
							<img src="${entity.messageUrl }" alt="暂无图片，点击上传！"
								class="img-rounded" style="max-width: 100%; max-height: 300px;"
								id="bannerFile_img">
						</div>
					</div>
				</div>			
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
					
					
					<div class="form-group" id = "importDiv">
						<label class="col-sm-2 control-label">数据导入</label>
						<div class="col-sm-10">
							<div class="input-group">
								<input type="file" class="form-control" id="importDataFile"  name="importDataFile"> <span class="input-group-btn">
									<button class="btn btn-success" type="button" id="uploadDataFile">上传文件</button>
								</span>
							</div>
						</div>
					</div>

				<%-- 	<div class="form-group" id = "cellPhoneDiv" style="display:block;">
						<label class="col-sm-2 control-label">用户手机号码<font color="red">&nbsp;*</font></label>
						<div class="col-sm-10" >
						    <textarea name = "cellPhone"  id ="message_cellPhone" class="form-control" rows="6" placeholder="输入多个手机号时用空格或者逗号隔开" style="overflow:auto">${entity.cellPhone}</textarea> 
						</div>
					</div>	 --%>	
					
					
					
					<div class="form-group">
					<label class="col-sm-2 control-label" >内容<font color="red">&nbsp;*</font></label>
					<div class="col-sm-10">
						<textarea name = "content" id="message_content" class="form-control" rows="6" style="overflow:auto">${entity.content}</textarea> 
					</div>
				</div>	
				<span id="tip" style="color: red;font-size: 14px;margin-left:20px; "></span>
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





	function check_fun(){
		$("#tip").html("");
		var b = true;
		var content = $("#message_content").val();
		/* var cellPhone = $("#message_cellPhone").val(); */
		var title = $("#message_title").val();
		/* var InputImage = $('input[name="bannerFile_input"]').val(); */
		var type = $("#message_type").val();
		var sendType =  $("input[name=sendType]:checked").val();
		if(content == null || content.trim() == ''){
			$("#tip").html("请输入内容");
			b = false;
		}
		/* if(type == 1){
			if(cellPhone == null || cellPhone.trim() == ''){
			   if(1==sendType){
				$("#tip").html("请输入手机号");
				b = false;
			   }
			}
		} */
		if(title == null || title.trim() == ''){
			$("#tip").html("请输入标题");
			b = false;
		}
		if(type == null || type.trim() == ''){
			$("#tip").html("请选择类型");
			b = false;
		}
		
		
		
		return b;
	}
	function clickSendType(value) {
		if (value == 1) {
			$("#importDiv").css("display",'block');
		} else if (value == 2) {
			$("#importDiv").css("display",'none');
		}
	}
	$(function() {
		
	    $("#message_type").change(function(){
	    	var type = $("#message_type").val();
	    	if(type == 2){
	    		$("#uploadImg1").hide();
	    		$("#uploadImg2").hide();
	    	}else{
		    	$("#uploadImg1").show();
	    		$("#uploadImg2").show();
	    	}
	    });
		//手机号码文件上传
		$('#uploadDataFile').click(function() {
			 $("#tip").html("");
			var file = document.sendMessageForm.importDataFile.value;
			if(file == ""){
				$("#tip").html("请选择文件");
				return false;
			}
			
			if(!/\.(XLSX)$/.test(file.toUpperCase())){
				$("#tip").html("文件格式错误");
				return false;
			}
			
			
			
			$.ajaxFileUpload({
				type : "post",
				dataType : "json",
				fileElementId : 'importDataFile',
				url : "import/phoneList.htm",
				data : {
					"id" : ""
				},
				error : function(data) {
					alert("错误！");
				},
				success : function(data) {
					if (data.result = 'success') {
						console.info(data);
						var  code =  data.code;
						if(code == 2000){
							alert("excel文件中手机号码格式不正确，导入失败！！！");
							$("#tip").html("excel文件中手机号码格式不正确，导入失败！！！");
						}else if(code == 2001){
							alert("excel文件中手机号码重复，导入失败！！！");
							$("#tip").html("excel文件中手机号码重复，导入失败！！！");
						}else if(code  ==  2002){
							alert("导入成功");
							$("#tip").html("导入成功");
							//redis key值写入隐藏域
							//将导入置灰不能导入
							$("#idStr").val(data.idStr);
							$("#uploadDataFile").attr("disabled",true);
							$("#importDataFile").attr("disabled",true);
						}else if(code == 2003){
							alert("excel文件数据为空，导入失败！！！");
							$("#tip").html("excel文件数据为空，导入失败！！！");
						}else if(code  == 2004){
							alert("excel文件数据超过5000条，导入失败！！！");
							$("#tip").html("excel文件数据超过5000条，导入失败！！！");
						}
						// $("#tip").html("");
					} else {
						alert(data.msg);
					}
				}
			});
		});
		
		
		$('#uploadBannerImg').click(function() {
			var file = document.sendMessageForm.messageFile.value;
			if(file == ""){
				$("#tip").html("请选择图片");
				return false;
			}
			$.ajaxFileUpload({
				type : "post",
				dataType : "json",
				fileElementId : 'messageFile',
				url : "upload/message.htm",
				data : {
					"id" : "${entity.messageId }"
				},
				error : function(data) {
					alert("错误！");
				},
				success : function(data) {
					if (data.result = 'success') {
						console.info(data);
						alert("上传图片成功！");
						$('#bannerFile_input').val(data.imgUrl);
						$('#bannerFile_img').attr("src", data.imgUrl);
						$("#tip").html("");
					} else {
						alert(data.msg);
					}
				}
			});
		});
	});
</script>
</html>