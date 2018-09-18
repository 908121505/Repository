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
            <h3 id="myModalLabel">${empty entity?'新增':'查看' }风险检查项表信息</h3>
        </div>
        <div class="modal-body">
            <form class="form-horizontal" method="post" id="productRuleForm" name="productRuleForm"
                  action="tdriskcheck/save${empty entity?'Insert':'Update' }.htm"
                  role="form">
                <div class="form-group">
                    <label class="col-sm-4 control-label">报告编号<font color="red">&nbsp;</font></label>
                    <div class="col-sm-8">
                        <input type="text" readonly="readonly"  class="form-control" name="reportId" value="${entity.reportId}">
                        <input type="hidden" name="seqRiskId" value="${entity.seqRiskId}">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-4 control-label">手机号</label>
                    <div class="col-sm-8">
                    	 <input type="text" readonly="readonly"  class="form-control" name="phoneNum" value="${entity.phoneNum}">
                    </div>
                </div>
                
                <div class="form-group">
                    <label class="col-sm-4 control-label">报告的申请编号</label>
                    <div class="col-sm-8">
                        <input type="text" readonly="readonly"  class="form-control" name="parentNameUserId" value="${entity.parentNameUserId}"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-4 control-label">名字</label>
                    <div class="col-sm-8">
                        <input type="text" readonly="readonly"  class="form-control" name="parentNameTitle" value="${entity.parentNameTitle}"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-4 control-label">身份证</label>
                    <div class="col-sm-8">
                        <input type="text" readonly="readonly"  class="form-control" name="parentNameIdCard" value="${entity.parentNameIdCard}"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-4 control-label">检查项分组</label>
                    <div class="col-sm-8">
                        <input type="text" readonly="readonly"  class="form-control" name="group" value="${entity.group}"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-4 control-label">报告时间</label>
                    <div class="col-sm-8">
                        <input type="text" readonly="readonly"  class="form-control" name="parentNameTime" value="${entity.parentNameTime}"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-4 control-label">风险类型</label>
                    <div class="col-sm-8">
                     <textarea readonly="readonly"  name = "parentNameFraudType"  class="form-control" rows="6" style="overflow:auto">${entity.parentNameFraudType}</textarea> 
                    </div>
                </div>
              
                <div class="form-group">
                    <label class="col-sm-4 control-label">匹配类型</label>
                    <div class="col-sm-8">
                        <input type="text" readonly="readonly"  class="form-control" name="parentNameTypeDisplayname" value="${entity.parentNameTypeDisplayname}"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-4 control-label">规则的类型</label>
                    <div class="col-sm-8">
                        <input type="text" readonly="readonly"  class="form-control" name="parentNameType" value="${entity.parentNameType}"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-4 control-label">描述</label>
                    <div class="col-sm-8">
                    <textarea readonly="readonly"  name = "parentNameDescription"  class="form-control" rows="6" style="overflow:auto">${entity.parentNameDescription}</textarea> 
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-4 control-label">检查项编号</label>
                    <div class="col-sm-8">
                        <input type="text" readonly="readonly"  class="form-control" name="itemId" value="${entity.itemId}"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-4 control-label">检查项名称</label>
                    <div class="col-sm-8">
                        <input type="text" readonly="readonly"  class="form-control" name="itemName" value="${entity.itemName}"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-4 control-label">风险等级</label>
                    <div class="col-sm-8">
                        <input type="text" readonly="readonly"  class="form-control" name="riskLevel" value="${entity.riskLevel}"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-4 control-label">分数</label>
                    <div class="col-sm-8">
                        <input type="text" readonly="readonly"  class="form-control" name="parentNameSource" value="${entity.parentNameSource}"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-4 control-label">信贷逾期次数</label>
                    <div class="col-sm-8">
                        <input type="text" readonly="readonly"  class="form-control" name="parentNameDiscreditTime" value="${entity.parentNameDiscreditTime}"/>
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