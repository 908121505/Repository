<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<link type="text/css"  href="resources/bootstrap/css/bootstrap-datetimepicker.min.css"  rel="stylesheet" />
<script type="text/javascript" language="javascript" src="resources/bootstrap/js/bootstrap-datetimepicker.min.js" charset="utf-8"></script>


<div class="modal-dialog">
	<div class="modal-content">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal"
				aria-hidden="true">X</button>
			<h3 id="myModalLabel">${empty entity?'新增渠道开关':'编辑渠道开关' }</h3>
		</div>
		<div class="modal-body" style="max-height: 700px; overflow-y: auto;"  id = "editBody">
			<form class="form-horizontal" method="post" id="resourcePoolForm"
				name="resourcePoolForm"
				action="channelSwitch/save${empty entity?'Insert':'Update' }.htm"
				role="form">

				<div class="form-group">
					<label class="col-sm-4 control-label">渠道号<font color="red">&nbsp;*</font></label>
					<div class="col-sm-8">
						<input type="text" id="channel" class="form-control" name="channel" value="${entity.channel}"  maxlength="50"/>
						<input type="hidden" id="id" class="form-control" name="id" value="${entity.id}" />
					</div>
				</div>
				<div class="form-group"  >
					<label class="col-sm-4 control-label">版本号<font color="red">&nbsp;*</font></label>
					<div class="col-sm-8">
						<input type="text" id="version" class="form-control" name="version" value="${entity.version}"  maxlength="50"/>
					</div>
				</div>
				<%--<div class="form-group"  >
					<label class="col-sm-4 control-label">类型<font color="red">&nbsp;*</font></label>
					<div class="col-sm-8">
						&lt;%&ndash;类型（0=ios，1=android）&ndash;%&gt;
						<select class="form-control" id="type">
							<option value="">--请选择--</option>
							<option value="0" <c:if test="${entity.type == 0 }">selected</c:if> >ios</option>
							<option value="1" <c:if test="${entity.type == 1 }">selected</c:if> >android</option>
						</select>
					</div>
				</div>--%>
				<div class="form-group"  >
					<label class="col-sm-4 control-label">状态<font color="red">&nbsp;*</font></label>
					<div class="col-sm-8">
						<%--<input type="hidden" id="status" class="form-control" name="status" value="${entity.status}" />--%>
						<%--状态(0=关，1=开)--%>
						<select class="form-control" id="status" name="status" >
							<option value="">--请选择--</option>
							<option value="0" <c:if test="${entity.status == 0 }">selected</c:if> >关</option>
							<option value="1" <c:if test="${entity.status == 1 }">selected</c:if> >开</option>
						</select>
					</div>
				</div>
			</form>
		</div>
		<div class="modal-footer">
			<span id="tip" style="color: red; font-size: 16px; margin-left: 20px;float:left;"></span>
			<button class="btn btn-default" data-dismiss="modal" aria-hidden="true" >取消</button>
			<button class="btn btn-primary" data-dismiss="modal" >确认</button>
		</div>
	</div>
</div>

<script type="text/javascript">

	function check_fun() {
		var channel =  $("#channel").val();
		if(channel == null  || channel.trim() == ''){
			$("#tip").text("请输入渠道号");
			return  false;
		}
		var version =  $("#version").val();
		if(version == null  || version.trim() == ''){
			$("#tip").text("请输入版本号");
			return  false;
		}
        var status =  $("#status").val();
        if(status == null  || status.trim() == ''){
            $("#tip").text("请选择状态");
            return  false;
        }
		return true;
	}


</script>