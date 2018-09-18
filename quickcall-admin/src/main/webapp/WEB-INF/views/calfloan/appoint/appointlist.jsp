<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<shiro:hasPermission name="product:select">
	<div class="content1">
		<div class="header">
			<h1 class="page-title">商务后台</h1>
			<ul class="breadcrumb">
				<li>商务后台</li>
				<li class="active">产品预约上线列表</li>
			</ul>
		</div>
		<div class="main-content">
			<div class="row">
			    <div class="col-md-2" style = "width:23%;">
					<div class="form-group">
						<div class="input-group">
							<div class="input-group-addon">产品ID</div>
							<input class="form-control" type="text" id="productId" placeholder="请输入产品ID">
						</div>
					</div>
				</div>
				<div class="col-md-2" style = "width:16%;">
					<div class="form-group">
						<div class="input-group">
							<div class="input-group-addon">商户名称</div>
							<input class="form-control" type="text" id="productName" placeholder="请输入商户名称">
						</div>
					</div>
				</div>
				
				<div class="col-md-2" style="padding-right: 0px;width:16%;">
                    <div class="form-group">
                        <div class="input-group">
                            <div class="input-group-addon">预约时间</div>
                            <input class="form-control" type="date" id="startTime" >
                        </div>
                    </div>
                </div>
				<div class="col-md-2"  style = "width:2%;padding-right: 0px;padding-left: 0px;height: 34px;">
                        <div class="input-group"  style ="height: 34px;">
                            <div class="input-group-addon">到</div>
                        </div>
                </div>
              <div class="col-md-2" style="padding-left: 0px;width: 10%;">
                    <div class="form-group">
                        <div class="input-group">
                            <input class="form-control" type="date" id="endTime">
                        </div>
                    </div>
                </div>
				
				<div class="col-md-2"  style = "width:12%;">
					<div class="form-group">
						<div class="input-group">
							<div class="input-group-addon">场次</div>
							<select class="form-control" id="seasonType">
								<option value="">--全部--</option>
								<option value="1">10:00</option>
								<option value="2">14:00</option>
								<option value="3">16:00</option>
								<option value="4">20:00</option>
							</select>
						</div>
					</div>
				</div>
				<div class="col-md-2"  style = "width:8%;">
					<button type="button" class="btn btn-primary btn-small btn-block"
						id="query">
						<i class="glyphicon glyphicon-search"></i> 查询
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
					sAjaxSource:"appoint/initTable.htm",
					aoColumns: [
			            { 
			              "data": "orderTime",
			              "sTitle":"日期",
			              'sClass':"text-center"
			            },
			            { 
				              "data": "seasonType",
				              "sTitle":"场次",
				              'sClass':"text-center",
			            	  "mRender": function(data, type, full) { 
				                	if(data == 1){
										return "<font color='red'>10:00</font>";
									}else if(data == 2){
										return "<font color='red'>14:00</font>";
									}else if(data == 3){
										return "<font color='red'>16:00</font>";
									}else if(data == 4){
										return "<font color='red'>20:00</font>";
									}
									
									
				                } 
				            },
			            { 
			               "data": "prodId",
			               "sTitle":"产品ID",
			               'sClass':"text-center"
			            },
			            { 
			               "data": "prodName",
			               "sTitle":"产品名称",
			               'sClass':"text-center"
			            },
			            { 
			                "data": "prodCat",
			                "sTitle":"产品分类",
			                'sClass':"text-center",
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
			                "sTitle":"修改人",
			                'sClass':"text-center"
			            }
			            /* ,{
			            	"data": "id",
			            	"sTitle":"操作",
			            	'sClass':"text-center"
			             } */
			            
			         ],
					 fnServerParams: function (aoData) {  //查询条件
						 aoData.push({ "name": "productId", "value": $("#productId").val() } );
		             	aoData.push({ "name": "productName", "value": $("#productName").val() } );
		             	aoData.push({ "name": "startTime", "value": $("#startTime").val() } );
		             	aoData.push({ "name": "endTime", "value": $("#endTime").val() } );
		             	aoData.push({ "name": "seasonType", "value": $("#seasonType").val() } );
		             },
		             
				});
				
				 $('#query').click(function(){
					$('#example').dataTable().fnDraw();
				});
			
			});
			//删除受影响的行数
			function deleteRow(id){
				console.log(id);
				$('#myModal').deleteRow('product/del.htm?id='+id);
			}
			//增加或者修改受影响的行数
			function addAndUpdateRow(id){
				$('#insertAndUpdate').addAndUpdateRow("product/addAndUpdateHome.htm?id="+id);
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