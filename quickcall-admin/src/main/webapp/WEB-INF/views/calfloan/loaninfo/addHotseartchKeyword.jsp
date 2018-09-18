<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<div class="modal-dialog">
	<div class="modal-content">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal"
				aria-hidden="true">X</button>
			<h3 id="myModalLabel">${empty entity?'新增':'修改' }热搜产品信息</h3>
		</div>
		<div class="modal-body">
			<form class="form-horizontal" method="post" id="HotseartchKeywordForm" name="HotseartchKeywordForm"
				action="HotseartchKeyword/save${empty entity?'Insert':'Update' }.htm"
				role="form">
				<div class="form-group">
					<label class="col-sm-2 control-label">热搜产品名称<font color="red">&nbsp;*需与产品名称一致</font></label>
					<div class="col-sm-10">
					   <c:if test="${empty entity==false}">
					   	    <input type="hidden" value="${entity.seartchId }" id="seartchId" name="seartchId" /> 
							<input type="hidden" value="${entity.createTime }" id="createTime" name="createTime" /> 
							<input type="hidden" value="${entity.createMan }" id="createMan" name="createMan" /> 
							<input type="hidden" value="${entity.modifyTime }" id="modifyTime" name="modifyTime" /> 
							<input type="hidden" value="${entity.modifyMan }" id="modifyMan" name="modifyMan" /> 
						</c:if>
						<input type="text" class="form-control" id="content_i" name="content" value="${entity.content}">
					</div>
				</div>
				
				<div class="form-group">
					<label class="col-sm-2 control-label">权重值</label>
					<div class="col-sm-10">
						<input type="number" class="form-control" id="sort" name="sort" value="${entity.sort}">
					</div>
				</div>
				
				<div class="form-group">
					<label class="col-sm-2 control-label">搜索类型<font color="red">&nbsp;*</font></label>
					<div class="col-sm-10">
							<select class="form-control" id="seartch_type" name="seartchType">
						             <option value="1">热门</option>
									 <option value="0">其他</option>
						</select>
					</div>
				</div>
				
				<div class="form-group">
					<label class="col-sm-2 control-label">状态 <font color="red">&nbsp;*</font></label>
					<div class="col-sm-10">
						<select class="form-control" id="state_id" name="state">
							  <option value="1">有效</option>
							  <option value="2">无效</option>
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
	 $("#seartch_type").val('${entity.seartchType}');
	$("#state_id").val('${entity.state}');
}    
	function check_fun(){
		$("#tip").html("");
		var b = true;
		
		
		var content = $("#content_i").val();
		var seartch_type = $("#seartch_type").val();
		var state = $("#state_id").val();
		
		if(content == null || content.trim() == ''){
			$("#tip").html("请输入热搜产品名称");
			b = false;
		}else if(seartch_type == null || seartch_type.trim() == ''){
			$("#tip").html("请选择搜索类型");
			b = false;
		}else if(state == null || state.trim() == ''){
			$("#tip").html("请选择状态");
			b = false;
		}
		return b;
	}
	
	
</script>