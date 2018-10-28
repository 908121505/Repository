<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<link type="text/css"  href="resources/bootstrap/css/bootstrap-select.min.css"  rel="stylesheet" />
<script type="text/javascript" language="javascript" src="resources/bootstrap/js/bootstrap-select.min.js"  charset="utf-8"></script>


<div class="modal-dialog">
	<div class="modal-content">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal"
				aria-hidden="true">X</button>
			<h3 id="myModalLabel">编辑</h3>
		</div>
		<div class="modal-body" style="max-height: 700px; overflow-y: auto;"  id = "editBody">
			<form class="form-horizontal" method="post" id="resourceConfigForm" name="resourceConfigForm" role="form"
				action="resourceConfig/saveUpdate.htm">
				<input type="hidden" name="resourceConfigId" id="resourceConfigId" value="${entity.resourceConfigId}"/>
				<input type="hidden" name="resourceConfigSkillList" id="resourceConfigSkillList" value="${entity.resourceConfigSkillList}"/>
				<div class="form-group">
					<label class="col-sm-3 control-label">当前策略<font color="red">&nbsp;*</font></label>
					<div class="col-sm-6">
					<select id = "strategy" name="strategy" class="form-control" onchange="changeStrantegy()">
                           <option value="1" ${entity.strategy==1?"selected='selected'" :""}>自然推荐</option> 
                           <option value="2" ${entity.strategy==2?"selected='selected'" :""}>运营推荐</option> 
                    </select>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label">启用品类<font color="red">&nbsp;*</font></label>
					<div class="col-sm-6">
						<select id = "skillItemId" name="skillItemId" title="请选择" class="form-control selectpicker" multiple>
                        <c:forEach items="${skillItemList}" var="item">
                            <option value="${item.id}" >
                            	${item.skillItemName} 
                            </option> 
                        </c:forEach>
                    	</select>
					</div>
				</div>
				<div class="form-group hid" >
					<label class="col-sm-3 control-label">资源池<font color="red">&nbsp;*</font></label>
					<div class="col-sm-6">
						<select id = "resourcePoolId" name="resourcePoolId" class="form-control" >
                        <c:forEach items="${resourcePoolList}" var="item">
                            <option value="${item.id}" ${item.id==entity.resourcePoolId?"selected='selected'" :""}>
                            	${item.resourceName} 
                            </option> 
                        </c:forEach>
                    	</select>
					</div>
				</div>
				<div class="form-group" >
					<label class="col-sm-3 control-label">排除声优<font color="red">&nbsp;*</font></label>
					<div class="col-sm-6">
					<textarea id="exCusList" name="exCusList" rows="6" cols="20"class="form-control" >${entity.exCusList}</textarea>
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
		$('#skillItemId').selectpicker();
		var skill = $('#resourceConfigSkillList').val();
  		var ids = skill.split(',');
  		$('#skillItemId').selectpicker("val",ids).trigger("change");
  		
  		changeStrantegy();
	});
	
	function changeStrantegy(){
		var val = $('#strategy').val();
		if(val==1){
			$(".hid").each(function() {
				$(this).hide();
			});
		}else if(val==2){
			$(".hid").each(function() {
				$(this).show();
			});
		}
	}
	
	function check_fun(){
		 $("#tip").html("");
	     var b = true;
	     /* var halfPrice = $("#halfPrice").val();
	 	
	 	if (halfPrice.trim() == '' || halfPrice == undefined) {
	         $("#tip").html("请输入半小时价格");
	         b = false;
	         return
	     } */
		
		$("#resourceConfigSkillList").val($("#skillItemId").val());
		return b;
	}

</script>