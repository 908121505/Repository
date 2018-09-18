<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="modal-dialog">
    <div class="modal-content">
        <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal"
                    aria-hidden="true">
            </button>
            <h3 id="myModalLabel">${empty entity?'新增':'修改' }系统参数配置</h3>
        </div>
        <div class="modal-body">
            <form class="form-horizontal" method="post" id="sysAppParam"
                  name="appParamForm"
                  action="appparam/save${empty entity?'Insert':'Update' }.htm"
                  role="form">
                <div class="form-group">
                    <label class="col-sm-2 control-label">参数对象<font
                            color="red">&nbsp;*</font></label>
                    <div class="col-sm-10">
                        <input type="hidden" class="form-control" name="sysParamId" value="${entity.sysParamId }">
                        <input type="text" class="form-control" name="sysParamValue" value="${entity.sysParamValue }">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">系统参数名称<font color="red">&nbsp;*</font></label>

                    <div class="col-sm-10">
                        <input type="text" class="form-control" name="sysParamName" value=${entity.sysParamName }>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">使用编号</label>

                    <div class="col-sm-10">
                        <input type="text" class="form-control" name="sysParamCode" value="${entity.sysParamCode }">
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
        var sysParamValue = $('input[name="sysParamValue"]').val();
        var sysParamName = $('input[name="sysParamName"]').val();
        var sysParamCode = $('input[name="sysParamCode"]').val();
        var remark = $('input[name="remark"]').val();
        var state = $('input[name="state"]:checked').val();
        if (state == undefined) {
            $("#tip").html("请选择状态");
            b = false;
        }
        if (sysParamValue == null || sysParamValue.trim() == '') {
            $("#tip").html("请输入参数对象");
            b = false;
        }
        if (sysParamName == null || sysParamName.trim() == '') {
            $("#tip").html("请输入参数名称");
            b = false;
        }
        if (sysParamCode == null || sysParamCode.trim() == '') {
            $("#tip").html("请输入使用编号");
            b = false;
        }
        return b;
    }
</script>
