<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<shiro:hasPermission name="remindConfig:select">
	<div class="content1">
		<div class="header">
			<h1 class="page-title">提醒配置列表</h1>
			<ul class="breadcrumb">
				<li>提醒配置管理</li>
				<li class="active">提醒配置列表</li>
			</ul>
		</div>
		<div class="main-content">
			<div class="row">
				<div class="col-md-2">
					<div class="form-group">
						<div class="input-group">
							<div class="input-group-addon">提醒名称</div>
							<input class="form-control" type="text" id="remindName">
						</div>
					</div>
				</div>
				<div class="col-md-2">
					<div class="form-group">
						<div class="input-group">
							<div class="input-group-addon">提醒方式</div>
							<select class="form-control" id="remindType">
								<option value="">--全部--</option>
								<option value="1">邮箱提醒</option>
								<option value="2">短信提醒</option>
								<option value="3">电话提醒</option>
							</select>
						</div>
					</div>
				</div>
				<div class="col-md-2">
					<div class="form-group">
						<div class="input-group">
							<div class="input-group-addon">状态</div>
							<select class="form-control" id="state">
								<option value="">--全部--</option>
								<option value="1">有效</option>
								<option value="2">无效</option>
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
					<shiro:hasPermission name="remindConfig:add">
						<button type="button" class="btn btn-info btn-small btn-block"
							onclick="addAndUpdateRow('')">
							<i class="glyphicon glyphicon-plus"></i> 增加
						</button>
					</shiro:hasPermission>
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
					sAjaxSource:"remindConfig/initTable.htm",
					aoColumns: [
			            { 
				              "data": "remindName",
				              "sTitle":"提醒名称",
				              'sClass':"text-center"
				            },
			            { 
			               "data": "remindContent",
			               "sTitle":"提醒内容",
			               'sClass':"text-center"
			            },
			            { 
				               "data": "mobileRemindMan",
				               "sTitle":"手机提醒人",
				               'sClass':"text-center"
				            },
			            { 
				               "data": "mailRemindMan",
				               "sTitle":"邮箱提醒人",
				               'sClass':"text-center"
				            },
			            { 
				               "data": "telephoneRemindMan",
				               "sTitle":"电话提醒人",
				               'sClass':"text-center"
				            },
			            { 
				               "data": "remindType",
				               "sTitle":"提醒方式",
				               'sClass':"text-center",
				               "mRender": function(data, type, full) {
				            	   var str="";
				            	   if(data!=null && data.trim()!=""){
				            	   var remindTypes=data.split(",");
					         		  $.each(remindTypes,function(i,n){
					         			 if(i == remindTypes.length-1){
					         				 if(remindTypes[i]=='1'){
					         			           str+="邮箱提醒";
					         				 }else if(remindTypes[i]=='2'){
					         					  str+="短信提醒"; 
					         				 }else if(remindTypes[i]=='3'){
					         					  str+="电话提醒"; 
					         				 }
					         				 
					         			 }else{
					         				 if(remindTypes[i]=='1'){
					         			           str+="邮箱提醒,";
					         				 }else if(remindTypes[i]=='2'){
					         					  str+="短信提醒,"; 
					         				 }else if(remindTypes[i]=='3'){
					         					  str+="电话提醒,"; 
					         				 } 
					         			 }
					         		  });
				            	   }
									return str;
				                } 
				         },
			            { 
			                "data": "state",
			                "sTitle":"状态",
			                'sClass':"text-center",
			                "mRender": function(data, type, full) { 
			                	if(data == 1){
									return "<font color='red'>有效</font>";
								}else{
									return "<font color='blue'>无效</font>";
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
			            },
			            { 
			                "data": "createMan",
			                "sTitle":"创建人",
			                'sClass':"text-center"
			            },
			            { 
			                "data": "modifyTime",
			                "sTitle":"更新时间",
			                'sClass':"text-center",
			                "mRender": function(data, type, full) { 
				                  return	Format(new Date(data),"yyyy-MM-dd HH:mm:ss")
				                } 
			            },
			            { 
			                "data": "modifyMan",
			                "sTitle":"更新人",
			                'sClass':"text-center"
			            },
			            { 
				               "data": "remark",
				               "sTitle":"备注",
				               'sClass':"text-center"
				            }
			            <shiro:hasPermission name="remindConfig:update or remindConfig:delete">
			            ,{"data": "remindConfigId","sTitle":"操作",'sClass':"text-center"}
			            </shiro:hasPermission>
			         ],
					 fnServerParams: function (aoData) {  //查询条件
		             	aoData.push({ "name": "remindName", "value": $("#remindName").val() } );
		             	aoData.push({ "name": "state", "value": $("#state").val() } );
		             	aoData.push({ "name": "remindType", "value": $("#remindType").val() } );
		             },
		             aoColumnDefs : [
						<shiro:hasPermission name="remindConfig:update or remindConfig:delete">
		             	{"aTargets" :12,"mRender" : function(data, type, row){
		             		var edit="",del="";
		             		<shiro:hasPermission name="remindConfig:update">
		             			edit = "<a href='#' onclick='addAndUpdateRow(\""+row.remindConfigId+"\")' data-toggle='modal' class='padding-right-small label label-success'><i class='glyphicon glyphicon-edit'></i> 编辑</a>";
		             		</shiro:hasPermission>
		             		<shiro:hasPermission name="remindConfig:delete">
		             			del ="<a href='#' onclick='deleteRow(\""+row.remindConfigId+"\")' data-toggle='modal' class='label label label-danger'><i class='glyphicon glyphicon-trash'></i> 删除 </a>";
		             		</shiro:hasPermission>
		                    return edit+"&nbsp;"+del;
		                }
		             }
		            	</shiro:hasPermission>
		             ]
				});
				
				 $('#query').click(function(){
					$('#example').dataTable().fnDraw();
				});
			
			});
			//删除受影响的行数
			function deleteRow(id){
				console.log(id);
				$('#myModal').deleteRow('remindConfig/del.htm?id='+id);
			}
			//增加或者修改受影响的行数
			function addAndUpdateRow(id){
				$('#insertAndUpdate').addAndUpdateRow("remindConfig/addAndUpdateHome.htm?id="+id);
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