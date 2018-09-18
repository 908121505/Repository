<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<div class="modal-dialog">
	<div class="modal-content">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal"
				aria-hidden="true">X</button>
			<h3 id="myModalLabel">操作前后数据</h3>
		</div>
		<div class="modal-body">
			<form class="form-horizontal" method="post" id="productLabelForm" name="productLabelForm"
				action="dailyRevenue/saveUpdate.htm"
				role="form">
		        <input type="hidden" readonly="readonly"   class="form-control" id="id" name="id" value="${entity.id}">		
				<div class="form-group">
					<label class="col-sm-2 control-label">操作前数据</label>
					<div class="col-sm-10">
                   <textarea rows="10" cols="30">${entity.newContent }</textarea>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">操作后数据</label>
					<div class="col-sm-10">
					<textarea rows="10" cols="30" >${entity.oldContent }</textarea>
					</div>
				</div>
				
				
				<span id="tip" style="color: red;font-size: 14px;margin-left:20px; "></span>
			</form>
		</div>
		<div class="modal-footer">
			<button class="btn btn-default" data-dismiss="modal"
				aria-hidden="true">取消</button>
			
		</div>
	</div>
</div>

<script type="text/javascript">
    function check_fun(){
    	return true;
    }
    
    function  changeAmount(){
    	var tallyAmount=$("#cpa").val()*$("#registCount").val();
    	$("#tallyAmount").val(tallyAmount);
    }
    
	  
	
	
</script>