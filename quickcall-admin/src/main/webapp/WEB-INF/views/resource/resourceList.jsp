<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
	<div class="content1">
		<div class="header">
			<h1 class="page-title">资源管理后台</h1>
			<ul class="breadcrumb">
				<li>资源管理后台</li>
				<li class="active">资源池管理</li>
			</ul>
		</div>
		<div class="main-content">
			<div class="row">
				<div class="col-md-2">
					<div class="form-group">
						<div class="input-group">
							<div class="input-group-addon">资源池名称</div>
							<input class="form-control" type="text" id="resourceNameSearch">
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
					<button type="button" class="btn btn-info btn-small btn-block"
						onclick="addAndUpdateRow(0)">
						<i class="glyphicon glyphicon-plus"></i> 增加
					</button>
				</div>
			</div>

			<table id="example" class="table"></table>
		</div>
<script>

		//表格的初始化
		$(document).ready(function() {

			var table = $('#example').initTable({
				sAjaxSource:"resource/initTable.htm",
				aoColumns: [
		            { 
						  "data": "index",
						  "sTitle":"序号",
						  'sClass':"text-center",
						  "mRender": function (data, type, full, meta) {
							  return meta.row+1 + meta.settings._iDisplayStart;
						  }
		            },
		            { 
		               "data": "resourceName",
		               "sTitle":"资源池名称",
		               'sClass':"text-center",
		            },
		            { 
		                "data": "soundTotal",
		                "sTitle":"声优总量",
		                'sClass':"text-center",
		            },
                    {
                        "data": "soundOnline",
                        "sTitle":"在线声优",
                        'sClass':"text-center",
                    },
                    {
                        "data": "soundOrder",
                        "sTitle":"已接单声优",
                        'sClass':"text-center",
                    },
                    {
                        "data": "soundRemaining",
                        "sTitle":"剩余声优",
                        'sClass':"text-center",
                    },
					{
						"data" : "id",
						"sTitle" : "操作",
						'sClass' : "text-center"
					}
		         ],
		         fnServerParams: function (aoData) {  //查询条件
                       aoData.push({ "name": "resourceName", "value": $("#resourceNameSearch").val() } );
				 } ,
                 aoColumnDefs : [{
                     "aTargets" : 6,
                     "mRender": function (data, type, row) {
                         var detail = "", del = "";
                         detail = "<a href='#' onclick='addAndUpdateRow(\"" + row.id + "\")' data-toggle='modal' class='padding-right-small label label-success'><i class='glyphicon glyphicon-edit'></i>编辑</a>";
                         del = "<a href='#' onclick='deleteRow(\"" + row.id + "\",\"" + row.activeStatus + "\")' data-toggle='modal' class='label label label-danger'><i class='glyphicon glyphicon-trash'></i>删除</a>";
                         return detail + "&nbsp;" + del;
                     }
                 }]
	            
			});
			
			 $('#query').click(function(){
				$('#example').dataTable().fnDraw();
			});

		});

        //增加或者修改受影响的行数
        function addAndUpdateRow(id) {
            $('#insertAndUpdate').addAndUpdateRow("resource/addAndUpdateHome.htm?id=" + id);
        }

        function deleteRow(id,activeStatus) {
            if(activeStatus=="0"){//1=不可删除，0=可删除
            	$('#myModal').deleteRow('resource/delete.htm?id=' + id);
            }else{
                alert("资源位正在使用则,无法删除!");
			}
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
