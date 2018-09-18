<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<div class="modal-dialog">
    <div class="modal-content">
        <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal"
                    aria-hidden="true">
            </button>
            <h3 id="myModalLabel">蜜蜂报告-用户手机详情</h3>
        </div>
        <div class="modal-body">
            <form class="form-horizontal" method="post" id="productRuleForm" name="productRuleForm"
                  action="jxlMgOrderInfo/save${empty entity?'Insert':'Update' }.htm"
                  role="form">
                <div class="form-group">
                    <div class="col-sm-8">
                        <%--<input type="text" readonly="readonly"  class="form-control" name="responseData" value="${entity.responseData}"/>--%>
                        <textarea readonly="readonly"cols="78" rows="30"   name="responseData" >${entity.userPhoneInfo}</textarea>
                    </div>
                </div>	
                <span id="tip" style="color: red;font-size: 14px;margin-left:20px; "></span>
            </form>
        </div>
        <div class="modal-footer">
            <button class="btn btn-default" data-dismiss="modal" aria-hidden="true">取消</button>
        </div>
    </div>
</div>

<script type="text/javascript">
</script>