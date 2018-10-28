<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="modal-dialog" style="width: 50%;">
	<div class="modal-content">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal" aria-hidden="true">X</button>
			<h3 id="myModalLabel">${empty entity?'新增':'修改' }banner</h3>
		</div>
		<div class="modal-body">
			<form class="form-horizontal" method="post" id="sysUserForm" name="bannerForm"
				action="banner/save${empty entity?'Insert':'Update' }.htm"
				role="form">
				<div class="form-group">
					<label class="col-sm-2 control-label">banner类型<font color="red">&nbsp;*</font></label>
					<div class="col-sm-10">
						<select class="form-control" id="bannerType" name="bannerType">
							<option value="1" ${entity.bannerType=='1'?'selected':''}>首页顶部banner</option>
							<option value="2" ${entity.bannerType=='2'?'selected':''}>首页中部banner</option>
							<option value="3" ${entity.bannerType=='3'?'selected':''}>分类页banner</option>
						</select>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">标题<font color="red">&nbsp;*</font></label>
					<div class="col-sm-10">
						<input type="hidden" value="${entity.bannerId }" name="bannerId" /> 
						<input type="text" class="form-control required" id="banner_title" name="title" value="${entity.title }">
					    <input type="hidden" class="form-control" name="imageUrl" id="bannerFile_input" value="${entity.imageUrl }">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">跳转方式<font color="red">&nbsp;*</font></label>
					<div class="col-sm-10">
						<select class="form-control" id="clickType" name="clickType">
							<option value="0" ${entity.clickType=='0'?'selected':''}>不跳转</option>
							<option value="1" ${entity.clickType=='1'?'selected':''}>HTML页面</option>
							<option value="2" ${entity.clickType=='2'?'selected':''}>个人主页</option>
							<option value="3" ${entity.clickType=='3'?'selected':''}>分类页</option>
						</select>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">链接<font color="red">&nbsp;*</font></label>
					<div class="col-sm-10">
						<input type="text" class="form-control" id="banner_url" name="url" value="${entity.url }"><br>
						<font color="red">
							配置说明：<br>
							1.HTML页面：直接配置网页链接<br>
							2.个人主页：voice://com.yanjing.voice/native?name=person_home&extra_user_id=要跳转的用户ID<br>
							3.分类页：voice://com.yanjing.voice/native?name=category&category_id=要跳转的分类ID&category_name=要跳转的分类名称</font>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">状态<font color="red">&nbsp;*</font></label>
					<div class="col-sm-10">
						<label class="checkbox-inline">
							<input type="radio" name="bannerStatus" value="1" ${empty entity or entity.bannerStatus=='1'?'checked':'' }>
							开启
						</label>
						<label class="checkbox-inline">
							<input type="radio" name="bannerStatus" value="0" ${entity.bannerStatus=='0'?'checked':'' }>
							关闭
						</label>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">序号<font color="red">&nbsp;*</font></label>
					<div class="col-sm-10">
						<input type="number" step="1" class="form-control" id="banner_sort" name="sort" value="${entity.sort }">
						<font color="red">(注：通过序号权重排序，数字大的优先展示 )</font>
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
						<input type="text" class="form-control" name="remark" value="${entity.remark }">
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
							<img src="${entity.imageUrl }" alt="暂无图片，点击上传！" class="img-rounded" style="max-width: 100%; max-height: 300px;" id="bannerFile_img">
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

		var title = $("#banner_title").val();
		if(title == null || title.trim() == ''){
			$("#tip").html("请输入标题");
			return false;
		}
		if(title&&title.length>150){
			$("#tip").html("标题不能超过150个字");
			return false;
		}

		var url = $("#banner_url").val();
		if(url == null || url.trim() == ''){
			if($("#clickType").val() == 1){
				$("#tip").html("请输入HTML链接");
				return false;
			}
			if($("#clickType").val() == 2){
				$("#tip").html("请输入个人主页的主页ID");
				return false;
			}
			if($("#clickType").val() == 3){
				$("#tip").html("请输入分类页的类别ID");
				return false;
			}
		}

		var sort = $("#banner_sort").val();
		if(sort == null || sort.trim() == ''){
			$("#tip").html("请输入序号");
			return false;
		}
		if(sort&&sort.length>9){
			$("#tip").html("序号不能超过9个字");
			return false;
		}

		var startTime = $("#banner_startTime").val();
		var endTime = $("#banner_endTime").val();
		if(startTime == null || startTime.trim() == ''){
			$("#tip").html("请输入开始时间");
			return false;
		}
		if(endTime == null || endTime.trim() == ''){
			$("#tip").html("请输入结束时间");
			return false;
		}
		if(endTime <= startTime){
			$("#tip").html("开始时间必须大于结束时间");
			return false;
		}

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
				return false;
			}

		}

		var remark = $('input[name="remark"]').val();
		if(remark&&remark.length>250){
			$("#tip").html("备注不能超过250个字");
			return false;
		}

		var temp = /^[0-9]*$/;
		if(!temp.test(sort)){
			$("#tip").html("序号必须全部是数字");
			return false;
		}

		return true;
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
				url : "upload/appBanner.htm",
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