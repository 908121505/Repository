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
            <h3 id="myModalLabel">${empty entity?'新增':'查看' }聚信立业务流水详情</h3>
        </div>
        <div class="modal-body">
            <form class="form-horizontal" method="post" id="productRuleForm" name="productRuleForm"
                  action="jxlMgOrderInfo/save${empty entity?'Insert':'Update' }.htm"
                  role="form">
                <div class="form-group">
                    <label class="col-sm-4 control-label">订单流水ID<font color="red">&nbsp;</font></label>
                    <div class="col-sm-8">
                        <input type="text" readonly="readonly"  class="form-control" name="orderInfoId" value="${entity.orderInfoId}">
                        <input type="hidden" name="orderInfoId" value="${entity.orderInfoId}">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-4 control-label">请求类型</label>
                    <div class="col-sm-8">
                         <input type="text" readonly="readonly"  class="form-control" name="requestType" value="${entity.requestType}"/>
                    </div>
                </div>
                
                <div class="form-group">
                    <label class="col-sm-4 control-label">请求名称</label>
                    <div class="col-sm-8">
                        <input type="text" readonly="readonly"  class="form-control" name="requestName" value="${entity.requestName}"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-4 control-label">请求URL</label>
                    <div class="col-sm-8">
                        <input type="text" readonly="readonly"  class="form-control" name="requestUrl" value="${entity.requestUrl}"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-4 control-label">请求数据</label>
                    <div class="col-sm-8">
                        <input type="text" readonly="readonly"  class="form-control" name="requestData" value="${entity.requestData}"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-4 control-label">状态</label>
                    <div class="col-sm-8">
                        <input type="text" readonly="readonly"  class="form-control" name="state" value="${entity.state}"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-4 control-label">响应消息</label>
                    <div class="col-sm-8">
                        <input type="text" readonly="readonly"  class="form-control" name="responseMessage" value="<c:out value="${entity.responseMessage}"></c:out>"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-4 control-label">响应code</label>
                    <div class="col-sm-8">
                        <input type="text" readonly="readonly"  class="form-control" name="responseCode" value="${entity.responseCode}"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-4 control-label">消耗用时</label>
                    <div class="col-sm-8">
                        <input type="text" readonly="readonly"  class="form-control" name="spendTime" value="${entity.spendTime}"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-4 control-label">响应数据</label>
                    <div class="col-sm-8">
                        <textarea readonly="readonly" name="responseData" cols="48px;" rows="4">${entity.responseData}</textarea>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-4 control-label">更新时间</label>
                    <div class="col-sm-8">
                        <input type="text" readonly="readonly"  class="form-control" name="updateTime" value="${entity.updateTime}"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-4 control-label">创建时间</label>
                    <div class="col-sm-8">
                        <input type="text" readonly="readonly"  class="form-control" name="createTime" value="${entity.createTime}"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-4 control-label">备注</label>
                    <div class="col-sm-8">
                        <input type="text" readonly="readonly"  class="form-control" name="remark" value="${entity.remark}"/>
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