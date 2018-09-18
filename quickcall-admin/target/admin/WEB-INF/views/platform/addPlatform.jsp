<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<div class="modal-dialog">
    <div class="modal-content">
        <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal"
                    aria-hidden="true"></button>
            <h3 id="myModalLabel">${empty entity?'新增':'修改' }平台</h3>
        </div>
        <div class="modal-body">
            <form class="form-horizontal" method="post" id="platForm"
                  action="platform/save${empty entity?'Insert':'Update' }.htm"
                  role="form">
                <div class="form-group">
                    <label class="col-sm-2 control-label">关联产品<font color="red">&nbsp;*</font></label>
                    <div class="col-sm-10">
                        <select class="form-control" id="product_id" name="productId">
                            <c:forEach var="info" items="${item}">
                                <option value="${info.productId }"
                                    ${fn:contains(entity.productId,info.productId)?'selected':'' }>
                                        ${info.productName}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">平台名称<font color="red">&nbsp;*</font></label>
                    <div class="col-sm-10">
                        <input type="hidden" name="platformsExtendId" value="${entity.platformsExtendId }">
                        <input type="text" class="form-control" name="platformName" value="${entity.platformName }">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">平台类别<font color="red">&nbsp;*</font></label>
                    <div class="col-sm-10">
                        <select class="form-control" name="platformType">
                            <option value="1" ${entity.platformType == 1 ?'selected':'' }>贷款平台</option>
                            <option value="2" ${entity.platformType == 2 ?'selected':'' }>信用卡</option>
                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">渠道<font color="red">&nbsp;*</font></label>
                    <div class="col-sm-10">
                        <input class="form-control" id="channelId" name="channelId" value="${entity.channelId }"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">合作方式<font color="red">&nbsp;*</font></label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" name="cooperation" value="${entity.cooperation }">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">限制数量<font color="red">&nbsp;*</font></label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" name="xlNum" value="${entity.xlNum }">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">状态<font color="red">&nbsp;*</font></label>
                    <div class="col-sm-10">
                        <label class="checkbox-inline"> <input type="radio"
                                                               name="state" value="1" ${entity.state=='1'?'checked':'' }>
                            开启
                        </label>
                        <label class="checkbox-inline">
                            <input type="radio" name="state" value="2" ${entity.state=='2'?'checked':'' }>
                            关闭
                        </label>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">备注</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" name="remark" value="${entity.remark }">
                    </div>
                </div>
                <c:if test="${not empty entity}">
                    <div class="form-group">
                        <label class="col-sm-2 control-label">密钥</label>
                        <div class="col-sm-10">
                            <input readonly type="text" class="form-control" name="platformKey" value="${entity.platformKey }">
                            <a class="btn btn-warning" onclick="isAgain()">重新生成密钥</a>
                        </div>
                    </div>
                </c:if>
                <span id="tip"
                      style="color: red; font-size: 14px; margin-left: 20px;"></span>
            </form>
        </div>
        <div class="modal-footer">
            <button class="btn btn-default" data-dismiss="modal"
                    aria-hidden="true">取消</button>
            <button class="btn btn-primary" data-dismiss="modal">保存</button>
        </div>
    </div>
</div>

<script>
    function isAgain() {
        var id = $("input[name=platformsExtendId]").val();
        $.ajax({
            url: "platform/refresh.htm?id=" + id,
            type:"POST",
            success: function(data){
                data = JSON.parse(data);
                if(data.code == 200){
                    $("input[name=platformKey]").val(data.key);
                    $.globalMessenger().post({
                        message: '新密钥生成成功！',
                        type: 'success',
                        id: "Only-one-message",
                        hideAfter: 2,
                        hideOnNavigate: true
                    });
                }else {
                    $.globalMessenger().post({
                        message: '新密钥生成失败：'+data,
                        type: 'error',
                        id: "Only-one-message",
                        hideAfter: 2,
                        hideOnNavigate: true
                    });
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
    }

</script>

<script type="text/javascript"
        src="resources/js/My97DatePicker/WdatePicker.js" charset="utf-8"></script>
<script type="text/javascript">
    function check_fun() {
        $("#tip").html("");
        var b = true;
        var articleClassId = $("#article_class_id option:selected").val();
        var platformName = $('input[name="platformName"]').val();
        var channelId = $('#channelId').val();
        var cooperation = $('input[name="cooperation"]').val();
        var state = $('input[name="state"]:checked').val();
        var xlNum = $('input[name="xlNum"]').val();

        if (platformName == null || platformName.trim() == '') {
            $("#tip").html("请输入平台名称");
            b = false;
        }
        if (channelId == null || channelId.trim() == '') {
            $("#tip").html("请输入渠道id");
            b = false;
        }
        if (cooperation == null || cooperation.trim() == '') {
            $("#tip").html("请输入合作方式");
            b = false;
        }
        if (state == undefined) {
            $("#tip").html("请选择状态");
            b = false;
        }
        if (xlNum == null || xlNum.trim() == '') {
            $("#tip").html("请输入限制数量");
            b = false;
        }
        return b;
    }
</script>
