<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<div class="content1">
    <div class="header">
        <h1 class="page-title">订单管理</h1>
        <ul class="breadcrumb">
            <li>订单管理</li>
            <li class="active">折扣管理</li>
        </ul>
    </div>
    <div class="main-content">
        <div class="row">
            <div class="col-md-2">
                <div class="form-group">
                    <div class="input-group">
                        <select class="form-control" id="skillTypeQuery">
                            <option value="">--请选择--</option>
                        </select>
                    </div>
                </div>
            </div>

            <div class="col-md-2">
                <div class="form-group">
                    <div class="input-group">
                        <select class="form-control" id="skillStatusQuery">
                            <option value="">--请选择--</option>
                            <option value="0">关闭</option>
                            <option value="1">开启</option>
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
                        onclick="addAndUpdateRow(0)">
                    <i class="glyphicon glyphicon-plus"></i> 增加
                </button>
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

        $(function() {
            $.ajax({
                url: "discount/getAllSkillName",    //后台webservice里的方法名称
                type: "post",
                dataType: "json",
                contentType: "application/json",
                traditional: true,
                success: function (data) {
                    for(i=0;i<data.length;i++){
                        $('#skillTypeQuery').append("<option value="+data[i].id+">"+data[i].name+"</option>")
                    }

                },
                error: function (msg) {
                    alert("出错了！");
                }
            });

        });


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
                sAjaxSource: "discount/initTable.htm",
                aoColumns: [
                    {
                        "data": "skillItemId",
                        "sTitle": "品类ID",
                        'sClass': "text-center"
                    },

                    {
                        "data": "discount",
                        "sTitle": "折扣",
                        'sClass': "text-center"
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
                        "data": "skillItemName",
                        "sTitle": "品类型",
                        'sClass': "text-center"
                    },


                    {
                        "data": "skillExtStatus",
                        "sTitle": "状态",
                        'sClass': "text-center",
                        "mRender": function(data, type, full) {
                            if(data== 0 || data==null){
                                return	"关闭";
                            }else{
                                return "开启";
                            }

                        }
                    },

                    {
                        "data": "id",
                        "sTitle": "操作",
                        'sClass': "text-center"
                    }
                ],
                fnServerParams: function (aoData) {  //查询条件
                  //  aoData.push({"name": "name", "value": $("#nameQuery").val().replace(new RegExp(" ", "g"), "")});
                    aoData.push({"name": "skillExtStatus", "value": $("#skillStatusQuery").val()});
                    aoData.push({"name": "skillItemId", "value": $("#skillTypeQuery").val()});
                },
                aoColumnDefs: [{
                    "aTargets": 4,
                    "mRender": function (data, type, row) {

                        var detail = "", del = "", status;
                        detail = "<a href='#' onclick='addAndUpdateRow(\"" + row.id + "\")' data-toggle='modal' class='padding-right-small label label-success'><i class='glyphicon glyphicon-edit'></i>详情</a>";
                        return detail;
                    }
                }]

            });

            $('#query').click(function () {
                $('#example').dataTable().fnDraw();
            });

        });

        //增加或者修改受影响的行数
        function addAndUpdateRow(id) {
            $('#insertAndUpdate').addAndUpdateRow("discount/addAndUpdateHome.htm?id=" + id);
        }

        function deleteRow(id) {
            $('#myModal').deleteRow('skill/delete.htm?id=' + id);
        }

        function updateUpStatus(id){
            $('#updateStatusModal').deleteRow('skill/saveUpdate.htm?id=' + id+"&skillStatus=0");
        }

        function updateDownStatus(id) {
            $('#updateStatusModal').deleteRow('skill/saveUpdate.htm?id=' +id+"&skillStatus=1");
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
