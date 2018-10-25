<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="modal-dialog" style="height: 80%;width: 50%;">
	<div class="modal-content">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal" aria-hidden="true">X</button>
			<h3 id="myModalLabel">${empty entity?'新增':'修改' }消息</h3>
		</div>
		<div class="modal-body">
			<form class="form-horizontal" method="post" id="messageForm" name="messageForm"
				action="internal/save${empty entity?'Insert':'Update' }.htm"
				role="form">
				<div class="form-group">
					<label class="col-sm-2 control-label">消息标题<font color="red">&nbsp;*</font></label>
					<div class="col-sm-10">
						<input type="hidden" value="${entity.messageId }" name="messageId" />
						<input type="hidden" value="${entity.backgroundImageUrl }" name="backgroundImageUrl" id="backgroundImageUrl" />
						<input type="hidden" value="${entity.messageDescribe }" name="messageDescribe" id="messageDescribe" />
						<input type="text" class="form-control required" id="message_title" name="title" style="width: 80%;" value="${entity.title}">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">H5链接<font color="red">&nbsp;*</font></label>
					<div class="col-sm-10">
						<input type="text" class="form-control" id="h5Url" name="h5Url"
							   style="width: 80%;" value="${entity.h5Url}"><br>
					</div>
				</div>
				<div class="form-group">
					<label for="file_img" class="col-sm-2 control-label">背景图片</label>
					<div class="col-sm-10">
						<div class="col-sm-6">
							<img src="${entity.backgroundImageUrl }" alt="暂无图片，点击上传！" class="img-rounded"
								 style="max-width: 80%; max-height: 300px;" id="file_img">
						</div>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">图片上传<c:if test="${entity eq null }"><font color="red">&nbsp;*</font></c:if>
					</label>
					<div class="col-sm-10">
						<div class="input-group">
							<input type="file" class="form-control" id="messageFile" style="width: 80%;"
								   name="messageFile"> <span class="input-group-btn">
								<button class="btn btn-success" type="button" id="uploadBanner">上传图片</button>
							</span>
						</div>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">发送对象<font color="red">&nbsp;*</font></label>
					<div class="col-sm-10">
						<label class="checkbox-inline">
							<input type="radio" name="sendType" value="0" ${empty entity or entity.sendType=='0'?'checked':'' }>
							指定用户
						</label>
						<label class="checkbox-inline">
							<input type="radio" name="sendType" value="1" ${entity.sendType=='1'?'checked':'' }>
							所有用户
						</label>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">用户上传<c:if test="${entity eq null }"><font color="red">&nbsp;*</font></c:if>
					</label>
					<div class="col-sm-10">
						<div class="input-group">
							<input type="file" class="form-control" id="file" style="width: 60%;" name="file">
							<span id="tip_customer" style="color: red;font-size: 14px;margin-left:20px; "></span>
							<span class="input-group-btn">
								<button class="btn btn-success" type="button" id="uploadCustomer">导入用户</button>
							</span>
						</div>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">消息类型：<font color="red">&nbsp;*</font></label>
					<div class="col-sm-10">
						<select class="form-control" id="type" name="type" style="width: 250px;">
							<option value="0" ${entity.type=='0'? 'selected':'' }>系统通知</option>
							<option value="1" ${entity.type=='1'? 'selected':'' }>活动通知</option>
						</select>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">内容</label>
					<div class="col-sm-10">
						<textarea class="form-control" id="messageContent" name="messageContent" style="width: 50%; height: 160px;">${entity.messageContent }</textarea>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">消息状态：<font color="red">&nbsp;*</font></label>
					<div class="col-sm-10">
						<select class="form-control" id="sendStatus" name = "sendStatus" style="width: 250px;">
							<option value="0" ${entity.sendStatus=='0'? 'selected':'' }>关闭</option>
							<option value="2" ${entity.sendStatus=='2'? 'selected':'' }>开启</option>
						</select>
					</div>
				</div>
				<div class="form-group">
					<span id="tip" style="color: red;font-size: 14px;margin-left:20px; "></span>
				</div>

			</form>
		</div>
		
		<div class="modal-footer">
			<button class="btn btn-default" data-dismiss="modal"
				aria-hidden="true">取消</button>
			<button class="btn btn-primary" data-dismiss="modal" style="display: ${entity.sendStatus=='2'? 'none':'' }">保存</button>
		</div>
	</div>
</div>
<script type="text/javascript"
	src="resources/js/My97DatePicker/WdatePicker.js" charset="utf-8"></script>
<script type="text/javascript">
	function check_fun(){
		$("#tip").html("");

		var title = $("#message_title").val();
		if(title == null || title.trim() == ''){
			$("#tip").html("请输入标题");
			return false;
		}
		if(title&&title.length>150){
			$("#tip").html("标题不能超过100个字");
			return false;
		}

		var url = $("#h5Url").val();
		if(url == null || url.trim() == ''){
			$("#tip").html("请输入H5链接");
			return false;
		}

		if('${entity}' == ''){
			var filepath = $("#backgroundImageUrl").val();
			if( filepath == null || filepath == ''){
				$("#tip").html("请上传图片");
				return false;
			}
		}

		var messageContent = $('input[name="messageContent"]').val();
		if(messageContent && messageContent.length>250){
			$("#tip").html("内容不能超过1000个字");
			return false;
		}

        var sendType = $("input[name='sendType']:checked").val();
		if(sendType == 0){
		    if($("#messageDescribe").val() == null || $("#messageDescribe").val().trim() == ''){
                $("#tip").html("发送对象需导入指定客户");
		        return false;
            }
        }
		return true;
	}
	$(function() {
        $('#uploadBanner').click(function() {
            var file = document.messageForm.messageFile.value;
            if(file == ""){
                $("#tip").html("请选择图片");
                return false;
            }
            $.ajaxFileUpload({
                type : "post",
                dataType : "json",
                fileElementId : 'messageFile',
                url : "internal/backgroundImg.htm",
                data : {
                    "imgFolder" : "internal",
                },
                error : function(data) {
                    alert("上传图片发生错误！");
                },
                success : function(data) {
                    if (data.result = 'success') {
                        $('#backgroundImageUrl').val(data.imgUrl);
                        $('#file_img').attr("src", data.imgUrl);
                        $("#tip").html("");
                    } else {
                        alert(data.msg);
                    }
                }
            });
        });

        $('#uploadCustomer').click(function() {
            var file = document.messageForm.file.value;
            if(file == ""){
                $("#tip_customer").html("请选择文件");
                return false;
            }
            $.ajaxFileUpload({
                type : "post",
                dataType : "json",
                fileElementId : 'file',
                url : "import/message.htm",
                data : {

                },
                error : function(data) {
                    alert("上传文件发生错误！");
                },
                success : function(data) {
                    if (data.code = '2000') {
						$('#messageDescribe').val(data.idStr);
                        $("#tip_customer").html(data.message);
                    } else {
                        alert(data.msg);
                    }
                }
            });
        });
	});
</script>