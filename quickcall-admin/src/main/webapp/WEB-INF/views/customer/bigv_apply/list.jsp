<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>

<shiro:hasPermission name="customerApplyBigv:select">
    <div class="content1">
        <div class="header">
            <h1 class="page-title">声优申请管理</h1>
            <ul class="breadcrumb">
                <li>用户管理</li>
                <li class="active">声优申请管理</li>
            </ul>
        </div>
        <div class="main-content">
            <div class="row">
                <div class="col-md-2">
                    <div class="form-group">
                        <div class="input-group">
                            <div class="input-group-addon">开始时间</div>
                            <input class="form-control" value="${showStartTime}" type="text" id="startTime" onfocus="WdatePicker({skin:'default',dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'1950-01-01'})">
                        </div>
                    </div>
                </div>
                <div class="col-md-2">
                    <div class="form-group">
                        <div class="input-group">
                            <div class="input-group-addon">结束时间</div>
                            <input class="form-control" value="${showEndTime}" type="text" id="endTime" onfocus="WdatePicker({skin:'default',dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'1950-01-01'})">
                        </div>
                    </div>
                </div>
                <div class="col-md-3">
                    <div class="form-group">
                        <div class="input-group">
                            <div class="input-group-addon">用户ID或用户昵称</div>
                            <input class="form-control" type="text" id="name" maxlength="20">
                        </div>
                    </div>
                </div>
                <div class="col-md-2">
                    <div class="form-group">
                        <div class="input-group">
                            <div class="input-group-addon">联系状态</div>
                            <select class="form-control" id="handleStatus">
                                <option value="">--请选择--</option>
                                <option value="0">未联系</option>
                                <option value="1">已联系</option>
                            </select>
                        </div>
                    </div>
                </div>
                <div class="col-md-2">
                    <button type="button" class="btn btn-primary btn-small btn-block" id="query">
                        <i class="glyphicon glyphicon-search"></i> 查询
                    </button>
                </div>
            </div>
            <table id="example" class="table"></table>
        </div>
        <script>
            //表格的初始化
            $(document).ready(function () {
                var table = $('#example').initTable({
                    sAjaxSource: "customerApplyBigv/initTable.htm",
                    aoColumns: [
                        {"data": "customerId", "sTitle": "用户ID", 'sClass': "text-center"},
                        {"data": "appId", "sTitle": "AppID", 'sClass': "text-center"},
                        {"data": "nickName", "sTitle": "用户昵称", 'sClass': "text-center"},
                        {"data": "phone", "sTitle": "手机号", 'sClass': "text-center"},
                        {"data": "applyTime", "sTitle": "申请时间", 'sClass': "text-center"},
                        {"data": "remark", "sTitle": "备注", 'sClass': "text-center"},
                        {"data": "handleStatus", "sTitle": "联系状态", 'sClass': "text-center",
                            "mRender": function (data, type, full) {
                                return data == 0 ? "<font color='red'>未联系</font>" : "<font color='blue'>已联系</font>";
                            }
                        }
                        <shiro:hasPermission name="customerApplyBigv:update">
                        , {"data": "applyId", "sTitle": "操作", 'sClass': "text-center", "mRender": function (data, type, row) {
                                var edit = "";
                                edit = "<a href='#' onclick='addAndUpdateRow(\"" + data + "\")' data-toggle='modal' class='padding-right-small label label-success'><i class='glyphicon glyphicon-edit'></i> 编辑</a>";
                                return edit;
                            }
                        }
                        </shiro:hasPermission>
                    ],
                    fnServerParams: function (aoData) {  //查询条件
                        aoData.push({"name": "startTime", "value": $("#startTime").val()});
                        aoData.push({"name": "endTime", "value": $("#endTime").val()});
                        aoData.push({"name": "name", "value": $.trim($("#name").val())});
                        aoData.push({"name": "handleStatus", "value": $("#handleStatus").val()});
                    }
                });

                $('#query').click(function () {
                    $('#example').dataTable().fnDraw();
                });

            });

            //删除受影响的行数
            function deleteRow(id) {
                $('#myModal').deleteRow('customerApplyBigv/del.htm?id=' + id);
            }

            //增加或者修改受影响的行数
            function addAndUpdateRow(id) {
                $('#bigvPhoneInsertAndUpdate').addAndUpdateRow("customerApplyBigv/addAndUpdateHome.htm?id=" + id);
            }

        </script>
        <!---dialog选项-->
        <div>
            <jsp:include page="/WEB-INF/views/common/delete_dialog.jsp"/>

            <div class="modal fade" id="bigvPhoneInsertAndUpdate" tabindex="-1"
                 role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static">
            </div>
        </div>
    </div>
</shiro:hasPermission>