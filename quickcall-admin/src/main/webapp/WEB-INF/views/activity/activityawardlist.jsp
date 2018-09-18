<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<shiro:hasPermission name="activityAward:select">
	<div class="content1">
		<div class="header">
			<h1 class="page-title">活动奖励列表</h1>
			<ul class="breadcrumb">
				<li>活动奖励管理</li>
				<li class="active">活动奖励列表</li>
			</ul>
		</div>
		<div class="main-content">
			<div class="row">
				<div class="col-md-2">
					<div class="form-group">
						<div class="input-group">
							<div class="input-group-addon">中奖手机号</div>
							<input class="form-control" type="text" id="phoneNum">
						</div>
					</div>
				</div>
				
				 <div class="col-md-4">
                    <div class="form-group">
                        <div class="input-group">
                            <div class="input-group-addon">时间</div>
                            <input class="form-control" type="date" id="startTime">
                            <div class="input-group-addon">到</div>
                            <input class="form-control" type="date" id="endTime">
                        </div>
                    </div>
                </div>
				<div class="col-md-2">
					<div class="form-group">
						<div class="input-group">
							<div class="input-group-addon">状态</div>
							<select class="form-control" id="state">
								<option value="">--全部--</option>
								<option value="1">未领取</option>
								<option value="2">已领取</option>
								<option value="3">已发放</option>
								<option value="4">已过期</option>
							</select>
						</div>
					</div>
				</div>
				<div class="col-md-2">
					<div class="form-group">
						<div class="input-group">
							<div class="input-group-addon">所属活动</div>
							<select class="form-control" id="activityId" name="activityId">
							<option value="">--全部--</option>
								<c:forEach var="info" items="${activitys }">
									<option value="${info.activityId }">${info.title }</option>
								</c:forEach>
						    </select>
						</div>
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
					<shiro:hasPermission name="activityAward:add">
						<button type="button" class="btn btn-info btn-small btn-block"
							onclick="addAndUpdateRow('')">
							<i class="glyphicon glyphicon-plus"></i> 增加
						</button>
					</shiro:hasPermission>
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
					sAjaxSource:"activityaward/initTable.htm",
					aoColumns: [
			            { 
			              "data": "phoneNum",
			              "sTitle":"中奖手机号",
			              'sClass':"text-center"
			            },
			            { 
				              "data": "activityPrizeName",
				              "sTitle":"奖品名称",
				              'sClass':"text-center"
				         },
			            { 
			               "data": "activityPrizePrice",
			               "sTitle":"奖品价格",
			               'sClass':"text-center",
			               "mRender": function(data, type, full) { 
			                	if(full.activityPrizeName == '流量'){
									return full.activityPrizePrice==null ? "--":full.activityPrizePrice+"M";
								}else{
									return full.activityPrizePrice==null ? "--":full.activityPrizePrice+"元";
								 }
				               }  
			            },
			            { 
				              "data": "activityTitle",
				              "sTitle":"所属活动",
				              'sClass':"text-center"
				         },
			            { 
				               "data": "refPhoneNum",
				               "sTitle":"被邀请用户手机号",
				               'sClass':"text-center"
				        },
			           
				        { 
			                "data": "state",
			                "sTitle":"状态",
			                'sClass':"text-center",
			                "mRender": function(data, type, full) { 
			                	if(data == 1){
									return "<font color='red'>未领取</font>";
								}else if(data == 2){
									return "<font color='blue'>已领取</font>";
								}else if(data == 3){
									return "<font color='green'>已发放</font>";
								}else if(data == 4){
									return "<font color='gray'>已过期</font>";
								}else{
									return "<font color='black'>未知</font>";
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
			            }
			            <shiro:hasPermission name="activityAward:update">
			            ,{"data": "activityAwardId","sTitle":"操作",'sClass':"text-center"}
			            </shiro:hasPermission>
			         ],
					 fnServerParams: function (aoData) {  //查询条件
		             	aoData.push({ "name": "phoneNum", "value": $("#phoneNum").val() } );
		            	aoData.push({ "name": "activityTitle", "value": $("#activityTitle").val() } );
		             	aoData.push({ "name": "state", "value": $("#state").val() } );
		             	aoData.push({ "name": "startTime", "value": $("#startTime").val() } );
		             	aoData.push({ "name": "endTime", "value": $("#endTime").val() } );
		             	aoData.push({ "name": "activityId", "value": $("#activityId").val() } );
		             	activityId
		             },
		             aoColumnDefs : [
		     						<shiro:hasPermission name="activityAward:update">
		     		             	{"aTargets" :10,"mRender" : function(data, type, row){
		     		             		var  del="";
		     		             		<shiro:hasPermission name="activityAward:update">
		     		             		     if(row.state==2||row.state==1){
		     		             			       del ="<a href='#' onclick='updateRow(\""+data+"\")' data-toggle='modal' class='label label label-danger'>已发放 </a>";
		     		             	              }else{
		     		             	            	  del="--";
		     		             	              }
		     		             			</shiro:hasPermission>
		     		                    return del;
		     		                }
		     		             }
		     		            	</shiro:hasPermission>
		     		             ]
				});
				
				 $('#query').click(function(){
					$('#example').dataTable().fnDraw();
				});
			
			});
			//更新受影响的行数
			function updateRow(id){
				console.log(id);
				$('#myModal').deleteRow('activityaward/del.htm?id='+id);
			}
			//增加或者修改受影响的行数
			function addAndUpdateRow(id){
				$('#insertAndUpdate').addAndUpdateRow("activityaward/addAndUpdateHome.htm?id="+id);
			}
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