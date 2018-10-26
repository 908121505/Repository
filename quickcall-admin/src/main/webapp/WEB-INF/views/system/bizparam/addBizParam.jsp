<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="modal-dialog">
    <div class="modal-content">
        <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal"
                    aria-hidden="true">
            </button>
            <h3 id="myModalLabel">${empty entity?'新增':'修改' }业务参数配置</h3>
        </div>
        <div class="modal-body">
            <form class="form-horizontal" method="post" id="bizAppParam"
                  name="appParamForm"
                  action="bizparam/save${empty entity?'Insert':'Update' }.htm"
                  role="form">
               
                <div class="form-group">
                    <label class="col-sm-2 control-label">业务参数名称<font color="red">&nbsp;*</font></label>

                    <div class="col-sm-10">
                        <input type="text" class="form-control" name="bizParamName" value=${entity.bizParamName }>
                    </div>
                </div>
                 <div class="form-group">
                    <label class="col-sm-2 control-label">参数对象<font
                            color="red">&nbsp;*</font></label>
                    <div class="col-sm-10">
                        <input type="hidden" class="form-control" name="bizParamId" value="${entity.bizParamId }">
                        <input type="text" class="form-control" name="bizParamValue" value="${entity.bizParamValue }">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">使用编号</label>

                    <div class="col-sm-10">
                        <input type="text" class="form-control" name="bizParamCode" value="${entity.bizParamCode }">
                    </div>
                </div>
                
                    <div class="form-group">
                      
                            <label class="col-sm-2 control-label">业务参数类型</label>
                            <div class="col-sm-10">
                            <select class="form-control" id="bizParamTypeId" name="bizParamTypeId">
                                <c:forEach items="${bizParamTypes}" var="bizParamType">
                                <option value="${bizParamType.bizParamTypeId }"${entity.bizParamTypeId==bizParamType.bizParamTypeId?'selected':'' }  >${bizParamType.bizParamTypeName } </option>
                                </c:forEach>
                            </select>
                            
                        </div>
                    </div>
                
                <div class="form-group">
                    <label class="col-sm-2 control-label">状态<font color="red">&nbsp;*</font></label>

                    <div class="col-sm-10">
                        <label class="checkbox-inline">
                            <input type="radio" name="state" value="1" ${entity.state=='1'?'checked':'' }>有效
                        </label>
                        <label class="checkbox-inline">
                            <input type="radio"name="state" value="2" ${entity.state=='2'?'checked':'' }>无效
                    </label>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">备注</label>

                    <div class="col-sm-10">
                        <input type="text" class="form-control" name="remark" value="${entity.remark }">
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
        var b = true;
        var bizParamValue = $('input[name="bizParamValue"]').val();
        var bizParamName = $('input[name="bizParamName"]').val();
        var bizParamCode = $('input[name="bizParamCode"]').val();
        var remark = $('input[name="remark"]').val();
        var state = $('input[name="state"]:checked').val();
        if (state == undefined) {
            $("#tip").html("请选择状态");
            b = false;
        }
        if (bizParamValue == null || bizParamValue.trim() == '') {
            $("#tip").html("请输入参数对象");
            b = false;
        }
        if (bizParamName == null || bizParamName.trim() == '') {
            $("#tip").html("请输入参数名称");
            b = false;
        }
        if (bizParamCode == null || bizParamCode.trim() == '') {
            $("#tip").html("请输入使用编号");
            b = false;
        }
        return b;
    }
</script>
