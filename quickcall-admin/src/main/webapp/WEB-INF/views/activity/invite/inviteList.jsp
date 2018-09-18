<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<shiro:hasPermission name="invite:select">
    <div class="content1">
        <div class="header">
            <h1 class="page-title">邀请关系列表</h1>
            <ul class="breadcrumb">
                <li>邀请关系管理</li>
                <li class="active">邀请关系列表</li>
            </ul>
        </div>
        <div class="main-content">
            <div class="row">
                <div class="col-md-2">
                    <div class="form-group">
                        <div class="input-group">
                            <div class="input-group-addon">邀请人手机号</div>
                            <input class="form-control" type="text" id="phoneNum">
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
            $(document).ready(function() {
                var table = $('#example').initTable({
                    sAjaxSource:"invite/initTable.htm",
                    aoColumns: [
                        {
                            "data": "inviteAccountId",
                            "sTitle":"邀请人id",
                            'sClass':"text-center"
                        },

                        {
                            "data": "phoneNum",
                            "sTitle":"邀请人手机号",
                            'sClass':"text-center"
                        },
                        {
                            "data": "refAccountId",
                            "sTitle":"被邀请人id",
                            'sClass':"text-center"
                        },
                        {
                            "data": "refPhoneNum",
                            "sTitle":"被邀请人手机号",
                            'sClass':"text-center"
                        },
                        {
                            "data": "createTime",
                            "sTitle":"邀请时间",
                            'sClass':"text-center"
                        },
                        {
                            "data": "remark",
                            "sTitle":"备注",
                            'sClass':"text-center"
                        }
                        <shiro:hasPermission name="invite:update or invite:delete">
                        ,{"data": "creditCardId","sTitle":"操作",'sClass':"text-center"}
                        </shiro:hasPermission>
                    ],
                    fnServerParams: function (aoData) {  //查询条件
                        aoData.push({ "name": "phoneNum", "value": $("#phoneNum").val() } );
                    },
                    aoColumnDefs : [
                        <shiro:hasPermission name="invite:update or invite:delete">
                        {"aTargets" :8,"mRender" : function(data, type, row){
                            var edit="",del="";
                            <shiro:hasPermission name="invite:update">
                            edit = "<a href='#' onclick='addAndUpdateRow(\""+data+"\")' data-toggle='modal' class='padding-right-small label label-success'><i class='glyphicon glyphicon-edit'></i> 编辑</a>";
                            </shiro:hasPermission>
                            <shiro:hasPermission name="invite:delete">
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
                $('#myModal').deleteRow('invite/del.htm?id='+id);
            }
            //增加或者修改受影响的行数
            function addAndUpdateRow(id){
                console.info(id);
                $('#insertAndUpdate').addAndUpdateRow("invite/addAndUpdateHome.htm?id="+id);
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