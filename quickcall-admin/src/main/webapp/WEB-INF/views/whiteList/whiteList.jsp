<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>

<shiro:hasPermission name="bizparam:select">
    <div class="content1">
        <div class="header">
            <h1 class="page-title">白名单配置</h1>
           
        </div>
        <div class="main-content">
            <div class="row">
                <div class="col-md-2">
                    <div class="form-group">
                        <div class="input-group">
                            <div class="input-group-addon">手机号</div>
                            
                            <input class="form-control" id="phoneNum1" />
                        </div>
                    </div>
                </div>
                <div class="col-md-2">
                    <div class="form-group">
                        <div class="input-group">
                            <div class="input-group-addon">类型</div>
                            
                            <select class="form-control" id="type1">
                            <option value="">全部</option>
                            <option value="1">登录白名单</option>
                            <option value="2">产品白名单</option>
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
                    <shiro:hasPermission name="bizparam:add">
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
                var table = $('#example').initTable({
                    sAjaxSource: "whiteList/initTable.htm",
                    aoColumns: [
                        {
                            "data": "phoneNum",
                            "sTitle": "手机号",
                            'sClass': "text-center"
                        },
                        {
                            "data": "nickName",
                            "sTitle": "姓名",
                            'sClass': "text-center"
                        },
                        {
                            "data": "type",
                            "sTitle": "类型",
                            'sClass': "text-center",
                            "mRender": function (data, type, full) {
                                if (data == 1) {
                                    return "登录白名单";
                                } else {
                                    return "产品白名单";
                                }
                            }
                        },
                        {
                            "data": "modifyMan",
                            "sTitle": "操作人",
                            'sClass': "text-center"
                        },
                        {
                            "data": "modifyTime",
                            "sTitle": "操作时间",
                            'sClass': "text-center"
                        }
                       
                        
                        <shiro:hasPermission name="whiteList:update or whiteList:delete">
                        , {"data": "id", "sTitle": "操作", 'sClass': "text-center"}
                        </shiro:hasPermission>
                    ],
                    fnServerParams: function (aoData) {  //查询条件
                        aoData.push({"name": "phoneNum", "value": $("#phoneNum1").val()});
                        aoData.push({"name": "type", "value": $("#type1").val()});
                    },
                    aoColumnDefs: [
                        <shiro:hasPermission name="whiteList:update or whiteList:delete">
                        {
                            "aTargets": 5, "mRender": function (data, type, row) {
                            var edit = "", del = "";
                            <shiro:hasPermission name="whiteList:update">
                            edit = "<a href='#' onclick='addAndUpdateRow(\"" + data + "\")' data-toggle='modal' class='padding-right-small label label-success'><i class='glyphicon glyphicon-edit'></i> 编辑</a>";
                            </shiro:hasPermission>
                            <shiro:hasPermission name="whiteList:delete">
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
                $('#myModal').deleteRow('whiteList/del.htm?id=' + id);
            }
            //增加或者修改受影响的行数
            function addAndUpdateRow(id) {
                $('#insertAndUpdate').addAndUpdateRow("whiteList/addAndUpdateHome.htm?id=" + id);
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