<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<link type="text/css" rel="stylesheet" href="resources/plugins/viewer/viewer.min.css" media="screen"/>
<div class="content1">
    <div class="header">
        <h1 class="page-title">优惠券管理</h1>
        <ul class="breadcrumb">
            <li>活动管理</li>
            <li class="active">优惠券管理</li>
        </ul>
    </div>
    <div class="main-content">
        <div class="row">
            <%--<div class="col-md-2">
                <div class="form-group">
                    <div class="input-group">
                        <div class="input-group-addon">用户id</div>
                        <input class="form-control" type="text" id="customerId">
                    </div>
                </div>
            </div>--%>
            <div class="col-md-2">
                <div class="form-group">
                    <div class="input-group">
                        <div class="input-group-addon">券名称</div>
                        <input class="form-control" type="text" id="couponNameQuery">
                    </div>
                </div>
            </div>
            <%--<div class="col-md-2">
                <div class="form-group">
                    <div class="input-group">
                        <div class="input-group-addon">审核状态</div>
                        <select class="form-control" id="auditStatus">
                            <option value="">--请选择--</option>
                            <option value="0">待审核</option>
                            <option value="1">已通过</option>
                            <option value="2">已拒绝</option>
                        </select>
                    </div>
                </div>
            </div>--%>

            <div class="col-md-2">
                <button type="button" class="btn btn-primary btn-small btn-block" id="query">
                    <i class="glyphicon glyphicon-search"></i> 查询
                </button>
            </div>

            <div class="col-md-2">
                <button type="button" class="btn btn-info btn-small btn-block"
                        onclick="addAndUpdateRow(0)">
                    <i class="glyphicon glyphicon-plus"></i> 新增
                </button>
            </div>

            <div class="row">
                <div class="col-md-2">
                    <div class="form-group">
                        <div class="input-group">
                            <div class="input-group-addon">有效时间</div>
                            <input class="form-control" style="width:200px" type="text" id="sTime" onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'1950-01-01'})">
                            <div class="input-group-addon">到</div>
                            <input class="form-control"  style="width:200px"  type="text" id="eTime" onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'1950-01-01'})">
                        </div>
                    </div>
                </div>
            </div>
            <table id="example" class="table"></table>

        </div>

        <script>
            //表格的初始化
            $(document).ready(function () {
                var table = $('#example').initTable({
                    sAjaxSource: "coupon/initTable.htm",
                    fnServerParams: function (aoData) {  //查询条件
                        aoData.push({"name": "couponName", "value": $("#couponNameQuery").val().trim()});
                        aoData.push({"name": "startTime", "value": $("#sTime").val()});
                        aoData.push({"name": "endTime", "value": $("#eTime").val()});
                    },
                    aoColumnDefs: [{}],
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
                            "data": "couponId",
                            "sTitle": "券编号",
                            'sClass': "text-center",
                            "bVisible": false //此列不显示
                        },
                        {
                            "data": "couponName",
                            "sTitle": "券的名称",
                            'sClass': "text-center",
                        },
                        {
                            "data": "isPermanent",
                            "sTitle": "是否永久",
                            'sClass': "text-center",
                            "mRender": function (data, type, full) {
                                if (data == 0) {
                                    return "不是永久";
                                } else if (data == 1) {
                                    return '永久';
                                }
                            }
                        },
                        {
                            "data": "couponType",
                            "sTitle": "券类型",
                            'sClass': "text-center",
                            "mRender": function (data, type, full) {
                                if (data == 0) {
                                    return "抵扣券";
                                } else if (data == 1) {
                                    return '打折券';
                                }
                            }
                        },
                       /* {
                            "data": "couponCode",
                            "sTitle": "券码",
                            'sClass': "text-center",
                        },*/
                        {
                            "data": "startTime",
                            "sTitle": "生效时间",
                            'sClass': "text-center",

                        },
                        {
                            "data": "endTime",
                            "sTitle": "结束时间",
                            'sClass': "text-center",

                        },
                        {
                            "data": "couponPrice",
                            "sTitle": "券的价值",
                            'sClass': "text-center",
                        },
                        {
                            "data": "activityName",
                            "sTitle": "活动名称",
                            'sClass': "text-center",
                        },
                        {
                            "data": "activityCode",
                            "sTitle": "活动编码",
                            'sClass': "text-center",
                        },
                        /*{
                            "data": "auditStatus",
                            "sTitle": "审核状态",
                            'sClass': "text-center",
                            "mRender": function (data, type, full) {
                                if (data == 0) {
                                    return "待审核";
                                } else if (data == 1) {
                                    return '已通过';
                                } else if (data == 2) {
                                    return '已拒绝';
                                }
                            }
                        },*/
                        /*{
                            "data": "type",
                            "sTitle":"照片类型",
                            'sClass':"text-center",
                            "mRender" : function(data, type,full) {
                                if (data == 0) {
                                    return "形象照";
                                } else if(data == 1){
                                    return '头像照';
                                }else if(data == 2){
                                    return '声鉴卡';
                                }
                            }
                        },*/


                        /*{
                            "data": "createMan",
                            "sTitle":"创建人",
                            'sClass':"text-center",
                        },*/
                        {
                            "data": "createTime",
                            "sTitle": "创建时间",
                            'sClass': "text-center",

                        },
                        /*{
                            "data": "modifyMan",
                            "sTitle":"修改人",
                            'sClass':"text-center",
                        },*/
                        {
                            "data": "modifyTime",
                            "sTitle": "修改时间",
                            'sClass': "text-center",

                        },

                        {
                            //"data" : "id",
                            "sTitle": "操作",
                            'sClass': "text-center",
                            "mRender": function (data, type, row) {
                                var edit = "", del = "";
                                edit = "<a href='#' onclick='addAndUpdateRow(\"" + row.couponId + "\")' data-toggle='modal' class='padding-right-small label label-success'><i class='glyphicon glyphicon-edit'></i>编辑</a>";
                                del = "<a href='#' onclick='deleteRow(\"" + row.couponId + "\")' data-toggle='modal' class='label label label-danger'><i class='glyphicon glyphicon-trash'></i> 删除 </a>";
                                return edit + "&nbsp;" + del;
                            }
                        }
                    ]

                });

                $('#query').click(function () {
                    $('#example').dataTable().fnDraw();
                });

            });

            //增加或者修改受影响的行数
            function addAndUpdateRow(id){
                $('#insertAndUpdate').addAndUpdateRow("coupon/addAndUpdateHome.htm?id="+id);
            }

            //删除禁用
            function deleteRow(id) {
                $('#myModal').deleteRow('coupon/del.htm?id=' + id);
            }


        </script>
        <!---dialog选项-->
        <div>
            <jsp:include page="/WEB-INF/views/common/delete_dialog.jsp" />
            <jsp:include page="/WEB-INF/views/common/addupdate_dialog.jsp" />
            <!-- 模态框（Modal） -->
            <%--<div class="modal fade" id="approveRow" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true"></div>--%>
        </div>
    </div>
</div>
<script type="text/javascript" src="resources/plugins/viewer/viewer.min.js"></script>
<script type="text/javascript" src="resources/js/My97DatePicker/WdatePicker.js" charset="utf-8"></script>