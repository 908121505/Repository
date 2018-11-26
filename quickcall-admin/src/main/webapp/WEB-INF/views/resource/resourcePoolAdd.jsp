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
			<h3 id="myModalLabel">${empty entity?'新增资源池':'编辑资源池' }</h3>
		</div>
		<div class="modal-body" style="max-height: 700px; overflow-y: auto;"  id = "editBody">
			<form class="form-horizontal" method="post" id="resourcePoolForm"
				name="resourcePoolForm"
				action="resource/save${empty entity?'Insert':'Update' }.htm"
				role="form">


				<div class="form-group">
					<label class="col-sm-4 control-label">资源池名称<font color="red">&nbsp;*</font></label>
					<div class="col-sm-8">
						<input type="text" id="resourceName" class="form-control" name="resourceName" value="${entity.resourceName}"  maxlength="50"/>
						<input type="hidden" id="id" class="form-control"  name="id" value="${entity.id}" />
					</div>
				</div>
				<div class="form-group"  >
					<label class="col-sm-4 control-label">录入<font color="red">&nbsp;*</font></label>
					<div class="col-sm-8">
						<%--<input type="hidden" id="soundTotal" class="form-control" name="soundTotal" value="${entity.soundTotal}" />--%>
						<textarea id="soundTotalUIDStr" rows="10" cols="48" name="soundTotalUIDStr" placeholder="录入主播的APPID，换行录入多个">${entity.soundTotalUIDStr}</textarea>
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
		var name =  $("#resourceName").val();
		if(name == null  || name.trim() == ''){
			$("#tip").text("请输入资源池名称");
			return  false;
		}
		var soundTotalUIDStr =  $("#soundTotalUIDStr").val();
		if(soundTotalUIDStr == null  || soundTotalUIDStr.trim() == ''){
			$("#tip").text("录入主播的UID，换行录入多个");
			return  false;
		}
		return true;
	}

    //获取回车事件
    $('#soundTotalUIDStr').keypress(function(event){
        var keycode = (event.keyCode ? event.keyCode : event.which);
        var areaRows = $("#soundTotalUIDStr").val().split("\n").length; //获取行数
        if(keycode != '' && areaRows<=1001){//小于一千行
            //$('#soundTotal').val(areaRows);//替换默认数量0
        } else{
            alert("最多可输入1000个");
            return false;
        }
    });

    //鼠标离开事件
    /*$('#soundTotalUIDStr').blur(function(){
        var areaRows = $("#soundTotalUIDStr").val().split("\n").length;
        $('#soundTotal').val(areaRows);
        if($("#soundTotalUIDStr").val() == 0){//如果为空时
            $('#soundTotal').val(0);//恢复初始值
        }
    });*/

</script>