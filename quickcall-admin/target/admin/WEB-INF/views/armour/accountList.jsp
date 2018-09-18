<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<shiro:hasPermission name="account:select">
    <div class="content1">
        <div class="header">
            <h1 class="page-title">马甲号配置</h1>
            <ul class="breadcrumb">
                <li>用户管理</li>
                <li class="active">马甲号配置</li>
            </ul>
        </div>
        <div class="main-content">
            <div class="row">
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
                            <div class="input-group-addon">状态</div>
                            <select class="form-control" id="state">
                                <option value="">--所有状态--</option>
                                <option value="1">正常</option>
                                <option value="2">关闭</option>
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
                <div class="col-md-1">
					<shiro:hasPermission name="account:update">
						<button type="button" class="btn btn-info btn-small btn-block"
							onclick="addAndUpdateRow('')">
							<i class="glyphicon glyphicon-plus"></i> 增加
						</button>
					</shiro:hasPermission>
				</div>
                <div class="col-md-1">
                    <div class="form-group">
                        <a id="armour_export" class="btn btn-primary btn-small btn-block" onclick="exportArmour();">导出马甲信息</a>
                    </div>
                </div>
               
            </div>
            <table id="example" class="table"></table>
        </div>
        <script>
            //表格的初始化
            $(document).ready(function() {
                var table = $('#example').initTable({
                    sAjaxSource:"armourAccount/initTable.htm",
                    aoColumns: [
                        {
                            "data": "phoneNum",
                            "sTitle":"电话号码",
                            'sClass':"text-center"
                        },
                        {
                            "data": "nickname",
                            "sTitle":"昵称",
                            'sClass':"text-center"
                        },
                        {
                            "data": "userAvatar",
                            "sTitle":"头像图片",
                            'sClass':"text-center",
                             "mRender": function(data, type, full) { 
    				            	return "<img src='" + data + "' height='50px;'/>"; 
    			              } 
                            
                        },
                        {
                            "data": "state",
                            "sTitle":"状态",
                            'sClass':"text-center",
                            "mRender": function(data, type, full) {
                                if(data == 1){
                                    return "<font>正常</font>";
                                }else if(data == 2){
                                    return "<font color='blue'>锁定</font>";
                                }else{
                                	 return "<font color='blue'>未知</font>";
                                }
                            }
                        },
                        {
                            "data": "createMan",
                            "sTitle":"创建人",
                            'sClass':"text-center"
                        },
                        
                        {
                            "data": "createTime",
                            "sTitle":"创建时间",
                            'sClass':"text-center"
                        }
                      
                        <shiro:hasPermission name="account:update or account:delete">
                        ,{"data": "accountId","sTitle":"操作",'sClass':"text-center"}
                        </shiro:hasPermission>
                    ],
                    fnServerParams: function (aoData) {  //查询条件
                        aoData.push({ "name": "phoneNum", "value": $("#phoneNum").val() } );
                        aoData.push({ "name": "sCreateTime", "value": $("#sCreateTime").val() } );
                        aoData.push({ "name": "eCreateTime", "value": $("#eCreateTime").val() } );
                        aoData.push({ "name": "state", "value": $("#state").val() } );
                    },
                    aoColumnDefs : [
                        <shiro:hasPermission name="account:update or account:delete">
                        {"aTargets" :6,"mRender" : function(data, type, row){
                            var edit="",del="";
                            <shiro:hasPermission name="account:update">
                            edit = "<a href='#' onclick='addAndUpdateRow(\""+data+"\")' data-toggle='modal' class='padding-right-small label label-success'><i class='glyphicon glyphicon-edit'></i> 编辑</a>";
                            </shiro:hasPermission>
                            <shiro:hasPermission name="account:delete">
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

            function exportArmour(){
                    var href="export/armour.htm"
                        +"?phoneNum="+$("#phoneNum").val()
                        +"&state="+$("#state").val()
                        +"&sDate="+$("#sCreateTime").val()
                        +"&eDate="+$("#eCreateTime").val();
                    $('#armour_export').attr("href",href);
            }


            //删除受影响的行数
			function deleteRow(id){
				$('#myModal').deleteRow('armourAccount/del.htm?id='+id);
			}

          //增加或者修改受影响的行数
			function addAndUpdateRow(id){
        	 
        	  $('#insertAndUpdate').addAndUpdateRow("armourAccount/addAndUpdateHome.htm?id="+id);
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