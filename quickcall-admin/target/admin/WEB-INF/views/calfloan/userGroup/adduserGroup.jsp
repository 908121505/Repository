<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<div class="modal-dialog">
	<div class="modal-content">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal"
				aria-hidden="true">X</button>
			<h3 id="myModalLabel">${empty entity?'新增':'修改' }用户分组信息</h3>
		</div>
		<div class="modal-body">
			<form class="form-horizontal" method="post" id="userGroupForm" name="userGroupForm"
				action="userGroup/save${empty entity?'Insert':'Update' }.htm"
				role="form">
				<div class="form-group">
					<div class="col-sm-10">
					   <c:if test="${empty entity==false}">
							<input type="hidden" value="${entity.userGroupId }" id="userGroupId" name="userGroupId" /> 
							<input type="hidden" value="${entity.createTime }" id="createTime" name="createTime" /> 
							<input type="hidden" value="${entity.createMan }" id="createMan" name="createMan" /> 
							<input type="hidden" value="${entity.modifyTime }" id="modifyTime" name="modifyTime" /> 
							<input type="hidden" value="${entity.modifyMan }" id="modifyMan" name="modifyMan" /> 
						</c:if>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">分组名称<font color="red">&nbsp;*</font></label>
					<div class="col-sm-10">
						<input type="text" class="form-control" id="user_group_name" name="userGroupName" value="${entity.userGroupName}">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">渠道来源</label>
					<div class="col-sm-10">
						<label> 全选 <input type="checkbox" id="qx" value="全选"></label>
					</div>
				</div>
				
				<div class="form-group">
					<label class="col-sm-2 control-label"></label>
					<div class="col-sm-10">
							<c:forEach items="${channels}"  var="info2">
				                   <label style="padding:1px 2px 0px 2px;"> 
				                 	  <input type="checkbox"  onclick="clickNum(this)" name="checkbox" value="${info2}"/>${info2}
                                   </label>  
	                     </c:forEach>
	                     <input type="hidden" id="useId" name= "useId" value="">
					</div>
				</div>

				<div class="form-group">
					<label class="col-sm-2 control-label">是否为法院黑户</label>
					<label class="col-sm-2 control-label"></label>
					<div class="col-sm-10">
						<c:if test="${empty entity==true}">
							<label><input name="courtClacklist" type="radio" value="0" />未设置 </label>
							<label><input name="courtClacklist" type="radio" value="1" />否 </label>
							<label><input name="courtClacklist" type="radio" value="2" />是 </label>
						</c:if>
						<c:if test="${entity.courtClacklist==0}">
						<label><input name="courtClacklist" type="radio" value="0" checked="checked" />未设置 </label>
						<label><input name="courtClacklist" type="radio" value="1" />否 </label>
						<label><input name="courtClacklist" type="radio" value="2" />是 </label>
						</c:if>
						<c:if test="${entity.courtClacklist==1}">
							<label><input name="courtClacklist" type="radio" value="0"  />未设置 </label>
							<label><input name="courtClacklist" type="radio" value="1" checked="checked" />否 </label>
							<label><input name="courtClacklist" type="radio" value="2" />是 </label>
						</c:if>
						<c:if test="${entity.courtClacklist==2}">
							<label><input name="courtClacklist" type="radio" value="0" checked="checked" />未设置 </label>
							<label><input name="courtClacklist" type="radio" value="1" />否 </label>
							<label><input name="courtClacklist" type="radio" value="2" checked="checked" />是 </label>
						</c:if>
					</div>
				</div>

				<div class="form-group">
					<label class="col-sm-2 control-label">是否为金融黑户</label>
					<label class="col-sm-2 control-label"></label>
					<div class="col-sm-10">
						<c:if test="${empty entity==true}">
							<label><input name="financialBlacklist" type="radio" value="0"  />未设置 </label>
							<label><input name="financialBlacklist" type="radio" value="1"  />否 </label>
							<label><input name="financialBlacklist" type="radio" value="2"  />是 </label>
						</c:if>
						<c:if test="${entity.financialBlacklist==0}">
							<label><input name="financialBlacklist" type="radio" value="0" checked="checked" />未设置 </label>
							<label><input name="financialBlacklist" type="radio" value="1"  />否 </label>
							<label><input name="financialBlacklist" type="radio" value="2"  />是 </label>
						</c:if>
						<c:if test="${entity.financialBlacklist==1}">
							<label><input name="financialBlacklist" type="radio" value="0"  />未设置 </label>
							<label><input name="financialBlacklist" type="radio" value="1" checked="checked" />否 </label>
							<label><input name="financialBlacklist" type="radio" value="2"  />是 </label>
						</c:if>
						<c:if test="${entity.financialBlacklist==2}">
							<label><input name="financialBlacklist" type="radio" value="0"  />未设置 </label>
							<label><input name="financialBlacklist" type="radio" value="1"  />否 </label>
							<label><input name="financialBlacklist" type="radio" value="2" checked="checked" />是 </label>
						</c:if>
					</div>
				</div>


				<div class="form-group">
					<label class="col-sm-2 control-label">芝麻信用等级</label>
					<label class="col-sm-2 control-label"></label>
					<div class="col-sm-10">
						<c:if test="${empty entity==true}">
							<label><input name="zhimaCredit" type="radio" value="0"  />未设置 </label>
							<label><input name="zhimaCredit" type="radio" value="1"  />无 </label>
							<label><input name="zhimaCredit" type="radio" value="2"  />350-500 </label>
							<label><input name="zhimaCredit" type="radio" value="3"  />500-600 </label>
							<label><input name="zhimaCredit" type="radio" value="4"  />600-700 </label>
							<label><input name="zhimaCredit" type="radio" value="5"  />700以上 </label>
						</c:if>
						<c:if test="${entity.zhimaCredit==0}">
							<label><input name="zhimaCredit" type="radio" value="0" checked="checked" />未设置 </label>
							<label><input name="zhimaCredit" type="radio" value="1"  />无 </label>
							<label><input name="zhimaCredit" type="radio" value="2"  />350-500 </label>
							<label><input name="zhimaCredit" type="radio" value="3"  />500-600 </label>
							<label><input name="zhimaCredit" type="radio" value="4"  />600-700 </label>
							<label><input name="zhimaCredit" type="radio" value="5"  />700以上 </label>
						</c:if>
						<c:if test="${entity.zhimaCredit==1}">
							<label><input name="zhimaCredit" type="radio" value="0"  />未设置 </label>
						<label><input name="zhimaCredit" type="radio" value="1" checked="checked" />无 </label>
						<label><input name="zhimaCredit" type="radio" value="2"  />350-500 </label>
						<label><input name="zhimaCredit" type="radio" value="3"  />500-600 </label>
						<label><input name="zhimaCredit" type="radio" value="4"  />600-700 </label>
						<label><input name="zhimaCredit" type="radio" value="5"  />700以上 </label>
						</c:if>
						<c:if test="${entity.zhimaCredit==2}">
							<label><input name="zhimaCredit" type="radio" value="0"  />未设置 </label>
							<label><input name="zhimaCredit" type="radio" value="1"  />无 </label>
							<label><input name="zhimaCredit" type="radio" value="2" checked="checked" />350-500 </label>
							<label><input name="zhimaCredit" type="radio" value="3"  />500-600 </label>
							<label><input name="zhimaCredit" type="radio" value="4"  />600-700 </label>
							<label><input name="zhimaCredit" type="radio" value="5"  />700以上 </label>
						</c:if>
						<c:if test="${entity.zhimaCredit==3}">
							<label><input name="zhimaCredit" type="radio" value="0"  />未设置 </label>
							<label><input name="zhimaCredit" type="radio" value="1"  />无 </label>
							<label><input name="zhimaCredit" type="radio" value="2"  />350-500 </label>
							<label><input name="zhimaCredit" type="radio" value="3" checked="checked" />500-600 </label>
							<label><input name="zhimaCredit" type="radio" value="4"  />600-700 </label>
							<label><input name="zhimaCredit" type="radio" value="5"  />700以上 </label>
						</c:if>
						<c:if test="${entity.zhimaCredit==4}">
							<label><input name="zhimaCredit" type="radio" value="0"  />未设置 </label>
							<label><input name="zhimaCredit" type="radio" value="1"  />无 </label>
							<label><input name="zhimaCredit" type="radio" value="2"  />350-500 </label>
							<label><input name="zhimaCredit" type="radio" value="3"  />500-600 </label>
							<label><input name="zhimaCredit" type="radio" value="4" checked="checked" />600-700 </label>
							<label><input name="zhimaCredit" type="radio" value="5"  />700以上 </label>
						</c:if>
						<c:if test="${entity.zhimaCredit==5}">
							<label><input name="zhimaCredit" type="radio" value="0"  />未设置 </label>
							<label><input name="zhimaCredit" type="radio" value="1"  />无 </label>
							<label><input name="zhimaCredit" type="radio" value="2"  />350-500 </label>
							<label><input name="zhimaCredit" type="radio" value="3"  />500-600 </label>
							<label><input name="zhimaCredit" type="radio" value="4"  />600-700 </label>
							<label><input name="zhimaCredit" type="radio" value="5" checked="checked" />700以上 </label>
						</c:if>
					</div>
				</div>
			
			
				<div class="form-group">
					<label class="col-sm-2 control-label">备注</label>
					<div class="col-sm-10">
								<textarea  id="remark" name="remark"  cols="45" rows="5" style="overflow:auto">${entity.remark}</textarea>
					</div>
				</div>
				
				<div class="form-group">
					<label class="col-sm-2 control-label">标签状态 <font color="red">&nbsp;*</font></label>
					<div class="col-sm-10">
						<select class="form-control" id="state_id" name="state">
							  <option value="1">启用</option>
							  <option value="2">关闭</option>
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
	 var $useIds = $('${entity.useId}'.split(","));
	 $useIds.each(function (i,v){
		 $("input:checkbox[value='"+v+"']").prop("checked",true)
	 });
	 
	$("#state_id").val('${entity.state}');
}
	
	
	
	$(function() {
		$("#qx").click(function() {
			if (this.checked) {
				$("input[name='checkbox']").prop("checked",true); 
			} else {
				$("input[name='checkbox']").prop("checked",false); 
			}
		});
		
		
	/* 	$('input:checkbox').click(function () { 
			var valArr = new Array;
			$("input[name='checkbox']:checkbox:checked").each(function(i){
				valArr[i] = $(this).val();
		    });
		 	var vals = valArr.join(',');//转换为逗号隔开的字符串
		});  */
		 
		
	});

	function check_fun() {
		
		var valArr = new Array;
		$("input[name='checkbox']:checkbox:checked").each(function(i){
			valArr[i] = $(this).val();
	    });
	 	var vals = valArr.join(',');//转换为逗号隔开的字符串
	 	$("#useId").val(vals);
		
		$("#tip").html("");
		var b = true;

		var userGroupName = $("#user_group_name").val();
		var useId = $("#useId").val();
		var state = $("#state_id").val();

		if (userGroupName == null || userGroupName.trim() == '') {
			$("#tip").html("请输入分组名称");
			b = false;
		} else if (useId == null || useId.trim() == '') {
			$("#tip").html("请选择渠道来源");
			b = false;
		} else if (state == null || state.trim() == '') {
			$("#tip").html("请选择状态");
			b = false;
		}
		return b;
	}
</script>