<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<link type="text/css" rel="stylesheet" href="resources/plugins/viewer/viewer.min.css" media="screen"/>
<div class="content1">
    <div class="header">
        <h1 class="page-title">声鉴卡审核</h1>
        <ul class="breadcrumb">
            <li>客服后台</li>
            <li class="active">声鉴卡审核</li>
        </ul>
    </div>
    <div class="main-content">
        <div class="row">
            <div class="col-md-2">
                <div class="form-group">
                    <div class="input-group">
                        <div class="input-group-addon">用户id</div>
                        <input class="form-control" type="text" id="customerId">
                    </div>
                </div>
            </div>
            <div class="col-md-2">
                <div class="form-group">
                    <div class="input-group">
                        <div class="input-group-addon">用户昵称</div>
                        <input class="form-control" type="text" id="nickName">
                    </div>
                </div>
            </div>
            <div class="col-md-2">
                <div class="form-group">
                    <div class="input-group">
                        <div class="input-group-addon">手机号</div>
                        <input class="form-control" type="text" id="phone">
                    </div>
                </div>
            </div>
            <div class="col-md-2">
                <div class="form-group">
                    <div class="input-group">
                        <div class="input-group-addon">审核状态</div>
                        <select class="form-control" id="auditStatus">
                            <option value="">--请选择--</option>
                            <option value="0">待审核</option>
                            <option value="1">已通过</option>
                            <option value="2">已拒绝</option>
                        </select>
                    </div>
                </div>
            </div>
            <div class="col-md-2">
                <button type="button" class="btn btn-primary btn-small btn-block" id="query">
                    <i class="glyphicon glyphicon-search"></i> 查询
                </button>
            </div>
            <%--<div class="col-md-2">
                <button type="button" class="btn btn-info btn-small btn-block"
                        onclick="addAndUpdateRow(0)">
                    <i class="glyphicon glyphicon-plus"></i> 增加
                </button>
            </div>--%>
            <table id="example" class="table"></table>
        </div>

        <script>
            //表格的初始化
            $(document).ready(function () {
                var table = $('#example').initTable({
                    sAjaxSource: "voiceIdentifyCard/initTable.htm",
                    fnServerParams: function (aoData) {  //查询条件
                        aoData.push({"name": "customerId", "value": $("#customerId").val().trim()});
                        aoData.push({"name": "nickName", "value": $("#nickName").val().trim()});
                        aoData.push({"name": "auditStatus", "value": $("#auditStatus").val().trim()});
                        aoData.push({"name": "phone", "value": $("#phone").val().trim()});
//                        aoData.push({"name": "startTime", "value": $("#sTime").val()});
//                        aoData.push({"name": "endTime", "value": $("#eTime").val()});
                    },
                    aoColumnDefs: [{}],
                    aoColumns: [
                        {
                            "data": "index",
                            "sTitle": "序号",
                            'sClass': "text-center",
                            "mRender": function (data, type, full, meta) {
                                return meta.row+1 + meta.settings._iDisplayStart;
                            }
                        },
                        {
                            "data": "id",
                            "sTitle": "ID",
                            'sClass': "text-center",
                            "bVisible": false //此列不显示
                        },
                        {
                            "data": "customerId",
                            "sTitle": "用户id",
                            'sClass': "text-center",
                        },
                        {
                            "data": "nickName",
                            "sTitle": "用户昵称",
                            'sClass': "text-center",
                        },
                        {
                            "data": "phone",
                            "sTitle": "手机号",
                            'sClass': "text-center",
                        },
                        {
                            "data": "appearance",
                            "sTitle": "当前使用照片",
                            'sClass': "text-center",
                            "mRender": function (data, type, full) {
                                return "<ul id='viewer-1-"+full.id+"\' style='list-style-type: none;padding: 0px;margin: 0px;'>" +
                                    "<li><img src=\"" + data + "\" style='width: 50px;' onclick='showImg(this)'></li></ul>";
                            },
                        },
                        {
                            "data": "auditAppearance",
                            "sTitle": "待审核照片",
                            'sClass': "text-center",
                            "mRender": function (data, type, full) {
                                return "<ul id='viewer-2-"+full.id+"\' style='list-style-type: none;padding: 0px;margin: 0px;'>" +
                                    "<li><img src=\"" + data + "\" style='width: 50px;' onclick='showImg(this)'></li></ul>";
                            },
                        },
                        {
                            "data": "auditStatus",
                            "sTitle": "审核状态",
                            'sClass': "text-center",
                            "mRender": function (data, type, full) {
                                if (data == 0) {
                                    return "待审核";
                                } else if (data == 1) {
                                    return '已通过';
                                } else if (data == 2) {
                                    return '已拒绝';
                                }
                            }
                        },
                        /*{
                            "data": "type",
                            "sTitle":"照片类型",
                            'sClass':"text-center",
                            "mRender" : function(data, type,full) {
                                if (data == 0) {
                                    return "形象照";
                                } else if(data == 1){
                                    return '头像照';
                                }else if(data == 2){
                                    return '声鉴卡';
                                }
                            }
                        },*/


                        /*{
                            "data": "createMan",
                            "sTitle":"创建人",
                            'sClass':"text-center",
                        },*/
                        {
                            "data": "createTime",
                            "sTitle": "创建时间",
                            'sClass': "text-center",
                            "mRender": function (data, type, full) {
                                if (data != null) {
                                    return Format(new Date(data), "yyyy-MM-dd HH:mm:ss")
                                } else {
                                    return '--';
                                }
                            }
                        },
                        /*{
                            "data": "modifyMan",
                            "sTitle":"修改人",
                            'sClass':"text-center",
                        },*/
                        {
                            "data": "modifyTime",
                            "sTitle": "修改时间",
                            'sClass': "text-center",
                            "mRender": function (data, type, full) {
                                if (data != null) {
                                    return Format(new Date(data), "yyyy-MM-dd HH:mm:ss")
                                } else {
                                    return '--';
                                }
                            }
                        },

                        {
                            //"data" : "id",
                            "sTitle": "操作",
                            'sClass': "text-center",
                            "mRender": function (data, type, row) {
                                var edit = "", del = "";
                                if (row.auditStatus == 0) {
                                    edit = "<a href='#' onclick='approveRow(\"" + row.id + "\")' data-toggle='modal' class='padding-right-small label label-success'><i class='glyphicon glyphicon-edit'></i>通过</a>";
                                    del = "<a href='#' onclick='rejectRow(\"" + row.id + "\")' data-toggle='modal' class='label label label-danger'><i class='glyphicon glyphicon-trash'></i> 拒绝 </a>";
                                }
                                return edit + "&nbsp;" + del;
                            }
                        }
                    ]

                });

                $('#query').click(function () {
                    $('#example').dataTable().fnDraw();
                });

            });

            /* $('#example tbody').on('click','tr:nth-child(2) td:nth-child(2)', function (e) {
                 var name = $(this).text();
                 alert("给第2行第2列的JQuery datatables添加点击事件"+name);
             } );*/


            //审核通过
            function approveRow(id) {
                $('#approveRow').deleteRow("voiceIdentifyCard/approve?id=" + id);
            }

            //拒绝
            function rejectRow(id) {
                $('#rejectRow').deleteRow("voiceIdentifyCard/reject?id=" + id);
            }

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
            }

            function showImg(obj) {
                var id = $(obj).parent().parent().attr("id");
                var viewer = new Viewer(document.getElementById(id), {
                    url: 'data-original'
                });
                viewer.show();
            }
        </script>
        <!---dialog选项-->
        <div>
            <jsp:include page="/WEB-INF/views/common/approve_dialog.jsp"/>
            <jsp:include page="/WEB-INF/views/common/reject_dialog.jsp"/>
            <!-- 模态框（Modal） -->
            <%--<div class="modal fade" id="approveRow" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true"></div>--%>
        </div>

    </div>
</div>
<script type="text/javascript" src="resources/plugins/viewer/viewer.min.js"></script>