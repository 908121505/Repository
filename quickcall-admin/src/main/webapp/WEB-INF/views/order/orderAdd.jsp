<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<link type="text/css"  href="resources/bootstrap/css/bootstrap-datetimepicker.min.css"  rel="stylesheet" />
<script type="text/javascript" language="javascript" src="resources/bootstrap/js/bootstrap-datetimepicker.min.js"  charset="utf-8"></script>


<div class="modal-dialog">
	<div class="modal-content">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal"
				aria-hidden="true">X</button>
			<h3 id="myModalLabel">订单详情</h3>
		</div>
		<div class="modal-body" style="max-height: 700px; overflow-y: auto;"  id = "editBody">
			<form class="form-horizontal" method="post" id="skillForm" name="skillForm" role="form"
				action="order/save${empty entity?'Insert':'Update' }.htm">

				<div class="form-group">
					<label class="col-sm-3 control-label">订单ID<font color="red">&nbsp;*</font></label>
					<div class="col-sm-6">
						<input type="text" id="name" class="form-control"  name="orderId" value="${entity.orderId}">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label">订单金额<font color="red">&nbsp;*</font></label>
					<div class="col-sm-6">
						<input type="text" id="orderAmounts" class="form-control"  name="orderAmounts" value="${entity.orderAmounts}">
					</div>
				</div>

			</form>
		</div>
		<div class="modal-footer">
		<span id="tip" style="color: red; font-size: 16px; margin-left: 20px;float:left;"></span>
			<button class="btn btn-default" data-dismiss="modal" aria-hidden="true" >取消</button>
			<%--<button class="btn btn-primary" data-dismiss="modal" >保存</button>--%>
		</div>
	</div>
</div>

<script type="text/javascript">
	Date.prototype.Format = function (fmt) { //author: meizz   
	    var o = {  
	        "M+": this.getMonth() + 1, //月份   
	        "d+": this.getDate(), //日   
	        "H+": this.getHours(), //小时   
	        "m+": this.getMinutes(), //分   
	        "s+": this.getSeconds(), //秒   
	        "q+": Math.floor((this.getMonth() + 3) / 3),
	        "S": this.getMilliseconds()
	    };  
	    if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));  
	    for (var k in o)  
	    if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));  
	    return fmt;  
	}  
	
	$(function() {
		/* $('#shangxianTime').datetimepicker({
			format : 'yyyy-mm-dd hh:ii:ss'
		}); */

	});
</script>