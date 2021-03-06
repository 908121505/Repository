<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<shiro:hasPermission name="apptype:select">
    <div class="content1">
        <div class="header">
            <h1 class="page-title">app类型</h1>
            <ul class="breadcrumb">
                <li>app类型</li>
                <li class="active">app类型</li>
            </ul>
        </div>
        <div class="main-content">
            <div class="row">
                <div class="col-md-2">
                    <div class="form-group">
                        <div class="input-group">
                            <div class="input-group-addon">APP类型</div>
                            <select class="form-control" id="appTypeSelect">
                                <option value="">全部</option>
                            </select>
                        </div>
                    </div>
                </div>
               
                <div class="col-md-2">
                    <div class="form-group">
                        <div class="input-group">
                            <div class="input-group-addon">状态</div>
                            <select class="form-control" id="state">
                                <option value="1">开启</option>
                                <option value="2">关闭</option>
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
                    <shiro:hasPermission name="apptype:add">
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
            $(document).ready(function () {
            	
            	$.ajax({
					url: "apptype/getAllAppType",
					method: "post",
					data: {
						
					},
					success:function(obj){
						$.each($.parseJSON(obj), function(k, v){
							$("#appTypeSelect").append("<option  value='"+v.appTypeCode+"'>"+v.appTypeName+"</option>");
						});
					},
					error: function(){
					}
				});
            	
            	
                var table = $('#example').initTable({
                    sAjaxSource: "apptype/initTable.htm",
                    aoColumns: [
                        {
                            "data": "appTypeName",
                            "sTitle": "APP类型名称",
                            'sClass': "text-center"
                        },
                        {
                            "data": "appTypeCode",
                            "sTitle": "APP类型编码",
                            'sClass': "text-center"
                        },
                        {
                            "data": "remark",
                            "sTitle": "备注",
                            'sClass': "text-center"
                        },
                        
                        {
                            "data": "state",
                            "sTitle": "状态",
                            'sClass': "text-center",
                            "mRender": function (data, type, full) {
                                if (data == 1) {
                                    return "<font color='red'>开启</font>";
                                } else {
                                    return "<font color='blue'>关闭</font>";
                                }
                            }
                        }
                        <shiro:hasPermission name="apptype:update or apptype:delete">
                        , {"data": "appTypeId", "sTitle": "操作", 'sClass': "text-center"}
                        </shiro:hasPermission>
                    ],
                    fnServerParams: function (aoData) {  //查询条件
                    	
                        aoData.push({"name": "appTypeName", "value": $("#appTypeName").val()});
                        aoData.push({"name": "appTypeCode", "value": $("#appTypeSelect").val()});
                        aoData.push({"name": "state", "value": $("#state").val()});
                    },
                    aoColumnDefs: [
                        <shiro:hasPermission name="apptype:update or apptype:delete">
                        {
                            "aTargets": 4, "mRender": function (data, type, row) {
                            var edit = "", del = "";
                            <shiro:hasPermission name="apptype:update">
                            edit = "<a href='#' onclick='addAndUpdateRow(\"" + data + "\")' data-toggle='modal' class='padding-right-small label label-success'><i class='glyphicon glyphicon-edit'></i> 编辑</a>";
                            </shiro:hasPermission>
                            <shiro:hasPermission name="apptype:delete">
                            del = "<a href='#' onclick='deleteRow(\"" + data + "\")' data-toggle='modal' class='label label label-danger'><i class='glyphicon glyphicon-trash'></i> 删除 </a>";
                            </shiro:hasPermission>
                            return edit + "&nbsp;" + del;
                        }
                        }
                        </shiro:hasPermission>
                    ]
                });

                $('#query').click(function () {
                    $('#example').dataTable().fnDraw();
                });

            });
            //删除受影响的行数
            function deleteRow(id) {
                console.log(id);
                $('#myModal').deleteRow('apptype/del.htm?id=' + id);
            }
            //增加或者修改受影响的行数
            function addAndUpdateRow(id) {
                $('#insertAndUpdate').addAndUpdateRow("apptype/addAndUpdateHome.htm?id=" + id);
            }
        </script>
        <!---dialog选项-->
        <div>
            <jsp:include page="/WEB-INF/views/common/delete_dialog.jsp"/>
            <jsp:include page="/WEB-INF/views/common/addupdate_dialog.jsp"/>

            <div class="modal fade" id="insertAndUpdate" tabindex="-1"
                 role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
            </div>
        </div>
    </div>
</shiro:hasPermission>