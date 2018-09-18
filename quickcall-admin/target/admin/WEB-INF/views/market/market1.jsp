<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link type="text/css" href="resources/bootstrap/css/bootstrap-datetimepicker.min.css" rel="stylesheet" />
<script type="text/javascript" language="javascript" src="resources/bootstrap/js/bootstrap-datetimepicker.min.js" charset="utf-8"></script>

<style type="text/css">
    /*input css*/
    .iInput{
        position: absolute;
        width: 99px;
        height: 22px;
        left: 1px;
        top: 2px;
        border-bottom: 0px;
        border-right: 0px;
        border-left: 0px;
        border-top: 0px;
    }
</style>

	<div class="content1">
		<div class="header">
			<h1 class="page-title">市场不去重报表</h1>
			
		</div>
		<div class="main-content">
			<div class="row">
				 	<div class="col-md-4">
					<div class="form-group">
						<div class="input-group">
							<div class="input-group-addon">时间</div>
							<input class="form-control" type="text" id="sTime">
							<div class="input-group-addon">到</div>
							<input class="form-control" type="text" id="eTime">
						</div>
					</div>
				</div>

                <div class="col-md-2">
					<div class="form-group">
						<div class="input-group">
							<div class="input-group-addon">渠道</div>
                            <div style="position:relative;">
                                <div class="col-sm-8">
									<select class="form-control1" name="productId" id="productEleName" 	name="productCategoryId"
									style="LEFT: 0px; TOP: 0px; WIDTH: 135px; CLIP: rect(0px auto auto 80px); POSITION: absolute" onchange="changeValue(this);"
									>
										<%-- <c:forEach var="info" 	items="${productList }">
										  <option value="${info.productId }-${info.productName}-${info.isRecommend}">${info.productName }</option>
										</c:forEach> --%>
										<c:forEach var="info" 	items="${appChannels }">
										<option value="${info.appChannelName }">${info.appChannelName }</option>
										</c:forEach>
						
									</select>
									<INPUT id="district" onchange="inputChange(this);"   name="district" style="LEFT: 0px; TOP: 0px; WIDTH: 110px; POSITION: absolute"/>
								
                            </div>
							
						</div>
					</div>
				</div>
				</div>
				<div class="col-md-2">
					<div class="form-group">
						<div class="input-group">
							<div class="input-group-addon">按时间分类</div>
							<select class="form-control" id="pageType">
							<option value="byDate">按天</option>
							<option value="byHour">按小时</option>
							
							
							</select>
						</div>
					</div>
				</div> 
				
				<div class="col-md-2">
					<div class="form-group">
						<div class="input-group">
							<div class="input-group-addon">马甲包</div>
							<select class="form-control" id="mjPacket">
							<option value="">全部</option>
							<option value="管家">贷款管家</option>
							<option value="小牛秒借">小牛秒借</option>
							<option value="小牛速贷">小牛速贷</option>
							<option value="花花购">花花购</option>
							<option value="喵贷无忧">喵贷无忧</option>
							</select>
						</div>
					</div>
				</div> 
				
				
			
				
			
				<div class="col-md-2">
					<button type="button"  class="btn btn-primary btn-small btn-block" data-toggle="modal" data-target="#myModal"
						id="query"> 
						<i class="glyphicon glyphicon-search"></i> 查询
					</button>
				</div>
				 <div class="col-md-1">
                    <div class="form-group">
                        <a class="btn btn-primary btn-small btn-block" id="user_export" onclick="exportUserInfo();">导出</a>
                    </div>
                </div> 
				
			</div>
			
				<div class="modal fade" id="myModal">
    <div style="width: 200px;height:20px; z-index: 20000; position: absolute; text-align: center; left: 50%; top: 50%;margin-left:-100px;margin-top:-10px">
   <div class="progress progress-striped active" style="margin-bottom: 0;">
  <div class="progress-bar" style="width: 100%;"></div>
    </div>
   <h5 style="color:black"> <strong>正在加载...！</strong> </h5>
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
				 $("#myModal").modal("show");
				 $('#sTime').datetimepicker({
			            format: 'yyyy-mm-dd hh:ii:ss'
			        });
			        $('#eTime').datetimepicker({
			            format: 'yyyy-mm-dd hh:ii:ss'
			        });
				
				var table = $('#example').initTable({
					sAjaxSource:"account/queryLoginAndRegAndActiveTotal",
					
					aoColumns: [
						{ 
						    "data": "createDate",
						    "sTitle":"日期/天",
						    'sClass':"text-center",
						    "asSorting": [ "desc", "asc" ],
						    bSort:true
						  },
						  { 
							    "data": "createHour",
							    "sTitle":"日期/小时",
							    'sClass':"text-center" ,
	                            "mRender": function(data, type, full) {
	                            	 $("#myModal").modal("hide");
	                            	if(data==''||data==null){
	                                return	"--";
	                            	}else{
	                            		return data;
	                            	}
	                            }
							  },
							 
					            { 
								    "data": "appName",
								    "sTitle":"APP名称",
								    'sClass':"text-center"  ,
								    "asSorting": [ "desc", "asc" ],
								    bSort:true,
		                            "mRender": function(data, type, full) {
		                            	if(data==''||data==null){
		                                return	"--";
		                            	}else{
		                            		return data;
		                            	}
		                            }
								  },
			            { 
			                "data": "appChannelName",
			                "sTitle":"注册渠道",
			                'sClass':"text-center" ,
			                "asSorting": [ "desc", "asc" ],
						    bSort:true,
                            "mRender": function(data, type, full) {
                            	if(data==''||data==null){
                                return	"--";
                            	}else{
                            		return data;
                            	}
                            }
			               
			            },
			            { 
			                "data": "regCount",
			                "sTitle":"注册数",
			                'sClass':"text-center",
			                "asSorting": [ "desc", "asc" ],
						    bSort:true
			            },
			            { 
			                "data": "loginCount",
			                "sTitle":"登录数",
			                'sClass':"text-center",
			                "asSorting": [ "desc", "asc" ],
						    bSort:true
			                
			            }
			            
			            
			         ],
					 fnServerParams: function (aoData) {  //查询条件
		             	if ($("#sTime").val() != '') {
                            aoData.push({"name": "startTime", "value": $("#sTime").val()});
                        }
                        if ($("#eTime").val() != '') {
                            aoData.push({"name": "endTime", "value": $("#eTime").val()});
                        }
                        aoData.push({"name": "appChannelName", "value": $("#district").val()});
                        aoData.push({"name": "appName", "value": $("#mjPacket").val()});
                        aoData.push({"name": "pageType", "value": $("#pageType").val()});
                        
		             },
		             aoColumnDefs : [
		             ]
		           
		             
				});
				
				 $('#query').click(function(){
					$('#example').dataTable().fnDraw();
				});
			
			});
		
			
			function changeValue(obj){
				$("#district").val(obj.options[obj.selectedIndex].innerText);
			}

			function  inputChange(obj){
			
				var ops = document.getElementById('productEleName').options;
				var value=obj.value;
				if(value==null||value==''){
					for(var i=0; i<ops.length; i++){
						ops[i].style.display ="block";	
						
					}
					$("#district").val("");
				}
				
				for(var i=0; i<ops.length; i++){
					if(ops[i].value.indexOf(value)==-1){
						ops[i].style.display ="none";
					}
				}
			}
			
			
			 function exportUserInfo(){
	                var href="export/market1.htm"
	                        +"?appChannelName="+encodeURI($("#district").val())
	                        +"&appName="+encodeURI($("#mjPacket").val())
	                        +"&pageType="+$("#pageType").val()
	                        +"&startTime="+$("#sTime").val()
	                        +"&endTime="+$("#eTime").val();
	                $('#user_export').attr("href",href);
	            }
			
			
			
//        	$("#名称").attr("属性名","属性值");
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
