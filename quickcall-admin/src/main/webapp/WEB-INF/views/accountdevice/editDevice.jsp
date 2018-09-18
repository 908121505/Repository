<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<div class="modal-dialog">
    <div class="modal-content">
        <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal"
                    aria-hidden="true">X
            </button>
            <h3 id="myModalLabel">修改设备状态</h3>
        </div>
        <div class="modal-body">
            <form class="form-horizontal" method="post" id="sysUserForm" name="bannerForm"
                  action="accountDevice/saveUpdate.htm"
                  role="form">
                <div class="form-group">
                    <label class="col-sm-4 control-label">当前状态：<font color="red">&nbsp;*</font></label>
                    <div class="col-sm-8">
                        <label class="checkbox-inline"> <input type="radio" name="deviceState" value="0" ${entity.deviceState=='0'?'checked':'' }> 正常 </label>
                        <label class="checkbox-inline"> <input type="radio" name="deviceState" value="1" ${entity.deviceState=='1'?'checked':'' }> 限制 </label>
                    </div>
                    <input type="hidden" name="accountDeviceId" value="${entity.accountDeviceId}"/>
                    <input type="hidden" name="accountId" value="${entity.accountId}"/>
                    <input type="hidden" name="deviceId" value="${entity.deviceId}"/>
                </div>
                <span id="tip" style="color: red;font-size: 14px;margin-left:20px; "></span>
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

        var deviceState = $('input[name="deviceState"]:checked').val();

        if (deviceState == undefined) {
            $("#tip").html("请选择状态");
            return false;
        }
        return true;
    }
    $(function () {

    });
</script>