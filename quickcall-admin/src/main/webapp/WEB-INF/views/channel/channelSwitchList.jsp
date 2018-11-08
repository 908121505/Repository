<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
	<div class="content1">
		<div class="header">
			<h1 class="page-title">渠道开关管理</h1>
			<ul class="breadcrumb">
				<li>渠道开关后台</li>
				<li class="active">渠道开关管理</li>
			</ul>
		</div>
		<div class="main-content">
			<div class="row">
				<div class="col-md-2">
					<div class="form-group">
						<div class="input-group">
							<div class="input-group-addon">渠道号</div>
							<input class="form-control" type="text" id="channelStr">
						</div>
					</div>
				</div>
				<div class="col-md-2">
					<div class="form-group">
						<div class="input-group">
							<div class="input-group-addon">版本号</div>
							<input class="form-control" type="text" id="versionStr">
						</div>
					</div>
				</div>
				<div class="col-md-2">
					<div class="form-group">
						<div class="input-group">
							<div class="input-group-addon">状态</div>
							<%--状态(0=关，1=开)--%>
							<select class="form-control" id="statusStr">
								<option value="">--请选择--</option>
								<option value="0">关</option>
								<option value="1">开</option>
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
		$(document).ready(function() {

			var table = $('#example').initTable({
				sAjaxSource:"channelSwitch/initTable.htm",
				aoColumns: [
		            { 
						  "data": "index",
						  "sTitle":"序号",
						  'sClass':"text-center",
						  "mRender": function (data, type, full, meta) {
							  return meta.row+1 + meta.settings._iDisplayStart;
						  }
		            },
		            { 
		               "data": "channel",
		               "sTitle":"渠道号",
		               'sClass':"text-center",
		            },
		            { 
		                "data": "version",
		                "sTitle":"版本号",
		                'sClass':"text-center",
		            },
                    {
                        "data": "type",
                        "sTitle":"类型",
                        'sClass':"text-center",
                        "bVisible": false //此列不显示
                    },
                    {
                        "data": "status",
                        "sTitle":"状态",
                        'sClass':"text-center",
                    },
                    {
                        "data": "createMan",
                        "sTitle":"创建人",
                        'sClass':"text-center",
                        "mRender": function (data, type, full) {
                            if (data != null && data != "") {
                                return data;
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
						   if (data != null && data != "") {
							   return Format(new Date(data), "yyyy-MM-dd HH:mm:ss")
						   } else {
							   return '--';
						   }
						}
					},
                    {
                        "data": "modifyMan",
                        "sTitle":"修改人",
                        'sClass':"text-center",
                        "mRender": function (data, type, full) {
                            if (data != null && data != "") {
                                return data;
                            } else {
                                return '--';
                            }
                        }
                    },
                    {
                        "data": "modifyTime",
                        "sTitle": "修改时间",
                        'sClass': "text-center",
                        "mRender": function (data, type, full) {
							if (data != null && data != "") {
								return Format(new Date(data), "yyyy-MM-dd HH:mm:ss")
							} else {
								return '--';
							}
						}
					},
					{
						"data" : "id",
						"sTitle" : "操作",
						'sClass' : "text-center",
                        "mRender": function (data, type, row) {
                            var edit = "";
                            var del = "";
							edit = "<a href='#' onclick='addAndUpdateRow(\"" + row.id + "\")' data-toggle='modal' class='padding-right-small label label-success'><i class='glyphicon glyphicon-edit'></i>编辑</a>";
                            del = "<a href='#' onclick='deleteRow(\"" + row.id + "\")' data-toggle='modal' class='label label label-danger'><i class='glyphicon glyphicon-trash'></i> 删除 </a>";
							return edit + "&nbsp;" + del;
                        }
					}
		         ],
		         fnServerParams: function (aoData) {  //查询条件
					 aoData.push({ "name": "channelStr", "value": $("#channelStr").val() } );
                     aoData.push({ "name": "versionStr", "value": $("#versionStr").val() } );
                     aoData.push({ "name": "statusStr", "value": $("#statusStr").val() } );
				 } ,
                 aoColumnDefs : [{

                 }]
	            
			});
			
			 $('#query').click(function(){
				$('#example').dataTable().fnDraw();
			});

		});

        //增加或者修改受影响的行数
        function addAndUpdateRow(id) {
            $('#insertAndUpdate').addAndUpdateRow("channelSwitch/addAndUpdateHome.htm?id=" + id);
        }

        function deleteRow(id) {
			$('#myModal').deleteRow('channelSwitch/delete.htm?id=' + id);
        }


</script>
		<!---dialog选项-->
		<div>
			<jsp:include page="/WEB-INF/views/common/delete_dialog.jsp" />
			<jsp:include page="/WEB-INF/views/common/addupdate_dialog.jsp"/>

			<div class="modal fade" id="insertAndUpdate" tabindex="-1"
				 role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static">
			</div>
		</div>
	</div>
