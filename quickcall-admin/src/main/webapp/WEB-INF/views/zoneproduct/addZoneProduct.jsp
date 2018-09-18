<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="modal-dialog">
	<div class="modal-content">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal"
				aria-hidden="true">X</button>
			<h3 id="myModalLabel">${empty entity?'新增':'修改' }专区类型信息</h3>
		</div>
		<div class="modal-body">
			<form class="form-horizontal" method="post" id="sysUserForm" name="bannerForm"
				action="zoneProduct/save${empty entity?'Insert':'Update' }.htm"
				role="form">
				<div class="form-group">
					<label class="col-sm-2 control-label">专区名<font color="red">&nbsp;*</font></label>
					<div class="col-sm-10">
						<input type="hidden" value="${entity.zoneId }" name="zoneId" /> 
						<input type="text" class="form-control required" id="zoneNames" name="zoneName"
							value="${entity.zoneName }">
							
					    <input type="hidden"
							class="form-control" name="labelBackgroundImg" id="bannerFile_input"
							value="${entity.labelBackgroundImg }">
					</div>
				</div>
				
				<div class="form-group">
					<label class="col-sm-2 control-label">匹配用户</label>
					<div class="col-sm-10">
				    	<select class="form-control" id="matchTheUser" name="matchTheUser">
						     <option value="">请选择</option>
						      <c:forEach var="ul" items="${userLabelList}">
						         <option value="${ul.labelCode}"   ${entity.matchTheUser eq ul.labelCode?'selected="selected"':''} >${ul.labelName}</option>
						      </c:forEach>
						</select>
						
					</div>
				</div>
				
				<div class="form-group">
					<label class="col-sm-2 control-label">链接<font color="red">&nbsp;*</font></label>
					<div class="col-sm-10">
						<input type="text" class="form-control" id="linkUrlH" name="linkUrlH"
							value="${entity.linkUrlH }"><br>
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
				
				<%-- <div class="form-group">
					<label class="col-sm-2 control-label">是否专区<font color="red">&nbsp;*</font></label>
					<div class="col-sm-10">
						<label class="checkbox-inline"> <input type="radio"
							name="isArea" value="1" ${entity.isArea=='1'?'checked':'' }>
							是
						</label> <label class="checkbox-inline"> <input type="radio"
							name="isArea" value="0" ${entity.isArea=='0'?'checked':'' }>
							否
						</label>
					</div>
				</div> --%>
				
				<div class="form-group">
					<label class="col-sm-2 control-label">权重<font color="red">&nbsp;*</font></label>
					<div class="col-sm-10">
						<input type="text" class="form-control required" id="zoneType" name="zoneType"
							value="${entity.zoneType }">
					</div>
				</div>
				<%-- <div class="form-group">
					<label class="col-sm-2 control-label">专区类型<font color="red">&nbsp;*</font></label>
					<div class="col-sm-10">
				    	<select class="form-control" id="homeType" name="homeType">
							<option value="1" ${entity.homeType=='1'?'selected':'' }>首页</option>
							<option value="2" ${entity.homeType=='2'?'selected':'' }>社区</option>
						    <option value="3" ${entity.homeType=='3'?'selected':'' }>信用卡</option>
						    <option value="4" ${entity.homeType=='4'?'selected':'' }>个人中心</option>
						    <option value="5" ${entity.homeType=='5'?'selected':'' }>工具</option>
						    <option value="6" ${entity.homeType=='6'?'selected':'' }>新版首页</option>
							<option value="7" ${entity.homeType=='7'?'selected':'' }>申请就返现</option>
						</select>
					</div>
				</div>

				<div class="form-group" id="toolTypeDiv" ${(entity.toolType!='1' && entity.toolType!='2')?'style="display: none"':''}>
					<label class="col-sm-2 control-label">工具类型<font color="red">&nbsp;*</font></label>
					<div class="col-sm-10">
						<select class="form-control" id="toolType" name="toolType" >
							<option value="1" ${entity.toolType=='1'?'selected':'' }>管家工具</option>
							<option value="2" ${entity.toolType=='2'?'selected':'' }>惠花花工具</option>
						</select>
					</div>
				</div> --%>

				<div class="form-group">
					<label class="col-sm-2 control-label">字体颜色<font color="red">&nbsp;*</font></label>
					<div class="col-sm-10">
						<input type="text" class="form-control required" id="labelLcolor" name="labelLcolor"
							value="${entity.labelLcolor }">
					</div>
				</div>
				
					<div class="form-group">
					<label class="col-sm-2 control-label">字体大小<font color="red">&nbsp;*</font></label>
					<div class="col-sm-10">
						<input type="text" class="form-control required" id="labelFontSize" name="labelFontSize"
							value="${entity.labelFontSize }">
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
					<label class="col-sm-2 control-label">用户标签ID(用户身份需要)</label>
					<div class="col-sm-10">
						<input type="text" class="form-control" name=matchTheUser
							value="${entity.matchTheUser }">
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
					<label for="inputEmail3" class="col-sm-2 control-label">背景图片</label>
					<div class="col-sm-10">
						<div class="col-sm-6">
							<img src="${entity.labelBackgroundImg }" alt="暂无图片，点击上传！" class="img-rounded"
								style="max-width: 100%; max-height: 300px;" id="bannerFile_img">
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
    /* $("select#homeType").change(function(){
        console.log("执行了change")
        var objs = document.getElementById("homeType");
        var homeType = objs.options[objs.selectedIndex].value;
        var toolType = document.getElementById("toolTypeDiv");
        if(homeType == '5'){
            toolType.setAttribute("style","display:block");
        }else{
            toolType.setAttribute("style","display:none");
        }
	}); */

	
function check_fun(){
	$("#tip").html("");
	
	var state = $('input[name="state"]:checked').val();
	var remark = $('input[name="remark"]').val();
	var bannerFileInput = $('input[name="bannerFile_input"]').val();
	var zoneName = $("#zoneNames").val();
	var linkUrlH = $("#linkUrlH").val();
	var zoneType = $("#zoneType").val();
	
	/* var homeType = $("#homeType").val();  //专区类型 */
	var labelLcolor = $("#labelLcolor").val();   //字体颜色
	var labelFontSize = $("#labelFontSize").val();   //字体大小
	var appversionFile = $('input[name="appversionFile"]').val();
	
	if(state == undefined){
		$("#tip").html("请选择状态");
		return false;
	}
	
	if(zoneName.length==0){
		$("#tip").html("请输入专区名");
		return false;
	}
	
	if(linkUrlH.length==0){
		$("#tip").html("请输入链接名");
		return false;
	}

	if(zoneType.length==0){
		$("#tip").html("请输入序号");
		return false;
	}
	
	/* if(homeType.length==0){
		$("#tip").html("请输入专区类型");
		return false;
	} */
	
	if(labelLcolor.length==0){
		$("#tip").html("请输入字体颜色");
		return false;
	}
	
	if(labelFontSize.length==0){
		$("#tip").html("请输入字体大小");
		return false;
	}
	
	if('${entity}' == ''){
		var filepath = $("#bannerFile_input").val();
		if( filepath == null || filepath == ''){
			$("#tip").html("请上传图片");
			return false;
		}
		
	}
	
	//格式校验
	
	/*if(title&&title.length>150){
		$("#tip").html("标题不能超过150个字");
		b = false;
	}**/
	
	
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
				url : "upload/appversion.htm",
				data : {
					"id" : "${entity.zoneId }"
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