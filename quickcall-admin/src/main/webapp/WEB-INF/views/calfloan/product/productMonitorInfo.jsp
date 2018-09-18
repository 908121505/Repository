<%--
  Created by IntelliJ IDEA.
  User: luoyanchong
  Date: 2018/4/9
  Time: 18:01
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<body>
<%--<shiro:hasPermission name="monitorInfo:select">当前用户有查询权限</shiro:hasPermission>--%>
<%--<shiro:lacksPermission name="monitorInfo:select">当前用户没有查询权限</shiro:lacksPermission>--%>
<%--<shiro:hasPermission name="monitorInfo:add">当前用户有增加权限</shiro:hasPermission>--%>
<%--<shiro:lacksPermission name="monitorInfo:add">当前用户没有增加权限</shiro:lacksPermission>--%>
<%--<shiro:hasPermission name="monitorInfo:select">--%>
    <div class="content1">
        <div class="header">
            <ul class="breadcrumb">
                <li>产品信息监控</li>
                <li class="active">产品监控列表</li>
            </ul>
        </div>
        <div class="main-content">
            <div class="row">
                <div class="col-md-2">
                    <div class="form-group">
                        <div class="input-group">
                            <div class="input-group-addon">产品名称</div>
                            <input class="form-control" type="text" id="productName">
                        </div>
                    </div>
                </div>
                <div class="col-md-2">
                    <div class="form-group">
                        <div class="input-group">
                            <div class="input-group-addon">产品ID</div>
                            <input class="form-control" type="text" id="productId">
                        </div>
                    </div>
                </div>
                <div class="col-md-2">
                    <div class="form-group">
                        <div class="input-group">
                            <div class="input-group-addon">key</div>
                            <input class="form-control" type="text" id="key">
                        </div>
                    </div>
                </div>
                <div class="col-md-2">
                    <div class="form-group">
                        <div class="input-group">
                            <div class="input-group-addon">value</div>
                            <input class="form-control" type="text" id="valueM">
                        </div>
                    </div>
                </div>

                <div class="col-md-2">
                    <div class="form-group">
                        <div class="input-group">
                            <div class="input-group-addon">状态</div>
                            <select class="form-control" id="state">
                                <option value="">--请选择--</option>
                                <option value="1">启用</option>
                                <option value="2">暂停</option>
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
                    <%--<shiro:hasPermission name="monitorInfo:add">--%>
                        <button type="button" class="btn btn-info btn-small btn-block"
                                onclick="addAndUpdateRow('')">
                            <i class="glyphicon glyphicon-plus"></i> 增加
                        </button>
                    <%--</shiro:hasPermission>--%>
                </div>
                <div class="col-md-2">
                    <%--<shiro:hasPermission name="monitorInfo:add">--%>
                    <button type="button" class="btn btn-info btn-small btn-block"
                            onclick="queryProductId('')">
                        <i class="glyphicon glyphicon-search"></i> 产品ID查询
                    </button>
                    <%--</shiro:hasPermission>--%>
                </div>
            </div>
            <table id="example" class="table"></table>
        </div>
        <script>
            function Format(now, mask) {
                var d = now;
                var zeroize = function (value, length) {
                    if (!length) length = 2;
                    value = String(value);
                    for (var i = 0, zeros = ''; i < (length - value.length); i++) {
                        zeros += '0';
                    }
                    return zeros + value;
                };

                return mask.replace(/"[^"]*"|'[^']*'|\b(?:d{1,4}|m{1,4}|yy(?:yy)?|([hHMstT])\1?|[lLZ])\b/g, function ($0) {
                    switch ($0) {
                        case 'd':
                            return d.getDate();
                        case 'dd':
                            return zeroize(d.getDate());
                        case 'ddd':
                            return ['Sun', 'Mon', 'Tue', 'Wed', 'Thr', 'Fri', 'Sat'][d.getDay()];
                        case 'dddd':
                            return ['Sunday', 'Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday'][d.getDay()];
                        case 'M':
                            return d.getMonth() + 1;
                        case 'MM':
                            return zeroize(d.getMonth() + 1);
                        case 'MMM':
                            return ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec'][d.getMonth()];
                        case 'MMMM':
                            return ['January', 'February', 'March', 'April', 'May', 'June', 'July', 'August', 'September', 'October', 'November', 'December'][d.getMonth()];
                        case 'yy':
                            return String(d.getFullYear()).substr(2);
                        case 'yyyy':
                            return d.getFullYear();
                        case 'h':
                            return d.getHours() % 12 || 12;
                        case 'hh':
                            return zeroize(d.getHours() % 12 || 12);
                        case 'H':
                            return d.getHours();
                        case 'HH':
                            return zeroize(d.getHours());
                        case 'm':
                            return d.getMinutes();
                        case 'mm':
                            return zeroize(d.getMinutes());
                        case 's':
                            return d.getSeconds();
                        case 'ss':
                            return zeroize(d.getSeconds());
                        case 'l':
                            return zeroize(d.getMilliseconds(), 3);
                        case 'L':
                            var m = d.getMilliseconds();
                            if (m > 99) m = Math.round(m / 10);
                            return zeroize(m);
                        case 'tt':
                            return d.getHours() < 12 ? 'am' : 'pm';
                        case 'TT':
                            return d.getHours() < 12 ? 'AM' : 'PM';
                        case 'Z':
                            return d.toUTCString().match(/[A-Z]+$/);
                        // Return quoted strings with the surrounding quotes removed
                        default:
                            return $0.substr(1, $0.length - 2);
                    }
                });
            };

            //表格的初始化
            $(document).ready(function () {
                var table = $('#example').initTable({
                    sAjaxSource: "monitorInfo/initTable.htm",
                    aoColumns: [
                        {
                            "data": "productId",
                            "sTitle": "产品ID",
                            'sClass': "text-center"
                        },
                        {
                            "data": "productName",
                            "sTitle": "产品名称",
                            'sClass': "text-center"
                        },
                        {
                            "data": "url",
                            "sTitle": "跳转地址",
                            'sClass': "text-center"
                        },
                        {
                            "data": "key",
                            "sTitle": "key",
                            'sClass': "text-center"
                        },
                        {
                            "data": "valueM",
                            "sTitle": "value",
                            'sClass': "text-center"
                        },
                        {
                            "data": "createTime",
                            "sTitle": "创建时间",
                            'sClass': "text-center",
                            "mRender": function (data, type, full) {
                                return Format(new Date(data), "yyyy-MM-dd HH:mm:ss")
                            }
                        },
                        {
                            "data": "state",
                            "sTitle": "状态",
                            'sClass': "text-center",

                        }
                        <%--<shiro:hasPermission name="monitorInfo:update or monitorInfo:delete">--%>
                        , {"data": "", "sTitle": "操作", 'sClass': "text-center"}
                        <%--</shiro:hasPermission>--%>
                    ],
                    fnServerParams: function (aoData) {  //查询条件
                        aoData.push({"name": "productId", "value": $("#productId").val()});
                        aoData.push({"name": "productName", "value": $("#productName").val()});
                        aoData.push({"name": "key", "value": $("#key").val()});
                        aoData.push({"name": "valueM", "value": $("#valueM").val()});
                        aoData.push({"name": "url", "value": $("#url").val()});
                        aoData.push({"name": "state", "value": $("#state").val()});
                    },
                    aoColumnDefs: [
                        {
                            "aTargets": 6, "mRender": function (data, type, row) {
                            var edit = "";
                            var state = row.state;
                            if (state == '1') {
                                edit = "启用";
                            } else if (state == '2') {
                                edit = "<font color='red'>暂停</font>";
                            } else {
                                edit = "未知";
                            }
                            return edit;
                        }

                        },
                        <%--<shiro:hasPermission name="monitorInfo:update or monitorInfo:delete">--%>
                        {
                            "aTargets": 7, "mRender": function (data, type, row) {
                            var edit = "",del = "";
                            <%--<shiro:hasPermission name="monitorInfo:update">--%>
                            edit = "<a href='#' onclick='addAndUpdateRow(\"" + row.productMonitorInfoId + "\")' data-toggle='modal' class='padding-right-small label label-success'><i class='glyphicon glyphicon-edit'></i> 编辑</a>";
                            <%--</shiro:hasPermission>--%>
                            <%--<shiro:hasPermission name="monitorInfo:delete">--%>
                            del ="<a href='#' onclick='deleteRow(\""+row.productMonitorInfoId+"\")' data-toggle='modal' class='label label label-danger'><i class='glyphicon glyphicon-trash'></i> 删除 </a>";
                            <%--</shiro:hasPermission>--%>
                            return edit+"&nbsp;"+del;
                        }
                        }
                        <%--</shiro:hasPermission>--%>
                    ]
                });

                $('#query').click(function () {
                    $('#example').dataTable().fnDraw();
                });

            });
            //删除受影响的行数
            function deleteRow(id) {
                $('#myModal').deleteRow('monitorInfo/del.htm?id=' + id);
            }
            //增加或者修改受影响的行数
            function addAndUpdateRow(id) {
                $('#insertAndUpdate').addAndUpdateRow("monitorInfo/addAndUpdateHome.htm?id=" + id);
            }
            // 产品ID查询
            function queryProductId(id) {
                $('#insertAndUpdate').addAndUpdateRow("monitorInfo/queryProductIdHome");
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
<%--</shiro:hasPermission>--%>
