<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
	<div class="content1">
		<div class="header">
			<h1 class="page-title">设备白名单管理</h1>
			<ul class="breadcrumb">
				<li>用户管理</li>
				<li class="active">设备白名单管理</li>
			</ul>
		</div>
		<div class="main-content">
			<div class="row">
				<div class="col-md-4">
					<div class="form-group">
						<div class="input-group">
							<div class="input-group-addon">用户ID/设备ID/手机号</div>
							<input class="form-control" type="text" id="searchStr" >
						</div>
					</div>
				</div>

				<div class="col-md-2">
					<button type="button" class="btn btn-primary btn-small btn-block"
						id="query">
						<i class="glyphicon glyphicon-search"></i> 查询
					</button>
				</div>
				<%--<div class="col-md-2">
					<button type="button" class="btn btn-info btn-small btn-block"
						onclick="addAndUpdateRow(0)">
						<i class="glyphicon glyphicon-plus"></i> 增加
					</button>
				</div>--%>
			</div>

			<table id="example" class="table"></table>
		</div>
<script>

		//表格的初始化
		$(document).ready(function() {

			var table = $('#example').initTable({
				sAjaxSource:"customerDeviceWhitelist/initTable.htm",
				aoColumns: [
                    {
                        "data": "index",
                        "sTitle": "序号",
                        'sClass': "text-center",
                        "mRender": function (data, type, full, meta) {
                            return meta.row+1 + meta.settings._iDisplayStart;
                        }
                    },
		            {
                        "data": "customerId",
                        "sTitle":"用户ID",
                        'sClass':"text-center",
		            },
		            { 
		               "data": "phone",
		               "sTitle":"手机号",
		               'sClass':"text-center",
		            },
		            { 
		                "data": "deviceId",
		                "sTitle":"设备ID",
		                'sClass':"text-center",
		            },
                    {
                        "data": "deviceType",
                        "sTitle":"设备型号",
                        'sClass':"text-center",
                    },
                    {
                        "data": "appId",
                        "sTitle":"APPID",
                        'sClass':"text-center",
                    },
                    {
                        "data": "nickName",
                        "sTitle":"用户昵称",
                        'sClass':"text-center",
                    },
					{
						"data" : "id",
						"sTitle" : "操作",
						'sClass' : "text-center"
					}
		         ],
		         fnServerParams: function (aoData) {  //查询条件
                       aoData.push({ "name": "searchStr", "value": $("#searchStr").val() } );
				 } ,
                 aoColumnDefs : [{
                     "aTargets" : 7,
                     "mRender": function (data, type, row) {
                         var del = "";
                         del = "<a href='#' onclick='deleteRow(\"" + row.id + "\")' data-toggle='modal' class='label label label-danger'><i class='glyphicon glyphicon-trash'></i>删除</a>";
                         return del;
                     }
                 }]
	            
			});
			
			 $('#query').click(function(){
				$('#example').dataTable().fnDraw();
			});

		});

        //增加或者修改受影响的行数
        /*function addAndUpdateRow(id) {
            $('#insertAndUpdate').addAndUpdateRow("...me.htm?id=" + id);
        }*/

        function deleteRow(id) {
			$('#myModal').deleteRow('customerDeviceWhitelist/delete.htm?id=' + id);
        }


</script>
		<!---dialog选项-->
		<div>
			<jsp:include page="/WEB-INF/views/common/delete_dialog.jsp" />
			<jsp:include page="/WEB-INF/views/common/addupdate_dialog.jsp"/>

			<div class="modal fade" id="insertAndUpdate" tabindex="-1"
				 role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static">
			</div>
		</div>
	</div>
