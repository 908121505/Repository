<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="modal-dialog">
	<div class="modal-content">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal"
				aria-hidden="true">X</button>
			<h3 id="myModalLabel">${empty entity?'新增':'修改' }合作平台配置</h3>
		</div>
		<div class="modal-body">
			<form class="form-horizontal" method="post" id="sysUserForm" name="bannerForm"
				action="platformInfoConfigController/save${empty entity?'Insert':'Update' }.htm"
				role="form">
				<div class="form-group">
					<label class="col-sm-2 control-label">合作平台名称<font color="red">&nbsp;*</font></label>
					<div class="col-sm-10">
						<input type="hidden" value="${entity.id }" name="id" /> 
						<input type="text" class="form-control required" id="platformName" name="platformName"
							value="${entity.platformName}"/>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">合作平台ID<font color="red">&nbsp;*</font></label>
					<div class="col-sm-10">
						<input type="text" class="form-control" id="platformId" name="platformId"
							value="${entity.platformId }"/>
					</div>
				</div>
				
				<div class="form-group">
					<label class="col-sm-2 control-label">平台类别<font color="red">&nbsp;*</font></label>
					<div class="col-sm-10">
						<input type="text" class="form-control" id="platformCategory" name="platformCategory"
							value="${entity.platformCategory }"/>
					</div>
				</div>
				
				<div class="form-group">
					<label class="col-sm-2 control-label">平台代码<font color="red">&nbsp;*</font></label>
					<div class="col-sm-10">
						<input type="text" class="form-control" id="platformCode" name="platformCode"
							value="${entity.platformCode }"/>
					</div>
				</div>
				
				<div class="form-group">
					<label class="col-sm-2 control-label">状态<font color="red">&nbsp;*</font></label>
					<div class="col-sm-10">
						<label class="checkbox-inline"> <input type="radio"
							name="state" value="1" ${entity.state=='1'|| (empty entity.state)?'checked':'' }>
							开启
						</label> <label class="checkbox-inline"> <input type="radio"
							name="state" value="0" ${entity.state=='0'?'checked':'' }>
							关闭
						</label>
					</div>
				</div>
				
				
				<div class="form-group">
					<label class="col-sm-2 control-label">说明</label>
					<div class="col-sm-10">
						<input type="text" class="form-control" name="remark"
							value="${entity.remark }">
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
	var platformName=$(".modal-body #platformName").val();
	var platformId=$(".modal-body #platformId").val();
	var platformCategory= $(".modal-body #platformCategory").val();
	var platformCode= $(".modal-body #platformCode").val();
    var b = true;
	if(platformName.length==0){
		$("#tip").html("请输入合作平台名称");
	    return false;
	}else{
		//检查合作平台是否重复
		$.ajax({
    		url: "platformInfoConfigController/queryRepeatName.htm?idUpd=${entity.id }&platformRepeatName="+  encodeURI(encodeURI(platformName)),  
    		async:false,
    		dataType:"JSON",
    		type:'post',
    		success: function(data){
    		 if(data>0){
    			    $("#tip").html("合作平台名称已存在");
   		    	    b = false;
   		           
   		        }  
    			 
    		}
    	});
	}
	
	if(!b){
		return false;
	}
	
	if(platformId.length==0){
		$("#tip").html("请输入合作平台ID");
		 return false;
	}
	
	if(platformCategory.length==0){
		$("#tip").html("请输入平台类别");
		 return false;
	}
	
	if(platformCode.length==0){
		$("#tip").html("请输入平台代码");
		 return false;
	}
	
	
   return true;
}

</script>