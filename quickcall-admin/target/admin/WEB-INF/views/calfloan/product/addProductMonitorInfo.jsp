<%--
  Created by IntelliJ IDEA.
  User: luoyanchong
  Date: 2018/4/9
  Time: 20:15
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <title>新增/修改产品监控信息</title>
</head>
<body>
<div class="modal-dialog">
    <div class="modal-content">
        <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal"
                    aria-hidden="true">X</button>
            <h3 id="myModalLabel">${empty entity?'新增':'修改' }产品监控信息</h3>
        </div>
        <div class="modal-body">
            <form class="form-horizontal" method="post" id="productMonitorlForm" name="productMonitorlForm"
                  action="monitorInfo/save${empty entity?'Insert':'Update' }.htm"
                  role="form">
                <div class="form-group">
                    <label class="col-sm-2 control-label">产品ID<font color="red">&nbsp;*</font></label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" id="product_id" name="productId" value="${entity.productId}">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">产品名称<font color="red">&nbsp;*</font></label>
                    <div class="col-sm-10">
                        <c:if test="${empty entity==false}">
                            <input type="hidden" value="${entity.productMonitorInfoId }" id="productMonitorInfoId" name="productMonitorInfoId" />
                            <input type="hidden" value="${entity.createTime }" id="createTime" name="createTime" />
                            <input type="hidden" value="${entity.createMan }" id="createMan" name="createMan" />
                            <input type="hidden" value="${entity.modifyTime }" id="modifyTime" name="modifyTime" />
                            <input type="hidden" value="${entity.modifyMan }" id="modifyMan" name="modifyMan" />
                        </c:if>
                        <input type="text" class="form-control" id="product_name" name="productName" value="${entity.productName}">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">状态<font color="red">&nbsp;*</font></label>
                    <div class="col-sm-10">
                        <label class="checkbox-inline"> <input type="radio"
                                                               name="state" value="1" ${entity.state=='1'?'checked':'' }>
                            启用
                        </label> <label class="checkbox-inline"> <input type="radio"
                                                                        name="state" value="2" ${entity.state=='2'?'checked':'' }>
                        暂停
                    </label>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">跳转地址<font color="red">&nbsp;</font></label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" id="url_m" name="url" value="${entity.url}">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">key<font color="red">&nbsp;*</font></label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" id="key_m" name="key" value="${entity.key}">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">value<font color="red">&nbsp;*</font></label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" id="value_m" name="valueM" value="${entity.valueM}">
                    </div>
                </div>
                <span id="tip" style="color: red;font-size: 14px;margin-left:20px; "></span>
            </form>
        </div>
        <div class="modal-footer">
            <button class="btn btn-default" data-dismiss="modal"
                    aria-hidden="true">取消</button>
            <button class="btn btn-primary" data-dismiss="modal">保存</button>
        </div>
    </div>
</div>

<script type="text/javascript">




    function check_fun(){
        $("#tip").html("");
        var b = true;
        var productName = $("#product_name").val();
        var url = $("#url_m").val();
        var key = $("#key_m").val();
        var valueM = $("#value_m").val();
        var productId = $("#product_id").val();
        var state =  $('input[name="state"]:checked').val();;

        if(productName == null || productName.trim() == ''){
            $("#tip").html("请输入产品名称");
            b = false;
        }else if(state == null || state.trim() == ''){
            $("#tip").html("请选择状态");
            b = false;
        }else if(key == null || key.trim() == ''){
            $("#tip").html("输入key");
            b = false;
        }else if(valueM == null || valueM.trim() == ''){
            $("#tip").html("输入value");
            b = false;
        }else if(productId == null || productId.trim() == ''){
            $("#tip").html("输入产品ID");
            b = false;
        }
        return b;
    }

</script>
</body>
</html>
