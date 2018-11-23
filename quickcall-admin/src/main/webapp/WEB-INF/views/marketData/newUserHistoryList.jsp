<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<link type="text/css"  href="resources/bootstrap/css/bootstrap-select.min.css"  rel="stylesheet" />
<script type="text/javascript" language="javascript" src="resources/bootstrap/js/bootstrap-select.min.js"  charset="utf-8"></script>
<div class="content1">
    <div class="header">
        <h1 class="page-title">轻音渠道后台</h1>
        <ul class="breadcrumb">
            <li>当日实时数据</li>
            <li class="active">轻音渠道后台</li>
        </ul>
    </div>
    <div class="main-content">
        <div class="row">
            <div class="col-md-4">
                <div class="form-group">
                    <div class="input-group">
                        <div class="input-group-addon">日期</div>
                        <input class="form-control" type="date" id="sTime">
                        <div class="input-group-addon">到</div>
                        <input class="form-control" type="date" id="eTime">
                    </div>
                </div>
            </div>
            <div class="col-md-2">
                <div class="form-group">
                    <div class="input-group">
                        <div class="input-group-addon">小时</div>
                        <input class="form-control" onkeydown="if(window.event.keyCode!=8)return false;" type="number" id="sHour" min=0 max=24 step=1>
                        <div class="input-group-addon">到</div>
                        <input class="form-control" onkeydown="if(window.event.keyCode!=8)return false;" type="number" id="eHour" min=0 max=24 step=1>
                    </div>
                </div>
            </div>
            <div class="col-md-2">
                <div class="form-group">
                    <div class="input-group">
                        <div class="input-group-addon">渠道</div>
                        <select class="form-control selectpicker" id="appChannelName" multiple>
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
                        id="exportCSV" onclick="ExportCSV()">
                    <i class="glyphicon glyphicon-export"></i> 导出CSV
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
        	$.ajax({
                type: "POST",
                url: "marketData/getChannel.htm",
                data: {},
                dataType: "json",
                success: function(data){
                	for (var i = 0; i < data.length; i++) {
               	　　　　$("#appChannelName").append("<option value="+data[i]+">"+data[i]+"</option>");
                    }
                	$('#appChannelName').selectpicker({
                		width:200
               		});
                }
            });
            var table = $('#example').initTable({
                sAjaxSource: "marketData/initTable.htm",
                aoColumns: [
                    {
                        "data": "date",
                        "sTitle": "日期",
                        'sClass': "text-center"
                    },
                    {
                        "data": "appChannelName",
                        "sTitle": "渠道名",
                        'sClass': "text-center"/* ,
                        "mRender": function (data, type, full) {
                            if (data == '' || data == null) {
                                return "未知渠道";
                            } else {
                                return data;
                            }
                        } */
                    },
                    {
                        "data": "activeNum",
                        "sTitle": "激活数",
                        'sClass': "text-center",
                        "mRender": function (data, type, full) {
                            if (data == '' || data == null) {
                                return "0";
                            } else {
                                return data;
                            }
                        }
                    },
                    {
                        "data": "registerNum",
                        "sTitle": "注册数",
                        'sClass': "text-center",
                        "mRender": function (data, type, full) {
                            if (data == '' || data == null) {
                                return "0";
                            } else {
                                return data;
                            }
                        }
                    },
                    {
                        "data": "couponNum",
                        "sTitle": "券使用数",
                        'sClass': "text-center",
                        "mRender": function (data, type, full) {
                            if (data == '' || data == null) {
                                return "0";
                            } else {
                                return data;
                            }
                        }
                    },
                    {
                        "data": "rechargeNum",
                        "sTitle": "充值人数",
                        'sClass': "text-center",
                        "mRender": function (data, type, full) {
                            if (data == '' || data == null) {
                                return "0";
                            } else {
                                return data;
                            }
                        }
                    },
                    {
                        "data": "rechargeTime",
                        "sTitle": "充值次数",
                        'sClass': "text-center",
                        "mRender": function (data, type, full) {
                            if (data == '' || data == null) {
                                return "0";
                            } else {
                                return data;
                            }
                        }
                    },
                    {
                        "data": "rechargeTotal",
                        "sTitle": "充值金额",
                        'sClass': "text-center",
                        "mRender": function (data, type, full) {
                            if (data == '' || data == null) {
                                return "0";
                            } else {
                                return data;
                            }
                        }
                    },
                    {
                        "data": "orderNum",
                        "sTitle": "总下单人数",
                        'sClass': "text-center",
                        "mRender": function (data, type, full) {
                            if (data == '' || data == null) {
                                return "0";
                            } else {
                                return data;
                            }
                        }
                    },
                    {
                        "data": "orderTime",
                        "sTitle": "总下单次数",
                        'sClass': "text-center",
                        "mRender": function (data, type, full) {
                            if (data == '' || data == null) {
                                return "0";
                            } else {
                                return data;
                            }
                        }
                    },
                    {
                        "data": "orderTotal",
                        "sTitle": "总下单金额",
                        'sClass': "text-center",
                        "mRender": function (data, type, full) {
                            if (data == '' || data == null) {
                                return "0";
                            } else {
                                return data;
                            }
                        }
                    },
                    {
                        "data": "wakeNum",
                        "sTitle": "哄睡单数",
                        'sClass': "text-center",
                        "mRender": function (data, type, full) {
                            if (data == '' || data == null) {
                                return "0";
                            } else {
                                return data;
                            }
                        }
                    },
                    {
                        "data": "sleepNum",
                        "sTitle": "咨询单数",
                        'sClass': "text-center",
                        "mRender": function (data, type, full) {
                            if (data == '' || data == null) {
                                return "0";
                            } else {
                                return data;
                            }
                        }
                    },
                    {
                        "data": "consultNum",
                        "sTitle": "叫醒单数",
                        'sClass': "text-center",
                        "mRender": function (data, type, full) {
                            if (data == '' || data == null) {
                                return "0";
                            } else {
                                return data;
                            }
                        }
                    }
                ],
                fnServerParams: function (aoData) {
                    //查询条件
                	seachData(aoData);
                },
            	bPaginate:false
            /* ,
                aoColumnDefs: [{
                    "aTargets": 7,
                    "mRender": function (data, type, row) {
                        var edit = "", del = "";
                        edit = "<a href='#' onclick='addAndUpdateRow(\"" + data.toString() + "\")' data-toggle='modal' class='padding-right-small label label-success'><i class='glyphicon glyphicon-edit'></i> 编辑</a>";

                        del = "<a href='#' onclick='deleteRow(\"" + data.toString() + "\")' data-toggle='modal' class='label label label-danger'><i class='glyphicon glyphicon-trash'></i> 删除 </a>";
                        return edit + "&nbsp;&nbsp;" + del;
                    }
                }] */

            });

            $('#query').click(function () {
            	if(check()){
                	$('#example').dataTable().fnDraw();
            	}
            });
			
        });
        
        function check(){
        	if($('#sTime').val()>$('#sTime').val()){
        		alert("日期右侧需大于左侧！");
        		return false;
        	}
        	if(parseInt($('#sHour').val())>parseInt($('#eHour').val())){
        		alert("小时右侧需大于左侧！");
        		return false;
        	}
        	return true;
        }

        //增加或者修改受影响的行数
        function addAndUpdateRow(id) {
            $('#insertAndUpdate').addAndUpdateRow("marketData/addAndUpdateHome.htm?id=" + id);
        }
        
        function seachData(aoData){
        	var channel = $("#appChannelName").val();
            if(channel != null){
            	channel = channel.join(',');
            }
            aoData.push({"name": "sTime", "value": $("#sTime").val()});
            aoData.push({"name": "eTime", "value": $("#eTime").val()});
            aoData.push({"name": "sHour", "value": $("#sHour").val()});
            aoData.push({"name": "eHour", "value": $("#eHour").val()});
            aoData.push({"name": "appChannelName", "value":channel});
            aoData.push({"name": "type", "value": 2});
        }
        
        function ExportCSV(){
        	var array=[];
        	seachData(array);
        	var form = document.createElement('form');
            form.action = 'marketData/exportCSV.htm';
            form.method = 'post'; 
            form.style.display = "none";
            for(var index in array){
                var input = document.createElement("input");
                input.type = "hidden";
                input.name = array[index].name;
                input.value = array[index].value;
                form.appendChild(input);
            }
            $(document.body).append(form);
            form.submit();
            form.remove();
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
