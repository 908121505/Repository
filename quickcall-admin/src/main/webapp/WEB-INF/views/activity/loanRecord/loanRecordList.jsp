<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<shiro:hasPermission name="loanRecord:select">
    <div class="content1">
        <div class="header">
            <h1 class="page-title">贷款记录列表</h1>
            <ul class="breadcrumb">
                <li>贷款记录</li>
                <li class="active">贷款记录列表</li>
            </ul>
        </div>
        <div class="main-content">
            <div class="row">
                <div class="col-md-2">
                    <div class="form-group">
                        <div class="input-group">
                            <div class="input-group-addon">手机号</div>
                            <input class="form-control" type="text" id="phoneNum">
                        </div>
                    </div>
                </div>
                <div class="col-md-2">
                    <div class="form-group">
                        <div class="input-group">
                            <div class="input-group-addon">状态</div>
                                <select class="form-control" id="state">
                                    <option value="">- - -请选择- - -</option>
                                    <option value="1">有效</option>
                                    <option value="2">无效</option>
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
                <div class="col-md-1">
                    <div class="form-group">
                        <a class="btn btn-primary btn-small btn-block" id="loan_import">导入贷款记录</a>
                    </div>
                </div>
            </div>
            <table id="example" class="table"></table>
        </div>
        <script>
        function Format(now,mask)
		{
		    var d = now;
		    var zeroize = function (value, length)
		    {
		        if (!length) length = 2;
		        value = String(value);
		        for (var i = 0, zeros = ''; i < (length - value.length); i++)
		        {
		            zeros += '0';
		        }
		        return zeros + value;
		    };
		 
		    return mask.replace(/"[^"]*"|'[^']*'|\b(?:d{1,4}|m{1,4}|yy(?:yy)?|([hHMstT])\1?|[lLZ])\b/g, function ($0)
		    {
		        switch ($0)
		        {
		            case 'd': return d.getDate();
		            case 'dd': return zeroize(d.getDate());
		            case 'ddd': return ['Sun', 'Mon', 'Tue', 'Wed', 'Thr', 'Fri', 'Sat'][d.getDay()];
		            case 'dddd': return ['Sunday', 'Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday'][d.getDay()];
		            case 'M': return d.getMonth() + 1;
		            case 'MM': return zeroize(d.getMonth() + 1);
		            case 'MMM': return ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec'][d.getMonth()];
		            case 'MMMM': return ['January', 'February', 'March', 'April', 'May', 'June', 'July', 'August', 'September', 'October', 'November', 'December'][d.getMonth()];
		            case 'yy': return String(d.getFullYear()).substr(2);
		            case 'yyyy': return d.getFullYear();
		            case 'h': return d.getHours() % 12 || 12;
		            case 'hh': return zeroize(d.getHours() % 12 || 12);
		            case 'H': return d.getHours();
		            case 'HH': return zeroize(d.getHours());
		            case 'm': return d.getMinutes();
		            case 'mm': return zeroize(d.getMinutes());
		            case 's': return d.getSeconds();
		            case 'ss': return zeroize(d.getSeconds());
		            case 'l': return zeroize(d.getMilliseconds(), 3);
		            case 'L': var m = d.getMilliseconds();
		                if (m > 99) m = Math.round(m / 10);
		                return zeroize(m);
		            case 'tt': return d.getHours() < 12 ? 'am' : 'pm';
		            case 'TT': return d.getHours() < 12 ? 'AM' : 'PM';
		            case 'Z': return d.toUTCString().match(/[A-Z]+$/);
		            // Return quoted strings with the surrounding quotes removed
		            default: return $0.substr(1, $0.length - 2);
		        }
		    });
		};
        
            //表格的初始化
            $(document).ready(function() {
                var table = $('#example').initTable({
                    sAjaxSource: "loanRecord/initTable.htm",
                    aoColumns: [
                        {
                            "data": "phoneNum",
                            "sTitle":"手机号",
                            'sClass':"text-center"
                        },
                        {
                            "data": "idCard",
                            "sTitle":"身份证",
                            'sClass':"text-center"
                        },
                        {
                            "data": "state",
                            "sTitle":"贷款状态",
                            'sClass':"text-center",
                            "mRender": function (data, type, full) {
                                if (data == 1) {
                                    return "<font >有效</font>";
                                } else {
                                    return "<font >无效</font>";
                                }
                            }
                        },
                        {
                            "data": "loanQuota",
                            "sTitle":"贷款额度",
                            'sClass':"text-center"
                        },
                        {
                            "data": "platformName",
                            "sTitle":"平台名称",
                            'sClass':"text-center"
                        },
                        {
                            "data": "batchNumber",
                            "sTitle":"批次号",
                            'sClass':"text-center"
                        },
                        {
                            "data": "createTime",
                            "sTitle":"导入时间",
                            'sClass':"text-center",
                            "mRender": function(data, type, full) { 
  			                  return	Format(new Date(data),"yyyy-MM-dd HH:mm:ss")
                            }
                        },
                        {
                            "data": "remark",
                            "sTitle":"备注",
                            'sClass':"text-center"
                        }
                        <shiro:hasPermission name="loanRecord:update or loanRecord:delete">
                        ,{"data": "loanRecordId","sTitle":"操作",'sClass':"text-center"}
                        </shiro:hasPermission>
                    ],
                    fnServerParams: function (aoData) {  //查询条件
                        aoData.push({ "name": "phoneNum", "value": $("#phoneNum").val() } );
                        aoData.push({ "name": "state", "value": $("#state").val() } );
                    },
                    aoColumnDefs : [
                        <shiro:hasPermission name="loanRecord:update or loanRecord:delete">
                        {"aTargets" :8,"mRender" : function(data, type, row){
                            var edit="",del="";
                            <shiro:hasPermission name="loanRecord:update">
                            edit = "<a href='#' onclick='addAndUpdateRow(\""+data+"\")' data-toggle='modal' class='padding-right-small label label-success'><i class='glyphicon glyphicon-edit'></i> 编辑</a>";
                            </shiro:hasPermission>
                            <shiro:hasPermission name="loanRecord:delete">
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

            $('#a_cancel').click(function () {
                $("#import_view").hide();
                $("#fileupload_input").val('');
            });

            $('#fileupload_input').change(function () {
                if ($(this).val() == '') {
                    alert("未选择任何文件");
                    return;
                } else if (!/\.(XLSX)$/.test($(this).val().toUpperCase())) {
                    alert("文件格式错误");
                    $("#fileupload_input").val('');
                    return;
                }
                $('#importForm').ajaxSubmit({
                    type: "POST",
                    dataType: "json",
                    url: "import/loanRecord.htm",
                    success: function (data) {
                        if (data >= 0) {
                            $("#fileupload_input").val('');
                            $.globalMessenger().post({
                                message: '上传成功！',
                                type: 'success',
                                id: "Only-one-message",
                                hideAfter: 2,
                                hideOnNavigate: true
                            });
                        } else {
                            $("#fileupload_input").val('');
                            $.globalMessenger().post({
                                message: '上传失败：'+data,
                                type: 'error',
                                id: "Only-one-message",
                                hideAfter: 2,
                                hideOnNavigate: true
                            });
                        }
                    },
                    error:function(errdata){
                        $("#fileupload_input").val('');
                        alert("系统错误");
                    }
                });
            });

            $('#loan_import').click(function(){
                $('#import_view').show();
            });

            //删除受影响的行数
            function deleteRow(id){
                $('#myModal').deleteRow('loanRecord/del.htm?id='+id);
            }
            //增加或者修改受影响的行数
            function addAndUpdateRow(id){
                $('#insertAndUpdate').addAndUpdateRow("loanRecord/addAndUpdateHome.htm?id="+id);
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

        <div class="modal" id="import_view" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h3 id="myModalLabel">请选择导入文件(.xlsx格式)</h3>
                    </div>
                    <div class="modal-body">
                        <form id="importForm" enctype="multipart/form-data">
                            <input type="file" id="fileupload_input" name="file">
                        </form>
                    </div>
                    <div class="modal-footer">
                        <button class="btn btn-default" id="a_cancel" data-dismiss="modal">关闭</button>
                    </div>
                </div>
            </div>
        </div>

    </div>
</shiro:hasPermission>