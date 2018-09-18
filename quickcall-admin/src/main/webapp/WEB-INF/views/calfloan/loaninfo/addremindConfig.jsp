<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<div class="modal-dialog">
	<div class="modal-content">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal"
				aria-hidden="true">X</button>
			<h3 id="myModalLabel">${empty entity?'新增':'修改' }提醒配置信息</h3>
		</div>
		<div class="modal-body">
			<form class="form-horizontal" method="post" id="remindConfigForm" name="remindConfigForm"
				action="remindConfig/save${empty entity?'Insert':'Update' }.htm"
				role="form">
				<div class="form-group">
					<label class="col-sm-4 control-label">提醒名称<font color="red">&nbsp;*</font></label>
					<div class="col-sm-6">
					   <c:if test="${empty entity==false}">
							<input type="hidden" value="${entity.remindConfigId }" id="remindConfigId" name="remindConfigId" /> 
							<input type="hidden" value="${entity.createTime }" id="createTime" name="createTime" /> 
							<input type="hidden" value="${entity.createMan }" id="createMan" name="createMan" /> 
							<input type="hidden" value="${entity.modifyTime }" id="modifyTime" name="modifyTime" /> 
							<input type="hidden" value="${entity.modifyMan }" id="modifyMan" name="modifyMan" /> 
						</c:if>
						<input type="text" class="form-control" id="remind_name" name="remindName" value="${entity.remindName}">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-4 control-label">提醒内容</label>
						<div class="col-sm-6">
								<textarea  id="remindContent" name="remindContent"  cols="45" rows="5" style="overflow:auto">${entity.remindContent}</textarea>
						</div>
				</div>
				
				<div class="form-group">
					<label class="col-sm-4 control-label">提醒方式</label>
					<div class="col-sm-6">
					     <input type="checkbox" id="remind_type1" name="remindType" value="1"/>邮箱提醒  <font color="red">(多个手机号用逗号分隔)</font><br>
						<textarea cols="45" rows="5" style="overflow:auto" id="mailRemindMan" name="mailRemindMan"  placeholder="提醒人邮箱账号">${entity.mailRemindMan}</textarea>
					</div>
				</div>
				
				<div class="form-group">
				   <label class="col-sm-4 control-label"></label>
					<div class="col-sm-6">
					  <input type="checkbox" id="remind_type2" name="remindType" value="2"/>短信提醒 <font color="red">(多个手机号用逗号分隔)</font><br>
	                   <textarea cols="45" rows="5" style="overflow:auto" id="mobileRemindMan" name="mobileRemindMan"  placeholder="提醒人电话号码">${entity.mobileRemindMan}</textarea>
					</div>
				</div>
				
				<div class="form-group">
				    <label class="col-sm-4 control-label"></label>
					<div class="col-sm-6">
					<input type="checkbox" id="remind_type3" name="remindType" value="3"/> 电话提醒  <font color="red">(多个手机号用逗号分隔)</font><br>
						<textarea cols="45" rows="5" style="overflow:auto" id="telephoneRemindMan" name="telephoneRemindMan"  placeholder="提醒人电话号码">${entity.telephoneRemindMan}</textarea>
					</div>
				</div>
				
				<div class="form-group">
					<label class="col-sm-4 control-label">状态 <font color="red">&nbsp;*</font></label>
					<div class="col-sm-6">
						<select class="form-control" id="state_id" name="state">
							  <option value="1">有效</option>
							  <option value="2">无效</option>
						</select>
							
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-4 control-label">备注</label>
						<div class="col-sm-6">
								<textarea  id="remark" name="remark"  cols="45" rows="5" style="overflow:auto">${entity.remark}</textarea>
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
	$("#state_id").val('${entity.state}');
	if('${entity.remindType}'!=''){
		 var remindType = '${entity.remindType}';
		  var remindTypes=remindType.split(",");
		  $.each(remindTypes,function(i,n){
		       $("input:checkbox[value="+remindTypes[i]+"]").attr('checked','true');
		  });
	}
}
	
	function check_fun(){
		$("#tip").html("");
		var b = true;
		
		var remindName = $("#remind_name").val();
		
		var mobileRemindMan = $("#mobileRemindMan").val();
		var mailRemindMan = $("#mailRemindMan").val();
		var telephoneRemindMan = $("#telephoneRemindMan").val();
		var state = $("#state_id").val();
		
		if(remindName == null || remindName.trim() == ''){
			$("#tip").html("请输入提醒名称");
			b = false;
		}else if(state == null || state.trim() == ''){
			$("#tip").html("请选择状态");
			b = false;
		}else if(mailRemindMan!=null && mailRemindMan.trim() != '' && !$('#remind_type1').is(':checked')){
				$("#tip").html("请选择邮箱提醒");
				b = false;
		}else if(mobileRemindMan!=null && mobileRemindMan.trim() != '' && !$('#remind_type2').is(':checked')){
				$("#tip").html("请选择短信提醒");
				b = false;
		}else if(telephoneRemindMan!=null && telephoneRemindMan.trim() != '' && !$('#remind_type3').is(':checked')){
				$("#tip").html("请选择电话提醒");
				b = false;
		}
		return b;
	}
	
</script>