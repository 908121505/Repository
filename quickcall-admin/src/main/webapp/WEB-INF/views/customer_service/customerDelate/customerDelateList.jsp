<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<shiro:hasPermission name="customerDelate:select">
<div class="content1">
    <div class="header">
        <h1 class="page-title">举报内容管理</h1>
        <ul class="breadcrumb">
            <li>客服后台</li>
            <li class="active">举报内容管理</li>
        </ul>
    </div>

    <div class="main-content">
			<div class="row">
				<div class="col-md-2">
					<div class="form-group">
						<div class="input-group">
							<div class="input-group-addon">用户昵称</div>
							<input class="form-control" type="text" id="nickName">
						</div>
					</div>
				</div>
				<div class="col-md-2">
					<div class="form-group">
						<div class="input-group">
							<div class="input-group-addon">举报名称</div>
							<input class="form-control" type="text" id="delateName">
						</div>
					</div>
				</div>
				<div class="col-md-2">
					<div class="form-group">
						<div class="input-group">
							<div class="input-group-addon">用户手机号</div>
							<input class="form-control" type="text" id="phone">
						</div>
					</div>
				</div>
				<div class="col-md-2">
					<button type="button" class="btn btn-primary btn-small btn-block"
						id="query">
						<i class="glyphicon glyphicon-search"></i> 查询
					</button>
				</div>				
			</div>
			<div class="row">
				<div class="col-md-2">
	                <div class="form-group">
	                    <div class="input-group">
	                        <%-- 0=未认证,1=待审核,2=已通过,3=拒绝 --%>
	                        <div class="input-group-addon">状态</div>
	                        <select class="form-control" id="handleStatus" name="handleStatus">
	                            <option value="">全部</option>
	                            <option value="0">未处理</option>
	                            <option value="1">已处理</option>
	                            <%--<option value="0">未认证</option>--%>
	                        </select>
	                    </div>
	                </div>
	            </div>
                <div class="col-md-2">
                    <div class="form-group">
                        <div class="input-group">
                            <div class="input-group-addon">提交时间</div>
                            <input class="form-control" type="date" id="startTime">
                            <div class="input-group-addon">到</div>
                            <input class="form-control" type="date" id="endTime">
                        </div>
                    </div>
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
					sAjaxSource:"customerDelate/initTable.htm",
					aoColumns: [
			            { 
			              "data": "nickName",
			              "sTitle":"用户名称",
			              'sClass':"text-center"
			            },
			            { 
			              "data": "delateName",
			              "sTitle":"举报名称",
			              'sClass':"text-center"
			            },
			            { 
			               "data": "delateContent",
			               "sTitle":"举报内容",
			               'sClass':"text-center",
			               "mRender": function(data, type, full) {
                          		if(data.length>10){
                          			return data.substring(0, 10)+"..."
                          		}else{
                          			return data;
                          		}
                          	}
			            },
						{
							"data": "createTime",
							"sTitle": "提交时间",
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
							"data": "phone",
							"sTitle": "用户手机号",
                            'sClass':"text-center"
						},
						{
							"data": "handleResult",
							"sTitle": "处理结果",
                            'sClass':"text-center",
                           	"mRender": function(data, type, full) {
                           		if(data.length>10){
                           			return data.substring(0, 10)+"..."
                           		}else{
                           			return data;
                           		}
                           	}
						},
						{
							"data": "handleUser",
							"sTitle": "处理人",
                            'sClass':"text-center"
						},
						{
							"data": "handleStatus",
							"sTitle": "状态",
                            'sClass':"text-center",
                           	"mRender": function(data, type, full) {
                            		if(data == 0){
                                       return "<font color='red'>未处理</font>";
                                   } else if(data == 1) {
                                       return "<font color='green'>已处理</font>";
                                   }else{
                                	   return data;
                                   }
                               }
						},
						{
							"data" : "id",
							"sTitle" : "操作",
							'sClass' : "text-center"
						}
			         ],
			         fnServerParams: function (aoData) {  //查询条件
	                       aoData.push({ "name": "nickName", "value": $("#nickName").val().replace(new RegExp(" ","g"),"") } );
	                       aoData.push({ "name": "delateName", "value": $("#delateName").val().replace(new RegExp(" ","g"),"") } );
		                   aoData.push({"name": "phone", "value": $("#phone").val()});
		                   aoData.push({"name": "startTime", "value": $("#startTime").val()});
		                   aoData.push({"name": "endTime", "value": $("#endTime").val()});
		                   aoData.push({"name": "handleStatus", "value": $("#handleStatus").val()});
	                    },
	                    aoColumnDefs : [ {
							"aTargets" : 8,
							"mRender" : function(data,type, row) {
								var detail = "",del = "";
								<shiro:hasPermission name="customerDelate:update">
								detail = "<a href='#' onclick='addAndUpdateRow(\""+ row.id +"\")' data-toggle='modal' class='padding-right-small label label-success'><i class='glyphicon glyphicon-edit'></i>修改</a>";
								</shiro:hasPermission>
								<shiro:hasPermission name="customerDelate:delete">
                                del = "<a href='#' onclick='deleteRow(\""+ row.id +"\")' data-toggle='modal' class='padding-right-small label label-success'><i class='glyphicon glyphicon-edit'></i>删除</a>";
                                </shiro:hasPermission>
								return  detail+"&nbsp;"+del;
							}
						} ]
		            
				});
				
				 $('#query').click(function(){
					$('#example').dataTable().fnDraw();
				});
			
			});
			//增加或者修改受影响的行数
			function addAndUpdateRow(id){
				$('#insertAndUpdate').addAndUpdateRow("customerDelate/addAndUpdateHome.htm?id="+id);
			}

        function deleteRow(id){
            $('#myModal').deleteRow("customerDelate/del.htm?id="+id);
        }
	</script>
		<!---dialog选项-->
		<div>
			<jsp:include page="/WEB-INF/views/common/delete_dialog.jsp" />
			<jsp:include page="/WEB-INF/views/common/addupdate_dialog.jsp" />

			<div class="modal fade" id="insertAndUpdate" tabindex="-1"
				role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			</div>
		</div>
	</div>
</shiro:hasPermission>