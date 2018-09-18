<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<shiro:hasPermission name="log:select">
<div class="content1">
	<div class="header">
		<h1 class="page-title">日志列表</h1>
		<ul class="breadcrumb">
			<li>系统管理</li>
			<li class="active">日志列表</li>
		</ul>
	</div>
		<div class="main-content">
		<div class="row">
			<div class="col-md-4">
				<div class="form-group">
					<div class="input-group">
						<div class="input-group-addon">操作类型</div>
						<input class="form-control" type="text" id="operType">
					</div>
				</div>
			</div>
			<div class="col-md-2">
				<button type="button" class="btn btn-primary btn-small btn-block" id="query"><i class="glyphicon glyphicon-search"></i> 查询</button>
			</div>
		</div>
		<table id="example" class="table"></table>
	</div>
	<script>
	//表格的初始化
	$(document).ready(function() {
		var table = $('#example').initTable({
			sAjaxSource:"system/log/initTable.htm",
			aoColumns: [
	            { "data": "account","sTitle":"操作人账号" },
	            { "data": "oper_type","sTitle":"操作类型" },
	            { "data": "oper_url","sTitle":"访问地址"},
	            { "data": "ip_addr","sTitle":"IP地址"},
	            { "data": "create_time","sTitle":"请求时间"}
	            <shiro:hasPermission name="log:detail or log:delete">
	            ,{ "data": "id","sTitle":"操作",'sClass':"text-center"}
	            </shiro:hasPermission>
	         ],
			 fnServerParams: function (aoData) {  //查询条件
             	aoData.push({ "name": "oper_type", "value": $("#operType").val() } );
             },
             aoColumnDefs : [
				<shiro:hasPermission name="log:detail or log:delete">
             	{"aTargets" :5,"mRender" : function(data, type, full){
             		var edit = "",del = "";
             		<shiro:hasPermission name="log:detail">
             			edit = "<a href='#' onclick='addAndUpdateRow("+data+")' data-toggle='modal' class='padding-right-small label label-success'><i class='fa fa-list'></i> 详细信息</a>";
             		</shiro:hasPermission>
             		<shiro:hasPermission name="log:delete">
             			del ="<a href='#' onclick='deleteRow("+data+")' data-toggle='modal' class='label label label-danger'><i class='glyphicon glyphicon-trash'></i> 删除 </a>";
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
		$('#myModal').deleteRow('system/log/delete.htm?id='+id);
	}
	//增加或者修改受影响的行数
	function addAndUpdateRow(id){
		$('#insertAndUpdate').addAndUpdateRow("system/log/addAndUpdateHome.htm?id="+id);
	}
	</script>
	<!---dialog选项-->
	<div>
		<jsp:include page="/WEB-INF/views/common/delete_dialog.jsp" />
	  	<jsp:include page="/WEB-INF/views/common/addupdate_dialog.jsp" />
	   </div>
</div>
</shiro:hasPermission>