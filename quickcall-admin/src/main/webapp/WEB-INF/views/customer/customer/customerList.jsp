<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<div class="content1">
    <div class="header">
        <h1 class="page-title">用户管理后台</h1>
        <ul class="breadcrumb">
            <li>用户管理</li>
            <li class="active">用户管理后台</li>
        </ul>
    </div>
    <div class="main-content">
        <div class="row">
            <div class="col-md-2">
                <div class="form-group">
                    <div class="input-group">
                        <div class="input-group-addon">用户昵称或UID</div>
                        <input class="form-control" type="text" id="nameQuery">
                    </div>
                </div>
            </div>
            <div class="col-md-2">
                <div class="form-group">
                    <div class="input-group">
                        <div class="input-group-addon">手机号</div>
                        <input class="form-control" type="text" id="phoneQuery">
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
                <div class="form-group">
                    <div class="input-group">
                        <div class="input-group-addon">用户类型</div>
                        <select class="form-control" id="customTypeQuery">
                            <option value="">--请选择--</option>
                            <option value="0">普通用户</option>
                            <option value="2">声优用户</option>
                        </select>
                    </div>
                </div>
            </div>
            <div class="col-md-2">
                <div class="form-group">
                    <div class="input-group">
                        <div class="input-group-addon">用户状态</div>
                        <select class="form-control" id="customerStatusQuery" onchange="customerStatusOnChange()">
                            <option value="">--请选择--</option>
                            <option value="1">正常</option>
                            <option value="2">封禁</option>
                        </select>
                    </div>
                </div>
            </div>
           <%-- 4=已封禁-无法接单,6=已封禁-无法接指定技能,8=已封禁-账户登录权限,10=已封禁-设备登录权限--%>
            <div class="col-md-2">
                <div class="form-group">
                    <div class="input-group">
                        <div class="input-group-addon">封禁类型</div>
                        <select class="form-control" id="closureStatusQuery" disabled="disabled">
                            <option value="">--请选择--</option>
                            <option value="4">已封禁-无法接单</option>
                            <option value="6">已封禁-无法接指定技能</option>
                            <option value="8">已封禁-账户登录权限</option>
                            <option value="10">已封禁-设备登录权限</option>
                        </select>
                    </div>
                </div>
            </div>


        </div>
        <!-- 			<div class="row">
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
                    </div> -->
        <table id="example" class="table"></table>
    </div>
    <script>
        var skillStatus = 0;

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
                sAjaxSource: "customer/initTable.htm",
                aoColumns: [
                    {
                        "data": "nickName",
                        "sTitle": "用户昵称",
                        'sClass': "text-center"
                    },
                    {
                        "data": "appId",
                        "sTitle": "用户UID",
                        'sClass': "text-center"
                    },
                    {
                        "data": "phone",
                        "sTitle": "手机号",
                        'sClass': "text-center"
                    },
                    {
                        "data": "frontPortraitUrl",
                        "sTitle": "实名认证",
                        'sClass': "text-center",
                        "mRender": function (data, type, full) {
                            if (data == '' || data == null) {
                                return "--";
                            } else {
                                return "<img src='" + data + "' height='50px;'/>";
                            }

                        }
                    },
                    {
                        "data": "formatString",
                        "sTitle": "声优认证",
                        'sClass': "text-center"
                    },

                 /*   {
                        "data": "unlockIcon",
                        "sTitle": "声优认证",
                        'sClass': "text-center",
                        "mRender": function (data, type, full) {
                            if (data == '' || data == null) {
                                return "--";
                            } else {
                                return "<img src='" + data + "' height='50px;'/>";
                            }

                        }
                    },*/
                    <%-- 4=已封禁-无法接单,6=已封禁-无法接指定技能,8=已封禁-账户登录权限,10=已封禁-设备登录权限--%>
                    {
                        "data": "custStatus",
                        "sTitle": "当前状态",
                        'sClass': "text-center",
                        "mRender": function (data, type, full) {
                            if(data == 1){
                                return "<font color='red'>正常</font>";
                            } else if(data == 4){
                                return "<font color='red'>已封禁-无法接单</font>";
                            } else if(data == 6){
                                return "<font color='red'>已封禁-声优技能</font>";
                            } else if(data == 8){
                                return "<font color='red'>已封禁-账户登录权限</font>";
                            } else if(data == 10){
                                return "<font color='red'>已封禁-设备登录权限</font>";
                            } else if(data == 12){
                                return "<font color='red'>已封禁-声优资格</font>";
                            }else{
                                return "<font color='red'>永久封禁</font>";
                            }

                        }
                    },


                    /*   {
                          "data": "titleUrl",
                          "sTitle":"标题背景图片",
                          'sClass':"text-center",
                          "mRender": function(data, type, full) {
                              if(data==''||data==null){
                                       return	"--";
                              }else{
                               return "<img src='" + data + "' height='50px;'/>";
                              }

                         }
                       },*/
                    /*    {
                            "data": "minPrice",
                            "sTitle":"最低价格",
                            'sClass':"text-center",
                        },
                        {
                            "data": "maxPrice",
                            "sTitle":"最高价格",
                            'sClass':"text-center",
                        },
                        {
                            "data": "priceStep",
                            "sTitle":"价格步长",
                            'sClass':"text-center",
                        },*/

                    {
                        "data": "blockStartTime",
                        "sTitle": "封禁起初时间",
                        'sClass': "text-center",
                        "mRender": function (
                            data, type,
                            full) {
                            //console.log(data);
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
                        "data": "blockEndTime",
                        "sTitle": "封禁结束时间",
                        'sClass': "text-center",
                        "mRender": function (
                            data, type,
                            full) {
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
                        "data": "customerId",
                        "sTitle": "操作",
                        'sClass': "text-center"
                    }
                ],
                fnServerParams: function (aoData) {  //查询条件
                    aoData.push({"name": "nickName", "value": $("#nameQuery").val().replace(new RegExp(" ", "g"), "")});
                    aoData.push({"name": "vStatus", "value": $("#customTypeQuery").val()});
                    aoData.push({"name": "appId", "value": $("#nameQuery").val().replace(new RegExp(" ", "g"), "")});
                    aoData.push({"name": "phone", "value": $("#phoneQuery").val().replace(new RegExp(" ", "g"), "")});
                    var status = $('#customerStatusQuery').val();
                    if(status == 1){
                        aoData.push({"name": "custStatus", "value": 1});
                    }else if(status == 2){
                        aoData.push({"name": "custStatus", "value": $("#closureStatusQuery").val()});
                    }
                },
                aoColumnDefs: [{
                    "aTargets": 8,
                    "mRender": function (data, type, row) {

                        var detail = "";
                        detail = "<a href='#' onclick='addAndUpdateRow(\"" + data + "\")' data-toggle='modal' class='padding-right-small label label-success'><i class='glyphicon glyphicon-edit'></i>管理</a>";
                        return detail;
                    }
                }]

            });

            $('#query').click(function () {
                if(check_fun()){
                    $('#example').dataTable().fnDraw();
                }
            });

        });

        //增加或者修改受影响的行数
        function addAndUpdateRow(id) {
            $('#insertAndUpdate').addAndUpdateRow("customer/addAndUpdateHome.htm?id=" + id);
        }


        function customerStatusOnChange(){
            var status = $('#customerStatusQuery').val();
            if(status == 2){
                //$('#closureStatusQuery').removeAttr("disabled");
                $('#closureStatusQuery').attr("disabled",false);
            }else {
                /*if($('#closureStatusQuery').attr("disabled")==true){
                    $('#closureStatusQuery').removeAttr("disabled");
                }*/

                // $('#closureStatusQuery').attr("disabled",false);
                //$("#closureStatusQuery option:first").attr('selected', true);
                // $('#closureStatusQuery').attr("disabled",true);
                $('#closureStatusQuery').val('').prop('disabled', true);

            }

        }

        //校验方法
        function check_fun() {
            //状态选择2为封禁，要再选择封禁的类型
            var status = $('#customerStatusQuery').val();
            /*if(status == null  || status.trim() == ''){
                $("#tip").text("请选择用户状态");
                return  false;
            }*/
            if(status == 2){//封禁
                var closureStatus = $("#closureStatusQuery").val()
                if(closureStatus == null || closureStatus == ""){
                    alert("请选择封禁类型");
                    return  false;
                }
            }

            return true;
        }

    </script>
    <!---dialog选项-->
    <div>
        <jsp:include page="/WEB-INF/views/common/delete_dialog.jsp"/>
        <jsp:include page="/WEB-INF/views/common/addupdate_dialog.jsp"/>
        <jsp:include page="/WEB-INF/views/common/updateStatus.jsp"/>

        <div class="modal fade" id="insertAndUpdate" tabindex="-1"
             role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
        </div>
    </div>
</div>
