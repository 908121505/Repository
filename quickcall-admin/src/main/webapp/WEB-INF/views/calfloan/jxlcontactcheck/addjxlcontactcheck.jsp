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
            <h3 id="myModalLabel">${empty entity?'新增':'查看' }联系人检查列表信息</h3>
        </div>
        <div class="modal-body">
            <form class="form-horizontal" method="post" id="productRuleForm" name="productRuleForm"
                  action="jxlcontactcheck/save${empty entity?'Insert':'Update' }.htm"
                  role="form">
                <div class="form-group">
                    <label class="col-sm-4 control-label">审核报告编号<font color="red">&nbsp;</font></label>
                    <div class="col-sm-8">
                        <input type="text" readonly="readonly"  class="form-control" name="auditReportId" value="${entity.auditReportId}">
                        <input type="hidden" name="contactCheckId" value="${entity.contactCheckId}">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-4 control-label">与检查手机通话检测</label>
                    <div class="col-sm-8">
                         <input type="number" readonly="readonly"  class="form-control" name="checkMobile" value="${entity.checkMobile}"/>
                    </div>
                </div>
                
                <div class="form-group">
                    <label class="col-sm-4 control-label">联系人号码</label>
                    <div class="col-sm-8">
                        <input type="number" readonly="readonly"  class="form-control" name="keyValue" value="${entity.keyValue}"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-4 control-label">联系人姓名</label>
                    <div class="col-sm-8">
                        <input type="number" readonly="readonly"  class="form-control" name="contactName" value="${entity.contactName}"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-4 control-label">与联系人关系</label>
                    <div class="col-sm-8">
                        <input type="number" readonly="readonly"  class="form-control" name="relationship" value="${entity.relationship}"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-4 control-label">临时小号检查</label>
                    <div class="col-sm-8">
                        <input type="number" readonly="readonly"  class="form-control" name="checkXiaohao" value="${entity.checkXiaohao}"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-4 control-label">状态</label>
                    <div class="col-sm-8">
                        <input type="number" readonly="readonly"  class="form-control" name="state" value="${entity.state}"/>
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