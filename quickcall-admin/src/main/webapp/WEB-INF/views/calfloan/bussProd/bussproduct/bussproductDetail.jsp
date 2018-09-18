<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<link type="text/css" href="resources/bootstrap/css/bootstrap-datetimepicker.min.css" rel="stylesheet" />
<script type="text/javascript" language="javascript" src="resources/bootstrap/js/bootstrap-datetimepicker.min.js" charset="utf-8"></script>
<div class="modal-dialog" style="width:998px">
    <div class="modal-content">
        <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal"
                    aria-hidden="true">X
            </button>
            <h3 id="myModalLabel">入驻商户详情</h3>
        </div>
        <div class="modal-body">
            <form class="form-horizontal" method="post" id="sysUserForm" name="bannerForm"
                  action="bussProduct/saveUpdate.htm"
                  role="form">
                <table border="0">
                    <tr>
                        <td style="vertical-align:top">
                            <div class="form-group">
                                <label class="col-sm-3 control-label">公司名称：<font color="red">&nbsp;*</font></label>
                                <div class="col-sm-7">
                                    <input type="text"  class="form-control required" id="companyName" name="companyName"
                                           value="${entity.companyName }" onblur="checkCompanyName(this.value)"/>
                                    <input type="hidden" id="productId"  name= "productId"  value="${entity.productId }"/>
                                    <input type="hidden" id="changeId" name = "id" value="${entity.id }"/>
                                    <span id="checkCompanyName" style="color: red;font-size: 14px;margin-left:20px; "></span>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">公司性质：<font color="red">&nbsp;*</font></label>
                                <div class="col-sm-7">
                                    <select class="form-control" id="companyNature" name="companyNature">
                                        <c:forEach var="gsxz"   items="${gsxzList }" varStatus="status">
                                            <option value="${gsxz.modelContent }" ${entity.companyNature != gsxz.modelContent? '':'selected' }>${gsxz.modelContent }</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">结算周期：<font color="red">&nbsp;*</font></label>
                                <div class="col-sm-7">
                                    <select class="form-control" id="settleDate" name="settleDate">
                                        <c:forEach var="jszq"   items="${jszqList }" varStatus="status">
                                            <option value="${jszq.modelContent }" ${entity.settleDate == jszq.modelContent? 'selected':'' }>${jszq.modelContent }</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                        </td>
                        <td style="vertical-align:top">

                            <div class="form-group">
                                <label class="col-sm-3 control-label">商户名称：<font color="red">&nbsp;*</font></label>
                                <div class="col-sm-7">
                                    <input type="text" class="form-control required" id="productName" name="productName"
                                           value="${entity.productName }" onblur="checkProductName(this.value)"/>
                                    <span id="checkProductName" style="color: red;font-size: 14px;margin-left:20px; "></span>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">合作模式：<font color="red">&nbsp;*</font></label>
                                <div class="col-sm-7">
                                    <select class="form-control" id="cooperModel" name = "cooperModel">
                                        <c:forEach var="hzms"   items="${hzmsList }" varStatus="status">
                                            <option value="${hzms.modelContent }"  ${entity.cooperModel == hzms.modelContent? 'selected':'' }>${hzms.modelContent }</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <div class="form-group">
                                <label class="col-sm-4 control-label">初次上线时间：</label>
                                <div class="col-sm-6">
                                    <input readonly = "readonly" type="text"  class="form-control required" id="firstOnlineTime" name="firstOnlineTime"
                                           value="${firstOnlineTime}" />
                                </div>
                            </div>
                        </td>
                        <td>
                            <div class="form-group">
                                <label class="col-sm-4 control-label">末次下线时间：</label>
                                <div class="col-sm-6">
                                    <input readonly = "readonly" type="text"  class="form-control required" id="lastDownLineTime" name="lastDownLineTime"
                                           value="${lastDownLineTime }" />
                                </div>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td >
                            <div class="form-group">
                                <label class="col-sm-4 control-label">总在线时长：</label>
                                <div class="col-sm-6">
                                    <input readonly = "readonly" type="text"  class="form-control required" id="allOnlineTime" name="allOnlineTime"
                                           value="${allOnlineTime }" />
                                </div>
                            </div>
                        </td>
                        <td >
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <div class="form-group">
                                <label class="col-sm-4 control-label">上次打款时间：</label>
                                <div class="col-sm-6">
                                    <input readonly = "readonly" type="text"  class="form-control required" id="previousPayAmountTime" name="previousPayAmountTime"
                                           value="${previousPayAmountTime}" />
                                </div>
                            </div>
                        </td>
                        <td>
                            <div class="form-group">
                                <label class="col-sm-4 control-label">未打款周期：</label>
                                <div class="col-sm-6">
                                    <input readonly = "readonly" type="text"  class="form-control required" id="unSettleDate" name="unSettleDate"
                                           value="${unSettleDate == '' ? '无':unSettleDate}" />
                                </div>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <div class="form-group">
                                <label class="col-sm-4 control-label">总对账金额：</label>
                                <div class="col-sm-6">
                                    <input readonly = "readonly" type="text"  class="form-control required" id="allTallyAmount" name="allTallyAmount"
                                           value="${allTallyAmount == '' || allTallyAmount == null ? '无':allTallyAmount }"/>
                                </div>
                            </div>
                        </td>
                        <td>
                            <div class="form-group">
                                <label class="col-sm-4 control-label">总打款金额：</label>
                                <div class="col-sm-6">
                                    <input readonly = "readonly" type="text"  class="form-control required" id="allPayAmount" name="allPayAmount"
                                           value="${allPayAmount == '' || allPayAmount == null ? '无':allPayAmount }"/>
                                </div>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <div class="form-group">
                                <label class="col-sm-4 control-label">对接人：</label>
                                <div class="col-sm-6">
                                    <input type="text"  class="form-control required" id="contactName" name="contactName"
                                           value="${entity.contactName }" />
                                </div>
                            </div>
                        </td>
                        <td>
                            <div class="form-group">
                                <label class="col-sm-4 control-label">联系电话：</label>
                                <div class="col-sm-6">
                                    <input type="text"  class="form-control required" id="contactMobile" name="contactMobile"
                                           maxlength="13" value="${entity.contactMobile }" />
                                </div>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <div class="form-group">
                                <label class="col-sm-4 control-label">联系人：</label>
                                <div class="col-sm-6">
                                    <input type="text" class="form-control required" id="comContactName" name="comContactName"
                                           value="${entity.comContactName }"/>
                                </div>
                            </div>
                        </td>
                        <td>
                            <div class="form-group">
                                <label class="col-sm-4 control-label">联系电话：</label>
                                <div class="col-sm-6">
                                    <input type="text" class="form-control required" id="comContactMobile" name="comContactMobile"
                                           maxlength="13" value="${entity.comContactMobile }"/>
                                </div>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td >
                            <div class="form-group">
                                <label class="col-sm-4 control-label">归属人：<font color="red">&nbsp;*</font></label>
                                <div class="col-sm-6">
                                    <shiro:hasPermission name="bussProduct:upload">
                                            <script type="text/javascript">
                                                $('#owner').removeAttr("readonly");
                                            </script>
                                    </shiro:hasPermission>
                                    <input type="text" class="form-control required" id="owner" name="owner"
                                           value="${entity.owner}" readonly ="readonly" />
                                </div>
                            </div>
                        </td>
                        <td >
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2" style="text-align:left" align="left">
                            <div class="form-group">
                                <label class="col-sm-3 control-label">备注：</label>
                                <div class="col-sm-5">
                                    <textarea   rows="5" cols="25" class="form-control required" id="remark" name="remark"
                                    >${entity.remark }</textarea>
                                </div>
                            </div>
                        </td>
                    </tr>
                </table>
                <span id="tip" style="color: red;font-size: 14px;margin-left:20px; "></span>
            </form>
        </div>

        <div class="modal-footer" style="text-align:center">
            <button class="btn btn-default" data-dismiss="modal"
                    aria-hidden="true">取消
            </button>
            <button class="btn btn-primary" data-dismiss="modal">保存</button>
        </div>


    </div>
</div>
<script type="text/javascript"
        src="resources/js/My97DatePicker/WdatePicker.js" charset="utf-8"></script>
<script type="text/javascript">
    function checkCompanyName(companyName,productName) {
        var id = $("#changeId").val();
        var flag = true;
        $.ajax({
            type: "post",
            dataType: "json",
            url: "bussProduct/checkName",
            async:false,
            data: {
                "id":id,
                "companyName": companyName,
                "productName":productName
            },
            error: function () {
                alert("错误！");
            },
            success: function (data) {
                if (data !== null && data !== '' && data.owner !== undefined) {
                    $("#tip").html("已存在，归属人："+data.owner);
                    flag = false;
                }
            }
        });
        return flag;
    }
    function checkProductName(productName) {
        $("#checkProductName").html("");
        var id = $("#changeId").val();
        var flag = true;
        $.ajax({
            type: "post",
            dataType: "json",
            url: "bussProduct/checkName",
            async:false,
            data: {
                "id":id,
                "productName": productName
            },
            error: function () {
                alert("错误！");
            },
            success: function (data) {
                if (data !== null && data !== '' && data.owner !== undefined) {
                    $("#checkProductName").html("已存在，归属人："+data.owner);
                    flag = false;
                }
            }
        });
        return flag;
    }
    function check_fun() {
        $("#tip").html("");
        var companyName = $("#companyName").val();
        var productName = $("#productName").val();
        var comContactName = $("#comContactName").val();
        var comContactMobile = $("#comContactMobile").val();
        var contactMobile = $("#contactMobile").val();
        if(companyName == null || companyName.trim() == ''){
            $("#tip").html("请输入公司名称");
            return false;
        }

        if(productName == null || productName.trim() == ''){
            $("#tip").html("请输入商户名称");
            return false;
        }
        if(comContactName == null || comContactName.trim() == ''){
            $("#tip").html("请输入联系人");
            return false;
        }
        if(comContactMobile == null || comContactMobile.trim() == ''){
            $("#tip").html("请输入联系电话");
            return false;
        }
        var myreg=/^[^\u4e00-\u9fa5]{0,}$/;
        if (!myreg.test(comContactMobile)) {
            $("#tip").html("联系人联系电话不能输入汉字");
            return false;
        }
        if (!myreg.test(contactMobile)) {
            $("#tip").html("对接人联系电话不能输入汉字");
            return false;
        }
        var companyflag = checkCompanyName(companyName,productName);
        // var productFlag =  checkProductName(productName);
        if (companyflag) {
            return true;
        } else {
            return false;
        }

    }
    $(function () {
        $('#startTime').datetimepicker({
            format: 'yyyy-mm-dd hh:ii:ss'
        });
        $('#endTime').datetimepicker({
            format: 'yyyy-mm-dd hh:ii:ss'
        });
    });
</script>