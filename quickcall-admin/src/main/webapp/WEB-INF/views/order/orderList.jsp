<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<link rel="stylesheet" href="//res.layui.com/layui/build/css/layui.css"  media="all">
<div class="content1">
		<div class="header">
			<h1 class="page-title">订单管理</h1>
			<ul class="breadcrumb">
				<li>订单管理</li>
				<li class="active">订单列表</li>
			</ul>
		</div>
		<div class="main-content">
		<!--  第一行：公共查询 -->
			<div class="row">
				<div class="col-md-2">
					<div class="form-group">
						<div class="input-group">
							<div class="input-group-addon">订单ID</div>
							<input class="form-control" type="text" id="order_id">
						</div>
					</div>
				</div>
				<div class="col-md-2">
					<div class="form-group">
						<div class="input-group">
							<div class="input-group-addon">开始时间</div>
							<input class="form-control" value="${showStartTime}" type="text" id="start_time" onfocus="WdatePicker({skin:'default',dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'1950-01-01'})">
						</div>
					</div>
				</div>

				<div class="col-md-2">
					<div class="form-group">
						<div class="input-group">
							<div class="input-group-addon">结束时间</div>
							<input class="form-control" value="${showEndTime}" type="text" id="end_time" onfocus="WdatePicker({skin:'default',dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'1950-01-01'})">
						</div>
					</div>
				</div>
			</div>
		<!--  第一行：下单人信息查询 -->
			<div class="row">
				

				<div class="col-md-2">
					<div class="form-group">
						<div class="input-group">
							<div class="input-group-addon">下单用户轻音ID</div>
							<input class="form-control" type="text" id="place_order_id">
						</div>
					</div>
				</div>
				<div class="col-md-2">
					<div class="form-group">
						<div class="input-group">
							<div class="input-group-addon">下单手机号码</div>
							<input class="form-control" type="text" id="customerPhoneQuery">
						</div>
					</div>
				</div>
				
				<div class="col-md-2">
					<div class="form-group">
						<div class="input-group">
							<div class="input-group-addon">下单用户昵称</div>
							<input class="form-control" type="text" id="place_order_user_nick_name">
						</div>
					</div>
				</div>
			</div>
			
			
			
			
			<div class="row">
				

		
				<div class="col-md-2">
					<div class="form-group">
						<div class="input-group">
							<div class="input-group-addon">接单用户轻音ID</div>
							<input class="form-control" type="text" id="received_order_id">
						</div>
					</div>
				</div>
				<div class="col-md-2">
					<div class="form-group">
						<div class="input-group">
							<div class="input-group-addon">接单手机号码</div>
							<input class="form-control" type="text" id="servicePhoneQuery">
						</div>
					</div>
				</div>

				<div class="col-md-2">
					<div class="form-group">
						<div class="input-group">
							<div class="input-group-addon">接单用户昵称</div>
							<input class="form-control" type="text" id="receive_order_user_nick_name">
						</div>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-md-2">
					<div class="form-group">
						<div class="input-group">
							<div class="input-group-addon">订单状态</div>
							<select class="form-control" id="order_status">
								<option value="-1">--请选择--</option>
								<c:forEach items="${statusList}" var ="orderStatusVO" varStatus="status">
									<option value="${orderStatusVO.value}">${orderStatusVO.desc}</option>
								</c:forEach>
							</select>
						</div>
					</div>
				</div>
				<div class="col-md-2">
					<div class="form-group">
						<div class="input-group">
							<div class="input-group-addon">服务类型</div>
							<select class="form-control" id="service_type">
								<option value="-1">--请选择--</option>
								<c:forEach items="${services}" var ="skillItem" varStatus="status">
									<option value="${skillItem.id}">${skillItem.skillItemName}</option>
								</c:forEach>
							</select>
						</div>
					</div>
				</div>
				<div class="col-md-2">
					<div class="form-group">
						<div class="input-group">
							<div class="input-group-addon">折扣类型</div>
							<select class="form-control" id="discount_type_val">
								<option value="-1">--请选择--</option>
								<option value="10-11">无折扣</option>
								<option value="1-2">10%</option>
								<option value="2-3">20%</option>
								<option value="3-4">30%</option>
								<option value="4-5">40%</option>
								<option value="5-6">50%</option>
								<option value="6-7">60%</option>
								<option value="7-8">70%</option>
								<option value="8-9">80%</option>
								<option value="9-10">90%</option>
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
							onclick="addAndUpdateRow(999)">
						<i class="glyphicon glyphicon-plus"></i> 增加
					</button>
				</div>
			</div>
			<table id="example" class="table"></table>
		</div>
<script>
		
		
        function Format(now,mask)
		{
		    var d = now;
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
		}; 
		//表格的初始化
		$(document).ready(function() {
			var table = $('#example').initTable({
				sAjaxSource:"order/initTable.htm",
				aoColumns: [
		            { 
		              "data": "orderId",
		              "sTitle":"订单ID",
		              'sClass':"text-center", "sortable": false
		            },
		            { 
		               "data": "receivedOrderId",
		               "sTitle":"接单用户轻音ID",
		               'sClass':"text-center", "sortable": false
		            },
		            { 
		                "data": "servicePhone",
		                "sTitle":"接单用户手机号码",
		                'sClass':"text-center", "sortable": false
		            },
		            { 
		                "data": "receiveOrderUserNickName",
		                "sTitle":"接单用户昵称",
		                'sClass':"text-center", "sortable": false
		            },
                    {
                        "data": "placeOrderId",
                        "sTitle":"下单用户轻音ID",
                        'sClass':"text-center", "sortable": false
                    },
                    {
                        "data": "customerPhone",
                        "sTitle":"下单用户手机号码",
                        'sClass':"text-center", "sortable": false
                    },
                    {
                        "data": "placeOrderUserNickName",
                        "sTitle":"下单用户昵称",
                        'sClass':"text-center", "sortable": false
                    },
                    {
                        "data": "serviceType",
                        "sTitle":"服务类型",
                        'sClass':"text-center", "sortable": false
                    },
                    {
                        "data" : "startTime",
                        "sTitle" : "开始时间",
                        'sClass' : "text-center",
                        "mRender" : function(data, type,full) {
                            if (data != null) {
                                return Format(new Date(data),"yyyy-MM-dd HH:mm:ss")
                            } else {
                                return '--';
                            }
                        }
                    },
                    {
                        "data" : "endTime",
                        "sTitle" : "结束时间",
                        'sClass' : "text-center", "sortable": false,
                        "mRender" : function(data, type,full) {
                            if (data != null) {
                                return Format(new Date(data),"yyyy-MM-dd HH:mm:ss")
                            } else {
                                return '--';
                            }
                        }
                    },
                    {
                        "data": "unitPrice",
                        "sTitle":"单价（次/音符、半小时/音符、小时/音符）",
                        'sClass':"text-center", "sortable": false
                    },
                    {
                        "data": "discountType",
                        "sTitle":"折扣类型",
                        'sClass':"text-center", "sortable": false
                    },
                    {
                        "data": "orderAmount",
                        "sTitle":"订单金额(单位:元)",
                        'sClass':"text-center", "sortable": false
                    },
                    {
                        "data" : "orderStatusVal",
                        "sTitle" : "订单状态",
                        'sClass' : "text-center", "sortable": false
                    },
                    {
						"data" : "orderCreateTime",
						"sTitle" : "订单创建时间",
						'sClass' : "text-center", "sortable": false,
						"mRender" : function(data, type,full) {
							if (data != null) {
								return Format(new Date(data),"yyyy-MM-dd HH:mm:ss")
							} else {
								return '--';
							}
						}
					},
					{
						"data" : "orderId",
						"sTitle" : "操作",
						'sClass' : "text-center", "sortable": false
					}
		         ],
		         fnServerParams: function (aoData) {  //查询条件
				    aoData.push({"name":"orderId","value":$("#order_id").val()});
				    aoData.push({"name":"placeOrderId","value":$("#place_order_id").val()});
				    aoData.push({"name":"receivedOrderId","value":$("#received_order_id").val()});
				    aoData.push({"name":"startTime","value":$("#start_time").val()});
				    aoData.push({"name":"endTime","value":$("#end_time").val()});
				    aoData.push({"name":"placeOrderUserNickName","value":$("#place_order_user_nick_name").val()});
				    aoData.push({"name":"receiveOrderUserNickName","value":$("#receive_order_user_nick_name").val()});
				    aoData.push({"name":"serviceType","value":$("#service_type").val()});
				    aoData.push({"name":"orderStatus","value":$("#order_status").val()});
				    aoData.push({"name":"customerPhone","value":$("#customerPhoneQuery").val()});
				    aoData.push({"name":"servicePhone","value":$("#servicePhoneQuery").val()});
                    } ,
                 aoColumnDefs : [ {
                     "aTargets": 15,
                     "mRender": function (data, type, row) {
                         var detail = "", del = "";
                         detail = "<a href='#' onclick='addAndUpdateRow(\"" + data + "-detail\")' data-toggle='modal' class='padding-right-small label label-success'><i class='glyphicon glyphicon-edit'></i>详情</a>";
                         del = "<a href='#' onclick='addAndUpdateRow(\"" + data + "-update\")' data-toggle='modal' class='padding-right-small label label-success'><i class='glyphicon glyphicon-edit'></i>编辑</a>";
                         return detail + "&nbsp;" + del;
                     }
			} ] 
	            
			});
			
			 $('#query').click(function(){
				$('#example').dataTable().fnDraw();
			});

		});

        //订单状态
        /*$(document).ready(function() {
            $.ajax({
                url:"../order/orderStatusList.htm",
                type:"POST",
                dataType: "json",
                contentType: "application/json",
                success: function (data) {
                    var option = "";
                    for (var resCode in data) {
                        var resName =data[resCode];
                        option += "<option value=\"" + resName.subset + "\" >" + resName.desc + "</option>";
                    }
                    $("#orderStatus").html("<option value=\"-1\">--请选择--</option>"+option); }
            });
        });
		*/
        //服务类型
        // $(document).ready(function() {
        //     $.ajax({
        //         url:"../order/skillItemsList.htm",
        //         type:"POST",
        //         dataType: "json",
        //         contentType: "application/json",
        //         success: function (data) {
        //             var option = "";
        //             for (var resCode in data) {
        //                 var resName =data[resCode];
        //                 option += "<option value=\"" + resName.id + "\" >" + resName.skillItemName + "</option>";
        //             }
        //             $("#serviceType").html("<option value=\"-1\">--请选择--</option>"+option); }
        //     });
        // });

        //增加或者修改受影响的行数
        function addAndUpdateRow(id) {
            $('#insertAndUpdate').addAndUpdateRow("order/addAndUpdateHome.htm?id=" + id);
        }

</script>
		<!---dialog选项-->
		<div>
			<jsp:include page="/WEB-INF/views/common/delete_dialog.jsp"/>
			<jsp:include page="/WEB-INF/views/common/addupdate_dialog.jsp"/>
			<jsp:include page="/WEB-INF/views/common/updateStatus.jsp"/>

			<div class="modal fade" id="order_detail_model" tabindex="-1"
				 role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			</div>
		</div>




<%--</script>
		<!---dialog选项-->
		<div>
			<jsp:include page="/WEB-INF/views/common/delete_dialog.jsp" />

			<div class="modal fade" id="order_detail_model" tabindex="-1"
				 role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static">
			</div>
		</div>
	</div>--%>
