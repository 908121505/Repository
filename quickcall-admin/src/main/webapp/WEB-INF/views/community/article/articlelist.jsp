<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<shiro:hasPermission name="article:select">
    <div class="content1">
        <div class="header">
            <h1 class="page-title">文章列表</h1>
            <ul class="breadcrumb">
                <li>文章列表</li>
                <li class="active">文章列表</li>
            </ul>
        </div>
        <div class="main-content">
            <div class="row">
                <div class="col-md-2">
                    <div class="form-group">
                        <div class="input-group">
                            <div class="input-group-addon">文章标题</div>
                            <input class="form-control" type="text" id="articleTitle">
                        </div>
                    </div>
                </div>
                <div class="col-md-2">
                    <div class="form-group">
                        <div class="input-group">
                            <div class="input-group-addon">是否推荐</div>
                            <select class="form-control" id="isPush">
                                <option value="">- - -请选择- - -</option>
                                <option value="1">是</option>
                                <option value="2">否</option>
                            </select>
                        </div>
                    </div>
                </div>
                <div class="col-md-2">
                    <div class="form-group">
                        <div class="input-group">
                            <div class="input-group-addon">状态</div>
                            <select class="form-control" id="state">
                                <option value="">- - -请选择- - -</option>
                                <option value="1">有效</option>
                                <option value="2">无效</option>
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
                    <shiro:hasPermission name="article:add">
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
                    sAjaxSource: "article/initTable.htm",
                    aoColumns: [
                        {
                            "data": "articleClass.articleClassTitle",
                            "sTitle": "主题类型",
                            'sClass': "text-center"
                        },
                        {
                            "data": "articleTitle",
                            "sTitle": "文章标题",
                            'sClass': "text-center"
                        },
                        {
                            "data": "state",
                            "sTitle": "状态",
                            'sClass': "text-center",
                            "mRender": function (data, type, full) {
                                if (data == 1) {
                                    return "<font >有效</font>";
                                } else {
                                    return "<font >无效</font>";
                                }
                            }
                        },
                        {
                            "data": "isPush",
                            "sTitle": "是否推荐",
                            'sClass': "text-center",
                            "mRender": function (data, type, full) {
                                if (data == 1) {
                                    return "<font color='red'>是</font>";
                                } else if (data == 2) {
                                    return "<font color='blue'>否</font>";
                                }
                            }
                        },
                        {
                            "data": "readNum",
                            "sTitle": "阅读数",
                            'sClass': "text-center"
                        },
                        {
                            "data": "praiseNum",
                            "sTitle": "点赞数量",
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
                            "data": "createMan",
                            "sTitle": "创建人",
                            'sClass': "text-center"
                        },
                        {
                            "data": "modifyTime",
                            "sTitle": "更新时间",
                            'sClass': "text-center",
                            "mRender": function (data, type, full) {
                                return Format(new Date(data), "yyyy-MM-dd HH:mm:ss")
                            }
                        },
                        {
                            "data": "modifyMan",
                            "sTitle": "更新人",
                            'sClass': "text-center"
                        } ,
                        {
                            "data": "articleContent",
                            "sTitle": "文章内容",
                            'sClass': "text-center"
                        }
                        <shiro:hasPermission name="article:update or article:delete">
                       , {
                            "data": "articleId",
                            "sTitle": "操作",
                            'sClass': "text-center"
                        }
                        </shiro:hasPermission>
                    ],
                    fnServerParams: function (aoData) {  //查询条件
                        aoData.push({"name": "state", "value": $("#state").val()});
                        aoData.push({"name": "isPush", "value": $("#isPush").val()});
                        aoData.push({"name": "articleTitle", "value": $("#articleTitle").val()});
                    },
                    aoColumnDefs: [
                        {
                                "aTargets": 10, "mRender": function (data) {
                                var detail = "";
                                detail = "<a onclick='detailRow(this)' data-toggle='modal' data-content='" + data
                                        +"' class='label label label-success'><i class='glyphicon glyphicon-edit'></i>查看</a>";
                                return detail;
                            }
                        },
                        <shiro:hasPermission name="article:update or article:delete">
                        {
                                "aTargets": 11, "mRender": function (data, type, row) {
                                var edit = "", del = "", comment = "", detail = "";
                                <shiro:hasPermission name="article:update">
                                edit = "<a href='#' onclick='addAndUpdateRow(\"" + data + "\")' data-toggle='modal' class='padding-right-small label label-success'><i class='glyphicon glyphicon-edit'></i> 编辑</a>";
                                </shiro:hasPermission>
                                <shiro:hasPermission name="article:delete">
                                del = "<a href='#' onclick='deleteRow(\"" + data + "\")' data-toggle='modal' class='label label label-danger'><i class='glyphicon glyphicon-trash'></i> 删除 </a>";
                                </shiro:hasPermission>
                                comment = "<a href='#' onclick='commentRow(\"" + data +
                                        "\")' data-toggle='modal' class='label label label-success'><i class='glyphicon glyphicon-trash'></i> 查看评论 </a>";
                                return edit + "&nbsp;" + del + "&nbsp;" + comment;
                            }
                        }
                        </shiro:hasPermission>
                    ]
                });

                $('#query').click(function () {
                    $('#example').dataTable().fnDraw();
                });

                $('#a_cancel').click(function () {
                    $("#a_content").hide();
                });

            });

            //查看内容
            function detailRow(obj) {
                $("#a_value").html('');
                var content = $(obj).attr("data-content");
                $("#a_value").html(content);
                $("#a_content").show();
            }
            //查看评论
            function commentRow(id) {
                $('#insertAndUpdate').addAndUpdateRow("article/detail.htm?articleId=" + id);
            }
            //删除受影响的行数
            function deleteRow(id) {
                $('#myModal').deleteRow('article/del.htm?id=' + id);
            }
            //增加或者修改受影响的行数
            function addAndUpdateRow(id) {
                $('#insertAndUpdate').addAndUpdateRow("article/addAndUpdateHome.htm?id=" + id);
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
            ;
        </script>
        <!---dialog选项-->
        <div>
            <jsp:include page="/WEB-INF/views/common/delete_dialog.jsp"/>
            <jsp:include page="/WEB-INF/views/common/addupdate_dialog.jsp"/>

            <div class="modal fade" id="insertAndUpdate" tabindex="-1"
                 role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
            </div>

            <div class="modal" id="a_content" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h3 id="myModalLabel">文章内容</h3>
                        </div>
                        <div class="modal-body">
                            <p id="a_value"></p>
                        </div>
                        <div class="modal-footer">
                            <button class="btn btn-default" id="a_cancel" data-dismiss="modal">关闭</button>
                        </div>
                    </div>
                </div>
            </div>

        </div>
    </div>
</shiro:hasPermission>