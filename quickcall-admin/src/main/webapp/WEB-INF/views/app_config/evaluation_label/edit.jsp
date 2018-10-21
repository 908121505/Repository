<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="modal-dialog" style="width: 50%;">
    <div class="modal-content">
        <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">X</button>
            <h3 id="myModalLabel">${empty entity?'新增':'修改' }评价标签</h3>
        </div>
        <div class="modal-body">
            <form class="form-horizontal" method="post" id="sysUserForm" name="bannerForm"
                  action="evaluationLabel/save${empty entity?'Insert':'Update' }.htm" role="form">
                <div class="form-group">
                    <label class="col-sm-2 control-label">技能类型<font color="red">&nbsp;*</font></label>
                    <div class="col-sm-10">
                        <select class="form-control" name="skillItemId">
                            <c:forEach items="${skillList}" var="item">
                                <option value="${item.id}" ${entity.skillItemId eq item.id ? 'selected':''}>${item.skillItemName}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">客户性别<font color="red">&nbsp;*</font></label>
                    <div class="col-sm-10">
                        <select class="form-control" name="customerSex">
                            <option value="0" ${entity.customerSex=='0'?'selected':''}>女</option>
                            <option value="1" ${entity.customerSex=='1'?'selected':''}>男</option>
                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">标签名称<font color="red">&nbsp;*</font></label>
                    <div class="col-sm-10">
                        <input type="hidden"  name="labelId" value="${entity.labelId }"/>
                        <input type="text" class="form-control" name="labelName" id="label_name" maxlength="10" value="${entity.labelName }">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">边框颜色编码</label>
                    <div class="col-sm-10">
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
        if (labelName && labelName.length > 10) {
            $("#tip").html("标签名称不能超过10个字");
            return false;
        }

        return true;
    }

    $(function () {
    });
</script>