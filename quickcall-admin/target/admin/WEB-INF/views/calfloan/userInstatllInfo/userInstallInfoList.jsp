<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<shiro:hasPermission name="userInstallInfo:select">
	<div class="content1">
		<div class="header">
			<h1 class="page-title">用户装机列表</h1>
			<ul class="breadcrumb">
				<li>用户装机管理</li>
				<li class="active">用户装机列表</li>
				 <spam>&nbsp;&nbsp;<font color="red">(*时间不填默认查当天数据)</font></spam>
			</ul>
		</div>
		<div class="main-content">
			<div class="row">
				<div class="col-md-2">
					<div class="form-group">
						<div class="input-group">
							<div class="input-group-addon">手机号</div>
							<input class="form-control" type="text" id="phoneNum">
						</div>
					</div>
				</div>
				<div class="col-md-2">
					<div class="form-group">
						<div class="input-group">
							<div class="input-group-addon">app名称</div>
							<input class="form-control" type="text" id="appName">
						</div>
					</div>
				</div>
				 <div class="col-md-4">
                    <div class="form-group">
                        <div class="input-group">
                            <div class="input-group-addon">创建时间</div>
                            <input class="form-control" type="date" id="startTime">
                            <div class="input-group-addon">到</div>
                            <input class="form-control" type="date" id="endTime">
                        </div>
                    </div>
                </div>
				<div class="col-md-1">
					&nbsp;
				</div>
				<div class="col-md-2">
					<div class="form-group">
						<div class="input-group">
							<div class="input-group-addon">是否管家平台</div>
							<select class="form-control" id="isGjplatform">
								<option value="">--全部--</option>
								<option value="1">是</option>
								<option value="2">否</option>
							</select>
						</div>
					</div>
				</div>
				
			</div>
			
				<div class="col-md-1">
					<button type="button" class="btn btn-primary btn-small btn-block"
						id="query">
						<i class="glyphicon glyphicon-search"></i> 查询
					</button>
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
					sAjaxSource:"userInstallInfo/initTable.htm",
					aoColumns: [
			            { 
			              "data": "phoneNum",
			              "sTitle":"手机号",
			              'sClass':"text-center"
			            },
			            { 
				              "data": "deviceId",
				              "sTitle":"设备号",
				              'sClass':"text-center"
				            	   },
	            	  { 
			              "data": "phoneType",
			              "sTitle":"手机类型",
			              'sClass':"text-center"
			            	   },
            	     { 
			              "data": "appName",
			              "sTitle":"app名称",
			              'sClass':"text-center"
			            	   },
            	     { 
			              "data": "appVersion",
			              "sTitle":"app版本号",
			              'sClass':"text-center"
			            },
			            { 
			                "data": "firstTime",
			                "sTitle":"第一次安装时间",
			                'sClass':"text-center",
			                "mRender": function(data, type, full) { 
			                  return	Format(new Date(data),"yyyy-MM-dd HH:mm:ss")
			                } 
			            },
			            { 
			                "data": "lastTime",
			                "sTitle":"最后一次安装时间",
			                'sClass':"text-center",
			                "mRender": function(data, type, full) { 
			                  return	Format(new Date(data),"yyyy-MM-dd HH:mm:ss")
			                } 
			            },
				        { 
			                "data": "state",
			                "sTitle":"状态",
			                'sClass':"text-center",
			                "mRender": function(data, type, full) { 
			                	if(data == 1){
									return "<font color='red'>有效</font>";
								}else if(data == 2){
									return "<font color='blue'>无效</font>";
								}
			                } 
			            },
			            { 
			                "data": "isGjplatform",
			                "sTitle":"是否管家平台",
			                'sClass':"text-center",
			                "mRender": function(data, type, full) { 
			                	if(data == 1){
									return "<font color='red'>是</font>";
								}else if(data == 2){
									return "<font color='blue'>否</font>";
								}
			                } 
			            },
			            { 
			                "data": "createTime",
			                "sTitle":"创建时间",
			                'sClass':"text-center",
			                "mRender": function(data, type, full) { 
			                  return	Format(new Date(data),"yyyy-MM-dd HH:mm:ss")
			                } 
			            }
			           
			         ],
					 fnServerParams: function (aoData) {  //查询条件
		             	aoData.push({ "name": "phoneNum", "value": $("#phoneNum").val() } );
		             	aoData.push({ "name": "appName", "value": $("#appName").val() } );
		            	aoData.push({ "name": "isGjplatform", "value": $("#isGjplatform").val() } );
		             	aoData.push({ "name": "startTime", "value": $("#startTime").val() } );
		             	aoData.push({ "name": "endTime", "value": $("#endTime").val() } );
		             }
				});
				
				 $('#query').click(function(){
					$('#example').dataTable().fnDraw();
				});
			
			});
			
			</script>
		<!---dialog选项-->
		<div>
	             <jsp:include page="/WEB-INF/views/common/update_dialog.jsp" />
			<div class="modal fade" id="insertAndUpdate" tabindex="-1"
				role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			</div>
		</div>
	</div>
</shiro:hasPermission>