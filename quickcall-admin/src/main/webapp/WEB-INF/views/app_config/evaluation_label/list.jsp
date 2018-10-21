<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<shiro:hasPermission name="evaluationLabel:select">
    <div class="content1">
        <div class="header">
            <h1 class="page-title">评价标签管理</h1>
            <ul class="breadcrumb">
                <li>APP配置</li>
                <li class="active">评价标签管理</li>
            </ul>
        </div>
        <div class="main-content">
            <div class="row">
                <div class="col-md-2">
                    <div class="form-group">
                        <div class="input-group">
                            <div class="input-group-addon">标签名称</div>
                            <input class="form-control" type="text" id="labelName">
                        </div>
                    </div>
                </div>
                <div class="col-md-2">
                    <div class="form-group">
                        <div class="input-group">
                            <div class="input-group-addon">技能类型</div>
                            <select class="form-control" id="skillItemId">
                                <option value="">--请选择--</option>
                                <c:forEach items="${skillList}" var="item">
                                    <option value="${item.id}">${item.skillItemName}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                </div>
                <div class="col-md-2">
                    <div class="form-group">
                        <div class="input-group">
                            <div class="input-group-addon">客户性别</div>
                            <select class="form-control" id="customerSex">
                                <option value="">--请选择--</option>
                                <option value="1">男</option>
                                <option value="0">女</option>
                            </select>
                        </div>
                    </div>
                </div>
                <div class="col-md-2">
                    <button type="button" class="btn btn-primary btn-small btn-block" id="query">
                        <i class="glyphicon glyphicon-search"></i> 查询
                    </button>
                </div>
                <shiro:hasPermission name="evaluationLabel:add">
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
                    sAjaxSource: "evaluationLabel/initTable.htm",
                    aoColumns: [
                        {"data": "skillItemName", "sTitle": "技能类型", 'sClass': "text-center", "sortable": false},
                        {"data": "customerSex", "sTitle": "性别", 'sClass': "text-center", "sortable": false,
                            "mRender": function (data, type, full) {
                                return getCustomerSex(data);
                            }
                        },
                        {"data": "labelName", "sTitle": "标签名称", 'sClass': "text-center", "sortable": false},
                        {"data": "borderColor", "sTitle": "边框颜色", 'sClass': "text-center", "sortable": false},
                        {"data": "createTime", "sTitle": "创建时间", 'sClass': "text-center", "sortable": false,
                            "mRender": function (data, type, full) {
                                return Format(data,"yyyy-MM-dd HH:mm:ss")
                            }
                        },
                        {"data": "createMan", "sTitle": "创建人", 'sClass': "text-center", "sortable": false},
                        {"data": "modifyTime", "sTitle": "修改时间", 'sClass': "text-center", "sortable": false,
                            "mRender": function (data, type, full) {
                                return Format(data,"yyyy-MM-dd HH:mm:ss")
                            }
                        },
                        {"data": "modifyMan", "sTitle": "修改人", 'sClass': "text-center", "sortable": false}
                        <shiro:hasPermission name="evaluationLabel:update">
                        , {"data": "labelId", "sTitle": "操作", 'sClass': "text-center", "sortable": false}
                        </shiro:hasPermission>
                    ],
                    fnServerParams: function (aoData) {  //查询条件
                        aoData.push({"name": "labelName", "value": $("#labelName").val()});
                        aoData.push({"name": "skillItemId", "value": $("#skillItemId").val()});
                        aoData.push({"name": "customerSex", "value": $("#customerSex").val()});
                    },
                    aoColumnDefs: [
                        <shiro:hasPermission name="evaluationLabel:update">
                        {"aTargets": 8, "mRender": function (data, type, row) {
                                var edit = "";
                                <shiro:hasPermission name="evaluationLabel:update">
                                edit = "<a href='#' onclick='addAndUpdateRow(\"" + data + "\")' data-toggle='modal' class='padding-right-small label label-success'><i class='glyphicon glyphicon-edit'></i> 编辑</a>";
                                </shiro:hasPermission>
                                return edit;
                            }
                        }
                        </shiro:hasPermission>
                    ]
                });

                $('#query').click(function () {
                    $('#example').dataTable().fnDraw();
                });

            });

            //增加或者修改受影响的行数
            function addAndUpdateRow(labelId) {
                $('#evaluationLabelInsertAndUpdate').addAndUpdateRow("evaluationLabel/addAndUpdateHome.htm?labelId=" + labelId);
            }

            /** 获取性别 **/
            function getCustomerSex(data) {
                if (data == "1") {
                    return "男";
                } else if (data == "0") {
                    return "女";
                }
                return data;
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
            <div class="modal fade" id="evaluationLabelInsertAndUpdate" tabindex="-1"
                 role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static">
            </div>
        </div>
    </div>
</shiro:hasPermission>