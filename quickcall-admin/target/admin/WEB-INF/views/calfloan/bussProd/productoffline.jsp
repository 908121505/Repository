<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<link type="text/css" href="resources/bootstrap/css/bootstrap-datetimepicker.min.css" rel="stylesheet" />
<script type="text/javascript" language="javascript" src="resources/bootstrap/js/bootstrap-datetimepicker.min.js" charset="utf-8"></script>

<head>

</head>
<div class="modal-dialog">
	<div class="modal-content">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal"
				aria-hidden="true">X</button>
			<h3 id="myModalLabel">商户下线</h3>
		</div>
		<div class="modal-body" style="max-height:600px;overflow-y:auto;">
			<form class="form-horizontal" method="post" id="productOffline" name="productOffline"
				action="spread/offline.htm"   role="form">
			
			
				<div class="form-group">
					<label class="col-sm-4 control-label">公司名称</label>
					<div class="col-sm-8">
						<input type="text" id="companyName" readonly="readonly" class="form-control" name="companyName"
							value="${prodVO.companyName}">
					</div>
				</div>
				
				<div class="form-group">
					<label class="col-sm-4 control-label">商户名称</label>
					<div class="col-sm-8">
						<input type="text" id="productName" readonly="readonly"   class="form-control" name="productName"
							value="${prodVO.productName}">
					</div>
				</div>
				
				<div class="form-group">
					<label class="col-sm-4 control-label">上线时间</label>
					<div class="col-sm-8">
						<input type="text" id="onlineTime"  readonly="readonly"   class="form-control" name="onlineTime"
							value="${prodVO.onlineTime}">
					</div>
				</div>

				<div class="form-group">
					<label class="col-sm-4 control-label">下线理由</label>
					<div class="col-sm-8">
						<textarea  id="offlineReason" name="offlineReason"  cols="45" rows="5" style="overflow:auto">${prodVO.offlineReason}</textarea>
					</div>
				</div>
				
				<div class="form-group">
					<label class="col-sm-4 control-label">预约下线时间</label>
					<div class="col-sm-8">
						<input type="text" id="orderOfflineTime"  class="form-control" name="orderOfflineTime"
							value="${prodVO.orderOfflineTime}">
					</div>
				</div>
				
				<span id="tip" style="color: red;font-size: 14px;margin-left:20px; "></span>
			</form>
		</div>
		<div class="modal-footer">
			<button class="btn btn-default" data-dismiss="modal"
				aria-hidden="true">取消</button>
			<button class="btn btn-primary" data-dismiss="modal">下线</button>
		</div>
	</div>
</div>

<script type="text/javascript">

	function check_fun(){
		$("#tip").html("");
		var b = true;
		
		var offlineReason = $("#offlineReason").val();

		if(offlineReason == null || offlineReason.trim() == ''){
			$("#tip").html("请输入下线理由");
			b = false;
		}
		
		return b;
	}
	

		
	
		


	$(function() {
		/* 在textarea处插入文本 */
		(function($) {
			 $.fn.extend({
			   insertContent : function(myValue, t) {
			   var $t = $(this)[0];
			   if (document.selection) { // ie
			    this.focus();
			    var sel = document.selection.createRange();
			    sel.text = myValue;
			    this.focus();
			    sel.moveStart('character', -l);
			    var wee = sel.text.length;
			    if (arguments.length == 2) {
			    var l = $t.value.length;
			    sel.moveEnd("character", wee + t);
			    t <= 0 ? sel.moveStart("character", wee - 2 * t - myValue.length) : sel.moveStart( "character", wee - t - myValue.length);
			    sel.select();
			    }
			   } else if ($t.selectionStart
			    || $t.selectionStart == '0') {
			    var startPos = $t.selectionStart;
			    var endPos = $t.selectionEnd;
			    var scrollTop = $t.scrollTop;
			    $t.value = $t.value.substring(0, startPos)
			     + myValue
			     + $t.value.substring(endPos,$t.value.length);
			    this.focus();
			    $t.selectionStart = startPos + myValue.length;
			    $t.selectionEnd = startPos + myValue.length;
			    $t.scrollTop = scrollTop;
			    if (arguments.length == 2) {
			    $t.setSelectionRange(startPos - t,
			     $t.selectionEnd + t);
			    this.focus();
			    }
			   } else {
			    this.value += myValue;
			    this.focus();
			   }
			   }
			  })
			 })(jQuery);
		
	});
	

</script>