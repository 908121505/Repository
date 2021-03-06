<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>

<shiro:hasPermission name="bigvPhone:select">
    <div class="content1">
        <div class="header">
            <h1 class="page-title">大V白名单管理</h1>
            <ul class="breadcrumb">
                <li>用户管理</li>
                <li class="active">大V白名单管理</li>
            </ul>
        </div>
        <div class="main-content">
            <div class="row">
                <div class="col-md-2">
                    <div class="form-group">
                        <div class="input-group">
                            <div class="input-group-addon">手机号</div>
                            <input class="form-control" type="text" id="phone" maxlength="11">
                        </div>
                    </div>
                </div>
                <div class="col-md-2">
                    <div class="form-group">
                        <div class="input-group">
                            <div class="input-group-addon">是否加入</div>
                            <select class="form-control" id="isJoin">
                                <option value="">--请选择--</option>
                                <option value="1">是</option>
                                <option value="0">否</option>
                            </select>
                        </div>
                    </div>
                </div>
                <div class="col-md-2">
                    <div class="form-group">
                        <div class="input-group">
                            <div class="input-group-addon">是否大V</div>
                            <select class="form-control" id="isBigv">
                                <option value="">--请选择--</option>
                                <option value="1">是</option>
                                <option value="0">否</option>
                            </select>
                        </div>
                    </div>
                </div>
                <div class="col-md-2">
                    <button type="button" class="btn btn-primary btn-small btn-block" id="query">
                        <i class="glyphicon glyphicon-search"></i> 查询
                    </button>
                </div>
                <shiro:hasPermission name="bigvPhone:add">
                    <div class="col-md-2">
                        <button type="button" class="btn btn-info btn-small btn-block"
                                onclick="addAndUpdateRow(0)">
                            <i class="glyphicon glyphicon-plus"></i> 增加
                        </button>
                    </div>
                </shiro:hasPermission>
            </div>
            <table id="example" class="table"></table>
        </div>
        <script>
            //表格的初始化
            $(document).ready(function () {
                var table = $('#example').initTable({
                    sAjaxSource: "bigvPhone/initTable.htm",
                    aoColumns: [
                        {"data": "phone", "sTitle": "手机号", 'sClass': "text-center", "sortable": false},
                        {"data": "isJoin", "sTitle": "是否加入", 'sClass': "text-center", "sortable": false,
                            "mRender": function (data, type, full) {
                                return data > 0 ? "是" : "否";
                            }
                        },
                        {"data": "isBigv", "sTitle": "是否大V", 'sClass': "text-center", "sortable": false,
                            "mRender": function (data, type, full) {
                                return data > 0 ? "是" : "否";
                            }
                        },
                        {"data": "remark", "sTitle": "备注", 'sClass': "text-center", "sortable": false},
                        {"data": "createTime", "sTitle": "创建时间", 'sClass': "text-center", "sortable": false,
                            "mRender": function (data, type, full) {
                                return Format(data,"yyyy-MM-dd HH:mm:ss");
                            }
                        },
                        {"data": "createMan", "sTitle": "创建人", 'sClass': "text-center", "sortable": false},
                        {"data": "modifyTime", "sTitle": "修改时间", 'sClass': "text-center", "sortable": false,
                            "mRender": function (data, type, full) {
                                return Format(data,"yyyy-MM-dd HH:mm:ss");
                            }
                        },
                        {"data": "modifyMan", "sTitle": "修改人", 'sClass': "text-center", "sortable": false}
                        <shiro:hasPermission name="banner:update or banner:delete">
                        , {"data": "id", "sTitle": "操作", 'sClass': "text-center", "sortable": false}
                        </shiro:hasPermission>
                    ],
                    fnServerParams: function (aoData) {  //查询条件
                        aoData.push({"name": "phone", "value": $("#phone").val()});
                        aoData.push({"name": "isJoin", "value": $("#isJoin").val()});
                    },
                    aoColumnDefs: [
                        {
                            "aTargets":8, "mRender": function (data, type, row) {
                                var edit = "",bigv="", del = "";
                                <shiro:hasPermission name="bigvPhone:update">
                                edit = "<a href='#' onclick='addAndUpdateRow(\"" + data + "\")' data-toggle='modal' class='padding-right-small label label-success'><i class='glyphicon glyphicon-edit'></i> 编辑</a>";
                                if(row.isJoin > 0 && row.isBigv == 0){
                                    bigv = "<a href='#' onclick='updateToBigv(\"" + row.phone + "\")' data-toggle='modal' class='padding-right-small label label-success'><i class='glyphicon glyphicon-edit'></i> 升级大V</a>";
                                }
                                </shiro:hasPermission>

                                <shiro:hasPermission name="bigvPhone:delete">
                                del = "<a href='#' onclick='deleteRow(\"" + data + "\")' data-toggle='modal' class='label label label-danger'><i class='glyphicon glyphicon-trash'></i> 删除 </a>";
                                </shiro:hasPermission>
                                return edit + "&nbsp;" + bigv +  "&nbsp;" + del;
                            }
                        }
                    ]
                });

                $('#query').click(function () {
                    $('#example').dataTable().fnDraw();
                });

            });

            //删除受影响的行数
            function deleteRow(id) {
                $('#myModal').deleteRow('bigvPhone/del.htm?id=' + id);
            }

            //增加或者修改受影响的行数
            function addAndUpdateRow(id) {
                $('#bigvPhoneInsertAndUpdate').addAndUpdateRow("bigvPhone/addAndUpdateHome.htm?id=" + id);
            }

            // 升级为大V
            function updateToBigv(phone) {
                if(window.confirm("确定升级该手机号的用户为大V？")){
                    $.ajax({
                        url: "bigvPhone/updateToBigv.htm",
                        async: false,
                        dataType: "JSON",
                        type: 'post',
                        data: {
                            phone: phone,
                        },
                        success: function (data) {
                            if (data.code == "1") {
                                alert(data.msg);
                            } else {
                                alert("成功升级为大V！");
                                $('#example').dataTable().fnDraw();
                            }
                        },
                        error: function (data) {
                            alert("请求出错，请稍后再试！");
                        }
                    });
                }
            }

            function Format(now, mask){
                if(!now || now == null){
                    return "--";
                }
                var d = new Date(now);
                var zeroize = function (value, length)
                {
                    if (!length) length = 2;
                    value = String(value);
                    for (var i = 0, zeros = ''; i < (length - value.length); i++)
                    {
                        zeros += '0';
                    }
                    return zeros + value;
                };

                return mask.replace(/"[^"]*"|'[^']*'|\b(?:d{1,4}|m{1,4}|yy(?:yy)?|([hHMstT])\1?|[lLZ])\b/g, function ($0)
                {
                    switch ($0)
                    {
                        case 'd': return d.getDate();
                        case 'dd': return zeroize(d.getDate());
                        case 'ddd': return ['Sun', 'Mon', 'Tue', 'Wed', 'Thr', 'Fri', 'Sat'][d.getDay()];
                        case 'dddd': return ['Sunday', 'Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday'][d.getDay()];
                        case 'M': return d.getMonth() + 1;
                        case 'MM': return zeroize(d.getMonth() + 1);
                        case 'MMM': return ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec'][d.getMonth()];
                        case 'MMMM': return ['January', 'February', 'March', 'April', 'May', 'June', 'July', 'August', 'September', 'October', 'November', 'December'][d.getMonth()];
                        case 'yy': return String(d.getFullYear()).substr(2);
                        case 'yyyy': return d.getFullYear();
                        case 'h': return d.getHours() % 12 || 12;
                        case 'hh': return zeroize(d.getHours() % 12 || 12);
                        case 'H': return d.getHours();
                        case 'HH': return zeroize(d.getHours());
                        case 'm': return d.getMinutes();
                        case 'mm': return zeroize(d.getMinutes());
                        case 's': return d.getSeconds();
                        case 'ss': return zeroize(d.getSeconds());
                        case 'l': return zeroize(d.getMilliseconds(), 3);
                        case 'L': var m = d.getMilliseconds();
                            if (m > 99) m = Math.round(m / 10);
                            return zeroize(m);
                        case 'tt': return d.getHours() < 12 ? 'am' : 'pm';
                        case 'TT': return d.getHours() < 12 ? 'AM' : 'PM';
                        case 'Z': return d.toUTCString().match(/[A-Z]+$/);
                        // Return quoted strings with the surrounding quotes removed
                        default: return $0.substr(1, $0.length - 2);
                    }
                });
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