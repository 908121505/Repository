<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
	<div class="content1">
		<div class="header">
			<h1 class="page-title">订单管理</h1>
			<ul class="breadcrumb">
				<li>订单管理</li>
				<li class="active">订单列表</li>
			</ul>
		</div>
		<div class="main-content">
			<div class="row">
				<div class="col-md-2">
					<div class="form-group">
						<div class="input-group">
							<div class="input-group-addon">订单ID</div>
							<input class="form-control" type="text" id="orderIdQuery">
						</div>
					</div>
				</div>
				<div class="col-md-2">
					<div class="form-group">
						<div class="input-group">
							<div class="input-group-addon">用户昵称</div>
							<input class="form-control" type="text" id="custNickNameQuery" value = "">
						</div>
					</div>
				</div>
				<div class="col-md-2">
					<div class="form-group">
						<div class="input-group">
							<div class="input-group-addon">主播昵称</div>
							<input class="form-control" type="text" id="servNickNameQuery">
						</div>
					</div>
				</div>
				<div class="col-md-2">
					<div class="form-group">
						<div class="input-group">
							<div class="input-group-addon">订单分类</div>
							<input class="form-control" type="text" id="orderTypeQuery">
						</div>
					</div>
				</div>
				
				
				
				
				
				
				<div class="col-md-2">
					<button type="button" class="btn btn-primary btn-small btn-block"
						id="query">
						<i class="glyphicon glyphicon-search"></i> 查询
					</button>
				</div>
				<!-- <div class="col-md-2">
					<button type="button" class="btn btn-info btn-small btn-block"
						onclick="addAndUpdateRow(0)">
						<i class="glyphicon glyphicon-plus"></i> 增加
					</button>
				</div> -->
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
		              'sClass':"text-center"
		            },
		            { 
		               "data": "custNickName",
		               "sTitle":"用户昵称",
		               'sClass':"text-center",
		            },
		            { 
		                "data": "servNickName",
		                "sTitle":"大V昵称",
		                'sClass':"text-center",
		            },
                    {
                        "data": "orderType",
                        "sTitle":"订单分类",
                        'sClass':"text-center",
                    },
                    {
                        "data": "orderTime",
                        "sTitle":"下单时间",
                        'sClass':"text-center",
                        "mRender" : function(data, type,full) {
							if (data != null) {
								return Format(new Date(data),"yyyy-MM-dd HH:mm:ss")
							} else {
								return '--';
							}
						}
                    },
                    {
                        "data": "price",
                        "sTitle":"订单单价",
                        'sClass':"text-center",
                    },
                    {
                        "data": "orderNum",
                        "sTitle":"下单数量",
                        'sClass':"text-center",
                    },
                    {
                        "data": "orderAmounts",
                        "sTitle":"总价",
                        'sClass':"text-center",
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
						"data" : "finishTime",
						"sTitle" : "完成时间",
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
						"data" : "orderStatus",
						"sTitle" : "订单状态",
						'sClass' : "text-center"
					}/*,
					{
						"data" : "orderId",
						"sTitle" : "操作",
						'sClass' : "text-center"
					}*/
		         ],
		         fnServerParams: function (aoData) {  //查询条件
                       aoData.push({ "name": "orderId", "value": $("#orderIdQuery").val() } );
                       aoData.push({ "name": "custNickName", "value": $("#custNickNameQuery").val().replace(new RegExp(" ","g"),"") } );
                       aoData.push({ "name": "servNickName", "value": $("#servNickNameQuery").val().replace(new RegExp(" ","g"),"") } );
                       aoData.push({ "name": "orderType", "value": $("#orderTypeQuery").val().replace(new RegExp(" ","g"),"") } );
                    } ,
                 aoColumnDefs : [ {
					"aTargets" : 11
					 /*,
					"mRender" : function(data,type, row) {
						var detail = "";
						// detail = "<a href='#' onclick='viewOrderDetail(\""+ row.orderId+ "\")' data-toggle='modal' class='padding-right-small label label-success'><i class='glyphicon glyphicon-edit'></i>详情</a>";
						return  detail;
					}*/
			} ] 
	            
			});
			
			 $('#query').click(function(){
				$('#example').dataTable().fnDraw();
			});

		});

		function viewOrderDetail(id) {
			$('#order_detail_model').addAndUpdateRow("order/addAndUpdateHome.htm?id=" + id);
		}

</script>
		<!---dialog选项-->
		<div>
			<jsp:include page="/WEB-INF/views/common/delete_dialog.jsp" />

			<div class="modal fade" id="order_detail_model" tabindex="-1"
				 role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static">
			</div>
		</div>
	</div>
