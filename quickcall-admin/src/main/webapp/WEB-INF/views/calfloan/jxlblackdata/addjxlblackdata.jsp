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
            <h3 id="myModalLabel">${empty entity?'新增':'查看' }黑名单数据信息</h3>
        </div>
        <div class="modal-body">
            <form class="form-horizontal" method="post" id="productRuleForm" name="productRuleForm"
                  action="jxlblackdata/save${empty entity?'Insert':'Update' }.htm"
                  role="form">
                <div class="form-group">
                    <label class="col-sm-4 control-label">APP应用客户编号<font color="red">&nbsp;</font></label>
                    <div class="col-sm-8">
                        <input type="text" readonly="readonly"  class="form-control" name="customerId" value="${entity.customerId}">
                        <input type="hidden" name="blackDataId" value="${entity.blackDataId}">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-4 control-label">黑中介分数</label>
                    <div class="col-sm-8">
                         <input type="number" readonly="readonly"  class="form-control" name="blackScore" value="${entity.blackScore}"/>
                    </div>
                </div>
                
                <div class="form-group">
                    <label class="col-sm-4 control-label">直接联系人黑名单人数</label>
                    <div class="col-sm-8">
                        <input type="number" readonly="readonly"  class="form-control" name="linkmanBlackNum" value="${entity.linkmanBlackNum}"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-4 control-label">间接联系人黑名单人数</label>
                    <div class="col-sm-8">
                        <input type="number" readonly="readonly"  class="form-control" name="jianBlackNum" value="${entity.jianBlackNum}"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-4 control-label">直接联系人数</label>
                    <div class="col-sm-8">
                        <input type="number" readonly="readonly"  class="form-control" name="directLinkmanNum" value="${entity.directLinkmanNum}"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-4 control-label">引起黑名单的直接联系人数量</label>
                    <div class="col-sm-8">
                        <input type="number" readonly="readonly"  class="form-control" name="directBlackNum" value="${entity.directBlackNum}"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-4 control-label">直接联系人中引起间接黑名单占比</label>
                    <div class="col-sm-8">
                        <input type="number" readonly="readonly"  class="form-control" name="directScaleNum" value="${entity.directScaleNum}"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-4 control-label">状态</label>
                    <div class="col-sm-8">
                        <input type="number" readonly="readonly"  class="form-control" name="state" value="${entity.state}"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-4 control-label">审核报告编号</label>
                    <div class="col-sm-8">
                        <input type="number" readonly="readonly"  class="form-control" name="auditReportId" value="${entity.auditReportId}"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-4 control-label">平台用户编号</label>
                    <div class="col-sm-8">
                        <input type="number" readonly="readonly"  class="form-control" name="platformCustomerId" value="${entity.platformCustomerId}"/>
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