<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>

<shiro:hasPermission name="banner:select">
    <div class="content1">
        <div class="header">
            <h1 class="page-title">banner管理</h1>
            <ul class="breadcrumb">
                <li>APP配置</li>
                <li class="active">banner管理</li>
            </ul>
        </div>
        <div class="main-content">
            <div class="row">
                <div class="col-md-2">
                    <div class="form-group">
                        <div class="input-group">
                            <div class="input-group-addon">标题</div>
                            <input class="form-control" type="text" id="title">
                        </div>
                    </div>
                </div>
                <div class="col-md-2">
                    <div class="form-group">
                        <div class="input-group">
                            <div class="input-group-addon">banner类型</div>
                            <select class="form-control" id="bannerType_query" name="bannerType">
                                <option value="">--请选择--</option>
                                <option value="1" ${entity.bannerType=='1'?'selected':''}>首页顶部banner</option>
                                <option value="2" ${entity.bannerType=='2'?'selected':''}>首页中部banner</option>
                                <option value="3" ${entity.bannerType=='3'?'selected':''}>分类页banner</option>
                            </select>
                        </div>
                    </div>
                </div>
                <div class="col-md-2">
                    <div class="form-group">
                        <div class="input-group">
                            <div class="input-group-addon">状态</div>
                            <select class="form-control" id="bannerStatus">
                                <option value="">--请选择--</option>
                                <option value="1">开启</option>
                                <option value="0">关闭</option>
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
                <shiro:hasPermission name="banner:add">
                    <div class="col-md-2">
                        <button type="button" class="btn btn-info btn-small btn-block"
                                onclick="addAndUpdateRow(0)">
                            <i class="glyphicon glyphicon-plus"></i> 增加
                        </button>
                    </div>
                </shiro:hasPermission>
            </div>
            <div class="row">
                <div class="col-md-2">
                    <div class="form-group">
                        <div class="input-group">
                            <div class="input-group-addon">有效时间</div>
                            <input class="form-control" type="date" id="sTime">
                            <div class="input-group-addon">到</div>
                            <input class="form-control" type="date" id="eTime">
                        </div>
                    </div>
                </div>
            </div>
            <table id="example" class="table"></table>
        </div>
        <script>
            //表格的初始化
            $(document).ready(function () {
                var table = $('#example').initTable({
                    sAjaxSource: "banner/initTable.htm",
                    aoColumns: [
                        {"data": "title", "sTitle": "标题", 'sClass': "text-center", "sortable": false},
                        {"data": "imageUrl", "sTitle": "图片", 'sClass': "text-center", "sortable": false,
                            "mRender": function (data, type, full) {
                                return "<img src='" + data + "' height='50px;'/>";
                            }
                        },
                        {"data": "bannerType", "sTitle": "类型", 'sClass': "text-center", "sortable": false,
                            "mRender": function (data, type, full) {
                                return getBannerType(data);
                            }
                        },
                        {"data": "clickType", "sTitle": "跳转方式", 'sClass': "text-center", "sortable": false,
                            "mRender": function (data, type, full) {
                                return getClickType(data);
                            }
                        },
                        {"data": "startTime", "sTitle": "开始时间", 'sClass': "text-center", "sortable": false,
                            "mRender": function (data, type, full) {
                                return formatDate(data);
                            }
                        },
                        {"data": "endTime", "sTitle": "结束时间", 'sClass': "text-center", "sortable": false,
                            "mRender": function (data, type, full) {
                                return formatDate(data);
                            }
                        },
                        {"data": "deviceType", "sTitle": "设备类型", 'sClass': "text-center", "sortable": false,
                            "mRender": function (data, type, full) {
                                return getDeviceType(data);
                            }
                        },
                        {"data": "appVersionRule", "sTitle": "版本号匹配规则", 'sClass': "text-center", "sortable": false,
                            "mRender": function (data, type, full) {
                                return getAppVersionRule(data);
                            }
                        },
                        {"data": "appVersion", "sTitle": "版本号", 'sClass': "text-center", "sortable": false},
                        {"data": "sort", "sTitle": "排序", 'sClass': "text-center", "sortable": false},
                        {"data": "remark", "sTitle": "备注", 'sClass': "text-center", "sortable": false},
                        {"data": "bannerStatus", "sTitle": "状态", 'sClass': "text-center", "sortable": false,
                            "mRender": function (data, type, full) {
                                return data == 1 ? "<font color='red'>开启</font>" : "<font color='blue'>关闭</font>";
                            }
                        }
                        <shiro:hasPermission name="banner:update or banner:delete">
                        , {"data": "bannerId", "sTitle": "操作", 'sClass': "text-center", "sortable": false}
                        </shiro:hasPermission>
                    ],
                    fnServerParams: function (aoData) {  //查询条件
                        aoData.push({"name": "title", "value": $("#title").val()});
                        aoData.push({"name": "state", "value": $("#bannerStatus").val()});
                        aoData.push({"name": "bannerType", "value": $("#bannerType_query").val()});
                        aoData.push({"name": "startTime", "value": $("#sTime").val()});
                        aoData.push({"name": "endTime", "value": $("#eTime").val()});
                    },
                    aoColumnDefs: [
                        {
                            "aTargets": 12, "mRender": function (data, type, row) {
                                var edit = "", del = "";
                                <shiro:hasPermission name="banner:update">
                                edit = "<a href='#' onclick='addAndUpdateRow(\"" + data + "\")' data-toggle='modal' class='padding-right-small label label-success'><i class='glyphicon glyphicon-edit'></i> 编辑</a>";
                                </shiro:hasPermission>

                                <shiro:hasPermission name="banner:delete">
                                del = "<a href='#' onclick='deleteRow(\"" + data + "\")' data-toggle='modal' class='label label label-danger'><i class='glyphicon glyphicon-trash'></i> 删除 </a>";
                                </shiro:hasPermission>
                                return edit + "&nbsp;" + del;
                            }
                        }
                    ]
                });

                $('#query').click(function () {
                    $('#example').dataTable().fnDraw();
                });

            });

            //删除受影响的行数
            function deleteRow(bannerId) {
                $('#myModal').deleteRow('banner/del.htm?bannerId=' + bannerId);
            }

            //增加或者修改受影响的行数
            function addAndUpdateRow(bannerId) {
                $('#bannerInsertAndUpdate').addAndUpdateRow("banner/addAndUpdateHome.htm?bannerId=" + bannerId);
            }

            /** 获取Banner类型 **/
            function getBannerType(data) {
                if (data == "1") {
                    return "首页顶部banner";
                } else if (data == "2") {
                    return "首页中部banner";
                } else if (data == "3") {
                    return "分类页banner";
                }
                return data;
            }
            /** 获取跳转方式 **/
            function getClickType(data) {
                if (data == "0") {
                    return "不跳转";
                } else if (data == "1") {
                    return "HTML页面";
                } else if (data == "2") {
                    return "个人主页";
                } else if (data == "3") {
                    return "分类页";
                }
                return data;
            }

            /** 格式化日期 **/
            function formatDate(data) {
                if (data == null || data.length < 19) {
                    return "";
                } else {
                    return data.substring(0, 19);
                }
            }

            /** 获取设备类型 **/
            function getDeviceType(data) {
                if (data == 1) {
                    return "<font color='red'>IOS</font>";
                } else if (data == 2) {
                    return "<font color='red'>Android</font>";
                } else {
                    return "<font color='blue'>所有设备</font>";
                }
            }

            /** 获取版本规则 **/
            function getAppVersionRule(data) {
                if (data == 1) {
                    return "<font >大于</font>";
                } else if (data == 2) {
                    return "<font >小于</font>";
                } else if (data == 3) {
                    return "<font >等于</font>";
                } else if (data == 4) {
                    return "<font >大于等于</font>";
                } else if (data == 5) {
                    return "<font >小于等于</font>";
                } else {
                    return "<font >所有版本</font>";
                }
            }
        </script>
        <!---dialog选项-->
        <div>
            <jsp:include page="/WEB-INF/views/common/delete_dialog.jsp"/>

            <div class="modal fade" id="bannerInsertAndUpdate" tabindex="-1"
                 role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static">
            </div>
        </div>
    </div>
</shiro:hasPermission>