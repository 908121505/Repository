<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<shiro:hasPermission name="apptype:select">
    <div class="content1">
        <div class="header">
            <h1 class="page-title">app分享配置</h1>
            <ul class="breadcrumb">
                <li>app分享配置</li>
                <li class="active">app分享配置</li>
            </ul>
        </div>
        <div class="main-content">
            <div class="row">
                <div class="col-md-2">
                    <div class="form-group">
                        <div class="input-group">
                            <div class="input-group-addon">分享类型</div>
                            <select class="form-control" id="typeSelect">
                                <option value="">全部</option>
                                <option value="1">个人主页分享</option>
                            </select>
                        </div>
                    </div>
                </div>

                <div class="col-md-2">
                    <button type="button" class="btn btn-primary btn-small btn-block"
                            id="query">
                        <i class="glyphicon glyphicon-search"></i> 查询
                    </button>
                </div>
                <div class="col-md-2">
                    <shiro:hasPermission name="appShareConfig:add">
                        <button type="button" class="btn btn-info btn-small btn-block"
                                onclick="addAndUpdateRow(0)">
                            <i class="glyphicon glyphicon-plus"></i> 增加
                        </button>
                    </shiro:hasPermission>
                </div>
            </div>
            <table id="example" class="table"></table>
        </div>
        <script>
            //表格的初始化
            $(document).ready(function () {

                var table = $('#example').initTable({
                    sAjaxSource: "appShareConfig/initTable.htm",
                    aoColumns: [
                        {
                            "data": "title",
                            "sTitle": "分享标题",
                            'sClass': "text-center"
                        },
                        {
                            "data": "content",
                            "sTitle": "分享内容",
                            'sClass': "text-center"
                        },
                        {
                            "data": "linkUrl",
                            "sTitle": "查看原文链接",
                            'sClass': "text-center"
                        },
                        {
                            "data": "type",
                            "sTitle": "分享类型",
                            'sClass': "text-center",
                            "mRender": function (data, type, full) {
                                if (data == "1") {
                                    return "个人主页分享";
                                } else {
                                    return data;
                                }
                            }
                        },
                        {"data": "iconUrl", "sTitle": "分享图标", 'sClass': "text-center",
                            "mRender": function (data, type, full) {
                                return "<img src='" + data + "' height='50px;'/>";
                            }
                        },
                        <shiro:hasPermission name="appShareConfig:update or appShareConfig:delete">
                        {"data": "id", "sTitle": "操作", 'sClass': "text-center"}
                        </shiro:hasPermission>
                    ],
                    fnServerParams: function (aoData) {  //查询条件
                        aoData.push({"name": "type", "value": $("#typeSelect").val()});
                    },
                    aoColumnDefs: [
                        <shiro:hasPermission name="appShareConfig:update or appShareConfig:delete">
                        {
                            "aTargets": 5, "mRender": function (data, type, row) {
                            var edit = "", del = "";
                            <shiro:hasPermission name="appShareConfig:update">
                            edit = "<a href='#' onclick='addAndUpdateRow(\"" + data + "\")' data-toggle='modal' class='padding-right-small label label-success'><i class='glyphicon glyphicon-edit'></i> 编辑</a>";
                            </shiro:hasPermission>
                            <shiro:hasPermission name="appShareConfig:delete">
                            del = "<a href='#' onclick='deleteRow(\"" + data + "\")' data-toggle='modal' class='label label label-danger'><i class='glyphicon glyphicon-trash'></i> 删除 </a>";
                            </shiro:hasPermission>
                            return edit + "&nbsp;" + del;
                        }
                        }
                        </shiro:hasPermission>
                    ]
                });

                $('#query').click(function () {
                    $('#example').dataTable().fnDraw();
                });

            });
            //删除受影响的行数
            function deleteRow(id) {
                console.log(id);
                $('#myModal').deleteRow('appShareConfig/del.htm?id=' + id);
            }
            //增加或者修改受影响的行数
            function addAndUpdateRow(id) {
                $('#insertAndUpdate').addAndUpdateRow("appShareConfig/addAndUpdateHome.htm?id=" + id);
            }
        </script>
        <!---dialog选项-->
        <div>
            <jsp:include page="/WEB-INF/views/common/delete_dialog.jsp"/>
            <jsp:include page="/WEB-INF/views/common/addupdate_dialog.jsp"/>

            <div class="modal fade" id="insertAndUpdate" tabindex="-1"
                 role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
            </div>
        </div>
    </div>
</shiro:hasPermission>