<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="modal-dialog">
	<div class="modal-content">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal"
				aria-hidden="true">X</button>
			<h3 id="myModalLabel">${empty entity?'新增':'修改' }banner</h3>
		</div>
		<div class="modal-body">
			<form class="form-horizontal" method="post" id="sysUserForm" name="bannerForm"
				action="banner/save${empty entity?'Insert':'Update' }.htm"
				role="form">
				<div class="form-group">
					<label class="col-sm-2 control-label">标题<font color="red">&nbsp;*</font></label>
					<div class="col-sm-10">
						<input type="hidden" value="${entity.bannerId }" name="bannerId" /> 
						<input type="text" class="form-control required" id="banner_title" name="title"
							value="${entity.title }">
					    <input type="hidden"
							class="form-control" name="image" id="bannerFile_input"
							value="${entity.image }">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">链接<font color="red">&nbsp;*</font></label>
					<div class="col-sm-10">
						<input type="text" class="form-control" id="banner_url" name="url"
							value="${entity.url }"><br>
							<font color="red">(注：如果是跳转到APP产品详情页URL传入产品ID，产品ID从产品管理-产品详情页面获取，格式如下：productId=abd815646d8a4544b7c454d5e77a063b )</font>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">状态<font color="red">&nbsp;*</font></label>
					<div class="col-sm-10">
						<label class="checkbox-inline"> <input type="radio"
							name="state" value="1" ${entity.state=='1'?'checked':'' }>
							开启
						</label> <label class="checkbox-inline"> <input type="radio"
							name="state" value="0" ${entity.state=='0'?'checked':'' }>
							关闭
						</label>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">banner类型<font color="red">&nbsp;*</font></label>
					<div class="col-sm-10">
						<select class="form-control" id="bannerType" name="bannerType">
							<option value="1" ${entity.bannerType=='1'?'selected':''}>首页banner</option>
						</select>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">序号<font color="red">&nbsp;*</font></label>
					<div class="col-sm-10">
						<input type="number" step="1" class="form-control" id="banner_serialNum" name="serialNum"
							value="${entity.serialNum }">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">开始时间<font color="red">&nbsp;*</font></label>
					<div class="col-sm-10">
						<input type="text" id="banner_startTime" class="form-control"
							onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'1950-01-01',maxDate:'#F{$dp.$D(\'banner_endTime\')}'})"
							value="${entity.startTime }" name="startTime" autocomplete="off"/>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">结束时间<font color="red">&nbsp;*</font></label>
					<div class="col-sm-10">
						<input type="text" id="banner_endTime" class="form-control"
							onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'banner_startTime\')}',maxDate:'2050-12-31'})"
							value="${entity.endTime }" name="endTime" autocomplete="off"/>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">设备类型：<font color="red">&nbsp;*</font></label>
					<div class="col-sm-10">
						<select class="form-control" id="deviceType" name="deviceType">
							<option value="0" ${entity.deviceType=='0'? 'selected':'' }>所有设备</option>
							<option value="1" ${entity.deviceType=='1'? 'selected':'' }>iOS</option>
							<option value="2" ${entity.deviceType=='2'? 'selected':'' }>Android</option>
						</select>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">版本号：<font color="red">&nbsp;*</font></label>
					<div class="col-sm-10">
						<select class="form-control" id="appVersionRule" name = "appVersionRule">
							<option value="0" ${entity.appVersionRule=='0'? 'selected':'' }>所有版本</option>
							<option value="1" ${entity.appVersionRule=='1'? 'selected':'' }>大于</option>
							<option value="2" ${entity.appVersionRule=='2'? 'selected':'' }>小于</option>
							<option value="3" ${entity.appVersionRule=='3'? 'selected':'' }>等于</option>
							<option value="4" ${entity.appVersionRule=='4'? 'selected':'' }>大于等于</option>
							<option value="5" ${entity.appVersionRule=='5'? 'selected':'' }>小于等于</option>
						</select>
						<input type="text" class="form-control required" id="appVersion" name="appVersion"
							   value="${entity.appVersion }"/> 格式：4.0.0
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">备注</label>
					<div class="col-sm-10">
						<input type="text" class="form-control" name="remark"
							value="${entity.remark }">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">背景色<font color="red">&nbsp;*</font></label>
					<div class="col-sm-10">
						<input type="text" step="1" class="form-control" id="bannerColor" name="bannerColor"
							value="${entity.bannerColor }">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">图片上传<c:if test="${entity eq null }"><font color="red">&nbsp;*</font></c:if>
					</label>
					
					<div class="col-sm-10">
						<div class="input-group">
							<input type="file" class="form-control" id="appversionFile"
								name="appversionFile"> <span class="input-group-btn">
								<button class="btn btn-success" type="button" id="uploadBanner">上传图片</button>
							</span>
						</div>
					</div>
				</div>
		      <div class="form-group">
					<label for="bannerFile_img" class="col-sm-2 control-label">图片</label>
					<div class="col-sm-10">
						<div class="col-sm-6">
							<img src="${entity.image }" alt="暂无图片，点击上传！" class="img-rounded" style="max-width: 100%; max-height: 300px;" id="bannerFile_img">
						</div>
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
<script type="text/javascript"
	src="resources/js/My97DatePicker/WdatePicker.js" charset="utf-8"></script>
<script type="text/javascript">
function check_fun(){
	$("#tip").html("");
	var b = true;
	var endTime = $("#banner_endTime").val();
	var startTime = $("#banner_startTime").val();
	var serialNum = $("#banner_serialNum").val();
	var state = $('input[name="state"]:checked').val();
	var remark = $('input[name="remark"]').val();
	var bannerFileInput = $('input[name="bannerFile_input"]').val();
	var url = $("#banner_url").val();
	var title = $("#banner_title").val();

    var versionRule = $("#appVersionRule").val();
    var version = $("#appVersion").val();
    if (versionRule != 0) {
        if(version == null || version.trim() == ''){
            $("#tip").html("请输入版本号，格式：4.0.0");
            return false;
        }
    }

	if('${entity}' == ''){
		var filepath = $("#bannerFile_input").val();
		if( filepath == null || filepath == ''){
			$("#tip").html("请上传图片");
			b = false;
		}
		
	}
	
	if(endTime == null || endTime.trim() == ''){
		$("#tip").html("请输入结束时间");
		b = false;
	}
	if(startTime == null || startTime.trim() == ''){
		$("#tip").html("请输入开始时间");
		b = false;
	}
	if(serialNum == null || serialNum.trim() == ''){
		$("#tip").html("请输入序号");
		b = false;
	}
	if(state == undefined){
		$("#tip").html("请选择状态");
		b = false;
	}
	if(url == null || url.trim() == ''){
		$("#tip").html("请输入链接");
		b = false;
	}
	if(title == null || title.trim() == ''){
		$("#tip").html("请输入标题");
		b = false;
	}
	
	
	//格式校验
	
	if(title&&title.length>150){
		$("#tip").html("标题不能超过150个字");
		b = false;
	}
	
  
    if(serialNum&&serialNum.length>9){
    	$("#tip").html("序号不能超过9个字");
    	b = false;
    }
    
    if(remark&&remark.length>250){
   	    $("#tip").html("备注不能超过250个字");
    	b = false;
    } 
    
    
    var temp = /^[0-9]*$/;
    if(!temp.test(serialNum)){
    	$("#tip").html("序号必须全部是数字");
    	b = false;
    }
    
	
	return b;
}
	$(function() {
		$('#uploadBanner').click(function() {
			var file = document.bannerForm.appversionFile.value;
			if(file == ""){
				$("#tip").html("请选择图片");
				return false;
			}
			$.ajaxFileUpload({
				type : "post",
				dataType : "json",
				fileElementId : 'appversionFile',
				url : "upload/appversion.htm",
				data : {
					"id" : "${entity.bannerId }"
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