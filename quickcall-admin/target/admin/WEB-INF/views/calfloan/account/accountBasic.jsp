<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<shiro:hasPermission name="account:basic:select">
    <div class="content1">
        <div class="header">
            <h1 class="page-title">用户基本信息</h1>
            <ul class="breadcrumb">
                <li>用户管理</li>
                <li class="active">用户基本信息</li>
            </ul>
        </div>
        <div class="main-content">
            <div class="row">
            	<div class="col-md-2">
                    <div class="form-group">
                        <div class="input-group">
                            <div class="input-group-addon">用户Id</div>
                            <input class="form-control" type="text" id="accountId"  name="accountId">
                        </div>
                    </div>
                </div>
                <div class="col-md-2">
                    <div class="form-group">
                        <div class="input-group">
                            <div class="input-group-addon">用户姓名</div>
                            <input class="form-control" type="text" id="accountName"  name="accountName">
                        </div>
                    </div>
                </div>
              
                 <div class="col-md-2">
                    <div class="form-group">
                        <div class="input-group">
                            <div class="input-group-addon">手机号码</div>
                            <input class="form-control" type="text" id="phoneNum"   name="phoneNum">
                        </div>
                    </div>
                </div>
                
                  <div class="col-md-2">
                    <div class="form-group">
                        <div class="input-group">
                            <div class="input-group-addon">身份证号码</div>
                            <input class="form-control" type="text" id="idCode"   name="idCode">
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
                    sAjaxSource: "account/basic/initTable.htm",
                    aoColumns: [
                        {
                            "data": "accountId",
                            "sTitle": "用户ID",
                            'sClass': "text-center"
                        },
                        {
                            "data": "phoneNum",
                            "sTitle": "手机号码",
                            'sClass': "text-center"
                        },
                        {
                            "data": "accountName",
                            "sTitle": "用户姓名",
                            'sClass': "text-center"
                        },
                        {
                            "data": "idCode",
                            "sTitle": "身份证号码",
                            'sClass': "text-center"
                        },
                        
                        {
                            "data": "registerTime",
                            "sTitle": "注册时间",
                            'sClass': "text-center"
                        },
                        {
                            "data": "gender",
                            "sTitle": "性别",
                            'sClass': "text-center",
                            "mRender": function (data, type, full) {
                                if (data == 1) {
                                    return "<font>男</font>";
                                } else if (data == 2) {
                                    return "<font>女</font>";
                                }else{
                                	 return "<font></font>";
                                }
                            }
                        }
                        <shiro:hasPermission name="account:basic:update or account:basic:delete">
                        , {"data": "accountId", "sTitle": "操作", 'sClass': "text-center"}
                        </shiro:hasPermission>
                    ],
                    fnServerParams: function (aoData) {  //查询条件
                         aoData.push({"name": "accountId","value": $("#accountId").val()});
                        aoData.push({"name": "accountName","value": $("#accountName").val()});
                        aoData.push({"name": "phoneNum", "value": $("#phoneNum").val()});
                        aoData.push({"name": "idCode", "value": $("#idCode").val()});  
                    },
                    aoColumnDefs: [
                        <shiro:hasPermission name="account:basic:update or account:basic:delete">
                        {
                            "aTargets": 6, "mRender": function (data, type, row) {
                            var edit="";
                            <shiro:hasPermission name="account:basic:update">
                            edit = "<a href='#' onclick='addAndUpdateRow(\""+data+"\")' data-toggle='modal' class='padding-right-small label label-success'><i class='glyphicon glyphicon-edit'></i> 查看详情</a>";
                           /*  detail = "<a href='#' onclick='detailRow(\"" + data + "\")' data-toggle='modal' class='padding-right-small label label-success'><i class='glyphicon glyphicon-edit'></i> 查看详情</a>"; */
                            </shiro:hasPermission>
                            return  edit;
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
            function addAndUpdateRow(id){
                $('#insertAndUpdate').addAndUpdateRow("account/basic/addAndUpdateHome.htm?id="+id);
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