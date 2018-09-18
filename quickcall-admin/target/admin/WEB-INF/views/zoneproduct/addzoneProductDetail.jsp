<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<div class="modal-dialog">
	<div class="modal-content">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal"
				aria-hidden="true">X</button>
			<h3 id="myModalLabel">${empty entity?'新增':'修改' }专区详细配置</h3>
		</div>
		<div class="modal-body">
			<form class="form-horizontal" method="post" id="zoneProductDetailForm" name="zoneProductDetailForm"
				action="zoneProductDetail/save${empty entity?'Insert':'Update' }.htm"
				role="form">
				<div class="form-group">
					<div class="col-sm-10">
					   <c:if test="${empty entity==false}">
							<input type="hidden" value="${entity.zoneProductDetailId }" id="zoneProductDetailId" name="zoneProductDetailId" /> 
							<input type="hidden" value="${entity.createTime }" id="createTime" name="createTime" /> 
							<input type="hidden" value="${entity.modifyTime }" id="modifyTime" name="modifyTime" /> 
						</c:if>
						<input type="text" value="${entity.zoneName }" id="zone_name" name="zoneName" />
					   <input type="text" value="${entity.productName }" id="product_name" name="productName" />
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">专区名称<font color="red" >&nbsp;*</font></label>
					<div class="col-sm-10">
						<select class="form-control" id="zoneId" name="zoneId" onchange="setZoneName()">
						           <option value="">-请选择-</option>
								<c:forEach var="info" items="${zoneProducts}">
									<option value="${info.zoneId}">${info.zoneName }</option>
								</c:forEach>
						</select>
					</div>
				</div>
				
				<div class="form-group">
					<label class="col-sm-2 control-label">产品<font color="red">&nbsp;*</font></label>
					<div class="col-sm-10">
						<select class="form-control" id=productId name="productId" onchange="setProductName()">
								<option value="">-请选择-</option>
								
						</select>
						
					</div>
				</div>
			<div class="form-group">
					<label class="col-sm-2 control-label">权重值<font color="red" >&nbsp;*</font></label>
					<div class="col-sm-10">
						<input type="number" value="${entity.sort }" id="sort" name="sort" />
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">备注</label>
					<div class="col-sm-10">
								<textarea  id="remark1" name="remark1"  cols="45" rows="5" style="overflow:auto">${entity.remark1}</textarea>
					</div>
				</div>
				
				<div class="form-group">
					<label class="col-sm-2 control-label">状态 <font color="red">&nbsp;*</font></label>
					<div class="col-sm-10">
						<select class="form-control" id="state_id" name="state">
							  <option value="1">有效</option>
							  <option value="2">无效</option>
						</select>
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
if('${entity}'!=''){
	 $("#zoneId").val('${entity.zoneId}');
	 $("#state_id").val('${entity.state}');
	 getpl('${entity.zoneId}');
}
function setZoneName(){
	$("#productId").empty(); //清空原数据
	var zoneId = $("#zoneId").val();
	var zoneName = $("#zoneId").find("option:selected").text();
	$("#zone_name").val(zoneName);
	getpl(zoneId);
}

function setProductName(){
	var productName = $("#productId").find("option:selected").text();
	$("#product_name").val(productName);

}

function getpl(zoneId){
	$.ajax({
		dataType: "json", 
		url: "zoneProductDetail/getpl.htm?zoneId="+zoneId,
		type:"POST",
		contentType: "application/json;charset=utf-8",
		async: false,
		success: function(data){
			//刷新列表
			  var datas = data.list;  
				 $.each(datas,function(index,value){
                     $("#productId").append("<option value='"+value.productId+"'>"+value.productName+"</option>");
                 })
		},
		error:function(data){
			alert("获取产品列表失败");
		}
		
		});
}
	function check_fun(){
		$("#tip").html("");
		var b = true;
		
		
		var zoneId = $("#zoneId").val();
		var productId = $("#productId").val();
		var state = $("#state_id").val();
		var sort = $("#sort").val();
		
		if(zoneId == null || zoneId.trim() == ''){
			$("#tip").html("请选择专区");
			b = false;
		}else if(productId == null || productId.trim() == ''){
			$("#tip").html("请选择产品");
			b = false;
		}else if(state == null || state.trim() == ''){
			$("#tip").html("请选择状态");
			b = false;
		}else if(sort == null || sort.trim() == ''){
			$("#tip").html("请输入权重值");
			b = false;
		}
		return b;
	}
	
</script>