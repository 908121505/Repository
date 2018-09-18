<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="modal-dialog">
	<div class="modal-content">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal"
				aria-hidden="true">X</button>
			<h3 id="myModalLabel">${empty entity?'新增':'修改' }供应商信息</h3>
		</div>
		<div class="modal-body">
			<form class="form-horizontal" method="post" id="sysUserForm" name="bannerForm"
				action="supplier/save${empty entity?'Insert':'Update' }.htm"
				role="form">
				<div class="form-group">
					<label class="col-sm-2 control-label">名称<font color="red">&nbsp;*</font></label>
					<div class="col-sm-10">
						<input type="hidden" value="${entity.supplierId }" id="supplierId" name="supplierId" /> 
						<input type="text" class="form-control required" id="supplier_name" name="supplierName"
							value="${entity.supplierName }">
					    <input type="hidden"
							class="form-control" name="supplierIcon" id="bannerFile_input"
							value="${entity.supplierIcon }">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">编码<font color="red">&nbsp;*</font></label>
					<div class="col-sm-10">
						<input type="text" class="form-control" id="supplier_code" name="supplierCode"
							value="${entity.supplierCode }">
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
					<label class="col-sm-2 control-label">别名</label>
					<div class="col-sm-10">
						<input type="text" step="1" class="form-control" id="supplier_alias" name="supplierAlias"
							value="${entity.supplierAlias }">
					</div>
				</div>
				
				<div class="form-group">
					<label class="col-sm-2 control-label">链接</label>
					<div class="col-sm-10">
						<input type="text" step="1" class="form-control" id="supplier_link" name="supplierLink"
							value="${entity.supplierLink }">
					</div>
				</div>
			
			
			
				<div class="form-group">
					<label class="col-sm-2 control-label">备注</label>
					<div class="col-sm-10">
						<input type="text" class="form-control" id="remark" name="remark"
							value="${entity.remark }">
					</div>
				</div>
				
				
				
				<div class="form-group">
					<label class="col-sm-2 control-label">图标上传
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
					<label for="inputEmail3" class="col-sm-2 control-label">图片</label>
					<div class="col-sm-10">
						<div class="col-sm-6">
							<img src="${entity.supplierIcon }" alt="暂无图片，点击上传！" class="img-rounded"
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
function check_fun(){
	
	$("#tip").html("");
	var b = true;
	
	var name = $("#supplier_name").val();
	var code = $("#supplier_code").val();
	var state = $('input[name="state"]:checked').val();
	var supplierAlias = $("#supplier_alias").val();
	var remark = $("#remark").val();
	var supplierLink = $("#supplier_link").val();
	var bannerFileInput = $("#bannerFile_input").val();
	
	
	if(name == null || name.trim() == ''){
		$("#tip").html("请输入供应商名称");
		b = false;
	}else{
		//检查供应商名称是否重复
		$.ajax({
    		url: "supplier/queryRepeatName.htm?supplierIdUpd=${entity.supplierId }&supplierRepeatName="+ encodeURI(encodeURI(name)), 
    		async:false,
    		dataType:"JSON",
    		type:'get',
    		success: function(data){
   		        if(data>0){
         		    	 $("#tip").html("供应商名称已存在");
         		         b = false;
   				}
    		}
    	});
		
		
	}
	
	if(code == null || code.trim() == ''){
		$("#tip").html("请输入供应商编码");
		b = false;
	}
	
	if(state == undefined){
		$("#tip").html("请选择状态");
		b = false;
	}
	
	if(name&&name.length>30){
		$("#tip").html("供应商名称不能超过30个字");
		b = false;
	}
	
	if(code&&code.length>30){
		$("#tip").html("供应商编码不能超过30个字");
		b = false;
	}
	
	if(supplierAlias&&supplierAlias.length>30){
		$("#tip").html("供应商别名不能超过30个字");
		b = false;
	}
	
	if(supplierLink&&supplierLink.length>30){
		$("#tip").html("供应商链接不能超过255个字");
		b = false;
	}
	
	if(remark&&remark.length>30){
		$("#tip").html("供应商链接不能超过255个字");
		b = false;
	}
	
	if(bannerFileInput&&bannerFileInput.length>255){
		$("#tip").html("供应商图标路径不能超过255个字");
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
			data : {},
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