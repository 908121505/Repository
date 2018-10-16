<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<shiro:hasPermission name="checkBigVAuth:select">
<div class="content1">
    <div class="header">
        <h1 class="page-title">大V声音审核</h1>
        <ul class="breadcrumb">
            <li>用户管理</li>
            <li class="active">大V声音审核</li>
        </ul>
    </div>

    <div class="main-content">
        <div class="row">
            <div class="col-md-2">
                <div class="form-group">
                    <div class="input-group">
                        <div class="input-group-addon">用户昵称</div>
                        <input class="form-control" type="text" id="nickName">
                    </div>
                </div>
            </div>
            <div class="col-md-2">
                <div class="form-group">
                    <div class="input-group">
                        <div class="input-group-addon">用户姓名</div>
                        <input class="form-control" type="text" id="realName">
                    </div>
                </div>
            </div>
            <div class="col-md-2">
                <div class="form-group">
                    <div class="input-group">
                        <div class="input-group-addon">身份证号码</div>
                        <input class="form-control" type="text" id="credentialsNum">
                    </div>
                </div>
            </div>
            <div class="col-md-2">
                <div class="form-group">
                    <div class="input-group">
                        <%-- 0=未认证,1=待审核,2=已通过,3=拒绝 --%>
                        <div class="input-group-addon">声音认证状态</div>
                        <select class="form-control" id="vVoiceStatus" name="vVoiceStatus">
                            <option value="2">待审核</option>
                            <option value="4">已通过</option>
                            <option value="3">拒绝</option>
                            <%--<option value="0">未认证</option>--%>
                        </select>
                    </div>
                </div>
            </div>
            <div class="col-md-2">
                <button type="button" class="btn btn-primary btn-small btn-block" id="query">
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
                sAjaxSource: "checkAuth/initTable.htm",
                aoColumns: [
                    {
                        "data": "nickName",
                        "sTitle": "用户昵称",
                        'sClass': "text-center"
                    },
                    {
                        "data": "realName",
                        "sTitle": "姓名",
                        'sClass': "text-center"
                    },
                    {
                        "data": "credentialsNum",
                        "sTitle": "身份证号",
                        'sClass': "text-center"
                    },
                    {
                        "data": "sex",
                        "sTitle": "性别",
                        'sClass': "text-center",
                        "mRender": function (data, type, full) {
                            if (data == 0) {
                                return "女";
                            } else if (data == 1) {
                                return "男";
                            } else {
                                return "<font color='red'>" + data + "</font>";
                            }
                        }
                    },
                    {
                        "data": "vVoiceUrlTmp",
                        "sTitle": "声音",
                        'sClass': "text-center",
                        "mRender": function (data, type, full) {
                            if(data && data != ''){
                                return "<img src='resources/images/pause.png' height='30px;' onclick='pauseVoiceFn(\"" + data + "\")'/>";
                            }else {
                                return "<font color='red'>文件不存在</font>";
                            }
                        }
                    },
                    {
                        "data": "vVoiceStatus",
                        "sTitle": "状态",
                        'sClass': "text-center",
                        "mRender": function (data, type, full) {
                            if (data == 1) {
                                return "<font color='red'>未认证</font>";
                            } else if (data == 2) {
                                return "<font color='blue'>待审核</font>";
                            } else if (data == 4) {
                                return "<font color='blue'>已通过</font>";
                            } else if (data == 3) {
                                return "<font color='blue'>拒绝</font>";
                            } else {
                                return "<font color='red'>" + data + "</font>";
                            }
                        }
                    }
                    , {"data": "customerId", "sTitle": "操作", 'sClass': "text-center"}
                ],
                fnServerParams: function (aoData) {  //查询条件
                    aoData.push({"name": "nickName", "value": $("#nickName").val()});
                    aoData.push({"name": "realName", "value": $("#realName").val()});
                    aoData.push({"name": "credentialsNum", "value": $("#credentialsNum").val()});
                    aoData.push({"name": "vVoiceStatus", "value": $("#vVoiceStatus").val()});
                },
                aoColumnDefs: [
                    {
                        "aTargets": 6, "mRender": function (data, type, row) {
                            var approved = "", refuse = "";
                            <shiro:hasPermission name="checkBigVVoiceAuth:update">
                            if (row.vVoiceStatus == 2) {
                                approved = "<a href='#' onclick='approvedFn(\"" + row.customerId + "\",\"" + row.vVoiceUrlTmp + "\")' data-toggle='modal' class='padding-right-small label label-success'><i class='glyphicon glyphicon-edit'></i> 通过</a>";
                                refuse = "<a href='#' onclick='refuseFn(\"" + row.customerId + "\",\"" + row.vVoiceUrlTmp + "\")' data-toggle='modal' class='padding-right-small label label-success'><i class='glyphicon glyphicon-edit'></i> 拒绝</a>";
                            }
                            </shiro:hasPermission>
                            return approved + "&nbsp;" + refuse;
                        }
                    }
                ]
            });

            $('#query').click(function () {
                $('#example').dataTable().fnDraw();
            });

        });

        function pauseVoiceFn(src) {
            $("#vVoiceUrlTmp").val(src);
            //弹出一个页面层
            layer.open({
                type: 1,
                title: false,
                area: ['auto', 'auto'],
                shadeClose: false, //点击遮罩关闭
                content: $('#audio_model')
            });
            audioCutFn(src);
        }

        function approvedFn(customerId,vVoiceUrlTmp) {
            if (window.confirm("确认通过审核？")) {
                doCheck(customerId, 4,vVoiceUrlTmp);
            }
        }

        function refuseFn(customerId,vVoiceUrlTmp) {
            if (window.confirm("确认拒绝审核？")) {
                doCheck(customerId, 3,vVoiceUrlTmp);
            }
        }

        function doCheck(customerId, vVoiceStatus,vVoiceUrlTmp) {
            $.ajax({
                url: "checkAuth/saveUpdate.htm",
                async: false,
                dataType: "JSON",
                type: 'post',
                data: {
                    customerId: customerId,
                    vVoiceStatus: vVoiceStatus,
                    vVoiceUrlTmp:vVoiceUrlTmp
                },
                success: function (data) {
                    if (data > 0) {
                        $('#example').dataTable().fnDraw();
                        $.globalMessenger().post({
                            message: '操作成功！',
                            type: 'success',
                            id: "Only-one-message",
                            hideAfter: 2,
                            hideOnNavigate: true
                        });
                    } else {
                        $.globalMessenger().post({message: '处理错误！', type: 'error', hideAfter: 2});
                    }
                },
                error: function (data) {
                    alert("请求出错，请稍后再试！");
                }
            });
        }
    </script>
    <!---dialog选项-->
    <div>
        <jsp:include page="/WEB-INF/views/common/delete_dialog.jsp"/>

        <div class="modal fade" id="checkAuthInsertAndUpdate" tabindex="-1"
             role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static">
        </div>
    </div>
</div>

<div id="audio_model" style="display: none">
    <input type="hidden" id="voiceUrl">
    <div class="wx-audio"></div>
</div>

<link rel="stylesheet" type="text/css" href="resources/plugins/wx-audio/wx-audio.css">
<script type="text/javascript" src="resources/plugins/wx-audio/wx-audio.js" charset="utf-8"></script>
</shiro:hasPermission>