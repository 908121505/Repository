<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<shiro:hasPermission name="account:select">
    <div class="content1">
        <div class="header">
            <h1 class="page-title">蜜罐报告列表</h1>
            <ul class="breadcrumb">
                <li>聚立信蜜罐</li>
                <li class="active">蜜罐报告列表</li>
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
                            <div class="input-group-addon">蜜罐ID</div>
                            <input class="form-control" type="text" id="userGridId">
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
                <!-- <div class="col-md-1">
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
                    sAjaxSource:"jxlmgreport/initTable.htm",
                    aoColumns: [
                         {
                            "data": "userGridId",
                            "sTitle":"蜜罐ID",
                            'sClass':"text-center"
                         },
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
                            "sTitle":"生成时间",
                            'sClass':"text-center"
                        },{
                            "data": "authOrg",
                            "sTitle":"机构账号",
                            'sClass':"text-center"
                        },
                        {
                            "data": "remark",
                            "sTitle":"备注",
                            'sClass':"text-center"
                        }
    		            ,{"data": "reportId","sTitle":"操作",'sClass':"text-center"}

                    ],
                    fnServerParams: function (aoData) {  //查询条件
                        aoData.push({ "name": "phoneNum", "value": $("#phoneNum").val() } );
                        aoData.push({"name": "userGridId", "value": $("#userGridId").val()});
                        aoData.push({"name": "sDate", "value": $("#sTime").val()});
	                    aoData.push({"name": "eDate", "value": $("#eTime").val()});
                    },
                    aoColumnDefs : [
                	             	{"aTargets" : 6,"mRender" : function(data, type, row){
                	             	   
                	             		  var  edit="";
                	             		 edit = "<a href='#' onclick='addAndUpdateRow(\""+data+"\")' data-toggle='modal' class='padding-right-small label label-success'><i class='glyphicon glyphicon-edit'></i> 蜜罐报告详情</a>";
                	             		/* detail = "<a href='#' onclick='detailRow(\"" + data + "\")' data-toggle='modal' class='padding-right-small label label-success'><i class='glyphicon glyphicon-edit'></i> 查看</a>"; */

                	             		
                	         		    return  edit;
                	                }
                	             }
                	             ]
                
                });

                $('#query').click(function(){
                    $('#example').dataTable().fnDraw();
                });

            });
            
            function addAndUpdateRow(id) {
                $('#insertAndUpdate').addAndUpdateRow("jxlmgreport/addAndUpdateHome.htm?id=" + id);
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