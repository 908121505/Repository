<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<shiro:hasPermission name="opinionfeedback:select">
	<div class="content1">
		<div class="header">
			<h1 class="page-title">反馈意见列表</h1>
			<ul class="breadcrumb">
				<li>反馈意见管理</li>
				<li class="active">反馈意见列表</li>
			</ul>
		</div>
		<div class="main-content">
			<div class="row">
				<div class="col-md-2">
					<div class="form-group">
						<div class="input-group">
							<div class="input-group-addon">状态</div>
							<select class="form-control" id="state">
								<option value="">--请选择--</option>
								<option value="1">待处理</option>
								<option value="2">已处理</option>
							</select>
						</div>
					</div>
				</div>
				
				
				<div class="col-md-2">
					<div class="form-group">
						<div class="input-group">
							<div class="input-group-addon">反馈类型</div>
								<select class="form-control" id="feedbackType">
								<option value="">--请选择--</option>
								<option value="3">用户反馈</option>
								<option value="4">信用反馈</option>
							</select>
						</div>
					</div>
				</div>
				
				<div class="col-md-2">
					<div class="form-group">
						<div class="input-group">
							<div class="input-group-addon">反馈标签</div>
								<input class="form-control" type="text" id="feedbackLabel">
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
					<shiro:hasPermission name="opinionfeedback:add">
						<button type="button" class="btn btn-info btn-small btn-block"
							onclick="addAndUpdateRow(0)">
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
					sAjaxSource:"opinionfeedback/initTable.htm",
					aoColumns: [
						{ 
							"data": "feedbackReason",
						    "sTitle":"反馈内容",
						    'sClass':"text-center",
						    'width':"400px"
						  },
							  { 
					                "data": "mobile",
					                "sTitle":"反馈人手机号",
					                'sClass':"text-center"
					            },
					            { 
								    "data": "feedbackLabel",
								    "sTitle":"反馈标签",
								    'sClass':"text-center" 
								  },
			            { 
			                "data": "state",
			                "sTitle":"状态",
			                'sClass':"text-center",
			                "mRender": function(data, state, full) { 
			                	if(data == 1){
									return "<font color='red'>待处理</font>";
								}else{
									return "<font color='blue'>已处理</font>";
								}
			                } 
			            },
			            { 
			                "data": "handelPerson",
			                "sTitle":"处理人",
			                'sClass':"text-center"
			            },
			            { 
			                "data": "modifyTime",
			                "sTitle":"更新时间",
			                'sClass':"text-center",
			                "mRender": function(data, modifyTime, full) { 
			                	if(data==null){
									return "";
								}else{
									return Format(new Date(data),"yyyy-MM-dd HH:mm:ss")
								}
				            } 
			            },
			            { 
			                "data": "handelResult",
			                "sTitle":"处理结果",
			                'sClass':"text-center"
			            },
			            { 
			                "data": "createTime",
			                "sTitle":"创建时间",
			                'sClass':"text-center",
			                "mRender": function(data, createTime, full) { 
				                  return	Format(new Date(data),"yyyy-MM-dd HH:mm:ss")
				                } 
			            }
			           
			            <shiro:hasPermission name="opinionfeedback:update or opinionfeedback:delete">
			            ,{"data": "opinionFeedbackId","sTitle":"操作",'sClass':"text-center"}
			            </shiro:hasPermission>
			         ],
					 fnServerParams: function (aoData) {  //查询条件
		             	aoData.push({ "name": "state", "value": $("#state").val() } ); 
		             	aoData.push({ "name": "feedbackType", "value": $("#feedbackType").val() } );
		             	aoData.push({ "name": "feedbackLabel", "value": $("#feedbackLabel").val() } );
		             },
		             aoColumnDefs : [
						<shiro:hasPermission name="opinionfeedback:update or opinionfeedback:delete">
		             	{"aTargets" :8,"mRender" : function(data, type, row){
		             	   
		             		var edit="",del="",detail="";
		             		<shiro:hasPermission name="opinionfeedback:update">
		             			edit = "<a href='#' onclick='addAndUpdateRow(\""+data+"\")' data-toggle='modal' class='padding-right-small label label-success'><i class='glyphicon glyphicon-edit'></i> 编辑</a>";
		             		</shiro:hasPermission>
		             		<shiro:hasPermission name="opinionfeedback:delete">
		             			del ="<a href='#' onclick='deleteRow(\""+data+"\")' data-toggle='modal' class='label label label-danger'><i class='glyphicon glyphicon-trash'></i> 删除 </a>";
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
				$('#myModal').deleteRow('opinionfeedback/del.htm?id='+id);
			}
			//增加或者修改受影响的行数
			function addAndUpdateRow(id){
				$('#insertAndUpdate').addAndUpdateRow("opinionfeedback/addAndUpdateHome.htm?id="+id);
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