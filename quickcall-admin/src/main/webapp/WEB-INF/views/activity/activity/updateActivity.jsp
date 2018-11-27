<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<link type="text/css"  href="resources/bootstrap/css/bootstrap-select.min.css"  rel="stylesheet" />
<script type="text/javascript" language="javascript" src="resources/bootstrap/js/bootstrap-select.min.js"  charset="utf-8"></script>

<div class="modal-dialog" style="width: 50%;">
    <div class="modal-content">
        <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">X</button>
            <h3 id="myModalLabel">${empty entity?'新增':'修改' }活动</h3>
        </div>
        <div class="modal-body">
            <form class="form-horizontal" method="post" id="sysUserForm" name="advertisementForm"
                  action="activity/save${empty entity?'Insert':'Update' }.htm"
                  role="form">

                <%--<input  class="form-control" name="id"  value="${entity.id }">
                <input  class="form-control" name="imageUrl" id="advertisementImgFile_input" value="${entity.imageUrl }">--%>

                <input type="hidden" class="form-control" name="activityId" value="${entity.activityId }">

                <div class="form-group">
                    <label class="col-sm-2 control-label">活动名称<font color="red">&nbsp;*</font></label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control required" id="activityName" name="activityName"
                               value="${entity.activityName }">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">活动编码<font color="red">&nbsp;*</font></label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control required" id="activityCode" name="activityCode" value="${entity.activityCode }">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">生效时间<font color="red">&nbsp;*</font></label>
                    <div class="col-sm-10">
                        <input type="text" id="banner_startTime" class="form-control"
                               onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'1950-01-01',maxDate:'#F{$dp.$D(\'banner_endTime\')}'})"
                               value="${entity.startTime }" name="startTime" autocomplete="off"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">结束时间<font color="red">&nbsp;*</font></label>
                    <div class="col-sm-10">
                        <input type="text" id="banner_endTime" class="form-control"
                               onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'banner_startTime\')}',maxDate:'2050-12-31'})"
                               value="${entity.endTime }" name="endTime" autocomplete="off"/>
                    </div>
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

<script type="text/javascript">
    function check_fun() {

        var activityName = $("#activityName").val();
        if(activityName == null || activityName.trim() == ''){
        $("#tip").html("请输入活动名称");
        return false;
        }
        var activityCode = $("#activityCode").val();
        if(activityCode == null || activityCode.trim() == ''){
            $("#tip").html("请输入活动编码");
            return false;
        }

        var startTime = $("#banner_startTime").val();
        var endTime = $("#banner_endTime").val();
        if(startTime == null || startTime.trim() == ''){
        $("#tip").html("请输入开始时间");
        return false;
        }
        if(endTime == null || endTime.trim() == ''){
        $("#tip").html("请输入结束时间");
        return false;
        }
        if(endTime <= startTime){
            $("#tip").html("开始时间必须大于结束时间");
            return false;
        }

        return true;
    }

</script>

<script type="text/javascript" src="resources/js/My97DatePicker/WdatePicker.js" charset="utf-8"></script>

