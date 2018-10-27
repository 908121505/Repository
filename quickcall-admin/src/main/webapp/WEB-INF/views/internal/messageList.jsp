<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<div class="content1">
    <div class="header">
        <h1 class="page-title">消息管理</h1>
        <ul class="breadcrumb">
            <li>站内信</li>
            <li class="active">消息管理</li>
        </ul>
    </div>
    <div class="main-content">
        <div class="row">
            <div class="col-md-2">
                <div class="form-group">
                    <div class="input-group">
                        <div class="input-group-addon">开始时间</div>
                        <input class="form-control" type="date" id="sTime">
                        <div class="input-group-addon">到</div>
                        <input class="form-control" type="date" id="eTime">
                    </div>
                </div>
            </div>
            <div class="col-md-2" style="margin-left: 218px;">
                <div class="form-group">
                    <div class="input-group">
                        <div class="input-group-addon">消息类型</div>
                        <select class="form-control" id="type_query">
                            <option value="">--请选择--</option>
                            <option value="0">系统通知</option>
                            <option value="1">活动通知</option>
                        </select>
                    </div>
                </div>
            </div>
            <div class="col-md-2">
                <div class="form-group">
                    <div class="input-group">
                        <div class="input-group-addon">消息状态</div>
                        <select class="form-control" id="sendStatus_query">
                            <option value="">--请选择--</option>
                            <option value="2">开启</option>
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
            <div class="col-md-2">
                <button type="button" class="btn btn-info btn-small btn-block"
                        onclick="addAndUpdateRow()">
                    <i class="glyphicon glyphicon-plus"></i> 增加
                </button>
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
                sAjaxSource: "internal/initTable.htm",
                aoColumns: [
                    {
                        "data": "title",
                        "sTitle": "消息标题",
                        'sClass': "text-center"
                    },
                    {
                        "data": "messageContent",
                        "sTitle": "消息内容",
                        'sClass': "text-center"
                    },
                    {
                        "data": "h5Url",
                        "sTitle": "H5链接",
                        'sClass': "text-center",
                        "mRender": function (data, type, full) {
                            if (data == '' || data == null) {
                                return "--";
                            } else {
                                return "<a href='"+ data +"' target='_blank'>"+data+"</a>";
                            }
                        }
                    },
                    {
                        "data": "type",
                        "sTitle": "消息类型",
                        'sClass': "text-center",
                        "mRender": function (data, type, full) {
                            if(data == 0){
                                return "<font>系统通知</font>";
                            } else if(data == 1){
                                return "<font>活动通知</font>";
                            } else if(data == 2){
                                return "<font>通知消息</font>";
                            }else{
                                return "<font>--</font>";
                            }
                        }
                    },
                    {
                        "data": "sendStatus",
                        "sTitle": "消息状态",
                        'sClass': "text-center",
                        "mRender": function (data, type, full) {
                            if(data == 0){
                                return "<font>关闭</font>";
                            } else if(data == 1){
                                return "<font>待发送</font>";
                            } else if(data == 2){
                                return "<font>开启</font>";
                            }else{
                                return "<font>--</font>";
                            }
                        }
                    },
                    {
                        "data": "bespeakSendTime",
                        "sTitle": "发送时间",
                        'sClass': "text-center",
                        "mRender": function (data, type, full) {
                            if (data != null) {
                                return Format(
                                    new Date(
                                        data),
                                    "yyyy-MM-dd HH:mm:ss")
                            } else {
                                return '--';
                            }
                        }
                    },
                    {
                        "data": "createTime",
                        "sTitle": "创建时间",
                        'sClass': "text-center",
                        "mRender": function (data, type, full) {
                            if (data != null) {
                                return Format(
                                    new Date(
                                        data),
                                    "yyyy-MM-dd HH:mm:ss")
                            } else {
                                return '--';
                            }
                        }
                    },
                    {
                        "data": "messageId",
                        "sTitle": "操作",
                        'sClass': "text-center"
                    }
                ],
                fnServerParams: function (aoData) {
                    //查询条件
                    aoData.push({"name": "sendStatus", "value": $("#sendStatus_query").val()});
                    aoData.push({"name": "type", "value": $("#type_query").val()});
                    aoData.push({"name": "startTime", "value": $("#sTime").val()});
                    aoData.push({"name": "endTime", "value": $("#eTime").val()});
                },
                aoColumnDefs: [{
                    "aTargets": 7,
                    "mRender": function (data, type, row) {
                        var edit = "", del = "";
                        edit = "<a href='#' onclick='addAndUpdateRow(\"" + data.toString() + "\")' data-toggle='modal' class='padding-right-small label label-success'><i class='glyphicon glyphicon-edit'></i> 编辑</a>";

                        del = "<a href='#' onclick='deleteRow(\"" + data.toString() + "\")' data-toggle='modal' class='label label label-danger'><i class='glyphicon glyphicon-trash'></i> 删除 </a>";
                        return edit + "&nbsp;&nbsp;" + del;
                    }
                }]

            });

            $('#query').click(function () {
                $('#example').dataTable().fnDraw();
            });

        });

        //增加或者修改受影响的行数
        function addAndUpdateRow(id) {
            $('#insertAndUpdate').addAndUpdateRow("internal/addAndUpdateHome.htm?id=" + id);
        }

        //删除受影响的行数
        function deleteRow(id) {
            $('#myModal').deleteRow('internal/del.htm?id=' + id);
        }

    </script>
    <!---dialog选项-->
    <div>
        <jsp:include page="/WEB-INF/views/common/delete_dialog.jsp"/>

        <div class="modal fade" id="insertAndUpdate" tabindex="-1"
             role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
        </div>
    </div>
</div>
