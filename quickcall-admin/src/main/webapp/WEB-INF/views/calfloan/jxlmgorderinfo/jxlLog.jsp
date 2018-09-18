<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<shiro:hasPermission name="account:select">
    <div class="content1">
        <div class="header">
            <h1 class="page-title">聚信立业务流水</h1>
            <ul class="breadcrumb">
                <li>聚信立业务管理</li>
                <li class="active">聚信立业务流水信息</li>
            </ul>
        </div>
        <div class="main-content">
            <div class="row">
                <div class="col-md-2">
                    <div class="form-group">
                        <div class="input-group">
                            <div class="input-group-addon">请求名称</div>
                            <input class="form-control" type="text" id="requestName">
                        </div>
                    </div>
                </div>

                <div class="col-md-2">
                    <div class="form-group">
                        <div class="input-group">
                            <div class="input-group-addon">状态</div>
                            <select class="form-control" id="state">
                                <option value="">--所有状态--</option>
                                <option value="1">正常</option>
                                <option value="2">失败</option>
                                <option value="0">初始化</option>
                            </select>
                        </div>
                    </div>
                </div>
               
                <div class="col-md-2">
                    <div class="form-group">
                        <div class="input-group">
                            <div class="input-group-addon">创建时间</div>
                            <input class="form-control" type="date" id="sCreateTime">
                        </div>
                    </div>
                </div>
               <div class="col-md-2">
                    <div class="form-group">
                        <div class="input-group">
                            <div class="input-group-addon">至</div>
                           <input class="form-control" type="date" id="eCreateTime">
                        </div>
                    </div>
                </div>
             
             
             
                
                <div class="col-md-1">
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
                    sAjaxSource:"jxlMgOrderInfo/initTable.htm",
                    aoColumns: [
                        {
                            "data": "requestType",
                            "sTitle":"请求类型",
                            'sClass':"text-center"
                        },
                        {
                            "data": "requestName",
                            "sTitle":"请求名称",
                            'sClass':"text-center"
                        },
                        {
                            "data": "requestUrl",
                            "sTitle":"请求URL",
                            'sClass':"text-center"
                        },
                        {
                            "data": "state",
                            "sTitle":"状态",
                            'sClass':"text-center",
                            "mRender": function(data, type, full) {
                                if(data == 1){
                                    return "<font>成功</font>";
                                }else if(data == 2){
                                    return "<font color='red'>失败</font>";
                                }else if(data == 0){
                                	 return "<font color='blue'>初始化</font>";
                                }
                            }
                        },
                        {
                            "data": "responseMessage",
                            "sTitle":"响应消息",
                            'sClass':"text-center"
                        },
                        
                        {
                            "data": "responseCode",
                            "sTitle":"响应code",
                            'sClass':"text-center"
                        },
                        {
                            "data": "updateTime",
                            "sTitle":"更新时间",
                            'sClass':"text-center"
                        },
                        {
                            "data": "createTime",
                            "sTitle":"创建时间",
                            'sClass':"text-center"
                        },
                        {
                            "data": "remark",
                            "sTitle":"备注",
                            'sClass':"text-center"
                        }

                        ,{"data": "orderInfoId","sTitle":"操作",'sClass':"text-center"}

                    ],
                    fnServerParams: function (aoData) {  //查询条件
                        aoData.push({ "name": "requestName", "value": $("#requestName").val() } );
                        aoData.push({ "name": "sCreateTime", "value": $("#sCreateTime").val() } );
                        aoData.push({ "name": "eCreateTime", "value": $("#eCreateTime").val() } );
                        aoData.push({ "name": "state", "value": $("#state").val() } );
                    },
                    aoColumnDefs : [
                        {"aTargets" : 9,"mRender" : function(data, type, row){
                            var  edit="";
                            edit = "<a href='#' onclick='addAndUpdateRow(\""+data+"\")' data-toggle='modal' class='padding-right-small label label-success'><i class='glyphicon glyphicon-edit'></i> 查看详情</a>";
                            return  edit;
                            }
                        }
                    ]
                });

                $('#query').click(function(){
                    $('#example').dataTable().fnDraw();
                });

            });


          //增加或者修改受影响的行数
			function addAndUpdateRow(id){
        	 
        	  $('#insertAndUpdate').addAndUpdateRow("jxlMgOrderInfo/addAndUpdateHome.htm?id="+id);
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