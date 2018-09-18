<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<div class="modal-dialog">
	<div class="modal-content">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal"
				aria-hidden="true">X</button>
			<h3 id="myModalLabel">信息编辑</h3>
		</div>
		<div class="modal-body">
			<form class="form-horizontal" method="post" id="productLabelForm" name="productLabelForm"
				action="waistcoatTrial/saveUpdate.htm"
				role="form">
		<input type="hidden" readonly="readonly"   class="form-control" id="id" name="id" value="${entity.id}"/>	
		<input type="hidden" readonly="readonly"   class="form-control" id="waistcoatName" name="waistcoatName" value="${entity.waistcoatName}"/>			
				<div class="form-group">
					<label class="col-sm-2 control-label">APP名称</label>
					<div class="col-sm-10">
						<input type="text" readonly="readonly"  class="form-control" id="name" name="name" value="${entity.name}"/>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">屏蔽渠道(多渠道号用,分割)<font color="red">&nbsp;*</font></label>
					<div class="col-sm-10">
					<textarea rows="4" cols="58"  name="chanel"  id="chanel">${entity.chanel}</textarea>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">屏蔽版本号<font color="red">&nbsp;*</font></label>
					<div class="col-sm-10">
						<input type="text"  class="form-control" id="version" name="version"  value="${entity.version}"/>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">状态<font color="red">&nbsp;*</font></label>
					<div class="col-sm-10">
					
					<label class="checkbox-inline">
                            <input type="radio" name="status" value="0" ${entity.status=='0'?'checked':'' }>开启
                        </label>
                        <label class="checkbox-inline">
                            <input type="radio"name="status" value="1" ${entity.status=='1'?'checked':'' }>关闭
                    </label>
					
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">备注</label>
					<div class="col-sm-10">
                           <textarea rows="4" cols="58"  name="remark" >${entity.remark}</textarea>

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
    function check_fun(){
    	var b=true;
    	var version=$('#version').val();
    	var chanel=$('#chanel').val();
    	 var status = $('input[name="status"]:checked').val();
    	 if (status == undefined) {
             $("#tip").html("请选择状态");
             b = false;
         }else if (version == null || version.trim() == '') {
             $("#tip").html("请输入屏蔽版本号");
             b = false;
         }
         else if (chanel == null || chanel.trim() == '') {
             $("#tip").html("请输入屏蔽渠道号");
             b = false;
         }
    	return b;
    }
    
    
    
  
    
	  
	
	
</script>