<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<shiro:hasPermission name="userLabel:select">
	<div class="content1">
		<div class="header">
			<ul class="breadcrumb">
				<li>用户标签分类信息管理</li>
			</ul>
		</div>
		        <div class="main-content">
            <div class="row">
                <div class="col-md-2">
                    <div class="form-group">
                        <div class="input-group">
                            <div class="input-group-addon">用户ID</div>
                            <input class="form-control" type="text" id="accountId">
                        </div>
                    </div>
                </div>
                 <div class="col-md-2">
                    <div class="form-group">
                        <div class="input-group">
                            <div class="input-group-addon">电话号码</div>
                            <input class="form-control" type="text" id="phoneNum">
                        </div>
                    </div>
                </div>
                
                <div class="col-md-2">
                    <div class="form-group">
                        <div class="input-group">
                            <div class="input-group-addon">身份证号码</div>
                            <input class="form-control" type="text" id="idCode">
                        </div>
                    </div>
                </div>
                <div class="col-md-2">
                    <div class="form-group">
                        <div class="input-group">
                            <div class="input-group-addon">所在地区</div>
                            <input class="form-control" type="text" id="region">
                        </div>
                    </div>
                </div>
                <div class="col-md-2">
                    <div class="form-group">
                        <div class="input-group">
                            <div class="input-group-addon">用户等级分类</div>
                            <select class="form-control" id="customerLebalName">
                                <!-- <option value="">--请选择--</option>
                                <option value="1">不通过</option>
                                <option value="2">通过</option>
                                <option value="3">已放款</option> -->
                                <option value="">--请选择--</option>
                                <c:forEach items="${productLabels}" var="ii">
                                	<option value='${ii.labelName}' > ${ii.labelName} </option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                </div>
                <div class="col-md-2">
                    <div class="form-group">
                        <div class="input-group">
                            <div class="input-group-addon">状态</div>
                            <select class="form-control" id="state">
                                <option value="">--请选择--</option>
                                <option value="1">不通过</option>
                                <option value="2">通过</option>
                                <option value="3">已放款</option>
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
                <div class="col-md-1">	
					<div class="form-group">
					<a class="btn btn-primary btn-small btn-block" id="message_export" onclick="exportMessage();">导出</a>
<!-- 						<button type="button" class="btn btn-primary btn-small btn-block" onclick="exportMessage();"><i class="glyphicon glyphicon-search"></i>导出Excel</button> -->
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
		function exportMessage(){
			
			var href="userInfo/label/exportInfo.htm"
					+"?accountId="+$("#accountId").val()
                    +"&customerLebalName="+$("#customerLebalName").val();		
                    +"&state="+$("#state").val();		
					+"&phoneNum="+$("#phoneNum").val();
					+"&idCode="+$("#idCode").val();
					+"&region="+$("#region").val();
			            
			$('#message_export').attr("href",href);
		}
			//表格的初始化
			$(document).ready(function() {
				var table = $('#example').initTable({
					sAjaxSource:"userInfo/label/initTable.htm",
					aoColumns: [
			            {
			              		"data": "accountId",
			              		"sTitle":"用户ID",
			              		'sClass':"text-center"
			            },
			            { 
				           	    "data": "name",
				            	"sTitle":"姓名",
				            	'sClass':"text-center"
				        },
			            { 
			               		"data": "phoneNum",
			               		"sTitle":"电话号码",
			               		'sClass':"text-center"
			            },
			            { 
				               "data": "idCode",
				               "sTitle":"身份证号码",
				               'sClass':"text-center"
				        },    
			            { 
				               "data": "region",
				               "sTitle":"所在地区",
				               'sClass':"text-center"
				        },
				        { 
				               "data": "customerLebalName",
				               "sTitle":"用户标签",
				               'sClass':"text-center"
				        },
				        { 
				               "data": "tjTime",
				               "sTitle":"推荐时间",
				               'sClass':"text-center",
				               "mRender": function(data, type, full) { 
					                  return	data==''||data==null?null:Format(new Date(data),"yyyy-MM-dd HH:mm:ss")
					                }
				        },
				        { 
				               "data": "tjZoneName",
				               "sTitle":"推荐专区",
				               'sClass':"text-center"
				        },
			            { 
			                   "data": "platformBackState",
			                   "sTitle":"平台反馈状态",
			                   'sClass':"text-center"
			                
			            }
			            <shiro:hasPermission name="userInfo:view">
			            ,{"data": "labelBackgroundImg","sTitle":"操作",'sClass':"text-center"}
			            </shiro:hasPermission>
			         ],
					 fnServerParams: function (aoData) {  //查询条件
						 	aoData.push({"name": "accountId", "value": $("#accountId").val()});
						    aoData.push({"name": "phoneNum", "value": $("#phoneNum").val()});
	                        aoData.push({"name": "idCode", "value": $("#idCode").val()});
	                        aoData.push({"name": "region", "value": $("#region").val()});
	                        aoData.push({"name": "customerLebalName", "value": $("#customerLebalName").val()});
	                        aoData.push({"name": "state", "value": $("#state").val()});
	                        
		             },
		             aoColumnDefs : [
		                             <shiro:hasPermission name="userInfo:view">
		                             {
		                                 "aTargets": 9, "mRender": function (data, type, row) {
		                                 var detail="";
		                                 <shiro:hasPermission name="userInfo:view">
		                                 detail = "<a href='#' onclick='detailRow(\"" + row.detailInfoId + "\")' data-toggle='modal' class='padding-right-small label label-success'><i class='glyphicon glyphicon-edit'></i> 查看</a>";
		                                 </shiro:hasPermission>
		                                 return detail;
		                             }
		                             }
		                             </shiro:hasPermission>
		             ]
				});
				
				 $('#query').click(function(){
					$('#example').dataTable().fnDraw();
				});
			
			});
			//增加或者修改受影响的行数
			function detailRow(id){

				$('#insertAndUpdate').addAndUpdateRow("account/info/viewInfoById.htm?id="+id);
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