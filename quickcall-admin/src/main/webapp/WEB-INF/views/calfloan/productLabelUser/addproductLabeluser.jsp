<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<div class="modal-dialog">
	<div class="modal-content">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal"
				aria-hidden="true">X</button>
			<h3 id="myModalLabel">${empty entity?'新增':'修改' }产品标签信息</h3>
		</div>
		<div class="modal-body">
			<form class="form-horizontal" method="post" id="productLabelForm" name="productLabelForm"
				action="productLabeluser/user/save${empty entity?'Insert':'Update' }.htm"
				role="form">
				<div class="form-group">
					<label class="col-sm-2 control-label">标签名称<font color="red">&nbsp;*</font></label>
					<div class="col-sm-10">
					   <c:if test="${empty entity==false}">
							<input type="hidden" value="${entity.productLabelId }" id="productLabelId" name="productLabelId" /> 
							<input type="hidden" value="${entity.labelCode }" id="labelCode" name="labelCode" /> 
							<input type="hidden" value="${entity.createTime }" id="createTime" name="createTime" /> 
							<input type="hidden" value="${entity.createMan }" id="createMan" name="createMan" /> 
							<input type="hidden" value="${entity.modifyTime }" id="modifyTime" name="modifyTime" /> 
							<input type="hidden" value="${entity.modifyMan }" id="modifyMan" name="modifyMan" /> 
						</c:if>
						<input type="text" class="form-control" id="label_name" name="labelName" value="${entity.labelName}">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">产品类型<font color="red">&nbsp;*</font></label>
					<div class="col-sm-10">
						<select class="form-control" id="label_category_id" name="labelCategoryId">
								<c:forEach var="info" items="${productCategorys }">
									<option value="${info.productCategoryId }">${info.categoryName }</option>
								</c:forEach>
						</select>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">子标签标签</label>
					<div class="col-sm-10">
						<select class="form-control" id="parent_id" name="parentId">
						             <option value="">--无--</option>
								<c:forEach var="info2" items="${parentLabels }">
									<option value="${info2.productLabelId }">${info2.labelName }</option>
								</c:forEach>
						</select>
					</div>
				</div>
				
				<div class="form-group">
					<label class="col-sm-2 control-label">标签字体大小<font color="red">&nbsp;*</font></label>
					<div class="col-sm-10">
						<%-- <input type="number" id="labelFontSize"  class="form-control" name="labelFontSize"
							value="${entity.labelFontSize }"> --%>
							<select class="form-control" id="labelFontSize" name="labelFontSize">
						             <option value="6">6</option>
									 <option value="8">8</option>
									  <option value="10">10</option>
									   <option value="12">12</option>
									    <option value="14">14</option>
									     <option value="16">16</option>
									      <option value="18">18</option>
									       <option value="20">20</option>
									        <option value="22">22</option>
									         <option value="24">24</option>
									          <option value="28">28</option>
									           <option value="36">36</option>
									            <option value="48">48</option>
						</select>
					</div>
				</div>
				
				<div class="form-group">
					<label class="col-sm-2 control-label">标签颜色  </label>
					<div class="col-sm-10">
					     <input type="color" name="labelLcolor" id="labelLcolor" value="${entity.labelLcolor }"/>
					</div>
				</div>
				
				<div class="form-group">
					<label class="col-sm-2 control-label">标签状态 <font color="red">&nbsp;*</font></label>
					<div class="col-sm-10">
						<select class="form-control" id="state_id" name="state">
							  <option value="1">有效</option>
							  <option value="2">无效</option>
						</select>
							
					</div>
				</div>
				
				<div class="form-group">
					<label class="col-sm-2 control-label">图片上传</label>
					<div class="col-sm-10">
						<div class="input-group">
							<input type="file" class="form-control" id="productLabelFile"
								name="productLabelFile"> <span class="input-group-btn">
								<button class="btn btn-success" type="button"
									id="uploadproductLabel">上传图片</button>
							</span>
						</div>
					</div>
				</div>
				<div class="form-group">
					<label for="inputEmail3" class="col-sm-2 control-label">标签背景图</label>
					<div class="col-sm-10">
						<div class="col-sm-6">
							<img src="${entity.labelBackgroundImg }"
								class="img-rounded" style="max-width: 100px; max-height: 80px;"
								id="labelBackgroundImg2"/>
							<input type="hidden" class="form-control" name="labelBackgroundImg"
							id="labelBackgroundImg" value="${entity.labelBackgroundImg }">
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

<script type="text/javascript">
    
	 $("#label_category_id").val('${entity.labelCategoryId}');
	$("#parent_id").val('${entity.parentId}');
	$("#state_id").val('${entity.state}');
	
	$("#labelFontSize").val('${entity.labelFontSize}'); 
	function check_fun(){
		$("#tip").html("");
		var b = true;
		
		
		var labelName = $("#label_name").val();
		var labelCategoryId = $("#label_category_id").val();
		var state = $("#state_id").val();
		var labelFontSize = $("#labelFontSize").val(); 

		if(labelName == null || labelName.trim() == ''){
			$("#tip").html("请输入标签名称");
			b = false;
		}else if(labelCategoryId == null || labelCategoryId.trim() == ''){
			$("#tip").html("请选择产品类型");
			b = false;
		}else if(labelFontSize == null || labelFontSize.trim() == ''){
			$("#tip").html("请选择标签字体大小");
			b = false;
		}else if(state == null || state.trim() == ''){
			$("#tip").html("请选择标签状态");
			b = false;
		}
		return b;
	}
	$(function() {
		$('#uploadproductLabel').click(function() {
			var file = document.productLabelForm.productLabelFile.value;
			if(file == ""){
				$("#tip").html("请选择图片");
				return false;
			}
			$.ajaxFileUpload({
				type : "post",
				dataType : "json",
				fileElementId : 'productLabelFile',
				url : "upload/productLabel.htm",
				data : {
					"id" : "${entity.productLabelId }"
				},
				error : function(data) {
					alert("错误！");
				},
				success : function(data) {
					if (data.result = 'success') {
						console.info(data);
						alert("上传图片成功！");
						$('#labelBackgroundImg').val(data.imgUrl);
						$('#labelBackgroundImg2').attr("src", data.imgUrl);
						$("#tip").html("");
					} else {
						alert(data.msg);
					}
				}
			});
		});
	});
</script>