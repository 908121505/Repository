<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<shiro:hasPermission name="community:statistics:select">
    <div class="content1">
        <div class="header">
            <h1 class="page-title">统计管理</h1>
            <ul class="breadcrumb">
                <li>社区实时统计分析</li>
                <li class="active">社区实时统计列表</li>
                <spam>&nbsp;&nbsp;<font color="red">(只查询当天实时数据)</font></spam>
            </ul>
        </div>
        <div class="main-content">
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
                    sAjaxSource:"${productLabels }"+"/initTableC.htm",
                    aoColumns: [
                        {
                            "data": "date",
                            "sTitle": "统计时间",
                            'sClass': "text-center"
                        },
                        {
                            "data": "actualPraiseNum",
                            "sTitle": "实际点赞数量",
                            'sClass': "text-center"
                        },
                        {
                            "data": "actualSeeNum",
                            "sTitle": "实际查看数量",
                            'sClass': "text-center"
                        },
                        {
                            "data": "actualCommentNum",
                            "sTitle": "实际评论数量",
                            'sClass': "text-center"
                        },
                        {
                            "data": "realPraiseNum",
                            "sTitle": "点赞总人数",
                            'sClass': "text-center"
                        },
                        {
                            "data": "realSeeNum",
                            "sTitle": "查看总人数",
                            'sClass': "text-center"
                        },
                        {
                            "data": "realCommentNum",
                            "sTitle": "评论总人数",
                            'sClass': "text-center"
                        }
                    ],
                    fnServerParams: function (aoData) {  //查询条件
                        aoData.push({"name": "sDate", "value": $("#sTime").val()});
                        aoData.push({"name": "eDate", "value": $("#eTime").val()});
                    }
                });

                $('#query').click(function () {
                    $('#example').dataTable().fnDraw();
                });

            });

        </script>
        <!---dialog选项-->
        <div>
            <jsp:include page="/WEB-INF/views/common/delete_dialog.jsp"/>
            <jsp:include page="/WEB-INF/views/common/addupdate_dialog.jsp"/>

        </div>
    </div>
</shiro:hasPermission>