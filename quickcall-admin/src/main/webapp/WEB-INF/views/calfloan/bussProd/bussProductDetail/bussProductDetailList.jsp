<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<shiro:hasPermission name="bussProductDetail:select">
    <div class="content1">
        <div class="header">
            <h1 class="page-title">商户结算详情</h1>
            <ul class="breadcrumb">
                <li>商务后台</li>
                <li class="active">商户结算详情</li>
            </ul>
        </div>
        <shiro:hasPermission name="bussProductDetail:upload">
        <input type="hidden" value="1" id="isAdmin"/>
        </shiro:hasPermission>
        
        <div class="main-content">
            <div class="row">
                <div class="col-md-2">
                    <div class="form-group">
                        <div class="input-group">
                            <div class="input-group-addon">商户名称</div>
                            <input class="form-control" type="text" id="productName1">
                        </div>
                    </div>
                </div>
                <div class="col-md-2">
                    <div class="form-group">
                        <div class="input-group">
                            <div class="input-group-addon">公司名称</div>
                            <input class="form-control" type="text" id="companyName1">
                        </div>
                    </div>
                </div>
                
                  <!-- <div class="col-md-4">
                    <div class="form-group">
                        <div class="input-group">
                            <div class="input-group-addon">上线时间</div>
                            <input class="form-control" type="date" id="onlineTime">
                        </div>
                    </div>
                </div> 
                <div class="col-md-4">
                    <div class="form-group">
                        <div class="input-group">
                            <div class="input-group-addon">下线时间</div>
                            <input class="form-control" type="date" id="offlineTime">
                        </div>
                    </div>
                </div> --> 
               <!--   <div class="col-md-2">
                    <div class="form-group">
                        <div class="input-group">
                            <div class="input-group-addon">在线时长</div>
                            <select class="form-control" id="redio1">
                                <option value="1">>=</option>
                                <option value="2"><=</option>                
                            </select>
                            <input class="form-control" type="text" id="onlineDate">
                        </div>
                    </div>
                </div> -->
                 <div class="col-md-2">
                    <div class="form-group">
                        <div class="input-group">
                            <div class="input-group-addon">归属人</div>
                            <input class="form-control" type="text" id="owner">
                        </div>
                    </div>
                </div>
                
                 <div class="col-md-4">
                    <div class="form-group">
                        <div class="input-group">
                            <div class="input-group-addon">结算录入时间</div>
                            <input class="form-control" type="date" id="entryTime">
                        </div>
                    </div>
                </div> 
                <div class="col-md-4">
                    <div class="form-group">
                        <div class="input-group">
                            <div class="input-group-addon">上次打款时间</div>
                            <input class="form-control" type="date" id="daylyDate">
                        </div>
                    </div>
                </div> 
                <div class="col-md-2">
                    <div class="form-group">
                        <div class="input-group">
                            <div class="input-group-addon">距上次打款时长</div>
                            <select class="form-control" id="redio2">
                                <option value="1">>=</option>
                                <option value="2"><=</option>                
                            </select>
                            <input class="form-control" type="text" id="daylyTime1">
                        </div>
                    </div>
                </div>
              
                <div class="col-md-2">
                    <button type="button" class="btn btn-primary btn-small btn-block"
                            id="query">
                        <i class="glyphicon glyphicon-search"></i> 查询
                    </button>
                </div>
                 <shiro:hasPermission name="bussProductDetail:download">
                 <div class="col-md-1">
                    <div class="form-group">
                        <a class="btn btn-primary btn-small btn-block" id="user_export" onclick="exportUserInfo();">导出</a>
                    </div>
                </div> 
                </shiro:hasPermission>
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
                    sAjaxSource:"bussProductDetail/initTable.htm",
                    aoColumns: [
                    	{
                            sTitle: '序号',
                            data: "no",
                            className: 'text-center whiteSpace',
                            render:function(data,type,row,meta) {
                            	if(data==null||data==''){
                                return meta.row + 1 ;
                            	}else{
                            		return data;
                            	}
                            }
                        },
                    	
                        {
                            "data": "productName",
                            "sTitle":"商户名称",
                            'sClass':"text-center",
                            "mRender": function(data, type, full) {
                            	if(data==''||data==null){
                                return	"--";
                            	}else{
                            		return data;
                            	}
                            }
                        },
                        {
                            "data": "companyName",
                            "sTitle":"公司名称",
                            'sClass':"text-center",
                            "mRender": function(data, type, full) {
                            	if(data==''||data==null){
                                return	"--";
                            	}else{
                            		if(data.length > 6){
										var head =  data.substring(0,6);
                                        return "<font>"+head +"......</font>";
									}else{
										return "<font>"+data+"</font>";
									}
                            	}
                            }
                        },
                        {
                            "data": "companyNature",
                            "sTitle":"公司性质",
                            'sClass':"text-center",
                            "mRender": function(data, type, full) {
                            	if(data==''||data==null){
                                return	"--";
                            	}else{
                            		return data;
                            	}
                            }
                        },
                        {
                            "data": "cpa",
                            "sTitle":"CPA/元",
                            'sClass':"text-center",
                            "mRender": function(data, type, full) {
                            	if(data==''||data==null){
                                return	"--";
                            	}else{
                            		return data.toFixed(2);
                            	}
                            }
                        },
                        {
                            "data": "owner",
                            "sTitle":"归属人",
                            'sClass':"text-center",
                            "mRender": function(data, type, full) {
                            	if(data==''||data==null){
                                return	"--";
                            	}else{
                            		return data;
                            	}
                            }
                        }/* ,
                        {
                            "data": "onlineTime",
                            "sTitle":"上线时间",
                            'sClass':"text-center",
                            "mRender": function(data, type, full) {
                            	if(data==''||data==null){
                                return	"--";
                            	}else{
                            		return data;
                            	}
                            }
                        },
                        {
                            "data": "offlineTime",
                            "sTitle":"下线时间",
                            'sClass':"text-center",
                            "mRender": function(data, type, full) {
                            	if(data==''||data==null){
                                return	"--";
                            	}else{
                            		return data;
                            	}
                            }
                        } */,
                        {
                            "data": "onlineDate",
                            "sTitle":"在线时长/天",
                            'sClass':"text-center",
                            "mRender": function(data, type, full) {
                            	if(data==''||data==null){
                                return	"--";
                            	}else{
                            		return data;
                            	}
                            }
                        },
                        {
                            "data": "daylyDate",
                            "sTitle":"上次打款时间",
                            'sClass':"text-center",
                            "mRender": function(data, type, full) {
                            	if(data==''||data==null){
                                return	"--";
                            	}else{
                            		return data;
                            	}
                            }
                            
                        },
                        {
                            "data": "daylyTime",
                            "sTitle":"距离上次打款时长/天",
                            'sClass':"text-center",
                            "mRender": function(data, type, full) {
                            	if(data==''||data==null){
                                return	"--";
                            	}else{
                            		if(data==-1){
                            			return  '当天';
                            		}else{
                            		return data;
                            		}
                            	}
                            }
                        },
                        {
                            "data": "amountOfIncome",
                            "sTitle":"累计收入/元",
                            'sClass':"text-center",
                            "mRender": function(data, type, full) {
                            	if(data==''||data==null){
                                return	"--";
                            	}else{
                            		return data;
                            	}
                            }
                        },
                        {
                            "data": "settledIncome",
                            "sTitle":"已结算收入/元",
                            'sClass':"text-center",
                            "mRender": function(data, type, full) {
                            	if(data==''||data==null){
                                return	"--";
                            	}else{
                            		return data;
                            	}
                            }
                        },
                        {
                            "data": "amountInArear",
                            "sTitle":"欠款金额/元",
                            'sClass':"text-center",
                            "mRender": function(data, type, full) {
                            	if(data==''||data==null){
                                return	"--";
                            	}else{
                            		return data;
                            	}
                            }
                        },
                        {
                            "data": "reimbursementRate",
                            "sTitle":"回款率",
                            'sClass':"text-center",
                            "mRender": function(data, type, full) {
                            	if(data==''||data==null){
                                return	"--";
                            	}else{
                            		return (data*100).toFixed(2)+"%";
                            	}
                            }
                        },
                        {
                            "data": "entryTime",
                            "sTitle":"结算录入时间",
                            'sClass':"text-center",
                            "mRender": function(data, type, full) {
                            	if(data==''||data==null){
                                return	"--";
                            	}else{
                            		return data;
                            	}
                            }
                            
                        }
                        ,{"data": "id","sTitle":"详情",'sClass':"text-center"}
                        <shiro:hasPermission name="bussProductDetail:delete">
                        ,{"data": "id","sTitle":"操作",'sClass':"text-center"}
                        </shiro:hasPermission> 
                    ],
                    fnServerParams: function (aoData) {  //查询条件
                        aoData.push({ "name": "productName", "value": $("#productName1").val().replace(new RegExp(" ","g"),"") } );
                        aoData.push({ "name": "companyName", "value": $("#companyName1").val().replace(new RegExp(" ","g"),"") } );
                        aoData.push({ "name": "onlineDate", "value": $("#onlineDate").val() } );
	                    aoData.push({"name": "redio1", "value": $("#redio1").val()});
	                    aoData.push({"name": "owner", "value": $("#owner").val().replace(new RegExp(" ","g"),"")});
	                    aoData.push({"name": "entryTime", "value": $("#entryTime").val()});
	                    aoData.push({"name": "daylyDate", "value": $("#daylyDate").val()});
	                    aoData.push({"name": "redio2", "value": $("#redio2").val()});
	                    aoData.push({"name": "daylyTime", "value": $("#daylyTime1").val()});
	                    aoData.push({ "name": "isAdmin", "value": $("#isAdmin").val() } );
                    }
                ,
                aoColumnDefs: [
                 
                    {"aTargets" :14,"mRender" : function(data, type, row){
						var edit="",del="";
					
							del ="<a href='#' onclick='addAndUpdateRow(\""+row.id+"\")' data-toggle='modal' class='padding-right-small label label-success'><i class='glyphicon glyphicon-edit'></i> 详情 </a>";
					if(row.id==null||row.id==''){
						return '--';
					}else{
					
				    return edit+"&nbsp;"+del;
                    }
				}
				}
                    <shiro:hasPermission name=" bussProductDetail:delete">
                    ,  {"aTargets" :15,"mRender" : function(data, type, row){
					var edit="",del="";
					<shiro:hasPermission name="bussProductDetail:delete">
						del ="<a href='#' onclick='deleteRow(\""+row.id+"\")' data-toggle='modal' class='label label label-danger'><i class='glyphicon glyphicon-trash'></i> 删除 </a>";
					</shiro:hasPermission>
					if(row.id==null||row.id==''){
						return '--';
					}else{
					
				    return edit+"&nbsp;"+del;
                    }
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
                var href="export/bussProductDetail.htm"
                        +"?productName="+encodeURI($("#productName1").val())
                        +"&companyName="+encodeURI($("#companyName1").val())
                        +"&onlineDate="+$("#onlineDate").val()
                        +"&redio1="+$("#redio1").val()
                        +"&owner="+$("#owner").val()
                        +"&entryTime="+$("#entryTime").val()
             		    +"&daylyDate="+$("#daylyDate").val()
              		    +"&redio2="+$("#redio2").val()
              		    +"&daylyTime="+$("#daylyTime1").val();
                if($("#isAdmin").val()!=undefined){
                	href+="&isAdmin="+$("#isAdmin").val();
                }
                $('#user_export').attr("href",href);
            }
            
             //删除受影响的行数
            function deleteRow(id) {
                console.log(id);
                $('#myModal').deleteRow('bussProductDetail/del.htm?id=' + id);
            }
            function addAndUpdateRow(id) {
                $('#insertAndUpdate').addAndUpdateRow("bussProductDetail/addAndUpdateHome.htm?id=" + id);
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