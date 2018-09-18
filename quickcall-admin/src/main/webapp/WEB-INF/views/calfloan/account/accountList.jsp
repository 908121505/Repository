<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<shiro:hasPermission name="account:select">
    <div class="content1">
        <div class="header">
            <h1 class="page-title">用户列表</h1>
            <ul class="breadcrumb">
                <li>用户管理</li>
                <li class="active">用户列表</li>
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
                            <div class="input-group-addon">昵称</div>
                            <input class="form-control" type="text" id="nickname">
                        </div>
                    </div>
                </div>
                <div class="col-md-2">
                    <div class="form-group">
                        <div class="input-group">
                            <div class="input-group-addon">状态</div>
                            <select class="form-control" id="state">
                                <option value="">--请选择--</option>
                                <option value="1">正常</option>
                                <option value="2">锁定</option>
                                <option value="3">冻结</option>
                                <option value="6">限制社区发帖</option>
                                <option value="7">限制社区评论</option>
                                <option value="8">限制社区发帖及评论</option> 
                                <option value="9">限制产品详情评论</option>  
                                <option value="10">限制社区发帖及评论及产品评论</option>                
                            </select>
                        </div>
                    </div>
                </div>
                
                 <div class="col-md-2">
                    <div class="form-group">
                        <div class="input-group">
                            <div class="input-group-addon">渠道</div>
                            <input class="form-control" type="text" id="appChannelName">
                        </div>
                    </div>
                </div>
                <div class="col-md-4">
                    <div class="form-group">
                        <div class="input-group">
                            <div class="input-group-addon">时间</div>
                            <input class="form-control" type="date" id="sTime">
                            <div class="input-group-addon">到</div>
                            <input class="form-control" type="date" id="eTime">
                        </div>
                    </div>
                </div> 
                <div class="col-md-2">
                    <button type="button" class="btn btn-primary btn-small btn-block"
                            id="query">
                        <i class="glyphicon glyphicon-search"></i> 查询
                    </button>
                </div>
               <!--  <div class="col-md-1">
                    <div class="form-group">
                        <a class="btn btn-primary btn-small btn-block" id="user_export" onclick="exportUserInfo();">导出用户信息</a>
                    </div>
                </div> -->
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
                    sAjaxSource:"account/initTable.htm",
                    aoColumns: [
                        {
                            "data": "phoneNum",
                            "sTitle":"手机号",
                            'sClass':"text-center"
                        },
                        {
                            "data": "accountId",
                            "sTitle":"用户ID",
                            'sClass':"text-center"
                        },
                        {
                            "data": "createTime",
                            "sTitle":"注册时间",
                            'sClass':"text-center",
                            "mRender": function(data, type, full) {
                                return	Format(new Date(data),"yyyy-MM-dd HH:mm:ss")
                            }
                        },
                        {
                            "data": "phoneType",
                            "sTitle":"手机型号",
                            'sClass':"text-center"
                        },
                        {
                            "data": "source",
                            "sTitle":"登录类型",
                            'sClass':"text-center",
                            "mRender": function(data, type, full) {
                                if(data == 1){
                                    return "<font>iPhone</font>";
                                }else if(data == 2){
                                    return "<font>iPad</font>";
                                }else if(data == 3){
                                    return "<font>Android</font>";
                                }else if(data == 4){
                                    return "<font>微信</font>";
                                }else if(data == 5){
                                    return "<font>H5</font>";
                                }else{
                                	return "<font>未知</font>";
                                }
                            }
                        },
                        {
                            "data": "appChannelName",
                            "sTitle":"渠道",
                            'sClass':"text-center"
                        },
                        {
                            "data": "lastLoginTime",
                            "sTitle":"最近登录时间",
                            'sClass':"text-center",
                            "mRender": function(data, type, full) {
                                return	Format(new Date(data),"yyyy-MM-dd HH:mm:ss")
                            }
                        },
                        {
                            "data": "modifyMan",
                            "sTitle":"修改人",
                            'sClass':"text-center"
                        },
                        {
                            "data": "modifyTime",
                            "sTitle":"修改时间",
                            'sClass':"text-center"
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
                                }else if(data == 3){
                                    return "<font color='red'>冻结</font>";
                                }else if(data == 6){
                                    return "<font color='red'>限制社区发帖</font>";
                                }else if(data == 7){
                                    return "<font color='red'>限制社区评论</font>";
                                }else if(data == 8){
                                    return "<font color='red'>限制发帖及评论</font>";
                                }else if(data == 9){
                                    return "<font color='red'>限制产品详情评论</font>";
                                }else if(data == 10){
                                    return "<font color='red'>限制社区发帖及评论及产品评论</font>";
                                }else{
                                    return "<font>未知</font>";
                                }
                            }
                        },
                        {
                            "data": "userInstallSum",
                            "sTitle":"贷款产品装机量",
                            'sClass':"text-center"
                        },
                        {
                            "data": "remark",
                            "sTitle":"备注",
                            'sClass':"text-center"
                        }
                        <shiro:hasPermission name="account:update or account:delete">
                        ,{"data": "accountId","sTitle":"操作",'sClass':"text-center"}
                        </shiro:hasPermission> 
                    ],
                    fnServerParams: function (aoData) {  //查询条件
                        aoData.push({ "name": "phoneNum", "value": $("#phoneNum").val() } );
                        aoData.push({ "name": "nickname", "value": $("#nickname").val() } );
                        aoData.push({ "name": "state", "value": $("#state").val() } );
                        aoData.push({ "name": "appChannelName", "value": $("#appChannelName").val() } );
                        aoData.push({"name": "sDate", "value": $("#sTime").val()});
	                    aoData.push({"name": "eDate", "value": $("#eTime").val()});
                    }
                ,
                    aoColumnDefs : [
                         <shiro:hasPermission name="account:update or account:delete"> 
                        {"aTargets" :12,"mRender" : function(data, type, row){
                            var edit="",del="",comment;
                            <shiro:hasPermission name="account:update">
                            edit = "<a href='#' onclick='addAndUpdateRow(\""+row.accountId+"\")' data-toggle='modal' class='padding-right-small label label-success'><i class='glyphicon glyphicon-edit'></i> 编辑权限</a>";
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
            function exportUserInfo(){
                var href="export/user.htm"
                        +"?phoneNum="+$("#phoneNum").val()
                        +"&nickname="+$("#nickname").val()
                        +"&state="+$("#state").val()
                        +"&acName="+$("#appChannelName").val()
                        +"&sDate="+$("#sTime").val()
                        +"&eDate="+$("#eTime").val();
                $('#user_export').attr("href",href);
            }
            
          
            function addAndUpdateRow(id) {
                $('#insertAndUpdate').addAndUpdateRow("account/addAndUpdateHome.htm?accountId=" + id);
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