<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<shiro:hasPermission name="product:select">
	<div class="content1">
		<div class="header">
			<h1 class="page-title">产品配置列表</h1>
			<ul class="breadcrumb">
				<li>产品管理</li>
				<li class="active">产品配置列表</li>
			</ul>
		</div>
		<div class="main-content">
			<div class="row">
			    <div class="col-md-2">
					<div class="form-group">
						<div class="input-group">
							<div class="input-group-addon">产品ID</div>
							<input class="form-control" type="text" id="productId">
						</div>
					</div>
				</div>
				<div class="col-md-2">
					<div class="form-group">
						<div class="input-group">
							<div class="input-group-addon">产品名称</div>
							<input class="form-control" type="text" id="productName">
						</div>
					</div>
				</div>
				<div class="col-md-2">
					<div class="form-group">
						<div class="input-group">
							<div class="input-group-addon">产品分类</div>
							<select class="form-control" id="productCategoryId" name="productCategoryId">
							<option value="">--全部--</option>
								<c:forEach var="info" items="${productCategorys }">
									<option value="${info.productCategoryId }">${info.categoryName }</option>
								</c:forEach>
						    </select>
						</div>
					</div>
				</div>
				<div class="col-md-2">
					<div class="form-group">
						<div class="input-group">
							<div class="input-group-addon">产品标签</div>
							<input class="form-control" type="text" id="productLabel">
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
					<div class="form-group">
						<div class="input-group">
							<div class="input-group-addon">是否推荐</div>
							<select class="form-control" id="isRecommend">
								<option value="">--全部--</option>
								<option value="1">推荐</option>
								<option value="2">不推荐</option>
							</select>
						</div>
					</div>
				</div>
				<div class="col-md-2">
					<div class="form-group">
						<div class="input-group">
							<div class="input-group-addon">排序</div>
							<select class="form-control" id="orderName">
								<option value="modifyTime">--默认排序--</option>
								<option value="sort">权重值优先</option>
								<option value="loanTime">速度快优先</option>
								<option value="loanRangeMax">金额高优先</option>
								<option value="dayRate">利率低优先</option>
								<option value="timeLimitMax">期限长优先</option>
								<option value="successRate">成功率高优先</option>
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
					<shiro:hasPermission name="product:add">
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
					sAjaxSource:"product/initTable.htm",
					aoColumns: [
			            { 
			              "data": "productName",
			              "sTitle":"产品名称",
			              'sClass':"text-center"
			            },
			            { 
				              "data": "productCategoryId",
				              "sTitle":"产品分类",
				              'sClass':"text-center"
				            },
			            { 
			               "data": "supplierId",
			               "sTitle":"供应商",
			               'sClass':"text-center"
			            },
			            { 
			                "data": "state",
			                "sTitle":"状态",
			                'sClass':"text-center",
			                "mRender": function(data, type, full) { 
			            		  if(data == null){
			            			  return "<font color='red'>--</font>";
			            	  		}if(data == 1){
										return "<font color='red'>已上线</font>";
									}else if(data == 2){
										return "<font color='red'>已下线</font>";
									}else if(data == 30){
										return "<font color='red'>待预约上线</font>";
									}else if(data == 3){
										return "<font color='red'>已预约上线</font>";
									}else if(data == 4){
										return "<font color='red'>待上线</font>";
									}else if(data == 5){
										return "<font color='red'>待下线</font>";
									}
			                } 
			            },
			            { 
			                "data": "isRecommend",
			                "sTitle":"是否推荐",
			                'sClass':"text-center",
			                "mRender": function(data, type, full) { 
			                	if(data == 1){
									return "<font color='red'>推荐</font>";
								}else{
									return "<font color='blue'>不推荐</font>";
								}
			                } 
			            },
			            { 
				               "data": "sort",
				               "sTitle":"权重值",
				               'sClass':"text-center"
				            },
			            { 
				               "data": "applyNum",
				               "sTitle":"申请数量",
				               'sClass':"text-center"
				            },
			            
			            { 
			                "data": "dayRepayment",
			                "sTitle":"日还款",
			                'sClass':"text-center"
			            },
			            { 
			                "data": "allAccrual",
			                "sTitle":"总利息",
			                'sClass':"text-center"
			            },
			            { 
			                "data": "loanRangeMin",
			                "sTitle":"最小额度",
			                'sClass':"text-center"
			            },
			            { 
			                "data": "loanRangeMax",
			                "sTitle":"最大额度",
			                'sClass':"text-center"
			            },
			            { 
			                "data": "timeLimitMint",
			                "sTitle":"最小期限",
			                'sClass':"text-center",
			                 "mRender": function(data, type, full) { 
			                	if(full.timeLimitType == 1){
									return full.timeLimitMint+"天";
								}else if(full.timeLimitType == 2){
									return full.timeLimitMint+"个月";
								}else{
									return data;
								 }
				               }  
			            },
			            { 
			                "data": "timeLimitMax",
			                "sTitle":"最大期限",
			                'sClass':"text-center",
			                 "mRender": function(data, type, full) { 
			                	if(full.timeLimitType == 1){
									return full.timeLimitMax+"天";
								}else if(full.timeLimitType == 2){
									return full.timeLimitMax+"个月";
								}else{
									return data;
								 }	
				               }  
			            },
			            { 
			                "data": "successRate",
			                "sTitle":"成功率",
			                'sClass':"text-center",
			                "mRender": function(data, type, full) { 
			                	  if(data!=null && data!=''){
			                		  return (data*100).toFixed(2)+"%";
			                	  }else{
			                		  return "0%";
			                	  }
			                }
			            },
			            { 
			                "data": "dayRate",
			                "sTitle":"利率",
			                'sClass':"text-center",
			                "mRender": function(data, type, full) { 
			                	if(full.rateType == 1){
									return "日利率:"+(full.dayRate*100).toFixed(2)+"%";
								}else if(full.rateType == 2){
									return "月利率:"+(full.dayRate*100).toFixed(2)+"%";
								}else if(full.rateType == 3){
									return "年利率:"+(full.dayRate*100).toFixed(2)+"%";
								}else{
									return "0%";
								}	
				               } 
			            },
			            { 
			                "data": "serviceRate",
			                "sTitle":"服务费率",
			                'sClass':"text-center",
			                "mRender": function(data, type, full) { 
			                	  if(data!=null && data!=''){
			                		  return (data*100).toFixed(2)+"%";
			                	  }else{
			                		  return "0%";
			                	  }
			                }
			            },
			            { 
			                "data": "loanTime",
			                "sTitle":"放款时间(小时)",
			                'sClass':"text-center"
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
			            <shiro:hasPermission name="product:update or product:delete">
			            ,{"data": "modifyTime","sTitle":"操作",'sClass':"text-center"}
			            </shiro:hasPermission>
			         ],
					 fnServerParams: function (aoData) {  //查询条件
						 aoData.push({ "name": "productId", "value": $("#productId").val() } );
		             	aoData.push({ "name": "productName", "value": $("#productName").val() } );
		             	aoData.push({ "name": "productCategoryId", "value": $("#productCategoryId").val() } );
		             	aoData.push({ "name": "state", "value": $("#state").val() } );
		             	aoData.push({ "name": "productLabel", "value": $("#productLabel").val() } );
		             	aoData.push({ "name": "isRecommend", "value": $("#isRecommend").val() } );
		            	aoData.push({ "name": "orderName", "value": $("#orderName").val() } );
		             },
		             aoColumnDefs : [
						<shiro:hasPermission name="product:update or product:delete">
		             	{"aTargets" :21,"mRender" : function(data, type, row){
		             		var edit="",del="";
		             		<shiro:hasPermission name="product:update">
		             			edit = "<a href='#' onclick='addAndUpdateRow(\""+row.productId+"\")' data-toggle='modal' class='padding-right-small label label-success'><i class='glyphicon glyphicon-edit'></i> 编辑</a>";
		             		</shiro:hasPermission>
		             		/* <shiro:hasPermission name="product:delete">
		             			del ="<a href='#' onclick='deleteRow(\""+row.productId+"\")' data-toggle='modal' class='label label label-danger'><i class='glyphicon glyphicon-trash'></i> 删除 </a>";
		             		</shiro:hasPermission> */
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