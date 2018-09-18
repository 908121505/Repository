<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<shiro:hasPermission name="cooperationRate:statistics:select">
    <div class="content1">
        <div class="header">
            <h1 class="page-title">统计管理</h1>
            <ul class="breadcrumb">
                <li>合作平台统计分析</li>
                <li class="active">合作平台实时点击率列表</li>
                <spam>&nbsp;&nbsp;<font color="red">(只统计当天实时数据)</font></spam>
            </ul>
        </div>
        <div class="main-content">
            <div class="row">
                <div class="col-md-2">
                    <div class="form-group">
                        <div class="input-group">
                            <div class="input-group-addon">产品名称</div>
                            <input class="form-control" type="text" id="name">
                        </div>
                    </div>
                </div>
                <div class="col-md-1">
                    <button type="button" class="btn btn-primary btn-small btn-block"
                            id="query">
                        <i class="glyphicon glyphicon-search"></i> 刷新
                    </button>
                </div>
            </div>
            <table id="example" class="table"></table>
        </div>

        <script>
            //表格的初始化
            $(document).ready(function () {
                var table = $('#example').initTable({
                    sAjaxSource: "${productLabels }"+"/initTableT.htm",
                    aoColumns: [
                        {
                            "data": "date",
                            "sTitle": "统计时间",
                            'sClass': "text-center"
                        },
                        {
                            "data": "",
                            "sTitle": "总点击率",
                            'sClass': "text-center",
                            "mRender": function (data, type, full) {
                                return Number((full.listCount + full.clickCount + full.applyCount)/full.users*100).toFixed(0) + '%'
                            }
                        },
                        {
                            "data": "",
                            "sTitle": "产品列表点击率",
                            'sClass': "text-center",
                            "mRender": function (data, type, full) {
                                return Number(full.listCount/full.users*100).toFixed(0) + '%'
                            }
                        },
                        {
                            "data": "productName",
                            "sTitle": "产品名称",
                            'sClass': "text-center"
                        },
                        {
                            "data": "position",
                            "sTitle": "产品位置",
                            'sClass': "text-center"
                        },
                        {
                            "data": "",
                            "sTitle": "产品点击率",
                            'sClass': "text-center",
                            "mRender": function (data, type, full) {
                                return Number(full.clickCount/full.users*100).toFixed(0) + '%'
                            }
                        },
                        {
                            "data": "",
                            "sTitle": "立即事件点击率",
                            'sClass': "text-center",
                            "mRender": function (data, type, full) {
                                return Number(full.applyCount/full.users*100).toFixed(0) + '%'
                            }
                        }
                    ],
                    fnServerParams: function (aoData) {  //查询条件
                        aoData.push({"name": "sDate", "value": $("#sTime").val()});
                        aoData.push({"name": "eDate", "value": $("#eTime").val()});
                        aoData.push({"name": "name", "value": $("#name").val()});
                    },
                });

                $('#query').click(function () {
                    $('#example').dataTable().fnDraw();
                });

            });

            function exportData() {
                var href = "export/cooperationRate.htm"
                    + "?sDate=" + $("#sTime").val()
                    + "&eDate=" + $("#eTime").val()
                    + "&name="+$("#name").val();
                $('#data_export').attr("href", href);
            }

        </script>
        <!---dialog选项-->
        <div>
            <jsp:include page="/WEB-INF/views/common/delete_dialog.jsp"/>
            <jsp:include page="/WEB-INF/views/common/addupdate_dialog.jsp"/>

        </div>
    </div>
</shiro:hasPermission>