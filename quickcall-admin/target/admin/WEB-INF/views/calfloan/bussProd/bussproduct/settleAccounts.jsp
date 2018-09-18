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
            <h3 id="myModalLabel">商户结算</h3>
        </div>
        <div class="modal-body">
            <form class="form-horizontal" method="post" id="sysUserForm2" name="bannerForm2"
                  action="bussProduct/settleAccounts"
                  role="form">
                <table border="0">
                    <tbody id = "contents">
                    <div class="col-sm-7" id="deleteDiv" style="display:none">
                        <input type="hidden"  class="form-control required" name="companyId"
                               value="${companyId }" />
                        <input type="hidden"  class="form-control required" name="productId"
                               value="${bussProduct.productId }" />
                    </div>
                    <c:if test="${listSize == 0}">
                        <tr id="deleteId${listSize }">
                            <td style="vertical-align:top">
                                <div class="input-group date">
                                    <label class="col-sm-5 control-label">打款时间：<font color="red">&nbsp;*</font></label>
                                    <div class="col-sm-7" >
                                        <input type="date"  class="form-control required" id="payDate${listSize }" name="payDate"
                                               value="${entity.payDate }" style="width: 182px"/>

                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="col-sm-5" >
                                        <span id="tip${listSize }" style="color: red;font-size: 14px;margin-left:20px; "></span>
                                    </div>
                                </div>
                            </td>
                            <td style="vertical-align:top">
                                <div class="form-group">
                                    <label class="col-sm-5 control-label">打款金额：<font color="red">&nbsp;*</font></label>
                                    <div class="col-sm-5">
                                        <input type="text" class="form-control required" id="payAmount${listSize }" name="payAmount"
                                               value="${entity.payAmount }"/>
                                    </div>
                                </div>
                            </td>
                            <td style="vertical-align:top">
                                <div class="form-group">
                                    <label class="col-sm-5 control-label">打款人：<font color="red">&nbsp;*</font></label>
                                    <div class="col-sm-5">
                                        <input type="text" class="form-control required" id="payer${listSize }" name="payer"
                                               value=""/>
                                    </div>
                                </div>
                            </td>
                            <td style="vertical-align:top">
                                <div style="background: #2aabd2;width: 50px;height: 30px;line-height: 26px;"  onclick="addData()"
                                     align="center" >
                                    +
                                </div>
                            </td>
                        </tr>
                    </c:if>
                    <c:forEach var="entity"   items="${list }" varStatus="status">
                    <tr id="deleteId${status.count }">
                        <td style="vertical-align:top">
                            <div class="input-group date">
                                <label class="col-sm-5 control-label">打款时间：<font color="red">&nbsp;*</font></label>
                                <div class="col-sm-7">
                                    <input type="date"  class="form-control required" id="payDate${status.count }" name="payDate"
                                           value="" style="width: 182px"/>
                                    <script type="text/javascript">
                                        var myDate = "${entity.payDate }";
                                        var now = new Date(myDate);
                                        //格式化日，如果小于9，前面补0
                                        var day = ("0" + now.getDate()).slice(-2);
                                        //格式化月，如果小于9，前面补0
                                        var month = ("0" + (now.getMonth() + 1)).slice(-2);
                                        //拼装完整日期格式
                                        var today = now.getFullYear()+"-"+(month)+"-"+(day) ;
                                        //完成赋值
                                        $("#payDate${status.count }").val(today);

                                    </script>
                                    <input type="hidden"  class="form-control required" id="entityId${status.count }" name="id"
                                           value="${entity.id }" />
                                </div>

                            </div>
                            <div class="form-group">
                                <div class="col-sm-5" >
                                    <span id="tip${status.count }" style="color: red;font-size: 14px;margin-left:20px; "></span>
                                </div>
                            </div>
                        </td>
                        <td style="vertical-align:top">
                            <div class="form-group">
                                <label class="col-sm-5 control-label">打款金额：<font color="red">&nbsp;*</font></label>
                                <div class="col-sm-5">
                                    <input type="text" class="form-control required" id="payAmount${status.count }" name="payAmount"
                                           value="${entity.payAmount }"/>
                                </div>
                            </div>
                        </td>
                        <td style="vertical-align:top">
                            <div class="form-group">
                                <label class="col-sm-5 control-label">打款人：<font color="red">&nbsp;*</font></label>
                                <div class="col-sm-5">
                                    <input type="text" class="form-control required" id="payer${status.count }" name="payer"
                                           value="${entity.payer }"/>
                                </div>
                            </div>
                        </td>
                        <c:if test="${status.count <= 1}">
                            <td style="vertical-align:top">
                                <div style="background: #2aabd2;width: 50px;height: 30px;line-height: 26px;"  onclick="addData()"
                                     align="center" >
                                    +
                                </div>
                            </td>
                        </c:if>
                        <c:if test="${status.count > 1}">
                            <td style="vertical-align:top">
                                <div style="background: #2aabd2;width: 50px;height: 30px;line-height: 26px;"  onclick="deleteData(${status.count})"
                                     align="center" >
                                    -
                                </div>
                            </td>
                        </c:if>
                    </tr>
                    </c:forEach>
                    </tbody>
                </table>
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

    $(function () {

    });
    var idCount = ${listSize};
    function check_fun() {
        for(var i = 0;i < idCount;i++) {
            var k = i + 1;
            var payDateObj = $("#payDate" + k + "").val();
            var payAmountObj = $("#payAmount" + k + "").val();
            var payerObj = $("#payer" + k + "").val();
            if (payDateObj == null || payDateObj == '') {
                $("#tip" + k + "").html("数据不能为空！");
                return false;
            }
            if (payAmountObj == null || payAmountObj == '') {
                $("#tip" + k + "").html("数据不能为空！");
                return false;
            }
            if (payerObj == null || payerObj == '') {
                $("#tip" + k + "").html("数据不能为空！");
                return false;
            }
            for(var j = i+1;j <= idCount;j++) {
                $("#tip" + j + "").html("");
                var tempValue = $("#payDate" + i + "").val();
                var tempValueCompare = $("#payDate" + j + "").val();

                if (tempValue !== null && tempValue !== '' && tempValue !== undefined) {
                    if (tempValueCompare !== null && tempValueCompare !== '' && tempValueCompare !== undefined) {
                        if (tempValue === tempValueCompare) {
                            $("#tip" + j + "").html("时间重复！");
                            return false;
                        }
                    }
                }
            }
        }

        return true;

    }
    /**
     * 新增一行
     * */
    function addData() {
        idCount = idCount +1;
       var tempData ="<tr id=\"deleteId"+idCount+"\">"+
        "<td style=\"vertical-align:top\">"+
            "<div class=\"input-group date\">"+
            "<label class=\"col-sm-5 control-label\">打款时间：<font color=\"red\">&nbsp;*</font></label>"+
        "<div class=\"col-sm-7\">"+
            "<input type=\"date\"  class=\"form-control required\" id=\"payDate"+idCount+"\" name=\"payDate\""+
        "value=\"\" style=\"width: 182px\"/>"+
            "</div>"+
            "</div>"+
           "<div class=\"form-group\">"+
            "<div class=\"col-sm-5\" >"+
            "<span id=\"tip"+idCount+"\" style=\"color: red;font-size: 14px;margin-left:20px; \"></span>"+
            "</div>"+
            "</div>"+
            "</td>"+
            "<td style=\"vertical-align:top\">"+
            "<div class=\"form-group\">"+
            "<label class=\"col-sm-5 control-label\">打款金额：<font color=\"red\">&nbsp;*</font></label>"+
        "<div class=\"col-sm-5\">"+
            "<input type=\"text\" class=\"form-control required\" id=\"payAmount"+idCount+"\" name=\"payAmount\""+
        "value=\"0\"/>"+
            "</div>"+
            "</div>"+
            "</td>"+
            "<td style=\"vertical-align:top\">"+
            "<div class=\"form-group\">"+
            "<label class=\"col-sm-5 control-label\">打款人：<font color=\"red\">&nbsp;*</font></label>"+
        "<div class=\"col-sm-5\">"+
            "<input type=\"text\" class=\"form-control required\" id=\"payer"+idCount+"\" name=\"payer\""+
        "value=\"\"/>"+
            "</div>"+
            "</div>"+
            "</td>"+
            "<td style=\"vertical-align:top\">"+
            "<div  style=\"background: #2aabd2;width: 50px;height: 30px;line-height: 26px;\"  onclick=\"deleteData("+idCount+")\""+
        "align=\"center\" >"+
            "-"+
            "</div>"+
            "</td>"+
            "</tr>";
        $("#contents").append(tempData);

    }

    /**
     * 删除当前行，如果是已存在的，则需要删除操作
     * @param data 当前行统一的id编号
     */
    function deleteData(data) {
        var entityId = $("#entityId"+data+"").val();
        // 需要被删除的id
        if (entityId !== null && entityId !== '' && entityId !== undefined) {
            var idsInput = "<input type=\"hidden\"  class=\"form-control required\" name=\"deleteIds\""+
             " value='"+entityId+"' />";
            var deleteDiv = $("#deleteDiv").append(idsInput);
        }
        // 删除当前行
        $("#deleteId"+data+"").remove();
    }


</script>