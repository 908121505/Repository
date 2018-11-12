<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<link type="text/css"  href="resources/bootstrap/css/bootstrap-select.min.css"  rel="stylesheet" />
<script type="text/javascript" language="javascript" src="resources/bootstrap/js/bootstrap-select.min.js"  charset="utf-8"></script>

<div class="modal-dialog" style="width: 50%;">
    <div class="modal-content">
        <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">X</button>
            <h3 id="myModalLabel">${empty entity?'新增':'修改' }优惠券</h3>
        </div>
        <div class="modal-body">
            <form class="form-horizontal" method="post" id="sysUserForm" name="advertisementForm"
                  action="coupon/save${empty entity?'Insert':'Update' }.htm"
                  role="form">

                <%--<input  class="form-control" name="id"  value="${entity.id }">
                <input  class="form-control" name="imageUrl" id="advertisementImgFile_input" value="${entity.imageUrl }">--%>

                <input type="hidden" class="form-control" name="couponId" value="${entity.couponId }">

                <div class="form-group">
                    <label class="col-sm-2 control-label">券的名称<font color="red">&nbsp;*</font></label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control required" id="couponName" name="couponName"
                               value="${entity.couponName }">
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-sm-2 control-label">是否永久<font color="red">&nbsp;*</font></label>
                    <div class="col-sm-10">
                        <select class="form-control" id="isPermanent" name="isPermanent">
                            <option value="0" ${entity.isPermanent=='0'?'selected':''}>不是永久</option>
                            <option value="1" ${entity.isPermanent=='1'?'selected':''}>永久</option>
                        </select>
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-sm-2 control-label">券类型<font color="red">&nbsp;*</font></label>
                    <div class="col-sm-10">
                        <select class="form-control" id="couponType" name="couponType">
                            <option value="0" ${entity.couponType=='0'?'selected':''}>抵扣券</option>
                            <option value="1" ${entity.couponType=='1'?'selected':''}>打折券</option>
                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">领取方式<font color="red">&nbsp;*</font></label>
                    <div class="col-sm-10">
                        <select class="form-control" id="getWay" name="getWay">
                            <option value="0" ${entity.getWay=='0'?'selected':''}>直接领取</option>
                            <option value="1" ${entity.getWay=='1'?'selected':''}>下单领取</option>
                        </select>
                    </div>
                </div>
                <%--<div class="form-group">
                    <label class="col-sm-2 control-label">券码<font color="red">&nbsp;*</font></label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control required" id="couponCode" name="couponCode"
                               value="${entity.couponCode }">
                    </div>
                </div>--%>
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

                <div class="form-group">
                    <label class="col-sm-2 control-label">券价值<font color="red">&nbsp;*</font></label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control required" id="couponPrice" name="couponPrice"
                               value="${entity.couponPrice }">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">选择活动<font color="red">&nbsp;*</font></label>
                    <div class="col-sm-10">
                        <select  class="form-control" id="activitySelect" name="activityId">
                            <option value="">请选择</option>
                            <c:if test="${not empty activityList}">
                                <c:forEach items="${activityList}" var="activity">
                                    <option value=${activity.activityId}>名称：${activity.activityName}       编码：${activity.activityCode}</option>
                                    <%--<option value= ${activityList.activityId}>名称：+${activityList.activityName}+       编码：+${activityList.activityCode}</option>--%>
                                </c:forEach>
                            </c:if>
                            <%--<option value="1">1.0</option>
                            <option value="2">2.0</option>--%>
                        </select>

                    </div>
                </div>
                <c:if test="${not empty entity}">
                    <div class="form-group">
                        <label class="col-sm-2 control-label">当前活动名称<font color="red">&nbsp;*</font></label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control required" id="activityName" name="activityName" value="${entity.activityName }" readonly="readonly"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">当前活动编码<font color="red">&nbsp;*</font></label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control required" id="activityCode" name="activityCode" value="${entity.activityCode }" readonly="readonly"/>
                        </div>
                    </div>
                </c:if>

                <div class="form-group">
                    <label class="col-sm-2 control-label">选择品类</label>
                    <div class="col-sm-10">
                        <select  class="form-control selectpicker" id="skillItemSelect" name="skillItemIdList" multiple>
                            <option value='clear'>清空当前所属品类</option>
                            <c:if test="${not empty skillItemList}">
                                <c:forEach items="${skillItemList}" var="skillItem">
                                    <option value=${skillItem.skillItemId}>${skillItem.skillItemName}</option>
                                </c:forEach>
                            </c:if>
                            <%--<option value="1">1.0</option>
                            <option value="2">2.0</option>--%>
                        </select>

                    </div>
                </div>
                <c:if test="${not empty entity}">
                    <div class="form-group">
                        <label class="col-sm-2 control-label">当前所属品类<font color="red">&nbsp;*</font></label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control required" id="skillItemName" name="skillItemName" value="${entity.skillItemName }" readonly="readonly"/>
                        </div>
                    </div>
                </c:if>


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

        var skillItemSelectValue = $("#skillItemSelect").val();
        if(skillItemSelectValue != null){
            var skillItemSelectValueStr = skillItemSelectValue.toString();
            if(skillItemSelectValueStr.indexOf("clear") != -1 && skillItemSelectValueStr != "clear"){
                $("#tip").html("清空当前所属品类选项不能与其他选项同时选择！");
                return false;
            }
        }

        var couponName = $("#couponName").val();
        if(couponName == null || couponName.trim() == ''){
        $("#tip").html("请输入券名称");
        return false;
        }
       /* var couponCode = $("#couponCode").val();
        if(couponCode == null || couponCode.trim() == ''){
            $("#tip").html("请输入券码");
            return false;
        }*/
        var couponPrice = $("#couponPrice").val();
        if(couponPrice == null || couponPrice.trim() == ''){
            $("#tip").html("请输入券价值");
            return false;
        }

        var isPermanent = $("#isPermanent").val();

        if(isPermanent != 0 && isPermanent != 1){
            $("#tip").html("请选择是否永久");
            return false;
        }

        var getWay = $("#getWay").val();

        if(getWay != 0 && getWay != 1){
            $("#tip").html("请选择领取方式");
            return false;
        }

        var couponType = $("#couponType").val();
        if(couponType != 0 && couponType != 1){
            $("#tip").html("请选择券类型");
            return false;
        }

        var activitySelect = $("#activitySelect").val();

        if(('${entity.activityId}' == '') && (activitySelect == null || activitySelect  == '')){
            $("#tip").html("请选择活动");
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

    $(document).ready(function () {

        //多选
        $('#skillItemSelect').selectpicker();

    });


</script>

<script type="text/javascript" src="resources/js/My97DatePicker/WdatePicker.js" charset="utf-8"></script>

