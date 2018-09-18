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
            <h3 id="myModalLabel">${empty entity?'新增':'查看' }法院详情信息信息</h3>
        </div>
        <div class="modal-body">
            <form class="form-horizontal" method="post" id="productRuleForm" name="productRuleForm"
                  action="tdriskcourt/save${empty entity?'Insert':'Update' }.htm"
                  role="form">
                <div class="form-group">
                    <label class="col-sm-4 control-label">报告编号<font color="red">&nbsp;</font></label>
                    <div class="col-sm-8">
                        <input type="text" readonly="readonly"  class="form-control" name="reportId" value="${entity.reportId}">
                        <input type="hidden" name="seqRiskId" value="${entity.seqRiskId}">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-4 control-label">被执行人姓名</label>
                    <div class="col-sm-8">
                    	 <input type="text" readonly="readonly"  class="form-control" name="name" value="${entity.name}">
                    </div>
                </div>
                
                 <div class="form-group">
                    <label  readonly="readonly" class="col-sm-4 control-label">风险类型</label>
                    <div class="col-sm-8">
                    <select readonly="readonly"  class="form-control" id="fraudType" name="fraudType">
							  <option value="法院执行" ${entity.fraudType=='法院执行'?'selected':''}>法院执行</option>
							  <option value="法院失信"  ${entity.fraudType=='法院失信'?'selected':''}>法院失信</option>
						</select>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-4 control-label">执行法院</label>
                    <div class="col-sm-8">
                        <input type="text" readonly="readonly"  class="form-control" name="courtName" value="${entity.courtName}"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-4 control-label">被执行人的履行情况</label>
                    <div class="col-sm-8">
                        <input type="text" readonly="readonly"  class="form-control" name="situation" value="${entity.situation}"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-4 control-label">案号</label>
                    <div class="col-sm-8">
                        <input type="text" readonly="readonly"  class="form-control" name="caseNumber" value="${entity.caseNumber}"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-4 control-label">失信被执行人行为具体情形</label>
                    <div class="col-sm-8">
                        <input type="text" readonly="readonly"  class="form-control" name="discreditDetail" value="${entity.discreditDetail}"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-4 control-label">创建时间</label>
                    <div class="col-sm-8">
                      <input type="text" readonly="readonly"  name = "createTime"  class="form-control" value="${entity.createTime}"/>
                    </div>
                </div>
              
                <div class="form-group">
                    <label class="col-sm-4 control-label">修改时间</label>
                    <div class="col-sm-8">
                        <input type="text" readonly="readonly"  class="form-control" name="modifyTime" value="${entity.modifyTime}"/>
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