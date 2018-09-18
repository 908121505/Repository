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
            <h3 id="myModalLabel">${empty entity?'新增':'查看' }身份信息检查信息</h3>
        </div>
        <div class="modal-body">
            <form class="form-horizontal" method="post" id="productRuleForm" name="productRuleForm"
                  action="jxlidcardcheck/save${empty entity?'Insert':'Update' }.htm"
                  role="form">
                <div class="form-group">
                    <label class="col-sm-4 control-label">审核报告编号<font color="red">&nbsp;</font></label>
                    <div class="col-sm-8">
                        <input type="text" readonly="readonly"  class="form-control" name="auditReportId" value="${entity.auditReportId}">
                        <input type="hidden" name="idCardCheckId" value="${entity.idCardCheckId}">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-4 control-label">性别</label>
                    <div class="col-sm-8">
                         <input type="number" readonly="readonly"  class="form-control" name="sex" value="${entity.sex}"/>
                    </div>
                </div>
                
                <div class="form-group">
                    <label class="col-sm-4 control-label">年龄</label>
                    <div class="col-sm-8">
                        <input type="number" readonly="readonly"  class="form-control" name="age" value="${entity.age}"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-4 control-label">省份编号</label>
                    <div class="col-sm-8">
                        <input type="number" readonly="readonly"  class="form-control" name="provinceId" value="${entity.provinceId}"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-4 control-label">省份名称</label>
                    <div class="col-sm-8">
                        <input type="number" readonly="readonly"  class="form-control" name="provinceName" value="${entity.provinceName}"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-4 control-label">城市编号</label>
                    <div class="col-sm-8">
                        <input type="number" readonly="readonly"  class="form-control" name="cityId" value="${entity.cityId}"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-4 control-label">城市名称</label>
                    <div class="col-sm-8">
                        <input type="number" readonly="readonly"  class="form-control" name="cityName" value="${entity.cityName}"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-4 control-label">城市区域编号</label>
                    <div class="col-sm-8">
                        <input type="number" readonly="readonly"  class="form-control" name="cityAreaId" value="${entity.cityAreaId}"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-4 control-label">城市区域名称</label>
                    <div class="col-sm-8">
                        <input type="number" readonly="readonly"  class="form-control" name="cityAreaName" value="${entity.cityAreaName}"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-4 control-label">金融黑名单记录</label>
                    <div class="col-sm-8">
                        <input type="number" readonly="readonly"  class="form-control" name="financialBlacklist" value="${entity.financialBlacklist}"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-4 control-label">法院黑名单记录</label>
                    <div class="col-sm-8">
                        <input type="number" readonly="readonly"  class="form-control" name="courtBlacklist" value="${entity.courtBlacklist}"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-4 control-label">检查值</label>
                    <div class="col-sm-8">
                        <input type="number" readonly="readonly"  class="form-control" name="keyValue" value="${entity.keyValue}"/>
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