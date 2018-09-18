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
            <h3 id="myModalLabel">${empty entity?'新增':'查看' }用户聚信立报告前置信息表信息</h3>
        </div>
        <div class="modal-body">
            <form class="form-horizontal" method="post" id="productRuleForm" name="productRuleForm"
                  action="jxlbasereportinfo/save${empty entity?'Insert':'Update' }.htm"
                  role="form">
                <div class="form-group">
                    <label class="col-sm-4 control-label">用户姓名<font color="red">&nbsp;</font></label>
                    <div class="col-sm-8">
                        <input type="text" readonly="readonly"  class="form-control" name="jxlName" value="${entity.jxlName}">
                        <input type="hidden" name="jxlBaseReportInfoId" value="${entity.jxlBaseReportInfoId}">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-4 control-label">身份证号</label>
                    <div class="col-sm-8">
                    	 <input type="text" readonly="readonly"  class="form-control" name="jxlIdNumber" value="${entity.jxlIdNumber}">
                    </div>
                </div>
                
                <div class="form-group">
                    <label class="col-sm-4 control-label">手机号码</label>
                    <div class="col-sm-8">
                        <input type="text" readonly="readonly"  class="form-control" name="jxlMobile" value="${entity.jxlMobile}"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-4 control-label">身份证</label>
                    <div class="col-sm-8">
                        <input type="text" readonly="readonly"  class="form-control" name="jxlMobileIdCard" value="${entity.jxlMobileIdCard}"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-4 control-label">客户姓名</label>
                    <div class="col-sm-8">
                        <input type="text" readonly="readonly"  class="form-control" name="jxlMobileIdName" value="${entity.jxlMobileIdName}"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-4 control-label">报告时间</label>
                    <div class="col-sm-8">
                        <input type="text" readonly="readonly"  class="form-control" name="jxlMobileReportTime" value="${entity.jxlMobileReportTime}"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-4 control-label">借款申请单编号</label>
                    <div class="col-sm-8">
                      <input type="text" readonly="readonly"  name = "jxlMobileApplicationNumber"  class="form-control" value="${entity.jxlMobileApplicationNumber}"/>
                    </div>
                </div>
              
                <div class="form-group">
                    <label class="col-sm-4 control-label">申请借款日期</label>
                    <div class="col-sm-8">
                        <input type="text" readonly="readonly"  class="form-control" name="jxlMobileLoanDate" value="${entity.jxlMobileLoanDate}"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-4 control-label">ip备注（公网+内网）</label>
                    <div class="col-sm-8">
                        <input type="text" readonly="readonly"  class="form-control" name="jxlMobileIpRemark" value="${entity.jxlMobileIpRemark}"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-4 control-label">是否命中</label>
                    <div class="col-sm-8">
                        <input type="text" readonly="readonly"  class="form-control" name="jxlMobileIpState" value="${entity.jxlMobileIpState}"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-4 control-label">用户ID</label>
                    <div class="col-sm-8">
                        <input type="text" readonly="readonly"  class="form-control" name="jxlAccountId" value="${entity.jxlAccountId}"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-4 control-label">获取报告token</label>
                    <div class="col-sm-8">
                        <input type="text" readonly="readonly"  class="form-control" name="jxlToken" value="${entity.jxlToken}"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-4 control-label">手机服务密码</label>
                    <div class="col-sm-8">
                        <input type="text" readonly="readonly"  class="form-control" name="jxlPassword" value="${entity.jxlPassword}"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-4 control-label">获取报告标识</label>
                    <div class="col-sm-8">
                        <input type="text" readonly="readonly"  class="form-control" name="jxlWebsite" value="${entity.jxlWebsite}"/>
                    </div>
                </div>
                <div class="form-group">
                    <label  readonly="readonly" class="col-sm-4 control-label">获取报告状态</label>
                    <div class="col-sm-8">
                    <select readonly="readonly"  class="form-control" id="jxlState" name="jxlState">
							  <option value="0" ${entity.jxlState=='0'?'selected':''}>未获取</option>
							  <option value="1"  ${entity.jxlState=='1'?'selected':''}>已获取</option>
						</select>
                    </div>
                </div>
                  <div class="form-group">
                    <label class="col-sm-4 control-label">创建时间</label>
                    <div class="col-sm-8">
                        <input type="text" readonly="readonly"  class="form-control" name="jxlCreateTime" value="${entity.jxlCreateTime}"/>
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