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
			<h3 id="myModalLabel">价格等级详情</h3>
		</div>
		<div class="modal-body" style="max-height: 700px; overflow-y: auto;"  id = "editBody">
			<form class="form-horizontal" method="post" id="skillPriceLevelForm" name="skillPriceLevelForm" role="form"
				action="skillPriceLevel/save${empty entity?'Insert':'Update' }.htm">

				<div class="form-group">
					<label class="col-sm-3 control-label">技能品类<font color="red">&nbsp;*</font></label>
					<div class="col-sm-6">
						<select id = "skillItemId" name="skillItemId" class="form-control" ${empty entity?'':'disabled="disabled"' }>
                        <c:forEach items="${itemList}" var="item">
                            <option value="${item.skillItemId}" ${item.skillItemId==entity.skillItemId?"selected='selected'" :""}>
                            	${item.skillItemName} 
                            </option> 
                        </c:forEach>
                    	</select>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label">价格等级<font color="red">&nbsp;*</font></label>
					<div class="col-sm-6">
					<select id = "priceLevel" name="priceLevel" class="form-control" ${empty entity?'':'disabled="disabled"' }>
                           <option value="1" ${entity.priceLevel==1?"selected='selected'" :""}>一等级</option> 
                           <option value="2" ${entity.priceLevel==2?"selected='selected'" :""}>二等级</option> 
                           <option value="3" ${entity.priceLevel==3?"selected='selected'" :""}>三等级</option> 
                    </select>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label">每半小时价格<font color="red">&nbsp;*</font></label>
					<div class="col-sm-6">
						<input type="number" min="0.0" step="0.1" id="halfPrice" class="form-control"  name="halfPrice" value="${entity.halfPrice}">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label">每一小时价格<font color="red">&nbsp;*</font></label>
					<div class="col-sm-6">
						<input type="number" min="0.0" step="0.1" id="onePrice" class="form-control"  name="onePrice" value="${entity.onePrice}">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label">每次价格<font color="red">&nbsp;*</font></label>
					<div class="col-sm-6">
						<input type="number" min="0.0" step="0.1" id="timePrice" class="form-control"  name="timePrice" value="${entity.timePrice}">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label">评分阀值<font color="red">&nbsp;*</font></label>
					<div class="col-sm-6">
						<input type="number" min="0" id="levelThreshold" class="form-control"  name="levelThreshold" value="${entity.levelThreshold}">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label">状态<font color="red">&nbsp;*</font></label>
					<div class="col-sm-6">
						<select id = "id="levelStatus" name="levelStatus" class="form-control" >
							<option value="0" ${entity.levelStatus==0?"selected='selected'" :""}>关闭</option>
							<option value="1" ${entity.levelStatus==1?"selected='selected'" :""}>显示次数</option>
							<option value="2" ${entity.levelStatus==2?"selected='selected'" :""}>显示一小时</option>
							<option value="4" ${entity.levelStatus==4?"selected='selected'" :""}>显示半小时</option>
							<option value="6" ${entity.levelStatus==6?"selected='selected'" :""}>同时显示半/一小时</option>
                    </select>
					</div>
				</div>
				<span id="tip"
                      style="color: red; font-size: 14px; margin-left: 20px;"></span>
			</form>
		</div>
		<div class="modal-footer">
		<span id="tip" style="color: red; font-size: 16px; margin-left: 20px;float:left;"></span>
			<button class="btn btn-default" data-dismiss="modal" aria-hidden="true" >取消</button>
			<button class="btn btn-primary" data-dismiss="modal" >保存</button>
		</div>
	</div>
</div>

<script type="text/javascript">
	$(function (){
		
	});
	function check_fun(){
		 $("#tip").html("");
	     var b = true;
	     var halfPrice = $("#halfPrice").val();
	   	 var onePrice = $("#onePrice").val();
	 	 var timePrice = $("#timePrice").val();
	 	var levelThreshold = $("#levelThreshold").val();
	 	
	 	if (halfPrice.trim() == '' || halfPrice == undefined) {
	         $("#tip").html("请输入半小时价格");
	         b = false;
	         return
	     }
	 	if (onePrice.trim() == '' || onePrice == undefined) {
	         $("#tip").html("请输入一小时价格");
	         b = false;
	         return
	     }
	 	if (timePrice.trim() == '' || timePrice == undefined) {
	         $("#tip").html("请输入一次价格");
	         b = false;
	         return
	     }
	 	if (levelThreshold.trim() == '' || levelThreshold == undefined) {
	         $("#tip").html("请输入等级阀值");
	         b = false;
	         return
	     }
	 	if(${empty entity}){
		 	 $.ajax({
	             type: "POST",
	             url: "skillPriceLevel/getPriceLevelExists.htm",
	             data: {skillItemId:$("#skillItemId").val(), priceLevel:$("#priceLevel").val()},
	             dataType: "json",
	             async:false, 
	             success: function(data){
	                if(data>0){
	                	$("#tip").html("已拥有该品类的等级，请重新输入");
	       	         b = false;
	       	         return
	                }
	             },
	             error:function(){
	            	 $("#tip").html("未知错误");
	       	         b = false;
	       	         return
	             }
	         });
	 	}
	 	
	 	$("#skillItemId").removeAttr("disabled");
	 	$("#priceLevel").removeAttr("disabled");
	 	
		return b;
	}

</script>