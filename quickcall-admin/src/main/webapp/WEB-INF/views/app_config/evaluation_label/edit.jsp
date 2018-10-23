<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="modal-dialog" style="width: 30%;">
    <div class="modal-content">
        <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">X</button>
            <h3 id="myModalLabel">${empty entity?'新增':'修改' }评价标签</h3>
        </div>
        <div class="modal-body">
            <form class="form-horizontal" method="post" id="sysUserForm" name="bannerForm"
                  action="evaluationLabel/save${empty entity?'Insert':'Update' }.htm" role="form">
                <div class="form-group">
                    <label class="col-sm-3 control-label">技能类型<font color="red">&nbsp;*</font></label>
                    <div class="col-sm-8">
                        <select class="form-control" name="skillItemId" id="add_skillItemId">
                            <c:forEach items="${skillList}" var="item">
                                <option value="${item.id}" <c:if test="${item.id eq entity.skillItemId}">selected</c:if>>${item.skillItemName}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label">客户性别<font color="red">&nbsp;*</font></label>
                    <div class="col-sm-8">
                        <select class="form-control" name="customerSex">
                            <option value="2" <c:if test="${entity.customerSex eq 2}">selected</c:if>>所有</option>
                            <option value="0" <c:if test="${entity.customerSex eq 0}">selected</c:if>>女</option>
                            <option value="1" <c:if test="${entity.customerSex eq 1}">selected</c:if>>男</option>
                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label">标签名称<font color="red">&nbsp;*</font></label>
                    <div class="col-sm-8">
                        <input type="hidden" name="labelId" id="labelId" value="${entity.labelId }"/>
                        <input type="text" class="form-control" name="labelName" id="label_name" maxlength="4" value="${entity.labelName }">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label">边框颜色编码</label>
                    <div class="col-sm-8">
                        <input type="text" class="form-control" name="borderColor" value="${entity.borderColor }" maxlength="10">
                    </div>
                </div>
                <span id="tip" style="color: red;font-size: 14px;margin-left:20px; "></span>
            </form>
        </div>

        <div class="modal-footer">
            <button class="btn btn-default" data-dismiss="modal" aria-hidden="true">取消</button>
            <button class="btn btn-primary" data-dismiss="modal">保存</button>
        </div>

    </div>
</div>
<script type="text/javascript">
    function check_fun() {
        $("#tip").html("");

        var labelName = $("#label_name").val();
        if (labelName == null || labelName.trim() == '') {
            $("#tip").html("请输入标签名称");
            return false;
        }

        var chekFlag = false;
        // 数据校验
        $.ajax({
            url: "evaluationLabel/checkData.htm",
            async: false,
            dataType: "JSON",
            type: 'post',
            data: {
                labelId: $("#labelId").val(),
                skillItemId: $("#add_skillItemId").val(),
                labelName: labelName
            },
            success: function (data) {
                if (data.code == "1") {
                    $("#tip").html(data.msg);
                } else {
                    if(window.confirm("确定是否保存标签【" + labelName + "】?")){
                        chekFlag = true;
                    }
                }
            },
            error: function (data) {
                alert("请求出错，请稍后再试！");
            }
        });

        return chekFlag;
    }

    $(function () {
    });
</script>