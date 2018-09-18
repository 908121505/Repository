<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<shiro:hasPermission name="arrondi:statistics:select">
    <div class="content1">
        <div class="header">
            <h1 class="page-title">统计管理</h1>
            <ul class="breadcrumb">
                <li>专区统计分析</li>
                <li class="active">专区统计列表</li>
                <spam>&nbsp;&nbsp;<font color="red">(*时间不填或只填一个默认查近15天数据)</font></spam>
            </ul>
        </div>
        <div class="main-content">
            <div class="row">
                <div class="col-md-2">
                    <div class="form-group">
                        <div class="input-group">
                            <div class="input-group-addon">专区名称</div>
                            <input class="form-control" type="text" id="name">
                        </div>
                    </div>
                </div>
                <div class="col-md-4">
                    <div class="form-group">
                        <div class="input-group">
                            <div class="input-group-addon">时间</div>
                            <input class="form-control" type="date" id="sTime">
                            <div class="input-group-addon">到</div>
                            <input class="form-control" type="date" id="eTime">
                        </div>
                    </div>
                </div>
                <div class="col-md-1">
                    &nbsp;
                </div>
                <div class="col-md-1">
                    <button type="button" class="btn btn-primary btn-small btn-block"
                            id="query">
                        <i class="glyphicon glyphicon-search"></i> 查询
                    </button>
                </div>
                <div class="col-md-1">
                    <div class="form-group">
                        <a class="btn btn-primary btn-small btn-block" id="data_export" onclick="exportData();">导出数据</a>
                    </div>
                </div>
            </div>
            <table id="example" class="table"></table>
        </div>

        <script>
            //表格的初始化
            $(document).ready(function () {
                var table = $('#example').initTable({
                    sAjaxSource: "${productLabels }"+"/initTable.htm",
                    aoColumns: [
                        {
                            "data": "date",
                            "sTitle": "统计时间",
                            'sClass': "text-center"
                        },
                        {
                            "data": "name",
                            "sTitle": "专区名称",
                            'sClass': "text-center"
                        },
                        {
                            "data": "position",
                            "sTitle": "专区位置",
                            'sClass': "text-center"
                        },
                        {
                            "data": "users",
                            "sTitle": "独立用户数",
                            'sClass': "text-center"
                        },
                        {
                            "data": "clickCount",
                            "sTitle": "专区点击次数",
                            'sClass': "text-center"
                        },
                        {
                            "data": "",
                            "sTitle": "点击率",
                            'sClass': "text-center",
                            "mRender": function (data, type, full) {
                                return Number(full.clickCount/full.users*100).toFixed(0) + '%'
                            }
                        },
                        {
                            "data": "arrondiId",
                            "sTitle": "操作",
                            'sClass': "text-center",
                            "mRender": function (data, type, full) {
                                return "<a class='label label label-success' onclick='statisticsDetail(this)' data-name="+full.name+" data-id="+full.arrondiId+" data-date="+full.date+" data-toggle='modal'><i class='glyphicon glyphicon-edit'>查看详情</i></a>"
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

            function exportData(){
                var href="export/arrondi.htm"
                    +"?sDate="+$("#sTime").val()
                    +"&eDate="+$("#eTime").val()
                    +"&name="+$("#name").val();
                $('#data_export').attr("href",href);
            }

            function statisticsDetail(obj) {
                var id = $(obj).attr("data-id");
                var date = $(obj).attr("data-date");
                localStorage.setItem("arrondi",$(obj).attr("data-name"));
                $('#insertAndUpdate').addAndUpdateRow("arrondi/statistics/detail.htm?id=" + id+ "&date=" + date);
            }

        </script>
        <!---dialog选项-->
        <div>
            <jsp:include page="/WEB-INF/views/common/delete_dialog.jsp"/>
            <jsp:include page="/WEB-INF/views/common/addupdate_dialog.jsp"/>

        </div>
    </div>
</shiro:hasPermission>