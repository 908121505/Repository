<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="modal-dialog">
	<div class="modal-content">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal"
				aria-hidden="true">X</button>
			<h3 id="myModalLabel">${empty entity?'新增':'修改' }产品类别</h3>
		</div>
		<div class="modal-body">
			<form class="form-horizontal" method="post" id="sysUserForm" name="bannerForm"
				action="productCategory/save${empty entity?'Insert':'Update' }.htm"
				role="form">
				<div class="form-group">
					<label class="col-sm-2 control-label">分类名</label>
					<div class="col-sm-10">
						<input type="hidden" value="${entity.productCategoryId }" name="productCategoryId" /> 
						<input type="text" class="form-control required" id="category_Name" name="categoryName"
							value="${entity.categoryName }">
					    <input type="hidden"
							class="form-control" name="imageUrl" id="bannerFile_input"
							value="${entity.imageUrl }">
					</div>
				</div>
			  
				<div class="form-group">
					<label class="col-sm-2 control-label">简介</label>
					<div class="col-sm-10">
						<input type="text" class="form-control" id="introduction" name="introduction"
							value="${entity.introduction}">
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
					<label class="col-sm-2 control-label">序号</label>
					<div class="col-sm-10">
						<input type="number" step="1" class="form-control" id="sort" name="sort"
							value="${entity.sort }">
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
					<label class="col-sm-2 control-label">图片上传
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
							<img src="banner/getImg.htm?imgUrl=${entity.imageUrl }" alt="暂无图片，点击上传！" class="img-rounded"
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
	var state = $('input[name="state"]:checked').val();
	var categoryName = $("#category_Name").val();
	var introduction = $("#introduction").val();
	var sort = $("#sort").val();
	var remark = $("#remark").val();
	var bannerFileInput = $("#bannerFile_input").val();
	
	
	if(categoryName==null || categoryName==''){
		$("#tip").html("请输入分类名");
		b = false;
	}
	
	if(state == undefined){
		$("#tip").html("请选择状态");
		b = false;
	}
	
	if(categoryName&&categoryName.legnth>100){
		$("#tip").html("分类名长度不能超过100个字");
		b = false;
	}
	
	if(introduction&&introduction.length>500){
		$("#tip").html("简介不能超过500个字");
		b = false;
	}
	
	if(sort&&sort.length>9){
		$("#tip").html("序号不能超过9个字");
		b = false;
	}
	if(remark&&remark.length>250){
		$("#tip").html("备注不能超过250个字");
		b = false;
	
	}
	
	if(bannerFileInput&&bannerFileInput.length>255){
		$("#tip").html("图片路径不能超过255个字");
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
					"id" : "${entity.productCategoryId }"
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