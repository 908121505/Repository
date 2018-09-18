<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="modal-dialog">
    <div class="modal-content">
        <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal"
                    aria-hidden="true">
            </button>
            <h3 id="myModalLabel">${empty entity?'新增':'修改' }模块内容</h3>
        </div>
        <div class="modal-body">
            <form class="form-horizontal" method="post" id="bizAppParam"
                  name="appParamForm"
                  action="moduleManager/save${empty entity?'Insert':'Update' }.htm"
                  role="form">
               
                <div class="form-group">
                    <label class="col-sm-2 control-label">模块类型<font color="red">&nbsp;*</font></label>
                    <input type="hidden" id="modelType"  name ="modelType" value="${empty entity?'公司性质':entity.modelType }">
                    <div class="col-sm-10">
                    <select name="modelNo" class="form-control" onchange="modelchange(this);">
                    <option value="GSXZ" ${entity.modelNo=='GSXZ'?'selected':''}>公司性质</option>
                     <option value="HZMS" ${entity.modelNo=='HZMS'?'selected':''}>合作模式</option>
                      <option value="JSZQ" ${entity.modelNo=='JSZQ'?'selected':''}>结算周期</option>
                       <option value="TGLX" ${entity.modelNo=='TGLX'?'selected':''}>活动类型</option>
                    </select>
                    
                    </div>
                </div>
                 <div class="form-group">
                    <label class="col-sm-2 control-label">模块内容<font
                            color="red">&nbsp;*</font></label>
                    <div class="col-sm-10">
                        <input type="hidden" class="form-control" name="id" value="${entity.id }">
                        <input type="text" class="form-control" id="modelContent" name="modelContent" value="${entity.modelContent }">
                    </div>
                </div>
                 <div class="form-group">
                    <label class="col-sm-2 control-label">权重<font
                            color="red">&nbsp;*</font></label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" id="weight" name="weight" value="${entity.weight }">
                    </div>
                </div>
                
				<span id="tip"
                      style="color: red; font-size: 14px; margin-left: 20px;"></span>
            </form>
        </div>
        <div class="modal-footer">
            <button class="btn btn-default" data-dismiss="modal"
                    aria-hidden="true">取消
            </button>
            <button class="btn btn-primary" data-dismiss="modal">保存</button>
        </div>
    </div>
</div>
<script type="text/javascript"
        src="resources/js/My97DatePicker/WdatePicker.js" charset="utf-8"></script>
<script type="text/javascript">
    function check_fun() {
        $("#tip").html("");
       var b=true;
        var  modelContent=$("#modelContent").val();
        var  weight=$("#weight").val();
        if (modelContent == null || modelContent.trim() == '') {
            $("#tip").html("请输入模块内容");
            b = false;
        }
        if (weight == null || weight.trim() == ''||isNaN(weight)) {
            $("#tip").html("请正确输入权重");
            b = false;
        }
        return b;
    }
    
    function modelchange(obj){
    	var selectValue=obj.value;
    	var inputValue='';
    	if(selectValue=='GSXZ'){
    		inputValue='公司性质';
    	}else if(selectValue=='HZMS'){
    		inputValue='合作模式';
    	}else if(selectValue=='JSZQ'){
    		inputValue='结算周期';
    	}else if(selectValue=='TGLX'){
    		inputValue='活动类型';
    	}
    	$("#modelType").val(inputValue);
    	
    }
    
    
    
    
    
    
    
    
    
    
</script>
