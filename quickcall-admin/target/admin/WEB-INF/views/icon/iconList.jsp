<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<shiro:hasPermission name=":select">
	<div class="content1">
		<div class="header">
			<h1 class="page-title">icon列表</h1>
			<ul class="breadcrumb">
				<li>APP配置</li>
				<li class="active">icon列表</li>
			</ul>
		</div>
		<div class="main-content">
			<div class="row">
				<div class="col-md-2">
					<div class="form-group">
						<div class="input-group">
							<div class="input-group-addon">icon名</div>
							<input class="form-control" type="text" name="zoneName"
								id="zoneNameFs">
								<input type="hidden" id="zoneName" value=""/>
								
						</div>
					</div>
				</div>
				<div class="col-md-2">
					<div class="form-group">
						<div class="input-group">
							<div class="input-group-addon">类型</div>
							<select class="form-control" id="homeTypeQuery">
								<option value="">--请选择--</option>
							    <option value="6">首页</option>
								<option value="51">管家工具</option>
								<option value="52">惠花花工具</option>
								<option value="8">底部icon</option>
								<option value="9">工具新icon</option>
								<option value="10">活动（必下款）icon</option>
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
								<option value="1">开启</option>
								<option value="0">关闭</option>
							</select>
						</div>
					</div>
				</div>
				 <div class="col-md-4">
                    <div class="form-group">
                        <div class="input-group">
                            <div class="input-group-addon">有效时间</div>
                            <input class="form-control" type="date" id="sTime">
                            <div class="input-group-addon">至</div>
                            <input class="form-control" type="date" id="eTime">
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
					<shiro:hasPermission name=":add">
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
			//表格的初始化
			$(document).ready(function() {
				var table = $('#example').initTable({
					sAjaxSource:"icon/initTable.htm",
					aoColumns: [
						{ 
						    "data": "zoneName",
						    "sTitle":"icon名称",
						    'sClass':"text-center" ,
						    "mRender": function(data, type, full) { 
						    	$("#zoneName").val(data);
				            	return data;
				              } 
						},			   
			            { 
			               "data": "labelBackgroundImg",
			               "sTitle":"icon图片",
			               'sClass':"text-center",
			               "mRender": function(data, type, full) { 
				            	return "<img src='" + data + "' height='50px;'/>"; 
			              } 
			            },			               
			            { 
			                "data": "homeTypeName",
			                "sTitle":"类型",
			                'sClass':"text-center"			                
			            },
			            { 
			                "data": "jumpType",
			                "sTitle":"跳转类型",
			                'sClass':"text-center",
			                "mRender": function(data, type, full) { 
			                	if(data == 1){
									return "H5";
			                	}else if(data == 2){
									return "内页";
								}else{
									return "";
								}
			              	} 
			            },
			            /* { 
			                "data": "linkUrlH",
			                "sTitle":"链接",
			                'sClass':"text-center",
							'width':"25%"

			            }, */
                        {
                            "data": "versionCompareType",
                            "sTitle":"版本比较方式",
                            'sClass':"text-center",
                            "mRender": function(data, type, full) {
                                if(data == 0){
                                    return "所有版本";
                                }else if(data == 1){
                                    return "大于";
                                }else if(data == 2){
                                    return "小于";
                                }else if(data == 3){
                                    return "等于";
                                }else if(data == 4){
                                    return "大于等于";
                                }else if(data == 5){
                                    return "小于等于";
                                }
                            }
                        },
                        {
                            "data": "actionVersion",
                            "sTitle":"作用版本",
                            'sClass':"text-center"
                        },
                        {
                            "data": "actionDeviceType",
                            "sTitle":"作用设备类型",
                            'sClass':"text-center",
                            "mRender": function(data, type, full) {
                                if(data == 1){
                                    return "安卓";
                                }else if(data == 2){
                                    return "ios";
                                }else{
                                    return "全部";
								}
                            }
                        },
                        { 
			                "data": "startTime",
			                "sTitle":"开始时间",
			                'sClass':"text-center"
			            }, { 
			                "data": "endTime",
			                "sTitle":"结束时间",
			                'sClass':"text-center"
			            },
                        
                        
                        
			            { 
			                "data": "zoneType",
			                "sTitle":"权重",
			                'sClass':"text-center"
			            },
			            { 
			                "data": "isProduct",
			                "sTitle":"是否为商户",
			                'sClass':"text-center",
                            "mRender": function(data, type, full) {
                                if(data == 1){
                                    return "是";
                                }else{
                                    return "否";
								}
                            }
			            },
			            { 
			                "data": "remark",
			                "sTitle":"备注",
			                'sClass':"text-center"
			            },
			            { 
			                "data": "state",
			                "sTitle":"状态",
			                'sClass':"text-center",
			                "mRender": function(data, type, full) { 
			                	if(data == 1){
									return "<font color='red'>开启</font>";
								}else{
									return "<font color='blue'>关闭</font>";
								}
			                } 
			            }	
			            <shiro:hasPermission name=":update or :delete">
			            ,{"data": "zoneId","sTitle":"操作",'sClass':"text-center"}
			            </shiro:hasPermission>
			         ],
					 fnServerParams: function (aoData) {  //查询条件
		             	aoData.push({ "name": "zoneName", "value": $("#zoneNameFs").val() } );
		             	aoData.push({ "name": "state", "value": $("#state").val() } );
		             	aoData.push({ "name": "homeTypeQuery", "value": $("#homeTypeQuery").val() } );
		            	aoData.push({ "name": "sTime", "value": $("#sTime").val() } );
		            	aoData.push({ "name": "eTime", "value": $("#eTime").val() } );
					 
					 },
		             aoColumnDefs : [
						<shiro:hasPermission name=":update or :delete">
		             	{"aTargets" :13,"mRender" : function(data, type, row){
		             	   
		             		var edit="",del="",detail="";
		             		<shiro:hasPermission name=":update">
		             			edit = "<a href='#' onclick='addAndUpdateRow(\""+data+"\")' data-toggle='modal' class='padding-right-small label label-success'><i class='glyphicon glyphicon-edit'></i> 编辑</a>";
		             		</shiro:hasPermission>
		             		<shiro:hasPermission name=":delete">
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
				$('#myModal').deleteRow('icon/del.htm?id='+id);
			}
			//增加或者修改受影响的行数
			function addAndUpdateRow(id){
				$('#insertAndUpdate').addAndUpdateRow("icon/addAndUpdateHome.htm?id="+id);
			}
			
			function sunProductListRow(id,zoneName){
			    $("#zoneName").val(zoneName);
				$('#insertAndUpdate').addAndUpdateRow("icon/sunZongDetail?zoneId="+id+"&zoneName="+$("#zoneName").val());
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