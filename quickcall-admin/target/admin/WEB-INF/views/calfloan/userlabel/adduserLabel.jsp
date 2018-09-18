<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<div class="modal-dialog">
	<div class="modal-content">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal"
				aria-hidden="true">X</button>
			<h3 id="myModalLabel">${empty entity?'新增':'修改' }产品标签信息</h3>
		</div>
		<div class="modal-body">
			<form class="form-horizontal" method="post" id="productLabelForm" name="productLabelForm"
				action="user/label/save${empty entity?'Insert':'Update' }.htm"
				role="form">
				<div class="form-group">
					<label class="col-sm-2 control-label">标签名称<font color="red">&nbsp;*</font></label>
					<div class="col-sm-10">
					   <c:if test="${empty entity==false}">
							<input type="hidden" value="${entity.productLabelId }" id="productLabelId" name="productLabelId" /> 
								<input type="hidden" value="${entity.labelCategoryId }" id="labelCategoryId" name="labelCategoryId" /> 
							<input type="hidden" value="${entity.createTime }" id="createTime" name="createTime" /> 
							<input type="hidden" value="${entity.createMan }" id="createMan" name="createMan" /> 
							<input type="hidden" value="${entity.modifyTime }" id="modifyTime" name="modifyTime" /> 
							<input type="hidden" value="${entity.modifyMan }" id="modifyMan" name="modifyMan" /> 
						</c:if>
						<input type="text" class="form-control" id="label_name" name="labelName" value="${entity.labelName}">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">状态<font color="red">&nbsp;*</font></label>
					<div class="col-sm-10">
					<label class="checkbox-inline"> <input type="radio"
							name="state" value="1" ${entity.state=='1'?'checked':'' }>
							启用
						</label> <label class="checkbox-inline"> <input type="radio"
							name="state" value="2" ${entity.state=='2'?'checked':'' }>
							暂停
						</label>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">标签ID<font color="red">&nbsp;*</font></label>
					<div class="col-sm-10">
					<input type="text" class="form-control" id="label_code" name="labelCode" value="${entity.labelCode}">
					</div>
				</div>
			    <div class="form-group">
					<label class="col-sm-2 control-label">说明<font color="red">&nbsp;*</font></label>
					<div class="col-sm-10">
						<textarea  style="width:100%;height:100px" name="remark">${entity.remark}
						</textarea>
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
    
function  isExist() {
	var flag='1';
    var labelName = $("#label_name").val();
    //labelName=  encodeURI(labelName,"UTF-8");
    var labelCode = $("#label_code").val();
    $.ajax({
        url: "user/label/isExist.htm" ,
        data:{'labelCode':labelCode,'labelName':labelName} ,
        type:"POST",
        async : false,
        success: function(data){
            data = JSON.parse(data);
            if(data.count == '1'){
                flag='2';
                return flag;
            }
        },
        error:function(data){
            $.globalMessenger().post({
                message: '系统错误',
                type: 'error',
                id: "Only-one-message",
                hideAfter: 2,
                hideOnNavigate: true
            });
        }
    });
    return flag;
}
    
    
    
	function check_fun(){
		$("#tip").html("");
		var b = true;
		var labelName = $("#label_name").val();
		var labelCode = $("#label_code").val();
		var state =  $('input[name="state"]:checked').val();;
       
		if(labelName == null || labelName.trim() == ''){
			$("#tip").html("请输入标签名称");
			b = false;
		}else if(state == null || state.trim() == ''){
			$("#tip").html("请选择标签状态");
			b = false;
		}else if(labelCode == null || labelCode.trim() == ''){
			$("#tip").html("输入标签ID");
			b = false;
		}else if(isExist()=='2'&&('${entity}'==''||'${entity}'==null||'${entity}'==undefined)){
			$("#tip").html("输入标签已经存在");
			b = false;	
		}
		
		return b;
	}

</script>