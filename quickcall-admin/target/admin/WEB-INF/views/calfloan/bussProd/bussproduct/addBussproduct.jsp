<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link type="text/css" href="resources/bootstrap/css/bootstrap-datetimepicker.min.css" rel="stylesheet" />
<script type="text/javascript" language="javascript" src="resources/bootstrap/js/bootstrap-datetimepicker.min.js" charset="utf-8"></script>
<div class="modal-dialog" style="width:998px">
    <div class="modal-content">
        <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal"
                    aria-hidden="true">X
            </button>
            <h3 id="myModalLabel">商户信息录入</h3>
        </div>
        <div class="modal-body">
            <form class="form-horizontal" method="post" id="sysUserForm" name="bannerForm"
                  action="bussProduct/save${empty entity?'Insert':'Update' }.htm"
                  role="form">
                <table border="0">
                    <tr>
                        <td style="vertical-align:top">
                            <div class="form-group">
                                <label class="col-sm-3 control-label">公司名称：<font color="red">&nbsp;*</font></label>
                                <div class="col-sm-5">
                                    <input type="text"  class="form-control required" id="companyName" name="companyName"
                                           value="${entity.companyName }" />
                                   <%--  <input type="hidden" name="productId" id="productId" value="${entity.productId }"> --%>
                                    <span id="checkCompanyName" style="color: red;font-size: 14px;margin-left:20px; "></span>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">商户名称：<font color="red">&nbsp;*</font></label>
                                <div class="col-sm-5">
                                    <input type="text" class="form-control required" id="productName" name="productName"
                                           value="${entity.productName }" />
                                    <span id="checkProductName" style="color: red;font-size: 14px;margin-left:20px; "></span>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">公司性质：<font color="red">&nbsp;*</font></label>
                                <div class="col-sm-5">
                                    <select class="form-control" id="companyNature" name="companyNature">
                                        <c:forEach var="gsxz"   items="${gsxzList }" varStatus="status">
                                            <option value="${gsxz.modelContent }" >${gsxz.modelContent }</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">合作模式：<font color="red">&nbsp;*</font></label>
                                <div class="col-sm-5">
                                    <select class="form-control" id="cooperModel" name = "cooperModel">
                                        <c:forEach var="hzms"   items="${hzmsList }" varStatus="status">
                                            <option value="${hzms.modelContent }" >${hzms.modelContent }</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">结算周期：<font color="red">&nbsp;*</font></label>
                                <div class="col-sm-5">
                                    <select class="form-control" id="settleDate" name="settleDate">
                                        <c:forEach var="jszq"   items="${jszqList }" varStatus="status">
                                            <option value="${jszq.modelContent }" >${jszq.modelContent }</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                        </td>
                        <td style="vertical-align:top">
                            <div class="form-group">
                                <label class="col-sm-3 control-label">联系人：<font color="red">&nbsp;*</font></label>
                                <div class="col-sm-5">
                                    <input type="text" class="form-control required" id="comContactName" name="comContactName"
                                           value="${entity.comContactName }"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">联系电话：<font color="red">&nbsp;*</font></label>
                                <div class="col-sm-5">
                                    <input type="text" class="form-control required" id="comContactMobile" name="comContactMobile"
                                           maxlength="13" value="${entity.comContactMobile }" />
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">归属人：<font color="red">&nbsp;*</font></label>
                                <div class="col-sm-5">
                                    <input type="text" class="form-control required" id="owner" name="owner"
                                           value="${owner}" readonly ="readonly" />
                                </div>
                            </div>
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
        var flag = true;
        $.ajax({
            type: "post",
            dataType: "json",
            url: "bussProduct/checkName",
            async:false,
            data: {
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
        var flag = true;
        $.ajax({
            type: "post",
            dataType: "json",
            url: "bussProduct/checkName",
            async:false,
            data: {
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
            $("#tip").html("联系电话不能输入汉字");
            return false;
        }
        // var companyflag = checkCompanyName(companyName);
        // var productFlag =  checkProductName(productName);
        // alert(companyflag);
        // alert(productFlag);
        var checkSameName = checkCompanyName(companyName, productName);
        if (checkSameName) {
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