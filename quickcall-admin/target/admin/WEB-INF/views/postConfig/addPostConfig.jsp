<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="modal-dialog">
	<div class="modal-content">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal"
				aria-hidden="true">X</button>
			<h3 id="myModalLabel">${empty entity?'新增':'修改' }虚拟数据配置</h3>
		</div>
		<div class="modal-body">
			<form class="form-horizontal" method="post" id="sysUserForm" name="bannerForm"
				action="postConfig/save${empty entity?'Insert':'Update' }.htm"
				role="form">
				<div class="form-group">
					<label class="col-sm-2 control-label">数据名称<font color="red">&nbsp;*</font></label>
					<div class="col-sm-10">
						<input type="hidden" value="${entity.id }" name="id" /> 
						<input type="text" class="form-control required" id="name" name="name"
							value="${entity.name }">
					   
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">2h增长数据<font color="red">&nbsp;*</font></label>
					<div class="col-sm-10">
						<input type="text" maxlength="8" class="form-control" id="twoHours" name="twoHours"
							value="${entity.twoHours }"><br>
							
					</div>
				</div>
				
				<div class="form-group">
					<label class="col-sm-2 control-label">4h增长数据<font color="red">&nbsp;*</font></label>
					<div class="col-sm-10">
						<input type="text" maxlength="8" class="form-control" id="fourHours" name="fourHours"
							value="${entity.fourHours }"><br>
							
					</div>
				</div>
				
				<div class="form-group">
					<label class="col-sm-2 control-label">6h增长数据<font color="red">&nbsp;*</font></label>
					<div class="col-sm-10">
						<input type="text" maxlength="8" class="form-control" id="sixHours" name="sixHours"
							value="${entity.sixHours }"><br>
							
					</div>
				</div>
				
					<div class="form-group">
					<label class="col-sm-2 control-label">8h增长数据<font color="red">&nbsp;*</font></label>
					<div class="col-sm-10">
						<input type="text" maxlength="8" class="form-control" id="eightHours" name="eightHours"
							value="${entity.eightHours }"><br>
							
					</div>
				</div>
				
				<div class="form-group">
					<label class="col-sm-2 control-label">10h增长数据<font color="red">&nbsp;*</font></label>
					<div class="col-sm-10">
						<input type="text" maxlength="8" class="form-control" id="tenHours" name="tenHours"
							value="${entity.tenHours }"><br>
							
					</div>
				</div>
				
				<div class="form-group">
					<label class="col-sm-2 control-label">12h增长数据<font color="red">&nbsp;*</font></label>
					<div class="col-sm-10">
						<input type="text" maxlength="8" class="form-control" id="twelveHours" name="twelveHours"
							value="${entity.twelveHours }"><br>
							
					</div>
				</div>
				
				
				<div class="form-group">
					<label class="col-sm-2 control-label">状态<font color="red">&nbsp;*</font></label>
					<div class="col-sm-10">
						<label class="checkbox-inline"> <input type="radio"
							name="state" value="1" ${entity.state=='1' or (empty entity) ?'checked':'' }>
							开启
						</label> <label class="checkbox-inline"> <input type="radio"
							name="state" value="0" ${entity.state=='0'?'checked':'' }>
							关闭
						</label>
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
	
	if(temp("name")){
		$("#tip").text("数据名称不能为空");
		return false;
	}
	if(temp("twoHours")){
		$("#tip").text("2h不能为空");
		return false;
	}
	if(temp("fourHours")){
		$("#tip").text("4h不能为空");
		return false;
	}
	if(temp("sixHours")){
		$("#tip").text("6h不能为空");
		return false;
	}
	if(temp("eightHours")){
		$("#tip").text("8h不能为空");
		return false;
	}
	if(temp("tenHours")){
		$("#tip").text("10h不能为空");
		return false;
	}
	if(temp("twelveHours")){
		$("#tip").text("12h不能为空");
		return false;
	}
	
	
	return true;
}

function temp(id){
	return $("#"+id).val().length<1;
}
</script>