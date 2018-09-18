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
            <h3 id="myModalLabel">${empty entity?'新增':'查看' }电商数据信息</h3>
        </div>
        <div class="modal-body">
            <form class="form-horizontal" method="post" id="productRuleForm" name="productRuleForm"
                  action="jxlnetbusidata/save${empty entity?'Insert':'Update' }.htm"
                  role="form">
                <div class="form-group">
                    <label class="col-sm-4 control-label">审核报告编号<font color="red">&nbsp;</font></label>
                    <div class="col-sm-8">
                        <input type="text" readonly="readonly"  class="form-control" name="auditReportId" value="${entity.auditReportId}">
                        <input type="hidden" name="netBusiDataId" value="${entity.netBusiDataId}">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-4 control-label">APP应用客户编号</label>
                    <div class="col-sm-8">
                         <input type="number" readonly="readonly"  class="form-control" name="customerId" value="${entity.customerId}"/>
                    </div>
                </div>
                
                <div class="form-group">
                    <label class="col-sm-4 control-label">平台用户编号</label>
                    <div class="col-sm-8">
                        <input type="number" readonly="readonly"  class="form-control" name="platformCustomerId" value="${entity.platformCustomerId}"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-4 control-label">年份</label>
                    <div class="col-sm-8">
                        <input type="number" readonly="readonly"  class="form-control" name="year" value="${entity.year}"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-4 control-label">月份</label>
                    <div class="col-sm-8">
                        <input type="number" readonly="readonly"  class="form-control" name="month" value="${entity.month}"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-4 control-label">消费笔数</label>
                    <div class="col-sm-8">
                        <input type="number" readonly="readonly"  class="form-control" name="consumeNum" value="${entity.consumeNum}"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-4 control-label">品类</label>
                    <div class="col-sm-8">
                        <input type="number" readonly="readonly"  class="form-control" name="category" value="${entity.category}"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-4 control-label">状态</label>
                    <div class="col-sm-8">
                        <input type="number" readonly="readonly"  class="form-control" name="state" value="${entity.state}"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-4 control-label">消费金额</label>
                    <div class="col-sm-8">
                        <input type="number" readonly="readonly"  class="form-control" name="consumeSum" value="${entity.consumeSum}"/>
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