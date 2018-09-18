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
            <h3 id="myModalLabel">${empty entity?'新增':'修改' }产品规则信息</h3>
        </div>
        <div class="modal-body">
            <form class="form-horizontal" method="post" id="productRuleForm" name="productRuleForm"
                  action="productRule/save${empty entity?'Insert':'Update' }.htm"
                  role="form">
                <div class="form-group">
                    <label class="col-sm-4 control-label">规则名称<font color="red">&nbsp;*</font></label>
                    <div class="col-sm-8">
                        <input type="text" class="form-control" name="ruleName" value="${entity.ruleName}">
                        <input type="hidden" name="productRuleId" value="${entity.productRuleId}">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-4 control-label">身份认证</label>
                    <div class="col-sm-8">
                        <select class="form-control" id="identity_auth" name="identityAuth">
                            <option value="需要认证">需要认证</option>
                            <option value="不需要认证">不需要认证</option>
                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-4 control-label">申请金额</label>
                    <div class="col-sm-8">
                        <input type="number" class="form-control" name="applyAmount" value="${entity.applyAmount}"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-4 control-label">申请期限</label>
                    <div class="col-sm-8">
                        <input type="text" class="form-control" name="applyTerm" value="${entity.applyTerm}"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-4 control-label">每月最高还款额度</label>
                    <div class="col-sm-8">
                        <input type="number" class="form-control" name="maxMonthAmount" value="${entity.maxMonthAmount}"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-4 control-label">教育程度</label>
                    <div class="col-sm-8">
                        <select class="form-control" id="education" name="education">
                            <option value="硕士及以上">硕士及以上</option>
                            <option value="本科">本科</option>
                            <option value="专科">专科</option>
                            <option value="大专/高中及以下">大专/高中及以下</option>
                            <option value="不作要求">不作要求</option>
                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-4 control-label">是否缴纳社保</label>
                    <div class="col-sm-8">
                        <select class="form-control" id="sbSituation" name="sbSituation">
                            <option value="缴纳本地社保">缴纳本地社保</option>
                            <option value="未缴纳社保">未缴纳社保</option>
                            <option value="不作要求">不作要求</option>
                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-4 control-label">车辆情况</label>
                    <div class="col-sm-8">
                        <select class="form-control" id="carSituation" name="carSituation">
                            <option value="无车">无车</option>
                            <option value="名下有车无贷款">名下有车无贷款</option>
                            <option value="名下有车有按揭贷款">名下有车有按揭贷款</option>
                            <option value="名下有车但已被抵押">名下有车但已被抵押</option>
                            <option value="不作要求">不作要求</option>
                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-4 control-label">职业类别</label>
                    <div class="col-sm-8">
                        <select class="form-control" id="occupation" name="occupation">
                            <option value="企业主">企业主</option>
                            <option value="个体工商户">个体工商户</option>
                            <option value="上班人群">上班人群</option>
                            <option value="学生">学生</option>
                            <option value="自由族">自由族</option>
                            <option value="不作要求">不作要求</option>
                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-4 control-label">月工资收入</label>
                    <div class="col-sm-8">
                        <input type="number" class="form-control" name="monthlyIncome" value="${entity.monthlyIncome}"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-4 control-label">工作年限</label>
                    <div class="col-sm-8">
                        <select class="form-control" id="workingLife" name="workingLife">
                            <option value="不作要求">不作要求</option>
                            <option value="不足三个月">不足三个月</option>
                            <option value="3-5个月">3-5个月</option>
                            <option value="6-11个月">6-11个月</option>
                            <option value="1-3年">1-3年</option>
                            <option value="4-7年">4-7年</option>
                            <option value="7年以上">7年以上</option>
                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-4 control-label">手机运营商</label>
                    <div class="col-sm-8">
                        <select class="form-control" id="checkPhone" name="checkPhone">
                            <option value="需要认证">需要认证</option>
                            <option value="不需要认证">不需要认证</option>
                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-4 control-label">邮箱认证</label>
                    <div class="col-sm-8">
                        <select class="form-control" id="checkEmail" name="checkEmail">
                            <option value="需要认证">需要认证</option>
                            <option value="不需要认证">不需要认证</option>
                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-4 control-label">单位名称</label>
                    <div class="col-sm-8">
                        <select class="form-control" id="companyName" name="companyName">
                            <option value="需要填写">需要填写</option>
                            <option value="不需要填写">不需要填写</option>
                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-4 control-label">紧急联系人</label>
                    <div class="col-sm-8">
                        <select class="form-control" id="emergencyContact" name="emergencyContact">
                            <option value="需要填写">需要填写</option>
                            <option value="不需要填写">不需要填写</option>
                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-4 control-label">社交账号</label>
                    <div class="col-sm-8">
                        <select class="form-control" id="socialAccount" name="socialAccount">
                            <option value="需要填写">需要填写</option>
                            <option value="不需要填写">不需要填写</option>
                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-4 control-label">房产情况</label>
                    <div class="col-sm-8">
                        <select class="form-control" id="houseSituation" name="houseSituation">
                            <option value="无房">无房</option>
                            <option value="名下有房无贷款">名下有房无贷款</option>
                            <option value="名下有房有按揭贷款">名下有房有按揭贷款</option>
                            <option value="名下有房但已被抵押">名下有房但已被抵押</option>
                            <option value="不作要求">不作要求</option>
                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-4 control-label">公积金</label>
                    <div class="col-sm-8">
                        <select class="form-control" id="accumulationFund" name="accumulationFund">
                            <option value="缴纳公积金">缴纳公积金</option>
                            <option value="不缴纳公积金">不缴纳公积金</option>
                            <option value="不作要求">不作要求</option>
                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-4 control-label">芝麻分</label>
                    <div class="col-sm-8">
                        <input type="number" class="form-control" name="sesameBranch" value="${entity.sesameBranch}"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-4 control-label">贷款用途</label>
                    <div class="col-sm-8">
                        <input type="text" class="form-control" name="loanUse" value="${entity.loanUse}"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-4 control-label">状态<font color="red">&nbsp;*</font></label>
                    <div class="col-sm-8">
                        <select class="form-control" id="state" name="state">
                            <option value="1"  ${entity.state=='1'?'selected':'' }>有效</option>
                            <option value="2"  ${entity.state=='2'?'selected':'' }>无效</option>
                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-4 control-label">备注</label>
                    <div class="col-sm-8">
                        <input type="text" class="form-control" name="remark" value="${entity.remark}"/>
                    </div>
                </div>
                <span id="tip" style="color: red;font-size: 14px;margin-left:20px; "></span>
            </form>
        </div>
        <div class="modal-footer">
            <button class="btn btn-default" data-dismiss="modal" aria-hidden="true">取消</button>
            <button class="btn btn-primary" data-dismiss="modal">保存</button>
        </div>
    </div>
</div>

<script type="text/javascript">

    $("#identity_auth").val('${entity.identityAuth}');
    $("#education").val('${entity.education}');
    $("#sbSituation").val('${entity.sbSituation}');
    $("#carSituation").val('${entity.carSituation}');
    $("#occupation").val('${entity.occupation}');
    $("#workingLife").val('${entity.workingLife}');
    $("#checkPhone").val('${entity.checkPhone}');
    $("#checkEmail").val('${entity.checkEmail}');
    $("#companyName").val('${entity.companyName}');
    $("#emergencyContact").val('${entity.emergencyContact}');
    $("#socialAccount").val('${entity.socialAccount}');
    $("#houseSituation").val('${entity.houseSituation}');
    $("#accumulationFund").val('${entity.accumulationFund}');

    function check_fun() {
        $("#tip").html("");
        var b = true;

        var state = $("#state").val();
        var ruleName = $('input[name=ruleName]').val();

        if (state == undefined) {
            $("#tip").html("请选择状态");
            b = false;
        }

        if (ruleName == null || ruleName.trim() == '') {
            $("#tip").html("规则名称不能为空");
            b = false;
        }
        return b;
    }
</script>