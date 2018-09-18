<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<shiro:hasPermission name="platformStatistics:select">
    <div class="content1">
        <div class="header">
            <h1 class="page-title">平台管理</h1>
            <ul class="breadcrumb">
                <li>平台列表</li>
                <li class="active">平台列表</li>
                <spam>&nbsp;&nbsp;<font color="red">(*时间不填或只填一个默认查近15天数据)</font></spam>
            </ul>
        </div>
        <div class="main-content">
            <div class="row">
                <div class="col-md-2">
                    <div class="form-group">
                        <div class="input-group">
                            <div class="input-group-addon">贷款平台</div>
                            <input class="form-control" type="text" id="platformName">
                        </div>
                    </div>
                </div>
                <div class="col-md-2">
                    <div class="form-group">
                        <div class="input-group">
                            <div class="input-group-addon">合作模式</div>
                            <input class="form-control" type="text" id="cooperation">
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
                    sAjaxSource: "platformStatistics/initTable.htm",
                    aoColumns: [
                        {
                            "data": "platformName",
                            "sTitle": "贷款平台",
                            'sClass': "text-center"
                        },
                        {
                            "data": "createTime",
                            "sTitle": "时间",
                            'sClass': "text-center"
                        },
                        {
                            "data": "diversionNum",
                            "sTitle": "导流数量",
                            'sClass': "text-center"
                        },
                        {
                            "data": "successNum",
                            "sTitle": "反馈成功数量",
                            'sClass': "text-center"
                        },
                        {
                            "data": "failNum",
                            "sTitle": "反馈失败数量",
                            'sClass': "text-center"
                        },
                        {
                            "data": "cooperation",
                            "sTitle": "合作方式",
                            'sClass': "text-center"
                        }
                    ],
                    fnServerParams: function (aoData) {  //查询条件
                        aoData.push({"name": "cooperation", "value": $("#cooperation").val()});
                        aoData.push({"name": "platformName", "value": $("#platformName").val()});
                        aoData.push({"name": "sDate", "value": $("#sTime").val()});
                        aoData.push({"name": "eDate", "value": $("#eTime").val()});
                    },
                });

                $('#query').click(function () {
                    $('#example').dataTable().fnDraw();
                });

            });

            function exportData(){
                var href="export/platform.htm"
                    +"?platformName="+$("#platformName").val()
                    +"&cooperation="+$("#cooperation").val()
                    +"&sDate="+$("#sTime").val()
                    +"&eDate="+$("#eTime").val();
                $('#data_export').attr("href",href);
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
            };
        </script>
        <!---dialog选项-->
        <div>
            <jsp:include page="/WEB-INF/views/common/delete_dialog.jsp"/>
            <jsp:include page="/WEB-INF/views/common/addupdate_dialog.jsp"/>

        </div>
    </div>
</shiro:hasPermission>