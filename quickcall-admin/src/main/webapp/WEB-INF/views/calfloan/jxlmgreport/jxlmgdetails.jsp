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
            <h3 id="myModalLabel">蜜罐报告信息详情</h3>
        </div>
        <div class="modal-body">
            <form class="form-horizontal" method="post" id="productRuleForm" name="productRuleForm"
                  action="jxlMgOrderInfo/save${empty entity?'Insert':'Update' }.htm"
                  role="form">
               
                <div class="form-group">
                    <label class="col-sm-4 control-label">手机号<font color="red">&nbsp;</font></label>
                    <div class="col-sm-8">
                        <input type="text" readonly="readonly"  class="form-control"  value="${entity.phoneNum}">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-4 control-label">用户灰度信息</label>
                    <div class="col-sm-8">
                        <%--<input type="text" readonly="readonly"  class="form-control" name="responseData" value="${entity.responseData}"/>--%>
                        <textarea readonly="readonly" cols="50" rows="4" name="responseData" >${entity.userGray}</textarea>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-4 control-label">手机号存疑</label>
                    <div class="col-sm-8">
                        <%--<input type="text" readonly="readonly"  class="form-control" name="responseData" value="${entity.responseData}"/>--%>
                        <textarea readonly="readonly" cols="50" rows="4"  name="responseData" >${entity.userPhoneSuspicion}</textarea>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-4 control-label">身份证存疑</label>
                    <div class="col-sm-8">
                        <%--<input type="text" readonly="readonly"  class="form-control" name="responseData" value="${entity.responseData}"/>--%>
                        <textarea readonly="readonly" cols="50" rows="3"  name="responseData" >${entity.userIdcardSuspicion}</textarea>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-4 control-label">被机构查询历史</label>
                    <div class="col-sm-8">
                        <%--<input type="text" readonly="readonly"  class="form-control" name="responseData" value="${entity.responseData}"/>--%>
                        <textarea readonly="readonly" cols="50" rows="4"  name="responseData" >${entity.userSearchedHistoryByOrgs}</textarea>
                    </div>
                </div>
                 <div class="form-group">
                    <label class="col-sm-4 control-label">被机构查询数量</label>
                    <div class="col-sm-8">
                        <%--<input type="text" readonly="readonly"  class="form-control" name="responseData" value="${entity.responseData}"/>--%>
                        <textarea readonly="readonly" cols="50" rows="1"  name="responseData" >${entity.userSearchedStatistic}</textarea>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-4 control-label">黑名单信息</label>
                    <div class="col-sm-8">
                        <%--<input type="text" readonly="readonly"  class="form-control" name="responseData" value="${entity.responseData}"/>--%>
                        <textarea readonly="readonly"cols="50" rows="4"   name="responseData" >${entity.userBlacklist}</textarea>
                    </div>
                </div>	
                 <div class="form-group">
                    <label class="col-sm-4 control-label">基本信息</label>
                    <div class="col-sm-8">
                        <%--<input type="text" readonly="readonly"  class="form-control" name="responseData" value="${entity.responseData}"/>--%>
                        <textarea readonly="readonly" cols="50" rows="4"  name="responseData" >${entity.userBasic}</textarea>
                    </div>
                </div>	
                <div class="form-group">
                    <label class="col-sm-4 control-label">用户注册情况</label>
                    <div class="col-sm-8">
                        <%--<input type="text" readonly="readonly"  class="form-control" name="responseData" value="${entity.responseData}"/>--%>
                        <textarea readonly="readonly" cols="50" rows="2"  name="responseData" >${entity.userRegisterOrgs}</textarea>
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