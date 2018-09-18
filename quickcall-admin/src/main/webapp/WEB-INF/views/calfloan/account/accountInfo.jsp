<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<shiro:hasPermission name="account:info:select">
    <div class="content1">
        <div class="header">
            <h1 class="page-title">客户信息列表</h1>
            <ul class="breadcrumb">
                <li>客户信息管理</li>
                <li class="active">客户信息列表</li>
            </ul>
        </div>
        <div class="main-content">
            <div class="row">
            	<div class="col-md-2">
                    <div class="form-group">
                        <div class="input-group">
                            <div class="input-group-addon">用户Id</div>
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
                            <div class="input-group-addon">身份证</div>
                            <input class="form-control" type="text" id="idCode">
                        </div>
                    </div>
                </div>
                <div class="col-md-2">
                    <div class="form-group">
                        <div class="input-group">
                            <div class="input-group-addon">用户等级分类</div>
                            <select class="form-control" id="customerLebalName">
                                <option value="">--请选择--</option>
                                <option value="DKXS">学生族</option>
                                <option value="DKSB">上班族</option>
                                <option value="DKQY">企业主</option>
                                <option value="DKZY">自由者</option>
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
            </div>
            <table id="example" class="table"></table>
        </div>
        <script>
            //表格的初始化
            $(document).ready(function () {
                var table = $('#example').initTable({
                    sAjaxSource: "account/info/initTable.htm",
                    aoColumns: [
                        {
                            "data": "name",
                            "sTitle": "用户姓名",
                            'sClass': "text-center"
                        },
                        {
                            "data": "birth",
                            "sTitle": "出生日期",
                            'sClass': "text-center"
                        },
                        {
                            "data": "addressDetail",
                            "sTitle": "详细地址",
                            'sClass': "text-center"
                        },
                        {
                            "data": "gender",
                            "sTitle": "性别",
                            'sClass': "text-center",
                            "mRender": function (data, type, full) {
                                if (data == 0) {
                                    return "<font>iPhone</font>";
                                } else if (data == 1) {
                                    return "<font>男</font>";
                                } else if (data == 2) {
                                    return "<font>女</font>";
                                }
                            }
                        },
                        {
                            "data": "email",
                            "sTitle": "邮箱",
                            'sClass': "text-center"
                        },
                        {
                            "data": "state",
                            "sTitle": "状态",
                            'sClass': "text-center",
                            "mRender": function (data, type, full) {
                                if (data == 1) {
                                    return "<font>正常</font>";
                                } else if (data == 2) {
                                    return "<font color='blue'>锁定</font>";
                                } else if (data == 3) {
                                    return "<font color='red'>冻结</font>";
                                } else {
                                    return "<font>未知</font>";
                                }
                            }
                        },
                        {
                            "data": "remark",
                            "sTitle": "备注",
                            'sClass': "text-center"
                        }
                        <shiro:hasPermission name="account:info:view">
                        , {"data": "customerId", "sTitle": "操作", 'sClass': "text-center"}
                        </shiro:hasPermission>
                    ],
                    fnServerParams: function (aoData) {  //查询条件
                        aoData.push({"name": "name", "value": $("#name").val()});
                        aoData.push({"name": "idCode", "value": $("#idCode").val()});
                        aoData.push({"name": "state", "value": $("#state").val()});
                    },
                    aoColumnDefs: [
                        <shiro:hasPermission name="account:info:view">
                        {
                            "aTargets": 7, "mRender": function (data, type, row) {
                            var edit = "", del = ""; detail="";
                            <shiro:hasPermission name="account:info:view">
                            detail = "<a href='#' onclick='detailRow(\"" + data + "\")' data-toggle='modal' class='padding-right-small label label-success'><i class='glyphicon glyphicon-edit'></i> 查看</a>";
                            </shiro:hasPermission>
                            return edit + "&nbsp;" + del+ "&nbsp;" + detail;
                        }
                        }
                        </shiro:hasPermission>
                    ]
                });

                $('#query').click(function () {
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
            <jsp:include page="/WEB-INF/views/common/delete_dialog.jsp"/>
            <jsp:include page="/WEB-INF/views/common/addupdate_dialog.jsp"/>

            <div class="modal fade" id="insertAndUpdate" tabindex="-1"
                 role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
            </div>
            
            
        </div>
    </div>
</shiro:hasPermission>